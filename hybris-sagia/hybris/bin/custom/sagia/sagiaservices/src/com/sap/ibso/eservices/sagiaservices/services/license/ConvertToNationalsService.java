package com.sap.ibso.eservices.sagiaservices.services.license;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ConvToNationalsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.CustomMultipartFile;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ConvertToNationalsService
 */
public class ConvertToNationalsService extends AbstractSagiaService<ConvToNationalsData> {

    private MediaService mediaService;


    /**
     * get all Convert to Nationals history list
     *
     * @return Collection of ConvToNationalsData
     */
    public final Collection<ConvToNationalsData> getConvertToNationalHistory() {
        return getCollection(ConvToNationalsData.class);
    }

    /**
     * retrieves ConvertToNational By srID
     *
     * @param srID unique identifier of Convert to Nationals
     * @return ConvToNationalsData
     */
    public final ConvToNationalsData getConvertToNationalBy(String srID) {
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .expand(createConvToNatExpandQuery())
                .build();
        return get(ConvToNationalsData.class, srID, queryOptions);
    }


    /**
     * create new Convert to Nationals
     *
     * @param convToNationalsFormData convToNationalsFormData
     * @param supportedAttachments    supportedAttachments
     */
    public void create(ConvToNationalsFormData convToNationalsFormData, Collection<CustomizingGetData> supportedAttachments) {
        if (convToNationalsFormData.getFileData() != null && !convToNationalsFormData.getFileData().isEmpty()) {
            for (int i = 0; i < convToNationalsFormData.getFileData().size(); i++) {
                if (convToNationalsFormData.getFileData().get(i) != null) {
                    final String fileCode = convToNationalsFormData.getFileData().get(i);
                    final CatalogUnawareMediaModel mediaFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                    final MultipartFile customMultipartFile = new CustomMultipartFile(
                            mediaService.getDataFromMedia(mediaFile),
                            mediaFile.getRealFileName(),
                            mediaFile.getMime());
                    convToNationalsFormData.getFiles().set(i, customMultipartFile);
                }
            }
        }

        ConvToNationalsData convToNationals = ConvToNationalsConverter.fromFormData(convToNationalsFormData, supportedAttachments);
        convToNationals.setTransType("ZSR7");
        save(convToNationals);
    }

    /**
     * resubmit an existing Convert to Nationals
     *
     * @param formData                formData
     * @param previouslyAttachedFiles previouslyAttachedFiles
     */
    public void update(ConvToNationalsResubmitFormData formData, List<ContentHDRData> previouslyAttachedFiles) {
        ConvToNationalsData convToNationals = ConvToNationalsConverter.fromResubmitFormData(formData, previouslyAttachedFiles);
        convToNationals.setReApply(true);
        convToNationals.setSrGuid(formData.getSrGuid());
        save(convToNationals);
    }

    /**
     * get all navigation properties of Convert to Nationals
     * build full expression of $expand parameter
     */
    private String createConvToNatExpandQuery() {
        return Arrays.asList(ConvToNationalExpandableEntities.values())
                .stream()
                .map(ConvToNationalExpandableEntities::navEntity)
                .collect(Collectors.joining(","));
    }

    public MediaService getMediaService() {
        return mediaService;
    }

    public void setMediaService(final MediaService mediaService) {
        this.mediaService = mediaService;
    }
}