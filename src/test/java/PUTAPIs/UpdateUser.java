package PUTAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class UpdateUser {
	
	//post request - create a user -- id
	//put request - update a user
	//get request - checking user has been updated or not

	public static String randomemailgenerator() {
		
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	
	//post request
	@Test
	public void updateuser() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		User user = new User("sahil", randomemailgenerator(), "male", "active");
		
		
		Response response =	RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
				.body(user)
				.when()
				.post("/public/v2/users");
//				.then()
//				.extract()
//				.path("id");
		
	 JsonPath jsonUser =	response.jsonPath();
	 
	int userID = jsonUser.get("id");
		
		System.out.println("userid is :: "+userID);
		
		System.out.println("-----------------------");
		
		
		//put request
		
		user.setName("sahil kumar");
		user.setStatus("inactive");
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
		.body(user)
		.when()
		.put("/public/v2/users/"+userID)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(userID))
		.and()
		.body("name", equalTo(user.getName()));
		
		//get request
		
				
		
	}
	

}
