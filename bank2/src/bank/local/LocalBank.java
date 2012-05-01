package bank.local;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;

public class LocalBank implements Bank {
	private int nextID = 1;
	private Map<String, Account> accounts = new HashMap<String, Account>();
	
	@Override
	public String createAccount(String owner) throws IOException {
		if (owner == null || "".equals(owner)) return null;
		Account acc = new LocalAccount(String.valueOf(nextID++), owner);
		accounts.put(acc.getNumber().toString(), acc);
		return acc.getNumber();
	}

	@Override
	public boolean removeAccount(String number) throws IOException {
		Account acc = accounts.get(number);
		if (acc != null) {
			if (acc.getBalance() != 0) {
				return false;
			} else {
				accounts.remove(number);
				return acc.setActive(false);
			}
		} else {
			return false;
		}
	}

	@Override
	public Set<String> getAccountNumbers() throws IOException {
//		Set<String> result = new TreeSet<String>();
//		for (String s : accounts.keySet()) {
//			result.add(s);
//		}
//		return result;
		
		// inner classes not serializable
		return accounts.keySet();
	}

	@Override
	public Account getAccount(String number) throws IOException {
		return accounts.get(number);
	}

	@Override
	public synchronized void transfer(Account a, Account b, double amount)
			throws IOException, IllegalArgumentException, OverdrawException,
			InactiveException {
		if (a == null || b == null) throw new IllegalArgumentException();
		if (amount < 0) throw new IllegalArgumentException();
		
		// 01.05.2012 also check if the accounts are contained in our Map (thx to Lerch)
		if (a.isActive() && b.isActive() && accounts.containsKey(a.getNumber())
				&& accounts.containsKey(b.getNumber())) {
			// b.deposit is guaranteed to work now
			a.withdraw(amount);
			b.deposit(amount);
		} else {
			throw new InactiveException();
		}
	}
}