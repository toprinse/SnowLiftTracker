package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.model.SkiLift;

public interface PlainSkiLiftMapper {

    /*****************
     * SkiLift Alone * 
     *****************/
    DTOPlainSkiLift fromSkiLiftToDtoPlainSkiLift(SkiLift skiLift);
    // @Mapping(target = "sensorList", ignore = true)
    // @Mapping(target = "station", ignore = true)
    SkiLift toSkiLiftFromDtoPlainSkiLift(DTOPlainSkiLift dtoPlainSkiLift);
}
