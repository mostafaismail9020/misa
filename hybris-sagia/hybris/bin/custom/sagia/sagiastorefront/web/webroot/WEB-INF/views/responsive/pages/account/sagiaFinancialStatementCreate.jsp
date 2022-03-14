<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
</script>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="header.financialStatement.text" />
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
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img"/>
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

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="row renewal-services w-100">
            <div class="col-xl-3 col-12">
                <a href="${encodedContextPath}/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack" id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
            </div>
            <div class="col-xl-9 d-none col-12 btn-drafts_list amend-service-link">
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="${serviceId}"
                        data-service-id="${serviceId}"><spring:theme code="general.savedraft"/><span
                        class="iconElement iconElement_save"><icon:save/></span></button>
                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="${serviceId}"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span></button>
            </div>
        </div>
    </div>
</div>
<!--<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <a href="${encodedContextPath}/my-sagia/sagia-profile" class="btn btn_leftIconLink btn_darkLink">
            <span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="legalConsultationCreate.backToAccountOverview.text"/>
        </a>
    </div>
</div>
-->

<div class="mainSection mainSection_dark mainSection_pdt16 mt-5 service-main">
    <div class="container">

    	<div class="expandableContent-main" id="expandedContentParent">
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
                                    <div>
                                         <spring:theme code="header.financialStatement.message" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <form:form id="${serviceId}" action="${encodedContextPath}/financial-statement/create" enctype="multipart/form-data" method="post" modelAttribute="financialStatementForm" class="js-financialStatement-create">
            <div class="panelModule panelModule_halfRadius">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule-headline headline-text">
                            <!-- <icon:documents/> -->
                            <spring:theme code="general.attachments"/>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file0" data-id="0" name="files[0]" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                                        <input id="text05" name="text05" class="form-control control-label_mandatory" type="text" value="" placeholder="" readonly="" tabindex="-1"/>
                                        <label class="control-label control-label_mandatory" for=""><spring:theme code="header.financialStatement.text"/></label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset" id="file0_reset"><icon:cross/></div>
										<div class="help-block"></div>
                                    </div>
                                </div>
                            </div>

                        </div>
						 <div class="form-condition-spl-notes"><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>
                    </div>
                </div>
            </div>
            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions">
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <formElement:termsAndConditionsCheckbox event="FINANCIAL_STATEMENT" id="termsAndConditions" path="termsAndConditionsChecked" containerCssClass="terms-and-condition"/>
                </div>
                <button type="reset" class="btn btn_leftIconLink btn_outline full-width-responsive" onclick="window.location.href='${encodedContextPath}">
                    <spring:theme code="general.cancel"/>
                </button>
                
                <button type="submit" value="Submit request" class="btn js-submit-financialStatement full-width-responsive" disabled><spring:theme code="general.submit"/></button>
            </div>
        </form:form>
    </div>
</div>

<common:errorModal />
<input type="hidden" id="financialStatementErrorMessage" value="${errorMessage}"/>
