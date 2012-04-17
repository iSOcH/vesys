package bank.uebung2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;

import bank.BankDriver;
import bank.commands.Command;
import bank.local.LocalDriver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Uebung2Server {
	
	public static void main(String[] args) throws IOException {
		new Uebung2Server(1081);
	}
	
	public Uebung2Server(int port) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/bank", new HTTPBankHandler(new LocalDriver()));
		server.start();
	}
	
	private class HTTPBankHandler implements HttpHandler {
		private BankDriver bankdriver;
		
		public HTTPBankHandler(BankDriver driver) throws IOException {
			this.bankdriver = driver;
			driver.connect(null);
		}
		
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(exchange.getRequestBody()));
			
			try {
				Command cmd = (Command) ois.readObject();
				cmd.execute(bankdriver);
				exchange.sendResponseHeaders(200, 0);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(exchange.getResponseBody()));
				oos.writeObject(cmd.getResult());
				oos.flush();
				oos.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				exchange.sendResponseHeaders(500, -1);
			} catch (IOException e2) {
				e2.printStackTrace();
				System.exit(1);
			}
		}
	}
}