package com.sap.ibso.eservices.core.event;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.investsaudi.portal.core.service.ContactTicketBusinessService;
import com.investsaudi.portal.core.service.InvestSaudiProductService;
import com.investsaudi.portal.core.service.OpportunityProductMediaRestApiService;
import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.sagia.services.SagiaTermsAndConditionsService;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.tx.AfterSaveEvent;
import de.hybris.platform.tx.AfterSaveListener;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

public class SagiaAfterSaveListener implements AfterSaveListener {

    private static final String TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS = "SagiaCMSParagraphTermsAndConditions";
    private static final String COMPONENT = "Component";
    private static final String TC_CMS_PARAGRAPH_REGISTRATION = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "Registration" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_GOV_DOCS = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "GovtDocs" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_LICENSE_SERVICES = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "LicenseServices" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_SPECIAL_SERVICES = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "SpecialServices" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_INTERIOR_PASSPORT = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "InteriorPassport" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_INTERIOR_RECRUITMENT = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "InteriorRecruitment" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_LABOUR = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "Labour" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_COMMERCE_INDUSTRY = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "CommerceIndustry" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_FINANCIAL_STATEMENT = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "FinancialStatement" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_REAL_ESTATE = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "RealEstate" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_FOLLOW_UP = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "FollowUp" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_LEGAL_CONSULTATION = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "LegalConsultation" + COMPONENT;
    private static final String TC_CMS_PARAGRAPH_APPLY_NEW_LICENSE = TC_CMS_PARAGRAPH_TERMS_AND_CONDITIONS + "ApplyNewLicense" + COMPONENT;

    private ModelService modelService;
    private SagiaTermsAndConditionsService sagiaTermsAndConditionsService;
    @Resource
    private CMSSiteService cmsSiteService;
    @Autowired
    private ContactTicketBusinessService contactTicketBusinessService;
    @Resource
    private InvestSaudiProductService investSaudiProductService;
    @Resource
    private OpportunityProductMediaRestApiService opportunityProductMediaRestApiService;
    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Override
    public void afterSave(Collection<AfterSaveEvent> collection) {
        boolean isMizaContactUsFlow=false;
        if(null!=sessionService.getAttribute("isMizaContactUsFlow"))
        {
            isMizaContactUsFlow=true;
        }
        for (final AfterSaveEvent event : collection) {
            final int type = event.getType();
            if (AfterSaveEvent.UPDATE == type) {
                final PK pk = event.getPk();

                if (1084 == pk.getTypeCode()) {
                	Object object = getModelService().get(pk);
                	if(object instanceof CMSParagraphComponentModel)
                	{
                		final CMSParagraphComponentModel paragraphComponentModel = getModelService().get(pk);
                		handleTermsAndConditionsUpdate(paragraphComponentModel);
                	}
                }
            }
			if (AfterSaveEvent.CREATE == type ) {
                final PK pk = event.getPk();
                Object object = getModelService().get(pk);
                if(object instanceof CustomerModel && !isMizaContactUsFlow){
                   String initialPassword=RandomStringUtils.randomAlphanumeric(Config.getInt("default.password.length", 8));
                   contactTicketBusinessService.sendOpportunityUserDetails(null, (UserModel) object, initialPassword,
				      SagiaCoreConstants.ORIGINSYSTEM.equalsIgnoreCase(((CustomerModel) object).getSystemOrigin()));
                }
                if(isMizaContactUsFlow)
                {
                    contactTicketBusinessService.sendMizaTicketDetails(null, (UserModel) object);
                }
                if(object instanceof OpportunityProductModel){
                    uploadPdfForOpportunity(((OpportunityProductModel) object));
                }
            }
        }
    }

    /**
     * To update media
     * @param opportunityProductModel
     */
    public void uploadPdfForOpportunity(OpportunityProductModel opportunityProductModel) {
            if (opportunityProductModel.getItemtype().equals(OpportunityProductModel._TYPECODE)) {
                if (null != opportunityProductModel) {
                    Collection<MediaModel> productDetails = new ArrayList<>();
                    if(CollectionUtils.isEmpty(opportunityProductModel.getDetail())) {
                        if(null != opportunityProductModel.getDownloadUrl()) {
                            MediaModel media = opportunityProductMediaRestApiService.uploadMediaAttachmentAsProduct(opportunityProductModel.getDownloadUrl(), opportunityProductModel.getCode(),opportunityProductModel.getCatalogVersion());
                            productDetails.add(media);
                            opportunityProductModel.setDetail(productDetails);
                            getModelService().save(opportunityProductModel);
                        }
                    }
                }
            }
    }

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | The cyclomatic complexity of methods should not exceed a defined threshold.)
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings("squid:MethodCyclomaticComplexity")
    private void handleTermsAndConditionsUpdate(CMSParagraphComponentModel paragraph) {
        switch (paragraph.getUid()) {
            case TC_CMS_PARAGRAPH_REGISTRATION:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.REGISTRATION);
                break;
            case TC_CMS_PARAGRAPH_GOV_DOCS:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.GOVERNMENT_DOCUMENTS);
                break;
            case TC_CMS_PARAGRAPH_LICENSE_SERVICES:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
                break;
            case TC_CMS_PARAGRAPH_SPECIAL_SERVICES:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.SPECIAL_SERVICES);
                break;
            case TC_CMS_PARAGRAPH_INTERIOR_PASSPORT:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.INTERIOR_PASSPORT);
                break;
            case TC_CMS_PARAGRAPH_INTERIOR_RECRUITMENT:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.INTERIOR_RECRUITMENT);
                break;
            case TC_CMS_PARAGRAPH_LABOUR:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.LABOUR);
                break;
            case TC_CMS_PARAGRAPH_COMMERCE_INDUSTRY:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.COMMERCE_INDUSTRY);
                break;
            case TC_CMS_PARAGRAPH_FINANCIAL_STATEMENT:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.FINANCIAL_STATEMENT);
                break;
            case TC_CMS_PARAGRAPH_LEGAL_CONSULTATION:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.LEGAL_CONSULTATION);
                break;
            case TC_CMS_PARAGRAPH_APPLY_NEW_LICENSE:  
                	sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.APPLY_NEW_LICENSE);  
                	break;  
            default:
                handleTermsAndConditionsRealEstateAndFollowUpUpdate(paragraph);
                break;
        }
    }

    private void handleTermsAndConditionsRealEstateAndFollowUpUpdate(CMSParagraphComponentModel paragraph) {
        switch (paragraph.getUid()) {
            case TC_CMS_PARAGRAPH_REAL_ESTATE:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.REAL_ESTATE);
                break;
            case TC_CMS_PARAGRAPH_FOLLOW_UP:
                sagiaTermsAndConditionsService.handleTermsAndConditionsUpdate(paragraph.getContent(), TermsAndConditionsAcceptanceEventEnum.FOLLOW_UP);
                break;
            default:
                break;
        }
    }


    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public SagiaTermsAndConditionsService getSagiaTermsAndConditionsService() {
        return sagiaTermsAndConditionsService;
    }

    public void setSagiaTermsAndConditionsService(SagiaTermsAndConditionsService sagiaTermsAndConditionsService) {
        this.sagiaTermsAndConditionsService = sagiaTermsAndConditionsService;
    }
}










