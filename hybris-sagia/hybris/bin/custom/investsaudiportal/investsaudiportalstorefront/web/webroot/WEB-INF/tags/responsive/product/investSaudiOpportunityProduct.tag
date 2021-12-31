<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>
	<section class="container sectors-content">
		<div class="row">
			<div class="col-md-12">
				<h1 class='heading text-left my-2'><spring:theme code="text.overview"/></h1>
				<c:if test="${not empty productData.overviewPicture.url}">
					<p style="direction: rtl;"><img style="width: 70%; height: 70%; display: block; margin-left: auto; margin-right: auto;" class="center" src="${productData.overviewPicture.url}" alt="${productData.overviewPicture.altText}" /></p>
					<p>&nbsp;</p>
				</c:if>
				${productData.description}
				<p>&nbsp;</p>
				<c:if test="${not empty productData.highlights}">
					${productData.highlights}
					<p>&nbsp;</p>
				</c:if>
				<c:if test="${not empty productData.pdfUrl}">
					<p class="text-center"><a rel="noopener" href="${productData.pdfUrl}" target="_blank" class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" download=""><spring:theme code="text.button.download.full.opportunity"/></a></p>
					<p>&nbsp;</p>
				</c:if>
				<c:if test="${not empty productData.imageUrl}">
					<p class="text-center"><spring:theme code="text.scan.mobile"/>: <br>&nbsp;<img style="width: 158px; height: 158px;" src="${productData.imageUrl}"></p>
				</c:if>
			</div>
		</div>
	</section>
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

	<cms:pageSlot position="PortalPageMain" var="slotComponent">
		<cms:component component="${slotComponent}"/>
	</cms:pageSlot>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>


</section>