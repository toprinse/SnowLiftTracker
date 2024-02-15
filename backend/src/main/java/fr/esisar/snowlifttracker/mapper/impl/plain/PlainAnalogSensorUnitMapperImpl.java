package fr.esisar.snowlifttracker.mapper.impl.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensorUnit;
import fr.esisar.snowlifttracker.model.AnalogSensorUnit;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.plain.PlainAnalogSensorUnitMapper;

@Singleton
@Named
public class PlainAnalogSensorUnitMapperImpl implements PlainAnalogSensorUnitMapper {

    @Override
    public DTOPlainAnalogSensorUnit fromAnalogSensorUnitToDtoPlainAnalogSensorUnit(AnalogSensorUnit analogSensorUnit) {
        if ( analogSensorUnit == null ) {
            return null;
        }

        DTOPlainAnalogSensorUnit dTOPlainAnalogSensorUnit = new DTOPlainAnalogSensorUnit();

        dTOPlainAnalogSensorUnit.id = analogSensorUnit.id;
        dTOPlainAnalogSensorUnit.unit = analogSensorUnit.unit;

        return dTOPlainAnalogSensorUnit;
    }

    @Override
    public AnalogSensorUnit toAnalogSensorUnitFromDtoPlainAnalogSensorUnit(DTOPlainAnalogSensorUnit dtoPlainAnalogSensorUnit) {
        if ( dtoPlainAnalogSensorUnit == null ) {
            return null;
        }

        AnalogSensorUnit analogSensorUnit = new AnalogSensorUnit();

        analogSensorUnit.id = dtoPlainAnalogSensorUnit.id;
        analogSensorUnit.unit = dtoPlainAnalogSensorUnit.unit;

        return analogSensorUnit;
    }
}
