<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>

<template:portalpage pageTitle="${pageTitle}">

    <jsp:body>
        <!-- <header:productPageTitle /> -->
        <c:choose>
            <c:when test="${productData.productType eq 'OpportunityProduct'}">
                <product:investSaudiOpportunityProduct />
            </c:when>
            <c:otherwise>
                <product:investSaudiSuccessStoryProduct />
            </c:otherwise>
        </c:choose>
		
		<div class="Inc-sector-panel">
			<h1 class="Inc-sector-panel-header"><spring:theme code="portal.sector.explore.other.label"/></h1>								
			<div class="hexagon-portal">
				<c:forEach var="allCategories" items="${mainCategories}">	
					<article>
						<figure>
							<a href="${encodedContextPath}/sectors-opportunities/${allCategories.code}">
								<img class="sector-item-icon" src="${fn:escapeXml(allCategories.logo.url)}" 
										data-norm="${fn:escapeXml(allCategories.logo.url)}" 
										data-alt="${fn:escapeXml(allCategories.logo.url)}" alt=""/>
								<h2><c:out value="${allCategories.name}"/></h2>
							</a>
						</figure>
						
					</article>						
				</c:forEach>
			</div>
		</div>	
		
    </jsp:body>
</template:portalpage>