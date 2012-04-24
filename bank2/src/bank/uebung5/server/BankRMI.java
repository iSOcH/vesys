package bank.uebung5.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import java.util.TreeSet;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;
import bank.local.LocalBank;
import bank.uebung5.BankRemote;

public class BankRMI extends UnicastRemoteObject implements BankRemote {

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
		// has to be Serializible
		return new TreeSet<String>(realBank.getAccountNumbers());
	}

	public Account getAccount(String number) throws IOException {
		Account realAcc = realBank.getAccount(number);
		
		if (realAcc != null) {
			return new AccountRMI(realAcc);
		} else {
			return null;
		}
	}

	public void transfer(Account a, Account b, double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException {
		realBank.transfer(a, b, amount);
	}

}
