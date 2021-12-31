package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.BusinessActivity;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.IsicDetPsSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class IsicDetPsReversePopulator implements Populator<BusinessActivity, IsicDetPsSetData> {

    @Override
    public void populate(BusinessActivity source, IsicDetPsSetData target) throws ConversionException {
        target.setInvestorid(source.getInvestorId());
        target.setActivity(source.getLicenseType());
        target.setLic(source.getBusinessType());
        target.setIsicSection(source.getSectionId());
        target.setIsicDivision(source.getDivisionId().replace(".0", ""));
        target.setIsicGroup(source.getGroupId().replace(".0", ""));
        target.setIsicClass(source.getClassId().replace(".0", ""));
        target.setIsicActivity(source.getActivityId().replace(".0", ""));
        target.setIsicBranch(source.getBranchId().replace(".0", ""));
        target.setAct(source.getActivityId().replace(".0", ""));
        target.setActDesc(source.getDescription());
    }
}
