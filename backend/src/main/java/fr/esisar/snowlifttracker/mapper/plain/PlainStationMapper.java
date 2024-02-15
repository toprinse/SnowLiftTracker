package fr.esisar.snowlifttracker.mapper.plain;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;
import fr.esisar.snowlifttracker.model.Station;

public interface PlainStationMapper {

    /*****************
     * Station Alone * 
     *****************/
    DTOPlainStation fromStationToDtoPlainStation(Station station);
    Station toStationFromDtoPlainStation(DTOPlainStation dtoPlainStation);
}
