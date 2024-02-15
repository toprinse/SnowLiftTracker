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
 * File name: MeasureRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for MeasureRessource.java to automate       
 * REST API testing.                                                                
 ************************************************************************************/

package fr.esisar.snowlifttracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.common.net.HttpHeaders;

import fr.esisar.snowlifttracker.dto.plain.DTOPlainAnalogMeasure;
import fr.esisar.snowlifttracker.dto.plain.DTOPlainNumMeasure;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
//@Disabled // to debug other tests
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeasureResourceTest {

    /************************************************** ANALOG MEASURE METHOD **********************************************************/

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	//date JSON Format

	@Test
    void shouldNotGetUnknownAnalogMeasure(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/Measure/analog/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidAnalogMeasure(){
		DTOPlainAnalogMeasure dtoAnalogMeasure= new DTOPlainAnalogMeasure();
        // Complete and change HERE
		dtoAnalogMeasure.analogData = null;
		dtoAnalogMeasure.timestamp = LocalDateTime.now();
		
		given() 
			.body(dtoAnalogMeasure)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/Measure/analog")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_ANALOGMEASURE = 3;
    
    @Test
	void shouldGetInitialAnalogMeasure(){
		List<DTOPlainAnalogMeasure> analogmeasureList = given()
				.when()
				.get("/Measure/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogMeasure.class);
		assertEquals(NB_ANALOGMEASURE, analogmeasureList.size());
	}
    
    // Complete and change name HERE
    private static final Float ANALOGDATA = (float) 999.9;
	private static final LocalDateTime TIMESTAMP = LocalDateTime.parse("2023-11-17 12:00:00", formatter);
	
	private static Long id_AnalogMeasure;
    
    @Test
	@Order(2)
	void shouldAddAnalogMeasure () {
		DTOPlainAnalogMeasure dtoAnalogMeasure = new DTOPlainAnalogMeasure();
		dtoAnalogMeasure.analogData = ANALOGDATA;
		dtoAnalogMeasure.timestamp = TIMESTAMP;
		
		DTOPlainAnalogMeasure analogmeasure = given()
				.body(dtoAnalogMeasure)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/Measure/analog")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainAnalogMeasure.class);

		assertNotNull(analogmeasure.id);
		id_AnalogMeasure = analogmeasure.id;
		
		List<DTOPlainAnalogMeasure> analogmeasureList = given()
				.when()
				.get("/Measure/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogMeasure.class);
		assertEquals(NB_ANALOGMEASURE + 1, analogmeasureList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetAnAnalogMeasureById() {
		given()
			.pathParam("id", id_AnalogMeasure)
			.when()
			.get("/Measure/analog/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("analogData", Is.is(ANALOGDATA))
			.body("timestamp", Is.is(TIMESTAMP.format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
	}
    
    // Complete and change name HERE
    private static final Float UPDATED_ANALOGDATA = (float) 111.1;
	private static final LocalDateTime UPDATED_TIMESTAMP = LocalDateTime.parse("9999-11-17 24:00:00", formatter);
    
    @Test
	@Order(4)
	void shouldUpdateAnAnalogMeasure() {
		DTOPlainAnalogMeasure dtoAnalogMeasure = new DTOPlainAnalogMeasure();
		dtoAnalogMeasure.id = id_AnalogMeasure;
        // Complete and change name HERE
		dtoAnalogMeasure.analogData = UPDATED_ANALOGDATA;
		dtoAnalogMeasure.timestamp = UPDATED_TIMESTAMP;
			given()
				.body(dtoAnalogMeasure)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/Measure/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("analogData", Is.is(UPDATED_ANALOGDATA))
				.body("timestamp", Is.is(UPDATED_TIMESTAMP.format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

			List<DTOPlainAnalogMeasure> analogmeasureList = given()
				.when()
				.get("/Measure/analog")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainAnalogMeasure.class);
				
		assertEquals(NB_ANALOGMEASURE + 1, analogmeasureList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveAnAnalogMeasure(){
		given()
			.pathParam("id", id_AnalogMeasure)
			.when()
			.delete("/Measure/analog/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainAnalogMeasure> analogmeasureList = given()
			.when()
			.get("/Measure/analog")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainAnalogMeasure.class);
		
		assertEquals(NB_ANALOGMEASURE, analogmeasureList.size());
	}

	/**************************************************** NUM MEASURE METHOD ********************************************************/

	@Test
	@Order(6)
    void shouldNotGetUnknownNumMeasure(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/Measure/num/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	@Order(7)
	void shouldNotAddInvalidNumMeasure(){
		DTOPlainNumMeasure dtoNumMeasure= new DTOPlainNumMeasure();
        // Complete and change HERE
		dtoNumMeasure.numData = null; //nullable = false
		dtoNumMeasure.timestamp = LocalDateTime.now();
		
		given() 
			.body(dtoNumMeasure)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/Measure/num")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_NUMMEASURE = 3;
    
    @Test
	@Order(8)
	void shouldGetInitialNumMeasure(){
		List<DTOPlainNumMeasure> nummeasureList = given()
				.when()
				.get("/Measure/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumMeasure.class);
		assertEquals(NB_NUMMEASURE, nummeasureList.size());
	}
    
    // Complete and change name HERE
    private static final Boolean NUMDATA = true;
	
	private static Long id_NumMeasure;
    
    @Test
	@Order(9)
	void shouldAddNumMeasure () {
		DTOPlainNumMeasure dtoNumMeasure = new DTOPlainNumMeasure();
		dtoNumMeasure.numData = NUMDATA;
		dtoNumMeasure.timestamp = TIMESTAMP;
		
		DTOPlainNumMeasure nummeasure = given()
				.body(dtoNumMeasure)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/Measure/num")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainNumMeasure.class);

		assertNotNull(nummeasure.id);
		id_NumMeasure = nummeasure.id;
		
		List<DTOPlainNumMeasure> nummeasureList = given()
				.when()
				.get("/Measure/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumMeasure.class);
		assertEquals(NB_NUMMEASURE + 1, nummeasureList.size());
	}
    
    @Test
	@Order(10)
	void shouldGetANumMeasureById() {
		given()
			.pathParam("id", id_NumMeasure)
			.when()
			.get("/Measure/num/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("numData", Is.is(NUMDATA))
			.body("timestamp", Is.is(TIMESTAMP.format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
	}
    
    // Complete and change name HERE
    private static final Boolean UPDATED_NUMDATA = false;
    
    @Test
	@Order(11)
	void shouldUpdateANumMeasure() {
		DTOPlainNumMeasure dtoNumMeasure = new DTOPlainNumMeasure();
		dtoNumMeasure.id = id_NumMeasure;
        // Complete and change name HERE
		dtoNumMeasure.numData = UPDATED_NUMDATA;
		dtoNumMeasure.timestamp = UPDATED_TIMESTAMP;
			given()
				.body(dtoNumMeasure)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/Measure/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("numData", Is.is(UPDATED_NUMDATA))
				.body("timestamp", Is.is(UPDATED_TIMESTAMP.format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
			
			List<DTOPlainNumMeasure> nummeasureList = given()
				.when()
				.get("/Measure/num")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainNumMeasure.class);
				
		assertEquals(NB_NUMMEASURE + 1, nummeasureList.size());
	}
    
    @Test
	@Order(12)
	void shouldRemoveANumMeasure(){
		given()
			.pathParam("id", id_NumMeasure)
			.when()
			.delete("/Measure/num/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainNumMeasure> nummeasureList = given()
			.when()
			.get("/Measure/num")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainNumMeasure.class);
		
		assertEquals(NB_NUMMEASURE, nummeasureList.size());
	}
}
