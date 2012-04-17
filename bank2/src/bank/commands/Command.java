package bank.commands;

import java.io.Serializable;

import bank.BankDriver;

public interface Command extends Serializable {
	public void execute(BankDriver driver);
	public ReturnValue getResult();
}
