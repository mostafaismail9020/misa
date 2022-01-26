<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="profileCompany.contactUpdateHistory.title"/>
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
            <!-- <h1 class="mainSection-headline"><spring:theme code="profileCompany.contactUpdateHistory.title"/></h1> -->
            <c:if test="${not empty processingTime}">
                <div class="serviceTime">
                    <div class="serviceTime-label"><spring:theme code="average.service.time" /></div>
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

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <c:url value="/my-sagia/sagia-profile" var="profileUrl"/>
            <div class="row w-100 renewal-services">
                <div class="col-xl-3 col-12 px-0">
                    <a href="${profileUrl}" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="profileCompany.button.back.text"/></a>
                </div>
            </div>            
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <c:if test="${fn:length(contactUpdateHistory) gt 1}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand02">
                <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
            </c:if>
        <div class="expandableContent expanded" id="expand02">
            <c:if test="${fn:length(contactUpdateHistory) gt 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                    <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    <spring:theme code="profileCompany.contactUpdateHistory.history"/></div>
                                <div class="searchInputBox searchInputBox_slim searchInputBox_spaceTop">
                                    <input onkeyup="filterHistory(this)" class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${contactUpdateHistory}" var="contactUpdateEntry" varStatus="contactUpdateStatus">
                                        <li class="historyList-item <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${contactUpdateEntry.srId == first.srId}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${contactUpdateStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">
                                            <a onclick="changeContactUpdateHistoryEntry(this,${contactUpdateEntry.srId})" class="historyList-link">
                                                <div class="historyList-id"><c:out value="${contactUpdateEntry.srId}"/></div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date"><c:out value="${contactUpdateEntry.dateOfUpdate.dateFormatted}"/></div>
                                                    <c:choose>
                                                        <c:when test="${fn:contains(contactUpdateEntry.status, 'E0001')}">
                                                            <div class="historyList-status historyList-status_process">${contactUpdateEntry.statusDescription}</div>
                                                        </c:when>
                                                        <c:when test="${fn:contains(contactUpdateEntry.status, 'E0002')}">
                                                            <div class="historyList-status historyList-status_accepted">${contactUpdateEntry.statusDescription}</div>
                                                        </c:when>
                                                        <c:when test="${fn:contains(contactUpdateEntry.status, 'E0014')}">
                                                            <div class="historyList-status historyList-status_accepted">${contactUpdateEntry.statusDescription}</div>
                                                        </c:when>
                                                        <c:when test="${fn:contains(contactUpdateEntry.status, 'E0006')}">
                                                            <div class="historyList-status historyList-status_rejected">${contactUpdateEntry.statusDescription}</div>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                                <div class="historyList-details">
                                                    <c:if test="${contactUpdateEntry.hasGeneralManagerChange}">
                                                        <spring:theme code="profileCompany.generalManager.title"/><br>
                                                    </c:if>
                                                    <c:if test="${contactUpdateEntry.companyRepresentativesChanges > 0}">
                                                        ${contactUpdateEntry.companyRepresentativesChanges} x <spring:theme code="profileCompany.companyRepresentative.title"/>
                                                    </c:if>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                                    <%--
                                    <div class="emptyIndicator">
                                        No History List
                                    </div>
                                    --%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="expandableContent-main js-support-visits">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule" id="supportVisitDetails">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline contentModule-headline-service-info">
                                    <!-- <icon:info/> -->
                                    <spring:theme code="text.account.followup.info"/>:&nbsp;<span id="contact-update-history-id" class="srId">${first.srId}</span>
                                </div>
                                <c:choose>
                                    <c:when test="${fn:contains(first.status, 'E0001')}">
                                        <div id="currentStatus" class="statusIndicator statusIndicator_inprocess">
                                            <spring:theme code="convertlicense.status"/><span id="statusText">${first.statusDescription}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${fn:contains(first.status, 'E0014')}">
                                        <div id="currentStatus" class="statusIndicator statusIndicator_accepted">
                                            <spring:theme code="convertlicense.status"/><span id="statusText">${first.statusDescription}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${fn:contains(first.status, 'E0006')}">
                                        <div id="currentStatus" class="statusIndicator statusIndicator_rejected">
                                            <spring:theme code="convertlicense.status"/><span id="statusText">${first.statusDescription}</span>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div id="history-container">
                            <!-- CONTACT UPDATE CHANGE-->
                            <c:forEach items="${first.updatedContacts}" var="contactUpdate">
                                <c:set var="attachmentIndex" value="0"/>
                                <div class="contentModule-section">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                        <!-- <div class="contentModule-headline"> -->
                                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">                                            
                                                <c:choose>
                                                    <c:when test="${fn:contains(contactUpdate.contactType, 'GM')}">
                                                        <span class="headline-background"><spring:theme code="profileCompany.generalManager.title"/></span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="headline-background"><spring:theme code="profileCompany.companyRepresentative.title"/></span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        <!-- </div> -->
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <dl class="dlList dlList_separated">
                                                <dt class="headline-golden"><spring:theme code="general.firstname"/></dt>
                                                <dd>${contactUpdate.firstName}</dd>
                                            </dl>
                                        </div>

                                        <div class="col-md-6">
                                            <dl class="dlList dlList_separated">
                                                <dt class="headline-golden"><spring:theme code="general.middlename"/></dt>
                                                <dd>${contactUpdate.middleName}</dd>
                                            </dl>
                                        </div>

                                        <div class="col-md-6">
                                            <dl class="dlList dlList_separated">
                                                <dt class="headline-golden"><spring:theme code="general.lastname"/></dt>
                                                <dd>${contactUpdate.lastName}</dd>
                                            </dl>
                                        </div>

                                        <div class="col-md-6">
                                            <dl class="dlList dlList_separated">
                                                <dt class="headline-golden"><spring:theme code="general.mobilenumber"/></dt>
                                                <dd>${contactUpdate.mobileNumber}</dd>
                                            </dl>
                                        </div>
                                        <div class="col-md-6">
                                            <dl class="dlList dlList_separated">
                                                <dt class="headline-golden"><spring:theme code="general.email"/></dt>
                                                <dd>${contactUpdate.email}</dd>
                                            </dl>
                                        </div>
                                        <div class="col-md-6">
                                            <dl class="dlList dlList_separated">
                                                <dt class="headline-golden"><spring:theme code="general.nationalid"/></dt>
                                                <dd>${contactUpdate.nationality}</dd>
                                            </dl>
                                        </div>
                                    </div>

                                    <div class="documentSection">                                            
                                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                                            <span class="headline-background"><spring:theme code="general.documents"/></span>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="documentSection-item">
                                                    <div class="documentSection-img">
                                                        <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                                    </div>
                                                    <div class="documentSection-name js-attach-file-download"
                                                         data-object-id="${first.attachedDocuments[attachmentIndex].objectId}"
                                                         data-document-id="${first.attachedDocuments[attachmentIndex].documentID}"
                                                         style="cursor: pointer"><spring:theme code="company.representativenationalid"/>
                                                    </div>
                                                    <c:set var="attachmentIndex" value="${attachmentIndex + 1}"/>
                                                </div>
                                            </div>
                                            <c:if test="${!fn:contains(contactUpdate.contactType, 'GM')}">
                                                <div class="col-md-6">
                                                    <div class="documentSection-item">
                                                        <div class="documentSection-img">
                                                            <span class="iconElement iconElement_pdf02"><icon:pdf02/></span>
                                                        </div>
                                                        <div class="documentSection-name js-attach-file-download"
                                                             data-object-id="${first.attachedDocuments[0].objectId}"
                                                             data-document-id="${first.attachedDocuments[0].documentID}"
                                                             style="cursor: pointer"><spring:theme code="company.gosicertificate"/>
                                                        </div>
                                                        <c:set var="attachmentIndex" value="${attachmentIndex + 1}"/>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--CONTACT UPDATE CHANGE END--%>

                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin ">
                                <!-- <div class="contentModule-headline headline-text">
                                    <icon:documents/>
                                    <spring:theme code="profileCompany.supportingDocuments"/>
                                </div> -->
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                                    <span class="headline-background"><spring:theme code="profileCompany.supportingDocuments"/></span>
                                </div>
                                <ul class="downloadList downloadList_maxHeight" id="documents-container">
                                    <c:forEach items="${first.attachedDocuments}" var="document">
                                        <c:url value="/attachment/pdf/${document.objectId}/${document.documentID}" var="attachment"/>
                                        <li class="downloadList-item">
                                            <div class="downloadList-description">
                                                <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                                <c:out value="${document.fileName}"/>
                                            </div>
                                            <div class="downloadList-actions">
                                                <a class="link link_nowrap" href='${attachment}' download>
                                                    <span class="iconElement iconElement_cloud"><icon:download/></span>Download
                                                </a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <%--BEGIN COMMENTS SECTION--%>
                        <c:set var="hasComments"/>
                        <c:forEach items="${first.comments}" var="entry">
                            <c:if test="${not empty entry.comments}">
                                <c:set var="hasComments" value="true"/>
                            </c:if>
                        </c:forEach>
                        <div id="contactUpdateComments"
                             <c:if test="${!hasComments}">hidden</c:if>
                             class="contentModule-section">
                            <div class="contentModule-headline contentModule-headline_small ">
                                <spring:theme code="text.account.followup.comments"/></div>
                            <div class="commentModule">
                                <div class="commentModule-window">
                                    <ul id="contactUpdateCommentsList" class="messageList">
                                        <c:forEach items="${first.comments}" var="entry">
                                            <li class="messageList-item">
                                                <div class="messageList-img">
                                                        <span class="iconElement iconElement_expertProfile_white">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24"
                                                                 height="24"
                                                                 viewBox="0 0 24 24"><path
                                                                    class="iconElement-colorPrimary_fill"
                                                                    d="M12 0c6.627 0 12 5.373 12 12s-5.373 12-12 12-12-5.373-12-12 5.373-12 12-12z"></path><path
                                                                    class="iconElement-colorSecondary_fill"
                                                                    d="M21.086 19.824c-2.201 2.553-5.451 4.176-9.086 4.176-3.567 0-6.761-1.565-8.959-4.036.846-.884 3.309-2.132 6.244-3.323v-1.8c-.166-.118-.343-.292-.512-.533-.39-.555-.63-1.336-.674-2.375-.441-.247-.715-.847-.765-1.503-.051-.676.158-1.267.616-1.538l-.174-.42c-.093-.231-.167-.44-.227-.642-.186-.62-.011-1.034.186-1.436.226-.459.395-.961 1.033-.912 1.205-2.449 6.619-2.044 7.297.671.204.815.776 1.56.44 2.416l-.125.296c.424.312.615.901.56 1.568-.056.656-.329 1.252-.769 1.499-.043 1.04-.284 1.821-.674 2.375-.168.241-.346.415-.512.533v1.8c2.786 1.131 5.143 2.311 6.101 3.184z"></path></svg></span>
                                                </div>
                                                <div class="messageList-content">
                                                    <h2 class="messageList-name">${entry.commentBy}</h2>
                                                    <h3 class="messageList-date">${entry.commentDate.dateFormatted}</h3>
                                                    <div class="messageList-message">
                                                        <p>${entry.comments}</p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <%--END COMMENTS SECTION--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var firstNameLabel = '<spring:theme code="general.firstname"/>';
    var middleNameLabel = '<spring:theme code="general.middlename"/>';
    var lastNameLabel = '<spring:theme code="general.lastname"/>';

    var emailLabel = '<spring:theme code="general.email"/>';
    var mobileNumberLabel = '<spring:theme code="general.mobilenumber"/>';
    var nationalIdLabel = '<spring:theme code="general.nationalid"/>';

    var generalManagerLabel = '<spring:theme code="profileCompany.generalManager.title"/>';
    var companyRepresentativeLabel = '<spring:theme code="profileCompany.companyRepresentative.title"/>';
    var supportingDocumentsLabel = '<spring:theme code="profileCompany.supportingDocuments"/>';
</script>

<script id="contactUpdateComments-template" type="text/template">
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
                <p>{{comments}}</p>
            </div>
        </div>
    </li>
</script>