package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ShareholderData;
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
public class LicenseAmendmentShareholderServiceTest extends ServicelayerTransactionalTest {

    private static LicenseAmendmentShareholderService licenseAmendmentShareholderService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        licenseAmendmentShareholderService = appCtx.getBean("licenseAmendmentShareholderService", LicenseAmendmentShareholderService.class);
        licenseAmendmentShareholderService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetLicenseAmendmentShareholder() {
        ShareholderData shareholderData = licenseAmendmentShareholderService.getLicenseAmendmentShareholder("1");
        Assert.assertEquals("613392", shareholderData.getBpID());
        Assert.assertEquals("621559", shareholderData.getShBpID());
    }
}