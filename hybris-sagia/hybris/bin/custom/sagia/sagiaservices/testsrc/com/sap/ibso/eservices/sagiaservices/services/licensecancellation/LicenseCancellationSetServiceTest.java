package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseCancellationSetData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class LicenseCancellationSetServiceTest extends ServicelayerTransactionalTest {
    private static LicenseCancellationSetService licenseCancellationSetService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        licenseCancellationSetService = appCtx.getBean("licenseCancellationSetService", LicenseCancellationSetService.class);
        licenseCancellationSetService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // missing json
    @Ignore
    @Test
    public void shouldPopulateLicenseCancellationSetData() {
        Collection<LicenseCancellationSetData> licenseCancellationSetDataList = licenseCancellationSetService.getCollection();
        Assert.assertTrue(licenseCancellationSetDataList.size() > 0);
    }

    @Test
    public void shouldGetLatestLicenseCancellationSetData() {
        LicenseCancellationSetData licenseCancellationSetData = licenseCancellationSetService.getLatestEntityCreated();
        Assert.assertTrue(licenseCancellationSetData != null);
    }

    @Test
    public void shouldGetLicenseCancellationSetData() {
        LicenseCancellationSetData licenseCancellationSetData = licenseCancellationSetService.get("1", "1");
        Assert.assertTrue(licenseCancellationSetData != null);
    }

    @Test
    public void shouldCreateLicenseCancellation() {
        LicenseCancelFormData licenseCancelFormData = new LicenseCancelFormData();
        licenseCancelFormData.setFiles(Arrays.asList(new MockMultipartFile("file", new byte[0])));
        CustomizingGetData customizingGetData = new CustomizingGetData();
        customizingGetData.setDescription("desc");
        customizingGetData.setDockey_ID("1");
        customizingGetData.setFieldkey("1");
        customizingGetData.setFieldname("desc");
        Collection<CustomizingGetData> supportedAttachments = Arrays.asList(customizingGetData);
        licenseCancellationSetService.createLicenseCancellation(licenseCancelFormData, supportedAttachments);
        assertThat("Exception was not thrown").isNotNull();
    }
}