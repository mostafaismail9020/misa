package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicDivisionData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Collection;

@IntegrationTest
public class DivisionServiceTest extends ServicelayerTransactionalTest {
    private static DivisionService divisionService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        divisionService = appCtx.getBean("sagiaIsicDivisionService", DivisionService.class);
        divisionService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateISICDivisionData() {
        Collection<IsicDivisionData> isicDivisionDataList = divisionService.getCollection("EN" , Arrays.asList("1"), "1");
        Assert.assertTrue(isicDivisionDataList.size() > 0);
    }
}
