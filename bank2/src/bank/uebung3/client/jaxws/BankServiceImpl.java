
package bank.uebung3.client.jaxws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "BankServiceImpl", targetNamespace = "http://srv.uebung3.bank/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BankServiceImpl {


    /**
     * 
     * @param amount
     * @param accA
     * @param accB
     * @throws OverdrawException_Exception
     * @throws IOException_Exception
     * @throws InactiveException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "transfer", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.Transfer")
    @ResponseWrapper(localName = "transferResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.TransferResponse")
    public void transfer(
        @WebParam(name = "accA", targetNamespace = "")
        String accA,
        @WebParam(name = "accB", targetNamespace = "")
        String accB,
        @WebParam(name = "amount", targetNamespace = "")
        double amount)
        throws IOException_Exception, IllegalArgumentException_Exception, InactiveException_Exception, OverdrawException_Exception
    ;

    /**
     * 
     * @param accountNumber
     * @return
     *     returns java.lang.String
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getOwner", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.GetOwner")
    @ResponseWrapper(localName = "getOwnerResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.GetOwnerResponse")
    public String getOwner(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber)
        throws IOException_Exception
    ;

    /**
     * 
     * @param owner
     * @return
     *     returns java.lang.String
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createAccount", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.CreateAccount")
    @ResponseWrapper(localName = "createAccountResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.CreateAccountResponse")
    public String createAccount(
        @WebParam(name = "owner", targetNamespace = "")
        String owner)
        throws IOException_Exception
    ;

    /**
     * 
     * @param accountNumber
     * @return
     *     returns boolean
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "removeAccount", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.RemoveAccount")
    @ResponseWrapper(localName = "removeAccountResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.RemoveAccountResponse")
    public boolean removeAccount(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber)
        throws IOException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAccountNumbers", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.GetAccountNumbers")
    @ResponseWrapper(localName = "getAccountNumbersResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.GetAccountNumbersResponse")
    public List<String> getAccountNumbers()
        throws IOException_Exception
    ;

    /**
     * 
     * @param accountNumber
     * @return
     *     returns boolean
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "accountExists", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.AccountExists")
    @ResponseWrapper(localName = "accountExistsResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.AccountExistsResponse")
    public boolean accountExists(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber)
        throws IOException_Exception
    ;

    /**
     * 
     * @param accountNumber
     * @return
     *     returns boolean
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "isActive", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.IsActive")
    @ResponseWrapper(localName = "isActiveResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.IsActiveResponse")
    public boolean isActive(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber)
        throws IOException_Exception
    ;

    /**
     * 
     * @param amount
     * @param accountNumber
     * @throws IOException_Exception
     * @throws InactiveException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "deposit", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.Deposit")
    @ResponseWrapper(localName = "depositResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.DepositResponse")
    public void deposit(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber,
        @WebParam(name = "amount", targetNamespace = "")
        double amount)
        throws IOException_Exception, IllegalArgumentException_Exception, InactiveException_Exception
    ;

    /**
     * 
     * @param amount
     * @param accountNumber
     * @throws OverdrawException_Exception
     * @throws IOException_Exception
     * @throws InactiveException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "withdraw", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.Withdraw")
    @ResponseWrapper(localName = "withdrawResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.WithdrawResponse")
    public void withdraw(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber,
        @WebParam(name = "amount", targetNamespace = "")
        double amount)
        throws IOException_Exception, IllegalArgumentException_Exception, InactiveException_Exception, OverdrawException_Exception
    ;

    /**
     * 
     * @param accountNumber
     * @return
     *     returns double
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBalance", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.GetBalance")
    @ResponseWrapper(localName = "getBalanceResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.GetBalanceResponse")
    public double getBalance(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber)
        throws IOException_Exception
    ;

    /**
     * 
     * @param accountNumber
     * @param active
     * @return
     *     returns boolean
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "setActive", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.SetActive")
    @ResponseWrapper(localName = "setActiveResponse", targetNamespace = "http://srv.uebung3.bank/", className = "bank.uebung3.client.jaxws.SetActiveResponse")
    public boolean setActive(
        @WebParam(name = "accountNumber", targetNamespace = "")
        String accountNumber,
        @WebParam(name = "active", targetNamespace = "")
        boolean active)
        throws IOException_Exception
    ;

}
