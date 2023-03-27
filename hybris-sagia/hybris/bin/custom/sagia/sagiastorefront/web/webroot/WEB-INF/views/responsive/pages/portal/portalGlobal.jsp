<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>

<template:portalpage pageTitle="${pageTitle}">

    <jsp:body>
	
	<script>
		document.querySelector("html").style.background = 'rgb(0, 0, 0)';
		document.querySelector("body").style.display = 'none';
		window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
		window['_sfw_script'] = 'sf_ab.min.js?v=1.0.3';
		window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
		(function(s,e,ss,i,o,n){
		if(s.console && s.console.log) { s.console.log(i);};
		o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
		n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
		})(window,document,'script','SessionForward Loaded.');
	</script>
	
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