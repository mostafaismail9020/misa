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
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/responsive/payment" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/infoModal.tag" %>
<%@ taglib prefix="modal" tagdir="/WEB-INF/tags/responsive/modals" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<%--@elvariable id="MIGS_Session_JS" type="java.lang.String"--%>
<%--@elvariable id="processingTime" type="com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData"--%>
<script>
    var controllerUrl = '${controllerUrl}';
</script>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="license.apply.sagialicenseapplication"/>
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense or hasAwaitingPayment}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                    <div class="calendar notification">
                        <a href="${encodedContextPath}/my-sagia/notifications">
                            <span></span>
                        </a>
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

<div class="mainSection mainSection_narrow mainSection_white">
    <div class="container">
        <!-- <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="license.apply.sagialicenseapplication"/></h1>
        </div> -->
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
    <c:if test="${'/simulator' eq controllerUrl}">
        <div class="container">
            <div class="globalMessage-holder">
                <div class="globalMessage">
                    <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
                        <div class="globalMessage-action">
                            <a href="${encodedContextPath}/login#register-quick" class="btn btn_round btn-warning"><spring:theme code="license.simulation.register"/></a>
                        </div>
                    </sec:authorize>
                    <div class="globalMessage-msg">
                        <div class="globalMessage-icon"><icon:warning/></div>
                        <spring:theme code="license.simulation.warning"/>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>

<div class="new-js-panelTabs panelTabs panelTabs_white panelTabs_lightNavigation panelTabs_noPanelInBody panelTabs_iconsAndLabel panelTabs_separated panelTabs_formFlow panelTabs_tip_none">
    <style>
        .new-js-panelTabs .panelTabs-content .panelTabs-head {
            display: none;
        }
    </style>
    <div class="panelTabs-navigation">
        <ul class="clearfix tabs-list tabamount4">
            <li id="accessibletabsnavigation0-0" class="first last">
                <c:url value="/my-sagia/license/entity" var="entityInfoLink"/>
                <a id="entityInformationTab" class="panelTabs-head active first last" style="cursor: default !important;" href="#">
                    <icon:entityInformation/>
                    <span class="panelTabs-label"><spring:theme code="licenseApplyEntityInformation.entityInformation.title"/></span>
                    <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
                    <span class="panelTabs-currentInfo"></span>
                </a>
            </li>
            <li id="accessibletabsnavigation0-1">
                <c:url value="/my-sagia/license/shareholders" var="shareHolderLink"/>
                <a id="shareholdersTab" class="panelTabs-head" style="cursor: default !important;" href="#">
                    <icon:shareholder/>
                    <span class="panelTabs-label"><spring:theme code="license.apply.shareholders"/></span>
                    <span class="panelTabs_formFlow-bar"></span>
                    <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
                </a>
            </li>
            <li id="accessibletabsnavigation0-2" class="active" >
                <a id="contactPersonTab" class="panelTabs-head" style="cursor: default !important;" href="#">
                    <icon:contactPerson/>
                    <span class="panelTabs-label"><spring:theme code="licenseApply.contactPerson.text"/></span>
                    <span class="panelTabs_formFlow-bar"></span>
                    <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
                </a>
            </li>
            <li id="accessibletabsnavigation0-3" class="last">
                <c:url value="/my-sagia/license/review" var="reviewLink"/>
                <a id="reviewTab" class="panelTabs-head last" style="cursor: default !important;" href="#">
                    <icon:review/>
                    <span class="panelTabs-label"><spring:theme code="license.apply.review"/></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="panelTabs-content container">
        <license:newLicenseApplyContactPerson/>
    </div>
<%--    <license:newLicenseApplyEntityInformation/>--%>
<%--    <license:licenseApplyContactPerson/>--%>
<%--    <license:licenseApplyReview/>--%>
</div>

<c:if test='${controllerUrl eq "/simulator"}'>
    <div class="modal fade" id="simulatorWarning" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title"><spring:theme code="license.simulation.modal.warning.title"/></div>
                </div>
                <div class="modal-body">
                    <div class="modal-description">
                        <spring:theme code="license.simulation.modal.warning.content"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim showHistoryBtn" data-dismiss="modal"><spring:theme
                            code="license.simulation.modal.close"/></button>
                </div>
            </div>
        </div>
    </div>
</c:if>



