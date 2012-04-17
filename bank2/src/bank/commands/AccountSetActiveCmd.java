package bank.commands;

import java.io.IOException;

import bank.BankDriver;

public class AccountSetActiveCmd extends AbstractCmd {
	private String number;
	private boolean active;
	
	public AccountSetActiveCmd(String number, boolean active) {
		super();
		this.number = number;
		this.active = active;
	}

	@Override
	public void execute(BankDriver driver) {
		try {
			retval.setResult(driver.getBank().getAccount(number).setActive(active));
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}