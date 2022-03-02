<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>
<%@ taglib prefix="services" tagdir="/WEB-INF/tags/responsive/services" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:if test="${fn:length(legalConsultations) > 0}">
    <input type="hidden" id="serviceId" value="${latestLegalConsultation.srId}"/>
</c:if>

<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="legalConsultationCreate.title" />
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
        <div class="mainSection-header row service-time">
            <!-- <h1 class="mainSection-headline"><spring:theme code="legalConsultationCreate.title" /></h1> -->
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

<!-- <div class="mainSection mainSection_dark mainSection_pdt16">
<div class="container">
<div class="contentModule">
<div class="contentModule-section" >
	<div class="statusBox-description">
    <div class="statusBox-details">
    	<spring:theme code="legalConsultation.disclaimer" />
        </div>
    </div>
</div>
</div>
</div>
</div> -->
<!-- </div> -->

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div>
                <button class="btn btn_slim"
                           onclick="window.location.href='${encodedContextPath}/legalconsultations/new'">
                    <spring:theme code="legalConsultation.create"/>
               </button>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class=" row renewal-services w-100">
                <div class="col-xl-3 col-md-6 col-12 px-0">
                    <a href="/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos-arrow"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-md-6 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-xl-3 col-md-6 col-12">
                    <div class="mainSection-linkActions mainSection-linkActions_right">
                        <button class="btn btn_slim back_to_service"
                                onclick="window.location.href='${encodedContextPath}/legalconsultations/new'">
                            <spring:theme code="legalConsultation.create"/>
                        </button>                        
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
        <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="service.overview.show"/></div>
        <div class=""><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
    </button>
</div>
<div class="container service-wrapper service-wrapper-info mb-5 expanded" id="expand-03" >
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
					<span class="serviceModule-headline"> <spring:theme code="sagia.services.rules.restrictions"/></span>
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

<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <c:if test="${fn:length(legalConsultations) gt 0}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div class="hidden"><span><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div class=""><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
        </c:if>
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(legalConsultations) gt 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    <spring:theme code="legalConsultation.history"/></div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input onkeyup="filterHistory(this)" class="searchInputBox-input" type="text"
                                           placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul id="history-list" class="historyList">
                                <c:forEach items="${legalConsultations}" var="item">
                                    <c:choose>
                                        <c:when test="${latestLegalConsultation.srId eq item.srId}">
                                            <li class="historyList-item historyList-item_current" style="cursor: pointer;" data-expand-target="expand01">
                                        </c:when>
                                        <c:otherwise>
                                            <li class="historyList-item" style="cursor: pointer;" data-expand-target="expand01">
                                        </c:otherwise>
                                    </c:choose>

                                        <a class="historyList-link">
                                                <div class="historyList-id">${item.srId}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${item.srCrDate.dateFormatted}</div>
                                                    <div class="historyList-status historyList-status_${fn:replace(fn:toLowerCase(item.srStDesc),' ', '')}">${item.srStDesc}</div>
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
                 <div  class="expandableContent-main" id="expandedContentParent">
                    <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                        <div  class="contentModule">
                            <div class="contentModule-section" id = "detailedLegalConsultationContent">

                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                    <div  class="contentModule-headline contentModule-headline-service-info headline-text">
                                    <span id= "currentID">${latestLegalConsultation.srId}</span>
                                    <span style="display: none;" id= "currentGUID">${latestLegalConsultation.srGuid}</span>
                                </div>
<!--
                                    <div>
                                <button class="btn btn_slim"
                                           onclick="window.location.href='${encodedContextPath}/legalconsultations/new'">
                                    <spring:theme code="legalConsultation.create"/>
                               </button>
                                    </div>
-->
                                    <c:if test="${fn:length(legalConsultations) gt 0}">
                                        <div id ="currentStatus" class="statusIndicator statusIndicator_${fn:replace(fn:toLowerCase(latestLegalConsultation.srStDesc),' ', '')}">
                                            <spring:theme code="legalConsultation.status.text"/> <span id="statusText" >${latestLegalConsultation.srStDesc}</span>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="statusBox">
                                    <div class="statusBox-description">
                                        <div class="statusBox-info"><spring:theme code="legalConsultation.info"/>
                                            <span class="iconElement iconElement_headlineTooltip js-tip"
                                                  data-tip-title="${serviceDescription}"
                                                  data-original-title="" title="">
                                            <icon:tipInfo/>
                                        </span>
                                        </div>
                                        <div class="statusBox-details">
                                               <services:undertakingLetters/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${fn:length(legalConsultations) gt 0}">
                                <div class="contentModule-section">
                                    <!-- <div class="contentModule-headline contentModule-headline-service-info contentModule-headline_small "><spring:theme code="legalConsultation.comments"/></div> -->
                                    <div class="contentModule contentModule-wrap">
                                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                            <span class="contentModule-headline"><spring:theme code="legalConsultation.comments"/></span>
                                            <div class="contentModule-headline-border"></div>
                                        </div>
                                    </div>
                                    <div class="commentModule-1">
                                        <div class="commentModule-window">
                                            <ul id="messagesListUL" class="messageList">
                                                <c:forEach items="${latestLegalConsultation.getTextSet}" var="comment">
                                                    <li class="messageList-item">
                                                        <div class="messageList-img">
                                                    <span class="iconElement iconElement_expertProfile_white">
                                                        <icon:expertProfile/>
                                                    </span>
                                                        </div>
                                                        <div class="messageList-content">
                                                            <h2 class="messageList-name">${comment.commentsBy}</h2>
                                                            <h3 class="messageList-date">${comment.commentDate.dateFormatted}</h3>
                                                            <div class="messageList-message">
                                                                <p>${comment.tdline}</p>
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

                    <div  class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section" id = "attachedFilesDivContent">
                                <div class="contentModule contentModule-wrap">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                        <span class="contentModule-headline"><spring:theme code="legalConsultation.supportDocuments"/></span>
                                        <div class="contentModule-headline-border"></div>
                                    </div>
                                </div>
                                <!-- <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered">
                                    <div  class="contentModule-headline headline-text"><spring:theme code="legalConsultation.supportDocuments"/></div>
                                </div> -->
                                <ul id="attachmentList" class="downloadList">
                                    <c:forEach items="${latestLegalConsultation.contentHDRSet}" var="attachment">
                                        <li class="downloadList-item">
                                            <div style="cursor: pointer;" class="downloadList-description"
                                                    data-view-attachment-target data-object-id = "${attachment.objectId}"
                                                    data-file-name="${attachment.filename}"
                                                    data-document-id = "${attachment.documentId}">
                                                <span class="iconElement iconElement_pdf"><icon:pdf /></span>
                                                ${attachment.filename}
                                            </div>
                                            <div id = "attachmentsActionsDIV" class="downloadList-actions" style="cursor: pointer;"
                                                data-target
                                                data-object-id="${attachment.objectId}"
                                                data-document-id="${attachment.documentId}"
                                                data-file-name="${attachment.filename}">
                                                <a  id = "downloadAnchorTag" class="link link_nowrap"
                                                     href="${encodedContextPath}/attachment/pdf/${attachment.objectId}/${attachment.documentId}"
                                                     download="${attachment.fullFileName}">
                                                     <span class="iconElement iconElement_cloud">
                                                           <icon:download />
                                                     </span>
                                                    <spring:theme code="legalConsultation.download"/>
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

 <script id="expandedLegalConsultationTemplate" type="text/template">
    <div class="contentModule-section" id = "detailedLegalConsultationContent">
    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
     <div  class="contentModule-headline contentModule-headline-service-info">
     <span class="iconElement iconElement_info"><icon:info/></span>
     <span id= "currentID">{{srId}}</span>
    </div>
   <div>
	<button class="btn btn_slim d-none"
    onclick="window.location.href='${encodedContextPath}/legalconsultations/new'">
    <spring:theme code="legalConsultation.create"/>
    </button>
    </div>
    <div id ="currentStatus" class="statusIndicator statusIndicator_${fn:replace(fn:toLowerCase(latestLegalConsultation.srStDesc),' ', '')}">
      <spring:theme code="legalConsultation.status.text"/> <span id="statusText" >{{srStDesc}}</span>
      </div>
      </div>
      <div class="statusBox">
      <div class="statusBox-description">
      <div class="statusBox-info"><spring:theme code="legalConsultation.info"/><span class="tip" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title="">
       <icon:tipInfo/>
        </span></div>
        <div class="statusBox-details">
       <services:undertakingLetters/>
        </div>
       </div>
       </div>
    </div>
</script>

<script id="supportedAttachments-template" type="text/template">
            <li class="downloadList-item">
                <div style="cursor: pointer;" class="downloadList-description"
                   data-view-attachment-target data-object-id = "{{objectId}}"
                   data-file-name="{{filename}}"
                   data-document-id = "{{documentId}}">
                   <span class="iconElement iconElement_pdf"><icon:pdf /></span>
                      {{filename}}
        		 </div>
              <div id = "attachmentsActionsDIV" class="downloadList-actions" style="cursor: pointer;"
               data-target
               data-object-id="{{objectId}}"
               data-document-id="{{documentId}}"
               data-file-name="{{filename}}">
               <a  id = "downloadAnchorTag" class="link link_nowrap"
               		href="{{downloadUrl}}"
					 download="{{fullFileName}}">
                <span class="iconElement iconElement_cloud">
                      <icon:download />
                       </span>
                        <spring:theme code="legalConsultation.download"/>
                </a>
          </div>
        </li>
      </script>


<script id="messagesLegalConsultation-template" type="text/template">
    <li class="messageList-item">
          <div class="messageList-img">
          <span class="iconElement iconElement_expertProfile_white">
              <icon:expertProfile/>
                </span>
                 </div>
                  <div class="messageList-content">
                   <h2 class="messageList-name">{{commentsBy}}</h2>
                    <h3 class="messageList-date">{{commentDate}}</h3>
                      <div class="messageList-message">
                      <p>{{tdline}}</p>
                      </div>
                     </div>
           </li>
</script>