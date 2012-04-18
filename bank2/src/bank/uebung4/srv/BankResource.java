package bank.uebung4.srv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.api.NotFoundException;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;
import bank.local.LocalAccount;
import bank.local.LocalBank;
import bank.uebung4.AccountData;

@Path("/bank")
public class BankResource {
	LocalBank localbank;
	
	public BankResource() {
		localbank = new LocalBank();
		System.out.println("BankResource() called");
	}
	
	@GET
	@Path("/accounts")
	@Produces("text/plain")
	public String getAccountNumbers() throws IOException{
		System.out.print("getAccountNumbers: ");
		StringBuffer result = new StringBuffer();
		for(String accnr:localbank.getAccountNumbers()){
			result.append("http://localhost:9998/bank/accounts/"+accnr+"\n");
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
	@GET
	@Path("/accounts")
	public Set<String> getAccountNumbersSet() throws IOException {
		return localbank.getAccountNumbers();
	}
		
	@GET
	@Path("/accounts/{id}")
	@Produces("text/plain")
	public String getAccountInfos(@PathParam("id") String id) throws IOException{
		System.out.println("getAccountInfos");
		StringBuffer result = new StringBuffer();
		Account acc = localbank.getAccount(id);
		if(acc != null){
			result.append("Owner: "+ acc.getOwner() +"/n");
			result.append("AccountNumber: "+ acc.getNumber() +"/n");
			result.append("Balance: "+ acc.getBalance() +"/n");
			result.append("IsActive: "+ acc.isActive() +"/n");
		} else {
			result.append("404 - Account does not exist");
		}
		return result.toString();
	}
	
	@GET
	@Path("/accounts/{id}")
	public Account getAccount(@PathParam("id") String id) throws IOException {
		Account realAcc = localbank.getAccount(id);
		
		if (realAcc != null) {
			return new AccountData(realAcc);
		} else {
			throw new NotFoundException("this account doesnt exist or is not active");
		}
	}
	
	@POST
	@Path("/accounts")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response createAccount(String owner) throws IOException, URISyntaxException{
		System.out.println("createAccount: " +owner);
		URI uri = new URI("http://localhost:9998/bank/accounts/"+localbank.createAccount(owner));
		System.out.println(uri.toString());
		return Response.status(201).contentLocation(uri).build();
	}

	@HEAD
	@Path("/accounts/{id}")
	public Response isActive(@PathParam("id") String id) throws IOException{
		System.out.println("isActive");
		Account acc = localbank.getAccount(id);
		if(acc != null){
			if(acc.isActive()){
				return Response.status(200).build();
			} else{
				return Response.status(204).build();			
			}
		} else {
			return Response.status(404).build();						
		}
	}
	
	@DELETE
	@Path("/accounts/{id}")
	public Response removeAccount(@PathParam("id") String id) throws IOException{
		System.out.println("removeAccount " + id);
		boolean result = localbank.removeAccount(id);
		if(result){
			return Response.noContent().status(200).build();
		} else {
			return Response.noContent().status(204).build();
		}
	}

	@PUT
	@Path("/accounts/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setBalance(@PathParam("id") String id, double balance) throws IOException{
		System.out.println("setBalance");
		Account acc = localbank.getAccount(id);
		if(acc != null){
			try {
			if(acc.getBalance()>balance){
					acc.withdraw(acc.getBalance() - balance);
			} else if(acc.getBalance()<balance) {
				acc.deposit(balance-acc.getBalance());
			} else {
				//nothing to do
			}
			return Response.status(200).build();
			} catch (Exception e) {
				return Response.status(204).build();
			}
		} else {
			return Response.status(404).build();						
		}
	}
	
	@POST
	@Path("/accounts/{accANumber}/transfer/{accBNumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response transfer(
			@PathParam("accANumber") String accANumber,
			@PathParam("accBNumber") String accBNumber,
			double amount) throws IOException {
		Account accA = localbank.getAccount(accANumber);
		Account accB = localbank.getAccount(accBNumber);
		
		if (accA != null && accB != null) {
			try {
				localbank.transfer(accA, accB, amount);
			} catch (IllegalArgumentException e) {
				return Response.notAcceptable(null).build();
			} catch (OverdrawException e) {
				return Response.status(409).build();
			} catch (InactiveException e) {
				throw new NotFoundException("at least one account was not active");
			}
			return Response.ok().build();
		}
		throw new NotFoundException("at least one account does not exist");
	}
}
