package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZListData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.util.Collection;

@IntegrationTest
public class ClassificationUpgradeServiceTest extends ServicelayerTransactionalTest {

    private static ClassificationUpgradeService classificationUpgradeService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        classificationUpgradeService = appCtx.getBean("classificationUpgradeService", ClassificationUpgradeService.class);
        classificationUpgradeService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // no mocks
    @Ignore
    @Test
    public void shouldPopulateZListData() {
        Collection<ZListData> zListDataList = classificationUpgradeService.getClassificationUpgradeList();
        Assert.assertTrue(zListDataList.size() > 0);
    }
}