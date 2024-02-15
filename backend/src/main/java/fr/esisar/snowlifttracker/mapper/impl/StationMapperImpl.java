package fr.esisar.snowlifttracker.mapper.impl;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

import fr.esisar.snowlifttracker.dto.DTOStation;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;
import fr.esisar.snowlifttracker.mapper.StationMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSkiLiftMapper;
import fr.esisar.snowlifttracker.model.NumSensor;
import fr.esisar.snowlifttracker.model.SkiLift;
import fr.esisar.snowlifttracker.model.Station;


@Singleton
@Named
public class StationMapperImpl implements StationMapper {

    private final PlainSkiLiftMapper skiLiftMapper;

    @Inject
    public StationMapperImpl(PlainSkiLiftMapper skiLiftMapper){
        this.skiLiftMapper = skiLiftMapper;
    }

    public DTOStation fromStationToDtoStation(Station station){
        if(station == null){
            return null;
        }

        DTOStation dtoStation = new DTOStation();

        dtoStation.id = station.id;
        dtoStation.latitude = station.latitude;
        dtoStation.longitude = station.longitude;
        dtoStation.name = station.name;
        dtoStation.skiLiftList = (skiLiftListToDtoPlainSkiLiftList(station.skiLiftList));

        return dtoStation;
    }

    public Station toStationFromDtoStation(DTOStation dtoStation){
        if(dtoStation == null){
            return null;
        }

        Station station = new Station();

        station.id =  dtoStation.id;
        station.latitude = station.latitude;
        station.longitude = dtoStation.longitude;
        station.name = dtoStation.name;
        station.skiLiftList = dtoPlainSkiLiftListToSkiLiftList(dtoStation.skiLiftList);

        return station;
    }

    protected List<SkiLift> dtoPlainSkiLiftListToSkiLiftList(List<DTOPlainSkiLift> list) {
        if ( list == null ) {
            return null;
        }

        List<SkiLift> list1 = new ArrayList<SkiLift>( list.size() );
        for ( DTOPlainSkiLift dtoPlainSkiLift : list ) {
            list1.add( skiLiftMapper.toSkiLiftFromDtoPlainSkiLift( dtoPlainSkiLift ) );
        }

        return list1;
    }

    protected List<DTOPlainSkiLift> skiLiftListToDtoPlainSkiLiftList(List<SkiLift> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOPlainSkiLift> list1 = new ArrayList<DTOPlainSkiLift>( list.size() );
        for ( SkiLift skiLift : list ) {
            list1.add( skiLiftMapper.fromSkiLiftToDtoPlainSkiLift( skiLift ) );
        }

        return list1;
    }
}
