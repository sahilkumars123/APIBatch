package POSTAPICalls;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CreateUserTest {
	
	//https://gorest.co.in
	//"Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d"
	
	
	//create a user - post call - user id = 123
	//get user id --  123 - json data 200Ok, name
	
	//Step 1:: to create a user
	int userID;
	
	@BeforeMethod
	public void createUserId() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		userID = given().log().all()
				.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
				.contentType(ContentType.JSON)
				.body(new File(".\\src\\test\\resoruces\\data\\createuser.json")).
			when().log().all()
				.post("/public/v2/users").
			then().log().all()
				.extract()
				.path("id");
		
		System.out.println("user id is ===> "+userID);		
	}
	
	@Test
	public void create_user() {
		
	//get call
	
	String userName = given().log().all()
		.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d").
	when().log().all()
		.get("/public/v2/users/"+userID).
	then().log().all()
		.assertThat()
		.statusCode(200)
		.body("id", equalTo(userID))
		.extract()
		.path("name");
	
	System.out.println("username is:: "+userName);
	
	Assert.assertEquals(userName, "sahil kumar");
		
	}
	

}
