package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceRequestHDRsData;
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

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ServicesRequestHDRServiceTest extends ServicelayerTransactionalTest {

    private static ServicesRequestHDRService servicesRequestHDRService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        servicesRequestHDRService = appCtx.getBean("servicesRequestHDRService", ServicesRequestHDRService.class);
        servicesRequestHDRService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateServiceRequestHDRsData() {
        Collection<ServiceRequestHDRsData> serviceRequests = servicesRequestHDRService.getCollection();
        Assert.assertTrue(serviceRequests.size() > 0);
    }

    @Test
    public void shouldCreateServiceRequestHDRsData() {
        ServiceRequestHDRsData serviceRequestHDRsData = new ServiceRequestHDRsData();
        servicesRequestHDRService.create(serviceRequestHDRsData);
        assertThat("Exception was not thrown").isNotNull();
    }
}