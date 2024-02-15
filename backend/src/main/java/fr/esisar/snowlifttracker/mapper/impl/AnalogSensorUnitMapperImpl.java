package fr.esisar.snowlifttracker.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import fr.esisar.snowlifttracker.dto.DTOAnalogSensorUnit;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.AnalogSensorUnit;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.AnalogSensorUnitMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;

@Singleton
@Named
public class AnalogSensorUnitMapperImpl implements AnalogSensorUnitMapper {
    
    private final PlainSensorMapper sensorMapper;
    
    @Inject
    public AnalogSensorUnitMapperImpl(PlainSensorMapper sensorMapper){
        this.sensorMapper = sensorMapper;
    }

    public DTOAnalogSensorUnit fromAnalogSensorUnitToDtoAnalogSensorUnit(AnalogSensorUnit analogSensorUnit){
        if ( analogSensorUnit == null ) {
            return null;
        }

        DTOAnalogSensorUnit dtoAnalogSensorUnit = new DTOAnalogSensorUnit();

        dtoAnalogSensorUnit.id = analogSensorUnit.id;
        dtoAnalogSensorUnit.unit = analogSensorUnit.unit;
        dtoAnalogSensorUnit.analogSensorList = AnalogSensorListTodtoPlainAnalogSensorList(analogSensorUnit.analogSensorList);

        return dtoAnalogSensorUnit;
    }

    public AnalogSensorUnit toAnalogSensorUnitFromDtoAnalogSensorUnit(DTOAnalogSensorUnit dtoAnalogSensorUnit){
        if ( dtoAnalogSensorUnit == null ) {
            return null;
        }

        AnalogSensorUnit analogSensorUnit = new AnalogSensorUnit();

        analogSensorUnit.id = dtoAnalogSensorUnit.id;
        analogSensorUnit.unit = dtoAnalogSensorUnit.unit;
        analogSensorUnit.analogSensorList = dtoPlainAnalogSensorListToAnalogSensorList(dtoAnalogSensorUnit.analogSensorList);

        return analogSensorUnit;
    }


    protected List<AnalogSensor> dtoPlainAnalogSensorListToAnalogSensorList(List<DTOPlainAnalogSensor> list) {
        if ( list == null ) {
            return null;
        }

        List<AnalogSensor> list1 = new ArrayList<AnalogSensor>( list.size() );
        for ( DTOPlainAnalogSensor dtoPlainAnalogSensor : list ) {
            list1.add( sensorMapper.toAnalogSensorFromDtoPlainAnalogSensor( dtoPlainAnalogSensor ) );
        }

        return list1;
    }

    protected List<DTOPlainAnalogSensor> AnalogSensorListTodtoPlainAnalogSensorList(List<AnalogSensor> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOPlainAnalogSensor> list1 = new ArrayList<DTOPlainAnalogSensor>( list.size() );
        for ( AnalogSensor analogSensor : list ) {
            list1.add( sensorMapper.fromAnalogSensorToDtoPlainAnalogSensor( analogSensor ) );
        }

        return list1;
    }
}
