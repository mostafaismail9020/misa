package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.time.LocalDate;
import java.util.Collection;

@IntegrationTest
public class ApptAvailSetTest extends ServicelayerTransactionalTest {

    private static ApptAvailSet apptAvailSet;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        apptAvailSet = appCtx.getBean("apptAvailSetService", ApptAvailSet.class);
        apptAvailSet.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateAmendHeaderData() {
        Collection<CalendarSlotData> calendarSlots = apptAvailSet.getCollection(LocalDate.now(), "10");
        Assert.assertTrue(calendarSlots.size() > 0);
    }
}

