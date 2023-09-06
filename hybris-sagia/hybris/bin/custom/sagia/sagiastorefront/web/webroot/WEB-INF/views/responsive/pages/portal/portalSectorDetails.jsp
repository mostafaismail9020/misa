<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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

			<div class="article-details-sector-page-banner">
				<div class="article-details-sector-page-banner-container" data-aos="fade-up">
					<div class="container">
						<div class="row">
							<div class="col-md-12 breadcrumb-container">
								<a href="${encodedContextPath}/sectors-opportunities/">
									<span class="breadcrumb-left-icon"></span>
									<span class="breadcrumb-page-info">Sectors</span>
								</a>
							</div>
							<div class="col-md-12">
								<h1 class="article-details-sector-page-general-title">
										${category.name}
								</h1>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal popup download now-->
			<div class="modal fade" id="downloadModal" tabindex="-1" role="dialog" aria-labelledby="downloadModalTitle" aria-hidden="true">
				<div class="modal-dialog opportunity-modal-dialog sector-modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title text-center clr_gld w-100" id="exampleModalLongTitle"><spring:theme code="portal.sector.download.label"/></h5>
							<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
							</button>
						</div>
						<div class="modal-body">
							<div id="popup-contact-form" class="contact-form pt-3" >
								<div class="form-row">
									<input type="hidden" class="form-control" name="buttonId" id="buttonId" />
									<div class="form-group col-md-12 form-normal-item">
										<label class="control-label" for="popup_crFirstName">
											<spring:theme code="portal.sector.download.firstName.label"/> <span class="mandatory">* </span>
										</label>
										<input type="text" class="form-control" name="popup_crFirstName" id="popup_crFirstName"/>
									</div>
									<div class="form-group form-floating col-md-12 form-normal-item">
										<label class="control-label" for="popup_crLastName">
											<spring:theme code="portal.sector.download.lastName.label"/> <span class="mandatory">* </span>
										</label>
										<input type="text" class="form-control" name="popup_crLastName" id="popup_crLastName"/>
									</div>
									<div class="form-group form-floating col-md-12 form-normal-item">
										<label class="control-label" for="popup_crEmail">
											<spring:theme code="portal.sector.download.email.label"/> <span class="mandatory">* </span>
										</label>
										<input type="text" class="form-control" name="popup_crEmail" id="popup_crEmail"/>
									</div>
									<div class="form-group form-floating col-md-12 form-normal-item">
										<label class="control-label" for="popup_crPhone">
											<spring:theme code="portal.sector.download.phoneNumber.label"/> <span class="mandatory">* </span>
										</label>
										<input type="text" class="form-control" name="popup_crPhone" id="popup_crPhone"/>
									</div>
									<div class="form-group form-floating col-md-12 form-normal-item">
										<label class="control-label" for="popup_crCompanyName">
											<spring:theme code="portal.sector.download.companyName.label"/> <span class="mandatory">* </span>
										</label>
										<input type="text" class="form-control" name="popup_crCompanyName" id="popup_crCompanyName"/>
									</div>
									<div class="form-check d-flex mb-3">
										<input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
										<label class="form-check-label mand-field-text" for="invalidCheck">
											<spring:theme code="portal.sector.download.invalidCheck.label"/>
											<a class="pvcy-policy" href="https://investsaudi.sa/en/privacy-policy">
												<spring:theme code="portal.sector.download.dataPolicy.label"/>
											</a>
										</label>
										<div class="invalid-feedback"><spring:theme code="portal.sector.download.agree.label"/></div>
									</div>
									<div class="form-group form-floating col-md-12 form-normal-item">
										<input type="hidden" id="recaptchaChallangeAnswered"
											   value="${requestScope.recaptchaChallangeAnswered}" />
										<div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>

									</div>
									<div class="opp-contact-buttons">
										<div class="btns">
											<button id="popup-btn-contact-cancel"  class="btn btn-primary-fill btn-form-outline w-100" data-dismiss="modal">
												<spring:theme code="portal.sector.download.cancel.button"/></button>
										</div>
										<div class="btns">
											<button id="popup-btn-contact" type="submit" class="btn btn-primary-fill btn-form-fill  w-100">
												<spring:theme code="portal.sector.download.submit.button"/></button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal popup download now *End-->

			<section class="article-details-sector-page">
				<div class="container">
					<div class="row mt-3 mb-3">

					</div>
					<div class="row mt-3 mb-3">
						<div class="col-md-9">
							<h1 class="article-details-sector-page-title">Overview</h1>
							<p>${category.sectorOverview}</p>

							<div class="article-details-sector-page-info-box-container mt-5 mb-5">
								<div class="article-details-sector-page-info-box">
									<c:forEach var="sectorFactsFigures" items="${category.sectorFactsFigures}">
										<div class="article-details-sector-page-info-box-item">
											<h2>${sectorFactsFigures.figures}${sectorFactsFigures.unit}</h2>
											<p>${sectorFactsFigures.facts}</p>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-md-3 article-details-sector-page-img">
							<img alt="" src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ" />
						</div>
					</div>
					<div class="row mt-3 mb-3">
						<c:if test="${category.categories.size() eq 0}">
							<div class="col-md-12">
								<div class="value-proposition-title mt-3 mb-3">
									<spring:theme code="portal.sector.value.proposition.label"/>
								</div>
							</div>
							<div class="col-md-12">
								<div class="value-proposition-items">
									<div class="value-proposition-item">
										<div class="chart-multiple"></div>
										<div class="value-proposition-text">
											Large and growing market demand for water
										</div>
									</div>
									<div class="value-proposition-item">
										<div class="building-home"></div>
										<div class="value-proposition-text">
											Global leader in desalination
										</div>
									</div>
									<div class="value-proposition-item">
										<div class="briefcase"></div>
										<div class="value-proposition-text">
											Strong commitment to develop infrastructure beyond
											desalination
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
					<c:if test="${not empty category.successStories}">
						<div class="row">
							<div class="col-md-12">
								<c:if test="${language eq 'en'}">
									<h1 class="success-stories-title">
										<spring:theme code="portal.sector.success.stories.label"/> the ${category.name}
									</h1>
								</c:if>
								<c:if test="${language eq 'ar'}">
									<h1 class="success-stories-title">
										<spring:theme code="portal.sector.success.stories.label"/> the ${category.name}
									</h1>
								</c:if>
							</div>
						</div>
						<div class="slider-carousel successstory-container">
							<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
								<div class="carousel-inner">
									<c:forEach var="successStories" items="${category.successStories}" varStatus="loopStatus">
										<div class="carousel-item">
											<div class="row">
												<div class="col-md-4 mt-3">
													<c:if test="${not empty successStories.videoLink}">
														<div class="success-stories-video">
															<iframe width="100%" src="${successStories.videoLink.url}"
																	frameborder="0"
																	allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
																	allowfullscreen></iframe>
														</div>
													</c:if>
												</div>
												<div class="col-md-8 mt-3">
													<c:if test="${not empty successStories.companyLogo}">
														<img class="js-responsive-image achievement_header_icon pb-0 icon_right"
															 src="${successStories.companyLogo.url}"
															 alt='${successStories.companyLogo.altText}'
															 title='${successStories.companyLogo.altText}' style="">
													</c:if>

													<c:if test="${not empty successStories.description}">
														<p class="core-components-content">${successStories.description}</p>
													</c:if>
													<c:if test="${not empty successStories.localizedStats}">
														<c:forEach var="entry" items="${successStories.localizedStats}">
															<p class="core-components-content">${entry.key}
																: ${entry.value}</p>
														</c:forEach>
													</c:if>
													<div class="success-stories-title">
														<h4>Look at other stories</h4>
													</div>
													<div class="success-stories-container">

														<div class="boxes">
															<div class="box">
																<a  href="#carouselExampleControls" role="button" data-slide="prev">
																	<div class="icon left-icon"></div>
																</a>

															</div>
															<div class="box">
																<div class="icon sectors-icon">${loopStatus.count}</div>

															</div>
															<div class="box">
																<div class="icon sectors-icon"></div>

															</div>
															<div class="box">
																<div class="icon sectors-icon"></div>

															</div>
															<div class="box">
																<a href="#carouselExampleControls" role="button" data-slide="next">
																	<div class="icon next-icon"></div>
																</a>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>


						</div>
					</c:if>
				</div>
			</section>

			<cms:pageSlot position="PortalPageMain" var="slotComponent">
				<cms:component component="${slotComponent}"/>
			</cms:pageSlot>

			<cms:pageSlot position="PortalPageBottom" var="slotComponent">
				<cms:component component="${slotComponent}"/>
			</cms:pageSlot>

			<div class="Inc-sector-panel">
				<h1 class="Inc-sector-panel-header"><spring:theme code="portal.sector.explore.other.label"/></h1>
				<div class="hexagon-portal">
					<c:forEach var="allCategories" items="${mainCategories}">
						<article class="item" id="${allCategories.code}">
							<figure>
								<a href="${encodedContextPath}/sectors-opportunities/${allCategories.code}">
									<img class="sector-item-icon" src="${fn:escapeXml(allCategories.logo.url)}"
										 data-norm="${fn:escapeXml(allCategories.logo.url)}"
										 data-alt="${fn:escapeXml(allCategories.logo.url)}" alt="" loading="lazy"/>
									<h2><c:out value="${allCategories.name}"/></h2>
								</a>
							</figure>

						</article>
					</c:forEach>
				</div>
				<c:forEach var="allCategories" items="${mainCategories}">
					<div class="test-item" id="${allCategories.code}" id="" data-slide-to="" style="background-image: url(${allCategories.banner.url});"></div>
				</c:forEach>
			</div>
		</main>

	</jsp:body>

</template:portalpage>
