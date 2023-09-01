package com.investsaudi.portal.facades.product.populator;

import com.google.common.collect.Lists;
import com.investsaudi.portal.core.model.InvestSaudiMediaModel;
import com.investsaudi.portal.core.model.InvestmentOverviewModel;
import com.investsaudi.portal.core.model.LocationModel;
import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.sap.ibso.eservices.core.model.OpportunityPartnerModel;

import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.catalog.model.ProductFeatureModel;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ProductReferenceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import com.sap.ibso.eservices.facades.data.OpportunityPartnerData;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;


public class InvestSaudiOpportunityPopulator implements Populator<ProductData, OpportunityProductModel> {

    private static final String SECTOR_URL = "/sectors-opportunities/";
    private static final String TYPE = "OpportunityProduct";

    private static final String PARTNER_ENABLER = "Enabler";
    private static final String PARTNER_ACCELERATOR = "Accelerator";
    private static final String PARTNER_INCUBATOR = "Incubator";
    
    
    private Converter<MediaModel, ImageData> imageConverter;
    
    private Converter<ProductReferenceModel, ProductReferenceData> productReferenceConverter;

    @Resource
    private I18NService i18NService;

    @Resource
    private Converter<InvestSaudiMediaModel, ImageData> sagiaImageConverter;
    
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
        productData.setProductReferences(populateProductReference(productModel.getProductReferences()));
        productData.setPartnerMap(populateProductPartnerDetails(productModel.getPartner()));
        
        final MediaModel overviewImage = productModel.getPicture();
        if (overviewImage != null) {
            productData.setOverviewPicture(getImageConverter().convert(overviewImage));
            productData.getOverviewPicture().setAltText(overviewImage.getAltText());
        }
        
        final Optional<CategoryModel> parentCategory = emptyIfNull(productModel.getSupercategories()).stream().findFirst();
        if (parentCategory.isPresent()) {
            productData.setParentCategory(parentCategory.get().getCode());
            productData.setUrl(SECTOR_URL + parentCategory.get().getCode() + "/" + productModel.getCode());
            
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

        if (productModel.getSeoKeywords() != null) {
            keywordSet.add(productModel.getSeoKeywords());
        }

        productData.setKeywords(keywordSet);
        
        productData.setOpportunityHighlights(getOpportunityHighlights(productModel));
        productData.setOpportunitySubHeadings(productModel.getOpportunitySubHeadings(currentLocale));
        productData.setOpportunityHeadingsWithMedia(getSubHeadingWithMedia(productModel, currentLocale));
        productData.setParaWithMedia(extractParaWithMedia(productModel.getParaWithMedia(currentLocale)));
        productData.setOpportunityDetailGrid(getOpportunityDetailGrid(productModel, currentLocale));
        productData.setOpportunityLead(sagiaImageConverter.convert(productModel.getOpportunityLead(currentLocale)));
    }


	private List<String> getOpportunityDetailGrid(OpportunityProductModel productModel, Locale currentLocale) {
		if(null != productModel.getInvestmentOverview() && null != productModel.getInvestmentOverview().getInvestmentHighlights()
				&& CollectionUtils.isNotEmpty(productModel.getLocation()) && null != productModel.getLocation().iterator().next().getCity() 
				&& null != productModel.getLocation().iterator().next().getRegion()) {
			return Arrays.asList(productModel.getLocation().iterator().next().getCity().getName(currentLocale), 
					emptyIfNull(productModel.getSupercategories()).stream().map(c -> c.getName(currentLocale)).findFirst().orElse(""),
					productModel.getInvestmentOverview().getInvestmentHighlights().getExpectedIRR(), 
					productModel.getInvestmentOverview().getInvestmentHighlights().getExpectedInvestmentSize());
		}
		return Collections.emptyList();
	}


	private Map<String, ImageData> getSubHeadingWithMedia(OpportunityProductModel productModel, Locale currentLocale) {
		ImageData image = new ImageData();
		Set<LocationModel> locations = productModel.getLocation();
		if (CollectionUtils.isNotEmpty(locations) && null != productModel.getLocation().iterator().next().getCity() 
				&& null != productModel.getLocation().iterator().next().getRegion()) {
			image.setDescription(productModel.getLocation().iterator().next().getCity().getName(currentLocale));
			image.setDescriptionText(productModel.getLocation().iterator().next().getRegion().getName(currentLocale));
			if (CollectionUtils.isNotEmpty(productModel.getOtherText())) {
				if (CollectionUtils.isNotEmpty(productModel.getOtherText().iterator().next().getAttachment())) {
					image.setUrl(productModel.getOtherText().iterator().next().getAttachment().iterator().next().getEncodedString());
				}
			}
			return Maps.of(image.getDescription(), image);
		}
		return Collections.emptyMap();
	}


    private Map<String, ImageData> extractParaWithMedia(InvestSaudiMediaModel paraWithMedia) {
		ImageData media = sagiaImageConverter.convert(paraWithMedia);
		return Map.of(media.getDescription(), media);
	}
	
	protected List<String> getOpportunityHighlights(OpportunityProductModel productModel) {
		if(null != productModel.getInvestmentOverview() && null != productModel.getInvestmentOverview().getInvestmentHighlights()) {
			return Arrays.asList(productModel.getInvestmentOverview().getInvestmentHighlights().getExpectedIRR(), 
					productModel.getInvestmentOverview().getInvestmentHighlights().getExpectedInvestmentSize());
		}
		return Collections.emptyList();
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
    
    private Map<String, List<OpportunityPartnerData>> populateProductPartnerDetails(List<OpportunityPartnerModel> partners) 
    {
    	String website = null;
    	OpportunityPartnerData partnerData = null;
    	List<OpportunityPartnerData> enablerPartners = new ArrayList<OpportunityPartnerData>();
    	List<OpportunityPartnerData> acceleratorPartners = new ArrayList<OpportunityPartnerData>();
    	List<OpportunityPartnerData> incubatorPartners = new ArrayList<OpportunityPartnerData>();
    	Map<String, List<OpportunityPartnerData>> partnersMap = new HashMap<String, List<OpportunityPartnerData>>();
    	
    	for (final OpportunityPartnerModel partnerModel : partners)
		{
    		if (partnerModel.getPartnerType().getCode().equals(PARTNER_ENABLER)) {
    			CustomerModel customer = partnerModel.getCustomer();
        		if (null != customer) {
        			partnerData = new OpportunityPartnerData();
        			if (null != customer.getCompanyLogo()) {     			
        				partnerData.setCompanyLogo(getImageConverter().convert(customer.getCompanyLogo()));
        			}
        			partnerData.setCompanyWebsite(customer.getCompanyWebsite());
        			enablerPartners.add(partnerData);
        		}
    		}
    		if (partnerModel.getPartnerType().getCode().equals(PARTNER_ACCELERATOR)) {
    			CustomerModel customer = partnerModel.getCustomer();
        		if (null != customer) {
        			partnerData = new OpportunityPartnerData();
        			if (null != customer.getCompanyLogo()) {
        				partnerData.setCompanyLogo(getImageConverter().convert(customer.getCompanyLogo()));
        			}
        			partnerData.setCompanyWebsite(customer.getCompanyWebsite());
        			acceleratorPartners.add(partnerData);
        		}
    		}
    		if (partnerModel.getPartnerType().getCode().equals(PARTNER_INCUBATOR)) {
    			CustomerModel customer = partnerModel.getCustomer();
        		if (null != customer) {
        			partnerData = new OpportunityPartnerData();
        			if (null != customer.getCompanyLogo()) {     			
        				partnerData.setCompanyLogo(getImageConverter().convert(customer.getCompanyLogo()));
        			}
        			partnerData.setCompanyWebsite(customer.getCompanyWebsite());
        			incubatorPartners.add(partnerData);
        		}
    		}
		}
    	partnersMap.put(PARTNER_ENABLER, enablerPartners);
    	partnersMap.put(PARTNER_ACCELERATOR, acceleratorPartners);
    	partnersMap.put(PARTNER_INCUBATOR, incubatorPartners);
    	
    	return partnersMap;
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
