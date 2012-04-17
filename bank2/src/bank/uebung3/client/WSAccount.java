package bank.uebung3.client;

import java.io.IOException;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;
import bank.uebung3.client.jaxws.BankServiceImpl;
import bank.uebung3.client.jaxws.IOException_Exception;
import bank.uebung3.client.jaxws.IllegalArgumentException_Exception;
import bank.uebung3.client.jaxws.InactiveException_Exception;
import bank.uebung3.client.jaxws.OverdrawException_Exception;

public class WSAccount implements Account {
	private String number;
	private BankServiceImpl service;
	
	public WSAccount(String number, BankServiceImpl service) {
		this.number = number;
		this.service = service;
	}
	
	@Override
	public String getNumber() throws IOException {
		return number;
	}

	@Override
	public String getOwner() throws IOException {
		try {
			return service.getOwner(number);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public boolean isActive() throws IOException {
		try {
			return service.isActive(number);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deposit(double amount) throws IOException,
			IllegalArgumentException, InactiveException {
		try {
			service.deposit(number, amount);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		} catch (IllegalArgumentException_Exception e) {
			throw new IllegalArgumentException(e);
		} catch (InactiveException_Exception e) {
			throw new InactiveException(e);
		}
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		try {
			service.withdraw(number, amount);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		} catch (IllegalArgumentException_Exception e) {
			throw new IllegalArgumentException(e);
		} catch (InactiveException_Exception e) {
			throw new InactiveException(e);
		} catch (OverdrawException_Exception e) {
			throw new OverdrawException(e);
		}
	}

	@Override
	public double getBalance() throws IOException {
		try {
			return service.getBalance(number);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public boolean setActive(boolean active) throws IOException {
		try {
			return service.setActive(number, active);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

}
