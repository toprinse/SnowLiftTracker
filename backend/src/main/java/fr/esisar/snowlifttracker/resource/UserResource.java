package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;


import fr.esisar.snowlifttracker.dto.plain.DTOPlainUserSnow;
import fr.esisar.snowlifttracker.mapper.plain.PlainUserSnowMapper;
import fr.esisar.snowlifttracker.model.UserSnow;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/UserSnow")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    PlainUserSnowMapper mapper;

    @GET
    @Operation(summary = "Return every usersnow")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainUserSnow.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no UserSnow")
    public Response getAllUser(){
        List<UserSnow> users = UserSnow.listAll();
        List<DTOPlainUserSnow> dtoPlainUsers = users.stream()
            .map(user -> mapper.fromUserToDtoPlainUser(user))
            .collect(Collectors.toList());

        return Response.ok(dtoPlainUsers).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a usersnow with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainUserSnow.class)))
    @APIResponse(responseCode = "204", description = "There is no usersnow with given identifier")
    public Response getUser(@PathParam("id") Long id){
        UserSnow user = UserSnow.findById(id);

        if(user == null){
            return Response.noContent().build();
        }

        return Response.ok(mapper.fromUserToDtoPlainUser(user)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new usersnow")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainUserSnow.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createUser(@Valid DTOPlainUserSnow dtoPlainUserSnow){
        UserSnow user = mapper.toUserFromDtoPlainUser(dtoPlainUserSnow);

        user.id = null;
        user.email = dtoPlainUserSnow.email;
        user.firstname = dtoPlainUserSnow.firstname;
        user.lastname = dtoPlainUserSnow.lastname;
        user.login = dtoPlainUserSnow.login;
        user.password = dtoPlainUserSnow.password;
        user.permission = dtoPlainUserSnow.permission;

        user.persist();

        return Response.status(Status.CREATED).entity(mapper.fromUserToDtoPlainUser(user)).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update an existing usersnow")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainUserSnow.class)))
    @APIResponse(responseCode = "404", description = "There is no usersnow with given identifier")
    public Response updateUser(@Valid DTOPlainUserSnow dtoPlainUserSnow){
        UserSnow user = mapper.toUserFromDtoPlainUser(dtoPlainUserSnow);
        UserSnow entity = UserSnow.findById(user.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.email = dtoPlainUserSnow.email;
        entity.firstname = dtoPlainUserSnow.firstname;
        entity.lastname = dtoPlainUserSnow.lastname;
        entity.login = dtoPlainUserSnow.login;
        entity.password = dtoPlainUserSnow.password;
        entity.permission = dtoPlainUserSnow.permission;

        return Response.ok(mapper.fromUserToDtoPlainUser(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a usersnow")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainUserSnow.class)))
    @APIResponse(responseCode = "404", description = "There is no usersnow with given identifier")
    public Response deleteUser(@PathParam("id") Long id){
        UserSnow entity = UserSnow.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }
}
