package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DropdownValueData;
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
public class DropdownValueServiceTest extends ServicelayerTransactionalTest {

    private static DropdownValueService dropdownValueService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        dropdownValueService = appCtx.getBean("dropdownValueService", DropdownValueService.class);
        dropdownValueService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldPopulateDropdownValueData() {
        Collection<DropdownValueData> dropdownValueDataList = dropdownValueService.getDropdownValuesData("RO", "RO", "");
        Assert.assertTrue(dropdownValueDataList.size() > 0);
    }
}
