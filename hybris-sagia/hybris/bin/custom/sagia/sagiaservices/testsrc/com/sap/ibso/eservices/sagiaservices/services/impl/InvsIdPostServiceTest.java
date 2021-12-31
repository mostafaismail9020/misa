package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.InvsIdPostData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class InvsIdPostServiceTest extends ServicelayerTransactionalTest {

    private static InvsIdPostService invsIdPostService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        invsIdPostService = appCtx.getBean("invsIdPostService", InvsIdPostService.class);
        invsIdPostService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateInvsIdPostData() {
        InvsIdPostData invsIdPostData = new InvsIdPostData();
        String result = invsIdPostService.save(invsIdPostData);
        Assert.assertNotNull(result);
        assertThat("Exception was not thrown").isNotNull();
    }
}