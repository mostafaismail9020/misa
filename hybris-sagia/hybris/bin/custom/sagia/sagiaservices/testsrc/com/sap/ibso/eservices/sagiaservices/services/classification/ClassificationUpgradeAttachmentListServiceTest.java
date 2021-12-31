package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZATT_LISTData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

public class ClassificationUpgradeAttachmentListServiceTest extends ServicelayerTransactionalTest {

    private static ClassificationUpgradeAttachmentListService classificationUpgradeAttachmentListService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        classificationUpgradeAttachmentListService = appCtx.getBean("classificationUpgradeAttachmentListService", ClassificationUpgradeAttachmentListService.class);
        classificationUpgradeAttachmentListService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetZATT_LISTData() {
        Collection<ZATT_LISTData> list = classificationUpgradeAttachmentListService.getClassificationUpgradeAttachmentList();
        Assert.assertTrue(list.size() > 0);
    }

}
