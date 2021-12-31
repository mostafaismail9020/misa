<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="Inc-energyoppertunities">
    <div class="container">
        <div class="Inc-title-header">
            <h1 class="Inc-secdetil-enop-header ">
            	<c:if test="${language eq 'en'}">
            		<span class="clr_gld">${category.name}</span>&nbsp;<spring:theme code="portal.sector.opportunity.label"/>
            	</c:if>
            	<c:if test="${language eq 'ar'}">
            		<span class="clr_gld"><spring:theme code="portal.sector.featured.opportunity.label"/>&nbsp;${category.name}</span>
            	</c:if>
            </h1>
            <button  class="btn btn-sector-primary responsive-btn-sector">
            	<c:if test="${empty featuredOpportunities}">
            		<a href="/${language}/sectors-opportunities/opportunities">${component.exploreAllURL.linkName}&nbsp;
            		<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
            	</c:if>
            	<c:if test="${not empty featuredOpportunities}">
            		<a href="${portal.cmsLinkUrl(component.exploreAllURL)}${category.code}">${component.exploreAllURL.linkName}&nbsp;
            		<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
            	</c:if>
            	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/know-more.png" alt="">
            </button>
        </div>
        <c:if test="${empty featuredOpportunities}">
        	<div class="page-main-content mt-0 text-center">
	            <div class="no-opportunities-text"><spring:theme code="portal.sector.opportunity.not.available.label"/></div>
			</div>
        </c:if>
        <c:if test="${not empty featuredOpportunities}">
	        <div class="page-main-content mt-0">
	            <div class="row">                                   
	                 <c:forEach var="featuredOpportunity" items="${featuredOpportunities}" varStatus="status">
	                     <div class="col-lg-4 col-md-6 col-sm-12 my-4">
	                         <a href="${encodedContextPath}${featuredOpportunity.opportunity.url}">
	                             <div class=" Inc-fearured-opp">
	                                 <h2 class="Inc-fearured-opp-headtitle" title="${featuredOpportunity.opportunity.name}">${featuredOpportunity.opportunity.name}</h2>
	                                 <h3 class="Inc-fearured-opp-type">${featuredOpportunity.parentCategory.name}</h3>
	                                 <button class="btn btn-sector-primary mx-auto">
	                                 	<spring:theme code="portal.opportunity.know.more.button"/>
	                                 	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/know-more.png" alt="">
	                                 </button>
	                                 <button class="btn btn-sector-outline mx-auto">
	                                 	<spring:theme code="portal.opportunity.iam.interested.button"/>
	                                 	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/btn-sector-outline.png" alt="">
	                                 </button>
	                                 <!-- <span class="Inc-sector-icon-mini">
	                                 	<img class="img-fluid" src="${featuredOpportunity.parentCategory.picture.url}" 
	                                 			alt="${featuredOpportunity.parentCategory.picture.altText}" />
	                                 </span> -->
	                             </div>
	                         </a>
	                     </div>
	                 </c:forEach>                                       
	            </div>
	        </div>
        </c:if>
    </div>
</section>
        
