package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.QeemahGeneralQstData;
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
public class QeemahGeneralQstServiceTest extends ServicelayerTransactionalTest {

    private static QeemahGeneralQstService qeemahGeneralQstService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        qeemahGeneralQstService = appCtx.getBean("qeemahGeneralQstService", QeemahGeneralQstService.class);
        qeemahGeneralQstService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldPopulateQeemahGeneralQstData() {
        Collection<QeemahGeneralQstData> qeemahGeneralQstDataList = qeemahGeneralQstService.getCollection("RO", "1", "2");
        Assert.assertTrue(qeemahGeneralQstDataList.size() > 0);
    }
}