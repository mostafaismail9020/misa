package com.sap.ibso.eservices.storefront.forms;

import com.sap.ibso.eservices.facades.data.BrandPresenceInMENARegion;
import com.sap.ibso.eservices.facades.data.EntitiesManagedByRhq;
import com.sap.ibso.eservices.facades.data.EstimatedOperatingCostForRhq;
import com.sap.ibso.eservices.facades.data.RhqActivities;

import java.io.Serializable;
import java.util.List;

public class SagiaApplyEntityInfoForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private String hasGCCNationality;
    private String hasAdvanceLicenseNr;
    private String advanceLicenseNumberInput;
    private String activity;
    private String isEntrepreneur;
    private String isMoreThan2Branch;
    private String boardResolutionFileName;
    private String letterOfSupportFileName;
    private String mainBranchCRFileName;
    private String otherBranchCR1FileName;
    private String otherBranchCR2FileName;

    private String entityListedInStockMarketFileName;
    private String entityAssetFileName;
    private String entityRevenueFileName;
    private String branchCR1FileName;
    private String branchCR2FileName;
    private String branchCR3FileName;
    private String branchCR4FileName;

    // New RHQ Requirememt Start
    private List<EntitiesManagedByRhq> listOfEntitiesManagedByRhq;
    private List<BrandPresenceInMENARegion> listOfBrandPresenceInMENARegion;
    private List<EstimatedOperatingCostForRhq> listOfEstimatedOperatingCostForRhq;
    private List<String> listOfCorporateActivities;
    private List<String> listOfStrategicActivities;
    private List<String> listOfManagementActivities;

    private List<String> rhqCenterAdmin;
    private List<String> rhqSubsidiaryPresence;
    private List<String> listOfRhqRegions;
    private List<String> listOfRhqCountries;
    private String rhqCurrentMarketValue;
    private String rhqAverage3YearRevenue;
    private String rhqLastYearAsset;
    private String rhqNumberOfEmployees;
    private String rhqCompanyRankedInFortune;
    private String rhqCurrentMarketValueFileName;
    private String rhqAverage3YearRevenueFileName;
    private String rhqLastYearAssetFileName;
    private String rhqNumberOfEmployeesFileName;


    private String entityFinancialStatementFileName;
    private String commercialRegMainEntryFileName;
    private String commercialRegBranch1FileName;
    private String commercialRegBranch2FileName;

    private String licenseYear;
    private String iSINCode;
    private String entityName;
    private String entityNameArabic;
    private String labourSize;
    private String legalStatus;
    private String basicInformationExtendedMultinationalCompany;
    private String capital;
    private String email;
    private String countryCodeForTelephone;
    private String telephone;
    private String countryCodeForMobilePhone;
    private String mobilePhone;
    private String website;
    private String region;
    private String city;
    private String address;
    private String poBox;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getHasGCCNationality() {
        return hasGCCNationality;
    }

    public void setHasGCCNationality(String hasGCCNationality) {
        this.hasGCCNationality = hasGCCNationality;
    }

    public String getHasAdvanceLicenseNr() {
        return hasAdvanceLicenseNr;
    }

    public void setHasAdvanceLicenseNr(String hasAdvanceLicenseNr) {
        this.hasAdvanceLicenseNr = hasAdvanceLicenseNr;
    }

    public String getAdvanceLicenseNumberInput() {
        return advanceLicenseNumberInput;
    }

    public void setAdvanceLicenseNumberInput(String advanceLicenseNumberInput) {
        this.advanceLicenseNumberInput = advanceLicenseNumberInput;
    }

    public String getIsEntrepreneur() {
        return isEntrepreneur;
    }

    public void setIsEntrepreneur(String isEntrepreneur) {
        this.isEntrepreneur = isEntrepreneur;
    }

    public String getBoardResolutionFileName() {
        return boardResolutionFileName;
    }

    public void setBoardResolutionFileName(String boardResolutionFileName) {
        this.boardResolutionFileName = boardResolutionFileName;
    }

    public String getLetterOfSupportFileName() {
        return letterOfSupportFileName;
    }

    public void setLetterOfSupportFileName(String letterOfSupportFileName) {
        this.letterOfSupportFileName = letterOfSupportFileName;
    }

    public String getLicenseYear() {
        return licenseYear;
    }

    public void setLicenseYear(String licenseYear) {
        this.licenseYear = licenseYear;
    }

    public String getiSINCode() {
        return iSINCode;
    }

    public void setiSINCode(String iSINCode) {
        this.iSINCode = iSINCode;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityNameArabic() {
        return entityNameArabic;
    }

    public void setEntityNameArabic(String entityNameArabic) {
        this.entityNameArabic = entityNameArabic;
    }

    public String getLabourSize() {
        return labourSize;
    }

    public void setLabourSize(String labourSize) {
        this.labourSize = labourSize;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getBasicInformationExtendedMultinationalCompany() {
        return basicInformationExtendedMultinationalCompany;
    }

    public void setBasicInformationExtendedMultinationalCompany(String basicInformationExtendedMultinationalCompany) {
        this.basicInformationExtendedMultinationalCompany = basicInformationExtendedMultinationalCompany;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryCodeForTelephone() {
        return countryCodeForTelephone;
    }

    public void setCountryCodeForTelephone(String countryCodeForTelephone) {
        this.countryCodeForTelephone = countryCodeForTelephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountryCodeForMobilePhone() {
        return countryCodeForMobilePhone;
    }

    public void setCountryCodeForMobilePhone(String countryCodeForMobilePhone) {
        this.countryCodeForMobilePhone = countryCodeForMobilePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getInvestment() {
        return investment;
    }

    public void setInvestment(String investment) {
        this.investment = investment;
    }

    private String postalCode;
    private String investment;
}
