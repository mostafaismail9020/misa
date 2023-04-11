  <%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
        <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
       <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
      <spring:htmlEscape defaultHtmlEscape="true"/>

         <template:portalpage pageTitle="${pageTitle}">
            <jsp:body>
			
			
                <header:portalPageTitle />

                <cms:pageSlot position="PortalPageTop" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>
        
        <main>
               
                
                 <section class="tab-section">
                        <div class="container">
                              <div class="tab-content" id="pills-tabContent">
                                <div class="tab-pane fade show active" id="pills-year" role="tabpanel" aria-labelledby="pills-year-tab">
                                    <div class="row">
                                    <c:url value="/mediaCenter/news" var="newsUrl"/>
                                        
                                            <div class="col-sm-4 mb-5">
                                                <div class="news-card">
                                                        <div class="news-date text-center">
                                                            <div class="day"><fmt:formatDate value="${latestNews.newsDate}" pattern="d" /></div>
                                                            <div class="month"><fmt:formatDate value="${latestNews.newsDate}" pattern="MMMM" /></div>
                                                        </div>
                                                        <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(latestNews.newsThumbnailImage.url)}" alt="${latestNews.newsTitle}" loading="lazy">
                                                        <div class="news-card-inner">
                                                            <h3>${latestNews.newsTitle}</h3>
                                                            <p>${latestNews.newsShortDescription}</p>
                                                            <a class="btn btn-primary-fill btn-knowmore" href="${newsUrl}/${latestNews.newsTitle}">Know More <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                       
                                    </div>
                                </div>
                                
                             <div class="container">
                      <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-year" role="tabpanel" aria-labelledby="pills-year-tab">
                            <div class="row">
                                <c:url value="/mediaCenter/events" var="eventsUrl"/>
                                
                                    <div class="col-sm-4 mb-5">
                                        <div class="news-card">
                                                <div class="events-date text-center">
                                                    <div class="day"><fmt:formatDate value="${latestEvent.eventStartDate}" pattern="d" /></div>
                                                <div class="month"><fmt:formatDate value="${latestEvent.eventEndDate}" pattern="MMMM" /></div>
                                                </div>
                                                <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(latestEvent.eventThumbnailImage.url)}" alt="${latestEvent.eventName}" loading="lazy">
                                                <div class="events-card-inner">
                                                    <h3>${latestEvent.eventName}</h3>
                                                    <p>${latestEvent.eventShortDescription}</p>
                                                    <a class="btn btn-primary-fill btn-knowmore" href="${eventsUrl}/${latestEvent.uid}">Know More <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span></a>
                                                </div>
                                            </div>
                                        </div>
                                
                            </div>
                          <div class="container">
                      <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-year" role="tabpanel" aria-labelledby="pills-year-tab">
                            <div class="row">
                                <c:url value="/mediaCenter/resources" var="resourcesUrl"/>
                            
                                <div class="col-sm-4 mb-5">
                                    <div class="resource-card">
                                            <div class="resources-date text-center">
                                                <div class="day"><fmt:formatDate value="${latestResource.resourceDate}" pattern="d" /></div>
                                            <div class="month"><fmt:formatDate value="${latestResource.resourceDate}" pattern="MMMM" /></div>
                                            </div>
                                            <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(latestResource.resourceThumbnailImage.url)}" alt="${latestResource.resourceTitle}" loading="lazy">
                                            <div class="resource-card-inner">
                                                <h3>${latestResource.resourceTitle}</h3>
                                                <p>${latestResource.resourceShortDescription}</p>
                                                <a class="btn btn-primary-fill btn-knowmore" href="${resourcesUrl}/${latestResource.uid}">Know More <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span> </a> 
                                            </div>
                                        </div>
                                    </div>
                                
                                </div>
                             <div class="container">
                      <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-year" role="tabpanel" aria-labelledby="pills-year-tab">
                            <div class="row">
                                
                                                    <div class="col-sm-6 news vedio_outer mb-5">
                                                        <div class="video-player-container">
                                                            <div class="embed-responsive embed-responsive-16by9">
                                                                <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471" src="${fn:escapeXml(latestWebinar.embedURL.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
                                                            </div>
                                                            <!-- <span class="video_text">
                                                                Healthcare & Life Sciences Sector
                                                            </span> -->
                                                        </div>
                                                    </div>
                                                 </div>
                                   </div>   
                                <div class="container">
                      <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-year" role="tabpanel" aria-labelledby="pills-year-tab">
                            <div class="row">
                                       <div class="col-sm-6 news vedio_outer mb-5">
                                                        <div class="video-player-container">
                                                            <div class="embed-responsive embed-responsive-16by9">
                                                                <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471" src="${fn:escapeXml(latestSuccessStory.embedURL.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
                                                            </div>
                                                            <!-- <span class="video_text">
                                                                Healthcare & Life Sciences Sector
                                                            </span> -->
                                                        </div>
                                                    </div>
                                              </div>
                        </div>
                                
  			   </section>	                
                
                        <section class="Inc-mediaCenter-wrapper-header">
                                    <div class="container">
                                        <div class="row text-center">
                                            <h1 class="w-100"><spring:theme code="portal.media.homepage.mediacenter.overview" text="MEDIA CENTER OVERVIEW"/></h1>
                                            <div class="col-md-9 m-auto">
                                                <p><spring:theme code="portal.media.homepage.mediacenter.description" text=""/></p>
                                            </div>
                                        </div>
                                    </div>
                        </section>       

                <!-- News section -->
  			   	<section class="Inc-mediaCenter-sectionwrapper">

                        <div class="container">
                            <div class="row mb-5">
                                <img class="img-fluid title-icon" src="${commonResourcePath}/images/news_icon.png" alt="news-icon" loading="lazy">
                                <h1 class="title"><spring:theme code="portal.media.news" text="NEWS FROM INVEST SAUDI"/></h1>
                            </div>
                            <div class="row pt-3">
                                <c:url value="/mediaCenter/news" var="newsUrl"/>
                                    <c:forEach items="${newsPageData.results}" var="newsComponent">
                                        <div class="col-sm-4 mb-5">
                                            <div class="news-card">
                                                    <div class="news-date text-center">
                                                        <div class="day"><fmt:formatDate value="${newsComponent.newsDate}" pattern="d" /></div>
                                                        <div class="month"><fmt:formatDate value="${newsComponent.newsDate}" pattern="MMMM" /></div>
                                                    </div>
                                                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(newsComponent.newsThumbnailImage.url)}" alt="${newsComponent.newsTitle}" loading="lazy">
                                                    <div class="news-card-inner">
                                                        <h3>${newsComponent.newsTitle}</h3>
                                                        <p>${newsComponent.newsShortDescription}</p>
                                                        <a class="btn btn-primary-fill btn-knowmore" href="${newsUrl}/${newsComponent.newsTitle}">Know More <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span></a>
                                                    </div>
                                                </div>
                                            </div>
                                </c:forEach>
                            </div>
                        </div>
  			   </section>	

  	            <!-- Events Section		      -->
                 <section class="Inc-mediaCenter-sectionwrapper">
                     <div class="container">
                            <div class="row mb-5">
                                <img class="img-fluid title-icon" src="${commonResourcePath}/images/events_icon.png" alt="events-icon" loading="lazy">
                                <h1 class="title"> <spring:theme code="portal.media.events" text="EVENTS"/></h1>
                            </div>
                            <div class="row">
                                <c:url value="/mediaCenter/events" var="eventsUrl"/>
                                <c:forEach items="${eventsPageData.results}" var="eventComponent">
                                    <div class="col-sm-4 mb-5">
                                        <div class="news-card">
                                            <div class="news-date text-center">
                                                    <div class="day"><fmt:formatDate value="${eventComponent.eventStartDate}" pattern="d" /></div>
                                                <div class="month"><fmt:formatDate value="${eventComponent.eventEndDate}" pattern="MMMM" /></div>
                                                </div>
                                                <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(eventComponent.eventThumbnailImage.url)}" alt="${eventComponent.eventName}" loading="lazy">
                                                <div class="news-card-inner">
                                                    <h3>${eventComponent.eventName}</h3>
                                                    <p>${eventComponent.eventShortDescription}</p>
                                                    <a class="btn btn-primary-fill btn-knowmore" href="${eventsUrl}/${eventComponent.uid}">Know More <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span></a>
                                                </div>
                                            </div>
                                        </div>
                                </c:forEach>
                            </div>
                        </div>
  			  </section>

  		    <!-- Resource section	   -->
                <section class="Inc-mediaCenter-sectionwrapper">
                    <div class="container">
                        <div class="row mb-5">
                            <img class="img-fluid title-icon" src="${commonResourcePath}/images/resource_icon.png" alt="events-icon" loading="lazy">
                            <h1 class="title"> <spring:theme code="portal.media.resources" text="RESOURCES"/></h1>
                        </div>
                            <c:url value="/mediaCenter/resources" var="resourcesUrl"/>
                            <c:forEach items="${resourcePageData.results}" var="resourceComponent">

                            <div class="row Inc-mediaCenter-resource-panel">
                                <div class="col-md-5">
                                    <img class="img-fluid w-100 Inc-mediaCenter-resource-img" src="${fn:escapeXml(resourceComponent.resourceThumbnailImage.url)}" alt="${resourceComponent.resourceTitle}">
                                </div>
                                <div class="col-md-7">
                                    <div class="Inc-mediaCenter-Date">
                                        <div class="day"><fmt:formatDate value="${resourceComponent.resourceDate}" pattern="d" /></div>
                                        <div class="month"><fmt:formatDate value="${resourceComponent.resourceDate}" pattern="MMMM" /></div>
                                    </div>
                                    <div class="news-card-inner">
                                        <h3>${resourceComponent.resourceTitle}</h3>
                                        <p>${resourceComponent.resourceShortDescription}</p>
                                        <a class="btn btn-primary-fill btn-knowmore" href="${resourcesUrl}/${resourceComponent.uid}">Know More <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span> </a> 
                                    </div>
                                </div>
                            </div>

                            </c:forEach>    
                        
                    </div>
                </section>

                <!-- Video Section          -->
                <section class="Inc-mediaCenter-sectionwrapper">
                    <div class="container">

                        <div class="row mb-5">
                            <img class="img-fluid title-icon" src="${commonResourcePath}/images/video_icon.png" alt="" loading="lazy">
                            <h1 class="title"><spring:theme code="portal.media.webinar.videos" text="WEBINAR VIDEOS"/></h1>
                        </div>
                        <div class="row">
                            <c:forEach items="${webinarPageData.results}" var="videoComponent">
                                                <div class="col-sm-4 news vedio_outer mb-5">
                                                    <div class="video-player-container">
                                                        <div class="embed-responsive embed-responsive-16by9">
                                                            <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471" src="${fn:escapeXml(videoComponent.embedURL.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
                                                        </div>
                                                        <!-- <span class="video_text">
                                                            Healthcare & Life Sciences Sector
                                                        </span> -->
                                                    </div>
                                                </div>
                            </c:forEach>

                        </div>
                    </div>
           </section>


                 
<section class="tab-section">
                    <div class="container">
                      <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-year" role="tabpanel" aria-labelledby="pills-year-tab">
                            <div class="row">
                                <c:forEach items="${successStoryPageData.results}" var="successStoryComponent">
                                                    <div class="col-sm-6 news vedio_outer mb-5">
                                                        <div class="video-player-container">
                                                            <div class="embed-responsive embed-responsive-16by9">
                                                                <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471" src="${fn:escapeXml(successStoryComponent.embedURL.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
                                                            </div>
                                                            <!-- <span class="video_text">
                                                                Healthcare & Life Sciences Sector
                                                            </span> -->
                                                        </div>
                                                    </div>
                                                </c:forEach>

                            </div>
                        </div>
           </section>
                              
        
        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>

            </jsp:body>
        </template:portalpage>