package fr.esisar.snowlifttracker.mapper.impl.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensorState;
import fr.esisar.snowlifttracker.model.NumSensorState;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.plain.PlainNumSensorStateMapper;

@Singleton
@Named
public class PlainNumSensorStateMapperImpl implements PlainNumSensorStateMapper {

    @Override
    public DTOPlainNumSensorState fromNumSensorStateToDtoPlainNumSensorState(NumSensorState numSensorState) {
        if ( numSensorState == null ) {
            return null;
        }

        DTOPlainNumSensorState dTOPlainNumSensorState = new DTOPlainNumSensorState();

        dTOPlainNumSensorState.id = numSensorState.id;
        dTOPlainNumSensorState.high = numSensorState.high;
        dTOPlainNumSensorState.low = numSensorState.low;

        return dTOPlainNumSensorState;
    }

    @Override
    public NumSensorState toNumSensorStateFromDtoPlainNumSensorState(DTOPlainNumSensorState dtoPlainNumSensorState) {
        if ( dtoPlainNumSensorState == null ) {
            return null;
        }

        NumSensorState numSensorState = new NumSensorState();

        numSensorState.id = dtoPlainNumSensorState.id;
        numSensorState.high = dtoPlainNumSensorState.high;
        numSensorState.low = dtoPlainNumSensorState.low;

        return numSensorState;
    }
}
