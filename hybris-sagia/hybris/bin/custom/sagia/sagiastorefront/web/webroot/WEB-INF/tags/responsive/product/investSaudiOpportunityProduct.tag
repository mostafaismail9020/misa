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
			<div class="row opportunity-tags">
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
    		<div class="row">
    			<div class="col-md-7 col-12">
    				<h3 class="sub-title">${productData.description}</h3>
    				<p class="desc">
    					In ut lacinia dui. Integer orci lacus, malesuada sit amet viverra et, consequat vitae massa.
    					Vestibulum sit amet auctor lacus. Sed rhoncus sed metus eu euismod.
						Maecenas et velit eget magna lobortis auctor a a odio.
						Nulla lorem sem, fringilla vitae lacinia in, semper a libero.
						Nunc et sollicitudin augue, vitae faucibus justo. Ut vehicula nisi vel ultrices ullamcorper.
    				</p>
    				<h3 class="second-sub-title">Sector and market opportunity</h3>
    				<p class="desc">
    					In ut lacinia dui. Integer orci lacus, malesuada sit amet viverra et, consequat vitae massa.
    					Vestibulum sit amet auctor lacus. Sed rhoncus sed metus eu euismod.
						Maecenas et velit eget magna lobortis auctor a a odio.
						Nulla lorem sem, fringilla vitae lacinia in, semper a libero.
						Nunc et sollicitudin augue, vitae faucibus justo. Ut vehicula nisi vel ultrices ullamcorper.
    				</p>
    			</div>
    			<div class="col-md-5 col-12">
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
    						<div class="cold-md-12 col-12">
    							<p class="irr-location-title">Sector</p>
    							<div class="row">
    								<div class="col-md-3 col-4 irr-sector-div text-center">
    									<p>
    										<span class="title">ICT</span> <span class="badge badge-pill badge-irr-sector">37</span>
    									</p>
    								</div>
    								<div class="col-md-6 col-6"></div>
    								<div class="col-md-3 col-2">
    									<button class="btn btn-download-pdf-opportunity" id="download" data-toggle="modal" data-target="#downloadModal">
											<img class="img-fluid" src="${commonResourcePath}/images/download.png" alt="<spring:theme code="portal.sector.download.label"/>"/> 
										</button>
    								</div>
    							</div>
    						</div>
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
    </section>
	<!-- </c:if> -->
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
