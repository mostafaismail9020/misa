<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="idKey" required="true" type="java.lang.String"%>
<%@ attribute name="labelKey" required="true" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean"%>
<%@ attribute name="labelCSS" required="false" type="java.lang.String"%>
<%@ attribute name="areaCSS" required="false" type="java.lang.String"%>
<%@ attribute name="placeholder" required="false" type="java.lang.String"%>
<%@ attribute name="placeholderkey" required="false" type="java.lang.String"%>
<%@ attribute name="disabled" required="false" type="java.lang.Boolean"%>
<%@ attribute name="maxlength" required="false" type="java.lang.Integer"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>

<template:errorSpanField path="${path}">

	<label class="${labelCSS}" for="${idKey}"> <spring:theme
			code="${labelKey}" /> <c:if
			test="${mandatory != null && mandatory == false}">
			<span>&nbsp;<spring:theme code="login.optional" /></span>
		</c:if> <span class="skip"><form:errors path="${path}" /></span>
	</label>
    <spring:theme code="${placeholderkey}"  text="${placeholder}" var="placeHolderMessage" />
	<form:textarea cssClass="${areaCSS}" id="${idKey}" path="${path}" disabled="${disabled}" placeholder="${placeHolderMessage}"
				   maxlength="${maxlength}"/>


</template:errorSpanField>
