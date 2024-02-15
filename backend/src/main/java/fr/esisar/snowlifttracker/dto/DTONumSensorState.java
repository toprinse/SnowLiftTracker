package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensorState;

public class DTONumSensorState extends DTOPlainNumSensorState {
    
    public List<DTOPlainNumSensor> numSensorList;
}
