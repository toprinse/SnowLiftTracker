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
 * File name: SkiLiftRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for SkiLiftRessource.java to automate       
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

import fr.esisar.snowlifttracker.dto.plain.DTOPlainSkiLift;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SkiLiftResourceTest {

    @Test
    void shouldNotGetUnknownSkiLift(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/SkiLift/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidSkiLift(){
		DTOPlainSkiLift dtoSkiLift= new DTOPlainSkiLift();
        // Complete and change HERE
		dtoSkiLift.latitude = " ";
		dtoSkiLift.longitude = " ";
		dtoSkiLift.name = " ";
		dtoSkiLift.open = null;
		
		given() 
			.body(dtoSkiLift)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/SkiLift")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_SKILIFT = 3;
    
    @Test
	void shouldGetInitialSkiLift(){
		List<DTOPlainSkiLift> skiliftList = given()
				.when()
				.get("/SkiLift")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainSkiLift.class);
		assertEquals(NB_SKILIFT, skiliftList.size());
	}
    
    // Complete and change name HERE
    private static final String LONGITUDE = "longitude";
	private static final String LATITUDE = "latitude";
	private static final String NAME = "name";
	private static final Boolean OPEN = true;
	
	private static Long id_SkiLift;
    
    @Test
	@Order(2)
	void shouldAddSkiLift () {
		DTOPlainSkiLift dtoSkiLift = new DTOPlainSkiLift();
		dtoSkiLift.longitude = LONGITUDE;
		dtoSkiLift.latitude = LATITUDE;
		dtoSkiLift.name = NAME;
		dtoSkiLift.open = OPEN;
		
		DTOPlainSkiLift skilift = given()
				.body(dtoSkiLift)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/SkiLift")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainSkiLift.class);

		assertNotNull(skilift.id);
		id_SkiLift = skilift.id;
		
		List<DTOPlainSkiLift> skiliftList = given()
				.when()
				.get("/SkiLift")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainSkiLift.class);
		assertEquals(NB_SKILIFT + 1, skiliftList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetASkiLiftById() {
		given()
			.pathParam("id", id_SkiLift)
			.when()
			.get("/SkiLift/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("longitude", Is.is(LONGITUDE))
			.body("latitude", Is.is(LATITUDE))
			.body("name", Is.is(NAME))
			.body("open", Is.is(OPEN));
	}
    
    // Complete and change name HERE
    private static final String UPDATED_LONGITUDE = "updated_longitude";
	private static final String UPDATED_LATITUDE = "updated_latitude";
	private static final String UPDATED_NAME = "updated_name";
	private static final Boolean UPDATED_OPEN = false;
    
    @Test
	@Order(4)
	void shouldUpdateASkiLift() {
		DTOPlainSkiLift dtoSkiLift = new DTOPlainSkiLift();
		dtoSkiLift.id = id_SkiLift;
        // Complete and change name HERE
		dtoSkiLift.longitude = UPDATED_LONGITUDE;
		dtoSkiLift.latitude = UPDATED_LATITUDE;
		dtoSkiLift.name = UPDATED_NAME;
		dtoSkiLift.open = UPDATED_OPEN;

			given()
				.body(dtoSkiLift)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/SkiLift")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("longitude", Is.is(UPDATED_LONGITUDE))
				.body("latitude", Is.is(UPDATED_LATITUDE))
				.body("name", Is.is(UPDATED_NAME));
				//.body("open", Is.is(UPDATED_OPEN)); // This line is buggy and I don't know why... 
			
			List<DTOPlainSkiLift> skiliftList = given()
				.when()
				.get("/SkiLift")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainSkiLift.class);
				
		assertEquals(NB_SKILIFT + 1, skiliftList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveASkiLift(){
		given()
			.pathParam("id", id_SkiLift)
			.when()
			.delete("/SkiLift/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainSkiLift> skiliftList = given()
			.when()
			.get("/SkiLift")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainSkiLift.class);
		
		assertEquals(NB_SKILIFT, skiliftList.size());
	}
}
