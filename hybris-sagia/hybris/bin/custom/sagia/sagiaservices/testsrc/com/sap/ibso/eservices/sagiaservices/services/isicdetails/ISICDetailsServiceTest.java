package com.sap.ibso.eservices.sagiaservices.services.isicdetails;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ISICDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.isicdetails.dto.ISICDetailsRequestParameters;
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
public class ISICDetailsServiceTest extends ServicelayerTransactionalTest {
    private static ISICDetailsService iSICDetailsService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        iSICDetailsService = appCtx.getBean("iSICDetailsService", ISICDetailsService.class);
        iSICDetailsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateISICDetailsData() {
        ISICDetailsRequestParameters functionParameters = new ISICDetailsRequestParameters();
        functionParameters.setLanguage("EN");
        functionParameters.setFlag("EN");
        functionParameters.setLictype("EN");
        functionParameters.setActivity("EN");
        functionParameters.setComplimentary("EN");
        functionParameters.setDivision("EN");
        functionParameters.setSection("EN");

        Collection<ISICDetailsData> ISICDetailsDataList = iSICDetailsService.getISICDetailsList(functionParameters);
        Assert.assertTrue(ISICDetailsDataList.size() > 0);
    }
}
