package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FinanceHDRS;
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
public class FinancialServiceTest extends ServicelayerTransactionalTest {

    private static FinancialService financialService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        financialService = appCtx.getBean("financialService", FinancialService.class);
        financialService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateFinancialEntities() {
        Collection<FinanceHDRS> financialEntities = financialService.getFinancialEntities();
        Assert.assertTrue(financialEntities.size() > 0);
    }

    @Test
    public void shouldGetFinancialData() {
        FinanceHDRS financialData = financialService.getFinancialData("80000041");
        Assert.assertEquals("2015", financialData.getYear());
        Assert.assertEquals("80000041", financialData.getSrID());
        Assert.assertEquals("613392", financialData.getBpID());
    }
}