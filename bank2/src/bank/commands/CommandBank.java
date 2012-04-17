package bank.commands;

import java.io.IOException;
import java.net.Socket;
import java.util.Set;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;

public class CommandBank implements Bank {
	private CommandDriver driver;
	
	public CommandBank(CommandDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public String createAccount(String owner) throws IOException {
		Command cmd = new BankCreateAccountCmd(owner);
		ReturnValue retval = driver.executeCommand(cmd);
		if (retval.isOK()) {
			return (String) retval.getResult();
		} else {
			throw (IOException) retval.getException();
		}
	}

	@Override
	public boolean removeAccount(String number) throws IOException {
		Command cmd = new BankRemoveAccountCmd(number);
		ReturnValue retval = driver.executeCommand(cmd);
		if (retval.isOK()) {
			return (Boolean) retval.getResult();
		} else {
			throw (IOException) retval.getException();
		}
	}

	@Override
	public Set<String> getAccountNumbers() throws IOException {
		Command cmd = new BankGetAccountNumbersCmd();
		ReturnValue retval = driver.executeCommand(cmd);
		if (retval.isOK()) {
			return (Set<String>) retval.getResult();
		} else {
			throw (IOException) retval.getException();
		}
	}

	@Override
	public Account getAccount(String number) throws IOException {
		Command cmd = new BankGetAccountCmd(number);
		ReturnValue retval = driver.executeCommand(cmd);
		if (retval.isOK()) {
			if (retval.getResult() != null) {
				return new CommandAccount(driver, number);
			} else {
				return null;
			}
		} else {
			throw (IOException) retval.getException();
		}
		
		//return new SocketsAccount(driver, number);
	}

	@Override
	public void transfer(Account a, Account b, double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException {
		Command cmd = new BankTransferCmd(a.getNumber(), b.getNumber(), amount);
		ReturnValue retval = driver.executeCommand(cmd);
		if (!retval.isOK()) {
			Exception exception = retval.getException();
			if (exception instanceof IOException) {
				throw (IOException) exception;
			} else if (exception instanceof IllegalArgumentException) {
				throw (IllegalArgumentException) exception;
			} else if (exception instanceof OverdrawException) {
				throw (OverdrawException) exception;
			} else {
				throw (InactiveException) exception;
			}
		}
	}
}