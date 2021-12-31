package com.sap.ibso.eservices.sagiaservices.services.license;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ConvToNationalsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ConvertToNationalsServiceTest extends ServicelayerTransactionalTest {
    private static ConvertToNationalsService convertToNationalsService;
    private static CustomizationListService customizationListService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        convertToNationalsService = appCtx.getBean("convertToNationalsService", ConvertToNationalsService.class);
        customizationListService = appCtx.getBean("customizationListService", CustomizationListService.class);
        convertToNationalsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
        customizationListService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateConvToNationalsData() {
        Collection<ConvToNationalsData> convToNationalsDataList = convertToNationalsService.getConvertToNationalHistory();
        Assert.assertTrue(convToNationalsDataList.size() > 0);
        ConvToNationalsData data = convToNationalsDataList.iterator().next();
        String payload = convertToNationalsService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("SrGuid"));
    }

    @Test
    public void shouldGetConvertToNationalBy() {
        ConvToNationalsData convToNationalsData = convertToNationalsService.getConvertToNationalBy("40000200");
        Assert.assertEquals("40000200", convToNationalsData.getSrID());
    }

    @Test
    public void shouldUpdateConvToNationalsData() {
        ConvToNationalsResubmitFormData convToNationalsResubmitFormData = new ConvToNationalsResubmitFormData();
        convToNationalsResubmitFormData.setSrGuid("1");
        convToNationalsResubmitFormData.setAttachments(new ArrayList<>());
        convToNationalsResubmitFormData.setFiles(new ArrayList<>());
        convertToNationalsService.update(convToNationalsResubmitFormData, new ArrayList<>());
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldCreateConvToNationalsData() {
        ConvToNationalsFormData convToNationalsFormData = new ConvToNationalsFormData();
        convToNationalsFormData.setSrID("1");
        convToNationalsFormData.setTransType("1");
        convToNationalsFormData.setFiles(new ArrayList<>());
        convertToNationalsService.create(convToNationalsFormData, new ArrayList<>());
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldCreateDetailedConvNationalsData() {
        ConvToNationalsData convToNationalData = convertToNationalsService.getConvertToNationalBy("40000200");
        Collection<CustomizingGetData> serviceDetails = customizationListService.readConvToNationalsInfo();
        DetailedConvToNationalsData expandedConvNationals = ConvToNationalsConverter.createDetailedConvNationalsData(convToNationalData, serviceDetails);
        Assert.assertNotNull(expandedConvNationals);
        assertThat("Exception was not thrown").isNotNull();
    }
}
