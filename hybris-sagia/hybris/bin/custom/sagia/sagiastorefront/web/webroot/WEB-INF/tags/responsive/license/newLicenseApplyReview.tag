<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="controllerUrl" required="false" type="java.lang.String"%>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsApplyModal.tag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>

<script>
    var sagiaData = {};
    sagiaData.qeemah = 1;
    sagiaData.isTermsAndConditionsChecked = false;
    sagiaData.businessActivities = {};
    sagiaData.qeemahChannel = "";
    sagiaData.businessActivities.selectedActivities = [];

    <c:if test="${not empty entityInformation}">
        sagiaData.licenseType = "${entityInformation.licenseType}";
        sagiaData.isEntrepreneur = ${entityInformation.isEntrepreneur};
        sagiaData.isMoreThan2Branch = ${entityInformation.isMoreThan2Branch};
        sagiaData.licenseYear = "${entityInformation.licenseDuration}";
        sagiaData.basicInformationExtended = {
            address: "${entityInformation.address}",
            capital: "${entityInformation.capital}",
            city: "${entityInformation.city}",
            cityText: "${entityInformation.cityText}",
            country: "${entityInformation.country}",
            countryCodeForMobilePhone: "${entityInformation.countryCodeForMobilePhone}",
            countryCodeForTelephone: "${entityInformation.countryCodeForTelephone}",
            countryName: "${entityInformation.countryText}",
            email: "${entityInformation.email}",
            entityName: "${entityInformation.entityName}",
            investment: "${entityInformation.investment}",
            investmentText: "${entityInformation.investment}",
            legalStatus: "${entityInformation.legalStatus}",
            legalStatusText: "${entityInformation.legalStatusText}",
            mobilePhone: "${entityInformation.mobilePhone}",
            multinationalCompany: "${entityInformation.basicInfoExtendedMultinationalCompany}",
            multinationalCompanyText: "${entityInformation.basicInfoExtendedMultinationalCompany}",
            poBox: "${entityInformation.poBox}",
            postalCode: "${entityInformation.postalCode}",
            region: "${entityInformation.region}",
            regionText: "${entityInformation.regionText}",
            telephone: "${entityInformation.telephone}",
            website: "${entityInformation.website}"
        };
    </c:if>

    <c:if test="${not empty selectedISICDetails}">
        <c:forEach var="isicDetails" items="${selectedISICDetails['activities']}">
        	<c:set var="newLine" value="<%= \"\n\" %>" />
            <c:set var="formattedDescription" value="${isicDetails.activityId} - ${fn:replace(isicDetails.description,newLine,' ')}"/>
            sagiaData.businessActivities.selectedActivities.push({
                activityId: "${isicDetails.activityId}",
                activityIdName: "${formattedDescription}",
                branchId: "${isicDetails.branchId}",
                branchIdName: "${formattedDescription}",
                classId: "${isicDetails.classId}",
                classIdName: "${formattedDescription}",
                descriptionName: "${formattedDescription}",
                divisionId: "${isicDetails.divisionId}",
                divisionIdName: "${formattedDescription}",
                groupId: "${isicDetails.groupId}",
                groupIdName: "${formattedDescription}",
                qeemahChannel: "${isicDetails.qeemahChannel}",
                qeemahChannelName: "${formattedDescription}",
                sectionId: "${isicDetails.sectionId}",
                sectionIdName: "${formattedDescription}",
                splrequirementId: "${isicDetails.splRequirementId}",
                splrequirementIdName: "${formattedDescription}"
            });
        </c:forEach>
    </c:if>

    <c:if test="${not empty contactPerson}">
        sagiaData.qeemah1Data = {};
        sagiaData.qeemah1Data.contactPerson = {
            address: "${contactPerson.address}",
            city: "${contactPerson.city}",
            country: "${contactPerson.country}",
            countryCodeForMobileNumber: "${contactPerson.countryCodeMobile}",
            countryCodeForTelephone: "${contactPerson.countryCodeTelephone}",
            countryText: "${contactPerson.countryText}",
            dateOfBirth: "${contactPerson.dateOfBirth}",
            education: "${contactPerson.education}",
            educationText: "${contactPerson.education}",
            email: "${contactPerson.email}",
            firstName: "${contactPerson.firstName}",
            lastName: "${contactPerson.lastName}",
            mobileNumber: "${contactPerson.mobileNumber}",
            passportExpiryDate: "${contactPerson.passportExpiryDate}",
            passportIssueDate: "${contactPerson.passportIssueDate}",
            passportNumber: "${contactPerson.passportNumber}",
            poBox: "${contactPerson.poBox}",
            postalCode: "${contactPerson.postalCode}",
            role: "${contactPerson.role}",
            roleText: "${contactPerson.role}",
            telephone: "${contactPerson.telephoneNumber}",
            title: "${contactPerson.title}"
        };
    </c:if>

    sagiaData.qeemah1Data.existingShareholder = [];
    sagiaData.qeemah1Data.newShareholder = [];
    var capitalizeString = '';

    <c:if test="${not empty shareholdersDataByLicense}">
        <c:forEach var="shareholder" items="${shareholdersDataByLicense}">
            <c:if test="${shareholder.shareHolderType eq 'existing'}">
                sagiaData.qeemah1Data.existingShareholder.push({
                    commercialRegistrationFile: "",
                    commercialRegistrationFileMimeType: "${shareholder.commercialRegistration.mime}",
                    commercialRegistrationFileName: "${shareholder.commercialRegistration.fileName}",
                    entityNumber: "${shareholder.shareHolderEntityNumber}",
                    hasDelegateInfo: false,
                    id: "${shareholder.code}",
                    lastBudgetFile: "",
                    lastBudgetFileMimeType: "${shareholder.lastBudget.mime}",
                    lastBudgetFileName: "${shareholder.lastBudget.fileName}",
                    name: "${shareholder.shareHolderName}",
                    parentCompanyCountry: "${shareholder.parentCompanyCountry}",
                    parentCompanyCountryText: "${shareholder.nationality}",
                    sharesPercentage: "${shareholder.sharesPercentage}"
                });
            </c:if>

            <c:if test="${shareholder.shareHolderType eq 'person' || shareholder.shareHolderType eq 'organization'}">
                delegateInfo = {
                    authorizationLetterFile: "",
                    authorizationLetterFileMimeType: "${shareholder.delegateInfo.authorisationLetter.mime}",
                    authorizationLetterFileName: "${shareholder.delegateInfo.authorisationLetter.fileName}",
                    country: "${shareholder.delegateInfo.delegateCountry}",
                    countryCodeForMobile: "${shareholder.delegateInfo.delegateCountryCodeMobile}",
                    countryCodeForTelephone: "${shareholder.delegateInfo.delegateCountryCodeTel}",
                    countryText: "${shareholder.delegateInfo.delegateCountry}",
                    dateofBirth: "${shareholder.delegateInfo.delegateDateOfBirth}",
                    email: "${shareholder.delegateInfo.delegateEmail}",
                    expiryDate: "${shareholder.delegateInfo.idExpiryDate}",
                    firstNameArabic: "${shareholder.delegateInfo.delegateFirstNameArabic}",
                    fullNameEnglish: "${shareholder.delegateInfo.delegateFullName}",
                    gender: "${shareholder.delegateInfo.gender}",
                    idCopyFileName: "${shareholder.delegateInfo.saudiIdCopy.fileName}",
                    idNumber: "${shareholder.delegateInfo.delegateIdentityNumber}",
                    idType: "${shareholder.delegateInfo.delegateIdentityType}",
                    issueDate: "${shareholder.delegateInfo.idIssueDate}",
                    lastNameArabic: "${shareholder.delegateInfo.delegateLastNameArabic}",
                    mobile: "${shareholder.delegateInfo.delegateMobileNumber}",
                    nationality: "${shareholder.delegateInfo.delegateNationality}",
                    nationalityText: "${shareholder.delegateInfo.delegateNationality}",
                    nicVerified: "${shareholder.delegateInfo.nicVerified}",
                    poBox: "${shareholder.delegateInfo.delegatePoBox}",
                    postalCode: "${shareholder.delegateInfo.delegatePostalCode}",
                    telephone: "${shareholder.delegateInfo.delegateTelephoneNumber}"
                };
            </c:if>
            <c:if test="${shareholder.shareHolderType eq 'person'}">
                capitalizeString = "${shareholder.shareHolderType}";
                sagiaData.qeemah1Data.newShareholder.push({
                    type: capitalizeString.charAt(0).toUpperCase() + capitalizeString.slice(1),
                    academicTitle: "${shareholder.academicTitle}",
                    city: "${shareholder.city}",
                    country: "${shareholder.country}",
                    countryCodeForMobile: "${shareholder.countryCodeMobile}",
                    countryCodeForTelephone: "${shareholder.countryCodeTelephone}",
                    dateOfBirth: "${shareholder.dateOfBirth}",
                    email: "${shareholder.email}",
                    firstNameArabic: "${shareholder.firstNameArabic}",
                    fullNameEnglish: "${shareholder.fullName}",
                    hasDelegateInfo: "${shareholder.fullName}",
                    id: "${shareholder.code}",
                    lastNameArabic: "${shareholder.lastNameArabic}",
                    mobile: "${shareholder.mobileNumber}",
                    otherFile: "",
                    otherFileMimeType: "${shareholder.other.mime}",
                    otherFileName: "${shareholder.other.fileName}",
                    passportExpiryDate: "${shareholder.passportExpiryDate}",
                    passportFile: "",
                    passportFileName: "${shareholder.passportIdCopy.fileName}",
                    passportIssueDate: "${shareholder.passportIssueDate}",
                    passportNumber: "${shareholder.passportNumber}",
                    poBox: "${shareholder.poBox}",
                    postalCode: "${shareholder.postalCode}",
                    premiumResident: "${shareholder.premiumResident eq true ? "yes" : "no"}",
                    previousNationality: "${shareholder.previousNationality}",
                    selfDelegate: "${shareholder.delegateInfo.delegateYourself}",
                    sharesPercentage: "${shareholder.sharesPercentage}",
                    telephone: "${shareholder.telephoneNumber}",
                    title: "${shareholder.shareHolderTitle}",
                    currentNationality: {
                        text: "${shareholder.nationality}",
                        val: "${shareholder.currentNationality}",
                    },
                    delegate: delegateInfo
                });
            </c:if>
            <c:if test="${shareholder.shareHolderType eq 'organization'}">
                capitalizeString = "${shareholder.shareHolderType}";
                sagiaData.qeemah1Data.newShareholder.push({
                    type: capitalizeString.charAt(0).toUpperCase() + capitalizeString.slice(1),
                    companyAddress: "${shareholder.organizationName}",
                    companyCapital: "${shareholder.capital}",
                    companyCity: "${shareholder.city}",
                    companyCountry: "${shareholder.companyCountry}",
                    companyCountryCodeForMobile: "${shareholder.countryCodeMobile}",
                    companyCountryCodeForTelephone: "${shareholder.countryCodeTelephone}",
                    companyCountryOfRegistration: "${shareholder.countryOfReg}",
                    companyCountryText: "${shareholder.companyCountry}",
                    companyDivision: "${shareholder.division}",
                    companyEmail: "${shareholder.email}",
                    companyFinancialStatementFile: "",
                    companyFinancialStatementFileMimeType: "${shareholder.lastYearFinStatement.mime}",
                    companyFinancialStatementFileName: "${shareholder.lastYearFinStatement.fileName}",
                    companyMemoAssociationFileName: "",
                    companyMobile: "${shareholder.mobileNumber}",
                    companyPOBox: "${shareholder.poBox}",
                    companyPostalCode: "${shareholder.postalCode}",
                    companyRegistrationFile: "",
                    companyRegistrationFileMimeType: "${shareholder.commercialRegCopy.mime}",
                    companyRegistrationFileName: "${shareholder.commercialRegCopy.fileName}",
                    companyRegistrationNumber: "${shareholder.companyRegNumber}",
                    companySection: "${shareholder.section}",
                    companySharesPercentage: "${shareholder.sharesPercentage}",
                    companyTelephone: "${shareholder.telephoneNumber}",
                    companyWebsite: "${shareholder.website}",
                    hasDelegateInfo: "${not empty shareholder.delegateInfo}",
                    id: "${shareholder.code}",
                    multinationalCompany: "${shareholder.multinationalCompany}",
                    multinationalCompanyText: "${shareholder.multinationalCompany eq 1 ? 'yes' : 'no'}",
                    organizationLegalStatus: "${shareholder.legalStatus}",
                    organizationLegalStatusText: "${shareholder.legalStatus}",
                    organizationNameArabic: "${shareholder.organizationNameArabic}",
                    organizationNameEnglish: "${shareholder.organizationName}",
                    parentCompanyCountry: "${shareholder.parentCompanyCountry}",
                    parentCompanyName: "${shareholder.parentCompanyName}",
                    selfDelegate: "${shareholder.delegateInfo.delegateYourself}",
                    delegate: delegateInfo
                });
            </c:if>
        </c:forEach>
    </c:if>


</script>

<div class="panelTabs-head" id="reviewTab">
    <icon:review/><span class="panelTabs-label"><spring:theme code="license.apply.review"/></span>
</div>
<div class="panelTabs-body" id="reviewContent">
    <div class="contentModule">
        <div class="contentModule-actions contentModule-actions_right">
            <button id="printButton" class="btn btn_outline btn_round btn_slim"><spring:theme code="general.print"/>
                <span class="iconElement iconElement_print"><icon:print/></span>
            </button>
        </div>

        <div class="contentModule-section contentModule-section_noDivider">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="licenseApplyEntityInformation.entityInformation.title"/></div>
                <c:url value="/my-sagia/license/entity" var="entityInfoLink"/>
                <a id="editEntityInformationButton" type="button" class="btn btn_link iconElement iconElement_edit03" href="${entityInfoLink}"><icon:edit/></a>
            </div>

            <div id="reviewLicenseTypeSection">
                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.review.licence.type"/></dt>
                            <dd>${entityInformation.licenseTypeText}</dd>
                            <input type="hidden" value="entityInformation.licenseType" id="licenseType" />
                        </dl>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div id="reviewLicenseYearSection">
                            <dl class="dlList">
                                <dt><spring:theme code="licenseApplyEntityInformation.licenseYearSection.title"/></dt>
                                        <dd><spring:theme code="license.entity.year.${entityInformation.licenseDuration}"/></dd>
                        </dl>
                        </div>
                    </div>
                </div>


            </div>

            <div id="reviewAdvanceLicenseNrSection" style="display: ${entityInformation.hasAdvanceLicenseNr?'block':'none'};">
                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.text"/></dt>
                            <dd>${entityInformation.advanceLicenseNr}</dd>
                        </dl>
                    </div>
                </div>
                <div class="col-md-4">
                        <div id="reviewAdvanceLicenseNrSection" style="display: ${entityInformation.hasAdvanceLicenseNr?'block':'none'};">
                            <dl class="dlList">
                                        <dt><spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.text"/></dt>
                                        <dd>${entityInformation.advanceLicenseNr}</dd>
                            </dl>
                        </div>
                </div>
            </div>
            <c:if test="${entityInformation.licenseType eq '11'}">
            <div id="rhq-review-section">
            <div id="reviewLicenseYearSection" class="rhq-review-items">
                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="review.rhq.investor.activities.corporate.label"/></dt>
                            <c:forEach items="${selectedListOfCorporateActivities}" var="corporateActivity">
                                	<dd class="rhqListItems">${corporateActivity}</dd>
                            </c:forEach>
                       </dl>
                    </div>
                     <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="review.rhq.investor.activities.strategic.label"/></dt>
                        <c:forEach items="${selectedListOfStrategicActivities}" var="strategicActivity">
                                <dd class="rhqListItems">${strategicActivity}</dd>
                        </c:forEach>
                       </dl>
                    </div>
                     <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="review.rhq.investor.activities.management.label"/></dt>
                                <c:forEach items="${selectedListOfManagementActivities}" var="managementActivity">
                                        <dd class="rhqListItems">${managementActivity}</dd>
                                </c:forEach>
                       </dl>
                    </div>
                     <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="review.rhq.investor.activities.center.of.administrative.label"/></dt>
                            <c:forEach items="${entityInformation.rhqCenterAdmin}" var="centerOfAdmin">
                                <dd class="rhqListItems centre-of-admin" >${centerOfAdmin}</dd>
                            </c:forEach>
                       </dl>
                    </div>
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="review.profile.rhq.countries"/></dt>
                                <c:forEach items="${selectedListOfCountries}" var="selectedCountries">
                                        <dd class="rhqListItems">${selectedCountries}</dd>
                                </c:forEach>
                       </dl>
                    </div>
                     <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="review.profile.rhq.regions"/></dt>
                        <c:forEach items="${selectedListOfRegions}" var="selectedRegions">
                                <dd class="rhqListItems">${selectedRegions}</dd>
                        </c:forEach>
                       </dl>
                    </div>
                     <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="review.rhq.mnc.subsidiaries.presence"/></dt>
                            <c:forEach items="${entityInformation.rhqSubsidiaryPresence}" var="subsidiary">
                                    <dd class="rhqListItems"        id="rhqSubsidiaryPresenceDiv">${subsidiary}</dd>
                            </c:forEach>
                       </dl>
                    </div>
                </div>
                 <hr class="contentModule-separator"/>
            </div>


            <div class="contentModule-section contentModule-section_noDivider" id="reviewMncBranchSection">
            <div class=" contentModule-actions_spaceBetween contentModule-actions_wrap ">
                <dl class="dlList""> <dt><spring:theme code="review.rhq.mnc.branches.label"/></dt></dl>
            </div>

            <div class="tableModule">
                <table class="tableModule-table" id="reviewRHQBranchesTable">
                    <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="review.rhq.company.name.label"/></th>
                        <th><spring:theme code="review.rhq.country.label"/></th>
                        <th><spring:theme code="review.rhq.business.label"/></th>
                        <th><spring:theme code="review.rhq.industry.label"/></th>
                        <th><spring:theme code="review.rhq.operations.label"/></th>
                        <th><spring:theme code="review.rhq.rhq.activity.label"/></th>

                    </tr>
                    </thead>
                    <tbody class="tableModule-body">
                    </tbody>
                </table>
            </div>
        </div>



           <div class="contentModule-section contentModule-section_noDivider" id="reviewMncBranchSection">
            <div class=" contentModule-actions_spaceBetween contentModule-actions_wrap ">
                <dl class="dlList""> <dt><spring:theme code="review.rhq.mnc.brand.label"/></dt></dl>
            </div>

            <div class="tableModule">
                <table class="tableModule-table" id="reviewMncBrandTable">
                    <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="review.rhq.brand.name.label"/></th>
                        <th><spring:theme code="review.rhq.country.label"/></th>
                        <th><spring:theme code="review.rhq.industry.label"/></th>
                        <th><spring:theme code="review.rhq.company.brand.in.mena.region.label"/></th>
                        <th><spring:theme code="review.rhq.rhq.activity.label"/></th>


                    </tr>
                    </thead>
                    <tbody class="tableModule-body">

                    </tbody>
                </table>
            </div>
        </div>


           <div class=" contentModule-section_noDivider" id="reviewMncBranchSection">
            <div class=" contentModule-actions_spaceBetween contentModule-actions_wrap ">
                <dl class="dlList""> <dt><spring:theme code="review.rhq.estimated.cost.label"/></dt></dl>
            </div>

            <div class="tableModule">
                <table class="tableModule-table" id="reviewMncCostTable" style="margin-bottom: 48px;">
                    <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="review.rhq.item.name.label"/></th>
                        <th><spring:theme code="review.rhq.unit.cost.label"/></th>
                        <th><spring:theme code="review.rhq.no.of.units.label"/></th>
                        <th><spring:theme code="review.rhq.cost.frequency.label"/></th>
                        <th><spring:theme code="review.rhq.year.2022.label"/></th>
                        <th><spring:theme code="review.rhq.year.2023.label"/></th>
                        <th><spring:theme code="review.rhq.year.2024.label"/></th>
                    </tr>
                    </thead>
                    <tbody class="tableModule-body">

                    </tbody>
                    <tfoot>
                        <tr>
                        <td style=""></td>
                        <td style=""></td>
                        <td style=""></td>
                        <td class="sum-row" id="rhqCostTable-totalText"></td>
                        <td class="sum-row" id="rhqCostTable-sum1"></td>
	                    <td class="sum-row" id="rhqCostTable-sum2"></td>
	                    <td class="sum-row" id="rhqCostTable-sum3"></td>

                        </tr>
                     </tfoot>
                </table>
                <hr class="contentModule-separator">
            </div>
        </div>
		</c:if>

            <div id="reviewBasicInformationExtendedSection">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.entity.name"/></dt>
                            <dd id="reviewBasicInformationExtendedEntityName">${entityInformation.entityName}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.entity.name.arabic"/></dt>
                            <dd id="reviewBasicInformationExtendedEntityNameInArabic">${entityInformation.entityNameArabic}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.legalstatus"/></dt>
                            <dd id="reviewBasicInformationExtendedStatus">${entityInformation.legalStatusText}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="license.multinational"/></dt>
                            <dd id="reviewBasicInformationExtendedMultinationalCompany">
                                <c:if test = "${entityInformation.basicInfoExtendedMultinationalCompany eq 1}">
                                    <spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.yes"/>
                                </c:if>
                                <c:if test = "${entityInformation.basicInfoExtendedMultinationalCompany eq 2}">
                                   <spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.no"/>
                                </c:if>
                            </dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.capital"/></dt>
                            <dd id="reviewBasicInformationExtendedCapital">${entityInformation.capital} SAR</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.email"/></dt>
                            <dd id="reviewBasicInformationExtendedEmail">${entityInformation.email}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.telephone"/></dt>
                            <dd id="reviewBasicInformationExtendedTelephone">${entityInformation.countryCodeForTelephone} ${entityInformation.telephone}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.mobilenumber"/></dt>
                            <dd id="reviewBasicInformationExtendedMobilePhone">${entityInformation.countryCodeForMobilePhone} ${entityInformation.mobilePhone}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="licence.apply.website"/></dt>
                            <dd id="reviewBasicInformationExtendedWebsite">${entityInformation.website}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.country"/></dt>
                            <dd id="reviewBasicInformationExtendedCountry">${entityInformation.countryText}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="licence.apply.region"/></dt>
                            <dd id="reviewBasicInformationExtendedRegion">${entityInformation.regionText}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.city"/></dt>
                            <dd id="reviewBasicInformationExtendedCity">${entityInformation.cityText}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.address"/></dt>
                            <dd id="reviewBasicInformationExtendedAddress">${entityInformation.address}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.postalcode"/></dt>
                            <dd id="reviewBasicInformationExtendedPostalCode">${entityInformation.postalCode}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.pobox"/></dt>
                            <dd id="reviewBasicInformationExtendedPoBox">${entityInformation.poBox}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList">
                            <dt><spring:theme code="general.expectedinvestment"/></dt>
                            <dd id="reviewBasicInformationExtendedInvestment"><spring:theme code="license.apply.expectedinvestment.${entityInformation.investment}"/></dd>
                        </dl>
                    </div>
                </div>

                <hr class="contentModule-separator"/>
            </div>
            <c:choose>
                <c:when test="${entityInformation.licenseType eq '6'}">
                    <div id="temporaryLicenseTextBoxSection" style="">
                        <div class="row">
                            <div class="col-12">
                                <dl class="dlList">
                                    <dt>
                                        <spring:theme code="license.apply.business.activities.temporary" />
                                    </dt>
                                    <dd>
                                        </br>
                                        <p class="white-space-pre">
                                            ${entityInformation.temporaryLicenseText}
                                        </p>
                                    </dd>
                                </dl>
                            </div>
                        </div>

                        <hr class="contentModule-separator" />
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="reviewISICSection" style="display: ${not empty entityInformation.isicActivities?'block':'none'};">
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <dl class="dlList" id="reviewSection">
                                    <dt><spring:theme code="license.apply.review.isicSection"/></dt>
                                    <c:if test="${not empty selectedISICDetails['sections']}">
                                        <c:forEach var="hash" items="${selectedISICDetails['sections']}">
                                            <dd><c:out value="${hash.sectionId}"/> - <c:out value="${hash.description}"/></dd>
                                        </c:forEach>
                                    </c:if>
                                </dl>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <dl class="dlList" id="reviewDivision">
                                    <dt><spring:theme code="license.apply.review.isicDivision"/></dt>
                                    <c:if test="${not empty selectedISICDetails['divisions']}">
                                        <c:forEach var="hash" items="${selectedISICDetails['divisions']}">
                                            <dd><c:out value="${hash.divisionId}"/> - <c:out value="${hash.description}"/></dd>
                                        </c:forEach>
                                    </c:if>
                                </dl>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <dl class="dlList" id="reviewGroup">
                                    <dt><spring:theme code="license.apply.review.isicGroup"/></dt>
                                    <c:if test="${not empty selectedISICDetails['groups']}">
                                        <c:forEach var="hash" items="${selectedISICDetails['groups']}">
                                            <dd><c:out value="${hash.groupId}"/> - <c:out value="${hash.description}"/></dd>
                                        </c:forEach>
                                    </c:if>
                                </dl>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <dl class="dlList" id="reviewClass">
                                    <dt><spring:theme code="license.apply.review.isicClass"/></dt>
                                    <c:if test="${not empty selectedISICDetails['classes']}">
                                        <c:forEach var="hash" items="${selectedISICDetails['classes']}">
                                            <dd><c:out value="${hash.classId}"/> - <c:out value="${hash.description}"/></dd>
                                        </c:forEach>
                                    </c:if>
                                </dl>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <dl class="dlList" id="reviewBranch">
                                    <dt><spring:theme code="license.apply.review.isicBranch"/></dt>
                                    <c:if test="${not empty selectedISICDetails['branches']}">
                                        <c:forEach var="hash" items="${selectedISICDetails['branches']}">
                                            <dd><c:out value="${hash.branchId}"/> - <c:out value="${hash.description}"/></dd>
                                        </c:forEach>
                                    </c:if>
                                </dl>
                            </div>
                             <div class="col-sm-6 col-md-4">
                                <dl class="dlList" id="reviewActivities">
                                    <dt><spring:theme code="license.apply.business.activities"/></dt>
                                    <c:if test="${not empty selectedISICDetails['activities']}">
                                        <c:forEach var="hash" items="${selectedISICDetails['activities']}">
                                            <dd><c:out value="${hash.activityId}"/> - <c:out value="${hash.description}"/></dd>
                                        </c:forEach>
                                    </c:if>
                                </dl>
                            </div>
                        </div>

                        <hr class="contentModule-separator"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="contentModule-section contentModule-section_noDivider" id="reviewShareholdersSection">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="license.apply.review.shareholders"/></div>
                <c:url value="/my-sagia/license/shareholders" var="shareHolderLink"/>
                <a id="editShareholdersButton" type="button" class="btn btn_link iconElement iconElement_edit03" href="${shareHolderLink}"><icon:edit/>
                </a>
            </div>

            <div class="tableModule">
                <table class="tableModule-table">
                    <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="license.apply.review.shareholderName"/></th>
                        <th><spring:theme code="license.apply.review.type"/></th>
                        <th><spring:theme code="license.apply.percentage"/></th>
                        <th><spring:theme code="license.apply.review.nationality"/></th>
                        <th><spring:theme code="license.apply.review.legal.status"/></th>
                        <th><spring:theme code="license.apply.review.delegate"/></th>
                        <th><spring:theme code="license.apply.review.delegateId"/></th>
                    </tr>
                    </thead>
                    <tbody class="tableModule-body">
                    <c:if test="${not empty shareholdersDataByLicense}">
                        <c:forEach items="${shareholdersDataByLicense}" var="shareholder">
                            <tr>
                                <td>${shareholder.mainName}</td>
                                <td style="text-transform: capitalize">${shareholder.shareHolderType}</td>
                                <td>${shareholder.sharesPercentage}%</td>
                                <td>${shareholder.nationality}</td>
                                <td>${not empty shareholder.mainLegalStatus ? shareholder.mainLegalStatus : "-"}</td>
                                <td>${not empty shareholder.delegateInfo && not empty shareholder.delegateInfo.delegateFullName ? shareholder.delegateInfo.delegateFullName : "-"}</td>
                                <td>${not empty shareholder.delegateInfo && not empty shareholder.delegateInfo.delegateIdentityNumber ? shareholder.delegateInfo.delegateIdentityNumber : "-"}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>


        <div class="contentModule-section" id="reviewContactQeemah1Section">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="license.apply.review.contactperson"/></div>
                <c:url value="/my-sagia/license/contactperson" var="contactPersonLink"/>
                <a type="button" class="editContactPersonButton btn btn_link iconElement iconElement_edit03" href="${contactPersonLink}"><icon:edit/></a>
            </div>

            <div class="row">
                <div class="col-sm-6 col-md-4">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.name"/></dt>
                        <dd><c:out value="${contactPerson.firstName} ${contactPerson.lastName}"/></dd>
                        <dt><spring:theme code="license.apply.review.role"/></dt>
                        <dd><spring:theme code="licenseApply.role.${contactPerson.role}"/></dd>
                        <dt><spring:theme code="license.apply.review.education"/></dt>
                        <dd><c:out value="${contactPerson.education}"/></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-4">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.passport.number"/></dt>
                        <dd><c:out value="${contactPerson.passportNumber}"/></dd>
                        <dt><spring:theme code="license.apply.review.passport.date"/></dt>
                        <dd><c:out value="${contactPerson.passportIssueDate}"/></dd>
                        <dt><spring:theme code="license.apply.review.passport.expirydate"/></dt>
                        <dd><c:out value="${contactPerson.passportExpiryDate}"/></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-4">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.country"/></dt>
                        <dd><c:out value="${contactPerson.countryText}"/></dd>
                        <dt><spring:theme code="license.apply.review.city"/></dt>
                        <dd><c:out value="${contactPerson.city}"/></dd>
                        <dt><spring:theme code="license.apply.review.address"/></dt>
                        <dd><c:out value="${contactPerson.address}"/></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-4">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.telephonenumber"/></dt>
                        <dd><c:out value="${contactPerson.telephoneNumber}"/></dd>
                        <dt><spring:theme code="license.apply.review.mobilenumber"/></dt>
                        <dd><c:out value="${contactPerson.mobileNumber}"/></dd>
                        <dt><spring:theme code="license.apply.review.email"/></dt>
                        <dd><c:out value="${contactPerson.email}"/></dd>
                    </dl>
                </div>
            </div>
        </div>

        <div class="contentModule-section" style="display:none;">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="licenseApply.contactPerson.unifiedLicenseUrl"/></div>
            </div>

            <h3 style="font-size:15px;">
                <spring:theme code="licenseApply.contactPerson.unifiedLicense"/>&nbsp
                <a id="unifiedLicenseUrl" target="_blank" href="https://sagia.meras.gov.sa/"><spring:theme code="licenseApply.contactPerson.unifiedLicenseUrl"/></a>
            </h3>
        </div>

        <div   id="typeRequirementSection" style="display: none;">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
            		<div class="contentModule-headline"><spring:theme code="licenseApply.contactPerson.typeRequirement"/></div>
        	</div>


      		<h3 style="color:red;font-size:15px;"> <spring:theme code="licenseApply.contactPerson.readAllRequirement"/></h3>
            <div class="scrollWrapper" id="scrollWrapperTypeRequirement">
                <div class="scrollWrapper-inner" id="scrolltypeRequirement" >
                    <div id="requirementContent" class="requirement-content"  >
                        <dd id="contentRequirement"></dd>
                    </div>

                </div>
                <div class="contentModule-actions contentModule-actions_spaceBetween justify-content-center">
                <span>
                    <button id="requirementSubmitButton" type="button" class="btn" disabled="disabled"><spring:theme code="text.consent.button.accept"/></button>

                </span>

            </div>
            </div>

            

        </div>

        <%--<div class="contentModule-actions contentModule-actions_spaceBetween">--%>
            <%--<div class="formCheckBox formCheckBox_belowPanel">--%>
                <%--<div class="form-group">--%>
                    <%--<formElement:termsAndConditionsCheckbox event="LICENSE_SERVICES" id="termsAndConditions" path="termsAndConditionsChecked"/>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="formCheckBox formCheckBox_block formCheckBox_slim">
            <div class="form-group">
                <form:form modelAttribute="sagiaApplyReviewForm">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox event="LICENSE_SERVICES" id="termsAndConditions" path="termsAndConditionsChecked"/>
                    </div>
                </form:form>
            </div>
        </div>

        <div class="contentModule-actions contentModule-actions_spaceBetween">
            <span>
                <c:url value="/my-sagia/license/contactperson" var="contactPersonLink"/>
                <a id="reviewBackButton" type="button" class="btn btn-secondary" href="${contactPersonLink}"><spring:theme code="license.apply.review.back"/></a>
                <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                    <button id="reviewCancelButton" type="button" class="btn btn-secondary btn_link btn_bold" style="display: none"><spring:theme code="license.apply.review.cancel"/></button>
                </sec:authorize>
            </span>
            <%--<button id="reviewPayButton" type="button" class="btn" data-toggle="modal" onclick="requestPaymentDetails()">(TEST) Pay Again</button>--%>
            <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                <c:if test="${'/simulator' ne controllerUrl}">
                    <button id="reviewSubmitButton" type="button" disabled="disabled" class="btn" ><spring:theme code="license.apply.review.submit"/></button>
                </c:if>
            </sec:authorize>
        </div>
    </div>
    <input type="hidden" id="serviceId"/>
    <div class="modal fade" id="delegateInformationModal"  tabindex="-1" role="dialog" aria-labelledby="delegateInformationModal" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title"></div>
                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                        <icon:close/>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="contentModule-headline">
                        DELEGATE INFORMATION
                    </div>
                    <div class="scrollWrapper">
                        <div class="scrollWrapper-inner">
                            <div class="modal-description">
                                 <div class="row">
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="license.apply.shareholder.name.english"/></dt>
                                              <dd id="delegateNameEnglish"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                                <dt><spring:theme code="license.apply.shareholder.name.arabic"/></dt>
                                                <dd id="delegateNameArabic"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="license.apply.shareholder.delegateDetails.gender"/></dt>
                                              <dd id="delegateGender"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="general.country"/></dt>
                                              <dd id="delegateCountry"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="text.account.profile.license.shareholders.nationality"/></dt>
                                              <dd id="delegateNationality"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="license.apply.shareholder.dateOfBirth"/></dt>
                                              <dd id="delegateDateOfBirth"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="general.telephone"/></dt>
                                              <dd id="delegateTelephone"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="general.mobilenumber"/></dt>
                                              <dd id="delegateMobileNumber"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="general.email"/></dt>
                                              <dd id="delegateEmail"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="general.pobox"/></dt>
                                              <dd id="delegatePobox"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="general.postalcode"/></dt>
                                              <dd id="delegatePostalcode"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="license.apply.shareholder.idType"/></dt>
                                              <dd id="delegateIdType"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                                <dt><spring:theme code="license.apply.shareholder.idNumber"/></dt>
                                                <dd id="delegateIdNumber"></dd>
                                           </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt> <spring:theme code="license.apply.shareholder.issueDate"/></dt>
                                              <dd id="delegateIdIssueDate"></dd>
                                          </dl>
                                      </div>
                                      <div class="col-sm-6 col-md-3">
                                          <dl class="dlList">
                                              <dt><spring:theme code="license.apply.shareholder.expiryDate"/></dt>
                                              <dd id="delegateIdExpiryDate"></dd>
                                          </dl>
                                      </div>
                                </div>
                           </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:message code="general.close"/></button>
                </div>
            </div>
        </div>
    </div>
</div>


<style>
.sum-row{
    padding: 15px 10px 15px;border-bottom:solid 1px #ebedee ;
    font-weight: 600;
    font-size: 14px;
    color: #1c242c;
}

.rhqListItems{
    margin-bottom:0px !important;
}
</style>


<script>
	try{
      objectBranchesString =  ('${entitiesManagedByRhq}');
      objectBranchesString1 = objectBranchesString.replace(/("{)/g, '{');
	  objectBranchesString2 = objectBranchesString1.replace(/(}")/g, '}');

  	  console.log(objectBranchesString2);
	  objectBranches = [];
      objectBranches =  JSON.parse(objectBranchesString2);
	  console.log(objectBranches);

	  objectBrandString =  ('${brandPresenceInMENARegion}');
      objectBrandString1 = objectBrandString.replace(/("{)/g, '{');
	  objectBrandString2 = objectBrandString1.replace(/(}")/g, '}');

	  console.log(objectBrandString2);
	  objectBrands = [];
      objectBrands =  JSON.parse(objectBrandString2);
	  console.log(objectBrands);

	  objectCostsString =  ('${estimatedOperatingCostForRhq}');
      objectCostsString1 = objectCostsString.replace(/("{)/g, '{');
	  objectCostsString2 = objectCostsString1.replace(/(}")/g, '}');

	  console.log(objectCostsString2);
	  objectCost = [];
      objectCost =  JSON.parse(objectCostsString2);
	  console.log(objectCost);

      subsidiaryString ='';
      subsidiaryString =  '${entityInformation.rhqSubsidiaryPresence}';

    }
        catch(error){
        console.log(error);
        }
</script>