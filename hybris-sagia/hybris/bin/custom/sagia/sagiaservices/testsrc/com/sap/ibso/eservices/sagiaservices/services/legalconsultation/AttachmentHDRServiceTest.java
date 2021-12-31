package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.AttachmantHDRData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@IntegrationTest
public class AttachmentHDRServiceTest extends ServicelayerTransactionalTest {
    private static AttachmentHDRService attachmentHDRService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        attachmentHDRService = appCtx.getBean("attachmentHDRService", AttachmentHDRService.class);
        attachmentHDRService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // no json
    @Ignore
    @Test
    public void shouldPopulateAttachmantHDRData() {
        Collection<AttachmantHDRData> attachmantHDRDataList = attachmentHDRService.getLegalConsultationAttachment();
        Assert.assertTrue(attachmantHDRDataList.size() > 0);
    }
}
