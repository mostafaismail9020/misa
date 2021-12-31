<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
		<header:portalPageTitle />

      	<cms:pageSlot position="PortalPageTop" var="slotComponent">
        	<cms:component component="${slotComponent}"/>
      	</cms:pageSlot>
        
      	<section class="page-details-banner">
        	<img  class="banner-image" src="${newsDetails.newsDetailsImage.url}" alt="${newsDetails.newsTitle}">
          	<div class="page-details-banner-container" data-aos="fade-up">
	          	<div class="container page-details-banner-item">
	            	<div class="row">
	              		<div class="col-md-7">
		                	<div class="event-date">
		                  		<div class="event-num"><fmt:formatDate value="${newsDetails.newsDate}" pattern="d" /></div>
		                  		<div class="event-mon"><fmt:formatDate value="${newsDetails.newsDate}" pattern="MMMM yyyy" /></div>
		                  	</div>
	                  		<h2>${newsDetails.newsTitle}</h2>                  	
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
           	<!--End of Breadcurms -->
           
            <section class="newsDetailContainer" id="newsDetailContainer">
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 news-detail-content m-auto">
                            <p>${newsDetails.newsShortInformation}</p>
                            <p>${newsDetails.newsFullDescription}</p> 
                        </div>                           
                    </div>
                </div>
            </section>

            <!-- <section class="newsDetailContainer" id="newsDetailContainer">
               <div class="container">
                   <div class="row">
                       <div class="col-md-10 news-detail-content m-auto">
                           <p>${newsDetails.quotation.quote}</p>
                           <p>${newsDetails.quotation.authorName}</p>                           
                       </div>                          
                   </div>
               </div>
			</section> -->
                    
            <!-- <section class="newsDetails-carosel-wrapper">
              	<div class="container">
                  	<div class="row">
                      	<div class="col-md-12 newsDetails-carosel m-auto">
							<div id="carouselExampleControls" class="carousel slide row" data-ride="carousel">
								<div class="col-md-1 p-0 controls">
									<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
										<span class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									  </a>
								</div>
								<div class="col-md-10 p-0">
									<div class="carousel-inner">
										<c:forEach items="${newsDetails.galleryImages.medias}" var="container" varStatus="varStatus">
											<div class="carousel-item ">
												<img class="d-block w-100" src="${fn:escapeXml(container.url)}" alt="${container.altText}">
											  </div>
										</c:forEach>	
									</div>
								</div>
								<div class="col-md-1 p-0 controls">
									<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
										<span class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									  </a>
								</div>
                                <div class="col-md-12 carousel-dots"> 
									<ol class="carousel-indicators">
										<li data-target="#carouselExampleControls" data-slide-to="0" class="active"></li>
										<li data-target="#carouselExampleControls" data-slide-to="1" ></li>
									  </ol>
								</div>																
							</div>	
 						</div>
					</div>	
 				</div>
  	 		</section> -->
  	 		
		   	<section class="Inc-exploreMore-section">
				<div class="row">
					<div class="col-xl-4 col-lg-12 col-12 px-0">
						<div class="media-explore-more-left">
							<h1><spring:theme code="portal.media.newsdetails.explorenews" text="Explore More News"/></h1>
							<p><spring:theme code="portal.media.newsdetails.explorenews.description" /></p>
							<c:url value="/mediaCenter/news" var="currentYearUrl"/>
							<a class="btn btn-primary-fill btn-knowmore" href="${currentYearUrl}" aria-selected="true"><spring:theme code="portal.media.explore.all" text="Explore All"/>&nbsp;
								<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span> 
							</a>
						</div>
					</div>
					<div class="media-explore-resolution col-xl-8 px-0 row">
						<c:url value="/mediaCenter/news" var="newsUrl"/>
						<c:forEach items="${recentNews}" var="newsDetails">
							<div class="col-10 col-md-12 col-lg-4 card-wrapper">
								<div class="news-card">
									<div class="news-date text-center">
										<div class="day"><fmt:formatDate value="${newsDetails.newsDate}" pattern="d" /></div>
										<div class="month"><fmt:formatDate value="${newsDetails.newsDate}" pattern="MMMM" /></div>
									</div>
									<img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(newsDetails.newsThumbnailImage.url)}" 
											alt="${newsDetails.newsTitle}">
									<div class="news-card-inner">
										<h3>${newsDetails.newsTitle}</h3>
										<p>${newsDetails.newsShortInformation}</p>
										<a class="btn btn-primary-fill btn-knowmore" href="${newsUrl}/${newsDetails.uid}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
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
        