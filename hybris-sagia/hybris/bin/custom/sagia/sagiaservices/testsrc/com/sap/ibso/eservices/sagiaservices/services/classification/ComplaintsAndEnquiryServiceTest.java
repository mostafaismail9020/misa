package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CompAndEnqHdrToDetailNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.ComplaintsAndEnquiryService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.util.ArrayList;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ComplaintsAndEnquiryServiceTest extends ServicelayerTransactionalTest {

    private static ComplaintsAndEnquiryService complaintsAndEnquiryService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        complaintsAndEnquiryService = appCtx.getBean("complaintsAndEnquiryService", ComplaintsAndEnquiryService.class);
        complaintsAndEnquiryService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateComplaintsAndEnquiryHdrsData() {
        Collection<ComplaintsAndEnquiryHdrsData> complaintsAndEnquiryHdrsDataList = complaintsAndEnquiryService.getComplaintsList();
        Assert.assertTrue(complaintsAndEnquiryHdrsDataList.size() > 0);
    }

    @Test
    public void shouldGetComplaintBy() {
        ComplaintsAndEnquiryHdrsData complaintsAndEnquiryHdrsData = complaintsAndEnquiryService.getComplaintBy("1");
        Assert.assertNotNull(complaintsAndEnquiryHdrsData.getAction());
    }

    @Test
    public void shouldCreateComplaintFormData() {
        ComplaintsAndEnquiryHdrsData complaintsAndEnquiryHdrsData = complaintsAndEnquiryService.getComplaintBy("1");
        ComplaintFormData complaintFormData = new ComplaintFormData();
        complaintFormData.setDetails(complaintsAndEnquiryHdrsData.getCompAndEnqHdrToDetailNav());
        complaintFormData.setFiles(new ArrayList<>());
        complaintFormData.setDetails(new CompAndEnqHdrToDetailNavData());
        complaintsAndEnquiryService.createComplaint(complaintFormData, new ArrayList<>());
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldUpdateComplaintFormData() {
        UpdatableComplaintDetails updatableComplaintDetails = new UpdatableComplaintDetails();
        complaintsAndEnquiryService.updateComplaint(updatableComplaintDetails, "1");
        assertThat("Exception was not thrown").isNotNull();
    }
}
