<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page pageTitle="${pageTitle}">
	<cms:pageSlot position="InnerPageContentSlot" var="feature">
		<cms:component component="${feature}" element="div" class=""/>
	</cms:pageSlot>
</template:page>
