package bank.uebung4.client;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;

public class RESTBank implements Bank {
	WebResource resource;
	
	public RESTBank(WebResource resource) {
		this.resource = resource;
	}

	@Override
	public String createAccount(String owner) throws IOException {
		System.out.print("createAccount: ");
		ClientResponse resp = resource.type(MediaType.TEXT_PLAIN).post(ClientResponse.class, owner);
		String[] result = resp.getHeaders().get("Content-Location").get(0).split("/");
		System.out.println(result[result.length-1]);
		return result[result.length-1];
	}

	@Override
	public boolean removeAccount(String number) throws IOException {
		ClientResponse resp = resource.path(number).delete(ClientResponse.class);
		return resp.getStatus() == 200;
	}

	@Override
	public Set<String> getAccountNumbers() throws IOException {
		return resource.accept(MediaType.APPLICATION_JSON).get(TreeSet.class);
	}

	@Override
	public Account getAccount(String number) throws IOException {
		if (getAccountNumbers().contains(number)) {
			return new RESTAccount(number, resource);
		} else {
			return null;
		}
	}

	@Override
	public void transfer(Account a, Account b, double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException {
		if (a == null || b == null) throw new IllegalArgumentException();
		if (amount < 0) throw new IllegalArgumentException();
		if (a.isActive() && b.isActive()) {
			ClientResponse response = resource.path(a.getNumber()+"/transfer/"+b.getNumber())
				.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, amount);
			switch (response.getStatus()) {
			case 409:
				throw new OverdrawException();
			case 406:
				throw new IllegalArgumentException();
			case 404:
				throw new InactiveException();
			case 200:
				// everything was OK :)
				break;
			default:
				throw new IOException();
			}
		} else {
			throw new InactiveException();
		}
	}
}
