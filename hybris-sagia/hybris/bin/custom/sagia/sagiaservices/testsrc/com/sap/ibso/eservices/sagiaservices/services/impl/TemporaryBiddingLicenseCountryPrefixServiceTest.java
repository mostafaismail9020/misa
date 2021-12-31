package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseCountryPrefixData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

@IntegrationTest
public class TemporaryBiddingLicenseCountryPrefixServiceTest extends ServicelayerTransactionalTest {

    private static TemporaryBiddingLicenseCountryPrefixService temporaryBiddingLicenseCountryPrefixService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        temporaryBiddingLicenseCountryPrefixService = appCtx.getBean("temporaryBiddingLicenseCountryPrefixService", TemporaryBiddingLicenseCountryPrefixService.class);
        temporaryBiddingLicenseCountryPrefixService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetCountryPrefix() {
        TemporaryBiddingLicenseCountryPrefixData data = temporaryBiddingLicenseCountryPrefixService.getCountryPrefix("AL");
        Assert.assertEquals("355", data.getTelNo());
    }
}