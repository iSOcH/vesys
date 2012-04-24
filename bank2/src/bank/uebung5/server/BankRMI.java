package bank.uebung5.server;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;
import bank.local.LocalBank;

public class BankRMI extends UnicastRemoteObject implements Bank, Remote {

	private Bank realBank = new LocalBank();
	
	public BankRMI() throws RemoteException {
		super(1077);
	}

	public String createAccount(String owner) throws IOException {
		return realBank.createAccount(owner);
	}

	public boolean removeAccount(String number) throws IOException {
		return realBank.removeAccount(number);
	}

	public Set<String> getAccountNumbers() throws IOException {
		return realBank.getAccountNumbers();
	}

	// FIXME: i dont think this works
	public Account getAccount(String number) throws IOException {
		return realBank.getAccount(number);
	}

	public void transfer(Account a, Account b, double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException {
		realBank.transfer(a, b, amount);
	}
	
	

}
