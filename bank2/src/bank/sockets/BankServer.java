package bank.sockets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import bank.BankDriver;
import bank.commands.Command;
import bank.local.LocalDriver;

public class BankServer {
	private static final int NUMTHREADS = 2;
	
	public static void main(String[] args) throws IOException {
		final int port = 21324;
		
		BankDriver driver = new LocalDriver();
		driver.connect(null);
		ServerSocket socket = new ServerSocket(port);
		
		for (int i=0; i<NUMTHREADS; i++) {
			Thread handler = new Thread(new BankHandler(driver, socket));
			handler.start();
		}
	}
	
	private static class BankHandler implements Runnable {
		private BankDriver driver;
		private ServerSocket s;
		
		public BankHandler(BankDriver driver, ServerSocket socket) {
			this.driver = driver;
			this.s = socket;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Socket sock = s.accept();
					
					System.out.println("connection from: " + sock.getInetAddress() + ":" + sock.getPort());
					
					// !!!! Performance !!!!
					// http://stackoverflow.com/questions/2251051/performance-issue-using-javas-object-streams-with-sockets
					ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
					
					while (true) {
						Command cmd = (Command) ois.readObject();
						cmd.execute(driver);
						oos.writeObject(cmd.getResult());
						oos.flush();
						//System.out.println("answered command: " + cmd);
					}
				} catch (IOException e) {
					System.out.println("IOException in Server: " + e);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}