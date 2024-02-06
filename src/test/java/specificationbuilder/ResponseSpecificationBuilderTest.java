package specificationbuilder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class ResponseSpecificationBuilderTest {
	
	
//	given().log().all()
//	.header("Authorization","Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
//		.when().log().all()
//			.get("/public/v2/users/6147907")
//				.then().log().all()
//					.assertThat()
//						.statusCode(200)
//							.and()
//								.contentType(ContentType.JSON)
//									.and()
//									 .header("Server", "cloudflare");	
	
	
	public static ResponseSpecification get_res_spec_200_OK() {

		ResponseSpecification response_spec =	new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
				.build();
			
		return response_spec;
	}
	
	
	public static ResponseSpecification get_res_spec_401_Fail() {

		ResponseSpecification response_spec =	new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(401)
			.expectHeader("Server", "cloudflare")
				.build();
			
		return response_spec;
	}
	
	
	public static ResponseSpecification get_res_spec_200_OK_With_Body() {

		ResponseSpecification response_spec =	new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
			.expectBody("$.size()", equalTo(10))
			.expectBody("id", hasSize(10))
			.expectBody("status", hasItem("active"))
			.build();
				
			
		return response_spec;
	}
	
	
	
	@Test
	public void get_user_res_spec_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_res_spec_200_OK());

	}
	
	@Test
	public void get_user_res_spec_Test_With_401_StatusCode() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.header("Authorization", "Bearer 8e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_res_spec_401_Fail());

	}
	
	@Test
	public void get_user_res_spec_WithBodyTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_res_spec_200_OK_With_Body());

	}
	

}
