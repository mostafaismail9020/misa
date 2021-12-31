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
package com.sap.ibso.eservices.facades.populators.draft;

import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.draft.DraftFileInfo;
import com.sap.ibso.eservices.facades.data.draft.DraftInfo;
import com.sap.ibso.eservices.facades.data.draft.DraftParameterInfo;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.collections.CollectionUtils;
import org.fest.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.populators.draft
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DraftInfoPopulator implements Populator<SagiaDraftModel, DraftInfo> {
    private MediaService mediaService;
    private SagiaFormatProvider sagiaFormatProvider;

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776", "squid:S134"})
    @Override
    public void populate(final SagiaDraftModel source, final DraftInfo target) {
        target.setName(source.getFormId());
        target.setCreationDate(sagiaFormatProvider.getLocalizedDateData(source.getCreationDate()));
        target.setTemporaryNumber(source.getTemporaryId());
        target.setUrl(source.getUrl());

        final List<DraftParameterInfo> parametersInfo = new ArrayList<>();
        if (source.getParameters() != null && !CollectionUtils.isEmpty(source.getParameters())) {
            source.getParameters().forEach(parameter -> {
                final DraftParameterInfo info = new DraftParameterInfo();
                info.setName(parameter.getName());
                info.setType(parameter.getType());
                info.setValue(parameter.getValue());

                if (!Strings.isEmpty(parameter.getFileName())) {
                    info.setFileName(parameter.getFileName());
                }

                parametersInfo.add(info);
            });
        }
        target.setParameters(parametersInfo);

        final List<DraftFileInfo> files = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source.getDraftFiles())) {
            source.getDraftFiles().forEach(draftFile -> {
                final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(draftFile.getFileCode());
                final DraftFileInfo fileInfo = new DraftFileInfo();
                fileInfo.setId(draftFile.getFileCode());
                fileInfo.setAttachmentName(draftFile.getAttachmentName());
                fileInfo.setAttachmentInputName(draftFile.getAttachmentInputName());
                fileInfo.setName(media.getRealFileName());
                fileInfo.setMimeType(media.getMime());

                files.add(fileInfo);
            });
        }
        target.setFiles(files);

        ServiceTypeCategoryModel category = source.getServiceCategory();
        if (category != null) {
            target.setCategory1(category.getName());
            if (category.getParentCategory() != null) {
                category = category.getParentCategory();
                target.setCategory2(category.getName());

                if (category.getParentCategory() != null) {
                    category = category.getParentCategory();
                    target.setCategory3(category.getName());

                    if (category.getParentCategory() != null) {
                        category = category.getParentCategory();
                        target.setCategory4(category.getName());
                    }
                    else {
                        target.setCategory4(category.getServiceType().getName());
                    }
                }
                else {
                    target.setCategory3(category.getServiceType().getName());
                }
            }
            else {
                target.setCategory2(category.getServiceType().getName());
            }
        }
        else {
            final ServiceTypeModel serviceType = source.getServiceType();
            if (serviceType != null) {
                target.setCategory1(serviceType.getName());
            }
        }
    }

    public MediaService getMediaService() {
        return mediaService;
    }

    public void setMediaService(final MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
