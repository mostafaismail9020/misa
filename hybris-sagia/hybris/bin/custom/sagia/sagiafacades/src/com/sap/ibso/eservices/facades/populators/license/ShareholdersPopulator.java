package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.facades.data.ShareholderData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ShareHolderInfoData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ShareholdersPopulator implements Populator<ShareHolderInfoData,ShareholderData> {
    @Override
    public void populate(ShareHolderInfoData shareholder, ShareholderData shareholderData)  {
        shareholderData.setName(shareholder.getMiddleName() == null || shareholder.getMiddleName().isEmpty() ? (shareholder.getFirstName()+" "+shareholder.getLastName()).trim() : (shareholder.getFirstName()+" "+shareholder.getMiddleName()+" "+shareholder.getLastName()).trim());
        shareholderData.setSharesPercentage(Double.parseDouble(shareholder.getShareinfo().trim()));
        shareholderData.setType(shareholder.getContactType());
        shareholderData.setNationality(shareholder.getNationality());
    }
}
