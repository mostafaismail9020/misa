package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
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
public class AmendProductsServiceTest extends ServicelayerTransactionalTest {

    private static AmendProductsService amendProductsService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        amendProductsService = appCtx.getBean("amendProductsService", AmendProductsService.class);
        amendProductsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAmendProductsWithId() {
        Collection<ProductData> productDataList = amendProductsService.getAmendProductsWithId("1", "1", "1");
        Assert.assertTrue(productDataList.size() > 0);
    }

    @Test
    public void shouldGetAmendProductsWithDescription() {
        Collection<ProductData> productDataList = amendProductsService.getAmendProductsWithDescription("desc", "1", "1");
        Assert.assertTrue(productDataList.size() > 0);
    }

    @Test
    public void shouldGetAmendProductsWithDescription2() {
        Collection<ProductData> productDataList = amendProductsService.getAmendProductsWithDescription("1", "1");
        Assert.assertTrue(productDataList.size() > 0);
    }
}
