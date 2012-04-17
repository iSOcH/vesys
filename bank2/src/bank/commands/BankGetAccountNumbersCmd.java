package bank.commands;

import java.io.IOException;
import java.util.TreeSet;

import bank.BankDriver;

public class BankGetAccountNumbersCmd extends AbstractCmd implements Command {
	
	public BankGetAccountNumbersCmd() {
		super();
	}
	
	@Override
	public void execute(BankDriver driver) {
		try {
			// the set we get from getAccountNumbers() may not be serializable
			retval.setResult(new TreeSet<String>(driver.getBank().getAccountNumbers()));
		} catch (IOException e) {
			retval.setOK(false);
			retval.setException(e);
		}
	}
}