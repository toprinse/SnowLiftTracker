package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import fr.esisar.snowlifttracker.dto.DTOSkiLift;
import fr.esisar.snowlifttracker.dto.DTOSkiLiftWithSensor;
import fr.esisar.snowlifttracker.dto.DTOSkiLiftWithStation;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.mapper.SkiLiftMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSkiLiftMapper;
import fr.esisar.snowlifttracker.model.Station;
import fr.esisar.snowlifttracker.model.SkiLift;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.NumMeasure;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumSensor;
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

@Path("/SkiLift")
public class SkiLiftResource {
    
    @Inject
    PlainSkiLiftMapper mapper;
    @Inject
    SkiLiftMapper mapper_;

    @GET
    @Operation(summary = "Return every skilift")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSkiLift.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no SkiLift")
    public Response getAllSkiLift(@DefaultValue("false") @QueryParam("withStation") Boolean withStation,
                                  @DefaultValue("false") @QueryParam("withSensors") Boolean withSensors){
        List<SkiLift> skiLiftList = SkiLift.listAll();

        if(withSensors && withStation){
            List<DTOSkiLift> dtoSkiLift = skiLiftList.stream()
                .map(skiLift -> mapper_.fromSkiLiftToDtoSkiLift(skiLift))
                .collect(Collectors.toList());
            return Response.ok(dtoSkiLift).build();
        }
        else if(withStation){
            List<DTOSkiLiftWithStation> dtoSkiLiftWithStationList = skiLiftList.stream()
                .map(skiLift -> mapper_.fromSkiLiftToDtoSkiLiftWithStation(skiLift))
                .collect(Collectors.toList());
            return Response.ok(dtoSkiLiftWithStationList).build();
        }
        else if(withSensors){
            List<DTOSkiLiftWithSensor> dtoSkiLiftWithSensorList = skiLiftList.stream()
                .map(skiLift -> mapper_.fromSkiLiftToDtoSkiLiftWithSensor(skiLift))
                .collect(Collectors.toList());
            return Response.ok(dtoSkiLiftWithSensorList).build();
        }
        else{
            List<DTOPlainSkiLift> dtoPlainSkiLiftList = skiLiftList.stream()
                .map(skiLift -> mapper.fromSkiLiftToDtoPlainSkiLift(skiLift))
                .collect(Collectors.toList());
            return Response.ok(dtoPlainSkiLiftList).build();
        }
    }

    @GET
    @Path("/open")
    @Operation(summary = "Return every skilift")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSkiLift.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no SkiLift")
    public Response getAllOpenSkiLift(){
        List<SkiLift> skiLiftList = SkiLift.listAllOpen();
        List<DTOPlainSkiLift> dtoPlainSkiLiftList = skiLiftList.stream()
            .map(skiLift -> mapper.fromSkiLiftToDtoPlainSkiLift(skiLift))
            .collect(Collectors.toList());

        return Response.ok(dtoPlainSkiLiftList).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a skilift with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainSkiLift.class)))
    @APIResponse(responseCode = "204", description = "There is no skilift with given identifier")
    public Response getSkiLiftById(@PathParam("id") Long id){
        SkiLift skiLift = SkiLift.findById(id);

        if(skiLift == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromSkiLiftToDtoPlainSkiLift(skiLift)).build();
    }

    @GET
    @Path("/c/{name}") // '/c/' because otherwise will be the same path as '/{id}'' using @Override or a template for getSkiLiftBy<T> ...
    @Operation(summary = "return a skilift with given name")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainSkiLift.class)))
    @APIResponse(responseCode = "204", description = "There is no skilift with given name")
    public Response getSkiLiftByName(@PathParam("name") String name){
        SkiLift skiLift = SkiLift.findByName(name);

        if(skiLift == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromSkiLiftToDtoPlainSkiLift(skiLift)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new skilift")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSkiLift.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createSkiLift(@Valid DTOPlainSkiLift dtoPlainSkiLift){
        SkiLift skiLift = mapper.toSkiLiftFromDtoPlainSkiLift(dtoPlainSkiLift);
        skiLift.id = null;
        skiLift.name = dtoPlainSkiLift.name;
        skiLift.longitude = dtoPlainSkiLift.longitude;
        skiLift.latitude = dtoPlainSkiLift.latitude;
        skiLift.persist();

        return Response.status(Status.CREATED).entity(mapper.fromSkiLiftToDtoPlainSkiLift(skiLift)).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update an existing skilift")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSkiLift.class)))
    @APIResponse(responseCode = "404", description = "There is no skilift with given identifier")
    public Response updateSkiLift(@Valid DTOPlainSkiLift dtoPlainSkiLift){
        SkiLift skiLift = mapper.toSkiLiftFromDtoPlainSkiLift(dtoPlainSkiLift);
        SkiLift entity = SkiLift.findById(skiLift.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        entity.name = skiLift.name;
        entity.latitude = skiLift.latitude;
        entity.longitude = skiLift.longitude;

        return Response.ok(mapper.fromSkiLiftToDtoPlainSkiLift(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a skilift")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSkiLift.class)))
    @APIResponse(responseCode = "404", description = "There is no skilift with given identifier")
    public Response deleteSkiLift(@PathParam("id") Long id){
        SkiLift entity = SkiLift.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }

    @DELETE
    @Path("/Recursive/{id}")
    @Transactional
    @Operation(summary = "Delete a skilift with associated sensors and measures")
    @APIResponse(responseCode = "204", description = "Skilift, sensors, and measures deleted successfully")
    @APIResponse(responseCode = "404", description = "There is no skilift with the given identifier")
    public Response deleteSkiLiftWithSensorsAndMeasures(@PathParam("id") Long id) {
        SkiLift skiLift = SkiLift.findById(id);

        if (skiLift == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // Supprimer les sensors affiliés
        List<AnalogSensor> analogSensors = skiLift.sensorList.stream()
            .filter(sensor -> sensor instanceof AnalogSensor)
            .map(analogSensor -> (AnalogSensor) analogSensor)
            .collect(Collectors.toList()); 

        for (AnalogSensor analogsensor : analogSensors) {
            for (AnalogMeasure  measure : analogsensor.analogMeasureList) {
                measure.delete();
            }

            analogsensor.delete();
        }

        List<NumSensor> numSensors = skiLift.sensorList.stream()
            .filter(sensor -> sensor instanceof NumSensor)
            .map(numSensor -> (NumSensor) numSensor)
            .collect(Collectors.toList()); 

        for (NumSensor numsensor : numSensors) {
            for (NumMeasure  measure : numsensor.numMeasureList) {
                measure.delete();
            }    

            numsensor.delete();
        }

        // Supprimer la skilift
        skiLift.delete();

        return Response.noContent().build();
    }

}
