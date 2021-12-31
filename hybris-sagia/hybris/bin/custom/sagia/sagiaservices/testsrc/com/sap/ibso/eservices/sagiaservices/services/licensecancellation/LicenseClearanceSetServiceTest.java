package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class LicenseClearanceSetServiceTest extends ServicelayerTransactionalTest {

    private static LicenseClearanceSetService licenseClearanceSetService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        licenseClearanceSetService = appCtx.getBean("licenseClearanceSetService", LicenseClearanceSetService.class);
        licenseClearanceSetService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetLicenseClearanceSetData() {
        LicenseClearanceSetData licenseClearanceSetData = licenseClearanceSetService.get("1", "1");
        Assert.assertTrue(licenseClearanceSetData != null);
    }

    @Test
    public void shouldCreateLicenseClearance() {
        LicenseClearanceFormData licenseClearanceFormData = new LicenseClearanceFormData();
        licenseClearanceFormData.setFiles(Arrays.asList(new MockMultipartFile("file", new byte[0])));
        CustomizingGetData customizingGetData = new CustomizingGetData();
        customizingGetData.setDescription("desc");
        customizingGetData.setDockey_ID("1");
        customizingGetData.setFieldkey("1");
        customizingGetData.setFieldname("desc");
        Collection<CustomizingGetData> supportedAttachments = Arrays.asList(customizingGetData);
        licenseClearanceSetService.createLicenseClearance(licenseClearanceFormData, supportedAttachments);
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldUpdateGlobalLicenseCancellation() {
        LicenseClearanceSetData licenseClearanceSetData = licenseClearanceSetService.get("1", "1");
        licenseClearanceSetService.updateGlobalLicenseCancellationData("1", "1", licenseClearanceSetData);
        assertThat("Exception was not thrown").isNotNull();
    }
}