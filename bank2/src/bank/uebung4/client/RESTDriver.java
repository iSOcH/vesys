package bank.uebung4.client;

import java.io.IOException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import bank.Bank;
import bank.BankDriver;
import bank.uebung3.client.jaxws.BankServiceImpl;

public class RESTDriver implements BankDriver{
	private WebResource resource;
	private Bank bank;
	
	@Override
	public void connect(String[] args) throws IOException {
		Client c = Client.create();
		resource = c.resource("http://localhost:9998/bank/accounts");
		bank = new RESTBank(resource, c);
		
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
		resource = null;
	}

	@Override
	public Bank getBank() {
		// TODO Auto-generated method stub
		return bank;
	}

}
