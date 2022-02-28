<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="idKey" required="true" type="java.lang.String"%>
<%@ attribute name="labelKey" required="true" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean"%>
<%@ attribute name="labelCSS" required="false" type="java.lang.String"%>
<%@ attribute name="areaCSS" required="false" type="java.lang.String"%>
<%@ attribute name="containerCSS" required="false" type="java.lang.String"%>
<%@ attribute name="placeholder" required="false" type="java.lang.String"%>
<%@ attribute name="disabled" required="false" type="java.lang.Boolean"%>
<%@ attribute name="maxlength" required="false" type="java.lang.Integer"%>
<%@ attribute name="spanClass" required="false" type="java.lang.String"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>

<div class="formTextArea ${containerCSS}">
	<template:errorSpanField path="${path}">
		<c:if test='${empty placeHolderMessage}'>
			<c:set var="placeholderMSG" value="." />
		</c:if>
		<c:if test='${not empty placeHolderMessage}'>
			<c:set var="placeholderMSG" value="${placeHolder}" />
		</c:if>
	
		
		<form:textarea cssClass="form-control ${areaCSS}" id="${idKey}" path="${path}" disabled="${disabled}" placeholder="${placeholderMSG}" maxlength="${maxlength}" />
		<label class="control-label ${labelCSS}" for="${idKey}"> <spring:theme
				code="${labelKey}" /> <c:if
				test="${mandatory != null && mandatory == false}">
				<span>&nbsp;<spring:theme code="login.optional" /></span>
			</c:if> <span class="skip ${spanClass}"><form:errors path="${path}" /></span>
		</label>
		<div class="help-block"></div>
	</template:errorSpanField>
</div>