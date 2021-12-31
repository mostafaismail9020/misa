<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<c:if test="${!hideHeaderLinks}">
<cms:pageSlot position="PortalHeader" var="slotComponent" element="span">
    <cms:component component="${slotComponent}"/>
</cms:pageSlot>
</c:if>

<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
        <%-- <div class="sagiaNavigation js-sagiaNavigation" id="login-Navigation">
            <div class="container">
                <div class="sagiaNavigation_row">
                    <ul class="sagiaNavigation-list ">
                        <li>
                            <c:if test="${hasLicense}">
                                <a href="${dashboardUrl}"><span><spring:message code="header.mySagia.text"/></span></a>
                            </c:if>
                            <c:if test="${!hasLicense}">
                                <a href="${dashboardWithoutLicenceUrl}"><span><spring:message code="header.mySagia.text"/></span></a>
                            </c:if>
                        </li>
                        <c:if test="${hasLicense}">
                            <header:navigation/>
                        </c:if>
                    </ul>
                    <div class="sagiaNavigation-right">
                        <div class="sagiaNavigation-name">
                            <strong>${user.company}</strong> | ${user.name}
                        </div>
                        <div class="sagiaNavigation-entry header-tutorial-header-btn">
                            <a href="javascript:;" data-toggle="modal" data-target="#eServiceTour" class="sagiaNavigation-btn" title="<spring:message code='dashboard.tutorial.modal.button.text'/>">
                                <icon:first-steps />
                            </a>
                        </div>
                        <c:if test="${hasLicense}">
                            <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
                                <icon:calendarDate_stroke/>
                            </a>
                        </c:if>
                        <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                            <c:if test="${hasLicense or hasAwaitingPayment}">
                                <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                    <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span><icon:mail/>
                                </button>
                            </c:if>
                            <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                            <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible">
                                <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                                <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                                <div class="sagiaNavigation-subPane-actions">
                                    <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                                </div>
                            </div>
                        </div>
                        <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>"
                           class="sagiaNavigation-btn sagiaNavigation-user"><icon:person_stroke/></a>
                        <a data-toggle="modal" data-target="#logoutModal" title="<spring:theme code='text.logout'/>" class="sagiaNavigation-btn sagiaNavigation-logout"><icon:logout/></a>
                    </div>
                </div>
            </div>
        </div> --%>
    </sec:authorize>

