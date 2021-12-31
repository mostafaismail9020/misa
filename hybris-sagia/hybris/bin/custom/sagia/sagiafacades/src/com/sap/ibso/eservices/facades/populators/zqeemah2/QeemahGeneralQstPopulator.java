package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.QeemahGeneralDropdown;
import com.sap.ibso.eservices.facades.data.zqeemah2.QeemahGeneralQst;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.QeemahGeneralQstData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.QeemahGeneralddData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class QeemahGeneralQstPopulator implements Populator<QeemahGeneralQstData, QeemahGeneralQst> {
    QeemahGeneralDropdownPopulator qeemahGeneralDropdownPopulator;

    @Override
    public void populate(QeemahGeneralQstData qeemahGeneralQstData, QeemahGeneralQst qeemahGeneralQst) throws ConversionException {
        qeemahGeneralQst.setLanguage(qeemahGeneralQstData.getLanguage());
		qeemahGeneralQst.setReferenceId(qeemahGeneralQstData.getRefid());
		qeemahGeneralQst.setQuestionId(qeemahGeneralQstData.getQuestionId());
		qeemahGeneralQst.setSectionNumber(qeemahGeneralQstData.getSectionno());
		qeemahGeneralQst.setQuestionText(qeemahGeneralQstData.getQuestionTxt());
		qeemahGeneralQst.setQuestionType(qeemahGeneralQstData.getQuestionType());
        List<QeemahGeneralDropdown> qeemahGeneralQstDataList = new ArrayList<>();
        if(qeemahGeneralQstData.getNavGeneralQuest() != null && !qeemahGeneralQstData.getNavGeneralQuest().isEmpty()){
            for(QeemahGeneralddData item : qeemahGeneralQstData.getNavGeneralQuest()){
                QeemahGeneralDropdown qeemahGeneralDropdown = new QeemahGeneralDropdown();
                qeemahGeneralDropdownPopulator.populate(item, qeemahGeneralDropdown);
                qeemahGeneralQstDataList.add(qeemahGeneralDropdown);
            }
        }
        qeemahGeneralQst.setGeneralQuest(qeemahGeneralQstDataList);
    }

    public void setQeemahGeneralDropdownPopulator(QeemahGeneralDropdownPopulator qeemahGeneralDropdownPopulator) {
        this.qeemahGeneralDropdownPopulator = qeemahGeneralDropdownPopulator;
    }
}
