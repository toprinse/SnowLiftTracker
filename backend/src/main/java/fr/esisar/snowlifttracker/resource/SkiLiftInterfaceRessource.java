package fr.esisar.snowlifttracker.resource;

import java.util.List;
import java.util.stream.Collectors;

//import fr.esisar.snowlifttracker.dto.DTOSensor;
//import fr.esisar.snowlifttracker.dto.DTOSensorList;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;
import fr.esisar.snowlifttracker.mapper.SensorMapper;
import fr.esisar.snowlifttracker.mapper.SkiLiftMapper;
import fr.esisar.snowlifttracker.mapper.StationMapper;
import fr.esisar.snowlifttracker.mapper.MeasureMapper;
import fr.esisar.snowlifttracker.mapper.NumSensorStateMapper;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumSensor;
import fr.esisar.snowlifttracker.model.Sensor;
import fr.esisar.snowlifttracker.model.SkiLift;
import fr.esisar.snowlifttracker.model.Station;
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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/skiliftinterface")
public class SkiLiftInterfaceRessource {
    // REST Interface to have complexes paths
    // Some chnage needs to be implemented to make this work properly without warnings

    @Inject
    SkiLiftMapper mapper2;

    @Inject
    SensorMapper mapper3;

    @Inject
    MeasureMapper mapper4;

    //-------------------------------
    //    SENSOR WITH ANALOGMEASURE
    //-------------------------------
    @GET
    @Path("/{id}/analogsensor/{id2}/analogmeasure")
    public Response getAnalogMeasureInSensor(@PathParam("id") Long id,  @PathParam("id2") Long id2){

        SkiLift skiLift = SkiLift.findById(id);
        if(skiLift == null){
            return Response.noContent().build();
        }

        AnalogSensor analogsensor = AnalogSensor.find("id = ?1 and  skiLift.id = ?2", id2, id).firstResult();

         if(analogsensor == null){
            return Response.noContent().build();
        }

        List<AnalogMeasure> analogmeasurelist = AnalogMeasure.list("analogSensor", analogsensor);

        DTOAnalogSensorWithAnalogMeasure sensorWitMEasures = mapper3.fromAnalogMeasureToDtoAnalogSensorWithAnalogMeasure(analogsensor);
        
     return Response.ok(sensorWitMEasures).build();
    }

}


