package GETAPICalls;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllProductsUsingNonBDD {

	@Test
	public void getAllProducts() {
		

		RestAssured.baseURI = "https://gorest.co.in";

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d");

		// response

		Response response = request.get("/public/v2/users");

		int statusCode = response.getStatusCode();
		System.out.println("Status code is:: " + statusCode);

		String responseLine = response.statusLine();
		System.out.println("Status line is:: " + responseLine);

		// print body
		String responseBody = response.prettyPrint();
		System.out.println("body is:: " + responseBody);

		String header = response.header("Connection");
		System.out.println("connection response header :: " + header);

		List<Header> listOfAllHeaders = response.headers().asList();

		System.out.println("size of all headers is:: " + listOfAllHeaders.size());

		for (Header h : listOfAllHeaders) {
			System.out.println(h.getName() + " " + h.getValue());
		}

	}

	@Test
	public void getAllProductsUsingQueryParams() {

		RestAssured.baseURI = "https://gorest.co.in";

		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer 53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d");
		request.queryParam("name", "sahil");
		// response

		Response response = request.get("/public/v2/users");

		int statusCode = response.getStatusCode();
		System.out.println("Status code is:: " + statusCode);

		String responseLine = response.statusLine();
		System.out.println("Status line is:: " + responseLine);

		// print body
		String responseBody = response.prettyPrint();
		System.out.println("body is:: " + responseBody);

		String header = response.header("Connection");
		System.out.println("connection response header :: " + header);

		List<Header> listOfAllHeaders = response.headers().asList();

		System.out.println("size of all headers is:: " + listOfAllHeaders.size());

		for (Header h : listOfAllHeaders) {
			System.out.println(h.getName() + " " + h.getValue());
		}

	}

}
