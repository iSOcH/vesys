package bank.uebung5.server;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;

public class AccountRMI extends UnicastRemoteObject implements Remote, Account {
	private Account realAccount;

	public AccountRMI(Account realAccount) throws RemoteException {
		super(1077);
		this.realAccount = realAccount;
	}
	
	public String getNumber() throws IOException {
		return realAccount.getNumber();
	}

	public String getOwner() throws IOException {
		return realAccount.getOwner();
	}

	public boolean isActive() throws IOException {
		return realAccount.isActive();
	}

	public void deposit(double amount) throws IOException,
			IllegalArgumentException, InactiveException {
		realAccount.deposit(amount);
	}

	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		realAccount.withdraw(amount);
	}

	public double getBalance() throws IOException {
		return realAccount.getBalance();
	}

	public boolean setActive(boolean active) throws IOException {
		return realAccount.setActive(active);
	}
	
	

}
