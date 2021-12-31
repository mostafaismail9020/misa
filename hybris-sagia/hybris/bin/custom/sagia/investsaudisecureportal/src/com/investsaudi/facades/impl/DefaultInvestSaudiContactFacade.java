package com.investsaudi.facades.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.investsaudi.data.InvestSaudiContactData;
import com.investsaudi.data.InvestSaudiContactDepartmentData;
import com.investsaudi.data.InvestSaudiContactListData;
import com.investsaudi.facades.InvestSaudiContactFacade;
import com.investsaudi.services.InvestsaudiContactService;
import com.sap.ibso.eservices.core.model.InvestSaudiContactModel;
import com.sap.ibso.eservices.core.model.InvestSaudiSectorModel;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultInvestSaudiContactFacade implements InvestSaudiContactFacade {

	@Resource
	private InvestsaudiContactService investsaudiContactService ;
	
	@Resource(name = "defaultInvestSaudiContactConverter")
	private Converter<InvestSaudiContactModel, InvestSaudiContactData> investSaudiContactConverter;

	
	@Override
	public InvestSaudiContactListData getContactDataList() {
		
		InvestSaudiContactListData investSaudiContactListData = new InvestSaudiContactListData();
		
		List<InvestSaudiContactDepartmentData> investSaudiContactDataList = new ArrayList<InvestSaudiContactDepartmentData>();
			
		List<InvestSaudiSectorModel>  listDepartments = investsaudiContactService.getContactDepartment() ;
			
		for (InvestSaudiSectorModel investSaudiSector : listDepartments) {
			
			 List<InvestSaudiContactData> contactData = Converters.convertAll(investsaudiContactService.getActiveContatByDepartment(investSaudiSector), investSaudiContactConverter);
             if(contactData != null && !contactData.isEmpty()) {
            	 InvestSaudiContactDepartmentData investSaudiContactDepartmentData = new InvestSaudiContactDepartmentData();
            	 investSaudiContactDepartmentData.setContacts(contactData);
            	 investSaudiContactDepartmentData.setCode(investSaudiSector.getCode());
            	 investSaudiContactDepartmentData.setName(investSaudiSector.getName());
            	 investSaudiContactDataList.add(investSaudiContactDepartmentData);
             }
		}
		investSaudiContactListData.setDepartments(investSaudiContactDataList);
		return investSaudiContactListData;
	}

}
