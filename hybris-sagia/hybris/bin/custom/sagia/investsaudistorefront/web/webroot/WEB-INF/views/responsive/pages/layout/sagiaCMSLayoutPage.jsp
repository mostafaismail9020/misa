<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<template:cmspage pageTitle="${pageTitle}">
    <cms:pageSlot position="MCM_CMS_COMMON" var="feature">
        <cms:component component="${feature}" element="div" class=""/>
    </cms:pageSlot>
    <cms:pageSlot position="MCM_CMS_OTHER" var="feature">
        <cms:component component="${feature}" element="div" class=""/>
    </cms:pageSlot>
</template:cmspage>
