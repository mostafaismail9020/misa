package com.sap.ibso.eservices.storefront.functions;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Iterator;

import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

public class BeanElResolver extends ELResolver implements ServletContextAware {

    private static final Logger log = LoggerFactory.getLogger(BeanElResolver.class);

    private Object bean;

    private String elPrefix;

    @Override
    public Object getValue(final ELContext elContext, final Object base, final Object property) {
        if (base == null) {
            if (StringUtils.equals(elPrefix, String.valueOf(property))) {
                elContext.setPropertyResolved(true);
                return bean;
            }
        }
        return null;
    }

    @Override
    public Class<?> getType(final ELContext elContext, final Object base, final Object property) {
        if (base == null) {
            if (StringUtils.equals(elPrefix, String.valueOf(property))) {
                elContext.setPropertyResolved(true);
                return bean.getClass();
            }
        }
        return null;
    }

    @Override
    public void setValue(final ELContext elContext, final Object base, final Object property, final Object value) {
        log.warn("Setting value not available");
    }

    @Override
    public boolean isReadOnly(final ELContext elContext, final Object base, final Object property) {
        return true;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(final ELContext elContext, final Object o) {
        try {
            return new ArrayIterator(Introspector.getBeanInfo(bean.getClass()).getMethodDescriptors());
        } catch (IntrospectionException e) {
            log.error("Error getting FeatureDescriptor", e);
        }

        return null;
    }

    @Override
    public Class<?> getCommonPropertyType(final ELContext elContext, final Object o) {
        return Object.class;
    }

    public void setBean(final Object bean) {
        this.bean = bean;
    }

    public void setElPrefix(final String elPrefix) {
        this.elPrefix = elPrefix;
    }

    @Override
    public void setServletContext(final ServletContext servletContext) {
        JspApplicationContext jspContext = JspFactory.getDefaultFactory().getJspApplicationContext(servletContext);
        jspContext.addELResolver(this);
    }
}
