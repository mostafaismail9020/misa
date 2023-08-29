<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:portalpage pageTitle="${pageTitle}">

	<jsp:body>
		<!--
		<script>
		document.querySelector("html").style.cssText = 'background:#000!important';
		document.querySelector("body").style.cssText += 'opacity:0!important;display:block!important;';
		window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
		window['_sfw_script'] = 'sf_ab.min.js?v=1.0.4';
		window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
		(function(s,e,ss,i,o,n){
		if(s.console && s.console.log) { s.console.log(i);};
		o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
		n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
		})(window,document,'script','SessionForward Loaded.');
		</script>
		-->

		<header:portalPageTitle/>

		<cms:pageSlot position="PortalPageTop" var="slotComponent">
			<cms:component component="${slotComponent}"/>
		</cms:pageSlot>

		<main>
			<div class="article-details-news-page-banner">
				<div class="article-details-news-page-banner-container" data-aos="fade-up">
					<div class="container">
						<div class="row">
							<div class="col-md-12 breadcrumb-container">
								<span class="breadcrumb-left-icon"></span>
								<span class="breadcrumb-page-info">News & Events</span>
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

							<h1 class="article-details-news-title">Four investment agreements</h1>
							<p>
								The Ministry of Investment of Saudi Arabia (MISA) has facilitated four more investment
								agreements on the sidelines of the Future Investment Initiative (FII6) across the
								education, entertainment and biotechnology sectors. The agreements signed showcase the
								government’s commitment to transforming quality of life in the Kingdom, coupled with
								investor recognition of the vast opportunities available.
							</p>
							<div class="article-details-news-video">
								<iframe width="100%" src="https://www.youtube.com/embed/jP-z4m92UAE"
										allowfullscreen></iframe>
							</div>
							<p>
								Riyadh, Saudi Arabia, October 26, 2022: The Ministry of Investment of Saudi Arabia
								(MISA) has facilitated four more investment agreements on the sidelines of the Future
								Investment Initiative (FII6) across the education, entertainment and biotechnology
								sectors.
								The agreements signed showcase the government’s commitment to transforming quality of
								life in the Kingdom, coupled with investor recognition of the vast opportunities
								available.
								MISA signed three agreements in the education and SME sectors with world-class
								institutions:
								* An agreement with the prestigious Paris-based ESSEC Business School to collaborate
								with the Ministry of Tourism in providing human capability development programs at their
								campus in Paris, and a collaboration agreement with the King Abdulaziz and King Saud
								Universities for a student exchange program; and
								* An investment agreement with global real estate and private equity firm SAFANAD and
								education platform Global School Management for US$200m in the education sector; and
								* An agreement with King Abdulaziz City for Science and Technology and US biotechnology
								company Illumina to set up a genome training program, establish an accelerator hub for
								SMEs in the genomics industry, and support the development of genomic databases and
								research activities.
							</p>

						</div>
					</div>

					<div class="row mt-5">
						<div class="col-md-12">

							<h2 class="article-details-news-sub-heading mt-3 mb-3">Saudi Arabia's education market</h2>
							<p>
								The Ministry of Investment of Saudi Arabia (MISA) has facilitated four more investment
								agreements on the sidelines of the Future Investment Initiative (FII6) across the
								education, entertainment and biotechnology sectors. The agreements signed showcase the
								government’s commitment to transforming quality of life in the Kingdom, coupled with
								investor recognition of the vast opportunities available.
							</p>
						</div>
					</div>

					<div class="row mt-5">
						<div class="col-md-12">
							<h2 class="article-details-news-sub-heading mt-3 mb-3">Core Component</h2>
							<p>A core component of the strategy is the Global Supply Chain Resilience Initiative –
								launched on Sunday. The initiative aims to enable global investors to create low-risk,
								low-cost and low-carbon supply chains leveraging the Kingdom’s natural resources, strong
								logistics infrastructure and untapped potential. In its launch phase, the initiative
								aims to attract more than US$10bn of industrial and service investments in global supply
								chains to the Kingdom.
							</p>
						</div>
					</div>

					<div class="row mt-5">
						<div class="col-md-9">
							<p>Saudi Arabia has seen strong growth in foreign direct investment (FDI) in recent years as
								the Kingdom’s economic reforms have unlocked a broad range of opportunities for
								international investors. Last year net FDI growth increased by an unprecedented 257.2%,
								with inflows totalling almost US$20bn for the year – the highest they have been in 10
								years. You can find the Ministry’s Q3 2022 Investment Highlights report here.
								According to the International Monetary Fund, Saudi Arabia is expected to be the
								fastest-growing G20 economy this year, due in part to sweeping pro-business reforms,
								with gross domestic product expected to expand by 7.6 percent, the fastest growth in
								almost a decade.
							</p>
						</div>
						<div class="col-md-3">
							<img class="article-details-news-img" alt=""
								 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>

						</div>

					</div>

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

	</jsp:body>

</template:portalpage>