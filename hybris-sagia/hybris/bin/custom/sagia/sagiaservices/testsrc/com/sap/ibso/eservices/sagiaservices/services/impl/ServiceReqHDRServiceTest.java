package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceReqData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceRequestHDRsData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

@IntegrationTest
public class ServiceReqHDRServiceTest extends ServicelayerTransactionalTest {

    private static ServiceReqHDRService serviceReqHDRService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        serviceReqHDRService = appCtx.getBean("servicesReqHDRService", ServiceReqHDRService.class);
        serviceReqHDRService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveServiceRequestHDRsData() {
        ServiceReqData serviceRequestHDRsData = new ServiceReqData();
        //serviceRequestHDRsData.setAction("new action");
        String payload = serviceReqHDRService.save(serviceRequestHDRsData);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        //Assert.assertTrue(payload.contains("new action"));
    }


}
