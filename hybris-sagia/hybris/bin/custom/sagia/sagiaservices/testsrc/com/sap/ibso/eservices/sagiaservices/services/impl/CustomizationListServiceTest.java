package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.util.Collection;

@IntegrationTest
public class CustomizationListServiceTest {

    private static CustomizationListService customizationListService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        customizationListService = appCtx.getBean("customizationListService", CustomizationListService.class);
        customizationListService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetLicenseAmendmentListItems() {
        Collection<CustomizingGetData> customizingGets = customizationListService.getLicenseAmendmentListItems();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadBranches() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readBranches();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadComplaintsSupportingAttachments() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readComplaintsSupportingAttachments();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadConvToNationalsInfo() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readConvToNationalsInfo();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadConvToNationalsSupportingAttachments() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readConvToNationalsSupportingAttachments();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadLicenseReplacementInfo() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readLicenseReplacementInfo();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadLicenseReplacementSupportingAttachments() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readLicenseReplacementSupportingAttachments();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadCancelLicenseSupportingAttachments() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readCancelLicenseSupportingAttachments();
        Assert.assertTrue(customizingGets.size() > 0);
    }

    @Test
    public void shouldReadLicenseRenewalSupportingAttachments() {
        Collection<CustomizingGetData> customizingGets = customizationListService.readLicenseRenewalSupportingAttachments();
        Assert.assertTrue(customizingGets.size() > 0);
    }
}

