package com.sap.ibso.eservices.sagiaservices.converters.simulation;

import com.sap.ibso.eservices.facades.data.license.amendment.LicenseAmendmentTypeData;
import com.sap.ibso.eservices.facades.data.license.simulation.SimulatedPriceData;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationItemData;
import com.sap.ibso.eservices.sagiaservices.price.AmendmentParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class SimulatedPriceParamsConverter {

	private SimulatedPriceParamsConverter() {
	}

	public static AmendmentParam from(LicenseAmendmentTypeData licenseAmendmentTypeData) {
		AmendmentParam amendmentParam = new AmendmentParam();
		amendmentParam.setShareholderShareRedistribution(isSet(licenseAmendmentTypeData.getShReQu()));
		amendmentParam.setShareholderRemoval(isSet(licenseAmendmentTypeData.getShRem()));
		amendmentParam.setShareholderAddition(isSet(licenseAmendmentTypeData.getShAddExNew()));
		amendmentParam.setShareholderPropertyInheritance(isSet(licenseAmendmentTypeData.getShInProp()));
		amendmentParam.setBranchOpening(isSet(licenseAmendmentTypeData.getBrOpen())); 
		amendmentParam.setBranchClosing(isSet(licenseAmendmentTypeData.getBrClose()));
		amendmentParam.setProductChange(isSet(licenseAmendmentTypeData.getPrAddReCh()));
		amendmentParam.setEntityNameChange(isSet(licenseAmendmentTypeData.getEnNameCh()));
		amendmentParam.setEntityCapitalReduction(isSet(licenseAmendmentTypeData.getEnCapRed()));
		amendmentParam.setEntityLaborIncreasing(isSet(licenseAmendmentTypeData.getEnIncrWf()));
		amendmentParam.setEntityCapitalIncreasing(isSet(licenseAmendmentTypeData.getEnCapIncr()));
		amendmentParam.setEntityActivityChange(isSet(licenseAmendmentTypeData.getEnActCh()));
		amendmentParam.setEntityConversionToLimitedLiabilityCompany(isSet(licenseAmendmentTypeData.getEnEstToLlc()));
		amendmentParam.setEntityConversionToIndividualLimitedLiabilityCompany(isSet(licenseAmendmentTypeData.getEnLegalstatToIllc()));
		amendmentParam.setEntityMainBranchLocationChange(isSet(licenseAmendmentTypeData.getEnLocCh()));
		amendmentParam.setBranchNumberOfNewBranches(licenseAmendmentTypeData.getNewBranchesCount());
		return amendmentParam;
	}
	
	public static SimulatedPriceData from(PriceSimulationItemData priceSimulationItemData) {
		SimulatedPriceData simulatedPriceData = new SimulatedPriceData();
		simulatedPriceData.setServiceName(priceSimulationItemData.getServiceName());
        simulatedPriceData.setCurrency(priceSimulationItemData.getCurrencyIso());

        BigDecimal netValue = priceSimulationItemData.getNetValue();
        simulatedPriceData.setNetValue(netValue.setScale(2, RoundingMode.CEILING).toPlainString());

		return simulatedPriceData;
	}
	
	public static List<SimulatedPriceData> from(List<PriceSimulationItemData> priceSimulationItemData) {
		return priceSimulationItemData
				.stream()
				.map(SimulatedPriceParamsConverter::from)
				.collect(Collectors.toList());
	}
	
	private static boolean isSet(String parameter) {
		return parameter != null;
	}
}
