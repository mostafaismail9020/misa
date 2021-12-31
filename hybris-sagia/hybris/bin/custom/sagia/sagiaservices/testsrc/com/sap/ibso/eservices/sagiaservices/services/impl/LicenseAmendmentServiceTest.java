package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.LicenseAmendmentData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class LicenseAmendmentServiceTest extends ServicelayerTransactionalTest {

    private static LicenseAmendmentService licenseAmendmentService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        licenseAmendmentService = appCtx.getBean("licenseAmendmentService", LicenseAmendmentService.class);
        licenseAmendmentService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetLicenseAmendment() {
        LicenseAmendmentData licenseAmendmentData = licenseAmendmentService.getLicenseAmendment("1");
        Assert.assertEquals("613392", licenseAmendmentData.getBpID());
        Assert.assertEquals("ZSR5", licenseAmendmentData.getTransType());
    }

    @Test
    public void shouldSaveLicenseAmendment() {
        LicenseAmendmentData licenseAmendmentData = licenseAmendmentService.getLicenseAmendment("1");
        licenseAmendmentService.isInstantAmendment(licenseAmendmentData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
