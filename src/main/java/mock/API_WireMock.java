package mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class API_WireMock {
	
	public static void getDummyUser() {
		
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
					.withStatus(200)
					.withHeader("Content-Type", "application/json")
					.withBody(" {\r\n"
							+ "	\"name\" : \"sahil\"\r\n"
							+ " }")
						)
				);
	}

}
