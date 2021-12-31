package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicBranchData;
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
import java.util.List;

@IntegrationTest
public class BranchServiceTest extends ServicelayerTransactionalTest {
    private static BranchService branchService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        branchService = appCtx.getBean("sagiaIsicBranchService", BranchService.class);
        branchService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateISICBranchData() {
        Collection<IsicBranchData> isicBranchDataList = branchService.getCollection("EN" , Arrays.asList("1"));
        Assert.assertTrue(isicBranchDataList.size() > 0);
    }
}
