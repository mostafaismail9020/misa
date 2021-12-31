package com.sap.ibso.eservices.sagiaservices.services.licensereplacement;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LicenseReplacementService
 */
public final class LicenseReplacementService extends AbstractSagiaService<LicenseReplaceMentData> {
    /**
     * get all License Replacement history list
     * @return Collection of LicenseReplaceMentData
     */
    public final Collection<LicenseReplaceMentData> getCollection() {
        return super.getCollection(LicenseReplaceMentData.class);
    }

    /**
     * retrieves LicenseReplaceMentData by id
     * @param id unique identifier of License Replacement
     * @return LicenseReplaceMentData
     */
    public final LicenseReplaceMentData get(String id){
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .expand(createExpandQuery())
                .build();
        return super.get(LicenseReplaceMentData.class, id, queryOptions);
    }


    /**
     * create new License Replacement
     * @param licenseReplacementFormData licenseReplacementFormData
     * @param supportedAttachments supportedAttachments
     */
    public void createLicenseReplacement(LicenseReplacementFormData licenseReplacementFormData,
            Collection<CustomizingGetData> supportedAttachments) {
        LicenseReplaceMentData licenseRep = LicenseReplacementConverter.fromFormData(licenseReplacementFormData, supportedAttachments);
        licenseRep.setTransType("ZSR8");
        save(licenseRep);
    }


    /**
     *
     * resubmit an existing License Replacement
     * @param licenseReplacementResubmitFormData licenseReplacementResubmitFormData
     * @param previouslyAttachedFiles previouslyAttachedFiles
     */
	public void updateLicenseReplacement(LicenseReplacementResubmitFormData licenseReplacementResubmitFormData,
			List<ContentHDRData> previouslyAttachedFiles) {
		LicenseReplaceMentData licenseReplaceMent = LicenseReplacementConverter.fromResubmitFormData(licenseReplacementResubmitFormData, previouslyAttachedFiles);
		licenseReplaceMent.setReApply(true);
		licenseReplaceMent.setSrGuid(licenseReplacementResubmitFormData.getSrGuid());
		save(licenseReplaceMent);
	}


    /**
     * get all navigation properties of License Replacement
     * build full expression of $expand parameter
     * @return expanded Query
     */
    private String createExpandQuery() {
        return Arrays.asList(LicenseReplaceMentExpandableEntities.values())
                .stream()
                .map(LicenseReplaceMentExpandableEntities::navEntity)
                .collect(Collectors.joining(","));
    }

    /**
     * retrieves LatestEntityCreated
     * @return LicenseReplaceMentData
     */
	public LicenseReplaceMentData getLatestEntityCreated() {
		return getCollection().stream().findFirst().orElse(null);
	}
}
