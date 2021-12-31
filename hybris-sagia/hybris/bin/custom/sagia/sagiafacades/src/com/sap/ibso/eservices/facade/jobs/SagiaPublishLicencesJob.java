package com.sap.ibso.eservices.facade.jobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.PublishStatus;
import com.sap.ibso.eservices.core.jalo.SAGIASiteCronJob;
import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.SAGIASiteCronJobModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultLicenseApplyService;
import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.facades.data.zqeemah.BusinessActivity;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderAttachment;
import com.sap.ibso.eservices.facades.data.zqeemah2.Attachment;
import com.sap.ibso.eservices.facades.data.zqeemah2.ServiceRequestCreation;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemah2Facade;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemahFacade;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.commerceservices.impersonation.ImpersonationContext;
import de.hybris.platform.core.model.user.CustomerModel;

import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStore;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

public class SagiaPublishLicencesJob extends AbstractJobPerformable<SAGIASiteCronJobModel> {

	private static final Logger LOG = LoggerFactory.getLogger(SagiaPublishLicencesJob.class);
   
	@Resource
	private DefaultLicenseApplyService defaultLicenseApplyService;
	
	@Resource(name = "sagiaZqeemahFacade")
	private SagiaZqeemahFacade sagiaZqeemahFacade;
	
	@Resource(name = "sagiaZqeemah2Facade")
    private SagiaZqeemah2Facade sagiaZqeemah2Facade;
	
	@Resource(name = "modelService")
    private ModelService modelService;
	
	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	
	private static String regularQeemahChannel = Config.getString("regular.qeemah.channel.constant.value", "R");
	
	
	/*

	•	Below are the calls which we will use for data submission for both license type
	sagiaZqeemah2Facade.saveDiffQeemah --> /ZQEEMAH2_EXT_SRV/DiffQeemahSet
	sagiaZqeemahFacade.uploadActivityAttachments --> /ZQEEMAH_SRV/ZBASIC_CONT_INFO_ENT
	sagiaZqeemahFacade.createShareholders --> /ZQEEMAH_SHRHLDR_SRV/ZSHAREHOLDER_INFO_ENT
	sagiaZqeemahFacade.createDelegates --> /ZQEEMAH_SHRHLDR_SRV/DelegateInfoSet
	sagiaZqeemahFacade.saveOrganizationInformation --> /ZQEEMAH_SRV/ZBASIC_ORG_INFO_ENT
	sagiaZqeemahFacade.saveContactPerson --> /ZQEEMAH_SRV/ZBASIC_CONT_INFO_ENT
	sagiaZqeemahFacade.saveBusinessActivities --> /ZQEEMAH_SRV/IsicDetPsSet
	
	•	If Qeemah1(Regular) license creation calls
	qeemahSubmitServiceQ1.saveQeemah  --> /ZQEEMAH_SRV/ZQEEMAH_SUBMIT_ENT
	submitServiceQ1.saveQeemah --> /ZQEEMAH_SRV/SUBMIT_ENT (Input: RefID, Guid)
	
	•	Qeemah2 license creation calls 
	sagiaZqeemah2Facade.saveServiceRequestCreation --> /ZQEEMAH2_EXT_SRV/ServiceReqCreationSet
	sagiaZqeemah2Facade.saveServiceRequestMD --> /ZQEEMAH2_EXT_SRV/ServReqMDSetSet
	
	*/
	
	
	
	
	@Override
	public PerformResult perform(SAGIASiteCronJobModel arg0) {
		
		//Setting base store to sessio
		
		baseSiteService.setCurrentBaseSite(arg0.getBaseSite() ,  true);
		LOG.info("Starting to publishing Licences to CRM...");
	
	
		List<SagiaLicenseModel>  sagiaLicenseList = defaultLicenseApplyService.getPendingLicenses();
		
		for (SagiaLicenseModel sagiaLicenseModel : sagiaLicenseList) {
			
			try {
			sagiaZqeemahFacade.createShareholdersInfoOData(sagiaLicenseModel.getShareHolders());
			sagiaZqeemahFacade.createDelegatesInfoOData(sagiaLicenseModel.getShareHolders());	       
	        sagiaZqeemahFacade.saveOrganizationInformationOData(sagiaLicenseModel.getEntityInformation());
	      //  sagiaZqeemahFacade.saveContactPersonOData(sagiaLicenseModel.getContactPerson());
	        
	       
		 //  businessActivities is missing from EntityInformation ---> Ankit to check
			if(sagiaLicenseModel.getEntityInformation() == null)
				continue;
			
		//	sagiaZqeemahFacade.saveBusinessActivitiesOData(sagiaLicenseModel.getEntityInformation().getIsicActivities(),sagiaLicenseModel.getEntityInformation().getLicenseType().getName(),"businessType", "temporaryTextValue", sagiaLicenseModel.getEntityInformation().getLicenseType().getCode(),sagiaLicenseModel.getCustomer()) ;  
	    	    

		   
	       String advanceLicenseNr = sagiaLicenseModel.getEntityInformation().getAdvanceLicenseNr();
		       
           boolean isEntrepreneur = sagiaLicenseModel.getEntityInformation().isIsEntrepreneur();
			    if(isEntrepreneur)
			        {
			     //       Collection<ShareholderAttachment> entrepreneurFiles = loadEntrepreneurFiles(map);
			    //        sagiaZqeemahFacade.uploadEntrepreneurFiles(entrepreneurFiles);
			        }
		        
			
			    
				setPublishStatus(sagiaLicenseModel,PublishStatus.SUCCESSFUL,"");
			}
			catch (Exception e) {
				
				LOG.error("License  "+sagiaLicenseModel.getCode()+" has not been published to CRM.",e);
				setPublishStatus(sagiaLicenseModel,PublishStatus.FAILED,e.getMessage());	            
			}
		}
		
		
		

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    
	}
	
	
	private ServiceRequestCreation createServiceRequestObj(String advanceLicenseNr) {
		ServiceRequestCreation serviceRequestCreationPost = new ServiceRequestCreation();
		//serviceRequestCreationPost.setAdvlicno(advanceLicenseNr);
		Collection<com.sap.ibso.eservices.facades.data.zqeemah2.Attachment> attachments = new ArrayList<>();
		Attachment attachment = new Attachment();
		attachments.add(attachment);
	//	serviceRequestCreationPost.setAttachments(attachments);
		return serviceRequestCreationPost;
	}
	
	private void setApplicationServiceRequestID(String serviceRequestID,CustomerModel customer){       
		    customer.setApplicationServiceRequestID(serviceRequestID);
		    modelService.save(customer);
	 }
	 
	 private void setPublishStatus(SagiaLicenseModel sagiaLicenseModel,PublishStatus publishStatus,String message){       
	//		sagiaLicenseModel.setPublishStatus(publishStatus);
		//	sagiaLicenseModel.setPublishDate(new Date());
	//		sagiaLicenseModel.setPublishMessage(message);
			modelService.save(sagiaLicenseModel);
	 }
	 
	 
	 
	 private boolean isRegularLicense(SagiaLicenseModel sagiaLicenseModel) {
		 
		 boolean isAllSame = true;
		 
		 List<IsicMasterModel> listIsicMaster = sagiaLicenseModel.getEntityInformation().getIsicActivities();
		 String firstVal = listIsicMaster.get(0).getQeemahChannel();
		 for (IsicMasterModel isicMasterModel : listIsicMaster) {
			if(!isicMasterModel.getQeemahChannel().equals(firstVal)) {
				isAllSame = false;
			}
		}
		if (isAllSame) {
			if(regularQeemahChannel.equals(firstVal))  return true;
			else return false;
		}else 
		return true ;
	 }
	 
	
		

}
