/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.cms.populators;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.servicelayer.services.AttributeDescriptorModelHelperService;
import de.hybris.platform.cmsfacades.data.ComponentTypeAttributeData;
import de.hybris.platform.cmsfacades.data.ComponentTypeData;
import de.hybris.platform.cmsfacades.types.populator.I18nComponentTypePopulator;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.type.AttributeDescriptorModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Required;

public class CMSInvestSaudiContainerTypesAttributePopulator implements
    Populator<AttributeDescriptorModel, ComponentTypeAttributeData> {

    private static final Logger log = LoggerFactory.getLogger(CMSInvestSaudiContainerTypesAttributePopulator.class);

    private TypeService typeService;
    private ObjectFactory<ComponentTypeData> componentTypeDataFactory;
    private AttributeDescriptorModelHelperService attributeDescriptorModelHelperService;
    private I18nComponentTypePopulator i18nComponentTypePopulator;
    private String searchParams;
    private String placeholder;

    @Override
    public void populate(final AttributeDescriptorModel source, final ComponentTypeAttributeData target)
        throws ConversionException {
        if (source.getEnclosingType() != null && StringUtils.equals(source.getQualifier(), "simpleCMSComponents") &&
            StringUtils.startsWith(source.getEnclosingType().getCode(),
                "InvestSaudi") && StringUtils.endsWith(source.getEnclosingType().getCode(),
            "Container")) {

            final Class<?> type = getAttributeDescriptorModelHelperService().getAttributeClass(source);
            Map<String, String> componentSubTypes = this.getComponentSubTypes(source.getEnclosingType().getCode());
            if (componentSubTypes != null) {
                target.setSubTypes(componentSubTypes);
            }

        }
    }

    /**
     * This method retrieves a map of concrete subtypes of the provided type. (If the provided type is concrete it will
     * also be included in the map).
     * @return map Map of concrete component subtypes. The key is the code of the sub-type and the value is its i18n
     * key.
     */
    protected Map<String, String> getComponentSubTypes(String typeCode) {
        final ComposedTypeModel abstractPageComposedTypeModel =
            this.getTypeService().getComposedTypeForClass(AbstractPageModel.class);

        String componentTypeCode = StringUtils.replace(typeCode, "Container", "Component");
        try {
            final ComposedTypeModel composedTypeModel = this.getTypeService().getComposedTypeForCode(componentTypeCode);
            if (composedTypeModel != null) {

                final ArrayList<ComposedTypeModel> supportedSubTypes =
                        new ArrayList<>(composedTypeModel.getAllSubTypes());
                if (!composedTypeModel.getAbstract()) {
                    // If the original type itself is not abstract it should also be returned as a supported SubType.
                    supportedSubTypes.add(composedTypeModel);
                }

                return supportedSubTypes.stream()
                        .filter(ctm -> !typeService.isAssignableFrom(abstractPageComposedTypeModel, ctm))
                        .collect(Collectors.toMap(ComposedTypeModel::getCode,
                                typeModel -> getComponentTypeI18nKey(typeModel)));
            }
        } catch (Exception e){
            log.warn("Error getting Component type for container {}", typeCode, e);
        }

        log.warn("Error getting Component type for container {}", typeCode);
        return null;
    }

    /**
     * This method retrieves the i18n key of the provided component type.
     * @param typeModel The type for which to retrieve its map of subtypes.
     * @return String The i18n key of the provided component type.
     */
    protected String getComponentTypeI18nKey(final ComposedTypeModel typeModel) {
        final ComponentTypeData componentTypeData = getComponentTypeDataFactory().getObject();
        getI18nComponentTypePopulator().populate(typeModel, componentTypeData);
        return componentTypeData.getI18nKey();
    }

    @Required
    public void setAttributeDescriptorModelHelperService(
        final AttributeDescriptorModelHelperService attributeDescriptorModelHelperService) {
        this.attributeDescriptorModelHelperService = attributeDescriptorModelHelperService;
    }

    protected AttributeDescriptorModelHelperService getAttributeDescriptorModelHelperService() {
        return attributeDescriptorModelHelperService;
    }

    @Required
    public void setTypeService(final TypeService typeService) {
        this.typeService = typeService;
    }

    protected TypeService getTypeService() {
        return this.typeService;
    }

    @Required
    public void setI18nComponentTypePopulator(final I18nComponentTypePopulator i18nComponentTypePopulator) {
        this.i18nComponentTypePopulator = i18nComponentTypePopulator;
    }

    protected I18nComponentTypePopulator getI18nComponentTypePopulator() {
        return i18nComponentTypePopulator;
    }

    @Required
    public void setComponentTypeDataFactory(final ObjectFactory<ComponentTypeData> componentTypeDataFactory) {
        this.componentTypeDataFactory = componentTypeDataFactory;
    }

    protected ObjectFactory<ComponentTypeData> getComponentTypeDataFactory() {
        return componentTypeDataFactory;
    }

    /**
     * If searchParams is set, the value will be added to the "params" map for the "itemSearchParams" key. This is
     * useful when the "itemSearchParams" query-parameter needs to be set when calling the CMS Item Search API.
     *
     * Example of usage of this setter:
     * <pre>
     * {@code
     * 	<property name="searchParams" value="pageStatus:active" />
     * }
     * </pre>
     * @param searchParams the searchParams value
     */
    public void setSearchParams(final String searchParams) {
        this.searchParams = searchParams;
    }

    protected String getSearchParams() {
        return searchParams;
    }

    protected String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
    }
}
