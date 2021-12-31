package com.investsaudi.portal.storefront.functions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import com.investsaudi.portal.core.jalo.InvestSaudiNewsComponent;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import de.hybris.platform.acceleratorservices.storefront.data.MetaElementData;
import de.hybris.platform.acceleratorstorefrontcommons.tags.Functions;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.jalo.CMSComponentType;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.pages.ProductPageModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForPageModel;
import de.hybris.platform.cms2.model.restrictions.CMSCategoryRestrictionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.url.impl.DefaultCategoryModelUrlResolver;
import de.hybris.platform.commerceservices.url.impl.DefaultProductModelUrlResolver;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static de.hybris.platform.acceleratorstorefrontcommons.tags.Functions.getSpringBean;

@Component("portalElFunctions")
public class PortalElFunctions {
    private PortalElFunctions() {

    }

    private static final String CHAR = "_";
    private static final String NEWS_STRING = "news";
    private static final String URL_STRING = "url";

    @Resource(name = "sectorCategoryModelUrlResolver")
    private DefaultCategoryModelUrlResolver sectorCategoryModelUrlResolver;

    @Resource(name = "opportunityModelUrlResolver")
    private DefaultProductModelUrlResolver opportunityModelUrlResolver;

    @Resource(name = "productUrlConverter")
    private Converter<ProductModel, ProductData> productUrlConverter;

    @Resource(name = "categoryUrlConverter")
    private Converter<CategoryModel, CategoryData> categoryUrlConverter;

    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    public String generateCategoryLink(CategoryModel categoryModel) {
        return sectorCategoryModelUrlResolver.resolve(categoryModel);
    }

    public String cmsLinkUrl(CMSLinkComponentModel cmsLink) {
        if (cmsLink != null) {
            String url = Functions.getUrlForCMSLinkComponent(cmsLink, productUrlConverter, categoryUrlConverter);
            if (StringUtils.startsWith(url, "/")) {
                return (getCurrentRequest().getContextPath() + url);
            } else {
                return url;
            }
        }
        return null;
    }

    public String getMetaDescription(List<MetaElementData> metaElementDataList) {
        if (CollectionUtils.isNotEmpty(metaElementDataList)) {
            Optional<MetaElementData> first = metaElementDataList.stream()
                .filter(metaElementData -> StringUtils.equals(metaElementData.getName(), "description")).findFirst();

            return first.isPresent() ? first.get().getContent() : "";
        }

        return null;
    }

    public String getMetaImage(AbstractPageModel pageModel, String param) {

        MediaModel mediaModel = null;
        if(pageModel instanceof ContentPageModel)
        {
            if(((ContentPageModel) pageModel).getLabel().indexOf(NEWS_STRING) == 1){
                final Optional<ContentSlotForPageModel> pageSlot = pageModel.getContentSlots().stream().findFirst();
                if(pageSlot.isPresent()){
                    final Optional<InvestSaudiNewsComponentModel> newsComponent = pageSlot.get().getContentSlot().getCmsComponents().stream()
                            .filter(InvestSaudiNewsComponentModel.class::isInstance)
                            .map(InvestSaudiNewsComponentModel.class::cast)
                            .findFirst();
                    if(newsComponent.isPresent()){
                        if(newsComponent.get().getNewsDetailsImage() != null){
                            mediaModel =  newsComponent.get().getNewsDetailsImage();
                        }
                    }
                }
                else
                {
                    mediaModel = getBaseSiteMedia();
                }
            }
            else
            {
                mediaModel = getBaseSiteMedia();
            }
        }
        else if (pageModel instanceof CategoryPageModel){
            final Optional<CMSCategoryRestrictionModel> cmsCategoryRestrictionModel = pageModel.getRestrictions().stream()
                    .map(CMSCategoryRestrictionModel.class::cast)
                    .findFirst();
            if(cmsCategoryRestrictionModel.isPresent()){
                final Optional<String> categoryCode = cmsCategoryRestrictionModel.get().getCategoryCodes().stream().findFirst();
                if(categoryCode.isPresent()){
                    final CategoryModel category = categoryService.getCategoryForCode(categoryCode.get());
                    final Optional<MediaModel> categoryMediaModel = category.getOthers().stream().findFirst();
                    if(categoryMediaModel.isPresent()){
                        mediaModel = categoryMediaModel.get();
                    }
                }
            }
        }
        else
        {
            mediaModel = getBaseSiteMedia();
        }
        if(mediaModel != null)
        {
            if (param.equals(URL_STRING))
            {
                return mediaModel.getURL();
            }
            else
            {
                return mediaModel.getMime();
            }
        }
        return null;
    }
    
    public String getConfiguration(String key) {
        return Config.getString(key, null);
    }

    private MediaModel getBaseSiteMedia(){
        return ((CMSSiteModel) baseSiteService.getCurrentBaseSite()).getMetaImage();
    }

    public Integer countCharactersOccurence(String string) {
        return StringUtils.countMatches(string, CHAR);
    }

    private DefaultCategoryModelUrlResolver resolverUrl(final HttpServletRequest httpRequest) {
        return getSpringBean(httpRequest, "sectorCategoryModelUrlResolver", DefaultCategoryModelUrlResolver.class);
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
