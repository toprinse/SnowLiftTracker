package fr.esisar.snowlifttracker.mapper.impl.plain;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;
import fr.esisar.snowlifttracker.model.Station;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.plain.PlainStationMapper;

@Singleton
@Named
public class PlainStationMapperImpl implements PlainStationMapper {

    @Override
    public DTOPlainStation fromStationToDtoPlainStation(Station station) {
        if ( station == null ) {
            return null;
        }

        DTOPlainStation dTOPlainStation = new DTOPlainStation();

        dTOPlainStation.id = station.id;
        dTOPlainStation.name = station.name;
        dTOPlainStation.longitude = station.longitude;
        dTOPlainStation.latitude = station.latitude;

        return dTOPlainStation;
    }

    @Override
    public Station toStationFromDtoPlainStation(DTOPlainStation dtoPlainStation) {
        if ( dtoPlainStation == null ) {
            return null;
        }

        Station station = new Station();

        station.id = dtoPlainStation.id;
        station.name = dtoPlainStation.name;
        station.longitude = dtoPlainStation.longitude;
        station.latitude = dtoPlainStation.latitude;

        return station;
    }
}
