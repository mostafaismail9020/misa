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
package com.sap.ibso.eservices.facades.sagia;

import java.util.List;

import com.sap.ibso.eservices.facades.data.draft.DraftInfo;
import com.sap.ibso.eservices.sagiaservices.data.DraftData;
import com.sap.ibso.eservices.sagiaservices.data.DraftJsonData;

/**
 * Provides access to SagiaDraftFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaDraftFacade
{
	/**
	 * saves DraftData
	 * @param draftData draftData
	 * @param serviceId serviceId
	 */
	void save(DraftData draftData, String serviceId);

	/**
	 * Saves Json Draft Data.
	 * @param draftJsonData draftJsonData
	 */
	void saveJson(DraftJsonData draftJsonData);


	/**
	 * Saves Json Draft Data, this saving for utility purposes
	 * @param draftJsonData draftJsonData
	 */
	void saveUtilityDraft(DraftJsonData draftJsonData);

	/**
	 * retrieves Draft
	 * @param formId formId
	 * @return DraftInfo
	 */
	DraftInfo getDraft(String formId);

	/**
	 * retrieves Drafts both from Draft and Json Draft
	 * @return List of DraftInfo
	 */
	List<DraftInfo> getDrafts();

	/**
	 * checks if DraftExists
	 * @param formId formId
	 * @return boolean
	 */
	boolean isDraftExists (String formId);

	/**
	 *
	 * Remove Draft model.
	 * @param formId the formId
	 */
	void removeDraft(String formId);

	/**
	 * Get Draft Json String.
	 * @param serviceId Service Id
	 * @return DraftJsonInfo with json and an info of draft
	 */
	DraftInfo getDraftJson(String serviceId);

	/**
	 * Get Draft Json String.
	 * @param serviceId Service Id
	 * @return DraftJsonInfo with json and an info of draft
	 */
	DraftInfo getJsonUtilityDraft(String serviceId);

	/**
	 * Is JsonDraftExists.
	 * @param serviceId the serviceId
	 * @return true or false that indicates draft exists or not
	 */
	boolean isJsonDraftExists(String serviceId);

	/**
	 * Remove Json Draft
	 * @param serviceId the serviceId
	 */
	void removeJsonDraft(String serviceId);
}
