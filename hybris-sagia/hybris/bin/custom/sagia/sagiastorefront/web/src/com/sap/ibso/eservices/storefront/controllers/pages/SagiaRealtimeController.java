package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.sagia.services.SagiaAvailabilityService;
import com.sap.ibso.eservices.facades.data.TimeSlot;
import com.sap.ibso.eservices.facades.sagia.RealTimeScheduleFacade;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.servicelayer.i18n.L10NService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/realtime")
public class SagiaRealtimeController  extends AbstractPageController {
    @Resource(name = "realTimeScheduleFacade")
    private RealTimeScheduleFacade realTimeScheduleFacade;

    @Resource(name = "sagiaAvailabilityService")
    private SagiaAvailabilityService sagiaAvailabilityService;

    @Resource(name = "salgiaCallBackValidator")
    private Validator salgiaCallBackValidator;

    @Resource(name = "l10nService")
    private L10NService l10nService;

    @Autowired
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @RequestMapping(value = "/connectivity",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> getRealtimeConnectivityDetails() {
        Map<String, Object> map = new HashMap<>();
        SagiaConfigurationFacade facade = getSagiaConfigurationFacade();
        map.put("realtimeEmailUs", facade.getRealtimeEmailUs());
        map.put("realtimeCallLocal", facade.getRealtimeCallLocal());
        map.put("realtimeCallInternational", facade.getRealtimeCallInternational());
        map.put("availabilityByDayOfWeek", getSagiaAvailabilityService().getTimeSlotAvailabilities());
        return map;
    }

    @RequestMapping(value = "/availability",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> getRealtimeAvailability() {
        Map<String, Object> map = new HashMap<>();
        map.put("isCallbackAvailable", getSagiaAvailabilityService().isAvailable(SagiaCoreConstants.CALLBACK));
        map.put("isLiveChatAvailable", getSagiaAvailabilityService().isAvailable(SagiaCoreConstants.LIVECHAT));
        return map;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequireHardLogIn
    public ResponseEntity saveTimeSlot(@ModelAttribute("realTimeScheduleForm") final TimeSlot realTimeScheduleForm, final Model model, final BindingResult result) {
        getSalgiaCallBackValidator().validate(realTimeScheduleForm, result);
        if (!result.hasErrors()) {
            final String responseMessage = realTimeScheduleFacade.saveScheduleCall(realTimeScheduleForm);
            if(responseMessage != null){
                return new ResponseEntity(HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new SagiaRuntimeException(l10nService.getLocalizedString("realtime.callback.data.invalid"));
        }
    }

    public SagiaAvailabilityService getSagiaAvailabilityService() {
        return sagiaAvailabilityService;
    }
    public SagiaConfigurationFacade getSagiaConfigurationFacade() {
        return sagiaConfigurationFacade;
    }
    public Validator getSalgiaCallBackValidator() {
        return salgiaCallBackValidator;
    }

}
