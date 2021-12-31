package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicActivityData;
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
public class ActivityServiceTest extends ServicelayerTransactionalTest {
    private static ActivityService activityService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        activityService = appCtx.getBean("sagiaIsicActivityService", ActivityService.class);
        activityService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateISICActivityData() {
        Collection<IsicActivityData> isicActivityDataList = activityService.getCollection("EN" , Arrays.asList("1"), "1");
        Assert.assertTrue(isicActivityDataList.size() > 0);
    }
}
