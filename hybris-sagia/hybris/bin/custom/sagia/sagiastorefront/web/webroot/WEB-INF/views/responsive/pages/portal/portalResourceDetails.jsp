<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<template:portalpage pageTitle="${pageTitle}">
  	<jsp:body>
		<header:portalPageTitle />

		<cms:pageSlot position="PortalPageTop" var="slotComponent">
	    	<cms:component component="${slotComponent}"/>
		</cms:pageSlot>

        <section class="page-details-banner">
          	<img class="banner-image" src="${resourceDetails.resourceDetailsImage.url}" alt="${resourceDetails.resourceTitle}">  
          	<div class="page-details-banner-container" data-aos="fade-up">
              	<div class="container page-details-banner-item">
              		<div class="row">
              			<div class="col-md-7">
                  			<div class="event-date">
		                  		<div class="event-num"><fmt:formatDate value="${resourceDetails.resourceDate}" pattern="d" /></div>
		                  		<div class="event-mon"><fmt:formatDate value="${resourceDetails.resourceDate}" pattern="MMMM yyyy" /></div>
		                  	</div>
			                <h2>${resourceDetails.resourceTitle}</h2>
			                
			                <c:url value="/mediaCenter/downloadResoruce/${resourceDetails.uid}" var="resourcedownloadURL"/>
			                <a href="${resourcedownloadURL}" class="btn-get-started ">
			                	<img class="mx-2" src="${commonResourcePath}/images/news_page/download.png" alt=""><spring:theme code="portal.media.resourcedetails.download" text = "Download"/>
			                </a>
			               
			                <!-- <a href="" class="btn btn-share"><img class="mx-2" src="${commonResourcePath}/images/news_page/share.svg" alt=""> Share</a> -->
              			</div>
              		</div>
          		</div>
          	</div>
      	</section>

       	<main>
       		<!-- Breadcurms -->
            <section id="page-breadcrums" class="page-breadcrums">
            	<div class="container">
                	<div class="row">
                    	<nav aria-label="breadcrumb">
                        	<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                        </nav>
                  	</div>
              	</div>
         	</section>
            <!--End of  Breadcurms --> 
            
            <section class="newsDetailContainer" id="newsDetailContainer">
                 <div class="container">
                     <div class="row">
                         <div class="col-md-10 news-detail-content resourceDetailTop m-auto">
                         	<p>${resourceDetails.resourceShortInformation}</p>
                            <p>${resourceDetails.resourceFullDescription}</p> 
                         </div>                            
                     </div>
                 </div>
            </section>
                
            <!-- <section class="resourceDetailContainer" id="resourceDetailContainer">
                 <div class="container">
                     <div class="row">
                         <div class="col-md-10 news-detail-content m-auto">
                             <p>${resourceDetails.quotation.quote}</p>
                             <p>${resourceDetails.quotation.authorName}</p>
                             <p>${resourceDetails.quotation.authorDesignation}</p>
                         </div>                            
                     </div>
                 </div>
            </section> -->
                                
			<section class="Inc-exploreMore-section">
				<div class="row">
					<div class="col-xl-4 col-lg-12 col-12 px-0">
						<div class="media-explore-more-left">
							<h1><spring:theme code="portal.media.resourcedetails.exploreresources" text = "Explore More Resources"/></h1>
							<p><spring:theme code="portal.media.resourcedetails.exploreresources.description" /></p>
							<c:url value="/mediaCenter/resources" var="currentYearReportsUrl"/>
							<a class="btn btn-primary-fill btn-knowmore" href="${currentYearReportsUrl}" aria-selected="true"><spring:theme code="portal.media.explore.all" text="Explore All"/>&nbsp; 
								<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
							</a>	                         
						</div>                         
					</div>
					<div class="media-explore-resolution col-xl-8 px-0 row">
						<c:url value="/mediaCenter/resources" var="resourcesUrl"/>
						<c:forEach items="${recentResourceDetails}" var="resourceDetails">
							<div class="col-10 col-md-12 col-lg-4 card-wrapper">
								<div class="news-card">
									<div class="news-date text-center">
										<div class="day"><fmt:formatDate value="${resourceDetails.resourceDate}" pattern="d" /></div>
										<div class="month"><fmt:formatDate value="${resourceDetails.resourceDate}" pattern="MMMM" /></div>
									</div>
									<img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(resourceDetails.resourceThumbnailImage.url)}" 
											alt="${resourceDetails.resourceTitle}">
									<div class="news-card-inner">
										<h3>${resourceDetails.resourceTitle}</h3>
										<p>${resourceDetails.resourceShortInformation}</p>
										<a class="btn btn-primary-fill btn-knowmore" href="${resourcesUrl}/${resourceDetails.uid}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
											<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
										</a> 
									</div>
								</div>
							</div>
						</c:forEach> 
					</div>
				</div>
			</section>                                
		</main>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
        	<cms:component component="${slotComponent}"/>
       	</cms:pageSlot>

	</jsp:body>
</template:portalpage>      
