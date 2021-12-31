package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpAttachmentData;
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
import java.util.Collection;

@IntegrationTest
public class SpecialServiceAttachmentServiceTest extends ServicelayerTransactionalTest {

    private static SpecialServiceAttachmentService specialServiceAttachmentService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        specialServiceAttachmentService = appCtx.getBean("specialServiceAttachment", SpecialServiceAttachmentService.class);
        specialServiceAttachmentService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReadContentAttachment() {
        InputStream data = specialServiceAttachmentService.readAttachmentBy("1", "1");
        Assert.assertNotNull(data);
    }

    @Test
    public void shouldPopulateSpAttachmentData() {
        Collection<SpAttachmentData> spAttachmentDataList = specialServiceAttachmentService.getCollection("DE", "1", "1", "2");
        Assert.assertTrue(spAttachmentDataList.size() > 0);
    }
}
