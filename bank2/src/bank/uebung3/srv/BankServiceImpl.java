package bank.uebung3.srv;

import java.io.IOException;
import java.util.Set;

import javax.jws.WebParam;
import javax.jws.WebService;

import bank.Account;
import bank.BankDriver;
import bank.InactiveException;
import bank.OverdrawException;
import bank.local.LocalDriver;

@WebService
public class BankServiceImpl implements BankService {
	private BankDriver driver;
	
	public BankServiceImpl() {
		driver = new LocalDriver();

		// with the localdriver there shouldnt be a problem here
		try {
			driver.connect(null);
		} catch (IOException ignore) {}
	}
	
	public String createAccount(@WebParam(name = "owner") String owner) throws IOException {
		return driver.getBank().createAccount(owner);
	}
	
	public boolean removeAccount(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException {
		return driver.getBank().removeAccount(accountNumber);
	}
	
	public Set<String> getAccountNumbers() throws IOException {
		return driver.getBank().getAccountNumbers();
	}
	
	public boolean accountExists(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException {
		return driver.getBank().getAccount(accountNumber) != null;
	}
	
	public void transfer(
			@WebParam(name = "accA") String accountANumber,
			@WebParam(name = "accB") String accountBNumber,
			@WebParam(name = "amount") double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException {
		Account a = driver.getBank().getAccount(accountANumber);
		Account b = driver.getBank().getAccount(accountBNumber);
		driver.getBank().transfer(a, b, amount);
	}

	public String getOwner(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException {	
		Account acc = driver.getBank().getAccount(accountNumber);
		if (acc == null) {
			throw new IOException("Account not found");
		} else {
			return acc.getOwner();
		}
	}

	public boolean isActive(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException {
		Account acc = driver.getBank().getAccount(accountNumber);
		if (acc == null) {
			throw new IOException("Account not found");
		} else {
			return acc.isActive();
		}
	}

	public void deposit(
			@WebParam(name = "accountNumber") String accountNumber,
			@WebParam(name = "amount") double amount)
			throws IOException, IllegalArgumentException, InactiveException {
		Account acc = driver.getBank().getAccount(accountNumber);
		if (acc == null) {
			throw new InactiveException("Account not found");
		} else {
			acc.deposit(amount);
		}
	}

	public void withdraw(
			@WebParam(name = "accountNumber") String accountNumber,
			@WebParam(name = "amount") double amount)
			throws IOException, IllegalArgumentException, OverdrawException, InactiveException {
		Account acc = driver.getBank().getAccount(accountNumber);
		if (acc == null) {
			throw new InactiveException("Account not found");
		} else {
			acc.withdraw(amount);
		}
	}

	public double getBalance(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException {
		Account acc = driver.getBank().getAccount(accountNumber);
		if (acc == null) {
			// according to tests, this shouldnt throw an exception, but return 0
			//throw new IOException("Account not found");
			return 0.0;
		} else {
			return acc.getBalance();
		}
	}

	public boolean setActive(
			@WebParam(name = "accountNumber") String accountNumber,
			@WebParam(name = "active") boolean active)
			throws IOException {
		Account acc = driver.getBank().getAccount(accountNumber);
		if (acc == null) {
			throw new IOException("Account not found");
		} else {
			return acc.setActive(active);
		}
	}

}
