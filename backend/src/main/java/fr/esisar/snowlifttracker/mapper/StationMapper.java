package fr.esisar.snowlifttracker.mapper;

import fr.esisar.snowlifttracker.dto.DTOStation;
import fr.esisar.snowlifttracker.model.Station;

public interface StationMapper {
    DTOStation fromStationToDtoStation(Station station);
    Station toStationFromDtoStation(DTOStation dtoStation);
}
