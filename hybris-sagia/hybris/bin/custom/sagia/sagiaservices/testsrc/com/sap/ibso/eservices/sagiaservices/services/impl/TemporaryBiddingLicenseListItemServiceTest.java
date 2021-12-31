package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseListItemData;
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

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class TemporaryBiddingLicenseListItemServiceTest extends ServicelayerTransactionalTest {

    private static TemporaryBiddingLicenseListItemService temporaryBiddingLicenseListItemService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        temporaryBiddingLicenseListItemService = appCtx.getBean("temporaryBiddingLicenseListItemService", TemporaryBiddingLicenseListItemService.class);
        temporaryBiddingLicenseListItemService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetListItems() {
        Collection<TemporaryBiddingLicenseListItemData> list = temporaryBiddingLicenseListItemService.getListItems("G", "EN");
        Assert.assertEquals(1, list.stream()
                .filter(listItem -> listItem.getGentity().equals("MOC")).count());
    }

    @Test
    public void shouldFilterEntityset() throws Exception {
        temporaryBiddingLicenseListItemService.getoDataService().filter("ZLISTSET");
        assertThat("Exception was not thrown").isNotNull();
    }
}