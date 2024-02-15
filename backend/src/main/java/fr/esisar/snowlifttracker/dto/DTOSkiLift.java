package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;

public class DTOSkiLift extends DTOPlainSkiLift{
    
    public List<DTOPlainSensor> sensorList;
    public DTOPlainStation station;
}
