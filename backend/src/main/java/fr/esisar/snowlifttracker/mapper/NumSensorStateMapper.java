package fr.esisar.snowlifttracker.mapper;

import fr.esisar.snowlifttracker.dto.DTONumSensorState;
import fr.esisar.snowlifttracker.model.NumSensorState;

public interface NumSensorStateMapper{
    
    DTONumSensorState fromNumSensorStateToDtoNumSensorState(NumSensorState numSensorState);
    NumSensorState toNumSensorStateFromDtoNumSensorState(DTONumSensorState dtoNumSensorState);
}
