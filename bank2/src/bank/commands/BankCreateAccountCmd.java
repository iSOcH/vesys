package bank.commands;

import java.io.IOException;

import bank.BankDriver;

public class BankCreateAccountCmd extends AbstractCmd {
	private String owner;
	
	public BankCreateAccountCmd(String owner) {
		super();
		this.owner = owner;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			retval.setResult(driver.getBank().createAccount(owner));
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}
