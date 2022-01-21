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
                    <div class="calendar notification">
                        <div class="count-notification">123</div>
                        <a href="${encodedContextPath}/my-sagia/notifications">
                            <span></span>
                        </a>
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
        <div class="mainSection-header">
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

<div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
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
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span
                    class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme
                    code="general.backtodashboard"/></a>
            <c:if test="${fn:length(specialServices) > 0}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent"
                        data-expand-target="expand01">
                    <div class="hidden"><spring:theme
                            code="text.specialservices.showServiceHistory"/><span>&#x27f6;</span></div>
                    <div><spring:theme code="text.specialservices.hideServiceHistory"/><span
                            class="iconElement iconElement_closeBack"><icon:close/></span></div>
                </button>
            </c:if>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(specialServices) > 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_history"><icon:history/></span>
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
                                <div class="contentModule-headline applicantList">
                                    <icon:info/>
                                    <spring:theme
                                            code="text.specialservices.applicants.list"/>:&nbsp;${specialServiceHeader.id}
                                </div>

                                <c:if test="${fn:length(specialServices) > 0}">
                                    <div class="statusIndicator statusIndicator_${fn:toLowerCase(specialServiceHeader.status)}">
                                        <spring:theme code="text.account.followup.status"/> :
                                        <span>${specialServiceHeader.status}</span>
                                    </div>
                                </c:if>
                            </div>

                            <div class="tableModule">
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
                                    <div class="contentModule-headline">
                                        <icon:contactPerson/>
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
                                        <dt><spring:theme code="text.specialservices.region"/></dt>
                                        <dd id="specialServiceRegion">${specialServiceHeader.serviceRegion}</dd>
                                        <dt><spring:theme code="text.specialservices.contactphonenumber"/></dt>
                                        <dd id="specialServicesEmail">${specialServiceHeader.email}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated dlList_lineThrough">
                                        <dt><spring:theme code="text.specialservices.contactemailaddress"/></dt>
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
                                                       class="link link_nowrap" download="${element.fullFileName}"><span class="iconElement iconElement_cloud02">
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


