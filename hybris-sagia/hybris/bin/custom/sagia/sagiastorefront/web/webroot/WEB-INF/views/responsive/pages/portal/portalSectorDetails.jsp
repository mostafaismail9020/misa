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
		<div class="Inc-sector-banner" style="background-image: url(${categoryBanner.url});">
			<div class="Inc-sector-banner-container" data-aos="fade-up">
				<div class="Inc-section-baner-logo"><img class="img-fluid" src="${categoryLogo.url}" alt=""/></div>
				<h1>${category.name}</h1>
				<div class="Inc-baner-btnwraper">
					<!-- 
					<button class="btn btn-sector-secondary open-popup-contact-form" data-toggle="modal" id="download" data-target="#downloadModal">
 						<img class="img-fluid download-icon" src="${commonResourcePath}/images/download.png" alt=""/>
						<spring:theme code="portal.sector.download.label"/>
					</button>
					 -->
					<button class="btn btn-sector-secondary"  id="i-am-interested">
						<spring:theme code="portal.sector.iaminterested.label"/>
						<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
					</button>
					<button class="btn btn-sector-primary">
						<a href="/en/investsaudi-login">
							<spring:theme code="portal.sector.investnow.label"/>
						 	<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
						</a>
					</button>
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

		
		<section class="Inc-sectordetail-overview">
			<div class="container "><div class="row text-center">${category.sectorOverview}</div></div>
		</section>
         					
		<c:if test="${category.categories.size() eq 0}">	
			<div class="Inc-sectordetails-wrapper Inc-sectordetail-nosubsector">
				<div class="Inc-sector-explorer">
					<div class="container">
						<div class="row">
							<c:if test="${language eq 'en'}">
								<h1 class="exp-title"><spring:theme code="portal.sector.explore.saudi.arabia.label"/></h1>
								<h1 class="sub-title">${category.name}&nbsp;<spring:theme code="portal.sector.explore.name.label"/></h1>
							</c:if>
							<c:if test="${language eq 'ar'}">
								<h1 class="exp-title"><spring:theme code="portal.sector.explore.saudi.arabia.label1"/></h1>
								<h1 class="sub-title">${category.name}&nbsp;<spring:theme code="portal.sector.explore.saudi.arabia.label2"/></h1>
							</c:if>
						</div>
					</div>
				</div>
				<div class="banner-item resBannerHide">
					<img class="img-fluid w-100 " src="${category.thumbnail.url}" alt=""/>
				</div>	
				<div class="container Inc-nosubsector-keyfacts">
					<div class="Inc-sector-keyFacts" >
						<ul class="keyFacts">			
							<c:forEach var="sectorFactsFigures" items="${category.sectorFactsFigures}">     
								<li>
									<span class="unit">${sectorFactsFigures.figures}${sectorFactsFigures.unit}</span> <br/> ${sectorFactsFigures.facts}
								</li>					
							</c:forEach>
						</ul>
					</div>						
				</div>
													
				<div class="container-fluid" id="sector-value-proposition">
					<div class="row">
						<div class="container">
							<div class="col-md-10 m-auto">
								<div class="val-header">
									<h1><spring:theme code="portal.sector.value.proposition.label"/></h1>
								</div>
								<div class="Inc-sector-keyDetails">
									${category.description}
								</div>
							</div>
						</div>			
					</div>
				</div>		
			</div>
		</c:if>
            
        <c:if test="${category.categories.size() gt 0}">
			<div class="Inc-sectordetails-wrapper">
				<div class="Inc-sector-explorer">
					<div class="container">
						<div class="row">
							<c:if test="${language eq 'en'}">
								<h1 class="exp-title"><spring:theme code="portal.sector.explore.saudi.arabia.label"/></h1>
								<h1 class="sub-title">${category.name}&nbsp;<spring:theme code="portal.sector.explore.name.label"/></h1>
							</c:if>
							<c:if test="${language eq 'ar'}">
								<h1 class="exp-title"><spring:theme code="portal.sector.explore.saudi.arabia.label1"/></h1>
								<h1 class="sub-title">${category.name}&nbsp;<spring:theme code="portal.sector.explore.saudi.arabia.label2"/></h1>
							</c:if>
						</div>
					</div>
				</div>
				
				<!-- <div class="container" id="fact-tabsItems">
					<div class="Inc-sectordetails-tab">
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
							<c:forEach var="subCategories" items="${category.categories}">
								<li class="nav-item"><a class="nav-link" href="#">${subCategories.name}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div> -->
				<div class="container sector-opportunities-title" id="fact-tabsItems">
					<div class="Inc-sectordetails-tab">
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
							<c:forEach var="subCategories" items="${category.categories}" varStatus="loop">
								<li class="nav-item"><a class="nav-link" href="#pane-${loop.index}" id="pills-sector-tab-${loop.index}" data-toggle="pill" aria-selected="true">${subCategories.name}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="sector-tab-content sector-opp-card" id="pills-sector-tabContent" role="tablist">
					<c:forEach var="subCategories" items="${category.categories}" varStatus="loop">
						<div id="pane-${loop.index}" class="tab-items card tab-pane fade show" role="tabpanel" aria-labelledby="pills-sector-tab-${loop.index}">
							<div class="card-header" role="tab" id="heading-sector-${loop.index}">
								<a class="nav-link sector-link-header" data-toggle="collapse" href="#collapse-${loop.index}" aria-expanded="true" aria-controls="collapse-${loop.index}"> ${subCategories.name}</a>
							</div>
							<div id="collapse-${loop.index}" class="collapse" role="tabpanel" data-parent="#pills-sector-tabContent" aria-labelledby="heading-sector-${loop.index}">
								<div class="banner-item responsive-banner-fact"><img class="img-fluid w-100" src="${subCategories.thumbnail.url}" alt="" loading="lazy"/></div>
								<div id="fact-tab-content">
									<div class="container">
										<div class="col-md-10 m-auto">
											<div class="Inc-sectoritem-overview">${subCategories.sectorOverview}</div>
										</div>
									</div>
									<c:forEach var="sectorKeyDetails" items="${subCategories.sectorKeyDetails}">
										<div class="container">
											<div class="Inc-sector-keyFacts">
												<c:set var="keyFact1" value="${sectorKeyDetails.sectorFactsFiguresList[0]}"/>
												<c:set var="keyFact2" value="${sectorKeyDetails.sectorFactsFiguresList[1]}"/>
												<ul class="keyFacts">
													<li><span class="unit">${keyFact1.figures}${keyFact1.unit}</span> <br/> ${keyFact1.facts}</li>
													<li><span class="unit">${keyFact2.figures}${keyFact2.unit}</span> <br/> ${keyFact2.facts}</li>
												</ul>
											</div>
										</div>
										<div class="container-fluid" id="sector-value-proposition">
											<div class="row">
												<div class="container">
													<div class="col-md-10 m-auto">
														<div class="val-header"><h1><spring:theme code="portal.sector.value.proposition.label"/></h1></div>
														<div class="Inc-sector-keyDetails">${sectorKeyDetails.bulletPoints}</div>
														<div class="val-item-buttons">
															<button class="btn btn-sector-primary my-5">
																<a href="${encodedContextPath}/sectors-opportunities/${subCategories.code}">
																	<spring:theme code="portal.sector.opportunity.know.more.label"/>
																	<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
																</a>
															</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
									<c:forEach items="${category.subHeadings1}" var="subHeadings1">
										<div class="row">
											${subHeadings1.key}
										</div>
						    			<div class="row">
						    			${subHeadings1.descriptionText}	
						    			</div>
						    		</c:forEach>
									<c:forEach items="${category.subHeadings2}" var="subHeadings2">
										<div class="row">
											${subHeadings2.key}
										</div>
						    			<div class="row">
						    			${subHeadings2.descriptionText}	
						    			</div>
						    		</c:forEach>
						   			<c:forEach items="${category.paraWithMedia}" var="paraWithMedia">
										<div class="row">
											${category.paraWithMedia.key}
										</div>
						    			<div class="row">
						    			<div class="col-md-4 col-12">
						    				<iframe src="${paraWithMedia.value.url}"></iframe>
						    			</div>
						    			<div class="col-md-8 col-12">
						    			${paraWithMedia.value.descriptionText}	
						    			</div>
						    	    </div>
						   			</c:forEach>
								</div>	
							</div>										
						</div>
					</c:forEach>
				</div>

				<!-- <div class="sector-tab-content">
					<c:forEach var="subCategories" items="${category.categories}">
						<div class="tab-items">
							<div class="banner-item responsive-banner-fact"><img class="img-fluid w-100" src="${subCategories.thumbnail.url}" alt=""/></div>
							<div id="fact-tab-content">
								<div class="container">
									<div class="col-md-10 m-auto">
										<div class="Inc-sectoritem-overview">${subCategories.sectorOverview}</div>
									</div>
								</div>
								<c:forEach var="sectorKeyDetails" items="${subCategories.sectorKeyDetails}">
									<div class="container">
										<div class="Inc-sector-keyFacts">
											<c:set var="keyFact1" value="${sectorKeyDetails.sectorFactsFiguresList[0]}"/>
											<c:set var="keyFact2" value="${sectorKeyDetails.sectorFactsFiguresList[1]}"/>
											<ul class="keyFacts">
												<li><span class="unit">${keyFact1.figures}${keyFact1.unit}</span> <br/> ${keyFact1.facts}</li>
												<li><span class="unit">${keyFact2.figures}${keyFact2.unit}</span> <br/> ${keyFact2.facts}</li>
											</ul>
										</div>
									</div>
									<div class="container-fluid" id="sector-value-proposition">
										<div class="row">
											<div class="container">
												<div class="col-md-10 m-auto">
													<div class="val-header"><h1>Value proposition</h1></div>
													<div class="Inc-sector-keyDetails">${sectorKeyDetails.bulletPoints}</div>
													<div class="val-item-buttons">
														<button class="btn btn-sector-primary my-5">
															<a href="${encodedContextPath}/sectors-opportunities/${subCategories.code}">
																<spring:theme code="portal.sector.opportunity.know.more.label"/>
																<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
															</a>
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>											
						</div>
					</c:forEach>
				</div> -->
			</div>
		</c:if>		
		                        
        <cms:pageSlot position="PortalPageMain" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>
        
        <c:if test="${not empty category.successStories}">
	        <section class="Inc-sector-sucessStories">
	        	<c:if test="${language eq 'en'}">
		        	<h1 class="Inc-sector-sucessStories-header"><span class="clr_gld"><spring:theme code="portal.sector.success.stories.label"/>&nbsp;
		        			</span><br/>${category.name}&nbsp;<spring:theme code="portal.sector.explore.name.label"/>
		        	</h1>
	        	</c:if>
	        	<c:if test="${language eq 'ar'}">
	        		<h1 class="Inc-sector-sucessStories-header">
	        			<span class="clr_gld"><spring:theme code="portal.sector.success.stories.label"/>&nbsp;${category.name}</span><br/>
	        		</h1>
	        	</c:if>
				<div class="slider-carousel successstory-container">
					<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner">
						<c:forEach var="successStories" items="${category.successStories}">	
							<div class="carousel-item">
								<div class="success_story_component container-fluid success-main-content pl-0">
									<div class="row sucess_section">
										<div class="col-md-5 p-md-0 sucess_img_bg aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
											<div class="quote-wrapper">
												<div class="sucess_img ">						                    
													<c:if test="${not empty successStories.videoLink}">
														<div class="video-player-container">
															<div class="embed-responsive embed-responsive-16by9">
																<iframe width="560" height="315" src="${successStories.videoLink.url}" 
																		title="YouTube video player" frameborder="0" 
																		allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
																		allowfullscreen loading="lazy"></iframe>
															</div>
														</div>
													</c:if>
												</div>
											</div>
										</div>
										<div class="col-md-7" data-aos="fade-up" data-aos-delay="600">
											<div class="sucess_content">
												<div class="sucess_content_itenlogo position-relative">
													<c:if test="${not empty successStories.companyLogo}">
														<img class="js-responsive-image achievement_header_icon pb-0 icon_right" 
																src="${successStories.companyLogo.url}" alt='${successStories.companyLogo.altText}' 
																title='${successStories.companyLogo.altText}' style="">
													</c:if>
												</div>
												<c:if test="${not empty successStories.description}">
													<p>${successStories.description}</p>
												</c:if>
												<c:if test="${not empty successStories.localizedStats}">
													<c:forEach var="entry" items="${successStories.localizedStats}">
														<p>${entry.key} : ${entry.value}</p>
													</c:forEach>
												</c:if>
											</div>
										</div>
									</div>
								</div>		
							</div>	
						</c:forEach>
						</div>
						<div class="sucess-slider-contols meet-kingdom-control">
							<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
								<img src="${commonResourcePath}/images/prev.svg" alt="" />
								<span class="sr-only">Previous</span>
							</a>
							<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
								<img src="${commonResourcePath}/images/next.svg" alt="" />
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</div> 
			</section>
		</c:if>
				
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
