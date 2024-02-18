package multibody;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyAPITest {

	@Test
	public void bodyWithTextTest() {

		RestAssured.baseURI = "http://httpbin.org";
		String payload = "this is sahil";

		Response response = RestAssured.given().contentType(ContentType.TEXT).body(payload).when().post("/post");
//		.then()
//		 	.assertThat()
//		 		.statusCode(200);

		response.prettyPrint();
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
	}

	@Test
	public void bodyWithJavaScriptTest() {

		RestAssured.baseURI = "http://httpbin.org";
		String payload = "function add(){\r\n" + "    let x = 10;\r\n" + "    let y = 20;\r\n" + "\r\n"
				+ "    let z = x+y;\r\n" + "}";

		Response response = RestAssured.given().header("Content-Type", "application/javascript").body(payload).when()
				.post("/post");
//		.then()
//		 	.assertThat()
//		 		.statusCode(200);

		response.prettyPrint();
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
	}

	@Test
	public void bodyWithHTMLTest() {

		RestAssured.baseURI = "http://httpbin.org";
		String payload = "<!DOCTYPE html>\r\n"
				+ "\r\n"
				+ "<html dir=\"ltr\" lang=\"en\">\r\n"
				+ "<!--<![endif]-->\r\n"
				+ "\r\n"
				+ "</html>";

		Response response = RestAssured.given()
				.contentType(ContentType.HTML)
				.body(payload)
				.when()
				.post("/post");
//		.then()
//		 	.assertThat()
//		 		.statusCode(200);

		response.prettyPrint();
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
	}
	
	@Test
	public void bodyWithXMLTest() {

		RestAssured.baseURI = "http://httpbin.org";
		String payload = "<root>\r\n"
				+ "  <especially equator=\"join\">1009654980</especially>\r\n"
				+ "  <garage pitch=\"behavior\">\r\n"
				+ "    <radio>756798317.8517923</radio>\r\n"
				+ "    <just record=\"whole\">398931292.735343</just>\r\n"
				+ "  </garage>\r\n"
				+ "  <drive>2002709990.903205</drive>\r\n"
				+ "</root>";

		Response response = RestAssured.given()
				.contentType(ContentType.XML)
				.body(payload)
				.when()
				.post("/post");
//		.then()
//		 	.assertThat()
//		 		.statusCode(200);

		response.prettyPrint();
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
	}
	
	@Test
	public void bodyWithMultiPartFormDataTest() {

		RestAssured.baseURI = "http://httpbin.org";
		
		Response response = RestAssured.given()
				.contentType(ContentType.MULTIPART)
				.multiPart("file", new File("C:/Users/SAHIL/Downloads/SahilQA_5.5Yrs (1).pdf"))
				.multiPart("name", "sahil")
				.when()
				.post("/post");
//		.then()
//		 	.assertThat()
//		 		.statusCode(200);

		response.prettyPrint();
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
	}
	
	
	@Test
	public void bodyWithBinaryDataTest() {

		RestAssured.baseURI = "http://httpbin.org";
		
		Response response = RestAssured.given()
				.contentType(ContentType.BINARY)
				.body(new File("C:/Users/SAHIL/Downloads/SahilQA_5.5Yrs (1).pdf"))
				.when()
				.post("/post");
//		.then()
//		 	.assertThat()
//		 		.statusCode(200);

		response.prettyPrint();
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
	}
	
	
	@Test
	public void bodyWithURLEncodedDataTest() {

		RestAssured.baseURI = "http://httpbin.org";
		
		Response response = RestAssured.given()
				.contentType(ContentType.URLENC)
				.param("name", "sahil")
				.when()
				.post("/post");
//		.then()
//		 	.assertThat()
//		 		.statusCode(200);

		response.prettyPrint();
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
	}
	
	
}
