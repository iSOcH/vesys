package bank.uebung2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import bank.Bank;
import bank.BankDriver;
import bank.commands.Command;
import bank.commands.CommandBank;
import bank.commands.CommandDriver;
import bank.commands.ReturnValue;

public class HTTPDriver implements CommandDriver {
	private Bank bank;
	private URL url;

	@Override
	public void connect(String[] args) throws IOException {
		url = new URL(args[0]);
		bank = new CommandBank(this);
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
	}

	@Override
	public Bank getBank() {
		return bank;
	}
	
	public synchronized ReturnValue executeCommand(Command cmd) {
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(conn.getOutputStream()));
			
			oos.writeObject(cmd);
			oos.flush();
			oos.close();
			
			if (conn.getResponseCode() != 200) {
				throw new IOException("server returned " + conn.getResponseCode()
						+ " " + conn.getResponseMessage());
			}
			
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
			return (ReturnValue) ois.readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}