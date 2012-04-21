package bank.uebung4.client;

import java.io.IOException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import bank.Bank;
import bank.BankDriver;

public class RESTDriver implements BankDriver{
	private WebResource resource;
	private Bank bank;
	
	@Override
	public void connect(String[] args) throws IOException {
		ClientConfig config = new DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
		
		Client c = Client.create(config);
		resource = c.resource("http://localhost:9998/bank/accounts");
		bank = new RESTBank(resource);
		
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
		resource = null;
	}

	@Override
	public Bank getBank() {
		return bank;
	}

}
