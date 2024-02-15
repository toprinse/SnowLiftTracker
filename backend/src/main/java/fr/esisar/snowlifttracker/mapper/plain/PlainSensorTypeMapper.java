package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensorType;
import fr.esisar.snowlifttracker.model.SensorType;

public interface PlainSensorTypeMapper {
    
    /********************
     * SensorType Alone * 
     ********************/
    DTOPlainSensorType fromSensorTypeToDtoPlainSensorType(SensorType sensorType);
    //@Mapping(target = "sensorList", ignore = true)
    SensorType toSensorTypeFromDtoPlainSensorType(DTOPlainSensorType dtoPlainSensorType);
}
