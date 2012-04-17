
package bank.uebung3.client.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bank.uebung3.client.jaxws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IllegalArgumentException_QNAME = new QName("http://srv.uebung3.bank/", "IllegalArgumentException");
    private final static QName _GetOwner_QNAME = new QName("http://srv.uebung3.bank/", "getOwner");
    private final static QName _AccountExists_QNAME = new QName("http://srv.uebung3.bank/", "accountExists");
    private final static QName _IOException_QNAME = new QName("http://srv.uebung3.bank/", "IOException");
    private final static QName _Deposit_QNAME = new QName("http://srv.uebung3.bank/", "deposit");
    private final static QName _RemoveAccountResponse_QNAME = new QName("http://srv.uebung3.bank/", "removeAccountResponse");
    private final static QName _GetBalance_QNAME = new QName("http://srv.uebung3.bank/", "getBalance");
    private final static QName _Withdraw_QNAME = new QName("http://srv.uebung3.bank/", "withdraw");
    private final static QName _DepositResponse_QNAME = new QName("http://srv.uebung3.bank/", "depositResponse");
    private final static QName _IsActiveResponse_QNAME = new QName("http://srv.uebung3.bank/", "isActiveResponse");
    private final static QName _CreateAccountResponse_QNAME = new QName("http://srv.uebung3.bank/", "createAccountResponse");
    private final static QName _SetActive_QNAME = new QName("http://srv.uebung3.bank/", "setActive");
    private final static QName _OverdrawException_QNAME = new QName("http://srv.uebung3.bank/", "OverdrawException");
    private final static QName _WithdrawResponse_QNAME = new QName("http://srv.uebung3.bank/", "withdrawResponse");
    private final static QName _Transfer_QNAME = new QName("http://srv.uebung3.bank/", "transfer");
    private final static QName _GetBalanceResponse_QNAME = new QName("http://srv.uebung3.bank/", "getBalanceResponse");
    private final static QName _GetAccountNumbersResponse_QNAME = new QName("http://srv.uebung3.bank/", "getAccountNumbersResponse");
    private final static QName _GetAccountNumbers_QNAME = new QName("http://srv.uebung3.bank/", "getAccountNumbers");
    private final static QName _GetOwnerResponse_QNAME = new QName("http://srv.uebung3.bank/", "getOwnerResponse");
    private final static QName _InactiveException_QNAME = new QName("http://srv.uebung3.bank/", "InactiveException");
    private final static QName _AccountExistsResponse_QNAME = new QName("http://srv.uebung3.bank/", "accountExistsResponse");
    private final static QName _TransferResponse_QNAME = new QName("http://srv.uebung3.bank/", "transferResponse");
    private final static QName _IsActive_QNAME = new QName("http://srv.uebung3.bank/", "isActive");
    private final static QName _RemoveAccount_QNAME = new QName("http://srv.uebung3.bank/", "removeAccount");
    private final static QName _CreateAccount_QNAME = new QName("http://srv.uebung3.bank/", "createAccount");
    private final static QName _SetActiveResponse_QNAME = new QName("http://srv.uebung3.bank/", "setActiveResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bank.uebung3.client.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetActiveResponse }
     * 
     */
    public SetActiveResponse createSetActiveResponse() {
        return new SetActiveResponse();
    }

    /**
     * Create an instance of {@link OverdrawException }
     * 
     */
    public OverdrawException createOverdrawException() {
        return new OverdrawException();
    }

    /**
     * Create an instance of {@link GetAccountNumbers }
     * 
     */
    public GetAccountNumbers createGetAccountNumbers() {
        return new GetAccountNumbers();
    }

    /**
     * Create an instance of {@link AccountExistsResponse }
     * 
     */
    public AccountExistsResponse createAccountExistsResponse() {
        return new AccountExistsResponse();
    }

    /**
     * Create an instance of {@link InactiveException }
     * 
     */
    public InactiveException createInactiveException() {
        return new InactiveException();
    }

    /**
     * Create an instance of {@link GetOwnerResponse }
     * 
     */
    public GetOwnerResponse createGetOwnerResponse() {
        return new GetOwnerResponse();
    }

    /**
     * Create an instance of {@link IsActive }
     * 
     */
    public IsActive createIsActive() {
        return new IsActive();
    }

    /**
     * Create an instance of {@link Withdraw }
     * 
     */
    public Withdraw createWithdraw() {
        return new Withdraw();
    }

    /**
     * Create an instance of {@link WithdrawResponse }
     * 
     */
    public WithdrawResponse createWithdrawResponse() {
        return new WithdrawResponse();
    }

    /**
     * Create an instance of {@link GetBalance }
     * 
     */
    public GetBalance createGetBalance() {
        return new GetBalance();
    }

    /**
     * Create an instance of {@link IllegalArgumentException }
     * 
     */
    public IllegalArgumentException createIllegalArgumentException() {
        return new IllegalArgumentException();
    }

    /**
     * Create an instance of {@link GetOwner }
     * 
     */
    public GetOwner createGetOwner() {
        return new GetOwner();
    }

    /**
     * Create an instance of {@link CreateAccountResponse }
     * 
     */
    public CreateAccountResponse createCreateAccountResponse() {
        return new CreateAccountResponse();
    }

    /**
     * Create an instance of {@link DepositResponse }
     * 
     */
    public DepositResponse createDepositResponse() {
        return new DepositResponse();
    }

    /**
     * Create an instance of {@link GetBalanceResponse }
     * 
     */
    public GetBalanceResponse createGetBalanceResponse() {
        return new GetBalanceResponse();
    }

    /**
     * Create an instance of {@link IsActiveResponse }
     * 
     */
    public IsActiveResponse createIsActiveResponse() {
        return new IsActiveResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link CreateAccount }
     * 
     */
    public CreateAccount createCreateAccount() {
        return new CreateAccount();
    }

    /**
     * Create an instance of {@link GetAccountNumbersResponse }
     * 
     */
    public GetAccountNumbersResponse createGetAccountNumbersResponse() {
        return new GetAccountNumbersResponse();
    }

    /**
     * Create an instance of {@link RemoveAccount }
     * 
     */
    public RemoveAccount createRemoveAccount() {
        return new RemoveAccount();
    }

    /**
     * Create an instance of {@link TransferResponse }
     * 
     */
    public TransferResponse createTransferResponse() {
        return new TransferResponse();
    }

    /**
     * Create an instance of {@link AccountExists }
     * 
     */
    public AccountExists createAccountExists() {
        return new AccountExists();
    }

    /**
     * Create an instance of {@link SetActive }
     * 
     */
    public SetActive createSetActive() {
        return new SetActive();
    }

    /**
     * Create an instance of {@link RemoveAccountResponse }
     * 
     */
    public RemoveAccountResponse createRemoveAccountResponse() {
        return new RemoveAccountResponse();
    }

    /**
     * Create an instance of {@link Deposit }
     * 
     */
    public Deposit createDeposit() {
        return new Deposit();
    }

    /**
     * Create an instance of {@link Transfer }
     * 
     */
    public Transfer createTransfer() {
        return new Transfer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IllegalArgumentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "IllegalArgumentException")
    public JAXBElement<IllegalArgumentException> createIllegalArgumentException(IllegalArgumentException value) {
        return new JAXBElement<IllegalArgumentException>(_IllegalArgumentException_QNAME, IllegalArgumentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOwner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "getOwner")
    public JAXBElement<GetOwner> createGetOwner(GetOwner value) {
        return new JAXBElement<GetOwner>(_GetOwner_QNAME, GetOwner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "accountExists")
    public JAXBElement<AccountExists> createAccountExists(AccountExists value) {
        return new JAXBElement<AccountExists>(_AccountExists_QNAME, AccountExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deposit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "deposit")
    public JAXBElement<Deposit> createDeposit(Deposit value) {
        return new JAXBElement<Deposit>(_Deposit_QNAME, Deposit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "removeAccountResponse")
    public JAXBElement<RemoveAccountResponse> createRemoveAccountResponse(RemoveAccountResponse value) {
        return new JAXBElement<RemoveAccountResponse>(_RemoveAccountResponse_QNAME, RemoveAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBalance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "getBalance")
    public JAXBElement<GetBalance> createGetBalance(GetBalance value) {
        return new JAXBElement<GetBalance>(_GetBalance_QNAME, GetBalance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Withdraw }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "withdraw")
    public JAXBElement<Withdraw> createWithdraw(Withdraw value) {
        return new JAXBElement<Withdraw>(_Withdraw_QNAME, Withdraw.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepositResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "depositResponse")
    public JAXBElement<DepositResponse> createDepositResponse(DepositResponse value) {
        return new JAXBElement<DepositResponse>(_DepositResponse_QNAME, DepositResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsActiveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "isActiveResponse")
    public JAXBElement<IsActiveResponse> createIsActiveResponse(IsActiveResponse value) {
        return new JAXBElement<IsActiveResponse>(_IsActiveResponse_QNAME, IsActiveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "createAccountResponse")
    public JAXBElement<CreateAccountResponse> createCreateAccountResponse(CreateAccountResponse value) {
        return new JAXBElement<CreateAccountResponse>(_CreateAccountResponse_QNAME, CreateAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetActive }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "setActive")
    public JAXBElement<SetActive> createSetActive(SetActive value) {
        return new JAXBElement<SetActive>(_SetActive_QNAME, SetActive.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OverdrawException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "OverdrawException")
    public JAXBElement<OverdrawException> createOverdrawException(OverdrawException value) {
        return new JAXBElement<OverdrawException>(_OverdrawException_QNAME, OverdrawException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithdrawResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "withdrawResponse")
    public JAXBElement<WithdrawResponse> createWithdrawResponse(WithdrawResponse value) {
        return new JAXBElement<WithdrawResponse>(_WithdrawResponse_QNAME, WithdrawResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transfer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "transfer")
    public JAXBElement<Transfer> createTransfer(Transfer value) {
        return new JAXBElement<Transfer>(_Transfer_QNAME, Transfer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBalanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "getBalanceResponse")
    public JAXBElement<GetBalanceResponse> createGetBalanceResponse(GetBalanceResponse value) {
        return new JAXBElement<GetBalanceResponse>(_GetBalanceResponse_QNAME, GetBalanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccountNumbersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "getAccountNumbersResponse")
    public JAXBElement<GetAccountNumbersResponse> createGetAccountNumbersResponse(GetAccountNumbersResponse value) {
        return new JAXBElement<GetAccountNumbersResponse>(_GetAccountNumbersResponse_QNAME, GetAccountNumbersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccountNumbers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "getAccountNumbers")
    public JAXBElement<GetAccountNumbers> createGetAccountNumbers(GetAccountNumbers value) {
        return new JAXBElement<GetAccountNumbers>(_GetAccountNumbers_QNAME, GetAccountNumbers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOwnerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "getOwnerResponse")
    public JAXBElement<GetOwnerResponse> createGetOwnerResponse(GetOwnerResponse value) {
        return new JAXBElement<GetOwnerResponse>(_GetOwnerResponse_QNAME, GetOwnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InactiveException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "InactiveException")
    public JAXBElement<InactiveException> createInactiveException(InactiveException value) {
        return new JAXBElement<InactiveException>(_InactiveException_QNAME, InactiveException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountExistsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "accountExistsResponse")
    public JAXBElement<AccountExistsResponse> createAccountExistsResponse(AccountExistsResponse value) {
        return new JAXBElement<AccountExistsResponse>(_AccountExistsResponse_QNAME, AccountExistsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "transferResponse")
    public JAXBElement<TransferResponse> createTransferResponse(TransferResponse value) {
        return new JAXBElement<TransferResponse>(_TransferResponse_QNAME, TransferResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsActive }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "isActive")
    public JAXBElement<IsActive> createIsActive(IsActive value) {
        return new JAXBElement<IsActive>(_IsActive_QNAME, IsActive.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "removeAccount")
    public JAXBElement<RemoveAccount> createRemoveAccount(RemoveAccount value) {
        return new JAXBElement<RemoveAccount>(_RemoveAccount_QNAME, RemoveAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "createAccount")
    public JAXBElement<CreateAccount> createCreateAccount(CreateAccount value) {
        return new JAXBElement<CreateAccount>(_CreateAccount_QNAME, CreateAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetActiveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://srv.uebung3.bank/", name = "setActiveResponse")
    public JAXBElement<SetActiveResponse> createSetActiveResponse(SetActiveResponse value) {
        return new JAXBElement<SetActiveResponse>(_SetActiveResponse_QNAME, SetActiveResponse.class, null, value);
    }

}
