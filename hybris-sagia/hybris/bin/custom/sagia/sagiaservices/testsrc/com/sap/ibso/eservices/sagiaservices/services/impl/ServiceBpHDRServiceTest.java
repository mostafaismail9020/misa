package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.facades.data.ProfileCompanyRepresentativeData;
import com.sap.ibso.eservices.facades.data.ProfileGeneralManagerData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceBpHDRData;
import com.sap.ibso.eservices.sagiaservices.services.contacts.dto.CompanyRepresentativeFormData;
import com.sap.ibso.eservices.sagiaservices.services.contacts.dto.GeneralManagerFormData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@IntegrationTest
public class ServiceBpHDRServiceTest extends ServicelayerTransactionalTest {

    private static ServiceBpHDRService serviceBpHDRService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        serviceBpHDRService = appCtx.getBean("serviceBpHDRService", ServiceBpHDRService.class);
        serviceBpHDRService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateServiceBpHDRDatas() {
        Collection<ServiceBpHDRData> serviceBpHDRDataList = serviceBpHDRService.getCollection();
        Assert.assertTrue(serviceBpHDRDataList.size() > 0);
        ServiceBpHDRData data = serviceBpHDRDataList.iterator().next();
        String payload = serviceBpHDRService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
    }

    @Test
    public void shouldGetServiceBpHDRDatas() {
        ServiceBpHDRData serviceBpHDRData = serviceBpHDRService.get("1");
        Assert.assertNotNull(serviceBpHDRData);
    }

    @Test
    public void shouldFillDocumentsFromServiceBPHDR () throws Exception {
        ProfileGeneralManagerData profileGeneralManagerData = new ProfileGeneralManagerData();
        List<ProfileCompanyRepresentativeData> profileCompanyRepresentativeData = new ArrayList<>();
        serviceBpHDRService.fillDocumentsFromServiceBPHDR(profileGeneralManagerData, profileCompanyRepresentativeData);
        assertThat("Exception was not thrown").isNotNull();
    }

}
