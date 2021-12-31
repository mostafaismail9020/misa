package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FollowUpServicesData;
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
public class FollowUpServiceTest extends ServicelayerTransactionalTest {

    private static FollowUpService followUpService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        followUpService = appCtx.getBean("followUpService", FollowUpService.class);
        followUpService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateFollowUpServicesData() {
        Collection<FollowUpServicesData> followUpServicesDataList = followUpService.getViolationRepliesEntries();
        Assert.assertTrue(followUpServicesDataList.size() > 0);
    }

    @Test
    public void shouldGetWarningLetterExtensionEntries() {
        Collection<FollowUpServicesData> warningLetterExtensionEntries = followUpService.getWarningLetterExtensionEntries();
        Assert.assertTrue(warningLetterExtensionEntries.size() > 0);
    }

    @Test
    public void shouldGetSingleEntry() {
        FollowUpServicesData followUpServicesData = followUpService.getSingleEntry("1");
        Assert.assertEquals("Reject", followUpServicesData.getComments());
    }
}