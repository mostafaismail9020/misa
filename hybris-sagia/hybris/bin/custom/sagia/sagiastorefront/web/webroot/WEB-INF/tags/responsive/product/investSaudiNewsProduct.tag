<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>
	<div class="article-details-news-page-banner">
		<div class="article-details-news-page-banner-container" data-aos="fade-up">
			<div class="container">
				<div class="row">
					<div class="col-md-12 breadcrumb-container">
						<span class="breadcrumb-left-icon"></span>
						<a href="${encodedContextPath}/newseventslist">
							<span class="breadcrumb-page-info">News & Events</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Article Details News Page -->
	<section class="article-details-news-page">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md-12">

					<h1 class="article-details-news-title">${fn:escapeXml(productData.name)}</h1>
					<p>${productData.description}</p>

					<div class="article-details-news-video">
						<iframe width="100%" src="${productData.videoUrl}"
								allowfullscreen></iframe>
					</div>
					<p>${productData.summary}</p>

				</div>
			</div>


			<c:forEach items="${productData.subHeadings}" var="newsSubHeading">
				<div class="row mt-5">
					<div class="col-md-12">
						<h2 class="article-details-news-sub-heading mt-3 mb-3">${newsSubHeading.key}</h2>
						<p>${newsSubHeading.value}
						</p>
					</div>
				</div>
			</c:forEach>

			<c:forEach items="${productData.paraWithMedia}" var="paraWithMedia">

				<div class="row mt-5">
					<div class="col-md-9">
						<p>${paraWithMedia.value.descriptionText}</p>
					</div>
					<div class="col-md-3">
						<img class="article-details-news-img" alt="" src="${paraWithMedia.value.url}"/>
					</div>
				</div>
			</c:forEach>

			<div class="new-events mt-5 mb-5">
				<div class="row">
					<div class="col-md-12 mb-4">
						<h1 class="title">New Events</h1>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month">DEC</span>
						</div>
						<div>
							<img class="new-events-card-img" alt=""
								 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time">12:00 PM - 01:00 PM</span>
							<span class="event-location">Location</span>
						</div>
						<div class="new-events-card-bottom">Saudi investment ministry signs four investment
							agreements
						</div>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month">DEC</span>
						</div>
						<div>
							<img class="new-events-card-img" alt=""
								 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time">12:00 PM - 01:00 PM</span>
							<span class="event-location">Location</span>
						</div>
						<div class="new-events-card-bottom">Saudi investment ministry signs four investment
							agreements
						</div>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month">DEC</span>
						</div>
						<div>
							<img class="new-events-card-img" alt=""
								 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time">12:00 PM - 01:00 PM</span>
							<span class="event-location">Location</span>
						</div>
						<div class="new-events-card-bottom">Saudi investment ministry signs four investment
							agreements
						</div>
					</div>
					<div class="col-md-3">
						<div class="date" style="position: absolute;">
							<span class="day">30</span>
							<span class="month">DEC</span>
						</div>
						<div>
							<img class="new-events-card-img" alt=""
								 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
						</div>
						<div class="new-events-time-location">
							<span class="event-time">12:00 PM - 01:00 PM</span>
							<span class="event-location">Location</span>
						</div>
						<div class="new-events-card-bottom">Saudi investment ministry signs four investment
							agreements
						</div>
					</div>

					<div class="col-md-12 mt-4 navigation-icons">
						<span class="left-icon"></span>
						<span class="page-info">1 of 2</span>
						<span class="right-icon"></span>
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
