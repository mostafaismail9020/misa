<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
</script>

<div class="mainSection mainSection_white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image" src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="">
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="temporaryBiddingLicense.title" />
                </h1>
            </div>
            <div class="profile-icons">
                <div class="calendar">
                    <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                        <img class="profile-icon-images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender.png"  alt="calender" />
                    </a>
                </div>
                <!-- <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                    <c:if test="${hasLicense or hasAwaitingPayment}">
                        <button class="sagiaNavigation-btn js-sagiaNavigationToggle btnNotifications messages" title="<spring:message code='account.notifications.yourMessages'/>">
                            <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span><img alt="" class="" src="${commonResourcePath}/images/dashboard-media/Profile-bar/message.png" />
                        </button>
                    </c:if>
                    <a class="btn btn_slim btn_round btn_outline" href="${encodedContextPath}/my-sagia/notifications">
                        <spring:message code="header.viewAll.text" />
                    </a>
                </div>-->

                <div class="profile">
                    <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>">
                        <!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/profile-icon.png" />-->
                        <span></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

                                                <div class="js-panelTabs panelTabs panelTabtemporaryBiddingLicenseFormIds_white panelTabs_white panelTabs_lightNavigation panelTabs_noPanelInBody panelTabs_iconsAndLabel panelTabs_formFlow panelTabs_separated panelTabs_tip_none">
                                                    <div class="container">
                                                        <div class="mainSection-header headerDrafts d-block mb-5">
                                                            <button class="btn btn_round btn_slim js-save-draft" data-target-form="temporaryBiddingLicenseFormId" data-service-id="${serviceId}"><spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save">
                        <icon:save/>
                    </span>
                </button><button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="temporaryBiddingLicenseFormId"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span>
                </button>
                                                        </div>
                                                    </div>

                                                    <form:form id="temporaryBiddingLicenseFormId" action="${encodedContextPath}/my-sagia/license/bidding-create" method="post" modelAttribute="sagiaTemporaryBiddingLicenseForm" enctype="multipart/form-data">
                                                        <!--Entity information-->
                                                        <div class="panelTabs-head">
                                                            <icon:entityInformation/>
                                                            <span class="panelTabs-label"><spring:theme code="temporaryBiddingLicense.entityInformation"/></span>
                                                            <span class="panelTabs_formFlow-bar"></span>
                                                            <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
                                                        </div>
                                                        <div class="panelTabs-body">
                                                            <div class="contentModule">
                                                                <div class="contentModule-section">
                                                                    <div class="row">
                                                                        <div class="col-md-6 mb-3">
                                                                            <formElement:formInputBox idKey="companyNameInArabicId" labelKey="temporaryBiddingLicense.companyNameInArabic" path="companyNameInArabic" inputCSS="form-control" maxlength="60" />
                                                                        </div>
                                                                        <div class="col-md-6 mb-3">
                                                                            <formElement:formInputBox idKey="companyNameInEnglishId" labelKey="temporaryBiddingLicense.companyNameInEnglish" path="companyNameInEnglish" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="60" />
                                                                        </div>
                                                                        <div class="col-md-6 mb-3">
                                                                            <formElement:formInputBox idKey="projectNameInArabicId" labelKey="temporaryBiddingLicense.projectNameInArabic" path="projectNameInArabic" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="60" />
                                                                        </div>
                                                                        <div class="col-md-6 mb-3">
                                                                            <formElement:formInputBox idKey="projectNameInEnglishId" labelKey="temporaryBiddingLicense.projectNameInEnglish" path="projectNameInEnglish" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="60" />
                                                                        </div>
                                                                        <div class="col-md-6 mb-3">
                                                                            <formElement:formInputBox idKey="capitalId" labelKey="temporaryBiddingLicense.capital" path="capital" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="15" />
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <formElement:formSelectBox idKey="governmentEntityId" labelKey="temporaryBiddingLicense.governmentEntity" path="governmentEntity" items="${governmentEntities}" itemLabel="name" itemValue="code" skipBlank="false" skipBlankMessageKey="form.select.empty"
                                                                                labelCSS="control-label_mandatory" selectCSSClass="form-control" />
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <formElement:formSelectBox idKey="countryId" labelKey="temporaryBiddingLicense.country" path="country" items="${countries}" itemLabel="name" itemValue="code" skipBlank="false" skipBlankMessageKey="form.select.empty" labelCSS="control-label_mandatory"
                                                                                selectCSSClass="form-control js-select2-searchBegining js-select2-sortAlphabetically" />
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <formElement:formInputBox idKey="cityId" labelKey="temporaryBiddingLicense.city" path="city" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="60" />
                                                                        </div>
                                                                        <div class="col-md-6 mb-3">
                                                                            <formElement:formInputBox idKey="postalCodeId" labelKey="temporaryBiddingLicense.postalCode" path="postalCode" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="10" />
                                                                        </div>
                                                                        <div class="col-md-6 mb-3">
                                                                            <formElement:formInputBox idKey="poBoxId" labelKey="temporaryBiddingLicense.poBox" path="poBox" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="10" />
                                                                        </div>
                                                                        <div class="col-md-6 mb-3">
                                                                            <div class="formInputBox-split">
                                                                                <div class="formInputBox">
                                                                                    <div class="form-group">
                                                                                        <input id="countryPrefixMobileId" name="countryPrefixMobile" class="form-control form-control_preNumber" placeholder="." type="text" value="">
                                                                                        <label class="control-label" for="countryPrefixMobileId">
                                            <spring:theme code="temporaryBiddingLicense.countryCode"/>
                                        </label>
                                                                                    </div>
                                                                                    <div class="help-block"></div>
                                                                                </div>
                                                                                <div class="formInputBox formInputBox_big">
                                                                                    <formElement:formInputBox idKey="mobileId" labelKey="temporaryBiddingLicense.mobile" path="mobile" inputCSS="form-control form-control_labeled" labelCSS="control-label_mandatory" mandatory="true" maxlength="32" />
                                                                                    <div class="help-block"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputBox-split">
                                                                                <div class="formInputBox">
                                                                                    <div class="form-group">
                                                                                        <input id="countryPrefixTelephoneId" name="countryPrefixTelephone" class="form-control form-control_preNumber" id="address.telephone.prefix" placeholder="." type="text" value="">
                                                                                        <label class="control-label" for="address.telephone.prefix">
                                            <spring:theme code="temporaryBiddingLicense.countryCode"/>
                                        </label>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="formInputBox formInputBox_big">
                                                                                    <formElement:formInputBox idKey="telephoneId" labelKey="temporaryBiddingLicense.telephone" path="telephone" inputCSS="form-control form-control_labeled" labelCSS="control-label_mandatory" mandatory="true" maxlength="32" />
                                                                                    <div class="help-block"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <formElement:formInputBox idKey="emailId" labelKey="temporaryBiddingLicense.email" path="email" inputCSS="form-control" labelCSS="control-label_mandatory" mandatory="true" maxlength="60" />
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="contentModule-actions contentModule-actions_spaceBetween">
                                                                    <button id="nextFirstTabButtonId" type="button" class="btn btn_bold"><spring:theme code="general.next"/></button>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <!-- Documents -->
                                                        <div class="panelTabs-head">
                                                            <!--<icon:document/>-->
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="36.14" height="36.168" viewBox="0 0 36.14 36.168">
                                                                <g id="Support_Documents" data-name="Support Documents" transform="translate(0.47 0.366)">
                                                                  <g id="Group_1703" data-name="Group 1703" transform="translate(13.16 0.134)">
                                                                    <g id="Group_1710" data-name="Group 1710">
                                                                      <path id="Path_2335" data-name="Path 2335" d="M139.875,18.112V28.583h22.01V4.957L157.455.5H139.875V15.6" transform="translate(-139.875 -0.5)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                      <path id="Path_2336" data-name="Path 2336" d="M328.258.883V5.336h4.4" transform="translate(-310.683 -0.847)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                    </g>
                                                                  </g>
                                                                  <line id="Line_553" data-name="Line 553" x2="10.429" transform="translate(22.713 8.137)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                  <line id="Line_554" data-name="Line 554" x2="10.429" transform="translate(22.713 11.843)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                  <line id="Line_555" data-name="Line 555" x2="10.429" transform="translate(22.713 15.549)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                  <line id="Line_556" data-name="Line 556" x2="10.429" transform="translate(22.713 19.255)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                  <line id="Line_557" data-name="Line 557" x2="10.429" transform="translate(22.713 23.089)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                  <g id="Group_1705" data-name="Group 1705" transform="translate(0.17 4.075)">
                                                                    <g id="Group_1709" data-name="Group 1709">
                                                                      <path id="Path_2337" data-name="Path 2337" d="M7.156,82.16l-6.515,2.3L4.722,95.846" transform="translate(-0.641 -78.438)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                      <path id="Path_2338" data-name="Path 2338" d="M49.492,61.514l4.446,12.045,16.039-6.917L55.749,69.948,51.492,46.206l-.459-2.56L57.925,42.5" transform="translate(-44.935 -42.5)" fill="none" stroke="#707070" stroke-width="1"/>
                                                                    </g>
                                                                  </g>
                                                                </g>
                                                              </svg>

                                                            <span class="panelTabs-label"><spring:theme code="temporaryBiddingLicense.uploadDocuments"/></span>
                                                            <span class="panelTabs_formFlow-bar"></span>
                                                            <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
                                                        </div>
                                                        <div class="panelTabs-body">
                                                            <div class="contentModule">
                                                                <div class="contentModule-section">
                                                                    <div class="formError" id="filesValidationId">
                                                                        <icon:messageError/>
                                                                        <spring:theme code="validation.file" />
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file0Id" name="temporaryBiddingLicenseMultipartFiles[0]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="0" value="">
                                                                                    <input id="text0" name="text0" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file0Id"><spring:theme code="temporaryBiddingLicense.doc0"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text on "Pledge for complete documentation and cr". You can also go to our <a href="#">help center</a>.'
                                                                                            data-original-title="" title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file1Id" name="temporaryBiddingLicenseMultipartFiles[1]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="1" value="">
                                                                                    <input id="text1" name="text1" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file1Id"><spring:theme code="temporaryBiddingLicense.doc1"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text on "Classification Letter from the ultimatecountry". You can also go to our <a href="#">help center</a>.'
                                                                                            data-original-title="" title="">
                                            <icon:tipInfo/>
                                        </span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file2Id" name="temporaryBiddingLicenseMultipartFiles[2]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="2" value="">
                                                                                    <input id="text2" name="text2" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label " for="file2Id"><spring:theme code="temporaryBiddingLicense.doc2"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file3Id" name="temporaryBiddingLicenseMultipartFiles[3]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="3" value="">
                                                                                    <input id="text3" name="text3" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label " for="file3Id"><spring:theme code="temporaryBiddingLicense.doc3"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file4Id" name="temporaryBiddingLicenseMultipartFiles[4]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="4" value="">
                                                                                    <input id="text4" name="text4" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file4Id"><spring:theme code="temporaryBiddingLicense.doc4"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file5Id" name="temporaryBiddingLicenseMultipartFiles[5]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="5" value="">
                                                                                    <input id="text06" name="text06" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file5Id"><spring:theme code="temporaryBiddingLicense.doc5"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file6Id" name="temporaryBiddingLicenseMultipartFiles[6]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="6" value="">
                                                                                    <input id="text07" name="text07" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file6Id"><spring:theme code="temporaryBiddingLicense.doc6"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file7Id" name="temporaryBiddingLicenseMultipartFiles[7]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="7" value="">
                                                                                    <input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label " for="file7Id"><spring:theme code="temporaryBiddingLicense.doc7"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file8Id" name="temporaryBiddingLicenseMultipartFiles[8]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="8" value="">
                                                                                    <input id="text09" name="text09" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file8Id"><spring:theme code="temporaryBiddingLicense.doc8"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file9Id" name="temporaryBiddingLicenseMultipartFiles[9]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="9" value="">
                                                                                    <input id="text10" name="text10" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file9Id"><spring:theme code="temporaryBiddingLicense.doc9"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file10Id" name="temporaryBiddingLicenseMultipartFiles[10]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="10" value="">
                                                                                    <input id="text11" name="text11" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file10Id"><spring:theme code="temporaryBiddingLicense.doc10"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="formInputFile formInputFile_group">
                                                                                <div class="form-group">
                                                                                    <input id="file11Id" name="temporaryBiddingLicenseMultipartFiles[11]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" data-id="11" value="">
                                                                                    <input id="text12" name="text12" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                                                                    <label class="control-label control-label_mandatory" for="file11Id"><spring:theme code="temporaryBiddingLicense.doc11"/></label>
                                                                                    <div class="form-icon form-icon_browse">
                                                                                        <icon:upload/>
                                                                                    </div>
                                                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                                                        <icon:cross/>
                                                                                    </div>
                                                                                    <div class="formInputFile-append">
                                                                                        <span class="formInputFile-text formInputFile-text_tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='This is a short explanation text. You can also go to our <a href="#">help center</a>.' data-original-title=""
                                                                                            title=""><icon:tipInfo/></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="help-block"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="para_txt">
                                                                        <spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}" />
                                                                    </div>
                                                                </div>
                                                                <div class="contentModule-actions contentModule-actions_spaceBetween">
                                                                    <button id="nextSecondTabButtonId" type="button" class="btn btn_bold">
                        <spring:theme code="general.next"/>
                    </button>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <!--Review -->
                                                        <div id="reviewTabId" class="panelTabs-head">
                                                            <icon:review/>
                                                            <span class="panelTabs-label"><spring:theme code="temporaryBiddingLicense.review"/></span>
                                                        </div>
                                                        <div class="panelTabs-body">
                                                            <div class="contentModule">
                                                                <div class="contentModule-actions contentModule-actions_right">
                                                                    <button id="printBiddingCert" class="btn btn_outline btn_round btn_slim"><spring:theme code="biddingLicense.button.print.text"/>
                                                                        <span class="iconElement iconElement_print"><icon:print/></span>
                                                                    </button>
                                                                </div>

                                                                <div class="contentModule-section contentModule-section_noDivider">
                                                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                                                                        <div class="contentModule-headline mw1">
                                                                            <!--<icon:info/>-->
                                                                            <spring:theme code="temporaryBiddingLicense.entityInformation" />
                                                                        </div>
                                                                        <button id="editEntityButtonId" type="button" class="btn btn_link iconElement iconElement_edit03"><icon:edit/></button>
                                                                    </div>

                                                                    <div class="row">
                                                                        <div class="col-md-6">
                                                                            <dl class="dlList">
                                                                                <dt><spring:theme code="temporaryBiddingLicense.companyNameInArabic"/></dt>
                                                                                <dd id="companyNameInArabicReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.companyNameInEnglish"/></dt>
                                                                                <dd id="companyNameInEnglishReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.projectNameInArabic"/></dt>
                                                                                <dd id="projectNameInArabicReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.projectNameInEnglish"/></dt>
                                                                                <dd id="projectNameInEnglishReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.capital"/></dt>
                                                                                <dd id="capitalReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.mobile"/></dt>
                                                                                <dd id="mobileReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.telephone"/></dt>
                                                                                <dd id="telephoneReviewId"></dd>
                                                                            </dl>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <dl class="dlList">
                                                                                <dt><spring:theme code="temporaryBiddingLicense.governmentEntity"/></dt>
                                                                                <dd id="governmentEntityReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.country"/></dt>
                                                                                <dd id="countryReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.city"/></dt>
                                                                                <dd id="cityReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.postalCode"/></dt>
                                                                                <dd id="postalCodeReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.poBox"/></dt>
                                                                                <dd id="poBoxReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.email"/></dt>
                                                                                <dd id="emailReviewId"></dd>
                                                                            </dl>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="contentModule-section">
                                                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered_green">
                                                                        <div class="contentModule-headline mw1">
                                                                            <!-- <icon:documents/>-->
                                                                            <spring:theme code="temporaryBiddingLicense.uploadDocuments" />
                                                                        </div>
                                                                        <button id="editDocumentsButtonId" type="button" class="btn btn_link iconElement iconElement_edit03"><icon:edit/></button>
                                                                    </div>

                                                                    <div class="row">
                                                                        <div class="col-md-6">
                                                                            <dl class="dlList">
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc0"/></dt>
                                                                                <dd id="file0ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc2"/></dt>
                                                                                <dd id="file2ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc4"/></dt>
                                                                                <dd id="file4ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc6"/></dt>
                                                                                <dd id="file6ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc8"/></dt>
                                                                                <dd id="file8ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc10"/></dt>
                                                                                <dd id="file10ReviewId"></dd>
                                                                            </dl>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <dl class="dlList">
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc1"/></dt>
                                                                                <dd id="file1ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc3"/></dt>
                                                                                <dd id="file3ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc5"/></dt>
                                                                                <dd id="file5ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc7"/></dt>
                                                                                <dd id="file7ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc9"/></dt>
                                                                                <dd id="file9ReviewId"></dd>
                                                                                <dt><spring:theme code="temporaryBiddingLicense.doc11"/></dt>
                                                                                <dd id="file11ReviewId"></dd>
                                                                            </dl>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="contentModule-actions contentModule-actions_spaceBetween">
                                                                    <div class="formCheckBox formCheckBox_belowPanel">
                                                                        <div class="form-group">
                                                                            <formElement:termsAndConditionsCheckbox event="LICENSE_SERVICES" id="termsAndConditions" path="termsAndConditionsChecked" />
                                                                        </div>
                                                                    </div>
                                                                    <button type="submit" class="btn btn_bold mt-3">
                        <spring:theme code="general.submit"/>
                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form:form>
                                                </div>

                                                <a id="noCountryPrefixId" href="#" data-toggle="modal" data-target="#noCountryPrefixDialogId"></a>

                                                <div class="modal fade" id="noCountryPrefixDialogId" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <div class="modal-title">
                                                                    <spring:theme code="biddingLicense.error.text" />
                                                                </div>
                                                                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="modal-description">
                                                                    <spring:theme code="license.countrycodeerror" />
                                                                </div>
                                                            </div>§
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="biddingLicense.button.ok.text"/></button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <input type="hidden" id="serviceId" />

                                                <div class="modal fade" id="requestSubmittedDialogId" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-heade">
                                                                <div class="modal-title">
                                                                    <spring:theme code="general.requestsubmitted" />
                                                                </div>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="modal-heroImage">
                                                                    <icon:modal02/>
                                                                </div>
                                                                <div class="modal-description">
                                                                    <spring:theme code="specialservices.wewillreviewmessage" />
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn_slim" data-dismiss="modal" onclick="window.location.href='${encodedContextPath}/dashboard'"><spring:theme code="general.gotomydashboard"/></button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <script>
                                                    var controllerUrl = '${controllerUrl}';
                                                </script>