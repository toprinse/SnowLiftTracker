package fr.esisar.snowlifttracker.mapper.impl.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumMeasure;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.NumMeasure;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.plain.PlainMeasureMapper;


@Singleton
@Named
public class PlainMeasureMapperImpl implements PlainMeasureMapper {

    @Override
    public DTOPlainAnalogMeasure fromAnalogMeasureToDtoPlainAnalogMeasure(AnalogMeasure analogMeasure) {
        if ( analogMeasure == null ) {
            return null;
        }

        DTOPlainAnalogMeasure dTOPlainAnalogMeasure = new DTOPlainAnalogMeasure();

        dTOPlainAnalogMeasure.id = analogMeasure.id;
        dTOPlainAnalogMeasure.timestamp = analogMeasure.timestamp;
        dTOPlainAnalogMeasure.analogData = analogMeasure.analogData;

        return dTOPlainAnalogMeasure;
    }

    @Override
    public AnalogMeasure toAnalogMeasureFromDtoPlainAnalogMeasure(DTOPlainAnalogMeasure dtoPlainAnalogMeasure) {
        if ( dtoPlainAnalogMeasure == null ) {
            return null;
        }

        AnalogMeasure analogMeasure = new AnalogMeasure();

        analogMeasure.id = dtoPlainAnalogMeasure.id;
        analogMeasure.timestamp = dtoPlainAnalogMeasure.timestamp;
        analogMeasure.analogData = dtoPlainAnalogMeasure.analogData;

        return analogMeasure;
    }

    @Override
    public DTOPlainNumMeasure fromNumMeasureToDtoPlainNumMeasure(NumMeasure numMeasure) {
        if ( numMeasure == null ) {
            return null;
        }

        DTOPlainNumMeasure dTOPlainNumMeasure = new DTOPlainNumMeasure();

        dTOPlainNumMeasure.id = numMeasure.id;
        dTOPlainNumMeasure.timestamp = numMeasure.timestamp;
        dTOPlainNumMeasure.numData = numMeasure.numData;

        return dTOPlainNumMeasure;
    }

    @Override
    public NumMeasure toNumMeasureFromDtoPlainNumMeasure(DTOPlainNumMeasure dtoPlainNumMeasure) {
        if ( dtoPlainNumMeasure == null ) {
            return null;
        }

        NumMeasure numMeasure = new NumMeasure();

        numMeasure.id = dtoPlainNumMeasure.id;
        numMeasure.timestamp = dtoPlainNumMeasure.timestamp;
        numMeasure.numData = dtoPlainNumMeasure.numData;

        return numMeasure;
    }
}
