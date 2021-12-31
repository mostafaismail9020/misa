package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.io.InputStream;

@IntegrationTest
public class HomeHDRServiceTest extends ServicelayerTransactionalTest {

    private static HomeHDRService homeHDRService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        homeHDRService = appCtx.getBean("homeHDRService", HomeHDRService.class);
        homeHDRService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetHomeHDRData() throws Exception {
        HomeHDRData homeHDRData = homeHDRService.get(HomeHDRData.class, "1");
        Assert.assertEquals("613392", homeHDRData.getBpID());
        Assert.assertEquals(" Shelled, whether or not broken,", homeHDRData.getActivity());
    }

    @Test
    public void shouldReadAttachmentFile() {
        InputStream inputStream = homeHDRService.readAttachmentFile("1", "1");
        Assert.assertNotNull(inputStream);
    }
}