package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpecialServiceHeaderData;
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
public class SpecialServiceHeaderServiceTest extends ServicelayerTransactionalTest {

    private static SpecialServiceHeaderService specialServiceHeaderService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        specialServiceHeaderService = appCtx.getBean("specialServiceHeader", SpecialServiceHeaderService.class);
        specialServiceHeaderService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldGetSpecialServiceHeaderData() {
        SpecialServiceHeaderData data = specialServiceHeaderService.get(9000100);
        Assert.assertEquals("Completed", data.getSTATUS());
    }


    @Test
    public void shouldSaveSpecialService() {
        SpecialServiceHeaderData data = specialServiceHeaderService.get(9000100);
        specialServiceHeaderService.saveSpecialService(data);
        assertThat("Exception was not thrown").isNotNull();
    }
}