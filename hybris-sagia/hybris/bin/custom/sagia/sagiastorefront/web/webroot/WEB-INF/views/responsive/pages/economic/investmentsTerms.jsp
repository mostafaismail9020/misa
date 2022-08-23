<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/style.css"/>

<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
    	<header:portalPageTitle />
    	
        <!-- Banner start -->
        <cms:pageSlot position="PortalPageMain" var="feature">
            <cms:component component="${feature}" element="div" class=""/>
        </cms:pageSlot>
        <!-- Banner end -->

        <!--  Investments Terms start -->
        <div class="container">

            <div class="sortModule">
                <!--  Insert sort here -->
            </div>

            <div class="tableModule tableModule_slim tableModule_striped">
                <table class="tableModule-table">
                    <thead class="tableModule-head">
                        <tr>
                            <th>#</th>
                            <th>Data point</th>
                            <th>Description</th>
                            <th>Calculation</th>
                            <th>Measurement Unit</th>
                            <th>Source</th>
                        </tr>
                    </thead>
                    <tbody class="tableModule-body">
                        <tr>
                            <td>2.1.1</td>
                            <td>GFCF by type of Capital Asset</td>
                            <td>
                                <a href="#"><div class="tableModule-headline">GFCF provided by Capital Asset type:</div></a>
                                <ul>
                                    <li>Residential and non-residential buidngs</li>
                                    <li>Machinery and equipment</li>
                                    <li>Transport equipment</li>
                                </ul>
                            </td>
                            <td>N/A</td>
                            <td>SAR (current prices)</td>
                            <td>GaStat - directly obtained from GaSat</td>
                        </tr>
                        <tr>
                            <td>2.1.2</td>
                            <td>GFCF by expendture item (Capital Goods)</td>
                            <td>
                                <a href="#"><div class="tableModule-headline">All Capital Goods:</div></a>
                                <ul>
                                    <li>Oil sector</li>
                                    <li>Fovernment sector</li>
                                    <li>Private sector</li>
                                </ul>
                            </td>
                            <td>N/A</td>
                            <td>SAR (current prices)</td>
                            <td>GaStat - directly obtained from GaSat</td>
                        </tr>
                        <tr>
                            <td>2.1.3</td>
                            <td>GFCF to GDP ratio</td>
                            <td>
                                <a href="#"><div class="tableModule-headline">GFCF as percentage of GDP (both indicators in current prices)</div></a>
                            </td>
                            <td>Total GFCF/GDP</td>
                            <td>%</td>
                            <td>Calculation</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="paginationModule">
                <!--  Insert pagination here -->
            </div>
        </div>
        <!--  Investment Terms end -->
    </jsp:body>
</template:portalpage>