package bank.commands;

import java.io.IOException;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;

public class CommandAccount implements Account {
	private CommandDriver driver;
	
	// needed for identifying the account, is used for the requests
	private String number;
	
	public CommandAccount(CommandDriver driver, String number) {
		this.driver = driver;
		this.number = number;
	}
	
	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public String getOwner() throws IOException {
		Command cmd = new AccountGetOwnerCmd(number);
		ReturnValue retval = driver.executeCommand(cmd);
		//System.out.println("getOwner called on " + this + " owner: " + retval.getResult());
		if (retval.isOK()) {
			return (String) retval.getResult();
		} else {
			throw (IOException) retval.getException();
		}
	}

	@Override
	public boolean isActive() throws IOException {
		Command cmd = new AccountIsActiveCmd(number);
		ReturnValue retval = driver.executeCommand(cmd);
		if (retval.isOK()) {
			return (Boolean) retval.getResult();
		} else {
			throw (IOException) retval.getException();
		}
	}

	@Override
	public void deposit(double amount) throws IOException,
			IllegalArgumentException, InactiveException {
		//System.out.println("deposit called on " + this + " amount:" + amount);
		Command cmd = new AccountDepositCmd(getNumber(), amount);
		ReturnValue retval = driver.executeCommand(cmd);
		if (!retval.isOK()) {
			Exception e = retval.getException();
			if (e instanceof IOException) {
				throw (IOException) e;
			} else if (e instanceof IllegalArgumentException) {
				throw (IllegalArgumentException) e;
			} else {
				throw (InactiveException) e;
			}
		}
	}

	@Override
	public void withdraw(double amount) throws IOException,
			IllegalArgumentException, OverdrawException, InactiveException {
		//System.out.println("withdraw called on " + this);
		Command cmd = new AccountWithdrawCmd(getNumber(), amount);
		ReturnValue retval = driver.executeCommand(cmd);
		
		if (!retval.isOK()) {
			Exception e = retval.getException();
			if (e instanceof IllegalArgumentException) {
				throw (IllegalArgumentException) e;
			} else if (e instanceof IOException) {
				throw (IOException) e;
			} else if (e instanceof OverdrawException) {
				throw (OverdrawException) e;
			} else {
				throw (InactiveException) e;
			}
		}
	}

	@Override
	public double getBalance() throws IOException {
		Command cmd = new AccountGetBalanceCmd(getNumber());
		ReturnValue retval = driver.executeCommand(cmd);
		if (retval.isOK()) {
			return (Double) retval.getResult();
		} else {
			throw (IOException) retval.getException();
		}
	}

	@Override
	public boolean setActive(boolean active) throws IOException {
		Command cmd = new AccountSetActiveCmd(getNumber(), active);
		ReturnValue retval = driver.executeCommand(cmd);
		if (retval.isOK()) {
			return (Boolean) retval.getResult();
		} else {
			throw (IOException) retval.getException();
		}
	}
}