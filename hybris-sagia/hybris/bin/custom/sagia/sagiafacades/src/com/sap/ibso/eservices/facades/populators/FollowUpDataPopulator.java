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
package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.ContentHDRDocument;
import com.sap.ibso.eservices.facades.data.specialservices.FollowUpData;
import com.sap.ibso.eservices.facades.data.specialservices.TextData;
import com.sap.ibso.eservices.facades.data.specialservices.ViolationData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FollowTextNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FollowUpServicesData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ViolationNavData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;
import org.fest.util.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.populators
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class FollowUpDataPopulator implements Populator<FollowUpServicesData, FollowUpData> {

    private SagiaFormatProvider sagiaFormatProvider;
    private ServicesRequestDocumentPopulator servicesRequestDocumentPopulator;

    @Override
    public void populate(final FollowUpServicesData source, final FollowUpData target)
            throws ConversionException {
        target.setSrId(source.getSrId());
        target.setSrGuid(source.getSrGuid());
        target.setMinistryType(source.getMinistryType());
        target.setServiceType(source.getServiceType());
        target.setObjectId(source.getObjectId());
        target.setMessage(source.getMessage());
        target.setMessageType(source.getMessageType());
        target.setBpGuid(source.getBpGuid());
        target.setBpId(source.getBpId());
        target.setComments(source.getComments());
        target.setAction(source.getAction());
        target.setSrStCode(source.getSrStCode());
        target.setSrStDesc(source.getSrStDesc());
        target.setSrStCode(source.getSrStCode());
        target.setNoOfApprDaysExtn(Integer.parseInt(source.getNoOfApprDaysExtn()));
        target.setNoOfDaysExtension(Integer.parseInt(source.getNoOfDaysExtension()));

        if (!Strings.isEmpty(source.getSrCrDate())) {
            target.setSrCrDate(sagiaFormatProvider.getLocalizedDateData(Long.parseLong(source.getSrCrDate())));
        }

        if (!Strings.isEmpty(source.getExtWlDate())) {
            target.setExtWlDate(sagiaFormatProvider.getLocalizedDateData(Long.parseLong(source.getExtWlDate())));
        }

        if (!Strings.isEmpty(source.getExtWlEndDate())) {
            target.setExtWlEndDate(sagiaFormatProvider.getLocalizedDateData(Long.parseLong(source.getExtWlEndDate())));
        }

        setContentHdr(source, target);
        setViolation(source, target);
        setFollowText(source, target);
    }

	private void setContentHdr(FollowUpServicesData source, FollowUpData target) {
		if (!CollectionUtils.isEmpty(source.getContentHdr())) {
			final Set<ContentHDRDocument> contentData = new HashSet<>();
			for (final ContentHDRData srcData : source.getContentHdr()) {
				final ContentHDRDocument contentHDRDocument = new ContentHDRDocument();
				servicesRequestDocumentPopulator.populate(srcData, contentHDRDocument);
				contentData.add(contentHDRDocument);
			}
			target.setContentHdr(contentData);
		}
	}

    private void setViolation(FollowUpServicesData source, FollowUpData target) {
        if (!CollectionUtils.isEmpty(source.getViolation())) {
            final Set<ViolationData> violationData = new HashSet<>();
            for (final ViolationNavData srcData : source.getViolation()) {
                final ViolationData trgData = new ViolationData();
                trgData.setSrId(srcData.getSrId());
                trgData.setServiceType(srcData.getServiceType());
                trgData.setViolationKey(srcData.getViolationKey());
                trgData.setViolationText(srcData.getViolationText());
                trgData.setViolationReply(srcData.getViolationReply());
                trgData.setViolationReplyText(srcData.getViolationReplyText());
                trgData.setViolationStatus(srcData.getViolationStatus());
                trgData.setViolationStatusText(srcData.getViolationStatusText());
                trgData.setViolationNote(srcData.getViolationNote());
                violationData.add(trgData);
            }
            target.setViolation(violationData);
        }
    }

    private void setFollowText(FollowUpServicesData source, FollowUpData target) {
        if (!CollectionUtils.isEmpty(source.getFollowText())) {
            final Set<TextData> textData = new HashSet<>();
            for (final FollowTextNavData srcData : source.getFollowText()) {
                final TextData trgData = new TextData();
                trgData.setSrID(srcData.getSrID());
                trgData.setCommentBy(srcData.getCommentBy());
                trgData.setComments(srcData.getComments());

                if (!Strings.isEmpty(srcData.getCommentDate())) {
                    trgData.setCommentDate(sagiaFormatProvider.getLocalizedDateData(Long.parseLong(srcData.getCommentDate())));
                }

                textData.add(trgData);
            }
            target.setFollowText(textData);
        }
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
    
    public void setServicesRequestDocumentPopulator(ServicesRequestDocumentPopulator servicesRequestDocumentPopulator) {
        this.servicesRequestDocumentPopulator = servicesRequestDocumentPopulator;
    }
    
    public ServicesRequestDocumentPopulator getServicesRequestDocumentPopulator() {
        return servicesRequestDocumentPopulator;
    }

}
