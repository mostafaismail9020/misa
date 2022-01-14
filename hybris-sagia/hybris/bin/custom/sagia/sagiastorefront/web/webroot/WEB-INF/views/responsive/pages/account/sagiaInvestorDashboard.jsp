<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/infoModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/confirmationModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/responsive/payment" %>

<%--@elvariable id="dashboardBanner" type="java.lang.String"--%>
<%--@elvariable id="encodedContextPath" type="java.lang.String"--%>
<%--@elvariable id="profilePicture" type="java.lang.String"--%>
<%--@elvariable id="DashboardBannerUrl" type="java.lang.String"--%>
<%--@elvariable id="user" type="de.hybris.platform.commercefacades.user.data.CustomerData"--%>

<script src = "${MIGS_Session_JS}"></script>

<c:set var="pageIsDashboard" value="${fn:containsIgnoreCase(requestScope['javax.servlet.forward.request_uri'], 'dashboard')}"/>

<section class="mainSection_grey mainSection_noPadding">
    <!-- <form:form id="bannerUploadForm" action="dashboard/update-dashboardPic" method="post" enctype="multipart/form-data">
        <input id="file" name="file" class="form-control js-inputFile dashboardBannerUpload" type="file" value="" accept="image/jpeg,application/pdf" style="display: none;">
            <c:choose>
                <c:when test="${empty dashboardBanner and (not user.shouldDisplaySetCompanyPhotoOption)}">
                    <div class="dashboardHead" id="dashboardHeadId" style="display: none;">
                </c:when>
                <c:otherwise>
                    <div class="dashboardHead" id="dashboardHeadId">
                </c:otherwise>
            </c:choose>
            <label for="file">
                <c:choose>
                    <c:when test="${empty dashboardBanner}">
                        <div class="dashboardHeadImage dashboardHeadImage_defaultBackground"></div>
                        <div class="dashboardHeadAdd">
                            <div class="dashboardHeadAdd-text">
                                <icon:camera/><spring:theme code="dashboard.addcoverphoto"/>
                            </div>
                            <a id="setCompanyPhotoAnchor" class="dashboardHeadAdd-link" style="cursor: pointer; display: none">
                                <icon:cross/><spring:theme code="dashboard.message.not.now"/>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="dashboardHeadImage" style="background-image: url(${dashboardBanner});"></div>
                    </c:otherwise>
                </c:choose>
            </label>
        </div>
    </form:form> -->
    <div class="row">
        <div class="col-12 owl-slider">
            <div class="owl-carousel owl-theme" id="dashboard-carousel">
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image1.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image2.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image3.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image4.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image5.jpg"></div>
            </div>
        </div>
    </div>
 
</section>



<section class="mainSection mainSection_grey mainSection_noPadding account-details-section">
    <div class="container">
        <div class="dashboardUser dashboardUser_slim dashboardUser_noBorder">
            <div class="dashboardUser-wrapper col-md-12 mr-0 pt-3 px-0">
                <div class="dashboardUser-left col-md-6 pr-0">
                    <div class="col">
                        <div class="dashboardUser-image">
                            <div class="dashboardUser-image position-absolute dashboardHeadAdd dashboard-user-add-icon">
                                <button type="button"  id="btnfile" class="dashboardUser-image-add"><icon:plus/><span id="fname"></span></button>
                                
                                <div class="myAccount-profilImage">
                                    <div class="myAccount-profilImage-img">
                                        <div class="profilePicture js-profilePicture" style="background-image:url(${profilePicture})"></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="dashboardUser-col">
                                <div class="dashboardUser-entry ml-3">
                                    <h2 class="clr_gld"><c:out value='${user.company}'/></h2>
                                    <div>Last Login: <span class="clr_gld">02/11/2021 10:00AM</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dashboardUser-right col-md-6 px-0">
                    <div class="col-6">
                        <div class="dashboardUser-col flex-column dashboardUser-col-alignment">
                                <!-- <div class="dashboardUser-label dashboardUser-label-sm"><spring:theme code="general.welcomeback"/></div> -->
                                <div class="dashboardUser-label profile-detail dashboardUser-label-xs"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Profile-name-icon.png"/><span class="dashboardUser-value h5"><c:out value='${user.name}'/></span></div>
                                <div class="dashboardUser-label profile-detail"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/profile-email-icon.png"/><span class="dashboardUser-value"><c:out value='${user.email}'/></span></div>
                                <div class="dashboardUser-label profile-detail"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/profile-mobile-number-icon.png"/><span class="dashboardUser-value"><c:out value='${user.mobileCountryCode}'/>&nbsp;<c:out value='${user.mobileNumber}'/></span></div>
                        </div>
                    </div>
                    <div class="col-6 d-flex">
                        <div class=" user-icon mr-3">
                            <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/>
                        </div>
                        <div class=" user-icon mr-3">
                            <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/>
                        </div>
                        <div class=" user-icon mr-1">
                            <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPadding account-details-section">
    <div class="container">
        <h1 class="dashboard-headline js-dashboardWidget-headline text-center mt-5 pt-5 mb-5">
            Services
        </h1>
        <div class="dashboardUser dashboardUser_slim dashboardUser_noBorder">
            <div class="service-card-wrapper">
                <div class="service-card">
                    <img  alt="" src="${commonResourcePath}/images/dashboard-media/Investment-license-application/Entity information-black.png"/>
                    <span>Licensing Services</span>
                </div>
                <div class="service-card">
                    <img  alt="" src="${commonResourcePath}/images/dashboard-media/Investment-license-application/Entity information-black.png"/>
                    <span>Licensing Services</span>
                </div>
                <div class="service-card">
                    <img  alt="" src="${commonResourcePath}/images/dashboard-media/Investment-license-application/Entity information-black.png"/>
                    <span>Licensing Services</span>
                </div>
            </div>
        </div>
    </div>
</section>

    <div class="globalMessage-holder" id="awaitingPaymentDiv" style="display: none;">
        <div class="container">
            <div class="globalMessage">
                <div class="globalMessage-action">
                    <a onclick="awaitingPayment()" class="btn btn_round"><spring:theme code="awaiting.payment.pay" /></a>
                </div>
                <div class="globalMessage-msg">
                    <div class="globalMessage-icon"><icon:warning/></div>
                    <spring:theme code="dashboard.message.awaitingpayment.text"/>
                </div>
            </div>
        </div>
    </div>
    <div class="globalMessage-holder" id="globalMessageDivHeader" style="display: none;">
        <div class="container">
            <div class="globalMessage">
                <div class="globalMessage-action">
                    <a href="${encodedContextPath}/my-sagia/notifications" class="btn btn_round"><spring:theme code="account.notifications.title"/></a>
                </div>
                <div class="globalMessage-msg">
                    <div class="globalMessage-icon"><icon:warning/></div>
                    <spring:theme code="dashboard.message.text"/>
                </div>
            </div>
        </div>
    </div>

<section class="mainSection mainSection_grey mainSection_xsmallPaddingTop mainSection_noPaddingBottom">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <a href="${encodedContextPath}/dashboard-edit" class="btn-dashboard btn_link_slim">
                <spring:theme code="dashboard.customize"/> <span class="iconElement iconElement_pin"><icon:pin/></span> 
            </a>
        </div>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPadding">
    <div class="container">
            <div class="dashboardUser dashboardUser_slim dashboardUser_noBorder">
                <div class="dashboard-tabs">
                    <ul class="nav nav-tabs mb-0" role="tablist">
                        <li class="nav-item">
                        <a class="nav-link active" href="#mylicense" role="tab" data-toggle="tab"><spring:theme code="myLicense.title"/></a>
                        </li>
                        <li class="nav-item">
                        <a class="nav-link" href="#payments" role="tab" data-toggle="tab">Payments</a>
                        </li>
                        <li class="nav-item">
                        <a class="nav-link" href="#servicerequest" role="tab" data-toggle="tab">My Service Request overview</a>
                        </li>
                        <li class="nav-item">
                        <a class="nav-link" href="#incompleterequest" role="tab" data-toggle="tab">My Incomplete Request</a>
                        </li>
                        <li class="nav-item">
                        <a class="nav-link" href="#tickets" role="tab" data-toggle="tab">My Tickets</a>
                        </li>
                    </ul>
                    
                    <!-- Tab panes -->
                    <div class="tab-content dashboard-tab-body">
                        <div role="tabpanel" class="license tab-pane fade in active show" id="mylicense">
                            <dashboard:myLicense/>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="payments">
                            <div class="dashboardWidget js-dashboardWidget">
                                <div class="dashboardWidget-headline js-dashboardWidget-headline">
                                    <a href="" data-redirect="payments-overview" class="js-page-redirect"
                                       style="text-decoration: inherit;color: inherit">
                                        <spring:theme code="payments.page.title"/>
                                    </a>
                                    <div class="dashboardWidget-headline-icon">
                                        <a href="" data-redirect="payments-overview" class="js-page-redirect"><icon:payments/></a>
                                    </div>
                                    <div class="dashboardWidget-filter">
                                        <select id="paymentSort" title="Payments" class="js-select2-oneColumn form-control" onchange="sortPayments()">
                                            <option value="null"><spring:theme code="sagia.sort.sort.by"/></option>
                                            <option value="name_asc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.ascending"/> </option>
                                            <option value="name_desc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.descending"/> </option>
                                            <option value="amount_asc"><spring:theme code="sagia.sort.lowest"/> </option>
                                            <option value="amount_desc"><spring:theme code="sagia.sort.highest"/> </option>
                                            <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.oldest"/></option>
                                            <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.latest"/></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="dashboardWidget-body">
                                    <div class="dashboardWidgetPayments">
                                        <div class="tableModule tableModule_slim tableModule_striped">
                                            <table class="tableModule-table">
                                                <thead class="tableModule-head">
                                                    <tr>
                                                        <th><spring:theme code="dashboard.payments.date"/></th>
                                                        <th><spring:theme code="dashboard.payments.name"/></th>
                                                        <th><spring:theme code="dashboard.payments.status"/></th>
                                                        <th class="dashboardWidgetPayments-lastCol"><spring:theme code="dashboard.payments.amount"/></th>
                                                        <th><spring:theme code="payment.pay" /></th>
                                                    </tr>
                                                </thead>
                                                <tbody class="tableModule-body" id="paymentsTable"></tbody>
                                            </table>
                                        </div>
                                        <div class="paginationModule paginationModule_loading">
                                            <c:if test="${!pageIsDashboard}">
                                                <div style="width: 150px; position: absolute">
                                                    <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                                </div>
                                            </c:if>
                                            <div class="paginationModule-wrapper">
                                                <button class="paginationModule-control paginationModule-control_left" disabled>
                                                    <icon:arrow_green_right/>
                                                </button>
                                                <div class="paginationModule-items">
                                                    <div class="loadingModule">
                                                        <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                        <div class="loadingModule-msg">Loading content ...</div>
                                                    </div>
                                                </div>
                                                <button class="paginationModule-control paginationModule-control_right">
                                                    <icon:arrow_green_right/>
                                                </button>
                                            </div>
                                                <div class="tableModule-headline">
                                                    <a href="" data-redirect="payments-overview" class="js-page-redirect">
                                                        <spring:theme code="dashboard.viewall" text="View all"/>
                                                    </a>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="servicerequest">
                            <dashboard:servicesRequest/>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="incompleterequest">
                            <dashboard:savedDrafts/>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="tickets">
                            <div class="dashboardWidget js-dashboardWidget">
                                <div class="dashboardWidget-headline js-dashboardWidget-headline">
                                    <span>
                                        <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"
                                           style="text-decoration: inherit;color: inherit">
                                        <spring:theme code="dashboard.ticket.yourtickets"/>
                                        </a>
                                    </span>
                                    <div class="dashboardWidget-headline-icon">
                                        <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"><icon:your-tickets/></a>
                                    </div>
                                    <div class="dashboardWidget-filter">
                                        <select id="ticketSort" title="tickets" class="js-select2-oneColumn form-control" onchange="sortTickets()">
                                            <option value="null"><spring:theme code="sagia.sort.sort.by"/></option>
                                            <option value="status_asc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                                            <option value="status_desc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                                            <option value="number_asc" data-sort="asc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                                            <option value="number_desc" data-sort="desc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                                            <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                                            <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="dashboardWidget-body">
                                    <div class="dashboardWidgetTickets">
                                        <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
                                            <table class="tableModule-table">
                                                <thead class="tableModule-head">
                                                <tr>
                                                    <th><spring:theme code="dashboard.ticket.lastupdate"/></th>
                                                    <th><spring:theme code="dashboard.ticket.ticketnumber"/></th>
                                                    <th><spring:theme code="dashboard.ticket.status"/></th>
                                                    <th><spring:theme code="dashboard.ticket.options"/></th>
                                                </tr>
                                                </thead>
                                                <tbody class="tableModule-body" id="ticketsTable"></tbody>
                                            </table>
                                        </div>
                                        <div class="paginationModule paginationModule_loading">
                                            <c:if test="${!pageIsDashboard}">
                                                <div style="width: 150px; position: absolute">
                                                    <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                                </div>
                                            </c:if>
                                            <div class="paginationModule-wrapper">
                                                <button class="paginationModule-control paginationModule-control_left" disabled>
                                                    <icon:arrow_green_right/>
                                                </button>
                                                <div class="paginationModule-items">
                                                    <div class="loadingModule">
                                                        <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                        <div class="loadingModule-msg">Loading content ...</div>
                                                    </div>
                                                </div>
                                                <button class="paginationModule-control paginationModule-control_right">
                                                    <icon:arrow_green_right/>
                                                </button>
                                            </div>
                                            <c:if test="${pageIsDashboard}">
                                                <div class="tableModule-headline">
                                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect">
                                                        <spring:theme code="dashboard.viewall" text="View all"/>
                                                    </a>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container">
        <div class="row">
            <ul id="draggableComponentsList" class="dashboardWidgetList">
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="myLicense"><dashboard:myLicense/></div>
                </li>
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="dashboardImage">
                        <div class="dashboardWidget dashboardWidget_banner">
                            <div class="simple-banner banner__component--responsive">
                                <a href="${DashboardBannerUrl}"><picture class="dashboardWidget-pictureSet"></picture></a>
                            </div>
                        </div>
                    </div>
                     <dashboard:opportunityTickets></dashboard:opportunityTickets>
                </li>
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="salaryAndEmployment">
                        <dashboard:salaryAndEmployment/>
                    </div>
                </li>
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="servicesRequest">
                        <dashboard:servicesRequest/>
                    </div>
                </li>
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="savedDrafts">
                        <dashboard:savedDrafts/>
                    </div>
                </li>
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="payments">
                        <div class="dashboardWidget js-dashboardWidget">
                            <div class="dashboardWidget-headline js-dashboardWidget-headline">
                                <a href="" data-redirect="payments-overview" class="js-page-redirect"
                                   style="text-decoration: inherit;color: inherit">
                                    <spring:theme code="payments.page.title"/>
                                </a>
                                <div class="dashboardWidget-headline-icon">
                                    <a href="" data-redirect="payments-overview" class="js-page-redirect"><icon:payments/></a>
                                </div>
                                <div class="dashboardWidget-filter">
                                    <select id="paymentSort" title="Payments" class="js-select2-oneColumn form-control" onchange="sortPayments()">
                                        <option value="null"><spring:theme code="sagia.sort.sort.by"/></option>
                                        <option value="name_asc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.ascending"/> </option>
                                        <option value="name_desc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.descending"/> </option>
                                        <option value="amount_asc"><spring:theme code="sagia.sort.lowest"/> </option>
                                        <option value="amount_desc"><spring:theme code="sagia.sort.highest"/> </option>
                                        <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.oldest"/></option>
                                        <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.latest"/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="dashboardWidget-body">
                                <div class="dashboardWidgetPayments">
                                    <div class="tableModule tableModule_slim tableModule_striped">
                                        <table class="tableModule-table">
                                            <thead class="tableModule-head">
                                                <tr>
                                                    <th><spring:theme code="dashboard.payments.date"/></th>
                                                    <th><spring:theme code="dashboard.payments.name"/></th>
                                                    <th><spring:theme code="dashboard.payments.status"/></th>
                                                    <th class="dashboardWidgetPayments-lastCol"><spring:theme code="dashboard.payments.amount"/></th>
                                                    <th><spring:theme code="payment.pay" /></th>
                                                </tr>
                                            </thead>
                                            <tbody class="tableModule-body" id="paymentsTable"></tbody>
                                        </table>
                                    </div>
                                    <div class="paginationModule paginationModule_loading">
                                        <c:if test="${!pageIsDashboard}">
                                            <div style="width: 150px; position: absolute">
                                                <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                            </div>
                                        </c:if>
                                        <div class="paginationModule-wrapper">
                                            <button class="paginationModule-control paginationModule-control_left" disabled>
                                                <icon:arrow_green_right/>
                                            </button>
                                            <div class="paginationModule-items">
                                                <div class="loadingModule">
                                                    <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                    <div class="loadingModule-msg">Loading content ...</div>
                                                </div>
                                            </div>
                                            <button class="paginationModule-control paginationModule-control_right">
                                                <icon:arrow_green_right/>
                                            </button>
                                        </div>
                                            <div class="tableModule-headline">
                                                <a href="" data-redirect="payments-overview" class="js-page-redirect">
                                                    <spring:theme code="dashboard.viewall" text="View all"/>
                                                </a>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="yourTickets">
                        <div class="dashboardWidget js-dashboardWidget">
                            <div class="dashboardWidget-headline js-dashboardWidget-headline">
                                <span>
                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"
                                       style="text-decoration: inherit;color: inherit">
                                    <spring:theme code="dashboard.ticket.yourtickets"/>
                                    </a>
                                </span>
                                <div class="dashboardWidget-headline-icon">
                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"><icon:your-tickets/></a>
                                </div>
                                <div class="dashboardWidget-filter">
                                    <select id="ticketSort" title="tickets" class="js-select2-oneColumn form-control" onchange="sortTickets()">
                                        <option value="null"><spring:theme code="sagia.sort.sort.by"/></option>
                                        <option value="status_asc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                                        <option value="status_desc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                                        <option value="number_asc" data-sort="asc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                                        <option value="number_desc" data-sort="desc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                                        <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                                        <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="dashboardWidget-body">
                                <div class="dashboardWidgetTickets">
                                    <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
                                        <table class="tableModule-table">
                                            <thead class="tableModule-head">
                                            <tr>
                                                <th><spring:theme code="dashboard.ticket.lastupdate"/></th>
                                                <th><spring:theme code="dashboard.ticket.ticketnumber"/></th>
                                                <th><spring:theme code="dashboard.ticket.status"/></th>
                                                <th><spring:theme code="dashboard.ticket.options"/></th>
                                            </tr>
                                            </thead>
                                            <tbody class="tableModule-body" id="ticketsTable"></tbody>
                                        </table>
                                    </div>
                                    <div class="paginationModule paginationModule_loading">
                                        <c:if test="${!pageIsDashboard}">
                                            <div style="width: 150px; position: absolute">
                                                <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                            </div>
                                        </c:if>
                                        <div class="paginationModule-wrapper">
                                            <button class="paginationModule-control paginationModule-control_left" disabled>
                                                <icon:arrow_green_right/>
                                            </button>
                                            <div class="paginationModule-items">
                                                <div class="loadingModule">
                                                    <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                    <div class="loadingModule-msg">Loading content ...</div>
                                                </div>
                                            </div>
                                            <button class="paginationModule-control paginationModule-control_right">
                                                <icon:arrow_green_right/>
                                            </button>
                                        </div>
                                        <c:if test="${pageIsDashboard}">
                                            <div class="tableModule-headline">
                                                <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect">
                                                    <spring:theme code="dashboard.viewall" text="View all"/>
                                                </a>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="dashboardWidgetListComponent js-component askOurExpert" style="display: none">
                    <div class="col col-12" id="support"><dashboard:support/></div>
                </li>
            </ul>
        </div>
    </div>
</section>

<table class="paymentTemplateWrapper" style="display:none;">
    <tr>
        <td>
            <div class="dashboardWidgetPayments-date dashboardWidgetPayments-date_advanced">
                <div class="paymentDate"></div>
            </div>
        </td>
        <td>
            <div class="dashboardWidgetPayments-title dashboardWidgetPayments-title_advanced">
                <a href=""><span class="paymentName"></span></a>
            </div>
            <div class="dashboardWidgetPayments-eid dashboardWidgetPayments-eid_noMargin"></div>
        </td>
        <td>
            <span class="statusDescription"></span>
            <div class="dashboardWidgetPayments-status-icon"><icon:status-complete/></div>
        </td>
        <td>
            <div class="dashboardWidgetPayments-amount"></div>
            <div class="dashboardWidgetPayments-currency"></div>
        </td>
        <td>
            <div class="dashboardWidgetPayments-pay">
            	<a onclick="" class="" style=""></a>
            </div>
        </td>
    </tr>
</table>

<table class="ticketTemplateWrapper" style="display:none;">
    <tr>
        <td class="lastUpdate"></td>
        <td class="ticketNumber"></td>
        <td><div class="dashboardWidgetTickets-status-open"></div></td>
        <td>
            <a href="javascript:void(0)" class="link dashboardWidgetTickets-btn"><spring:theme code="dashboard.ticket.details"/></a>
            <div class="dashboardWidgetTickets-count">1</div>
        </td>
    </tr>
</table>

<div class="statusIcons" style="display: none">
    <div class="ERROR"><icon:status-cancelled/></div>
    <div class="PENDING"><icon:status-open/></div>
    <div class="DONE"><icon:status-complete/></div>
</div>

<div class="modal fade" id="enquiryDetail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document"></div>
</div>

<payment:paymentModal/>

<script type="application/javascript">
    //var displayTutorial = ${displayTutorial};
    variableEditable = false;
</script>
