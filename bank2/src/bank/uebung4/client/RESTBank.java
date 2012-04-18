package bank.uebung4.client;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;

public class RESTBank implements Bank {
	WebResource resource;
	Client client;
	
	public RESTBank(WebResource resource, Client client) {
		this.resource = resource;
		this.client = client;
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
		ClientResponse resp = resource.path("/"+number).delete(ClientResponse.class);
		return resp.getStatus() == 200;
	}

	@Override
	public Set<String> getAccountNumbers() throws IOException {
//		System.out.print("getAccountNumbers: ");
//		String response = resource.accept(MediaType.TEXT_PLAIN_TYPE).get(String.class);
//		System.out.println(response);
//		Set<String> set = new TreeSet<String>();
//		for(String s : response.split("/n")){
//			if(!"".equals(s)) set.add(s); System.out.println(s);
//		}
//		return set;
		
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
			// b.deposit is guaranteed to work now
			a.withdraw(amount);
			b.deposit(amount);
		} else {
			throw new InactiveException();
		}
		
	}

}
