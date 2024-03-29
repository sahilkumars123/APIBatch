package JsonPathValidationTest;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;


public class JsonPathTest {
	
	
	@Test
	public void jsonpath() {
		
		RestAssured.baseURI = "http://ergast.com";
		
		Response response =  given()
			.when()
				.get("/api/f1/2017/circuits.json");
		
		
			String resposeData = response.asString();
			
			System.out.println("response data is:: "+resposeData);
			
			List<String> listOfCountries =  JsonPath.read(resposeData, "$..CircuitTable.Circuits[*].Location.country");
			
			System.out.println("list of counttries are:: "+listOfCountries);
			
			System.out.println(listOfCountries.size());
			
			for(String country : listOfCountries) {
				System.out.println(country);
			}
	}
	
	@Test
	public void getProducts() {
		
RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =  given()
			.when()
				.get("/products");
		
		
			String resposeData = response.asString();
			
			System.out.println("response data is:: "+resposeData);
			
			List<String> listOfRates =  JsonPath.read(resposeData, "$[?(@.rating.rate < 3)].rating.rate");
			
			System.out.println("list of rates are:: "+listOfRates);
			
			
			System.out.println("--------------------------------");
	}
	
	
	@Test
	public void getJeweleryTitleAndPrice() {
		
RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =  given()
			.when()
				.get("/products");
		
		
			String resposeData = response.asString();
			
			
		List<Map<String, Object>> jewelleryList = JsonPath.read(resposeData,"$[?(@.category == 'jewelery')].[\"title\",\"price\"]");
		
		System.out.println(jewelleryList);
		
		
		for(Map<String, Object> jewellery : jewelleryList) {
			
			String title = (String) jewellery.get("title");
			Object price =  jewellery.get("price");
			
			System.out.println("title:: "+title+ " price:: "+price);
			
			System.out.println("----------------------------------");
		}
		
	}
	
	
//	a small homework ...need JQL queries for these statements :
//
//		1. fetch title of products with rating rate >=4.5
//		2. fetch title and jewellery category of the products where rate is between 2 and 4
	//	//$[?(@.category == 'jewelery' && @.rating.rate >=2 && @.rating.rate<=4)].[title,category,id]
//		3. fetch title and women's clothing category where rating count >=100 and price < 10$
	//$[?(@.category == "women's clothing" && @.rating.count>=100 && @.price<10)].[title,category]
	


}
