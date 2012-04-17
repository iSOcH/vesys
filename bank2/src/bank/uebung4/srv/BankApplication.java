package bank.uebung4.srv;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class BankApplication extends Application{

	private final Set<Object> singletons = new HashSet<Object>();
	private final Set<Class<?>> empty = new HashSet<Class<?>>();

	public BankApplication() {
		singletons.add(new BankResource());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
