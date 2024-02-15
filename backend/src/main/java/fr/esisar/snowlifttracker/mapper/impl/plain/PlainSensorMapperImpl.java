package fr.esisar.snowlifttracker.mapper.impl.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumSensor;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;

@Singleton
@Named
public class PlainSensorMapperImpl implements PlainSensorMapper {

    @Override
    public DTOPlainAnalogSensor fromAnalogSensorToDtoPlainAnalogSensor(AnalogSensor analogSensor) {
        if ( analogSensor == null ) {
            return null;
        }

        DTOPlainAnalogSensor dTOPlainAnalogSensor = new DTOPlainAnalogSensor();

        dTOPlainAnalogSensor.id = analogSensor.id;
        dTOPlainAnalogSensor.reference = analogSensor.reference;
        dTOPlainAnalogSensor.manufacturer = analogSensor.manufacturer;

        return dTOPlainAnalogSensor;
    }

    @Override
    public AnalogSensor toAnalogSensorFromDtoPlainAnalogSensor(DTOPlainAnalogSensor dtoPlainAnalogSensor) {
        if ( dtoPlainAnalogSensor == null ) {
            return null;
        }

        AnalogSensor analogSensor = new AnalogSensor();

        analogSensor.id = dtoPlainAnalogSensor.id;
        analogSensor.reference = dtoPlainAnalogSensor.reference;
        analogSensor.manufacturer = dtoPlainAnalogSensor.manufacturer;

        return analogSensor;
    }

   

    @Override
    public DTOPlainNumSensor fromNumSensorToDtoPlainNumSensor(NumSensor numSensor) {
        if ( numSensor == null ) {
            return null;
        }

        DTOPlainNumSensor dTOPlainNumSensor = new DTOPlainNumSensor();

        dTOPlainNumSensor.id = numSensor.id;
        dTOPlainNumSensor.reference = numSensor.reference;
        dTOPlainNumSensor.manufacturer = numSensor.manufacturer;

        return dTOPlainNumSensor;
    }

    @Override
    public NumSensor toNumSensorFromDtoPlainNumSensor(DTOPlainNumSensor dtoPlainNumSensor) {
        if ( dtoPlainNumSensor == null ) {
            return null;
        }

        NumSensor numSensor = new NumSensor();

        numSensor.id = dtoPlainNumSensor.id;
        numSensor.reference = dtoPlainNumSensor.reference;
        numSensor.manufacturer = dtoPlainNumSensor.manufacturer;

        return numSensor;
    }
}
