package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.sagia.SagiaFinancialStatementFacade;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.AttachmantHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.FinancialStatementHDR;
import com.sap.ibso.eservices.facades.populators.zesrvEnhOData.AttachmantHDRPopulator;
import com.sap.ibso.eservices.facades.populators.zesrvEnhOData.FinancialStatementHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.AttachmantHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.FinancialStatementHDRData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.DocumentAttachmentService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.AttachmentHDRService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.FinancialStatementHDRService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultSagiaFinancialStatementFacade implements SagiaFinancialStatementFacade {
	
    private static final Logger LOG = Logger.getLogger(DefaultSagiaFinancialStatementFacade.class);
    
    private FinancialStatementHDRService financialStatementHDRService;
    //private AttachmentHDRService attachmentHDRService;
    private FinancialStatementHDRPopulator financialStatementHDRPopulator;
    //private AttachmantHDRPopulator attachmantHDRPopulator;
    private DocumentAttachmentService documentAttachmentService;
    
    @Autowired
    private I18NService i18NService;


    /**
     * @return
     */
    @Override
    public List<FinancialStatementHDR> getFinancialStatementHDR() {

            List<FinancialStatementHDR> financialStatementHDRs= new ArrayList<>();
            Collection<FinancialStatementHDRData> financialStatementHDRsData = financialStatementHDRService.getFinancialStatementList();
            if (financialStatementHDRsData != null && !financialStatementHDRsData.isEmpty()) {
                for (FinancialStatementHDRData financialStatementHDRData : financialStatementHDRsData) {
                	FinancialStatementHDR financialStatementHDR = new FinancialStatementHDR();
                    financialStatementHDRPopulator.populate(financialStatementHDRData, financialStatementHDR);
                    financialStatementHDRs.add(financialStatementHDR);
                }
            }
            return financialStatementHDRs;
        }
    

    @Override
    public FinancialStatementHDR getFinancialStatementHDR(final String srId)  {
    	FinancialStatementHDR financialStatementHDR = new FinancialStatementHDR();
    	FinancialStatementHDRData financialStatementHDRData = financialStatementHDRService.getFinancialStatementBy(srId);
    	financialStatementHDRPopulator.populate(financialStatementHDRData, financialStatementHDR);
            return financialStatementHDR;
        }

//    @Override
//    public Collection<AttachmantHDR> getFinancialStatementSupportingAttachments() {
//        List<AttachmantHDR> attachments = new ArrayList<>();
//        Collection<AttachmantHDRData> attachmentsData = attachmentHDRService.getLegalConsultationAttachment();
//        for (AttachmantHDRData attachmentHDRData :
//                attachmentsData) {
//            AttachmantHDR attachmentHDR = new AttachmantHDR();
//            attachmantHDRPopulator.populate(attachmentHDRData, attachmentHDR);
//            attachments.add(attachmentHDR);
//        }
//        return attachments;
//
//    }

    @Override
    public InputStream readAttachment(String objectId, String documentId) {
        return documentAttachmentService.readAttachmentBy(objectId, documentId);
    }


	public FinancialStatementHDRService getFinancialStatementHDRService() {
		return financialStatementHDRService;
	}


	public void setFinancialStatementHDRService(FinancialStatementHDRService financialStatementHDRService) {
		this.financialStatementHDRService = financialStatementHDRService;
	}


//	public AttachmentHDRService getAttachmentHDRService() {
//		return attachmentHDRService;
//	}
//
//
//	public void setAttachmentHDRService(AttachmentHDRService attachmentHDRService) {
//		this.attachmentHDRService = attachmentHDRService;
//	}


	public FinancialStatementHDRPopulator getFinancialStatementHDRPopulator() {
		return financialStatementHDRPopulator;
	}


	public void setFinancialStatementHDRPopulator(FinancialStatementHDRPopulator financialStatementHDRPopulator) {
		this.financialStatementHDRPopulator = financialStatementHDRPopulator;
	}


//	public AttachmantHDRPopulator getAttachmantHDRPopulator() {
//		return attachmantHDRPopulator;
//	}
//
//
//	public void setAttachmantHDRPopulator(AttachmantHDRPopulator attachmantHDRPopulator) {
//		this.attachmantHDRPopulator = attachmantHDRPopulator;
//	}


	public DocumentAttachmentService getDocumentAttachmentService() {
		return documentAttachmentService;
	}


	public void setDocumentAttachmentService(DocumentAttachmentService documentAttachmentService) {
		this.documentAttachmentService = documentAttachmentService;
	}

}
