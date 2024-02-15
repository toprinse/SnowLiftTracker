/* WARNING : Those example may have Syntax errors.
 * 
 * When using insert always verify the SQL syntax.
 *      H2 : http://www.h2database.com/html/grammar.html
 *      PostGre : 
 * With each database type comes differents keywords which CANNOT be used for table name.
 * For H2 : CROSS, CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP, DISTINCT, EXCEPT, EXISTS, FALSE, FOR, FROM, FULL, GROUP, HAVING, INNER, INTERSECT,
 * IS, JOIN, LIKE, LIMIT, MINUS, NATURAL, NOT, NULL, ON, ORDER, PRIMARY, ROWNUM, SELECT, SYSDATE, SYSTIME, SYSTIMESTAMP, TODAY, TRUE, UNION, UNIQUE, WHERE
 * Also some are not referenced, for example USER seem to be unusable.
 * For PostGre :
 *
 * Do not use "" but ''
 *
 */
-- Create a user 
INSERT INTO UserSnow(id, email, firstname, lastname, login, password, permission) VALUES(nextval('UserSnow_SEQ'), 'toto.tata@gmail.com', 'toto', 'tata', 'tatat', 'azerty', 'Client');
INSERT INTO UserSnow(id, email, firstname, lastname, login, password, permission) VALUES(nextval('UserSnow_SEQ'), 'titi.tete@gmail.com', 'titi', 'tete', 'tetet', 'azerty', 'Client');
-- Create a Station
INSERT INTO Station(id, latitude, longitude, name) VALUES (nextval('Station_SEQ'), '6.631974610752491', '45.40991987885902', 'Courchevel');

-- Create a SkiLift
INSERT INTO SkiLift(open, id, station_id, latitude, longitude, name) VALUES (TRUE, nextval('SkiLift_SEQ'), NULL, '6.6324483777399', '45.42042878584228', 'Grangette');
INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), 1, 'x', 'y', 'z');
--INSERT INTO SkiLift VALUES (TRUE, 51, NULL, 'x', 'y', 'z1');
--INSERT INTO SkiLift VALUES (TRUE, 101, NULL, 'x', 'y', 'z2');
--INSERT INTO SkiLift VALUES (TRUE, 151, 1, 'x', 'y', 'z3');  

-- Create a Sensor
INSERT INTO SensorType(id, type) VALUES (nextval('SensorType_SEQ'), 'Humidity');
INSERT INTO SensorType(id, type) VALUES (nextval('SensorType_SEQ'), 'Temperature');
INSERT INTO SensorType(id, type) VALUES (nextval('SensorType_SEQ'), 'Pressure');
-- Create an Analog Sensor
INSERT INTO AnalogSensorUnit(id, unit) VALUES (nextval('AnalogSensorUnit_SEQ'), '%');
INSERT INTO AnalogSensorUnit(id, unit) VALUES (nextval('AnalogSensorUnit_SEQ'), '°C');
INSERT INTO AnalogSensorUnit(id, unit) VALUES (nextval('AnalogSensorUnit_SEQ'), 'bar');
INSERT INTO AnalogSensor(analogSensorUnit_id, id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (1, nextval('Sensor_SEQ'), 1, 1,'Bosh', 'BME280');
INSERT INTO AnalogSensor(analogSensorUnit_id, id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (NULL, nextval('Sensor_SEQ'), 51, NULL,'Bosh', 'BME280f');
INSERT INTO AnalogSensor(analogSensorUnit_id, id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (101, nextval('Sensor_SEQ'), 101, 1,'Bosh', 'BME280l');

-- Create an analog Measure 
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (37.2, '2023-10-27', 51,  nextval('Measure_SEQ')); 
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (35456.9, '2023-11-27',51, nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (516.2, '2023-12-27', 51,  nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (300.2, '2023-12-24', 1, nextval('Measure_SEQ'));

-- Create a Num Sensor
INSERT INTO NumSensorState(high, low, id) VALUES (TRUE, FALSE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState(high, low, id) VALUES (FALSE, TRUE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState(high, low, id) VALUES (TRUE, TRUE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), 1, 1, 1, 'test', 'XXXYYY');
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), 51, 51, 1, 'test2', 'AAABBB');
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), 101, NULL, NULL, 'test3', 'CCCDDD');
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), NULL, NULL, NULL, 'test4', 'EEEFFF');

INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-10-27', nextval('Measure_SEQ'), 201);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-10-28', nextval('Measure_SEQ'), 201);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-11-27', nextval('Measure_SEQ'), 251);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-12-24', nextval('Measure_SEQ'), 301);

/* For Measure, we may convert from LocalDate to LocalDateTime or a combination of LocalDate & LocalTime :
            - LocalDate = (year, month, day (yyyy-MM-dd))
            - LocalTime = (hour, minute, second and nanoseconds (HH-mm-ss-ns))
            - LocalDateTime = (yyyy-MM-dd-HH-mm-ss-ns)
*/    

/* Exctract From Quarkus creation Script (can change if we change inheritance startegy)
Hibernate: 
    create table AnalogMeasure (
        analogData float(24) not null unique,
        timestamp date not null unique,
        analogSensor_id bigint unique,
        id bigint not null,
        primary key (id)
    )

Hibernate: 
    create table AnalogSensor (
        analogSensorUnit_id bigint unique,
        id bigint not null,
        sensorType_id bigint unique,
        skiLift_id bigint unique,
        manufacturer varchar(255),
        reference varchar(255) not null unique,
        primary key (id)
    )

Hibernate: 
    create table AnalogSensorUnit (
        id bigint not null,
        unit varchar(255) not null unique,
        primary key (id)
    )

Hibernate: 
    create table NumMeasure (
        numData boolean not null,
        timestamp date not null unique,
        id bigint not null,
        numSensor_id bigint,
        primary key (id)
    )

Hibernate: 
    create table NumSensor (
        id bigint not null,
        numSensorState_id bigint unique,
        sensorType_id bigint unique,
        skiLift_id bigint unique,
        manufacturer varchar(255),
        reference varchar(255) not null unique,
        primary key (id)
    )

Hibernate: 
    create table NumSensorState (
        high boolean not null,
        low boolean not null,
        id bigint not null,
        primary key (id)
    )

Hibernate: 
    create table SensorType (
        id bigint not null,
        type varchar(255) not null unique,
        primary key (id)
    )

Hibernate: 
    create table SkiLift (
        open boolean not null,
        id bigint not null,
        station_id bigint,
        latitude varchar(255),
        longitude varchar(255),
        name varchar(255) not null unique,
        primary key (id)
    )

Hibernate: 
    create table Station (
        id bigint not null,
        latitude varchar(255),
        longitude varchar(255),
        name varchar(255) not null unique,
        primary key (id)
    )

Hibernate: 
    create table UserSnow (
        id bigint not null,
        email varchar(255),
        firstname varchar(255) not null unique,
        lastname varchar(255),
        login varchar(255),
        password varchar(255),
        permission varchar(255),
        primary key (id)
    )
*/