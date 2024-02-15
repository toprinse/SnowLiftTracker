package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensorType;

public class DTOSensorType extends DTOPlainSensorType {
    
    public List<DTOPlainSensor> sensorList; 
}
