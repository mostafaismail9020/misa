<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="services" tagdir="/WEB-INF/tags/responsive/services" %>
<spring:htmlEscape defaultHtmlEscape="true"/>

<!--site content for second license download page-->
<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="splitURI" value="${fn:split(currentPage, '/')}"/>
<c:set var="serviceUrl" value="${splitURI[fn:length(splitURI)-1]}"/>
<c:set var="categoryUrl" value="${splitURI[fn:length(splitURI)-2]}"/>

<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    ${serviceName}
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
        <div class="mainSection-header  row service-time">
            <!-- <h1 class="mainSection-headline">${serviceName}</h1> -->
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
        <%-- <div class="modal-description js-description-text" style="color: red">
                <label class="control-label" for="department">
	                <spring:theme code="services.government.create.disclaimer.title"/>
	                <br>
	                <spring:theme code="services.government.create.disclaimer.message"/>
	                <br>
	                <spring:theme code="services.government.create.disclaimer.greet"/>
                </label>
            </div> --%>
    </div>
</div>

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div id="serviceUrl">
                <c:url value="/services/government/${categoryUrl}/${serviceUrl}/create" var="url">
                    <c:param name="serviceName" value="${serviceName}"/>
                    <c:if test="${fn:length(serviceList) gt 0}">
                        <c:param name="srID" value="${serviceList[0].srID}"/>
                    </c:if>
                </c:url>
                <button class="btn btn_slim" id="createGovtServiceButton">
                    <input id="createGovtServUrl" type="hidden" value="${url}">
                    <spring:theme code="create.govtServices"/>
                </button>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="d-flex row renewal-services w-100">
                <div class="col-xl-3 col-md-6 col-12">
                    <a href="/service-search/GOVERNMENTAL SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-md-6 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-xl-3 col-md-6 col-12">
                    <div class=" mainSection-linkActions_right amend-service-link govt-service-btn">
                        <div id="serviceUrl">
                            <c:url value="/services/government/${categoryUrl}/${serviceUrl}/create" var="url">
                                <c:param name="serviceName" value="${serviceName}"/>
                                <c:if test="${fn:length(serviceList) gt 0}">
                                    <c:param name="srID" value="${serviceList[0].srID}"/>
                                </c:if>
                            </c:url>
                            <button class="btn btn_slim back_to_service" id="createGovtServiceButton">
                                <input id="createGovtServUrl" type="hidden" value="${url}">
                                <spring:theme code="create.govtServices"/>
                            </button>
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
        <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> <spring:theme code="service.overview.show"/></div>
        <div class=""><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
    </button>
</div>
<div class="container service-wrapper service-wrapper-info mb-5 expanded"  id="expand-03">
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
					<span class="serviceModule-headline"><spring:theme code="sagia.services.service.document"/></span>
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
					<span class="serviceModule-headline"><spring:theme code="sagia.services.rules.restrictions"/></span>
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
					<span class="serviceModule-headline"><spring:theme code="sagia.services.financial.fees"/></span>
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
					<span class="serviceModule-headline"><spring:theme code="sagia.services.duration"/></span>
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
        <c:if test="${fn:length(serviceList) gt 1}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
        </c:if>
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(serviceList) gt 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                    <!-- <span class="iconElement iconElement_history"><svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            width="24" height="24"
                                            viewBox="0 0 24 24"><g
                                            transform="translate(1 1)" fill="none"><circle cx="11" cy="11" r="10"
                                                                                           class="iconElement-colorPrimary_stroke"
                                                                                           stroke-width="2"></circle><path
                                            class="iconElement-colorSecondary_stroke" stroke-linecap="round"
                                            stroke-linejoin="round"
                                            d="M10.542 4.125v8.25m0 0l5.5-1.833"></path></g></svg></span> -->
                                    <spring:theme code="text.specialservices.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input onkeyup="filterHistory(this)" id="convertSearchBox"
                                           class="searchInputBox-input" type="text"
                                           placeholder="<spring:theme code='storeFinder.search'/>">
                                </div>
                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${serviceList}" var="service">
                                        <li class="historyList-item serviceItem cursor-pointer"
                                            data-expand-target="expand01" data-param1="${service.srID}">
                                            <input type="hidden" id="category" value="${categoryUrl}"/>
                                            <input type="hidden" id="service" value="${serviceUrl}"/>
                                            <input type="hidden" id="serviceId"/>
                                            <a class="historyList-link">
                                                <div class="historyList-id">${service.srID}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${service.srCrDateData.dateFormatted}</div>
                                                    <div class="historyList-status
                                                            <c:choose>
                                                                <c:when test="${fn:contains(fn:toLowerCase(service.srStDesc),'process')}">historyList-status_process</c:when>
                                                                <c:when test="${fn:contains(fn:toLowerCase(service.srStDesc),'reject')}">historyList-status_rejected</c:when>
                                                                <c:when test="${fn:contains(fn:toLowerCase(service.srStDesc),'accept')}">historyList-status_accepted</c:when>
                                                                <c:when test="${fn:contains(fn:toLowerCase(service.srStDesc),'complete')}">historyList-status_completed</c:when>
                                                                <c:otherwise>historyList-status_process</c:otherwise>
                                                            </c:choose>
                                                        ">${service.srStDesc}</div>
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
            <div class="expandableContent-main" id="expandedContentParent" style="${serviceList.size() == 0 ? 'visibility:hidden;' : ''}">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin mt-3">
                    <div id="detailedConvertToNationalsContent" class="contentModule">
                        <div class="contentModule-section">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                                    <span id="currentID" class="headline-background">
                                        <c:if test="${fn:length(serviceList) gt 0}">
                                             ${serviceList[0].srID}
                                        </c:if>
                                    </span>
                                
                                <c:if test="${fn:length(serviceList) gt 0}">
                                    <div id="currentStatus" class="statusIndicator
                                    <c:if test="${fn:length(serviceList) gt 0}">
                                        <c:choose>
                                            <c:when test="${fn:contains(fn:toLowerCase(serviceList[0].srStDesc),'process')}">statusIndicator_process</c:when>
                                            <c:when test="${fn:contains(fn:toLowerCase(serviceList[0].srStDesc),'reject')}">statusIndicator_rejected</c:when>
                                            <c:when test="${fn:contains(fn:toLowerCase(serviceList[0].srStDesc),'accept')}">statusIndicator_accepted</c:when>
                                            <c:when test="${fn:contains(fn:toLowerCase(serviceList[0].srStDesc),'complete')}">statusIndicator_completed</c:when>
                                            <c:otherwise>statusIndicator_process</c:otherwise>
                                        </c:choose>
                                    </c:if>
                                
                                        <spring:theme code="convertlicense.status"/>
                                        <span id="statusText">
                                                <c:if test="${fn:length(serviceList) gt 0}">
                                                    ${serviceList[0].srStDesc}
                                                </c:if>
                                        </span>
                            </div>
                        </div>
                                </c:if>
                        </div>

                            <div class="statusBox">
                                <div class="statusBox-description">
                                    <div class="statusBox-info"><spring:theme code="convertlicense.info"/>
                                        <span class="iconElement iconElement_headlineTooltip js-tip"
                                              data-tip-title="${serviceDescription}"
                                              data-original-title="" title="">
                                            <icon:tipInfo/>
                                        </span>
                                    </div>
                                    <div class="statusBox-details">
                                        <c:set var="serviceUrl" value="${serviceUrl}"/>
                                        <c:choose>
                                            <c:when test="${fn:startsWith(serviceUrl,'ZMOCI')}">
                                                <div>
                                                    <spring:theme code="services.type.zmoci.crnumber"
                                                                  text="CR Number: "/>
                                                    <span>${infoData.crNumber}</span>
                                                </div>
                                                <div>
                                                    <spring:theme code="services.type.zmoci.crissuesdate"
                                                                  text="CR Issue Date: "/>
                                                    <span>${infoData.crIssueDate}</span>
                                                </div>
                                                <div>
                                                    <spring:theme code="services.type.zmoci.crexpirydate"
                                                                  text="CR Expiry Date: "/>
                                                    <span>${infoData.crExpiryDate}</span>
                                                </div>
                                            </c:when>
                                            <c:when test="${fn:startsWith(serviceUrl,'ZMOL')}">
                                                <div>
                                                    <spring:theme code="services.type.zmol.molnumber"
                                                                  text="MOL Number: "/>
                                                    <span>${infoData.molNumber}</span>
                                                </div>
                                                <div>
                                                    <spring:theme code="services.type.zmol.molnitiqatsize"
                                                                  text="MOL Nitiqat Size: "/>
                                                    <span>${infoData.molSize}</span>
                                                </div>
                                                <div>
                                                    <spring:theme code="services.type.zmol.molcolor"
                                                                  text="MOL Color: "/>
                                                    <span>${infoData.molColor}</span>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div>
                                                    <spring:theme code="services.type.zmoip.number700" text="700 Number: "/>
                                                    <span>${infoData.sevenHundredNumber}</span>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        <services:undertakingLetters/>
                                        <div>
										    <spring:theme code="service.govt.mci.moa.text" text="For ministry of commerce forms please"/>&nbsp
										    <a href="${encodedContextPath}/service-search">
										        <spring:theme code="service.govt.mci.moa.link" text="click here"/>
										    </a>
										</div>
										<br>
										<br>
										<div>
										<c:if test = "${fn:startsWith(serviceUrl, 'ZMOCI')}">
                                             <b><spring:theme code="service.govt.moc.salutation"/></b>
                                             <spring:theme code="service.govt.moc.message"/>
                                             <br>
                                             <a href="https://efile.mci.gov.sa/ar/Account/Login?client_ID=152b6467-1d78-497f-a03f-076172250b80">
                                                  <spring:theme code="service.govt.moc.link.name"/>
                                              </a>
                                          </c:if>
										</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:if test="${fn:length(attachments) gt 0}">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <!-- <div class="contentModule-headline contentModule-headline_bordered">
                                    <icon:documents/><spring:theme code="text.account.followup.supportDocuments"/>
                                </div> -->
                                <div class="contentModule contentModule-wrap">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                        <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                        <div class="contentModule-headline-border"></div>
                                    </div>
                                </div>
                            </div>

                            <div id="govtServicesAttachments">
                                <ul class="downloadList downloadList_maxHeight">
                                    <c:forEach items="${attachments}" var="attachment">
                                        <li class="downloadList-item">
                                            <div class="downloadList-description">
                                                <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                                <c:choose>
                                                    <c:when test="${not empty attachment.filename}">${attachment.filename}</c:when>
                                                    <c:otherwise>
                                                        <spring:theme code="services.file.noName" text="[No Description]"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <input id="objectId" type="hidden" value="${attachment.objectId}">
                                            <input id="documentID" type="hidden" value="${attachment.documentID}">
                                            <div class="downloadList-actions">
                                                <a href="${encodedContextPath}/govtServices/pdf/${attachment.objectId}/${attachment.documentID}" class="link link_nowrap"
                                                	  download="${attachment.fullFileName}">
                                                    <span class="iconElement iconElement_cloud"><icon:download/></span>
                                                    <spring:theme code="text.account.followup.download"/>
                                                </a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>

                        <c:if test="${fn:length(serviceList) gt 0}">
                            <div class="contentModule-section">
                                <div class="contentModule-headline contentModule-headline_small "><spring:theme
                                        code="text.account.followup.comments"/></div>
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
            </div>
        </div>
    </div>
</div>

<script id="messagesSagiaGovtServices-template" type="text/template">

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

<script>
    var serviceName = "${serviceName}";
    var srID = ${srID};
</script>