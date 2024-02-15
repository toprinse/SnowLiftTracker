package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;

public class DTONumSensorWithNumMeasure extends DTOPlainNumSensor{
    
    public List<DTOPlainNumMeasure> numMeasureList;
}
