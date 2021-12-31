<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ attribute name="event" required="true" type="java.lang.String" %>
<%@ attribute name="disabled" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="true" type="java.lang.String" %>
<%@ attribute name="containerCssClass" required="false" type="java.lang.String" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" %>
<%@ attribute name="name" required="false" type="java.lang.String" %>
<%@ attribute name="onchange" required="false" type="java.lang.String" %>
<%@ attribute name="path" required="true" type="java.lang.String" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<div class="form-item ${containerCssClass}">
    <c:choose>
        <c:when test="${disabled == null || disabled != 'disabled'}">
            <form:checkbox id="${id}" path="${path}" class="form-control ${cssClass}" placeholder="." name="${name}" onchange="${onchange}"/>
        </c:when>
        <c:otherwise>
            <form:checkbox id="${id}" path="${path}" class="form-control ${cssClass}" placeholder="." name="${name}" onchange="${onchange}" disabled="true"/>
        </c:otherwise>
    </c:choose>
    <label class="control-label uncased" for="${id}">
        <span><icon:check/></span>
        <c:choose>
            <c:when test="${event == 'REGISTRATION'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-registration"/>
            </c:when>
            <c:when test="${event == 'SPECIAL_SERVICES'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-specialServices"/>
            </c:when>
            <c:when test="${event == 'COMMERCE_INDUSTRY'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-commerceIndustry"/>
            </c:when>
            <c:when test="${event == 'FINANCIAL_STATEMENT'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-financialStatement"/>
            </c:when>
            <c:when test="${event == 'LABOUR'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-labour"/>
            </c:when>
            <c:when test="${event == 'INTERIOR_PASSPORT'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-interiorPassport"/>
            </c:when>
            <c:when test="${event == 'LICENSE_SERVICES'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-licenseServices"/>
            </c:when>
            <c:when test="${event == 'REAL_ESTATE'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-realEstate"/>
            </c:when>
            <c:when test="${event == 'FOLLOW_UP'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-followUp"/>
            </c:when>
            <c:when test="${event == 'GOVERNMENT_DOCUMENTS'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-govDocs"/>
            </c:when>
            <c:when test="${event == 'LEGAL_CONSULTATION'}">
                <c:url var="TandCUrl" value="/cms/sagia-cms-TandC-legalConsultation"/>
            </c:when>
        </c:choose>
        <spring:theme code="register.termsConditions" arguments="${TandCUrl}"/>
    </label>
    <div class="help-block"><form:errors path="${path}"/></div>
</div>
