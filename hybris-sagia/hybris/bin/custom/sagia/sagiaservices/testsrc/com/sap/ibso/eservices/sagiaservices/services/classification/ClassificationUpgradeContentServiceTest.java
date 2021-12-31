package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;

public class ClassificationUpgradeContentServiceTest extends ServicelayerTransactionalTest {

    private static ClassificationUpgradeContentService classificationUpgradeContentService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        classificationUpgradeContentService = appCtx.getBean("classificationUpgradeContentService", ClassificationUpgradeContentService.class);
        classificationUpgradeContentService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldreadContentAttachment() {
        InputStream data = classificationUpgradeContentService.readContentAttachmentBy("1", "1");
        Assert.assertNotNull(data);
    }

}
