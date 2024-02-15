package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import fr.esisar.snowlifttracker.dto.DTOSensorType;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensorType;
import fr.esisar.snowlifttracker.mapper.SensorTypeMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorTypeMapper;
import fr.esisar.snowlifttracker.model.SensorType;
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

@Path("/SensorType")
public class SensorTypeResource {
    
    @Inject
    PlainSensorTypeMapper mapper;
    @Inject
    SensorTypeMapper mapper_;
   
    @GET
    @Operation(summary = "Return every sensortype")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensorType.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no SensorType")
    public Response getAllSensorType(@DefaultValue("false") @QueryParam("withSensors") Boolean withSensors){
        List<SensorType> sensorTypeList = SensorType.listAll();

        if(withSensors){
            List<DTOSensorType> dtoSensorTypeList = sensorTypeList.stream()
                .map(sensorType -> mapper_.fromSensorTypeToDtoSensorType(sensorType))
                .collect(Collectors.toList());
            return Response.ok(dtoSensorTypeList).build();
        }

        List<DTOPlainSensorType> dtoPlainSensorTypeList = sensorTypeList.stream()
            .map(sensorType -> mapper.fromSensorTypeToDtoPlainSensorType(sensorType))
            .collect(Collectors.toList());
        return Response.ok(dtoPlainSensorTypeList).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a sensortype with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainSensorType.class)))
    @APIResponse(responseCode = "204", description = "There is no sensortype with given identifier")
    public Response getSensorType(@PathParam("id") Long id){
        SensorType sensorType = SensorType.findById(id);

        if(sensorType == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromSensorTypeToDtoPlainSensorType(sensorType)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new sensortype")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensorType.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createSensorType(@Valid DTOPlainSensorType dtoPlainSensorType){
        SensorType sensorType = mapper.toSensorTypeFromDtoPlainSensorType(dtoPlainSensorType);
        sensorType.id = null;
        sensorType.type = dtoPlainSensorType.type;
        sensorType.persist();

        return Response.status(Status.CREATED).entity(mapper.fromSensorTypeToDtoPlainSensorType(sensorType)).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update an existing sensortype")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensorType.class)))
    @APIResponse(responseCode = "404", description = "There is no sensortype with given identifier")
    public Response updateSensorType(@Valid DTOPlainSensorType dtoPlainSensorType){
        SensorType sensorType = mapper.toSensorTypeFromDtoPlainSensorType(dtoPlainSensorType);
        SensorType entity = SensorType.findById(sensorType.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.type = sensorType.type;

        return Response.ok(mapper.fromSensorTypeToDtoPlainSensorType(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a sensortype")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensorType.class)))
    @APIResponse(responseCode = "404", description = "There is no sensortype with given identifier")
    public Response deleteSensorType(@PathParam("id") Long id){
        SensorType entity = SensorType.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }
}
