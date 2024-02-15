package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import fr.esisar.snowlifttracker.dto.DTOAnalogSensor;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithSensorType;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithSkiLift;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithUnit;
import fr.esisar.snowlifttracker.dto.DTONumSensor;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithNumMeasure;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithSensorType;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithSkiLift;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithState;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.mapper.SensorMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumSensor;
import fr.esisar.snowlifttracker.model.Sensor;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/Sensor")
public class SensorResource {
    
    @Inject
    PlainSensorMapper mapper;
    @Inject 
    SensorMapper mapper_;

    @GET
    @Operation(summary = "Return every sensor")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensor.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no Sensor")
    public Response getAllSensor(){
        List<Sensor> sensorList = Sensor.listAll();
        List<DTOPlainSensor> dtoPlainSensorList = sensorList.stream()
            .map(Sensor -> mapper.fromSensorToDtoPlainSensor(Sensor))
            .collect(Collectors.toList());

        return Response.ok(dtoPlainSensorList).build();
    }    
    
    // @GET
    // @Operation(summary = "Return every sensor")
    // @APIResponse(responseCode = "200",
    //             content = @Content(mediaType = MediaType.APPLICATION_JSON, 
    //                                 schema = @Schema(implementation = DTOPlainSensor.class,
    //                                                 type= SchemaType.ARRAY)))
    // @APIResponse(responseCode = "204", description = "There is no Sensor")
    // public Response getAllSensor(){
    //     List<Sensor> sensorList = Sensor.listAll();
    //     List<DTOPlainSensor> dtoPlainSensorList = sensorList.stream()
    //         .map(Sensor -> mapper.fromSensorToDtoPlainSensor(Sensor))
    //         .collect(Collectors.toList());

    //     return Response.ok(dtoPlainSensorList).build();
    // }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a sensor with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainSensor.class)))
    @APIResponse(responseCode = "204", description = "There is no sensor with given identifier")
    public Response getSensor(@PathParam("id") Long id){
        Sensor sensor = Sensor.findById(id);

        if(sensor == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromSensorToDtoPlainSensor(sensor)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a sensor")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensor.class)))
    @APIResponse(responseCode = "404", description = "There is no sensor with given identifier")
    public Response deleteSensor(@PathParam("id") Long id){
        Sensor entity = Sensor.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }

    /************************************************** ANALOG SENSOR METHOD **********************************************************/

    @GET
    @Path("/analog")
    @Operation(summary = "Return every analogsensor")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensor.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no AnalogSensor")
    public Response getAllAnalogSensor(@DefaultValue("false") @QueryParam("withUnit") Boolean withUnit,
                                       @DefaultValue("false") @QueryParam("withMeasure") Boolean withMeasure,
                                       @DefaultValue("false") @QueryParam("withType") Boolean withType,
                                       @DefaultValue("false") @QueryParam("withSkiLift") Boolean withSkiLift){
        List<AnalogSensor> analogSensorList = AnalogSensor.listAll();
        // We should implement a treatment logic        
        if(withUnit && withMeasure && withType && withSkiLift){
                List<DTOAnalogSensor> dtoAnalogSensors = analogSensorList.stream()
                    .map(analogSensor -> mapper_.fromAnalogSensorToDtoAnalogSensor(analogSensor))
                    .collect(Collectors.toList());
                return Response.ok(dtoAnalogSensors).build();
        }
        else if (withUnit){
            List<DTOAnalogSensorWithUnit> dtoAnalogSensorWithUnits = analogSensorList.stream()
                .map(analogSensor -> mapper_.fromAnalogMeasureToDtoAnalogSensorWithUnit(analogSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoAnalogSensorWithUnits).build();
        }
        else if(withMeasure){
            List<DTOAnalogSensorWithAnalogMeasure> dtoAnalogSensorWithAnalogMeasures = analogSensorList.stream()
                .map(analogSensor -> mapper_.fromAnalogMeasureToDtoAnalogSensorWithAnalogMeasure(analogSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoAnalogSensorWithAnalogMeasures).build();
        }
        else if(withType){
            List<DTOAnalogSensorWithSensorType> dtoAnalogSensorWithSensorTypes = analogSensorList.stream()
                .map(analogSensor -> mapper_.fromAnalogSensorToDtoAnalogSensorWithSensorType(analogSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoAnalogSensorWithSensorTypes).build();
        }
        else if(withSkiLift){
            List<DTOAnalogSensorWithSkiLift> dtoAnalogSensorWithSkiLifts = analogSensorList.stream()
                .map(analogSensor -> mapper_.fromAnalogSensorToDtoAnalogSensorWithSkiLift(analogSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoAnalogSensorWithSkiLifts).build();
        }
        else{
            List<DTOPlainAnalogSensor> dtoPlainAnalogSensorList = analogSensorList.stream()
                .map(analogSensor -> mapper.fromAnalogSensorToDtoPlainAnalogSensor(analogSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoPlainAnalogSensorList).build();
        }
    }

    @GET
    @Path("/analog/{id}")
    @Operation(summary = "return a analogsensor with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainAnalogSensor.class)))
    @APIResponse(responseCode = "204", description = "There is no analogsensor with given identifier")
    public Response getAnalogSensor(@PathParam("id") Long id, 
                                    @DefaultValue("false") @QueryParam("withMeasure") Boolean withMeasure){
        AnalogSensor analogSensor = AnalogSensor.findById(id);

        if(analogSensor == null){
            return Response.noContent().build();
        }
        
        else if(withMeasure){
            List<AnalogMeasure> analogmeasurelist = AnalogMeasure.list("analogSensor", analogSensor);
            DTOAnalogSensorWithAnalogMeasure sensorWitMeasures = mapper_.fromAnalogMeasureToDtoAnalogSensorWithAnalogMeasure(analogSensor);
        
            return Response.ok(sensorWitMeasures).build();
        }
        else {
        return Response.ok(mapper.fromAnalogSensorToDtoPlainAnalogSensor(analogSensor)).build();
        }
    }
    


    @POST
    @Path("/analog")
    @Transactional
    @Operation(summary = "Create a new analogsensor")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensor.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createAnalogSensor(@Valid DTOPlainAnalogSensor dtoPlainAnalogSensor){
        AnalogSensor analogSensor = mapper.toAnalogSensorFromDtoPlainAnalogSensor(dtoPlainAnalogSensor);
        analogSensor.id = null;
        analogSensor.manufacturer = dtoPlainAnalogSensor.manufacturer;
        analogSensor.reference = dtoPlainAnalogSensor.reference;
        analogSensor.persist();

        return Response.status(Status.CREATED).entity(mapper.fromAnalogSensorToDtoPlainAnalogSensor(analogSensor)).build();
    }

    @PUT
    @Path("/analog")
    @Transactional
    @Operation(summary = "Update an existing analogsensor")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensor.class)))
    @APIResponse(responseCode = "404", description = "There is no analogsensor with given identifier")
    public Response updateAnalogSensor(@Valid DTOPlainAnalogSensor dtoPlainAnalogSensor){
        AnalogSensor analogSensor = mapper.toAnalogSensorFromDtoPlainAnalogSensor(dtoPlainAnalogSensor);
        AnalogSensor entity = AnalogSensor.findById(analogSensor.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.manufacturer = analogSensor.manufacturer;
        entity.reference = analogSensor.reference;

        return Response.ok(mapper.fromAnalogSensorToDtoPlainAnalogSensor(entity)).build();
    }

    @DELETE
    @Path("/analog/{id}")
    @Transactional
    @Operation(summary = "Delete a analogsensor")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensor.class)))
    @APIResponse(responseCode = "404", description = "There is no analogsensor with given identifier")
    public Response deleteAnalogSensor(@PathParam("id") Long id){
        AnalogSensor entity = AnalogSensor.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }

    /**************************************************** NUM SENSOR METHOD ********************************************************/

    @GET
    @Path("/num")
    @Operation(summary = "Return every numsensor")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensor.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no NumSensor")
    public Response getAllNumSensor(@DefaultValue("false") @QueryParam("withState") Boolean withState,
                                    @DefaultValue("false") @QueryParam("withMeasure") Boolean withMeasure,
                                    @DefaultValue("false") @QueryParam("withType") Boolean withType,
                                    @DefaultValue("false") @QueryParam("withSkiLift") Boolean withSkiLift){
        
        List<NumSensor> numSensorList = NumSensor.listAll();
        if(withState && withMeasure && withType && withSkiLift){
            List<DTONumSensor> dtoNumSensors = numSensorList.stream()
                .map(numSensor -> mapper_.fromNumSensorToDtoNumSensor(numSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoNumSensors).build();
        }
        else if(withState){
            List<DTONumSensorWithState> dtoNumSensorWithStateList = numSensorList.stream()
                .map(numSensor -> mapper_.fromNumSensorToDtoNumSensorWithState(numSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoNumSensorWithStateList).build();
        }
        else if(withMeasure){
            List<DTONumSensorWithNumMeasure> dtoNumSensorWithNumMeasureList = numSensorList.stream()
                .map(numSensor -> mapper_.fromNumSensorToDtoNumSensorWithNumMeasure(numSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoNumSensorWithNumMeasureList).build();
        }
        else if(withType){
            List<DTONumSensorWithSensorType> dtoNumSensorWithSensorTypes = numSensorList.stream()
                .map(numSensor -> mapper_.fromNumSensorToDtoNumSensorWithSensorType(numSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoNumSensorWithSensorTypes).build();
        }
        else if(withSkiLift){
            List<DTONumSensorWithSkiLift> dtoNumSensorWithSkiLifts = numSensorList.stream()
                .map(numSensor -> mapper_.fromNumSensorToDtoNumSensorWithSkiLift(numSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoNumSensorWithSkiLifts).build();
        }
        else{
            List<DTOPlainNumSensor> dtoPlainNumSensorList = numSensorList.stream()
                .map(NumSensor -> mapper.fromNumSensorToDtoPlainNumSensor(NumSensor))
                .collect(Collectors.toList());
            return Response.ok(dtoPlainNumSensorList).build();
        }
    }

    @GET
    @Path("/num/{id}")
    @Operation(summary = "return a numsensor with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainNumSensor.class)))
    @APIResponse(responseCode = "204", description = "There is no numsensor with given identifier")
    public Response getNumSensor(@PathParam("id") Long id){
        NumSensor numSensor = NumSensor.findById(id);

        if(numSensor == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromNumSensorToDtoPlainNumSensor(numSensor)).build();
    }

    @POST
    @Path("/num")
    @Transactional
    @Operation(summary = "Create a new numsensor")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensor.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createNumSensor(@Valid DTOPlainNumSensor dtoPlainNumSensor){
        NumSensor numSensor = mapper.toNumSensorFromDtoPlainNumSensor(dtoPlainNumSensor);
        numSensor.id = null;
        numSensor.manufacturer = dtoPlainNumSensor.manufacturer;
        numSensor.reference = dtoPlainNumSensor.reference;
        numSensor.persist();

        return Response.status(Status.CREATED).entity(mapper.fromNumSensorToDtoPlainNumSensor(numSensor)).build();
    }

    @PUT
    @Path("/num")
    @Transactional
    @Operation(summary = "Update an existing numsensor")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensor.class)))
    @APIResponse(responseCode = "404", description = "There is no numsensor with given identifier")
    public Response updateNumSensor(@Valid DTOPlainNumSensor dtoPlainNumSensor){
        NumSensor numSensor = mapper.toNumSensorFromDtoPlainNumSensor(dtoPlainNumSensor);
        NumSensor entity = NumSensor.findById(numSensor.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.manufacturer = numSensor.manufacturer;
        entity.reference = numSensor.reference;

        return Response.ok(mapper.fromNumSensorToDtoPlainNumSensor(entity)).build();
    }

    @DELETE
    @Path("/num/{id}")
    @Transactional
    @Operation(summary = "Delete a numsensor")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensor.class)))
    @APIResponse(responseCode = "404", description = "There is no numsensor with given identifier")
    public Response deleteNumSensor(@PathParam("id") Long id){
        NumSensor entity = NumSensor.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }
}
