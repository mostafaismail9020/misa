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
        </c:choose>
        <spring:theme code="register.termsConditions" arguments="${TandCUrl}"/>
    </label>
    <div class="help-block"><form:errors path="${path}"/></div>
</div>
