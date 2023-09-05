<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<section class="News_press" id="News_press">
	<div class="rect">
    	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" id="carousel" data-interval="false">
       		<div class="carousel-inner">
            	<c:url value="/mediaCenter/events" var="eventUrl"/>
                	<div class="carousel-item">
                    	<img class="d-block w-100" src="${fn:escapeXml(eventSearchPageData.results[0].imageUrl)}" alt=" loading="lazy">
                        <div class="toplist">
                        	<div class="container">
                            	<div class="mask flex-center">
                                	<div class="row  align-items-center  ">
                                    	<div class="container">
                                        	<div class="col-md-12 col-sm-12">
                                               <h2 class="pageTitle"><spring:theme code="portal.media.events" text = "Events"/></h2>
                                            </div>
                                      	</div>
                                      	<div class="col-md-4 col-sm-12">
                                        	<div class="News_press_bgwhite">
                                                <a href="${encodedContextPath}${eventSearchPageData.results[0].url}">
                                                    <div class="top_date position-absolute">
                                                        <h4 class="date"><fmt:formatDate value="${eventSearchPageData.results[0].eventDate}" pattern="d" /></h4>
                                                        <h6 class="date_name"><fmt:formatDate value="${eventSearchPageData.results[0].eventDate}" pattern="MMM" /></h6>
                                                    </div>
                                                    <div class="p-5 paddding_align">
                                                        <div>
                                                            <h3 class="highlight_title">${eventSearchPageData.results[0].name}</h3>
                                                            <c:set var="description" value="${eventSearchPageData.results[0].description}" />
                                                             <c:set var="start" value="${fn:indexOf(description, '<p>')}"/>
                                                             <c:set var="end" value="${fn:indexOf(description, '</p>')}"/>
                                                             <p class="eventDescription">${fn:substring(description, start + 3, end)}</p>
                                                        </div>
                                                    </div>
                                               </a>
											</div>
										</div>
	                             	</div>
	                         	</div>
	                     	</div>
	                 	</div>
	            	</div>
	 		</div>
		</div>
	</div>
</section>

<div class="container">
    <div class="row">
        <c:if test="${language eq 'en'}">
            <a href="/${language}/">
                <div class="col-md-12 mt-4 breadcrumb-container">
                    <span class="breadcrumb-left-icon"></span>
                    <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                </div>
            </a>
        </c:if>
        <c:if test="${language eq 'ar'}">
            <div class="col-md-12 mt-4 breadcrumb-container">
                <a href="/${language}/">
                    <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                    <span class="breadcrumb-left-icon"></span>
                </a>
            </div>
        </c:if>
        <div class="col-md-12 mt-4">
            <c:choose>
                <c:when test="${not empty eventSearchPageData.results}">
                    <h2 class="newsTitle"><spring:theme code="text.newsevents.listing.page.upcoming.events"/></h2>
                   <div class="col-md-12">
                    <div class="row events-container">
                        <c:forEach var="result" items="${eventSearchPageData.results}" varStatus="status">
                            <tags:events-card result="${result}" loopCount="${status.index}"/>
                        </c:forEach>
                    </div>
                    </div>
                    <div class="col-md-12">
                    <div class="showMoreLessButtonContainer">
                      <button id="loadEventMore" class="loadNewsEventShowLessButton"> <spring:theme code="review.show.more"/></button>
                      <button id="showEventLess" class="loadNewsEventShowLessButton"> <spring:theme code="review.show.less"/></button>
                    </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12 col-md-12 mt-4 text-center">
                        <spring:theme code="text.label.notFound"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-12 mt-5 mb-5">
            <c:choose>
                <c:when test="${not empty searchPageData.results}">
                    <h2 class="newsTitle"><spring:theme code="text.newsevents.listing.page.latest.news"/></h2>
                    <div class="col-md-12">
                    <div class="row news-container">
                        <c:forEach var="result" items="${newsSearchPageData.results}" varStatus="status">
                            <tags:news-card result="${result}" loopCount="${status.index}"/>
                        </c:forEach>
                    </div>
                    </div>
                   <div class="col-md-12">
                      <div class="showMoreLessButtonContainer">
                     <button id="loadNewsMore" class="loadNewsEventShowLessButton"><spring:theme code="review.show.more"/></button>
                     <button id="showNewsLess" class="loadNewsEventShowLessButton"><spring:theme code="review.show.less"/></button>
                     </div>
                   </div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12 col-md-12 mt-4 text-center">
                        <spring:theme code="text.label.notFound"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <section class="latest-announcements">
        <div class="header mt-3 mb-3">
            <h2 class="title"><spring:theme code="text.newsevents.listing.page.latest.announcements"/></h2>
        </div>
        <div class="content">
            <div class="announcement">
                <img class="image" src="https://investsaudi.sa/medias/all-opportunity.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw4OTU5MzZ8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDljL2hiNC84OTA5NDE5MzQ3OTk4LmpwZ3xiZGM4NzA3ZTZmNDc5ZmJkZDJlZDQ5MjI2ODQ4YzQ1NWY0YTk4N2RlN2QxMTVlNTAyM2Q5NGQ4OWEyZjQ3MGY4" alt="" loading="lazy">
                <div class="details">
                    <span class="date mt-2">16 Mar 2023</span>
                    <h3 class="headline">Discover the dynamism of Saudi Arabia's</h3>
                    <span class="description mb-2">Discover the dynamism of Saudi Arabia's investment landscape with the launch of four new Special Economic Zones (SEZs), spanning across advanced manufacturing, ICT & logistics.</span>
                </div>
            </div>

            <div class="announcement">
                <img class="image" src="https://investsaudi.sa/medias/meet-the-Kingdom-Banner.png?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwzNzY2NXxpbWFnZS9wbmd8cG9ydGFsLW1lZGlhL2g5My9oOWQvODkyMzM1OTkwMzc3NC5wbmd8ZDU4ZDE0NzE1OGM1YjBkMzMxMTA1MjUzZGFmNzI2OTMxYTBlMmNjNDZlMGQ3MmUyYmNmYjcwZGUwMDEyNzcxZg" alt="" loading="lazy">
                <div class="details">
                    <span class="date mt-2">16 Mar 2023</span>
                    <h3 class="headline">The Kingdom is embarking on a world-leading</h3>
                    <span class="description mb-2">The Kingdom is embarking on a world-leading development program to unlock new sectors and diversify the economy through five major Giga-projects, and several mega-projects.</span>
                </div>
            </div>

            <div class="announcement">
                <img class="image" src="https://investsaudi.sa/medias/economic-Highlights-Banner.png?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwyMzkzMXxpbWFnZS9wbmd8cG9ydGFsLW1lZGlhL2gzMi9oMWYvODkyMzM1OTgzODIzOC5wbmd8MGY1YzUxNWE5NmY2NzJiZTNkYWFiOGI2N2FmOGMxYzhjOGY5OTQ3NDgwMTcwMDdmNmMxMGJmYzYyMDVmNzVhYw" alt="" loading="lazy">
                <div class="details">
                    <span class="date mt-2">16 Mar 2023</span>
                    <h3 class="headline">Discover why Saudi Arabia</h3>
                    <span class="description mb-2">Discover why Saudi Arabia is rapidly becoming the preferred global investment destination, fueled by the growth of non-oil activities.</span>
                </div>
            </div>
        </div>
    </section>

    <section class="newsletterSection">
                <div class="container text-center get-updates-section">
                    <div class="row">
                        <div class="col-md-12">
                            <h2 class="get-updates-title">Get the Latest Updates</h2>
                            <button class="btn btn-primary join-newsletter-btn">Join Our Newsletter</button>
                        </div>
                    </div>
                </div>
            </section>
<section class="Inc-mediaCenter-sectionwrapper pb-3">
   <div class="container">
      <div class="row text-center col-md-12">
         <h2 class="newsTitle"><spring:theme code="text.newsevents.listing.page.media.library"/></h2>
      </div>
   </div>
   <div class="container">
      <div class="row">
         <div class="col-md-6">
            <div class="">
               <div class="">
                  <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="194px" src="https://www.youtube.com/embed/u3sQ7TDFUWs" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy">
                  </iframe>
               </div>
               <h4 class="videoTitle">Saudi investment ministry signs four investment agreements</h4>
               <span class="videoDate">25 Oct 2022</span>
            </div>
         </div>
         <div class="col-md-6">
            <div class="row">
               <div class="col-md-6">
                  <div>
                     <div class="">
                        <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="113" src="https://www.youtube.com/embed/u3sQ7TDFUWs" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy">
                        </iframe>
                     </div>
                  </div>
               </div>
               <div class="col-md-6">
                  <h4 class="videoTitle">Saudi investment ministry signs four investment agreements</h4>
                  <span class="videoDate">25 Oct 2022</span>
               </div>
               <div class="col-md-6">
                  <div class="video-player-container video-inner-div border-0 p-0">
                     <div class="embed-responsive embed-responsive-16by9 video-events">
                        <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="113" src="https://www.youtube.com/embed/u3sQ7TDFUWs" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" loading="lazy">
                        </iframe>
                     </div>
                  </div>
               </div>
               <div class="col-md-6">
                  <h4 class="videoTitle">Saudi investment ministry signs four investment agreements</h4>
                  <span class="videoDate">25 Oct 2022</span>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>
</div>
