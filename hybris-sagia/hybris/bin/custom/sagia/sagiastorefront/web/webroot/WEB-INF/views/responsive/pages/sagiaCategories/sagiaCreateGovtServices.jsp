<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="services" tagdir="/WEB-INF/tags/responsive/services" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
</script>
<spring:htmlEscape defaultHtmlEscape="true"/>

<!--site content for second license download page-->
<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="splitURI" value="${fn:split(currentPage, '/')}"/>
<c:set var="serviceUrl" value="${splitURI[fn:length(splitURI)-2]}"/>
<c:set var="categoryUrl" value="${splitURI[fn:length(splitURI)-3]}"/>
<c:set var="formName" value="createGovtService"/>
<c:url value="/services/government/create" var="createGovtServiceUrl"/>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    ${serviceNameDecoded}
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense or hasAwaitingPayment}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                    <div class="calendar notification p-0 sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img" alt="message"/>
                            </button>
                        </c:if>
                        <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
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

<!-- <div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">${serviceNameDecoded}</h1>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="row w-100 renewal-services">
            <div class="col-md-3">
                <a href="${request.contextPath}/services/government/${categoryUrl}/${serviceUrl}?serviceName=${serviceName}" class="btn btn_leftIconLink btn_darkLink back_to_service">
                    <span class="iconElement iconElement_closeBack  " id="image-pos-arrow"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span>
                    <spring:theme code="createGovtServices.backToServiceDetails.text"/>
                </a>
            </div>
        </div>
        <div class="row w-100 d-none mt-4">
            <div class="amend-service-link btn-drafts_list">
                <div class="col-xl-12 col-12 amend-btns-list">
                    <button class="btn btn_round btn_slim js-save-draft"
                            data-target-form="${formName}"
                            data-service-id="${serviceId}"><spring:theme code="general.savedraft"/>
                        <span class="iconElement iconElement_save"><icon:save/></span>
                    </button>

                    <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                            data-target-form="${formName}"
                            data-service-id="${serviceUrl}"><spring:theme code="general.loaddraft"/>
                        <span class="iconElement iconElement_save"><icon:upload/></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16 mt-5">
    <div class="container">

        <div class="expandableContent-main mt-4" id="expandedContentParent" style="${serviceList.size() == 0 ? 'visibility:hidden;' : ''}">
            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div id="detailedConvertToNationalsContent" class="contentModule">
                    <div class="contentModule-section">
                        <div class="statusBox">
                            <div class="statusBox-description">
                                <div class="statusBox-info">
                                    <spring:theme code="createGovtServices.info.text"/>
                                    <span class="tip" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title="">
                                        <svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18"><path fill="#999ca4" d="M7.567 6.081c.407-.433.965-.649 1.674-.649.657 0 1.182.186 1.577.556s.592.844.592 1.42c0 .349-.07.632-.214.849-.144.218-.437.537-.88.958-.323.305-.533.564-.63.776-.098.213-.146.526-.146.94h-.879c0-.471.056-.85.167-1.138s.361-.618.748-.99l.402-.39c.122-.111.219-.227.295-.35.136-.213.205-.436.205-.666 0-.323-.099-.603-.295-.84s-.522-.355-.975-.355c-.561 0-.949.204-1.165.612-.121.228-.189.555-.207.983h-.878c0-.711.203-1.283.609-1.716zm1.074 5.67h.982v1.027h-.982v-1.027z" enable-background="new"></path><path fill="#999ca4" d="M9 17.389c-4.625 0-8.389-3.763-8.389-8.389 0-4.625 3.764-8.389 8.389-8.389 4.626 0 8.389 3.764 8.389 8.389 0 4.626-3.763 8.389-8.389 8.389zm0-16c-4.197 0-7.611 3.414-7.611 7.611 0 4.196 3.414 7.611 7.611 7.611 4.196 0 7.611-3.415 7.611-7.611 0-4.197-3.415-7.611-7.611-7.611z"></path>
                                        </svg>
                                    </span>
                                </div>
                                <div class="statusBox-details">
                                    <c:set var="serviceUrl" value="${serviceUrl}"/>
                                    <c:choose>
                                        <c:when test="${fn:startsWith(serviceUrl,'ZMOCI')}">
                                            <div>
                                                <spring:theme code="services.type.zmoci.crnumber" text="CR Number: "/>
                                                <span>${infoData.crNumber}</span>
                                            </div>
                                            <div>
                                                <spring:theme code="services.type.zmoci.crissuesdate" text="CR Issue Date: "/>
                                                <span>${infoData.crIssueDate}</span>
                                            </div>
                                            <div>
                                                <spring:theme code="services.type.zmoci.crexpirydate" text="CR Expiry Date: "/>
                                                <span>${infoData.crExpiryDate}</span>
                                            </div>
                                        </c:when>
                                        <c:when test="${fn:startsWith(serviceUrl,'ZMOL')}">
                                            <div>
                                                <spring:theme code="services.type.zmol.molnumber" text="MOL Number: "/>
                                                <span>${infoData.molNumber}</span>
                                            </div>
                                            <div>
                                                <spring:theme code="services.type.zmol.molnitiqatsize" text="MOL Nitiqat Size: "/>
                                                <span>${infoData.molSize}</span>
                                            </div>
                                            <div>
                                                <spring:theme code="services.type.zmol.molcolor" text="MOL Color: "/>
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
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <form:form method="post" modelAttribute="${formName}" action="${createGovtServiceUrl}" enctype="multipart/form-data" class="js-create-governamentalDocuments">
            <c:if test="${fn:length(createGovtService.documentsToUpload) gt 0}">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section">
                            <!-- <div class="contentModule-headline contentModule-headline-service-info">
                                <spring:theme code="text.account.followup.supportDocuments"/>
                                <span class="iconElement iconElement_headlineTooltip js-tip" data-tip-title="<spring:theme code="text.account.followup.supportDocuments.mandatory"/>" data-original-title="" title=""><icon:tipInfo/></span>
                            </div> -->
                            <div class="contentModule contentModule-wrap">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                    <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                    <span class="iconElement iconElement_headlineTooltip js-tip" data-tip-title="<spring:theme code="text.account.followup.supportDocuments.mandatory"/>" data-original-title="" title=""><icon:tipInfo/></span>
                                    <div class="contentModule-headline-border"></div>
                                </div>
                            </div>

                            <div class="row mt-5">
                                <c:forEach items="${createGovtService.documentsToUpload}" var="document" varStatus="count">
                                    <div class="col-md-6">
                                        <div class="formInputFile">
                                            <div class="form-group <c:if test="${hasErrors && empty document.fileText}">has-error</c:if>">
                                                <input id="fileId_${count.index}" name="files[${count.index}]" data-id="${count.index}" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                                <input id="fileTextId_${count.index}" name="fileText[${count.index}]" class="form-control uploadServiceFile" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                                <label class="control-label control-label_mandatory">
                                                    <c:choose>
                                                        <c:when test="${empty document.fileText}">${document.description}</c:when>
                                                        <c:otherwise>${document.fileText}</c:otherwise>
                                                    </c:choose>
                                                </label>
                                                <input id="dockey_${count.index}" name="dockeyID[${count.index}]" type="hidden" value="${document.dockeyID}">
                                                <div class="form-icon form-icon_browse">
                                                    <icon:upload/>
                                                </div>
                                                <div class="form-icon form-icon_reset js-inputFile-reset">
                                                    <icon:cross/>
                                                </div>
												<c:if test="${not hasErrors && not empty document.fileText}">
                                                    <div class="help-block"></div>
                                                </c:if>
                                                <c:if test="${hasErrors && empty document.fileText}">
                                                    <div class="help-block">
                                                        <span id="create.govtServices.uploadError"><spring:theme code="create.govtServices.uploadError" text="You must also upload a file in here."/></span>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
							<div class="upload-doc-note"><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions action_res_view">
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox event="LABOUR" id="termsAndConditions" path="termsAndConditionsChecked"/>
                    </div>
                </div>
                <button type="button" class="btn btn_leftIconLink btn_outline" onclick="window.location.href='${request.contextPath}/services/government/${categoryUrl}/${serviceUrl}?serviceName=${serviceName}'">
                    <spring:theme code="general.cancel"/>
                </button>

                <button type="submit" class="btn js-submit-governamentalDocuments">
                    <spring:theme code="general.submit"/>
                </button>
            </div>

            <input type="hidden" name="srID" value="${srID}"/>
            <input type="hidden" name="ministryType" value="${fn:substringBefore(serviceUrl, "_")}"/>
            <input type="hidden" name="serviceType" value="${serviceUrl}"/>
            <input type="hidden" name="categoryUrl" value="${categoryUrl}"/>
            <input type="hidden" name="serviceName" value="${serviceName}"/>
            <input type="hidden" id="serviceId"/>
        </form:form>
    </div>
</div>

<script>
    var srID = 0;
</script>
