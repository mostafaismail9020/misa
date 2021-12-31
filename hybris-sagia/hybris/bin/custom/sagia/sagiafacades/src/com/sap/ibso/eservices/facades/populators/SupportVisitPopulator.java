package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.DocumentAttachment;
import com.sap.ibso.eservices.facades.data.SupportVisit;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.GetText;
import com.sap.ibso.eservices.facades.populators.zesrvEnhOData.GetTextPopulator;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashSet;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SupportVisitPopulator implements Populator<SupportVisitData, SupportVisit> {

    private SagiaFormatProvider sagiaFormatProvider;
    private GetTextPopulator getTextPopulator;
    private DocumentAttachmentPopulator documentAttachmentPopulator;

    @Override
    public void populate(SupportVisitData source, SupportVisit target) throws ConversionException {
        target.setSrId(source.getSrId());
        target.setBpId(source.getBpId());
        target.setTextMsg(source.getTextMsg());
        target.setVisitDate(sagiaFormatProvider.getLocalizedDateData(source.getVisitDate()));
        target.setTdid(source.getTdid());
        target.setStatus(source.getStatus());
        target.setStatDesc(source.getStatDesc());

        Set<GetTextData> getTextDataSet = source.getSuppVisitToText();
        if(CollectionUtils.isNotEmpty(getTextDataSet)){
            Set<GetText> texts = new HashSet<>();
            for (GetTextData text: getTextDataSet) {
                GetText getText = new GetText();
                getTextPopulator.populate(text, getText);
                texts.add(getText);
            }
            target.setSuppVisitToText(texts);
        }

        Set<DocAttachSetData> documentAttachments = source.getSupportVstToAttachNav();
        if(CollectionUtils.isNotEmpty(documentAttachments)){
            Set<DocumentAttachment> docAttach = new HashSet<>();
            for(DocAttachSetData doc : documentAttachments){
                DocumentAttachment documentAttachment = new DocumentAttachment();
                documentAttachmentPopulator.populate(doc,documentAttachment);
                docAttach.add(documentAttachment);
            }
            target.setSupportVstToAttachNav(docAttach);
        }

    }

    /**
     * @param sagiaFormatProvider
     */
    @Required
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    /**
     * @return
     */
    public GetTextPopulator getGetTextPopulator() {
        return getTextPopulator;
    }

    /**
     * @param getTextPopulator
     */
    public void setGetTextPopulator(GetTextPopulator getTextPopulator) {
        this.getTextPopulator = getTextPopulator;
    }

    /**
     * @param documentAttachmentPopulator
     */
    public void setDocumentAttachmentPopulator(DocumentAttachmentPopulator documentAttachmentPopulator) {
        this.documentAttachmentPopulator = documentAttachmentPopulator;
    }
}