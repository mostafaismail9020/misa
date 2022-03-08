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

<!-- <div class="mainSection mainSection_white">
    <div class="container">
        <div class="mainSection-header">
            <div class="mainSection-headline">

                <spring:theme code="licenseCancellation.title"/>
            </div>
            <div class="mainSection-action">
                <button class="btn btn_round"><spring:theme code="licenseCancellation.save.draft"/>
                    <icon:save/></button>
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


<div class="container mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
        <div class="row w-100 renewal-services">
            <div class="col-md-3 col-12 px-0">
                <a href="${encodedContextPath}/service-search/FIRST" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
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
                    <button class="btn btn_round"><spring:theme code="licenseCancellation.save.draft"/>
                        <icon:save/></button>
                </div>               
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_noPadding">
    <div class="container">
        <div class="stepModule">
            <div class="stepModule-item done">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step1/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step1_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.request.cancellation.letter"/>
                </div>
            </div>
            <div class="stepModule-item done">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step2/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step2_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.collect.cancellation.docs"/>
                </div>
            </div>
            <div class="stepModule-item done">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step3/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step3_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.submit.cancellation.docs"/>
                </div>
            </div>
            <div class="stepModule-item active">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step4/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step4_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.cancellation.approval"/>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- stage 5 --%>
<div class="mainSection mainSection_white mainSection_smallPaddingTop">
    <div class="container">
        <div class="contentModule">
            <div class="contentModule-section">
                <div class="contentModule-headline">
                    <spring:theme code="licenseCancellation.successful"/>
                </div>

                <%--<div class="licenseCancel-text">--%>
                    <%--<spring:theme code="licenseCancellation.stage5.message01"/>--%>
                <%--</div>--%>
                <%--<button class="btn btn_round"><spring:theme code="licenseCancellation.stage5.button.generate.letters"/></button>--%>
            </div>
            <div class="contentModule-actions contentModule-actions_right">
                <button type="button" class="btn btn-back">
                    <spring:theme code="license.back.dashboard"/>
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="blockCancellation"  tabindex="-1" role="dialog" aria-labelledby="blockCancellation" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%-- <div class="modal-title"><spring:theme code="general.requestsubmitted"/></div> --%>
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