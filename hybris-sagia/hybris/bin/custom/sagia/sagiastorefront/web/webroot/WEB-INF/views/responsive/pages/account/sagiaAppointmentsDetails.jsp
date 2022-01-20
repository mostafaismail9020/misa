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
<spring:url value="/appointments/appointment-info/" var="infoPath" htmlEscape="false"/>
<fmt:parseDate value="${appointmentData.date}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateAppt" type="both" />
<fmt:parseDate value="${appointmentData.timeStart}" pattern="HH:mm" var="parsedTimeAppt" type="time" />

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

<!-- <div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="appointments.appointmentdetails" /></h1>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">

        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div>
                <a href="${encodedContextPath}/appointments"
                   class="btn btn_leftIconLink btn_darkLink appointmentControl-backBtn"><span class="iconElement iconElement_closeBack"><icon:close/></span><span
                        class="appointmentControl-backBtn-label"><spring:theme code="appointments.backtooverview" /></span></a>
            </div>
            <%-- <a href="${encodedContextPath}/appointments/edit/${appointmentData.appointmentID}" class="btn btn--primary btn--half-radius btn--small"><spring:theme code="appointments.edit" /></a> --%>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16">
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
                                <span class="appointmentList-label">${appointmentData.branchDescription}</span>
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
                                <dd>${appointmentData.departmentDescription}</dd>
                                <dt><spring:theme code="appointments.branch" /></dt>
                                <dd>${appointmentData.branchDescription}</dd>
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
                    <c:if test="${not empty appointmentData.serviceType1Description}">
                        <div class="row">
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
                        <div class="row">
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
                        <div class="row">
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
                    <a href="${encodedContextPath}/attachment/appointment-file/${appointmentData.appointmentID}" target="_blank" class="btn">
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
