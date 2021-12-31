package com.sap.ibso.eservices.sagiaservices.services.util;

import de.hybris.platform.core.Registry;
import de.hybris.platform.spring.ctx.ScopeTenantIgnoreDocReader;
import de.hybris.platform.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import static org.junit.Assert.assertTrue;

public class TestUtil {

    private static final Logger LOG = Logger.getLogger(TestUtil.class);

    public static ApplicationContext buildContext() {
        Registry.activateStandaloneMode();
        Utilities.setJUnitTenant();
        Registry.getCurrentTenant().getConfig().setParameter("sagiaservices.https.certificate.validation", "false");
        Registry.getCurrentTenant().getConfig().setParameter("sagiaservices.https.hostname.verifying", "false");
        LOG.debug("Preparing...");

        final ApplicationContext appCtx = Registry.getGlobalApplicationContext();
        assertTrue("Application context of type " + appCtx.getClass() + " is not a subclass of "
                + ConfigurableApplicationContext.class, appCtx instanceof ConfigurableApplicationContext);

        final ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) appCtx;
        final ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        assertTrue("Bean Factory of type " + beanFactory.getClass() + " is not of type " + BeanDefinitionRegistry.class,
                beanFactory instanceof BeanDefinitionRegistry);
        final XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        xmlReader.setDocumentReaderClass(ScopeTenantIgnoreDocReader.class);
        xmlReader.loadBeanDefinitions(new ClassPathResource(
                "/sagiaservices-spring.xml"));
        LOG.warn("Prepare Process Definition factory...");
        return appCtx;
    }
}
