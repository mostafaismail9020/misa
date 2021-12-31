<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <c:choose>
        <c:when test="${isCategoryPage}">
            <div class="container">
                <div class="row">
                    <h1 class="heading text-left mb-3 col-md-12">${component.title}</h1>
                </div>
                <div class="page-main-content mt-0">
                    <div style="direction: ltr;" class="row">
                        <c:choose>
                            <c:when test="${not empty featuredOpportunities}">
                                <c:forEach var="featuredOpportunity" items="${featuredOpportunities}" varStatus="status">
                                    <div class="col-lg-4 col-md-6 col-sm-12 my-4">
                                        <a href="${encodedContextPath}${featuredOpportunity.opportunity.url}">
                                            <div class="content-box fearured-opp">
                                                <h2 class="h1 font-bold fearured-opp-headtitle">${featuredOpportunity.opportunity.name}</h2>
                                                <h3 class="fearured-opp-type font-bold"><span class="sector-icon-mini"><img src="${featuredOpportunity.parentCategory.picture.url}" alt="${featuredOpportunity.parentCategory.picture.altText}" /></span>${featuredOpportunity.parentCategory.name}</h3>
                                                <div class="button btn org-button btn-green"><spring:theme code="opportunity.button.text"/></div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                                <div style="direction: ltr;" class="col-md-12 float-rigt text-right explore-more mb-2"><a href="${portal.cmsLinkUrl(component.exploreAllURL)}">${component.exploreAllURL.linkName}</a></div>
                            </c:when>
                            <c:otherwise>
                                <h1 class="diff-color comming-soon mx-auto col-md-12"><spring:theme code="text.opportunity.contact.message"/></h1>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="page-main-content mt-0 mb-0">
                <div class="container">
                    <h2 class="heading text-left mb-3">${component.title}</h2>
                    <div class="row">
                        <c:forEach var="featuredOpportunity" items="${featuredOpportunities}" varStatus="status">
                            <div class="col-lg-4 col-md-6 col-sm-12 my-4">
                                <a href="${encodedContextPath}${featuredOpportunity.opportunity.url}">
                                    <div class="content-box fearured-opp">
                                        <h2 class="h1 font-bold fearured-opp-headtitle">${featuredOpportunity.opportunity.name}</h2>
                                        <h3 class="fearured-opp-type font-bold"><span class="sector-icon-mini"><img src="${featuredOpportunity.parentCategory.picture.url}" alt="${featuredOpportunity.parentCategory.picture.altText}"/></span>${featuredOpportunity.parentCategory.name}</h3>
                                        <div class="button btn org-button btn-green"><spring:theme code="opportunity.button.text"/></div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                        <div class="col-md-12 float-rigt text-right explore-more mb-2"><a href="${portal.cmsLinkUrl(component.exploreAllURL)}">${component.exploreAllURL.linkName}</a></div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</c:if>
