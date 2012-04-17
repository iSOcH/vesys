package bank.commands;

import java.io.IOException;

import bank.BankDriver;

public class BankGetAccountCmd extends AbstractCmd {
	private String number;
	
	public BankGetAccountCmd(String number) {
		super();
		this.number = number;
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			//retval.setResult(driver.getBank().getAccount(number));
			
			// this is somewhat special, we dont send the account, but the number of the
			// account back. if the account does not exist, we return null.
			// this allows the Client-Side bank.getAccount(String)-Method
			// to return null, if the account does not exist on the server side.
			// otherwise bank.getAccount(String) would have to always return an
			// account or Account would need to be Serializable
			if (driver.getBank().getAccount(number) != null) {
				retval.setResult(number);
			} else {
				retval.setResult(null);
			}
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}