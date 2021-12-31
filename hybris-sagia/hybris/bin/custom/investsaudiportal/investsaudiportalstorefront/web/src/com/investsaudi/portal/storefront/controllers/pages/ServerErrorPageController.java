package com.investsaudi.portal.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/serverError")
public class ServerErrorPageController extends AbstractPageController {
    
    private static final Logger log = LoggerFactory.getLogger(ServerErrorPageController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String get(final Model model, final HttpServletRequest request, final HttpServletResponse response)
                    throws CMSItemNotFoundException
    {
        try {
            storeCmsPageInModel(model, getContentPageForLabelOrId("serverError"));
            setUpMetaDataForContentPage(model, getContentPageForLabelOrId("serverError"));
        } catch (Exception ex) {
            log.error("Error managing error page", ex);
        }

        return "pages/error/portalError";
    }

    @RequestMapping(value = {"/ajax"}, method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody Map<String, Object> showError(HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("assignedErrorCode", request.getAttribute("assignedErrorCode"));
        Object exception = request.getAttribute("exception");
        if(exception instanceof Exception) {
            map.put("message", ((Exception) exception).getMessage());
        }
        response.setStatus(500);
        return map;
    }
}
