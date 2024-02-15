package fr.esisar.snowlifttracker.mapper.impl;

import fr.esisar.snowlifttracker.dto.DTOAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTONumMeasure;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.NumMeasure;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import fr.esisar.snowlifttracker.mapper.MeasureMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorMapper;

@Singleton
@Named
public class MeasureMapperImpl implements MeasureMapper {

    private final PlainSensorMapper sensorMapper;

    @Inject
    public MeasureMapperImpl(PlainSensorMapper sensorMapper){
        this.sensorMapper = sensorMapper;
    } 

    @Override
    public DTOAnalogMeasure fromAnalogMeasureToDtoAnalogMeasure(AnalogMeasure analogMeasure) {
        if ( analogMeasure == null ) {
            return null;
        }

        DTOAnalogMeasure dtoAnalogMeasure = new DTOAnalogMeasure();

        dtoAnalogMeasure.analogData = analogMeasure.analogData;
        dtoAnalogMeasure.timestamp = analogMeasure.timestamp;
        dtoAnalogMeasure.id = analogMeasure.id;
        dtoAnalogMeasure.analogSensor = sensorMapper.fromAnalogSensorToDtoPlainAnalogSensor(analogMeasure.analogSensor);

        return dtoAnalogMeasure;
    }
    
    @Override
    public AnalogMeasure toAnalogMeasureFromDtoAnalogMeasure(DTOAnalogMeasure dtoAnalogMeasure){
        if ( dtoAnalogMeasure == null ) {
            return null;
        }

        AnalogMeasure analogMeasure = new AnalogMeasure();

        analogMeasure.analogData = dtoAnalogMeasure.analogData;
        analogMeasure.timestamp = dtoAnalogMeasure.timestamp;
        analogMeasure.id = dtoAnalogMeasure.id;
        analogMeasure.analogSensor = sensorMapper.toAnalogSensorFromDtoPlainAnalogSensor(dtoAnalogMeasure.analogSensor);

        return analogMeasure;
    };

    public DTONumMeasure fromNumMeasureToDtoNumMeasure(NumMeasure numMeasure){
        if ( numMeasure == null ) {
            return null;
        }

        DTONumMeasure dtoNumMeasure = new DTONumMeasure();

        dtoNumMeasure.id = numMeasure.id;
        dtoNumMeasure.numData = numMeasure.numData;
        dtoNumMeasure.timestamp = numMeasure.timestamp;
        dtoNumMeasure.numSensor = sensorMapper.fromNumSensorToDtoPlainNumSensor(numMeasure.numSensor);

        return dtoNumMeasure;
    }
    
    public NumMeasure toNumMeasureFromDtoNumMeasure(DTONumMeasure dtoNumMeasure){
        if ( dtoNumMeasure == null ) {
            return null;
        }

        NumMeasure numMeasure = new NumMeasure();

        numMeasure.id = dtoNumMeasure.id;
        numMeasure.numData = dtoNumMeasure.numData;
        numMeasure.timestamp = dtoNumMeasure.timestamp;
        numMeasure.numSensor =  sensorMapper.toNumSensorFromDtoPlainNumSensor(dtoNumMeasure.numSensor);

        return numMeasure;
    }
}
