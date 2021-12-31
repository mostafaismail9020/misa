package com.sap.ibso.eservices.sagiaservices.services.attachments;

import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.io.InputStream;

@IntegrationTest
public class ContentDetailsServiceTest extends ServicelayerTransactionalTest {

    private static ContentDetailsService contentDetailsService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        contentDetailsService = appCtx.getBean("contentDetailsService", ContentDetailsService.class);
        contentDetailsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetCountryPrefix() {
        InputStream data = contentDetailsService.readAttachmentBy("1","AL");
        Assert.assertNotNull(data);
    }

}
