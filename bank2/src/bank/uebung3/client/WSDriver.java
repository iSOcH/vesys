package bank.uebung3.client;

import java.io.IOException;

import bank.Bank;
import bank.BankDriver;
import bank.uebung3.client.jaxws.BankServiceImpl;
import bank.uebung3.client.jaxws.BankServiceImplService;

public class WSDriver implements BankDriver {
	private BankServiceImpl bankservice;
	private Bank bank;
	
	@Override
	public void connect(String[] args) throws IOException {
		BankServiceImplService service = new BankServiceImplService();
		bankservice = service.getBankServiceImplPort();
		bank = new WSBank(bankservice);
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
		bankservice = null;
	}

	@Override
	public Bank getBank() {
		return bank;
	}

}
