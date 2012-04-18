package bank.uebung4.srv;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import com.sun.jersey.api.json.JSONConfiguration;

public class BankServer {
	public static void main(String[] args) throws IOException {

		final String baseUri = "http://localhost:9998/";
		final Map<String, String> config = new HashMap<String, String>();

		// config.put("com.sun.jersey.config.property.packages",
		// "ch.fhnw.imvs.msg.resources"); // package with resource classes

		config.put("javax.ws.rs.Application", BankApplication.class.getName());
		config.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
		System.out.println("Starting grizzly...");
		GrizzlyWebContainerFactory.create(baseUri, config);

		System.out.println("Jersey app started with WADL available at "
				+ baseUri + "application.wadl\n");
	}
}
