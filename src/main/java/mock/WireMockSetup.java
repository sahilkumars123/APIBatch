package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

//wiremock

// http://localhost:8085/api/users

public class WireMockSetup {

	private static WireMockServer server;

	public static void startWireMockServer() {

		server = new WireMockServer(8085);
		configureFor("localhost", 8085);
		server.start();
	}

	public static void stoptWireMockServer() {
		server.stop();
	}
}
