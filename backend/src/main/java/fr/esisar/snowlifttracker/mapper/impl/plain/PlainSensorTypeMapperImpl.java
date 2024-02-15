package fr.esisar.snowlifttracker.mapper.impl.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensorType;
import fr.esisar.snowlifttracker.model.SensorType;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorTypeMapper;

@Singleton 
@Named
public class PlainSensorTypeMapperImpl implements PlainSensorTypeMapper {

    @Override
    public DTOPlainSensorType fromSensorTypeToDtoPlainSensorType(SensorType sensorType) {
        if ( sensorType == null ) {
            return null;
        }

        DTOPlainSensorType dTOPlainSensorType = new DTOPlainSensorType();

        dTOPlainSensorType.id = sensorType.id;
        dTOPlainSensorType.type = sensorType.type;

        return dTOPlainSensorType;
    }

    @Override
    public SensorType toSensorTypeFromDtoPlainSensorType(DTOPlainSensorType dtoPlainSensorType) {
        if ( dtoPlainSensorType == null ) {
            return null;
        }

        SensorType sensorType = new SensorType();

        sensorType.id = dtoPlainSensorType.id;
        sensorType.type = dtoPlainSensorType.type;

        return sensorType;
    }
}
