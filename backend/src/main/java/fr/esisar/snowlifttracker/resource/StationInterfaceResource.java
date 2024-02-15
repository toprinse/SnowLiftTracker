package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

//import fr.esisar.snowlifttracker.dto.DTOSensor;
//import fr.esisar.snowlifttracker.dto.DTOSensorList;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSkiLiftMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainStationMapper;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumSensor;
import fr.esisar.snowlifttracker.model.Sensor;
import fr.esisar.snowlifttracker.model.SkiLift;
import fr.esisar.snowlifttracker.model.Station;
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
import jakarta.ws.rs.core.Response.Status;

@Path("/stationinterface")
public class StationInterfaceResource {
    // REST Interface to have complexes paths
    // Some chnage needs to be implemented to make this work properly without warnings

    @Inject
    PlainStationMapper mapper;

    @Inject
    PlainSkiLiftMapper mapper2;

    @Inject
    PlainSensorMapper mapper3;

 

    //-------------------------------
    //    STATION WITH REMONTEE
    //-------------------------------
    @GET
    @Path("/{id}/remontee")
    public Response getSkiLiftListInStation(@PathParam("id") Long id){
        Station station = Station.findById(id);

        if(station == null){
            return Response.noContent().build();
        }

        List<SkiLift> skiLiftList = SkiLift.list("station", station);
        List<DTOPlainSkiLift> dtoPlainSkiLiftList = skiLiftList.stream()
            .map(skiLift -> mapper2.fromSkiLiftToDtoPlainSkiLift(skiLift))
            .collect(Collectors.toList());

        return Response.ok(dtoPlainSkiLiftList).build();
    }
    /*
    @GET
    @Path("/{id}/remontee/{id2}")
    public Response getStkiLiftInStation(@PathParam("id") Long id, @PathParam("id2") Long id2){
        Station station = Station.findById(id);

        if(station == null){
            return Response.noContent().build();
        }

        SkiLift skiLift = SkiLift.find("id = ?1 and station.id = ?2", id2, id).firstResult();

        if(skiLift == null){
            return Response.noContent().build();
        }

        DTOPlainSkiLift dtoPlainSkiLift = mapper2.fromSkiLiftToDtoPlainSkiLift(skiLift);

        return Response.ok(dtoPlainSkiLift).build();
    }

    //-------------------------------
    //    STATION WITH SENSOR
    //-------------------------------
    @GET
    @Path("/{id}/remontee/{id2}/capteur")
    public Response getSensorInStation(@PathParam("id") Long id,  @PathParam("id2") Long id2){
        Station station = Station.findById(id);

        if(station == null){
            return Response.noContent().build();
        }

        SkiLift skiLift = SkiLift.find("id = ?1 and station.id = ?2", id2, id).firstResult();

        if(skiLift == null){
            return Response.noContent().build();
        }
        
        List<Sensor> sensorList = Sensor.list("skiLift", skiLift);
        List<DTOSensor> dtoSensorList = sensorList.stream()
            .map(sensor -> mapper3.fro(sensor))
            .collect(Collectors.toList());
        
        return Response.ok(dtoSensorList).build();
    }

    @GET
    @Path("/{id}/remontee/{id2}/capteur/{id3}")
    public Response getSensorInStation(@PathParam("id") Long id, @PathParam("id2") Long id2, @PathParam("id3") Long id3){
        Station station = Station.findById(id);

        if(station == null){
            return Response.noContent().build();
        }

        SkiLift skiLift = SkiLift.find("id = ?1 and station.id = ?2", id2, id).firstResult();

        if(skiLift == null){
            return Response.noContent().build();
        }

        Sensor sensor = Sensor.find("id = ?1 and skiLift.id = ?2", id3, id2).firstResult();

        if(sensor == null){
            return Response.noContent().build();
        }

        DTOSensor dtoPlainSensor;
        if(sensor instanceof NumSensor){
           dtoPlainSensor = mapper3.fromNumSensor((NumSensor)sensor);
        }
        else if(sensor instanceof AnalogSensor){
           dtoPlainSensor = mapper3.fromAnalogSensor((AnalogSensor)sensor);
        }
        else{
            return Response.noContent().build();
        }

        return Response.ok(dtoPlainSensor).build();
    }

    */
}
