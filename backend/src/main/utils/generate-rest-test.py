import datetime
import os
import platform

def generate_test_file(file_name, class_name):
    content = f"""\
/************************************************************************************
 *  ___       __   ________  ________  ________   ___  ________   ________          
 * |\  \     |\  \|\   __  \|\   __  \|\   ___  \|\  \|\   ___  \|\   ____\         
 * \ \  \    \ \  \ \  \|\  \ \  \|\  \ \  \\\ \  \ \  \ \  \\\ \  \ \  \___|        
 *  \ \  \  __\ \  \ \   __  \ \   _  _\ \  \\\ \  \ \  \ \  \\\ \  \ \  \  ___      
 *   \ \  \|\__\_\  \ \  \ \  \ \  \\\  \\\ \  \\\ \  \ \  \ \  \\\ \  \ \  \|\  \   
 *    \ \____________\ \__\ \__\ \__\\\ _\\\ \__\\\ \__\ \__\ \__\\\ \__\ \_______\\ 
 *     \|____________|\|__|\|__|\|__|\|__|\|__| \|__|\|__|\|__| \|__|\|_______|      
 *                                                                                  	   
 * This file is auto-generated you should change attribute at certain places.        
 * File name: {class_name}RessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: {datetime.datetime.now().strftime("%m/%d/%Y-%H:%M:%S")}           
 * Description: This file is a test for {class_name}Ressource.java to automate       
 * REST API testing.                                                                
 ************************************************************************************/

package fr.esisar.snowlifttracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.common.net.HttpHeaders;

import fr.esisar.snowlifttracker.dto.plain.DTOPlain{class_name};
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class {class_name}RessourceTest {{

    @Test
    void shouldNotGetUnknown{class_name}(){{
        given()
            .pathParam("id", -1)
            .when()
            .get("/{class_name.lower()}/{{id}}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }}

    @Test
	void shouldNotAddInvalid{class_name}(){{
		DTOPlain{class_name} dto{class_name}= new DTOPlain{class_name}();
        // Complete and change HERE
		dto{class_name}.attribute = "";
		
		given() 
			.body(dto{class_name})
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/{class_name.lower()}")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}}
    
    private static final int NB_{class_name.upper()} = 3;
    
    @Test
	void shouldGetInitialMoutons(){{
		List<DTOPlain{class_name}> {class_name.lower()}List = given()
				.when()
				.get("/{class_name.lower()}")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlain{class_name}.class);
		assertEquals(NB_{class_name.upper()}, {class_name}List.size());
	}}
    
    // Complete and change name HERE
    private static final Type ATTRIBUTE1 = ;
	private static final Type ATTRIBUTE2 = ;
	
	private static Long id_{class_name};
    
    @Test
	@Order(2)
	void shouldAdd{class_name} () {{
		DTOPlain{class_name} dto{class_name} = new DTOPlain{class_name}();
		dto{class_name}.attribute1 = ATTRIBUTE1;
		dto{class_name}.dateNaissance = ATTRIBUTE2;
		
		DTOPlain{class_name} {class_name.lower()} = given()
				.body(dto{class_name})
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/{class_name.lower()}")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlain{class_name}.class);

		assertNotNull({class_name.lower()}.id);
		id_{class_name} = {class_name.lower()}.id;
		
		List<DTOPlain{class_name}> {class_name.lower()}List = given()
				.when()
				.get("/{class_name.lower()}")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlain{class_name}.class);
		assertEquals(NB_{class_name.upper()} + 1, {class_name.lower()}List.size());
	}}
    
    @Test
	@Order(3)
	void shouldGetA{class_name}ById() {{
		given()
			.pathParam("id", id_{class_name})
			.when()
			.get("/{class_name}/{{id}}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("attribute1", Is.is(ATTRIBUTE1))
			.body("attribute2", Is.is(ATTRIBUTE2.format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
	}}
    
    // Complete and change name HERE
    private static final Type UPDATED_ATTRIBUTE1 = ;
	private static final Type UPDATED_ATTRIBUTE2 = ;
    
    @Test
	@Order(4)
	void shouldUpdateA{class_name}() {{
		DTOPlain{class_name} dto{class_name} = new DTOPlain{class_name}();
		dto{class_name}.id = id_{class_name};
        // Complete and change name HERE
		dto{class_name}.attribute1 = UPDATED_ATTRIBUTE1;
		dto{class_name}.attribute2 = UPDATED_ATTRIBUTE2;
			given()
				.body(dto{class_name})
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/{class_name}")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("attribute1", Is.is(ATTRIBUTE1))
				.body("attribute2", Is.is(UPDATED_ATTRIBUTE2.format(
						DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
			
			List<DTOPlain{class_name}> {class_name.lower()}List = given()
				.when()
				.get("/{class_name}")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlain{class_name}.class);
				
		assertEquals(NB_{class_name.upper()} + 1, {class_name.lower()}List.size());
	}}
    
    @Test
	@Order(5)
	void shouldRemoveA{class_name}(){{
		given()
			.pathParam("id", id_{class_name})
			.when()
			.delete("/{class_name}/{{id}}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlain{class_name}> {class_name.lower()}List = given()
			.when()
			.get("/{class_name}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlain{class_name}.class);
		
		assertEquals(NB_{class_name.upper()}, {class_name.lower()}List.size());
	}}
}}
"""

    with open(file_name, 'w') as file:
        file.write(content)

# Specify the classes names you want to use
my_classes = ["AnalogMeasure",
				"AnalogSensor",
				"AnalogSensorUnit",
				"Measure",
				"NumMeasure",
				"NumSensor",
				"NumSensorState",
				"Sensor",
				"SensorType",
				"SkiLift",
				"Sation",
				"UserSnow"]

BUILD_DIR = "build"
PATH = os.getcwd()
PATH_SEPARATOR = "/"
if platform.system() == "Windows":
    PATH_SEPARATOR = "\\"
    
BUILD_PATH = PATH+PATH_SEPARATOR+BUILD_DIR
if os.path.exists(BUILD_PATH):
	os.rmdir(BUILD_DIR)
      
os.mkdir(BUILD_DIR)
os.chdir(PATH+PATH_SEPARATOR+BUILD_DIR)

for my_class in my_classes:
	# Generate the test file with the specified class name
	file_name = f"{my_class}RessourceTest.java"
	generate_test_file(file_name, my_class)
	print(f"Java test file '{file_name}' generated successfully.")
