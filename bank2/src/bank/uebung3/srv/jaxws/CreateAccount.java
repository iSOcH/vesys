
package bank.uebung3.srv.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createAccount", namespace = "http://srv.uebung3.bank/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createAccount", namespace = "http://srv.uebung3.bank/")
public class CreateAccount {

    @XmlElement(name = "owner", namespace = "")
    private String owner;

    /**
     * 
     * @return
     *     returns String
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * 
     * @param owner
     *     the value for the owner property
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

}
