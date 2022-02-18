<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
</script>

<spring:url value="/special-services/${serviceType}" var="returnUrl" htmlEscape="false"/>


<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="text.specialservices.${serviceType}"/>
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense or hasAwaitingPayment}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                    <div class="calendar notification p-0">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png" class="notification_b2b_img"/>
                            </button>
                        </c:if>
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
<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
    <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
    <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
    <div class="sagiaNavigation-subPane-actions">
        <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
    </div>
</div>

<!-- <div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="text.specialservices.${serviceType}"/></h1>
            <div>
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="specialServiceHeaderId"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save"><icon:save/></span>
                </button>
                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="specialServiceHeaderId"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span>
                </button>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row renewal-services w-100">
                <div class="col-xl-3 col-12">
                   <a href="${returnUrl}" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="specialservices.back.to.service.details"/></a>
                </div>
                <div class="col-xl-9 col-12 btn-drafts_list amend-service-link">
                    <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="specialServiceHeaderId"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save"><icon:save/></span>
                </button>
                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="specialServiceHeaderId"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span>
                </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16 mt-5 service-main">
    <div class="container">
        <c:if test='${serviceType eq "transfer-of-iqama" or serviceType eq "final-exit-visa"}'>
            <c:if test='${fn:length(existingApplicants) gt 0}'>
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin mt-3">
                    <div class="contentModule">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                            <!-- <div class="contentModule-headline">
                                <icon:person/>
                                <spring:theme code="text.specialservices.applicants.list"/>
                                <c:if test="${not empty specialServiceHeader.id}">: ${specialServiceHeader.id}</c:if>
                            </div> -->
                            <span class="headline-background"><spring:theme code="text.specialservices.applicants.list"/> <c:if test="${not empty specialServiceHeader.id}">: ${specialServiceHeader.id}</c:if> </span>
                        </div>
                        <div class="tableModule tableModule_slim">
                            <table id="existingApplicants" class="tableModule-table">
                                <thead class="tableModule-head">
                                <tr>
                                    <th style="width: 25px;"></th>
                                    <th><spring:theme code="text.specialservices.applicantName"/></th>
                                    <th><spring:theme code="text.specialservices.iqmaNumber"/></th>
                                    <th><spring:theme code="text.specialservices.iqmaExpiryDate"/></th>
                                    <th><spring:theme code="text.specialservices.nationality"/></th>
                                    <th><spring:theme code="text.specialservices.nationalityNote"/></th>
                                    <th><spring:theme code="text.specialservices.applicantProfession"/></th>
                                    <th><spring:theme code="text.specialservices.investorNumber"/></th>
                                </tr>
                                </thead>
                                <tbody class="tableModule-body jqApplicantsTable">
                                <c:forEach items="${existingApplicants}" var="item" varStatus="countme">
                                    <tr>
                                        <td>
                                            <div class="formRadioButton">
                                                <div class="form-group">
                                                    <div class="form-item">
                                                        <input id="applicant${fn:replace(item.applicantName,' ', '')}${item.iqmaNumber}${item.investorNumber}" name="applicant" class="form-control jqApplicant" value="${item.iqmaNumber}" type="radio">
                                                        <label for="applicant${fn:replace(item.applicantName,' ', '')}${item.iqmaNumber}${item.investorNumber}" class="control-label"><span></span></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <label class="applicant-applicantName" for="applicant${fn:replace(item.applicantName,' ', '')}${item.iqmaNumber}${item.investorNumber}">${item.applicantName}</label>
                                        </td>
                                        <td class="applicant-iqmaNumber">${item.iqmaNumber}</td>
                                        <td class="applicant-iqmaExpiryDate">${item.iqmaExpiryDate}</td>
                                        <td class="applicant-nationality">${item.nationality}</td>
                                        <td class="applicant-nationalityNote">${item.nationalityNote}</td>
                                        <td class="applicant-applicantProfession">${item.applicantProfession}</td>
                                        <td class="applicant-investorNumber">${item.investorNumber}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:if>
        <div class="panelModule panelModule_halfRadius panelModule_smallMargin mt-3">
            <div class="contentModule">
                <form:form method="post" action="${encodedContextPath}/special-services/${serviceType}/add-applicant" modelAttribute="serviceApplicant">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <!-- <div class="contentModule-headline">
                            <icon:info/> <spring:theme code="text.specialservices.applicants.details"/>
                        </div> -->
                        <div class="contentModule-actions contentModule-actions_wrap headline-background-wrapper">
                            <span class="headline-background"><spring:theme code="text.specialservices.applicants.details"/></span>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <form:input path="applicantName" cssClass="form-control" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="applicantName">
                                            <spring:theme code="text.specialservices.applicantName"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <form:input path="iqmaNumber" cssClass="form-control" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="iqmaNumber">
                                            <spring:theme code="text.specialservices.iqmaNumber"/>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formInputBox formInputBox_group ">
                                    <div class="form-group">
                                        <form:input path="iqmaExpiryDate" cssClass="form-control js-form-control_date flatpickr-input" placeholder="."/>
                                        <label class="control-label" for="iqmaExpiryDate">
                                            <spring:theme code="text.specialservices.iqmaExpiryDate"/>
                                        </label>
                                        <div class="formInputBox-append">
                                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select path="nationality" cssClass="js-select2-oneColumn service js-select2-searchBegining js-select2-sortAlphabetically">
                                            <option></option>
                                            <form:options items="${countries}" itemValue="${not empty itemValue ? itemValue :'code'}" itemLabel="${not empty itemLabel ? itemLabel :'name'}" htmlEscape="true"/>
                                        </form:select>
                                        <label class="control-label" for="nationality"><spring:theme code="text.specialservices.nationality"/></label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <form:input path="nationalityNote" cssClass="form-control" placeholder="."/>
                                        <label class="control-label" for="nationalityNote">
                                            <spring:theme code="text.specialservices.nationalityNoteLabel"/>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <form:input path="applicantProfession" cssClass="form-control" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="applicantProfession">
                                           <spring:theme code="text.specialservices.applicantProfession"/>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <form:input path="investorNumber" cssClass="form-control" placeholder="."/>
                                        <label class="control-label" for="investorNumber">
                                            <spring:theme code="text.specialservices.investorNumber"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test='${serviceType eq "exit-re-entry-visa" or serviceType eq "renewal-of-iqama"}'>
                            <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                <button class="btn add-applicant" type="submit"><spring:theme code="general.add"/></button>
                            </div>
                        </c:if>
                    </div>
                </form:form>
            </div>
        </div>

        <form:form id="specialServiceHeaderId" method="post" data-service-id="${formName}" modelAttribute="specialServiceHeader" enctype="multipart/form-data">
            <c:if test='${serviceType eq "exit-re-entry-visa" or serviceType eq "renewal-of-iqama"}'>
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                                <!-- <div class="contentModule-headline">
                                    <icon:person/>
                                    <spring:theme code="text.specialservices.applicants.list"/>
                                    <c:if test="${not empty specialServiceHeader.id}">: ${specialServiceHeader.id}</c:if>
                                </div> -->
                                <span class="headline-background"><spring:theme code="text.specialservices.applicants.list"/><c:if test="${not empty specialServiceHeader.id}">: ${specialServiceHeader.id}</c:if></span>
                            </div>

                            <div class="tableModule tableModule_slim">
                                <table id="applicantTable" class="tableModule-table jqApplicantsTable">
                                    <thead class="tableModule-head">
                                    <tr>
                                        <th style="width: 25px;"></th>
                                        <th><spring:theme code="text.specialservices.applicantName"/></th>
                                        <th><spring:theme code="text.specialservices.iqmaNumber"/></th>
                                        <th><spring:theme code="text.specialservices.iqmaExpiryDate"/></th>
                                        <th><spring:theme code="text.specialservices.nationality"/></th>
                                        <th><spring:theme code="text.specialservices.nationalityNote"/></th>
                                        <th><spring:theme code="text.specialservices.applicantProfession"/></th>
                                        <th><spring:theme code="text.specialservices.investorNumber"/></th>
                                    </tr>
                                    </thead>
                                    <c:forEach items="${serviceApplicants}" var="item" varStatus="countme">
                                        <tr>
                                            <td>
                                                <div class="formRadioButton">
                                                    <div class="form-group">
                                                        <div class="form-item">
                                                            <input id="applicant${fn:replace(item.applicantName,' ', '')}${item.iqmaNumber}${item.investorNumber}" name="applicant" class="form-control" value="${item.iqmaNumber}" type="radio">
                                                            <label for="applicant${fn:replace(item.applicantName,' ', '')}${item.iqmaNumber}${item.investorNumber}" class="control-label"><span></span></label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <label for="applicant${fn:replace(item.applicantName,' ', '')}${item.iqmaNumber}${item.investorNumber}">${item.applicantName}</label>
                                            </td>
                                            <td>${item.iqmaNumber}</td>
                                            <td>${item.iqmaExpiryDate}</td>
                                            <td>${item.nationality}</td>
                                            <td>${item.nationalityNote}</td>
                                            <td>${item.applicantProfession}</td>
                                            <td>${item.investorNumber}</td>
                                            <td>${item.applicationCategory}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <!-- <div class="contentModule-headline">
                            <icon:contact-details/>
                            <spring:theme code="text.specialservices.contact.details"/>
                        </div> -->
                        <div class="contentModule-actions contentModule-actions_wrap headline-background-wrapper">
                            <span class="headline-background"><spring:theme code="text.specialservices.contact.details"/></span>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select path="serviceRegion" cssClass="js-select2-oneColumn region">
                                            <option></option>
                                            <form:options items="${regions}" itemValue="${not empty itemValue ? itemValue :'code'}" itemLabel="${not empty itemLabel ? itemLabel :'name'}" htmlEscape="true"/>
                                        </form:select>
                                        <label class="control-label" for="serviceRegion">
                                            <spring:theme code="text.specialservices.region"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <form:input path="phoneNumber" cssClass="form-control" placeholder="."/>
                                        <label class="control-label" for="phoneNumber">
                                            <spring:theme code="text.specialservices.contactphonenumber"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <form:input path="email" cssClass="form-control" placeholder="."/>
                                        <label class="control-label" for="email">
                                            <spring:theme code="text.specialservices.contactemailaddress"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <c:if test='${serviceType eq "transfer-of-iqama"}'>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <form:input path="company" cssClass="form-control" placeholder="."/>
                                            <label class="control-label" for="company">
                                                <spring:theme code="text.specialservices.sponsor"/>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <form:input path="CRNumber" cssClass="form-control" placeholder="."/>
                                            <label class="control-label" for="CRNumber">
                                                <spring:theme code="text.specialservices.crnumber"/>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <form:input path="profession" cssClass="form-control" placeholder="."/>
                                            <label class="control-label" for="profession">
                                                <spring:theme code="text.specialservices.profession"/>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="contentModule-separator"></div>
                        <!-- <div class="contentModule-headline">
                            <icon:enquiry3/>
                            <spring:theme code="text.specialservices.reasonforapplication"/>
                        </div> -->
                        <div class="contentModule-actions contentModule-actions_wrap headline-background-wrapper">
                            <span class="headline-background"><spring:theme code="text.specialservices.reasonforapplication"/></span>
                        </div>
                        <div class="formTextArea">
                            <div class="form-group">
                                <form:textarea path="applicationReason" cssClass="form-control" placeholder="."/>
                                <label class="control-label control-label_mandatory" for="applicationReason">
                                    <spring:theme code="text.specialservices.reasonforapplication"/>
                                </label>
                            </div>
                        </div>
                        <div class="contentModule-separator"></div>
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                            <!-- <div class="contentModule-headline">
                                <icon:documents/>
                                <spring:theme code="text.specialservices.attachments"/>
                            </div> -->
                            
                            <span class="headline-background"><spring:theme code="text.specialservices.attachments"/></span>
                          
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <c:forEach items="${attachments}" var="item" varStatus="countme">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input name="fileNames[${countme.index}]" value="${item.descriptionPass}" type="hidden">
                                            <input name="files[${countme.index}]" id="fileToUpload${countme.index}" class="form-control js-inputFile" value="" type="file" data-id="${countme.index}">
                                            <input class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label control-label_mandatory">${item.description}</label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
											<div class="help-block"></div>
                                        </div>
                                    </div>
                                </c:forEach>
								 <div><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>
                            </div>
                            <div class="col-sm-6">
                                <div class="undertakingLetter">
                                    <div class="undertakingLetter-text">
                                        <p><spring:theme code="specialservices.upload.required.documents.download"/><a href="javascript:;" class="downloadUndertakingLetterButton"><spring:theme code="specialservices.undertaking.letter"/></a> </p>
                                        <p><spring:theme code="specialservices.upload.required.documents"/></p>
                                    </div>
                                    <button type="button" id="downloadUndertakingLetterButton" class="btn btn_leftIconLink btn_outline downloadUndertakingLetterButton">
                                        <spring:theme code="text.account.followup.download"/> <icon:download/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions">
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox event="SPECIAL_SERVICES" id="termsAndConditions" path="termsAndConditionsChecked" containerCssClass="terms-and-condition"/>
                    </div>
                </div>
                <button type="button" class="btn btn_leftIconLink btn_outline full-width-responsive"><spring:theme code="general.cancel"/></button>
               
                <button type="submit" class="btn full-width-responsive ${serviceType}"><spring:theme code="general.submit"/></button>
            </div>
        <input type="hidden" id="serviceId"/>
        <input type="hidden" name="csrfToken" value="${_csrf.token}" />
        </form:form>
    </div>
</div>

<div class="modal fade" id="requestSubmitted3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="specialservices.requestsubmitted"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:modal02/>
                </div>
                <div class="modal-description">
                    <spring:theme code="specialservices.wewillreviewmessage"/>
                </div>
            </div>
            <div class="modal-footer">
                <a href="${returnUrl}" class="btn btn_slim"><spring:theme code="specialservices.returntoservices"/></a>
            </div>
        </div>
    </div>
</div>
<table class="applicantTemplate" style="display: none;">
    <tr class="table-item">
        <td><a href="javascript:void(0)" class="jqRemove" data-guid=""><icon:remove/></a></td>
        <td class="jqApplicantName"></td>
        <td class="jqIqmaNumber"></td>
        <td class="jqIqmaExpiryDate"></td>
        <td class="jsNationality"></td>
        <td class="jqNationalityNote"></td>
        <td class="jqApplicantProfession"></td>
        <td class="jqInvestorNumber"></td>
    </tr>
</table>

<script>
    var specialServiceHeaderJson = '';
    <c:if test="${not empty specialServiceHeaderJson}">
        specialServiceHeaderJson = ${specialServiceHeaderJson};
    </c:if>

    var serviceType = '';
    <c:if test="${not empty serviceType}">
        serviceType = '${serviceType}';
    </c:if>
</script>
