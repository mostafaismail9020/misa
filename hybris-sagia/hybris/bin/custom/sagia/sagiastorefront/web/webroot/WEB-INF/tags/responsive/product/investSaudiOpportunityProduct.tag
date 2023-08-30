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
	    				<h3 class="sub-title">${productData.description}</h3>
	    				<p class="desc">
	    					In ut lacinia dui. Integer orci lacus, malesuada sit amet viverra et, consequat vitae massa.
	    					Vestibulum sit amet auctor lacus. Sed rhoncus sed metus eu euismod.
							Maecenas et velit eget magna lobortis auctor a a odio.
							Nulla lorem sem, fringilla vitae lacinia in, semper a libero.
							Nunc et sollicitudin augue, vitae faucibus justo. Ut vehicula nisi vel ultrices ullamcorper.
	    				</p>
    				</div>
    				<div class="order-lg-2 order-md-2 order-3">
	    				<h3 class="second-sub-title">Sector and market opportunity</h3>
	    				<p class="desc">
	    					In ut lacinia dui. Integer orci lacus, malesuada sit amet viverra et, consequat vitae massa.
	    					Vestibulum sit amet auctor lacus. Sed rhoncus sed metus eu euismod.
							Maecenas et velit eget magna lobortis auctor a a odio.
							Nulla lorem sem, fringilla vitae lacinia in, semper a libero.
							Nunc et sollicitudin augue, vitae faucibus justo. Ut vehicula nisi vel ultrices ullamcorper.
	    				</p>
    				</div>
    			</div>
    			<div class="col-md-5 col-12 order-lg-3 order-md-3 order-2">
    				<div class="jumbotron jumbotron-main">
    					<div class="row justify-content-center">
    						<div class="col-md-4 col-5 text-center irr-box">
    							<p class="irr-title">~30%</p>
    							<p class="irr-desc">Expected IRR</p>
    						</div>
    						<div class="col-md-2 col-2"></div>
    						<div class="col-md-4 col-5 text-center irr-box">
    							<p class="irr-title">$2 BN</p>
    							<p class="irr-desc">Investment size</p>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col-md-6 col-6">
    							<p class="irr-location-title">Location</p>
    							<p class="irr-location-desc">Saudi Arabia Riyadh</p>
    						</div>
    						<div class="col-md-6 col-6">
    							<p class="irr-location-title">Investment type</p>
    							<p class="irr-location-desc">Foreign direct investment</p>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col-md-12 col-12">
    							<p class="irr-location-title">Sector</p>
    							<div class="d-flex justify-content-between">
    								<div class="col-md-3 col-4 irr-sector-div text-center">
    									<div class="row">
    										<div class="col-md-5 col-5">
    											<span class="title">ICT</span>
    										</div>
    										<div class="col-md-7 col-7">
    											<span class="badge badge-pill badge-irr-sector">37</span>
    										</div>
    									</div>
    								</div>
    								<div class="col-md-3 col-2">
    									<button class="btn btn-download-pdf-opportunity" id="controllerButton" class="button" onclick="callController()">
											<img class="img-fluid" src="${commonResourcePath}/images/download.png" alt="<spring:theme code="portal.sector.download.label"/>"/>
										</button>
										<button class="btn btn-download-pdf-opportunity" id="buttonWithText" class="button" style="display: none;">Processing...</button>
  										<form id="controllerForm" action="<c:url value='/merged-pdf-download/${productData.code}' />" method="get" style="display: none;"></form>
    								</div>
    							</div>
    						</div>
    					</div>
    				</div>
    				<div class="jumbotron jumbotron-second">
    					<div class="row">
    						<div class="col-md-3 col-3">
    							<img class="img-fluid rounded-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS78M1xPINkEl0df2wnbq3bro9Oz0rCHpWn284h5iT4tQ&s" />
    						</div>
    						<div class="col-md-9 col-9">
    							<div class="row">
    								<div class="col-md-12 col-12">
    									<p class="opportunity-owner">Ahmed Ali</p>
    								</div>
    								<div class="col-md-12 col-12">
    									<p class="opportunity-owner-position">Opportunity lead</p>
    								</div>
    							</div>
    						</div>
    					</div>
    					<div class="row">
    						<div class="col text-center">
    							<button type="button" class="btn btn-primary-fill btn-get-in-touch-opportunity">Get in touch</button>
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
    							<p class="opportunity-statics-desc">Expected Investment size [Number or range]</p>
								<p class="opportunity-statics-desc">Plant capacity [LIST] [LIST] [NUMBER]</p>
								<p class="opportunity-statics-desc">Expected IRR [Number or range]</p>
								<p class="opportunity-statics-desc">Payback period [Number]</p>
								<p class="opportunity-statics-desc">Job Creation [Number]</p>
								<p class="opportunity-statics-desc">GDP Impact [Number]</p>
								<p class="opportunity-statics-desc">Location (Region) [TEXT]</p>
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
    							<p class="opportunity-statics-desc text-center">[TEXT] Summary of key differentiators that position KSA as a strategic choice over other regional/global peers</p>
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
    							<p class="opportunity-statics-desc text-center">[LIST] [TEXT] Factors that enable investment in the underlying opportunity such as General Incentive and financing</p>
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
    							<p class="opportunity-statics-desc text-center">[TEXT] Summary of key differentiators that position KSA as a strategic choice over other regional/global peers</p>
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
    							<p class="opportunity-statics-desc text-center">[TEXT] Raw Materials</p>
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
    							<p class="opportunity-statics-desc text-center">[TEXT] Latest business developments within the sector/product category</p>
    						</div>
    					</div>
    				</div>
				</div>
			</div>
		</div>
	</section>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
