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
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
    var isInstant = ${isInstant};
</script>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="convertlicense.converttonational" />
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
            <h1 class="mainSection-headline"><spring:theme code="convertlicense.converttonational"/></h1>
            <div>
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="formNewConvertToNationals"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save"><icon:save/></span>
                </button>

                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="formNewConvertToNationals"
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
            <!-- <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/></a> -->
            <div class="row w-100 renewal-services">
                <div class="col-md-3 col-12 px-0">
                    <a href="/service-search" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
            </div>
            <div class="row w-100 mt-4 d-none">
                <div class="mainSection-linkActions mainSection-linkActions_right amend-service-link">
                    <button class="btn btn_round btn_slim js-save-draft" data-target-form="formNewConvertToNationals" data-service-id="${serviceId}">
                        <spring:theme code="general.savedraft"/>
                        <span class="iconElement iconElement_save"><icon:save/></span>
                    </button>
                    <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                            data-target-form="formNewConvertToNationals"
                            data-service-id="${serviceId}">
                        <spring:theme code="general.loaddraft"/>
                        <span class="iconElement iconElement_save"><icon:upload/></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <div class="expandableContent" id="service-tab">
            
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16 mt-3">
    <div class="container">
        <form:form action="${encodedContextPath}/convertToNationals/create" enctype="multipart/form-data" method="post" id="formNewConvertToNationals" modelAttribute="convertToNationalsFormData">
            <input type="hidden" id="isInstant" name="isInstant" value="${isInstant}" />
            <div class="panelModule panelModule_halfRadius">
                <div class="contentModule">
                <c:choose>
               <c:when test ="${isInstant}">
               		<div class="contentModule-section">
                    	<div class="statusBox-description">
                    		<div><spring:theme code="converttonational.instant.message"/></div>
    					</div>
                    </div>
                </c:when>
               	<c:otherwise>
                    <div class="contentModule-section">
                        <!-- <div class="contentModule-headline">
                            <spring:theme code="text.account.followup.supportDocuments"/>
                        </div> -->
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                        </div>
                        <div class="row">
                            <c:forEach items="${attachments}" var="attachment" varStatus="attachmentStatus">
                                <div class="col-md-6">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input id="file${attachmentStatus.index}" name="files[${attachmentStatus.index}]"
                                                   data-id="${attachmentStatus.index}" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value="" <c:if test="${empty attachment.optionalAttach}">data-mandatory</c:if>>
                                            <input id="text${attachmentStatus.index}" name="text${attachmentStatus.index}" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                            <label class="control-label <c:if test="${empty attachment.optionalAttach}">control-label_mandatory</c:if>" for="file${attachmentStatus.index}">${attachment.description}</label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset js-change-after-reset">
                                                <icon:cross/>
                                            </div>											
											<div class="help-block"></div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
						<div><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>
                    </div>
                </c:otherwise>
                </c:choose>
                </div>
            </div>

            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions">
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox path="termsAndConditionsChecked" event="LICENSE_SERVICES" id="checkbox01" name="checkbox01name" cssClass="js-terms-agree"/>
                    </div>
                </div>
                <button type="reset" class="btn btn_leftIconLink btn_outline" id="cancelNewConvertToNationalsButton">   <%-- TODO for MinIon: find proper way to reset form --%>
                    <spring:theme code="general.cancel"/>
                </button>
                
                <button id="convertSubmitButton" type="submit" value = "SUBMIT" class="btn" disabled="disabled">
                    <spring:theme code="general.submit"/>
                </button>
            </div>
        </form:form>

        <input type="hidden" name="csrfToken" value="${_csrf.token}"/>
        <input type="hidden" id="serviceId"/>
    </div>
</div>