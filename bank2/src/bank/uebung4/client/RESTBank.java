package bank.uebung4.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
		int status = resp.getStatus();
		if(status == 200){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<String> getAccountNumbers() throws IOException {
		System.out.print("getAccountNumbers: ");
		String response = resource.accept(MediaType.TEXT_PLAIN_TYPE).get(String.class);
		System.out.println(response);
		Set<String> set = new TreeSet<String>();
		for(String s : response.split("/n")){
			if(!"".equals(s)) set.add(s); System.out.println(s);
		}
		return set;
	}

	@Override
	public Account getAccount(String number) throws IOException {
		System.out.print("getAccount: ");
		WebResource res = client.resource(number.replace("\n", ""));
		String response = res.accept(MediaType.TEXT_PLAIN_TYPE).get(String.class);
		String[] infos = response.split("\n");
		if(infos!=null && infos.length == 4){
			String[] details = infos[1].split(" ");
			StringBuffer result = new StringBuffer();
			for(int i=1;i<details.length;i++){
				result.append(details[i]);
			}
			System.out.println(result);
			return new RESTAccount(result.toString(),res);
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
