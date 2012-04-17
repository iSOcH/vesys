
package bank.uebung3.srv.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deposit", namespace = "http://srv.uebung3.bank/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deposit", namespace = "http://srv.uebung3.bank/", propOrder = {
    "accountNumber",
    "amount"
})
public class Deposit {

    @XmlElement(name = "accountNumber", namespace = "")
    private String accountNumber;
    @XmlElement(name = "amount", namespace = "")
    private double amount;

    /**
     * 
     * @return
     *     returns String
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * 
     * @param accountNumber
     *     the value for the accountNumber property
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 
     * @return
     *     returns double
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * 
     * @param amount
     *     the value for the amount property
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

}
