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
			<div class="article-details-page-banner">
				<div class="article-details-page-banner-container" data-aos="fade-up">
					<div class="container">
						<div class="row">
							<div class="col-md-12 breadcrumb-container">
								<span class="breadcrumb-left-icon"></span>
								<span class="breadcrumb-page-info">Services</span>
							</div>
							<div class="col-md-12">
								<h1 class="article-detail-page-general-title">General Article</h1>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Article Details Page -->
			<section class="article-details-page">
				<div class="container">
					<!-- Top -->
					<div class="row">
						<div class="col-md-12">
							<h1 class="article-detail-page-title">Article Name</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<p>The Ministry of Investment of Saudi Arabia (MISA) has facilitated four more investment
								agreements on the sidelines of the Future Investment Initiative (FII6) across the
								education, entertainment and biotechnology sectors. The agreements signed showcase the
								government’s commitment to transforming quality of life in the Kingdom, coupled with
								investor recognition of the vast opportunities available.</p>
							<p>The Ministry of Investment of Saudi Arabia (MISA) has facilitated four more investment
								agreements on the sidelines of the Future Investment Initiative (FII6) across the
								education, entertainment and biotechnology sectors. The agreements signed showcase the
								government’s commitment to transforming quality of life in the Kingdom, coupled with
								investor recognition of the vast opportunities available.</p>

						</div>
						<div class="col-md-6 article-detail-page-video">
							<iframe width="100%" src="https://www.youtube.com/embed/jP-z4m92UAE"
									title="TAP INTO THE UNTAPPED   Invest Saudi  20200722 1000 1  ICT" frameborder="0"
									allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
									allowfullscreen></iframe>
						</div>
					</div>

					<!-- Our Goal and Mission -->
					<div class="row mt-5 mb-5 our-goal-and-mission">
						<div class="col-md-6 center-content our-goal">
							Our Goal
						</div>
						<div class="col-md-6"></div>
					</div>
					<div class="row mt-5 mb-5 our-goal-and-mission">
						<div class="col-md-6"></div>
						<div class="col-md-6 center-content our-mission">
							Our Mission
						</div>
					</div>

					<!-- Paragraph -->
					<div class="row mt-4 mb-4">
						<div class="col-md-12">
							<p>The Ministry of Investment of Saudi Arabia (MISA) has facilitated four more investment
								agreements on the sidelines of the Future Investment Initiative (FII6) across the
								education,
								entertainment and biotechnology sectors. The agreements signed showcase the government’s
								commitment to transforming quality of life in the Kingdom, coupled with investor
								recognition
								of the vast opportunities available.</p>
						</div>
					</div>

					<!-- Our Values -->
					<div class="our-values mt-4">
						<div class="row">
							<div class="col-md-12 mb-4">
								<h1 class="article-detail-page-subtitle">OUR VALUES</h1>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<img class="our-values-img" alt=""
									 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>

							</div>
							<div class="col-md-6">
								<div class="value-item">
									<div class="value-icon"></div>
									<div class="value-content">
										<h2>Wealth</h2>
										<p>A powerful G20 economy with macroeconomic fundamentals that offer long-term
											stability and provide a sustainable platform for investment.</p>
									</div>
								</div>

								<div class="value-item">
									<div class="value-icon"></div>
									<div class="value-content">
										<h2>Transparency</h2>
										<p>Enabling a raft of business reforms aimed at easing investment and bolstering
											investor support in the Kingdom.</p>
									</div>
								</div>
								<div class="value-item">
									<div class="value-icon"></div>
									<div class="value-content">
										<h2>Opportunity</h2>
										<p>Creating unique opportunities in new sectors in the Kingdom that have
											hitherto been restricted to international investment.</p>
									</div>
								</div>
							</div>
						</div>
						<div class="row mt-2">
							<div class="col-md-6">
								<p>A core component of the strategy is the Global Supply Chain Resilience Initiative –
									launched on Sunday. The initiative aims to enable global investors to create
									low-risk, low-cost and low-carbon supply chains leveraging the Kingdom’s natural
									resources, strong logistics infrastructure and untapped potential. In its launch
									phase, the initiative aims to attract more than US$10bn of industrial and service
									investments in global supply chains to the Kingdom.</p>
							</div>
							<div class="col-md-6">
								<img class="our-values-img" alt=""
									 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>

							</div>
						</div>
					</div>

					<!-- Ways to Meet Expectations -->
					<div class="ways-to-meet-expectations mt-4 mb-4">
						<div class="row">
							<div class="col-md-12 mb-4">
								<h1 class="article-detail-page-subtitle">Ways to meet our expectations:</h1>
								<ul>
									<li>Large and growing market demand for water</li>
									<li>Global leader in desalination</li>
									<li>Strong commitment to develop infrastructure beyond desalination</li>
								</ul>
								<p>A core component of the strategy is the Global Supply Chain Resilience Initiative –
									launched on Sunday. The initiative aims to enable global investors to create
									low-risk, low-cost and low-carbon supply chains leveraging the Kingdom’s natural
									resources, strong logistics infrastructure and untapped potential. In its launch
									phase, the initiative aims to attract more than US$10bn of industrial and service
									investments in global supply chains to the Kingdom.</p>
							</div>
						</div>
					</div>

					<!-- What We Do -->
					<div class="what-we-do">
						<div class="row">
							<div class="col-md-12 mb-4">
								<h1 class="article-detail-page-subtitle">What We Do?</h1>
							</div>
							<div class="col-md-12 mb-4">
								<p>A core component of the strategy is the Global Supply Chain Resilience Initiative –
									launched on Sunday. The initiative aims to enable global investors to create
									low-risk, low-cost and low-carbon supply chains leveraging.</p>
							</div>

							<!-- First Box -->
							<div class="col-md-12 boxes-container">
								<div class="col-xs-12 col-sm-12 col-md-5 mb-4">
									<div class="what-we-do-box">
										<div class="number-box">1</div>
										<div class="box-content">
											<p class="title">Warehouse Automation</p>
											<p>Localization of warehouse automation technology (e.g., goods-to-person,
												sortation systems) to improve efficiency of existing warehousing
												infrastructure</p>
										</div>
									</div>
								</div>

								<!-- Second Box -->
								<div class="col-xs-12 col-sm-12 col-md-5 mb-4">
									<div class="what-we-do-box">
										<div class="number-box">2</div>
										<div class="box-content">
											<p class="title">Warehouse Automation</p>
											<p>Localization of warehouse automation technology (e.g., goods-to-person,
												sortation systems) to improve efficiency of existing warehousing
												infrastructure.</p>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12 mb-4">
								<p>A core component of the strategy is the Global Supply Chain Resilience Initiative –
									launched on Sunday. The initiative aims to enable global investors to create
									low-risk, low-cost and low-carbon supply chains leveraging the Kingdom’s natural
									resources, strong logistics infrastructure and untapped potential. In its launch
									phase, the initiative aims to attract more than US$10bn of industrial and service
									investments in global supply chains to the Kingdom.</p>
							</div>

						</div>
					</div>

					<!-- Download, Share Buttons-->
					<div class="download-share-buttons">
						<div class="row">
							<div class="col-md-12">
								<button class="btn-download">
									Download
								</button>
								<button class="btn-share">
									<i class="icon-share"></i> Share
								</button>
							</div>
						</div>
					</div>

					<!-- Recent Projects -->
					<div class="recent-projects mt-4">
						<div class="row">
							<div class="col-md-12 mb-4 article-detail-page-title-container">
								<h1 class="article-detail-page-title">Recent Projects</h1>
								<button class="see-all-btn">
									See All
								</button>
							</div>
							<div class="col-md-3">
								<div>
									<img class="recent-projects-card-img" alt=""
										 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
							</div>
							<div class="col-md-3">
								<div><img class="recent-projects-card-img" alt=""
										  src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
							</div>
							<div class="col-md-3">
								<div><img class="recent-projects-card-img" alt=""
										  src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
							</div>
							<div class="col-md-3">
								<div><img class="recent-projects-card-img" alt=""
										  src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
							</div>
						</div>
					</div>

				</div>
			</section>
			<!-- Article Details Page -->


		</main>

	</jsp:body>

</template:portalpage>