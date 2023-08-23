<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<! -- Start InvestSaudiMediaCenterMainComponent -->
<main>
<section class="tab-section Inc-mediaCenter-sectionwrapper">
        <c:url value="/mediaCenter/events" var="eventsUrl" />
        <div class="container">
            <div class="row text-center">
                <h1 class="w-100 title service-title">
                    ${eventsSectionTitle}
                </h1>
            </div>
        </div>
        <div class="container">
                <div class="row">
                    <c:forEach items="${eventsPageData.results}" var="eventComponent">
                        <div class="col-sm-12 col-md-4 mb-5">
                            <div class="flip-card">
                                <a href="${eventsUrl}/${eventComponent.uid}">
                                <div class="flip-card-front">
                                    <div class="news-date text-center">
                                        <div class="day">
                                            <fmt:formatDate value="${eventComponent.eventStartDate}" pattern="d" />
                                        </div>
                                        <div class="month">
                                            <fmt:formatDate value="${eventComponent.eventStartDate}" pattern="MMM" />
                                        </div>
                                    </div>
                                    <div class="card-img">
                                    	<img class="img-fluid w-100 events-card-img" src="${fn:escapeXml(eventComponent.eventThumbnailImage.url)}"  alt="${eventComponent.eventName}" loading="lazy">

                                    	</div>
                                    <div class="flip-card-text">
                                        <h2 class="eventName">${eventComponent.eventName}</h2>
                                         <p class="eventShortInformation">${eventComponent.eventLocation}</p>
                                        <p class="eventShortInformation">${eventComponent.eventShortInformation}</p>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

    <section class="Inc-mediaCenter-sectionwrapper">
        <c:url value="/mediaCenter/news" var="newsUrl" />
        <div class="container">
            <div class="row text-center">
                <h1 class="w-100 title service-title">
                    ${newsSectionTitle}
                </h1>
            </div>
        </div>
         <div class="container">
                 <div class="row">
                     <c:forEach items="${newsPageData.results}" var="newsComponent">
                         <div class="col-sm-12 col-md-12 col-lg-4 mb-5">
                             <div class="events-card">
                                 <a href="${newsUrl}/${newsComponent.uid}">
                                     <div class="row">
                                         <div class="col-sm-4">
                                             <div class="card-image">
                                                 <img class="img-fluid media-card-img" src="${fn:escapeXml(newsComponent.newsThumbnailImage.url)}" alt="${newsComponent.newsTitle}" loading="lazy">
                                             </div>
                                         </div>
                                         <div class="col-sm-8">
                                             <div class="card-text">
                                                 <h3 class="newsTitle">${newsComponent.newsTitle}</h3>
                                                 <span class="d-news-update-date">
                                                     <fmt:formatDate value="${newsComponent.newsDate}" pattern="d MMM yyyy" />
                                                 </span>
                                             </div>
                                         </div>
                                     </div>
                                 </a>
                             </div>
                         </div>
                     </c:forEach>
                 </div>
             </div>
         </section>

<section class="Inc-mediaCenter-sectionwrapper pb-0">
    <c:url value="/mediaCenter/resources" var="resourcesUrl" />
    <div class="container">
        <div class="row text-center">
            <h1 class="w-100 title service-title">
                ${resourcesSectionTitle}
            </h1>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <c:forEach items="${resourcePageData.results}" var="resourceComponent">
                <a href="${resourcesUrl}/${resourceComponent.uid}" class="col-md-12 resources-wrapper">
                    <div class="row">
                        <div class="col-md-3">
                            <img class="img-fluid  service-card-img resources-card-img"
                                 src="${fn:escapeXml(resourceComponent.resourceThumbnailImage.url)}"
                                 alt="${resourceComponent.resourceTitle}" loading="lazy">
                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-6 resourceDate">
                                    <fmt:formatDate  value="${resourceComponent.resourceDate}" pattern="d MMM yyyy" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <h3 class="resourceTitle" title="${resourceComponent.resourceTitle}">${resourceComponent.resourceTitle}</h3>
                                    <p class="resourceInfo">${resourceComponent.resourceShortInformation}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</section>


   <section class="Inc-mediaCenter-sectionwrapper pb-3">
       <c:url value="/mediaCenter/videos" var="videosUrl" />
       <div class="container">
           <div class="row text-center">
               <h1 class="w-100 title service-title">
                   ${videosSectionTitle}
               </h1>
           </div>
       </div>
       <div class="container">
           <div class="row">
               <div class="col-md-7">
                   <div class="video-player-container video-inner-div border-0 p-0">
                       <div class="embed-responsive embed-responsive-16by9">
                           <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="100%"
                                   src="${fn:escapeXml(webinarPageData.results[0].embedURL.url)}" frameborder="0"
                                   allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                           </iframe>
                       </div>
                       <h4 class="media-center-webinar" title="${webinarPageData.results[0].title}">${webinarPageData.results[0].title}</h4>
                   </div>
               </div>
               <div class="col-md-5">
                   <div class="row">
                       <div class="col-md-12 news vedio_outer">
                           <div class="video-player-container video-inner-div border-0 p-0">
                               <div class="embed-responsive embed-responsive-16by9">
                                   <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="100%"
                                           src="${fn:escapeXml(successStoryPageData.results[0].embedURL.url)}" frameborder="0"
                                           allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                   </iframe>
                               </div>
                               <h4 class="media-center-webinar" title="${webinarPageData.results[1].title}">${webinarPageData.results[1].title}</h4>
                           </div>
                       </div>
                       <div class="col-md-12 news vedio_outer">
                           <div class="video-player-container video-inner-div border-0 p-0">
                               <div class="embed-responsive embed-responsive-16by9">
                                   <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="100%"
                                           src="${fn:escapeXml(successStoryPageData.results[1].embedURL.url)}" frameborder="0"
                                           allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                   </iframe>
                               </div>
                               <h4 class="media-center-webinar" title="${webinarPageData.results[2].title}">${webinarPageData.results[2].title}</h4>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
       </div>
   </section>
   <main>


<! -- Finish InvestSaudiMediaCenterMainComponent -->