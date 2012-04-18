package bank.uebung4;

import java.io.IOException;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;

public class AccountData implements Account {
	private String number;
	private String owner;
	private double balance;

	private boolean active;
	
	public AccountData() {}
	
	public AccountData(Account original) throws IOException {
		number = original.getNumber();
		owner = original.getOwner();
		balance = original.getBalance();
		active = original.isActive();
	}
	
	@Override
	public String getNumber() throws IOException {
		return number;
	}

	@Override
	public String getOwner() throws IOException {
		return owner;
	}

	@Override
	public boolean isActive() throws IOException {
		return active;
	}

	@Override
	public void deposit(double amount) throws IOException,
			IllegalArgumentException, InactiveException {
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {

	}

	@Override
	public double getBalance() throws IOException {
		return balance;
	}

	@Override
	public boolean setActive(boolean active) throws IOException {
		return false;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
