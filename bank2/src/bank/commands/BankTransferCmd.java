package bank.commands;

import bank.Account;
import bank.BankDriver;

public class BankTransferCmd extends AbstractCmd implements Command {
	private String aNumber, bNumber;
	private double amount;

	public BankTransferCmd(String aNumber, String bNumber, double amount) {
		super();
		this.aNumber = aNumber;
		this.bNumber = bNumber;
		this.amount = amount;
	}

	@Override
	public void execute(BankDriver driver) {
		try {
			Account a, b;
			a = driver.getBank().getAccount(aNumber);
			b = driver.getBank().getAccount(bNumber);
			driver.getBank().transfer(a, b, amount);
		} catch (Exception e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}