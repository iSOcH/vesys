package bank.uebung3.srv;

import java.io.IOException;
import java.util.Set;

import javax.jws.WebParam;
import javax.jws.WebService;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;
import bank.uebung3.client.WSAccount;

@WebService
public interface BankService {
	// "[...] is an interface, and JAXB can't handle interfaces" damn
	// public ReturnValue executeCommand(@WebParam(name = "cmd")Command cmd);
	
	String createAccount(@WebParam(name = "owner") String owner) throws IOException;
	
	boolean removeAccount(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException;
	
	Set<String> getAccountNumbers() throws IOException;
	
	boolean accountExists(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException;
	
	void transfer(
			@WebParam(name = "accA") String accountANumber,
			@WebParam(name = "accB") String accountBNumber,
			@WebParam(name = "amount") double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException;

	String getOwner(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException;

	boolean isActive(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException;

	void deposit(
			@WebParam(name = "accountNumber") String accountNumber,
			@WebParam(name = "amount") double amount)
			throws IOException, IllegalArgumentException, InactiveException;

	void withdraw(
			@WebParam(name = "accountNumber") String accountNumber,
			@WebParam(name = "amount") double amount)
			throws IOException, IllegalArgumentException, OverdrawException, InactiveException;

	double getBalance(@WebParam(name = "accountNumber") String accountNumber)
			throws IOException;

	boolean setActive(
			@WebParam(name = "accountNumber") String accountNumber,
			@WebParam(name = "active") boolean active)
			throws IOException;
}
