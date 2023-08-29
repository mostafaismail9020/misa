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
			<div class="article-details-events-page-banner" style="background-image: url('/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ');">
				<div class="article-details-events-page-banner-container" data-aos="fade-up">
					<div class="container">
						<div class="row">

							<div class="col-md-8">
								<h1 class="article-details-events-page-general-title">Event Name</h1>
							</div>

							<div class="col-md-4" style="position: relative;">
								<div class="event-top-info-box">
									<div class="date" style="position: absolute;">
										<span class="day">30</span>
										<span class="month">DEC</span>
									</div>
									<h2 class="event-title">Event Name</h2>
									<p class="event-description">Enlit vows to light the spark that will fuel the change we need to ensure our industry - and our planet - have the brightest possible future.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Article Details Events Page -->
			<section class="article-details-events-page">
				<div class="container">
					<div class="row">
						<div class="col-md-6">
							<div class="event-time-location">
								<span class="event-time">12:00 PM - 01:00 PM</span>
								<span class="event-location">Location</span>
							</div>
							<h1 class="article-details-event-title">Event Name</h1>
							<p>Lorem ipsum dolor sit amet consectetur. Venenatis in habitant metus dolor. Id adipiscing eget enim tellus eleifend. Dignissim at et vel suspendisse.</p>

							<div class="event-info-box-container mt-5 mb-5">
								<div class="event-info-box">
									<div class="event-info-box-item">
										<h2>5</h2>
										<p>Sponsor</p>
									</div>
									<div class="event-info-box-item">
										<h2>+200</h2>
										<p>Attendance</p>
									</div>
									<div class="event-info-box-item">
										<h2>15</h2>
										<p>Speaker</p>
									</div>
									<div class="event-info-box-item">
										<h2>5</h2>
										<p>Topics Covered</p>
									</div>
								</div>
							</div>

						</div>
						<div class="col-md-6">
							<img alt="" class="article-details-events-page-event-img"
								 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>

						</div>
					</div>

					<div class="row mt-5 mb-5">
						<div class="col-md-12">
							<div class="subjects-container">
								<h1 class="title mb-3">Subjects</h1>
								<ul>
									<li><p><b>Subject Name: </b>Lorem ipsum dolor sit amet consectetur. Venenatis in
										habitant metus dolor. Id adipiscing eget enim tellus eleifend. Dignissim at et
										vel suspendisse.</p></li>
									<li><p><b>Subject Name: </b>Lorem ipsum dolor sit amet consectetur. Venenatis in
										habitant metus dolor. Id adipiscing eget enim tellus eleifend. Dignissim at et
										vel suspendisse.</p></li>
									<li><p><b>Subject Name: </b>Lorem ipsum dolor sit amet consectetur. Venenatis in
										habitant metus dolor. Id adipiscing eget enim tellus eleifend. Dignissim at et
										vel suspendisse.</p></li>
									<li><p><b>Subject Name: </b>Lorem ipsum dolor sit amet consectetur. Venenatis in
										habitant metus dolor. Id adipiscing eget enim tellus eleifend. Dignissim at et
										vel suspendisse.</p></li>

								</ul>
							</div>
						</div>
					</div>

					<div class="row mt-5 mb-5">
						<div class="col-md-12">
							<div class="highlighted-speakers-container">
								<h1 class="title mb-4">Highlighted Speakers</h1>
								<div class="highlighted-speakers">
									<div class="speaker">
										<h2>Ali Mohammad</h2>
										<p>Job name</p>
									</div>
									<div class="speaker">
										<h2>Ali Mohammad</h2>
										<p>Job name</p>
									</div>
									<div class="speaker">
										<h2>Ali Mohammad</h2>
										<p>Job name</p>
									</div>
									<div class="speaker">
										<h2>Ali Mohammad</h2>
										<p>Job name</p>
									</div>
									<div class="speaker">
										<h2>Ali Mohammad</h2>
										<p>Job name</p>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row mb-5 mt-5">
						<div class="sponsors-partners-container">
							<h1 class="title mb-3">Sponsors & Partners</h1>
							<div class="boxes">
								<div class="box">
									<div class="icon sectors-icon"></div>
									<p>Sectors</p>
								</div>
								<div class="box">
									<div class="icon news-icon"></div>
									<p>News</p>
								</div>
								<div class="box">
									<div class="icon events-icon"></div>
									<p>Events</p>
								</div>
								<div class="box">
									<div class="icon reports-icon"></div>
									<p>Reports</p>
								</div>
								<div class="box">
									<div class="icon services-icon"></div>
									<p>Services</p>
								</div>
								<div class="box">
									<div class="icon incentives-icon"></div>
									<p>Incentives</p>
								</div>
								<div class="box">
									<div class="icon guide-icon"></div>
									<p>Guide</p>
								</div>
							</div>
						</div>
					</div>


					<div class="similar-events mt-5 mb-5">
						<div class="row">
							<div class="col-md-12 mb-4">
								<h1 class="title">Similar Events</h1>
							</div>
							<div class="col-md-3">
								<div class="date" style="position: absolute;">
									<span class="day">30</span>
									<span class="month">DEC</span>
								</div>
								<div>
									<img class="similar-events-card-img" alt=""
										 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="similar-events-time-location">
									<span class="event-time">12:00 PM - 01:00 PM</span>
									<span class="event-location">Location</span>
								</div>
								<div class="similar-events-card-bottom">Saudi investment ministry signs four investment agreements</div>
							</div>
							<div class="col-md-3">
								<div class="date" style="position: absolute;">
									<span class="day">30</span>
									<span class="month">DEC</span>
								</div>
								<div>
									<img class="similar-events-card-img" alt=""
										 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="similar-events-time-location">
									<span class="event-time">12:00 PM - 01:00 PM</span>
									<span class="event-location">Location</span>
								</div>
								<div class="similar-events-card-bottom">Saudi investment ministry signs four investment agreements</div>
							</div>
							<div class="col-md-3">
								<div class="date" style="position: absolute;">
									<span class="day">30</span>
									<span class="month">DEC</span>
								</div>
								<div>
									<img class="similar-events-card-img" alt=""
										 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="similar-events-time-location">
									<span class="event-time">12:00 PM - 01:00 PM</span>
									<span class="event-location">Location</span>
								</div>
								<div class="similar-events-card-bottom">Saudi investment ministry signs four investment agreements</div>
							</div>
							<div class="col-md-3">
								<div class="date" style="position: absolute;">
									<span class="day">30</span>
									<span class="month">DEC</span>
								</div>
								<div>
									<img class="similar-events-card-img" alt=""
										 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
								</div>
								<div class="similar-events-time-location">
									<span class="event-time">12:00 PM - 01:00 PM</span>
									<span class="event-location">Location</span>
								</div>
								<div class="similar-events-card-bottom">Saudi investment ministry signs four investment agreements</div>
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
			<!-- Article Details Events Page -->
		</main>

	</jsp:body>

</template:portalpage>