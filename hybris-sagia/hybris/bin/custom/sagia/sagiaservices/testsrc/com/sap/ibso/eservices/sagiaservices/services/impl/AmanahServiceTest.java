package com.sap.ibso.eservices.sagiaservices.services.impl;


import com.sap.ibso.eservices.facades.data.AmanahData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AmendHeaderData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtDropdown;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
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
public class AmanahServiceTest extends ServicelayerTransactionalTest {
    private static AmanahService amanahService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        amanahService = appCtx.getBean("amanahService", AmanahService.class);
        amanahService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldPopulateGovtDropdownTest() {
        Collection<GovtDropdown> amanahServiceCollection = amanahService.getCollection();
        Assert.assertEquals(true, amanahServiceCollection.stream()
                .allMatch(govtDropdown -> govtDropdown.getKey().equals("A")));
    }


}
