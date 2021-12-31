package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtServiceUploadData;
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

@IntegrationTest
public class SagiaSupportingDocumentsServiceTest extends ServicelayerTransactionalTest {

    private static SagiaSupportingDocumentsService sagiaSupportingDocumentsService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        sagiaSupportingDocumentsService = appCtx.getBean("sagiaSupportingDocumentsService", SagiaSupportingDocumentsService.class);
        sagiaSupportingDocumentsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldPopulateGovtServiceUploadData() {
        Collection<GovtServiceUploadData> sagiaSupportingDocumentsServiceCollection = sagiaSupportingDocumentsService.getFilesToBeUploaded("01");
        Assert.assertTrue(sagiaSupportingDocumentsServiceCollection.size()>0);
    }



}
