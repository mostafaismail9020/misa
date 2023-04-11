<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>


<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
		
    	<header:portalPageTitle />

        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

		<section class="page-details-banner">
            <img  class="banner-image" src="${eventDetails.eventBannerImage.url}" alt="${eventDetails.eventName}" loading="lazy">
            <div class="page-details-banner-container" data-aos="fade-up">
            	<div class="container page-details-banner-item">
	                <div class="row">
		                <div class="col-md-7">
		                    <div class="event-date">
								<div class="d-flex">
									<div class="event-num"><fmt:formatDate value="${eventDetails.eventStartDate}" pattern="dd" /></div>
									<div class="hyph-seperator event-num"><fmt:formatDate value="${eventDetails.eventEndDate}" pattern="dd" /></div>
								</div>
								<div class="event-mon"><fmt:formatDate value="${eventDetails.eventStartDate}" pattern="MMMM yyyy" /></div>
			                    
		                    </div>
		                    <h2>${eventDetails.eventName}</h2>
		                    <h6><span style="color:#fff">${eventDetails.eventLocation}</span></h6>
		                    <!-- 
		                    <a href="" class="btn-get-started ">REGISTER NOW<img class="img-fluid mx-2" src="${commonResourcePath}/images/know-more.png" alt=""/></a>
		                    <a href="" class="btn btn-outline">READ MORE <img class="img-fluid mx-2" src="${commonResourcePath}/images/arow-r-md.png" alt=""/></a>
		                	 -->
		                </div>
                	</div>
				</div>
			</div>
		</section>

       	<main>
           	<section class="newsDetailContainer pb-0" id="newsDetailContainer">
               <div class="container">
                   <div class="row">
                       <div class="col-md-10 news-detail-content m-auto">
                           <p>${eventDetails.eventShortInformation}</p>
                           <p>${eventDetails.eventFullDescription}</p> 
                       </div>                          
                   </div>
               </div>
			</section>                 			
			
			<c:if test="${not empty eventDetails.eventKeyDetails}">
				<section class="eventsDetail-keys pt-4">
					<h1><spring:theme code="portal.media.eventdetail.keys" text="KEYS"/></h1>
					<div class="eventsDetail-keys-wrapper">
						<div class="container">
							<div class="row">
								<div class="col-md-10 key-detail-content m-auto">
									<c:forEach items="${eventDetails.eventKeyDetails}" var="item">
										<div class="box">
											<h4>${item.keyValue}</h4>
											<strong>${item.keyName}</strong>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>	
					</div>
				</section>
			</c:if>

			<c:if test="${not empty eventDetails.eventAgenda}">
				<section class="eventsDetail-agenda">
					<h1><spring:theme code="portal.media.eventdetail.agenda" text="AGENDA"/></h1>
					<div class="container">
						<div class="row">
							<div class="col-md-10 events-detail-content m-auto">
							<c:forEach items="${eventDetails.eventAgenda}" var="item">
								<div class="agenda-event">
									<div class="agenda-event-date">
										<fmt:formatDate value="${item.agendaDate}" pattern="dd. MMM yyyy" />
									</div>
									<div class="agenda-event-desc">
										<strong>${item.agendaTitle}</strong>
										<span>${item.speakerName}</span>
										<span>${item.speakerDetails}</span>
									</div>
								</div>
							</c:forEach>
							</div>
						</div>
					</div>
				</section>
  			</c:if>  
			
			<c:if test="${not empty eventDetails.eventUrl}">
				<section class="eventsDetail-linkwebsite">
					<div class="container">
						<div class="row">
							<div class="col-md-10 invest-forum-content m-auto">
								<h3><spring:theme code="portal.media.eventdetails.linkwebsite" text="Link of Website"/></h3> 
								<div class="col-md-12">
									<a class="invest-forum-content-link" href="${eventDetails.eventUrl.url}">${eventDetails.eventUrl.url}</a>
								</div>
							</div>
						</div>
					</div>
				</section>
			</c:if>
  			  
			<section class="Inc-exploreMore-section">
				<div class="row">
					<div class="col-xl-4 col-lg-12 col-12 px-0">
						<div class="media-explore-more-left">
							<h1><spring:theme code="portal.media.eventdetails.exploreevents" text="Explore More From Events"/></h1>
							<p><spring:theme code="portal.media.eventdetails.exploreevents.description"/></p> 
							<c:url value="/mediaCenter/events" var="upcomingEventsUrl"/>
							<a class="btn btn-primary-fill btn-knowmore" href="${upcomingEventsUrl}" aria-selected="true"><spring:theme code="portal.media.explore.all" text="Explore All"/>&nbsp; 
								<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span> 
							</a>                               						
						</div>
					</div>
					<div class="media-explore-resolution col-xl-8 px-0 row">
						<c:url value="/mediaCenter/events" var="eventsUrl"/>
						<c:forEach items="${recentEventDetails}" var="eventDetails">
							<div class="col-10 col-md-12 col-lg-4 card-wrapper">
								<div class="news-card">
									<div class="news-date text-center">
										<div class="day"><fmt:formatDate value="${eventDetails.eventStartDate}" pattern="d" /></div>
										<div class="month"><fmt:formatDate value="${eventDetails.eventEndDate}" pattern="MMMM" /></div>
									</div>
									<img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(eventDetails.eventThumbnailImage.url)}" alt="${eventDetails.eventName}" loading="lazy"/>
									<div class="news-card-inner">
										<h3>${eventDetails.eventName}</h3>
										<p>${eventDetails.eventShortInformation}</p>
										<a class="btn btn-primary-fill btn-knowmore" href="${eventsUrl}/${eventDetails.uid}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
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
