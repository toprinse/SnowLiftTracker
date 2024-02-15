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
 * File name: UserSnowRessourceTest.java                                        
 * File type: java source file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: 11/16/2023-15:44:44           
 * Description: This file is a test for UserSnowRessource.java to automate       
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

import fr.esisar.snowlifttracker.dto.plain.DTOPlainUserSnow;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserSnowResourceTest {

    @Test
    void shouldNotGetUnknownUserSnow(){
        given()
            .pathParam("id", -1)
            .when()
            .get("/UserSnow/{id}")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
	void shouldNotAddInvalidUserSnow(){
		DTOPlainUserSnow dtoUserSnow= new DTOPlainUserSnow();
        // Complete and change HERE
		dtoUserSnow.email = " "; // @NotBlanck
		dtoUserSnow.firstname = " "; // @NotBlanck
		dtoUserSnow.lastname = " "; // @NotBlanck
		dtoUserSnow.login = " "; // @NotBlanck
		dtoUserSnow.password = " "; // @NotBlanck
		dtoUserSnow.permission = " "; // @NotBlanck
		
		given() 
			.body(dtoUserSnow)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.when()
			.post("/UserSnow")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}
    
    private static final int NB_USERSNOW = 3;
    
    @Test
	void shouldGetInitialUserSnow(){
		List<DTOPlainUserSnow> usersnowList = given()
				.when()
				.get("/UserSnow")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainUserSnow.class);
		assertEquals(NB_USERSNOW, usersnowList.size());
	}
    
    // Complete and change name HERE
    private static final String EMAIL = "user.user@gmail.com";
	private static final String FIRSTNAME = "user";
	private static final String LASTNAME = "user";
	private static final String LOGIN = "useru";
	private static final String PASSWORD = "password";
	private static final String PERMISSION = "client";
	
	private static Long id_UserSnow;
    
    @Test
	@Order(2)
	void shouldAddUserSnow () {
		DTOPlainUserSnow dtoUserSnow = new DTOPlainUserSnow();
		dtoUserSnow.email = EMAIL;
		dtoUserSnow.firstname = FIRSTNAME;
		dtoUserSnow.lastname = LASTNAME;
		dtoUserSnow.login = LOGIN;
		dtoUserSnow.password = PASSWORD;
		dtoUserSnow.permission = PERMISSION;

		DTOPlainUserSnow usersnow = given()
				.body(dtoUserSnow)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.post("/UserSnow")
				.then()
				.statusCode(Status.CREATED.getStatusCode())
				.extract()
				.body()
				.as(DTOPlainUserSnow.class);

		assertNotNull(usersnow.id);
		id_UserSnow = usersnow.id;
		
		List<DTOPlainUserSnow> usersnowList = given()
				.when()
				.get("/UserSnow")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainUserSnow.class);
		assertEquals(NB_USERSNOW + 1, usersnowList.size());
	}
    
    @Test
	@Order(3)
	void shouldGetAUserSnowById() {
		given()
			.pathParam("id", id_UserSnow)
			.when()
			.get("/UserSnow/{id}")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
            // Complete and change name HERE
			.body("email", Is.is(EMAIL))
			.body("firstname", Is.is(FIRSTNAME))
			.body("lastname", Is.is(LASTNAME))
			.body("login", Is.is(LOGIN))
			.body("password", Is.is(PASSWORD))
			.body("permission", Is.is(PERMISSION));
	}
    
    // Complete and change name HERE
    private static final String UPDATED_EMAIL = "newuser.newuser@gmail.com";
	private static final String UPDATED_FIRSTNAME = "newuser";
	private static final String UPDATED_LASTNAME = "newuser";
	private static final String UPDATED_LOGIN = "newuseru";
	private static final String UPDATED_PASSWORD = "newpassword";
	private static final String UPDATED_PERMISSION = "newclient";
    
    @Test
	@Order(4)
	void shouldUpdateAUserSnow() {
		DTOPlainUserSnow dtoUserSnow = new DTOPlainUserSnow();
		dtoUserSnow.id = id_UserSnow;
        // Complete and change name HERE
		dtoUserSnow.email = UPDATED_EMAIL;
		dtoUserSnow.firstname = UPDATED_FIRSTNAME;
		dtoUserSnow.lastname = UPDATED_LASTNAME;
		dtoUserSnow.login = UPDATED_LOGIN;
		dtoUserSnow.password = UPDATED_PASSWORD;
		dtoUserSnow.permission = UPDATED_PERMISSION;

			given()
				.body(dtoUserSnow)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.when()
				.put("/UserSnow")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.body("email", Is.is(UPDATED_EMAIL))
				.body("firstname", Is.is(UPDATED_FIRSTNAME))
				.body("lastname", Is.is(UPDATED_LASTNAME))
				.body("login", Is.is(UPDATED_LOGIN))
				.body("password", Is.is(UPDATED_PASSWORD))
				.body("permission", Is.is(UPDATED_PERMISSION));
			
			List<DTOPlainUserSnow> usersnowList = given()
				.when()
				.get("/UserSnow")
				.then()
				.statusCode(Status.OK.getStatusCode())
				.contentType(MediaType.APPLICATION_JSON)
				.extract()
				.body()
				.jsonPath()
				.getList(".", DTOPlainUserSnow.class);
				
		assertEquals(NB_USERSNOW + 1, usersnowList.size());
	}
    
    @Test
	@Order(5)
	void shouldRemoveAUserSnow(){
		given()
			.pathParam("id", id_UserSnow)
			.when()
			.delete("/UserSnow/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
		
		List<DTOPlainUserSnow> usersnowList = given()
			.when()
			.get("/UserSnow")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(MediaType.APPLICATION_JSON)
			.extract()
			.body()
			.jsonPath()
			.getList(".", DTOPlainUserSnow.class);
		
		assertEquals(NB_USERSNOW, usersnowList.size());
	}
}
