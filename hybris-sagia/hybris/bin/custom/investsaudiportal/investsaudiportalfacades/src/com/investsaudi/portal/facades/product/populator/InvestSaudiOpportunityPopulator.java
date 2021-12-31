package com.investsaudi.portal.facades.product.populator;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.catalog.model.ProductFeatureModel;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class InvestSaudiOpportunityPopulator implements Populator<ProductData, OpportunityProductModel> {

    private static final String SECTOR_URL = "/sectors-opportunities/";
    private static final String TYPE = "OpportunityProduct";

    private Converter<MediaModel, ImageData> imageConverter;

    @Resource
    private I18NService i18NService;

    @Override
    public void populate(ProductData productData, OpportunityProductModel productModel) throws ConversionException {

        Locale currentLocale = i18NService.getCurrentLocale();
        productData.setCode(productModel.getCode());
        productData.setName(productModel.getName());
        productData.setDescription(productModel.getDescription(currentLocale));
        productData.setInvestmentModel(productModel.getInvestmentModel(currentLocale));
        productData.setHighlights(productModel.getHighlights(currentLocale));
        productData.setFeatured(productModel.getFeatured());
        productData.setPdfUrl(extractMediaUrl(productModel.getDetail()));
        productData.setImageUrl(extractMediaUrl(productModel.getOthers()));
        productData.setOpportunityDetailsTitle(extractTitle(productModel));
        productData.setFeatureMap(extractProductFeatures(productModel.getFeatures()));
        productData.setSummary(productModel.getSummary());
        productData.setProductType(TYPE);
        final MediaModel overviewImage = productModel.getPicture();
        if (overviewImage != null) {
            productData.setOverviewPicture(getImageConverter().convert(overviewImage));
            productData.getOverviewPicture().setAltText(overviewImage.getAltText());
        }
        final Optional<CategoryModel> parentCategory = emptyIfNull(productModel.getSupercategories()).stream().findFirst();
        if (parentCategory.isPresent()) {
            productData.setParentCategory(parentCategory.get().getCode());
            productData.setUrl(SECTOR_URL + parentCategory.get().getCode() + "/" + productModel.getCode());
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


    private String extractMediaUrl(Collection<MediaModel> mediaModels) {

        Optional<MediaModel> mediaModelOptional = emptyIfNull(mediaModels).stream().findFirst();
        return mediaModelOptional.isPresent() ? mediaModelOptional.get().getURL() : StringUtils.EMPTY;
    }

    private Map<String, String> extractProductFeatures(List<ProductFeatureModel> featureModels) {
        Map<String, String> map = new LinkedHashMap<>();

        for (ProductFeatureModel featureModel : emptyIfNull(getProductFeaturesForCurrentLanguage(featureModels))) {
            map.put(extractClassAttributeName(featureModel), featureModel.getValue().toString());
        }

        return map;

    }

    private String extractTitle(ProductModel productModel) {

        Optional<ProductFeatureModel> productFeatureModelOptional = productModel.getFeatures().stream().findFirst();
        return productFeatureModelOptional.isPresent() ?
            validateTitleField(productFeatureModelOptional.get().getClassificationAttributeAssignment()) : StringUtils.EMPTY;
    }

    private String extractClassAttributeName(ProductFeatureModel featureModel) {

        return (featureModel.getClassificationAttributeAssignment() != null && featureModel.getClassificationAttributeAssignment().getClassificationAttribute() != null) ?
            featureModel.getClassificationAttributeAssignment().getClassificationAttribute().getName() : StringUtils.EMPTY;
    }

    private String validateTitleField(ClassAttributeAssignmentModel classAttributeAssignmentModel) {
        return classAttributeAssignmentModel != null && classAttributeAssignmentModel.getClassificationClass() != null ?
            classAttributeAssignmentModel.getClassificationClass().getName() : StringUtils.EMPTY;
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