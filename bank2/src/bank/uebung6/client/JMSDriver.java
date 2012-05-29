package bank.uebung6.client;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import bank.Bank;
import bank.BankDriver;
import bank.commands.Command;
import bank.commands.CommandBank;
import bank.commands.CommandDriver;
import bank.commands.ReturnValue;

public class JMSDriver implements CommandDriver {

	private CommandBank proxyBank;
	
	@Override
	public void connect(String[] args) throws IOException {
		proxyBank = new CommandBank(this);
		
		ActiveMQConnectionFactory connectionFactory = new
				ActiveMQConnectionFactory("tcp://localhost:61616");
		
		try {
			// Create JMS objects
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
		} catch (JMSException e) {
			System.err.println("could not connect to BankJMS");
			e.printStackTrace();
		}
		
	}

	@Override
	public void disconnect() throws IOException {
		proxyBank = null;
	}

	@Override
	public Bank getBank() {
		return proxyBank;
	}

	@Override
	public ReturnValue executeCommand(Command cmd) {
		// TODO Auto-generated method stub
		return null;
	}

}
