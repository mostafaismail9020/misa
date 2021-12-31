package com.sap.ibso.eservices.facades.sagia.economic.impl;


import com.sap.ibso.eservices.core.sagia.services.SAInternationalIndicesService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import com.sap.ibso.eservices.facades.sagia.economic.SAInternationalIndicesFacade;

import com.sap.ibso.eservices.core.model.EaseOfProtectingMinorityInvestorsModel;
import com.sap.ibso.eservices.core.model.LogisticsPerformanceIndexModel;
import com.sap.ibso.eservices.core.model.EconomicFreedomIndexModel;
import com.sap.ibso.eservices.core.model.GlobalInnovationIndexModel;
import com.sap.ibso.eservices.core.model.EGovernmentDevelopmentIndexModel;
import com.sap.ibso.eservices.core.model.GlobalCompetitivenessReportModel;
import com.sap.ibso.eservices.core.model.LegatumProsperityIndexModel;
import com.sap.ibso.eservices.core.model.EaseOfStartingBusinessModel;
import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;

import java.util.List;
import java.util.ArrayList;


public class DefaultSAInternationalIndicesFacade implements SAInternationalIndicesFacade {

	  private SAInternationalIndicesService saInternationalIndicesService;
	  private Converter<EaseOfProtectingMinorityInvestorsModel, SAInternationalIndicesData> easeOfProtectingMinorityInvestorsConverter; 
	  private Converter<LogisticsPerformanceIndexModel, SAInternationalIndicesData> logisticsPerformanceIndexConverter;
	  private Converter<EconomicFreedomIndexModel, SAInternationalIndicesData> economicFreedomIndexConverter;
	  private Converter<GlobalInnovationIndexModel, SAInternationalIndicesData> globalInnovationIndexConverter;
	  private Converter<EGovernmentDevelopmentIndexModel, SAInternationalIndicesData> eGovernmentDevelopmentIndexConverter;
	  private Converter<GlobalCompetitivenessReportModel, SAInternationalIndicesData> globalCompetitivenessReportConverter;
	  private Converter<LegatumProsperityIndexModel, SAInternationalIndicesData> legatumProsperityIndexConverter;
	  private Converter<EaseOfStartingBusinessModel, SAInternationalIndicesData> easeOfStartingBusinessConverter;
	

	@Override
	public List<SAInternationalIndicesData> getSAInternationalIndicesListData(final String indicator, final Integer startYear, final Integer endYear)
	  {
		  if("EaseOfProtectingMinorityInvestors".equals(indicator))
		  {
			 final List<EaseOfProtectingMinorityInvestorsModel> model = getSaInternationalIndicesService().getEaseOfProtectingMinorityInvestorsModel(indicator,startYear,endYear);
			 
			 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
					
			 for(final EaseOfProtectingMinorityInvestorsModel saInternationalIndicesModel :  model)
			 {
				 final SAInternationalIndicesData eoData = getEaseOfProtectingMinorityInvestorsConverter().convert(saInternationalIndicesModel);
				 saInternationalIndicesListData.add(eoData);
			 }
			 return saInternationalIndicesListData;
					
		  }
		  else if("LogisticsPerformanceIndex".equals(indicator))
		  {
			  final List<LogisticsPerformanceIndexModel> model = getSaInternationalIndicesService().getLogisticsPerformanceIndexModel(indicator,startYear,endYear);
				 
				 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
						
				 for(final LogisticsPerformanceIndexModel saInternationalIndicesModel :  model)
				 {
					 final SAInternationalIndicesData eoData = getLogisticsPerformanceIndexConverter().convert(saInternationalIndicesModel);
					 saInternationalIndicesListData.add(eoData);
				 }
				 return saInternationalIndicesListData;
		  }
		  else if("EconomicFreedomIndex".equals(indicator))
		  {
			  final List<EconomicFreedomIndexModel> model = getSaInternationalIndicesService().getEconomicFreedomIndexModel(indicator,startYear,endYear);
				 
				 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
						
				 for(final EconomicFreedomIndexModel saInternationalIndicesModel :  model)
				 {
					 final SAInternationalIndicesData eoData = getEconomicFreedomIndexConverter().convert(saInternationalIndicesModel);
					 saInternationalIndicesListData.add(eoData);
				 }
				 return saInternationalIndicesListData;
		  }
		  else if("GlobalInnovationIndex".equals(indicator))
		  {
			  final List<GlobalInnovationIndexModel> model = getSaInternationalIndicesService().getGlobalInnovationIndexModel(indicator,startYear,endYear);
				 
				 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
						
				 for(final GlobalInnovationIndexModel saInternationalIndicesModel :  model)
				 {
					 final SAInternationalIndicesData eoData = getGlobalInnovationIndexConverter().convert(saInternationalIndicesModel);
					 saInternationalIndicesListData.add(eoData);
				 }
				 return saInternationalIndicesListData;
		  }
		  else if("EGovernmentDevelopmentIndex".equals(indicator))
		  {
			  final List<EGovernmentDevelopmentIndexModel> model = getSaInternationalIndicesService().getEGovernmentDevelopmentIndexModel(indicator,startYear,endYear);
				 
				 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
						
				 for(final EGovernmentDevelopmentIndexModel saInternationalIndicesModel :  model)
				 {
					 final SAInternationalIndicesData eoData = geteGovernmentDevelopmentIndexConverter().convert(saInternationalIndicesModel);
					 saInternationalIndicesListData.add(eoData);
				 }
				 return saInternationalIndicesListData;
		  }
		  else if("GlobalCompetitivenessReport".equals(indicator))
		  {
			  final List<GlobalCompetitivenessReportModel> model = getSaInternationalIndicesService().getGlobalCompetitivenessReportModel(indicator,startYear,endYear);
				 
				 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
						
				 for(final GlobalCompetitivenessReportModel saInternationalIndicesModel :  model)
				 {
					 final SAInternationalIndicesData eoData = getGlobalCompetitivenessReportConverter().convert(saInternationalIndicesModel);
					 saInternationalIndicesListData.add(eoData);
				 }
				 return saInternationalIndicesListData;
		  }
		  else if("LegatumProsperityIndex".equals(indicator))
		  {
			  final List<LegatumProsperityIndexModel> model = getSaInternationalIndicesService().getLegatumProsperityIndexModel(indicator,startYear,endYear);
				 
				 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
						
				 for(final LegatumProsperityIndexModel saInternationalIndicesModel :  model)
				 {
					 final SAInternationalIndicesData eoData = getLegatumProsperityIndexConverter().convert(saInternationalIndicesModel);
					 saInternationalIndicesListData.add(eoData);
				 }
				 return saInternationalIndicesListData;
		  }
		  else if("EaseOfStartingBusiness".equals(indicator))
		  {
			  final List<EaseOfStartingBusinessModel> model = getSaInternationalIndicesService().getEaseOfStartingBusinessModel(indicator,startYear,endYear);
				 
				 final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<SAInternationalIndicesData>();
						
				 for(final EaseOfStartingBusinessModel saInternationalIndicesModel :  model)
				 {
					 final SAInternationalIndicesData eoData = getEaseOfStartingBusinessConverter().convert(saInternationalIndicesModel);
					 saInternationalIndicesListData.add(eoData);
				 }
				 return saInternationalIndicesListData;
		  }
		  else {
			  return null;
		  }
	  }


	/**
	 * @return the saInternationalIndicesService
	 */
	public SAInternationalIndicesService getSaInternationalIndicesService() {
		return saInternationalIndicesService;
	}


	/**
	 * @param saInternationalIndicesService the saInternationalIndicesService to set
	 */
	public void setSaInternationalIndicesService(SAInternationalIndicesService saInternationalIndicesService) {
		this.saInternationalIndicesService = saInternationalIndicesService;
	}


	/**
	 * @return the easeOfProtectingMinorityInvestorsConverter
	 */
	public Converter<EaseOfProtectingMinorityInvestorsModel, SAInternationalIndicesData> getEaseOfProtectingMinorityInvestorsConverter() {
		return easeOfProtectingMinorityInvestorsConverter;
	}


	/**
	 * @param easeOfProtectingMinorityInvestorsConverter the easeOfProtectingMinorityInvestorsConverter to set
	 */
	public void setEaseOfProtectingMinorityInvestorsConverter(
			Converter<EaseOfProtectingMinorityInvestorsModel, SAInternationalIndicesData> easeOfProtectingMinorityInvestorsConverter) {
		this.easeOfProtectingMinorityInvestorsConverter = easeOfProtectingMinorityInvestorsConverter;
	}


	/**
	 * @return the logisticsPerformanceIndexConverter
	 */
	public Converter<LogisticsPerformanceIndexModel, SAInternationalIndicesData> getLogisticsPerformanceIndexConverter() {
		return logisticsPerformanceIndexConverter;
	}


	/**
	 * @param logisticsPerformanceIndexConverter the logisticsPerformanceIndexConverter to set
	 */
	public void setLogisticsPerformanceIndexConverter(
			Converter<LogisticsPerformanceIndexModel, SAInternationalIndicesData> logisticsPerformanceIndexConverter) {
		this.logisticsPerformanceIndexConverter = logisticsPerformanceIndexConverter;
	}


	/**
	 * @return the economicFreedomIndexConverter
	 */
	public Converter<EconomicFreedomIndexModel, SAInternationalIndicesData> getEconomicFreedomIndexConverter() {
		return economicFreedomIndexConverter;
	}


	/**
	 * @param economicFreedomIndexConverter the economicFreedomIndexConverter to set
	 */
	public void setEconomicFreedomIndexConverter(
			Converter<EconomicFreedomIndexModel, SAInternationalIndicesData> economicFreedomIndexConverter) {
		this.economicFreedomIndexConverter = economicFreedomIndexConverter;
	}


	/**
	 * @return the globalInnovationIndexConverter
	 */
	public Converter<GlobalInnovationIndexModel, SAInternationalIndicesData> getGlobalInnovationIndexConverter() {
		return globalInnovationIndexConverter;
	}


	/**
	 * @param globalInnovationIndexConverter the globalInnovationIndexConverter to set
	 */
	public void setGlobalInnovationIndexConverter(
			Converter<GlobalInnovationIndexModel, SAInternationalIndicesData> globalInnovationIndexConverter) {
		this.globalInnovationIndexConverter = globalInnovationIndexConverter;
	}


	/**
	 * @return the eGovernmentDevelopmentIndexConverter
	 */
	public Converter<EGovernmentDevelopmentIndexModel, SAInternationalIndicesData> geteGovernmentDevelopmentIndexConverter() {
		return eGovernmentDevelopmentIndexConverter;
	}


	/**
	 * @param eGovernmentDevelopmentIndexConverter the eGovernmentDevelopmentIndexConverter to set
	 */
	public void seteGovernmentDevelopmentIndexConverter(
			Converter<EGovernmentDevelopmentIndexModel, SAInternationalIndicesData> eGovernmentDevelopmentIndexConverter) {
		this.eGovernmentDevelopmentIndexConverter = eGovernmentDevelopmentIndexConverter;
	}


	/**
	 * @return the globalCompetitivenessReportConverter
	 */
	public Converter<GlobalCompetitivenessReportModel, SAInternationalIndicesData> getGlobalCompetitivenessReportConverter() {
		return globalCompetitivenessReportConverter;
	}


	/**
	 * @param globalCompetitivenessReportConverter the globalCompetitivenessReportConverter to set
	 */
	public void setGlobalCompetitivenessReportConverter(
			Converter<GlobalCompetitivenessReportModel, SAInternationalIndicesData> globalCompetitivenessReportConverter) {
		this.globalCompetitivenessReportConverter = globalCompetitivenessReportConverter;
	}


	/**
	 * @return the legatumProsperityIndexConverter
	 */
	public Converter<LegatumProsperityIndexModel, SAInternationalIndicesData> getLegatumProsperityIndexConverter() {
		return legatumProsperityIndexConverter;
	}


	/**
	 * @param legatumProsperityIndexConverter the legatumProsperityIndexConverter to set
	 */
	public void setLegatumProsperityIndexConverter(
			Converter<LegatumProsperityIndexModel, SAInternationalIndicesData> legatumProsperityIndexConverter) {
		this.legatumProsperityIndexConverter = legatumProsperityIndexConverter;
	}


	/**
	 * @return the easeOfStartingBusinessConverter
	 */
	public Converter<EaseOfStartingBusinessModel, SAInternationalIndicesData> getEaseOfStartingBusinessConverter() {
		return easeOfStartingBusinessConverter;
	}


	/**
	 * @param easeOfStartingBusinessConverter the easeOfStartingBusinessConverter to set
	 */
	public void setEaseOfStartingBusinessConverter(
			Converter<EaseOfStartingBusinessModel, SAInternationalIndicesData> easeOfStartingBusinessConverter) {
		this.easeOfStartingBusinessConverter = easeOfStartingBusinessConverter;
	}
	
	


	
}
