package com.sap.ibso.eservices.storefront.controllers.cms;

import com.sap.ibso.eservices.core.model.SagiaRealTimeOnlineSupportComponentModel;
import com.sap.ibso.eservices.facades.data.TimeSlot;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author i313445
 */
@Controller("SagiaRealTimeOnlineSupportComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.SagiaRealTimeOnlineSupportComponent)
public class SagiaRealTimeOnlineSupportComponentController extends AbstractCMSComponentController<SagiaRealTimeOnlineSupportComponentModel> {

    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final SagiaRealTimeOnlineSupportComponentModel component) {
        final CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
        TimeSlot timeSlot = new TimeSlot();
        if(customerData != null){
            timeSlot.setMobileNumber(customerData.getMobileNumber());
            timeSlot.setMobileCountryCode(customerData.getMobileCountryCode());
        }

        model.addAttribute("realTimeScheduleForm", timeSlot);
        model.addAttribute("user", getUserService().getCurrentUser());
    }

    @Override
    protected String getView(final SagiaRealTimeOnlineSupportComponentModel component) {
        return ControllerConstants.Views.Cms.RealTime.SagiaRealTimeOnlineSupportComponent;
    }

    public UserService getUserService() {
        return userService;
    }

    public SagiaConfigurationFacade getSagiaConfigurationFacade() {
        return sagiaConfigurationFacade;
    }
}
