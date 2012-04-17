package bank.uebung4.client;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.WebResource;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;

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
		System.out.print("getOwner: ");
		String response = resource.path("/"+number).accept(MediaType.TEXT_PLAIN_TYPE).get(String.class);
		String[] infos = response.split("\n");
		if(infos!=null && infos[0].contains("Owner")){
			String[] details = infos[0].split(" ");
			StringBuffer result = new StringBuffer();
			for(int i=1;i<details.length;i++){
				result.append(details[i]);
			}
			System.out.println(result);
			return result.toString();
		} else {
			return null;
		}
	}

	@Override
	public boolean isActive() throws IOException {
		int stautus = resource.path("/"+number).head().getStatus();
		if(stautus == 200){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deposit(double amount) throws IOException,
			IllegalArgumentException, InactiveException {
		double balance = this.getBalance();
		resource.path("/"+number).type(MediaType.TEXT_PLAIN).put(balance + amount);
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		double balance = this.getBalance();
		resource.path("/"+number).type(MediaType.TEXT_PLAIN).put(balance - amount);
	}

	@Override
	public double getBalance() throws IOException {
		String response = resource.path("/"+number).accept(MediaType.TEXT_PLAIN_TYPE).get(String.class);
		String[] infos = response.split("\n");
		if(infos!=null && infos[2].contains("Balance")){
			String[] details = infos[2].split(" ");
			StringBuffer result = new StringBuffer();
			for(int i=1;i<details.length;i++){
				result.append(details[i]);
			}
			return Double.valueOf(result.toString());
		} else {
			return 0;
		}
	}

	@Override
	public boolean setActive(boolean active) throws IOException {
		//not implemented
		return false;
	}

}
