package specificationbuilder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationTest {
	
	//content type
	//header
	//url
	
	
	public static RequestSpecification user_req_get_spec() {
		
		RequestSpecification requestSpec =	new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.addHeader("Authorization","Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
						.build();
		
		return requestSpec;
	}
	
public static RequestSpecification user_req_get_spec_WithQueryParam() {
		
		RequestSpecification requestSpec =	new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.addQueryParam("name", "rithica")
					.addQueryParam("status", "active")
					.addHeader("Authorization","Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
						.build();
		
		return requestSpec;
	}
	
	
	
	@Test
	public void user_req_get() {
		
		RestAssured.baseURI= "https://gorest.co.in";
		
//		given().log().all()
//			.header("Authorization","Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
//				.when().log().all()
//					.get("/public/v2/users/6147907")
//						.then().log().all()
//							.assertThat()
//								.statusCode(200)
//									.and()
//										.contentType(ContentType.JSON)
//											.and()
//											 .header("Server", "cloudflare");	
		
		
		
		given()
			.spec(user_req_get_spec())
				.when()
					.get("/public/v2/users/6147907")
						.then()
							.assertThat()
								.statusCode(200);

	}
	
	
	
	@Test
	public void user_req_get_withqueryparam() {

		given().log().all()
			.spec(user_req_get_spec_WithQueryParam())
				.when().log().all()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200);
		
	}
	

}
