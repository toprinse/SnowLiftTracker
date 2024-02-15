package fr.esisar.snowlifttracker.mapper.impl;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

import fr.esisar.snowlifttracker.dto.DTOSkiLift;
import fr.esisar.snowlifttracker.dto.DTOSkiLiftWithSensor;
import fr.esisar.snowlifttracker.dto.DTOSkiLiftWithStation;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.mapper.SkiLiftMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainStationMapper;
import fr.esisar.snowlifttracker.model.Sensor;
import fr.esisar.snowlifttracker.model.SkiLift;


@Singleton
@Named
public class SkiLiftMapperImpl implements SkiLiftMapper {
    
    private final PlainStationMapper stationMapper;
    private final PlainSensorMapper sensorMapper;

    @Inject
    SkiLiftMapperImpl(PlainStationMapper stationMapper, PlainSensorMapper sensorMapper){
        this.stationMapper = stationMapper;
        this.sensorMapper = sensorMapper;
    }

    public DTOSkiLiftWithStation fromSkiLiftToDtoSkiLiftWithStation(SkiLift skiLift){
        if(skiLift == null) return null;
        
        DTOSkiLiftWithStation dtoSkiLiftWithStation = new DTOSkiLiftWithStation();

        dtoSkiLiftWithStation.id = skiLift.id;
        dtoSkiLiftWithStation.latitude = skiLift.latitude;
        dtoSkiLiftWithStation.longitude = skiLift.longitude;
        dtoSkiLiftWithStation.name = skiLift.name;
        dtoSkiLiftWithStation.open = skiLift.open;
        dtoSkiLiftWithStation.station = stationMapper.fromStationToDtoPlainStation(skiLift.station);
        
        return dtoSkiLiftWithStation;
    }

    public SkiLift toSkiLiftFromDtoSkiLiftWithStation(DTOSkiLiftWithStation dtoSkiLiftWithStation){
        if(dtoSkiLiftWithStation == null) return null;

        SkiLift skiLift = new SkiLift();

        skiLift.id = dtoSkiLiftWithStation.id;
        skiLift.latitude = dtoSkiLiftWithStation.latitude;
        skiLift.longitude = dtoSkiLiftWithStation.longitude;
        skiLift.name = dtoSkiLiftWithStation.name;
        skiLift.open = dtoSkiLiftWithStation.open;
        skiLift.station = stationMapper.toStationFromDtoPlainStation(dtoSkiLiftWithStation.station);

        return skiLift;
    }

    public DTOSkiLiftWithSensor fromSkiLiftToDtoSkiLiftWithSensor(SkiLift skiLift){
        if(skiLift == null) return null;

        DTOSkiLiftWithSensor dtoSkiLiftWithSensor = new DTOSkiLiftWithSensor();

        dtoSkiLiftWithSensor.id = skiLift.id;
        dtoSkiLiftWithSensor.latitude = skiLift.latitude;
        dtoSkiLiftWithSensor.longitude = skiLift.longitude;
        dtoSkiLiftWithSensor.name = skiLift.name;
        dtoSkiLiftWithSensor.open = skiLift.open;
        dtoSkiLiftWithSensor.sensorList = sensorListToDtoPlainSensorList(skiLift.sensorList);

        return dtoSkiLiftWithSensor;
    }

    public SkiLift toSkiLiftFromDtoSkiLiftWithSensor(DTOSkiLiftWithSensor dtoSkiLiftWithSensor){
        if(dtoSkiLiftWithSensor == null) return null;

        SkiLift skiLift = new SkiLift();

        skiLift.id = dtoSkiLiftWithSensor.id;
        skiLift.latitude = dtoSkiLiftWithSensor.latitude;
        skiLift.longitude = dtoSkiLiftWithSensor.longitude;
        skiLift.name = dtoSkiLiftWithSensor.name;
        skiLift.open = dtoSkiLiftWithSensor.open;
        skiLift.sensorList = dtoPlainSensorListToSensorList(dtoSkiLiftWithSensor.sensorList);

        return skiLift;
    }

    public DTOSkiLift fromSkiLiftToDtoSkiLift(SkiLift skiLift){
        if(skiLift == null) return null;

        DTOSkiLift dtoSkiLift = new DTOSkiLift();

        dtoSkiLift.id = skiLift.id;
        dtoSkiLift.latitude = skiLift.latitude;
        dtoSkiLift.longitude = skiLift.longitude;
        dtoSkiLift.name = skiLift.name;
        dtoSkiLift.open = skiLift.open;
        dtoSkiLift.station = stationMapper.fromStationToDtoPlainStation(skiLift.station);
        dtoSkiLift.sensorList = sensorListToDtoPlainSensorList(skiLift.sensorList);

        return dtoSkiLift;
    }

    public SkiLift toSkiLiftFromDtoSkiLift(DTOSkiLift dtoSkiLift){
        if(dtoSkiLift == null) return null;

        SkiLift skiLift = new SkiLift();

        skiLift.id = dtoSkiLift.id;
        skiLift.latitude = dtoSkiLift.latitude;
        skiLift.longitude = dtoSkiLift.longitude;
        skiLift.name = dtoSkiLift.name;
        skiLift.open = dtoSkiLift.open;
        skiLift.sensorList = dtoPlainSensorListToSensorList(dtoSkiLift.sensorList);
        skiLift.station = stationMapper.toStationFromDtoPlainStation(dtoSkiLift.station);

        return skiLift;
    }

    protected List<Sensor> dtoPlainSensorListToSensorList(List<DTOPlainSensor> list){
        if(list == null) return null;

        List<Sensor> list1 = new ArrayList<Sensor>( list.size() );
        for ( DTOPlainSensor dtoPlainSensor : list ) {
            list1.add( sensorMapper.toSensorFromDtoPlainSensor( dtoPlainSensor ) );
        }
        return list1;
    }

    protected List<DTOPlainSensor> sensorListToDtoPlainSensorList(List<Sensor> list){
        if(list == null) return null;

        List<DTOPlainSensor> list1 = new ArrayList<DTOPlainSensor>( list.size() );
        for ( Sensor sensor : list ) {
            list1.add( sensorMapper.fromSensorToDtoPlainSensor( sensor ) );
        }
        return list1;
    }
}
