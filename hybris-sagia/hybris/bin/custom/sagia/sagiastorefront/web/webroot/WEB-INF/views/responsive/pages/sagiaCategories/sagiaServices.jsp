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


<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">${serviceName}</h1>
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

<div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
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
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard"
               class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard" /></a>
            <c:if test="${fn:length(serviceList) gt 1}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent"
                        data-expand-target="expand01">
                    <div><spring:theme code="text.account.followup.hideServiceHistory"/><span>&#x27f6;</span></div>
                    <div class="hidden"><spring:theme code="text.account.followup.showServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span></div>
                </button>
            </c:if>
        </div>
    </div>
</div>

<div class="service-wrapper service-wrapper-info">
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

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(serviceList) gt 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_history"><svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            width="24" height="24"
                                            viewBox="0 0 24 24"><g
                                            transform="translate(1 1)" fill="none"><circle cx="11" cy="11" r="10"
                                                                                           class="iconElement-colorPrimary_stroke"
                                                                                           stroke-width="2"></circle><path
                                            class="iconElement-colorSecondary_stroke" stroke-linecap="round"
                                            stroke-linejoin="round"
                                            d="M10.542 4.125v8.25m0 0l5.5-1.833"></path></g></svg></span>
                                    <spring:theme code="text.specialservices.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input onkeyup="filterHistory(this)" id="convertSearchBox"
                                           class="searchInputBox-input" type="text"
                                           placeholder="<spring:theme code='storeFinder.search'/>">
                                </div>
                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${serviceList}" var="service">
                                        <li class="historyList-item serviceItem" style="cursor: pointer;"
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
            <div class="expandableContent-main" id="expandedContentParent"
                 style="${serviceList.size() == 0 ? 'visibility:hidden;' : ''}">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div id="detailedConvertToNationalsContent" class="contentModule">
                        <div class="contentModule-section">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_info"><svg xmlns="http://www.w3.org/2000/svg"
                                                                                    width="24" height="24"
                                                                                    viewBox="0 0 24 24"><g
                                            fill="#5CC83B" fill-rule="evenodd"><path
                                            d="M11.956 21.702c-5.375 0-9.746-4.371-9.746-9.746s4.37-9.746 9.746-9.746c5.375 0 9.746 4.37 9.746 9.746 0 5.375-4.371 9.746-9.746 9.746zm0-21.702c-6.58 0-11.956 5.375-11.956 11.956 0 6.58 5.375 11.906 11.956 11.906 6.58 0 11.906-5.325 11.906-11.906 0-6.58-5.325-11.956-11.906-11.956zM13.387 16.213l-1.005.452 1.306-6.681c.15-.703-.603-1.457-1.407-1.005l-2.461 1.357c-.452.25-.603.853-.352 1.306.251.452.854.602 1.306.351l.603-.301-1.256 6.38c-.1.552.352 1.155.955 1.155.15 0 .301-.05.402-.1l2.662-1.156c.503-.2.703-.753.503-1.256-.201-.502-.754-.703-1.256-.502m-.662-8.953a1.35 1.35 0 0 0 1.356-1.356 1.35 1.35 0 0 0-1.356-1.357 1.35 1.35 0 0 0-1.357 1.357 1.35 1.35 0 0 0 1.357 1.356"></path></g></svg></span>
                                    <span id="currentID">
                                        <c:if test="${fn:length(serviceList) gt 0}">
                                            ${serviceList[0].srID}
                                        </c:if>
                                    </span>
                                </div>
<!--
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
-->
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
                                    ">
                                        <spring:theme code="convertlicense.status"/><span id="statusText">
                                                <c:if test="${fn:length(serviceList) gt 0}">
                                                    ${serviceList[0].srStDesc}
                                                </c:if>
                                            </span>
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
                                <div class="contentModule-headline contentModule-headline_bordered">
                                    <icon:documents/><spring:theme code="text.account.followup.supportDocuments"/>
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