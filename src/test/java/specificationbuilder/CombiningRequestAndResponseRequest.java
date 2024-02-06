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

public class CombiningRequestAndResponseRequest {
	

	
public static RequestSpecification user_req_get_spec() {
		
		RequestSpecification requestSpec =	new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.addHeader("Authorization","Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
						.build();
		
		return requestSpec;
	}

public static ResponseSpecification get_res_spec_200_OK() {

	ResponseSpecification response_spec =	new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
			.build();
		
	return response_spec;
}
	

//given().log().all()
//.header("Authorization","Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d")
//	.when().log().all()
//		.get("/public/v2/users/6147907")
//			.then().log().all()
//				.assertThat()
//					.statusCode(200)
//						.and()
//							.contentType(ContentType.JSON)
//								.and()
//								 .header("Server", "cloudflare");	
	
	
	
	
	@Test
	public void get_user_request() {
		
		given().log().all()
			.spec(user_req_get_spec())
				.when().log().all()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.spec(get_res_spec_200_OK());

		
	}

}
