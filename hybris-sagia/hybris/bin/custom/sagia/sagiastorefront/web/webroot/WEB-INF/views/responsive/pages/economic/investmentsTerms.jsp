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
<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/bootstrap-table.min.css"/>

<script type="text/javascript" src="${themeResourcePath}/js/jquery-3.3.1.min.js${version}"></script>
<script type="text/javascript" src="${themeResourcePath}/js/jquery.dataTables.min.js${version}"></script>
<script type="text/javascript" src="${themeResourcePath}/js/sagia.investmentTerms.js"></script>


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
                <table>
                    <tr class="investmentsTermsTemplate">
                        <td><strong></strong></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <table id="investmentsTermsTableId" class="tableModule-table">
                        <thead class="tableModule-head">
                        <tr>
                            <th>#</th>
                            <th><spring:theme code="economic.investmentsterms.page.table.datapoint"/></th>
                            <th><spring:theme code="economic.investmentsterms.page.table.description"/></th>
                            <th><spring:theme code="economic.investmentsterms.page.table.calculation"/></th>
                            <th><spring:theme code="economic.investmentsterms.page.table.measurement"/></th>
                            <th><spring:theme code="economic.investmentsterms.page.table.source"/></th>
                        </tr>
                        </thead>
                        <tbody id="investmentsTermsId" class="tableModule-body"></tbody>
                    </table>
            </div>

            <div class="paginationModule">
                <!--  Insert pagination here -->
            </div>
        </div>
        <!--  Investment Terms end -->
    </jsp:body>
</template:portalpage>