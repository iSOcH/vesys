
package bank.uebung3.srv.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getBalance", namespace = "http://srv.uebung3.bank/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBalance", namespace = "http://srv.uebung3.bank/")
public class GetBalance {

    @XmlElement(name = "accountNumber", namespace = "")
    private String accountNumber;

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

}
