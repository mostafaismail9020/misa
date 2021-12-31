<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>

<template:portalpage pageTitle="${pageTitle}">

    <jsp:body>
        <header:portalPageTitle />

        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
            <section class="mb-5 general-info mt-0 pt-0">
                <cms:pageSlot position="PortalPageMain" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>
            </section>
        </main>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

    </jsp:body>
</template:portalpage>