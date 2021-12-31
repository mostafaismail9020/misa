package com.sap.ibso.eservices.facades.sagia.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.sap.ibso.eservices.core.event.SagiaPublishLicenseEvent;
import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import com.sap.ibso.eservices.facades.data.odata.ServiceRequestCreation;
import com.sap.ibso.eservices.facades.data.zqeemah.BusinessActivity;
import com.sap.ibso.eservices.facades.data.zqeemah2.Attachment;
import com.sap.ibso.eservices.facades.populators.odata.ServiceRequestCreationReverseODataPopulator;
import com.sap.ibso.eservices.facades.populators.odata.ServiceRequestODataCreationPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaODataFacade;
import com.sap.ibso.eservices.sagiaservices.data.odata.ODataServiceRequestCreationData;
import com.sap.ibso.eservices.sagiaservices.data.odata.BasicContactInformationData;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntityInformationData;
import com.sap.ibso.eservices.sagiaservices.data.odata.IsicInfoData;
import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.IsicDetPsSetData;
import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderAttachmentData;
import com.sap.ibso.eservices.sagiaservices.services.odata.BasicContactInformationService;
import com.sap.ibso.eservices.sagiaservices.services.odata.EntityInformationService;
import com.sap.ibso.eservices.sagiaservices.services.odata.IsicInformationService;
import com.sap.ibso.eservices.sagiaservices.services.odata.ShareHolderODataService;
import com.sap.ibso.eservices.sagiaservices.services.odata.ShareHolderInfoAttService;
import com.sap.ibso.eservices.sagiaservices.services.odata.ServiceRequestODataCreationDataService;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;

public class DefaultSagiaODataFacade implements SagiaODataFacade {
	
	@Resource
    private ServiceRequestODataCreationPopulator serviceRequestODataCreationPopulator;
    
    @Resource
    private ServiceRequestCreationReverseODataPopulator serviceRequestCreationReverseODataPopulator;
    
    @Resource
    private ServiceRequestODataCreationDataService serviceRequestODataCreationDataService;
    
    
    @Resource
   	private Converter<ContactPersonModel, BasicContactInformationData> basicContactInformationODataConverter;
    
    @Resource
   	private Converter<EntityInformationModel, EntityInformationData> entityODataConverter;
    
    
    @Resource                                                
   	private Converter<MediaModel, ShareholderAttachmentData> shareHolderAttachmentODataConverter;
    
    @Resource
   	private Converter<IsicMasterModel, BusinessActivity> businessActivityODataConverter;
    
    @Resource
    private BasicContactInformationService odataBasicContactInformationService ;
    
    @Resource
    private EntityInformationService odataEntityInformationService ;
    
    @Resource
    private ShareHolderODataService shareHolderODataService ;
    
    @Resource
	private LicenseApplyService licenseApplyService;
    
    
    @Resource
	private ShareHolderInfoAttService shareHolderInfoAttService;
    
    @Resource
    private IsicInformationService odataIsicInformationService;
    

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	
	private static String temporaryLicenseConstant = Config.getString("temporary.licese.constant.value", "6");
	
	@Resource
	private Converter<ShareHolderModel, ShareholderData> shareHolderODataConverter;
	
	@Resource
	private Converter<IsicMasterModel, IsicInfoData> isicInfoODataPopulatorConverter;
	
    @Autowired
    private EventService eventService;
    
	@Override
	public ServiceRequestCreation saveODataServiceRequestCreation(CustomerModel customer) {
		SagiaLicenseModel draftLicense = licenseApplyService.getDraftLicense();
		
		String advanceLicenseNr ="";
		if(draftLicense.getEntityInformation().isHasAdvanceLicenseNr())
		{
			advanceLicenseNr = draftLicense.getEntityInformation().getAdvanceLicenseNr();
		}
		
		ServiceRequestCreation serviceRequestCreation = createServiceRequestObj(advanceLicenseNr);
    	ODataServiceRequestCreationData data = new ODataServiceRequestCreationData();
    	serviceRequestCreationReverseODataPopulator.populate(serviceRequestCreation, data);
        ODataServiceRequestCreationData oppServiceCreationData = serviceRequestODataCreationDataService.saveServiceRequestCreation(data,customer);
        ServiceRequestCreation serviceRequestCreationResponse = new ServiceRequestCreation();
        serviceRequestODataCreationPopulator.populate(oppServiceCreationData, serviceRequestCreationResponse);
        return serviceRequestCreationResponse;
    
	}
	
	private ServiceRequestCreation createServiceRequestObj(String advanceLicenseNr) {
		ServiceRequestCreation serviceRequestCreationPost = new ServiceRequestCreation();
		serviceRequestCreationPost.setAdvlicno(advanceLicenseNr);
		Collection<com.sap.ibso.eservices.facades.data.zqeemah2.Attachment> attachments = new ArrayList<>();
		Attachment attachment = new Attachment();
		attachments.add(attachment);
		//serviceRequestCreationPost.setAttachments(attachments);
		return serviceRequestCreationPost;
	}
	
	@Override
    public BasicContactInformationData saveContactPersonOData(ContactPersonModel contactPersonModel) {
        if (contactPersonModel == null ) {
        	return null;
        }
        BasicContactInformationData basicContactInformationData = basicContactInformationODataConverter.convert(contactPersonModel);
        return odataBasicContactInformationService.saveContact(basicContactInformationData);
    }


	@Override
	public EntityInformationData saveEntityOData(EntityInformationModel entityInformationModel) {
        if (entityInformationModel == null ) {
        	return null;
        }
        EntityInformationData entityInformationData = entityODataConverter.convert(entityInformationModel);
        return odataEntityInformationService.saveEntity(entityInformationData,entityInformationModel);
    }
	
	@Override 
	public ShareholderData saveShareHolderOData(ShareHolderModel shareHolderModel) {
        if (shareHolderModel == null ) {
        	return null;
        }
        ShareholderData shareholderData = shareHolderODataConverter.convert(shareHolderModel);
        return shareHolderODataService.saveShareholder(shareholderData,shareHolderModel);
        
    }
	
	@Override 
	public String uploadAttachmentOData(MediaModel mediaModel,String delegate,String refid,String guid,String fileType,String objectid,String shCode) {
        if (mediaModel == null ) {
        	return null;
        }
        ShareholderAttachmentData shareholderAttachmentData = shareHolderAttachmentODataConverter.convert(mediaModel);
        shareholderAttachmentData.setDelegate(delegate);
        shareholderAttachmentData.setRefid(refid);
        shareholderAttachmentData.setGuid(guid);
        shareholderAttachmentData.setFileType(fileType);
        shareholderAttachmentData.setObjectid(objectid);
        shareholderAttachmentData.setShhldcode(shCode);
        return shareHolderInfoAttService.uploadAttachmentFile(shareholderAttachmentData);
        
    }
	
	@Override 
	public void savesIsicInfoOData(SagiaLicenseModel license) {
		
		List<IsicMasterModel> isicList = license.getEntityInformation().getIsicActivities();
		
		if(license.getEntityInformation().getLicenseType().getCode().equals(temporaryLicenseConstant)) {
			IsicInfoData isicInfoData = new IsicInfoData();
			isicInfoData.setAct(license.getEntityInformation().getLicenseType().getCode());  
			isicInfoData.setLicenseType(license.getEntityInformation().getLicenseType().getCode());  
			isicInfoData.setInvestorid(license.getApplicantReferenceID());
			isicInfoData.setGuid(license.getGuid());
			isicInfoData.setObjectid(license.getApplicationServiceRequestID());
			isicInfoData.setPurofctrct(license.getEntityInformation().getTemporaryLicenseText());
			odataIsicInformationService.save(isicInfoData);
		}else if (CollectionUtils.isNotEmpty(isicList))
		{
			for (IsicMasterModel isicMasterModel : isicList) {
				IsicInfoData isicInfoData = isicInfoODataPopulatorConverter.convert(isicMasterModel);
				isicInfoData.setInvestorid(license.getApplicantReferenceID());
				isicInfoData.setGuid(license.getGuid());
				isicInfoData.setObjectid(license.getApplicationServiceRequestID());
				isicInfoData.setLicenseType(license.getEntityInformation().getLicenseType().getCode());  
				odataIsicInformationService.save(isicInfoData);
			}
		}
		 
         
    }

}
