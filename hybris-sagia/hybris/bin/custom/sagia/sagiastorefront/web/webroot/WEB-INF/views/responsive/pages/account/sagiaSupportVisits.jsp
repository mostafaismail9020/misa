<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>


<div class="mainSection mainSection bg-white">
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

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header service-time">
            <!-- <h1 class="mainSection-headline"><spring:theme code="text.account.followup.supportVisits"/></h1> -->
            <c:if test="${not empty processingTime}">
                <div class="serviceTime">
                    <div class="serviceTime-label"><spring:theme code="average.service.time"/></div>
                    <div class="serviceTime-detail">
                        <c:choose>
                            <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                <span class="serviceTime-highlight">${processingTime.days}</span>
                                <spring:theme code="average.processingTime.days"/>
                                <span class="serviceTime-highlight">${processingTime.hours}</span>
                                <spring:theme code="average.processingTime.hours"/>
                            </c:when>
                            <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                <spring:theme code="average.processingTime.minutes"/>
                                <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                <spring:theme code="average.processingTime.seconds"/>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

                                   
<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div>
                <a href="${encodedContextPath}/support-visits/create" class="btn btn_slim">
                    <spring:theme code="text.account.followup.create"/>
                </a>
            </div>
        </div>
    </div>
</div> -->


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row renewal-services w-100">
                <div class="col-xl-3 col-md-6 col-12 px-0">
                    <a href="${encodedContextPath}/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-xl-3 col-md-6 col-12">
                    <div class="mainSection-linkActions mainSection-linkActions_right">
                        <a href="${encodedContextPath}/support-visits/create" class="btn btn_slim back_to_service">
                            <spring:theme code="text.account.followup.create"/>
                        </a>
                    </div>
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
<div class="mainSection mainSection_dark mainSection_pdt16 mt-5 service-main">
    <div class="container">
        <c:if test="${fn:length(supportVisits) > 0}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div class=""><span><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div class="hidden"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
        </c:if>
        <div class="expandableContent" id="expand01">
            <c:if test="${fn:length(supportVisits) > 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                    <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    <spring:theme code="text.account.followup.history"/></div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input onkeyup="filterHistory(this)" id="convertSearchBox"
                                           class="searchInputBox-input" type="text"
                                           placeholder="<spring:theme code='storeFinder.search'/>">
                                </div>
                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${supportVisits}" var="item" varStatus="letterStatus">
                                        <li class="historyList-item supportVisitItem <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${item.srId == selectedSupportVisit.srId}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${letterStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">
                                            <a href="#"
                                               class="historyList-link js-show-visits-details"
                                               data-id="${item.srId}"
                                               data-type="support-visits">
                                                <div class="historyList-id">${item.srId}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">
                                                            ${item.visitDate.dateFormatted}
                                                    </div>
                                                    <div class="historyList-status
                                                    <c:if test="${item.status == 'E0001'}">historyList-status_process</c:if>
                                                    <c:if test="${item.status == 'E0002'}">historyList-status_accepted</c:if>
                                                    <c:if test="${item.status == 'E0003'}">historyList-status_rejected</c:if>
                                                ">${item.statDesc}</div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <input type="hidden" id="serviceId"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="expandableContent-main js-support-visits">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">

                    <div id="supportVisitDetails" class="contentModule">

                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                    <div class="contentModule-headline headline-text">
                                        <!-- <icon:info/> -->

                                        <spring:theme code="text.account.followup.info"/>:&nbsp;<span id="supportVisitId">${selectedSupportVisit.srId}</span>
                                    </div>
                                </div>
                            </div>

                            <div class="tableModule">
                                <c:if test="${fn:length(supportVisits) gt 0}">
                                    <table class="tableModule-table">
                                        <thead class="tableModule-head">
                                        <tr>
                                            <th><spring:theme code="text.account.followup.supportVisitsDate"/></th>
                                            <th><spring:theme code="text.account.followup.description"/></th>
                                        </tr>
                                        </thead>
                                        <tbody class="tableModule-body">
                                            <tr>
                                                <td><span
                                                        id="supportVisitDate">${selectedSupportVisit.visitDate.dateFormatted}</span>
                                                </td>
                                                <td class="tableModule-bodyItem-left"><span
                                                        id="supportVisitMsg">${selectedSupportVisit.textMsg}</span></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </c:if>
                                <c:if test="${fn:length(supportVisits) lt 1}">
                                    <div class="text-center"><span class="h5 text-center">No Data Available</span></div>
                                </c:if>
                            </div>

                    </div>
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                        <div class="commentModule commentModule-no-border">
                        </div>
                    </div>
                    <c:set var="hasComments"/>
                        <c:forEach items="${selectedSupportVisit.suppVisitToText}" var="entry">
                            <c:if test="${not empty entry.tdline}">
                                <c:set var="hasComments" value="true"/>
                            </c:if>
                        </c:forEach>
                    <div id="supportVisitsComments"
                         <c:if test="${!hasComments}">hidden</c:if>
                         class="contentModule-section">
                        <div class="contentModule-headline contentModule-headline_small headline-text"><spring:theme
                                code="text.account.followup.comments"/></div>
                        <div class="commentModule">
                            <div class="commentModule-window">
                                <ul id="supportVisitsCommentsList" class="messageList">
                                    <c:forEach items="${selectedSupportVisit.suppVisitToText}" var="entry">
                                        <li class="messageList-item">
                                            <div class="messageList-img">
                                                    <span class="iconElement iconElement_expertProfile_white">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                             viewBox="0 0 24 24"><path
                                                                class="iconElement-colorPrimary_fill"
                                                                d="M12 0c6.627 0 12 5.373 12 12s-5.373 12-12 12-12-5.373-12-12 5.373-12 12-12z"></path><path
                                                                class="iconElement-colorSecondary_fill"
                                                                d="M21.086 19.824c-2.201 2.553-5.451 4.176-9.086 4.176-3.567 0-6.761-1.565-8.959-4.036.846-.884 3.309-2.132 6.244-3.323v-1.8c-.166-.118-.343-.292-.512-.533-.39-.555-.63-1.336-.674-2.375-.441-.247-.715-.847-.765-1.503-.051-.676.158-1.267.616-1.538l-.174-.42c-.093-.231-.167-.44-.227-.642-.186-.62-.011-1.034.186-1.436.226-.459.395-.961 1.033-.912 1.205-2.449 6.619-2.044 7.297.671.204.815.776 1.56.44 2.416l-.125.296c.424.312.615.901.56 1.568-.056.656-.329 1.252-.769 1.499-.043 1.04-.284 1.821-.674 2.375-.168.241-.346.415-.512.533v1.8c2.786 1.131 5.143 2.311 6.101 3.184z"></path></svg></span>
                                            </div>
                                            <div class="messageList-content">
                                                <h2 class="messageList-name">${entry.commentsBy}</h2>
                                                <h3 class="messageList-date">${entry.commentDate.dateFormatted}</h3>
                                                <div class="messageList-message">
                                                    <p>${entry.tdline}</p>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>

                <!--Begin support documents section-->
                <div id="supportVisitDocuments"
                     <c:if test="${empty selectedSupportVisit.supportVstToAttachNav}">hidden</c:if>>
                    <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">

                                <div class="contentModule contentModule-wrap">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                        <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                        <div class="contentModule-headline-border"></div>
                                    </div>
                                </div>

                                <ul id="supportVisitsAttachmentList" class="downloadList downloadList_maxHeight">
                                    <c:forEach items="${selectedSupportVisit.supportVstToAttachNav}" var="attachment">
                                        <li class="downloadList-item">
                                            <div id="supportVisitsAttachment_${attachment.fileName}"
                                                 class="downloadList-description">
                                                <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                                    ${attachment.fileName}
                                            </div>
                                            <div class="downloadList-actions">
                                                <a href="${encodedContextPath}/support-visits/download/${attachment.objectId}/${attachment.fileGuid}?mimeType=${attachment.mimeType}"
                                                   class="link link_nowrap" download="${attachment.fullFileName}">
                                                    <span class="iconElement iconElement_cloud"><icon:download/></span><spring:theme
                                                        code="text.account.followup.download"/>
                                                </a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!--End support documents section-->
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="failInformationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
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
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme
                        code="text.account.followup.close"/></button>
            </div>
        </div>
    </div>
</div>


<script id="supportVisitAttachments-template" type="text/template">
    <li class="downloadList-item">
        <div id="supportVisitsAttachment_{{fileName}}"
             class="downloadList-description">
            <span class="iconElement iconElement_pdf"><icon:pdf/></span>
            {{fileName}}
        </div>
        <div class="downloadList-actions">
            <a href="${encodedContextPath}/support-visits/download/{{objectId}}/{{fileGuid}}?mimeType={{mimeType}}"
               class="link link_nowrap" download="{{fullFileName}}">
                <span class="iconElement iconElement_cloud"><icon:download/></span><spring:theme
                    code="text.account.followup.download"/>
            </a>
        </div>
    </li>
</script>

<script id="supportVisitComments-template" type="text/template">
    <li class="messageList-item">
        <div class="messageList-img">
                                                <span class="iconElement iconElement_expertProfile_white">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                         viewBox="0 0 24 24"><path class="iconElement-colorPrimary_fill"
                                                                                   d="M12 0c6.627 0 12 5.373 12 12s-5.373 12-12 12-12-5.373-12-12 5.373-12 12-12z"></path><path
                                                            class="iconElement-colorSecondary_fill"
                                                            d="M21.086 19.824c-2.201 2.553-5.451 4.176-9.086 4.176-3.567 0-6.761-1.565-8.959-4.036.846-.884 3.309-2.132 6.244-3.323v-1.8c-.166-.118-.343-.292-.512-.533-.39-.555-.63-1.336-.674-2.375-.441-.247-.715-.847-.765-1.503-.051-.676.158-1.267.616-1.538l-.174-.42c-.093-.231-.167-.44-.227-.642-.186-.62-.011-1.034.186-1.436.226-.459.395-.961 1.033-.912 1.205-2.449 6.619-2.044 7.297.671.204.815.776 1.56.44 2.416l-.125.296c.424.312.615.901.56 1.568-.056.656-.329 1.252-.769 1.499-.043 1.04-.284 1.821-.674 2.375-.168.241-.346.415-.512.533v1.8c2.786 1.131 5.143 2.311 6.101 3.184z"></path></svg></span>
        </div>
        <div class="messageList-content">
            <h2 class="messageList-name">{{commentsBy}}</h2>
            <h3 class="messageList-date">{{dateFormatted}}</h3>
            <div class="messageList-message">
                <p>{{tdline}}</p>
            </div>
        </div>
    </li>
</script>