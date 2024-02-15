package fr.esisar.snowlifttracker.mapper;

import fr.esisar.snowlifttracker.dto.DTOAnalogSensor;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithSensorType;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithSkiLift;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithUnit;
import fr.esisar.snowlifttracker.dto.DTONumSensor;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithNumMeasure;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithSensorType;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithSkiLift;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithState;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumSensor;


public interface SensorMapper{

    /************************************************** ANALOG SENSOR METHOD **********************************************************/

    /**************************
     * AnalogSensor With Unit * 
     **************************/
    DTOAnalogSensorWithUnit fromAnalogMeasureToDtoAnalogSensorWithUnit(AnalogSensor analogSensor);
    // @Mapping(target = "analogMeasureList", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    AnalogSensor toAnalogMeasureFromDtoAnalogSensorWithUnit(DTOAnalogSensorWithUnit dtoAnalogSensorWithUnit);

    /*****************************
     * AnalogSensor With Measure * 
     *****************************/
    DTOAnalogSensorWithAnalogMeasure fromAnalogMeasureToDtoAnalogSensorWithAnalogMeasure(AnalogSensor analogSensor);
    // @Mapping(target = "analogSensorUnit", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    AnalogSensor toAnalogMeasureFromDtoAnalogSensorWithAnalogMeasure(DTOAnalogSensorWithAnalogMeasure dtoAnalogSensorWithAnalogMeasure);

    /**************************
     * AnalogSensor With Type * 
     **************************/
    DTOAnalogSensorWithSensorType fromAnalogSensorToDtoAnalogSensorWithSensorType(AnalogSensor analogSensor);
    // @Mapping(target = "analogMeasureList", ignore = true)
    // @Mapping(target = "analogSensorUnit", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    AnalogSensor toAnalogSensorFromDtoAnalogSensorWithSensorType(DTOAnalogSensorWithSensorType dtoAnalogSensorWithSensorType);

    /*****************************
     * AnalogSensor With SkiLift * 
     *****************************/
    DTOAnalogSensorWithSkiLift fromAnalogSensorToDtoAnalogSensorWithSkiLift(AnalogSensor analogSensor);
    // @Mapping(target = "analogMeasureList", ignore = true)
    // @Mapping(target = "analogSensorUnit", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    AnalogSensor toAnalogSensorFromDtoAnalogSensorWithSkiLift(DTOAnalogSensorWithSkiLift dtoAnalogSensorWithSkiLift);

    /****************
     * AnalogSensor * 
     ****************/
    DTOAnalogSensor fromAnalogSensorToDtoAnalogSensor(AnalogSensor analogSensor);
    AnalogSensor toAnalogSensorFromDtoAnalogSensor(DTOAnalogSensor dtoAnalogSensor);

    /**************************************************** NUM SENSOR METHOD ********************************************************/

    /***********************
     * NumSensor With State * 
     ***********************/
    DTONumSensorWithState fromNumSensorToDtoNumSensorWithState(NumSensor numSensor);
    // @Mapping(target = "numMeasureList", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    NumSensor toNumSensorFromDtoNumSensorWithState(DTONumSensorWithState dtoNumSensorWithState);

    /**************************
     * NumSensor With Measure * 
     **************************/
    DTONumSensorWithNumMeasure fromNumSensorToDtoNumSensorWithNumMeasure(NumSensor numSensor);
    // @Mapping(target = "numSensorState", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    NumSensor toNumSensorFromDtoNumSensorWithState(DTONumSensorWithNumMeasure dtoNumSensorWithNumMeasure);

    /***********************
     * NumSensor With Type * 
     ***********************/
    DTONumSensorWithSensorType fromNumSensorToDtoNumSensorWithSensorType(NumSensor numSensor);
    // @Mapping(target = "numMeasureList", ignore = true)
    // @Mapping(target = "numSensorState", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    NumSensor toNumSensorFromDtoNumSensorWithSensorType(DTONumSensorWithSensorType dtoNumSensorWithSensorType);

    /***********************
     * NumSensor With Type * 
     ***********************/
    DTONumSensorWithSkiLift fromNumSensorToDtoNumSensorWithSkiLift(NumSensor numSensor);
    // @Mapping(target = "numMeasureList", ignore = true)
    // @Mapping(target = "numSensorState", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    NumSensor toNumSensorFRomDtoNumSensorWithSkiLift(DTONumSensorWithSkiLift dtoNumSensorWithSkiLift);
    
    /*************
     * NumSensor * 
     *************/
    DTONumSensor fromNumSensorToDtoNumSensor(NumSensor numSensor);
    NumSensor toNumSensorFRomDtoNumSensor(DTONumSensor dtoNumSensor);
}
