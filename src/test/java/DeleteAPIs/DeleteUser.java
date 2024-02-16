package DeleteAPIs;

import org.testng.annotations.Test;

import PUTAPIs.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class DeleteUser {

	
	//create a user - 201
	//delete a user - 204
	//get a user - 404
	
public static String randomemailgenerator() {
		
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	
	//post request
	@Test
	public void deleteuser() {
		
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
	
		
		//delete request
		RestAssured.given().log().all()
		.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
		.when()
		.delete("/public/v2/users/"+userID)
		.then().log().all()
		.assertThat()
		.statusCode(204);

		
		
		//get request	
		RestAssured.given().log().all()
		.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
		.when()
		.get("/public/v2/users/"+userID)
		.then().log().all()
		.assertThat()
		.statusCode(404)
		.and()
		.body("message", equalTo("Resource not found"));
		
		
	
	}
}
