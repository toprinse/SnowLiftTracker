package fr.esisar.snowlifttracker.mapper.impl;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensor;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithAnalogMeasure;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithSensorType;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithSkiLift;
import fr.esisar.snowlifttracker.dto.DTOAnalogSensorWithUnit;
import fr.esisar.snowlifttracker.dto.DTONumSensor;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithNumMeasure;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithSensorType;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithSkiLift;
import fr.esisar.snowlifttracker.dto.DTONumSensorWithState;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumMeasure;
import fr.esisar.snowlifttracker.model.AnalogMeasure;
import fr.esisar.snowlifttracker.model.AnalogSensor;
import fr.esisar.snowlifttracker.model.NumMeasure;
import fr.esisar.snowlifttracker.model.NumSensor;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

import fr.esisar.snowlifttracker.mapper.SensorMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainAnalogSensorUnitMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainMeasureMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainNumSensorStateMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSensorTypeMapper;
import fr.esisar.snowlifttracker.mapper.plain.PlainSkiLiftMapper;

@Singleton
@Named
public class SensorMapperImpl implements SensorMapper{
    // every mapper to plains
    private final PlainAnalogSensorUnitMapper analogSensorUnitMapper;
    private final PlainMeasureMapper measureMapper;
    private final PlainNumSensorStateMapper numSensorStateMapper;
    private final PlainSensorTypeMapper sensorTypeMapper;
    private final PlainSkiLiftMapper skiLiftMapper;

    @Inject
    public SensorMapperImpl(PlainAnalogSensorUnitMapper analogSensorUnitMapper, PlainMeasureMapper measureMapper,
                            PlainNumSensorStateMapper numSensorStateMapper, PlainSensorTypeMapper sensorTypeMapper,
                            PlainSkiLiftMapper skiLiftMapper) {

        this.analogSensorUnitMapper = analogSensorUnitMapper;
        this.measureMapper = measureMapper;
        this.numSensorStateMapper = numSensorStateMapper;
        this.sensorTypeMapper = sensorTypeMapper;
        this.skiLiftMapper = skiLiftMapper;
    }

    @Override
    public DTOAnalogSensorWithUnit fromAnalogMeasureToDtoAnalogSensorWithUnit(AnalogSensor analogSensor) {
        if ( analogSensor == null ) {
            return null;
        }

        DTOAnalogSensorWithUnit dTOAnalogSensorWithUnit = new DTOAnalogSensorWithUnit();

        dTOAnalogSensorWithUnit.id = analogSensor.id;
        dTOAnalogSensorWithUnit.reference = analogSensor.reference;
        dTOAnalogSensorWithUnit.manufacturer = analogSensor.manufacturer;
        dTOAnalogSensorWithUnit.analogSensorUnit = analogSensorUnitMapper.fromAnalogSensorUnitToDtoPlainAnalogSensorUnit( analogSensor.analogSensorUnit );

        return dTOAnalogSensorWithUnit;
    }

    @Override
    public AnalogSensor toAnalogMeasureFromDtoAnalogSensorWithUnit(DTOAnalogSensorWithUnit dtoAnalogSensorWithUnit) {
        if ( dtoAnalogSensorWithUnit == null ) {
            return null;
        }

        AnalogSensor analogSensor = new AnalogSensor();

        analogSensor.id = dtoAnalogSensorWithUnit.id;
        analogSensor.reference = dtoAnalogSensorWithUnit.reference;
        analogSensor.manufacturer = dtoAnalogSensorWithUnit.manufacturer;
        analogSensor.analogSensorUnit = analogSensorUnitMapper.toAnalogSensorUnitFromDtoPlainAnalogSensorUnit( dtoAnalogSensorWithUnit.analogSensorUnit );

        return analogSensor;
    }

    @Override
    public DTOAnalogSensorWithAnalogMeasure fromAnalogMeasureToDtoAnalogSensorWithAnalogMeasure(AnalogSensor analogSensor) {
        if ( analogSensor == null ) {
            return null;
        }

        DTOAnalogSensorWithAnalogMeasure dTOAnalogSensorWithAnalogMeasure = new DTOAnalogSensorWithAnalogMeasure();

        dTOAnalogSensorWithAnalogMeasure.id = analogSensor.id;
        dTOAnalogSensorWithAnalogMeasure.reference = analogSensor.reference;
        dTOAnalogSensorWithAnalogMeasure.manufacturer = analogSensor.manufacturer;
        dTOAnalogSensorWithAnalogMeasure.analogMeasureList = analogMeasureListToDTOPlainAnalogMeasureList( analogSensor.analogMeasureList );

        return dTOAnalogSensorWithAnalogMeasure;
    }

    @Override
    public AnalogSensor toAnalogMeasureFromDtoAnalogSensorWithAnalogMeasure(DTOAnalogSensorWithAnalogMeasure dtoAnalogSensorWithAnalogMeasure) {
        if ( dtoAnalogSensorWithAnalogMeasure == null ) {
            return null;
        }

        AnalogSensor analogSensor = new AnalogSensor();

        analogSensor.id = dtoAnalogSensorWithAnalogMeasure.id;
        analogSensor.reference = dtoAnalogSensorWithAnalogMeasure.reference;
        analogSensor.manufacturer = dtoAnalogSensorWithAnalogMeasure.manufacturer;
        analogSensor.analogMeasureList = dTOPlainAnalogMeasureListToAnalogMeasureList( dtoAnalogSensorWithAnalogMeasure.analogMeasureList );

        return analogSensor;
    }

    @Override
    public DTOAnalogSensorWithSensorType fromAnalogSensorToDtoAnalogSensorWithSensorType(AnalogSensor analogSensor) {
        if ( analogSensor == null ) {
            return null;
        }

        DTOAnalogSensorWithSensorType dTOAnalogSensorWithSensorType = new DTOAnalogSensorWithSensorType();

        dTOAnalogSensorWithSensorType.id = analogSensor.id;
        dTOAnalogSensorWithSensorType.reference = analogSensor.reference;
        dTOAnalogSensorWithSensorType.manufacturer = analogSensor.manufacturer;
        dTOAnalogSensorWithSensorType.sensorType = sensorTypeMapper.fromSensorTypeToDtoPlainSensorType( analogSensor.sensorType );

        return dTOAnalogSensorWithSensorType;
    }

    @Override
    public AnalogSensor toAnalogSensorFromDtoAnalogSensorWithSensorType(DTOAnalogSensorWithSensorType dtoAnalogSensorWithSensorType) {
        if ( dtoAnalogSensorWithSensorType == null ) {
            return null;
        }

        AnalogSensor analogSensor = new AnalogSensor();

        analogSensor.id = dtoAnalogSensorWithSensorType.id;
        analogSensor.reference = dtoAnalogSensorWithSensorType.reference;
        analogSensor.manufacturer = dtoAnalogSensorWithSensorType.manufacturer;
        analogSensor.sensorType = sensorTypeMapper.toSensorTypeFromDtoPlainSensorType( dtoAnalogSensorWithSensorType.sensorType );

        return analogSensor;
    }

    @Override
    public DTOAnalogSensorWithSkiLift fromAnalogSensorToDtoAnalogSensorWithSkiLift(AnalogSensor analogSensor) {
        if ( analogSensor == null ) {
            return null;
        }

        DTOAnalogSensorWithSkiLift dTOAnalogSensorWithSkiLift = new DTOAnalogSensorWithSkiLift();

        dTOAnalogSensorWithSkiLift.id = analogSensor.id;
        dTOAnalogSensorWithSkiLift.reference = analogSensor.reference;
        dTOAnalogSensorWithSkiLift.manufacturer = analogSensor.manufacturer;
        dTOAnalogSensorWithSkiLift.skiLift = skiLiftMapper.fromSkiLiftToDtoPlainSkiLift( analogSensor.skiLift );

        return dTOAnalogSensorWithSkiLift;
    }

    @Override
    public AnalogSensor toAnalogSensorFromDtoAnalogSensorWithSkiLift(DTOAnalogSensorWithSkiLift dtoAnalogSensorWithSkiLift) {
        if ( dtoAnalogSensorWithSkiLift == null ) {
            return null;
        }

        AnalogSensor analogSensor = new AnalogSensor();

        analogSensor.id = dtoAnalogSensorWithSkiLift.id;
        analogSensor.reference = dtoAnalogSensorWithSkiLift.reference;
        analogSensor.manufacturer = dtoAnalogSensorWithSkiLift.manufacturer;
        analogSensor.skiLift = skiLiftMapper.toSkiLiftFromDtoPlainSkiLift( dtoAnalogSensorWithSkiLift.skiLift );

        return analogSensor;
    }

    @Override
    public DTOAnalogSensor fromAnalogSensorToDtoAnalogSensor(AnalogSensor analogSensor) {
        if ( analogSensor == null ) {
            return null;
        }

        DTOAnalogSensor dTOAnalogSensor = new DTOAnalogSensor();

        dTOAnalogSensor.id = analogSensor.id;
        dTOAnalogSensor.reference = analogSensor.reference;
        dTOAnalogSensor.manufacturer = analogSensor.manufacturer;
        dTOAnalogSensor.analogMeasureList = analogMeasureListToDTOPlainAnalogMeasureList( analogSensor.analogMeasureList );
        dTOAnalogSensor.analogSensorUnit = analogSensorUnitMapper.fromAnalogSensorUnitToDtoPlainAnalogSensorUnit( analogSensor.analogSensorUnit );
        dTOAnalogSensor.sensorType = sensorTypeMapper.fromSensorTypeToDtoPlainSensorType( analogSensor.sensorType );
        dTOAnalogSensor.skiLift = skiLiftMapper.fromSkiLiftToDtoPlainSkiLift( analogSensor.skiLift );

        return dTOAnalogSensor;
    }

    @Override
    public AnalogSensor toAnalogSensorFromDtoAnalogSensor(DTOAnalogSensor dtoAnalogSensor) {
        if ( dtoAnalogSensor == null ) {
            return null;
        }

        AnalogSensor analogSensor = new AnalogSensor();

        analogSensor.id = dtoAnalogSensor.id;
        analogSensor.reference = dtoAnalogSensor.reference;
        analogSensor.manufacturer = dtoAnalogSensor.manufacturer;
        analogSensor.sensorType = sensorTypeMapper.toSensorTypeFromDtoPlainSensorType( dtoAnalogSensor.sensorType );
        analogSensor.skiLift = skiLiftMapper.toSkiLiftFromDtoPlainSkiLift( dtoAnalogSensor.skiLift );
        analogSensor.analogMeasureList = dTOPlainAnalogMeasureListToAnalogMeasureList( dtoAnalogSensor.analogMeasureList );
        analogSensor.analogSensorUnit = analogSensorUnitMapper.toAnalogSensorUnitFromDtoPlainAnalogSensorUnit( dtoAnalogSensor.analogSensorUnit );

        return analogSensor;
    }

    @Override
    public DTONumSensorWithState fromNumSensorToDtoNumSensorWithState(NumSensor numSensor) {
        if ( numSensor == null ) {
            return null;
        }

        DTONumSensorWithState dTONumSensorWithState = new DTONumSensorWithState();

        dTONumSensorWithState.id = numSensor.id;
        dTONumSensorWithState.reference = numSensor.reference;
        dTONumSensorWithState.manufacturer = numSensor.manufacturer;
        dTONumSensorWithState.numSensorState = numSensorStateMapper.fromNumSensorStateToDtoPlainNumSensorState( numSensor.numSensorState );

        return dTONumSensorWithState;
    }

    @Override
    public NumSensor toNumSensorFromDtoNumSensorWithState(DTONumSensorWithState dtoNumSensorWithState) {
        if ( dtoNumSensorWithState == null ) {
            return null;
        }

        NumSensor numSensor = new NumSensor();

        numSensor.id = dtoNumSensorWithState.id;
        numSensor.reference = dtoNumSensorWithState.reference;
        numSensor.manufacturer = dtoNumSensorWithState.manufacturer;
        numSensor.numSensorState = numSensorStateMapper.toNumSensorStateFromDtoPlainNumSensorState( dtoNumSensorWithState.numSensorState );

        return numSensor;
    }

    @Override
    public DTONumSensorWithNumMeasure fromNumSensorToDtoNumSensorWithNumMeasure(NumSensor numSensor) {
        if ( numSensor == null ) {
            return null;
        }

        DTONumSensorWithNumMeasure dTONumSensorWithNumMeasure = new DTONumSensorWithNumMeasure();

        dTONumSensorWithNumMeasure.id = numSensor.id;
        dTONumSensorWithNumMeasure.reference = numSensor.reference;
        dTONumSensorWithNumMeasure.manufacturer = numSensor.manufacturer;
        dTONumSensorWithNumMeasure.numMeasureList = numMeasureListToDTOPlainNumMeasureList( numSensor.numMeasureList );

        return dTONumSensorWithNumMeasure;
    }

    @Override
    public NumSensor toNumSensorFromDtoNumSensorWithState(DTONumSensorWithNumMeasure dtoNumSensorWithNumMeasure) {
        if ( dtoNumSensorWithNumMeasure == null ) {
            return null;
        }

        NumSensor numSensor = new NumSensor();

        numSensor.id = dtoNumSensorWithNumMeasure.id;
        numSensor.reference = dtoNumSensorWithNumMeasure.reference;
        numSensor.manufacturer = dtoNumSensorWithNumMeasure.manufacturer;
        numSensor.numMeasureList = dTOPlainNumMeasureListToNumMeasureList( dtoNumSensorWithNumMeasure.numMeasureList );

        return numSensor;
    }

    @Override
    public DTONumSensorWithSensorType fromNumSensorToDtoNumSensorWithSensorType(NumSensor numSensor) {
        if ( numSensor == null ) {
            return null;
        }

        DTONumSensorWithSensorType dTONumSensorWithSensorType = new DTONumSensorWithSensorType();

        dTONumSensorWithSensorType.id = numSensor.id;
        dTONumSensorWithSensorType.reference = numSensor.reference;
        dTONumSensorWithSensorType.manufacturer = numSensor.manufacturer;
        dTONumSensorWithSensorType.sensorType = sensorTypeMapper.fromSensorTypeToDtoPlainSensorType( numSensor.sensorType );

        return dTONumSensorWithSensorType;
    }

    @Override
    public NumSensor toNumSensorFromDtoNumSensorWithSensorType(DTONumSensorWithSensorType dtoNumSensorWithSensorType) {
        if ( dtoNumSensorWithSensorType == null ) {
            return null;
        }

        NumSensor numSensor = new NumSensor();

        numSensor.id = dtoNumSensorWithSensorType.id;
        numSensor.reference = dtoNumSensorWithSensorType.reference;
        numSensor.manufacturer = dtoNumSensorWithSensorType.manufacturer;
        numSensor.sensorType = sensorTypeMapper.toSensorTypeFromDtoPlainSensorType( dtoNumSensorWithSensorType.sensorType );

        return numSensor;
    }

    @Override
    public DTONumSensorWithSkiLift fromNumSensorToDtoNumSensorWithSkiLift(NumSensor numSensor) {
        if ( numSensor == null ) {
            return null;
        }

        DTONumSensorWithSkiLift dTONumSensorWithSkiLift = new DTONumSensorWithSkiLift();

        dTONumSensorWithSkiLift.id = numSensor.id;
        dTONumSensorWithSkiLift.reference = numSensor.reference;
        dTONumSensorWithSkiLift.manufacturer = numSensor.manufacturer;
        dTONumSensorWithSkiLift.skiLift = skiLiftMapper.fromSkiLiftToDtoPlainSkiLift( numSensor.skiLift );

        return dTONumSensorWithSkiLift;
    }

    @Override
    public NumSensor toNumSensorFRomDtoNumSensorWithSkiLift(DTONumSensorWithSkiLift dtoNumSensorWithSkiLift) {
        if ( dtoNumSensorWithSkiLift == null ) {
            return null;
        }

        NumSensor numSensor = new NumSensor();

        numSensor.id = dtoNumSensorWithSkiLift.id;
        numSensor.reference = dtoNumSensorWithSkiLift.reference;
        numSensor.manufacturer = dtoNumSensorWithSkiLift.manufacturer;
        numSensor.skiLift = skiLiftMapper.toSkiLiftFromDtoPlainSkiLift( dtoNumSensorWithSkiLift.skiLift );

        return numSensor;
    }

    @Override
    public DTONumSensor fromNumSensorToDtoNumSensor(NumSensor numSensor) {
        if ( numSensor == null ) {
            return null;
        }

        DTONumSensor dTONumSensor = new DTONumSensor();

        dTONumSensor.id = numSensor.id;
        dTONumSensor.reference = numSensor.reference;
        dTONumSensor.manufacturer = numSensor.manufacturer;
        dTONumSensor.numSensorState = numSensorStateMapper.fromNumSensorStateToDtoPlainNumSensorState( numSensor.numSensorState );
        dTONumSensor.numMeasureList = numMeasureListToDTOPlainNumMeasureList( numSensor.numMeasureList );
        dTONumSensor.sensorType = sensorTypeMapper.fromSensorTypeToDtoPlainSensorType( numSensor.sensorType );
        dTONumSensor.skiLift = skiLiftMapper.fromSkiLiftToDtoPlainSkiLift( numSensor.skiLift );

        return dTONumSensor;
    }

    @Override
    public NumSensor toNumSensorFRomDtoNumSensor(DTONumSensor dtoNumSensor) {
        if ( dtoNumSensor == null ) {
            return null;
        }

        NumSensor numSensor = new NumSensor();

        numSensor.id = dtoNumSensor.id;
        numSensor.reference = dtoNumSensor.reference;
        numSensor.manufacturer = dtoNumSensor.manufacturer;
        numSensor.sensorType = sensorTypeMapper.toSensorTypeFromDtoPlainSensorType( dtoNumSensor.sensorType );
        numSensor.skiLift = skiLiftMapper.toSkiLiftFromDtoPlainSkiLift( dtoNumSensor.skiLift );
        numSensor.numMeasureList = dTOPlainNumMeasureListToNumMeasureList( dtoNumSensor.numMeasureList );
        numSensor.numSensorState = numSensorStateMapper.toNumSensorStateFromDtoPlainNumSensorState( dtoNumSensor.numSensorState );

        return numSensor;
    }

    protected List<DTOPlainAnalogMeasure> analogMeasureListToDTOPlainAnalogMeasureList(List<AnalogMeasure> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOPlainAnalogMeasure> list1 = new ArrayList<DTOPlainAnalogMeasure>( list.size() );
        for ( AnalogMeasure analogMeasure : list ) {
            list1.add( measureMapper.fromAnalogMeasureToDtoPlainAnalogMeasure( analogMeasure ) );
        }

        return list1;
    }

    protected List<AnalogMeasure> dTOPlainAnalogMeasureListToAnalogMeasureList(List<DTOPlainAnalogMeasure> list) {
        if ( list == null ) {
            return null;
        }

        List<AnalogMeasure> list1 = new ArrayList<AnalogMeasure>( list.size() );
        for ( DTOPlainAnalogMeasure dTOPlainAnalogMeasure : list ) {
            list1.add( measureMapper.toAnalogMeasureFromDtoPlainAnalogMeasure( dTOPlainAnalogMeasure ) );
        }

        return list1;
    }

    protected List<DTOPlainNumMeasure> numMeasureListToDTOPlainNumMeasureList(List<NumMeasure> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOPlainNumMeasure> list1 = new ArrayList<DTOPlainNumMeasure>( list.size() );
        for ( NumMeasure numMeasure : list ) {
            list1.add( measureMapper.fromNumMeasureToDtoPlainNumMeasure( numMeasure ) );
        }

        return list1;
    }

    protected List<NumMeasure> dTOPlainNumMeasureListToNumMeasureList(List<DTOPlainNumMeasure> list) {
        if ( list == null ) {
            return null;
        }

        List<NumMeasure> list1 = new ArrayList<NumMeasure>( list.size() );
        for ( DTOPlainNumMeasure dTOPlainNumMeasure : list ) {
            list1.add( measureMapper.toNumMeasureFromDtoPlainNumMeasure( dTOPlainNumMeasure ) );
        }

        return list1;
    }
}
