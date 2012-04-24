package bank.uebung5.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import bank.uebung5.BankRemote;

public class RMIServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// try to create rmi registry within this process
		try {
			System.out.println(RMIServer.class.getName()+".main: creating registry");
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println(">> registry could not be exported");
			System.out.println(">> probably another registry already runs on 1099");
		}

		BankRemote bank = new BankRMI();
		Naming.rebind("rmi://localhost:1099/BankService", bank);
		System.out.println("Bank server started...");
	}

}
