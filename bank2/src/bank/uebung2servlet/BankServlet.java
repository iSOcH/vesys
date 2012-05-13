package bank.uebung2servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.BankDriver;
import bank.commands.Command;
import bank.commands.ReturnValue;
import bank.local.LocalDriver;

public class BankServlet extends HttpServlet {
	private BankDriver bankdriver;
	
	@Override
	public void init() throws javax.servlet.ServletException {
		bankdriver = new LocalDriver();
		try {
			bankdriver.connect(null);
		} catch (IOException ignore) {
			// no problem with localdriver
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// read command-object from request
		// TODO: buffer?
		ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
		try {
			Command cmd = (Command) ois.readObject();
			
			// execute the command
			cmd.execute(bankdriver);
			ReturnValue retVal = cmd.getResult();
			
			// respond
			ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
			oos.writeObject(retVal);
			oos.flush();
			oos.close();
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = new PrintWriter(resp.getOutputStream());
		pw.write("Hello, this is the BankServlet... however, we only process POST-Requests");
		pw.flush();
		pw.close();
	}
}
