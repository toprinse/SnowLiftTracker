package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensorUnit;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensorType;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;

public class DTOAnalogSensor extends DTOPlainAnalogSensor{
    
    public List<DTOPlainAnalogMeasure> analogMeasureList;
    public DTOPlainAnalogSensorUnit analogSensorUnit;
    public DTOPlainSensorType sensorType;
    public DTOPlainSkiLift skiLift;
}
