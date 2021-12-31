/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.core.sagia.services;

import java.util.Date;
import java.util.List;

import com.sap.ibso.eservices.core.jalo.SagiaJsonUtility;
import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonUtilityModel;
import com.sap.ibso.eservices.sagiaservices.data.DraftData;
import com.sap.ibso.eservices.sagiaservices.data.DraftJsonData;

/**
 * Provides access to the Draft Service
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaDraftService
{
	/**
	 * saves DraftData
	 * @param draftData the draftData
	 * @param serviceId the serviceId
	 */
	void save(DraftData draftData, String serviceId);

	/**
	 * retrieves SagiaDraftModel
	 * @param formId formId
	 * @return SagiaDraftModel
	 */
	SagiaDraftModel getDraft(String formId);

	/**
	 * retrieves a List of SagiaDraftModel
	 * @return List of SagiaDraftModel
	 */
	List<SagiaDraftModel> getDrafts();

	/**
	 * Returns list of drafts from all users that older than date
	 * @param date the date for retrieving drafts
	 * @return list of drafts model
	 */
	List<SagiaDraftModel> getDrafts(Date date);

	/**
	 * checks if DraftExists
	 * @param formId formId
	 * @return boolean
	 */
	boolean isDraftExists (String formId);

	/**
	 * Remove Draft From Database
	 * @param formId the formId
	 */
	void removeDraft(String formId);

	/**
	 * Save Json Draft.
	 * @param draftJsonData the draftJsonData
	 */
	void saveJsonDraft(DraftJsonData draftJsonData);

	/**
	 * Save Utility Draft.
	 * @param draftJsonData the draftJsonData
	 */
	void saveUtilityDraft(DraftJsonData draftJsonData);

	/**
	 * Retrieves Json Draft Model.
	 * @param serviceId Service Id
	 * @return SagiaJsonDraftModel
	 */
	SagiaJsonDraftModel getJsonDraft(String serviceId);

	/**
	 * Retrieves Json Utility Model.
	 * @param serviceId Service Id
	 * @return SagiaJsonUtilityModel
	 */
	SagiaJsonUtilityModel getJsonUtilityDraft(String serviceId);

	/**
	 * retrieves JsonDrafts
	 * @return List of SagiaJsonDraftModel
	 */
	List<SagiaJsonDraftModel> getJsonDrafts();

	/**
	 * retrieves JsonDrafts
	 * @param date date
	 * @return List of SagiaJsonDraftModel
	 */
	List<SagiaJsonDraftModel> getJsonDrafts(Date date);

	/**
	 * checks if isJsonDraftExists
	 * @param serviceId serviceId
	 * @return boolean
	 */
	boolean isJsonDraftExists(String serviceId);

	/**
	 * removes JsonDraft
	 * @param serviceId serviceId
	 */
	void removeJsonDraft(String serviceId);
}
