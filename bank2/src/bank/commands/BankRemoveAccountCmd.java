package bank.commands;

import java.io.IOException;

import bank.BankDriver;

public class BankRemoveAccountCmd extends AbstractCmd implements Command {
	private String number;
	
	public BankRemoveAccountCmd(String number) {
		super();
		this.number = number;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			retval.setResult(driver.getBank().removeAccount(number));
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}