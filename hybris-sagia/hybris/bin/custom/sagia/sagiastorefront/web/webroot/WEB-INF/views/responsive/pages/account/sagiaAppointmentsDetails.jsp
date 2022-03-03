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
<spring:url value="/appointments/appointment-info/" var="infoPath" htmlEscape="false" />
<fmt:parseDate value="${appointmentData.date}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateAppt" type="both" />
<fmt:parseDate value="${appointmentData.timeStart}" pattern="HH:mm" var="parsedTimeAppt" type="time" />

                                                <div class="mainSection mainSection">
                                                    <div class="achievement_header">
                                                        <img class="achievement_header_icon  page-header-image" src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
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
            <h1 class="mainSection-headline"><spring:theme code="appointments.appointmentdetails" /></h1>
        </div>
    </div>
</div> -->

                                                <div class="mainSection mainSection_dark mainSection_noPadding">
                                                    <div class="container">

                                                        <div class="mainSection-linkActions mainSection-linkAc  tions_spaceBetween">
                                                            <div class="w-25">
                                                                <a href="${encodedContextPath}/appointments" class="btn btn_leftIconLink btn_bold appointmentControl-backBtn w-75">
                                                                    <span class="iconElement iconElement_closeBack">
																		<!--<icon:close/>-->
                                                                        <svg class="potential_svg_arrow" xmlns="http://www.w3.org/2000/svg" width="10" height="17.116" viewBox="0 0 10 17.116">
																			<path id="Icon_ionic-ios-arrow-back" data-name="Icon ionic-ios-arrow-back" d="M14.265,14.749l6.618-6.471a1.2,1.2,0,0,0,0-1.727,1.275,1.275,0,0,0-1.77,0l-7.5,7.332a1.2,1.2,0,0,0-.036,1.687l7.53,7.383a1.277,1.277,0,0,0,1.77,0,1.2,1.2,0,0,0,0-1.727Z" transform="translate(-11.251 -6.194)" fill="#00a6be"></path>
																		</svg>
																	</span>
                                                                    <span class="appointmentControl-backBtn-label btn_bold"><spring:theme code="appointments.backtooverview" /></span></a>
                                                            </div>
                                                            <%-- <a href="${encodedContextPath}/appointments/edit/${appointmentData.appointmentID}" class="btn btn--primary btn--half-radius btn--small"><spring:theme code="appointments.edit" /></a> --%>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="mainSection mainSection_dark mainSection_pdt16">
                                                    <div class="container">
                                                        <div class="panelModule panelModule_halfRadius appointmentDetails mt-4">
                                                            <div class="contentModule">
                                                                <div class="contentModule-section contentModule-section_noDivider  contentModule-section_noMargin contentModule-section_noPadding">
                                                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator border-0 mb-2">
                                                                        <div class="personal-appointment-title w-100 text-center pb-2 mb-4 text-uppercase">
                                                                            <!--    <icon:calendarProfile/>-->
                                                                            <spring:theme code="appointments.personalappointment" />
                                                                        </div>
                                                                        <div class="appointmentDetails-properties w-100 pb-2">
                                                                            <div class="appointmentDetails-property appointmentList-date pl-5">
                                                                                <svg xmlns="http://www.w3.org/2000/svg" width="29" height="32" viewBox="0 0 29 32">
                                                                                    <g id="Icon_feather-calendar" data-name="Icon feather-calendar" transform="translate(-3.5 -2)">
                                                                                      <path id="Path_2150" data-name="Path 2150" d="M7.5,6h21a3,3,0,0,1,3,3V30a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3V9a3,3,0,0,1,3-3Z" fill="none" stroke="#BF9B2E" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
                                                                                      <path id="Path_2151" data-name="Path 2151" d="M24,3V9" fill="none" stroke="#BF9B2E" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
                                                                                      <path id="Path_2152" data-name="Path 2152" d="M12,3V9" fill="none" stroke="#BF9B2E" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
                                                                                      <path id="Path_2153" data-name="Path 2153" d="M4.5,15h27" fill="none" stroke="#BF9B2E" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
                                                                                    </g>
                                                                                  </svg>
                                                                                <span class="appointmentList-date-month"><fmt:formatDate value="${parsedDateAppt}"
                                                                                         pattern="MMM"
                                                                                         dateStyle="medium"/>
                                                                                        </span>
                                                                                <span class="appointmentList-date-day"><fmt:formatDate value="${parsedDateAppt}"
                                                                                       pattern="dd"/></span>
                                                                                <span class="appointmentList-date-time"><fmt:formatDate value="${parsedTimeAppt}"
                                                                                        pattern="HH:mm" timeStyle="medium"/></span>
                                                                            </div>
                                                                            <div class="appointmentDetails-property appointmentList-location pl-5">
                                                                                <button class="appointmentList-locationBtn">
                                    <icon:location-pin/>
                                </button>
                                                                                <span class="appointmentList-label">${appointmentData.branchDescription}</span>
                                                                            </div>
                                                                            <div class="appointmentDetails-property_delimiter"></div>
                                                                            <div class="appointmentDetails-property appointmentList-status">
                                                                                <span class="appointmentList-status-value appointmentList-icon_after appointmentList-icon_status appointmentList-icon_status_${fn:toLowerCase(appointmentData.statusDescription)}">${appointmentData.statusDescription}</span>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="row">
                                                                        <div class="col-sm-6 col-md-8">
                                                                            <dl class="dlList border-0">
                                                                                <div class="w-100">
                                                                                    <dt><spring:theme code="appointments.department" />&nbsp;:</dt> 
                                                                                    <dd>${appointmentData.departmentDescription}</dd>
                                                                                </div>
                                                                                <div class="w-100">
                                                                                    <dt><spring:theme code="appointments.branch" />&nbsp;:</dt>
                                                                                    <dd>${appointmentData.branchDescription}</dd>
                                                                                </div>
                                                                            </dl>
                                                                        </div>
                                                                        <div class="col-sm-6 col-md-4">
                                                                            <div class="appointmentDetails-qrCodeImagePlaceholder"></div>
                                                                        </div>
                                                                    </div>


                                                                </div>


                                                                <div class="contentModule-section service-main-div">
                                                                    <div class="contentModule-headline contentModule-subheadline mw0">Services</div>
                                                                    <hr class="hr" />
                                                                    <c:if test="${not empty appointmentData.serviceType1Description}">
                                                                        <div class="row service-hr-line">
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated">
                                                                                    <dt><spring:theme code="appointments.service" /></dt>
                                                                                    <dd>${appointmentData.serviceType1Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated dlList_lineThrough">
                                                                                    <dt><spring:theme code="appointments.ministry" /></dt>
                                                                                    <dd>${appointmentData.ministry1Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated dlList_lineThrough">
                                                                                    <dt><spring:theme code="appointments.servicestep" /></dt>
                                                                                    <dd>${appointmentData.service1Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${not empty appointmentData.serviceType2Description}">
                                                                        <div class="row service-hr-line mt-3">
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated">
                                                                                    <dt><spring:theme code="appointments.service" /></dt>
                                                                                    <dd>${appointmentData.serviceType2Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated dlList_lineThrough">
                                                                                    <dt><spring:theme code="appointments.ministry" /></dt>
                                                                                    <dd>${appointmentData.ministry2Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated dlList_lineThrough">
                                                                                    <dt><spring:theme code="appointments.servicestep" /></dt>
                                                                                    <dd>${appointmentData.service2Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${not empty appointmentData.serviceType3Description}">
                                                                        <div class="row service-hr-line mt-3">
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated">
                                                                                    <dt><spring:theme code="appointments.service" /></dt>
                                                                                    <dd>${appointmentData.serviceType3Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated dlList_lineThrough">
                                                                                    <dt><spring:theme code="appointments.ministry" /></dt>
                                                                                    <dd>${appointmentData.ministry3Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                            <div class="col-sm-4">
                                                                                <dl class="dlList dlList_separated dlList_lineThrough">
                                                                                    <dt><spring:theme code="appointments.servicestep" /></dt>
                                                                                    <dd>${appointmentData.service3Description}</dd>
                                                                                </dl>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </div>
                                                                <div class="contentModule-actions contentModule-actions_centered">
                                                                    <a href="${encodedContextPath}/attachment/appointment-file/${appointmentData.appointmentID}" target="_blank" class="btn btn-normal btn_slim w-25 print_btn">
                                                                        <spring:theme code="appointments.print" />
                                                                    </a>
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
    var formOptionsJSON = [];
    var appointmentDate = '<fmt:formatDate value="${parsedDateAppt}" pattern="MMM dd, yyyy" dateStyle="medium"/>';
    var appointmentDate2 = '<fmt:formatDate value="${parsedDateAppt}" pattern="yyyy-MM-dd'T'HH:mm:ss" dateStyle="medium"/>';
    var appointmentTime = '';
    var appointmentDepartment = '${appointmentData.departmentDescription}';
    var appointmentBranch = '${appointmentData.branchDescription}';
    var appointmentData = ${appointmentDataJSON};
    var infoPath = window.location.protocol + '//' + window.location.host + '${infoPath}';
    infoPath += appointmentData.department + '/';
    infoPath += appointmentData.branch + '/';
    infoPath += appointmentDate2 + '/';
    infoPath += appointmentData.ministry1 != '' ? appointmentData.ministry1 + ':' : '-' + ':';
    infoPath += appointmentData.ministry2 != '' ? appointmentData.ministry2 + ':' : '-' + ':';
    infoPath += appointmentData.ministry3 != '' ? appointmentData.ministry3 + '/' : '-' + '/';
    infoPath += appointmentData.serviceType1 != '' ? appointmentData.serviceType1 + ':' : '-' + ':';
    infoPath += appointmentData.serviceType2 != '' ? appointmentData.serviceType2 + ':' : '-' + ':';
    infoPath += appointmentData.serviceType3 != '' ? appointmentData.serviceType3 + '/' : '-' + '/';
    infoPath += appointmentData.service1 != '' ? appointmentData.service1 + ':' : '-' + ':';
    infoPath += appointmentData.service2 != '' ? appointmentData.service2 + ':' : '-' + ':';
    infoPath += appointmentData.service3 != '' ? appointmentData.service3 + '/': '-' + '/';
</script>
