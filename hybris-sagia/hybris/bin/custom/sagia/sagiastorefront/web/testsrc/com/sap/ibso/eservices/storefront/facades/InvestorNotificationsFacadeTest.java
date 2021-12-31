package com.sap.ibso.eservices.storefront.facades;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.sap.ibso.eservices.facades.sagia.impl.SagiaInvestorNotificationsFacade;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationCount;
import com.sap.ibso.eservices.sagiaservices.services.notifications.SagiaCRMInvestorNotificationsService;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaInvestorNotificationDTO;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;

@IntegrationTest
public class InvestorNotificationsFacadeTest {

    private static SagiaCRMInvestorNotificationsService investorNotificationService;
    private static SagiaInvestorNotificationsFacade investorNotificationsFacade;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        investorNotificationService = appCtx.getBean("investorNotificationService", SagiaCRMInvestorNotificationsService.class);
        investorNotificationsFacade = appCtx.getBean("investorNotificationsFacade", SagiaInvestorNotificationsFacade.class);
        investorNotificationService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }
    
    @Test
    public void ShouldGetAllNotifications() {
        Collection<SagiaInvestorNotificationDTO> expandedInvestorNotificationList = investorNotificationsFacade.getAllNotifications();
        Assert.assertTrue(expandedInvestorNotificationList.size() > 0);
    }

    @Test
    public void ShouldGetMandatoryNotifications() {
        Collection<SagiaInvestorNotificationDTO> mandatoryNotificationsList = investorNotificationsFacade.getMandatoryNotifications();
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void ShouldGetNotificationBy() {
    		SagiaInvestorNotificationDTO expandedInvestorNotification = investorNotificationsFacade.getNotificationBy("SAGIA_SR_COMPLETE_SURVEY");
        Assert.assertNotNull(expandedInvestorNotification);
    }

    @Test
    public void ShouldUpdateNotifications() {
    		SagiaInvestorNotificationDTO expandedInvestorNotification = investorNotificationsFacade.getNotificationBy("1");
        investorNotificationsFacade.updateNotification("1", expandedInvestorNotification);
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void ShouldMarkAllNotificationsAsRead() {
        investorNotificationsFacade.markAllNotificationsAsRead("1");
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void ShouldGetNotificationSize() {
        InvestorNotificationCount investorNotificationCount = investorNotificationsFacade.getNotificationCount();
        Assert.assertNotNull(investorNotificationCount);
        assertThat("Exception was not thrown").isNotNull();
    }

}