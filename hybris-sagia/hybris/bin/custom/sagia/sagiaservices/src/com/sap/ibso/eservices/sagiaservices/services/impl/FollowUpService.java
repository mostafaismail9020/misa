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
package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FollowUpServicesData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.WarningLettersInfos;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMCreateException;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.*;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.fest.util.Strings;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * FollowUpService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class FollowUpService extends AbstractSagiaService<FollowUpServicesData> {

	private static final Logger LOG = Logger.getLogger(FollowUpService.class);
	private static final String VIOLATION_REPLIES_SERVICE_TYPE = "ZFLUP_02";
	private static final String WARNING_LETTER_EXTENSION_SERVICE_TYPE = "ZFLUP_03";
	private static final String FOLLOW_UP_SERVICES_EXPAND = "FollowupServicesToViolationsNav,FollowupServicesToContentHDRNav,FollowupServicesToTextNav";
	private static final String SERVICE_TYPE = "ServiceType";
	private static final String MINISTRY_TYPE = "ZFLUP";
	private static final String TRANS_TYPE = "ZS15";
	private static final String VIOLATION_REPLY_PARAMETER = "FI";

	private ModelService modelService;
	private MediaService mediaService;

	/**
	 * retrieves ViolationRepliesEntries
	 * @return Collection of FollowUpServicesData
	 */
	public Collection<FollowUpServicesData> getViolationRepliesEntries() {
		final String parameters = SERVICE_TYPE + " eq '" + VIOLATION_REPLIES_SERVICE_TYPE + "'";
		return getCollection(FollowUpServicesData.class, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, parameters);
	}

	/**
	 * retrieves WarningLetterExtensionEntries
	 * @return Collection of FollowUpServicesData
	 */
	public Collection<FollowUpServicesData> getWarningLetterExtensionEntries() {
		final String parameters = SERVICE_TYPE + " eq '" + WARNING_LETTER_EXTENSION_SERVICE_TYPE + "'";
		return getCollection(FollowUpServicesData.class, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, parameters);
	}

	/**
	 * retrieves SingleEntry by id
	 * @param id id
	 * @return FollowUpServicesData
	 */
	public FollowUpServicesData getSingleEntry(Object id) {
		return get(FollowUpServicesData.class, id, SagiaservicesConstants.REQUEST_PARAMETER_EXPAND, FOLLOW_UP_SERVICES_EXPAND);
	}

	/**
	 * creates ViolationReply
	 * @param letter letter
	 * @param form form
	 * @throws SagiaCRMCreateException exception
	 */
	public void createViolationReply(final WarningLettersInfos letter, final CreateViolationReplyFormData form)
		throws SagiaCRMCreateException	{

		final FollowUpCRMCreateData data = new FollowUpCRMCreateData();
		data.setComments(form.getComments());
		data.setServiceType(VIOLATION_REPLIES_SERVICE_TYPE);
		data.setMinistryType(MINISTRY_TYPE);
		data.setTransType(TRANS_TYPE);
		data.setNoOfDaysExtension("");

		final List<FollowUpFileUploadData> docs = new ArrayList<>();
		final List<FollowUpViolationData> violations = new ArrayList<>();

		if(letter.getViolations()!=null) {
			letter.getViolations().forEach(violation -> {
				final FollowUpViolationData violationData = new FollowUpViolationData();
				violationData.setBpId(violation.getBpId());

				if (form.getViolationsTexts() != null && form.getViolations() != null
						&& form.getViolations().contains(violation.getViolationKey())) {
					violationData.setViolationNote(
							form.getViolationsTexts().get(form.getViolations().indexOf(violation.getViolationKey())));
				}
				violationData.setServiceType(VIOLATION_REPLIES_SERVICE_TYPE);
				violationData.setWarningEndDate(createWarningDateFrom(violation.getWarningEndDate()));
				violationData.setWarningLetterNo(violation.getWarningLetterNo());
				violationData.setViolationText(violation.getViolationText());
				violationData.setViolationKey(violation.getViolationKey());
				violationData.setViolationReply(VIOLATION_REPLY_PARAMETER);

				violations.add(violationData);
			});
		}
		data.setViolations(violations);

		form.getDocs().forEach(fileCode -> {
			if (!Strings.isEmpty(fileCode)) {
				final CatalogUnawareMediaModel uploadedFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
				final FollowUpFileUploadData fileUploadData = new FollowUpFileUploadData();

				fileUploadData.setFilename(uploadedFile.getRealFileName());
				fileUploadData.setMimeType(uploadedFile.getMime());
				fileUploadData.setFileContString(org.apache.ws.security.util.Base64.encode(mediaService.getDataFromMedia(uploadedFile)));
				docs.add(fileUploadData);
			}
		});
		data.setDocs(docs);

		final String error = createWithErrorHandling(data);
		if (error != null) {
			throw new SagiaCRMCreateException(error);
		}
	}

	/**
	 * creates WarningLetterExtension
	 * @param letter letter
	 * @param form form
	 * @throws SagiaCRMCreateException exception
	 */
	public void createWarningLetterExtension(final WarningLettersInfos letter, final CreateWarningLetterExtensionFormData form) throws SagiaCRMCreateException	{
		final FollowUpCRMCreateData data = new FollowUpCRMCreateData();
		data.setComments(form.getExtensionReason());
		data.setServiceType(WARNING_LETTER_EXTENSION_SERVICE_TYPE);
		data.setMinistryType(MINISTRY_TYPE);
		data.setTransType(TRANS_TYPE);
		data.setNoOfDaysExtension(form.getDaysExtension().toString());

		final List<FollowUpFileUploadData> docs = new ArrayList<>();
		final List<FollowUpViolationData> violations = new ArrayList<>();

		if(letter.getViolations() != null && !letter.getViolations().isEmpty()) {
			letter.getViolations().forEach(violation -> {
				final FollowUpViolationData violationData = new FollowUpViolationData();
				violationData.setBpId(violation.getBpId());
				violationData.setViolationNote(violation.getViolationNote());
				violationData.setServiceType(WARNING_LETTER_EXTENSION_SERVICE_TYPE);
				violationData.setWarningEndDate(createWarningDateFrom(violation.getWarningEndDate()));
				violationData.setWarningLetterNo(violation.getWarningLetterNo());
				violationData.setViolationText(violation.getViolationText());
				violationData.setViolationKey(violation.getViolationKey());
				violationData.setViolationReply(violation.getViolationReply());

				violations.add(violationData);
			});
		}
		data.setViolations(violations);

		if(form.getDocs() != null && !form.getDocs().isEmpty()) {
			form.getDocs().forEach(fileCode -> {
				if (!Strings.isEmpty(fileCode)) {
					final CatalogUnawareMediaModel uploadedFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
					final FollowUpFileUploadData fileUploadData = new FollowUpFileUploadData();

					fileUploadData.setFilename(uploadedFile.getRealFileName());
					fileUploadData.setMimeType(uploadedFile.getMime());
					fileUploadData.setFileContString(org.apache.ws.security.util.Base64.encode(mediaService.getDataFromMedia(uploadedFile)));
					docs.add(fileUploadData);
				}
			});
		}
		data.setDocs(docs);

		final String error = createWithErrorHandling(data);
		if (error != null) {
			throw new SagiaCRMCreateException(error);
		}
	}
	
	private String createWarningDateFrom(String date) {
		if (date == null) {
			return null;
		}
		if(date.isEmpty()) {
			return date;
		}
		try {
			Instant fromEpochMilli = Instant.ofEpochMilli(NumberUtils.toLong(date));
			return fromEpochMilli.toString().replace("Z", "");
		}
		catch(DateTimeException ex) {
			LOG.warn(ex.getMessage(),ex);
			return date;
		}
	}

	/**
	 * @return
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return
	 */
	public MediaService getMediaService()
	{
		return mediaService;
	}

	/**
	 * @param mediaService
	 */
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}
}
