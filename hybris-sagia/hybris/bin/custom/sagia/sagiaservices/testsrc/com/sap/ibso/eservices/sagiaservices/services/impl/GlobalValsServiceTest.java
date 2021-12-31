package com.sap.ibso.eservices.sagiaservices.services.impl;

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
public class GlobalValsServiceTest extends ServicelayerTransactionalTest {

    private static GlobalValsService globalValsService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        globalValsService = appCtx.getBean("globalValsService", GlobalValsService.class);
        globalValsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCheckWarningLetterCreateAvalability() {
        globalValsService.checkWarningLetterCreateAvalability();
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldCheckViolationRepliesCreateAvalability() {
        globalValsService.checkViolationRepliesCreateAvalability();
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldCheckLicenseReplacementAvalability() {
        globalValsService.checkLicenseReplacementAvalability();
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldCheckLicenseAmendmentAvailability() {
        globalValsService.checkLicenseAmendmentAvailability();
        assertThat("Exception was not thrown").isNotNull();
    }
}
