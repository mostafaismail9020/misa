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
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>

<div class="panelTabs-head" id="reviewTab">
    <icon:review/><span class="panelTabs-label"><spring:theme code="license.apply.review"/></span>
</div>
<div class="panelTabs-body" id="reviewContent">
    <div class="contentModule">
        <div class="contentModule-actions contentModule-actions_right">
            <button id="printButton" class="border-0 bg-transparent btn_round btn_slim">
           <!-- <spring:theme code="general.print"/>-->
                <span class="iconElement iconElement_print"><icon:print/></span>
            </button>
        </div>

        <div class="contentModule-section contentModule-section_noDivider">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="licenseApplyEntityInformation.entityInformation.title"/></div>
                <button id="editEntityInformationButton" type="button" class="iconElement_edit03"><icon:edit/></button>
            </div>

            <div class="row" id="reviewHasSagiaLicenseSection">
                <div class="col-md-6">
                    <ul class="ynList">
                        <li class="ynList-item">
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_no"><icon:ynIcon_no/></span>
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_yes"><icon:ynIcon_yes/></span>
                            <spring:theme code="text.account.profile.license.hasSAGIALicense"/>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row" id="reviewHasShareholdersTypePerson">
                <div class="col-md-6">
                    <ul class="ynList">
                        <li class="ynList-item">
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_no"><icon:ynIcon_no/></span>
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_yes"><icon:ynIcon_yes/></span>
                            <spring:theme code="text.account.profile.license.hasShareholdersTypePerson"/>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row" id="reviewAdvanceLicenseSection">
                <div class="col-md-6">
                    <ul class="ynList">
                        <li class="ynList-item">
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_yes"><icon:ynIcon_yes/></span>
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_no"><icon:ynIcon_no/></span>
                            <spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.title"/>
                        </li>
                    </ul>
                </div>
                <div class="col-md-6" id="advanceLicenseNumberSection">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.advance.licence.no"/></dt>
                        <dd id="advanceLicenseNumber"><spring:theme code="license.apply.review.advance.licence.no.text"/></dd>
                    </dl>
                </div>

                <hr class="contentModule-separator"/>
            </div>

            <div class="row" id="reviewLicenseInformationSection">
                <div class="col-md-6">
                    <ul class="ynList">
                        <li class="ynList-item">
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_no"><icon:ynIcon_no/></span>
                            <span class="iconElement iconElement_ynIcon iconElement_ynIcon_yes"><icon:ynIcon_yes/></span>
                            <spring:theme code="licenseApplyEntityInformation.licenseInformationSection.text"/>
                        </li>
                    </ul>
                </div>

                <hr class="contentModule-separator"/>
            </div>
            <div id="reviewLicenseTypeSection">
                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.review.licence.type"/></dt>
                            <dd>Service</dd>
                        </dl>
                    </div>
                </div>
            </div>
			<div id="reviewLicenseYearSection">
                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="licenseApplyEntityInformation.licenseYearSection.title"/></dt>
                            <dd></dd>
                        </dl>
                    </div>
                </div>

                <hr class="contentModule-separator"/>
            </div>

            <div id="reviewAdvanceLicenseNrSection">
                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList">
                            <dt><spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.text"/></dt>
                            <dd>Service</dd>
                        </dl>
                    </div>
                </div>

            </div>

            <div id="reviewStockMarketSection">
                <div class="row">
                    <div class="col-md-6">
                        <ul class="ynList">
                            <li class="ynList-item">
                                <span class="iconElement iconElement_ynIcon iconElement_ynIcon_yes"><icon:ynIcon_yes/></span>
                                <span class="iconElement iconElement_ynIcon iconElement_ynIcon_no"><icon:ynIcon_no/></span>
                                <spring:theme code="text.account.profile.license.hasStockListing"/>
                            </li>
                        </ul>
                    </div>
                </div>

                <hr class="contentModule-separator"/>
            </div>

            <div id="reviewStockMarketQuestionsSection">
                <div class="row">
                    <div class="col-md-6">
                        <ul class="ynList"></ul>
                    </div>
                </div>

                <hr class="contentModule-separator"/>
            </div>

            <div id="reviewOnStockMarketSection">
                <div class="row">
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="licenseApplyEntityInformation.stockMarketSection.country"/></dt>
                            <dd id="reviewOnStockMarketSectionCountry"></dd>
                            <dt><spring:theme code="profile.company.ISINCode"/></dt>
                            <dd id="reviewOnStockMarketSectionISINCode"></dd>
                        </dl>
                    </div>
                </div>
            </div>

            <div id="reviewBasicInformationSection">
                <div class="row">
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.review.entity.name.english"/></dt>
                            <dd id="reviewBasicInformationEntityName"></dd>
                            <dt><spring:theme code="license.apply.review.entity.name.arabic"/></dt>
                            <dd id="reviewBasicInformationEntityNameArabic"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.review.legal.status"/></dt>
                            <dd id="reviewBasicInformationStatus"></dd>
                            <dt><spring:theme code="license.apply.review.labor.size"/></dt>
                            <dd id="reviewBasicInformationSize"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt> <spring:theme code="general.capital"/></dt>
                            <dd id="reviewBasicInformationCapital"></dd>
                            <dt><spring:theme code="licence.apply.region"/></dt>
                            <dd id="reviewBasicInformationRegion"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.city"/></dt>
                            <dd id="reviewBasicInformationCity"></dd>
                        </dl>
                    </div>
                </div>

                <hr class="contentModule-separator"/>
            </div>

            <div id="reviewBasicInformationExtendedSection">
                <div class="row">
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.entity.name"/></dt>
                            <dd id="reviewBasicInformationExtendedEntityName"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="license.apply.entity.name.arabic"/></dt>
                            <dd id="reviewBasicInformationExtendedEntityNameInArabic"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.legalstatus"/></dt>
                            <dd id="reviewBasicInformationExtendedStatus"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="license.multinational"/></dt>
                            <dd id="reviewBasicInformationExtendedMultinationalCompany"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.capital"/></dt>
                            <dd id="reviewBasicInformationExtendedCapital"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.email"/></dt>
                            <dd id="reviewBasicInformationExtendedEmail"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.telephone"/></dt>
                            <dd id="reviewBasicInformationExtendedTelephone"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.mobilenumber"/></dt>
                            <dd id="reviewBasicInformationExtendedMobilePhone"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="licence.apply.website"/></dt>
                            <dd id="reviewBasicInformationExtendedWebsite"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.country"/></dt>
                            <dd id="reviewBasicInformationExtendedCountry"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="licence.apply.region"/></dt>
                            <dd id="reviewBasicInformationExtendedRegion"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.city"/></dt>
                            <dd id="reviewBasicInformationExtendedCity"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.address"/></dt>
                            <dd id="reviewBasicInformationExtendedAddress"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.postalcode"/></dt>
                            <dd id="reviewBasicInformationExtendedPostalCode"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.pobox"/></dt>
                            <dd id="reviewBasicInformationExtendedPoBox"></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <dl class="dlList">
                            <dt><spring:theme code="general.pobox"/></dt>
                            <dd id="reviewBasicInformationExtendedInvestment"></dd>
                        </dl>
                    </div>
                </div>

                <hr class="contentModule-separator"/>
            </div>

            <div id="reviewISICSection" style="display: none;">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList" id="reviewSection">
                            <dt><spring:theme code="license.apply.review.isicSection"/></dt>
                            <dd></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList" id="reviewDivision">
                            <dt><spring:theme code="license.apply.review.isicDivision"/></dt>
                            <dd></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList" id="reviewGroup">
                            <dt><spring:theme code="license.apply.review.isicGroup"/></dt>
                            <dd></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList" id="reviewClass">
                            <dt><spring:theme code="license.apply.review.isicClass"/></dt>
                            <dd></dd>
                        </dl>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <dl class="dlList" id="reviewBranch">
                            <dt><spring:theme code="license.apply.review.isicBranch"/></dt>
                            <dd></dd>
                        </dl>
                    </div>
                    <div class="col-12">
                        <dl class="dlList" id="reviewBusinessType">
                            <dt><spring:theme code="license.businesstype"/></dt>
                            <dd></dd>
                        </dl>
                    </div>
                    <div class="col-12">
                        <dl class="dlList" id="reviewActivities">
                            <dt><spring:theme code="license.apply.business.activities"/></dt>
                            <dd>
                                <ul class="dottedList dottedList_green">
                                </ul>
                            </dd>
                        </dl>
                    </div>
                </div>

                <hr class="contentModule-separator"/>
            </div>

<!--             <div id="reviewProductsSection"> -->
<!--                 <div class="row"> -->
<!--                     <div class="col-12"> -->
<!--                         <dl class="dlList"> -->
<%--                             <dt><spring:theme code="license.apply.products"/></dt> --%>
<!--                             <dd> -->
<!--                                 <div class="tableModule"> -->
<!--                                     <table class="tableModule-table"> -->
<!--                                         <thead class="tableModule-head"> -->
<!--                                             <tr> -->
<%--                                                 <th><spring:theme code="products.productid"/></th> --%>
<%--                                                 <th><spring:theme code="products.productdescription"/></th> --%>
<%--                                                 <th><spring:theme code="products.qty"/></th> --%>
<%--                                                 <th><spring:theme code="products.unit"/></th> --%>
<!--                                             </tr> -->
<!--                                         </thead> -->
<!--                                         <tbody class="tableModule-body"> -->
<!--                                             <tr></tr> -->
<!--                                         </tbody> -->
<!--                                     </table> -->
<!--                                 </div> -->
<!--                             </dd> -->
<!--                         </dl> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
			<div id="temporaryLicenseTextBoxSection" style="display: none;">
				<div class="row">
					<div class="col-12">
						<dl class="dlList">
							<dt>
								<spring:theme code="license.apply.business.activities.temporary" />
							</dt>
							<dd>
							</br>
								<p class="white-space-pre"></p>
							</dd>
						</dl>
					</div>
				</div>

				<hr class="contentModule-separator" />
			</div>
        </div>

        <div class="contentModule-section contentModule-section_noDivider" id="reviewShareholdersSection">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline test"><spring:theme code="license.apply.review.shareholders"/></div>
                <button id="editShareholdersButton" type="button" class="iconElement_edit03"><icon:edit/>
                </button>
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
                    <tbody class="tableModule-body"></tbody>
                </table>
            </div>
        </div>

        <div class="contentModule-section" id="reviewContactQeemah1Section">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="license.apply.review.contactperson"/></div>
                <button type="button" class="editContactPersonButton iconElement iconElement_edit03"><icon:edit/></button>
            </div>

            <div class="row">
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.name"/></dt>
                        <dd id="contactQeemah1Name"></dd>
                        <dt><spring:theme code="license.apply.review.role"/></dt>
                        <dd id="contactQeemah1Role"></dd>
                        <dt><spring:theme code="license.apply.review.education"/></dt>
                        <dd id="contactQeemah1Education"></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.passport.number"/></dt>
                        <dd id="contactQeemah1PassportNumber"></dd>
                        <dt><spring:theme code="license.apply.review.passport.date"/></dt>
                        <dd id="contactQeemah1PassportDate"></dd>
                        <dt><spring:theme code="license.apply.review.passport.expirydate"/></dt>
                        <dd id="contactQeemah1PasswordExpiryDate"></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.country"/></dt>
                        <dd id="contactQeemah1Country"></dd>
                        <dt><spring:theme code="license.apply.review.city"/></dt>
                        <dd id="contactQeemah1City"></dd>
                        <dt><spring:theme code="license.apply.review.address"/></dt>
                        <dd id="contactQeemah1Address"></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.telephonenumber"/></dt>
                        <dd id="contactQeemah1TelephoneNumber"></dd>
                        <dt><spring:theme code="license.apply.review.mobilenumber"/></dt>
                        <dd id="contactQeemah1MobileNumber"></dd>
                        <dt><spring:theme code="license.apply.review.email"/></dt>
                        <dd id="contactQeemah1Email"></dd>
                    </dl>
                </div>
            </div>
        </div>

        <div class="contentModule-section" id="reviewContactQeemah2Section">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="license.apply.review.contactperson"/></div>
                <button type="button" class="editContactPersonButton btn btn_link iconElement iconElement_edit03"><icon:edit/></button>
            </div>

            <div class="row">
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.firstname"/></dt>
                        <dd id="contactQeemah2FirstName"></dd>
                        <dt><spring:theme code="license.apply.review.lastname"/></dt>
                        <dd id="contactQeemah2LastName"></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.nationality"/></dt>
                        <dd id="contactQeemah2Nationality"></dd>
                        <dt><spring:theme code="license.apply.review.position"/></dt>
                        <dd id="contactQeemah2Position"></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.email"/></dt>
                        <dd id="contactQeemah2Email"></dd>
                        <dt><spring:theme code="license.apply.review.country"/></dt>
                        <dd id="contactQeemah2Country"></dd>
                    </dl>
                </div>
                <div class="col-sm-6 col-md-3">
                    <dl class="dlList">
                        <dt><spring:theme code="license.apply.review.mobilenumber"/></dt>
                        <dd id="contactQeemah2MobileNumber"></dd>
                        <dt><spring:theme code="license.apply.review.telephonenumber"/></dt>
                        <dd id="contactQeemah2TelephoneNumber"></dd>
                    </dl>
                </div>
            </div>
        </div>

        <div class="contentModule-section">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                <div class="contentModule-headline"><spring:theme code="licenseApply.contactPerson.unifiedLicenseUrl"/></div>
            </div>

            <h3>
                <spring:theme code="licenseApply.contactPerson.unifiedLicense"/>&nbsp
                <a id="unifiedLicenseUrl" target="_blank" href="#"><spring:theme code="licenseApply.contactPerson.unifiedLicenseUrl"/></a>
            </h3>
        </div>
<hr/>
        <div   id="typeRequirementSection">
            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
            <div class="contentModule-headline"><spring:theme code="licenseApply.contactPerson.typeRequirement"/></div>
            </div>


        <h3 style="color:red"> <spring:theme code="licenseApply.contactPerson.readAllRequirement"/></h3>
                <div class="scrollWrapper" id="scrollWrapperTypeRequirement">
                    <div class="scrollWrapper-inner" id="scrolltypeRequirement" >
                        <div id="requirementContent" class="requirement-content"  >
                            <dd id="contentRequirement"></dd>
                        </div>

                    </div>
                </div>




            <div class="contentModule-actions contentModule-actions_spaceBetween">
            <span>
                <button id="requirementSubmitButton" type="button" class="btn" disabled="disabled"><spring:theme code="text.consent.button.accept"/></button>

            </span>

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
                <button id="reviewBackButton" type="button" class="btn btn_bold btn-normal btn-bg"><spring:theme code="license.apply.review.back"/></button>
                <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                    <button id="reviewCancelButton" type="button" class="btn btn-normal btn-ctrl btn_link btn_bold btn-outline"><spring:theme code="license.apply.review.cancel"/></button>
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
    <div class="modal fade" id="delegateInformationModal"  tabindex="-1" role="dialog" aria-labelledby="delegateInformationModal" aria-hidden="true">
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
