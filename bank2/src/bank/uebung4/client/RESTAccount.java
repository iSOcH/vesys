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
//		System.out.print("getOwner: ");
//		String response = resource.path("/"+number).accept(MediaType.TEXT_PLAIN_TYPE).get(String.class);
//		String[] infos = response.split("\n");
//		if(infos!=null && infos[0].contains("Owner")){
//			String[] details = infos[0].split(" ");
//			StringBuffer result = new StringBuffer();
//			for(int i=1;i<details.length;i++){
//				result.append(details[i]);
//			}
//			System.out.println(result);
//			return result.toString();
//		} else {
//			return null;
//		}
		
		Account realAcc = resource.path("/" + number).accept(MediaType.APPLICATION_JSON)
				.get(AccountData.class);
		System.out.println("realAcc: " + realAcc.toString());
		return realAcc.getOwner();
	}

	@Override
	public boolean isActive() throws IOException {
		int status = resource.path("/"+number).head().getStatus();
		return status == 200;
	}

	@Override
	public void deposit(double amount) throws IOException,
			IllegalArgumentException, InactiveException {
		double balance = this.getBalance();
		resource.path("/"+number).type(MediaType.APPLICATION_JSON).put(balance + amount);
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		double balance = this.getBalance();
		resource.path("/"+number).type(MediaType.APPLICATION_JSON).put(balance - amount);
	}

	@Override
	public double getBalance() throws IOException {
		Account realAcc = resource.path("/"+number).accept(MediaType.APPLICATION_JSON).get(AccountData.class);
		return realAcc.getBalance();
	}

	@Override
	public boolean setActive(boolean active) throws IOException {
		//not implemented
		return false;
	}

}
