<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
</script>

<input type="hidden" id="serviceId">
<c:set var="formName" value="supportVisit"/>


<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="text.account.followup.supportVisits"/>
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

<!-- <div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="text.account.followup.supportVisitRequest"/></h1>
            <button class="btn btn_round btn_slim js-save-draft"
                    data-target-form="supportVisitForm"
                    data-service-id="${serviceId}">
                <spring:theme code="general.savedraft"/>
                <span class="iconElement iconElement_save"><icon:save/></span>
            </button>

            <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                    data-target-form="supportVisitForm"
                    data-service-id="${serviceId}">
                <spring:theme code="general.loaddraft"/>
                <span class="iconElement iconElement_save"><icon:upload/></span>
            </button>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions my-4 mainSection-linkActions_spaceBetween">
            <div class="row renewal-services w-100">
                <div class="col-xl-3 col-12">
                    <a href="/service-search" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
            </div>
            <div class="row renewal-services w-100 d-none">
                <div class="col-xl-12 col-12 btn-drafts_list amend-service-link amend-btns-list">
                    <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="supportVisitForm"
                        data-service-id="${serviceId}">
                        <spring:theme code="general.savedraft"/>
                        <span class="iconElement iconElement_save"><icon:save/></span>
                    </button>

                    <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="supportVisitForm"
                        data-service-id="${serviceId}">
                        <spring:theme code="general.loaddraft"/>
                        <span class="iconElement iconElement_save"><icon:upload/></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">
            <c:url value="/support-visits/create" var="createUrl"/>
            <form:form method="post" id="supportVisitForm" modelAttribute="supportVisit" action="${createUrl}" enctype="multipart/form-data">
                <div class="appointmentDetails appointmentDetails_forms">
                    <div class="contentModule">
                        <div class="contentModule-section">
                            <div class="contentModule-headline">
                                <!-- <icon:calendarText/> -->
                                <spring:theme code="text.account.followup.supportVisitRequest"/>
                            </div>
                            <hr class="hr" />
                            <div class="row">
                                <div class="col-xs-12 col-md-6">
                                    <div class="formInputBox formInputBox_group">
                                        <div class="form-group <c:if test='${status.error}'>has-error</c:if>">
                                            <form:input path="dateString" cssClass="form-control js-form-control_date js-supportVisit-select-date"/>
                                            <label class="control-label control-label_mandatory" for="dateString">Date:</label>
                                            <div class="formInputBox-append" id="calendar-icon-pos">
                                                <span class="formInputBox-text"><icon:calendar-gray/></span>
                                            </div>
                                        </div>
                                        <div class="help-block">
                                            <span><form:errors path="dateString"/></span>    
                                        </div>
                                    </div>
                                </div>
								<div class="col-md-6">
	                                <div class="formSelectBox">
	                                    <div class="form-group">
	                                        <form:select path="addInfo" cssClass="js-select2-oneColumn">
                                            	<option value="SAG"><spring:theme code="text.account.followup.supportVisitAddInfo.option1"/></option>
											  	<option value="MCI"><spring:theme code="text.account.followup.supportVisitAddInfo.option2"/></option>
											  	<option value="MOL"><spring:theme code="text.account.followup.supportVisitAddInfo.option3"/></option>
											  	<option value="ZAK"><spring:theme code="text.account.followup.supportVisitAddInfo.option4"/></option>
											  	<option value="MOJ"><spring:theme code="text.account.followup.supportVisitAddInfo.option5"/></option>
											  	<option value="COC"><spring:theme code="text.account.followup.supportVisitAddInfo.option6"/></option>
											  	<option value="GDP"><spring:theme code="text.account.followup.supportVisitAddInfo.option7"/></option>
											  	<option value="MOM"><spring:theme code="text.account.followup.supportVisitAddInfo.option8"/></option>                                            
	                                        </form:select>
	                                        <label class="control-label" for="addInfo"><spring:theme code="text.account.followup.supportVisitAddInfo"/></label>
	                                    </div>
	                                    <div class="help-block"></div>
	                                </div>
	                            </div>
                                <div class="col-xs-12 col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group <c:if test='${status.error}'>has-error</c:if>">
                                            <form:input path="message" cssClass="form-control" placeholder="."/>
                                            <label class="control-label control-label_mandatory" for="message">
                                                <spring:theme code="text.account.followup.supportVisitDetails"/>
                                            </label>
                                            <div class="help-block"><span><form:errors path="message"/></span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <c:forEach begin="0" end="3" varStatus="loop">
                                    <div class="col-md-6">
                                        <div class="formInputFile">
                                            <div class="form-group ">
                                                <input id="fileId_${loop.index}" data-id="${loop.index}" name="files[${loop.index}]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                                <input id="fileTextId_${loop.index}" name="fileText[${loop.index}]" class="form-control uploadServiceFile" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                                <label class="control-label"><spring:theme code="text.account.followup.supportVisit.file.upload"/></label>
                                                <input id="dockey_${loop.index}" name="dockeyID[${loop.index}]" type="hidden" value="dockeyID">
                                                <div class="form-icon form-icon_browse">
                                                    <icon:upload/>
                                                </div>
                                                <div class="form-icon form-icon_reset js-inputFile-reset">
                                                    <icon:cross/>
                                                </div>
												<div class="help-block"></div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
							<div><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>

                            <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                <button type="submit" class="btn btn--primary btn--half-radius btn_auto "><spring:theme code="text.account.followup.supportVisitRequest"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="modal fade" id="failInformationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title js-message"><spring:theme code="text.account.followup.error"/></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body modal-body-center">
                <div class="modal-heroImage image-medium">
                    <icon:status-cancelled/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="text.account.followup.close"/></button>
            </div>
        </div>
    </div>
</div>