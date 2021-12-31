package com.sap.ibso.eservices.sagiaservices.services.license.application.populator;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.QeemahGeneralQstData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.QeemahGeneralddData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class QeemahGeneralQstDataPopulator extends ODataPopulator<QeemahGeneralQstData> {
    private QeemahGeneralDdDataPopulator qeemahGeneralDdDataPopulator;

    @Override
    public void populate(ODataModel model, QeemahGeneralQstData qeemahGeneralQstData) throws ConversionException {
        super.populate(model, qeemahGeneralQstData);
        Map<String, Object> map = model.get();
        ODataFeed dropdownData = (ODataFeed) map.get("NavGeneralQuest");
        if(dropdownData != null && dropdownData.getEntries() != null && !dropdownData.getEntries().isEmpty()) {
            List<QeemahGeneralddData> qeemahGeneralddData = new ArrayList<>();
            for(ODataEntry oDataEntry : dropdownData.getEntries()) {
                QeemahGeneralddData generalddData = new QeemahGeneralddData();
                qeemahGeneralDdDataPopulator.populate(new ODataModel(oDataEntry), generalddData);
                qeemahGeneralddData.add(generalddData);
            }
            qeemahGeneralQstData.setNavGeneralQuest(qeemahGeneralddData);
        }
    }

    public void setQeemahGeneralDdDataPopulator(QeemahGeneralDdDataPopulator qeemahGeneralDdDataPopulator) {
        this.qeemahGeneralDdDataPopulator = qeemahGeneralDdDataPopulator;
    }
}
