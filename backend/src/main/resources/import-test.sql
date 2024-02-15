/* WARNING : This test file is to test unit resources so it shouldn't contain association.
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

-- Note : In test file it's hardcoded that each class should have 3 instances.

-- Users declaration
-- TABLE prototype ==> UserSnow(id, email, firstname, lastname, login, password, permission)
INSERT INTO UserSnow VALUES(nextval('UserSnow_SEQ'), 'toto.tata@gmail.com', 'toto', 'tata', 'tatat', 'azerty', 'Client');
INSERT INTO UserSnow VALUES(nextval('UserSnow_SEQ'), 'titi.tete@gmail.com', 'titi', 'tete', 'tetet', 'azerty', 'Client');
INSERT INTO UserSnow VALUES(nextval('UserSnow_SEQ'), 'abc.def@gmail.com', 'abc', 'def', 'defa', 'azerty', 'Client');

-- Stations declaration 
-- TABLE prototype ==> Station(id, latitude, longitude, name)
INSERT INTO Station VALUES (nextval('Station_SEQ'), '11111', '22222', 'Courchevel');
INSERT INTO Station VALUES (nextval('Station_SEQ'), '33333', '44444', 'Meribel');
INSERT INTO Station VALUES (nextval('Station_SEQ'), '55555', '66666', 'Arcs');

-- SkiLifts declaration
-- TABLE prototype ==> SkiLift(open, id, station_id, latitude, longitude, name)
INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), NULL, 'xxxxx', 'yyyyy', 'gondola');
INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), NULL, 'aaaaa', 'zzzzz', 'chairlift');
INSERT INTO SkiLift VALUES (TRUE, nextval('SkiLift_SEQ'), NULL, 'eeeee', 'rrrrr', 'skilift');  

-- SensorTypes declaration
-- TABLE prototype ==> SensorType(id, type)
INSERT INTO SensorType VALUES (nextval('SensorType_SEQ'), 'Humidity');
INSERT INTO SensorType VALUES (nextval('SensorType_SEQ'), 'Temperature');
INSERT INTO SensorType VALUES (nextval('SensorType_SEQ'), 'Pressure');

-- AnalogSensors declaration
-- TABLE prototype ==> AnalogSensor(analogSensorUnit_id, id, sensorType_id, skiLift_id, manufacturer, reference)
INSERT INTO AnalogSensor VALUES (NULL, nextval('Sensor_SEQ'), NULL, NULL,'Bosh', 'BME280');
INSERT INTO AnalogSensor VALUES (NULL, nextval('Sensor_SEQ'), NULL, NULL,'TI', 'LM35');
INSERT INTO AnalogSensor VALUES (NULL, nextval('Sensor_SEQ'), NULL, NULL,'DFRobot', 'SEN0114');

-- AnalogSensorUnits declaration
-- TABLE prototype ==> AnalogSensorUnit(id, unit)
INSERT INTO AnalogSensorUnit VALUES (nextval('AnalogSensorUnit_SEQ'), '%');
INSERT INTO AnalogSensorUnit VALUES (nextval('AnalogSensorUnit_SEQ'), '°C');
INSERT INTO AnalogSensorUnit VALUES (nextval('AnalogSensorUnit_SEQ'), 'bar');

-- AnlogMeasures declaration 
-- TABLE prototype ==> AnalogMeasure(analogData, analogSensor_id, id, timestamp)
INSERT INTO AnalogMeasure VALUES(516.21, NULL, nextval('Measure_SEQ'), '2023-10-27 01:00:00');
INSERT INTO AnalogMeasure VALUES(1.2345, NULL, nextval('Measure_SEQ'), '2023-10-27 02:00:00');
INSERT INTO AnalogMeasure VALUES(0.9876, NULL, nextval('Measure_SEQ'), '2023-10-27 03:00:00');

-- NumSensors declaration
-- TABLE prototype ==> NumSensor(id, numSensorState_id, sensorType_id, skiLift_id, manufacturer, reference)
INSERT INTO NumSensor VALUES (nextval('Sensor_SEQ'), NULL, NULL, NULL, 'TI', 'XXXYYY');
INSERT INTO NumSensor VALUES (nextval('Sensor_SEQ'), NULL, NULL, NULL, 'SE', 'AZERTY');
INSERT INTO NumSensor VALUES (nextval('Sensor_SEQ'), NULL, NULL, NULL, 'LG', 'UIOPQS');

-- NumSensorStates declaration
-- TABLE prototype ==> NumSensorState(high, low, id)
INSERT INTO NumSensorState VALUES (TRUE, FALSE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState VALUES (TRUE, TRUE, nextval('NumSensorState_SEQ'));
INSERT INTO NumSensorState VALUES (FALSE, FALSE, nextval('NumSensorState_SEQ'));

-- NumMeasures declaration 
-- TABLE prototype ==> NumMeasure(numData, id, numSensor_id, timestamp)
INSERT INTO NumMeasure VALUES(TRUE, nextval('Measure_SEQ'), NULL, '2023-10-27 01:00:00');
INSERT INTO NumMeasure VALUES(FALSE, nextval('Measure_SEQ'), NULL, '2023-11-27 02:00:00');
INSERT INTO NumMeasure VALUES(TRUE, nextval('Measure_SEQ'), NULL, '2023-12-27 03:00:00');
