<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

               
<!-- section content start -->
<section class="sectors-investment">
	<div class="where-to-invest-container">
		<!-- Wrap the rest of the page in another container to center all the content. -->
		<div class="container where-to-invest">
			<div class="row">
				<div class="col-md-12">
					<!-- <h2 class="title-heading">${title}<span>INVEST</span></h2> -->
					<h2 class="title-heading">${title}</span></h2>
					<!-- <div class="sector">sector</div> -->
				 </div>
			 </div>         
		</div>
	</div>
	<div class="Inc-sector-panel portal-sector home-portal-sector">				
		<div class="hexagon-portal">
			<c:forEach var="allCategories" items="${allCategories}">		        	
				<c:if test="${allCategories.value.size() gt 0}"> 
					<c:set var="category" value="${allCategories.value[0]}"/>
					<article class="showinset" data-slide-to=""  data-contentid="">
						<a href="#sector-infomation" class="scrollto">
							<figure>
								<!--<a href="${encodedContextPath}/sectors-opportunities/${category.code}"></a>-->
								<div>
									<img class="sector-item-icon" src="${fn:escapeXml(category.logo.url)}" 
											data-norm="${fn:escapeXml(category.logo.url)}" 
											data-alt="${fn:escapeXml(category.logo.url)}" alt="" loading="lazy"/>
									<h2><c:out value="${category.name}"/></h2>
								</div> 
							</figure>
						</a>
					</article>
				</c:if>
			</c:forEach>   
		</div>
		
		<div class="container" id="sector-infomation">
			<c:forEach var="allCategories" items="${allCategories}">		        	
				<c:if test="${allCategories.value.size() eq 1}"> 
					<c:set var="category" value="${allCategories.value[0]}"/>					
					<div class="sector-box" id="" data-slide-to="" style="display: none;">
						<div class="home-sector-banner" style="background-image: url(${category.banner.url});"></div>
						<div class="row sector-box-inner">
							<div class="col-md-12 col-lg-7 section-title sect-left-panel">
								<h4>${category.name}</h4>
								<p>${category.shortOverview}</p>
								<a href="${encodedContextPath}/sectors-opportunities/${category.code}" class="know-more">
									<spring:theme code="portal.sector.opportunity.know.more.label"/>
									<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
										<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
									</svg>
								</a>
							</div>
							<div class="col-md-12 col-lg-5 section-counts sect-right-panel">
								<c:forEach var="sectorFactsFigures" items="${category.sectorFactsFigures}">  
									<div class="count-item">
										<h5><span class="unit">${sectorFactsFigures.figures}${sectorFactsFigures.unit}</span></h5>
										<p class="description">${sectorFactsFigures.facts}</p>
									</div>  
								</c:forEach>
							</div>
						</div> 
					</div>  	 
				</c:if> 

				<c:if test="${allCategories.value.size() gt 1}"> 
					<c:set var="category" value="${allCategories.value[0]}"/>		
					<c:set var="categorySize" value="${allCategories.value.size()}"/>	
						<div class="sector-box" id="" data-slide-to="" style="display: none;">
							<div class="home-sector-banner" style="background-image: url(${category.banner.url});"></div>
							<div class="row sector-box-inner">
								<div class="col-md-12 col-lg-7 section-title sect-left-panel">
									<h4>${category.name}</h4>
									<p>${category.shortOverview}</p>
									<a href="${encodedContextPath}/sectors-opportunities/${category.code}" class="know-more">
										<spring:theme code="portal.sector.opportunity.know.more.label"/>
										<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
											<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
										</svg>
									</a>
								</div>
								<div class="col-md-12 col-lg-5 section-counts sect-right-panel">
									<c:forEach var="sectorFactsFigures" items="${category.sectorFactsFigures}">  
										<div class="count-item">
											<h5><span class="unit">${sectorFactsFigures.figures}${sectorFactsFigures.unit}</span></h5>
											<p class="description">${sectorFactsFigures.facts}</p>
										</div>  
									</c:forEach>
								</div>
							</div>  

							<%--<div class="sub-sector pb-3">
								<c:forEach var="subCategories" items="${allCategories.value}" begin="1" end="${categorySize}"> 
									<h6>${subCategories.name}</h6>	
									<a href="#" class="sub-sector-img">								
										<img class="sector-item-icon" src="${fn:escapeXml(subCategories.logo.url)}" 
										data-norm="${fn:escapeXml(subCategories.logo.url)}"  
										data-alt="${fn:escapeXml(subCategories.logo.url)}" alt=""/>
									</a>
								</c:forEach>  
                            </div>--%>
							
						</div>  
				</c:if>
			</c:forEach> 
		</div>
		
						
	</div>
</section>
<style>
	.sector-box.active {
		display: block !important;
	} 
</style>
 
