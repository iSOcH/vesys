package bank.uebung6.client;

import java.io.IOException;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
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
	private MessageConsumer receiver;
	private Destination answerDestination;
	
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
			
			answerDestination = session.createQueue("JMSBankClient-" + UUID.randomUUID());
			receiver = session.createConsumer(answerDestination);
		} catch (JMSException e) {
			System.err.println("could not connect to BankJMS");
			e.printStackTrace();
		}
		
	}

	@Override
	public void disconnect() throws IOException {
		proxyBank = null;
		try {
			session.close();
		} catch (JMSException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Bank getBank() {
		return proxyBank;
	}

	@Override
	public ReturnValue executeCommand(Command cmd) {
		try {
			ObjectMessage msg = session.createObjectMessage(cmd);
			msg.setJMSReplyTo(answerDestination);
			producer.send(msg);
			
			// get answer and process
			Message respMsg = receiver.receive(500);
			if (respMsg == null || !(respMsg instanceof ObjectMessage)) {
				return new ReturnValueDefault(false, new IOException("received response not OK! " + respMsg), null);
			}
			
			return (ReturnValue) ((ObjectMessage) respMsg).getObject();
			
		} catch (JMSException e) {
			return new ReturnValueDefault(false, new IOException(e), null);
		}
	}

}
