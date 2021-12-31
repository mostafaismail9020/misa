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
import java.util.Collection;

@IntegrationTest
public class CalendarSlotServiceTest extends ServicelayerTransactionalTest {

    private static CalendarSlotService calendarSlotService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        calendarSlotService = appCtx.getBean("calendarSlotService", CalendarSlotService.class);
        calendarSlotService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateCalendarSlotData() {
        CalendarSlotData calendarSlotData = new CalendarSlotData();
        Collection<CalendarSlotData> calendarSlots = calendarSlotService.getCollection(calendarSlotData);
        Assert.assertTrue(calendarSlots.size() > 0);
    }
}

