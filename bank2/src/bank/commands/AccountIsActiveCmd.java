package bank.commands;

import java.io.IOException;

import bank.Account;
import bank.BankDriver;

public class AccountIsActiveCmd extends AbstractCmd {
	private String number;
	
	public AccountIsActiveCmd(String number) {
		super();
		this.number = number;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			Account acc = driver.getBank().getAccount(number);
			if (acc == null) {
				retval.setResult(false);
			} else {
				retval.setResult(acc.isActive());
			}
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}