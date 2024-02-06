package POSTAPICalls;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BookingAuthTest {
	
	
	@Test
	public void post_req_BookingAuthTokenTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenID = given()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "    \"username\"  : \"admin\",\r\n"
					+ "    \"password\" : \"password123\"\r\n"
					+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		
		
		System.out.println("tokenID is ----"+tokenID);	
	}
	
	
	@Test
	public void post_req_BookingAuthTokenTest_WithJSONFile() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenID = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resoruces/data/basicauth.json"))
			.when().log().all()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		
		
		System.out.println("tokenID is ----"+tokenID);	
		
		Assert.assertNotNull(tokenID);
	}

}
