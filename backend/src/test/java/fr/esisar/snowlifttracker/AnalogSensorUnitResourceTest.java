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
 * File name: AnalogSensorUnitRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for AnalogSensorUnitRessource.java to automate       
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

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogSensorUnit;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnalogSensorUnitResourceTest {

    @Test
    void shouldNotGetUnknownAnalogSensorUnit(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/AnalogSensorUnit/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidAnalogSensorUnit(){
		DTOPlainAnalogSensorUnit dtoAnalogSensorUnit= new DTOPlainAnalogSensorUnit();
        // Complete and change HERE
		dtoAnalogSensorUnit.unit = " "; // @NotBlanck
		
		given() 
			.body(dtoAnalogSensorUnit)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/AnalogSensorUnit")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_ANALOGSENSORUNIT = 3;
    
    @Test
	void shouldGetInitialAnalogSensorUnit(){
		List<DTOPlainAnalogSensorUnit> analogsensorunitList = given()
				.when()
				.get("/AnalogSensorUnit")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogSensorUnit.class);
		assertEquals(NB_ANALOGSENSORUNIT, analogsensorunitList.size());
	}
    
    // Complete and change name HERE
    private static final String UNIT = "°C";
	
	private static Long id_AnalogSensorUnit;
    
    @Test
	@Order(2)
	void shouldAddAnalogSensorUnit () {
		DTOPlainAnalogSensorUnit dtoAnalogSensorUnit = new DTOPlainAnalogSensorUnit();
		dtoAnalogSensorUnit.unit = UNIT;
		
		DTOPlainAnalogSensorUnit analogsensorunit = given()
				.body(dtoAnalogSensorUnit)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/AnalogSensorUnit")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainAnalogSensorUnit.class);

		assertNotNull(analogsensorunit.id);
		id_AnalogSensorUnit = analogsensorunit.id;
		
		List<DTOPlainAnalogSensorUnit> analogsensorunitList = given()
				.when()
				.get("/AnalogSensorUnit")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogSensorUnit.class);
		assertEquals(NB_ANALOGSENSORUNIT + 1, analogsensorunitList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetAAnalogSensorUnitById() {
		given()
			.pathParam("id", id_AnalogSensorUnit)
			.when()
			.get("/AnalogSensorUnit/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("unit", Is.is(UNIT));
	}
    
    // Complete and change name HERE
    private static final String UPDATED_UNIT = "°F";
    
    @Test
	@Order(4)
	void shouldUpdateAAnalogSensorUnit() {
		DTOPlainAnalogSensorUnit dtoAnalogSensorUnit = new DTOPlainAnalogSensorUnit();
		dtoAnalogSensorUnit.id = id_AnalogSensorUnit;
        // Complete and change name HERE
		dtoAnalogSensorUnit.unit = UPDATED_UNIT;
			given()
				.body(dtoAnalogSensorUnit)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/AnalogSensorUnit")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("unit", Is.is(UPDATED_UNIT));
			
			List<DTOPlainAnalogSensorUnit> analogsensorunitList = given()
				.when()
				.get("/AnalogSensorUnit")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogSensorUnit.class);
				
		assertEquals(NB_ANALOGSENSORUNIT + 1, analogsensorunitList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveAAnalogSensorUnit(){
		given()
			.pathParam("id", id_AnalogSensorUnit)
			.when()
			.delete("/AnalogSensorUnit/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainAnalogSensorUnit> analogsensorunitList = given()
			.when()
			.get("/AnalogSensorUnit")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainAnalogSensorUnit.class);
		
		assertEquals(NB_ANALOGSENSORUNIT, analogsensorunitList.size());
	}
}
