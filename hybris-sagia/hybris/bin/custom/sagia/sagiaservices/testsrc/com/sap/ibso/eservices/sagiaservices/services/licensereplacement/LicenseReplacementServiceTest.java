package com.sap.ibso.eservices.sagiaservices.services.licensereplacement;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class LicenseReplacementServiceTest extends ServicelayerTransactionalTest {
    private static LicenseReplacementService licenseReplacementService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        licenseReplacementService = appCtx.getBean("licenseReplacementService", LicenseReplacementService.class);
        licenseReplacementService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetNotificationsFor() {
        LicenseReplaceMentData licenseReplaceMentData = licenseReplacementService.get("1");
        Assert.assertNotNull(licenseReplaceMentData);
        Assert.assertEquals("50000089", licenseReplaceMentData.getSrID());
    }

    @Test
    public void shouldUpdateLicenseReplaceMentData() {
        LicenseReplacementResubmitFormData formData = new LicenseReplacementResubmitFormData();
        formData.setSrGuid("1");
        formData.setFiles(new ArrayList<>());
        licenseReplacementService.updateLicenseReplacement(formData, new ArrayList<>());
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldCreateLicenseReplaceMentData() {
        LicenseReplaceMentData licenseReplaceMentData = licenseReplacementService.get("1");
        LicenseReplacementFormData licenseReplacementFormData = new LicenseReplacementFormData();
        licenseReplacementFormData.setSrID(licenseReplaceMentData.getSrID());
        licenseReplacementFormData.setFiles(new ArrayList<>());
        licenseReplacementFormData.setTransType(licenseReplaceMentData.getTransType());
        licenseReplacementService.createLicenseReplacement(licenseReplacementFormData, new ArrayList<>());
        assertThat("Exception was not thrown").isNotNull();
    }
}