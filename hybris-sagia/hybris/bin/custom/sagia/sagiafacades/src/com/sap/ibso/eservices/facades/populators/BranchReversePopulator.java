package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtBranchData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.NO;
import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.YES;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class BranchReversePopulator implements Populator<BranchData,GovtBranchData> {
    @Override
    public void populate(BranchData branchData, GovtBranchData govtBranchData) throws ConversionException {
        govtBranchData.setBranchCity(branchData.getCity());
        govtBranchData.setBranchName(branchData.getName());
        govtBranchData.setBranchType(branchData.getType());
        govtBranchData.setMomraYesNo(branchData.getHasMomra()? YES : NO);
        govtBranchData.setCrYesNo(branchData.getHasCr() ? YES : NO);
    }

    public void populateMainBranch(BranchData branchData, GovtHeaderData govtHeaderData) throws ConversionException {
        govtHeaderData.setCrNo(branchData.getCommercialRegisterNumber());
        govtHeaderData.setGosiNo(branchData.getGosiNumber());
        govtHeaderData.setZakatNo(branchData.getZakatNumber());
        govtHeaderData.setMolNo(branchData.getMolNumber());
        govtHeaderData.setMomraYesNo(branchData.getHasMomra()? YES : NO);
        govtHeaderData.setShopLicNo(branchData.getShopLicenseNumber());
        govtHeaderData.setAmanah(branchData.getAmanah());
    }

}
