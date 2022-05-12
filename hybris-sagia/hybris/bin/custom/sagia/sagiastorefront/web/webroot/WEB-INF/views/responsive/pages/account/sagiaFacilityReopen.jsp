<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    var createRequestEnabled = ${reopenFacilityRequests.createRequestEnabled};
</script>



<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="facilityReopen.title"/>
                </h1>
            </div>
            <div class="profile-icons">
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
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img" alt="message"/>
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

<!-- 
<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="facilityReopen.title"/></h1>
        </div>
    </div>
</div> -->

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <c:if test="${reopenFacilityRequests.createRequestEnabled}">
                <a href="${encodedContextPath}/facility-reopen/create"
                   class="btn btn_slim">
                    <spring:theme code="text.specialservices.create"/>
                </a>
            </c:if>
            <c:if test="${not reopenFacilityRequests.createRequestEnabled}">
                <span class="iconElement iconElement_headlineTooltip js-tip"
                      data-tip-title="${serviceDescription}"
                      data-original-title="" title="">
                 <icon:tipInfo/>
                 </span>
                <a href="javascript:;"
                   class="btn btn_slim" disabled="disabled">
                    <spring:theme code="text.specialservices.create"/>
                </a>
            </c:if>
        </div>
    </div>
</div> -->



<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row renewal-services w-100">
                <div class="col-md-3 col-12 px-0">
                    <a href="${encodedContextPath}/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-xl-6 col-12 d-flex">
                    <c:if test="${reopenFacilityRequests.createRequestEnabled}">
                        <a href="${encodedContextPath}/facility-reopen/create"
                           class="btn btn_slim">
                            <spring:theme code="text.specialservices.create"/>
                        </a>
                    </c:if>
                    <c:if test="${not reopenFacilityRequests.createRequestEnabled}">
                        <div class="col-xl-1 col-1">
                        <span class="iconElement iconElement_headlineTooltip js-tip"
                              data-tip-title="${serviceDescription}"
                              data-original-title="" title="">
                         <icon:tipInfo/>
                         </span>
                        </div>
                        <a href="javascript:;"
                           class="btn btn_slim" disabled="disabled">
                            <spring:theme code="text.specialservices.create"/>
                        </a>
                    </c:if>         
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
        <c:if test="${fn:length(reopenFacilityRequests.serviceRequests) gt 0}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand02">
                <div class=""><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div class="hidden"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
            </c:if>
        <div class="expandableContent mt-3" id="expand01">
            <c:if test="${fn:length(reopenFacilityRequests.serviceRequests) gt 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                    <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    <spring:theme code="text.account.followup.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul class="historyList" id="history-list">
                                    <c:forEach items="${reopenFacilityRequests.serviceRequests}" var="item"
                                               varStatus="countme">
                                        <li class="historyList-item <c:if test="${countme.index eq 0}">historyList-item_current</c:if>">
                                            <a href="javascript:;" class="historyList-link"
                                               data-srid="${item.objectId}">
                                                <div class="historyList-id">${item.objectId}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${item.createdDateData.dateFormatted}</div>
                                                    <div class="historyList-status historyList-status_${item.statusKey}">${item.statusText}</div>
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
            <div class="expandableContent-main">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper w-100">
                                <!-- <div class="contentModule-headline">
                                    <icon:info/>
                                    <spring:theme code="facilityReopen.entityInformation.text"/>
                                </div> -->
                                <span class="headline-background"><spring:theme code="facilityReopen.entityInformation.text"/></span>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList">
                                        <dt data-name="text"><spring:theme
                                                code="facilityReopen.serviceRequestNumber.text"/></dt>
                                        <dd>
                                            <div name="text" class="jqSrId">${reopenFacility.srId}</div>
                                        </dd>
                                    </dl>
                                </div>
                                <div class="col-md-3">
                                    <dl class="dlList">
                                        <dt data-name="street"><spring:theme code="facilityReopen.street.text"/></dt>
                                        <dd><span name="street">${reopenFacilityRequests.street}</span></dd>
                                        <dt data-name="houseNo"><spring:theme code="facilityReopen.number.text"/></dt>
                                        <dd><span name="houseNo">${reopenFacilityRequests.houseNumber}</span></dd>
                                        <dt data-name="city"><spring:theme code="facilityReopen.city.text"/></dt>
                                        <dd><span name="city">${reopenFacilityRequests.city}</span></dd>
                                        <dt data-name="additionalNotes"><spring:theme
                                                code="facilityReopen.additionalNo.text"/></dt>
                                        <dd><span
                                                name="additionalNotes">${reopenFacilityRequests.additionalNumber}</span>
                                        </dd>
                                    </dl>
                                </div>
                                <div class="col-md-3">
                                    <dl class="dlList">
                                        <dt><spring:theme code="facilityReopen.country.text"/></dt>
                                        <dd><span name="country">${reopenFacilityRequests.countryDescription}</span>
                                        </dd>
                                        <dt><spring:theme code="facilityReopen.postalCode.text"/></dt>
                                        <dd><span name="zipCode">${reopenFacilityRequests.zipCode}</span></dd>
                                        <dt><spring:theme code="facilityReopen.buildingNo.text"/></dt>
                                        <dd><span name="building">${reopenFacilityRequests.building}</span></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="contentModule-section">
                            <div class="contentModule-actions contentModule-actions_noMargin contentModule-actions_wrap headline-background-wrapper w-100">
                                <!-- <div class="contentModule-headline contentModule-headline_smallMargin">
                                    <icon:enquiry2/>
                                    <spring:theme code="facilityReopen.comment.text"/>
                                </div> -->
                                <span class="headline-background"><spring:theme code="facilityReopen.comment.text"/></span>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="formTextArea">
                                        <div class="form-group">
                                            <textarea id="text" name="text" class="form-control jqText"
                                                      placeholder="." disabled>${reopenFacility.text}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <!-- <div class="contentModule-headline">
                                <icon:documents/>
                                <spring:theme code="facilityReopen.documents.text"/>
                            </div> -->
                            
                            <div class="contentModule-actions contentModule-actions_wrap headline-background-wrapper w-100">
                                <span class="headline-background"><spring:theme code="facilityReopen.documents.text"/></span>
                            </div>

                            <div class="jqDownloadList">
                                <c:if test="${fn:length(reopenFacility.attachments) gt 0}">
                                    <ul class="downloadList" id="documents-container">
                                        <c:forEach items="${reopenFacility.attachments}" var="document">
                                            <li class="downloadList-item">
                                                <div class="downloadList-description">
                                                    <span class="iconElement iconElement_pdf"><icon:document/></span>${document.fileName}
                                                </div>
                                                <div class="downloadList-actions">
                                                    <a href='${encodedContextPath}/attachment/pdf/${document.objectId}/${document.fileGuid}'
                                                       class="link link_nowrap" download="${document.fullFileName}">
                                                        <span class="iconElement iconElement_cloud"><icon:download/></span><spring:theme
                                                            code="facilityReopen.download.text"/>
                                                    </a>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${fn:length(reopenFacility.attachments) eq 0}">
                                    <h2><spring:theme code="facilityReopen.noDocumentsAttached.text"/></h2>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="documentDownloadTemplate" style="display: none;">
    <li class="downloadList-item">
        <div class="downloadList-description">
            <span class="iconElement iconElement_pdf"><icon:document/></span>
        </div>
        <div class="downloadList-actions">
            <a href='${encodedContextPath}/attachment/pdf/${document.objectId}/${document.fileGuid}/${document.fileName}'
               class="link link_nowrap" id="downloadFileAnchor" download="${document.fullFileName}">
                <span class="iconElement iconElement_cloud"><icon:download/></span><spring:theme
                    code="facilityReopen.download.text"/>
            </a>
        </div>
    </li>
</div>
<div class="documentDownloadEmptyTemplate" style="display: none;">
    <h2><spring:theme code="facilityReopen.noDocumentsAttached.text"/></h2>
</div>

<c:if test="${not reopenFacilityRequests.createRequestEnabled}">
    <div class="modal fade" id="reopenFacilityWarning" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title"><spring:theme code="facilityReopen.modal.warning.title"/></div>
                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
						<icon:close/>
					</button>
                </div>
                <div class="modal-body">
                    <div class="modal-description">
                        ${reopenFacilityRequests.message}
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
