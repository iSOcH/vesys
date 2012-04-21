package bank.uebung4.client;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.UniformInterfaceException;
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
		return number;
	}

	@Override
	public String getOwner() throws IOException {
		Account realAcc = resource.path(number).accept(MediaType.APPLICATION_JSON)
				.get(AccountData.class);
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
		try {
			double balance = this.getBalance();
			resource.path(number).type(MediaType.APPLICATION_JSON).put(balance + amount);
		} catch (UniformInterfaceException e) {
			try {
				handleUniformInterfaceException(e);
			} catch (OverdrawException e1) {
				// this should not happen here
				throw new IOException(e1);
			}
		}
		
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		try {
			double balance = this.getBalance();
			resource.path(number).type(MediaType.APPLICATION_JSON).put(balance - amount);
		} catch (UniformInterfaceException e) {
			handleUniformInterfaceException(e);
		}
	}

	@Override
	public double getBalance() throws IOException {
		try {
			Account realAcc = resource.path(number).accept(MediaType.APPLICATION_JSON).get(AccountData.class);
			return realAcc.getBalance();
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 404) {
				// this means this account does not exist (anymore) on the server
				return 0;
			} else {
				// should not happen
				throw new IOException(e);
			}
		}
	}

	@Override
	public boolean setActive(boolean active) throws IOException {
		//not implemented
		return false;
	}
	
	/**
	 * used for deposit and withdraw to reduce code-redundancy
	 * @param exception
	 * @throws OverdrawException
	 * @throws InactiveException
	 * @throws IOException
	 */
	private void handleUniformInterfaceException(UniformInterfaceException exception) throws OverdrawException, InactiveException, IOException {
		switch (exception.getResponse().getStatus()) {
		case 404:
			throw new InactiveException();
		case 409:
			throw new OverdrawException();
		case 406:
			throw new IllegalArgumentException();
		default:
			throw new IOException();
		}
	}

}
