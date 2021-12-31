package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import static org.jgroups.util.Util.assertNotNull;

@IntegrationTest
public class GovtServicesAttachmentServiceTest  extends ServicelayerTransactionalTest {
    private static GovtServicesAttachmentService govtServicesAttachmentService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        govtServicesAttachmentService = appCtx.getBean("govtServicesAttachmentService", GovtServicesAttachmentService.class);
        govtServicesAttachmentService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readGovtServicesPdfTest(){
        assertNotNull(govtServicesAttachmentService.readGovtServicesPdf("objectId", "documentID"));
    }



}
