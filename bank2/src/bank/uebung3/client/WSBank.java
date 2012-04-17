package bank.uebung3.client;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;
import bank.uebung3.client.jaxws.BankServiceImpl;
import bank.uebung3.client.jaxws.IOException_Exception;
import bank.uebung3.client.jaxws.IllegalArgumentException_Exception;
import bank.uebung3.client.jaxws.InactiveException_Exception;
import bank.uebung3.client.jaxws.OverdrawException_Exception;

public class WSBank implements Bank {
	private BankServiceImpl service;
	
	public WSBank(BankServiceImpl service) {
		this.service = service;
	}
	
	@Override
	public String createAccount(String owner) throws IOException {
		try {
			return service.createAccount(owner);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public boolean removeAccount(String number) throws IOException {
		try {
			return service.removeAccount(number);
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public Set<String> getAccountNumbers() throws IOException {
		try {
			// WTF? this should be a Set already...
			return new TreeSet<String>(service.getAccountNumbers());
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public Account getAccount(String number) throws IOException {
		try {
			if (service.accountExists(number)) {
				return new WSAccount(number, service);
			} else {
				return null;
			}
		} catch (IOException_Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public void transfer(Account a, Account b, double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException {
		try {
			service.transfer(a.getNumber(), b.getNumber(), amount);
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

}
