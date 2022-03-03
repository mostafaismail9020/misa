<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<c:choose>
    <c:when test="${branchData.statusCode == 'V' || branchData.statusCode == 'I' }">
        <c:set var="disabled" value="disabled"/>
    </c:when>
    <c:otherwise>
        <c:set var="disabled" value=""/>
    </c:otherwise>
</c:choose>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image" src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="general.governmentdocuments" />
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
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png" class="notification_b2b_img"/>
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
            <h1 class="mainSection-headline">Government documents</h1>
        </div>
    </div>
</div> -->
<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
		<a href="${encodedContextPath}/my-sagia/sagia-profile" class="btn btn_leftIconLink btn_darkLink back_to_service">
           	<span class="iconElement iconElement_closeBack" id="image-pos-arrow">
           		<img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/>
           	</span><spring:message code="myprofile.account.overview"/>
		</a>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16 mt-5">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                            <span class="contentModule-headline"><spring:theme code="dashboard.myLicense.branches"/>
                                ${pageType}</span>
                            <div class="contentModule-headline-border"></div>
                        </div>
                    </div>
                    <div class="formError hidden" id="error-group">
                        <icon:messageError/><spring:theme code="govDocs.error.branchesNotCompleted"/>
                    </div>

                    <div class="tableModule mt-3">
                        <table class="tableModule-table">
                            <thead class="tableModule-head">
                            <tr>
                                <th><spring:theme code="text.account.profile.license.branches.name"/></th>
                                <th><spring:theme code="text.account.profile.license.branches.type"/></th>
                                <th><spring:theme code="text.account.profile.license.branches.city"/></th>
                                <th class="tableModule-headItem tableModule-headItem_actionsCount_2">
                                	<%--<spring:theme code="dashboard.myLicense.branches.actions"/>--%>
                                </th>
                            </tr>
                            </thead>
                            <tbody class="tableModule-body">
                            <c:forEach items="${branches}" var="branch" varStatus="loop">
                                <tr>
                                    <td><c:out value="${branch.name}"/></td>
                                    <td><c:out value="${branch.type}"/></td>
                                    <td><c:out value="${branch.city}"/></td>
                                    <td class="tableModule-bodyItem-action">
                                        <button class="btn btn_link no_background hidden" data-completed-index="${loop.index}">
                                            <icon:status-complete/>
                                        </button>
                                        <button class="btn btn_link btn-edit-branch" data-toggle="modal"
                                                data-target="#branchEditModal" data-index="${loop.index}"
                                                onclick="showBranchInModal(this)">
                                            <c:choose>
                                                <c:when test="${branchData.statusCode == 'V' || branchData.statusCode == 'I' }">
                                                    <icon:view/>
                                                </c:when>
                                                <c:otherwise>
                                                    <icon:edit/>
                                                </c:otherwise>
                                            </c:choose>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween justify-content-end ">
            <c:url value="/governmentDocuments" var="governmentDocs"/>
            <button type="submit" class="btn" onclick="sendBranches()" ${disabled}><spring:theme code="myprofile.submit"/></button>
 			<input type="hidden" id="serviceId"/>
        </div>
    </div>
</div>


<div class="modal fade" id="branchEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered govt-doc-edit" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="myprofile.edit"/><span id="branchName" class="modal-title"></span></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>

            <div class="modal-body modal-body_bordered">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="general.governmentdocuments.commercialregister"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="formRadioButton">
                                    <div class="form-group">

                                        <div class="form-item">
                                            <input id="aahasCr1" class="form-control gov-docs-hasCr" name="aahasCr"
                                                   type="radio" value="true" onchange="handleCrnRadioChange()" ${disabled}>
                                            <label for="aahasCr1" class="control-label">
                                                <span></span> <spring:theme code="govDocs.hasCrY"/></label>
                                        </div>

                                        <div class="form-item">
                                            <input id="aahasCr2" class="form-control gov-docs-hasCr" name="aahasCr"
                                                   type="radio" value="false" onchange="handleCrnRadioChange()" ${disabled}>
                                            <label for="aahasCr2" class="control-label">
                                                <span></span><spring:theme code="govDocs.hasCrN"/></label>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="formInputBox">
                                        <div class="form-group" id="cr-group">
                                            <input id="govDocs-CRN" class="form-control gov-docs-crn" placeholder="."
                                                   value="" type="text" ${disabled}>
                                            <label class="control-label" for="govDocs-CRN">
                                                <spring:theme code="govDocs.CRN"/>
                                            </label>
                                            <div class="help-block">
                                                <span id="govDocs-CRN-error"></span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="contentModule-section">
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="govDocs.Momra"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="formRadioButton">
                                    <div class="form-group">
                                        <div class="form-item">
                                            <input id="aahasMomra1" class="form-control gov-docs-momra"
                                                   name="aahasMomra" type="radio" value="true"
                                                   onchange="handleMomraRadioChange()" ${disabled}>
                                            <label for="aahasMomra1" class="control-label">
                                            	<span></span><spring:theme code="govDocs.momraY"/></label>
                                        </div>

                                        <div class="form-item">
                                            <input id="aahasMomra2" class="form-control gov-docs-momra"
                                                   name="aahasMomra" type="radio" value="false"
                                                   onchange="handleMomraRadioChange()" ${disabled}>
                                            <label for="aahasMomra2" class="control-label">
                                            	<span></span><spring:theme code="govDocs.momraN"/></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="formInputBox">
                                        <div class="form-group" id="shopLicNo-group">
                                            <input id="govDocs-shopLicNo" class="form-control gov-docs-shopLicNo"
                                                   placeholder="." value="" type="text" ${disabled}>
                                            <label class="control-label" for="govDocs-shopLicNo">
                                                <spring:theme code="govDocs.shopLicNo"/>
                                            </label>
                                            <div class="help-block">
                                                <span id="govDocs-shopLicNo-error"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group" id="amanah-group">
                                        <select id="govDocs-amanah" name=""
                                                class="js-select2 form-control gov-docs-amanah" ${disabled}>
                                            <option></option>
                                            <c:forEach items="${amanahs}" var="amanah">
                                                <option value="${amanah.key}">${amanah.name}</option>
                                            </c:forEach>
                                        </select>
                                        <label class="control-label" for="govDocs-amanah">
                                            <spring:theme code="govDocs.amanah"/>
                                        </label>
                                        <div class="help-block">
                                            <span id="govDocs-amanah-error"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="contentModule-section contentModule-section_slimDivider">
                        <div class="row">
                            <div class="col-md-5">
                                <div class="contentModule contentModule-wrap">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                        <span class="contentModule-headline"><spring:message code="myprofile.wassel"/></span>
                                        <div class="contentModule-headline-border"></div>
                                    </div>
                                </div>                             
                                <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                    <div class="form-group">

                                        <div class="form-item">
                                            <input id="aaWassel0" class="form-control gov-docs-wassel" name="wassel"
                                                   type="radio" value="0" onchange="handleWasselRadioChange()" ${disabled}>
                                            <label for="aaWassel0" class="control-label">
                                                <span></span> <spring:theme code="govDocs.wassel.CrConnected"/></label>
                                        </div>

                                        <div class="form-item">
                                            <input id="aaWassel1" class="form-control gov-docs-wassel" name="wassel"
                                                   type="radio" value="1" onchange="handleWasselRadioChange()" ${disabled}>
                                            <label for="aaWassel1" class="control-label">
                                                <span></span><spring:theme code="govDocs.wassel.noCrConnected"/></label>
                                        </div>
                                        <div class="form-item">
                                            <input id="aaWassel2" class="form-control gov-docs-wassel" name="wassel"
                                                   type="radio" value="2" onchange="handleWasselRadioChange()" ${disabled}>
                                            <label for="aaWassel2" class="control-label">
                                                <span></span><spring:theme code="govDocs.wassel.noAddress"/></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-7" id="mapContainer">
                                <!-- <div class="contentModule-headline">
                                    <span class="iconElement iconElement_locationPin_filled"><icon:locationPin_filled/></span>
                                    Physical address
                                </div> -->
                                <div class="contentModule contentModule-wrap">
                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                        <span class="contentModule-headline"><spring:message code="myprofile.physical.address"/></span>
                                        <div class="contentModule-headline-border"></div>
                                    </div>
                                </div>
                                <div class="mapsModule">
                                    <div id="map" style="height: 350px"></div>
                                </div>
                                <div class="formInputBox">
                                    <div class="form-group" id="gMap-group">
                                        <input id="gMapAddress" class="form-control" placeholder="." value="" type="text" readonly="true" >
                                        <label class="control-label control-label_mandatory" for="gMapAddress">
                                            <spring:message code="myprofile.your.address"/>
                                        </label>
                                        <div class="help-block">
                                            <span id="govDocs-gMap-error"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="modal-footer modal-footer_centered">
                <button type="reset" class="btn btn_outline btn_slim full-width-responsive" data-dismiss="modal">
                    <spring:message code="myprofile.cancel"/>
                </button>
                <button type="button" class="btn full-width-responsive" onclick="saveBranch()" ${disabled}>
                    <spring:theme code="govDocs.saveButton"/>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="branchesUpdated" tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:modal02/>
                </div>
                <div class="modal-description">
                    <spring:theme code="govDocs.updated"/><span id="service-id"></span>
                </div>
            </div>
            <div class="modal-footer">
                <c:url value="/dashboard" var="dashboardUrl"></c:url>
                <a class="btn btn_slim" href="${dashboardUrl}"><spring:theme code="govDocs.okay"/></a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="branchesUpdatedError"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:attention-error/>
                </div>
                <div class="modal-description">
                    <spring:theme code="govDocs.updated.error"/><span id="update-error"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:message code="myprofile.close"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="wassel-check" tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:modal02/>
                </div>
                <div class="modal-description">
                    <c:out value="${wasselCheckMessage}"></c:out>
                </div>
            </div>
            <div class="modal-footer">
                <button id="infoModalClose" type="button" class="btn btn_slim" data-dismiss="modal"><spring:message code="myprofile.close"/></button>
            </div>
        </div>
    </div>
</div>

<script>
    var notNullError = '<spring:theme code="govDocs.error.notEmpty"/>';
    var notNumberError = '<spring:theme code="govDocs.error.notNumber"/>';
    var gMapError = '<spring:theme code="govDocs.error.gmapNotNull"/>';

    var branchesJson = ${branchesJSON};
    var mainBranchJson = ${mainBranchJSON};
    var selectedBranch;
</script>

