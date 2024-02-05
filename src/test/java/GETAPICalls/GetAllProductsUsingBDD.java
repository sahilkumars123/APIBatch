package GETAPICalls;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GetAllProductsUsingBDD {
	
	
	@Test
	public void getAllProducts() {
		
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.when().log().all()
				.get("/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.header("connection", "keep-alive")
										.and()
											.body("$.size", equalTo(20));
												
		
	
	}
	
	@Test
	public void getAllProductsWithHeader() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
	given().log().all()
		.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
			.when().log().all()
				.get("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.header("connection", "keep-alive")
										.and()
											.body("$.size", equalTo(10));
												
		
	}
	
	
	@Test
	public void getAllProductsWithHeaderWithQueryParam() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
	given().log().all()
		.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
			.queryParam("name", "sahil")	
				.when().log().all()
				.get("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.header("connection", "keep-alive")
										.and()
											.body("$.size", equalTo(10));
												
		
	}
	
	
	@Test
	public void getProductData_With_Extract_Body() {
		
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		 Response response =  given().log().all()
				 					.when().log().all()
				 						.get("/products");
		 
		 
		 JsonPath js =  response.jsonPath();
		 
		 int firstProductId = js.getInt("[0].id");
		 
		 System.out.println("id is:: "+firstProductId);
		 
		 String firstProductTitle = js.getString("[0].title");
		 
		 System.out.println("title is:: "+firstProductTitle);
		 
		 float secondProductPrice =  js.getFloat("[1].price");
		 
		 System.out.println("price is :: "+secondProductPrice);
		 
		 Assert.assertEquals(secondProductPrice, 22.3);
		
	}
	
	
	@Test
	public void getProductData_With_Extract_Body_WithJsonArray() {
		
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		 Response response =  given().log().all()
				 					.when().log().all()
				 						.get("/products");
		 
		 
		 JsonPath js =  response.jsonPath();
		 
		 List<Integer> listOfIds =js.getList("id");
		 
		 System.out.println("total number of ids:: "+listOfIds.size());
		 
		 List<String> listOfTitles = js.getList("title");
		 
		 //List<Float> listOfPrices = js.getList("price", Float.class);
		 
		 List<Object> listOfPrices = js.getList("price");
		 
		 List<Object> listOfRates = js.getList("rating.rate");
		 
		 for(int i=0; i<listOfIds.size(); i++) {
			 
			int id = listOfIds.get(i);
			String title = listOfTitles.get(i);
			Object price = listOfPrices.get(i);
			Object rate =  listOfRates.get(i);
			
			System.out.println(" id:: "+id+ " title:: "+title+" price"+price+" rate:: "+rate);
		 }
	}
	
	@Test
	public void getProductData_With_Extract_Body_WithJsonObject() {
		
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response =  given().log().all()
		 	.header("Authorization","Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
				 .when().log().all()
				 	.get("/public/v2/users/6147907");
//				 		.then()
//				 			.extract()
//				 				.path("id");
		
		int id = response.then().extract().path("id");
		String email = response.then().extract().path("email");
		String status = response.then().extract().path("status");
		
		System.out.println("id is :: "+id);
		System.out.println("email is :: "+email);
		System.out.println("status is :: "+status);
		 }
	}
	
	
