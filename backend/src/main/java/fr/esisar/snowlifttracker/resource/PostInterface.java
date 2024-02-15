package fr.esisar.snowlifttracker.resource;

import java.util.Objects;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import fr.esisar.snowlifttracker.model.SkiLift;
import fr.esisar.snowlifttracker.model.Station;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.NumMeasure;
import fr.esisar.snowlifttracker.model.NumSensor;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.SensorType;
import fr.esisar.snowlifttracker.dto.DTOAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensor;
import fr.esisar.snowlifttracker.dto.DTONumMeasure;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithSkiLift;
import fr.esisar.snowlifttracker.dto.DTOPostAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTOPostNumMeasure;
import fr.esisar.snowlifttracker.dto.DTOPostSensor;
import fr.esisar.snowlifttracker.dto.DTOPostSkiLift;
import fr.esisar.snowlifttracker.dto.DTOSkiLiftWithStation;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainMeasure;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorTypeMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSkiLiftMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainStationMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@Path("/PostInterface")
public class PostInterface {
    
    private final PlainSensorMapper sensor_mapper;
    private final PlainSkiLiftMapper skiLift_mapper;
    private final PlainStationMapper station_mapper;
    private final PlainSensorTypeMapper sensorType_mapper;

    @Inject
    PostInterface(PlainSensorMapper sensor_mapper,  PlainSkiLiftMapper skiLift_mapper, PlainStationMapper station_mapper, PlainSensorTypeMapper sensorType_mapper){
        this.sensor_mapper = sensor_mapper;
        this.skiLift_mapper = skiLift_mapper;
        this.station_mapper = station_mapper;
        this.sensorType_mapper = sensorType_mapper;
    }

    @POST
    @Path("/analogmeasure")
    @Transactional
    @Operation(summary = "create a new analog measure from a sensor")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainMeasure.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createAnalogMeasure(@Valid DTOPostAnalogMeasure dtoPostMeasure){

        if(Objects.isNull(dtoPostMeasure.sensorId)) return null;
        AnalogSensor entity = AnalogSensor.findById(dtoPostMeasure.sensorId);
        AnalogMeasure analogMeasure = new AnalogMeasure();
        if(entity == null) return null;
        DTOAnalogMeasure dtoAnalogMeasure = new DTOAnalogMeasure();

        analogMeasure.id = null;
        analogMeasure.analogData = dtoPostMeasure.analogData;
        analogMeasure.timestamp = dtoPostMeasure.timestamp;
        analogMeasure.analogSensor = entity;
        analogMeasure.persist();
         
        dtoAnalogMeasure.id = analogMeasure.id;
        dtoAnalogMeasure.analogData = analogMeasure.analogData;
        dtoAnalogMeasure.timestamp = analogMeasure.timestamp;
        dtoAnalogMeasure.analogSensor = sensor_mapper.fromAnalogSensorToDtoPlainAnalogSensor(entity);

        return Response.status(Status.CREATED).entity(dtoAnalogMeasure).build();
    }

    @POST
    @Path("/nummeasure")
    @Transactional
    @Operation(summary = "create a new num measure from a sensor")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainMeasure.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createNumMeasure(@Valid DTOPostNumMeasure dtoPostMeasure){

        if(Objects.isNull(dtoPostMeasure.sensorId)) return null;
        NumSensor entity = NumSensor.findById(dtoPostMeasure.sensorId);
        NumMeasure numMeasure = new NumMeasure();
        if(entity == null) return null;
        DTONumMeasure dtoNumMeasure = new DTONumMeasure();

        numMeasure.id = null;
        numMeasure.numData = dtoPostMeasure.numData;
        numMeasure.timestamp = dtoPostMeasure.timestamp;
        numMeasure.numSensor = entity;
        numMeasure.persist();

        dtoNumMeasure.id = numMeasure.id;
        dtoNumMeasure.numData = numMeasure.numData;
        dtoNumMeasure.timestamp = numMeasure.timestamp;
        dtoNumMeasure.numSensor = sensor_mapper.fromNumSensorToDtoPlainNumSensor(entity);

        return Response.status(Status.CREATED).entity(dtoNumMeasure).build();
    }
 
    @POST
    @Path("/analogsensor")
    @Transactional
    @Operation(summary = "Create an analog sensor in skilift")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensor.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createAnalogSensor(@Valid DTOPostSensor dtoPostSensor){
        
        if(Objects.isNull(dtoPostSensor.skiLiftId)) return null;

        SkiLift skilift = SkiLift.findById(dtoPostSensor.skiLiftId);
        if(skilift == null) return null;

        SensorType sensorType = SensorType.findById(dtoPostSensor.sensorTypeId);
        if(sensorType == null) return null;

        AnalogSensor analogSensor = new AnalogSensor();
        DTOAnalogSensor dtoAnalogSensor = new DTOAnalogSensor();

        analogSensor.id = null;
        analogSensor.manufacturer = dtoPostSensor.manufacturer;
        analogSensor.reference = dtoPostSensor.reference;
        analogSensor.skiLift = skilift;
        analogSensor.sensorType = sensorType;
        analogSensor.persist();

        dtoAnalogSensor.id = analogSensor.id;
        dtoAnalogSensor.manufacturer = analogSensor.manufacturer;
        dtoAnalogSensor.reference = analogSensor.reference;
        dtoAnalogSensor.skiLift = skiLift_mapper.fromSkiLiftToDtoPlainSkiLift(skilift);
        dtoAnalogSensor.sensorType= sensorType_mapper.fromSensorTypeToDtoPlainSensorType(sensorType);

        return Response.status(Status.CREATED).entity(dtoAnalogSensor).build();
    }

    @POST
    @Path("/numsensor")
    @Transactional
    @Operation(summary = "Create a num sensor in skilift")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSensor.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createSensor(@Valid DTOPostSensor dtoPostSensor){

        if(Objects.isNull(dtoPostSensor.skiLiftId)) return null;
        SkiLift skilift = SkiLift.findById(dtoPostSensor.skiLiftId);
        NumSensor numSensor = new NumSensor();
        if(skilift == null) return null;
        DTONumSensorWithSkiLift dtoNumSensorWithSkiLift = new DTONumSensorWithSkiLift();

        numSensor.id = null;
        numSensor.manufacturer = dtoPostSensor.manufacturer;
        numSensor.reference = dtoPostSensor.reference;
        numSensor.skiLift = skilift;
        numSensor.persist();

        dtoNumSensorWithSkiLift.id = numSensor.id;
        dtoNumSensorWithSkiLift.manufacturer = numSensor.manufacturer;
        dtoNumSensorWithSkiLift.reference = numSensor.reference;
        dtoNumSensorWithSkiLift.skiLift = skiLift_mapper.fromSkiLiftToDtoPlainSkiLift(skilift);

        return Response.status(Status.CREATED).entity(dtoNumSensorWithSkiLift).build();
    }

    @POST
    @Path("/skilift")
    @Transactional
    @Operation(summary = "Create a new skilift in station")
    @APIResponse(responseCode = "201", 
                content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = DTOPlainSkiLift.class)))
    @APIResponse(responseCode = "400", description = "Missing attribute(s)")
    public Response createSkiLift(@Valid DTOPostSkiLift dtoPostSkiLift){

        if(Objects.isNull(dtoPostSkiLift.stationId)) return null;
        Station entity = Station.findById(dtoPostSkiLift.stationId);
        SkiLift skiLift = new SkiLift();
        if(entity == null) return null;
        DTOSkiLiftWithStation dtoSkiLiftWithStation = new DTOSkiLiftWithStation();

        skiLift.id = null;
        skiLift.name = dtoPostSkiLift.name;
        skiLift.longitude = dtoPostSkiLift.longitude;
        skiLift.latitude = dtoPostSkiLift.latitude;
        skiLift.open = dtoPostSkiLift.open;
        skiLift.station = entity;
        skiLift.persist();

        dtoSkiLiftWithStation.id = skiLift.id;
        dtoSkiLiftWithStation.name = skiLift.name;
        dtoSkiLiftWithStation.longitude = skiLift.longitude;
        dtoSkiLiftWithStation.latitude = skiLift.latitude;
        dtoSkiLiftWithStation.station = station_mapper.fromStationToDtoPlainStation(entity);

        return Response.status(Status.CREATED).entity(dtoSkiLiftWithStation).build();
    }
}