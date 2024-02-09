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
import pojo.CreateUser;

public class CreateUserTestWithPoJoTest {
	
	//https://gorest.co.in
	//"Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d"
	
	
	//create a user - post call - user id = 123
	//get user id --  123 - json data 200Ok, name
	
	
//	
//	{
//	    "name" : "sahil kumar",
//	    "email" : "sahil126456022657@gmail.com",
//	    "gender" : "male",
//	    "status" : "active"
//	}
//	
	
	
	
	
	//Step 1:: to create a user
	int userID;
	
//	@BeforeMethod
//	public void createUserId() {
//		
//	
//	}
	
	public static String generateRandomEmailID() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";	
	}

	@Test
	public void create_user() {
		
		
		RestAssured.baseURI = "https://gorest.co.in";
		CreateUser user = new CreateUser("sahil kumar", generateRandomEmailID(),"male", "active");
		
		userID = given().log().all()
				.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
				.contentType(ContentType.JSON)
				.body(user).
			when().log().all()
				.post("/public/v2/users").
			then().log().all()
				.extract()
				.path("id");
		
		System.out.println("user id is ===> "+userID);	
		
	//get call
	
	String userName = given().log().all()
		.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d").
	when().log().all()
		.get("/public/v2/users/"+userID).
	then().log().all()
		.assertThat()
		.statusCode(200)
		.body("id", equalTo(userID))
		.and()
		.body("name", equalTo(user.getName()))
		.and()
		.body("gender", equalTo(user.getGender()))
		.extract()
		.path("name");
	
	System.out.println("username is:: "+userName);
	
	Assert.assertEquals(userName, "sahil kumar");
		
	}
	

}
