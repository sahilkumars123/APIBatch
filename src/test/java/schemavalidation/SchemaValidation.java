package schemavalidation;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.CreateUser;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class SchemaValidation {

	//json
	
	@Test
	public void createUserSchemaValidation() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		 given().log().all()
				.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
				.contentType(ContentType.JSON)
				.body(new File("./src/test/resoruces/data/createuser.json")).
			when().log().all()
				.post("/public/v2/users").
			then().log().all()
				.assertThat()
					.statusCode(201)
					.body(matchesJsonSchemaInClasspath("createuserschema.json"));	
	}
	
	@Test
	public void getUserSchemaValidation() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		 given().log().all()
				.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
			.when().log().all()
				.get("/public/v2/users").
			then().log().all()
				.assertThat()
					.statusCode(200)
					.body(matchesJsonSchemaInClasspath("getuserschema.json"));	
	}

}
