
package bank.uebung3.client.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transfer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transfer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transfer", propOrder = {
    "accA",
    "accB",
    "amount"
})
public class Transfer {

    protected String accA;
    protected String accB;
    protected double amount;

    /**
     * Gets the value of the accA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccA() {
        return accA;
    }

    /**
     * Sets the value of the accA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccA(String value) {
        this.accA = value;
    }

    /**
     * Gets the value of the accB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccB() {
        return accB;
    }

    /**
     * Sets the value of the accB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccB(String value) {
        this.accB = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

}
