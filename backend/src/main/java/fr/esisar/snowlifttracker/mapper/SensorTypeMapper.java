package fr.esisar.snowlifttracker.mapper;

import fr.esisar.snowlifttracker.dto.DTOSensorType;
import fr.esisar.snowlifttracker.model.SensorType;

public interface SensorTypeMapper{
    
    DTOSensorType fromSensorTypeToDtoSensorType(SensorType sensorType);
    SensorType toSensorTypeFromDtoSensorType(DTOSensorType dtoSensorType);
}
