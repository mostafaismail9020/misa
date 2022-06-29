<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/responsive/transaction" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>
<%@ taglib prefix="survey" tagdir="/WEB-INF/tags/responsive/survey" %>
<%@ taglib prefix="modals" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>


<%--<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>--%>
<%--<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>--%>
<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"
             src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png"
             alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    MY QUARTERLY FDI SURVEYS
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments"
                           title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                </c:if>
                <div class="calendar notification p-0 sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                    <c:if test="${hasLicense or hasAwaitingPayment}">
                        <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0"
                                title="<spring:message code='account.notifications.yourMessages'/>">
                            <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                            <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg"
                                 class="notification_b2b_img"/>
                        </button>
                    </c:if>
                    <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                    <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
                        <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen">
                            <spring:message code="header.mostRecent.text"/></div>
                        <ul id="popupNotificationHistoryList"
                            class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                        <div class="sagiaNavigation-subPane-actions">
                            <a class="btn btn_slim btn_round btn_outline"
                               href="${encodedContextPath}/my-sagia/notifications"><spring:message
                                    code="header.viewAll.text"/></a>
                        </div>
                    </div>
                </div>
                <!--<div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/>
                            </button>
                        </c:if>
                        <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                    </div>-->

                <div class="profile">
                    <a href="${encodedContextPath}/my-sagia/sagia-profile"
                       title="<spring:theme code='company.myprofile'/>">
                        <span></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- <div class="mainSection mainSection_white mainSection_narrow">
<div class="container">
<div class="mainSection-linkActions mainSection-linkActions_right">
<button id="expandAmendmentHistoryBtnId"
class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent"
data-expand-target="expand01">
<%--<div>
    <spring:theme code="text.account.followup.showServiceHistory" />
    <span>&#x27f6;</span>
</div>
<div class="hidden">
    <spring:theme code="text.account.followup.hideServiceHistory" />
    <span class="iconElement iconElement_closeBack"><icon:close /></span>
</div>--%>
</button>
</div>
</div>
</div> -->

<div
        class="mainSection mainSection_white mainSection_narrow mainSection_noPadding">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">
                <spring:theme code="dashboard.financialsurveys.completesurvey"/>
            </h1>

            <%--<div class="mainSection-linkActions mainSection-linkActions_right">
                <button id="saveDraftBtnId" class="btn btn_round">
                    <spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save"><icon:save/></span>
                </button>
                <button id="loadDraftBtnId"
                        class="btn btn_round btn_slim js-load-draft"
                        <c:if test="${!draftExists}">style="display: none"</c:if>>
                    <spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span>
                </button>
            </div>--%>
            <%--<c:if test="${not empty processingTime}">
                <div class="serviceTime">
                    <div class="serviceTime-label">
                        <spring:theme code="average.service.time" />
                    </div>
                    <div class="serviceTime-detail">
                        <c:choose>
                            <c:when
                                test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                <span class="serviceTime-highlight">${processingTime.days}</span>
                                <spring:theme code="average.processingTime.days" />
                                <span class="serviceTime-highlight">${processingTime.hours}</span>
                                <spring:theme code="average.processingTime.hours" />
                            </c:when>
                            <c:when
                                test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                <spring:theme code="average.processingTime.minutes" />
                                <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                <spring:theme code="average.processingTime.seconds" />
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:if>--%>
        </div>


    </div>
</div>

<div
        class="mainSection mainSection_white mainSection_narrow mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <%--<button id="saveDraftBtnId" class="btn btn_round">
                <spring:theme code="general.savedraft" />
                <span class="iconElement iconElement_save"><icon:save /></span>
            </button>
            <button id="loadDraftBtnId"
                class="btn btn_round btn_slim js-load-draft"
                <c:if test="${!draftExists}">style="display: none"</c:if>>
                <spring:theme code="general.loaddraft" />
                <span class="iconElement iconElement_save"><icon:upload /></span>
            </button>--%>
        </div>
    </div>
</div>


<div
        class="mainSection mainSection_white mainSection_narrow mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="expandableContent expandableContent_upLg" id="expand01">
            <%--<div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div
                            class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                                <span class="iconElement iconElement_history"><icon:history /></span>
                                <spring:theme code="text.account.followup.history" />
                            </div>
                            <div class="searchInputBox searchInputBox_slim">
                                <input onkeyup="filterHistory(this)" id="convertSearchBox"
                                    class="searchInputBox-input" type="text"
                                    placeholder="<spring:theme code='storeFinder.search'/>" />
                            </div>
                            <ul id="history-list" class="historyList"></ul>
                        </div>
                    </div>
                </div>
            </div>--%>
            <div class="expandableContent-main">
                <div
                        class="js-panelTabs panelTabs panelTabs_iconsAndLabel panelTabs_separated panelTabs_tip_none  panelTabs_noPanelInBody panelTabs_white panelTabs_whiteNavigation panelTabs_noPaddingTop">
                    <div class="panelTabs-head" id="tab1">
                        <icon:registerinTab/>
                        <span class="panelTabs-label"><spring:theme code="survey.companyProfile"/></span>
                    </div>
                    <div class="panelTabs-body">
                        <div id="entityAmendTabId" class="contentModule">

                            <div class="contentModule-section">
                                <form:form id="entityFormId">
                                    <div class="contentModule-section">
                                        <div class="row">

                                            <div class="col-sm-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="companyNameId" name="companyName"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label control-label_mandatory"
                                                                                      for="companyNameId"> <spring:theme
                                                            code="survey.companyName"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="commercialRegistrationNoID"
                                                               name="commercialRegistrationNo"
                                                               class="form-control" placeholder="." value="" type="text"
                                                            <%--style="text-align: right;"--%>
                                                               dir="ltr"
                                                               maxlength="80"> <label
                                                            class="control-label control-label_mandatory"
                                                            for="commercialRegistrationNoID"> <spring:theme
                                                            code="survey.commercialRegistrationNo"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="unifiedNo700ID" name="unifiedNo700"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="unifiedNo700ID">
                                                        <spring:theme
                                                                code="survey.unifiedNo"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="formSelectBox">
                                                    <div class="form-group">
                                                        <select id="legalStatusId" name="legalStatus"
                                                                class="js-select2-oneColumn form-control">
                                                            <option></option>
                                                        </select> <label class="control-label control-label_mandatory"
                                                                         for="legalStatusId"><spring:theme
                                                            code="license.legalstatus"/></label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div id="incorporationDateSection" class="col-md-6">
                                                <div class="formInputBox formInputBox_group ">
                                                    <div class="form-group">
                                                        <input id="incorporationDate"
                                                               name="incorporationDate"
                                                               class="form-control " placeholder="." value=""
                                                               type="text"/>
                                                        <label class="control-label control-label_mandatory"
                                                               for="incorporationDate"><spring:theme
                                                                code="financial.survey.incorporationDate"/></label>
                                                        <div class="formInputBox-append">
                                                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                        </div>
                                                    </div>
                                                    <div class="help-block"></div>
                                                </div>
                                            </div>

                                            <div id="crIssueDateSection" class="col-md-6">
                                                <div class="formInputBox formInputBox_group ">
                                                    <div class="form-group">
                                                        <input id="crIssueDate"
                                                               name="crIssueDate"
                                                               class="form-control " placeholder="." value=""
                                                               type="text"/>
                                                        <label class="control-label control-label_mandatory"
                                                               for="crIssueDate"><spring:theme
                                                                code="services.type.zmoci.crissuesdate"/></label>
                                                        <div class="formInputBox-append">
                                                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                        </div>
                                                    </div>
                                                    <div class="help-block"></div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="formSelectBox">
                                                    <div class="form-group">
                                                        <select id="companyStatusId" name="companyStatus"
                                                                class="js-select2-oneColumn form-control">
                                                            <option></option>
                                                            <option value="ACTIVE" selected><spring:theme
                                                                    code="survey.status.active"/></option>
                                                            <option value="TEMPORARILY_SUSPENDED"><spring:theme
                                                                    code="survey.status.suspended"/></option>
                                                            <option value="DISSOLVED"><spring:theme
                                                                    code="survey.status.dissolved"/></option>
                                                        </select> <label class="control-label control-label_mandatory"
                                                                         for="legalStatusId"><spring:theme
                                                            code="survey.companyStatus"/></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="suspensionDateSection" class="col-md-6">
                                                <div class="formInputBox formInputBox_group ">
                                                    <div class="form-group">
                                                        <input id="suspensionDateId"
                                                               name="suspensionDate"
                                                               class="form-control " placeholder="." value=""
                                                               type="text"/>
                                                        <label class="control-label control-label_mandatory"
                                                               for="suspensionDateId"><spring:theme
                                                                code="survey.suspensionDate"/></label>
                                                        <div class="formInputBox-append">
                                                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                        </div>
                                                    </div>
                                                    <div class="help-block"></div>
                                                </div>
                                            </div>




                                            <div class="col-md-12">
                                                <div class="contentModule-separator"></div>
                                            </div>

                                                <%--<div class="col-sm-6 contentModule-headline_smallMargin">
                                                    <spring:theme code="financial.survey.scale.level"/>
                                                </div>--%>

                                            <div class="col-sm-6">
                                                <div class="formRadioBox ml-4 pl-2">
                                                    <div class="form-group">
                                                        <div class="formRadioBox-label"><spring:theme
                                                                code="financial.survey.scale.level"/></div>
                                                        <div class="form-item pt-2 pb-5">
                                                            <input id="actualUnitId"
                                                                   name="sacaleLevelRadioBox" class="form-control"
                                                                   value="true" type="radio" checked> <label
                                                                for="actualUnitId" class="control-label"> <spring:theme
                                                                code="financial.survey.actualUnit"/>
                                                        </label>
                                                        </div>
                                                        <div class="form-item pt-2 pb-5">
                                                            <input id="thousandsId"
                                                                   name="sacaleLevelRadioBox" class="form-control"
                                                                   value="false" type="radio"> <label
                                                                for="thousandsId" class="control-label"> <spring:theme
                                                                code="financial.survey.thousands"/>
                                                        </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-sm-6">
                                                <div class="formRadioBox ml-4 pl-2" id="myRadio">

                                                    <div class="form-group">
                                                        <div class="formRadioBox-label"><spring:theme
                                                                code="financial.survey.data.type"/></div>
                                                        <div class="form-item pt-2 pb-5">
                                                            <input id="standloneId"
                                                                   name="consolidatedStandloneRadioBox"
                                                                   class="form-control"
                                                                   value="true" type="radio" checked> <label
                                                                for="standloneId" class="control-label"> <spring:theme
                                                                code="financial.survey.standlone"/>
                                                        </label>
                                                        </div>
                                                        <div class="form-item pt-2 pb-5">
                                                            <input id="consolidatedId"
                                                                   name="consolidatedStandloneRadioBox"
                                                                   class="form-control"
                                                                   value="false" type="radio"> <label
                                                                for="consolidatedId" class="control-label">
                                                            <spring:theme
                                                                    code="financial.survey.consolidated"/>
                                                        </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-md-6">
                                                <div class="formSelectBox">
                                                    <div class="form-group">
                                                        <select id="disclosureCurrencyId" name="disclosureCurrency"
                                                                class="js-select2-oneColumn form-control">
                                                            <option></option>
                                                            <option value="SAR" selected><spring:theme
                                                                    code="survey.currency.sar"/></option>
                                                            <option value="USD"><spring:theme
                                                                    code="survey.currency.usd"/></option>
                                                            <option value="EURO"><spring:theme
                                                                    code="survey.currency.euro"/></option>
                                                        </select> <label class="control-label control-label_mandatory"
                                                                         for="legalStatusId"><spring:theme
                                                            code="survey.disclosureCurrency"/></label>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-sm-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="comppanyPaidUpCapitalCurrentQuarterId"
                                                               name="paidUpCapitalCurrentQuarter"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label
                                                            class="control-label control-label_mandatory"
                                                            for="comppanyPaidUpCapitalCurrentQuarterId"> <spring:theme
                                                            code="survey.comppanyPaidUpCapital"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>




                                            <div class="col-md-12">
                                                <div class="contentModule-separator"></div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="financeManagerNameId" name="financeManagerName"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label control-label_mandatory"
                                                                                      for="financeManagerNameId">
                                                        <spring:theme code="survey.financeManagerName"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="financeManagerEmailId" name="financeManagerEmail"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label
                                                            class="control-label control-label_mandatory"
                                                            for="financeManagerEmailId">
                                                        <spring:theme code="survey.financeManagerEmail"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="financeManagerTelephoneId"
                                                               name="financeManagerTelephone"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label
                                                            class="control-label control-label_mandatory"
                                                            for="financeManagerTelephoneId">
                                                        <spring:theme code="survey.financeManagerTelephone"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>


                                    <div class="contentModule-section" id="businessActivitiesSection">
                                        <div class="contentModule-headline"><spring:theme
                                                code="financial.survey.business.activities"/></div>
                                        <hr class="hr w-100">

                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="formSelectBox">
                                                    <div class="form-group">
                                                        <select id="sectionId" name="section"
                                                                class="js-select2-search form-control"></select> <label
                                                            class="control-label control-label_mandatory"
                                                            for="sectionId"><spring:theme
                                                            code="survey.section"/></label>
                                                    </div>
                                                    <div class="help-block"></div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="formSelectBox">
                                                    <div class="form-group">
                                                        <select id="divisionId" name="division"
                                                                class="js-select2-search form-control"></select> <label
                                                            class="control-label control-label_mandatory"
                                                            for="divisionId"><spring:theme
                                                            code="survey.division"/></label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-md-6">
                                                <div class="formSelectBox">
                                                    <div class="form-group">
                                                        <select id="groupId" name="group"
                                                                class="js-select2-search form-control"></select> <label
                                                            class="control-label control-label_mandatory"
                                                            for="groupId"><spring:theme code="survey.group"/></label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-md-6">
                                                <div class="formSelectBox">
                                                    <div class="form-group">
                                                        <select id="classId" name="class"
                                                                class="js-select2-search form-control"></select> <label
                                                            class="control-label control-label_mandatory"
                                                            for="classId"><spring:theme code="survey.class"/></label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                    <%--<survey:surveyBusinessActivitiesSection/>--%>

                                </form:form>

                            </div>
                            <div
                                    class="contentModule-actions contentModule-actions_spaceBetween">
                                <button type="button" id="cancelTabEntityBtnId"
                                        class="btn btn-secondary cancelAmendmentBtn newAmendmentBtn">
                                    <spring:theme code="general.cancel"/>
                                </button>
                                <button id="nextTabEntityBtnId" type="button"
                                        class="btn btn-primary newAmendmentBtn">
                                    <spring:theme code="survey.proceed"/>
                                </button>
                            </div>

                        </div>

                    </div>


                    <div class="panelTabs-head" id="tab2">
                        <icon:branch/>
                        <span class="panelTabs-label"><spring:theme
                                code="general.branchandsubsidiary"/></span>
                    </div>
                    <div class="panelTabs-body">
                        <div class="contentModule">


                            <div class="contentModule-section">
                                <div class="tableModule">
                                    <table>
                                        <tr class="branchTemplate">
                                            <td><strong></strong></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="tableModule-bodyItem-action">
                                                <button type="button" class="btn btn_link editBranchBtn"
                                                        data-toggle="modal" data-target="#branchModalId"
                                                        data-backdrop="static" data-keyboard="false">
                                                    <icon:edit/>
                                                </button>
                                                <button type="button" class="btn btn_link viewBranchBtn"
                                                        data-toggle="modal" data-target="#branchModalId"
                                                        data-backdrop="static" data-keyboard="false">
                                                    <icon:view/>
                                                </button>
                                                <div class="deleteDropdown js-deleteDropdown">
                                                    <button type="button"
                                                            class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
                                                        <icon:remove/>
                                                    </button>
                                                    <div class="deleteDropdown-drop">
                                                        <div class="deleteDropdown-text">
                                                            <spring:theme
                                                                    code="text.account.profile.license.branches.deletethebranch"/>
                                                        </div>
                                                        <div class="deleteDropdown-actions">
                                                            <button type="button"
                                                                    class="btn btn_outline btn_slim js-deleteDropdown-cancel">
                                                                <spring:theme code="general.cancel"/>
                                                            </button>
                                                            <button type="button"
                                                                    class="btn btn_slim removeBranchBtn">
                                                                <spring:theme code="general.delete"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <table id="branchesTableId" class="tableModule-table">
                                        <thead class="tableModule-head">
                                        <tr>
                                            <th><spring:theme
                                                    code="text.account.profile.license.branches.type"/> <span
                                                    class="sort-icon"></span></th>
                                            <th><spring:theme
                                                    code="text.account.profile.license.branches.name"/> <span
                                                    class="sort-icon"></span></th>
                                            <th><spring:theme code="general.city"/> <span
                                                    class="sort-icon"></span></th>
                                            <th><spring:theme
                                                    code="financial.survey.branch.weightPercentage"/> <span
                                                    class="sort-icon"></span></th>
                                            <th id="branchesBtnColumnId"></th>
                                        </tr>
                                        </thead>
                                        <tbody id="branchesId" class="tableModule-body"></tbody>
                                    </table>
                                </div>
                                <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                    <button type="button" class="btn btn-primary newBranchBtn"
                                            data-toggle="modal" data-target="#branchModalId"
                                            data-backdrop="static" data-keyboard="false">
                                        <spring:theme
                                                code="text.account.profile.license.branches.newbranch"/>
                                    </button>
                                </div>
                            </div>


                            <div id="subsidiarySection" class="contentModule-section">

                                <div class="tableModule">
                                    <table>
                                        <tr class="subsidiaryTemplate">
                                            <td><strong></strong></td>
                                            <td></td>
                                            <td></td>
                                            <td class="tableModule-bodyItem-action">
                                                <button type="button" class="btn btn_link editSubsidiaryBtn"
                                                        data-toggle="modal" data-target="#subsidiaryModalId"
                                                        data-backdrop="static" data-keyboard="false">
                                                    <icon:edit/>
                                                </button>
                                                <div class="deleteDropdown js-deleteDropdown">
                                                    <button type="button"
                                                            class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
                                                        <icon:remove/>
                                                    </button>
                                                    <div class="deleteDropdown-drop">
                                                        <div class="deleteDropdown-text">
                                                            <spring:theme
                                                                    code="financial.survey.subsidiaries.deletethesubsidiary"/>
                                                        </div>
                                                        <div class="deleteDropdown-actions">
                                                            <button type="button"
                                                                    class="btn btn_outline btn_slim js-deleteDropdown-cancel">
                                                                <spring:theme code="general.cancel"/>
                                                            </button>
                                                            <button type="button"
                                                                    class="btn btn_slim removeSubsidiaryBtn">
                                                                <spring:theme code="general.delete"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <table id="subsidiariesTableId" class="tableModule-table">
                                        <thead class="tableModule-head">
                                        <tr>
                                            <th><spring:theme
                                                    code="financial.survey.subsidiaries.residentSubsidiaryName"/> <span
                                                    class="sort-icon"></span></th>
                                            <th><spring:theme
                                                    code="financial.survey.subsidiaries.commercialRegistration"/> <span
                                                    class="sort-icon"></span></th>
                                            <th><spring:theme
                                                    code="financial.survey.subsidiaryCompanyIncluded"/> <span
                                                    class="sort-icon"></span></th>
                                            <th id="subsidiariesBtnColumnId"></th>
                                        </tr>
                                        </thead>
                                        <tbody id="subsidiariesId" class="tableModule-body"></tbody>
                                    </table>
                                </div>

                                <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                    <button type="button" class="btn btn-primary newSubsidiaryBtn"
                                            data-toggle="modal" data-target="#subsidiaryModalId"
                                            data-backdrop="static" data-keyboard="false">
                                        <spring:theme
                                                code="financial.survey.subsidiaries.newsubsidiary"/>
                                    </button>
                                </div>

                            </div>


                            <div
                                    class="contentModule-actions contentModule-actions_spaceBetween">
                                <button type="button" id="cancelTabSubsidiariesBtnId"
                                        class="btn btn-secondary  newAmendmentBtn">
                                    <spring:theme code="general.cancel"/>
                                </button>
                                <button id="nextTabSubsidiariesBtnId" type="button"
                                        class="btn newAmendmentBtn">
                                    <spring:theme code="survey.proceed"/>
                                </button>
                            </div>


                        </div>
                    </div>


                    <div class="panelTabs-head" id="tab3">
                        <icon:productinTab/>
                        <span class="panelTabs-label"><spring:theme
                                code="general.shareholderequity"/></span>
                    </div>
                    <div class="panelTabs-body">
                        <div class="contentModule">
                            <div class="contentModule-section">

                                <form:form id="equityFormId">

                                    <div class="tableModule tableModule_slim  overflow-y-hidden">
                                        <table class="tableModule-table">
                                            <thead class="tableModule-head">
                                            <tr>
                                                <th></th>
                                                <th style="text-align: center"><spring:theme code="financial.survey.transaction.assets.currentQuarter"/></th>
                                                <th style="text-align: center"><spring:theme code="financial.survey.transaction.assets.previousQuarter"/></th>
                                            </tr>
                                            </thead>
                                            <tbody class="tableModule-body" id="shareEquityable">
                                            <tr>
                                                <td class="lastUpdate">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.paidUpCapital.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="paidUpCapitalCurrentQuarterId"
                                                           name="paidUpCapitalCurrentQuarter"
                                                           onchange="caluculateTotalCapital()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="paidUpCapitalPreviousQuarterId"
                                                           name="paidUpCapitalPreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>

                                                </td>

                                            </tr>



                                            <tr>
                                                <td class="lastUpdate">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.additionalPaidUpCapital.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="additionalPaidUpCapitalCurrentQuarterId"
                                                           name="additionalPaidUpCapitalCurrentQuarter"
                                                           onchange="caluculateTotalCapital()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="additionalPaidUpCapitalPreviousQuarterId"
                                                           name="additionalPaidUpCapitalPreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lastUpdate">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.retainedEarningsInclude.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="retainedEarningsIncludeCurrentQuarterId"
                                                           name="retainedEarningsIncludeCurrentQuarter"
                                                           onchange="caluculateTotalCapital()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="retainedEarningsIncludePreviousQuarterId"
                                                           name="retainedEarningsIncludePreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>

                                                </td>
                                            </tr>



                                            <tr>
                                                <td class="lastUpdate"  style="padding-right: 5px">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.profitLossQuarter.currentQuarter"/>
                                                </td>
                                                <td>

                                                           <div class="formInputBox">
                                                               <div class="form-group">
                                                                   <input id="profitLossQuarterCurrentQuarterId"
                                                                          name="profitLossQuarterCurrentQuarter"
                                                                          class="form-control" placeholder="." value="" type="text"
                                                                          dir="ltr"
                                                                          maxlength="80">
                                                                   <div class="help-block"></div>
                                                               </div>
                                                           </div>

                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-item">
                                                    <input id="profitLossQuarterPreviousQuarterId"
                                                           name="profitLossQuarterPreviousQuarter"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                             <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td class="lastUpdate"  style="padding-right: 5px">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.totalReserves.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-item">
                                                         <input id="totalReservesCurrentQuarterId"
                                                           name="totalReservesCurrentQuarter"
                                                           onchange="caluculateTotalCapital()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                             <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-item">
                                                    <input id="totalReservesPreviousQuarterId"
                                                           name="totalReservesPreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                            <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>



                                            <tr>
                                                <td class="lastUpdate"  style="padding-right: 5px">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.treasuryShares.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="treasurySharesCurrentQuarterId"
                                                           name="treasurySharesCurrentQuarter"
                                                           onchange="caluculateTotalCapital()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="treasurySharesPreviousQuarterId"
                                                           name="treasurySharesPreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>




                                            <tr>
                                                <td class="lastUpdate"  style="padding-right: 5px">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.headOfficeAccountInBranch.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="headOfficeAccountInBranchCurrentQuarterId"
                                                           name="headOfficeAccountInBranchCurrentQuarter"
                                                           onchange="caluculateTotalCapital()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="headOfficeAccountInBranchPreviousQuarterId"
                                                           name="headOfficeAccountInBranchPreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>




                                            <tr>
                                                <td class="lastUpdate"  style="padding-right: 5px">
                                                    <spring:theme
                                                            code="financial.survey.shareholderEquity.others.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="shareholderEquityOthersCurrentQuarterId"
                                                           onchange="caluculateTotalCapital()"
                                                           name="shareholderEquityOthersCurrentQuarter"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="shareholderEquityOthersPreviousQuarterId"
                                                           name="shareholderEquityOthersPreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>



                                            <tr>
                                                <td class="lastUpdate"  style="padding-right: 5px">
                                                    <spring:theme
                                                            code="financial.survey.minorityRights.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="minorityRightsCurrentQuarterId"
                                                           name="minorityRightsCurrentQuarter"
                                                           onchange="caluculateTotalCapital()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="minorityRightsPreviousQuarterId"
                                                           name="minorityRightsPreviousQuarter"
                                                           onchange="caluculateTotalCapitalPreviousQuarter()"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td class="lastUpdate"  style="padding-right: 5px">
                                                    <spring:theme
                                                            code="financial.survey.totalShareholderEquity.currentQuarter"/>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="totalShareholderEquityCurrentQuarterId"
                                                           name="totalShareholderEquityCurrentQuarter"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr" disabled="true"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="formInputBox">
                                                        <div class="form-group">
                                                    <input id="totalShareholderEquityPreviousQuarterId"
                                                           name="totalShareholderEquityPreviousQuarter"
                                                           class="form-control" placeholder="." value="" type="text"
                                                           dir="ltr" disabled="true"
                                                           maxlength="80">
                                                    <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>


                                            </tbody>



                                        </table>
                                    </div>


                                <div class="row">


                                        <div class="col-md-12">
                                            <div class="contentModule-separator"></div>
                                        </div>

                                      </div>
                              </form:form>

                            </div>


                            <div
                                    class="contentModule-actions contentModule-actions_spaceBetween">
                                <button type="button" id="cancelTabShareholdersEquityBtnId"
                                        class="btn btn-secondary  newAmendmentBtn">
                                    <spring:theme code="general.cancel"/>
                                </button>


                                <button id="nextTabShareholdersEquityBtnId" type="button"
                                        class="btn newAmendmentBtn">
                                    <spring:theme code="survey.proceed"/>
                                </button>
                            </div>
                        </div>

                    </div>


                    <div class="panelTabs-head" id="tab4">
                        <icon:shareholder/>
                        <span class="panelTabs-label"><spring:theme
                                code="financial.survey.shareholdersAndFellows"/></span>
                    </div>
                    <div class="panelTabs-body">
                        <div class="contentModule">

                            <div class="contentModule-section">
                                <div class="tableModule">
                                    <table class="tableModule-table">
                                        <thead class="tableModule-head">
                                        <tr>
                                            <th><spring:theme code="general.name"/></th>
                                            <th><spring:theme code="license.type"/></th>
                                            <th><spring:theme code="license.percentage"/></th>
                                            <th><spring:theme
                                                    code="financial.survey.shareholders.shareValue"/></th>
                                            <th><spring:theme
                                                    code="financial.survey.shareholders.votingPower"/></th>
                                            <th id="shareholderBtnColumnId"></th>
                                        </tr>
                                        </thead>
                                        <tbody id="shareholdersId" class="tableModule-body">
                                        <tr class="shareholderTemplate">
                                            <td></td>
                                            <td class="type"></td>
                                            <td class="percentage"></td>
                                            <td></td>
                                            <td></td>
                                            <td class="tableModule-bodyItem-action">
                                                <button type="button"
                                                        class="btn btn_link editShareholderBtn"
                                                        data-toggle="modal"
                                                <%--                                                        data-target="#shareholderEditModalId" data-backdrop="static"--%>
                                                        data-target="#shareholderModalId"
                                                        data-backdrop="static" data-keyboard="false">
                                                    <icon:edit/>
                                                </button>
                                                <div class="deleteDropdown js-deleteDropdown">
                                                    <button type="button"
                                                            class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
                                                        <icon:remove/>
                                                    </button>
                                                    <div class="deleteDropdown-drop">
                                                        <div class="deleteDropdown-text">
                                                            <spring:theme
                                                                    code="text.account.profile.license.shareholders.deleteShareholder"/>
                                                        </div>
                                                        <div class="deleteDropdown-actions">
                                                            <button type="button"
                                                                    class="btn btn_outline btn_slim js-deleteDropdown-cancel">
                                                                <spring:theme code="general.cancel"/>
                                                            </button>
                                                            <button type="button"
                                                                    class="btn btn_slim removeShareholderBtn">
                                                                <spring:theme code="general.delete"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div
                                        class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                    <button type="button" class="btn btn-primary newShareholderBtn"
                                            data-toggle="modal" data-target="#shareholderModalId"
                                            data-backdrop="static" data-keyboard="false">
                                        <spring:theme
                                                code="financial.survey.shareholders.newShareholder"/>
                                    </button>
                                </div>
                            </div>


                            <div class="contentModule-section">
                                <div class="tableModule">
                                    <table class="tableModule-table">
                                        <thead class="tableModule-head">
                                        <tr>
                                            <th><spring:theme code="general.name"/></th>
                                            <th><spring:theme code="license.type"/></th>
                                            <th><spring:theme
                                                    code="text.account.profile.license.shareholders.nationality"/></th>
                                            <th><spring:theme
                                                    code="text.account.profile.license.shareholders.legalStatus"/></th>
                                            <th id="affiliateBtnColumnId"></th>
                                        </tr>
                                        </thead>
                                        <tbody id="affiliatesId" class="tableModule-body">
                                        <tr class="affiliateTemplate">
                                            <td></td>
                                            <td class="type"></td>
                                            <td class="percentage"></td>
                                            <td></td>
                                            <td></td>
                                            <td class="tableModule-bodyItem-action">
                                                <button type="button"
                                                        class="btn btn_link editAffiliateBtn"
                                                        data-toggle="modal"
                                                        data-target="#affiliateModalId"
                                                        data-backdrop="static" data-keyboard="false">
                                                    <icon:edit/>
                                                </button>
                                                <div class="deleteDropdown js-deleteDropdown">
                                                    <button type="button"
                                                            class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
                                                        <icon:remove/>
                                                    </button>
                                                    <div class="deleteDropdown-drop">
                                                        <div class="deleteDropdown-text">
                                                            <spring:theme
                                                                    code="text.account.profile.license.affiliate.deleteAffiliate"/>
                                                        </div>
                                                        <div class="deleteDropdown-actions">
                                                            <button type="button"
                                                                    class="btn btn_outline btn_slim js-deleteDropdown-cancel">
                                                                <spring:theme code="general.cancel"/>
                                                            </button>
                                                            <button type="button"
                                                                    class="btn btn_slim removeAffiliateBtn">
                                                                <spring:theme code="general.delete"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div
                                        class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                                    <button type="button" class="btn btn-primary newAffiliateBtn"
                                            data-toggle="modal" data-target="#affiliateModalId"
                                            data-backdrop="static" data-keyboard="false">
                                        <spring:theme
                                                code="text.account.profile.license.affiliates.newAffiliate"/>
                                    </button>
                                </div>
                            </div>


                            <div
                                    class="contentModule-actions contentModule-actions_spaceBetween">
                                <button type="button" id="cancelTabShareholdersBtnId"
                                        class="btn btn-secondary  newAmendmentBtn">
                                    <spring:theme code="general.cancel"/>
                                </button>
                                <button id="nextTabShareholdersBtnId" type="button"
                                        class="btn btn-primary newAmendmentBtn">
                                    <spring:theme code="survey.proceed"/>
                                </button>
                            </div>
                        </div>
                    </div>


                    <div class="panelTabs-head" style="pointer-events: none;cursor: default;" id="tab5">
                        <icon:documentsUpload/>
                        <span class="panelTabs-label"><spring:theme code="license.attachments"/></span>
                    </div>
                    <div class="panelTabs-body">




                        <div id="attachmentSection" class="contentModule-section">

                            <form:form id="attachementForm"
                                       action="${encodedContextPath}/my-sagia/financial-survey/complete/saveAttachment"
                                       enctype="multipart/form-data" method="post"
                                       modelAttribute="financialStatementForm" class="js-financialStatement-create">
                                <div class="panelModule panelModule_halfRadius">
                                    <div class="contentModule">
                                        <div class="contentModule-section">
                                            <div class="contentModule-headline">
                                                <icon:documents/><spring:theme code="general.attachments"/>
                                            </div>
                                            <hr class="hr w-100">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="formInputFile">
                                                        <div class="form-group">
                                                            <input id="srId" data-id="0" name="srId" type="text"
                                                                   value="${quarterCode}" hidden="hidden"/>
                                                            <input id="file0" data-id="0" name="files[0]"
                                                                   class="form-control js-inputFile" type="file"
                                                                   accept="application/pdf" value=""/>
                                                            <input id="text05" name="text05"
                                                                   class="form-control control-label_mandatory"
                                                                   type="text" value="" placeholder="" readonly=""
                                                                   tabindex="-1"/>
                                                            <label class="control-label" for=""><spring:theme
                                                                    code="header.financialStatement.text"/></label>
                                                            <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                            <div class="form-icon form-icon_reset js-inputFile-reset"
                                                                 id="file0_reset"><icon:cross/></div>
                                                            <div class="help-block"></div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="form-condition-spl-notes"><spring:theme
                                                    code="sagia.upload.file.size.note"
                                                    arguments="${maxUploadSize}"/></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="contentModule-headline_smallMargin">
                                            <spring:theme
                                                    code="text.survey.voluntary.questions"/>
                                        </div>
                                    </div>


                                    <div class="col-md-12">
                                        <div class="form-condition-spl-notes">
                                            <spring:theme
                                                    code="text.how.long.survey.has.taken"/>
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <formElement:formInputBox idKey="text.hours.to.complete.survey"
                                                                  labelKey="text.hours.to.complete.survey"
                                                                  path="hoursToCompleteSurvey" inputCSS="number"
                                                                  maxlength="3"/>
                                    </div>
                                    <div class="col-md-3">
                                        <formElement:formInputBox idKey="text.minutes.to.complete.survey"
                                                                  labelKey="text.minutes.to.complete.survey"
                                                                  path="minutesToCompleteSurvey" inputCSS="number"
                                                                  maxlength="3"/>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-condition-spl-notes">
                                            <spring:theme
                                                    code="text.survey.knowledge.source"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <formElement:formInputBox idKey="text.survey.source"
                                                                  labelKey="text.survey.source" path="sourceOfKnowledge"
                                                                  inputCSS="text"/>
                                    </div>


                                </div>
                                <div class="formCheckBox formCheckBox_belowPanel">
                                    <formElement:termsAndConditionsCheckbox event="FINANCIAL_STATEMENT"
                                                                            id="termsAndConditions"
                                                                            path="termsAndConditionsChecked"
                                                                            containerCssClass="terms-and-condition"/>
                                </div>


                                <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
                                    <button type="reset" class="btn btn-secondary" id="cancelSubmit">
                                        <spring:theme code="general.cancel"/>
                                    </button>
                                    <button type="submit" value="Submit request"
                                            class="btn js-submit-financialStatement" disabled><spring:theme
                                            code="general.submit"/></button>
                                </div>
                                <csrf disabled="false"/>
                            </form:form>
                        </div>



                    </div>


                    <div class="contentModule-commentsSection" id="ammendComments"
                         style="display: none;">
                        <div class="contentModule-headline contentModule-headline_small ">
                            <spring:theme code="text.account.followup.comments"/>
                        </div>
                        <div class="commentModule">
                            <div class="commentModule-window">
                                <ul id="messagesListUL" class="messageList">
                                    <li class="messageList-item">
                                        <div class="messageList-img">
										<span class="iconElement iconElement_expertProfile_white">
											<icon:expertProfile/>
										</span>
                                        </div>
                                        <div class="messageList-content">
                                            <div class="messageList-message">
                                                <p id="commentMessage"></p>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="shareholderEditModalId" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content panelModule panelModule_halfRadius">
                <div class="contentModule-headline">
                    <spring:theme code="financial.survey.shareholder"/>
                </div>


                <form class="editShareholder">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input type="text" id="shareholderPercentage"
                                           name="shareholderPercentage" class="form-control"> <label
                                        for="shareholderPercentage"
                                        class="control-label control-label_mandatory"><spring:theme
                                        code="license.apply.review.shares.percentage"/></label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                        <button type="button"
                                class="btn btn-slim btn_outline cancelShareholderBtn"
                                data-dismiss="modal">
                            <spring:theme code="general.cancel"/>
                        </button>
                        <button type="button" class="btn btn-slim saveEditShareholderBtn">
                            <spring:theme code="general.save"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div class="modal fade" id="shareholderModalId" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form id="shareholderFormId">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content panelModule panelModule_halfRadius">
                    <div class="contentModule-headline">
                        <spring:theme code="financial.survey.shareholder"/>
                    </div>
                    <hr class="hr w-100">
                    <div id="shareholderPersonEntityTypeId" class="row">
                        <div class="col-md-8">
                            <div class="formRadioBox">
                                <div class="form-group">
                                    <div class="form-item">
                                        <input id="personId" name="shareholderPersonEntityRadioBox"
                                               class="form-control" value="true" type="radio" checked>
                                        <label for="personId" class="control-label"> <spring:theme
                                                code="financial.survey.person"/>
                                        </label>
                                    </div>
                                    <div class="form-item">
                                        <input id="entityId" name="shareholderPersonEntityRadioBox"
                                               class="form-control" value="false" type="radio"> <label
                                            for="entityId" class="control-label"> <spring:theme
                                            code="financial.survey.entity"/>
                                    </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="contentModule-separator"></div>
                    <div id="contentNewShareholderForm">
                        <div class="contentModule-headline">
                            <spring:theme code="license.basicinformation"/>
                        </div>
                        <hr class="hr w-100">
                        <%--Entity shareholder--%>
                        <div id="entityShareholderId">

                            <div id="entityBasicInformation" class="row">
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="shareholderNameEnglishId"
                                                   name="shareholderNameEnglish" class="form-control"
                                                   placeholder="." value="" type="text"> <label
                                                class="control-label control-label_mandatory"
                                                for="shareholderNameEnglishId"> <spring:theme
                                                code="financial.survey.nameEntity"/>
                                        </label>
                                            <div class="help-block"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="companyCountry" name="companyCountry"
                                                    class="js-select2-oneColumn form-control"></select> <label
                                                class="control-label control-label_mandatory"
                                                for="companyCountry"><spring:theme
                                                code="general.country"/></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="shareholderSectorId" name="shareholderSector"
                                                    class="js-select2-oneColumn form-control"></select> <label
                                                class="control-label"
                                                for="shareholderSectorId"><spring:theme
                                                code="survey.sector"/></label>
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="nationalityOfUCPId" name="nationalityOfUCP"
                                                    class="js-select2-oneColumn form-control"></select> <label
                                                class="control-label control-label_mandatory"
                                                for="nationalityOfUCPId"><spring:theme
                                                code="survey.ucp.country"/></label>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <%--Individual shareholder--%>
                        <div id="individualShareholderId" class="row">
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="individualShareholderNameEnglishId"
                                               name="shareholderNameEnglish" class="form-control"
                                               placeholder="." value="" type="text"> <label
                                            class="control-label control-label_mandatory"
                                            for="individualShareholderNameEnglishId"> <spring:theme
                                            code="financial.survey.nameperson"/>
                                    </label>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="shareholderGenderId" name="shareholderGender"
                                                class="js-select2-oneColumn form-control"></select> <label
                                            class="control-label control-label_mandatory"
                                            for="shareholderGenderId"><spring:theme
                                            code="license.gender"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="shareholderNationalityCurrentId"
                                                name="shareholderNationalityCurrent"
                                                class="js-select2-search form-control"></select> <label
                                            class="control-label control-label_mandatory"
                                            for="shareholderNationalityCurrentId"><spring:theme
                                            code="license.currentnationality"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="shareholderCountryId" name="shareholderCountry"
                                                class="js-select2-search form-control"></select> <label
                                            class="control-label control-label_mandatory"
                                            for="shareholderCountryId"><spring:theme
                                            code="financial.survey.shareholders.residenceCountry"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>

                        </div>


                        <div class="contentModule-separator"></div>

                        <div class="contentModule-headline">
                            <spring:theme code="financial.survey.shareinformation"/>
                        </div>
                        <hr class="hr w-100">

                        <div id="shareholderAddressId" class="row">

                            <div class="col-sm-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="shareholderPercentageId" onchange="calculateShareholderEquity()"
                                               name="shareholderPercentage" class="form-control"
                                               placeholder="." value="" type="text"> <label
                                            class="control-label control-label_mandatory"
                                            for="shareholderPercentageId"> <spring:theme
                                            code="license.sharespercentage"/>
                                    </label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="shareholderCapitalId" name="shareholderCapital"
                                               class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label control-label_mandatory" for="shareholderCapitalId">
                                            <spring:theme code="license.capital"/>
                                        </label>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                            </div>


                            <div class="col-md-12">
                                <div class="formCheckBox">
                                    <div class="form-group">
                                        <div class="form-item">
                                            <input id="shareholderIsVotingPowerId"
                                                   name="shareholderIsVotingPower" class="form-control"
                                                   placeholder="." type="checkbox" value=""> <label
                                                class="control-label" for="shareholderIsVotingPowerId">
												<span> <icon:check/>
											</span> <spring:theme code="financial.survey.isVotingPower"/>
                                        </label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="shareholderVotingPowerSectionId" class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="shareholderVotingPowerId" name="shareholderVotingPower"
                                               class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label control-label_mandatory"
                                               for="shareholderVotingPowerId">
                                            <spring:theme code="financial.survey.shareholderVotingPower"/>
                                        </label>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                            </div>


                            <div class="col-md-12">
                                <div class="formCheckBox">
                                    <div class="form-group">
                                        <div class="form-item">
                                            <input id="shareholderHasPreferredSharesId"
                                                   name="shareholderHasPreferredShares" class="form-control"
                                                   placeholder="." type="checkbox" value=""> <label
                                                class="control-label" for="shareholderHasPreferredSharesId">
												<span> <icon:check/>
											</span> <spring:theme code="financial.survey.hasPreferredShares"/>
                                        </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="shareholderPreferredSharesSectionId" class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="shareholderPreferredSharesId" name="shareholderPreferredShares"
                                               class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label control-label_mandatory"
                                               for="shareholderPreferredSharesId">
                                            <spring:theme code="financial.survey.shareholderPreferredShares"/>
                                        </label>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                            </div>


                            <div class="col-md-12">
                                <div class="formCheckBox">
                                    <div class="form-group">
                                        <div class="form-item">
                                            <input id="shareholderHaveReverseInvestmentId"
                                                   name="shareholderHaveReverseInvestment" class="form-control"
                                                   placeholder="." type="checkbox" value=""> <label
                                                class="control-label" for="shareholderHaveReverseInvestmentId">
												<span> <icon:check/>
											</span> <spring:theme code="financial.survey.haveReverseInvestment"/>
                                        </label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="shareholderValueOfReverseInvestmentSectionId" class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="shareholderValueOfReverseInvestmentId"
                                               name="valueOfReverseInvestment"
                                               class="form-control" placeholder="." value="" type="text">
                                        <label class="control-label control-label_mandatory"
                                               for="shareholderValueOfReverseInvestmentId">
                                            <spring:theme code="financial.survey.shareholderValueOfReverseInvestment"/>
                                        </label>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="contentModule-separator"></div>
                        <div class="contentModule-headline">
                            <spring:theme code="general.shareholderequity"/>
                        </div>
                        <hr class="hr w-100">

                        <div id="accordionTransaction" class="services-container-tabcontent">
                            <div class="accordion-item" style="width: 100%">
                                <h5 class="mb-0">
                                    <button class="accordion-button collapsed" data-toggle="collapse" data-target="#collapseThree"
                                            aria-expanded="false" aria-controls="collapseThree">
                                        <h5 class="mb-0"><spring:theme code="general.shareholderequity"/></h5>
                                        <div class="plus-minus-icon"></div>
                                    </button>


                                </h5>

                                <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                                     data-parent="#accordionTransaction">
                                    <div class="accordion-body serviceModule-detail border-top-0">

                                        <div class="row" style="margin-top: 15px;">
                                            <div class="col-md-12">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderAdditionalPaidUpCapitalCurrentQuarterId"
                                                               name="shareholderAdditionalPaidUpCapitalCurrentQuarter"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderAdditionalPaidUpCapitalCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.shareholderEquity.additionalPaidUpCapital.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderRetainedEarningsIncludeCurrentQuarterId"
                                                               name="shareholderRetainedEarningsIncludeCurrentQuarter"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderRetainedEarningsIncludeCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.shareholderEquity.retainedEarningsInclude.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderProfitLossQuarterCurrentQuarterId"
                                                               name="shareholderProfitLossQuarterCurrentQuarter"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderProfitLossQuarterCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.shareholderEquity.profitLossQuarter.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderTotalReservesCurrentQuarterId"
                                                               name="shareholderTotalReservesCurrentQuarter"
                                                               onchange="caluculateTotalCapital()"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderTotalReservesCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.shareholderEquity.totalReserves.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderTreasurySharesCurrentQuarterId"
                                                               name="shareholderTreasurySharesCurrentQuarter"
                                                               onchange="caluculateTotalCapital()"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderTreasurySharesCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.shareholderEquity.treasuryShares.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderHeadOfficeAccountInBranchCurrentQuarterId"
                                                               name="shareholderHeadOfficeAccountInBranchCurrentQuarter"
                                                               onchange="caluculateTotalCapital()"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderHeadOfficeAccountInBranchCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.shareholderEquity.headOfficeAccountInBranch.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderShareholderEquityOthersCurrentQuarterId"
                                                               name="shareholderShareholderEquityOthersCurrentQuarter"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderShareholderEquityOthersCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.shareholderEquity.others.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderMinorityRightsCurrentQuarterId"
                                                               name="shareholderMinorityRightsCurrentQuarter"
                                                               onchange="caluculateTotalCapital()"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderMinorityRightsCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.minorityRights.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="formInputBox">
                                                    <div class="form-group">
                                                        <input id="shareholderTotalShareholderEquityCurrentQuarterId"
                                                               name="shareholderTotalShareholderEquityCurrentQuarter"
                                                               class="form-control" placeholder="." value="" type="text"
                                                               dir="ltr" disabled="true"
                                                               maxlength="80"> <label class="control-label"
                                                                                      for="shareholderTotalShareholderEquityCurrentQuarterId">
                                                        <spring:theme
                                                                code="financial.survey.totalShareholderEquity.currentQuarter"/>
                                                    </label>
                                                        <div class="help-block"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </div>

                        </div>


                        <div class="contentModule-separator"></div>

                        <div class="contentModule-headline">
                            <spring:theme code="financial.survey.shareholderTransaction"/>
                        </div>
                        <hr class="hr w-100">
                        <tags:transaction/>

                        <div
                                class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                            <button type="button"
                                    class="btn btn-slim btn_outline cancelShareholderBtn"
                                    data-dismiss="modal">
                                <spring:theme code="general.cancel"/>
                            </button>
                            <button type="button" class="btn btn-slim saveShareholderBtn">
                                <spring:theme code="general.save"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>






    <div class="modal fade" id="branchModalId" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content panelModule panelModule_halfRadius">
                <form id="branchFormId">

                    <div class="contentModule-headline">
                        <spring:theme code="license.branchdetails"/>
                    </div>
                    <hr class="hr w-100">

                    <div class="row">
                        <div class="col-md-6">
                            <div class="formSelectBox">
                                <div class="form-group">
                                    <select id="branchTypeId" name="branchType"
                                            class="js-select2-oneColumn form-control">
                                    </select> <label class="control-label control-label_mandatory"
                                                     for="branchTypeId"><spring:theme
                                        code="license.branchtype"/></label>
                                </div>
                                <div class="help-block"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="branchNameId" name="branchName" class="form-control"
                                           placeholder="." value="" type="text"> <label
                                        class="control-label control-label_mandatory" for=""> <spring:theme
                                        code="license.branchname"/>
                                </label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="volumeWeightId" name="volumeWeight" class="form-control"
                                           placeholder="." value="" type="text"> <label
                                        class="control-label control-label_mandatory" for="volumeWeightId">
                                    <spring:theme
                                            code="financial.survey.branch.weightPercentage"/>
                                </label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div class="contentModule-separator"></div>
                    <div class="contentModule-headline">
                        <spring:theme code="license.contactinformation"/>
                    </div>
                    <hr class="hr w-100">

                    <div class="row">
                        <div class="col-md-6">
                            <div class="formSelectBox">
                                <div class="form-group">
                                    <select id="branchCountryId" name="branchCountry"
                                            class="js-select2-oneColumn form-control" disabled>
                                        <option value="SA" selected>Saudi Arabia</option>
                                    </select> <label class="control-label control-label_mandatory"
                                                     for="branchCountryId"><spring:theme
                                        code="license.country"/></label>
                                </div>
                                <div class="help-block"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox-split">
                                <div class="formInputBox formInputBox_big">
                                    <div class="form-group">
                                        <input id="branchStreetId" name="branchStreet"
                                               class="form-control form-control_labeled" placeholder="."
                                               value="" type="text"> <label
                                            class="control-label"
                                            for="branchStreetId"> <spring:theme
                                            code="license.street"/>
                                    </label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="branchNumberId" name="branchNumber"
                                               class="form-control form-control_preNumber" placeholder="."
                                               value="" type="text"> <label
                                            class="control-label"
                                            for="branchNumberId"> <spring:theme
                                            code="general.no.number"/>
                                    </label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formSelectBox">
                                <div class="form-group">
                                    <select id="branchRegionId" name="branchRegion"
                                            class="js-select2-search form-control"></select> <label
                                        class="control-label control-label_mandatory"
                                        for="branchRegionId"><spring:theme
                                        code="license.region"/></label>
                                </div>
                                <div class="help-block"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formSelectBox">
                                <div class="form-group">
                                    <select id="branchCityId" name="branchCity"
                                            class="js-select2-search form-control"></select> <label
                                        class="control-label control-label_mandatory"
                                        for="branchCityId"><spring:theme code="license.city"/></label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="branchTelephoneId" name="branchTelephone"
                                           class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label"
                                           for="branchTelephoneId"> <spring:theme
                                            code="license.telephoneno"/>
                                    </label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="branchEmailId" name="branchEmail"
                                           class="form-control" placeholder="." value="" type="text">
                                    <label class="control-label"
                                           for="branchEmailId"> <spring:theme code="license.email"/>
                                    </label>
                                    <div class="help-block" id="branchEmailValidation"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox formInputBox_group">
                                <div class="form-group">
                                    <input id="branchWebsiteId" name="branchWebsite"
                                           class="form-control website" placeholder="." value=""
                                           type="text"> <label
                                        class="control-label"
                                        for="branchWebsiteId"> <spring:theme
                                        code="license.website"/>
                                </label>
                                    <div class="formInputBox-append">
									<span class="formInputBox-text formInputBox-text_tip js-tip"
                                          style="position: relative; z-index: 1000;"
                                          data-tip-title="Tooltip Information to be shown to the user."
                                          data-original-title="" title=""><icon:tipInfo/> </span>
                                    </div>
                                </div>
                                <div class="help-block"></div>
                            </div>
                        </div>
                    </div>

                    <div
                            class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                        <button type="button"
                                class="btn btn-slim btn_outline cancelBranchBtn"
                                data-dismiss="modal">
                            <spring:theme code="general.cancel"/>
                        </button>
                        <button type="button" class="btn btn-slim saveBranchBtn">
                            <spring:theme code="general.save"/>
                        </button>
                        <button type="button" class="btn btn-slim closeBranchBtn"
                                data-dismiss="modal">
                            <spring:theme code="general.close"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div class="modal fade" id="subsidiaryModalId" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content panelModule panelModule_halfRadius">
                <form id="subsidiaryFormId">

                    <div class="contentModule-headline">
                        <spring:theme code="license.subsidiary.details"/>
                    </div>

                    <div class="row">

                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="subsidiaryNameId" name="subsidiaryName" class="form-control"
                                           placeholder="." value="" type="text"> <label
                                        class="control-label control-label_mandatory" for="subsidiaryNameId">
                                    <spring:theme
                                            code="license.subsidiary.name"/>
                                </label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="registrationNameId" name="registrationName" class="form-control"
                                           placeholder="." value="" type="text"> <label
                                        class="control-label control-label_mandatory" for="registrationNameId">
                                    <spring:theme
                                            code="license.subsidiary.registration.name"/>
                                </label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="unifiedNoId" name="unifiedNo" class="form-control"
                                           placeholder="." value="" type="text"> <label
                                        class="control-label control-label_mandatory" for="unifiedNoId"> <spring:theme
                                        code="license.subsidiary.unifiedno"/>
                                </label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="contributionId" name="contribution" class="form-control"
                                           placeholder="." value="" type="text"> <label
                                        class="control-label control-label_mandatory" for="volumeWeightId">
                                    <spring:theme
                                            code="license.subsidiary.contribution"/>
                                </label>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.subsidiaryCompanyIncluded"/>
                        </div>
                        <div class="col-md-12">
                            <div class="formRadioBox">
                                <div class="form-group">
                                    <div class="form-item">
                                        <input id="yesId"
                                               name="subsidiaryIncludedInHeadOfficeRadioBox" class="form-control"
                                               value="true" type="radio" checked> <label
                                            for="yesId" class="control-label"> <spring:theme
                                            code="financial.survey.subsidiaryCompanyIncluded.Yes"/>
                                    </label>
                                    </div>
                                    <div class="form-item">
                                        <input id="noId"
                                               name="subsidiaryIncludedInHeadOfficeRadioBox" class="form-control"
                                               value="false" type="radio"> <label
                                            for="noId" class="control-label"> <spring:theme
                                            code="financial.survey.subsidiaryCompanyIncluded.No"/>
                                    </label>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>

                    <div
                            class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                        <button type="button"
                                class="btn btn-slim btn_outline cancelSubsidiaryBtn"
                                data-dismiss="modal">
                            <spring:theme code="general.cancel"/>
                        </button>
                        <button type="button" class="btn btn-slim saveSubsidiaryBtn">
                            <spring:theme code="general.save"/>
                        </button>
                        <button type="button" class="btn btn-slim closeSubsidiaryBtn"
                                data-dismiss="modal">
                            <spring:theme code="general.close"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>



    <div class="modal fade" id="unsavedChangesModalId" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div
                class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
                role="document">
            <div class="modal-content">
                <form action="" class="js-formInputFileBox">
                    <div class="modal-header modal-header_smallPDB">
                        <div class="modal-title">
                            <spring:theme code="license.apply.changes.unsaved"/>
                        </div>
                        <button type="button" class="modal-close" data-dismiss="modal"
                                aria-label="Close">
                            <icon:close/>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div
                                class="modal-description modal-description_largeMargin modal-description_smallText">
                            <spring:theme code="license.apply.changes.notsubmitted"/>
                        </div>
                    </div>
                    <div class="modal-footer modal-footer_spaceBetween">
                        <button id="dismissChangesBtnId" type="button"
                                class="btn btn-warning btn_round btn_slim" data-dismiss="modal">
                            <spring:theme code="general.dismiss.changes"/>
                        </button>
                        <button id="submitChangesBtnId" type="button"
                                class="btn btn_round btn_slim" data-dismiss="modal">
                            <spring:theme code="general.submit.changes"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div class="modal fade" id="docsmodalId" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content panelModule panelModule_halfRadius">
                <div class="contentModule">
                    <form:form id="docsFormId">
                        <div id="amendmentTypeId"
                             class="contentModule-headline contentModule-headline_big contentModule-headline_bordered">
                            <spring:theme code="license.amend.documents"/>
                        </div>

                        <div
                                class="contentModule-headline contentModule-headline_marginBottom">
                            <spring:theme code="license.amend.amendment.types"/>
                        </div>
                        <div id="amendmentTypesId" class="row"></div>

                        <div id="regularAmendmentDocsId">
                            <div class="contentModule-separator"></div>
                            <div class="contentModule-headline">
                                <spring:theme code="license.amend.supporting.documents"/>
                            </div>
                            <div id="documentsId" class="row"></div>
                        </div>
                        <div class="col-sm-6 docTemplate">
                            <div class="formInputFile">
                                <div class="form-group">
                                    <input id="fileId" name="file" class="form-control js-inputFile"
                                           type="file" accept="image/jpeg,application/pdf" value="">
                                    <input id="text08" name="text08" class="form-control"
                                           type="text" value="" placeholder="" readonly tabindex="-1">
                                    <label class="control-label" for=""></label>
                                    <div class="form-icon form-icon_browse">
                                        <icon:upload/>
                                    </div>
                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                        <icon:cross/>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>
                        <%-- <div class="contentModule-separator"></div>
                       <div id="simulatedPriceDivContent" class="contentModule-headline contentModule-headline_marginBottom"><spring:theme code="license.amend.totalPrice"/></div>
                          <div id="simulatedPriceRow" class="row">
                               <div class="col-sm-6">
                               <ul class="dottedList dottedList_green dottedList_big">
                               <li class="dottedList-item" id="simulatedPriceNetValue"></li></ul></div>
                       </div> --%>
                        <div class="acceptTerms acceptTerms-no-margin">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="formCheckBox">
                                        <div class="form-group">
                                            <div class="form-item">
                                                <c:url var="termsUrl"
                                                       value="/cms/sagia-cms-TandC-licenseServices"/>
                                                <input id="termsAndConditionsId" name="checkbox01name"
                                                       class="form-control" placeholder="." type="checkbox"
                                                       value="entity name"> <label class="control-label"
                                                                                   for="termsAndConditionsId">
                                                <span><icon:check/></span>
                                                <spring:theme code="register.termsConditions"
                                                              arguments="${termsUrl}"/>
                                            </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div
                                class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                            <button type="button"
                                    class="btn btn-slim btn_outline cancelAmendmentDialogBtn">
                                <spring:theme code="general.cancel"/>
                            </button>
                            <button id="submitAmendmentBtnId" type="button"
                                    class="btn btn-slim submitAmendmentBtn">
                                <spring:theme code="general.submit"/>
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="requestSubmittedDialogId" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div
                class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
                role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title">
                        <spring:theme code="financial.survey.survey"/>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="modal-description">
                        <spring:theme code="financial.survey.submitted"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim" data-dismiss="modal">
                        <spring:theme code="general.close"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="requestErrorDialogId" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div
                class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
                role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title">
                        <spring:theme code="financial.survey.survey"/>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="modal-description globalMessage-msg">
                        <spring:theme code="financial.survey.errorsaving.survey"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim showHistoryBtn"
                            data-dismiss="modal">
                        <spring:theme code="financial.survey.gotosurveypage"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="licenseAmendmentValidationDialogId"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div
                class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
                role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title">
                        <spring:theme code="financial.survey.errorsaving.survey"/>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="modal-description">
                        <spring:theme code="financial.survey.errorvalidatingsurvey"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim" data-dismiss="modal">
                        <spring:theme code="general.close"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="affiliateModalId" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form id="affiliateFormId">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content panelModule panelModule_halfRadius">
                    <div class="contentModule-headline">
                        <spring:theme code="financial.survey.affiliate"/>
                    </div>
                    <hr class="hr w-100">
                    <div id="affiliatePersonEntityTypeId" class="row">
                        <div class="col-md-8">
                            <div class="formRadioBox">
                                <div class="form-group">
                                    <div class="form-item">
                                        <input id="affiliatePersonId" name="affiliatePersonEntityRadioBox"
                                               class="form-control" value="true" type="radio" checked>
                                        <label for="affiliatePersonId" class="control-label"> <spring:theme
                                                code="financial.survey.person"/>
                                        </label>
                                    </div>
                                    <div class="form-item">
                                        <input id="affiliateEntityId" name="affiliatePersonEntityRadioBox"
                                               class="form-control" value="false" type="radio"> <label
                                            for="affiliateEntityId" class="control-label"> <spring:theme
                                            code="financial.survey.entity"/>
                                    </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="contentModule-separator"></div>
                    <div id="contentNewAffiliateForm">
                        <div class="contentModule-headline">
                            <spring:theme code="license.basicinformation"/>
                        </div>
                        <hr class="hr w-100">

                        <%--Entity affiliate--%>
                        <div id="entityAffiliateId">

                            <div id="affiliateEntityBasicInformation" class="row">


                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="affiliateNameEnglishId"
                                                   name="affiliateNameEnglish" class="form-control"
                                                   placeholder="." value="" type="text"> <label
                                                class="control-label control-label_mandatory"
                                                for="affiliateNameEnglishId"> <spring:theme
                                                code="financial.survey.nameEntity"/>
                                        </label>
                                            <div class="help-block"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="affiliateCompanyCountry" name="affiliateCompanyCountry"
                                                    class="js-select2-oneColumn form-control"></select> <label
                                                class="control-label control-label_mandatory"
                                                for="companyCountry"><spring:theme
                                                code="general.country"/></label>
                                        </div>
                                    </div>
                                </div>


                                <div class="col-md-6">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="affiliateSectorId" name="affiliateSector"
                                                    class="js-select2-oneColumn form-control"></select> <label
                                                class="control-label"
                                                for="affiliateSectorId"><spring:theme
                                                code="license.sector"/></label>
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="affiliateSubsectorId"
                                                   name="affiliateSubsector" class="form-control"
                                                   placeholder="." value="" type="text" maxlength="20"> <label
                                                class="control-label" for="">
                                            <spring:theme code="license.subsector"/>
                                        </label>
                                            <div class="help-block"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="affiliateMultinationalCompanyId"
                                                    name="affiliateMultinationalCompany"
                                                    class="js-select2-oneColumn form-control"></select> <label
                                                class="control-label control-label_mandatory"
                                                for="affiliateMultinationalCompanyId"><spring:theme
                                                code="license.multinational"/></label>
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <%--Individual affiliate--%>
                        <div id="individualAffiliateId" class="row">
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="individualAffiliateNameEnglishId"
                                               name="affiliateNameEnglish" class="form-control"
                                               placeholder="." value="" type="text"> <label
                                            class="control-label control-label_mandatory"
                                            for="individualAffiliateNameEnglishId"> <spring:theme
                                            code="financial.survey.nameperson"/>
                                    </label>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="affiliateGenderId" name="affiliateGender"
                                                class="js-select2-oneColumn form-control"></select> <label
                                            class="control-label control-label_mandatory"
                                            for="affiliateGenderId"><spring:theme
                                            code="license.gender"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="affiliateNationalityCurrentId"
                                                name="affiliateNationalityCurrent"
                                                class="js-select2-search form-control"></select> <label
                                            class="control-label control-label_mandatory"
                                            for="affiliateNationalityCurrentId"><spring:theme
                                            code="license.currentnationality"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="affiliateCountryId" name="affiliateCountry"
                                                class="js-select2-search form-control"></select> <label
                                            class="control-label control-label_mandatory"
                                            for="affiliateCountryId"><spring:theme
                                            code="financial.survey.shareholders.residenceCountry"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>


                        <div class="contentModule-separator"></div>

                        <div class="contentModule-headline">
                            <spring:theme code="financial.survey.shareholderTransaction"/>
                        </div>
                        <hr class="hr w-100">
                        <tags:transaction-affeliate/>


                        <div
                                class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                            <button type="button"
                                    class="btn btn-slim btn_outline cancelAffiliateBtn"
                                    data-dismiss="modal">
                                <spring:theme code="general.cancel"/>
                            </button>
                            <button type="button" class="btn btn-slim saveAffiliateBtn">
                                <spring:theme code="general.save"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>



    <div class="modal fade" id="licenseAmendmentNoChangesDialogId"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div
                class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
                role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title">
                        <spring:theme code="license.amendment.noChanges"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim" data-dismiss="modal">
                        <spring:theme code="general.close"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div id="paginationElementId" style="display: none">
        <icon:arrow_green_right/>
    </div>

    <modals:termsAndConditionsModal/>
    <modals:errorModal/>

    <script>
        var controllerUrl = '${controllerUrl}';
        var quarterCode = '${quarterCode}';
        var srId = '${quarterCode}';
        var configuredFileSize = ${maxUploadSize};
    </script>
