package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.QeemahGeneralDropdown;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.QeemahGeneralddData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class QeemahGeneralDropdownPopulator implements Populator<QeemahGeneralddData, QeemahGeneralDropdown> {
    @Override
    public void populate(QeemahGeneralddData qeemahGeneralddData, QeemahGeneralDropdown qeemahGeneralDropdown) throws ConversionException {
        qeemahGeneralDropdown.setKey(qeemahGeneralddData.getZkey());
		qeemahGeneralDropdown.setValue(qeemahGeneralddData.getValue());
    }
}
