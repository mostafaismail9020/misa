package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.customizinglist.CustomizingGetListFilterExpression;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.Map;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * CustomizationListService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class CustomizationListService  extends AbstractSagiaService<CustomizingGetData> {
	private static final String FILTER_BY_SCENARIO = "Scenario eq 'APPT'";
	private static final String ALLPAGES = "allpages";
	private static final String ATTACHMENT = "ATTACHMENT";
	private static final String ZRVR = "ZRVR";
	private static final String ZSR_5 = "ZSR5";
	private static final String ZSR_7 = "ZSR7";
	private static final String INFO = "INFO";
	private static final String ZSR_8 = "ZSR8";
	private static final String ZSR_9 = "ZSR9";
	private static final String ZSR_6 = "ZSR6";
	private static final String ZSR_4 = "ZSR4";
	private static final String TOP_VALUE = "100";
	private static final String TOP_VALUE_500 = "500";
	private static final String SKIP_VALUE = "0";
	private static final String BRANCH = "BRANCH";

	/**
	 * Gets a collection of items used to populate the appointment dropdown
	 * @return Collection of CustomizingGetData
	 */
    public final Collection<CustomizingGetData> getCollection(){
        return super.getCollection(CustomizingGetData.class, REQUEST_PARAMETER_FILTER, FILTER_BY_SCENARIO);
    }

	/**
	 * reads Branches
	 * @return Collection of CustomizingGetData
	 */
	public Collection<CustomizingGetData> readBranches() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(BRANCH)
				.scenario(ZRVR);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE_500)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
        return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * retrieves LicenseAmendmentListItems
	 * @return Collection of CustomizingGetData
	 */
	public Collection<CustomizingGetData> getLicenseAmendmentListItems() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
//				.fieldName("BRANCHTYPE") filter not working
				.scenario(ZSR_5);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE_500)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * reads readComplaintsSupportingAttachments
	 * @return supporting attachments list for Complaints
	 */
	public Collection<CustomizingGetData> readComplaintsSupportingAttachments() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(ATTACHMENT)
				.scenario(ZRVR);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE_500)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
        return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * read service info for Convert to Nationals screen
	 * @return Collection of CustomizingGetData
	 */
	public Collection<CustomizingGetData> readConvToNationalsInfo() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(INFO)
				.scenario(ZSR_7);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * reads readConvToNationalsSupportingAttachments
	 * @return ConvertToNationals supporting attachments
	 * they are stored on scenario ZSR7
	 */
	public Collection<CustomizingGetData> readConvToNationalsSupportingAttachments() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(ATTACHMENT)
				.scenario(ZSR_7);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
        return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * reads readLicenseReplacementInfo
	 * read service info for License Replacement screen
	 * @return Collection of CustomizingGetData
	 */
	public Collection<CustomizingGetData> readLicenseReplacementInfo() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(INFO)
				.scenario(ZSR_8);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * Reads Service Info for a specific service type (scenario)
	 * read service info for License Replacement screen
	 * @param scenario scenario
	 * @return Collection of CustomizingGetData
	 */
	public Collection<CustomizingGetData> getServiceInfo(String scenario) {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(INFO)
				.scenario(scenario);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * reads readLicenseReplacementSupportingAttachments
	 * @return License Replacement supporting attachments
	 * they are stored on scenario ZSR8
	 */
	public Collection<CustomizingGetData> readLicenseReplacementSupportingAttachments() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(ATTACHMENT)
				.scenario(ZSR_8);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * reads readClearSetAttachments
	 * @return ClearSet supporting attachments
	 * they are stored on scenario ZSR9
	 */
	public Collection<CustomizingGetData> readClearSetAttachments() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(ATTACHMENT)
				.scenario(ZSR_9);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}
	/**
	 * reads CancelLicenseSupportingAttachments
	 * @return CancelLicense supporting attachments
	 * they are stored on scenario ZSR6
	 */
	public Collection<CustomizingGetData> readCancelLicenseSupportingAttachments() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(ATTACHMENT)
				.scenario(ZSR_6);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}

	/**
	 * reads LicenseRenewalSupportingAttachments
	 * @return Collection of CustomizingGetData
	 */
	public Collection<CustomizingGetData> readLicenseRenewalSupportingAttachments() {
		CustomizingGetListFilterExpression filterExpression = new CustomizingGetListFilterExpression()
				.fieldName(ATTACHMENT)
				.scenario(ZSR_4);
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.skip(SKIP_VALUE)
				.top(TOP_VALUE)
				.filter(filterExpression.build())
				.inLineCount(ALLPAGES)
				.build();
		return getCollection(CustomizingGetData.class, queryOptions);
	}
}
