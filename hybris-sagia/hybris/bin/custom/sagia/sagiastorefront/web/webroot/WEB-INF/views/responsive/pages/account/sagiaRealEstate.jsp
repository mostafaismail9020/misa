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

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="realEstate.management"/></h1>
        </div>
    </div>
</div>

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div>
                <a id="realEstateCreateBtn" data-entity-status="${entityStatus}" href="${encodedContextPath}/real-estate/create" class="btn btn_slim"><spring:theme code="text.realEstate.create"/></a>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="d-flex row renewal-services w-100">
                <div class="col-md-3">
                    <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/></a>
                </div>
                <div class="col-md-3">
                    <div class="mainSection-linkActions mainSection-linkActions_right">
                        <div>
                            <a id="realEstateCreateBtn" data-entity-status="${entityStatus}" href="${encodedContextPath}/real-estate/create" class="btn btn_slim"><spring:theme code="text.realEstate.create"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container service-wrapper service-wrapper-info">
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
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.description}</p></div></div>
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
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.serviceDocuments}</p></div></div>
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
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.rulesRestrictions}</p></div></div>
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
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.serviceFinancialFees}</p></div></div>
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
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.serviceDuration}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>

<c:if test="${not empty realEstateHistory}">
    <div class="mainSection mainSection_dark mainSection_pdt16 mt-5">
        <div class="container">
            <c:if test="${fn:length(realEstateHistory) gt 1}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent"
                        data-expand-target="expand01">
                    <div><spring:theme code="text.account.followup.showServiceHistory"/><span>&#x27f6;</span></div>
                    <div class="hidden"><spring:theme code="text.account.followup.hideServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span></div>
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
                                    <div class="contentModule-headline contentModule-headline-service-info">
                                        <icon:info/>
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
                                            <dt><spring:theme code="realEstateDetails.requestType" text="Request Type:"/></dt>
                                            <dd id="reiRequestType">${firstElement.requestType}</dd>
                                            <dt><spring:theme code="realEstateDetails.plotNo" text="Plot No:"/></dt>
                                            <dd id="reiPlotNo">${firstElement.plotNo}
                                                <c:if test="${not empty firstElement.plotNo2}">|${firstElement.plotNo2}</c:if>
                                                <c:if test="${not empty firstElement.plotNo3}">|${firstElement.plotNo3}</c:if>
                                                <c:if test="${not empty firstElement.plotNo4}">|${firstElement.plotNo4}</c:if>
                                                <c:if test="${not empty firstElement.plotNo5}">|${firstElement.plotNo5}</c:if>
                                            </dd>
                                            <dt><spring:theme code="realEstateDetails.deedNo" text="Deed No:"/></dt>
                                            <dd id="reiDeedNo">${firstElement.deedNo}</dd>
                                            <dt><spring:theme code="realEstateDetails.outsideMakkah" text="Outside Makkah:"/></dt>
                                            <dd id="reiOutsideMakkah">${firstElement.outsideMakkah}</dd>
                                            <dt><spring:theme code="realEstateDetails.projectValue" text="Project Value:"/></dt>
                                            <dd id="reiProjectValue">${firstElement.projectValue}</dd>
                                            <dt><spring:theme code="realEstateDetails.region" text="Region:"/></dt>
                                            <dd id="reiRegion">${firstElement.region}</dd>
                                            <dt><spring:theme code="realEstateDetails.housingType" text="Housing Type:"/></dt>
                                            <dd id="reiHousingType">${firstElement.housingType}</dd>
                                            <dt><spring:theme code="realEstateDetails.unitNo" text="Unit No:"/></dt>
                                            <dd id="reiUnitNo">${firstElement.unitNo}</dd>
                                            <dt><spring:theme code="realEstateDetails.additionalDetails" text="Additional Details:"/></dt>
                                            <dd id="reiRemarks">${firstElement.remarks}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated">
                                            <dt><spring:theme code="realEstateDetails.realEstatetype" text="Real Estate Type:"/></dt>
                                            <dd id="reiPurchaseType">${firstElement.purchaseType}</dd>
                                            <dt><spring:theme code="realEstateDetails.plotArea" text="Plot Area:"/></dt>
                                            <dd id="reiPlotArea">${firstElement.plotArea}</dd>
                                            <dt><spring:theme code="realEstateDetails.deedDate" text="Deed date:"/></dt>
                                            <dd id="reiPurchaseDate">${firstElement.sagiaPurchaseDate.dateFormatted}</dd>
                                            <dt><spring:theme code="realEstateDetails.approvedIndustrial" text="Approved Industrial:"/></dt>
                                            <dd id="reiApprovedIndustrial">${firstElement.approvedIndustrial}</dd>
                                            <dt><spring:theme code="realEstateDetails.price" text="Price:"/></dt>
                                            <dd id="reiPrice">${firstElement.price}</dd>
                                            <dt><spring:theme code="realEstateDetails.city" text="City:"/></dt>
                                            <dd id="reiCity">${firstElement.city}</dd>
                                            <dt><spring:theme code="realEstateDetails.district" text="District:"/></dt>
                                            <dd id="reiDistrict">${firstElement.district}</dd>
                                            <dt><spring:theme code="realEstateDetails.blockNo" text="Block No:"/></dt>
                                            <dd id="reiBlockNo">${firstElement.blockNo}</dd>
                                            <dt><spring:theme code="realEstateDetails.moreThan30" text="More than 30:"/></dt>
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
                            <div class="contentModule">
                                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                        <div class="contentModule-headline headline-text contentModule-headline_bordered">
                                            <icon:documents/>
                                            <spring:theme code="text.account.followup.supportDocuments"/>
                                        </div>
                                    </div>

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