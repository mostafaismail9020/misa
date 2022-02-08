<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="sector" required="false" type="com.sap.ibso.eservices.facades.data.SagiaSectorData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="Inc-title-header py-5 Inc-title-header-mobile">
   	<h1 class="Inc-secdetil-enop-header text-uppercase text-center clr_gld">
    	<c:if test="${language eq 'en'}">
        	<span class="clr_gld">${category.name}</span>&nbsp; <spring:theme code="text.dashboard.without.license.sectorAndOpportunities"/>
       	</c:if>
       	<c:if test="${language eq 'ar'}">
           <spring:theme code="text.dashboard.without.license.sectorAndOpportunities"/><span class="clr_gld">&nbsp;${category.name}</span>
       	</c:if>
   	</h1>
	<a href="/${language}/sectors-opportunities/opportunities" class="btn-dashboard text-uppercase  explore-all-btn">
		<spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/><img class="pl-3 transform-180-degree" src="${commonResourcePath}/images/arow_btn.png"/>
	</a>	
</div>

                   
<div class="js-dashboardWidget">
	<div class="dashboardWidget-body">
    	<div class="dashboardWidgetNoLicense">
        	<div class="dashboardWidgetNoLicense-tabs">
            	<div class="dashboardWidgetNoLicense-body">
               		<div class="dashboardWidget js-dashboardWidget dashboardWidget_dark no-border">
                  		<div class="dashboardWidget_inner noLicenseBody">
                     		<div class="dashboardWidget-body">                        
                        		<div class="dashboardWidgetBanner">
	                           		<div class="dashboardWidgetBanner-tabs js-dashboardWidgetBanner-tabs ">
	                           			<div class="dashboardWidgetBanner-tabs-body col-12">
	                              			<div class="dashboardWidget-headline js-dashboardWidget-headline invest-us-header clr_gld">
                                 				<!-- ${sector.sectorName} <spring:theme code="text.dashboard.without.license.opportunities"/> -->
                              				</div>
											  <c:if test="${not empty customerSectorCategory}">
		                                            <p class="invest-with-us-description">${customerSectorCategory.overview} </p>
		                                       	</c:if>
		                                    <!-- <p class="invest-with-us-description">${sector.sectorDetails}</p> -->
		                                    <div class="row m-auto justify-content-center">	
		                                       	<c:if test="${sector.sectorCode ne 'Others'}">
		                                   			<div class="dashboardWidgetBanner-action">
		                                   				<a href="https://investsaudi.sa/en/sectors-opportunities/${sector.sectorCode}" class="btn-dashboard">
		                                   					<spring:theme code="text.dashboard.without.license.exploreThisOpportunities"/>
		                                   					<img class="pl-3 transform-180-degree"  src="${commonResourcePath}/images/arow_btn.png"/>
		                                   				</a>
		                                   			</div>
		                                       	</c:if>
		                                       	<c:if test="${sector.sectorCode eq 'Others'}">
		                                            <div class="dashboardWidgetBanner-action">
		                                             	<a href="https://investsaudi.sa/en/sectors-opportunities/opportunities/" class="btn btn_round btn_outline">
		                                             		<spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/>
		                                             	</a>
		                                        	</div>
		                                       	</c:if>
		                                 	</div>
		                           		</div>
		                                <c:if test="${not empty customerSectorCategory && not empty customerSectorCategory.sectorFactsFigures}">
		                                 	<div class="col-md-12 col-lg-5 section-counts sect-right-panel">
		                                       	<c:forEach var="sectorFactsFigures" items="${customerSectorCategory.sectorFactsFigures}">  
		                                        	<div class="count-item">
		                                             	<h5><span class="unit">${sectorFactsFigures.figures}${sectorFactsFigures.unit}</span></h5>
		                                             	<p class="description">${sectorFactsFigures.facts}</p>
		                                          	</div>  
		                                       	</c:forEach>
		                                    </div>
		                				</c:if>                              
                           			</div>
		                           <%--<div class="row">	
		                              <c:if test="${sector.sectorCode ne 'Others'}">
		                                     <div class="dashboardWidgetBanner-action">
		                                     <a href="https://investsaudi.sa/en/sectors-opportunities/${sector.sectorCode}" class="btn btn_round btn_outline">
		                                     <spring:theme code="text.dashboard.without.license.exploreThisOpportunities"/> </a> </div>
		                              </c:if>
		                              <c:if test="${sector.sectorCode eq 'Others'}">
		                                    <div class="dashboardWidgetBanner-action">
		                                    <a href="https://investsaudi.sa/en/sectors-opportunities/opportunities/" class="btn btn_round btn_outline">
		                                    <spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/></a></div>
		                              </c:if>
		                           </div>--%>
                        		</div>
                     		</div>
                 		</div>
              		</div>
            	</div>
         	</div>
      	</div>
   	</div>
</div>

<c:if test="${not empty customerSectorCategory}">
	<section class="Inc-energyoppertunities ">
	    <div class="container">
	        <div class="Inc-title-header py-3 py-md-5">
	            <h1 class="Inc-secdetil-enop-header text-uppercase clr_gld col-12 mt-3 text-center">
	            	<c:if test="${language eq 'en'}">
	            		${customerSectorCategory.name} &nbsp; <spring:theme code="portal.sector.opportunity.label"/>
	            	</c:if>
	            	<c:if test="${language eq 'ar'}">
	            		<spring:theme code="portal.sector.featured.opportunity.label"/>&nbsp;${customerSectorCategory.name}
	            	</c:if>
	            </h1>	            
	        </div>
			<button  class="btn-dashboard responsive-btn-sector col-2 dashboard-invest-btn">
				<c:if test="${empty featuredOpportunities}">
					<a class="text-white text-uppercase" href="${encodedContextPath}/sectors-opportunities/opportunities" >
						<spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/>&nbsp;
						<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive transform-180-degree">
					</a>
				</c:if>
				<c:if test="${not empty featuredOpportunities}">
					<a class="text-white text-uppercase" href="${encodedContextPath}/sectors-opportunities/${customerSectorCategory.code}">
						<spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/>&nbsp;
						<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive transform-180-degree">
					</a>
				</c:if>
				<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/know-more.png" alt="">
			</button>
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
		                         <!-- <a href="${encodedContextPath}${featuredOpportunity.opportunity.url}"> -->
		                             <div class=" Inc-fearured-opp d-opportunities">
		                                 <h2 class="Inc-fearured-opp-headtitle" title="${featuredOpportunity.opportunity.name}">${featuredOpportunity.opportunity.name}</h2>
		                                 <h3 class="Inc-fearured-opp-type">${featuredOpportunity.parentCategory.name}</h3>
		                                 <a href="${encodedContextPath}${featuredOpportunity.opportunity.url}" class="m-auto">
											<button class="btn btn-sector-primary text-uppercase mx-auto">
												<spring:theme code="portal.sector.opportunity.know.more.label"/>
												<img class="img-fluid arrow-icon" src="/_ui/responsive/common/images/know-more.png" alt="">
											</button>
										</a>
										<a href="${encodedContextPath}${featuredOpportunity.opportunity.url}/?scrollTo=contact" class="m-auto">
		                                 <button class="btn btn-sector-outline mx-auto">
		                                 	<spring:theme code="portal.opportunity.iam.interested.button"/>
		                                 	<img class="img-fluid arrow-icon" src="/_ui/responsive/common/images/btn-sector-outline.png" alt="">
		                                 </button>
										</a>
		                                 <!-- <span class="Inc-sector-icon-mini">
		                                 	<img class="img-fluid" src="${featuredOpportunity.parentCategory.picture.url}" 
		                                 			alt="${featuredOpportunity.parentCategory.picture.altText}" />
		                                 </span> -->
		                             </div>
		                         <!-- </a> -->
		                     </div>
		                 </c:forEach>                                       
		            </div>
		        </div>
	        </c:if>
	    </div>
	</section>
</c:if>
