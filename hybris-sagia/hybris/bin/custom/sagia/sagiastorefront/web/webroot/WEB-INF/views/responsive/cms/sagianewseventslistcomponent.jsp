<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="news-events-page-banner" style="background-image: url('https://sagia.local:9002/medias/home-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw0NzI4MDV8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDI1L2hjZS84ODExNzA0NzEzMjQ2LmpwZ3wxZGUxOTY0ZDJlOTIyNTUxNjY3OTM0ZTg3YzcwZWJjNzlhYzAxMmMyNmIxNTNkM2M0YjZiYWVmY2RkYWU2MWM4');">
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
                        <h2 class="event-title">${eventSearchPageData.results[0].name}</h2>

                        <c:set var="description" value="${eventSearchPageData.results[0].description}"/>
                        <c:choose>
                            <c:when test="${fn:length(description) gt 100}">
                                <p class="event-description">
                                        ${fn:substring(description, 0, 100)}...
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

    <div class="row">
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
        <div class="col-md-12 mt-5">
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
                    <div class="showMoreLessButtonContainer mt-4">
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


    <div class="row">
        <div class="col-md-12">
            <section class="latest-announcements">
                <div class="header mt-3 mb-3">
                    <h2 class="title latest-announcements-title"><spring:theme code="text.newsevents.listing.page.latest.announcements"/></h2>
                </div>
                <div class="content">
                    <div class="announcement">
                        <img class="image"
                             src="https://investsaudi.sa/medias/all-opportunity.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw4OTU5MzZ8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDljL2hiNC84OTA5NDE5MzQ3OTk4LmpwZ3xiZGM4NzA3ZTZmNDc5ZmJkZDJlZDQ5MjI2ODQ4YzQ1NWY0YTk4N2RlN2QxMTVlNTAyM2Q5NGQ4OWEyZjQ3MGY4"
                             alt="" loading="lazy">
                        <div class="details">
                            <span class="date mt-2">16 Mar 2023</span>
                            <h3 class="headline">Discover the dynamism of Saudi Arabia's</h3>
                            <span class="description mb-2">Discover the dynamism of Saudi Arabia's investment landscape with the launch of four new Special Economic Zones (SEZs), spanning across advanced manufacturing, ICT & logistics.</span>
                        </div>
                    </div>

                    <div class="announcement">
                        <img class="image"
                             src="https://investsaudi.sa/medias/meet-the-Kingdom-Banner.png?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwzNzY2NXxpbWFnZS9wbmd8cG9ydGFsLW1lZGlhL2g5My9oOWQvODkyMzM1OTkwMzc3NC5wbmd8ZDU4ZDE0NzE1OGM1YjBkMzMxMTA1MjUzZGFmNzI2OTMxYTBlMmNjNDZlMGQ3MmUyYmNmYjcwZGUwMDEyNzcxZg"
                             alt="" loading="lazy">
                        <div class="details">
                            <span class="date mt-2">16 Mar 2023</span>
                            <h3 class="headline">The Kingdom is embarking on a world-leading</h3>
                            <span class="description mb-2">The Kingdom is embarking on a world-leading development program to unlock new sectors and diversify the economy through five major Giga-projects, and several mega-projects.</span>
                        </div>
                    </div>

                    <div class="announcement">
                        <img class="image"
                             src="https://investsaudi.sa/medias/economic-Highlights-Banner.png?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwyMzkzMXxpbWFnZS9wbmd8cG9ydGFsLW1lZGlhL2gzMi9oMWYvODkyMzM1OTgzODIzOC5wbmd8MGY1YzUxNWE5NmY2NzJiZTNkYWFiOGI2N2FmOGMxYzhjOGY5OTQ3NDgwMTcwMDdmNmMxMGJmYzYyMDVmNzVhYw"
                             alt="" loading="lazy">
                        <div class="details">
                            <span class="date mt-2">16 Mar 2023</span>
                            <h3 class="headline">Discover why Saudi Arabia</h3>
                            <span class="description mb-2">Discover why Saudi Arabia is rapidly becoming the preferred global investment destination, fueled by the growth of non-oil activities.</span>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <div class="row mt-4 mb-4">
        <div class="col-md-12">
            <div class="get-the-latest-updates">
                <h2 class="get-updates-title">Get the Latest Updates</h2>
                <button class="btn btn-primary join-newsletter-btn misa-btn-special">Join Our Newsletter</button>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h2 class="media-library-title mt-3 mb-3"><spring:theme code="text.newsevents.listing.page.media.library"/></h2>
        </div>
        <div class="col-md-12">
            <div class="media-library">
                <div class="media-content">
                    <div class="media-left">
                        <iframe width="100%" height="194" src="https://www.youtube.com/embed/GDyqFD4rhWo" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy"></iframe>
                        <h4 class="videoTitle mt-2">Saudi investment ministry signs four investment agreements</h4>
                        <span class="videoDate">25 Oct 2022</span>
                    </div>

                    <div class="media-right">
                        <div class="media-small">
                            <div class="media-video">
                                <iframe width="100%" height="113" src="https://www.youtube.com/embed/qWbrcmGRxW0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy"></iframe>
                            </div>
                            <div class="media-text">
                                <h4 class="videoTitle">Saudi investment ministry signs four investment agreements</h4>
                                <span class="videoDate">25 Oct 2022</span>
                            </div>
                        </div>
                        <div class="media-small">
                            <div class="media-video">
                                <iframe width="100%" height="113" src="https://www.youtube.com/embed/qWbrcmGRxW0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy"></iframe>
                            </div>
                            <div class="media-text">
                                <h4 class="videoTitle">Saudi investment ministry signs four investment agreements</h4>
                                <span class="videoDate">25 Oct 2022</span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
