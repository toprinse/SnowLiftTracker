package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;


import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;
import fr.esisar.snowlifttracker.dto.DTOStation;
import fr.esisar.snowlifttracker.mapper.StationMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainStationMapper;
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

@Path("/Station")
public class StationResource {
    
    @Inject
    PlainStationMapper mapper;
    @Inject
    StationMapper mapper_;

    @GET
    @Operation(summary = "Return every station")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainStation.class,
                                                    type= SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is no Station")
    public Response getAllStation(@DefaultValue("false") @QueryParam("withSkiLifts") Boolean withSkiLifts){
        List<Station> stationList = Station.listAll();

        if(withSkiLifts){
            List<DTOStation> dtoStationList = stationList.stream()
                .map(station -> mapper_.fromStationToDtoStation(station))
                .collect(Collectors.toList());
            return Response.ok(dtoStationList).build();
        }

        List<DTOPlainStation> dtoPlainStationList = stationList.stream()
            .map(station -> mapper.fromStationToDtoPlainStation(station))
            .collect(Collectors.toList());
        return Response.ok(dtoPlainStationList).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "return a station with given identifier")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainStation.class)))
    @APIResponse(responseCode = "204", description = "There is no station with given identifier")
    public Response getStationById(@PathParam("id") Long id){
        Station station = Station.findById(id);

        if(station == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromStationToDtoPlainStation(station)).build();
    }

    @GET
    @Path("/c/{name}")
    @Operation(summary = "return a station with given name")
    @APIResponse(responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = DTOPlainStation.class)))
    @APIResponse(responseCode = "204", description = "There is no station with given name")
    public Response getStationByName(@PathParam("name") String name){
        Station station = Station.findByName(name);

        if(station == null){
            return Response.noContent().build();
        }
        return Response.ok(mapper.fromStationToDtoPlainStation(station)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Create a new station")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainStation.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createStation(@Valid DTOPlainStation dtoPlainStation){
        Station station = mapper.toStationFromDtoPlainStation(dtoPlainStation);
        station.id = null;
        station.name = dtoPlainStation.name;
        station.longitude = dtoPlainStation.longitude;
        station.latitude = dtoPlainStation.latitude;
        station.persist();

        return Response.status(Status.CREATED).entity(mapper.fromStationToDtoPlainStation(station)).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Update an existing station")
    @APIResponse(responseCode = "200", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainStation.class)))
    @APIResponse(responseCode = "404", description = "There is no station with given identifier")
    public Response updateStation(@Valid DTOPlainStation dtoPlainStation){
        Station station = mapper.toStationFromDtoPlainStation(dtoPlainStation);
        Station entity = Station.findById(station.id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        
        entity.name = station.name;
        entity.latitude = station.latitude;
        entity.longitude = station.longitude;

        return Response.ok(mapper.fromStationToDtoPlainStation(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete a station")
    @APIResponse(responseCode = "204",
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainStation.class)))
    @APIResponse(responseCode = "404", description = "There is no station with given identifier")
    public Response deleteStation(@PathParam("id") Long id){
        Station entity = Station.findById(id);
        if(entity == null){
            return Response.status(Status.NOT_FOUND).build();
        }

        entity.delete();
        
        return Response.noContent().build();
    }

    @DELETE
    @Path("/Recursive/{id}")
    @Transactional
    @Operation(summary = "Delete a Station with associated skilifts, sensors and measures")
    @APIResponse(responseCode = "204", description = "Station, Skilift, sensors, and measures deleted successfully")
    @APIResponse(responseCode = "404", description = "There is no station with the given identifier")
    public Response deleteStationWithSkiLiftAndOthers(@PathParam("id") Long id) {
        Station station = Station.findById(id);

        if (station == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // Supprimer les skiLifts affiliées à un analog sensor
        List<SkiLift> skiLifts = station.skiLiftList.stream()
        .map(skilift -> (SkiLift) skilift)
        .collect(Collectors.toList()); 



        for(SkiLift skiLift : skiLifts)
        {
            List<AnalogSensor> analogSensors = skiLift.sensorList.stream()
            .filter(sensor -> sensor instanceof AnalogSensor)
            .map(analogSensor -> (AnalogSensor) analogSensor)
            .collect(Collectors.toList()); 

            for(AnalogSensor analogSensor : analogSensors)
            {
                for(AnalogMeasure analogMeasure : analogSensor.analogMeasureList)
                {
                    analogMeasure.delete();
                }
                analogSensor.delete();
            }
             
        }

        // Supprimer les skiLifts affiliées à un numeric sensor
        for(SkiLift skiLift : skiLifts)
        {
            List<NumSensor> numSensors = skiLift.sensorList.stream()
            .filter(sensor -> sensor instanceof NumSensor)
            .map(numSensor -> (NumSensor) numSensor)
            .collect(Collectors.toList());

            for(NumSensor numSensor : numSensors)
            {
                for(NumMeasure numMeasure : numSensor.numMeasureList)
                {
                    numMeasure.delete();
                }
                numSensor.delete();
            }
            skiLift.delete(); 
        }

        // Supprimer la station
        station.delete();

        return Response.noContent().build();
    }
}
