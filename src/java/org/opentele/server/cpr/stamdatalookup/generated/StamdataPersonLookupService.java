package org.opentele.server.cpr.stamdatalookup.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-07-03T08:18:11.779+02:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "StamdataPersonLookupService", 
                  wsdlLocation = "StamdataPersonLookup.wsdl",
                  targetNamespace = "http://nsi.dk/2011/09/23/StamdataCpr/") 
public class StamdataPersonLookupService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://nsi.dk/2011/09/23/StamdataCpr/", "StamdataPersonLookupService");
    public final static QName StamdataPersonLookup = new QName("http://nsi.dk/2011/09/23/StamdataCpr/", "StamdataPersonLookup");
    static {
        URL url = StamdataPersonLookupService.class.getResource("StamdataPersonLookup.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(StamdataPersonLookupService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "StamdataPersonLookup.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public StamdataPersonLookupService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public StamdataPersonLookupService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StamdataPersonLookupService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns StamdataPersonLookup
     */
    @WebEndpoint(name = "StamdataPersonLookup")
    public StamdataPersonLookup getStamdataPersonLookup() {
        return super.getPort(StamdataPersonLookup, StamdataPersonLookup.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StamdataPersonLookup
     */
    @WebEndpoint(name = "StamdataPersonLookup")
    public StamdataPersonLookup getStamdataPersonLookup(WebServiceFeature... features) {
        return super.getPort(StamdataPersonLookup, StamdataPersonLookup.class, features);
    }

}
