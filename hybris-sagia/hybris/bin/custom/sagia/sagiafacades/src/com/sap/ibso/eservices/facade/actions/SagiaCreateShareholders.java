package com.sap.ibso.eservices.facade.actions;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.core.model.DelegateInfoModel;
import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.ExistingShareholderModel;
import com.sap.ibso.eservices.core.model.OrganizationShareholderModel;
import com.sap.ibso.eservices.core.model.PersonShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.facades.sagia.SagiaODataFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemah2Facade;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemahFacade;
import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderData;

import de.hybris.platform.commerceservices.model.process.SagiaPublishLicenseProcessProcessModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.task.RetryLaterException;

public class SagiaCreateShareholders extends AbstractAction<SagiaPublishLicenseProcessProcessModel> {

	
	private static final Logger LOG = LoggerFactory.getLogger(SagiaCreateShareholders.class);
	   
	
	private final String RETURN_SUCCESS =  "XXXX Information updated successfully";
	
	@Resource(name = "sagiaODataFacade")
    private SagiaODataFacade sagiaODataFacade;
	
    private static final String PERSON_SHAREHOLDER = "Person";
    private static final String ORGANIZATION_SHAREHOLDER = "Organization";
    private static final String EXISTING_SHAREHOLDER = "Existing";
	

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	
	@Override
	public Set<String> getTransitions() {
		return AbstractAction.createTransitions("OK", "NOK");
	}

	@Override
	public String execute(SagiaPublishLicenseProcessProcessModel businessProcess) throws RetryLaterException, Exception {
		
		try {
				baseSiteService.setCurrentBaseSite(businessProcess.getSite() ,  true);	
				
				SagiaLicenseModel sagiaLicense = businessProcess.getSagiaLicense();
				if(sagiaLicense != null)
				{
					List<ShareHolderModel> shareHolders = sagiaLicense.getShareHolders();
					for(ShareHolderModel shareHolderModel: shareHolders) {
						ShareholderData shareholderData = sagiaODataFacade.saveShareHolderOData(shareHolderModel);				
					}
					uploadEntityAttachments(sagiaLicense);
					for(ShareHolderModel shareHolderModel: shareHolders) {
						uploadAttachments(shareHolderModel);
					}
					return "OK";
				}
				else
				{
					return("NOK");
				}
			
			}
			catch (Exception e) {
				LOG.error("License Share Holder  "+businessProcess.getSagiaLicense().getCode()+" has not been published to CRM.",e);
				return("NOK");
			}
	}
	
	private void uploadEntityAttachments(SagiaLicenseModel sagiaLicense) {
		String refId = sagiaLicense.getApplicantReferenceID();
		String guid = sagiaLicense.getGuid();
		String objectid = sagiaLicense.getApplicationServiceRequestID();
		EntityInformationModel entityInformation = sagiaLicense.getEntityInformation();
		
		MediaModel boardResolutionFile = entityInformation.getBoardResolutionFile();
		sagiaODataFacade.uploadAttachmentOData(boardResolutionFile, "E",refId,guid,"ENBO",objectid,null);
		
		MediaModel letterOfSupportFile = entityInformation.getLetterOfSupportFile();
		sagiaODataFacade.uploadAttachmentOData(letterOfSupportFile, "E",refId,guid,"ENLA",objectid,null);
		
		MediaModel financialStatementFile = entityInformation.getFinancialStatementFile();
		sagiaODataFacade.uploadAttachmentOData(financialStatementFile, "E",refId,guid,"ENFS",objectid,null);
		
		MediaModel iqamaFile = entityInformation.getIqamaFile();
		sagiaODataFacade.uploadAttachmentOData(iqamaFile, "E",refId,guid,"ENIQ",objectid,null);
		
		MediaModel crCertificateFile = entityInformation.getCrCertificateFile();
		sagiaODataFacade.uploadAttachmentOData(crCertificateFile, "E",refId,guid,"ENCR",objectid,null);
		
		MediaModel gosiCertificateFile = entityInformation.getGosiCertificateFile();
		sagiaODataFacade.uploadAttachmentOData(gosiCertificateFile, "E",refId,guid,"ENGC",objectid,null);
		
		MediaModel noObjectionCertificateFile = entityInformation.getNoObjectionCertificateFile();
		sagiaODataFacade.uploadAttachmentOData(noObjectionCertificateFile, "E",refId,guid,"ENNC",objectid,null);
		
/*
		MediaModel mainBranchCRFile = entityInformation.getMainBranchCR();
		sagiaODataFacade.uploadAttachmentOData(mainBranchCRFile, "E",refId,guid,"ENMBCR",objectid,null);
		MediaModel otherBranchCR1File = entityInformation.getOtherBranchCR1();
		sagiaODataFacade.uploadAttachmentOData(otherBranchCR1File, "E",refId,guid,"ENOBCR1",objectid,null);
		MediaModel otherBranchCR2File = entityInformation.getOtherBranchCR2();
		sagiaODataFacade.uploadAttachmentOData(otherBranchCR2File, "E",refId,guid,"ENOBCR2",objectid,null);
		
		MediaModel entityListedInStockMarketFile = entityInformation.getRhqStockMarketAttachment();
		sagiaODataFacade.uploadAttachmentOData(entityListedInStockMarketFile, "E",refId,guid,"ENLSM",objectid,null);
		
		MediaModel entityAssetFile = entityInformation.getRhqEntityAssetAttachment();
		sagiaODataFacade.uploadAttachmentOData(entityAssetFile, "E",refId,guid,"ENAS",objectid,null);
		
		MediaModel entityRevenueFile = entityInformation.getRhqEntityRevenueAttachment();
		sagiaODataFacade.uploadAttachmentOData(entityRevenueFile, "E",refId,guid,"ENRV",objectid,null);
		
		MediaModel branchCR1File = entityInformation.getRhqCR1();
		sagiaODataFacade.uploadAttachmentOData(branchCR1File, "E",refId,guid,"ENBCR1",objectid,null);
		MediaModel branchCR2File = entityInformation.getRhqCR2();
		sagiaODataFacade.uploadAttachmentOData(branchCR2File, "E",refId,guid,"ENBCR2",objectid,null);
		MediaModel branchCR3File = entityInformation.getRhqCR3();
		sagiaODataFacade.uploadAttachmentOData(branchCR3File, "E",refId,guid,"ENBCR3",objectid,null);
		MediaModel branchCR4File = entityInformation.getRhqCR4();
		sagiaODataFacade.uploadAttachmentOData(branchCR4File, "E",refId,guid,"ENBCR4",objectid,null);
*/

		//NEW RHQ Requirements
		MediaModel entityFinancialStatementFile = entityInformation.getEntityFinancialStatementFile();
		sagiaODataFacade.uploadAttachmentOData(entityFinancialStatementFile, "E",refId,guid,"ENRHQFS",objectid,null);
		MediaModel commercialRegMainEntryFile = entityInformation.getCommercialRegMainEntryFile();
		sagiaODataFacade.uploadAttachmentOData(commercialRegMainEntryFile, "E",refId,guid,"ENCRME",objectid,null);
		MediaModel commercialRegBranch1File = entityInformation.getCommercialRegBranch1File();
		sagiaODataFacade.uploadAttachmentOData(commercialRegBranch1File, "E",refId,guid,"ENCRBR1",objectid,null);
		MediaModel commercialRegBranch2File = entityInformation.getCommercialRegBranch2File();
		sagiaODataFacade.uploadAttachmentOData(commercialRegBranch2File, "E",refId,guid,"ENCRBR2",objectid,null);
	}

	private void uploadAttachments(ShareHolderModel shareHolderModel) {
		
		String refId = shareHolderModel.getLicense().getApplicantReferenceID();
		String guid = shareHolderModel.getLicense().getGuid();
		String objectid = shareHolderModel.getLicense().getApplicationServiceRequestID();
		String shType = shareHolderModel.getShareHolderType();
		DelegateInfoModel delegateInfo = shareHolderModel.getDelegateInfo();
		if(!EXISTING_SHAREHOLDER.equalsIgnoreCase(shType) && delegateInfo != null) {
			MediaModel authorizationLetterFile = shareHolderModel.getDelegateInfo().getAuthorisationLetter();
			sagiaODataFacade.uploadAttachmentOData(authorizationLetterFile, "X",refId,guid,"AUTH",objectid,shareHolderModel.getCode());
		
			MediaModel idCopyFile = shareHolderModel.getDelegateInfo().getSaudiIdCopy();
			sagiaODataFacade.uploadAttachmentOData(idCopyFile, "X",refId,guid,"ID",objectid,shareHolderModel.getCode());
		}
		
		if (PERSON_SHAREHOLDER.equalsIgnoreCase(shType)) {
		
			PersonShareholderModel personSH = ((PersonShareholderModel) shareHolderModel);
			MediaModel passportFile = personSH.getPassportIdCopy();
			sagiaODataFacade.uploadAttachmentOData(passportFile, "",refId,guid,"PASS",objectid,shareHolderModel.getCode());
			
			MediaModel otherFile = personSH.getOther();
			sagiaODataFacade.uploadAttachmentOData(otherFile, "",refId,guid,"OTHR",objectid,shareHolderModel.getCode());
			
			MediaModel perProfessionalLicenseCertificate = personSH.getProfessionalLicenseCertificate();
			sagiaODataFacade.uploadAttachmentOData(perProfessionalLicenseCertificate, "",refId,guid,"PPLC",objectid,shareHolderModel.getCode());
			
			
		} else if (ORGANIZATION_SHAREHOLDER.equalsIgnoreCase(shType)) {
			
			OrganizationShareholderModel orgSH = ((OrganizationShareholderModel) shareHolderModel);
			MediaModel companyRegistrationFile = orgSH.getCommercialRegCopy();
			sagiaODataFacade.uploadAttachmentOData(companyRegistrationFile, "",refId,guid,"COMM",objectid,shareHolderModel.getCode());
						
			MediaModel companyFinancialStatementFile = orgSH.getLastYearFinStatement();
			sagiaODataFacade.uploadAttachmentOData(companyFinancialStatementFile, "",refId,guid,"BAL",objectid,shareHolderModel.getCode());
			
			MediaModel companyMemoAssociation = orgSH.getCompanyMemoAssociation();
			sagiaODataFacade.uploadAttachmentOData(companyMemoAssociation, "",refId,guid,"CMOA",objectid,shareHolderModel.getCode());
			
			MediaModel orgProfessionalLicenseCertificate = orgSH.getProfessionalLicenseCertificate();
			sagiaODataFacade.uploadAttachmentOData(orgProfessionalLicenseCertificate, "",refId,guid,"OPLC",objectid,shareHolderModel.getCode());
			
		} else if (EXISTING_SHAREHOLDER.equalsIgnoreCase(shType)) {
			
			ExistingShareholderModel exiSH = ((ExistingShareholderModel) shareHolderModel);
			MediaModel commercialRegistrationFile = exiSH.getCommercialRegistration();
			sagiaODataFacade.uploadAttachmentOData(commercialRegistrationFile, "",refId,guid,"E3",objectid,shareHolderModel.getCode());

			MediaModel lastBudgetFile = exiSH.getLastBudget();
			sagiaODataFacade.uploadAttachmentOData(lastBudgetFile, "",refId,guid,"E5",objectid,shareHolderModel.getCode());
			
			MediaModel extProfessionalLicenseCertificate = exiSH.getProfessionalLicenseCertificate();
			sagiaODataFacade.uploadAttachmentOData(extProfessionalLicenseCertificate, "",refId,guid,"EPLC",objectid,shareHolderModel.getCode());
		}
		
	}

}
