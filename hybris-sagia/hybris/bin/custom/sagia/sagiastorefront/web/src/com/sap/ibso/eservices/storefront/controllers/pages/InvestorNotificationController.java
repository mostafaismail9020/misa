package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.ibso.eservices.facades.sagia.impl.DefaultLicensePrintingFacade;
import com.sap.ibso.eservices.facades.sagia.impl.SagiaInvestorNotificationsFacade;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationCount;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaInvestorNotificationDTO;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaPopupNotificationData;
import com.sap.ibso.eservices.storefront.controllers.SagiaConstants;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.util.SagiaUtils;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/my-sagia/notifications")
public class InvestorNotificationController extends SagiaAbstractPageController {
    @Resource(name = "sagiaInvestorNotificationsFacade")
    private SagiaInvestorNotificationsFacade investorNotificationsFacade;

    @Resource(name = "sagiaLicensePrintingFacade")
    private DefaultLicensePrintingFacade sagiaLicensePrintingFacade;

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    private static final String SAGIA_INVESTOR_NOTIFICATION = "notifications";
    private static final String SAGIA_INVESTOR_LATEST_NOTIFICATION = "notification";
    private static final String SAGIA_INVESTOR_LATEST_NOTIFICATION_JSON = "notificationJson";
    private static final String SAGIA_INVESTOR_NOTIFICATION_EMPTY_PAGE = "empty-notifications";

    @ModelAttribute("MIGS_Session_JS")
    public String migsSessionUrl() {
        return configurationService.getConfiguration().getString(SagiaConstants.MIGS_SESSION_URL);
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    public String readInvestorNotifications(final Model model) throws CMSItemNotFoundException {
        Collection<SagiaInvestorNotificationDTO> notifications = investorNotificationsFacade.getAllNotifications();
        if (CollectionUtils.isEmpty(notifications)) {
            return getNotificationPage(model, SAGIA_INVESTOR_NOTIFICATION_EMPTY_PAGE);
        }
        model.addAttribute(SAGIA_INVESTOR_NOTIFICATION, notifications);
        return getNotificationPage(model, SAGIA_INVESTOR_NOTIFICATION);
    }

    private String getNotificationPage(Model model, String pageIdentifier) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(pageIdentifier));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(pageIdentifier));
        return getViewForPage(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @RequireHardLogIn
    @ResponseBody
    public List<SagiaInvestorNotificationDTO> getAllNotifications() {
        return investorNotificationsFacade.getAllNotifications();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/popup")
    @RequireHardLogIn
    @ResponseBody
    public SagiaPopupNotificationData getPopupNotificationsData() {
        return investorNotificationsFacade.getPopupNotificationData();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{transactionId}")
    @RequireHardLogIn
    public String readDashboardNotificationData(@PathVariable("transactionId") String transactionId, final Model model) throws CMSItemNotFoundException {
        List<SagiaInvestorNotificationDTO> notifications = investorNotificationsFacade.getAllNotifications();
        Optional<SagiaInvestorNotificationDTO> notification = notifications
                .stream()
                .filter(notif -> notif.getTransactionId().equals(transactionId))
                .findFirst();
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(new Date());

        notification.ifPresent(notif -> {
            if (null == notif.getReadDate()) {
                notif.setReadDate(today);
                investorNotificationsFacade.updateNotification(transactionId, notif);
            }
        });
        if (CollectionUtils.isEmpty(notifications)) {
            return getNotificationPage(model, SAGIA_INVESTOR_NOTIFICATION_EMPTY_PAGE);
        }
        model.addAttribute(SAGIA_INVESTOR_NOTIFICATION, notifications);
        model.addAttribute(SAGIA_INVESTOR_LATEST_NOTIFICATION, notification.orElse(null));
        model.addAttribute(SAGIA_INVESTOR_LATEST_NOTIFICATION_JSON, new Gson().toJson(notification.orElse(null)));
        return getNotificationPage(model, SAGIA_INVESTOR_NOTIFICATION);
    }

    @RequestMapping(path = "/changeNotification/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequireHardLogIn
    public String getSagiaInvestorNotification(@PathVariable String id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        SagiaInvestorNotificationDTO data = investorNotificationsFacade.getNotificationBy(id);
        return gson.toJson(data);
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @RequireHardLogIn
    public void updateNotification(@PathVariable("transactionId") String transactionId, @RequestBody SagiaInvestorNotificationDTO notificationBody) {
        investorNotificationsFacade.updateNotification(transactionId, notificationBody);
    }

    @RequestMapping(value = "/read/all", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @RequireHardLogIn
    public void updateNotification(@RequestBody SagiaInvestorNotificationDTO notificationBody) {
        investorNotificationsFacade.markAllNotificationsAsRead(notificationBody.getReadDate());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/format/json", produces = "application/json")
    @RequireHardLogIn
    public ResponseEntity<Collection<SagiaInvestorNotificationDTO>> getInvestorNotificationAsJson() {
        return new ResponseEntity<>(investorNotificationsFacade.getAllNotifications(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{transactionId}/format/json", produces = "application/json")
    @RequireHardLogIn
    public ResponseEntity<SagiaInvestorNotificationDTO> getSingleInvestorNotificationAsJson(@PathVariable("transactionId") String transactionId) {
        return new ResponseEntity<>(investorNotificationsFacade.getNotificationBy(transactionId), HttpStatus.OK);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
    @RequireHardLogIn
    public ResponseEntity<InvestorNotificationCount> getNotificationsCount() {
        return new ResponseEntity<>(investorNotificationsFacade.getNotificationCount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/print/{transactionId}", method = RequestMethod.GET)
    @RequireHardLogIn
    public void printLicenseWarningLetter(final HttpServletResponse response, @PathVariable("transactionId") String transactionId) {
        SagiaUtils.writeByteArray(response, sagiaLicensePrintingFacade.getWarningLetter(transactionId));
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    @RequireHardLogIn
    public void printWrittenNotification(final HttpServletResponse response) {
        SagiaUtils.writeByteArray(response, sagiaLicensePrintingFacade.getNotificationNotes());
    }
}
