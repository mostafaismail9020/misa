package com.investsaudi.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.investsaudi.controllers.ControllerConstants;
import com.sap.ibso.eservices.core.model.SagiaRealTimeOnlineSupportComponentModel;


/**
 * @author i313445
 */
@Controller("SagiaRealTimeOnlineSupportComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.SagiaRealTimeOnlineSupportComponent)
public class SagiaRealTimeOnlineSupportComponentController
		extends AbstractCMSComponentController<SagiaRealTimeOnlineSupportComponentModel>
{

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final SagiaRealTimeOnlineSupportComponentModel component)
	{
		//        final CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
		//        TimeSlot timeSlot = new TimeSlot();
		//        if(customerData != null){
		//            timeSlot.setMobileNumber(customerData.getMobileNumber());
		//            timeSlot.setMobileCountryCode(customerData.getMobileCountryCode());
		//        }
		//
		//        model.addAttribute("realTimeScheduleForm", timeSlot);
		//        model.addAttribute("user", getUserService().getCurrentUser());
	}

	@Override
	protected String getView(final SagiaRealTimeOnlineSupportComponentModel component)
	{
		return ControllerConstants.Views.Cms.RealTime.SagiaRealTimeOnlineSupportComponent;
	}

}
