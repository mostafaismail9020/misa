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
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var cancellationMessage = '${licenseCancellationMessage}';
</script>

<div class="mainSection mainSection_white">
    <div class="container">
        <div class="mainSection-header">
            <div class="mainSection-headline">

                <spring:theme code="licenseCancellation.title"/>
            </div>
            <div class="mainSection-action">
                <button class="btn btn_round"><spring:theme code="licenseCancellation.save.draft"/>
                    <icon:save/></button>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_noPadding">
    <div class="container">
        <div class="stepModule">

            <c:if test="${ globalLicenseCancellation.stage eq '01' || globalLicenseCancellation.stage eq '02'}">
                <div class="stepModule-item active">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step1/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step1_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.request.cancellation.letter"/>
                    </div>
                </div>
                <div class="stepModule-item">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step2/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step2_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.collect.cancellation.docs"/>
                    </div>
                </div>
                <div class="stepModule-item">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step3/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step3_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.submit.cancellation.docs"/>
                    </div>
                </div>
                <div class="stepModule-item">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step4/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step4_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.cancellation.approval"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${ globalLicenseCancellation.stage eq '04'}">
                <div class="stepModule-item">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step1/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step1_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.request.cancellation.letter"/>
                    </div>
                </div>
                <div class="stepModule-item">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step2/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step2_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.collect.cancellation.docs"/>
                    </div>
                </div>
                <div class="stepModule-item">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step3/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step3_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.submit.cancellation.docs"/>
                    </div>
                </div>
                <div class="stepModule-item active">
                    <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step4/></div>
                    <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step4_hover/></div>
                    <div class="stepModule-text">
                        <spring:theme code="licenseCancellation.cancellation.approval"/>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<%-- step1a --%>
<div class="mainSection mainSection_white mainSection_noPadding">
    <div class="container">
        <form:form action="${encodedContextPath}/my-sagia/license/cancel/stage/next"
                   id="formCancelLicenseStage1A"
                   enctype="multipart/form-data"
                   method="post"
                   modelAttribute="licenseCancelFormData">
            <div class="contentModule">
            	<c:choose>
            	<c:when test ="${isInstant}">
               		<div class="contentModule-section">
                    	<!-- <div class="contentModule-headline"> -->
                    	${licenseCancellationMessage}
                    	<!-- </div> -->
                    </div>
                </c:when>
               	<c:otherwise>
                <div class="contentModule-section">
                    <div class="contentModule-headline contentModule-headline_bordered">
                        <span class="iconElement iconElement_documents"><icon:documents/></span>
                        <spring:theme code="licenseCancellation.support.documents"/></div>
                    <ul id="attachmentList" class="downloadList">
                        <c:forEach items="${uploadedAttachments}" var="attachment">
                            <li class="downloadList-item">
                                <div style="cursor: pointer;" class="downloadList-description"
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
                                    <a  id = "downloadAnchorTag" class="link link_nowrap"
                                        href="${encodedContextPath}/attachment/pdf/${attachment.objectId}/${attachment.documentID}" 
                                        download="${attachment.fullFileName}">
                                                     <span class="iconElement iconElement_cloud">
                                                           <icon:download />
                                                     </span>
                                        <spring:theme code="licenseReplacement.download" />
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
				</c:otherwise>
				</c:choose>

                <div class="contentModule-actions contentModule-actions_spaceBetween">
                    <button type="button" class="btn btn-secondary btn-back">
                        <spring:theme code="licenseCancellation.back"/>
                    </button>
                    <c:if test="${ globalLicenseCancellation.srStCode eq 'E0002' && globalLicenseCancellation.stage eq '02'}">
                        <div class="formCheckBox formCheckBox_belowPanel">
                            <div class="form-group">
                                <div class="form-item">
                                    <input id="lc_a1" name="lc_a1" class="form-control" placeholder="."
                                           type="checkbox">
                                    <label class="control-label " for="lc_a1">
                                        <span><icon:check/></span>
                                        <spring:theme code="licenseCancellation.print.cancellation.letter"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </c:if>                    
                    <c:if test="${globalLicenseCancellation.srStCode eq 'E0002' && globalLicenseCancellation.stage eq '02'}">
                        <button type="submit" class="btn">
                            <spring:theme code="licenseCancellation.next"/>
                        </button>
                    </c:if>

                </div>
            </div>
        </form:form>
    </div>
</div>

<div class="modal fade" id="blockCancellation"  tabindex="-1" role="dialog" aria-labelledby="blockCancellation" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%-- <div class="modal-title"><spring:theme code="general.requestsubmitted"/></div> --%>
            </div>
            <div class="modal-body">
                <div class="modal-description js-description-text">
                <label class="control-label" for="department">${licenseCancellationMessage}</label>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn js-close-btn" href="${encodedContextPath}/dashboard"><spring:theme code="general.close"/></a>
            </div>
        </div>
    </div>
</div>

