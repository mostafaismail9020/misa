package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryDropdownSetData;
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
public class LegalInquiryDropdownSetServiceTest extends ServicelayerTransactionalTest {
    private static LegalInquiryDropdownSetService legalInquiryDropdownSetService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        legalInquiryDropdownSetService = appCtx.getBean("legalInquiryDropdownSetService", LegalInquiryDropdownSetService.class);
        legalInquiryDropdownSetService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateALegalInquiryDropdownSetData() {
        Collection<LegalInquiryDropdownSetData> legalInquiryDropdownSetDataList = legalInquiryDropdownSetService.getCollection("E");
        Assert.assertTrue(legalInquiryDropdownSetDataList.size() > 0);
    }
}
