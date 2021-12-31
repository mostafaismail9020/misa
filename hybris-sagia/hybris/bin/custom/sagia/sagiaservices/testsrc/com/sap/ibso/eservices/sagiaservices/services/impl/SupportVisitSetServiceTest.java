package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
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

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class SupportVisitSetServiceTest extends ServicelayerTransactionalTest {
    private static SupportVisitSetService supportVisitSetService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        supportVisitSetService = appCtx.getBean("supportVisitSetService", SupportVisitSetService.class);
        supportVisitSetService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateSupportVisitData() {
        Collection<SupportVisitData> supportVisitDataList = supportVisitSetService.getCollection();
        Assert.assertTrue(supportVisitDataList.size() > 0);
    }

    @Test
    public void shouldGetSupportVisitData() {
        SupportVisitData supportVisitData = supportVisitSetService.get("27000045");
        Assert.assertNotNull(supportVisitData);
        Assert.assertEquals("27000045", supportVisitData.getSrId());
    }


    @Test
    public void shouldCreateSupportVisitData() {
        SupportVisitData supportVisitData = supportVisitSetService.get("27000045");
        supportVisitSetService.create(supportVisitData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
