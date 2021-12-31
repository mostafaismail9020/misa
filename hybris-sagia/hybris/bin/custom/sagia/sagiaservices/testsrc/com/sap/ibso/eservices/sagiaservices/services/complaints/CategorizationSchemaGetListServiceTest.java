package com.sap.ibso.eservices.sagiaservices.services.complaints;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CategorizationSchemaGetListData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

public class CategorizationSchemaGetListServiceTest extends ServicelayerTransactionalTest {

    private static CategorizationSchemaGetListService categorizationSchemaGetListService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        categorizationSchemaGetListService = appCtx.getBean("categorizationSchemaGetListService", CategorizationSchemaGetListService.class);
        categorizationSchemaGetListService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateCategorizationSchemaGetListData() {
        Collection<CategorizationSchemaGetListData> categorizationSchemaGetListDataList = categorizationSchemaGetListService.readCategorizationSchema();
        Assert.assertTrue(categorizationSchemaGetListDataList.size() > 0);
    }
}