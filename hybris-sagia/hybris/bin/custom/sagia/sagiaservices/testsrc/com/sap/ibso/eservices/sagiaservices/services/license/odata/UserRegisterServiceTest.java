package com.sap.ibso.eservices.sagiaservices.services.license.odata;

import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.ApplicationStatusService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.UserRegisterService;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

public class UserRegisterServiceTest {

    private static UserRegisterService userRegisterService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        userRegisterService = appCtx.getBean("userRegisterService", UserRegisterService.class);
        userRegisterService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
