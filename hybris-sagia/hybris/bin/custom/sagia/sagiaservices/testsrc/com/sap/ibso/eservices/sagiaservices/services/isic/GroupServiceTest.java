package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicGroupData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Collection;

@IntegrationTest
public class GroupServiceTest extends ServicelayerTransactionalTest {
    private static GroupService groupService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        groupService = appCtx.getBean("sagiaIsicGroupService", GroupService.class);
        groupService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateISICGroupData() {
        Collection<IsicGroupData> isicGroupDataList = groupService.getCollection("EN" , Arrays.asList("1"));
        Assert.assertTrue(isicGroupDataList.size() > 0);
    }
}
