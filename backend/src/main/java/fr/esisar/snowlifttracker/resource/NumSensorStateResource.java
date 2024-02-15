package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import fr.esisar.snowlifttracker.dto.DTONumSensorState;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensorState;
import fr.esisar.snowlifttracker.mapper.NumSensorStateMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainNumSensorStateMapper;
import fr.esisar.snowlifttracker.model.NumSensorState;
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

@Path("/NumSensorState")
public class NumSensorStateResource {
    
    @Inject
    PlainNumSensorStateMapper mapper;
    @Inject
    NumSensorStateMapper mapper_;

    @GET
    @Operation(summary = "Return every numsensorstate")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensorState.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no NumSensorState")
    public Response getAllNumSensorState(@DefaultValue("false") @QueryParam("WithSensors") Boolean WithSensors){
        List<NumSensorState> numSensorStateList = NumSensorState.listAll();

        if(WithSensors){
            List<DTONumSensorState> dtoNumSensorStateList = numSensorStateList.stream()
                .map(NumSensorState -> mapper_.fromNumSensorStateToDtoNumSensorState(NumSensorState))
                .collect(Collectors.toList());
            return Response.ok(dtoNumSensorStateList).build();
        }
        
        List<DTOPlainNumSensorState> dtoPlainNumSensorStateList = numSensorStateList.stream()
            .map(NumSensorState -> mapper.fromNumSensorStateToDtoPlainNumSensorState(NumSensorState))
            .collect(Collectors.toList());
        return Response.ok(dtoPlainNumSensorStateList).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a numsensorstate with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainNumSensorState.class)))
    @APIResponse(responseCode = "204", description = "There is no numsensorstate with given identifier")
    public Response getNumSensorState(@PathParam("id") Long id){
        NumSensorState numSensorState = NumSensorState.findById(id);

        if(numSensorState == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromNumSensorStateToDtoPlainNumSensorState(numSensorState)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new numsensorstate")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensorState.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createNumSensorState(@Valid DTOPlainNumSensorState dtoPlainNumSensorState){
        NumSensorState numSensorState = mapper.toNumSensorStateFromDtoPlainNumSensorState(dtoPlainNumSensorState);
        numSensorState.id = null;
        numSensorState.high = dtoPlainNumSensorState.high;
        numSensorState.low = dtoPlainNumSensorState.low;
        numSensorState.persist();

        return Response.status(Status.CREATED).entity(mapper.fromNumSensorStateToDtoPlainNumSensorState(numSensorState)).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update an existing numsensorstate")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensorState.class)))
    @APIResponse(responseCode = "404", description = "There is no numsensorstate with given identifier")
    public Response updateNumSensorState(@Valid DTOPlainNumSensorState dtoPlainNumSensorState){
        NumSensorState numSensorState = mapper.toNumSensorStateFromDtoPlainNumSensorState(dtoPlainNumSensorState);
        NumSensorState entity = NumSensorState.findById(numSensorState.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        
        entity.high = numSensorState.high;
        entity.low = numSensorState.low;

        return Response.ok(mapper.fromNumSensorStateToDtoPlainNumSensorState(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a numsensorstate")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainNumSensorState.class)))
    @APIResponse(responseCode = "404", description = "There is no numsensorstate with given identifier")
    public Response deleteNumSensorState(@PathParam("id") Long id){
        NumSensorState entity = NumSensorState.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }
}
