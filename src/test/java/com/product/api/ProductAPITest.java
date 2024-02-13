package com.product.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {
	//Deserialzation
	@Test
	public void productTest() {
		
    RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =  given()
			.when()
				.get("/products");
		
		//json
		
		//json to pojo
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
		 Product[] product = mapper.readValue(response.getBody().asString(), Product[].class);
		 
		 
		 for(Product p : product) {
				System.out.println("ID:: "+p.getId());
				System.out.println("Title:: "+p.getTitle());
				System.out.println("Price:: "+p.getPrice());
				System.out.println("Description:: "+p.getDescription());
				System.out.println("Category:: "+p.getCategory());
				System.out.println("Image:: "+p.getImage());
				System.out.println("Rating Rate:: "+p.getRating().getRate());
				System.out.println("Count Rate:: "+p.getRating().getCount());

				System.out.println("------------------------------------");
			}
		
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Test
	public void productTest_WithLombokAPITest() {
		
	    RestAssured.baseURI = "https://fakestoreapi.com";
			
			Response response =  given()
				.when()
					.get("/products");
			
			//json
			
			//json to pojo
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				
			 ProductLombok[] product = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
			 
			 
			 for(ProductLombok p : product) {
					System.out.println("ID:: "+p.getId());
					System.out.println("Title:: "+p.getTitle());
					System.out.println("Price:: "+p.getPrice());
					System.out.println("Description:: "+p.getDescription());
					System.out.println("Category:: "+p.getCategory());
					System.out.println("Image:: "+p.getImage());
					System.out.println("Rating Rate:: "+p.getRating().getRate());
					System.out.println("Count Rate:: "+p.getRating().getCount());

					System.out.println("------------------------------------");
				}
			
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
