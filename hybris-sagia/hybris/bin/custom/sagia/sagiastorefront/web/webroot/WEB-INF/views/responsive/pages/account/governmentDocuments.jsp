<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="general.governmentdocuments" />
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense or hasAwaitingPayment}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                    <div class="calendar notification p-0 sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img"/>
                            </button>
                        </c:if>
                        <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                        <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
                            <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                            <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                            <div class="sagiaNavigation-subPane-actions">
                                <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="profile">
                    <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>">
                        <span></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header row service-time">
           	<h1 class="mainSection-headline">
           		<spring:theme code="services.government.documents.create" />
            </h1>
            <c:if test="${not empty processingTime}">
                <div class="serviceTime">
                    <div class="serviceTime-label">
                        <spring:theme code="average.service.time" />
                    </div>
                    <div class="serviceTime-detail">
                        <c:choose>
                            <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                <span class="serviceTime-highlight">${processingTime.days}</span>
                                <spring:theme code="average.processingTime.days" />
                                <span class="serviceTime-highlight">${processingTime.hours}</span>
                                <spring:theme code="average.processingTime.hours" />
                            </c:when>
                            <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                <spring:theme code="average.processingTime.minutes" />
                                <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                <spring:theme code="average.processingTime.seconds" />
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container ">
        <div class="d-flex row renewal-services w-100">
            <div class="col-md-3">
                <!-- <a href="${encodedContextPath}/my-sagia/sagia-profile" class="btn btn_leftIconLink btn_darkLink w-auto back_to_service"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="profileCompany.button.back.text"/></a> -->
                <a href="${encodedContextPath}/service-search/GOVERNMENTAL SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
            </div>
            <c:if test="${fn:length(sagiaService.tabs) > 0}">
                <div class="col-xl-3 col-12">
                    <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                </div>
            </c:if>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <div class="expandableContent" id="service-tab">
            
        </div>
    </div>
</div>

<c:if test="${not empty sagiaService.description || not empty sagiaService.serviceDocuments || not empty sagiaService.rulesRestrictions || not empty sagiaService.serviceFinancialFees || not empty sagiaService.serviceDuration}">  
    <div class="container">
        <button class="btn_history btn_rightIconLink btn_bold btn_greenLink btn_show_hide_service" data-expand-target="expand-03">
            <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> <spring:theme code="service.overview.show"/></div>
            <div class=""><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
        </button>
    </div>
    <div class="container service-wrapper service-wrapper-info mb-5 expanded"  id="expand-03">
        <div class="serviceModule serviceModule_list mx-5 pt-4">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.description}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.service.overview"/> </span>	
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.description}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.serviceDocuments}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.service.document"/> </span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDocuments}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.rulesRestrictions}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.rules.restrictions"/></span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.rulesRestrictions}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.serviceFinancialFees}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.financial.fees"/> </span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceFinancialFees}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5 pb-4">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.serviceDuration}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.duration"/> </span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDuration}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<div class="mainSection mainSection_dark mainSection_pdt16 branchData-mainSection">
    <div class="container mt-3">
        <form:form method="post" modelAttribute="branchData"
            action="${encodedContextPath}/governmentDocuments/branches">

            <div class="panelModule panelModule_halfRadius">
                <c:if test="${branchData.statusCode == 'V' || branchData.statusCode == 'I' }">
                    <c:set var="disabledFields" value="true" />
                    <c:set var="disabledTnC" value="disabled" />
                </c:if>
                <c:if test="${not empty registerFormError}">
                    <div class="formError">
                        <icon:messageError />
                        <spring:theme code="${registerFormError}" arguments="" />
                    </div>
                </c:if>
                <div class="contentModule">

                    <div class="contentModule-section">
                        <div class="contentModule-headline">
                            <!-- <span class="iconElement iconElement_register02"><icon:register02/></span>-->
                            <spring:theme code="general.governmentdocuments.commercialregister" />
                        </div>
                        <hr class="hr" />
                        <div class="row">
                            <div class="col-md-6">
                                <formElement:formInputBox idKey="govDocs.CRN" labelKey="govDocs.CRN"
                                    path="commercialRegisterNumber" inputCSS="form-control" mandatory="true"
                                    labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                            </div>
                            <div class="col-md-6">
                                <formElement:formInputBox idKey="govDocs.gosiNo" labelKey="govDocs.gosiNo"
                                    path="gosiNumber" inputCSS="form-control" mandatory="true"
                                    labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                            </div>
                            <div class="col-md-6">
                                <formElement:formInputBox idKey="govDocs.zakatNo" labelKey="govDocs.zakatNo"
                                    path="zakatNumber" inputCSS="form-control" mandatory="true"
                                    labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                            </div>
                            <div class="col-md-6">
                                <formElement:formInputBox idKey="govDocs.molNo" labelKey="govDocs.molNo"
                                    path="molNumber" inputCSS="form-control" mandatory="true"
                                    labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                            </div>
                        </div>
                    </div>


                    <div class="contentModule-section">
                        <div class="contentModule-headline">
                            <!--<span class="iconElement iconElement_momra"><icon:momra/></span>-->
                            <spring:theme code="govDocs.Momra" />
                        </div>
                        <hr class="hr" />
                        <div class="row">
                            <div class="col">
                                <div class="formRadioButton">
                                    <div class="form-group">

                                        <div class="form-item">
                                            <form:radiobutton path="hasMomra" value="true"
                                                class="form-control gov-docs-momra" onchange="handleMomraRadioChange()"
                                                checked="checked" disabled="${disabledFields}"></form:radiobutton>
                                            <label for="hasMomra1" class="control-label">
                                                <span></span>
                                                <spring:theme code="govDocs.momraY" />
                                            </label>
                                        </div>

                                        <div class="form-item">
                                            <form:radiobutton path="hasMomra" value="false"
                                                class="form-control gov-docs-momra" onchange="handleMomraRadioChange()"
                                                disabled="${disabledFields}"></form:radiobutton>
                                            <label for="hasMomra2" class="control-label">
                                                <span></span>
                                                <spring:theme code="govDocs.momraN" />
                                            </label>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <formElement:formInputBox idKey="govDocs.shopLicNo" labelKey="govDocs.shopLicNo"
                                    path="shopLicenseNumber" inputCSS="form-control gov-docs-shopLicNo" mandatory="true"
                                    labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                            </div>
                            <div class="col-md-6">
                                <formElement:formSelectBox idKey="govDocs.amanah" labelKey="govDocs.amanah"
                                    selectCSSClass="form-control gov-docs-amanah" path="amanah" mandatory="true"
                                    skipBlank="false" itemLabel="name" itemValue="key"
                                    skipBlankMessageKey="form.select.empty" labelCSS="control-label_mandatory" removeSkip="d-none"
                                    items="${amanahs}" disabled="${disabledFields}" />
                            </div>
                        </div>
                    </div>


                    <div class="contentModule-section">
                        <div class="contentModule-headline">
                            <!--  <span class="iconElement iconElement_enquiry"><icon:enquiry2/></span>-->
                            <spring:theme code="general.governmentdocuments.comments" />
                        </div>
                        <hr class="hr" />
                        <div class="row">
                            <div class="col">
                                <formElement:formTextArea areaCSS="form-control" idKey="govDocs.comments"
                                    labelKey="govDocs.comments" path="comments" mandatory="false" labelCSS="govt-service-label-span"
                                    disabled="${disabledFields}" />
                            </div>
                        </div>
                    </div>
                </div>
                </fieldset>
            </div>

            <div class="hidden">
                <formElement:formInputBox idKey="" labelKey="" path="statusCode" inputCSS="hidden" />
            </div>


            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions action_res_view">
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox event="GOVERNMENT_DOCUMENTS" id="termsAndConditions" path="termsAndConditionsChecked" containerCssClass="terms-and-condition" disabled="${disabledTnC}" />
                    </div>
                </div>
                <button type="reset" class="btn btn_outline full-width-responsive"><spring:theme code="services.government.documents.cancel" /></button>
                
                <ycommerce:testId code="nextButton">
                    <button type="submit" class="btn btn-bg btn_bold btn-normal full-width-responsive"><spring:theme code="govDocs.nextButton" /></button>
                </ycommerce:testId>
            </div>

        </form:form>

    </div>
</div>


<div class="modal fade" id="wassel-check" tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close />
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:modal02 />
                </div>
                <div class="modal-description">
                    <c:out value="${wasselCheckMessage}"></c:out>
                </div>
            </div>
            <div class="modal-footer">
                <button id="infoModalClose" type="button" class="btn btn_slim" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>