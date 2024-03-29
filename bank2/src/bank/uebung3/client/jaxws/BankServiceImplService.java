
package bank.uebung3.client.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "BankServiceImplService", targetNamespace = "http://srv.uebung3.bank/", wsdlLocation = "http://127.0.0.1:9876/bankservice?wsdl")
public class BankServiceImplService
    extends Service
{

    private final static URL BANKSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(bank.uebung3.client.jaxws.BankServiceImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = bank.uebung3.client.jaxws.BankServiceImplService.class.getResource(".");
            url = new URL(baseUrl, "http://127.0.0.1:9876/bankservice?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://127.0.0.1:9876/bankservice?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        BANKSERVICEIMPLSERVICE_WSDL_LOCATION = url;
    }

    public BankServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BankServiceImplService() {
        super(BANKSERVICEIMPLSERVICE_WSDL_LOCATION, new QName("http://srv.uebung3.bank/", "BankServiceImplService"));
    }

    /**
     * 
     * @return
     *     returns BankServiceImpl
     */
    @WebEndpoint(name = "BankServiceImplPort")
    public BankServiceImpl getBankServiceImplPort() {
        return super.getPort(new QName("http://srv.uebung3.bank/", "BankServiceImplPort"), BankServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BankServiceImpl
     */
    @WebEndpoint(name = "BankServiceImplPort")
    public BankServiceImpl getBankServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://srv.uebung3.bank/", "BankServiceImplPort"), BankServiceImpl.class, features);
    }

}
