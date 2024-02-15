package fr.esisar.snowlifttracker.mapper.impl.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.model.SkiLift;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.plain.PlainSkiLiftMapper;

@Singleton
@Named
public class PlainSkiLiftMapperImpl implements PlainSkiLiftMapper {

    @Override
    public DTOPlainSkiLift fromSkiLiftToDtoPlainSkiLift(SkiLift skiLift) {
        if ( skiLift == null ) {
            return null;
        }

        DTOPlainSkiLift dTOPlainSkiLift = new DTOPlainSkiLift();

        dTOPlainSkiLift.id = skiLift.id;
        dTOPlainSkiLift.name = skiLift.name;
        dTOPlainSkiLift.longitude = skiLift.longitude;
        dTOPlainSkiLift.latitude = skiLift.latitude;
        dTOPlainSkiLift.open = skiLift.open;

        return dTOPlainSkiLift;
    }

    @Override
    public SkiLift toSkiLiftFromDtoPlainSkiLift(DTOPlainSkiLift dtoPlainSkiLift) {
        if ( dtoPlainSkiLift == null ) {
            return null;
        }

        SkiLift skiLift = new SkiLift();

        skiLift.id = dtoPlainSkiLift.id;
        skiLift.name = dtoPlainSkiLift.name;
        skiLift.longitude = dtoPlainSkiLift.longitude;
        skiLift.latitude = dtoPlainSkiLift.latitude;
        if ( dtoPlainSkiLift.open != null ) {
            skiLift.open = dtoPlainSkiLift.open;
        }

        return skiLift;
    }
}
