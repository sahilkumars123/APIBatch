package org.api.mockingapi;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import mock.API_WireMock;
import mock.WireMockSetup;

public class MockGETAPITest {
	
	@BeforeTest
	public void setup() {
		WireMockSetup.startWireMockServer();
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8085;	
	}
	
	@Test
	public void getUser() {
		
		API_WireMock.getDummyUser();
		RestAssured.given().log().all()
		.when()
		.get("/api/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("name", equalTo("sahil"));
	}
	
	
	@AfterTest
	public void stopServer() {
		
		WireMockSetup.stoptWireMockServer();
	}
	
}
