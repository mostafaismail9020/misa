package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicClassData;
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
public class ClassServiceTest extends ServicelayerTransactionalTest {
    private static ClassService classService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        classService = appCtx.getBean("sagiaIsicClassService", ClassService.class);
        classService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateISICActivityData() {
        Collection<IsicClassData> isicClassDataList = classService.getCollection("EN" , Arrays.asList("1"));
        Assert.assertTrue(isicClassDataList.size() > 0);
    }
}
