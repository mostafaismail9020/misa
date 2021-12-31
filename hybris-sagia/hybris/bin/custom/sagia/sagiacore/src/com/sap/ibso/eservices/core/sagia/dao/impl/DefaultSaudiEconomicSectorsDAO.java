package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.AvgMonthlyWagesModel;
import com.sap.ibso.eservices.core.model.ExternalSectorModel;
import com.sap.ibso.eservices.core.model.FiscalSectorModel;
import com.sap.ibso.eservices.core.model.GraduatesByDegreeModel;
import com.sap.ibso.eservices.core.model.GrowthrateWagesModel;
import com.sap.ibso.eservices.core.model.LabourPrivateSectorModel;
import com.sap.ibso.eservices.core.model.MonetarySectorModel;
import com.sap.ibso.eservices.core.model.NonSaudiEmploymentByRegionModel;
import com.sap.ibso.eservices.core.model.NonSaudiEmploymentModel;
import com.sap.ibso.eservices.core.model.NonSaudiUnemploymentModel;
import com.sap.ibso.eservices.core.model.OverallUnemploymentModel;
import com.sap.ibso.eservices.core.model.PopulationByRegionModel;
import com.sap.ibso.eservices.core.model.RealSectorModel;
import com.sap.ibso.eservices.core.model.SaudiEmploymentByRegionModel;
import com.sap.ibso.eservices.core.model.SaudiEmploymentModel;
import com.sap.ibso.eservices.core.model.SaudiPopulationModel;
import com.sap.ibso.eservices.core.model.SaudiUnemploymentModel;
import com.sap.ibso.eservices.core.sagia.dao.SaudiEconomicSectorsDAO;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultSaudiEconomicSectorsDAO implements SaudiEconomicSectorsDAO {

	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<RealSectorModel> getRealSectorModelBySearch() {
		final String queryString = "SELECT {" + RealSectorModel.PK + "} FROM {" + RealSectorModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<RealSectorModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<MonetarySectorModel> getMonetarySectorModelBySearch() {
		final String queryString = "SELECT {" + MonetarySectorModel.PK + "} FROM {" + MonetarySectorModel._TYPECODE
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<MonetarySectorModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<FiscalSectorModel> getFiscalSectorModelBySearch() {
		final String queryString = "SELECT {" + FiscalSectorModel.PK + "} FROM {" + FiscalSectorModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<FiscalSectorModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<ExternalSectorModel> getExternalSectorModelBySearch() {
		final String queryString = "SELECT {" + ExternalSectorModel.PK + "} FROM {" + ExternalSectorModel._TYPECODE
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<ExternalSectorModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<GraduatesByDegreeModel> getGraduatesByDegreeModelBySearch(int year) {
		final String queryString = "SELECT {" + GraduatesByDegreeModel.PK + "} FROM {"
				+ GraduatesByDegreeModel._TYPECODE + "}" + " WHERE {" + GraduatesByDegreeModel.YEAR + "}='" + year
				+ "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GraduatesByDegreeModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<SaudiPopulationModel> getSaudiPopulationModelBySearch(int year) {
		final String queryString = "SELECT {" + SaudiPopulationModel.PK + "} FROM {" + SaudiPopulationModel._TYPECODE
				+ "}" + " WHERE {" + SaudiPopulationModel.YEAR + "}='" + year + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<SaudiPopulationModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<PopulationByRegionModel> getPopulationByRegionModelBySearch(int year) {
		final String queryString = "SELECT {" + PopulationByRegionModel.PK + "} FROM {"
				+ PopulationByRegionModel._TYPECODE + "}" + " WHERE {" + PopulationByRegionModel.YEAR + "}='" + year
				+ "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<PopulationByRegionModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<SaudiEmploymentByRegionModel> getSaudiEmploymentByRegionModelBySearch(int year) {
		final String queryString = "SELECT {" + SaudiEmploymentByRegionModel.PK + "} FROM {"
				+ SaudiEmploymentByRegionModel._TYPECODE + "}" + " WHERE {" + SaudiEmploymentByRegionModel.YEAR + "}='"
				+ year + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<SaudiEmploymentByRegionModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<NonSaudiEmploymentByRegionModel> getNonSaudiEmploymentByRegionModelBySearch(int year) {
		final String queryString = "SELECT {" + NonSaudiEmploymentByRegionModel.PK + "} FROM {"
				+ NonSaudiEmploymentByRegionModel._TYPECODE + "}" + " WHERE {" + NonSaudiEmploymentByRegionModel.YEAR
				+ "}='" + year + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<NonSaudiEmploymentByRegionModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<SaudiEmploymentModel> getSaudiEmploymentModelBySearch(int year) {
		final String queryString = "SELECT {" + SaudiEmploymentModel.PK + "} FROM {" + SaudiEmploymentModel._TYPECODE
				+ "}" + " WHERE {" + SaudiEmploymentModel.YEAR + "}='" + year + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<SaudiEmploymentModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<NonSaudiEmploymentModel> getNonSaudiEmploymentModelBySearch(int year) {
		final String queryString = "SELECT {" + NonSaudiEmploymentModel.PK + "} FROM {"
				+ NonSaudiEmploymentModel._TYPECODE + "}" + " WHERE {" + NonSaudiEmploymentModel.YEAR + "}='" + year
				+ "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<NonSaudiEmploymentModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<AvgMonthlyWagesModel> getAvgMonthlyWagesModelBySearch(int year) {
		final String queryString = "SELECT {" + AvgMonthlyWagesModel.PK + "} FROM {" + AvgMonthlyWagesModel._TYPECODE
				+ "}" + " WHERE {" + AvgMonthlyWagesModel.YEAR + "}='" + year + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<AvgMonthlyWagesModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<GrowthrateWagesModel> getGrowthrateWagesModelBySearch(int year) {
		final String queryString = "SELECT {" + GrowthrateWagesModel.PK + "} FROM {" + GrowthrateWagesModel._TYPECODE
				+ "}" + " WHERE {" + GrowthrateWagesModel.YEAR + "}='" + year + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GrowthrateWagesModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<LabourPrivateSectorModel> getLabourPrivateSectorModelBySearch(int year) {
		final String queryString = "SELECT {" + LabourPrivateSectorModel.PK + "} FROM {"
				+ LabourPrivateSectorModel._TYPECODE + "}" + " WHERE {" + LabourPrivateSectorModel.YEAR + "}='" + year
				+ "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<LabourPrivateSectorModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<SaudiUnemploymentModel> getSaudiUnemploymentModelBySearch(int year) {
		final String queryString = "SELECT {" + SaudiUnemploymentModel.PK + "} FROM {"
				+ SaudiUnemploymentModel._TYPECODE + "}" + " WHERE {" + SaudiUnemploymentModel.YEAR + "}IN('Q1-" + year
				+ "','Q2-" + year + "','Q3-" + year + "','Q4-" + year + "')" + "ORDER BY" + "{"
				+ SaudiUnemploymentModel.YEAR + "}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<SaudiUnemploymentModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<NonSaudiUnemploymentModel> getNonSaudiUnemploymentModelBySearch(int year) {
		final String queryString = "SELECT {" + NonSaudiUnemploymentModel.PK + "} FROM {"
				+ NonSaudiUnemploymentModel._TYPECODE + "}" + " WHERE {" + NonSaudiUnemploymentModel.YEAR + "}IN('Q1-"
				+ year + "','Q2-" + year + "','Q3-" + year + "','Q4-" + year + "')" + "ORDER BY" + "{"
				+ NonSaudiUnemploymentModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<NonSaudiUnemploymentModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<OverallUnemploymentModel> getOverallUnemploymentModelBySearch(int year) {
		final String queryString = "SELECT {" + OverallUnemploymentModel.PK + "} FROM {"
				+ OverallUnemploymentModel._TYPECODE + "}" + " WHERE {" + OverallUnemploymentModel.YEAR + "}IN('Q1-"
				+ year + "','Q2-" + year + "','Q3-" + year + "','Q4-" + year + "')" + "ORDER BY" + "{"
				+ OverallUnemploymentModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<OverallUnemploymentModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
	

}
