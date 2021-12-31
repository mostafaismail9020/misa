package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_DETSETData;
import com.sap.ibso.eservices.sagiaservices.services.classification.dto.ClassificationUpgradeFormData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class ClassificationUpgradeDetailServiceTest extends ServicelayerTransactionalTest {

    private static ClassificationUpgradeDetailService classificationUpgradeDetailService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        classificationUpgradeDetailService = appCtx.getBean("classificationUpgradeDetailService", ClassificationUpgradeDetailService.class);
        classificationUpgradeDetailService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetZCLASS_DETSETData() {
        ZCLASS_DETSETData data = classificationUpgradeDetailService.getClassificationUpgradeBy("ID");
        Assert.assertNotNull(data);
        Assert.assertEquals("A+", data.getClassProperty());
    }

    @Test
    public void shouldCreateZCLASS_DETSETData() {
        ClassificationUpgradeFormData classificationUpgradeFormData = new ClassificationUpgradeFormData();
        classificationUpgradeFormData.setFiles(new ArrayList<>());
        classificationUpgradeFormData.setAttachments(new ArrayList<>());
        classificationUpgradeDetailService.createClassificationUpgrade(classificationUpgradeFormData, new ArrayList<>());
        assertThat("Exception was not thrown").isNotNull();
    }
}