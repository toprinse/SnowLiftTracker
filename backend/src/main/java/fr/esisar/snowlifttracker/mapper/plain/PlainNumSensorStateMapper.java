package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensorState;
import fr.esisar.snowlifttracker.model.NumSensorState;

public interface PlainNumSensorStateMapper {
    
    /****************************
     * NumNumSensorState  Alone * 
     ****************************/
    DTOPlainNumSensorState fromNumSensorStateToDtoPlainNumSensorState(NumSensorState numSensorState);
    //@Mapping(target = "numSensorList", ignore = true)
    NumSensorState toNumSensorStateFromDtoPlainNumSensorState(DTOPlainNumSensorState dtoPlainNumSensorState);
}
