package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class SupportVisitDataPopulator extends ODataPopulator<SupportVisitData> {

    @Override
    public void populate(ODataModel model, SupportVisitData supportVisitData) {
        super.populate(model, supportVisitData);
        populateComments(model, supportVisitData);
        populateAttachments(model, supportVisitData);
    }

    /**
     * @param model
     * @param supportVisitData
     */
    private void populateAttachments(ODataModel model, SupportVisitData supportVisitData) {
        if (null != model.get("SupportVstToAttachNav")) {
            List<ODataEntry> attachments = ((ODataDeltaFeedImpl) model.get("SupportVstToAttachNav")).getEntries();
            Set<DocAttachSetData> attachmentsSet = new HashSet<>();
            for (ODataEntry attachment : attachments) {
                DocAttachSetData doc = getDocAttachSetData(attachment);
                attachmentsSet.add(doc);
            }
            supportVisitData.setSupportVstToAttachNav(attachmentsSet);
        }
    }

    /**
     * @param attachment
     * @return
     */
    private DocAttachSetData getDocAttachSetData(ODataEntry attachment) {
        DocAttachSetData doc = new DocAttachSetData();
        if (null != attachment.getProperties().get("FileContent")) {
            doc.setFileContent(attachment.getProperties().get("FileContent").toString());
        }
        if (null != attachment.getProperties().get("FileGuid")) {
            doc.setFileGuid(attachment.getProperties().get("FileGuid").toString());
        }
        if (null != attachment.getProperties().get("Filename")) {
            doc.setFilename(attachment.getProperties().get("Filename").toString());
        }
        if (null != attachment.getProperties().get("Mimetype")) {
            doc.setMimetype(attachment.getProperties().get("Mimetype").toString());
        }
        if (null != attachment.getProperties().get("ObjectId")) {
            doc.setObjectId(attachment.getProperties().get("ObjectId").toString());
        }
        if (null != attachment.getProperties().get("ObjectType")) {
            doc.setObjectType(attachment.getProperties().get("ObjectType").toString());
        }
        return doc;
    }

    /**
     * @param model
     * @param supportVisitData
     */
    private void populateComments(ODataModel model, SupportVisitData supportVisitData) {
        if (null != model.get("SuppVisitToText")) {
            List<ODataEntry> comments = ((ODataDeltaFeedImpl) model.get("SuppVisitToText")).getEntries();
            Set<GetTextData> commentsSet = new HashSet<>();
            for (ODataEntry comment : comments) {
                GetTextData textData = new GetTextData();
                textData.setSrId(comment.getProperties().get("SrId").toString());
                textData.setSrGuid(comment.getProperties().get("SrGuid").toString());
                textData.setTdid(comment.getProperties().get("Tdid").toString());
                textData.setTdline(comment.getProperties().get("Tdline").toString());
                textData.setCommentsBy(comment.getProperties().get("CommentsBy").toString());

                if (null != comment.getProperties().get("CommentDate")) {
                    textData.setCommentDate(((GregorianCalendar) comment.getProperties().get("CommentDate")).toZonedDateTime().toLocalDateTime());
                }
                if (null != comment.getProperties().get("CommentTime")) {
                    textData.setCommentTime(((GregorianCalendar) comment.getProperties().get("CommentTime")).toZonedDateTime().toLocalTime().toString());
                }
                textData.setTimezone(comment.getProperties().get("Timezone").toString());
                textData.setStage(comment.getProperties().get("Stage").toString());
                commentsSet.add(textData);
            }
            supportVisitData.setSuppVisitToText(commentsSet);
        }
    }
}

