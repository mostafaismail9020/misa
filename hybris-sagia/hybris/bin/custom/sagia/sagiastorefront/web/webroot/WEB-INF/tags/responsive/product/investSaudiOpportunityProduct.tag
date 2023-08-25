<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>
	<section class="mb-5 mt-0 pt-0">
        <div style="background-image: url('')" class="yCmsComponent banner-section rhq-banner-content">
            <div class="banner-container container aos-init aos-animate" data-aos="fade-up">
            	<h1>${fn:escapeXml(productData.name)}</h1>
				<c:if test="${true}"><!-- test, if kpis are available-->
					<div class="col-md-12">
						<div class="row kpi-row">
							<div class="col-md-5 col-5 kpi">
								<div class="kpi-value">XX%</div>
								<div class="kpi-postfix">TODO actual data</div>
							</div>
							<div class="col-md-2 col-2"></div>
							<div class="col-md-5 col-5 kpi">
								<div class="kpi-value">$X</div>
								<div class="kpi-postfix">TODO actual data</div>
							</div>
						</div>
					</div>
				</c:if>
				<div class="Inc-baner-btnwraper">
					<a href="/en/investsaudi-login" class="btn btn-sector-primary">
						<spring:theme code="portal.sector.investnow.label"/>
						<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
					</a>
					<c:if test="${not empty productData.pdfUrl}">
						<button id="controllerButton" class="download-opportunity" onclick="callController()">
							<img class="img-fluid download-icon" src="${commonResourcePath}/images/Icon awesome-download.png" alt="" />
						</button>
	  					<form id="controllerForm" action="<c:url value='/merged-pdf-download/${productData.code}' />" method="get" style="display: none;">
	  					</form>
					</c:if>
				</div>
            </div>
        </div>
    </section>

	<!-- <c:if test="${not empty productData.description}"> -->
	<section class="container sectors-content">
		<div class="row">
			<div class="col-md-12">
				<div class="opportunity-productData-description">
					${productData.description}		
					<a>Text to be displayed. This is an example Opportunity description</a>			
				</div>
			</div>
		</div>
	</section>
	<!-- </c:if> -->
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
				<button class="btn contact-button">
					<a href="/en/investsaudi-login">
						<spring:theme code="portal.sector.investnow.label"/>
						<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
					</a>
				</button>
			</div>
		</section>
	</div>

	<cms:pageSlot position="PortalPageMain" var="slotComponent">
		<cms:component component="${slotComponent}"/>
	</cms:pageSlot>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
