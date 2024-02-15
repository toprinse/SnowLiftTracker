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
 * File name: SensorTypeRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for SensorTypeRessource.java to automate       
 * REST API testing.                                                                
 ************************************************************************************/

package fr.esisar.snowlifttracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.common.net.HttpHeaders;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSensorType;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SensorTypeResourceTest {

    @Test
    void shouldNotGetUnknownSensorType(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/SensorType/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidSensorType(){
		DTOPlainSensorType dtoSensorType= new DTOPlainSensorType();
        // Complete and change HERE
		dtoSensorType.type = " "; // @NotBlanck
		
		given() 
			.body(dtoSensorType)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/SensorType")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_SENSORTYPE = 3;
    
    @Test
	void shouldGetInitialSensorType(){
		List<DTOPlainSensorType> sensortypeList = given()
				.when()
				.get("/SensorType")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainSensorType.class);
		assertEquals(NB_SENSORTYPE, sensortypeList.size());
	}
    
    // Complete and change name HERE
    private static final String TYPE = "type";
	
	private static Long id_SensorType;
    
    @Test
	@Order(2)
	void shouldAddSensorType () {
		DTOPlainSensorType dtoSensorType = new DTOPlainSensorType();
		dtoSensorType.type = TYPE;
		
		DTOPlainSensorType sensortype = given()
				.body(dtoSensorType)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/SensorType")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainSensorType.class);

		assertNotNull(sensortype.id);
		id_SensorType = sensortype.id;
		
		List<DTOPlainSensorType> sensortypeList = given()
				.when()
				.get("/SensorType")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainSensorType.class);
		assertEquals(NB_SENSORTYPE + 1, sensortypeList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetASensorTypeById() {
		given()
			.pathParam("id", id_SensorType)
			.when()
			.get("/SensorType/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("type", Is.is(TYPE));
	}
    
    // Complete and change name HERE
    private static final String UPDATED_TYPE = "updated_type";
    
    @Test
	@Order(4)
	void shouldUpdateASensorType() {
		DTOPlainSensorType dtoSensorType = new DTOPlainSensorType();
		dtoSensorType.id = id_SensorType;
        // Complete and change name HERE
		dtoSensorType.type= UPDATED_TYPE;

			given()
				.body(dtoSensorType)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/SensorType")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("type", Is.is(UPDATED_TYPE));
			
			List<DTOPlainSensorType> sensortypeList = given()
				.when()
				.get("/SensorType")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainSensorType.class);
				
		assertEquals(NB_SENSORTYPE + 1, sensortypeList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveASensorType(){
		given()
			.pathParam("id", id_SensorType)
			.when()
			.delete("/SensorType/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainSensorType> sensortypeList = given()
			.when()
			.get("/SensorType")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainSensorType.class);
		
		assertEquals(NB_SENSORTYPE, sensortypeList.size());
	}
}
