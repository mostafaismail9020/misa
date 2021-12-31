<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>

<template:portalpage pageTitle="${pageTitle}">

    <jsp:body>
        <header:portalPageTitle/>
        
        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
            <section id="whyKSA">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-4 col-sm-12 col-xs-3 my-2 tabs_container d-md-block d-none relatedLinks">
                             <cms:pageSlot position="PortalPageSidebar" var="slotComponent" >
                                <cms:component component="${slotComponent}"/>
                            </cms:pageSlot>
                        </div>
                        <div class="col-lg-9 col-md-8 col-sm-12">
                            <div class="umb-grid">
                                <div class="grid-section">
                                    <div >
                                        <div class='container'>
                                            <div class="row clearfix">
                                                <div class="col-md-12 column">
                                                    <div >
                                                         <cms:pageSlot position="PortalPageRight" var="slotComponent">
                                                            <cms:component component="${slotComponent}"/>
                                                        </cms:pageSlot>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

    </jsp:body>
</template:portalpage>









