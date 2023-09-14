<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>
	<section class="opportunity-article-section-header">
		<div class="container">
			<div class="row">
                <c:if test="${language eq 'en'}">
                    <a href="/${language}/">
                        <div class="col-md-12 breadcrumb-container">
                            <span class="breadcrumb-left-icon"></span>
                            <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                        </div>
                    </a>
                </c:if>
                <c:if test="${language eq 'ar'}">
                    <a href="/${language}/">
                        <div class="col-md-12 breadcrumb-container">
                            <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                            <span class="breadcrumb-left-icon"></span>
                        </div>
                    </a>
                </c:if>
            </div>
			<div class="row">
				<div class="col-md-12">
					<h1 class="page-title">${fn:escapeXml(productData.name)}</h1>
				</div>
			</div>
			<div class="row opportunity-tags justify-content-center justify-content-md-start" style="display: none;">
				<div class="col-md-2 col-5">
					<span class="badge badge-pill badge-light">Promoting Tag 1</span>
				</div>
				<div class="col-md-2 col-5">
					<span class="badge badge-pill badge-light">Promoting Tag 2</span>
				</div>
				<div class="col-md-2 col-5">
					<span class="badge badge-pill badge-light">Promoting Tag 3</span>
				</div>
			</div>
		</div>
    </section>
    
    <!-- <c:if test="${not empty productData.description}"> -->
    <section class="opportunity-article-section-body">
    	<div class="container">
    		<div class="row justify-content-between">
    			<div class="col-md-7 col-12">
    				<div class="order-lg-1 order-md-1 order-1">
	    				<p class="desc">${productData.description}</p>
    				</div>
    			</div>
    			<div class="col-md-5 col-12 order-lg-3 order-md-3 order-2">
    				<div class="jumbotron jumbotron-main">
    					<div class="row justify-content-center">
    						<div class="col-md-4 col-5 text-center irr-box">
    							<p class="irr-title">${fn:escapeXml(productData.expectedIRR)}</p>
    							<p class="irr-desc"><spring:theme code="portal.opportunity.details.expected.irr"/></p>
    						</div>
    						<div class="col-md-2 col-2"></div>
    						<div class="col-md-4 col-5 text-center irr-box">
    							<p class="irr-title">${fn:escapeXml(productData.expectedInvestmentSize)}</p>
    							<p class="irr-desc"><spring:theme code="portal.opportunity.details.expected.investment.size"/></p>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col-md-6 col-6">
    							<p class="irr-location-title"><spring:theme code="portal.opportunity.details.location"/></p>
    							<p class="irr-location-desc">${fn:escapeXml(productData.locationRegionText)} - ${fn:escapeXml(productData.locationCityText)}</p>
    						</div>
    						<div class="col-md-6 col-6">
    							<p class="irr-location-title"><spring:theme code="portal.opportunity.details.investment.type"/></p>
    							<p class="irr-location-desc">${fn:escapeXml(productData.investmentType)}</p>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<p class="irr-location-title mt-2"><spring:theme code="portal.opportunity.details.sector"/></p>
   								<div class="col-md-12 col-12 irr-sector-div text-center">
   									<div class="row">
   										<div class="col-md-12 col-12">
   											<span class="title">${fn:escapeXml(productData.parentCategory)}</span>
   										</div>
   										<div class="col-md-7 col-7" style="display: none;">
   											<span class="badge badge-pill badge-irr-sector">37</span>
   										</div>
   									</div>
   								</div>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="irr-location-title mt-2"><spring:theme code="portal.opportunity.details.segment"/></p>
   								<div class="col-md-12 col-12 irr-sector-div text-center">
   									<div class="row">
   										<div class="col-md-12 col-12">
   											<span class="title">${fn:escapeXml(productData.segmentName)}</span>
   										</div>
   										<div class="col-md-7 col-7" style="display: none;">
   											<span class="badge badge-pill badge-irr-sector">37</span>
   										</div>
   									</div>
   								</div>
    						</div>
  							<div class="col-md-12 col-12 mt-3">
  								<div class="row">
  									<div class="col-md-5">
  										<button class="btn btn-download-pdf-opportunity col" id="controllerButton" class="button" onclick="callController()">
											<img class="img-fluid" src="${commonResourcePath}/images/download.png" alt="<spring:theme code="portal.sector.download.label"/>"/>
										</button>
										<button class="btn btn-download-pdf-opportunity col mt-2" id="buttonWithText" class="button" style="display: none;"><spring:theme code="portal.opportunity.details.download.processing"/></button>
  									</div>
  									<div class="col-md-7 mt-3 text-center"><spring:theme code="portal.opportunity.details.download.opportunity"/></div>
									<form id="controllerForm" action="<c:url value='/merged-pdf-download/${productData.code}' />" method="get" style="display: none;"></form>
  								</div>
  							</div>
    					</div>
    				</div>
    				<div class="jumbotron jumbotron-second">
    					<div class="row">
    						<div class="col-md-3 col-3" style="display: none;">
    							<img class="img-fluid rounded-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS78M1xPINkEl0df2wnbq3bro9Oz0rCHpWn284h5iT4tQ&s" />
    						</div>
    					</div>
                        <div class="row d-flex align-items-center">
                            <div class="col-md-12 text-center">
                                <label class="lbError d-none"></label>
                            </div>
                        </div>
    					<div class="row">
    						<div class="col text-center">
    							<button type="button" class="btn btn-primary-fill btn-get-in-touch-opportunity" id="toggle-opportunity-contact-form"><spring:theme code="portal.opportunity.details.get.in.touch.btn"/></button>
    						</div>
    					</div>
    					<form id="corForm">
	    					<div class="div-form-opportunity-lead">
	    						<div class="row">
									<div class="form-group col-md-12 col-12 form-normal-item">
										<label for="crName">
											<spring:theme code="portal.sector.contact.expert.name.label"/> *
										</label>
										<input type="text" class="form-control required validate-name" data-val="true" 
		                               		data-val-regex="Numbers and Special Characters are not allowed"
			                                data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
			                                data-val-required="Required" id="crName" name="Name"/>
										<small class="error-msg"></small>
									</div>
								</div>
		    					<div class="row">
									<div class="form-group col-md-12 col-12 form-normal-item">
										<label for="crCompany">
											<spring:theme code="portal.sector.contact.expert.companyName.label"/> *
										</label>
										<input type="text" class="form-control required validate-name" data-val="true"
			                                data-val-regex="Numbers and Special Characters are not allowed"
			                                data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
			                                id="crCompany" name="Company"/>
										<small class="error-msg"></small>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-12 col-12 form-normal-item">
										<label for="crMobile">
											<spring:theme code="portal.sector.contact.expert.phoneNumber.label"/> *
										</label>
										<div class="row no-gutters">
											<div class="col-md-4 col-4">
												<input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off"/>
											</div>
			                               <div class="col-md-8 col-8">
			                                   <input type="text" class="form-control validate-mobile required mobile-number" id="crMobile" name="MobileNumber" type="number"/>
			                                   <small class="error-msg"></small>
			                               </div> 
										</div>                               
		                           </div>
								</div>
								<div class="row">
									<div class="form-group col-md-12 col-12 form-normal-item">
										<label for="crJobTitle">
											<spring:theme code="portal.sector.contact.expert.jobTitle.label"/> *
										</label>
										<input type="text" class="form-control required validate-name" data-val="true"
			                                data-val-regex="Numbers and Special Characters are not allowed"
			                                data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
			                                id="crJobTitle" name="JobTitle"/>
										<small class="error-msg"></small>
									</div>
								</div>
		    					<div class="row">
									<div class="form-group col-md-12 col-12 form-normal-item">
										<label for="crEmail">
											<spring:theme code="portal.sector.contact.expert.email.label"/> *
										</label>
										<input type="text" class="form-control validate-email required" data-val="true"
			                                data-val-regex="Invalid email address"
			                                data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
			                                data-val-required="Required" id="crEmail" name="Email"/>
										<small class="error-msg"></small>
									</div>
								</div>
	    						<div class="row">
									<div class="form-group col-md-12 col-12 form-normal-item">
										<label for="contactSubjectList">
											<spring:theme code="portal.sector.contact.expert.purpose.label"/> *
										</label>
										<input type="text" class="form-control required error" data-val="true"
			                                data-val-required="Required" id="contactSubjectList" name="contactSubjectList"/>
										<small class="error-msg"></small>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-12">
										<label for="crMessage">
											<spring:theme code="portal.sector.contact.expert.message.label"/> *
										</label>
										<textarea class="form-control required" cols="10" style="border-radius: 10px;"
				                            data-val-required="Required" id="crMessage" name="Message" rows="5">
			                            </textarea>
										<small class="error-msg"></small>
									</div>
								</div>
		                        <div class="row">
		                        	<div class="form-group col-md-12 col-12">
			                        	<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
			                         	<div class="form_field-elements control-group js-recaptcha-captchaaddon sector-page-captcha"></div>
			                         	<span id="lblSectorPageErrorCaptcha" class="mandatory"></span>
			                        </div>
		                        </div>
		    					<div class="row">
		    						<div class="col text-center">
		    							<button type="button" class="mt-2 btn btn-primary-fill btn-get-in-touch-opportunity" id="submit-opportunity-contact-form">
		    								<spring:theme code="portal.opportunity.details.submit.btn"/>
		    							</button>
		    						</div>
		    					</div>
	    					</div>
    					</form>
    				</div>
    			</div>
    		</div>
    	</div>
    </section>
	<!-- </c:if> -->
	
	<section class="opportunity-article-section-statistics">
		<div class="container">
			<div class="row justify-content-between">
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.investment.highlights"/></h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc"><spring:theme code="portal.opportunity.details.expected.investment.size"/> ${fn:escapeXml(productData.expectedInvestmentSize)}</p>
								<p class="opportunity-statics-desc"><spring:theme code="portal.opportunity.details.plant.capacity"/>
									<ul>
										<c:forEach var="plantCapacity" items="${productData.capacityData}">
		                                    <li>${plantCapacity.measure} - ${plantCapacity.value} ${plantCapacity.unit}</li>
		                                </c:forEach>
	                                </ul>
								</p>
								<p class="opportunity-statics-desc"><spring:theme code="portal.opportunity.details.expected.irr"/> ${fn:escapeXml(productData.expectedIRR)}</p>
								<c:if test="${productData.paybackPeriod != 0}">
									<p class="opportunity-statics-desc"><spring:theme code="portal.opportunity.details.payback.period"/> ${fn:escapeXml(productData.paybackPeriod)} Years</p>
								</c:if>
								<c:if test="${productData.jobscreated != 0}">
									<p class="opportunity-statics-desc"><spring:theme code="portal.opportunity.details.job.creation"/> ${fn:escapeXml(productData.jobscreated)}</p>
								</c:if>
								<c:if test="${productData.gdpContribution != '0' and productData.gdpContribution != '0 SAR'}">
									<p class="opportunity-statics-desc"><spring:theme code="portal.opportunity.details.gdp.impact"/> ${fn:escapeXml(productData.gdpContribution)}</p>
								</c:if>
								<p class="opportunity-statics-desc"><spring:theme code="portal.opportunity.details.location.region"/> ${fn:escapeXml(productData.locationRegionText)} - ${fn:escapeXml(productData.locationCityText)}</p>
    						</div>
    					</div>
    				</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.value.proposition"/></h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc">${productData.valuePropositionText}</p>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			<div class="row justify-content-between">
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.incentives.enablers"/></h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc">${productData.incentivesAndEnablersText}</p>
    						</div>
    					</div>
    				</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box special-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.cost.business"/></h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<div class="row mt-3">
    								<div class="col-md-7"><spring:theme code="portal.opportunity.details.factor"/></div>
    								<div class="col-md-5"><spring:theme code="portal.opportunity.details.ranking.regional.peers"/></div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7"><spring:theme code="portal.opportunity.details.electricity.tarifs"/></div>
    								<div class="col-md-5 text-center"><span class="badge badge-pill special-badge-opportunity-details">${fn:escapeXml(productData.electricityTariffs)}</span></div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7"><spring:theme code="portal.opportunity.details.productivity.adjusted.wages"/></div>
    								<div class="col-md-5 text-center"><span class="badge badge-pill special-badge-opportunity-details">${fn:escapeXml(productData.productivityAdjustedWages)}</span></div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7"><spring:theme code="portal.opportunity.details.logistics.performance.index"/></div>
    								<div class="col-md-5 text-center"><span class="badge badge-pill special-badge-opportunity-details">${fn:escapeXml(productData.logisticsPerformanceIndex)}</span></div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7"><spring:theme code="portal.opportunity.details.construction.costs"/></div>
    								<div class="col-md-5 text-center"><span class="badge badge-pill special-badge-opportunity-details">4${fn:escapeXml(productData.constructionCosts)}</span></div>
    							</div>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 col-12">
					<div class="jumbotron jumbotron-statistics-box statistics-stackeholders">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.key.stakeholders"/></h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<div class="row justify-content-center">
									<div class="col-md-2 col-6">
										<div class="row">
											<div class="col-md-12">
												<p class="text-center stackholder-name">Org 1</p>
											</div>
										</div>
									</div>
						            <div class="col-md-2 col-6">
										<div class="row">
											<div class="col-md-12">
												<p class="text-center stackholder-name">Org 2</p>
											</div>
										</div>
									</div>
						            <div class="col-md-2 col-6">
										<div class="row">
											<div class="col-md-12">
												<p class="text-center stackholder-name">Org 3</p>
											</div>
										</div>
									</div>
						            <div class="col-md-2 col-6">
										<div class="row">
											<div class="col-md-12">
												<p class="text-center stackholder-name">Org 4</p>
											</div>
										</div>
									</div>
						            <div class="col-md-2 col-6">
										<div class="row">
											<div class="col-md-12">
												<p class="text-center stackholder-name">Org 5</p>
											</div>
										</div>
									</div>
					            </div>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			<c:if test="${not empty productData.rawMaterialText || not empty productData.globalTrendsText}">
				<div class="row justify-content-between">
					<div class="col-md-6 col-12">
						<div class="jumbotron jumbotron-statistics-box">
	    					<div class="row">
	    						<div class="col-md-12 col-12">
	    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.raw.materials"/></h3>
	    						</div>
	    						<div class="col-md-12 col-12">
	    							<p class="opportunity-statics-desc">${productData.rawMaterialText}</p>
	    						</div>
	    					</div>
	    				</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="jumbotron jumbotron-statistics-box">
	    					<div class="row">
	    						<div class="col-md-12 col-12">
	    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.global.trends"/></h3>
	    						</div>
	    						<div class="col-md-12 col-12">
	    							<p class="opportunity-statics-desc">${productData.globalTrendsText}</p>
	    						</div>
	    					</div>
	    				</div>
					</div>
				</div>
			</c:if>
			<div class="row" style="display: none;">
				<div class="col-md-12 col-12">
					<div class="jumbotron jumbotron-statistics-box special-statistics-box box-without-height ">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.market.size"/></h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">
									<img class="img-fluid" src="${commonResourcePath}/images/marketsize.png" />
								</p>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			<c:if test="${not empty productData.keyDemandDriversText || not empty productData.scalabilityAndLocalizationText}">
				<div class="row justify-content-between">
					<div class="${empty productData.scalabilityAndLocalizationText ? 'col-md-12' : 'col-md-6'} col-12 ${empty productData.keyDemandDriversText ? 'd-none' : ''}" style="${empty productData.scalabilityAndLocalizationText ? 'min-height: auto;' : ''}">
						<div class="jumbotron jumbotron-statistics-box">
	    					<div class="row">
	    						<div class="col-md-12 col-12">
	    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.key.demand.drivers"/></h3>
	    						</div>
	    						<div class="col-md-12 col-12">
	    							<p class="opportunity-statics-desc">${productData.keyDemandDriversText}</p>
	    						</div>
	    					</div>
	    				</div>
					</div>
					<div class="${empty productData.keyDemandDriversText ? 'col-md-12' : 'col-md-6'} col-12 ${empty productData.scalabilityAndLocalizationText ? 'd-none' : ''}" style="${empty productData.keyDemandDriversText ? 'min-height: auto;' : ''}">
						<div class="jumbotron jumbotron-statistics-box">
	    					<div class="row">
	    						<div class="col-md-12 col-12">
	    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.scalability.localization"/></h3>
	    						</div>
	    						<div class="col-md-12 col-12">
	    							<p class="opportunity-statics-desc">${productData.scalabilityAndLocalizationText}</p>
	    						</div>
	    					</div>
	    				</div>
					</div>
				</div>
			</c:if>
			<div class="row" style="display: none;">
				<div class="col-md-12 col-12">
					<div class="jumbotron jumbotron-statistics-box box-without-height ">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.value.chain"/> ${fn:escapeXml(productData.valueChainText)}</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">
									<img class="img-fluid" src="${commonResourcePath}/images/valuechain.png" />
								</p>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			<c:if test="${not empty productData.importDependencyText}">
				<div class="row">
					<div class="col-md-12 col-12">
						<div class="jumbotron jumbotron-statistics-box box-without-height ">
	    					<div class="row">
	    						<div class="col-md-12 col-12">
	    							<h3 class="opportunity-statics-title text-center"><spring:theme code="portal.opportunity.details.import.dependency"/></h3>
	    						</div>
	    						<div class="col-md-12 col-12">
	    							<p class="opportunity-statics-desc">${productData.importDependencyText}</p>
	    						</div>
	    					</div>
	    				</div>
					</div>
				</div>
			</c:if>
		</div>
	</section>
	
	<section id="newsandupdates" class="newsAndUpdateContainer">
	    <div class="container">
	        <div class="row titleContainer">
	            <div class="col-md-12 content mx-auto mb-3">
	                <h2 class="misa-text-title"><spring:theme code="portal.opportunity.details.more.opportunities.text"/></h2>
				</div>
			</div>
			<div class="row">
		      	<div class="col-lg-4 col-md-6 card-wrapper " data-aos="fade-up" data-aos-delay="100">
		        	<div class="flip-card flip-card-custom row">
		        		<div class="col-md-5 col-4">
		        			<a class="know-more-link" href="#">
		        				<img class="img-fluid" src="https://investsaudi.sa/medias/Banner-6-.jpg?context=bWFzdGVyfHJvb3R8MTQyNDI0fGltYWdlL2pwZWd8aDlkL2g4MS85MDUyNjg0NjgxMjQ2LmpwZ3w5MjgzZjMwYjViMDYxZTgwYjljODZlMzZiNTg2NDc3NmU4ZDFkMmVlOWUzY2M5NWY1MjY4ZDBhZDcyZjljNTU4" alt="" loading="lazy">
		        			</a>
		        		</div>
		        		<div class="col-md-7 col-8">
		        			<a class="know-more-link" href="#">
		        				<h5>Digital Twins For Tourism, Heritage And Events</h5>
							</a>
							<p class="desc">Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.</p>
		        			<p class="irr-value">Market CAGR 19-26 +3%</p>
		        		</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 card-wrapper " data-aos="fade-up" data-aos-delay="100">
		        	<div class="flip-card flip-card-custom row">
		        		<div class="col-md-5 col-4">
		        			<a class="know-more-link" href="#">
		        				<img class="img-fluid" src="https://investsaudi.sa/medias/Banner-6-.jpg?context=bWFzdGVyfHJvb3R8MTQyNDI0fGltYWdlL2pwZWd8aDlkL2g4MS85MDUyNjg0NjgxMjQ2LmpwZ3w5MjgzZjMwYjViMDYxZTgwYjljODZlMzZiNTg2NDc3NmU4ZDFkMmVlOWUzY2M5NWY1MjY4ZDBhZDcyZjljNTU4" alt="" loading="lazy">
		        			</a>
		        		</div>
		        		<div class="col-md-7 col-8">
		        			<a class="know-more-link" href="#">
		        				<h5>Digital Twins For Tourism, Heritage And Events</h5>
							</a>
							<p class="desc">Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.</p>
		        			<p class="irr-value">Market CAGR 19-26 +3%</p>
		        		</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 card-wrapper " data-aos="fade-up" data-aos-delay="100">
		        	<div class="flip-card flip-card-custom row">
		        		<div class="col-md-5 col-4">
		        			<a class="know-more-link" href="#">
		        				<img class="img-fluid" src="https://investsaudi.sa/medias/Banner-6-.jpg?context=bWFzdGVyfHJvb3R8MTQyNDI0fGltYWdlL2pwZWd8aDlkL2g4MS85MDUyNjg0NjgxMjQ2LmpwZ3w5MjgzZjMwYjViMDYxZTgwYjljODZlMzZiNTg2NDc3NmU4ZDFkMmVlOWUzY2M5NWY1MjY4ZDBhZDcyZjljNTU4" alt="" loading="lazy">
		        			</a>
		        		</div>
		        		<div class="col-md-7 col-8">
		        			<a class="know-more-link" href="#">
		        				<h5>Digital Twins For Tourism, Heritage And Events</h5>
							</a>
							<p class="desc">Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.</p>
		        			<p class="irr-value">Market CAGR 19-26 +3%</p>
		        		</div>
					</div>
				</div>
        	</div>
			<div class="col-md-12">
				<div class="row justify-content-center justify-content-md-between mt-4">
					<a href="#" class="btn btn-primary-fill btn-video misa-btn-special">
						<spring:theme code="portal.seemoreupdates.button.text"/>
					</a>
				</div>
			</div>
    	</div>
	</section>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
