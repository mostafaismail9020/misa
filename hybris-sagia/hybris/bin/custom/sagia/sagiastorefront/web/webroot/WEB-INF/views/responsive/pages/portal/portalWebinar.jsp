<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header " %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:portalpage pageTitle="${pageTitle}">

			<jsp:attribute name="pageCss">

			</jsp:attribute>

    <jsp:body>
	<!--
	<script>
    document.querySelector("html").style.cssText = 'background:#000!important';
    document.querySelector("body").style.cssText += 'opacity:0!important;display:block!important;';
    window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
    window['_sfw_script'] = 'sf_ab.min.js?v=1.0.4';
    window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
    (function(s,e,ss,i,o,n){
    if(s.console && s.console.log) { s.console.log(i);};
    o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
    n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
    })(window,document,'script','SessionForward Loaded.');
    </script>
	-->

        <main>
            <section class="w-100 hero-banner">

                <figure>
                    <cms:pageSlot position="PortalBanner" var="slotComponent">
                        <cms:component component="${slotComponent}"/>
                    </cms:pageSlot>
                </figure>
            </section>
            <section class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3 text-center">
                        <div class="border-square mb-5 launchDate py-3 d-none">
                            Launches on December 3, 1:00-2:00 PM KSA(GMT +3)
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 body-contents">
                        <div id="corFormThankyou" class="mt-2 mb-4 mx-4 alert alert-success text-center d-none">
                            <spring:theme code="text.registration.success"/>
                        </div>
                        <div id="corFormAlready" class="mt-2 mb-4 mx-4 alert alert-warning text-center d-none">
                            <spring:theme code="text.registration.fail"/>
                        </div>
                        <div id="corFormError" class="mt-2 mb-4 mx-4 alert alert-danger text-center d-none">
                            <spring:theme code="text.registration.error"/>
                        </div>
                    </div>


                    <div class="umb-grid">
                        <div class="grid-section">
                            <div class="two-col-lay">
                                <div class='container'>
                                    <div class="row clearfix">
                                        <div class="col-md-6 column">
                                            <div>
                                                <cms:pageSlot position="PortalDescription" var="slotComponent">
                                                    <cms:component component="${slotComponent}"/>
                                                </cms:pageSlot>
                                            </div>
                                        </div>
                                        <div class="col-md-6 column">
                                            <div>
                                                <cms:pageSlot position="PortalEmail" var="slotComponent">
                                                    <cms:component component="${slotComponent}"/>
                                                </cms:pageSlot>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class='container'>
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <cms:pageSlot position="PortalWebinarFooter" var="slotComponent">
                                            <cms:component component="${slotComponent}"/>
                                        </cms:pageSlot>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <div class="cookie-desclaimer d-none"></div>
        </main>
        <footer>

        </footer>
    </jsp:body>
</template:portalpage>