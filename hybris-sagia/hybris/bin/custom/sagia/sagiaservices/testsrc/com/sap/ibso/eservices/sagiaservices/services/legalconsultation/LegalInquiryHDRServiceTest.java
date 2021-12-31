package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryHDRData;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.LegalConsultationFormData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class LegalInquiryHDRServiceTest extends ServicelayerTransactionalTest {
    private static LegalInquiryHDRService legalInquiryHDRService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        legalInquiryHDRService = appCtx.getBean("legalInquiryHDRService", LegalInquiryHDRService.class);
        legalInquiryHDRService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateLegalInquiryHDRData() {
        Collection<LegalInquiryHDRData> legalInquiryHDRDataList = legalInquiryHDRService.getLegalConsultationList();
        Assert.assertTrue(legalInquiryHDRDataList.size() > 0);
    }

    @Test
    public void shouldGetLegalConsultationBy() {
        LegalInquiryHDRData legalInquiryHDRData = legalInquiryHDRService.getLegalConsultationBy("1");
        Assert.assertTrue(legalInquiryHDRData != null);
    }

    @Test
    public void shouldCreateLegalConsultation() {
        LegalConsultationFormData legalConsultationFormData = new LegalConsultationFormData();
        legalConsultationFormData.setFiles(Arrays.asList(new MockMultipartFile("file", new byte[0])));
        legalInquiryHDRService.createLegalConsultation(legalConsultationFormData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
