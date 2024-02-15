package fr.esisar.snowlifttracker.mapper.impl;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

import fr.esisar.snowlifttracker.dto.DTOSensorType;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.mapper.SensorTypeMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;
import fr.esisar.snowlifttracker.model.Sensor;
import fr.esisar.snowlifttracker.model.SensorType;



@Singleton 
@Named
public class SensorTypeMapperImpl implements SensorTypeMapper {

    private final PlainSensorMapper sensorMapper;

    @Inject
    SensorTypeMapperImpl(PlainSensorMapper sensorMapper){
        this.sensorMapper = sensorMapper;
    }

    public DTOSensorType fromSensorTypeToDtoSensorType(SensorType sensorType){
        if(sensorType == null){
            return null;
        }

        DTOSensorType dtoSensorType = new DTOSensorType();

        dtoSensorType.id = sensorType.id;
        dtoSensorType.type = sensorType.type;
        dtoSensorType.sensorList = sensorListToDtoPlainSensorList(sensorType.sensorList);

        return dtoSensorType;
    }
    public SensorType toSensorTypeFromDtoSensorType(DTOSensorType dtoSensorType){
        if(dtoSensorType == null){
            return null;
        }

        SensorType sensorType = new SensorType();

        sensorType.id = dtoSensorType.id;
        sensorType.type = dtoSensorType.type;
        sensorType.sensorList = dtoPlainSensorListToSensorList(dtoSensorType.sensorList);

        return sensorType;
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
