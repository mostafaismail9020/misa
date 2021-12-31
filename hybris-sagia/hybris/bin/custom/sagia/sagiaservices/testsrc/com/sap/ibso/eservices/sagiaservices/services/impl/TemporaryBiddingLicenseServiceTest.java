package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class TemporaryBiddingLicenseServiceTest extends ServicelayerTransactionalTest {

    private static TemporaryBiddingLicenseService temporaryBiddingLicenseService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        temporaryBiddingLicenseService = appCtx.getBean("temporaryBiddingLicenseService", TemporaryBiddingLicenseService.class);
        temporaryBiddingLicenseService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateTemporaryBiddingLicenseDataTest() {
        TemporaryBiddingLicenseData temporaryBiddingLicenseData = new TemporaryBiddingLicenseData();
        temporaryBiddingLicenseData.setId("id");
        temporaryBiddingLicenseData.setCapital("London");
        List<TemporaryBiddingLicenseAttachmentData> licenseAttachmentDataList = new ArrayList<>();
        temporaryBiddingLicenseData.setZTEMPTOATT(licenseAttachmentDataList);
        temporaryBiddingLicenseService.create(temporaryBiddingLicenseData);
        assertThat("Exception was not thrown").isNotNull();
    }


}
