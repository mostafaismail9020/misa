package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoDictionaryData;
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
public class OrgInfoDictionaryServiceTest extends ServicelayerTransactionalTest {

    private static OrgInfoDictionaryService orgInfoDictionaryService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        orgInfoDictionaryService = appCtx.getBean("orgInfoDictionaryService", OrgInfoDictionaryService.class);
        orgInfoDictionaryService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetOrgInfoData() {
        Collection<OrgInfoDictionaryData> orgInfoDictionaryDataList = orgInfoDictionaryService.getLegalStatus("LS");
        Assert.assertTrue(orgInfoDictionaryDataList.size() > 0);
        orgInfoDictionaryDataList = orgInfoDictionaryService.getCities("EN", null);
        Assert.assertTrue(orgInfoDictionaryDataList.size() > 0);
        orgInfoDictionaryDataList = orgInfoDictionaryService.getRegions("RG");
        Assert.assertTrue(orgInfoDictionaryDataList.size() > 0);
    }
}