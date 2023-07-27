<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>
	<!-- <div class="Inc-sector-banner" style="background-image: url(${productData.banner.url});"> -->
	
	<div class="Inc-sector-banner">
		<div class="Inc-sector-banner-container" data-aos="fade-up">
			<!-- <div class="Inc-section-baner-logo">
				<img src="${productData.logo.url}" alt="${fn:escapeXml(productData.name)}" title="${fn:escapeXml(productData.name)}"/>
			</div> -->
			<h1>${fn:escapeXml(productData.name)}</h1>

			<c:if test="${true}"><!-- test, if kpis are available-->
				<div class="row kpi-row">
					<div class="kpi">
						<div class="kpi-value">XX%</div>
						<div class="kpi-postfix">TODO actual data</div>
					</div>
					<div class="kpi">
						<div class="kpi-value">$X</div>
						<div class="kpi-postfix">TODO actual data</div>
					</div>
				</div>
			</c:if>
			<div class="Inc-baner-btnwraper">
				<!--  
				<button class="btn btn-sector-secondary open-popup-contact-form" id="download" data-toggle="modal" data-target="#downloadModal">
					<img class="img-fluid download-icon" src="${commonResourcePath}/images/download.png" alt=""/> 
					<spring:theme code="portal.sector.download.label"/>
				</button>
				-->

				<!-- 
				<button class="btn btn-sector-secondary" id="i-am-interested">
					<spring:theme code="portal.sector.iaminterested.label"/> 
					<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
				</button>
				-->
				<button class="btn btn-sector-primary">
					<a href="/en/investsaudi-login">
						<spring:theme code="portal.sector.investnow.label"/>
						<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
					</a>
				</button>
				<c:if test="${not empty productData.pdfUrl}">
					<!--<p class="text-center" style="width: 100%">-->
					<a rel="noopener" id="pdfDownloadTrigerrer" href="${productData.pdfUrl}" target="_blank" download></a>
						<a rel="noopener"  id="download" 
								data-toggle="modal" data-target="#downloadModal" target="_blank" class="download-opportunity" download="">									
							<!-- <spring:theme code="portal.opportunity.download.here.label"/> -->
							<img class="img-fluid download-icon" src="${commonResourcePath}/images/Icon awesome-download.png" alt="" />
						</a>
					</p>							
				</c:if>
			</div>
		</div>    
	</div>

	<!-- <div class="container"> -->
	<div class="row opportunity-article-row">
		
		<c:if test="${not empty productData.description}">
			<section class="container sectors-content">
				<div class="row">
					<div class="col-md-12">				
						<!-- <img src="${productData.logo.url}" alt="${fn:escapeXml(productData.name)}" title="${fn:escapeXml(productData.name)}"/>		
						<div class="sector-bg-header" style="background-image: url(${productData.banner.url}); font-size: 15px;"></div> -->
					
						<!-- <h1 class='clr_gld section-headline my-2'><spring:theme code="portal.opportunity.overview.label"/></h1> -->
						
							<div class="opportunity-productData-description">
								<!-- <c:if test="${not empty productData.overviewPicture.url}">
									<p style="direction: rtl;">
										<img style="width: 70%; height: 70%; display: block; margin-left: auto; margin-right: auto;" class="center" 
												src="${productData.overviewPicture.url}" alt="${productData.overviewPicture.altText}" />
									</p>
									<p>&nbsp;</p>
								</c:if>					 -->
								${productData.description}					
							</div>
						
					</div>
				</div>
			</section>
		</c:if>
		<div class="sectors-content data-contact-field">
			<c:if test="${true}"><!-- test, if meta data is available-->
				<section class="sectors-content opp-metadata">
					
				</section>
			</c:if>
			
			<section class="sectors-content opp-contact-card">
				<div class="opportunity-contact-name">
					<c:if test="${true}"><!-- test, if contact data is available-->
						<div class="row contact-row">
							<div class="contact-name col-md-12">Firstname Lastname</div>
							<div class="contact-role col-md-12">Role description</div>
						</div>
					</c:if>
				</div>
				<div class="Inc-baner-btnwraper">
					<button class="btn btn-sector-primary">
						<a href="/en/investsaudi-login">
							<spring:theme code="portal.sector.investnow.label"/>
							<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
						</a>
					</button>
				</div>
				
				<!-- <div class="text-center mx-4">
					<!-- 
					<button class="download-opportunity" data-toggle="modal" data-target="#downloadModal" style="display:none;">
						<img class="img-fluid download-icon" src="${commonResourcePath}/images/Icon awesome-download.png" alt="" />
						Download The full Opportunity Here
					</button>
					-->
					<!-- Modal -->
					<!--<div class="modal fade" id="downloadModal" tabindex="-1" role="dialog" aria-labelledby="downloadModalTitle" aria-hidden="true">
						<div class="modal-dialog opportunity-modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title text-center clr_gld w-100" id="exampleModalLongTitle">
										<spring:theme code="portal.sector.download.label"/>
									</h5>
									<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button> -->							
								<!--</div>
								<div class="modal-body">
									<div id="popup-contact-form" class="contact-form pt-3" >
										<div class="form-row" style="padding: 20px;">
											<input type="hidden" class="form-control" name="buttonId" id="buttonId" />
											<div class="form-group col-md-12 form-normal-item">
												<label class="control-label" for="popup_crFirstName">
													<spring:theme code="portal.sector.download.firstName.label"/> <span class="mandatory">* </span>
												</label>
												<input type="text" class="required validate-name form-control" name="popup_crFirstName" id="popup_crFirstName"/>
												<div class="error-msg"></div>
											</div>
											<div class="form-group form-floating col-md-12 form-normal-item">
												<label class="control-label" for="popup_crLastName">
													<spring:theme code="portal.sector.download.lastName.label"/> <span class="mandatory">* </span>
												</label>
												<input type="text" class="required validate-name form-control" name="popup_crLastName" id="popup_crLastName"/>
												<div class="error-msg"></div>
											</div>
											<div class="form-group form-floating col-md-12 form-normal-item">
												<label class="control-label" for="popup_crEmail">
													<spring:theme code="portal.sector.download.email.label"/> <span class="mandatory">* </span>
												</label>
												<input type="text" class="required validate-email form-control" name="popup_crEmail" id="popup_crEmail"/>
												<div class="error-msg"></div>
											</div>
											<div class="form-group form-floating col-md-12 form-normal-item country-code-mobile">
												<input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off">
										
												<div class="input-wrapper">
													<label class="control-label" for="popup_crPhone">
														<spring:theme code="portal.sector.download.phoneNumber.label"/>
														<span class="mandatory">* </span>
													</label>
													<input type="text" class="required validate-mobile form-control" id="popup_crPhone" name="popup_crPhone"/>
													<div class="error-msg"></div>
												</div>
											</div>
											<div class="form-group form-floating col-md-12 form-normal-item">
												<label class="control-label" for="popup_crCompanyName">
													<spring:theme code="portal.sector.download.companyName.label"/> <span class="mandatory">* </span>
												</label>
												<input type="text" class="required validate-name form-control" name="popup_crCompanyName" id="popup_crCompanyName"/>
												<div class="error-msg"></div>
											</div>
											<div class="form-check d-flex mb-3">
												<input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
												<label class="form-check-label mand-field-text" for="invalidCheck">
													<spring:theme code="portal.sector.download.invalidCheck.label"/>
													<a class="pvcy-policy" href="/${language}/privacy-policy">
														<spring:theme code="portal.sector.download.dataPolicy.label"/>
													</a>
												</label>
												<div class="invalid-feedback"><spring:theme code="portal.sector.download.agree.label"/></div>
											</div>
											<div class="form-group form-floating col-md-12 form-normal-item">
												<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
												<div class="form_field-elements control-group js-recaptcha-captchaaddon sector-page-download-captcha"></div>
												<span id="lblSectorErrorCaptcha" class="mandatory"></span>
											</div>
											<div class="opp-contact-buttons">
												<div><button id="popup-btn-contact-cancel"  class="btn btn-primary-fill btn-form-outline w-100" data-dismiss="modal">
														<spring:theme code="portal.sector.download.cancel.button"/></button>
												</div>
												<div><button id="popup-btn-contact" type="submit" class="btn btn-primary-fill btn-form-fill  w-100">
														<spring:theme code="portal.sector.download.submit.button"/></button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>						
					<!-- Modal End -->
				<!--</div> -->
			</section>
		</div>
			
	</div>
 </div>
	

	<!-- <div>
		<h3 class="clr_gld section-headline mb-5"><spring:theme code="portal.opportunity.highlights.label"/></h3>
	</div> -->
	
	
	<%-- 
	<c:if test="${not empty productData.featureMap}">
		<section class="company-mgr-quote mt-4">
			<div class="container">
				<div class="row">
					<h1 class="heading text-left my-3 mb-4 col-md-12">${productData.opportunityDetailsTitle}</h1>
					<c:forEach var="featureMapItem" items="${productData.featureMap}">
						<div class="col-md-3 border-right-gray mb-3">
							<h5 class="header-tabs-main">${featureMapItem.value}</h5>
							<h6 class="dark-gray">${featureMapItem.key}</h6>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</c:if>	
	<section class="container sectors-content">
		<div class="row">
			<div class="col-md-12">
				<c:if test="${not empty productData.investmentModel}">
					<section class="container mb-5">
						<div class="row">
							<div class="col-md-12">
								${productData.investmentModel}
							</div>
						</div>
					</section>
					<p>&nbsp;</p>
				</c:if>
			</div>
		</div>
	</section>
	 --%>

	<cms:pageSlot position="PortalPageMain" var="slotComponent">
		<cms:component component="${slotComponent}"/>
	</cms:pageSlot>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
