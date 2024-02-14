package com.product.user
;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserAPITest {
	
	
	public static String generateRandomEmail() {
		
		return "apiautomationtesting"+System.currentTimeMillis()+"@gmail.com";
	}

	@Test
	public void createUser() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		User user = new User("sahil", generateRandomEmail(), "male", "active");
		
		//serliazation
		Response response =  RestAssured.given()
						.contentType(ContentType.JSON)
						.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
						.body(user)
						.when()
						.post("/public/v2/users");
		
		
		Integer userID = response.jsonPath().get("id");
		
		System.out.println("userid is :: "+userID);
		
		//get call
		
		Response getResonse =  RestAssured.given().log().all()
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when().log().all()
					.get("/public/v2/users/"+userID);
		
		getResonse.prettyPrint();
		
		
		//de-serliazation
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			User userRes = mapper.readValue(getResonse.getBody().asString(), User.class);
			
			System.out.println("id:: "+userRes.getId() + " name:: "+userRes.getName()+" email"+userRes.getEmail());
			
			Assert.assertEquals(userID, userRes.getId());
			Assert.assertEquals(user.getName(), userRes.getName());
			Assert.assertEquals(user.getGender(), userRes.getGender());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
