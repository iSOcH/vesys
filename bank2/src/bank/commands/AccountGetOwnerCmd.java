package bank.commands;

import java.io.IOException;

import bank.Account;
import bank.BankDriver;

public class AccountGetOwnerCmd extends AbstractCmd implements Command {
	private String number;
	
	public AccountGetOwnerCmd(String number) {
		super();
		this.number = number;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			Account acc = driver.getBank().getAccount(number);
			retval.setResult(acc.getOwner());
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}