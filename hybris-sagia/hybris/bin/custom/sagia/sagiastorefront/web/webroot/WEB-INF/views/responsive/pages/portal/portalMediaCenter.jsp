<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
	
        <header:portalPageTitle />

        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}" />
        </cms:pageSlot>

        <cms:pageSlot position="PortalPageMain" var="slotComponent">
            <cms:component component="${slotComponent}" />
        </cms:pageSlot>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
            <cms:component component="${slotComponent}" />
        </cms:pageSlot>

    </jsp:body>
</template:portalpage>
