<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:portalpage pageTitle="${pageTitle}">

    <jsp:body>
        <header:productPageTitle />
        <c:choose>
            <c:when test="${productData.productType eq 'OpportunityProduct'}">
                <product:investSaudiOpportunityProduct />
            </c:when>
            <c:otherwise>
                <product:investSaudiSuccessStoryProduct />
            </c:otherwise>
        </c:choose>

    </jsp:body>
</template:portalpage>