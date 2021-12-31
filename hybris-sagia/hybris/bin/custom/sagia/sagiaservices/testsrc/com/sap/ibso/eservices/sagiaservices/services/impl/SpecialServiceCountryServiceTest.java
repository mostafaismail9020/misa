package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCountryData;
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
public class SpecialServiceCountryServiceTest extends ServicelayerTransactionalTest {
    private static SpecialServiceCountryService specialServiceCountryService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        specialServiceCountryService = appCtx.getBean("specialServiceCountryService", SpecialServiceCountryService.class);
        specialServiceCountryService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetSupportVisitData() {
        Collection<SpCountryData> spCountryDataList = specialServiceCountryService.getCountryCollection("EN");
        Assert.assertTrue(spCountryDataList.size() > 0);
    }
}
