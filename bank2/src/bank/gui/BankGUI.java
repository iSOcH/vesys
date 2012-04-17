/*
 * Copyright (c) 2000-2009 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package bank.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bank.Account;
import bank.Bank;
import bank.BankDriver;
import bank.InactiveException;
import bank.OverdrawException;


public class BankGUI extends JFrame {

	final static int NUMBER_OF_EFF_TESTS = 1000;
	
	private BankDriver driver;
	private Bank bank;

	private JComboBox accountcombo = new JComboBox();
	private Map<String, Account> accounts = new HashMap<String,Account>();

	private JTextField fld_owner   = new JTextField();
	private JTextField fld_balance = new JTextField();

	private JButton btn_refresh   = new JButton("Refresh");
	private JButton btn_deposit   = new JButton("Deposit Money");
	private JButton btn_withdraw  = new JButton("Withdraw Money");
	private JButton btn_transfer  = new JButton("Transfer Money");

	private JMenuItem item_new    = new JMenuItem("New Account...");
	private JMenuItem item_remove = new JMenuItem("Remove Account");
	private JMenuItem item_exit   = new JMenuItem("Exit");
	private JMenuItem item_about  = new JMenuItem("About");

	private JMenuItem item_test_efficiency    = new JMenuItem("Efficiency Test");
	private JMenuItem item_test_deposit       = new JMenuItem("Deposit/Withdraw Test");
	private JMenuItem item_test_functionality = new JMenuItem("Functionality Test");
	private JMenuItem item_test_transfer      = new JMenuItem("Transfer Test");


	private boolean ignoreItemChanges = false;

	public BankGUI(BankDriver server) {
		this.driver = server;
		this.bank   = server.getBank();

		setTitle("ClientBank Application");
		setBackground(Color.lightGray);

		// define menus
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu menu_file = new JMenu("File");
		menubar.add(menu_file);
		menu_file.add(item_new);
		menu_file.add(item_remove);
		menu_file.addSeparator();
		menu_file.add(item_exit);

		JMenu menu_test = new JMenu("Test");
		menubar.add(menu_test);
		menu_test.add(item_test_efficiency);
		menu_test.add(item_test_deposit);
		menu_test.add(item_test_functionality);
		menu_test.add(item_test_transfer);
		item_test_efficiency.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						testEfficiency();
					}
					catch(Exception ex){
						error(ex);
					}
				}
			}
		);
		item_test_deposit.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						testDeposit();
					}
					catch(Exception ex){
						error(ex);
					}
				}
			}
		);
		item_test_transfer.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						testTransfer();
					}
					catch(Exception ex){
						error(ex);
					}
				}
			}
		);
		item_test_functionality.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						testFunctionality();
					}
					catch(Exception ex){
						error(ex);
					}
				}
			}
		);


		JMenu menu_help = new JMenu("Help");
		menubar.add(menu_help);
		menu_help.add(item_about);

		item_new.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					addAccount();
				}
			}
		);
		item_remove.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					removeAccount();
				}
			}
		);
		item_exit.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					exit();
				}
			}
		);
		item_about.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					about();
				}
			}
		);

		addWindowListener(
			new WindowAdapter(){
				@Override public void windowClosing(WindowEvent e){
					exit();
				}
				@Override public void windowDeiconified(WindowEvent e) {
					refreshDialog();
				}
			}
		);

		accountcombo.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent e){
					if(ignoreItemChanges)return;
					if(e.getStateChange() == ItemEvent.SELECTED)
						updateCustomerInfo();
				}
			}
		);

		// create layout

		setResizable(false);

		JPanel center=new JPanel(new GridLayout(3,2,5,5));
		center.add(new JLabel("Account Nr: ", SwingConstants.RIGHT));
		center.add(accountcombo);
		center.add(new JLabel("Owner: ", SwingConstants.RIGHT));
		center.add(fld_owner);
		center.add(new JLabel("Balance: ", SwingConstants.RIGHT));
		center.add(fld_balance);

		// set text fields read only
		fld_owner.setEditable(false);
		fld_balance.setEditable(false);

		JPanel east=new JPanel(new GridLayout(3,1,5,5));
		east.add(btn_deposit);
		east.add(btn_withdraw);
		east.add(btn_transfer);


		JPanel p=new JPanel(new BorderLayout(10,10));
		p.add(new JLabel(""), BorderLayout.NORTH);
		p.add(center,         BorderLayout.CENTER);
		p.add(east,           BorderLayout.EAST);
		p.add(btn_refresh,    BorderLayout.SOUTH);

		//getContentPane().add(p);
		add(p);

		// Add ActionListeners
		btn_refresh.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					refreshDialog();
				}
			}
		);
		btn_deposit.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					deposit();
				}
			}
		);
		btn_withdraw.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					withdraw();
				}
			}
		);
		btn_transfer.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					transfer();
				}
			}
		);

		Dimension d = accountcombo.getPreferredSize();
		d.setSize(Math.max(d.getWidth(), 130), d.getHeight());
		accountcombo.setPreferredSize(d);

		refreshDialog();
	}

	public String currentAccountNumber() {
		return (String)accountcombo.getSelectedItem();
	}

	public void addAccount() {
		AddAccountDialog addaccount = new AddAccountDialog(this, "Add Account");

		Point loc = getLocation();
		addaccount.setLocation(loc.x + 50, loc.y + 50);
		addaccount.setModal(true);
		addaccount.setVisible(true);

		if (!addaccount.canceled()) {
			String number = null;
			try {
				number = bank.createAccount(addaccount.getOwnerName());
			}
			catch (Exception e){
				error(e);
			}

			if(number==null){
				JOptionPane.showMessageDialog(this, "Account could not be created",
					"Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				try {
					Account acc = bank.getAccount(number);
					accounts.put(number, acc);

					String str = addaccount.getBalance().trim();
					double amount;
					if(str.equals("")) amount=0;
					else amount = Double.parseDouble(str);
					acc.deposit(amount);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Illegal Format",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(this, "Illegal Argument",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (InactiveException e) {
					JOptionPane.showMessageDialog(this, "Account is inactive",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e){
					error(e);
				}
				ignoreItemChanges=true;
				accountcombo.addItem(number);
				accountcombo.setSelectedItem(number);
				ignoreItemChanges=false;
				refreshDialog();
			}
		}
	}

	public void removeAccount() {
		String number = currentAccountNumber();
		if (number != null) {
			int res = JOptionPane.showConfirmDialog(this, "Really delete account " + number + "?",
				"Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(res == 0) {
				try {
					boolean done = bank.removeAccount(number);
					if(done){
						refreshDialog();
					}
					else {
						JOptionPane.showMessageDialog(this, "Account could not be removed",
							"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception e){
					error(e);
				}
			}
		}
	}

	public void deposit() {
		String number = currentAccountNumber();
		if (number != null) {
		    String s=JOptionPane.showInputDialog(this, "Enter amount to deposit:",
				"Deposit Money", JOptionPane.QUESTION_MESSAGE);
		    if (s!=null) {
			    try  {
					double amount = Double.parseDouble(s);
					Account a = accounts.get(number);
					a.deposit(amount);
					fld_balance.setText(currencyFormat(a.getBalance()));
			    }
			    catch (NumberFormatException e) {
			    	JOptionPane.showMessageDialog(this,"Illegal Value",
						"Error", JOptionPane.ERROR_MESSAGE);
			    }
				catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(this, "Illegal Argument",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (InactiveException e) {
					JOptionPane.showMessageDialog(this, "Account is inactive",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e){
					error(e);
				}
			}
		}
	}

	public void withdraw() {
        String number = currentAccountNumber();
	    if (number != null) {
		    String s=JOptionPane.showInputDialog(this, "Enter amount to draw:", "Draw Money",
	                                         JOptionPane.QUESTION_MESSAGE);
	    	if (s!=null) {
		    	try {
					double amount = Double.parseDouble(s);
					Account a = accounts.get(number);
					a.withdraw(amount);
	    			fld_balance.setText(currencyFormat(a.getBalance()));
		    	}
		    	catch (NumberFormatException e) {
		    		JOptionPane.showMessageDialog(this,"Illegal Value",
						"Error", JOptionPane.ERROR_MESSAGE);
		    	}
				catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(this, "Illegal Argument",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (InactiveException e) {
					JOptionPane.showMessageDialog(this, "Account is inactive",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (OverdrawException e) {
					JOptionPane.showMessageDialog(this, "Account must not be overdrawn",
						"Error", JOptionPane.ERROR_MESSAGE);
				}
		    	catch (Exception e) {
		    		error(e);
		    	}
	    	}
	    }
	}


	public void transfer() {
 		String number = currentAccountNumber();
		if (number != null) {
			try{
				Set<String> s = new HashSet<String>(accounts.keySet());
				s.remove(number);

			    TransferDialog trans = new TransferDialog(this, "Transfer Money", number, s);
				Point loc = getLocation();
				trans.setLocation(loc.x + 50, loc.y + 50);
				trans.setModal(true);
		    	trans.setVisible(true);

		    	if (!trans.canceled()) {
			    	if (number.equals(trans.getAccountNumber())){
			    		JOptionPane.showMessageDialog(this, "Both Accounts are the same!",
							"Error", JOptionPane.ERROR_MESSAGE);
			    	}
			    	else {
						try {
							double amount = Double.parseDouble(trans.getBalance());
							Account from = accounts.get(number);
							Account to   = accounts.get(trans.getAccountNumber());
							bank.transfer(from, to, amount);
							
							// after transfer adjust value of displayed account
							fld_balance.setText(currencyFormat(from.getBalance()));

				    	    JOptionPane.showMessageDialog(this,"Transfer successfull",
								"Information", JOptionPane.INFORMATION_MESSAGE);
				    	}
				    	catch (NumberFormatException e) {
				    		JOptionPane.showMessageDialog(this,"Illegal Balance",
								"Error", JOptionPane.ERROR_MESSAGE);
				    	}
						catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(this, "Illegal Argument",
								"Error", JOptionPane.ERROR_MESSAGE);
						}
						catch (InactiveException e) {
							JOptionPane.showMessageDialog(this, "At least one account is inactive",
								"Error", JOptionPane.ERROR_MESSAGE);
						}
						catch (OverdrawException e) {
							JOptionPane.showMessageDialog(this, "Account must not be overdrawn",
								"Error", JOptionPane.ERROR_MESSAGE);
						}
			    	}
		    	}
			}
			catch(Exception e){
				error(e);
			}
		}
    }

	public void exit(){
		try {
			driver.disconnect();
		} catch (IOException e) {
			// TODO what to do with IOException upon disconnection
			e.printStackTrace();
		}
		System.exit(0);
	}

	private void refreshDialog() {
		String nr = currentAccountNumber();
		accountcombo.removeAllItems();
		if (bank != null)  {
			try{
				Set<String> s = bank.getAccountNumbers();
				ArrayList<String> accnumbers = new ArrayList<String>(s);
				Collections.sort(accnumbers);
				ignoreItemChanges=true;
				for(String item : accnumbers){
					accountcombo.addItem(item);
					if(item.equals(nr)) accountcombo.setSelectedItem(item);
				}
				ignoreItemChanges=false;
				
				// clean up local accounts map
				for(String key : s){
					if(!accounts.containsKey(key)){
						accounts.put(key, bank.getAccount(key));
					}
				}
				Iterator<String> it = accounts.keySet().iterator();
				while(it.hasNext()){
					if(!s.contains(it.next())) it.remove();
				}

				int size = s.size();
				btn_deposit.setEnabled(size > 0);
				btn_withdraw.setEnabled(size > 0);
				btn_transfer.setEnabled(size > 1);
				item_remove.setEnabled(size > 0);
				item_test_efficiency.setEnabled(size > 0);
				item_test_deposit.setEnabled(size > 0);
				item_test_transfer.setEnabled(size > 1);
				item_test_functionality.setEnabled(size > 0);

				updateCustomerInfo();
			}
			catch(Exception e){
				error(e);
			}
		}
	}

	private void updateCustomerInfo() {
		String nr = currentAccountNumber();
		try{
			if(nr != null){
				Account a = accounts.get(nr);
				if(a != null){
					fld_owner.setText(a.getOwner());
					fld_balance.setText(currencyFormat(a.getBalance()));
				}
				else {
					JOptionPane.showMessageDialog(this,"Account not found",
						"Error", JOptionPane.ERROR_MESSAGE);
					refreshDialog();
				}
			}
			else {
				fld_owner.setText("");
				fld_balance.setText("");
			}
		}
		catch(Exception e){
			error(e);
		}
	}

	public String currencyFormat(double val) {
		return NumberFormat.getCurrencyInstance().format(val);
	}

	public void error(Exception e){
		JDialog dlg = new ErrorBox(this, e);
		dlg.setModal(true);
		dlg.setVisible(true);
	}


	public void about(){
		AboutBox dlg = new AboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation(
			(frmSize.width  - dlgSize.width) /2 + loc.x,
			(frmSize.height - dlgSize.height)/2 + loc.y);
		dlg.setModal(true);
		dlg.setVisible(true);
	}

	static class ErrorBox extends JDialog {
		public ErrorBox(Frame parent, Exception e){
			super(parent);
			setTitle("Exception");
			setResizable(true);

			//JTextField msg1 = new JTextField();
			//msg1.setText(e.getMessage());

			JTextArea trace = new JTextArea(10, 50);
			java.io.StringWriter buf = new java.io.StringWriter();
			java.io.PrintWriter  wr = new java.io.PrintWriter(buf);
			e.printStackTrace(wr);
			trace.setText(buf.toString());
			trace.setCaretPosition(0);
			trace.setEditable(false);

			JScrollPane msg = new JScrollPane(trace);

			JButton ok = new JButton("OK");

			ok.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
					}
				}
			);

			//getContentPane().add(msg1,   BorderLayout.NORTH);
			getContentPane().add(msg, BorderLayout.CENTER);
			getContentPane().add(ok,  BorderLayout.SOUTH);
			getRootPane().setDefaultButton(ok);
			ok.requestFocus();
			pack();
		}
	}


	static class AboutBox extends JDialog {

		public AboutBox(Frame parent){
			super(parent);
			setTitle("About Bank Client");
			setResizable(false);

			JPanel p_text = new JPanel(new GridLayout(0, 1));
			p_text.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
			p_text.add(new JLabel("Distributed Systems", SwingConstants.CENTER));
			p_text.add(new JLabel("Bank Client", SwingConstants.CENTER));
			p_text.add(new JLabel("", SwingConstants.CENTER));
			p_text.add(new JLabel("� D. Gruntz, 2001-2011", SwingConstants.CENTER));

			JButton ok = new JButton("OK");
			ok.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
					}
				}
			);

			getContentPane().add(p_text, BorderLayout.CENTER);
			getContentPane().add(ok,     BorderLayout.SOUTH);
			pack();
		}
	}

	static class AddAccountDialog extends JDialog {
		private JTextField ownerfield   = new JTextField(12);
		private JTextField balancefield = new JTextField(12);

		private boolean canceled=true;

		AddAccountDialog(Frame owner, String title) {

			super(owner, title);

			// Create Layout
			JButton btn_ok     = new JButton("Ok");
			JButton btn_cancel = new JButton("Cancel");
			JPanel p=new JPanel(new GridLayout(3,2,10,10));
			p.add(new JLabel("Owner:", JLabel.RIGHT));
			p.add(ownerfield);
			p.add(new JLabel("Balance:", JLabel.RIGHT));
			p.add(balancefield);
			p.add(btn_ok);
			p.add(btn_cancel);

			getContentPane().add(p);

			btn_ok.setDefaultCapable(true);
			getRootPane().setDefaultButton(btn_ok);

			// Add ActionListeners
			btn_ok.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						canceled=false;
						setVisible(false);
					}
				}
			);
			btn_cancel.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						canceled=true;
						setVisible(false);
					}
				}
			);
			pack();
		}

		public boolean canceled()     {return canceled; }
		public String  getOwnerName() {return ownerfield.getText(); }
		public String  getBalance()   {return balancefield.getText(); }
	}

	static class TransferDialog extends JDialog {
		private JTextField balancefield = new JTextField(12);
		private JComboBox  accountcombo;

		private boolean canceled=true;

		TransferDialog(Frame owner, String title, String account, Set<String> accounts) {
			super(owner, title);

			JButton btn_ok     = new JButton("Ok");
			JButton btn_cancel = new JButton("Cancel");
			ArrayList<String> accnumbers = new ArrayList<String>(accounts);
			Collections.sort(accnumbers);
			accountcombo = new JComboBox(accnumbers.toArray());

			// Create Layout
			JPanel p=new JPanel(new GridLayout(4,2,10,10));
			p.add(new JLabel("From Account:", JLabel.RIGHT));
			p.add(new JLabel(account));
			p.add(new JLabel("To Account:", JLabel.RIGHT));
			p.add(accountcombo);
			p.add(new JLabel("Amount:", JLabel.RIGHT));
			p.add(balancefield);
			p.add(btn_ok);
			p.add(btn_cancel);

			getContentPane().add(p);
			btn_ok.setDefaultCapable(true);
			getRootPane().setDefaultButton(btn_ok);

			// Add ActionListeners
			btn_ok.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						canceled=false;
						setVisible(false);
					}
				}
			);
			btn_cancel.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						canceled = true;
						setVisible(false);
					}
				}
			);
			pack();
		}

		public boolean canceled()  { return canceled; }
		public String getAccountNumber() { return (String)accountcombo.getSelectedItem(); }
		public String getBalance() { return balancefield.getText(); }
	}


	public void testEfficiency() throws Exception {
		String nr = currentAccountNumber();
		if(nr != null){
			final Account acc = accounts.get(nr);

			String msg;
			try {
				System.gc();
				long st = System.currentTimeMillis();
				for(int i=1; i<=NUMBER_OF_EFF_TESTS; i++){
					acc.deposit(i);
					acc.withdraw(i);
					if (i%100==0) System.out.println("efficiency-test: i=" + i);
				}
				st = System.currentTimeMillis()-st;
				msg = 2*NUMBER_OF_EFF_TESTS + " operations in " + st/1000.0 + " Sek\n"
					 +st/(2.0 * NUMBER_OF_EFF_TESTS) + " msec/op";
			}
			catch(Exception e){
				msg = "test did throw an exception\n" + e.getMessage();
			}

			JOptionPane.showMessageDialog(this, msg, "Test Result",
				JOptionPane.INFORMATION_MESSAGE);

		}
	}

	public void testDeposit() throws Exception {
		String nr = currentAccountNumber();
		if(nr != null){
			final Account acc = accounts.get(nr);
			final double  amount = acc.getBalance();

			class TestThread extends Thread {
				private double val;
				TestThread(double val){this.val = val;}
				@Override public void run(){
					for(int i=0; i<100; i++){
						try{
							acc.deposit(val);
							acc.withdraw(val);
							yield();
						}
						catch(Exception e){
							break;
						}
					}
				}
			}

			TestThread t1 = new TestThread(120);
			TestThread t2 = new TestThread(60);
			TestThread t3 = new TestThread(20);
			TestThread t4 = new TestThread(55.5);
			t1.start(); t3.start();
			t2.start(); t4.start();
			try{
				t1.join();
				t2.join();
				t3.join();
				t4.join();
			}
			catch(InterruptedException e){
			}

			String msg;



			if(amount == acc.getBalance()){
				msg = "your bank seems to be thread safe";
			}
			else {
				msg = "your bank is not thread safe";
			}
			JOptionPane.showMessageDialog(this, msg, "Test Result",
				JOptionPane.INFORMATION_MESSAGE);

		}
	}

	public void testFunctionality() throws Exception {
		String msg = null;

		// is active implemented correctly?
		// After remove a deposit on the removed account has to throw an exception
		if(msg == null){
			String nr = bank.createAccount("TestUser");
			Account a = bank.getAccount(nr);
			bank.removeAccount(nr);
			try {
				a.deposit(100);
				msg = "active is not implemented correctly!\n"
					+ "Transactions are not allowed on a removed account";
			}
			catch(InactiveException e){
			}
		}

		// is it possible to overdraw the account?
		if(msg == null){
			String nr = currentAccountNumber();
			if(nr != null){
				Account acc = bank.getAccount(nr);
				double  amount = acc.getBalance();
				acc.withdraw(amount);
				try{
					acc.deposit(Math.sin(100));
				}
				catch(Exception e){
				}
				if(acc.getBalance() < 0)
					msg = "it is possible to overdraw your account!\n"
						+ "look at the balance of the current account.";
				else
					acc.deposit(amount);
			}
		}

		// test of transfer
		if(msg == null){
			String  n1 = currentAccountNumber();
			String  n2 = bank.createAccount("Account2");
			Account a1 = bank.getAccount(n1);
			Account a2 = bank.getAccount(n2);
			double a1Balance = a1.getBalance();
			double a2Balance = a2.getBalance();
			a1.withdraw(a1Balance);
			a2.withdraw(a2Balance);
			a1.deposit(50);
			a2.deposit(50);

			try {
				bank.transfer(a1, a2, -100);
				msg = "oops, your account could be overdrawn!";
			}
			catch(Exception e){
				double bal1 = a1.getBalance();
				double bal2 = a2.getBalance();
				if(bal1 != 50 || bal2 != 50)
					msg = "Although an exception was thrown by transfer, "
						+ "the balances have been changed.\n"
						+ "When an exception is thrown the state must not be changed.";
			}

			if(msg == null){
				double bal2 = a2.getBalance();
				a2.withdraw(bal2);
				bank.removeAccount(n2);
				a1.withdraw(50);
				a1.deposit(a1Balance);
			}
		}

		// deadlock in transfer
		if(msg == null){
			String  n = bank.createAccount("Account3");
			final Account a = bank.getAccount(n);

			a.deposit(100);

			Thread t = new Thread(){
				@Override public void run(){
					try {
						bank.transfer(a, a, 50);
					}
					catch(Exception e){	}
					try {
						a.withdraw(100);
					}
					catch(Exception e){	}
				}
			};
			t.start();
			try {
				t.join(8000);
			}
			catch(InterruptedException e){
			}
			if(a.getBalance()>0)
				msg = "A call of bank.transfer ended in a deadlock!";

			bank.removeAccount(n);
		}

		// can an account with positive balance be removed?
		if(msg == null){
			String  n = bank.createAccount("Account4");
			Account a = bank.getAccount(n);
			a.deposit(100);
			boolean done = bank.removeAccount(n);
			if(done)
				msg = "Accounts with a positive balance must not be removed!";

			System.out.println("positive-balance-remove-test: is a active? " + a.isActive());
			a.withdraw(100);
			bank.removeAccount(n);
		}

		// can an owner open two accounts?
		if(msg == null){
			String n1 = bank.createAccount("Meier");
			String n2 = bank.createAccount("Meier");
			if(n1.equals(n2)){
				msg = "A user cannot create two accounts using the same name";
			}
			bank.removeAccount(n1);
			bank.removeAccount(n2);
		}

		// uniqueness of account numbers
		if(msg == null){
			String  n1 = bank.createAccount("Account1");
			String  n2 = bank.createAccount("Account54039680");
			
			if(n1.equals(n2))
				msg = "different accounts should have different account numbers!";

			bank.removeAccount(n1);
			bank.removeAccount(n2);
		}
		
		// are arbitrary names supported
		if(msg == null) {
			String name, id;
			Account a;
			name = "Hans Muster";
			id = bank.createAccount(name);
			a = bank.getAccount(id);
			if( !name.equals(a.getOwner()))
				msg = "not all names are properly supported";
			bank.removeAccount(id);
			
			name = "Peter M�ller;junior";
			id = bank.createAccount(name);
			a = bank.getAccount(id);
			if( !name.equals(a.getOwner()))
				msg = "not all names are properly supported";
			bank.removeAccount(id);
		}

		if(msg == null){
			Bank bank = driver.getBank();
			if(bank != this.bank)
				msg = "getBank should be implemented as singleton";
		}

		if(msg == null){
			String n = bank.createAccount("Account5");
			Set<String> s1 = new HashSet<String>(bank.getAccountNumbers());
			bank.removeAccount(n);
			Set<String> s2 = new HashSet<String>(bank.getAccountNumbers());
			if(s1.equals(s2))
				msg = "method getAccountNumbers should only return the numbers of active accounts.";
		}
		
		if(msg == null){
			String n = bank.createAccount("Account6");
			Account a = bank.getAccount(n);
			bank.removeAccount(n);
			try {
				double balance = a.getBalance();
				if(balance != 0.0)
					msg = "balance of a removed account should be zero.";
			}
			catch(Exception e){
				msg = "method getBalance should not throw an Exception.";
			}
		}
		
		if(msg == null)
			msg = "Your implementation passed all unit tests";

		updateCustomerInfo();

		JOptionPane.showMessageDialog(this, msg, "Test Result",
			JOptionPane.INFORMATION_MESSAGE);


	}

	public void testTransfer() throws Exception {
		Set<String> s = bank.getAccountNumbers();
		Iterator<String> it = s.iterator();
		final Account a1 = bank.getAccount(it.next());
		final Account a2 = bank.getAccount(it.next());

		class TestThread extends Thread {
			private Account from, to;
			private boolean running = false;
			TestThread(Account from, Account to){
				this.from = from; this.to = to;
			}
			@Override public void run(){
				running = true;
				for(int i=0; i<100; i++){
					try{
						bank.transfer(from, to, 1);
						bank.transfer(to, from, 1);
					}
					catch(Exception e){
						break;
					}
				}
				running = false;
			}
			boolean isRunning(){return running;}
		}

		TestThread t1 = new TestThread(a1, a2);
		TestThread t2 = new TestThread(a2, a1);

		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		double bal1 = a1.getBalance();
		double bal2 = a2.getBalance();

		try{
			a1.deposit(100);
			a2.deposit(100);
			t1.start();
			t2.start();
		}
		catch(InactiveException e){
		}

		String msg = null;
		try{
			t1.join(5000);
			t2.join(5000);
		}
		catch(InterruptedException e){}

		if(t1.isRunning() || t2.isRunning()){
			msg = "your bank seems to hang in a deadlock";
		}
		else {
			try {
				a1.withdraw(100);
				a2.withdraw(100);
				bank.transfer(a1, a2, bal1+1);
			}
			catch(InactiveException e){}
			catch(OverdrawException e){}

			if(bal1 == a1.getBalance() && bal2 == a2.getBalance()){
				msg = "your bank is operating correctly";
			}
			else {
				msg = "your implementation of transfer is not correct";
			}
		}

		JOptionPane.showMessageDialog(this, msg, "Test Result",
			JOptionPane.INFORMATION_MESSAGE);
	}

}
