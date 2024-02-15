package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensorUnit;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorUnit;
import fr.esisar.snowlifttracker.mapper.plain.PlainAnalogSensorUnitMapper;
import fr.esisar.snowlifttracker.mapper.AnalogSensorUnitMapper;
import fr.esisar.snowlifttracker.model.AnalogSensorUnit;
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

@Path("/AnalogSensorUnit")
public class AnalogSensorUnitResource {
    
    @Inject
    PlainAnalogSensorUnitMapper mapper;
    @Inject
    AnalogSensorUnitMapper mapper_;

    @GET
    @Operation(summary = "Return every analogsensorunit")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensorUnit.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no AnalogSensorUnit")
    public Response getAllAnalogSensorUnit(@DefaultValue("false") @QueryParam("WithSensors") Boolean WithSensors){
        List<AnalogSensorUnit> analogSensorUnitList = AnalogSensorUnit.listAll();
        if(WithSensors){
            List<DTOAnalogSensorUnit> dtoAnalogSensorUnitList = analogSensorUnitList.stream()
                .map(AnalogSensorUnit -> mapper_.fromAnalogSensorUnitToDtoAnalogSensorUnit(AnalogSensorUnit))
                .collect(Collectors.toList());
            return Response.ok(dtoAnalogSensorUnitList).build();
        }
        List<DTOPlainAnalogSensorUnit> dtoPlainAnalogSensorUnitList = analogSensorUnitList.stream()
            .map(AnalogSensorUnit -> mapper.fromAnalogSensorUnitToDtoPlainAnalogSensorUnit(AnalogSensorUnit))
            .collect(Collectors.toList());
        return Response.ok(dtoPlainAnalogSensorUnitList).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a analogsensorunit with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainAnalogSensorUnit.class)))
    @APIResponse(responseCode = "204", description = "There is no analogsensorunit with given identifier")
    public Response getAnalogSensorUnit(@PathParam("id") Long id){
        AnalogSensorUnit analogSensorUnit = AnalogSensorUnit.findById(id);

        if(analogSensorUnit == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromAnalogSensorUnitToDtoPlainAnalogSensorUnit(analogSensorUnit)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new analogsensorunit")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensorUnit.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createAnalogSensorUnit(@Valid DTOPlainAnalogSensorUnit dtoPlainAnalogSensorUnit){
        AnalogSensorUnit analogSensorUnit = mapper.toAnalogSensorUnitFromDtoPlainAnalogSensorUnit(dtoPlainAnalogSensorUnit);
        analogSensorUnit.id = null;
        analogSensorUnit.unit = dtoPlainAnalogSensorUnit.unit;
        analogSensorUnit.persist();

        return Response.status(Status.CREATED).entity(mapper.fromAnalogSensorUnitToDtoPlainAnalogSensorUnit(analogSensorUnit)).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update an existing analogsensorunit")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensorUnit.class)))
    @APIResponse(responseCode = "404", description = "There is no analogsensorunit with given identifier")
    public Response updateAnalogSensorUnit(@Valid DTOPlainAnalogSensorUnit dtoPlainAnalogSensorUnit){
        AnalogSensorUnit analogSensorUnit = mapper.toAnalogSensorUnitFromDtoPlainAnalogSensorUnit(dtoPlainAnalogSensorUnit);
        AnalogSensorUnit entity = AnalogSensorUnit.findById(analogSensorUnit.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.unit = analogSensorUnit.unit;

        return Response.ok(mapper.fromAnalogSensorUnitToDtoPlainAnalogSensorUnit(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a analogsensorunit")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainAnalogSensorUnit.class)))
    @APIResponse(responseCode = "404", description = "There is no analogsensorunit with given identifier")
    public Response deleteAnalogSensorUnit(@PathParam("id") Long id){
        AnalogSensorUnit entity = AnalogSensorUnit.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }
}
