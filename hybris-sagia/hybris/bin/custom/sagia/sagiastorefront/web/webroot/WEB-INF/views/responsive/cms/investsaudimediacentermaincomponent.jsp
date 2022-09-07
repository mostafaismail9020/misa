<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<! -- Start InvestSaudiMediaCenterMainComponent -->
<main>
    <section class="Inc-mediaCenter-wrapper-header text-center">
        <div class="container">
            <div class="row">
                <div class="col-md-10 m-auto">
                    <h1><spring:theme code="portal.media.homepage.mediacenter.overview" text="MEDIA CENTER OVERVIEW" /></h1>
                    <p><spring:theme code="portal.media.homepage.mediacenter.description" text="" /></p>
                </div>
            </div>
        </div>
    </section>

    <section class="Inc-mediaCenter-sectionwrapper">
        <c:url value="/mediaCenter/news" var="newsUrl" />
        <div class="container">
            <div class="row text-center">
                <img class="img-fluid title-icon" src="${commonResourcePath}/images/news_icon.png" alt="" loading="lazy"/>
                <h1 class="w-100 title service-title">
                    ${newsSectionTitle}
                    <a href="${newsUrl}" class="btn-primary explore-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
                        <svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                            <path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                        </svg>
                    </a>
                </h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <c:forEach items="${newsPageData.results}" var="newsComponent">
                    <div class="col-sm-12 col-md-4 col-lg-4 mb-5">
                        <div class="news-card">
                            <div class="news-date text-center">
                                <div class="day">
                                    <fmt:formatDate value="${newsComponent.newsDate}" pattern="d" />
                                </div>
                                <div class="month">
                                    <fmt:formatDate value="${newsComponent.newsDate}" pattern="MMMM" />
                                </div>
                            </div>
                            <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(newsComponent.newsThumbnailImage.url)}"
                                    alt="${newsComponent.newsTitle}" loading="lazy">
                            <div class="news-card-inner">
                                <h3>${newsComponent.newsTitle}</h3>
                                <p>${newsComponent.newsShortInformation}</p>
                                <a class="btn btn-primary-fill btn-knowmore" href="${newsUrl}/${newsComponent.uid}"><spring:theme code="portal.media.know.more" text= "Know More"/>&nbsp;
                                    <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <section class="tab-section Inc-mediaCenter-sectionwrapper">
        <c:url value="/mediaCenter/events" var="eventsUrl" />
        <div class="container">
            <div class="row text-center">
                <img class="img-fluid title-icon" src="${commonResourcePath}/images/events_icon.png" alt="" loading="lazy"/>
                <h1 class="w-100 title service-title">
                    ${eventsSectionTitle}
                    <a href="${eventsUrl}" class="btn-primary explore-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
                        <svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                            <path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                        </svg>
                    </a>
                </h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <c:forEach items="${eventsPageData.results}" var="eventComponent">
                    <div class="col-sm-12 col-md-4 mb-5">
                        <div class="news-card">
                            <div class="news-date text-center">
                                <div class="day">
                                    <fmt:formatDate value="${eventComponent.eventStartDate}" pattern="d" />
                                </div>
                                <div class="month">
                                    <fmt:formatDate value="${eventComponent.eventStartDate}" pattern="MMMM" />
                                </div>
                            </div>
                            <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(eventComponent.eventThumbnailImage.url)}"
                                    alt="${eventComponent.eventName}" loading="lazy">
                            <div class="news-card-inner">
                                <h3>${eventComponent.eventName}</h3>
                                <p>${eventComponent.eventShortInformation}</p>
                                <a class="btn btn-primary-fill btn-knowmore" href="${eventsUrl}/${eventComponent.uid}"><spring:theme code="portal.media.know.more" text= "Know More"/>&nbsp;
                                    <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                                </a>
                            </div>
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
                <img class="img-fluid title-icon" src="${commonResourcePath}/images/resource_icon.png" alt="" loading="lazy"/>
                <h1 class="w-100 title service-title">
                    ${resourcesSectionTitle}
                    <a href="${resourcesUrl}" class="btn-primary explore-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
                        <svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                            <path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                        </svg>
                    </a>
                </h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <c:forEach items="${resourcePageData.results}" var="resourceComponent">
                    <div class="col-sm-12 col-md-4 mb-5">
                        <div class="news-card news-service-card">
                            <div class="news-date service-date text-center">
                                <div class="day">
                                    <fmt:formatDate value="${resourceComponent.resourceDate}" pattern="d" />
                                </div>
                                <div class="month">
                                    <fmt:formatDate value="${resourceComponent.resourceDate}" pattern="MMMM" />
                                </div>
                            </div>
                            <img class="img-fluid w-100 service-card-img news-card-img" src="${fn:escapeXml(resourceComponent.resourceThumbnailImage.url)}"
                                    alt="${resourceComponent.resourceTitle}" loading="lazy">
                            <div class="service-card">
                                <h3 title="${resourceComponent.resourceTitle}">${resourceComponent.resourceTitle}</h3>
                                <p>${resourceComponent.resourceShortInformation}</p>
                                <a class="btn btn-primary-fill btn-knowmore" href="${resourcesUrl}/${resourceComponent.uid}"><spring:theme code="portal.media.know.more" text= "Know More"/>&nbsp;
                                    <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <section class="Inc-mediaCenter-sectionwrapper pb-3">
        <c:url value="/mediaCenter/videos" var="videosUrl" />
        <div class="container">
            <div class="row text-center">
                <img class="img-fluid title-icon" src="${commonResourcePath}/images/video_icon.png" alt="" loading="lazy"/>
                <h1 class="w-100 title service-title">
                    ${videosSectionTitle}
                    <a href="${videosUrl}" class="btn-primary explore-btn webinar-service-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
                        <svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                            <path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                        </svg>
                    </a>
                </h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <ul class="nav nav-tabs justify-content-center border-0 w-100 mb-5" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
                                aria-selected="true"><spring:theme code="portal.media.webinar" text= "Webinar"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
                                aria-selected="false"><spring:theme code="portal.media.success.stories" text= "Success Stories"/></a>
                    </li>
                  </ul>
                  <div class="tab-content w-100" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="container">
                            <div class="row">
                                <c:forEach items="${webinarPageData.results}" var="videoComponent">
                                    <div class="col-md-4 news vedio_outer mb-3">
                                        <div class="video-player-container video-inner-div border-0 p-0">
                                            <div class="embed-responsive embed-responsive-16by9">
                                                <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471"
                                                        src="${fn:escapeXml(videoComponent.embedURL.url)}" frameborder="0"
                                                        allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                                </iframe>
                                            </div>
                                            <h4 class="media-center-webinar" title="${videoComponent.title}">${videoComponent.title}</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="container">
                            <div class="row">
                                <c:forEach items="${successStoryPageData.results}" var="successStoryComponent">
                                    <div class="col-md-4 news vedio_outer">
                                        <div class="video-player-container video-inner-div border-0 p-0">
                                            <div class="embed-responsive embed-responsive-16by9">
                                                <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471"
                                                    src="${fn:escapeXml(successStoryComponent.embedURL.url)}" frameborder="0"
                                                    allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                                </iframe>
                                            </div>
                                            <h4>${videoComponent.title}</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                  </div>
            </div>
        </div>
    </section>
<main>
<! -- Finish InvestSaudiMediaCenterMainComponent -->