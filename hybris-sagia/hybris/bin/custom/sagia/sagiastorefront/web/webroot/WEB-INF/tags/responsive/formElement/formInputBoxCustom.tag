<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="idKey" required="true" type="java.lang.String"%>
<%@ attribute name="labelKey" required="true" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean"%>
<%@ attribute name="labelCSS" required="false" type="java.lang.String"%>
<%@ attribute name="inputCSS" required="false" type="java.lang.String"%>
<%@ attribute name="inputBoxCSS" required="false" type="java.lang.String"%>
<%@ attribute name="placeholder" required="false" type="java.lang.String"%>
<%@ attribute name="tabindex" required="false" rtexprvalue="true"%>
<%@ attribute name="autocomplete" required="false" type="java.lang.String"%>
<%@ attribute name="disabled" required="false" type="java.lang.Boolean"%>
<%@ attribute name="readonly" required="false" type="java.lang.Boolean"%>
<%@ attribute name="maxlength" required="false" type="java.lang.Integer"%>
<%@ attribute name="helpBlockSuccessCSS" required="false" type="java.lang.String"%>
<%@ attribute name="inputBoxAppendKey" required="false" type="java.lang.String"%>
<%@ attribute name="inputBoxCode" required="false" type="java.lang.String"%>
<%@ attribute name="dataValue" required="false" type="java.lang.String"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="formInputBox ${inputBoxCSS}">
	<template:errorSpanFieldCustom path="${path}">
		<ycommerce:testId code="LoginPage_Item_${idKey}">
			<spring:theme code="${placeholder}" var="placeHolderMessage" />
			<c:if test='${empty placeHolderMessage}'>
				<c:set var="placeholder" value="." />
			</c:if>
			<c:if test='${not empty placeHolderMessage}'>
				<c:set var="placeholder" value="${placeHolderMessage}" />
			</c:if>
			<c:choose>
				<c:when test="${not empty dataValue}">
					<form:input cssClass="${inputCSS} form-control" id="${idKey}" path="${path}"
								tabindex="${tabindex}" autocomplete="${autocomplete}" placeholder="${placeholder}"
								disabled="${disabled}" maxlength="${maxlength}" readonly="${readonly}" data-value="${dataValue}"/>
				</c:when>
				<c:otherwise>
					<form:input cssClass="${inputCSS} form-control" id="${idKey}" path="${path}"
								tabindex="${tabindex}" autocomplete="${autocomplete}" placeholder="${placeholder}"
								disabled="${disabled}" maxlength="${maxlength}" readonly="${readonly}"/>
				</c:otherwise>
			</c:choose>

			<label class="control-label ${labelCSS}" for="${idKey}">
				<spring:theme code="${labelKey}" />
				<c:if test="${mandatory != null && mandatory == false}">
					<span>&nbsp;<spring:theme code="login.optional" /></span>
				</c:if>
			</label>
			<c:if test="${not empty inputBoxAppendKey || not empty inputBoxCode}">
				<div class="formInputBox-append">
					<span class="formInputBox-text"><spring:theme code="${inputBoxAppendKey}"/>${inputBoxCode}</span>
				</div>
			</c:if>
			<div class="success-message-block ${helpBlockSuccessCSS}"></div>
		</ycommerce:testId>
	</template:errorSpanFieldCustom>
	<div class="help-block"></div>
</div>

