package io.github.navpil.restrictvalve;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.RequestFilterValve;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class RemoteAddrStrictUrlValve extends RequestFilterValve {

    protected volatile String restrictUrl;
    protected volatile String restrictUrlWithSlash;

    public void setRestrictUrl(String restrictUrl) {
        this.restrictUrl = restrictUrl;
        this.restrictUrlWithSlash = restrictUrl + '/';
    }

    private static final Log log = LogFactory.getLog(RemoteAddrStrictUrlValve.class);

    public RemoteAddrStrictUrlValve() {
    }

    public void invoke(Request request, Response response) throws IOException, ServletException {
        if (request.getRequest().getRequestURI().equals(restrictUrl)
                || request.getRequest().getRequestURI().equals(restrictUrlWithSlash)) {
            String property;
            if (this.getAddConnectorPort()) {
                property = request.getRequest().getRemoteAddr() + ";" + request.getConnector().getPort();
            } else {
                property = request.getRequest().getRemoteAddr();
            }

            this.process(property, request, response);
        } else {
            getNext().invoke(request, response);
        }
    }

    protected Log getLog() {
        return log;
    }
}
