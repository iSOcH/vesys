package bank.uebung6.client;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import bank.Bank;
import bank.BankDriver;
import bank.commands.Command;
import bank.commands.CommandBank;
import bank.commands.CommandDriver;
import bank.commands.ReturnValue;
import bank.commands.ReturnValueDefault;

public class JMSDriver implements CommandDriver {

	private CommandBank proxyBank;
	private MessageProducer producer;
	private Session session;
	
	@Override
	public void connect(String[] args) throws IOException {
		proxyBank = new CommandBank(this);
		
		ActiveMQConnectionFactory connectionFactory = new
				ActiveMQConnectionFactory("tcp://localhost:61616");
		
		try {
			// Create JMS objects
			Connection connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("JMSBank");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
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
		try {
			ObjectMessage msg = session.createObjectMessage(cmd);
			producer.send(msg);
			
			// TODO: receive result (sync) and return the ReturnValue
			
		} catch (JMSException e) {
			return new ReturnValueDefault(false, new IOException(e), null);
		}
		
		
		return null;
	}

}
