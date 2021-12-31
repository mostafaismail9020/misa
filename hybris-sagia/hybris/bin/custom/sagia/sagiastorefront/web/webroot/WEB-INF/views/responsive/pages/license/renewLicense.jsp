<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ taglib prefix="services" tagdir="/WEB-INF/tags/responsive/services" %>
<%@ include file="/WEB-INF/tags/responsive/common/infoModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>



<c:url var="attachmentURL" value="/attachment/pdf/"/>
<c:url var="rootURL" value="/"/>

<c:set var="status_in_process"><spring:theme code="service.status.indicator.process"/></c:set>
<c:set var="status_rejected"><spring:theme code="service.status.indicator.reject"/></c:set>
<c:set var="status_completed"><spring:theme code="service.status.indicator.complete"/></c:set>
<c:set var="status_accepted"><spring:theme code="service.status.indicator.accept"/></c:set>

<script>
    var fromRenewSubmitPage = ('${fromRenewSubmitPage}' === 'true');
    var autoRenewal = "${autoRenewal}";
    var autoRenewalClearance = "${autoRenewalClearance}";
</script>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="renewlicense.licenserenewal"/></h1>
            	<input type="hidden" id="serviceId"/>
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

<div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div id="renewalButtons" style="display: none;">
                <c:choose>
                    <c:when test ="${autoRenewal}">
                        <a class="btn btn_slim btn-warning btn_outline jqInstantRenewal" href="javascript:void(0);">
                            <spring:theme code="renewlicense.instantrenew"/>
                        </a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim btn-warning btn_outline jqInstantRenewal" href="javascript:void(0);" style="display: none;">
                            <spring:theme code="renewlicense.instantrenew"/>
                        </a>&nbsp;
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test ="${autoRenewalClearance}">
                        <a class="btn btn_slim btn-warning btn_outline jqInstantClearanceRenewal" href="javascript:void(0);">
                            <spring:theme code="renewlicense.instantrenew.clearance"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim btn-warning btn_outline jqInstantClearanceRenewal" href="${encodedContextPath}/my-sagia/license/renew/edit" style="display: none;">
                            <spring:theme code="renewlicense.instantrenew.clearance"/>
                        </a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test ="${governmentDocumentsCheck}">
                        <a class="btn btn_slim btn-warning btn_outline jqGovDocsCheck" href="javascript:void(0);">
                            <spring:theme code="renewlicense.govDocsCheck"/>
                        </a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim btn-warning btn_outline jqGovDocsCheck" href="javascript:void(0);" style="display: none;">
                            <spring:theme code="renewlicense.govDocsCheck"/>
                        </a>&nbsp;
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test ="${clearanceCheck}">
                        <a class="btn btn_slim btn-warning btn_outline jqClearanceCheck" href="javascript:void(0);">
                            <spring:theme code="renewlicense.clearanceCheck"/>
                        </a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn_slim btn-warning btn_outline jqClearanceCheck" href="javascript:void(0);" style="display: none;">
                            <spring:theme code="renewlicense.clearanceCheck"/>
                        </a>&nbsp;
                    </c:otherwise>
                </c:choose>
                <a class="btn btn_slim jqCreateRenewal" href="${encodedContextPath}/my-sagia/license/renew/edit" style="display: none;">
                    <spring:theme code="dashboard.myLicense.renew"/>
                </a>
            </div>      
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/></a>
            <c:if test="${fn:length(licenseRenew) > 1}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" id="historyList" data-expand-target="expand01">
                    <div><spring:theme code="text.account.followup.hideServiceHistory"/><span>&#x27f6;</span></div>
                    <div class="hidden"><spring:theme code="text.account.followup.showServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span></div>
                </button>
            </c:if>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(licenseRenew) > 0}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_history"><icon:history/></span><spring:theme code="text.account.followup.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input onkeyup="filterHistory(this)" class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul class="historyList" id="history-list" >
                                    <c:forEach items="${licenseRenew}" var="licenseItem" varStatus="licenseStatus">
                                        <li style="cursor: pointer" data-expand-target="expand01" class="historyList-item
                                            <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${licenseItem.srID == license.srID}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${licenseStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">

                                            <a class="historyList-link">
                                                <div class="historyList-id">${licenseItem.srID}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">
                                                    ${licenseItem.dateData.dateFormatted}
                                                </div>
                                                <div class="historyList-status 
                                                  <c:choose>
        												<c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_in_process)}">historyList-status_inprocess</c:when>
        												<c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_rejected)}">historyList-status_rejected</c:when>
        												<c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_accepted)}">historyList-status_accepted</c:when>
        											    <c:when test ="${fn:containsIgnoreCase(licenseItem.statusDescription, status_completed)}">historyList-status_completed</c:when>
       												<c:otherwise>historyList-status_inprocess</c:otherwise>
    												</c:choose> ">
                                              		${licenseItem.statusDescription}</div>
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
                                <input type="hidden" name="csrfToken" value="${_csrf.token}"/>
                                <div class="contentModule-headline"><icon:info/><spring:theme code="renewlicense.serviceinfo"/><span id="srID">${license.srID}</span></div>
                                <c:if test="${fn:length(licenseRenew) gt 0}">
                                    <div id="currentStatus" class="statusIndicator
                                 <c:choose>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_in_process)}">statusIndicator_inprocess</c:when>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_rejected)}">statusIndicator_rejected</c:when>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_accepted)}">statusIndicator_accepted</c:when>
        								<c:when test ="${fn:containsIgnoreCase(license.statusDescription, status_completed)}">statusIndicator_completed</c:when>
       								<c:otherwise>statusIndicator_inprocess</c:otherwise>
    								</c:choose> ">
                                        <spring:theme code="renewlicense.status" />
                                        <span id="statusText">${license.statusDescription}</span>
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
                                    <div class="statusBox-details">
                                        <c:if test="${not empty serviceInformation}">
                                            ${serviceInformation.longDescription}
                                        </c:if>
                                        <c:if test="${empty serviceInformation}">
                                            <spring:theme code="services.missing.description" />
                                        </c:if>
                                        <services:undertakingLetters/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt data-name="street"><spring:theme code="address.streetorunitno"/></dt>
                                        <dd><span name="street">${license.address.street} / ${license.address.houseNo}</span></dd>
                                        <dt><spring:theme code="general.country"/></dt>
                                        <dd><span name="country">${license.address.country}</span></dd>
                                        <dt><spring:theme code="address.postalcodeorcity"/></dt>
                                        <dd><span name="zipCode">${license.address.zipCode}</span></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="license.additionalnumber"/></dt>
                                        <dd><span name="additNo">${license.address.additionalNotes}</span></dd>
                                        <dt><spring:theme code="address.buildingno"/></dt>
                                        <dd><span name="building">${license.address.building}</span></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="contentModule-actions contentModule-actions_right" id="resubmitContainer">
                            <c:if test ="${license.reApply}">
                                <a href="${encodedContextPath}/my-sagia/license/renew/reapply/${license.srID}" class="btn btn_slim btn_outline"><spring:theme code="licenseReplacement.reapply"/></a>
                            </c:if>
                        </div>

                        <c:if test="${fn:length(licenseRenew) gt 0}">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin comments-wrapper"
                                 <c:if test="${fn:length(license.comments) > 0}">style="display: none"</c:if>>
                                <div class="contentModule-headline contentModule-headline_small"><spring:theme code="general.comments"/></div>
                                <div class="commentModule">
                                    <div class="commentModule-window">
                                        <ul class="messageList">
                                            <c:forEach items="${license.comments}" var="comment">
                                                <%--<fmt:parseDate value="${comment.date}" pattern="yyyy-MM-dd" var="parsedDateAppt" type="both"/>--%>
                                                <li class="messageList-item">
                                                    <div class="messageList-img">
                                                        <span class="iconElement iconElement_expertProfile_white"><icon:expertProfile/></span>
                                                    </div>
                                                    <div class="messageList-content">
                                                        <h2 class="messageList-name">${comment.commentBy}</h2>
                                                        <h3 class="messageList-date">
                                                                ${comment.dateData.dateFormatted}
                                                        </h3>
                                                        <div class="messageList-message">${comment.comments}</div>
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
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline contentModule-headline_bordered">
                                <icon:pictures/><spring:theme code="general.pictures"/>
                            </div>
                            <div id="documents-container1"></div>
                            <ul class="pictureGrid" id="images-container">
                                <c:forEach items="${license.attachedImages}" var="image">
                                    <li><img id="image" src="${encodedContextPath}/my-sagia/license/image/${image.objectID}/${image.documentID}"/></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline contentModule-headline_bordered"><icon:documents/><spring:theme code="text.account.followup.supportDocuments"/></div>
                            <ul class="downloadList" id="documents-container">
                                <c:forEach items="${license.attachedDocuments}" var="document">
                                    <li class="downloadList-item js-download-service-attachment">
                                        <div class="downloadList-description">
                                            <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                            <c:out value="${not empty document.fileName ? document.fileName : ''}" />
                                        </div>
                                        <div class="downloadList-actions">
                                            <a href='${encodedContextPath}/attachment/pdf/${document.objectId}/${document.documentID}' class="link link_nowrap" download="${document.fullFileName}">
                                                <span class="iconElement iconElement_cloud"><icon:download/></span><spring:theme code="general.download"/>
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


<!-- Instant Renewal Modal -->
<div class="modal fade" id="instantRenewal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"><spring:theme code="licenseApplyEntityInformation.licenseYearSection.title"/></div>
			</div>
		 	<div class="modal-body">
			 <div class="contentModule-section contentModule-section_paddingSide">
						
                        <div class="documentModule js-upload-files-list" data-files-name="img">
                            
                               <div class="formSelectBox">
									<div class="form-group">
									    
										<select id="durationSelectInstant" name="durationSelectInstant" class="js-select2-oneColumn select2-hidden-accessible"  aria-hidden="true" onChange="updateExpiryDate(this, event)">
										      <option></option>  
										       <c:forEach items="${durations}" var="duration">
                                                        <option value="${duration.code}"> ${duration.name} </option>                                        
                                              </c:forEach>
									    </select>
									    <label class="control-label control-label_mandatory" for="durationSelectInstant"><spring:theme code="license.apply.entity.licenseYear"/></label>
									    
									</div>
									<div class="help-block"></div>
									 <span class="renewMessage" > <spring:theme code="license.apply.licenseYear.warning"/> </span>
									 <br/><br/>
									 <span> <spring:theme code="license.apply.licenseYear.note"/> </span>
									 <br/>
									 <span> <spring:theme code="license.apply.licenseYear.example"/> </span>
									 <br/>
									 <span class="renewMessage" id="expDateTag" style="display: none"></span>
									 <input class="renewMessage" id="expDate" style="display: none" value="${licExDateData.date}"/>
									 <input class="renewMessage" id="dateUIPattern" style="display: none" value="${licExDateData.dateUIPattern}"/>
							   </div>
                        </div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="jqInstantRenewalSubmit" class="btn btn_slim" data-dismiss="modal"><spring:theme code="general.submit"/></button>
				<button type="button" class="btn btn-secondary js-cancel-create-realEstate" data-dismiss="modal"><spring:theme code="general.cancel"/></button>
				
			</div>
			
		</div>
	</div>
</div>



<div class="messageTemplateWrapper" style="display: none">
    <li class="messageList-item">
        <div class="messageList-img">
            <span class="iconElement iconElement_expertProfile_white"><icon:expertProfile/></span>
        </div>
        <div class="messageList-content">
            <h2 class="messageList-name"></h2>
            <h3 class="messageList-date"></h3>
            <div class="messageList-message"></div>
        </div>
    </li>
</div>

<c:if test="${isAllowed eq ''}">
<div class="modal fade" id="renewalDisclaimer"  tabindex="-1" role="dialog" aria-labelledby="renewalDisclaimer" aria-hidden="true"  data-keyboard="false" data-backdrop="static">
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
                <a class="btn js-close-btn" href="${encodedContextPath}/dashboard">Close</a>
            </div>
        </div>
    </div>
</div>
</c:if>

<script>
    var url = '${attachmentURL}';
    var attachmentURL = '${attachmentURL}';
    var licenseid = ${licenseRenew[0].srID};
    var icon = '<icon:pdf/>';
</script>

