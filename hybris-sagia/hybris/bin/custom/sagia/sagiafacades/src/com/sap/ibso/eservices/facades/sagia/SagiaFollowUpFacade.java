/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.facades.data.WarningLettersData;
import com.sap.ibso.eservices.facades.data.specialservices.FollowUpData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMCreateException;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateViolationReplyFormData;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateWarningLetterExtensionFormData;

import java.util.List;

/**
 * Provides access to SagiaFollowUpFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaFollowUpFacade {
    /**
     * retrieves ViolationReplyEntries
     * @return List of FollowUpData
     */
    List<FollowUpData> getViolationReplyEntries();

    /**
     * retrieves WarningLetterEntries
     * @return List of FollowUpData
     */
    List<FollowUpData> getWarningLetterEntries();

    /**
     * retrieves FollowUpEntry
     * @param id id
     * @return FollowUpData
     */
    FollowUpData getFollowUpEntry(Object id);

    /**
     * retrieves DateData
     * @param startDate startDate
     * @param days days
     * @return SagiaDateData
     */
    SagiaDateData getDateData(String startDate, Integer days);

    /**
     * retrieves WarningLettersExtensionData
     * @return List of WarningLettersData
     */
    List<WarningLettersData> getWarningLettersExtensionData();

    /**
     * retrieves WarningLettersViolationRepliesData
     * @return List of WarningLettersData
     */
    List<WarningLettersData> getWarningLettersViolationRepliesData();

    /**
     * creates ViolationReply
     * @param createViolationReplyFormData createViolationReplyFormData
     * @throws SagiaCRMCreateException exception
     */
    void createViolationReply(CreateViolationReplyFormData createViolationReplyFormData) throws SagiaCRMCreateException;

    /**
     * creates WarningLetterExtension
     * @param createWarningLetterExtensionFormData createWarningLetterExtensionFormData
     * @throws SagiaCRMCreateException exception
     */
    void createWarningLetterExtension(CreateWarningLetterExtensionFormData createWarningLetterExtensionFormData) throws SagiaCRMCreateException;

    /**
     * checks CreateViolationReply
     */
    void checkCreateViolationReply();

    /**
     * checks CreateWarningLetter
     */
    void checkCreateWarningLetter();

    /**
     *
     * retrieves WarningLetterData
     * @param letterNumber the letterNumber
     * @return WarningLettersData
     */
    WarningLettersData getWarningLetterData(String letterNumber);
}
