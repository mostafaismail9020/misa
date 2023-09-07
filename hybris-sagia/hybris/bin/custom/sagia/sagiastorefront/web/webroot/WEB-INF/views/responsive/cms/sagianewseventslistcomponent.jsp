<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="news-events-page-banner" style="background-image: url(${fn:escapeXml(eventSearchPageData.results[0].imageUrl)});">
    <div class="news-events-page-banner-container" data-aos="fade-up">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <h1 class="article-details-events-page-general-title"><spring:theme code="portal.media.events" text = "Events"/></h1>
                </div>
            </div>
            <div class="row">

                <div class="col-md-4" style="position: relative;">
                    <div class="event-top-info-box mb-4">
                        <div class="date" style="position: absolute;">
                            <span class="day"><fmt:formatDate value="${eventSearchPageData.results[0].eventDate}" pattern="d" /></span>
                            <span class="month"><fmt:formatDate value="${eventSearchPageData.results[0].eventDate}" pattern="MMM" /></span>
                        </div>

                         <c:choose>
                            <c:when test="${fn:length(eventSearchPageData.results[0].name) gt 20}">
                                 <h2 class="event-title">${fn:substring(eventSearchPageData.results[0].name, 0, 20)}...</h2>
                            </c:when>
                            <c:otherwise>
                                   <h2 class="event-title">${eventSearchPageData.results[0].name}</h2>
                            </c:otherwise>
                         </c:choose>
                        <c:set var="description" value="${eventSearchPageData.results[0].description}"/>
                        <c:choose>
                            <c:when test="${fn:length(description) gt 150}">
                                <p class="event-description">
                                        ${fn:substring(description, 0, 150)}...
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p class="event-description">
                                        ${description}
                                </p>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <div class="col-md-8"></div>
            </div>
        </div>
    </div>
</div>

<div class="container">

    <div class="row mb-3">
        <c:if test="${language eq 'en'}">
            <a href="/${language}/">
                <div class="col-md-12 mt-4 breadcrumb-container">
                    <span class="breadcrumb-left-icon"></span>
                    <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                </div>
            </a>
        </c:if>

        <c:if test="${language eq 'ar'}">
            <a href="/${language}/">
                <div class="col-md-12 mt-4 breadcrumb-container">
                    <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                    <span class="breadcrumb-left-icon"></span>
                </div>
            </a>
        </c:if>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <h2 class="upcoming-events-title"><spring:theme code="text.newsevents.listing.page.upcoming.events"/></h2>
        </div>
        <c:choose>
            <c:when test="${not empty eventSearchPageData.results}">
                <div class="events-container">
                    <div class="col-md-12">
                        <div class="row">
                            <c:forEach var="result" items="${eventSearchPageData.results}" varStatus="status">
                                <tags:events-card result="${result}" loopCount="${status.index}"/>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="showMoreLessButtonContainer">
                        <button id="loadEventMore" class="loadNewsEventShowLessButton misa-btn-special"><spring:theme
                                code="review.show.more"/></button>
                        <button id="showEventLess" class="loadNewsEventShowLessButton misa-btn-special"><spring:theme
                                code="review.show.less"/></button>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <spring:theme code="text.label.notFound"/>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="row">
        <div class="col-md-12 mt-2">
            <div class="latest-news-header mt-3 mb-3">
                <h2 class="latest-news-title"><spring:theme code="text.newsevents.listing.page.latest.news"/></h2>
            </div>
            <c:choose>
                <c:when test="${not empty searchPageData.results}">
                    <div class="latest-news-container">
                        <c:forEach var="result" items="${newsSearchPageData.results}" varStatus="status">
                            <tags:news-card result="${result}" loopCount="${status.index}"/>
                        </c:forEach>
                    </div>
                    <div class="showMoreLessButtonContainer">
                        <button id="loadNewsMore" class="loadNewsEventShowLessButton misa-btn-special"><spring:theme code="review.show.more"/></button>
                        <button id="showNewsLess" class="loadNewsEventShowLessButton misa-btn-special"><spring:theme code="review.show.less"/></button>
                    </div>
                </c:when>
                <c:otherwise>
                    <spring:theme code="text.label.notFound"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <section class="latest-announcements">
                <div class="header mt-5 mb-3">
                    <h2 class="title latest-announcements-title"><spring:theme code="text.newsevents.listing.page.latest.announcements"/></h2>
                </div>
                <div class="content">
                    <div class="announcement">
                        <img class="image"
                             src="https://investsaudi.sa/medias/all-opportunity.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw4OTU5MzZ8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDljL2hiNC84OTA5NDE5MzQ3OTk4LmpwZ3xiZGM4NzA3ZTZmNDc5ZmJkZDJlZDQ5MjI2ODQ4YzQ1NWY0YTk4N2RlN2QxMTVlNTAyM2Q5NGQ4OWEyZjQ3MGY4"
                             alt="" loading="lazy">
                        <div class="details">
                            <span class="date mt-2"><spring:theme code="text.newsevents.listing.page.latest.announcements.date"/></span>
                            <h3 class="headline"><spring:theme code="text.newsevents.listing.page.latest.announcements.title"/></h3>
                            <span class="description mb-2"><spring:theme code="text.newsevents.listing.page.latest.announcements.content"/></span>
                        </div>
                    </div>

                    <div class="announcement">
                        <img class="image"
                             src="https://investsaudi.sa/medias/meet-the-Kingdom-Banner.png?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwzNzY2NXxpbWFnZS9wbmd8cG9ydGFsLW1lZGlhL2g5My9oOWQvODkyMzM1OTkwMzc3NC5wbmd8ZDU4ZDE0NzE1OGM1YjBkMzMxMTA1MjUzZGFmNzI2OTMxYTBlMmNjNDZlMGQ3MmUyYmNmYjcwZGUwMDEyNzcxZg"
                             alt="" loading="lazy">
                        <div class="details">
                             <span class="date mt-2"><spring:theme code="text.newsevents.listing.page.latest.announcements.date"/></span>
                             <h3 class="headline"><spring:theme code="text.newsevents.listing.page.latest.announcements.title2"/></h3>
                             <span class="description mb-2"><spring:theme code="text.newsevents.listing.page.latest.announcements.content2"/></span>
                        </div>
                    </div>

                    <div class="announcement">
                        <img class="image"
                             src="https://investsaudi.sa/medias/economic-Highlights-Banner.png?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwyMzkzMXxpbWFnZS9wbmd8cG9ydGFsLW1lZGlhL2gzMi9oMWYvODkyMzM1OTgzODIzOC5wbmd8MGY1YzUxNWE5NmY2NzJiZTNkYWFiOGI2N2FmOGMxYzhjOGY5OTQ3NDgwMTcwMDdmNmMxMGJmYzYyMDVmNzVhYw"
                             alt="" loading="lazy">
                        <div class="details">
                             <span class="date mt-2"><spring:theme code="text.newsevents.listing.page.latest.announcements.date"/></span>
                             <h3 class="headline"><spring:theme code="text.newsevents.listing.page.latest.announcements.title3"/></h3>
                             <span class="description mb-2"><spring:theme code="text.newsevents.listing.page.latest.announcements.content3"/></span>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <div class="get-the-latest-updates">
                <h2 class="get-updates-title"><spring:theme code="text.newsevents.listing.page.get.the.latest.updates"/></h2>
                <button class="btn btn-primary join-newsletter-btn misa-btn-special"><spring:theme code="text.newsevents.listing.page.join.our.newsletter"/></button>
            </div>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <h2 class="media-library-title mt-3 mb-3"><spring:theme code="text.newsevents.listing.page.media.library"/></h2>
        </div>
        <div class="col-md-12">
            <div class="media-library">
                <div class="media-content">
                    <div class="media-left">
                        <iframe width="100%" height="194" src="https://www.youtube.com/embed/GDyqFD4rhWo" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy"></iframe>
                        <h4 class="videoTitle mt-2"><spring:theme code="text.newsevents.listing.page.media.library.title"/></h4>
                        <span class="videoDate"><spring:theme code="text.newsevents.listing.page.media.library.date"/></span>
                    </div>

                    <div class="media-right">
                        <div class="media-small">
                            <div class="media-video">
                                <iframe width="100%" height="113" src="https://www.youtube.com/embed/qWbrcmGRxW0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy"></iframe>
                            </div>
                            <div class="media-text">
                                <h4 class="videoTitle mt-2"><spring:theme code="text.newsevents.listing.page.media.library.title"/></h4>
                                <span class="videoDate"><spring:theme code="text.newsevents.listing.page.media.library.date"/></span>
                            </div>
                        </div>
                        <div class="media-small">
                            <div class="media-video">
                                <iframe width="100%" height="113" src="https://www.youtube.com/embed/qWbrcmGRxW0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy"></iframe>
                            </div>
                            <div class="media-text">
                               <h4 class="videoTitle mt-2"><spring:theme code="text.newsevents.listing.page.media.library.title"/></h4>
                               <span class="videoDate"><spring:theme code="text.newsevents.listing.page.media.library.date"/></span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
