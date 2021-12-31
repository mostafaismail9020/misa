<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<template:portalpage pageTitle="${pageTitle}" hideHeaderLinks="true" hideFooterLinks="true">
    <jsp:body>
        <header:portalPageTitle />
        
        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
            <cms:pageSlot position="PortalPageMain" var="slotComponent">
                <cms:component component="${slotComponent}"/>
            </cms:pageSlot>                   	       
        </main>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

    </jsp:body>
</template:portalpage>

<%-- 
<template:error pageTitle="${pageTitle}">
	<c:url value="/" var="homePageUrl" />
	<cms:pageSlot position="MiddleContent" var="comp" element="div" class="errorNotFoundPageMiddle">
		<cms:component component="${comp}" element="div" class="errorNotFoundPageMiddle-component" />
	</cms:pageSlot>
	<cms:pageSlot position="BottomContent" var="comp" element="div" class="errorNotFoundPageBottom">
		<cms:component component="${comp}" element="div" class="errorNotFoundPageBottom-component"/>
	</cms:pageSlot>
	<cms:pageSlot position="SideContent" var="feature" element="div" class="errorNotFoundPageSide">
		<cms:component component="${feature}" element="div" class="errorNotFoundPageSide-component"/>
	</cms:pageSlot>

	<div class="errorPage">
        <div class="errorPage-heroImage">
            <icon:attention-error/>
        </div>
        <div class="errorPage-description">
             <h1><spring:theme code="error.pagenotfoundmessage" htmlEscape="false"/></h1>
                  <br/>
                  <br/>
             <h2>${errorMessage}</h2>
        </div>
        <div class="errorPage-actions">
            <div class="errorPage-actions">
                <button type="button" class="btn btn_wide" onclick="location.href='${homePageUrl}';">Return to dashboard</button>
            </div>
        </div>
    </div>
</template:error>
--%>
