package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.NationalInvestorAppointmentData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class NipAppointmentSetServiceTest extends ServicelayerTransactionalTest {
    private static NipAppointmentSetService nipAppointmentSetService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipAppointmentSetService = appCtx.getBean("nipAppointmentSetService", NipAppointmentSetService.class);
        nipAppointmentSetService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Ignore
    @Test
    public void shouldCreateNationalInvestorAppointmentDataTest() {
        NationalInvestorAppointmentData nationalInvestorAppointmentData = new NationalInvestorAppointmentData();
        nationalInvestorAppointmentData.setBranch("10");
        nipAppointmentSetService.save(nationalInvestorAppointmentData);
        assertThat("Exception was not thrown").isNotNull();
    }


}
