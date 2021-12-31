
package com.sap.ibso.eservices.soapservices.pojo.webcallback;

import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the icisysteminterface package. 
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

    private final static QName _User_QNAME = new QName("urn:IciSystemInterface", "user");
    private final static QName _Language_QNAME = new QName("urn:IciSystemInterface", "language");



    /**
     * Create an instance of {@link CreateCallBackResponse }
     *
     */
    public CreateCallBackResponse createCreateCallBackResponse() {
        return new CreateCallBackResponse();
    }

    /**
     * Create an instance of {@link CreateCallBack }
     *
     */
    public CreateCallBack createCreateCallBack() {
        return new CreateCallBack();
    }


}
