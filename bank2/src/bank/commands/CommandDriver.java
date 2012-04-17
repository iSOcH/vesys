package bank.commands;

import bank.BankDriver;

public interface CommandDriver extends BankDriver {
	public ReturnValue executeCommand(Command cmd);
}