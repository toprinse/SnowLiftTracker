/************************************************************************************
 *  ___       __   ________  ________  ________   ___  ________   ________          
 * |\  \     |\  \|\   __  \|\   __  \|\   ___  \|\  \|\   ___  \|\   ____\         
 * \ \  \    \ \  \ \  \|\  \ \  \|\  \ \  \\ \  \ \  \ \  \\ \  \ \  \___|        
 *  \ \  \  __\ \  \ \   __  \ \   _  _\ \  \\ \  \ \  \ \  \\ \  \ \  \  ___      
 *   \ \  \|\__\_\  \ \  \ \  \ \  \\  \\ \  \\ \  \ \  \ \  \\ \  \ \  \|\  \   
 *    \ \____________\ \__\ \__\ \__\\ _\\ \__\\ \__\ \__\ \__\\ \__\ \_______\ 
 *     \|____________|\|__|\|__|\|__|\|__|\|__| \|__|\|__|\|__| \|__|\|_______|      
 *                                                                                  	   
 * This file is auto-generated you should change attribute at certain places.        
 * File name: SensorRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for SensorRessource.java to automate       
 * REST API testing.                                                                
 ************************************************************************************/

package fr.esisar.snowlifttracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.common.net.HttpHeaders;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensor;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensor;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
//@Disabled // to debug other tests
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SensorResourceTest {

	/************************************************** ANALOG SENSOR METHOD **********************************************************/

	@Test
    void shouldNotGetUnknownAnalogSensor(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/Sensor/analog/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidAnalogSensor(){
		DTOPlainAnalogSensor dtoAnalogSensor= new DTOPlainAnalogSensor();
        // Complete and change HERE
		dtoAnalogSensor.manufacturer = " "; // @NotBlanck
		dtoAnalogSensor.reference = " "; // @NotBlanck
		
		given() 
			.body(dtoAnalogSensor)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/Sensor/analog")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_ANALOGSENSOR = 3;
    
    @Test
	void shouldGetInitialAnlogSensor(){
		List<DTOPlainAnalogSensor> analogsensorList = given()
				.when()
				.get("/Sensor/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogSensor.class);
		assertEquals(NB_ANALOGSENSOR, analogsensorList.size());
	}
    
    // Complete and change name HERE
    private static final String MANUFACTURER = "manufacturer";
	private static final String REFERENCE = "reference";
	
	private static Long id_AnalogSensor;
    
    @Test
	@Order(2)
	void shouldAddAnalogSensor () {
		DTOPlainAnalogSensor dtoAnalogSensor = new DTOPlainAnalogSensor();
		dtoAnalogSensor.manufacturer = MANUFACTURER;
		dtoAnalogSensor.reference = REFERENCE;
		
		DTOPlainAnalogSensor analogsensor = given()
				.body(dtoAnalogSensor)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/Sensor/analog")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainAnalogSensor.class);

		assertNotNull(analogsensor.id);
		id_AnalogSensor = analogsensor.id;
		
		List<DTOPlainAnalogSensor> analogsensorList = given()
				.when()
				.get("/Sensor/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogSensor.class);
		assertEquals(NB_ANALOGSENSOR + 1, analogsensorList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetAAnalogSensorById() {
		given()
			.pathParam("id", id_AnalogSensor)
			.when()
			.get("/Sensor/analog/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("manufacturer", Is.is(MANUFACTURER))
			.body("reference", Is.is(REFERENCE));
	}
    
    // Complete and change name HERE
    private static final String UPDATED_MANUFACTURER = "updated_manufacturer";
	private static final String UPDATED_REFERENCE = "updated_reference";
    
    @Test
	@Order(4)
	void shouldUpdateAAnalogSensor() {
		DTOPlainAnalogSensor dtoAnalogSensor = new DTOPlainAnalogSensor();
		dtoAnalogSensor.id = id_AnalogSensor;
        // Complete and change name HERE
		dtoAnalogSensor.manufacturer = UPDATED_MANUFACTURER;
		dtoAnalogSensor.reference = UPDATED_REFERENCE;
			given()
				.body(dtoAnalogSensor)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/Sensor/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("manufacturer", Is.is(UPDATED_MANUFACTURER))
				.body("reference", Is.is(UPDATED_REFERENCE));
			
			List<DTOPlainAnalogSensor> analogsensorList = given()
				.when()
				.get("/Sensor/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogSensor.class);
				
		assertEquals(NB_ANALOGSENSOR + 1, analogsensorList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveAAnalogSensor(){
		given()
			.pathParam("id", id_AnalogSensor)
			.when()
			.delete("/Sensor/analog/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainAnalogSensor> analogsensorList = given()
			.when()
			.get("/Sensor/analog")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainAnalogSensor.class);
		
		assertEquals(NB_ANALOGSENSOR, analogsensorList.size());
	}

	/**************************************************** NUM SENSOR METHOD ********************************************************/

	@Test
    void shouldNotGetUnknownNumSensor(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/Sensor/num/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidNumSensor(){
		DTOPlainNumSensor dtoNumSensor= new DTOPlainNumSensor();
        // Complete and change HERE
		dtoNumSensor.manufacturer = " ";
		dtoNumSensor.reference = " ";
		
		given() 
			.body(dtoNumSensor)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/Sensor/num")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_NUMSENSOR = 3;
    
    @Test
	@Order(6)
	void shouldGetInitialNumSensor(){
		List<DTOPlainNumSensor> numsensorList = given()
				.when()
				.get("/Sensor/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumSensor.class);
		assertEquals(NB_NUMSENSOR, numsensorList.size());
	}
	
	private static Long id_NumSensor;
    
    @Test
	@Order(7)
	void shouldAddNumSensor () {
		DTOPlainNumSensor dtoNumSensor = new DTOPlainNumSensor();
		dtoNumSensor.manufacturer = MANUFACTURER;
		dtoNumSensor.reference = REFERENCE;
		
		DTOPlainNumSensor numsensor = given()
				.body(dtoNumSensor)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/Sensor/num")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainNumSensor.class);

		assertNotNull(numsensor.id);
		id_NumSensor = numsensor.id;
		
		List<DTOPlainNumSensor> numsensorList = given()
				.when()
				.get("/Sensor/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumSensor.class);
		assertEquals(NB_NUMSENSOR + 1, numsensorList.size());
	}
    
    @Test
	@Order(8)
	void shouldGetANumSensorById() {
		given()
			.pathParam("id", id_NumSensor)
			.when()
			.get("/Sensor/num/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("manufacturer", Is.is(MANUFACTURER))
			.body("reference", Is.is(REFERENCE));
	}
    
    @Test
	@Order(9)
	void shouldUpdateANumSensor() {
		DTOPlainNumSensor dtoNumSensor = new DTOPlainNumSensor();
		dtoNumSensor.id = id_NumSensor;
        // Complete and change name HERE
		dtoNumSensor.manufacturer = UPDATED_MANUFACTURER;
		dtoNumSensor.reference = UPDATED_REFERENCE;
			given()
				.body(dtoNumSensor)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/Sensor/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("manufacturer", Is.is(UPDATED_MANUFACTURER))
				.body("reference", Is.is(UPDATED_REFERENCE));
			
			List<DTOPlainNumSensor> numsensorList = given()
				.when()
				.get("/Sensor/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumSensor.class);
				
		assertEquals(NB_NUMSENSOR + 1, numsensorList.size());
	}
    
    @Test
	@Order(10)
	void shouldRemoveANumSensor(){
		given()
			.pathParam("id", id_NumSensor)
			.when()
			.delete("/Sensor/num/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainNumSensor> numsensorList = given()
			.when()
			.get("/Sensor/num")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainNumSensor.class);
		
		assertEquals(NB_NUMSENSOR, numsensorList.size());
	}
}
