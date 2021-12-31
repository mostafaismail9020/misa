package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AmendHeaderData;
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
public class AmendHeadersServiceTest extends ServicelayerTransactionalTest {

    private static AmendHeadersService amendHeadersService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        amendHeadersService = appCtx.getBean("amendHeadersService", AmendHeadersService.class);
        amendHeadersService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateAmendHeaderData() {
        Collection<AmendHeaderData> amendHeaders = amendHeadersService.getCollection();
        Assert.assertTrue(amendHeaders.size() > 0);
    }
}
