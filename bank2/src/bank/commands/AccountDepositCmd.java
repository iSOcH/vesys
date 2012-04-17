package bank.commands;

import bank.Account;
import bank.BankDriver;
import bank.InactiveException;

public class AccountDepositCmd extends AbstractCmd {
	private String accNumber;
	private double amount;
	
	public AccountDepositCmd(String accountNumber, double amount) {
		super();
		this.accNumber = accountNumber;
		this.amount = amount;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			Account account = driver.getBank().getAccount(accNumber);
			if (account == null || !account.isActive()) {
				throw new InactiveException();
			} else {
				account.deposit(amount);
			}
		} catch (Exception e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}