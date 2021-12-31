package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ShareholderInfoData;
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
public class ShareholderInfoServiceTest extends ServicelayerTransactionalTest {

    private static ShareholderInfoService shareholderInfoService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        shareholderInfoService = appCtx.getBean("shareholderInfoService", ShareholderInfoService.class);
        shareholderInfoService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateShareholderInfoData() {
        Collection<ShareholderInfoData> shareholderInfoDataList = shareholderInfoService.getShareholdersInfo("1", "1", "1");
        Assert.assertTrue(shareholderInfoDataList.size() > 0);
    }
}
