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

                                                <div class="mainSection mainSection">
                                                    <div class="achievement_header">
                                                        <img class="achievement_header_icon  page-header-image" src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
                                                        <div class="container">
                                                            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                                                                <h1 data-aos="fade-up">
                                                                    <spring:theme code="appointments.appointmentoverview" />
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

                                                <!-- <div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image1.jpg" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="">
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="appointments.appointmentoverview"/>
                </h1>
            </div>
            <div class="profile-icons float-right">
                <div class=""><img alt="" class="profile-icon-images" src="${commonResourcePath}/images/eservices/Calender-active.png"/>
                    <img alt="" class="profile-icon-images d-none" src="${commonResourcePath}/images/esrvices/Calender-in-active.png"/>
                </div>
                <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub mr-5">
                    <c:if test="${hasLicense or hasAwaitingPayment}">
                        <button class="sagiaNavigation-btn js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                            <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                            <img alt="" class="" src="${commonResourcePath}/images/eservices/message-in-active.png" />
                            <img alt="" class="d-none" src="${commonResourcePath}/images/eservices/Calender-active-1.png" />
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
                <div class=""><img class="profile-icon-images" alt="" src="${commonResourcePath}/images/eservices/Profile-in-active.png" /> </div>
            </div>
        </div>
    </div>
</div> -->

                                                <div class="mainSection mainSection mainSection_noPaddingBottom mt-5  pb-5">
                                                    <!-- Appointment Overview -->
                                                    <div class="container">
                                                        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
                                                            <div>
                                                                <a href="${encodedContextPath}/appointments/create" class="btn-dashboard btn_bold text-uppercase pl-5 pr-5">
                                                                    <spring:theme code="appointments.makeanappointment" />
                                                                </a>
                                                            </div>

                                                            <form action="${encodedContextPath}/appointments">
                                                                <div class="searchInputBox searchInputBox_limited">
                                                                    <input name="searchParameter" id="searchParameter" class="searchInputBox-input" type="text" placeholder="" value="${searchParameter}" />
                                                                    <button class="btn-search searchInputBox-switchModeBtn no-background" type="submit">Search</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="mainSection mainSection mainSection_xsmallPaddingTop">
                                                    <div class="container">
                                                        <%--<c:set var="listLimit" value="${5}"/>--%>
                                                            <%--<c:if test="${listLimit < 1 || listLimit > fn:length(appointments)}">--%>
                                                                <%--<c:set var="listLimit" value="${fn:length(appointments)}"/>--%>
                                                                    <%--</c:if>--%>
                                                                        <c:set var="detailsDemoItem" value="${appointments}[0]" />
                                                                        <div class="panelModule mt-3">
                                                                            <div class="contentModule-actions d-block">
                                                                                <h2 class="clr_gld text-center text-uppercase">
                                                                                    <spring:theme code="appointments.appointmentoverview" /> </h2>
                                                                            </div>
                                                                            <div class="contentModule-filter">
                                                                                <select id="appointmentSort" class="js-select2-oneColumn form-control" onchange="sortAppointments()">
                        <option value="null"><spring:theme code="sagia.sort.sort.by"/></option>
                        <option value="status_asc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                        <option value="status_desc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                        <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                        <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                        <option value="branch_asc" data-sort="asc"><spring:theme code="sagia.sort.branch"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                        <option value="branch_desc" data-sort="desc"><spring:theme code="sagia.sort.branch"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                    </select>
                                                                            </div>
                                                                            <%--<c:set var="listEntryClass" value="appointmentList-entry"/>--%>
                                                                                <div class="tableModule">
                                                                                    <table class="tableModule-table appointmentList appointmentList_hasTopBoder" id="appointmentList">
                                                                                        <tbody>
                                                                                            <c:forEach items="${appointments}" var="item" varStatus="countme">
                                                                                                <c:set var="statusClass" value="appointmentList-icon_status" />

                                                                                                <c:if test="${not empty item.statusDescription}">
                                                                                                    <c:set var="stringSplit" value="${fn:split(item.statusDescription, ' ')}" />
                                                                                                    <c:set var="statusClass" value="appointmentList-icon_status appointmentList-icon_status_${fn:toLowerCase(stringSplit[0])}" />
                                                                                                </c:if>
                                                                                                <%--<c:if test="${countme.count > listLimit}">--%>
                                                                                                    <%--<c:set var="listEntryClass" value="appointmentList-entry appointmentList-entry_hidden"/>--%>
                                                                                                        <%--</c:if>--%>
                                                                                                            <fmt:parseDate value="${item.date}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateAppt" type="both" />
                                                                                                            <fmt:parseDate value="${item.timeStart}" pattern="HH:mm" var="parsedTimeAppt" type="time" />
                                                                                                            <%--<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />--%>
                                                                                                                <tr class="appointmentList-entry">
                                                                                                                    <td class="appointmentList-date" data-date="<fmt:formatDate value='${parsedDateAppt}' pattern='yyyy-MM-dd' />" data-time="" data-duration="">
                                                                                                                        <span class="appointmentList-date-month"><fmt:formatDate value="${parsedDateAppt}"
                                                                                             pattern="MMM"
                                                                                             dateStyle="medium"/></span>
                                                                                                                        <span class="appointmentList-date-day"><fmt:formatDate value="${parsedDateAppt}"
                                                                                           pattern="dd"/></span>
                                                                                                                    </td>
                                                                                                                    <td class="appointmentList-time">
                                                                                                                        <span class="appointmentList-date-time"><fmt:formatDate value="${parsedTimeAppt}"
                                                       pattern="HH:mm" timeStyle="medium"/></span>
                                                                                                                    </td>
                                                                                                                    <td data-displayname="${item.service1Description}">
                                                                                                                        <div class="appointmentList-title">${item.service1Description}</div>
                                                                                                                        <div class="appointmentList-subTitle">${item.branchDescription}</div>
                                                                                                                    </td>
                                                                                                                    <td data-location="Moscow, Russia">
                                                                                                                        <button class="btn_link appointmentList-locationBtn">
                                        <icon:location-pin/>
                                    </button>
                                                                                                                        <span class="appointmentList-label">${item.branchDescription}</span>
                                                                                                                    </td>
                                                                                                                    <td class="appointmentList-status" data-type="${item.serviceType1Description}" data-state="${item.statusDescription}"><span class="appointmentList-label"><spring:theme code="appointments.status"/></span>
                                                                                                                        <button class="appointmentList-status-value appointmentList-icon_after ${statusClass}">
                                        <span>${item.statusDescription}</span></button>
                                                                                                                    </td>
                                                                                                                    <td class="appointmentList-view">
                                                                                                                        <c:choose>
                                                                                                                            <c:when test="${item.status eq 'E0012'}">
                                                                                                                                <a href="${encodedContextPath}/appointments/details/${item.appointmentID}" class="btn_link appointmentList-viewBtn appointmentList-icon_view">
                                                                                                                                    <span><spring:theme code="appointments.appointmentdetails.view"/></span>
                                                                                                                                </a>
                                                                                                                            </c:when>
                                                                                                                            <c:when test="${item.status eq 'E0001'}">
                                                                                                                                <span style="color: red;"><spring:theme code="appointment.pending.approval"/></span>
                                                                                                                            </c:when>
                                                                                                                            <c:otherwise>
                                                                                                                                <span style="color: green;">${item.statusDescription}</span>
                                                                                                                            </c:otherwise>
                                                                                                                        </c:choose>
                                                                                                                        <%-- <c:if test="${item.statusDescription ne 'Open'}">
	                                   <a href="${encodedContextPath}/appointments/details/${item.appointmentID}"
	                                       class="btn btn_link appointmentList-viewBtn appointmentList-icon_view">
	                                        <span><spring:theme code="appointments.appointmentdetails.view"/></span>
	                                        <icon:view/>
	                                    </a>
                                    </c:if> --%>
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                            </c:forEach>
                                                                                        </tbody>
                                                                                    </table>
                                                                                </div>

                                                                                <div class="paginationModule">
                                                                                    <div class="paginationModule-wrapper">
                                                                                        <button class="paginationModule-control paginationModule-control_left" disabled>
                                                                                            <!-- <img src="${commonResourcePath}/images/arrow-left.svg" class="img-responsive leftsideshows opacity_gray_color sssss" id="successstories_firstimg">
                                                                                            <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive leftsideshows" id="successstories_secondimg" style="display: none;"> -->
                                                                                            <img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive">
                                                                                        </button>

                                                                                        <div class="paginationModule-items">
                                                                                            <div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link appointment active">0</a></div>
                                                                                            <c:forEach begin="1" end="${appointmentsPageNumber}" varStatus="loop">
                                                                                                <div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link appointment">${loop.index}</a></div>
                                                                                            </c:forEach>
                                                                                        </div>

                                                                                        <button class="paginationModule-control paginationModule-control_right">
                                                                                            <img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive" id="ss_right_arrow">
                                                                                                        <!-- <img src="${commonResourcePath}/images/arrow-left.svg" class="img-responsive" id="ss_right_arrow" style="transform: rotate(180deg);"> -->
                                                                                        </button>
                                                                                    </div>
                                                                                </div>

                                                                                <%--<p>${listLimit} / ${fn:length(appointments)}</p>--%>
                                                                        </div>
                                                                        <div class="appointmentControl-calendar">
                                                                            <div class="js-calendar" id="appointmentCalendar"></div>
                                                                        </div>
                                                    </div>

                                                    <!-- deprecated Create Modal, code can be removed -->

                                                    <!-- Appointment Create Modal -->
                                                    <div class="modal fade" id="newAppointmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="container">
                                                                        <%--<form:form method="POST" action="${encodedContextPath}/appointments/appointment?site=sagia" commandName="appointmentModel">--%>
                                                                            <form:form method="GET" action="${encodedContextPath}/appointments?site=sagia" modelAttribute="appointmentModel">
                                                                                <div class="appointmentDetails appointmentDetails_forms">
                                                                                    <h2 class="headline headline-icon headline-icon_details">
                                                                                        <spring:theme code="appointments.appointmentdetails" />
                                                                                    </h2>
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-md-6">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="department" cssClass="js-select2-oneColumn form-control">
                                                                                                        <option></option>
                                                                                                        <form:options items="${departments}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="department"><spring:theme code="appointments.department"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-xs-8 col-md-6">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="branch" cssClass="js-select2-oneColumn form-control">
                                                                                                        <option></option>
                                                                                                        <form:options items="${branches}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="branch"><spring:theme code="appointments.branch"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <hr class="contentModule-section-delimiter" />
                                                                                    <h2 class="headline">Services</h2>
                                                                                    <div class="row service-selection">
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="servType1" cssClass="js-select2-oneColumn service-type">
                                                                                                        <option></option>
                                                                                                        <form:options items="${serviceTypes}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="servType1">
                                                        <spring:theme code="appointments.servicetype"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="ministry1" cssClass="js-select2-oneColumn ministry" disabled="true">
                                                                                                        <option></option>
                                                                                                        <form:options items="${ministries}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="ministry1">
                                                        <spring:theme code="appointments.ministry"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="service1" cssClass="js-select2-oneColumn service" disabled="true">
                                                                                                        <option></option>
                                                                                                        <form:options items="${services}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="service1">
                                                        <spring:theme code="appointments.servicetype"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <hr class="contentModule-section-rowDelimiter" />
                                                                                    <div class="row service-selection">
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="servType2" cssClass="js-select2-oneColumn service-type">
                                                                                                        <option></option>
                                                                                                        <form:options items="${serviceTypes}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="servType2">
                                                        <spring:theme code="appointments.servicetype"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="ministry2" cssClass="js-select2-oneColumn ministry" disabled="true">
                                                                                                        <option></option>
                                                                                                        <form:options items="${ministries}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="ministry2">
                                                        <spring:theme code="appointments.ministry"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">

                                                                                                    <form:select path="service2" cssClass="js-select2-oneColumn service" disabled="true">
                                                                                                        <option></option>
                                                                                                        <form:options items="${services}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="service2">
                                                        <spring:theme code="appointments.enquirytype"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <hr class="contentModule-section-rowDelimiter" />
                                                                                    <div class="row service-selection">
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="servType3" cssClass="js-select2-oneColumn service-type">
                                                                                                        <option></option>
                                                                                                        <form:options items="${serviceTypes}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="servType3"><spring:theme code="appointments.servicetype"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="ministry3" cssClass="js-select2-oneColumn ministry" disabled="true">
                                                                                                        <option></option>
                                                                                                        <form:options items="${ministries}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="ministry3"><spring:theme code="appointments.ministry"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                            <div class="formSelectBox">
                                                                                                <div class="form-group">
                                                                                                    <form:select path="service3" cssClass="js-select2-oneColumn service" disabled="true">
                                                                                                        <option></option>
                                                                                                        <form:options items="${services}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                                    </form:select>
                                                                                                    <label class="control-label" for="service1"><spring:theme code="appointments.enquirytype"/></label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <%--<div class="appointmentDetails-row_add row text-center">
                                            <button class="btn btn_plain">+ Add Row</button>
                                        </div>--%>
                                                                                        <hr class="contentModule-section-delimiter" />
                                                                                        <h2 class="headline">
                                                                                            <spring:theme code="register.national.investor.availableslots" />
                                                                                        </h2>
                                                                                        <div class="row">
                                                                                            <div class="col-xs-8 col-md-6">
                                                                                                <div class="formInputBox formInputBox_group ">
                                                                                                    <div class="form-group">
                                                                                                        <input id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date" placeholder="." value="" type="text">
                                                                                                        <label class="control-label" for="profile.enquiry.type">
                                                        <spring:theme code="appointments.from"/>
                                                    </label>
                                                                                                        <div class="formInputBox-append">
                                                                                                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="col-xs-8 col-md-6">
                                                                                                <div class="formInputBox formInputBox_group ">
                                                                                                    <div class="form-group">
                                                                                                        <input id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date" placeholder="." value="" type="text">
                                                                                                        <label class="control-label" for="profile.enquiry.type">
                                                        <spring:theme code="appointments.to"/>
                                                    </label>
                                                                                                        <div class="formInputBox-append">
                                                                                                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="appointmentControl">
                                                                                            <%--<div class="appointmentControl_left">
                                                <button class="btn btn_link appointmentControl-backBtn"><svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="currentColor" stroke-width="4" stroke-miterlimit="10" fill="none" d="M2 1.922l12 12M2 13.922l12-12"/></svg>Back to Overview</button>
                                            </div>--%>
                                                                                                <div class="appointmentControl_right">
                                                                                                    <button class="btn btn--primary btn--half-radius btn--small"><spring:theme code="appointments.makeanappointment"/>
                                            </button>
                                                                                                </div>
                                                                                        </div>
                                                                                </div>
                                                                            </form:form>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <%--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- end deprecated Create Modal -->

                                                </div>

                                                <table class="appointmentTemplateWrapper" style="display: none">
                                                    <tr class="appointmentList-entry">
                                                        <td class="appointmentList-date" data-date="" data-time="" data-duration="">
                                                            <span class="appointmentList-date-month"></span>
                                                            <span class="appointmentList-date-day"></span>
                                                        </td>
                                                        <td class="appointmentList-time">
                                                            <span class="appointmentList-date-time"></span>
                                                        </td>
                                                        <td class="displayName" data-displayname="">
                                                            <div class="appointmentList-title"></div>
                                                            <div class="appointmentList-subTitle"></div>
                                                        </td>
                                                        <td data-location="Moscow, Russia">
                                                            <button class="btn btn_link appointmentList-locationBtn">
                <icon:location-pin/>
            </button>
                                                            <span class="appointmentList-label branch"></span>
                                                        </td>
                                                        <td class="appointmentList-status" data-type="" data-state=""><span class="appointmentList-label"><spring:theme code="appointments.status"/></span>
                                                            <button class="appointmentList-status-value appointmentList-icon_after ">
                <span></span></button>
                                                        </td>
                                                        <td class="appointmentList-view">
                                                            <a href="${encodedContextPath}/appointments/details/" class="btn btn_link appointmentList-viewBtn appointmentList-icon_view">
                                                                <span><spring:theme code="appointments.viewDetails"/></span>
                                                                <!-- <icon:view/>-->
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </table>

                                                <script>
                                                    var appointmentsJson = ${appointmentsJson};                                                       
                                                    
                                                    var formOptionsJSON = ${formOptionsJSON};
                                                                                                          
                                                    var notificationsJson = ${notificationsJson};
                                                                                                           
                                                    var calendarLabelsJson = ${calendarLabelsJson};
                                                </script>