<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">
	<%--<div class="row">--%>
	<div class="login-logo">
		<cms:pageSlot position="SiteLogo" var="logo" limit="1">
			<cms:component component="${logo}"/>
		</cms:pageSlot>
	</div>

	<div class="login-wrapper">
		<cms:pageSlot position="LeftContentSlot" var="feature" >
			<cms:component component="${feature}"  />
		</cms:pageSlot>
	</div>
		<%--<div class="col-md-6">--%>
			<%--<cms:pageSlot position="RightContentSlot" var="feature" element="div" class="login-right-content-slot">--%>
				<%--<cms:component component="${feature}"  element="div" class="login-right-content-component"/>--%>
			<%--</cms:pageSlot>--%>
		<%--</div>--%>
	<%--</div>--%>
</template:page>