package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
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
public class SPCheckApplicantsTest extends ServicelayerTransactionalTest {

    private static SPCheckApplicants spCheckApplicants;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        spCheckApplicants = appCtx.getBean("spCheckApplicants", SPCheckApplicants.class);
        spCheckApplicants.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAmendProductsWithId() {
        Collection<ServiceApplicantData> serviceApplicantDataList = spCheckApplicants.getCollection("1", "1", "1");
        Assert.assertTrue(serviceApplicantDataList.size() > 0);
    }
}
