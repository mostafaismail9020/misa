<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>


	<div class="article-details-news-page-banner" style="background-image: url(${fn:escapeXml(productData.overviewPicture.url)});">
        <div class="article-details-news-page-banner-container" data-aos="fade-up">

        </div>
    </div>

	<!-- Article Details News Page -->
	<section class="article-details-news-page">
		<div class="container">
		 <div class="row mt-2">

             <c:if test="${language eq 'en'}">
                  <a href="${encodedContextPath}/newseventslist">
                     <div class="col-md-12 mt-4 breadcrumb-container">
                         <span class="breadcrumb-left-icon"></span>
                       <span class="breadcrumb-page-info"> <spring:theme code="dashboard.newsevents" /></span>
                     </div>
                 </a>
             </c:if>

             <c:if test="${language eq 'ar'}">
                  <a href="${encodedContextPath}/newseventslist">
                     <div class="col-md-12 mt-4 breadcrumb-container">
                        <span class="breadcrumb-page-info"> <spring:theme code="dashboard.newsevents" /></span>
                         <span class="breadcrumb-left-icon"></span>
                     </div>
                 </a>
             </c:if>

         </div>
			<div class="row mt-2">
				<div class="col-md-12">

					<h1 class="article-details-news-title">${fn:escapeXml(productData.name)}</h1>

                    <p>${fn:escapeXml(productData.summary)}</p>
					<div class="article-details-news-video">
						<iframe width="100%" src="${productData.videoUrl}"
								allowfullscreen></iframe>
					</div>
					<p>${productData.description}</p>

				</div>
			</div>


			<c:forEach items="${productData.subHeadings}" var="newsSubHeading">
				<div class="row mt-2">
					<div class="col-md-12">
						<h2 class="article-details-news-sub-heading mt-3 mb-3">${fn:escapeXml(newsSubHeading.key)}</h2>
						<p>${fn:escapeXml(newsSubHeading.value)}
						</p>
					</div>
				</div>
			</c:forEach>

			<c:forEach items="${productData.paraWithMedia}" var="paraWithMedia">

				<div class="row mt-2">
					<div class="col-md-9">
						<p>${fn:escapeXml(paraWithMedia.value.descriptionText)}</p>
					</div>
					<div class="col-md-3">
						<img class="article-details-news-img" alt="" src="${paraWithMedia.value.url}"/>
					</div>
				</div>
			</c:forEach>

			<div class="new-events mt-2">
				<div class="row">
					<div class="col-md-12 mb-4">
						<h1 class="title"><spring:theme code="text.newsevents.listing.page.news.events.title" /></h1>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month"><spring:theme code="text.newsevents.listing.page.news.events.month" /></span>
						</div>
						<div>
							<img class="new-events-card-img" alt=""
								 src="https://investsaudi.sa/medias/real-estate-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw0MzUxNDh8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDI2L2hjMi84OTIzMjkwNTAxMTUwLmpwZ3xmMWRhMGJhMTI4Y2E2MTY2YmM1ZDBjNzlmOTdiNGQzZGZkMDI3ZmQ1YjMwNDk0MGY2YzJlYzBlMGEwYTMwOWMx"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time"><spring:theme code="text.newsevents.listing.page.news.events.time"/></span>
							<span class="event-location"><spring:theme code="text.newsevents.listing.page.news.events.location" /></span>
						</div>
						<div class="new-events-card-bottom"><spring:theme code="text.newsevents.listing.page.media.library.title" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month"><spring:theme code="text.newsevents.listing.page.news.events.month" /></span>
						</div>
						<div>
								 <img class="new-events-card-img" alt="" src="https://investsaudi.sa/medias/real-estate-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw0MzUxNDh8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDI2L2hjMi84OTIzMjkwNTAxMTUwLmpwZ3xmMWRhMGJhMTI4Y2E2MTY2YmM1ZDBjNzlmOTdiNGQzZGZkMDI3ZmQ1YjMwNDk0MGY2YzJlYzBlMGEwYTMwOWMx"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time"><spring:theme code="text.newsevents.listing.page.news.events.time" /></span>
							<span class="event-location"><spring:theme code="text.newsevents.listing.page.news.events.location" /></span>
						</div>
						<div class="new-events-card-bottom"><spring:theme code="text.newsevents.listing.page.media.library.title" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month"><spring:theme code="text.newsevents.listing.page.news.events.month" /></span>
						</div>
						<div>
								<img class="new-events-card-img" alt=""
                            								 src="https://investsaudi.sa/medias/real-estate-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw0MzUxNDh8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDI2L2hjMi84OTIzMjkwNTAxMTUwLmpwZ3xmMWRhMGJhMTI4Y2E2MTY2YmM1ZDBjNzlmOTdiNGQzZGZkMDI3ZmQ1YjMwNDk0MGY2YzJlYzBlMGEwYTMwOWMx"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time"><spring:theme code="text.newsevents.listing.page.news.events.time" /></span>
							<span class="event-location"><spring:theme code="text.newsevents.listing.page.news.events.location" /></span>
						</div>
						<div class="new-events-card-bottom"><spring:theme code="text.newsevents.listing.page.media.library.title" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month"><spring:theme code="text.newsevents.listing.page.news.events.month" /></span>
						</div>
						<div>
								<img class="new-events-card-img" alt=""
                            								 src="https://investsaudi.sa/medias/real-estate-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw0MzUxNDh8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDI2L2hjMi84OTIzMjkwNTAxMTUwLmpwZ3xmMWRhMGJhMTI4Y2E2MTY2YmM1ZDBjNzlmOTdiNGQzZGZkMDI3ZmQ1YjMwNDk0MGY2YzJlYzBlMGEwYTMwOWMx"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time"><spring:theme code="text.newsevents.listing.page.news.events.time" /></span>
							<span class="event-location"><spring:theme code="text.newsevents.listing.page.news.events.location" /></span>
						</div>
						<div class="new-events-card-bottom"><spring:theme code="text.newsevents.listing.page.media.library.title" />
						</div>
					</div>

					<div class="col-md-12 mt-2 navigation-icons">
                        <c:if test="${language eq 'en'}">
                           <span class="left-icon"></span>
                            <span class="page-info">1 of 2</span>
                            <span class="right-icon"></span>
                        </c:if>
                        <c:if test="${language eq 'ar'}">
                          <span class="right-icon"></span>
                        <span class="page-info">1 of 2</span>
                        <span class="left-icon"></span>
                        </c:if>
					</div>

				</div>
			</div>

		</div>
	</section>
	<!-- Article Details News Page -->
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
