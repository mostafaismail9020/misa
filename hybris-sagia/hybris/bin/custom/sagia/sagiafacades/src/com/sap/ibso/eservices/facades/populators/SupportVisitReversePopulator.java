package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.SupportVisit;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SupportVisitReversePopulator implements Populator<SupportVisit, SupportVisitData> {

    private DocumentAttachmentReversePopulator documentAttachmentReversePopulator;

    @Override
    public void populate(SupportVisit supportVisit, SupportVisitData supportVisitData) throws ConversionException {
        supportVisitData.setSrId(supportVisit.getSrId());
        supportVisitData.setBpId(supportVisit.getBpId());
        supportVisitData.setTextMsg(supportVisit.getTextMsg());

        supportVisitData.setTdid(supportVisit.getTdid());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy HH:mm");
        supportVisitData.setVisitDate(LocalDateTime.parse(supportVisit.getDateString() + " 00:00", formatter));

        if(CollectionUtils.isNotEmpty(supportVisit.getSupportVstToAttachNav())){
            Set<DocAttachSetData> docAttach = new HashSet<>();
            supportVisit.getSupportVstToAttachNav().forEach(document -> {
                DocAttachSetData documentData = new DocAttachSetData();
                documentAttachmentReversePopulator.populate(document, documentData);
                docAttach.add(documentData);
            });
            supportVisitData.setSupportVstToAttachNav(docAttach);
        }
    }

    /**
     * @param documentAttachmentReversePopulator
     */
    public void setDocumentAttachmentReversePopulator(DocumentAttachmentReversePopulator documentAttachmentReversePopulator) {
        this.documentAttachmentReversePopulator = documentAttachmentReversePopulator;
    }
}
