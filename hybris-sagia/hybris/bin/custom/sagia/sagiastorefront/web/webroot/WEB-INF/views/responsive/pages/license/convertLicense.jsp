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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ taglib prefix="services" tagdir="/WEB-INF/tags/responsive/services" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var isReApply = ${latestConvToNationals.reApply};
</script>
<input type="hidden" id="serviceId" value="${latestConvToNationals.srID}"/>

<div class="mainSection mainSection bg-white">
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
<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
    <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
    <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
    <div class="sagiaNavigation-subPane-actions">
        <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
    </div>
</div>

<div class="mainSection mainSection_dark">
    <div class="container">
        <!-- <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="convertlicense.converttonational" /></h1>
        </div> -->
        <div class="row service-time">
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

<div class="container mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="m-0 ml-custom-35">
        <div class="row w-100 renewal-services">
            <div class="col-md-3 col-12 px-0">
                <a href="/service-search/FIRST" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
            </div>
            <c:if test="${fn:length(sagiaService.tabs) > 0}">
					<div class="col-xl-3 col-12 ml-1">
						<button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
					</div>
				</c:if>
        </div>
        <div class="row w-100 mt-4">
            <div class="mainSection-linkActions mainSection-linkActions_right amend-service-link">
                <div>
                <c:choose>
                <c:when test ="${isInstant}">
                    <a class="btn btn_slim btn-warning btn_outline back_to_service " href="${encodedContextPath}/my-sagia/license/convert/new">
                        <spring:theme code="convertlicense.instant.convert"/>
                    </a>
                </c:when>
                <c:otherwise>
                    <button class="btn btn_slim back_to_service" onclick="window.location.href='${encodedContextPath}/my-sagia/license/convert/new'">
                        <spring:theme code="convertlicense.convert"/>
                    </button>
                </c:otherwise>
                </c:choose>
                </div>
                <%-- <div>
                    <button class="btn btn_slim" onclick="window.location.href='${encodedContextPath}/my-sagia/license/convert/new'">
                        <spring:theme code="convertlicense.convert"/>
                    </button>
                </div> --%>
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

<!-- <div class="container mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/></a>
            <c:if test="${fn:length(convertToNationals_list) gt 1}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                    <div class="hidden"><spring:theme code="text.account.followup.showServiceHistory"/><span>&#x27f6;</span></div>
                    <div><spring:theme code="text.account.followup.hideServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span></div>
                </button>
            </c:if>
        </div>
    </div>
</div> -->
<div class="container">
    <button class="btn_history btn_rightIconLink btn_bold btn_greenLink btn_show_hide_service" data-expand-target="expand-03">
        <div class=" "><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> <spring:theme code="service.overview.show"/></div>
        <div class="hidden"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
    </button>
</div>
<div class="container service-wrapper service-wrapper-info" id="expand-03" style="display:none">
	<div class="serviceModule serviceModule_list mx-5 pt-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="sagia.services.service.overview"/></span>
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

<div class="container mainSection mainSection_dark mainSection_pdt16 mb-3 service-main">
    <div class="">
			<div class="mainSection-linkActions mainSection-linkActions_right amend-service-link">
                <c:if test="${fn:length(convertToNationals_list) gt 1}">
                    <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                        <div class=""><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                        <div class="hidden "><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
                    </button>
                </c:if>
			</div>

        <div class="expandableContent" id="expand01">
            <c:if test="${fn:length(convertToNationals_list) gt 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    <spring:theme code="text.account.followup.history"/></div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input id = "convertSearchBox" class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${convertToNationals_list}" var="convertToNationals" varStatus="convertToNationalsStatus">
                                        <li data-expand-target="expand01" class="historyList-item cursor-pointer 
                                            <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${convertToNationals.srID == latestConvToNationals.srID}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${convertToNationalsStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">
                                            <a class="historyList-link">
                                                <div class="historyList-id">${convertToNationals.srID}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${convertToNationals.sagiaSrCrDate.dateFormatted}</div>
                                                    <div class="historyList-status historyList-status_${fn:replace(fn:toLowerCase(convertToNationals.srStDesc),' ', '')}">${convertToNationals.srStDesc}</div>
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
            <div class="expandableContent-main" id="expandedContentParent">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section" id = "detailedConvertToNationalsContent">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline contentModule-headline-service-info">
                                    <span class="iconElement iconElement_info"><icon:info/></span>
                                    <span id= "currentID">${latestConvToNationals.srID}</span>
                                    <span style="display: none;" id= "currentGUID">${latestConvToNationals.srGuid}</span>
                                </div>
<!--
                                <div>
                                    <button class="btn btn_slim" onclick="window.location.href='${encodedContextPath}/my-sagia/license/convert/new'">
                                        <spring:theme code="convertlicense.convert"/>
                                    </button>
                                </div>
-->
                                <c:if test="${fn:length(convertToNationals_list) gt 0}">
                                    <div id="currentStatus" class="statusIndicator statusIndicator_${fn:replace(fn:toLowerCase(latestConvToNationals.srStDesc),' ', '')}">
                                        <spring:theme code="convertlicense.status"/> <span id="statusText" >${latestConvToNationals.srStDesc}</span>
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
                                    <div class="statusBox-details">${serviceDetailInfo.longDescr}
                                        <services:undertakingLetters/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:if test="${fn:length(convertToNationals_list) gt 0}">
                            <div class="contentModule-section">
                                <div class="contentModule-headline contentModule-headline_small serviceModule"><span class="serviceModule-headline"><spring:theme code="text.account.followup.comments"/></span></div>
                                <div class="commentModule">
                                    <div class="commentModule-window">
                                        <ul id="messagesListUL" class="messageList">
                                            <c:forEach items="${messages}" var="comment">
                                                <li class="messageList-item">
                                                    <div class="messageList-img">
                                                    <span class="iconElement iconElement_expertProfile_white">
                                                        <icon:expertProfile/>
                                                    </span>
                                                    </div>
                                                    <div class="messageList-content">
                                                        <h2 class="messageList-name">${comment.commentBy}</h2>
                                                        <h3 class="messageList-date">${comment.commentDate}</h3>
                                                        <div class="messageList-message">
                                                            <p>${comment.comments}</p>
                                                        </div>
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

                <form:form enctype="multipart/form-data" method="post" id="convNationalResubmitForm" modelAttribute="convToNationalsResubmitFormData">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section" id="attachedFilesDivContent">
                                <!-- <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered"> -->
                                    <!-- <div class="contentModule-headline serviceModule">
                                        <span class="iconElement iconElement_documents"><icon:documents/></span>
                                        <span class="serviceModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                    </div> -->
                                    <div class="contentModule contentModule-wrap">
                                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                            <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                            <button type="submit" value="SUBMIT" class="btn btn_outline btn_slim" id="resubmitButton" data-toggle="modal"
                                                <c:choose>
                                                    <c:when test = "${fn:toUpperCase(latestConvToNationals.srStDesc) == 'REJECTED' && latestConvToNationals.reApply}"></c:when>
                                                        <c:otherwise>disabled="true" style="display: none;"</c:otherwise>
                                                    </c:choose>>
                                                <spring:theme code="text.account.followup.resubmit"/>
                                            </button>
                                            <div class="contentModule-headline-border"></div>
                                        </div>
                                    </div>
                                    <!-- <div>
                                        <button type="submit" value="SUBMIT" class="btn btn_outline btn_slim" id="resubmitButton" data-toggle="modal"
                                                <c:choose>
                                                    <c:when test = "${fn:toUpperCase(latestConvToNationals.srStDesc) == 'REJECTED' && latestConvToNationals.reApply}"></c:when>
                                                    <c:otherwise>disabled="true" style="display: none;"</c:otherwise>
                                                </c:choose>>
                                            <spring:theme code="text.account.followup.resubmit"/>
                                        </button>
                                    </div> -->
                                <!-- </div> -->
                                <ul id="attachmentList" class="downloadList">
                                    <c:forEach items="${attachedFiles}" var="attachment">
                                        <li class="downloadList-item">
                                            <div class="downloadList-description cursor-pointer"
                                                    data-view-attachment-target data-object-id = "${attachment.objectId}"
                                                    data-file-name="${attachment.filename}"
                                                    data-document-id = "${attachment.documentID}">
                                                <span class="iconElement iconElement_pdf"><icon:pdf /></span>
                                                ${attachment.filename}
                                            </div>
                                            <div id = "attachmentsActionsDIV" class="downloadList-actions" style="cursor: pointer;"
                                                data-target
                                                data-object-id="${attachment.objectId}"
                                                data-document-id="${attachment.documentID}"
                                                data-file-name="${attachment.filename}">
                                                <a id="downloadAnchorTag" class="link link_nowrap" href="${encodedContextPath}/attachment/pdf/${attachment.objectId}/${attachment.documentID}"
                                                	 download="${attachment.fullFileName}">
                                                    <span class="iconElement iconElement_cloud">
                                                           <icon:download />
                                                     </span>
                                                    <spring:theme code="text.account.followup.download"/>
                                                </a>
                                                <a href="#" class="link link_nowrap"
                                                        <c:choose>
                                                	         <c:when test = "${fn:toUpperCase(latestConvToNationals.srStDesc) == 'REJECTED'}">style="display: block;"</c:when>
                                                	         <c:otherwise>style="display: none;"</c:otherwise>
                                                		</c:choose>
                                                   id="replaceAnchorTag${attachment.documentID}"
                                                   data-toggle="modal" data-target="#uploadFileModal"
                                                   data-file-name="${attachment.filename}"
                                                   data-object-id="${attachment.documentID}">
                                                    <span class="iconElement iconElement_reupload">
                                                        <icon:reupload/>
                                                    </span>
                                                    <spring:theme code="convertlicense.replace"/>
                                                </a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="uploadFileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <div class="modal-title"><spring:theme code="convertlicense.uploadyourdocument"/></div>
                                </div>
                                <div class="modal-body">
                                    <ul id="inputFilesList" class="formInputFileBox-list">
                                        <c:forEach items="${attachedFiles}" var="attachment" varStatus="theCount">
                                            <li style="display: none;" id="formInputListItem${attachment.documentID}">
                                                <div class="formInputFileBox">
                                                    <div class="form-group">
                                                        <div class="form-icon form-icon_browse">
                                                            <icon:upload/>
                                                        </div>
                                                        <input style="display: none;" id="files[${theCount.index}].documentID" name="files[${theCount.index}].documentID" value="${attachment.documentID}"/>
                                                        <input id="inputFile${attachment.documentID}" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" name="files[${theCount.index}].multiPartFile" />
                                                        <label class="control-label" id="controlLabel-inputFile${attachment.documentID}">
                                                            <span class="formInputFileBox-list-dragndrop">
                                                                <spring:theme code="general.chooseafile"/>&nbsp;<spring:theme code="general.draghere"/>
                                                            </span>.</label>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <div class="formInputFileBox-uploading"><spring:theme code="company.uploading"/></div>
                                    <div class="formInputFileBox-success"><spring:theme code="company.done"/></div>
                                    <div class="formInputFileBox-error"><spring:theme code="company.error"/> <span></span>.</div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" type="submit" value="" class="btn btn_slim btn_round" data-dismiss="modal"><spring:theme code="convertlicense.replacefile"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
          		    <input type="hidden" name="csrfToken" value="${_csrf.token}" />
              </form:form>
            </div>
        </div>
	</div>
</div>

<c:if test="${isAllowed eq ''}">
<div class="modal fade" id="convertToNationalDisclaimer"  tabindex="-1" role="dialog" aria-labelledby="convertToNationalDisclaimer" aria-hidden="true"  data-keyboard="false" data-backdrop="static">
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
                <a class="btn js-close-btn btn_a" href="${encodedContextPath}/dashboard">Close</a>
            </div>
        </div>
    </div>
</div>
</c:if>

<script id="expandedConvToNational-template" type="text/template">
    <div class="contentModule-section contentModule-section_slimDivider">
        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
            <div class="contentModule-headline">
                <span class="iconElement iconElement_info"><icon:info/></span>
                <span id= "currentID">{{srID}}</span>
                <span style="display: none;" id= "currentGUID">{{srGuid}}</span>
            </div>
            <%-- <div>
                <button class="btn btn_slim" onclick="window.location.href='${encodedContextPath}/my-sagia/license/convert/new'">
                    <spring:theme code="convertlicense.convert"/>
                </button>
            </div> --%>
            <div id ="currentStatus" class="statusIndicator statusIndicator_{{srStDescIndicator}}">
                <spring:theme code="convertlicense.status"/> <span id="statusText" >{{srStDesc}}</span>
            </div>
        </div>
        <div class="statusBox">
            <div class="statusBox-description">
                <div class="statusBox-info"><spring:theme code="convertlicense.info"/>
                    <span class="tip" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title="">
                        <icon:tipInfo/>
                    </span>
                </div>
                <div class="statusBox-details">{{longDescr}}
                    <services:undertakingLetters/>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="supportedAttachments-template" type="text/template">
    <li class="downloadList-item">
	    <div style="cursor: pointer;" class="downloadList-description" data-view-attachment-target data-object-id = "{{objectId}}" data-document-id = "{{documentID}}">
		    <span class="iconElement iconElement_pdf"><icon:pdf /></span>{{filename}}
	    </div>
	    <div class="downloadList-actions" style="cursor: pointer;" data-target data-object-id="{{objectId}}" data-document-id="{{documentID}}" data-file-name="{{filename}}">
		    <a id="downloadAnchorTag" class="link link_nowrap" href="{{downloadUrl}}"
				 download="{{fullFileName}}">
                <span class="iconElement iconElement_cloud"><icon:download /></span><spring:theme code="text.account.followup.download"/>
            </a>
            <a href="#" class="link link_nowrap" id="replaceAnchorTag{{documentID}}" data-toggle="modal" data-target="#uploadFileModal" style="display: none;" data-object-id="{{documentID}}">
                <span class="iconElement iconElement_reupload">
                    <icon:reupload/>
                </span>
                <spring:theme code="convertlicense.replace"/>
            </a>
        </div>
    </li>
</script>

<script id="uploadFilesModal-template" type="text/template">
    <li style="display: none;" id="formInputListItem{{documentID}}">
        <div class="formInputFileBox">
            <div class="form-group">
                <div class="form-icon form-icon_browse">
                    <icon:upload/>
                </div>
                <input style="display: none;" id="files[{{index}}].documentID" name="files[{{index}}].documentID" value="{{documentID}}"/>
                <input id="inputFile{{documentID}}" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" name="files[{{index}}].multiPartFile" />
                <label class="control-label" id="controlLabel-inputFile{{documentID}}">
                    <span class="formInputFileBox-list-dragndrop">
                        <spring:theme code="general.chooseafile"/>&nbsp;<spring:theme code="general.draghere"/>
                    </span>.
                </label>
            </div>
        </div>
    </li>
</script>

<script id="messagesConvToNationals-template" type="text/template">
    <li class="messageList-item">
        <div class="messageList-img">
            <span class="iconElement iconElement_expertProfile_white">
                <icon:expertProfile/>
            </span>
        </div>
        <div class="messageList-content">
            <h2 class="messageList-name">{{commentBy}}</h2>
            <h3 class="messageList-date">{{commentDate}}</h3>
            <div class="messageList-message">
                <p>{{comments}}</p>
            </div>
        </div>
    </li>
</script>
