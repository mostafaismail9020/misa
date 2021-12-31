<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">
    <div class="main-banner">
        <cms:pageSlot position="Section1" var="feature">
            <cms:component component="${feature}" />
        </cms:pageSlot>
    </div>

    <div class="row no-margin">
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2A" var="feature" element="div" class="row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2B" var="feature" element="div" class="row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12">
            <cms:pageSlot position="Section2C" var="feature" element="div" class="landingLayout2PageSection2C">
                <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
            </cms:pageSlot>
        </div>
    </div>

    <div class="bars_section mb-4 p-4">
        <div class="container white_bgcolor mb-5">
            <div class="row"> 
                <div class="col-12 col-md-12 p-2 mt-4">   
                    <h3 class="jobsector">
                        Job by Sectors
                    </h3>
                    <div class="text-center pt-4 pb-4">
                        <img src="${commonResourcePath}/images/Group_698.png" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
        <div class="container white_bgcolor mt-5 mb-5">
            <div class="row"> 
                <div class="col-12 col-md-12 p-2 mt-4">   
                    <h3 class="jobsector">
                        Number of Opportunities by sectors
                    </h3>
                    <div class="text-center pt-4 pb-4">
                        <img src="${commonResourcePath}/images/Group_700.png" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
        <div class="container  mt-5 mb-5">
            <div class="row pb-8"> 
                <div class="col-6 col-md-6 pl-0">
                    <div class="white_bgcolor p-2 mt-4"> 
                        <h3 class="jobsector padd25">
                            Top 5 Sectors by Revenue
                        </h3>
                        <div class="text-center p-4 set_heigth">
                            <img src="${commonResourcePath}/images/Group_484.png" class="img-fluid ">
                        </div>
                    </div> 
                </div>
                <div class="col-6 col-md-6 pr-0">  
                    <div class="white_bgcolor p-2 mt-4">  
                        <h3 class="jobsector padd25">
                            Number of Opportunities by sectors
                        </h3>
                        <div class="text-center p-4 set_heigth">
                            <img src="${commonResourcePath}/images/Group_701.png" class="img-fluid">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row"> 
            <cms:pageSlot position="Section3" var="feature" element="div" class="row no-margin product-category-slide-area" >
                <cms:component component="${feature}" element="div" class="no-space yComponentWrapper"/>
            </cms:pageSlot>

            <cms:pageSlot position="Section4" var="feature" element="div" class="row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-6 col-md-3 no-space yComponentWrapper"/>
            </cms:pageSlot>

            <cms:pageSlot position="Section5" var="feature" element="div">
                <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
            </cms:pageSlot>
        </div>
    </div>
    
</template:page>
