<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ taglib prefix="services" tagdir="/WEB-INF/tags/responsive/services" %>
<%@ include file="/WEB-INF/tags/responsive/common/infoModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>



<c:url var="attachmentURL" value="/attachment/pdf/"/>
<c:url var="rootURL" value="/"/>

<c:set var="status_in_process"><spring:theme code="service.status.indicator.process"/></c:set>
<c:set var="status_rejected"><spring:theme code="service.status.indicator.reject"/></c:set>
<c:set var="status_completed"><spring:theme code="service.status.indicator.complete"/></c:set>
<c:set var="status_accepted"><spring:theme code="service.status.indicator.accept"/></c:set>

<script>
    var fromRenewSubmitPage = ('${fromRenewSubmitPage}' === 'true');
    var autoRenewal = "${autoRenewal}";
    var autoRenewalClearance = "${autoRenewalClearance}";
</script>

<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="renewlicense.licenserenewal"/>
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
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png" style="margin-top: -3px;margin-left: -3px;"/>
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
<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup" style="top:55%;right:10%">
    <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
    <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
    <div class="sagiaNavigation-subPane-actions">
        <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
    </div>
</div>


<div class="mainSection mainSection_dark bg-white">
    <div class="container">
        <div class="mainSection-header row service-time">
            <!-- <h1 class="mainSection-headline"><spring:theme code="renewlicense.licenserenewal"/></h1> -->
            <input type="hidden" id="serviceId"/>
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

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div id="renewalButtons" style="display: none;">
                <c:choose>
                    <c:when test ="${autoRenewal}">
                        <a class="btn btn_slim jqInstantRenewal" href="javascript:void(0);">
                            <spring:theme code="renewlicense.instantrenew"/>
                        </a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim jqInstantRenewal" href="javascript:void(0);" style="display: none;">
                            <spring:theme code="renewlicense.instantrenew"/>
                        </a>&nbsp;
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test ="${autoRenewalClearance}">
                        <a class="btn btn_slim btn-warning btn_outline jqInstantClearanceRenewal" href="javascript:void(0);">
                            <spring:theme code="renewlicense.instantrenew.clearance"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim btn-warning btn_outline jqInstantClearanceRenewal" href="${encodedContextPath}/my-sagia/license/renew/edit" style="display: none;">
                            <spring:theme code="renewlicense.instantrenew.clearance"/>
                        </a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test ="${governmentDocumentsCheck}">
                        <a class="btn btn_slim btn-warning btn_outline jqGovDocsCheck" href="javascript:void(0);">
                            <spring:theme code="renewlicense.govDocsCheck"/>
                        </a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim btn-warning btn_outline jqGovDocsCheck" href="javascript:void(0);" style="display: none;">
                            <spring:theme code="renewlicense.govDocsCheck"/>
                        </a>&nbsp;
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test ="${clearanceCheck}">
                        <a class="btn btn_slim btn-warning btn_outline jqClearanceCheck" href="javascript:void(0);">
                            <spring:theme code="renewlicense.clearanceCheck"/>
                        </a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim btn-warning btn_outline jqClearanceCheck" href="javascript:void(0);" style="display: none;">
                            <spring:theme code="renewlicense.clearanceCheck"/>
                        </a>&nbsp;
                    </c:otherwise>
                </c:choose>
                <a class="btn btn_slim jqCreateRenewal" href="${encodedContextPath}/my-sagia/license/renew/edit" style="display: none;">
                    <spring:theme code="dashboard.myLicense.renew"/>
                </a>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding bg-white">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween d-flex">
            <div class="row renewal-services w-100">
                <div class="col-md-6 col-lg-4 col-12 ">
                    <a href="/service-search/FIRST" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-md-6 col-lg-4 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>

            <!-- <div class="mainSection-linkActions mainSection-linkActions_right"> -->
                <div id="renewalButtons" style="display: none;">
                    <div class="col-md-6 col-lg-4 col-12">
                        <c:choose>
                            <c:when test ="${autoRenewal}">
                                <a class="btn btn_slim btn_outline back_to_service jqInstantRenewal" href="javascript:void(0);">
                                    <spring:theme code="renewlicense.instantrenew"/>
                                </a>&nbsp;
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn_slim btn_outline back_to_service jqInstantRenewal" href="javascript:void(0);" style="display: none;">
                                    <spring:theme code="renewlicense.instantrenew"/>
                                </a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-md-6 col-lg-4 col-12">
                        <a class="btn btn_slim btn_outline back_to_service jqCreateRenewal" href="${encodedContextPath}/my-sagia/license/renew/edit" style="display: none;">
                            <spring:theme code="dashboard.myLicense.renew"/>
                        </a>
                    </div>
                </div>
            </div>
                <div class="d-flex">
                    <div id="renewalButtons" style="display: none;" class="renewal-services row">
                        <div class="col-xl-4 col-12">
                            <c:choose>
                                <c:when test ="${autoRenewalClearance}">
                                    <a class="btn btn_slim btn-warning btn_outline jqInstantClearanceRenewal" href="javascript:void(0);">
                                        <spring:theme code="renewlicense.instantrenew.clearance"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn_slim btn-warning btn_outline jqInstantClearanceRenewal" href="${encodedContextPath}/my-sagia/license/renew/edit" style="display: none;">
                                        <spring:theme code="renewlicense.instantrenew.clearance"/>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-xl-4 col-12">
                            <c:choose>
                                <c:when test ="${governmentDocumentsCheck}">
                                    <a class="btn btn_slim btn-warning btn_outline jqGovDocsCheck" href="javascript:void(0);">
                                        <spring:theme code="renewlicense.govDocsCheck"/>
                                    </a>&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn_slim btn-warning btn_outline jqGovDocsCheck" href="javascript:void(0);" style="display: none;">
                                        <spring:theme code="renewlicense.govDocsCheck"/>
                                    </a>&nbsp;
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-xl-4 col-12">
                            <c:choose>
                                <c:when test ="${clearanceCheck}">
                                    <a class="btn btn_slim btn-warning btn_outline jqClearanceCheck" href="javascript:void(0);">
                                        <spring:theme code="renewlicense.clearanceCheck"/>
                                    </a>&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn_slim btn-warning btn_outline jqClearanceCheck" href="javascript:void(0);" style="display: none;">
                                        <spring:theme code="renewlicense.clearanceCheck"/>
                                    </a>&nbsp;
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            <!-- </div> -->
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <div class="expandableContent" id="service-tab">
            
        </div>
    </div>
</div>
<div class="container">
    <button class="btn_history btn_rightIconLink btn_bold btn_greenLink btn_show_hide_service" data-expand-target="expand-03">
        <div class=" "><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="service.overview.show"/></div>
        <div class="hidden"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
    </button>
</div>
<!-- ${sagiaService.code} -->
<div class="container service-wrapper service-wrapper-info mb-5" id="expand-03" style="display:none">
	<div class="serviceModule serviceModule_list mx-5 pt-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> <spring:theme code="sagia.services.service.overview"/> </span>
					<c:choose>
						<c:when test="${empty sagiaService.description}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.description}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> <spring:theme code="sagia.services.service.document"/> </span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceDocuments}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDocuments}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> <spring:theme code="sagia.services.rules.restrictions"/> </span>
					<c:choose>
						<c:when test="${empty sagiaService.rulesRestrictions}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.rulesRestrictions}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> <spring:theme code="sagia.services.financial.fees"/> </span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceFinancialFees}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceFinancialFees}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5 pb-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> <spring:theme code="sagia.services.duration"/> </span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceDuration}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDuration}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16 bg-white service-main">
    <div class="container">
        <c:if test="${fn:length(licenseRenew) > 1}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" id="historyList" data-expand-target="expand01">
                <div><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div class="hidden"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
        </c:if>
        <div class="expandableContent" id="expand01">
            <c:if test="${fn:length(licenseRenew) > 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                    <!-- <span class="iconElement iconElement_history"></span> -->
                                    <spring:theme code="text.account.followup.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input onkeyup="filterHistory(this)" class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul class="historyList" id="history-list" >
                                    <c:forEach items="${licenseRenew}" var="licenseItem" varStatus="licenseStatus">
                                        <li style="cursor: pointer" data-expand-target="expand01" class="historyList-item
                                            <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${licenseItem.srID == license.srID}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${licenseStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">

                                            <a class="historyList-link">
                                                <div class="historyList-id">${licenseItem.srID}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">
                                                    ${licenseItem.dateData.dateFormatted}
                                                </div>
                                                <div class="historyList-status
                                                  <c:choose>
        												<c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_in_process)}">historyList-status_inprocess</c:when>
        												<c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_rejected)}">historyList-status_rejected</c:when>
        												<c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_accepted)}">historyList-status_accepted</c:when>
        											    <c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_completed)}">historyList-status_completed</c:when>
       												<c:otherwise>historyList-status_inprocess</c:otherwise>
    												</c:choose> ">
                                              		${licenseItem.statusDescription}</div>
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
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <input type="hidden" name="csrfToken" value="${_csrf.token}"/>
                                <div class="contentModule-headline contentModule-headline-service-info">
                                    <!-- <icon:info/> -->
                                    <spring:theme code="renewlicense.serviceinfo"/><span id="srID">${license.srID}</span></div>
                                <c:if test="${fn:length(licenseRenew) gt 0}">
                                    <div id="currentStatus" class="statusIndicator
                                 <c:choose>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_in_process)}">statusIndicator_inprocess</c:when>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_rejected)}">statusIndicator_rejected</c:when>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_accepted)}">statusIndicator_accepted</c:when>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_completed)}">statusIndicator_completed</c:when>
       								<c:otherwise>statusIndicator_inprocess</c:otherwise>
    								</c:choose> ">
                                        <spring:theme code="renewlicense.status" />
                                        <span id="statusText">${license.statusDescription}</span>
                                    </div>
                                </c:if>
							</div>
                            <div class="statusBox">
                                <div class="statusBox-description">
                                    <div class="statusBox-info"><spring:theme code="convertlicense.info"/>
                                        <span class="iconElement iconElement_headlineTooltip js-tip"
                                        		data-tip-title="${serviceDescription}"
                                        		data-original-title="" >
                                            <icon:tipInfo/>
                                        </span>
                                    </div>
                                    <div class="statusBox-details">
                                        <c:if test="${not empty serviceInformation}">
                                            ${serviceInformation.longDescription}
                                        </c:if>
                                        <c:if test="${empty serviceInformation}">
                                            <spring:theme code="services.missing.description" />
                                        </c:if>
                                        <services:undertakingLetters/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt class="headline-golden" data-name="street"><spring:theme code="address.streetorunitno"/></dt>
                                        <dd class=""><span name="street">${license.address.street} / ${license.address.houseNo}</span></dd>
                                        <dt class="headline-golden"><spring:theme code="general.country"/></dt>
                                        <dd class=""><span name="country">${license.address.country}</span></dd>
                                        <dt class="headline-golden"><spring:theme code="address.postalcodeorcity"/></dt>
                                        <dd class=""><span name="zipCode">${license.address.zipCode}</span></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt class="headline-golden"><spring:theme code="license.additionalnumber"/></dt>
                                        <dd class=""><span name="additNo">${license.address.additionalNotes}</span></dd>
                                        <dt class="headline-golden"><spring:theme code="address.buildingno"/></dt>
                                        <dd class=""><span name="building">${license.address.building}</span></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="contentModule-actions contentModule-actions_right" id="resubmitContainer">
                            <c:if test ="${license.reApply}">
                                <a href="${encodedContextPath}/my-sagia/license/renew/reapply/${license.srID}" class="btn btn_slim btn_outline"><spring:theme code="licenseReplacement.reapply"/></a>
                            </c:if>
                        </div>

                        <c:if test="${fn:length(licenseRenew) gt 0}">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin comments-wrapper"
                                 <c:if test="${fn:length(license.comments) > 0}">style="display: none"</c:if>>
                                <div class="contentModule-headline contentModule-headline_small"><spring:theme code="general.comments"/></div>
                                <div class="commentModule">
                                    <div class="commentModule-window">
                                        <ul class="messageList">
                                            <c:forEach items="${license.comments}" var="comment">
                                                <%--<fmt:parseDate value="${comment.date}" pattern="yyyy-MM-dd" var="parsedDateAppt" type="both"/>--%>
                                                <li class="messageList-item">
                                                    <div class="messageList-img">
                                                        <span class="iconElement iconElement_expertProfile_white"><icon:expertProfile/></span>
                                                    </div>
                                                    <div class="messageList-content">
                                                        <h2 class="messageList-name">${comment.commentBy}</h2>
                                                        <h3 class="messageList-date">
                                                                ${comment.dateData.dateFormatted}
                                                        </h3>
                                                        <div class="messageList-message">${comment.comments}</div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <span class="contentModule-headline contentModule-headline_bordered"><spring:theme code="general.pictures"/></span>
                            <div id="documents-container1 contentModule-headline-border"></div>
                            <ul class="pictureGrid" id="images-container">
                                <c:forEach items="${license.attachedImages}" var="image">
                                    <li><img id="image" src="${encodedContextPath}/my-sagia/license/image/${image.objectID}/${image.documentID}"/></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <span class="contentModule-headline contentModule-headline_bordered"><spring:theme code="text.account.followup.supportDocuments"/></span>
                            <ul class="downloadList contentModule-headline-border" id="documents-container">
                                <c:forEach items="${license.attachedDocuments}" var="document">
                                    <li class="downloadList-item js-download-service-attachment">
                                        <div class="downloadList-description">
                                            <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                            <c:out value="${not empty document.fileName ? document.fileName : ''}" />
                                        </div>
                                        <div class="downloadList-actions">
                                            <a href='${encodedContextPath}/attachment/pdf/${document.objectId}/${document.documentID}' class="link link_nowrap" download="${document.fullFileName}">
                                                <span class="iconElement iconElement_cloud"><icon:download/></span><spring:theme code="general.download"/>
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


<!-- Instant Renewal Modal -->
<div class="modal fade" id="instantRenewal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"><spring:theme code="licenseApplyEntityInformation.licenseYearSection.title"/></div>
			</div>
		 	<div class="modal-body">
			 <div class="contentModule-section contentModule-section_paddingSide">

                        <div class="documentModule js-upload-files-list" data-files-name="img">

                               <div class="formSelectBox">
									<div class="form-group">

										<select id="durationSelectInstant" name="durationSelectInstant" class="js-select2-oneColumn select2-hidden-accessible"  aria-hidden="true" onChange="updateExpiryDate(this, event)">
										      <option></option>
										       <c:forEach items="${durations}" var="duration">
                                                        <option value="${duration.code}"> ${duration.name} </option>
                                              </c:forEach>
									    </select>
									    <label class="control-label control-label_mandatory" for="durationSelectInstant"><spring:theme code="license.apply.entity.licenseYear"/></label>

									</div>
									<div class="help-block"></div>
									 <span class="renewMessage" > <spring:theme code="license.apply.licenseYear.warning"/> </span>
									 <br/><br/>
									 <span> <spring:theme code="license.apply.licenseYear.note"/> </span>
									 <br/>
									 <span> <spring:theme code="license.apply.licenseYear.example"/> </span>
									 <br/>
									 <span class="renewMessage" id="expDateTag" style="display: none"></span>
									 <input class="renewMessage" id="expDate" style="display: none" value="${licExDateData.date}"/>
									 <input class="renewMessage" id="dateUIPattern" style="display: none" value="${licExDateData.dateUIPattern}"/>
							   </div>
                        </div>
					</div>
			</div>
			<div class="modal-footer1 mb-3">
				<button type="button" class="btn btn-outline js-cancel-create-realEstate" data-dismiss="modal"><spring:theme code="general.cancel"/></button>
				<button type="button" id="jqInstantRenewalSubmit" class="btn btn-primary" data-dismiss="modal"><spring:theme code="general.submit"/></button>

			</div>

		</div>
	</div>
</div>



<div class="messageTemplateWrapper" style="display: none">
    <li class="messageList-item">
        <div class="messageList-img">
            <span class="iconElement iconElement_expertProfile_white"><icon:expertProfile/></span>
        </div>
        <div class="messageList-content">
            <h2 class="messageList-name"></h2>
            <h3 class="messageList-date"></h3>
            <div class="messageList-message"></div>
        </div>
    </li>
</div>

<c:if test="${isAllowed eq ''}">
<div class="modal fade" id="renewalDisclaimer"  tabindex="-1" role="dialog" aria-labelledby="renewalDisclaimer" aria-hidden="true"  data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%-- <div class="modal-title"><spring:theme code="services.government.create.disclaimer.title"/></div> --%>
            </div>
            <div class="modal-body">
                <div class="modal-description js-description-text">
                <label class="control-label" for="department">${disclaimer}</label>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn js-close-btn btn js-close-btn" href="${encodedContextPath}/dashboard">Close</a>
            </div>
        </div>
    </div>
</div>
</c:if>

<script>
    var url = '${attachmentURL}';
    var attachmentURL = '${attachmentURL}';
    var licenseid = ${licenseRenew[0].srID};
    var icon = '<icon:pdf/>';
</script>

