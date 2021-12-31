package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZAPPEALData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

public class ClassificationUpgradeAppealServiceTest  extends ServicelayerTransactionalTest {

    private static ClassificationUpgradeAppealService classificationUpgradeAppealService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        classificationUpgradeAppealService = appCtx.getBean("classificationUpgradeAppealService", ClassificationUpgradeAppealService.class);
        classificationUpgradeAppealService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetZAPPEALData() {
        Collection<ZAPPEALData> list = classificationUpgradeAppealService.getClassificationUpgradeAppealList();
        Assert.assertTrue(list.size() > 0);
    }
}
