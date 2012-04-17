package bank.local;

import java.io.IOException;
import java.io.Serializable;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;

public class LocalAccount implements Account {
	private String number;
	private String owner;
	private volatile boolean active = true;
	private volatile double balance = 0;

	public LocalAccount(String number, String owner) {
		this.number = number;
		this.owner = owner;
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

		if (amount < 0) throw new IllegalArgumentException();
		synchronized (this) {
			if (!active) throw new InactiveException();
			balance += amount;
		}
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		
		synchronized(this) {
			if (!active) throw new InactiveException();
			if (amount < 0) throw new IllegalArgumentException();
			if (amount > balance) throw new OverdrawException();
			balance -= amount;
		}
	}

	@Override
	public double getBalance() throws IOException {
		return balance;
	}

	@Override
	public boolean setActive(boolean active) {
		if (!active) {
			if (this.active) {
				if (balance == 0) {
					this.active = active;
					return true;
				} else {
					return false;
				}
			} else {
				// no change needed
				return false;
			}
		} else {
			if (this.active) {
				// no change
				return false;
			} else {
				this.active = active;
				return true;
			}
		}
	}
}