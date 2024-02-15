package fr.esisar.snowlifttracker.mapper;

import fr.esisar.snowlifttracker.dto.DTOAnalogSensorUnit;
import fr.esisar.snowlifttracker.model.AnalogSensorUnit;

public interface AnalogSensorUnitMapper {
    
    DTOAnalogSensorUnit fromAnalogSensorUnitToDtoAnalogSensorUnit(AnalogSensorUnit analogSensorUnit);
    AnalogSensorUnit toAnalogSensorUnitFromDtoAnalogSensorUnit(DTOAnalogSensorUnit dtoAnalogSensorUnit);
}
