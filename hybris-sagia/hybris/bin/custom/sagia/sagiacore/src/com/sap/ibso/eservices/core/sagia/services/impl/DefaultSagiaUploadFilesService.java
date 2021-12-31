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
package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.Date;
import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaUploadFilesDataModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaUploadFilesDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaUploadFilesService;

/**
 * Default implementation of SagiaUploadFilesService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaUploadFilesService implements SagiaUploadFilesService
{
	private transient SagiaUploadFilesDAO sagiaUploadFilesDAO;

	@Override
	public List<SagiaUploadFilesDataModel> getUploadedFilesData(final Date date)
	{
		return sagiaUploadFilesDAO.getFilesUploadedFilesRecords(date);
	}

	public SagiaUploadFilesDAO getSagiaUploadFilesDAO()
	{
		return sagiaUploadFilesDAO;
	}

	public void setSagiaUploadFilesDAO(final SagiaUploadFilesDAO sagiaUploadFilesDAO)
	{
		this.sagiaUploadFilesDAO = sagiaUploadFilesDAO;
	}
}
