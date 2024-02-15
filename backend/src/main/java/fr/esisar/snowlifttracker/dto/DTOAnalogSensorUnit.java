package fr.esisar.snowlifttracker.dto;

import java.util.List;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensorUnit;

public class DTOAnalogSensorUnit extends DTOPlainAnalogSensorUnit{
    
    public List<DTOPlainAnalogSensor> analogSensorList;
}
