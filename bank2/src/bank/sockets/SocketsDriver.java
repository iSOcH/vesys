package bank.sockets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import bank.Bank;
import bank.BankDriver;
import bank.commands.Command;
import bank.commands.CommandBank;
import bank.commands.CommandDriver;
import bank.commands.ReturnValue;

public class SocketsDriver implements CommandDriver {
	private Socket sock;
	private CommandBank bank;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	@Override
	public void connect(String[] args) throws IOException {
		sock = new Socket(args[0], Integer.parseInt(args[1]));
		if (sock != null && sock.isConnected()) {
			bank = new CommandBank(this);
			
			// !!!! Performance !!!!
			// http://stackoverflow.com/questions/2251051/performance-issue-using-javas-object-streams-with-sockets
			oos = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
			oos.flush();
			ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
			
		} else {
			throw new IOException();
		}
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
		sock.close();
		sock = null;
	}

	@Override
	public Bank getBank() {
		return bank;
	}
	
	public synchronized ReturnValue executeCommand(Command cmd) {
		try {
			// this requires this method to be synchronized
			oos.writeObject(cmd);
			oos.flush();
			//System.out.println("sent command: " + cmd);
			return (ReturnValue) ois.readObject();
			
			// open a seperate connection per request, which means that
			// this method does not need to be synchronized
//			Socket ownsock = new Socket(sock.getInetAddress(), sock.getPort());
//			ObjectOutputStream ownoos = new ObjectOutputStream(ownsock.getOutputStream());
//			ObjectInputStream ownois = new ObjectInputStream(ownsock.getInputStream());
//			ownoos.writeObject(cmd);
//			//System.out.println("sent command: " + cmd);
//			ReturnValue result = (ReturnValue) ownois.readObject();
//			ownsock.close();
//			return result;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
