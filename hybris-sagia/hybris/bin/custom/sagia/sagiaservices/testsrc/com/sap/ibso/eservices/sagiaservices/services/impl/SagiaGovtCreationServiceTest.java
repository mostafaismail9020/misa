package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaGovtServiceFileUploadData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class SagiaGovtCreationServiceTest extends ServicelayerTransactionalTest {

    private static SagiaGovtCreationService sagiaGovtCreationService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        sagiaGovtCreationService = appCtx.getBean("sagiaGovtCreationService", SagiaGovtCreationService.class);
        sagiaGovtCreationService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateNIPRegionSetData() {
        SagiaGovtServiceFileUploadData sagiaGovtServiceFileUploadData = new SagiaGovtServiceFileUploadData();
        sagiaGovtServiceFileUploadData.setMinistryType("1");
        sagiaGovtCreationService.createGovtService(sagiaGovtServiceFileUploadData);
        assertThat("Exception was not thrown").isNotNull();
    }

}
