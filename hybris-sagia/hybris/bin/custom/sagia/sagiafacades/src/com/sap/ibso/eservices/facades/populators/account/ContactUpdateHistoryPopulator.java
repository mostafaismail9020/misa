package com.sap.ibso.eservices.facades.populators.account;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.ContentHDRDocument;
import com.sap.ibso.eservices.facades.data.ProfileCompanyRepresentativeData;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateHistory;
import com.sap.ibso.eservices.facades.data.license.replace.ContactUpdateMessages;
import com.sap.ibso.eservices.facades.populators.ContactUpdateCommentsPopulator;
import com.sap.ibso.eservices.facades.populators.ServicesRequestDocumentPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContactUpdateMessagesData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceBpHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SrvBPHdrContactData;
import de.hybris.platform.converters.Populator;
import org.apache.commons.collections4.CollectionUtils;
import org.fest.util.Collections;

import javax.annotation.Resource;
import java.util.*;

public class ContactUpdateHistoryPopulator implements Populator<ServiceBpHDRData,ContactUpdateHistory> {

    @Resource
    private SagiaFormatProvider sagiaFormatProvider;

    @Resource
    private CompanyRepresentativePopulator companyRepresentativePopulator;

    @Resource
    private ServicesRequestDocumentPopulator servicesRequestDocumentPopulator;

    @Resource
    private ContactUpdateCommentsPopulator contactUpdateCommentsPopulator;

    @Override
    public void populate(ServiceBpHDRData serviceBpHDRData, ContactUpdateHistory contactUpdateHistory) {
        contactUpdateHistory.setBpId(serviceBpHDRData.getBpID());
        contactUpdateHistory.setSrId(serviceBpHDRData.getSrID());
        contactUpdateHistory.setDateOfUpdate(sagiaFormatProvider.getLocalizedDateData(serviceBpHDRData.getSrCrDate()));
        contactUpdateHistory.setChangeId(serviceBpHDRData.getBpChangeID());
        contactUpdateHistory.setStatus(serviceBpHDRData.getSrStCode());
        contactUpdateHistory.setStatusDescription(serviceBpHDRData.getSrStDesc());
        contactUpdateHistory.setCompanyRepresentativesChanges(0);

        StringTokenizer st = new StringTokenizer(serviceBpHDRData.getBpChangeID(),",");

        while(st.hasMoreTokens()){
            String s = st.nextToken();
            if(s.contains("GM")){
                contactUpdateHistory.setHasGeneralManagerChange(true);
            }
            else if(s.contains("R")){
                contactUpdateHistory.setCompanyRepresentativesChanges(contactUpdateHistory.getCompanyRepresentativesChanges()+1);
            }
        }

        if(!Collections.isEmpty(serviceBpHDRData.getSrvBPHdrToBPContactDataSet())){
            List updatedContacts = new ArrayList();

            for(SrvBPHdrContactData srvBPHdrContactData : serviceBpHDRData.getSrvBPHdrToBPContactDataSet()){
                ProfileCompanyRepresentativeData companyRepresentativeData = new ProfileCompanyRepresentativeData();
                companyRepresentativePopulator.populate(srvBPHdrContactData,companyRepresentativeData);
                updatedContacts.add(companyRepresentativeData);
            }
            contactUpdateHistory.setUpdatedContacts(updatedContacts);
        }

        if(!Collections.isEmpty(serviceBpHDRData.getSrvBPHdrToAttachNav())){
            List<ContentHDRDocument> documents = new ArrayList();

            for(ContentHDRData contentHDRData : serviceBpHDRData.getSrvBPHdrToAttachNav()){
                ContentHDRDocument document = new ContentHDRDocument();
                servicesRequestDocumentPopulator.populate(contentHDRData,document);
                documents.add(document);
            }
            contactUpdateHistory.setAttachedDocuments(documents);
        }

        if(CollectionUtils.isNotEmpty(serviceBpHDRData.getSrvBPHdrToTextNav())){
            Set<ContactUpdateMessages> contactUpdateMessagesSet = new HashSet<>();
            for(ContactUpdateMessagesData contactUpdateMessagesData : serviceBpHDRData.getSrvBPHdrToTextNav()){
                ContactUpdateMessages messagesData = new ContactUpdateMessages();
                contactUpdateCommentsPopulator.populate(contactUpdateMessagesData, messagesData);
                contactUpdateMessagesSet.add(messagesData);
            }
            contactUpdateHistory.setComments(contactUpdateMessagesSet);
        }
    }

    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    public CompanyRepresentativePopulator getCompanyRepresentativePopulator() {
        return companyRepresentativePopulator;
    }

    public void setCompanyRepresentativePopulator(CompanyRepresentativePopulator companyRepresentativePopulator) {
        this.companyRepresentativePopulator = companyRepresentativePopulator;
    }

    public void setServicesRequestDocumentPopulator(ServicesRequestDocumentPopulator servicesRequestDocumentPopulator) {
        this.servicesRequestDocumentPopulator = servicesRequestDocumentPopulator;
    }
}
