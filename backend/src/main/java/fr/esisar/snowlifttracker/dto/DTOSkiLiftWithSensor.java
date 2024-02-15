package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;


public class DTOSkiLiftWithSensor extends DTOPlainSkiLift {
    
    public List<DTOPlainSensor> sensorList;
}
