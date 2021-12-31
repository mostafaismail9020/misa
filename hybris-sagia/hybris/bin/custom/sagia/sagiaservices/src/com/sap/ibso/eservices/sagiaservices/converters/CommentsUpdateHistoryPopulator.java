package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContactUpdateMessagesData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

/**
 * @author Razvan Badea <razvan.badea@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.converters
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class CommentsUpdateHistoryPopulator extends ODataPopulator<ContactUpdateMessagesData> {

    @Resource
    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(ODataModel model, ContactUpdateMessagesData contactUpdateMessagesData) {
        super.populate(model, contactUpdateMessagesData);
        if (model.get("CommentDate") != null) {
            LocalDateTime commentDate = ((GregorianCalendar) model.get("CommentDate")).toZonedDateTime().toLocalDateTime();
            if (commentDate != null) {
                contactUpdateMessagesData.setCommentDate(sagiaFormatProvider.getLocalizedDateTimeData(commentDate));
            }
        }
    }


}
