<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<spring:url value="/special-services/${serviceType}" var="currentUrl" htmlEscape="false"/>
<script>
    var serviceType = '${serviceType}';
    var commentsTitle = '<spring:theme code="text.account.followup.comments"/>';
</script>

<div class="mainSection mainSection bg-white">
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
                    <!-- <div class="calendar notification">
                        <div class="count-notification" id="unreadNotificationSpan"></div>
                        <a href="${encodedContextPath}/my-sagia/notifications">
                            <span></span>
                        </a>
                    </div> -->
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
        <div class="mainSection-header row service-time">
            <!-- <h1 class="mainSection-headline"><spring:theme code="text.specialservices.${serviceType}"/></h1> -->
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
                
                <a id="specialServiceCreateBtn" data-entity-status="${entityStatus}"
                   data-cancel-letter="${cancelLetter}"
                   type="submit"
                   href="${currentUrl}/create" class="btn btn_slim" style="display:none;">
                    <spring:theme code="text.specialservices.create"/>
                </a>
                <input type="hidden" id="specialServiceErrorMsg" value="${specialServiceErrorMsg}"/>
            </div>
        </div>
    </div>
</div> -->


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween d-flex">
            <div class="row renewal-services w-100">
                <div class="col-xl-3 col-md-6 col-12">
                    <!-- <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink back_to_service">
                        <span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="general.backtodashboard"/></a> -->
                        <a href="${encodedContextPath}/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                    </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-md-6 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-xl-3 col-md-6 col-12">
                    <div class="mainSection-linkActions mainSection-linkActions_right">                              
                        <a id="specialServiceCreateBtn" data-entity-status="${entityStatus}"
                            data-cancel-letter="${cancelLetter}"
                            type="submit"
                            href="${currentUrl}/create" class="btn btn_slim back_to_service " style="display:none;">
                            <spring:theme code="text.specialservices.create"/>
                        </a>
                        <input type="hidden" id="specialServiceErrorMsg" value="${specialServiceErrorMsg}"/>
                    </div>                    
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
<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <c:if test="${fn:length(specialServices) > 0}">
            <button class="btn_rightIconLink btn_bold btn_greenLink js-expandContent btn_history" data-expand-target="expand01">
                <div class=""><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div class="hidden"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
        </c:if>
        <div class="expandableContent" id="expand01">
            <c:if test="${fn:length(specialServices) > 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline headline-text text-golden">
                                    <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    <spring:theme code="text.specialservices.history"/></div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input onkeyup="filterHistory(this)" class="searchInputBox-input" type="text"
                                           placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${specialServices}" var="item" varStatus="serviceStatus">
                                        <li class="specialServiceItem historyList-item                                            <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${item.id == specialServiceHeader.id}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${serviceStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">
                                            <a href="javascript:void(0)" class="historyList-link" data-id="${item.id}"
                                               data-type="violation-replies">
                                                <div class="historyList-id">${item.id}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${item.dateData.dateFormatted}</div>
                                                    <div class='historyList-status historyList-status_${fn:replace(fn:toLowerCase(item.status), " " , "")}'>${item.status}</div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="expandableContent-main js-violation-replies">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline applicantList headline-text text-golden special-services-info">
                                    <!-- <icon:info/> -->
                                    <img src="${commonResourcePath}/images/dashboard-media/Setting.png" alt="setting" class="mr-2"/>
                                    <spring:theme
                                            code="text.specialservices.applicants.list"/>:&nbsp;${specialServiceHeader.id}
                                </div>

                                <c:if test="${fn:length(specialServices) > 0}">
                                    <div class="statusIndicator pb-0 statusIndicator_${fn:toLowerCase(specialServiceHeader.status)}" id="currentStatus">
                                        <spring:theme code="text.account.followup.status"/> :
                                        <span id="statusText" class="text-golden">${specialServiceHeader.status}</span>
                                    </div>
                                </c:if>
                            </div>

                            <div class="tableModule">
                                <c:if test="${fn:length(specialServiceHeader.applicants) gt 0}">
                                    <table class="tableModule-table">
                                        <thead class="tableModule-head">
                                        <tr>
                                            <th><spring:theme code="text.specialservices.applicantName"/></th>
                                            <th><spring:theme code="text.specialservices.iqmaNumber"/></th>
                                            <th><spring:theme code="text.specialservices.iqmaExpiryDate"/></th>
                                            <th><spring:theme code="text.specialservices.nationality"/></th>
                                            <th><spring:theme code="text.specialservices.nationalityNote"/></th>
                                            <th><spring:theme code="text.specialservices.applicantProfession"/></th>
                                            <th><spring:theme code="text.specialservices.investorNumber"/></th>
                                            <th><spring:theme code="text.specialservices.applicationCategory"/></th>
                                        </tr>
                                        </thead>
                                        <tbody class="tableModule-body">
                                        <c:forEach items="${specialServiceHeader.applicants}" var="item"
                                                varStatus="countme">
                                            <tr>
                                                <td class="tableModule-bodyItem-right">${item.applicantName}</td>
                                                <td>${item.iqmaNumber}</td>
                                                <td>${item.iqmaExpiryDate}</td>
                                                <td>${item.nationality}</td>
                                                <td>${item.nationalityNote}</td>
                                                <td>${item.applicantProfession}</td>
                                                <td>${item.investorNumber}</td>
                                                <td>${item.applicationCategory}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                                <c:if test="${fn:length(specialServiceHeader.applicants) lt 0}">
                                    <div class="text-center"><span class="h5 text-center">No Data Available</span></div>
                                </c:if>
                            </div>
                            <c:choose>
                            <c:when test="${empty specialServiceHeader.comments}">
                            <div class="contentModule-commentsSection" style="display: none;">
                                </c:when>
                                <c:otherwise>
                                <div class="contentModule-commentsSection" style="display: block;">
                                    </c:otherwise>
                                    </c:choose>
                                    <div class="contentModule-headline contentModule-headline_small "><spring:theme
                                            code="text.account.followup.comments"/></div>
                                    <div class="commentModule">
                                        <div class="commentModule-window">
                                            <ul id="messagesListUL" class="messageList">
                                                <li class="messageList-item">
                                                    <div class="messageList-img">
                                                    <span class="iconElement iconElement_expertProfile_white">
                                                        <icon:expertProfile/>
                                                    </span>
                                                    </div>
                                                    <div class="messageList-content">
                                                        <div class="messageList-message">
                                                            <p id="commentMessage">${specialServiceHeader.comments}</p>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                    <div class="contentModule-headline headline-text text-golden special-services-info">
                                        <!-- <icon:contactPerson/> -->
                                        <img src="${commonResourcePath}/images/dashboard-media/services/Contact Details.png" alt="Contact Details" class="mr-2"/>
                                        <spring:theme code="text.specialservices.contact.details"/>
                                    </div>
                                    <%--<a href="${currentUrl}/edit/${specialServiceHeader.id}"
                                       class="btn btn_link link_nowrap iconElement iconElement_edit02" style="width: 80px">
                                        <icon:edit/> <spring:theme code="text.specialservices.edit"/>
                                    </a>--%>
                                </div>
                            </div>

                            <div class="row specialServicesDetails">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated dlList_marginBottom">
                                        <dt class="headline-golden"><spring:theme code="text.specialservices.region"/></dt>
                                        <dd id="specialServiceRegion">${specialServiceHeader.serviceRegion}</dd>
                                        <dt class="headline-golden"><spring:theme code="text.specialservices.contactphonenumber"/></dt>
                                        <dd id="specialServicesEmail">${specialServiceHeader.email}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated dlList_lineThrough">
                                        <dt class="headline-golden"><spring:theme code="text.specialservices.contactemailaddress"/></dt>
                                        <dd id="specialServicesPhone">${specialServiceHeader.phoneNumber}</dd>
                                    </dl>
                                </div>
                            </div>
                            <div id="specialServiceAttachments" class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin" <c:if test="${empty specialServiceHeader.attachedDocuments}">hidden</c:if>>
                                <div class="contentModule-headline contentModule-headline_small">
                                    <spring:theme code="text.specialservices.attachments"/>
                                </div>
                                <div class="documentModule">
                                    <ul class="downloadList downloadList_secondary specialServicesAttachments">
                                        <c:forEach items="${specialServiceHeader.attachedDocuments}" var="element"
                                                   varStatus="countme">
                                            <li class="downloadList-item js-download-service-attachment"
                                                style="cursor: pointer"
                                                data-attachment-object-id="${element.objectId}"
                                                data-attachment-document-guid="${element.documentGuid}">
                                                <div class="downloadList-description"><span class="iconElement iconElement_pdf">
                                                    <icon:pdf/>
                                                </span>
                                                    <c:choose>
                                                        <c:when test="${not empty element.fileName}">${element.fileName}</c:when>
                                                        <c:otherwise><spring:theme code="services.file.noName"/> </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="downloadList-actions">
                                                    <a href="${encodedContextPath}/special-services/pdf/ATTACHMENTDETAILSSET/${element.objectId}/${element.documentGuid}"
                                                       class="link link_nowrap text-primary-color" download="${element.fullFileName}">
                                                       <span class="iconElement iconElement_cloud02">
                                                            <icon:download/>
                                                       </span>
                                                        <spring:theme code="text.specialservices.download"/>
                                                    </a>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="isEntityStatusValid" value="${isEntityStatusValid}">
    <common:errorModal/>

    <c:if test="${not creationAllowed}">
    <div class="modal fade" id="specialServicesWarning" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true"  show-modal="${not creationAllowed}">
        <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title" id="specialServicesWarningTitle"><spring:theme code="special.services.validation.entityStatus.error"/></div>
                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
						<icon:close/>
					</button>
                </div>
                <div class="modal-body">
                    <div class="modal-description">
                            ${specialServiceErrorMsg}
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim showHistoryBtn" data-dismiss="modal"><spring:theme
                            code="general.close"/></button>
                </div>
            </div>
        </div>
    </div>
    </c:if>


