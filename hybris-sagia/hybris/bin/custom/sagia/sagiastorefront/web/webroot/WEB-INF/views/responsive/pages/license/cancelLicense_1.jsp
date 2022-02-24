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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var cancellationMessage = '${licenseCancellationMessage}';
</script>
<c:set var="formName" value="formCancelLicenseStage1"/>

<!-- <div class="mainSection mainSection_white">
    <div class="container">
        <div class="mainSection-header">
            <div class="mainSection-headline">
                <spring:theme code="licenseCancellation.title"/>
            </div>
            <div>
                <button class="btn btn_round btn_slim js-save-draft" data-target-form="${formName}"><spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save"><icon:save/></span>
                </button>

                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if> data-target-form="${formName}"><spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span>
                </button>
            </div>
        </div>
    </div>
</div> -->
<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="licenseCancellation.title"/>
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

<div class="container mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
        <div class="row w-100 renewal-services">
            <div class="col-md-3 col-12 px-0">
                <a href="/service-search/FIRST" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
            </div>
            <c:if test="${fn:length(sagiaService.tabs) > 0}">
                <div class="col-xl-3 col-12 ml-1">
                    <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                </div>
            </c:if>
        </div>	
        <div class="row w-100 mt-4 d-none">
            <div class="mainSection-linkActions mainSection-linkActions_right amend-service-link amend-btns-list">
                <div class="btn-drafts_list">
                    <button class="btn btn_round btn_slim js-save-draft" data-target-form="${formName}"><spring:theme code="general.savedraft"/>
                        <span class="iconElement iconElement_save"><icon:save/></span>
                    </button>
    
                    <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if> data-target-form="${formName}"><spring:theme code="general.loaddraft"/>
                        <span class="iconElement iconElement_save"><icon:upload/></span>
                    </button>
                </div>               
            </div>
        </div>
    </div>
</div>


<div class="mainSection mainSection_noPadding">
    <div class="container">
        <div class="stepModule">
            <div class="stepModule-item active">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step1/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step1_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.request.cancellation.letter"/>
                </div>
            </div>
            <div class="stepModule-item">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step2/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step2_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.collect.cancellation.docs"/>
                </div>
            </div>
            <div class="stepModule-item">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step3/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step3_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.submit.cancellation.docs"/>
                </div>
            </div>
            <div class="stepModule-item">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step4/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step4_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.cancellation.approval"/>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- step1 a --%>
<div class="mainSection mainSection_white">
    <div class="container">
        <form:form action="${encodedContextPath}/my-sagia/license/cancel/stage1/create"
                   id="${formName}"
                   enctype="multipart/form-data"
                   method="post"
                   modelAttribute="licenseClearanceFormData">
            <input type="hidden" id="isInstant" name="isInstant" value="${isInstant}" />
            <div class="contentModule">
            <div class="contentModule-section contentModule-section_paddingSide">
            	<!-- <div class="contentModule-headline">
                        <spring:theme code="licenseCancellation.basic.section"/>
                    </div> -->
                    
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                            <span class="contentModule-headline"><spring:theme code="licenseCancellation.basic.section"/></span>
                            <div class="contentModule-headline-border"></div>
                        </div>
                    </div>
				<div class="row">
					<div class="col-md-6">
						<div class="formInputBox">
							<div class="form-group">
								<form:input path="crNo" cssClass="form-control form-control_crNo validate__numbers-only" placeholder="." id="crNo" value="${not empty crNo ? crNo : ''}" readonly="${not empty crNo ? true : false}" />
								<label class="control-label control-label_mandatory" for="crNo"><spring:theme code="license.cancellation.crNo"/></label>
								<div class="help-block"></div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="formInputBox">
							<div class="form-group">
								<form:input path="zakathNo" cssClass="form-control form-control_zakathNo validate__numbers-only" placeholder="." value="${not empty zakathNo ? zakathNo : ''}" readonly="${not empty zakathNo ? true : false}"/>
								<label class="control-label control-label_mandatory" for="zakathNo"><spring:theme code="license.cancellation.zakathNo"/></label>
								<div class="help-block"></div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
            <c:choose>
            <c:when test ="${isInstant}">
               		<div class="contentModule-section no-data-remove">${licenseCancellationMessage}</div>
                </c:when>
               	<c:otherwise>
                <div class="contentModule-section no-data-remove">
                    <div class="contentModule-headline">
                        <spring:theme code="licenseCancellation.support.documents"/>
                    </div>

                    <div class="row">
                        <c:forEach items="${supportingAttachments}" var="fileToUpload" varStatus="count">
                            <c:set var="counter" value="${counter + 1}" scope="page"/>
                            <div class="col-sm-6">
                                <div class="formInputFile formInputFile_btnBegin">
                                    <div class="form-group">
                                        <input id="file0<c:out value="${count.index+1}"/>" name="files[<c:out value="${count.index}"/>]" data-id="${count.index}" class="form-control js-inputFile" value="" accept="image/jpeg,application/pdf" type="file">
                                        <input id="text0<c:out value="${count.index+1}"/>" name="text0<c:out value="${count.index+1}"/>" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                        <label class="control-label " for="">${fileToUpload.description}</label>
                                        <div class="form-icon form-icon_browse">
                                            <icon:upload/>
                                        </div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset">
                                            <icon:cross/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                </c:otherwise>
                </c:choose>

                <div class="contentModule-actions mainSection-linkActions_flexend px-4 contentModule-actions">
                    <div class="formCheckBox formCheckBox_belowPanel w-100">
                        <div class="form-group">
                            <formElement:termsAndConditionsCheckbox event="LICENSE_SERVICES" id="termsAndConditions1" path="termsAndConditionsChecked"/>
                        </div>
                    </div> 
                    <button type="button" class="btn btn-outline btn-back">
                        <spring:theme code="licenseCancellation.back"/>
                    </button>
                                       
                    <button type="submit" class="btn" <c:if test="${!allowCancellationLetter}">disabled</c:if>>
                        <spring:theme code="licenseCancellation.submit"/>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<div class="modal fade" id="blockCancellation"  tabindex="-1" role="dialog" aria-labelledby="blockCancellation" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%-- <div class="modal-title"><spring:theme code="general.requestsubmitted"/></div> --%>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-description js-description-text">
                <label class="control-label" for="department">${licenseCancellationMessage}</label>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn js-close-btn btn_a" href="${encodedContextPath}/dashboard"><spring:theme code="general.close"/></a>
            </div>
        </div>
    </div>
</div>
