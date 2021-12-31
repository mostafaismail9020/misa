<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    var createRequestEnabled = ${reopenFacilityRequests.createRequestEnabled};
</script>
<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="facilityReopen.title"/></h1>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
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
</div>



<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard"
               class="btn btn_leftIconLink btn_darkLink"><span>&times;</span><spring:theme
                    code="general.backtodashboard"/></a>
            <c:if test="${fn:length(reopenFacilityRequests.serviceRequests) gt 0}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent"
                        data-expand-target="expand01">
                    <div class="hidden">
                        <span><spring:theme code="text.account.followup.showServiceHistory"/>&#x27f6;</span>
                    </div>
                    <div>
                        <span><spring:theme code="text.account.followup.hideServiceHistory"/>&#x27f6;</span>
                    </div>
                </button>
            </c:if>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(reopenFacilityRequests.serviceRequests) gt 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_history"><icon:history/></span>
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
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline">
                                    <icon:info/><spring:theme code="facilityReopen.entityInformation.text"/>
                                </div>
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
                            <div class="contentModule-actions contentModule-actions_noMargin contentModule-actions_wrap">
                                <div class="contentModule-headline contentModule-headline_smallMargin">
                                    <icon:enquiry2/><spring:theme code="facilityReopen.comment.text"/>
                                </div>
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
                            <div class="contentModule-headline">
                                <icon:documents/>
                                <spring:theme code="facilityReopen.documents.text"/>
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
