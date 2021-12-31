package com.sap.ibso.eservices.soapservices.ws.request;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RequestHeaderForWebcallback implements InitializingBean, HttpRequestInterceptor {
    protected static final Logger LOG = Logger.getLogger(RequestHeaderForWebcallback.class);



    /**
     * HTTP POST URI
     */
    private String uri;

    /**
     * HTTP host header value
     */
    private String host;


    @Override
    public void afterPropertiesSet() throws Exception
    {
        final String uriWithProtocolRemoved = uri.substring(uri.indexOf(":") + 3);
        host = uriWithProtocolRemoved.substring(0, uriWithProtocolRemoved.indexOf("/"));
    }

    @Override
    public void process(final HttpRequest httpRequest, final HttpContext httpContext) throws HttpException, IOException
    {
        LOG.debug("Processing request");
        HttpRequestWrapper wrapper = (HttpRequestWrapper) httpRequest;

        try
        {
            // correctly set the POST URI
            wrapper.setURI(new URI(uri));
        }
        catch (URISyntaxException e)
        {
            LOG.error(e);
        }

        final Header[] headers = getHeaders(httpRequest.getFirstHeader("Content-Length").getValue());
        httpRequest.setHeaders(headers);
    }

    private Header[] getHeaders(final String contentLength)
    {
        return new Header[] {
                 new BasicHeader("Content-Type", "text/xml;charset=UTF-8")
                , new BasicHeader("SOAPAction", "\"http://inqmy.com/soapdispatcher/rpc/bcbici/IciSystemBean\"")
                , new BasicHeader("Content-Length", contentLength)
                , new BasicHeader("Host", host)
//                , new BasicHeader("Connection", "Keep-Alive")
//                , new BasicHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)")
        };
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
