package com.investsaudi.portal.facades.product.populator;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.investsaudi.portal.core.model.NewsProductModel;
import com.investsaudi.portal.core.model.ReportProductModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.investsaudi.portal.core.model.ArticleProductModel;
import com.investsaudi.portal.core.model.InvestSaudiMediaModel;

import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.catalog.model.ProductFeatureModel;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ProductReferenceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;


public class InvestSaudiReportPopulator implements Populator<ProductData, ReportProductModel> {

    private static final String SECTOR_URL = "/sectors-opportunities/";
    private static final String ARTICLE_TYPE = "ReportProduct";


    private Converter<MediaModel, ImageData> imageConverter;

    private Converter<ProductReferenceModel, ProductReferenceData> productReferenceConverter;

    @Resource
    private I18NService i18NService;

    @Resource
    private Converter<InvestSaudiMediaModel, ImageData> sagiaImageConverter;

    @Override
    public void populate(ProductData productData, ReportProductModel productModel) throws ConversionException {

        Locale currentLocale = i18NService.getCurrentLocale();
        productData.setCode(productModel.getCode());
        productData.setName(productModel.getName());
        productData.setDescription(productModel.getDescription(currentLocale));
        productData.setImageUrl(extractMediaUrl(productModel.getOthers()));
        productData.setOpportunityDetailsTitle(extractTitle(productModel));
        productData.setFeatureMap(extractProductFeatures(productModel.getFeatures()));
        productData.setSummary(productModel.getSummary());
        productData.setProductType(ARTICLE_TYPE);
        productData.setProductReferences(populateProductReference(productModel.getProductReferences()));


        final MediaModel overviewImage = productModel.getPicture();
        if (overviewImage != null) {
            productData.setOverviewPicture(getImageConverter().convert(overviewImage));
            productData.getOverviewPicture().setAltText(overviewImage.getAltText());
        }

        final Optional<CategoryModel> parentCategory = emptyIfNull(productModel.getSupercategories()).stream().findFirst();
        if (parentCategory.isPresent()) {
            productData.setParentCategory(parentCategory.get().getCode());
            productData.setUrl(SECTOR_URL + "reports" + "/" + productModel.getCode());

            final MediaModel logoImage = parentCategory.get().getPicture();
            if (logoImage != null)
            {
                productData.setLogo(getImageConverter().convert(logoImage));
            }

            final Optional<MediaModel> bannerImage = parentCategory.get().getOthers().stream().findFirst();
            if(bannerImage.isPresent())
            {
                productData.setBanner(getImageConverter().convert(bannerImage.get()));
            }

            final Optional<MediaModel> normalImage = parentCategory.get().getNormal().stream().findFirst();
            if(normalImage.isPresent())
            {
                productData.setNormal(getImageConverter().convert(normalImage.get()));
            }
        }

        final List<KeywordModel> keywords = productModel.getKeywords();
        final Set<String> keywordSet = new HashSet<String>(keywords.size());
        for (final KeywordModel keyword : keywords) {
            keywordSet.add(keyword.getKeyword());
        }
        productData.setKeywords(keywordSet);

        productData.setVideoUrl(productModel.getVideoUrl());
        productData.setSubHeadings(productModel.getReportSubHeadings(currentLocale));
        if (null != productModel.getParaWithMedia(currentLocale)) {
            productData.setParaWithMedia(extractParaWithMedia(productModel.getParaWithMedia(currentLocale)));
        }
    }


    private Map<String, ImageData> extractParaWithMedia(InvestSaudiMediaModel paraWithMedia) {
        ImageData media = sagiaImageConverter.convert(paraWithMedia);
        return Map.of(media.getDescription(), media);
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
        return (featureModel.getClassificationAttributeAssignment() != null &&
                featureModel.getClassificationAttributeAssignment().getClassificationAttribute() != null) ?
                featureModel.getClassificationAttributeAssignment().getClassificationAttribute().getName() : StringUtils.EMPTY;
    }

    private String validateTitleField(ClassAttributeAssignmentModel classAttributeAssignmentModel) {
        return classAttributeAssignmentModel != null && classAttributeAssignmentModel.getClassificationClass() != null ?
                classAttributeAssignmentModel.getClassificationClass().getName() : StringUtils.EMPTY;
    }

    private List<ProductFeatureModel> getProductFeaturesForCurrentLanguage(List<ProductFeatureModel> featureModels) {
        String currentIso = i18NService.getCurrentLocale().getLanguage();

        return emptyIfNull(featureModels).stream().filter(featureModel ->
                validateLanguage(featureModel).equalsIgnoreCase(currentIso)).collect(Collectors.toList());
    }

    private String validateLanguage(ProductFeatureModel featureModel) {
        if (null != featureModel && null != featureModel.getLanguage() && null != featureModel.getLanguage().getIsocode()) {
            return featureModel.getLanguage().getIsocode();
        } else {
            return StringUtils.EMPTY;
        }
    }

    private List<ProductReferenceData> populateProductReference(Collection<ProductReferenceModel> references)
    {
        List<ProductReferenceData> productReferences = new ArrayList<ProductReferenceData>();
        ProductReferenceData productRefData = null;

        for (final ProductReferenceModel productRefer : references)
        {
            productRefData = getProductReferenceConverter().convert(productRefer);

            ProductModel opportunity = productRefer.getTarget();
            if (null != opportunity) {
                final Optional<CategoryModel> parentCategory = emptyIfNull(opportunity.getSupercategories()).stream().findFirst();
                if (parentCategory.isPresent()) {
                    productRefData.getTarget().setParentCategory(parentCategory.get().getName());
                    productRefData.getTarget().setUrl(SECTOR_URL + parentCategory.get().getCode() + "/" + opportunity.getCode());
                }
            }

            productReferences.add(productRefData);
        }
        return productReferences;
    }

    public Converter<MediaModel, ImageData> getImageConverter() {
        return imageConverter;
    }

    @Required
    public void setImageConverter(final Converter<MediaModel, ImageData> imageConverter) {
        this.imageConverter = imageConverter;
    }

    public Converter<ProductReferenceModel, ProductReferenceData> getProductReferenceConverter()
    {
        return productReferenceConverter;
    }

    @Required
    public void setProductReferenceConverter(final Converter<ProductReferenceModel, ProductReferenceData> productReferenceConverter)
    {
        this.productReferenceConverter = productReferenceConverter;
    }

}


