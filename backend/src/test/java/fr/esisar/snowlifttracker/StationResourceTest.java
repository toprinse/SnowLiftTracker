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
 * File name: StationRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for StationRessource.java to automate       
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

import fr.esisar.snowlifttracker.dto.plain.DTOPlainStation;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StationResourceTest {

    @Test
    void shouldNotGetUnknownStation(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/Station/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidStation(){
		DTOPlainStation dtoStation= new DTOPlainStation();
        // Complete and change HERE
		dtoStation.latitude = " "; // @NotBlanck
		dtoStation.longitude = " "; // @NotBlanck
		dtoStation.name = " "; // @NotBlanck
		
		given() 
			.body(dtoStation)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/Station")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_STATION = 3;
    
    @Test
	void shouldGetInitialStation(){
		List<DTOPlainStation> stationList = given()
				.when()
				.get("/Station")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainStation.class);
		assertEquals(NB_STATION, stationList.size());
	}
    
    // Complete and change name HERE
    private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String NAME = "name";
	
	private static Long id_Station;
    
    @Test
	@Order(2)
	void shouldAddStation () {
		DTOPlainStation dtoStation = new DTOPlainStation();
		dtoStation.latitude = LATITUDE; 
		dtoStation.longitude = LONGITUDE; 
		dtoStation.name = NAME; 
		
		DTOPlainStation station = given()
				.body(dtoStation)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/Station")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainStation.class);

		assertNotNull(station.id);
		id_Station = station.id;
		
		List<DTOPlainStation> stationList = given()
				.when()
				.get("/Station")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainStation.class);
		assertEquals(NB_STATION + 1, stationList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetAStationById() {
		given()
			.pathParam("id", id_Station)
			.when()
			.get("/Station/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("latitude", Is.is(LATITUDE))
			.body("longitude", Is.is(LONGITUDE))
			.body("name", Is.is(NAME));
	}
    
    // Complete and change name HERE
    private static final String UPDATED_LATITUDE = "updated_latitude";
	private static final String UPDATED_LONGITUDE = "updated_longitude";
	private static final String UPDATED_NAME = "updated_name";
    
    @Test
	@Order(4)
	void shouldUpdateAStation() {
		DTOPlainStation dtoStation = new DTOPlainStation();
		dtoStation.id = id_Station;
        // Complete and change name HERE
		dtoStation.latitude = UPDATED_LATITUDE;
		dtoStation.longitude = UPDATED_LONGITUDE;
		dtoStation.name = UPDATED_NAME;
			given()
				.body(dtoStation)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/Station")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("latitude", Is.is(UPDATED_LATITUDE))
				.body("longitude", Is.is(UPDATED_LONGITUDE))
				.body("name", Is.is(UPDATED_NAME));
			
			List<DTOPlainStation> stationList = given()
				.when()
				.get("/Station")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainStation.class);
				
		assertEquals(NB_STATION + 1, stationList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveAStation(){
		given()
			.pathParam("id", id_Station)
			.when()
			.delete("/Station/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainStation> stationList = given()
			.when()
			.get("/Station")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainStation.class);
		
		assertEquals(NB_STATION, stationList.size());
	}
}
