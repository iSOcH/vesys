package bank.local;

import java.io.IOException;

import bank.Bank;
import bank.BankDriver;

public class LocalDriver implements BankDriver {
	private Bank bank;

	@Override
	public void connect(String[] args) throws IOException {
		bank = new LocalBank();
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
	}

	@Override
	public Bank getBank() {
		return bank;
	}

}
