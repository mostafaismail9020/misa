package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.facades.data.BasicCompanyData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.apache.commons.lang.StringUtils;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class OrgInfoServiceTest extends ServicelayerTransactionalTest {

    private static OrgInfoService orgInfoService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        orgInfoService = appCtx.getBean("orgInfoService", OrgInfoService.class);
        orgInfoService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetOrgInfoData() {
        OrgInfoData data = orgInfoService.get();
        Assert.assertEquals("6000003610", data.getRefID());
        String payload = orgInfoService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
    }

    @Ignore
    @Test
    public void shouldSaveOrgInfoData() {
        BasicCompanyData basicCompanyFormData = new BasicCompanyData();
        orgInfoService.updateOrgInfoData(basicCompanyFormData);
        assertThat("Exception was not thrown").isNotNull();
    }
}