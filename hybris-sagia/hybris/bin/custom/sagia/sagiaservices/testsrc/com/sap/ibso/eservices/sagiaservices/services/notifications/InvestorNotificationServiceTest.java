package com.sap.ibso.eservices.sagiaservices.services.notifications;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@IntegrationTest
public class InvestorNotificationServiceTest extends ServicelayerTransactionalTest {
    private static SagiaCRMInvestorNotificationsService investorNotificationService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        investorNotificationService = appCtx.getBean("sagiaCRMInvestorNotificationsService", SagiaCRMInvestorNotificationsService.class);
        investorNotificationService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetNotificationsFor() {
        InvestorNotificationData investorNotificationData = investorNotificationService.getNotificationBy("1", "1");
        Assert.assertNotNull(investorNotificationData);
        Assert.assertNotNull(investorNotificationData.getInvestorId());
    }

    @Test
    public void shouldGetNotificationBy() {
        Collection<InvestorNotificationData> investorNotificationDataList = investorNotificationService.getNotificationsFor("1");
        Assert.assertTrue(investorNotificationDataList.size() > 0);
    }

    @Test
    public void shouldGetNotificationByType() {
        Collection<InvestorNotificationData> investorNotificationDataList = investorNotificationService.getNotificationsByType("1", "1");
        Assert.assertTrue(investorNotificationDataList.size() > 0);
    }
}
