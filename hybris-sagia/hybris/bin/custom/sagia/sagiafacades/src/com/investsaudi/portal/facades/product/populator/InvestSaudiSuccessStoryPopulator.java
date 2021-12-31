package com.investsaudi.portal.facades.product.populator;

import com.investsaudi.portal.core.model.SuccessStoryProductModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.catalog.model.ProductFeatureModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class InvestSaudiSuccessStoryPopulator implements Populator<ProductData, SuccessStoryProductModel> {

    private static final String SECTOR_URL = "/sectors-opportunities/";
    private static final String INFO_CLASSIFICATION = "success-story-info-classification";
    private static final String ADDITIONAL_INFO_CLASSIFICATION = "success-story-aditional-info-classification";
    private static final String TYPE = "SuccessStoryProduct";

    private Converter<MediaModel, ImageData> imageConverter;

    @Resource
    private I18NService i18NService;

    @Override
    public void populate(ProductData productData, SuccessStoryProductModel productModel) throws ConversionException {

        Locale currentLocale = i18NService.getCurrentLocale();
        productData.setCode(productModel.getCode());
        productData.setName(productModel.getName());
        productData.setDescription(productModel.getDescription(currentLocale));
        productData.setQuote(productModel.getQuote(currentLocale));
        productData.setFeatureInfoMap(extractProductFeatures(getProductFeaturesForCurrentLanguage(productModel.getFeatures()), INFO_CLASSIFICATION));
        productData.setFeatureAdditionalInfoMap(extractProductFeatures(getProductFeaturesForCurrentLanguage(productModel.getFeatures()), ADDITIONAL_INFO_CLASSIFICATION));
        productData.setProductType(TYPE);
        productData.setSummary(productModel.getSummary(currentLocale));
        final Optional<CategoryModel> parentCategory = emptyIfNull(productModel.getSupercategories()).stream().findFirst();
        if (parentCategory.isPresent()) {
            productData.setParentCategory(parentCategory.get().getCode());
            productData.setUrl(SECTOR_URL + parentCategory.get().getCode() + "/" + productModel.getCode());
        }
        final Optional<MediaModel> logo = productModel.getLogo().stream().findFirst();
        if (logo.isPresent()) {
            productData.setLogo(getImageConverter().convert(logo.get()));
            productData.getLogo().setAltText(logo.get().getAltText());
        }
        final MediaModel banner = productModel.getPicture();
        if (banner != null) {
            productData.setBanner(getImageConverter().convert(banner));
            productData.getBanner().setAltText(banner.getAltText());
        }
        final List<KeywordModel> keywords = productModel.getKeywords();

        final Set<String> keywordSet = new HashSet<String>(keywords.size());
        for (final KeywordModel keyword : keywords) {
            keywordSet.add(keyword.getKeyword());
        }

        if (productModel.getSeoKeywords() != null) {
            keywordSet.add(productModel.getSeoKeywords());
        }
        productData.setKeywords(keywordSet);
    }

    private Map<String, String> extractProductFeatures(List<ProductFeatureModel> featureModels, String classificationClass) {
        Map<String, String> map = new LinkedHashMap<>();

        for (ProductFeatureModel featureModel : emptyIfNull(featureModels)) {
            if (featureModel.getClassificationAttributeAssignment().getClassificationClass().getCode().equals(classificationClass)) {
                map.put(extractClassAttributeName(featureModel), featureModel.getValue().toString());
            }
        }

        return map;

    }

    private String extractClassAttributeName(ProductFeatureModel featureModel) {

        return (featureModel.getClassificationAttributeAssignment() != null && featureModel.getClassificationAttributeAssignment().getClassificationAttribute() != null) ?
            featureModel.getClassificationAttributeAssignment().getClassificationAttribute().getName() : StringUtils.EMPTY;
    }

    public Converter<MediaModel, ImageData> getImageConverter() {
        return imageConverter;
    }

    @Required
    public void setImageConverter(final Converter<MediaModel, ImageData> imageConverter) {
        this.imageConverter = imageConverter;
    }

    private List<ProductFeatureModel> getProductFeaturesForCurrentLanguage(List<ProductFeatureModel> featureModels) {
        String currentIso = i18NService.getCurrentLocale().getLanguage();
        return emptyIfNull(featureModels).stream().filter(featureModel -> validateLanguage(featureModel).equalsIgnoreCase(currentIso)).collect(Collectors.toList());
    }

    private String validateLanguage(ProductFeatureModel featureModel) {
        if (null != featureModel && null != featureModel.getLanguage() && null != featureModel.getLanguage().getIsocode()) {
            return featureModel.getLanguage().getIsocode();
        } else {
            return StringUtils.EMPTY;
        }
    }

}