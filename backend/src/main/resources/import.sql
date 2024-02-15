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
INSERT INTO Station(id, latitude, longitude, name) VALUES (nextval('Station_SEQ'), '45.1106963', '5.8746192', 'Chamrousse');
INSERT INTO Station VALUES (nextval('Station_SEQ'), '45.083328', '6.05', 'AlpesHuez');

-- Create a SkiLift
INSERT INTO SkiLift(open, id, station_id, latitude, longitude, name) 
VALUES (TRUE, nextval('SkiLift_SEQ'), 1,'45.109433','5.877349','Tire-fesse chamrousse');
INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), 1,  '45.111160', '5.879539', 'tele-siege chamrousse');
INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), 1,  '45.109131', '5.881942', 'tele-cabine chamrousse');
INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), 51, '45.09096597789204', '6.07595477484832', 'Tire-fesse alpeshuez');
INSERT INTO SkiLift VALUES (FALSE, nextval('SkiLift_SEQ'), 51, '45.09134923263898', '6.075166286729946', 'tele-siege alpeshuez');
--INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), 51, '45.09530049708717', '6.0717113840265196', 'tele-cabine alpeshuez');   

-- Create a Sensor
INSERT INTO SensorType(id, type) VALUES (nextval('SensorType_SEQ'), 'Humidity');
INSERT INTO SensorType(id, type) VALUES (nextval('SensorType_SEQ'), 'Temperature');
INSERT INTO SensorType(id, type) VALUES (nextval('SensorType_SEQ'), 'Pressure');
-- Create an Analog Sensor Unit
INSERT INTO AnalogSensorUnit(id, unit) VALUES (nextval('AnalogSensorUnit_SEQ'), '%');
INSERT INTO AnalogSensorUnit(id, unit) VALUES (nextval('AnalogSensorUnit_SEQ'), '°C');
INSERT INTO AnalogSensorUnit(id, unit) VALUES (nextval('AnalogSensorUnit_SEQ'), 'bar');
--Create Analog Sensor (SkiLift ID=201)
INSERT INTO AnalogSensor(analogSensorUnit_id, id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (1, nextval('Sensor_SEQ'), 1, 201,'Bosh', 'BME280f');
INSERT INTO AnalogSensor(analogSensorUnit_id, id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (51, nextval('Sensor_SEQ'), 51, 201,'Bosh', 'BME280');
INSERT INTO AnalogSensor(analogSensorUnit_id, id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (101, nextval('Sensor_SEQ'), 101, 201,'Bosh', 'BME280l');
-- Create an analog Measure (SkiLift ID:201)
--Hygrometry
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (70.1, '2023-10-27', 1,  nextval('Measure_SEQ')); 
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (24.8, '2023-11-27', 1, nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (4.2, '2023-12-27', 1,  nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (64, '2024-01-13', 1,  nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (98, '2021-12-27', 1, nextval('Measure_SEQ'));
--Temperature
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (2, '2022-09-27', 51, nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (-1, '2022-10-27', 51, nextval('Measure_SEQ')); 
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (-5, '2022-11-27', 51, nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (8, '2022-12-27', 51, nextval('Measure_SEQ'));
--Pression
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (0.8, '2021-10-27', 101, nextval('Measure_SEQ')); 
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (1.5, '2021-11-27', 101, nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (1.2, '2021-12-27', 101, nextval('Measure_SEQ'));
INSERT INTO AnalogMeasure(analogData, timestamp, analogSensor_id, id) VALUES (1.8, '2022-01-02', 101, nextval('Measure_SEQ'));


-- Create a Num Sensor
INSERT INTO NumSensorState(high, low, id) VALUES (TRUE, FALSE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState(high, low, id) VALUES (FALSE, TRUE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState(high, low, id) VALUES (TRUE, TRUE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), 1, 1, 201, 'test', 'XXXYYY');
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), 51, 51, 151, 'test2', 'AAABBB');
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), 101, NULL, NULL, 'test3', 'CCCDDD');
INSERT INTO NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference) VALUES (nextval('Sensor_SEQ'), NULL, NULL, NULL, 'test4', 'EEEFFF');


INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-10-27', nextval('Measure_SEQ'), 151);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-10-27', nextval('Measure_SEQ'), 151);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-10-28', nextval('Measure_SEQ'), 151);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2023-10-29', nextval('Measure_SEQ'), 151);

INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2024-10-27', nextval('Measure_SEQ'), 201);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2024-11-27', nextval('Measure_SEQ'), 201);
INSERT INTO NumMeasure(numData, timestamp, id, numSensor_id) VALUES (TRUE, '2024-10-28', nextval('Measure_SEQ'), 201);


-- Analog Measure declaration
-- TABLE AnalogMeasure(analogData, analogSensor_id, id, timestamp)
INSERT INTO AnalogMeasure VALUES(37.2, NULL, nextval('Measure_SEQ'), '2023-10-27 00:00:00');
INSERT INTO AnalogMeasure VALUES(35456.9, NULL, nextval('Measure_SEQ'), '2023-11-27 00:00:00');
INSERT INTO AnalogMeasure VALUES(516.2, NULL, nextval('Measure_SEQ'), '2023-12-27 00:00:00');
INSERT INTO AnalogMeasure VALUES(300.2, NULL, nextval('Measure_SEQ'), '2023-12-24 00:00:00');

-- Num Sensor State declaration
-- TABLE NumSensorState(high, low, id)
INSERT INTO NumSensorState VALUES(TRUE, FALSE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState VALUES(FALSE, TRUE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState VALUES(TRUE, TRUE, nextval('NumSensorState_SEQ'));

-- Num Sensor declaration
-- TABLE NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference)
INSERT INTO NumSensor VALUES(nextval('Sensor_SEQ'), 1, 1, 1, 'test', 'XXXYYY');
INSERT INTO NumSensor VALUES(nextval('Sensor_SEQ'), 51, 51, 1, 'test2', 'AAABBB');
INSERT INTO NumSensor VALUES(nextval('Sensor_SEQ'), 101, NULL, NULL, 'test3', 'CCCDDD');
INSERT INTO NumSensor VALUES(nextval('Sensor_SEQ'), NULL, NULL, NULL, 'test4', 'EEEFFF');

-- Num Measure declaration
-- TABLE NumMeasure(numData, id, numSensor_id, timestamp)
INSERT INTO NumMeasure VALUES(TRUE, nextval('Measure_SEQ'), 201, '2023-10-27 00:00:00');
INSERT INTO NumMeasure VALUES(TRUE, nextval('Measure_SEQ'), 201, '2023-10-28 00:00:00');
INSERT INTO NumMeasure VALUES(TRUE, nextval('Measure_SEQ'), 201, '2023-10-27 01:00:00');
INSERT INTO NumMeasure VALUES(TRUE, nextval('Measure_SEQ'), 201, '2023-10-24 00:00:00');
