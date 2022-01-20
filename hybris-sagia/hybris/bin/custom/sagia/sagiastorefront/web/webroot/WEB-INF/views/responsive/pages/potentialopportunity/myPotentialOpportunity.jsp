<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

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

    <div class="dashboardUser-right col-md-6 px-0">
                        <div class="col-6 d-flex">
                            <div class=" user-icon mr-3">
                                <!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/> -->
                                <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
                                    <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/>
                                </a>
                            </div>
                            <div class=" user-icon mr-3">
                                <!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/> -->
                                <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                                    <c:if test="${hasLicense or hasAwaitingPayment}">
                                        <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                            <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                            <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/>
                                        </button>
                                    </c:if>
                                    <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                                    <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible ">
                                        <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                                        <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                                        <div class="sagiaNavigation-subPane-actions">
                                            <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class=" user-icon mr-1">
                                <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>"
                                class="sagiaNavigation-btn sagiaNavigation-user"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png"/></a>
                            </div>
                        </div>
                      </div>

</br>
                <div class="col-md-3">
                    <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard" /></a>
                </div>
</br>

<h5> INVESTMENT OPPORTUNITY NUMBER :  ${contactTicketDetails.ticketID} </h5>

<h5> ZOOS DEVELOPMENT </h5>

<div class="container service-wrapper service-wrapper-info">
	<div class="serviceModule serviceModule_list mx-5 pt-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> Opportunity Overview </span>
					<c:choose>
						<c:when test="${empty opportunityDetails.description}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${opportunityDetails.description}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> Opportunity Highlights </span>
					<c:choose>
						<c:when test="${empty opportunityDetails.highlights}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${opportunityDetails.highlights}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> Enablers </span>
					<c:choose>
						<c:when test="${empty sagiaService.rulesRestrictions}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.rulesRestrictions}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> Incubators </span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceFinancialFees}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.serviceFinancialFees}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5 pb-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> Partners </span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceDuration}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>${sagiaService.serviceDuration}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>