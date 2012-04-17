
package bank.uebung3.srv.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "setActive", namespace = "http://srv.uebung3.bank/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setActive", namespace = "http://srv.uebung3.bank/", propOrder = {
    "accountNumber",
    "active"
})
public class SetActive {

    @XmlElement(name = "accountNumber", namespace = "")
    private String accountNumber;
    @XmlElement(name = "active", namespace = "")
    private boolean active;

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
     *     returns boolean
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * 
     * @param active
     *     the value for the active property
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
