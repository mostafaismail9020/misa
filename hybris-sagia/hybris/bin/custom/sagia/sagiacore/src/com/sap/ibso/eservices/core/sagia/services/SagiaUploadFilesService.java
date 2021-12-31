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

import com.sap.ibso.eservices.core.model.SagiaUploadFilesDataModel;

/**
 * Provides access to the files upload service
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaUploadFilesService
{
	/**
	 * Get the uploaded files data
	 * @param date - The date for which the uploaded files are retrieved.
	 * @return - The Upload Files Data Model on that specific date.
	 */
	List<SagiaUploadFilesDataModel> getUploadedFilesData(Date date);
}
