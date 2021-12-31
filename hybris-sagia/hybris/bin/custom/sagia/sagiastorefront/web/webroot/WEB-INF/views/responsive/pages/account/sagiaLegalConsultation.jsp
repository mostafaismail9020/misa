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

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="legalConsultationCreate.title" /></h1>
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

<div class="mainSection mainSection_dark mainSection_pdt16">
<div class="container">
<!-- <div style="padding: 15px 67px 15px;"> -->
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
</div>
<!-- </div> -->

<div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
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
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/></a>
            <c:if test="${fn:length(legalConsultations) gt 1}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                    <div class="hidden"><spring:theme code="legalConsultation.showServiceHistory"/><span>&#x27f6;</span></div>
                    <div><spring:theme code="legalConsultation.hideServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span></div>
                </button>
            </c:if>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(legalConsultations) gt 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline">
                                <span class="iconElement iconElement_history"><icon:history/></span>
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
                                    <div  class="contentModule-headline">
                                    <span class="iconElement iconElement_info"><icon:info/></span>
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
                                    <div class="contentModule-headline contentModule-headline_small "><spring:theme code="legalConsultation.comments"/></div>
                                    <div class="commentModule">
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

                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered">
                                    <div  class="contentModule-headline">
                                        <span class="iconElement iconElement_documents"><icon:documents/></span>
                                        <spring:theme code="legalConsultation.supportDocuments"/>
                                    </div>
                                </div>
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
     <div  class="contentModule-headline">
     <span class="iconElement iconElement_info"><icon:info/></span>
     <span id= "currentID">{{srId}}</span>
    </div>
   <div>
	<button class="btn btn_slim"
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