<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<fmt:parseDate value="${date}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateAppt" type="both" />
<fmt:parseDate value="${timeStart}" pattern="HH:mm" var="parsedTimeAppt" type="time" />

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="appointments.appointmentdetails" />
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

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingBottom">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="appointments.appointmentdetails" /></h1>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius appointmentDetails">
            <div class="contentModule">
                <div class="contentModule-section contentModule-section_noDivider  contentModule-section_noMargin contentModule-section_noPadding">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                        <div class="contentModule-headline"><icon:calendarProfile/><spring:theme code="appointments.personalappointment" /></div>
                        <div class="appointmentDetails-properties">
                            <div class="appointmentDetails-property appointmentList-location">
                                <button class="appointmentList-locationBtn">
                                    <icon:location-pin/>
                                </button>
                                <span class="appointmentList-label">${branch.description}</span>
                            </div>
                            <div class="appointmentDetails-property_delimiter"></div>
                            <div class="appointmentDetails-property appointmentList-status">
                                <span class="appointmentList-status-value appointmentList-icon_after appointmentList-icon_status appointmentList-icon_status_${fn:toLowerCase(appointmentData.statusDescription)}">${appointmentData.statusDescription}</span>
                            </div>
                            <div class="appointmentDetails-property appointmentList-date">
                                <span class="appointmentList-date-month"><fmt:formatDate value="${parsedDateAppt}"
                                                                                         pattern="MMM"
                                                                                         dateStyle="medium"/></span>
                                <span class="appointmentList-date-day"><fmt:formatDate value="${parsedDateAppt}"
                                                                                       pattern="dd"/></span>
                                <span class="appointmentList-date-time"><fmt:formatDate value="${parsedTimeAppt}"
                                                                                        pattern="HH:mm" timeStyle="medium"/></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6 col-md-8">
                            <dl class="dlList">
                                <dt><spring:theme code="appointments.department" /></dt>
                                <dd>${department.description}</dd>
                                <dt><spring:theme code="appointments.branch" /></dt>
                                <dd>${branch.description}</dd>
                            </dl>
                        </div>
                        <div class="col-sm-6 col-md-4">
                            <div class="appointmentDetails-qrCodeImagePlaceholder"></div>
                        </div>
                    </div>


                </div>


                <div class="contentModule-section">
                    <div class="contentModule-headline contentModule-subheadline">Services</div>
                    <hr class="contentModule-section-rowDelimiter"/>
                    <c:if test="${not empty serviceType1}">
                        <div class="row">
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="appointments.service" /></dt>
                                    <dd>${serviceType1}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated dlList_lineThrough">
                                    <dt><spring:theme code="appointments.ministry" /></dt>
                                    <dd>${ministry1}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated dlList_lineThrough">
                                    <dt><spring:theme code="appointments.servicestep" /></dt>
                                    <dd>${service1}</dd>
                                </dl>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty serviceType2}">
                        <div class="row">
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="appointments.service" /></dt>
                                    <dd>${serviceType2}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated dlList_lineThrough">
                                    <dt><spring:theme code="appointments.ministry" /></dt>
                                    <dd>${ministry2}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated dlList_lineThrough">
                                    <dt><spring:theme code="appointments.servicestep" /></dt>
                                    <dd>${service2}</dd>
                                </dl>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty serviceType3}">
                        <div class="row">
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="appointments.service" /></dt>
                                    <dd>${serviceType3}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated dlList_lineThrough">
                                    <dt><spring:theme code="appointments.ministry" /></dt>
                                    <dd>${ministry3}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-4">
                                <dl class="dlList dlList_separated dlList_lineThrough">
                                    <dt><spring:theme code="appointments.servicestep" /></dt>
                                    <dd>${service3}</dd>
                                </dl>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="contentModule-actions contentModule-actions_centered">
                    <button type="reset" class="btn">
                        <spring:theme code="appointments.gobacktoinitialappointment" />
                    </button>
                </div>

                <%--<div class="contentModule-actions contentModule-actions_centered contentModule-actions_noMargin">
                    <button class="btn btn_link">
                        <icon:download/> Download .ics file
                    </button>
                </div>--%>
            </div>


        </div>
    </div>
</div>
<script>
    var appointmentDate = '<fmt:formatDate value="${parsedDateAppt}" pattern="MMM dd, yyyy" dateStyle="medium"/>';
    var appointmentTime = '';
    var appointmentDepartment = '${appointmentData.departmentDescription}';
    var appointmentBranch = '${appointmentData.branchDescription}';
</script>
