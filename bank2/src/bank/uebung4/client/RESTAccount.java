package bank.uebung4.client;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.WebResource;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;
import bank.uebung4.AccountData;

public class RESTAccount implements Account{
	private String number;
	private WebResource resource;

	public RESTAccount(String number, WebResource resource){
		this.number = number;
		this.resource = resource;
	}
	
	@Override
	public String getNumber() throws IOException {
		System.out.println("getNumber: "+number);
		return number;
	}

	@Override
	public String getOwner() throws IOException {
		Account realAcc = resource.path(number).accept(MediaType.APPLICATION_JSON)
				.get(AccountData.class);
		System.out.println("realAcc: " + realAcc.toString());
		return realAcc.getOwner();
	}

	@Override
	public boolean isActive() throws IOException {
		int status = resource.path(number).head().getStatus();
		return status == 200;
	}

	@Override
	public void deposit(double amount) throws IOException,
			IllegalArgumentException, InactiveException {
		double balance = this.getBalance();
		resource.path(number).type(MediaType.APPLICATION_JSON).put(balance + amount);
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		double balance = this.getBalance();
		resource.path(number).type(MediaType.APPLICATION_JSON).put(balance - amount);
	}

	@Override
	public double getBalance() throws IOException {
		Account realAcc = resource.path(number).accept(MediaType.APPLICATION_JSON).get(AccountData.class);
		return realAcc.getBalance();
	}

	@Override
	public boolean setActive(boolean active) throws IOException {
		//not implemented
		return false;
	}

}
