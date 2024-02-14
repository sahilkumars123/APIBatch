package com.pet.api;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetAPITest {
	
	@Test
	public void createPETAPITest() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		Category category = new Category(1,"Dog");
		
		List<String> photoURLs = Arrays.asList("https://www.dog1.com", "https://www.dog2.com");
		
		Tag tag1 = new Tag(12, "white");
		Tag tag2 = new Tag(13, "brown");
		
		List<Tag> tags = Arrays.asList(tag1, tag2);

		PetLombok pet = new PetLombok(300, category, "Ronney", photoURLs, tags, "avaialble");
		
		//serliazation
		Response response =  RestAssured.given()
					.contentType(ContentType.JSON)
					.body(pet)
					.when()
					.post("/v2/pet");
		
		response.prettyPrint();
		
		//de-serliazation
		
		ObjectMapper mapper = new ObjectMapper();
		
	try {
		PetLombok petResponse =	mapper.readValue(response.getBody().asString(), PetLombok.class);
		
		System.out.println(petResponse.getName() + " " + petResponse.getCategory().getName() + " "+petResponse.getTags() + " "+ petResponse.getPhotoUrls());
		
		
		Assert.assertEquals(pet.getId(), petResponse.getId());
		Assert.assertEquals(pet.getName(), petResponse.getName());
		Assert.assertEquals(pet.getCategory().getName(), petResponse.getCategory().getName());
		
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}

}
