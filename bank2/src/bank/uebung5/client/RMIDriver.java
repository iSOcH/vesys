package bank.uebung5.client;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import bank.Bank;
import bank.BankDriver;
import bank.uebung5.BankRemote;

public class RMIDriver implements BankDriver {
	private Bank bank;
	
	@Override
	public void connect(String[] args) throws IOException {
		try {
			Object foo = Naming.lookup("rmi://localhost/BankService");
			bank = (BankRemote) foo;
		} catch (NotBoundException e) {
			throw new IOException(e);
		}

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
