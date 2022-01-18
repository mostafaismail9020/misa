<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="sector" required="false" type="com.sap.ibso.eservices.facades.data.SagiaSectorData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="Inc-title-header py-5">
   <h1 class="Inc-secdetil-enop-header text-uppercase text-center clr_gld">
       <c:if test="${language eq 'en'}">
           <span class="clr_gld">${category.name}</span>&nbsp; <spring:theme code="text.dashboard.without.license.sectorAndOpportunities"/>
       </c:if>
       <c:if test="${language eq 'ar'}">
           <span class="clr_gld"> <spring:theme code="text.dashboard.without.license.sectorAndOpportunities"/>&nbsp;${category.name}</span>
       </c:if>
       <a href="/${language}/sectors-opportunities/opportunities" class="btn-dashboard float-right text-uppercase"><spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/> <img class="pl-3"  src="${commonResourcePath}/images/arow_btn.png"/></a>
   </h1>
 </div>
                   
<div class="js-dashboardWidget">
    <%--<div class="dashboardWidget-headline js-dashboardWidget-headline">
       <spring:theme code="text.dashboard.without.license.sectorAndOpportunities"/>
     <div class="dashboardWidget-headline-icon">
         <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="22px" height="24px" viewBox="0 0 22 24" enable-background="new 0 0 22 24" xml:space="preserve">
            <path fill="#32465A" d="M21.707,17L21,17.707l-1-1V23h-2v-4h-4v4h-2v-6.293l-1,1L10.293,17L16,11.293L21.707,17z"></path>
            <path fill="#5CC83B" d="M11,19.121V21H0V0h11l5,5v4.879L8.879,17L11,19.121z M3,8h10V7H3V8z M3,11h10v-1H3V11z M3,14h6v-1H3V14z"></path>
            <path fill="#32465A" d="M11,0l5,5h-5V0z"></path>
         </svg>
      </div>
   </div> --%>
   <div class="dashboardWidget-body">
      <div class="dashboardWidgetNoLicense">
         <div class="dashboardWidgetNoLicense-tabs">
<!--                <ul class="tabs-list"> -->
<!-- 				   <li class="dashboardWidgetNoLicense-current"> -->
<!-- 				      <a> -->
<!-- 				         <span class="dashboardWidgetNoLicense-currentInfo">current tab: </span> -->
<!-- 				         <svg version="1.1" id="Ebene_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="46px" height="60px" viewBox="0 0 46 60" enable-background="new 0 0 46 60" xml:space="preserve"> -->
<!-- 				            <path fill="#FFFFFF" d="M39.573,39.342l5.569,12.342l-9.848,1.451l-7.623,6.443l-3.867-8.534l1.87-4.136		c0.947,0.415,1.623,0.497,2.548,0.279c1.336-0.307,1.967-0.97,3.188-2.726c0.584-0.838,0.852-1.146,1.021-1.229		c0.156-0.08,0.545-0.102,1.56-0.043c2.063,0.111,2.974,0.021,4.044-0.858C38.93,41.596,39.273,40.843,39.573,39.342L39.573,39.342z		 M24.059,46.072l-6.28,13.869l-7.624-6.489l-9.891-1.451l5.71-12.612c0.294,1.461,0.639,2.211,1.53,2.942		c1.035,0.882,1.933,0.974,4.069,0.854c0.983-0.056,1.379-0.03,1.535,0.047c0.17,0.084,0.437,0.394,1.033,1.25		c1.21,1.735,1.84,2.398,3.187,2.709c1.316,0.31,2.132,0.002,3.98-1.025l0.218-0.12c0.706-0.39,1.072-0.534,1.244-0.534		c0.171,0,0.537,0.146,1.244,0.533l0.045,0.025V46.072z"></path> -->
<!-- 				            <g class="normal"> -->
<!-- 				               <path fill="#1C242C" d="M39.573,39.342l5.569,12.342l-9.848,1.451l-7.623,6.443l-3.867-8.534l1.87-4.136			c0.947,0.415,1.623,0.497,2.548,0.279c1.336-0.307,1.967-0.97,3.188-2.726c0.584-0.838,0.852-1.146,1.021-1.229			c0.156-0.08,0.545-0.102,1.56-0.043c2.063,0.111,2.974,0.021,4.044-0.858C38.93,41.596,39.273,40.843,39.573,39.342L39.573,39.342z			 M24.059,46.072l-6.28,13.869l-7.624-6.489l-9.891-1.451l5.71-12.612c0.294,1.461,0.639,2.211,1.53,2.942			c1.035,0.882,1.933,0.974,4.069,0.854c0.983-0.056,1.379-0.03,1.535,0.047c0.17,0.084,0.437,0.394,1.033,1.25			c1.21,1.735,1.84,2.398,3.187,2.709c1.316,0.31,2.132,0.002,3.98-1.025l0.218-0.12c0.706-0.39,1.072-0.534,1.244-0.534			c0.171,0,0.537,0.146,1.244,0.533l0.045,0.025V46.072z"></path> -->
<!-- 				            </g> -->
<!-- 				            <path fill="#5CC83B" d="M45.412,23.73c0-1.679-2.405-3.085-2.859-4.673c-0.363-1.634,1.227-3.993,0.545-5.445		c-0.726-1.498-3.494-1.68-4.492-2.95s-0.545-4.084-1.812-5.127c-1.227-1.044-3.812,0.045-5.266-0.681		c-1.45-0.726-2.223-3.449-3.812-3.812c-1.543-0.363-3.403,1.725-5.037,1.725c-1.634,0-3.494-2.088-5.037-1.725		c-1.588,0.363-2.36,3.086-3.811,3.812c-1.452,0.726-3.993-0.363-5.264,0.68c-1.27,1.044-0.817,3.858-1.815,5.128		s-3.766,1.452-4.492,2.95c-0.68,1.497,0.907,3.811,0.544,5.445c-0.363,1.588-2.768,2.993-2.768,4.673s2.405,3.086,2.859,4.674		c0.363,1.634-1.225,3.993-0.544,5.446c0.726,1.498,3.493,1.678,4.492,2.949c0.998,1.27,0.544,4.083,1.815,5.127		c1.225,1.044,3.811-0.045,5.263,0.68c1.452,0.729,2.224,3.451,3.812,3.812c1.543,0.363,3.403-1.725,5.037-1.725		c1.632,0,3.494,2.088,5.037,1.725c1.588-0.363,2.359-3.085,3.812-3.811c1.451-0.729,3.992,0.36,5.263-0.684s0.817-3.855,1.813-5.127		c1-1.271,3.769-1.449,4.494-2.947c0.681-1.498-0.907-3.812-0.545-5.448C43.007,26.816,45.412,25.409,45.412,23.73z"></path> -->
<!-- 				            <path fill="#FFFFFF" d="M22.725,14.655c-5.037,0-9.075,4.084-9.075,9.075c0,4.992,4.084,9.075,9.075,9.075		c5.037,0,9.075-4.083,9.075-9.075C31.8,18.739,27.762,14.655,22.725,14.655z M22.725,31.241c-4.131,0.017-7.494-3.319-7.51-7.45		c0-0.02,0-0.04,0-0.06c-0.017-4.131,3.318-7.494,7.449-7.511c0.02,0,0.041,0,0.061,0c4.13-0.017,7.494,3.319,7.509,7.45		c0,0.02,0,0.04,0,0.06c0.018,4.131-3.318,7.495-7.449,7.51C22.765,31.24,22.745,31.24,22.725,31.241L22.725,31.241z M22.725,7.395		C13.695,7.395,6.39,14.7,6.39,23.73s7.305,16.336,16.335,16.336c9.03,0,16.335-7.309,16.335-16.336S31.755,7.395,22.725,7.395z		 M22.727,38.273c-8.022,0.01-14.535-6.484-14.545-14.507c0-0.013,0-0.026,0-0.039C8.171,15.705,14.667,9.192,22.689,9.182		c0.013,0,0.025,0,0.038,0c8.023-0.011,14.535,6.483,14.546,14.506c0,0.013,0,0.026,0,0.039c0.011,8.023-6.484,14.535-14.506,14.546		C22.754,38.273,22.74,38.273,22.727,38.273z"></path> -->
<!-- 				         </svg> -->
<%--       					 <spring:theme code="text.dashboard.without.license.currentInvestmentOpportunities"/> --%>
<!-- 				      </a> -->
<!-- 				   </li> -->
<!-- 				</ul> -->
            <div class="dashboardWidgetNoLicense-body">
               <div class="dashboardWidget js-dashboardWidget dashboardWidget_dark no-border">
                <div class="dashboardWidget_inner">
                  <div class="dashboardWidget-headline js-dashboardWidget-headline invest-us-header">
                     ${sector.sectorName} <spring:theme code="text.dashboard.without.license.opportunities"/>
                  </div>
                  <div class="dashboardWidget-body">
                     <div class="dashboardWidgetBanner">
                        <div class="dashboardWidgetBanner-tabs js-dashboardWidgetBanner-tabs ">
                           <div class="dashboardWidgetBanner-tabs-body col-8">
                              <p>${sector.sectorDetails}</p>
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
                                 <div class="d-flex col-4">
                                    <div class="extended-left-block">
                                       <div class="extended-block-data">100%</div>
                                       <div class="extended-block-text">of the population will be covered by the unified digital medical records system by 2025</div>
                                    </div>
                                    <div class="extended-right-block pl-3">
                                       <div class="extended-block-data">$5.4BN</div>
                                       <div class="extended-block-text">medical technology market size in 2021</div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <div class="row">	
                           <c:if test="${sector.sectorCode ne 'Others'}">
                                          <div class="dashboardWidgetBanner-action"><a href="https://investsaudi.sa/en/sectors-opportunities/${sector.sectorCode}" class="btn btn_round btn_outline"><spring:theme code="text.dashboard.without.license.exploreThisOpportunities"/> </a> </div>
                           </c:if>
                           <c:if test="${sector.sectorCode eq 'Others'}">
                                 <div class="dashboardWidgetBanner-action"><a href="https://investsaudi.sa/en/sectors-opportunities/opportunities/" class="btn btn_round btn_outline"><spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/></a></div>
                           </c:if>
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
<section class="Inc-energyoppertunities">
    <div class="container">
        <div class="Inc-title-header">
            <h1 class="Inc-secdetil-enop-header ">
            	<c:if test="${language eq 'en'}">
            		<span class="clr_gld">${customerSectorCategory.name}</span>&nbsp;<spring:theme code="portal.sector.opportunity.label"/>
            	</c:if>
            	<c:if test="${language eq 'ar'}">
            		<span class="clr_gld"><spring:theme code="portal.sector.featured.opportunity.label"/>&nbsp;${customerSectorCategory.name}</span>
            	</c:if>
            </h1>
            <button  class="btn btn-sector-primary responsive-btn-sector">
            	<c:if test="${empty featuredOpportunities}">
            		<a href="${encodedContextPath}/sectors-opportunities/opportunities">Explore all&nbsp;
            		<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
            	</c:if>
            	<c:if test="${not empty featuredOpportunities}">
            		<a href="${encodedContextPath}/sectors-opportunities/${customerSectorCategory.code}">Explore all&nbsp;
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
	                                 	<spring:theme code="portal.sector.opportunity.know.more.label"/>
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
</c:if>