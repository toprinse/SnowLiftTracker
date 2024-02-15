package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import fr.esisar.snowlifttracker.dto.DTOAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTONumMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.mapper.plain.PlainMeasureMapper;
import fr.esisar.snowlifttracker.mapper.MeasureMapper;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.Measure;
import fr.esisar.snowlifttracker.model.NumMeasure;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@Path("/Measure")
public class MeasureResource {
    
    @Inject
    PlainMeasureMapper mapper;
    @Inject 
    MeasureMapper mapper_;


    @GET
    @Operation(summary = "Return every measure")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensor.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no measure")
    public Response getAllMeasure(){
        List<Measure> measureList = Measure.listAll();
        List<DTOPlainMeasure> dtoPlainMeasureList = measureList.stream()
            .map(Measure -> mapper.fromMeasureToDtoPlainMeasure(Measure))
            .collect(Collectors.toList());

        return Response.ok(dtoPlainMeasureList).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a measure with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainSensor.class)))
    @APIResponse(responseCode = "204", description = "There is no measure with given identifier")
    public Response getMeasure(@PathParam("id") Long id){
        Measure measure = Measure.findById(id);

        if(measure == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromMeasureToDtoPlainMeasure(measure)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a measure")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensor.class)))
    @APIResponse(responseCode = "404", description = "There is no measure with given identifier")
    public Response deleteMeasure(@PathParam("id") Long id){
        Measure entity = Measure.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }

    /************************************************** ANALOG MEASURE METHOD **********************************************************/

    @GET
    @Path("/analog")
    @Operation(summary = "Return every analogmeasure")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogMeasure.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no AnalogMeasure")
    public Response getAllAnalogMeasure(@DefaultValue("false") @QueryParam("withSensor") Boolean withSensor){
        List<AnalogMeasure> analogMeasureList = AnalogMeasure.listAll();

        if(withSensor){
            List<DTOAnalogMeasure> dtoAnalogSensorList = analogMeasureList.stream()
                .map(analogMeasure -> mapper_.fromAnalogMeasureToDtoAnalogMeasure(analogMeasure))
                .collect(Collectors.toList());
            return Response.ok(dtoAnalogSensorList).build();
        }
        
        List<DTOPlainAnalogMeasure> dtoPlainAnalogSensorList = analogMeasureList.stream()
            .map(analogMeasure -> mapper.fromAnalogMeasureToDtoPlainAnalogMeasure(analogMeasure))
            .collect(Collectors.toList());
        return Response.ok(dtoPlainAnalogSensorList).build();
    }

    @GET
    @Path("/analog/{id}")
    @Operation(summary = "return a analogmeasure with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainAnalogMeasure.class)))
    @APIResponse(responseCode = "204", description = "There is no analogmeasure with given identifier")
    public Response getAnalogMeasure(@PathParam("id") Long id){
        AnalogMeasure analogMeasure = AnalogMeasure.findById(id);

        if(analogMeasure == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromAnalogMeasureToDtoPlainAnalogMeasure(analogMeasure)).build();
    }

    @POST
    @Path("/analog")
    @Transactional
    @Operation(summary = "Create a new analogmeasure")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogMeasure.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createAnalogMeasure(@Valid DTOPlainAnalogMeasure dtoPlainAnalogMeasure){
        AnalogMeasure analogMeasure = mapper.toAnalogMeasureFromDtoPlainAnalogMeasure(dtoPlainAnalogMeasure);
        analogMeasure.id = null;
        analogMeasure.timestamp = dtoPlainAnalogMeasure.timestamp;
        analogMeasure.analogData = dtoPlainAnalogMeasure.analogData;
        analogMeasure.persist();

        return Response.status(Status.CREATED).entity(mapper.fromAnalogMeasureToDtoPlainAnalogMeasure(analogMeasure)).build();
    }

    @PUT
    @Path("/analog")
    @Transactional
    @Operation(summary = "Update an existing analogmeasure")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogMeasure.class)))
    @APIResponse(responseCode = "404", description = "There is no analogmeasure with given identifier")
    public Response updateAnalogMeasure(@Valid DTOPlainAnalogMeasure dtoPlainAnalogMeasure){
        AnalogMeasure analogMeasure = mapper.toAnalogMeasureFromDtoPlainAnalogMeasure(dtoPlainAnalogMeasure);
        AnalogMeasure entity = AnalogMeasure.findById(analogMeasure.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.analogData = analogMeasure.analogData;
        entity.timestamp = analogMeasure.timestamp;
        entity.analogSensor = analogMeasure.analogSensor;

        return Response.ok(mapper.fromAnalogMeasureToDtoPlainAnalogMeasure(entity)).build();
    }

    @DELETE
    @Path("/analog/{id}")
    @Transactional
    @Operation(summary = "Delete a analogmeasure")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogMeasure.class)))
    @APIResponse(responseCode = "404", description = "There is no analogmeasure with given identifier")
    public Response deleteAnalogMeasure(@PathParam("id") Long id){
        AnalogMeasure entity = AnalogMeasure.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }

    /**************************************************** NUM MEASURE METHOD ********************************************************/

    @GET
    @Path("/num")
    @Operation(summary = "Return every nummeasure")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumMeasure.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no NumMeasure")
    public Response getAllNumMeasure(@DefaultValue("false") @QueryParam("withSensor") Boolean withSensor){
        List<NumMeasure> numMeasureList = NumMeasure.listAll();

        if(withSensor){
            List<DTONumMeasure> dtoNumMeasureList = numMeasureList.stream()
                .map(NumMeasure -> mapper_.fromNumMeasureToDtoNumMeasure(NumMeasure))
                .collect(Collectors.toList());
            return Response.ok(dtoNumMeasureList).build();
        }

        List<DTOPlainNumMeasure> dtoPlainNumMeasureList = numMeasureList.stream()
            .map(NumMeasure -> mapper.fromNumMeasureToDtoPlainNumMeasure(NumMeasure))
            .collect(Collectors.toList());
        return Response.ok(dtoPlainNumMeasureList).build();
    }

    @GET
    @Path("/num/{id}")
    @Operation(summary = "return a nummeasure with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainNumMeasure.class)))
    @APIResponse(responseCode = "204", description = "There is no nummeasure with given identifier")
    public Response getNumMeasure(@PathParam("id") Long id){
        NumMeasure numMeasure = NumMeasure.findById(id);

        if(numMeasure == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromNumMeasureToDtoPlainNumMeasure(numMeasure)).build();
    }

    @POST
    @Path("/num")
    @Transactional
    @Operation(summary = "Create a new nummeasure")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumMeasure.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createNumMeasure(@Valid DTOPlainNumMeasure dtoPlainNumMeasure){
        NumMeasure numMeasure = mapper.toNumMeasureFromDtoPlainNumMeasure(dtoPlainNumMeasure);
        numMeasure.id = null;
        numMeasure.numData = dtoPlainNumMeasure.numData;
        numMeasure.timestamp = dtoPlainNumMeasure.timestamp;
        numMeasure.persist();

        return Response.status(Status.CREATED).entity(mapper.fromNumMeasureToDtoPlainNumMeasure(numMeasure)).build();
    }

    @PUT
    @Path("/num")
    @Transactional
    public Response updateNumMeasure(@Valid DTOPlainNumMeasure dtoPlainNumMeasure){
        NumMeasure numMeasure = mapper.toNumMeasureFromDtoPlainNumMeasure(dtoPlainNumMeasure);
        NumMeasure entity = NumMeasure.findById(numMeasure.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.numData = numMeasure.numData;
        entity.timestamp = dtoPlainNumMeasure.timestamp;

        return Response.ok(mapper.fromNumMeasureToDtoPlainNumMeasure(entity)).build();
    }

    @DELETE
    @Path("/num/{id}")
    @Transactional
    @Operation(summary = "Delete a nummeasure")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumMeasure.class)))
    @APIResponse(responseCode = "404", description = "There is no nummeasure with given identifier")
    public Response deleteNumMeasure(@PathParam("id") Long id){
        NumMeasure entity = NumMeasure.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }
}
