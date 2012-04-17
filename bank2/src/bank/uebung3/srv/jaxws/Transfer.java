
package bank.uebung3.srv.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "transfer", namespace = "http://srv.uebung3.bank/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transfer", namespace = "http://srv.uebung3.bank/", propOrder = {
    "accA",
    "accB",
    "amount"
})
public class Transfer {

    @XmlElement(name = "accA", namespace = "")
    private String accA;
    @XmlElement(name = "accB", namespace = "")
    private String accB;
    @XmlElement(name = "amount", namespace = "")
    private double amount;

    /**
     * 
     * @return
     *     returns String
     */
    public String getAccA() {
        return this.accA;
    }

    /**
     * 
     * @param accA
     *     the value for the accA property
     */
    public void setAccA(String accA) {
        this.accA = accA;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getAccB() {
        return this.accB;
    }

    /**
     * 
     * @param accB
     *     the value for the accB property
     */
    public void setAccB(String accB) {
        this.accB = accB;
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
