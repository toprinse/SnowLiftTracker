package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.Measure;
import fr.esisar.snowlifttracker.model.NumMeasure;


public interface PlainMeasureMapper {
    
    // This mapper will change when implementing `abstract` class

    default DTOPlainMeasure fromMeasureToDtoPlainMeasure(Measure measure){
        if(measure instanceof NumMeasure){
            return fromNumMeasureToDtoPlainNumMeasure((NumMeasure) measure);
        }
        if(measure instanceof AnalogMeasure){
            return fromAnalogMeasureToDtoPlainAnalogMeasure((AnalogMeasure) measure);
        }
        throw new IllegalArgumentException("Sensor Type Unknown");
    }

    /************************************************** ANALOG MEASURE METHOD **********************************************************/

    /***********************
     * AnalogMeasure Alone * 
     ***********************/
    DTOPlainAnalogMeasure fromAnalogMeasureToDtoPlainAnalogMeasure(AnalogMeasure analogMeasure);
    //@Mapping(target = "analogSensor", ignore = true)
    AnalogMeasure toAnalogMeasureFromDtoPlainAnalogMeasure(DTOPlainAnalogMeasure dtoPlainAnalogMeasure);

    /**************************************************** NUM MEASURE METHOD ********************************************************/

    /********************
     * NumMeasure Alone * 
     ********************/
    DTOPlainNumMeasure fromNumMeasureToDtoPlainNumMeasure(NumMeasure numMeasure);
    //@Mapping(target = "numSensor", ignore = true)
    NumMeasure toNumMeasureFromDtoPlainNumMeasure(DTOPlainNumMeasure dtoPlainNumMeasure);
}
