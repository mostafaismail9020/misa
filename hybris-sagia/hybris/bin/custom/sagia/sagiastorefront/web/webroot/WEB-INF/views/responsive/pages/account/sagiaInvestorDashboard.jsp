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
<%@ include file="/WEB-INF/tags/responsive/common/successModal.tag" %>
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

<%-- <section class="mainSection_grey mainSection_noPadding">
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
</section> --%>


<section class="mainSection mainSection_noPadding userDetail-block" style="overflow: visible;">
    <div class="container">
        <div class="dashboardUser dashboardUser_slim dashboardUser_noBorder pb-0" style="overflow: visible;">
            <div class="dashboardUser-wrapper col-md-12 mr-0 pt-md-3 px-0">
                <div class="dashboardUser-left col-md-6 pr-0">
                    <div class="col">
                        <div class="dashboardUser-image">
                            <div class="dashboardUser-image position-absolute dashboardHeadAdd dashboard-user-add-icon">
                                <button type="button"  id="btnfile" class="dashboardUser-image-add cursor-pointer"><img src="${commonResourcePath}/images/change-profile-icon.png"/><span id="fname"></span></button>
                                
                                <div class="myAccount-profilImage">
                                    <div class="myAccount-profilImage-img">
                                        <div class="profilePicture js-profilePicture" style="background-image:url(${profilePicture})"></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="dashboardUser-col">
                                <div class="dashboardUser-entry ml-md-3">
                                    <h2 class="clr_gld"><c:out value='${user.company}'/></h2>
                                    <span class="last-login"><spring:theme code="dashboard.license.user.lastlogin.title"/>&nbsp;&nbsp;<span class="clr_gld"><fmt:formatDate value="${customerLastLogon}" pattern="dd/MM/yyyy, hh:mm a"/></span></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dashboardUser-right d-block d-sm-flex col-md-6 px-0 mt-2">
                    <div class="col-12 col-md-6 p-0 px-md-3">
                        <div class="dashboardUser-col flex-column dashboardUser-col-alignment">
                        	<!-- <div class="dashboardUser-label dashboardUser-label-sm"><spring:theme code="general.welcomeback"/></div> -->
                            <div class="account-manager"><h4 class="clr_gld"><spring:theme code="dashboard.license.account.manager.title"/></h4></div>
                            <div class="dashboardUser-label profile-detail dashboardUser-label-xs pt-1"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Profile-name-icon.png"/><span class="dashboardUser-name"><b><c:out value='${user.name}'/></b></span></div>
                            <div class="dashboardUser-label profile-detail pt-1"><img src="${commonResourcePath}/images/dashboard-media/Profile-bar/profile-email-icon.png"/><span class="dashboardUser-value"><c:out value='${user.email}'/></span></div>
                            <div class="dashboardUser-label profile-detail pt-1"><img src="${commonResourcePath}/images/dashboard-media/Profile-bar/profile-mobile-number-icon.png"/><span class="dashboardUser-value"><c:out value='${user.mobileCountryCode}'/>&nbsp;<c:out value='${user.mobileNumber}'/></span></div>
                            <!--  <button class="btn-dashboard text-uppercase">service request</button> -->
                        </div>
                    </div>
                    <div class="col-12 col-md-6 d-flex p-0 user-icons-block">
                        <div class=" user-icon mr-1 mr-sm-3 ">
                            <!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/> -->
                            <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/>
                            </a>
                        </div>
                        <div class=" user-icon mr-1 mr-sm-3">
                            <!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/> -->
                            

                            <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                                <c:if test="${hasLicense or hasAwaitingPayment}">
                                    <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                        <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                        <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/>
                                    </button>
                                </c:if>
                                <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                                <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup">
                                    <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                                    <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                                    <div class="sagiaNavigation-subPane-actions">
                                        <a class="btn btn_slim btn_round btn_outline" href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class=" user-icon mr-1 mr-sm-3">
                            <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>" class="sagiaNavigation-btn sagiaNavigation-user"> 
                            	<img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="globalMessageHolder globalMessage- d-none" id="globalMessageDivHeader" >
    <div class="container">
        <div class="globalMessage">
            <div class="globalMessage-action">
                <a onclick="awaitingPayment()" class="btn btn_round d-flex justify-content-center"><spring:theme code="awaiting.payment.pay" /></a>
            </div>
            <div class="globalMessage-msg">
                <div class="globalMessage-icon"><img class="Applylicense-icon" src="${commonResourcePath}/images/dashboard-media/Apply-license/Allert-icon.png"/></div>
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

<section class="mainSection mainSection_noPadding bg-white ">
    <div class="container">
        <h1 class="dashboard-headline services-headline js-dashboardWidget-headline text-center py-md-5 py-3">
            <spring:theme code="dashboard.license.services.title"/>
        </h1>
        <!-- <div class="dashboardUser_slim dashboardUser_noBorder pb-5">
            <div class="service-card-wrapper">
                <div class="service-card" onclick="location.href='${encodedContextPath}/service-search/FIRST';">
                    <img src="${commonResourcePath}/images/dashboard-media/services/License-Services-Yellow-100x100.png" alt="LICENSING SERVICES" class="text-center"/>
                    <span class="licensing-heading cursor-pointer"><spring:theme code="dashboard.license.service.name"/></span>
                </div>
                <div class="service-card" onclick="location.href='${encodedContextPath}/service-search/GOVERNMENTAL SERVICES';">
                    <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-Yellow-100x100.png" alt="GOVERNMENTAL SERVICES" class="text-center"/>
                    <span class="licensing-heading cursor-pointer"><spring:theme code="dashboard.governmental.service.name"/></span>
                </div>
                <div class="service-card" onclick="location.href='${encodedContextPath}/service-search/SAGIA SERVICES';">
                    <img src="${commonResourcePath}/images/dashboard-media/services/MISA Services.png" alt="MISA SERVICES" class="text-center"/>
                    <span class="licensing-heading cursor-pointer"><spring:theme code="dashboard.misa.service.name"/></span>
                </div>
            </div>
        </div> -->

        <div class="col-xs-12 col-md-12 services-category-list">
            <ul class="nav nav-pills sagiaNavigation-services" id="pills-tab" role="tablist">
                <li class="nav-item col-lg-4 col-md-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link active" id="pills-licensing-tab" data-toggle="pill" href="#licensing" role="tab" aria-controls="pills-licensing" aria-selected="true">
                            <div class="INS_EPM_border_set text-center">
                                <img src="${commonResourcePath}/images/dashboard-media/services/License-Services-Yellow-100x100.png" alt="LICENSING SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/License-Services-blue-100x100.png" alt="LICENSING SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.license.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent0">
                        <div class="tab-pane fade service_tab_pane_show show active pb-5 m-auto" id="licensing1" role="tabpanel" aria-labelledby="pills-licensing-tab">
                            <div class="p-4 dashboard-service-wrapper serviceModule-detail">
                                <div class="content-wrapper">
                                    <p class="INS_letter_set_para pb-3 mb-3">
                                        <spring:theme code="sagia.licenses.catagory.description"/>
                                    </p>
                                    <button class="btn-dashboard text-uppercase" onclick="location.href='${encodedContextPath}/service-search/FIRST';">
                                    	<spring:theme code="dashboard.goto.services.label"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="nav-item col-lg-4 col-md-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-govt-tab" data-toggle="pill" href="#govt-service" role="tab" aria-controls="pills-govt" aria-selected="true">
                            <div class="INS_EPM_border_set text-center">
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-Yellow-100x100.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-blue-100x100.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading">
                                    <spring:theme code="dashboard.governmental.service.name" />
                                </span>
                            </div>
                        </a>
                    </div>
                </li>
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent0">
                        <div class="tab-pane fade service_tab_pane_show pb-5 m-auto" id="govt-service1" role="tabpanel" aria-labelledby="pills-govt-tab">
                            <div class="p-4 dashboard-service-wrapper serviceModule-detail">
                                <div class="content-wrapper">
                                    <p class="INS_letter_set_para pb-3 mb-3">
                                        <spring:theme code="sagia.governmental.catagory.description"/>
                                    </p>
                                    <button class="btn-dashboard text-uppercase" onclick="location.href='${encodedContextPath}/service-search/GOVERNMENTAL SERVICES';">
                                    	<spring:theme code="dashboard.goto.services.label"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="nav-item col-lg-4 col-md-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-misa-tab" data-toggle="pill" href="#sagia-services" role="tab"
                            aria-controls="pills-misa" aria-selected="true">
                            <div class="INS_EPM_border_set text-center">
                                <img src="${commonResourcePath}/images/dashboard-media/services/MISA Services.png" alt="MISA SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/MISA Services-blue-100x100.png" alt="MISA SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading">
                                    <spring:theme code="dashboard.misa.service.name" />
                                </span>
                            </div>
                        </a>
                    </div>
                </li>
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent0">
                        <div class="tab-pane fade service_tab_pane_show pb-5 m-auto" id="sagia-services1" role="tabpanel" aria-labelledby="pills-misa-tab">
                            <div class="p-4 dashboard-service-wrapper serviceModule-detail">
                                <div class="content-wrapper">
                                    <p class="INS_letter_set_para pb-3 mb-3">
                                    	<spring:theme code="sagia.misa.services.catagory.description"/>
                                    </p>
                                    <button class="btn-dashboard text-uppercase" onclick="location.href='${encodedContextPath}/service-search/SAGIA SERVICES';">
                                    	<spring:theme code="dashboard.goto.services.label"/> 
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="tab-content desktop_services services-container-tabcontent" id="pills-tabContent">
                <div class="tab-pane fade show service_tab_pane_show active m-auto" id="licensing" role="tabpanel"
                    aria-labelledby="pills-licensing-tab">
                    <div class="p-4 dashboard-service-wrapper serviceModule-detail my-5 shadow-none">
                        <div class="content-wrapper">
                            <p class="INS_letter_set_para pb-3 mb-3">
                                <spring:theme code="sagia.licenses.catagory.description"/>
                            </p>
                            <button class="btn-dashboard text-uppercase" onclick="location.href='${encodedContextPath}/service-search/FIRST';">
                               <spring:theme code="dashboard.goto.services.label"/>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade show service_tab_pane_show m-auto" id="govt-service" role="tabpanel"
                    aria-labelledby="pills-govt-tab" role="tablist">
                    <div class="p-4 dashboard-service-wrapper serviceModule-detail my-5 shadow-none">
                        <div class="content-wrapper">
                            <p class="INS_letter_set_para pb-3 mb-3">
                              <spring:theme code="sagia.governmental.catagory.description"/>
                            </p>
                            <button class="btn-dashboard text-uppercase" onclick="location.href='${encodedContextPath}/service-search/GOVERNMENTAL SERVICES';">
                            	<spring:theme code="dashboard.goto.services.label"/>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade show service_tab_pane_show m-auto" id="sagia-services" role="tabpanel" aria-labelledby="pills-misa-tab" role="tablist">
                    <div class="p-4 dashboard-service-wrapper serviceModule-detail my-5 shadow-none">
                        <div class="content-wrapper">
                            <p class="INS_letter_set_para pb-3 mb-3">
                               <spring:theme code="sagia.misa.services.catagory.description"/>
                            </p>
                            <button class="btn-dashboard text-uppercase" onclick="location.href='${encodedContextPath}/service-search/SAGIA SERVICES';">
                            	<spring:theme code="dashboard.goto.services.label"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="container mainSection_noPaddingTop js-dashboard  p-0">
    <div class="container row p-0 m-0">
        <div class="Inc-title-header col-12">
            <h1 class="Inc-secdetil-enop-header text-uppercase text-center clr_gld">
               <spring:theme code="sagia.services.licenses.label"/>
            </h1>
		</div>
        <div class="col-12 p-0">
            <a class="float-right text-uppercase mb-3 mb-md-5"></a>
            <!-- <a href="${encodedContextPath}/dashboard-edit" class="btn-dashboard float-right text-uppercase mb-3 mb-md-5 explore-all-btn">
            	<spring:theme code="dashboard.customize"/><img class="pl-3" src="${commonResourcePath}/images/Customize Dashboard-icon.png"/>
            </a> -->
        </div>
    </div>
</section>

<section class="container mainSection mainSection_noPadding bg-white p-0">
    <div class="">
            <div class="container dashboardUser dashboardUser_slim dashboardUser_noBorder p-0">
                <div class="dashboard-tabs">
                    <ul class="nav nav-tabs mb-0 dashboard-licenses-navs" role="tablist" id="tabsDasboard">
                        <li class="nav-item" id="js-myLicense"> <a class="nav-link active" href="#myLicense" role="tab" data-toggle="tab"><spring:theme code="myLicense.title"/></a>
                        </li>
                        <li class="nav-item" id="js-paymentsDetails"><a class="nav-link" href="#payments" role="tab" data-toggle="tab"><spring:theme code="payments.page.title"/></a>
                        </li>
                        <li class="nav-item" id="js-servicesRequest"> <a class="nav-link" href="#servicerequest" role="tab" data-toggle="tab"> <spring:theme code="dashboard.servicesRequest.title"/></a>
                        </li>
                        <li class="nav-item" id="js-incompleRequest"><a class="nav-link" href="#savedDrafts" role="tab" data-toggle="tab"><spring:theme code="dashboard.savedDrafts.title"/></a>
                        </li>
                        <li class="nav-item" id="js-serviceTickets"><a class="nav-link" href="#yourTickets" role="tab" data-toggle="tab"><spring:theme code="dashboard.ticket.yourtickets"/></a>
                        </li>
                    </ul>
                    
                    <!-- Tab panes -->
                    <div class="tab-content dashboard-tab-body license">
                        <div role="tabpanel" class=" tab-pane fade in active show" id="myLicense">
                            <dashboard:myLicense/>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="payments">
                            <div class="dashboardWidget js-dashboardWidget">
                                <div class="dashboardWidget-headline js-dashboardWidget-headline d-none d-sm-block">
                                     <a href="" data-redirect="payments-overview" class="d-block d-md-none js-page-redirect"
                                       style="text-decoration: inherit;color: inherit">
                                        <spring:theme code="payments.page.title"/> 
                                    </a> 
                                    <!-- <div class="dashboardWidget-headline-icon">
                                        <a href="" data-redirect="payments-overview" class="js-page-redirect"><icon:payments/></a>
                                    </div> -->
                                    <div class="dashboardWidget-filter">
                                        <label class="label-dashboardWidget-filter" for="dashboardWidget-filter"><spring:theme code="sagia.sort.sort.by"/></label>
                                        <select id="paymentSort" title="Payments" class="js-select2-oneColumn form-control" onchange="sortPayments()">
                                            <!-- <option value="null" disabled><spring:theme code="sagia.sort.sort.by"/></option> -->
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
                                                    <!-- <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive leftsideshows opacity_gray_color sssss" id="successstories_firstimg">
                                                    <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive leftsideshows" id="successstories_secondimg" style="display: none;"> -->
                                                    <img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive transform-180-degree">
                                                </button>
                                                <div class="paginationModule-items">
                                                    <div class="loadingModule">
                                                        <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                        <div class="loadingModule-msg">Loading content ...</div>
                                                    </div>
                                                </div>
                                                <button class="paginationModule-control paginationModule-control_right">
                                                    <!-- <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive" id="ss_right_arrow"> -->
                                                    <img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive transform-180-degree" id="ss_right_arrow">
                                                </button>
                                            </div>
                                            <div class="tableModule-headline">
                                                <a href="" data-redirect="payments-overview" class="btn-dashboard btn-view-all js-page-redirect">
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
                        <div role="tabpanel" class="tab-pane fade" id="savedDrafts">
                            <dashboard:savedDrafts/>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="yourTickets">
                            <div class="dashboardWidget js-dashboardWidget no-border">
                                <div class="dashboardWidget-headline js-dashboardWidget-headline d-none d-sm-block">
                                    <span>
                                        <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="myTicketsHeader js-page-redirect"
                                           style="text-decoration: inherit;color: inherit">
                                        <spring:theme code="dashboard.ticket.yourtickets"/>
                                        </a>
                                    </span>
                                    <div class="dashboardWidget-headline-icon">
                                        <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"><icon:your-tickets/></a>
                                    </div>
                                    <div class="dashboardWidget-filter">
                                        <label class="label-dashboardWidget-filter" for="dashboardWidget-filter"><spring:theme code="sagia.sort.sort.by"/></label>
                                        <select id="ticketSort" title="tickets" class="js-select2-oneColumn form-control" onchange="sortTickets()">
                                            <!-- <option value="null" disabled><spring:theme code="sagia.sort.sort.by"/></option> -->
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
                                        <div class="tableModule tableModule_slim dashboardWidgetTickets-table overflow-y-hidden">
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
                                        <div class="paginationModule paginationModule_loading  mytickets-view-pagination">
                                            <c:if test="${!pageIsDashboard}">
                                                <div style="width: 150px; position: absolute">
                                                    <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                                </div>
                                            </c:if>
                                            <div class="paginationModule-wrapper">
                                                <button class="paginationModule-control paginationModule-control_left" disabled>
                                                    <img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive transform-180-degree">
                                                </button>
                                                <div class="paginationModule-items">
                                                    <div class="loadingModule">
                                                        <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                        <div class="loadingModule-msg">Loading content ...</div>
                                                    </div>
                                                </div>
                                                <button class="paginationModule-control paginationModule-control_right">
                                                    <img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive transform-180-degree" >
                                                </button>
                                            </div>
                                            <c:if test="${pageIsDashboard}">
                                                <div class="tableModule-headline">
                                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="btn-dashboard btn-view-all js-page-redirect">
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
                    <div class="panel-group" id="accordionDashboard" role="tablist" aria-multiselectable="true"></div>
                </div>
        </div>
    </div>
</section>
<section class="mainSection license mainSection_grey mainSection_noPaddingTop js-dashboard ">
    <div class="container px-0">
        <dashboard:opportunityTickets></dashboard:opportunityTickets>
    </div>
</section>
<section class="mainSection license mainSection_grey mainSection_noPaddingTop js-dashboard ">
    <div class="container px-0">
        <dashboard:financialSurvey></dashboard:financialSurvey>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container px-0">
        <dashboard:sectorAndOpportunity sector="${currentCustomerSector}"></dashboard:sectorAndOpportunity>
    </div>
</section>

<section class="mainSection license mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container px-0">
    <!--News Section Start-->
        <div id="newsandupdates" class="newsAndUpdateContainer">
            <div class="dashboard-container">
                <div class="row titleContainer">
                    <div class="col-md-12 title-heading aos-init aos-animate px-0" data-aos="fade-right" data-aos-delay="100">
                        <h1 class="section-title text-center clr_gld"><spring:theme code="dashboard.license.news.updates.title"/></h1>
                        <!-- <a href="/${language}/mediaCenter/news" class="btn-primary explore-btn explore-gia-btn">Explore All&nbsp; -->
                        <a href="/${language}/mediaCenter/news" class="btn-dashboard float-right text-uppercase mb-3 mb-md-5 explore-all-btn">
                        	<spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/>&nbsp;
                            <img src="/_ui/responsive/common/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive transform-180-degree">
                    	</a>
                    </div>
                </div>
                
                <c:if test="${not empty lastNews}">
	                <div class="row contentWrapper">
		                <c:url value="/mediaCenter/news" var="newsUrl"/>
		                <c:forEach var="currentNews" items="${lastNews}" varStatus="status">
		                    <div class="col-lg-4 col-md-6 card-wrapper aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
	                            <div class="flip-card">
	                                <div class="card-img">
	                                    <img class="img-fluid" src="${fn:escapeXml(currentNews.newsDetailsImage.url)}" alt="">
	                                </div>
	                                <div class="card-box p-3 pr-3 home-news-updates-content">
	                                    <!-- <strong><fmt:formatDate value="${currentNews.newsDate}" pattern="d" />&nbsp;<fmt:formatDate value="${currentNews.newsDate}" pattern="MMMM" /></strong> -->
	                                    <span class="d-news-update-date"><fmt:formatDate value="${currentNews.newsDate}" pattern="d" />&nbsp;<fmt:formatDate value="${currentNews.newsDate}" pattern="MMMM" />
                                            <!-- &nbsp;<fmt:formatDate value="${currentNews.newsDate}" pattern="YY" /> -->
                                        </span>
	                                    <h3 class="d-news-update-content my-3">${fn:substring(currentNews.newsTitle,0,30)} ...</h3>
	                                    <p class="home-news-updates-content-p">${currentNews.newsShortInformation}</p>
	                                    <p><a class="know-more-link" href="${newsUrl}/${currentNews.uid}"><spring:theme code="dashboard.goto.services.label"/>&nbsp;<img class="ml-3 transform-180-degree" src="${commonResourcePath}/images/btn-sector-outline.png"></a></p>
	                                </div>
	                            </div>
	                        </div>
						</c:forEach>
	          		</div>
           		</c:if>
            </div>
	    </div>
    </div>
</section>

<section class="helpSection">
    <div class="container">
        <div class="firstBlock">
            <div class="firstBlock-widget">
                <h1 class="text-center text-uppercase clr_gld">
                    <spring:theme code="dashboard.license.letus.help.you.heading.name"/>
                </h1>
                <span class="firstBlock-text d-contact-help-text">
                   <spring:theme code="dashboard.license.letus.help.you.text"/>
                </span>
            </div>
            <div class="firstBlock-contact d-flex row my-5">
                <div class="firstBlock-contact-local line-after col-md-4">
                    <div>
                        <img  alt="" src="${commonResourcePath}/images/Contact-us/local.png"/>
                    </div>
                    <div>
                        <span class="firstBlock-contact-local-label">
                            <spring:theme code="dashboard.license.letus.help.you.local"/>
                        </span>
                    </div>
                    <div>
                        <span class="firstBlock-contact-local-number local-connect">
                            <spring:theme code="dashboard.license.letus.help.you.local.number"/>
                        </span>
                    </div>
                </div>
                <div class="firstBlock-contact-local line-after col-md-4 mt-3 mt-sm-0">
                    <div>
                        <img  alt="" src="${commonResourcePath}/images/Contact-us/International.png"/>
                    </div>
                    <div>
                        <span class="firstBlock-contact-local-label international-connect">
                            <spring:theme code="dashboard.license.letus.help.you.International"/>
                        </span>
                    </div>
                    <div>
                        <span class="firstBlock-contact-local-number international-connect-number">
                            <spring:theme code="dashboard.license.letus.help.you.International.number"/>
                        </span>
                    </div>
                </div>
                <div class="firstBlock-contact-local col-md-4 mt-3 mt-sm-0">
                    <div>
                        <img  alt="" src="${commonResourcePath}/images/Contact-us/email.png"/>
                    </div>
                    <div class="d-none d-md-block">
                        <span class="firstBlock-contact-local-label">&nbsp;
                           <!-- <spring:theme code="dashboard.license.letus.help.you.International"/> -->
                        </span>
                    </div>
                    <div>
                        <span class="firstBlock-contact-local-number emailBlock d-email-id">
                            InvestorCare@misa.gov.sa
                        </span>
                    </div>
                </div>
            </div>

            <div class="row mb-3 helpSectionBtn">
                <div class="mr-md-5 pb-3 pb-md-0">
                    <button class="btn btn-outline" data-target="#eServiceTour" id="btn-show-me-around" data-toggle="modal" ><spring:theme code="dashboard.license.letus.help.you.emailus.link1.text"></spring:theme></button>
                </div>
                <div class="mr-md-5">
                    <a class="btn make-an-enquiry  d-flex align-items-center justify-content-center" href="${encodedContextPath}/my-sagia/sagia-profile#enquiriesTab" ><spring:theme code="dashboard.license.letus.help.you.emailus.link2.text"></spring:theme></a>
                </div>
            </div>
        </div>
        <!-- <div class="emailBlock">
            <div class="firstBlock-widget">
                <h1 class="text-center text-uppercase clr_gld">
                    <spring:theme code="dashboard.license.letus.help.you.emailus.heading.name"/>
                </h1>
                <span class="firstBlock-text">
                    <   
                </span>
            </div>
            <div class="emailBlock-contact">
                <div class="emailBlock-contact-international">
                    <span class="firstBlock-contact-local-email">
                        <spring:theme code="dashboard.license.letus.help.you.emailus.email"/>
                    </span>
                    <div class="email-buttons">
                        <button class="btn-outline mr-5"><spring:theme code="dashboard.license.letus.help.you.emailus.link1.text"/></button>
                        <button class="btn-dashboard"><spring:theme code="dashboard.license.letus.help.you.emailus.link2.text"/></button>
                    </div>
                </div>
            </div>
        </div> -->
    </div>
</section>

	<!-- <%--<div class="container">
	<div class="row mt-5">
            <div class="col-md-8 ">
                <div class="js-dashboardWidget dashboardWidget_noRadiusRight">
                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
                        <spring:theme code="dashboard.support.title"/>
                        <div class="dashboardWidget-headline-icon"><icon:ask-our-expert/></div>
                    </div>

                    <div class="dashboardWidget-body ">
                        <div class="dashboardWidgetAskOurExpert">
                                    <div class="dashboardWidgetAskOurExpert-headline"><spring:theme code="dashboard.support.helpQuestion"/></div>
                                    <ul class="dashboardWidgetAskOurExpert-list">
                                        <li>
                                            <a href="${encodedContextPath}/my-sagia/sagia-profile" class="dashboardWidgetAskOurExpert-link">
                                                <icon:account-settings/><spring:theme code="dashboard.support.accountSettings"/>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="" data-toggle="modal" data-target="#eServiceTour" class="dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                <icon:first-steps/><spring:theme code="dashboard.support.firstSteps"/>
                                            </a>
                                        </li>

                                            <li>
                                                <a href="#" class="js-realTimeOnlineSupportCall-open dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:call/><spring:theme code="realTimeOnlineSupportCall.title"/>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="j-realTimeOnlineSupport-enquiry dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:contactUs-file/><spring:theme code="realTimeOnlineSupport.enquiry"/>
                                                </a>
                                            </li>

                                             <li>
                                                <a href="#" class="js-realTimeOnlineSupportChatList-open dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:chat/><spring:theme code="realTimeOnlineSupportChatList.title"/>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="js-realtimeOnlineSupportEmailUs dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:contactUs-message/><spring:theme code="realtimeOnlineSupportEmailUs.title"/>
                                                </a>
                                            </li>

                                        <li>
                                            <a href="${encodedContextPath}/my-sagia/license/bidding" class="dashboardWidgetAskOurExpert-link">
                                                <icon:bidding-certificates/><spring:theme code="dashboard.support.biddingCertificates"/>
                                            </a>
                                        </li>
                                        <li>
                                           <a href="${encodedContextPath}/service-search" class="dashboardWidgetAskOurExpert-link">
                                                <icon:services/><spring:theme code="dashboard.support.services"/>
                                            </a>
                                        </li>
                                    </ul>  
                               <div class="col-lg-5 dashboardWidgetAskOurExpert-seperator">
                                    <div class="dashboardWidgetAskOurExpert-headline">
                                        <spring:theme code="dashboard.support.ask"/>
                                    </div>

                                    <ul class="dashboardWidgetAskOurExpert-list dashboardWidgetAskOurExpert-list_oneColumn">
                                        <li><icon:call/><a href="#" id="scheduleCallButton"><spring:theme code="support.schedulecall"/></a></li>
                                        <li><icon:chat/><a href="#" id="liveChatButton"><spring:theme code="support.livechat"/></a></li>
                                        <li><icon:enquiry/><a href="#" id="makeAnEnquiry"><spring:theme code="support.makeenquiry"/></a></li>
                                    </ul>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </div>	--%> -->


<%-- <section class="mainSection license mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container">
        <div class="row">
            <ul id="draggableComponentsList" class="dashboardWidgetList">
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="myLicense">
                        <dashboard:myLicense/>
                    </div>
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
                                    <label class="label-dashboardWidget-filter" for="dashboardWidget-filter"><spring:theme code="sagia.sort.sort.by"/></label>
                                    <select id="paymentSort" title="Payments" class="js-select2-oneColumn form-control" onchange="sortPayments()">
                                        <!-- <option value="null" disabled><spring:theme code="sagia.sort.sort.by"/></option> -->
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
                                                <img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive">
                                            </button>
                                            <div class="paginationModule-items">
                                                <div class="loadingModule">
                                                    <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                    <div class="loadingModule-msg">Loading content ...</div>
                                                </div>
                                            </div>
                                            <button class="paginationModule-control paginationModule-control_right">
                                                <img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive" >
                                            </button>
                                        </div>
                                            <div class="tableModule-headline">
                                                <a href="" data-redirect="payments-overview" class="btn-dashboard js-page-redirect">
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
                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="btn-dashboard js-page-redirect"
                                       style="text-decoration: inherit;color: inherit">
                                    <spring:theme code="dashboard.ticket.yourtickets"/>
                                    </a>
                                </span>
                                <div class="dashboardWidget-headline-icon">
                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"><icon:your-tickets/></a>
                                </div>
                                <div class="dashboardWidget-filter">
                                    <label class="label-dashboardWidget-filter" for="dashboardWidget-filter"><spring:theme code="sagia.sort.sort.by"/></label>
                                    <select id="ticketSort" title="tickets" class="js-select2-oneColumn form-control" onchange="sortTickets()">
                                        <!-- <option value="null" disabled><spring:theme code="sagia.sort.sort.by"/></option> -->
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
                                                <img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive">
                                            </button>
                                            <div class="paginationModule-items">
                                                <div class="loadingModule">
                                                    <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                    <div class="loadingModule-msg">Loading content ...</div>
                                                </div>
                                            </div>
                                            <button class="paginationModule-control paginationModule-control_right">
                                                <img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive">
                                            </button>
                                        </div>
                                        <c:if test="${pageIsDashboard}">
                                            <div class="tableModule-headline">
                                                <a  href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="btn-dashboard js-page-redirect ">
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
                <li class="dashboardWidgetListComponent js-component" style="display: none">
                    <div class="col col-12" id="yourFinancialSurveys">
                        <div class="dashboardWidget js-dashboardWidget">
                            <div class="dashboardWidget-headline js-dashboardWidget-headline">
                                <span>
                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"
                                       style="text-decoration: inherit;color: inherit">
                                    <spring:theme code="dashboard.yourfinancialsurveys"/>
                                    </a>
                                </span>
                                <div class="dashboardWidget-headline-icon">
                                    <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect"><icon:your-tickets/></a>
                                </div>
                            </div>
                            <div class="dashboardWidget-body">
                                <div class="dashboardWidgetTickets">
                                    <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
                                        <table class="tableModule-table">
                                            <thead class="tableModule-head">
                                            <tr>
                                                <th><spring:theme code="dashboard.financialsurveys.quarter"/></th>
                                                <th><spring:theme code="dashboard.financialsurveys.lastupdate"/></th>
                                                <th><spring:theme code="dashboard.financialsurveys.status"/></th>
                                                <th><spring:theme code="dashboard.financialsurveys.connectWithUs"/></th>
                                                <th><spring:theme code="dashboard.financialsurveys.completesurvey"/></th>
                                            </tr>
                                            </thead>
                                            <tbody class="tableModule-body" id="financialSurveysTable"></tbody>
                                        </table>
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
</section> --%>

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
            <div class="d-flex">
                <span class="dashboardWidgetPayments-amount mr-1"></span>
                <span class="dashboardWidgetPayments-currency"></span>
            </div>
            
        </td>
        <td>
            <div class="dashboardWidgetPayments-pay payment-link">
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
            <a href="javascript:void(0)" class="link dashboardWidgetTickets-btn dashboard-detail-text"><spring:theme code="dashboard.ticket.details"/></a>
            <div class="dashboardWidgetTickets-count">1</div>
        </td>
    </tr>
</table>

<table class="financialSurveyTemplateWrapper" style="display:none;">
    <tr>
        <td id="quarter-id" class="quarter"></td>
        <td class="lastUpdate"></td>
        <td><div class="dashboardWidgetTickets-status-open"></div></td>
        <%--<td>
            <a href="javascript:void(0)" class="link dashboardWidgetFinancialSurvey-btn"><spring:theme code="dashboard.financialsurveys.completesurvey"/></a>
        </td>--%>

        <td>
            <a href="javascript:void(0)" class="link dashboardWidgetFinancialSurveys-btn"><spring:theme code="dashboard.financial.survey.connectWithUs"/></a>
        </td>
        <td>
            <div class="dashboardWidgetFinancialSurvey-btn">
                <a href="" class="" style=""></a>
            </div>
        </td>

    </tr>
</table>

<div class="statusIcons" style="display: none">
    <div class="ERROR"><icon:status-cancelled/></div>
    <div class="PENDING"><icon:status-open/></div>
    <div class="DONE"><icon:status-complete/></div>
</div>

<div class="modal fade" id="enquiryDetail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-sm" role="document"></div>
</div>

<payment:paymentModal/>

<script type="application/javascript">
    //var displayTutorial = ${displayTutorial};
    variableEditable = false;
    $(document).ready(function(){
        getAccordionWithLicense("#tabsDasboard",768);
        $(window).resize(function () { getAccordionWithLicense("#tabsDasboard",768);});
    });
</script>
