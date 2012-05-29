package bank.uebung6.srv;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import bank.BankDriver;
import bank.commands.Command;
import bank.commands.ReturnValue;
import bank.local.LocalDriver;

public class JMSBankServer implements MessageListener {

	private BankDriver realBankDriver;
	private QueueSession session;
	
	public static void main(String[] args) throws JMSException {
		new JMSBankServer();
	}
	
	public JMSBankServer() throws JMSException {
		realBankDriver = new LocalDriver();
		
		ActiveMQConnectionFactory connectionFactory = new
				ActiveMQConnectionFactory("tcp://localhost:61616");
		
		// Create JMS objects
		QueueConnection connection = connectionFactory.createQueueConnection();
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("JMSBank");
		MessageConsumer receiver = session.createConsumer(destination);
		receiver.setMessageListener(this);
		connection.start();
	}

	@Override
	public void onMessage(Message msg) {
		if (msg instanceof ObjectMessage) {
			try {
				// prepare response
				Queue replyTo = (Queue) msg.getJMSReplyTo();
				QueueSender sender = session.createSender(replyTo);
				ObjectMessage returnMessage = session.createObjectMessage();

				// execute the command
				Command cmd = (Command) ((ObjectMessage) msg).getObject();
				cmd.execute(realBankDriver);
				ReturnValue retVal = cmd.getResult();
				
				// we seem to be done, send result back to client
				returnMessage.setObject(retVal);
				sender.send(replyTo, returnMessage);
			} catch (JMSException e) {
				System.err.println("could not get command object from message: " + msg);
				e.printStackTrace();
			} catch (ClassCastException e) {
				System.err.println("something was not received in the format we want it");
				e.printStackTrace();
			}
		} else {
			System.err.println("we only handle ObjectMessages but we received something else: " + msg);
		}
	}

}
