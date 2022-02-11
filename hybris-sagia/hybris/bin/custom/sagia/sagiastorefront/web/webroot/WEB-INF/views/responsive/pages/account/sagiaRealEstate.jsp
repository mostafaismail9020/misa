<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<c:if test="${fn:length(realEstateHistory) > 0}">
    <input type="hidden" id="serviceId" value="${realEstateHistory[0].objectId}"/>
</c:if>

<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="realEstate.management"/>
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
                        <div class="count-notification" id="unreadNotificationSpan"></div>
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

<!-- <div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="realEstate.management"/></h1>
        </div>
    </div>
</div> -->

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div>
                <a id="realEstateCreateBtn" data-entity-status="${entityStatus}" href="${encodedContextPath}/real-estate/create" class="btn btn_slim"><spring:theme code="text.realEstate.create"/></a>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding mt-padd-30">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row renewal-services w-100">
                <div class="col-md-6 col-xl-3 col-12">
                    <a href="/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-md-6 col-xl-3 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-md-6 col-xl-3 col-12">
                    <div class="mainSection-linkActions mainSection-linkActions_right">
                        <div>
                            <a id="realEstateCreateBtn" data-entity-status="${entityStatus}" href="${encodedContextPath}/real-estate/create" class="btn btn_slim back_to_service"><spring:theme code="text.realEstate.create"/></a>
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
<div class="container">
    <button class="btn_history btn_rightIconLink btn_bold btn_greenLink btn_show_hide_service" data-expand-target="expand-03">
        <div class="hidden "><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> <spring:theme code="service.overview.show"/></div>
        <div class=""><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
    </button>
</div>
<div class="container service-wrapper service-wrapper-info mb-5" id="expand-03">
	<div class="serviceModule serviceModule_list mx-5 pt-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> Service overview </span>
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
					<span class="serviceModule-headline"> Service document </span>
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
					<span class="serviceModule-headline"> Rules & Restriction </span>
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
					<span class="serviceModule-headline"> Financial FEE </span>
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
					<span class="serviceModule-headline"> Duration </span>
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

<c:if test="${not empty realEstateHistory}">
    <div class="mainSection mainSection_dark mainSection_pdt16 service-main">
        <div class="container">
            <c:if test="${fn:length(realEstateHistory) gt 1}">
                <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                    <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                    <div><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
                </button>
            </c:if>
            <div class="expandableContent expanded" id="expand01">
                <c:if test="${fn:length(realEstateHistory) gt 1}">
                    <div class="expandableContent-aside">
                        <div class="panelModule panelModule_halfRadius">
                            <div class="contentModule">
                                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                    <div class="contentModule-headline contentModule-headline-history">
                                        <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                        <spring:theme code="text.account.followup.history"/>
                                    </div>
                                    <div class="searchInputBox searchInputBox_slim">
                                        <input onkeyup="filterHistory(this)" class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                    </div>
                                    <ul id="history-list" class="historyList">
                                        <!-- links, historyList-item_current and status class need to be added -->
                                        <c:forEach items="${realEstateHistory}" var="realEstate" varStatus="realEstateStatus">
                                            <li class="realEstateItemId historyList-item
                                                <c:choose>
                                                    <c:when test="${fromServiceRequestOverview}">
                                                        <c:if test="${realEstate.objectId == firstElement.objectId}">historyList-item_current</c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${realEstateStatus.index == 0}">historyList-item_current</c:if>
                                                    </c:otherwise>
                                                </c:choose>" id="${realEstate.objectId}">
                                                <a href="javascript:;" class="historyList-link">
                                                    <div class="historyList-id">${realEstate.objectId}</div>
                                                    <div class="historyList-info">
                                                        <div class="historyList-date">${realEstate.sagiaPostingDate.dateFormatted}</div>
                                                        <div class="historyList-status
                                                        <c:choose>
                                                            <c:when test="${fn:contains(realEstate.status,inProcess)}">historyList-status_process</c:when>
                                                            <c:when test="${fn:contains(realEstate.status,rejected)}">historyList-status_rejected</c:when>
                                                            <c:when test="${fn:contains(realEstate.status,completed)}">historyList-status_completed</c:when>
                                                            <c:otherwise>historyList-status_process</c:otherwise>
                                                        </c:choose>
                                                    ">${realEstate.status}</div>
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
                                    <div class="contentModule-headline headline-text mob-heading-responsive">
                                        <!-- <icon:info/> -->
                                        <spring:theme code="realEstateDetails.serviceInfo" text="Service Info: "/>
                                        <span id="reiObjectId"> ${firstElement.objectId}</span>
                                    </div>
                                    <div class="statusIndicator
                                        <c:if test="${fn:length(realEstateHistory) gt 0}">
                                            <c:choose>
                                                <c:when test="${fn:contains(fn:toLowerCase(firstElement.status),'process')}">statusIndicator_process</c:when>
                                                <c:when test="${fn:contains(fn:toLowerCase(firstElement.status),'reject')}">statusIndicator_rejected</c:when>
                                                <c:when test="${fn:contains(fn:toLowerCase(firstElement.status),'accept')}">statusIndicator_accepted</c:when>
                                                <c:when test="${fn:contains(fn:toLowerCase(firstElement.status),'complete')}">statusIndicator_completed</c:when>
                                                <c:otherwise>statusIndicator_process</c:otherwise>
                                            </c:choose>
                                         </c:if>
                                        ">
                                        <spring:theme code="realEstate.status" text="Status"/>
                                        <span id="realEstateStatus">
                                            <c:if test="${fn:length(realEstateHistory) gt 0}">
                                                ${firstElement.status}
                                            </c:if>
                                        </span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.requestType" text="Request Type:"/></dt>
                                            <dd id="reiRequestType">${firstElement.requestType}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.plotNo" text="Plot No:"/></dt>
                                            <dd id="reiPlotNo">${firstElement.plotNo}
                                                <c:if test="${not empty firstElement.plotNo2}">|${firstElement.plotNo2}</c:if>
                                                <c:if test="${not empty firstElement.plotNo3}">|${firstElement.plotNo3}</c:if>
                                                <c:if test="${not empty firstElement.plotNo4}">|${firstElement.plotNo4}</c:if>
                                                <c:if test="${not empty firstElement.plotNo5}">|${firstElement.plotNo5}</c:if>
                                            </dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.deedNo" text="Deed No:"/></dt>
                                            <dd id="reiDeedNo">${firstElement.deedNo}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.outsideMakkah" text="Outside Makkah:"/></dt>
                                            <dd id="reiOutsideMakkah">${firstElement.outsideMakkah}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.projectValue" text="Project Value:"/></dt>
                                            <dd id="reiProjectValue">${firstElement.projectValue}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.region" text="Region:"/></dt>
                                            <dd id="reiRegion">${firstElement.region}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.housingType" text="Housing Type:"/></dt>
                                            <dd id="reiHousingType">${firstElement.housingType}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.unitNo" text="Unit No:"/></dt>
                                            <dd id="reiUnitNo">${firstElement.unitNo}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.additionalDetails" text="Additional Details:"/></dt>
                                            <dd id="reiRemarks">${firstElement.remarks}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.realEstatetype" text="Real Estate Type:"/></dt>
                                            <dd id="reiPurchaseType">${firstElement.purchaseType}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.plotArea" text="Plot Area:"/></dt>
                                            <dd id="reiPlotArea">${firstElement.plotArea}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.deedDate" text="Deed date:"/></dt>
                                            <dd id="reiPurchaseDate">${firstElement.sagiaPurchaseDate.dateFormatted}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.approvedIndustrial" text="Approved Industrial:"/></dt>
                                            <dd id="reiApprovedIndustrial">${firstElement.approvedIndustrial}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.price" text="Price:"/></dt>
                                            <dd id="reiPrice">${firstElement.price}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.city" text="City:"/></dt>
                                            <dd id="reiCity">${firstElement.city}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.district" text="District:"/></dt>
                                            <dd id="reiDistrict">${firstElement.district}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.blockNo" text="Block No:"/></dt>
                                            <dd id="reiBlockNo">${firstElement.blockNo}</dd>
                                            <dt class="headline-golden"><spring:theme code="realEstateDetails.moreThan30" text="More than 30:"/></dt>
                                            <dd id="reiThirtyMore">${firstElement.thirtyMore}</dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>

                            <div class="contentModule-actions contentModule-actions_right">
                                <a id="realEstateResubmitBtn" data-entity-status="${entityStatus}" href="${encodedContextPath}/real-estate/resubmit/${firstElement.objectId}" class="btn btn_slim btn_outline"
                                <c:if test="${!fn:containsIgnoreCase(firstElement.status, 'rejected')}">hidden</c:if>>
                                    <spring:theme code="text.account.followup.resubmit"/>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div id="realEstateDocuments" <c:if test="${empty firstElement.attachmentsSet}">hidden</c:if>>
                        <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                            <div class="contentModule contentModule-wrap">
                                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                        <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                        <div class="contentModule-headline-border"></div>
                                    </div>
                                    <!-- <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-headline_bordered w-100">
                                        <div class="contentModule-headline headline-text">
                                            <spring:theme code="text.account.followup.supportDocuments"/>
                                        </div>
                                    </div> -->

                                    <ul id="realEstateAttachmentList" class="downloadList downloadList_maxHeight">
                                        <c:forEach items="${firstElement.attachmentsSet}" var="attachment">
                                            <li class="downloadList-item">
                                                <div id="realEstateAttachment_${attachment.filename}" class="downloadList-description">
                                                    <span class="iconElement iconElement_pdf"><icon:pdf/></span>${attachment.filename}
                                                </div>
                                                <div class="downloadList-actions">
                                                    <a href="${encodedContextPath}/real-estate/pdf/${attachment.objectId}/${attachment.docGuid}" class="link link_nowrap" download="${attachment.fullFileName}">
                                                        <span class="iconElement iconElement_cloud"><icon:download/></span><spring:theme code="text.account.followup.download"/>
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
</c:if>
<input type="hidden" id="isEntityStatusValid" value="${isEntityStatusValid}">
<common:errorModal />

<script>
    var currentEntityStatus = "${currentEntityStatus}";
</script>