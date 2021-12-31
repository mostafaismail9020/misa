package com.investsaudi.portal.storefront.exceptions;

import de.hybris.platform.util.Config;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class PortalExceptionHandler extends DefaultHandlerExceptionResolver {

    private final static Logger log = LoggerFactory.getLogger(PortalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final String handleAllExceptions(Exception ex, WebRequest request, Model model) throws Exception {
        boolean responseCommitted = false;
        if (request instanceof ServletWebRequest) {
            responseCommitted = ((ServletWebRequest) request).getResponse().isCommitted();
        }
        model.addAttribute("responseCommitted", responseCommitted);
        String assignedErrorCode = "ERR-" + RandomStringUtils.randomAlphabetic(12).toUpperCase();
        model.addAttribute("assignedErrorCode", assignedErrorCode);
        model.addAttribute("exception", ex);

        if(Config.getBoolean("portal.display.error.stacktrace", false)) {
            StringWriter stacktrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(stacktrace));
            model.addAttribute("stacktrace", stacktrace.toString());
        }

        log.error("Error code: [{}], Request info: [{}]", assignedErrorCode, request.getDescription( Config.getBoolean("portal.display.error.clientinfo", false)), ex);

        if (isAjax(request)) {
            return "forward:/serverError/ajax";
        }

        return "forward:/serverError";
    }

    private boolean isAjax(WebRequest request){
        return request != null && StringUtils.equals(request.getHeader("x-requested-with"), "XMLHttpRequest");
    }
}
