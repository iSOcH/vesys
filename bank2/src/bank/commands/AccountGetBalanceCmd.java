package bank.commands;

import java.io.IOException;

import bank.Account;
import bank.BankDriver;

public class AccountGetBalanceCmd extends AbstractCmd {
	private String number;
	
	public AccountGetBalanceCmd(String number) {
		super();
		this.number = number;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			Account account = driver.getBank().getAccount(number);
			if (account != null) {
				retval.setResult(account.getBalance());
			} else {
				// according to tests, calling getBalance on an inactive
				// account should not throw an exception, but return 0
				retval.setResult(0.0);
			}
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}