package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DiffQeemahData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

@IntegrationTest
public class DiffQeemahServiceTest extends ServicelayerTransactionalTest {

    private static DiffQeemahService diffQeemahService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        diffQeemahService = appCtx.getBean("diffQeemahService", DiffQeemahService.class);
        diffQeemahService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetDiffQeemahData() {
        DiffQeemahData diffQeemahData = diffQeemahService.get();
        Assert.assertNotNull(diffQeemahData);
    }
}
