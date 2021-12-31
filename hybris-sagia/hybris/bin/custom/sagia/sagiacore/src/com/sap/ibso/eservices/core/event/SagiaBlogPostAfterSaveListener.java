/*
package com.sap.ibso.eservices.core.event;

import com.casblogaddon.model.BlogPostComponentModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.enums.CmsApprovalStatus;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.pages.PageTemplateModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForPageModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.tx.AfterSaveEvent;
import de.hybris.platform.tx.AfterSaveListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SagiaBlogPostAfterSaveListener implements AfterSaveListener {

    private ModelService modelService;
    private FlexibleSearchService flexibleSearchService;
    private CatalogVersionService catalogVersionService;

    private final static String CATALOG_ID = "investsaudiContentCatalog"; //get currentCMS Catalog -> get or implement
    private final static String VERSION_STAGED = "Staged";
    private final static String CATALOG_DEFAULT = "Default";
    private final static String SECTION_PATTERN = "Section2A-";
    private final static String BLOG_POST_PAGE_TEMPLATE = "BlogPostPageTemplate";

    @Override
    public void afterSave(Collection<AfterSaveEvent> collection) {
        for (final AfterSaveEvent event : collection) {
            final int type = event.getType();

             if (AfterSaveEvent.CREATE == type){

                final PK pk = event.getPk();

                if (1084 == pk.getTypeCode()) {

                    Object object = getModelService().get(pk);
                    if(object instanceof BlogPostComponentModel)
                    {
                        final BlogPostComponentModel blogPostComponent = getModelService().get(pk);
                        createCMSScructureforBlogPost(blogPostComponent);
                    }
                }

            }
        }
    }

    private void createCMSScructureforBlogPost(BlogPostComponentModel blogPostComponent) {

        if(blogPostComponent.getCatalogVersion().getCatalog().getId().equals(CATALOG_DEFAULT)){

            final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(CATALOG_ID, VERSION_STAGED);

            blogPostComponent.setCatalogVersion(catalogVersion);

            final ContentSlotModel contentSlotModel =  getModelService().create(ContentSlotModel.class);
            contentSlotModel.setCatalogVersion(catalogVersion);
            contentSlotModel.setUid(SECTION_PATTERN + blogPostComponent.getName());
            contentSlotModel.setCmsComponents(Arrays.asList(blogPostComponent));
            contentSlotModel.setName(blogPostComponent.getName() + " BlogPost Slot");

            final ContentPageModel contentPage =  getModelService().create(ContentPageModel.class);
            contentPage.setCatalogVersion(catalogVersion);
            contentPage.setUid("blog"+blogPostComponent.getName());
            contentPage.setName("Blog Post " + blogPostComponent.getName());
            contentPage.setMasterTemplate(getPageTemplateModel(catalogVersion));
            contentPage.setLabel("/blog/" + blogPostComponent.getName());
            contentPage.setDefaultPage(Boolean.TRUE);
            contentPage.setApprovalStatus(CmsApprovalStatus.APPROVED);
            contentPage.setHomepage(Boolean.FALSE);

            final ContentSlotForPageModel contentSlotForPage =  getModelService().create(ContentSlotForPageModel.class);
            contentSlotForPage.setUid(SECTION_PATTERN + blogPostComponent.getName());
            contentSlotForPage.setPosition("Section2A");
            contentSlotForPage.setPage(contentPage);
            contentSlotForPage.setCatalogVersion(catalogVersion);
            contentSlotForPage.setContentSlot(contentSlotModel);

            modelService.saveAll(blogPostComponent, contentSlotModel, contentPage, contentSlotForPage);

        }

    }

    private PageTemplateModel getPageTemplateModel(CatalogVersionModel catalogVersion) {
        final PageTemplateModel pageTemplateModel = modelService.create(PageTemplateModel.class);
        pageTemplateModel.setUid(BLOG_POST_PAGE_TEMPLATE);
        pageTemplateModel.setCatalogVersion(catalogVersion);
        return flexibleSearchService.getModelByExample(pageTemplateModel);
    }



    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public CatalogVersionService getCatalogVersionService() {
        return catalogVersionService;
    }

    public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
*/
