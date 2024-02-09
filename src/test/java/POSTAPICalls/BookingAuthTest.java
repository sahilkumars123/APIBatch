package POSTAPICalls;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.BookingAuth;

public class BookingAuthTest {
	
	
	// POJO - Plain Old Java Object
	// can not be inhertited
	//Encapsulation 
	//private variables
	// through public methods
	// setter getters
	
	//Step 1: We will create a POJO class
	// Step 2: We wil intialize variabbles of POJO class
	//Step 3: We will use the reference of pojo class in POST api body
	
	
	//serliazation - to convert java object(POJO) to other format :: json, media, json/xml, text, pdf etc
	//pojo to json - serliazation
	//json to pojo class - De-serliazation
	// add jackson dependency for serliazation
	
	
	//1. to paste the json object
	//2. to have json resource file
	//3 to have a pojo class object
	
	@Test
	public void post_req_BookingAuthTokenTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		BookingAuth bookingauth = new BookingAuth("admin","password123");
		
		String tokenID = given()
			.contentType(ContentType.JSON)
			.body(bookingauth)
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		
		
		System.out.println("tokenID is ----"+tokenID);	
	}
	
	
	@Test
	public void post_req_BookingAuthTokenTest_WithJSONFile() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenID = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resoruces/data/basicauth.json"))
			.when().log().all()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		
		
		System.out.println("tokenID is ----"+tokenID);	
		
		Assert.assertNotNull(tokenID);
	}

}
