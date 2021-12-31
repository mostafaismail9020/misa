package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class AppointmentServiceTest extends ServicelayerTransactionalTest {

    private static AppointmentService appointmentService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        appointmentService = appCtx.getBean("appointmentService", AppointmentService.class);
        appointmentService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateAppointmentData() {
        Collection<AppointmentData> appointments = appointmentService.getCollection();
        Assert.assertTrue(appointments.size() > 0);
        AppointmentData data = appointments.iterator().next();
        String payload = appointmentService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("ServType1Desc"));
    }

    // failing test parameter must be of type String
    @Test
    public void shouldGetAppointmentData() {
        AppointmentData appointment = appointmentService.get(1);
        Assert.assertNotNull(appointment);
    }

    @Test
    public void shouldCreateAppointmentData() {
        AppointmentData data = new AppointmentData();
        data.setAction("Action");
        appointmentService.create(data);
        assertThat("Exception was not thrown").isNotNull();
    }
}

