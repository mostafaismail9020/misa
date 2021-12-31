package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.facades.data.ShareHoldersData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class LicenseShareholderPopulator implements Populator<ShareHolderModel, ShareHoldersData> {

	@Override
	public void populate(ShareHolderModel source, ShareHoldersData target) throws ConversionException {
		target.setShareHolderType(source.getShareHolderType());
		target.setShareHolderIdType(source.getShareHolderIdType());
		target.setCode(source.getCode());
	}
}
