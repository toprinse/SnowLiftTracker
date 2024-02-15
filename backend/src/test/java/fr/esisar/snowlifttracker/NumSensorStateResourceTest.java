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
 * File name: NumSensorStateRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for NumSensorStateRessource.java to automate       
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

import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumSensorState;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NumSensorStateResourceTest {

    @Test
    void shouldNotGetUnknownNumSensorState(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/NumSensorState/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidNumSensorState(){
		DTOPlainNumSensorState dtoNumSensorState= new DTOPlainNumSensorState();
        // Complete and change HERE
		dtoNumSensorState.high = null; // nullable = false
		dtoNumSensorState.low = null; // nullable = false
		
		given() 
			.body(dtoNumSensorState)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/NumSensorState")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_NUMSENSORSTATE = 3;
    
    @Test
	void shouldGetInitialNumSensorState(){
		List<DTOPlainNumSensorState> numsensorstateList = given()
				.when()
				.get("/NumSensorState")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumSensorState.class);
		assertEquals(NB_NUMSENSORSTATE, numsensorstateList.size());
	}
    
    // Complete and change name HERE
    private static final Boolean HIGH = true;
	private static final Boolean LOW = false;
	
	private static Long id_NumSensorState;
    
    @Test
	@Order(2)
	void shouldAddNumSensorState () {
		DTOPlainNumSensorState dtoNumSensorState = new DTOPlainNumSensorState();
		dtoNumSensorState.high = HIGH;
		dtoNumSensorState.low = LOW;
		
		DTOPlainNumSensorState numsensorstate = given()
				.body(dtoNumSensorState)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/NumSensorState")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainNumSensorState.class);

		assertNotNull(numsensorstate.id);
		id_NumSensorState = numsensorstate.id;
		
		List<DTOPlainNumSensorState> numsensorstateList = given()
				.when()
				.get("/NumSensorState")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumSensorState.class);
		assertEquals(NB_NUMSENSORSTATE + 1, numsensorstateList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetANumSensorStateById() {
		given()
			.pathParam("id", id_NumSensorState)
			.when()
			.get("/NumSensorState/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("high", Is.is(HIGH))
			.body("low", Is.is(LOW));
	}
    
    // Complete and change name HERE
    private static final Boolean UPDATED_HIGH = false;
	private static final Boolean UPDATED_LOW = true;
    
    @Test
	@Order(4)
	void shouldUpdateANumSensorState() {
		DTOPlainNumSensorState dtoNumSensorState = new DTOPlainNumSensorState();
		dtoNumSensorState.id = id_NumSensorState;
        // Complete and change name HERE
		dtoNumSensorState.high = UPDATED_HIGH;
		dtoNumSensorState.low = UPDATED_LOW;
			given()
				.body(dtoNumSensorState)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/NumSensorState")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("high", Is.is(UPDATED_HIGH))
				.body("low", Is.is(UPDATED_LOW));
			
			List<DTOPlainNumSensorState> numsensorstateList = given()
				.when()
				.get("/NumSensorState")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumSensorState.class);
				
		assertEquals(NB_NUMSENSORSTATE + 1, numsensorstateList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveANumSensorState(){
		given()
			.pathParam("id", id_NumSensorState)
			.when()
			.delete("/NumSensorState/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainNumSensorState> numsensorstateList = given()
			.when()
			.get("/NumSensorState")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainNumSensorState.class);
		
		assertEquals(NB_NUMSENSORSTATE, numsensorstateList.size());
	}
}
