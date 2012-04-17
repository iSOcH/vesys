package bank.uebung3.srv;

import javax.xml.ws.Endpoint;

public class BankServicePublisher {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:9876/bankservice";
		Endpoint.publish(url, new BankServiceImpl());
	}

}
