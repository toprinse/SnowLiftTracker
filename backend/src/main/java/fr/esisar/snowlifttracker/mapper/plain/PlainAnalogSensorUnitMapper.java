package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensorUnit;
import fr.esisar.snowlifttracker.model.AnalogSensorUnit;

public interface PlainAnalogSensorUnitMapper {
    
    /**************************
     * AnalogSensorUnit Alone * 
     **************************/
    DTOPlainAnalogSensorUnit fromAnalogSensorUnitToDtoPlainAnalogSensorUnit(AnalogSensorUnit analogSensorUnit);
    //@Mapping(target = "analogSensorList", ignore = true)
    AnalogSensorUnit toAnalogSensorUnitFromDtoPlainAnalogSensorUnit(DTOPlainAnalogSensorUnit dtoPlainAnalogSensorUnit);
}
