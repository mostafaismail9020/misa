package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
@RequestMapping(value = "sagia-error")
public class SagiaErrorController extends SagiaAbstractPageController {
    private static final String SAGIA_GENERIC_EXCEPTION_PAGE = "sagia-generic-exception";
    private static final String SAGIA_ODATA_EXCEPTION_PAGE = "sagia-odata-exception";
    private static final Logger LOG = Logger.getLogger(SagiaErrorController.class);

    private void setStacktraceToModel(Model model, final HttpServletRequest request) {
        try {
            Exception exception = (Exception) request.getAttribute("exception");
            if(LOG.isDebugEnabled()) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                exception.printStackTrace(printWriter);
                model.addAttribute("errorMessage", exception.getMessage() + "<br/><br/>" + stringWriter.toString());
            } else {
                model.addAttribute("errorMessage", exception.getMessage());
            }
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
            model.addAttribute("errorMessage", e.getMessage());
        }
    }

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, path = "/generic")
    public String getGeneric(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException {
        setStacktraceToModel(model, request);
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_GENERIC_EXCEPTION_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_GENERIC_EXCEPTION_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, path = "/odata")
    public String getOData(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException {
        setStacktraceToModel(model, request);
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_ODATA_EXCEPTION_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_ODATA_EXCEPTION_PAGE));
        return getViewForPage(model);
    }
}
