package fr.esisar.snowlifttracker.mapper;

import fr.esisar.snowlifttracker.dto.DTOAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTONumMeasure;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.NumMeasure;


public interface MeasureMapper {

    /************************************************** ANALOG MEASURE METHOD **********************************************************/

    /*****************************
     * AnalogMeasure With Sensor * 
     *****************************/
    DTOAnalogMeasure fromAnalogMeasureToDtoAnalogMeasure(AnalogMeasure analogMeasure);
    AnalogMeasure toAnalogMeasureFromDtoAnalogMeasure(DTOAnalogMeasure dtoAnalogMeasure);

    /**************************************************** NUM MEASURE METHOD ********************************************************/

    /**************************
     * NumMeasure With Sensor * 
     **************************/
    DTONumMeasure fromNumMeasureToDtoNumMeasure(NumMeasure numMeasure);
    NumMeasure toNumMeasureFromDtoNumMeasure(DTONumMeasure dtoNumMeasure);
}
