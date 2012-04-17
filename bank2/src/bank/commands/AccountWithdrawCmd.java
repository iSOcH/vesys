package bank.commands;

import bank.Account;
import bank.BankDriver;
import bank.InactiveException;

public class AccountWithdrawCmd extends AbstractCmd {
	private String accNumber;
	private double amount;
	
	public AccountWithdrawCmd(String number, double amount) {
		this.accNumber = number;
		this.amount = amount;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			Account account = driver.getBank().getAccount(accNumber);
			if (account == null || !account.isActive()) {
				throw new InactiveException();
			} else {
				account.withdraw(amount);
			}
		} catch (Exception e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}