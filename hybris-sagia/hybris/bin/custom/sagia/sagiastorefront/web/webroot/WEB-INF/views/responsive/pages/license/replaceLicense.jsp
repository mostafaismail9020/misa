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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="services" tagdir="/WEB-INF/tags/responsive/services" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>
<c:set var="formName" value="licenseReplacementResubmitFormData"/>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="licenseReplacement.title"/></h1>
            <c:if test="${not empty processingTime}">
                <div class="serviceTime">
                    <div class="serviceTime-label"><spring:theme code="average.service.time"/></div>
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

<div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div>
                <button type="submit" class="btn btn_slim js-replace-new-validation" data-entity-status="${entityStatus}">
                    <spring:theme code="licenseReplacement.replace"/>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span
                    class="iconElement iconElement_closeBack"><icon:close/></span> <spring:theme
                    code="licenseReplacement.back.dashboard"/></a>
            <c:if test="${fn:length(LicenseReplaceMents) gt 1}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                    <div class="hidden"><spring:theme code="licenseReplacement.download.history.show"/>
                        <span class="iconElement iconElement_closeBack"><icon:close/></span>
                    </div>
                    <div><spring:theme code="licenseReplacement.download.history.hide"/><span>&#x27f6;</span></div>
                </button>
            </c:if>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(LicenseReplaceMents) gt 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_history"><icon:history/></span>
                                    <spring:theme code="licenseReplacement.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input id="replaceSearchBox" class="searchInputBox-input" type="text" placeholder="<spring:theme code="licenseReplacement.search" />"/>
                                </div>

                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${LicenseReplaceMents}" var="licenseReplaceMent" varStatus="licenseReplaceMentStatus">
                                        <li style="cursor: pointer;" data-expand-target="expand01" class="historyList-item
                                            <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${licenseReplaceMent.srID == licensereplacementFormData.srID}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${licenseReplaceMentStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">
                                            <a class="historyList-link">
                                                <div class="historyList-id">${licenseReplaceMent.srID}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${licenseReplaceMent.srCrDate.dateFormatted}</div>
                                                    <div class="historyList-status historyList-status_${fn:replace(fn:toLowerCase(licenseReplaceMent.srStDesc),' ', '')}">${licenseReplaceMent.srStDesc}</div>
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
                        <div class="contentModule-section" id="detailedLicenseReplacementContent">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_info"><icon:info/></span>
                                    <span id="currentID">${licensereplacementFormData.srID}</span>
                                    <span style="display: none;" id="currentGUID">${licensereplacementFormData.srGuid}</span>
                                </div>
<!--
                                <div>
                                    <button type="submit" class="btn btn_slim js-replace-new-validation" data-entity-status="${entityStatus}">
                                        <spring:theme code="licenseReplacement.replace"/>
                                    </button>
                                </div>
-->

                                <c:if test="${fn:length(LicenseReplaceMents) gt 0}">
                                    <div id="currentStatus" class="statusIndicator statusIndicator_${fn:replace(fn:toLowerCase(licensereplacementFormData.srStDesc),' ', '')}">
                                        <spring:theme code="licenseReplacement.status"/> <span id="statusText">${licensereplacementFormData.srStDesc}</span>
                                    </div>
                                </c:if>
                            </div>

                            <div class="statusBox">
                                <div class="statusBox-description">
                                    <div class="statusBox-info"><spring:theme code="licenseReplacement.info"/>
                                        <span class="iconElement iconElement_headlineTooltip js-tip"
                                              data-tip-title="${serviceDescription}"
                                              data-original-title="" title="">
                                            <icon:tipInfo/>
                                        </span>

                                    </div>
                                    <div class="statusBox-details">
                                        <c:choose>
                                            <c:when test="${empty licenseReplaceMentinfo}">
                                                <spring:theme code="licenseReplacement.longDescr.missing"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${empty licenseReplaceMentinfo.longDescr}">
                                                    <spring:theme code="licenseReplacement.longDescr.missing"/>
                                                </c:if>
                                                ${licenseReplaceMentinfo.longDescr}
                                            </c:otherwise>
                                        </c:choose>
                                        <services:undertakingLetters/>
                                    </div>
                                    <div class="statusBox-details">
                                        ${serviceDetailInfo.longDescr}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${fn:length(LicenseReplaceMents) gt 0}">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline_small"><spring:theme code="licenseReplacement.comments"/></div>
                                <div class="commentModule">
                                    <div class="commentModule-window">
                                        <ul id="messagesListUL" class="messageList">
                                            <c:choose>
                                                <c:when test="${empty messages}">
                                                    <div class="messageList-message" align="center">
                                                        <p><spring:theme code="licenseReplacement.comments.missing"/></p>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${messages}" var="messages">
                                                        <li class="messageList-item">
                                                            <div class="messageList-img">
                                                                <img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="">
                                                            </div>
                                                            <div class="messageList-content">
                                                                <h2 class="messageList-name">${messages.commentBy}</h2>
                                                                <h3 class="messageList-date">${messages.commentDate.dateTimeFormatted}</h3>
                                                                <div class="messageList-message"><p>${messages.comments}</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <form:form action="${encodedContextPath}/my-sagia/license/replace/1/1/resubmit"
                           enctype="multipart/form-data"
                           method="post"
                           id="licenseReplacementResubmitForm"
                           modelAttribute="${formName}">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section" id="attachedFilesDivContent">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_bordered">
                                    <div class="contentModule-headline">
                                        <span class="iconElement iconElement_documents"><icon:documents/></span>
                                        <spring:theme code="licenseReplacement.support.documents"/>
                                    </div>
                                    <div>
                                        <button type="submit" value="SUBMIT" class="btn btn_outline btn_slim"
                                                id="resubmitButton" data-toggle="modal"
                                                <c:choose>
                                                    <c:when test="${fn:toUpperCase(licensereplacementFormData.srStDesc) == 'REJECTED'}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        disabled="disabled" style="display: none;"
                                                    </c:otherwise>
                                                </c:choose>>
                                            <spring:theme code="text.account.followup.resubmit"/>
                                        </button>
                                    </div>
                                </div>
                                <ul id="attachmentList" class="downloadList">
                                    <c:forEach items="${attachedFiles}" var="attachment">
                                        <li class="downloadList-item">
                                            <div style="cursor: pointer;" class="downloadList-description"
                                                 data-view-attachment-target data-object-id="${attachment.objectID}"
                                                 data-file-name="${attachment.filename}"
                                                 data-document-id="${attachment.documentID}">
                                                <span class="iconElement iconElement_pdf"><icon:pdf/></span>${attachment.filename}
                                            </div>
                                            <div id="attachmentsActionsDIV" class="downloadList-actions"
                                                 style="cursor: pointer;"
                                                 data-target
                                                 data-object-id="${attachment.objectID}"
                                                 data-document-id="${attachment.documentID}"
                                                 data-file-name="${attachment.filename}">
                                                <a id="downloadAnchorTag" class="link link_nowrap" href="${encodedContextPath}/attachment/pdf/${attachment.objectID}/${attachment.documentID}"
                                                	 download="${attachment.fullFileName}">
                                                     <span class="iconElement iconElement_cloud">
                                                           <icon:download/>
                                                     </span>
                                                    <spring:theme code="licenseReplacement.download"/>
                                                </a>
                                                <a href="#" class="link link_nowrap"
                                                        <c:choose>
                                                            <c:when test="${fn:toUpperCase(licensereplacementFormData.srStDesc) == 'REJECTED'}">
                                                                style="display: block;"
                                                            </c:when>
                                                            <c:otherwise>
                                                                style="display: none;"
                                                            </c:otherwise>
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
                                                        <input id="inputFile${attachment.documentID}" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" name="files[${theCount.index}].multiPartFile"/>
                                                        <label class="control-label" id="controlLabel-inputFile${attachment.documentID}">
                                                            <span class="formInputFileBox-list-dragndrop">
                                                                <spring:theme code="general.chooseafile"/>&nbsp;<spring:theme code="general.draghere"/>
                                                            </span>.
                                                        </label>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <div class="formInputFileBox-uploading"><spring:theme code="company.uploading"/></div>
                                    <div class="formInputFileBox-success"><spring:theme code="company.done"/></div>
                                    <div class="formInputFileBox-error"><spring:theme code="company.error"/>
                                        <span></span>.
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" type="submit" value="" class="btn btn_slim btn_round" data-dismiss="modal"><spring:theme code="convertlicense.replacefile"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script id="expandedLicenseReplace-template" type="text/template">
    <div class="contentModule-section contentModule-section_slimDivider">
        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
            <div class="contentModule-headline">
                <span class="iconElement iconElement_info"><icon:info/></span>
                <span id="currentID">{{srID}}</span>
                <span style="display: none;" id="currentGUID">{{srGuid}}</span>
            </div>
            <div id="currentStatus" class="statusIndicator statusIndicator_{{srStDescIndicator}}">
                <spring:theme code="licenseReplacement.status"/> <span id="statusText">{{srStDesc}}</span>
            </div>
        </div>

        <div class="statusBox">
            <div class="statusBox-description">
                <div class="statusBox-info"><spring:theme code="licenseReplacement.info"/>
                    <span class="tip" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title="">
                        <icon:tipInfo/>
                    </span>
                </div>
                <div class="statusBox-details">
                    {{longDescr}}<services:undertakingLetters/>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="supportedAttachments-template" type="text/template">
    <li class="downloadList-item">
        <div style="cursor: pointer;" class="downloadList-description" data-view-attachment-target data-object-id="{{objectID}}" data-document-id="{{documentID}}">
            <span class="iconElement iconElement_pdf"><icon:pdf/></span>{{filename}}
        </div>
        <div class="downloadList-actions" style="cursor: pointer;" data-target data-object-id="{{objectID}}" data-document-id="{{documentID}}" data-file-name="{{filename}}">
            <a id="downloadAnchorTag" class="link link_nowrap" href="{{downloadUrl}}" 
 					download="{{fullFileName}}">
                <span class="iconElement iconElement_cloud">
                    <icon:download/>
                </span>
                <spring:theme code="text.account.followup.download"/>
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
                <input id="inputFile{{documentID}}" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" name="files[{{index}}].multiPartFile"/>
                <label class="control-label" id="controlLabel-inputFile{{documentID}}">
                    <span class="formInputFileBox-list-dragndrop">
                        <spring:theme code="general.chooseafile"/>&nbsp;<spring:theme code="general.draghere"/>
                    </span>.
                </label>
            </div>
        </div>
    </li>
</script>

<script id="missingMessages-template" type="text/template">
    <div class="messageList-message" align="center">
        <p><spring:theme code="licenseReplacement.comments.missing"/></p>
    </div>
</script>

<script id="messagesLicenseReplace-template" type="text/template">
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