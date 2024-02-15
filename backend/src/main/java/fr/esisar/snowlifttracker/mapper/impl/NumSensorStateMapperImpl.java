package fr.esisar.snowlifttracker.mapper.impl;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

import fr.esisar.snowlifttracker.dto.DTONumSensorState;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import fr.esisar.snowlifttracker.mapper.NumSensorStateMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;
import fr.esisar.snowlifttracker.model.NumSensor;
import fr.esisar.snowlifttracker.model.NumSensorState;


@Singleton
@Named
public class NumSensorStateMapperImpl implements NumSensorStateMapper {

    private final PlainSensorMapper sensorMapper;

    @Inject
    public NumSensorStateMapperImpl(PlainSensorMapper sensorMapper){
        this.sensorMapper = sensorMapper;
    }

    public DTONumSensorState fromNumSensorStateToDtoNumSensorState(NumSensorState numSensorState){
        if(numSensorState ==null){
            return null;
        }

        DTONumSensorState dtoNumSensorState = new DTONumSensorState();

        dtoNumSensorState.id = numSensorState.id;
        dtoNumSensorState.high = numSensorState.high;
        dtoNumSensorState.low = numSensorState.low;
        dtoNumSensorState.numSensorList = NumMeasureListToDtoPlainNumMeasureList(numSensorState.numSensorList);
        
        return dtoNumSensorState;
    }
    public NumSensorState toNumSensorStateFromDtoNumSensorState(DTONumSensorState dtoNumSensorState){
        if(dtoNumSensorState ==null){
            return null;
        }

        NumSensorState numSensorState = new NumSensorState();

        numSensorState.id = dtoNumSensorState.id;
        numSensorState.high = dtoNumSensorState.high;
        numSensorState.low = dtoNumSensorState.low;
        numSensorState.numSensorList = dtoPlainNumMeasureListToNumMeasureList(dtoNumSensorState.numSensorList);

        return numSensorState;
    }

    protected List<NumSensor> dtoPlainNumMeasureListToNumMeasureList(List<DTOPlainNumSensor> list) {
        if ( list == null ) {
            return null;
        }

        List<NumSensor> list1 = new ArrayList<NumSensor>( list.size() );
        for ( DTOPlainNumSensor dtoPlainNumSensor : list ) {
            list1.add( sensorMapper.toNumSensorFromDtoPlainNumSensor( dtoPlainNumSensor ) );
        }

        return list1;
    }

    protected List<DTOPlainNumSensor> NumMeasureListToDtoPlainNumMeasureList(List<NumSensor> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOPlainNumSensor> list1 = new ArrayList<DTOPlainNumSensor>( list.size() );
        for ( NumSensor numSensor : list ) {
            list1.add( sensorMapper.fromNumSensorToDtoPlainNumSensor( numSensor ) );
        }

        return list1;
    }


}
