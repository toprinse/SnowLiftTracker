package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensorState;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensorType;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;

public class DTONumSensor extends DTOPlainNumSensor{
    
    public DTOPlainNumSensorState numSensorState;
    public List<DTOPlainNumMeasure> numMeasureList;
    public DTOPlainSensorType sensorType;
    public DTOPlainSkiLift skiLift;
}
