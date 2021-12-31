package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.sagiaservices.data.GovtServicesToUploadNav;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceFileUpload;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtServicesToUploadNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaGovtServiceFileUploadData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaGovtServiceUploadPopulator implements Populator<SagiaGovtServiceFileUpload, SagiaGovtServiceFileUploadData> {

    /**
     * Populate from SagiaGovtServiceFileUpload to SagiaGovtServiceFileUploadData.
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(SagiaGovtServiceFileUpload source, SagiaGovtServiceFileUploadData target) throws ConversionException {
        target.setSrID(source.getSrID());
        target.setMinistryType(source.getMinistryType());
        target.setServiceType(source.getServiceType());
        List<GovtServicesToUploadNav> uploadedFiles = source.getGovtServicesToUploadNav();
        if(CollectionUtils.isNotEmpty(uploadedFiles)){
            List<GovtServicesToUploadNavData> files = new ArrayList<>();
            uploadedFiles.forEach( uploadedFile -> {
                GovtServicesToUploadNavData file = new GovtServicesToUploadNavData();
                file.setFilename(uploadedFile.getFilename());
                file.setMimeType(uploadedFile.getMimeType());
                file.setFileContString(uploadedFile.getFileContString());
                file.setDockey_ID(uploadedFile.getDockeyID());
                files.add(file);
            });
            target.setGovtServicesToUploadNav(files);
        }
    }
}
