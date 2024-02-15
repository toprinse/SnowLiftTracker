package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensor;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumSensor;
import fr.esisar.snowlifttracker.model.Sensor;

public interface PlainSensorMapper {

    // This mapper will change when implementing `abstract` class

    default DTOPlainSensor fromSensorToDtoPlainSensor(Sensor sensor){
        if(sensor instanceof NumSensor){
            return fromNumSensorToDtoPlainNumSensor((NumSensor) sensor);
        }
        if(sensor instanceof AnalogSensor){
            return fromAnalogSensorToDtoPlainAnalogSensor((AnalogSensor) sensor);
        }
        throw new IllegalArgumentException("Sensor Type Unknown");
    }

    default Sensor toSensorFromDtoPlainSensor(DTOPlainSensor dtoPlainSensor){
        if(dtoPlainSensor instanceof DTOPlainNumSensor){
            return toNumSensorFromDtoPlainNumSensor((DTOPlainNumSensor) dtoPlainSensor);
        }
        if(dtoPlainSensor instanceof DTOPlainAnalogSensor){
            return toAnalogSensorFromDtoPlainAnalogSensor((DTOPlainAnalogSensor) dtoPlainSensor);
        }
        throw new IllegalArgumentException("Sensor Type Unknown");
    }

    /************************************************** ANALOG SENSOR METHOD **********************************************************/

    /**********************
     * AnalogSensor Alone * 
     **********************/
    DTOPlainAnalogSensor fromAnalogSensorToDtoPlainAnalogSensor(AnalogSensor analogSensor);
    // @Mapping(target = "analogMeasureList", ignore = true)
    // @Mapping(target = "analogSensorUnit", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    AnalogSensor toAnalogSensorFromDtoPlainAnalogSensor(DTOPlainAnalogSensor dtoPlainAnalogSensor);

    /**************************************************** NUM SENSOR METHOD ********************************************************/

    /*******************
     * NumSensor Alone * 
     *******************/
    DTOPlainNumSensor fromNumSensorToDtoPlainNumSensor(NumSensor numSensor);
    // @Mapping(target = "numMeasureList", ignore = true)
    // @Mapping(target = "numSensorState", ignore = true)
    // @Mapping(target = "sensorType", ignore = true)
    // @Mapping(target = "skiLift", ignore = true)
    NumSensor toNumSensorFromDtoPlainNumSensor(DTOPlainNumSensor dtoPlainNumSensor);
}
