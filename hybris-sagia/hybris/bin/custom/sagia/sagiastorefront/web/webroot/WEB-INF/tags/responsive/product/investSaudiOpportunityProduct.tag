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
			<div class="row opportunity-tags justify-content-center justify-content-md-start">
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
    							<p class="irr-desc">Expected IRR</p>
    						</div>
    						<div class="col-md-2 col-2"></div>
    						<div class="col-md-4 col-5 text-center irr-box">
    							<p class="irr-title">${fn:escapeXml(productData.expectedInvestmentSize)}</p>
    							<p class="irr-desc">Investment size</p>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col-md-6 col-6">
    							<p class="irr-location-title">Location</p>
    							<p class="irr-location-desc">${fn:escapeXml(productData.locationRegionText)} - ${fn:escapeXml(productData.locationCityText)}</p>
    						</div>
    						<div class="col-md-6 col-6">
    							<p class="irr-location-title">Investment type</p>
    							<p class="irr-location-desc">${fn:escapeXml(productData.investmentType)}</p>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<p class="irr-location-title mt-2">Sector</p>
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
    							<p class="irr-location-title mt-2">Segment</p>
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
  								<button class="btn btn-download-pdf-opportunity col" id="controllerButton" class="button" onclick="callController()">
									<img class="img-fluid" src="${commonResourcePath}/images/download.png" alt="<spring:theme code="portal.sector.download.label"/>"/>
								</button>
								<button class="btn btn-download-pdf-opportunity col" id="buttonWithText" class="button" style="display: none;">Processing...</button>
								<form id="controllerForm" action="<c:url value='/merged-pdf-download/${productData.code}' />" method="get" style="display: none;"></form>
  							</div>
    					</div>
    				</div>
    				<div class="jumbotron jumbotron-second">
    					<div class="row">
    						<div class="col-md-3 col-3" style="display: none;">
    							<img class="img-fluid rounded-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS78M1xPINkEl0df2wnbq3bro9Oz0rCHpWn284h5iT4tQ&s" />
    						</div>
    						<div class="col-md-12 col-12">
    							<div class="row">
    								<div class="col-md-12 col-12">
    									<p class="opportunity-owner text-center">Ahmed Ali</p>
    								</div>
    								<div class="col-md-12 col-12">
    									<p class="opportunity-owner-position text-center">Opportunity lead</p>
    								</div>
    							</div>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col text-center">
    							<button type="button" class="btn btn-primary-fill btn-get-in-touch-opportunity" id="toggle-opportunity-contact-form">Get in touch</button>
    						</div>
    					</div>
    					<div class="div-form-opportunity-lead">
    						<div class="row mt-5">
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
	    						<div class="col text-center">
	    							<button type="button" class="btn btn-primary-fill btn-get-in-touch-opportunity" id="submit-opportunity-contact-form">Submit</button>
	    						</div>
	    					</div>
    					</div>
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
    							<h3 class="opportunity-statics-title text-center">Investment Highlights</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc">Expected Investment size ${fn:escapeXml(productData.expectedInvestmentSize)}</p>
								<p class="opportunity-statics-desc">Plant capacity
									<ul>
										<c:forEach var="plantCapacity" items="${productData.capacityData}">
		                                    <li>${plantCapacity.measure} - ${plantCapacity.value} ${plantCapacity.unit}</li>
		                                </c:forEach>
	                                </ul>
								</p>
								<p class="opportunity-statics-desc">Expected IRR ${fn:escapeXml(productData.expectedIRR)}</p>
								<p class="opportunity-statics-desc">Payback period ${fn:escapeXml(productData.paybackPeriod)} years</p>
								<p class="opportunity-statics-desc">Job Creation ${fn:escapeXml(productData.jobscreated)}</p>
								<p class="opportunity-statics-desc">GDP Impact ${fn:escapeXml(productData.gdpContribution)}</p>
								<p class="opportunity-statics-desc">Location (Region) ${fn:escapeXml(productData.locationRegionText)} - ${fn:escapeXml(productData.locationCityText)}</p>
    						</div>
    					</div>
    				</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Value Proposition</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">${fn:escapeXml(productData.valuePropositionText)}</p>
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
    							<h3 class="opportunity-statics-title text-center">Incentives and Enablers</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">${fn:escapeXml(productData.incentivesAndEnablersText)}</p>
    						</div>
    					</div>
    				</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box special-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Cost of Doing Business</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<div class="row mt-3">
    								<div class="col-md-7">Factor</div>
    								<div class="col-md-5">Ranking amongst regional peers</div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7">Electricity tariffs for industrial players</div>
    								<div class="col-md-5 text-center"><span class="badge badge-pill special-badge-opportunity-details">${fn:escapeXml(productData.electricityTariffs)}</span></div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7">Productivity adjusted wages</div>
    								<div class="col-md-5 text-center"><span class="badge badge-pill special-badge-opportunity-details">${fn:escapeXml(productData.productivityAdjustedWages)}</span></div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7">Logistics Performance Index (1-5)</div>
    								<div class="col-md-5 text-center"><span class="badge badge-pill special-badge-opportunity-details">${fn:escapeXml(productData.logisticsPerformanceIndex)}</span></div>
    							</div>
    							<div class="row mt-3">
    								<div class="col-md-7">Construction Costs (Indexed to US, %0 is cheapest</div>
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
    							<h3 class="opportunity-statics-title text-center">Key Stakeholders</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<div class="row justify-content-center">
									<div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 1</p>
												</a>
											</div>
										</div>
									</div>
						            <div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 2</p>
												</a>
											</div>
										</div>
									</div>
						            <div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 3</p>
												</a>
											</div>
										</div>
									</div>
						            <div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 4</p>
												</a>
											</div>
										</div>
									</div>
						            <div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 5</p>
												</a>
											</div>
										</div>
									</div>
						            <div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 6</p>
												</a>
											</div>
										</div>
									</div>
						            <div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 7</p>
												</a>
											</div>
										</div>
									</div>
						            <div class="col-md-1 col-6">
										<div class="row">
											<div class="col-md-12 text-center">
												<a href="#">
													<img src="https://tikkurila.com/sites/default/files/styles/thumbnail_800_auto/public/color_resources/%2345474B.png?itok=yOcFo8Fw" class="rounded-circle img-fluid" alt="" title="" style="" loading="lazy" width="65" height="65">
												</a>
											</div>
											<div class="col-md-12">
												<a href="#">
													<p class="text-center">Org 8</p>
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
			<div class="row justify-content-between">
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Raw Materials</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">${productData.rawMaterialText}</p>
    						</div>
    					</div>
    				</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Global Trends</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">${fn:escapeXml(productData.globalTrendsText)}</p>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			<div class="row" style="display: none;">
				<div class="col-md-12 col-12">
					<div class="jumbotron jumbotron-statistics-box special-statistics-box box-without-height ">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Market Size</h3>
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
			<div class="row justify-content-between">
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Key Demand Drivers</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">${fn:escapeXml(productData.keyDemandDriversText)}</p>
    						</div>
    					</div>
    				</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="jumbotron jumbotron-statistics-box">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Scalability & Localization</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">${fn:escapeXml(productData.scalabilityAndLocalizationText)}</p>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			<div class="row" style="display: none;">
				<div class="col-md-12 col-12">
					<div class="jumbotron jumbotron-statistics-box box-without-height ">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Value Chain ${fn:escapeXml(productData.valueChainText)}</h3>
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
			<div class="row">
				<div class="col-md-12 col-12">
					<div class="jumbotron jumbotron-statistics-box box-without-height ">
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<h3 class="opportunity-statics-title text-center">Import Dependency</h3>
    						</div>
    						<div class="col-md-12 col-12">
    							<p class="opportunity-statics-desc text-center">${fn:escapeXml(productData.importDependencyText)}</p>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
		</div>
	</section>
	
	<section id="newsandupdates" class="newsAndUpdateContainer">
	    <div class="container">
	        <div class="row titleContainer">
	            <div class="col-md-12 content mx-auto mb-3">
	                <h2 class="misa-text-title">More ICT opportunities</h2>
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
