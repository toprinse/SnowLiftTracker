package fr.esisar.snowlifttracker.mapper;

import fr.esisar.snowlifttracker.dto.DTOSkiLift;
import fr.esisar.snowlifttracker.dto.DTOSkiLiftWithSensor;
import fr.esisar.snowlifttracker.dto.DTOSkiLiftWithStation;
import fr.esisar.snowlifttracker.model.SkiLift;

public interface SkiLiftMapper {

     /***********************
     * SkiLift With Station * 
     ************************/
    DTOSkiLiftWithStation fromSkiLiftToDtoSkiLiftWithStation(SkiLift skiLift);
    // Not mapped : sensorList
    SkiLift toSkiLiftFromDtoSkiLiftWithStation(DTOSkiLiftWithStation dtoSkiLiftWithStation);

    /***********************
     * SkiLift With Sensor * 
     ************************/
    DTOSkiLiftWithSensor fromSkiLiftToDtoSkiLiftWithSensor(SkiLift skiLift);
    // Not mapped : station
    SkiLift toSkiLiftFromDtoSkiLiftWithSensor(DTOSkiLiftWithSensor dtoSkiLiftWithSensor);

    /****************
     * SkiLift Full * 
     ****************/
    DTOSkiLift fromSkiLiftToDtoSkiLift(SkiLift skiLift);
    SkiLift toSkiLiftFromDtoSkiLift(DTOSkiLift dtoSkiLift);
}
