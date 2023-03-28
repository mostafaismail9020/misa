<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
        <!-- <header:productPageTitle /> -->
        <c:choose>
            <c:when test="${productData.productType eq 'OpportunityProduct'}">
                <product:investSaudiOpportunityProduct />
            </c:when>
            <c:otherwise>
                <product:investSaudiSuccessStoryProduct />
            </c:otherwise>
        </c:choose>
	
		<c:if test="${not empty productData.productReferences}">
			<section class="Inc-energyoppertunities">
			    <div class="container">
			        <div class="Inc-title-header">
			            <h1 class="Inc-secdetil-enop-header ">
			            	<span class="clr_gld"><spring:theme code="portal.similar.opportunities.text"/>&nbsp;</span>			            	
			            </h1>			            
			            <c:url value="/sectors-opportunities/opportunities/?q=&sectorIds=" var="exploreAllUrl"/>
			            <button  class="btn btn-sector-primary responsive-btn-sector">
			            	<a href="${exploreAllUrl}${categoryCode}"><spring:theme code="portal.exploreall.button.text"/>&nbsp;
			            		<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive">
			            		<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/know-more.png" alt="">
			            	</a>
			            </button>
			        </div>			
				
					<div class="page-main-content mt-0">
			           <div class="row">                                   
			                <c:forEach items="${productData.productReferences}" var="references">
			                    <div class="col-lg-4 col-md-6 col-sm-12 my-4">
			                        <a href="${encodedContextPath}${references.target.url}">
			                            <div class=" Inc-fearured-opp">
			                                <h2 class="Inc-fearured-opp-headtitle" title="${references.target.name}">${references.target.name}</h2>
			                                <h3 class="Inc-fearured-opp-type">${references.target.parentCategory}</h3>
			                                <button class="btn btn-sector-primary mx-auto">
			                                	<spring:theme code="portal.opportunity.know.more.button"/>
			                                	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/know-more.png" alt="">
			                                </button>
			                                <button class="btn btn-sector-outline mx-auto">
			                                	<spring:theme code="portal.opportunity.iam.interested.button"/>
			                                	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/btn-sector-outline.png" alt="">
			                                </button>	                               
			                            </div>
			                        </a>
			                    </div>
							</c:forEach>
						</div>
					</div>									
				</div>
			</section>
		</c:if>
		
		<c:if test="${not empty productData.partnerMap}">
			<div class="Inc-sector-panel">
				<div class="container py-5">
						<div class="">
						<c:forEach var="partnerMap" items="${productData.partnerMap}">
							<c:if test="${partnerMap.value.size() gt 0}">
								<div class="mt-5">
									<h1 class="clr_gld text-center text-uppercase pb-3">${partnerMap.key}</h1>
									<div class="d-flex justify-content-center">
										<c:forEach var="partnerLogo" items="${partnerMap.value}">
											<div class="col-4">
												<div class="product-card text-center">
													<img class="sector-item-icon pb-3" src="${fn:escapeXml(partnerLogo.companyLogo.url)}" 
													data-norm="${fn:escapeXml(partnerLogo.companyLogo.url)}" 
													data-alt="${fn:escapeXml(partnerLogo.companyLogo.url)}" alt=""/>
													<a href="${partnerLogo.companyWebsite}" target="_blank">know more - visit website
													</a>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
				
		<div class="Inc-sector-panel">
			<h1 class="Inc-sector-panel-header"><spring:theme code="portal.sector.explore.other.label"/></h1>								
			<div class="hexagon-portal">
				<c:forEach var="allCategories" items="${mainCategories}">	
					<article>
						<figure>
							<a href="${encodedContextPath}/sectors-opportunities/${allCategories.code}">
								<img class="sector-item-icon" src="${fn:escapeXml(allCategories.logo.url)}" 
										data-norm="${fn:escapeXml(allCategories.logo.url)}" 
										data-alt="${fn:escapeXml(allCategories.logo.url)}" alt="" loading="lazy"/>
								<h2><c:out value="${allCategories.name}"/></h2>
							</a>
						</figure>						
					</article>						
				</c:forEach>
			</div>
		</div>	
		
    </jsp:body>
</template:portalpage>
