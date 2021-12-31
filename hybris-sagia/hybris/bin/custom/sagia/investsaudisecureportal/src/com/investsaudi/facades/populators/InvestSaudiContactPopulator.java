package com.investsaudi.facades.populators;

import com.investsaudi.data.InvestSaudiContactData;
import com.sap.ibso.eservices.core.model.InvestSaudiContactModel;

import de.hybris.platform.converters.Populator;

/**
 *
 */
public class InvestSaudiContactPopulator implements Populator<InvestSaudiContactModel, InvestSaudiContactData> {
	
    @Override
    public void populate(InvestSaudiContactModel source, InvestSaudiContactData target) {
    	target.setCode(source.getCode());
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setMobile(source.getMobile());
        target.setTelephone(source.getTelephone());
        target.setPosistion(source.getPosistion());
    }
}