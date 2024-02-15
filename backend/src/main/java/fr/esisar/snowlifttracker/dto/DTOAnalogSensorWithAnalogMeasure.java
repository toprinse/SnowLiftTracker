package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;

public class DTOAnalogSensorWithAnalogMeasure extends DTOPlainAnalogSensor {
    
    public List<DTOPlainAnalogMeasure> analogMeasureList;
}
