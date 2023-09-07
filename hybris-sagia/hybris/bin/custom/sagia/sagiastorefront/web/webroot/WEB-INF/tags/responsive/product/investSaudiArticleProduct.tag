<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>
	<div class="article-details-page-banner">
		<div class="article-details-page-banner-container" data-aos="fade-up">
			<div class="container">
				<div class="row">

					<c:if test="${language eq 'en'}">
						<a href="${encodedContextPath}/articlesList">
							<div class="col-md-12 breadcrumb-container">
								<span class="breadcrumb-left-icon"></span>
								<span class="breadcrumb-page-info"><spring:theme
										code="text.article.listing.page.title"/></span>
							</div>
						</a>
					</c:if>
					<c:if test="${language eq 'ar'}">
						<a href="${encodedContextPath}/articlesList">
							<div class="col-md-12 breadcrumb-container">
								<span class="breadcrumb-page-info"><spring:theme
										code="text.article.listing.page.title"/></span>
								<span class="breadcrumb-left-icon"></span>
							</div>
						</a>
					</c:if>

					<div class="col-md-12">
						<h1 class="article-detail-page-general-title">${fn:escapeXml(productData.articleTitle)}</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<section class="article-details-page">
		<div class="container">
			<!-- Top -->
			<div class="row">
				<div class="col-md-12">
					<h1 class="article-detail-page-title">${fn:escapeXml(productData.name)}</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<p>${fn:escapeXml(productData.description)}</p>

				</div>
				<div class="col-md-6 article-detail-page-video">
					<iframe width="100%" src="${productData.videoUrl}" frameborder="0"
							allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
							allowfullscreen></iframe>
				</div>
			</div>

			<!-- Summary Paragraph -->
			<div class="row mt-4 mb-4">
				<div class="col-md-12">
					<p>${fn:escapeXml(productData.summary)}</p>
				</div>
			</div>

			<c:forEach items="${productData.articleTiles}" var="tile" varStatus="status">
				<c:if test="${status.index % 2 == 0}">
					<div class="row mt-5 mb-5 our-goal-and-mission">
						<div class="col-md-6 center-content our-goal">
								${fn:escapeXml(tile.value)}
						</div>
						<div class="col-md-6"></div>
					</div>
				</c:if>
				<c:if test="${status.index % 2 != 0}">
					<div class="row mt-5 mb-5 our-goal-and-mission">
						<div class="col-md-6"></div>
						<div class="col-md-6 center-content our-mission">
								${fn:escapeXml(tile.value)}
						</div>
					</div>
				</c:if>
			</c:forEach>

			<!-- Paragraph -->
			<div class="row mt-4 mb-4">
				<div class="col-md-12">
					<p>${fn:escapeXml(productData.articleThirdPara)}</p>
				</div>
			</div>

			<!-- Our Values -->
			<div class="our-values mt-4">

				<c:forEach items="${productData.subheadingWithMedia}" var="subheadingWithMedia">
					<div class="row">
						<div class="col-md-12 mb-4">
							<h1 class="article-detail-page-subtitle">${fn:escapeXml(subheadingWithMedia.key)}</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<img class="our-values-img mb-4" alt="" src="${subheadingWithMedia.value.url}" />
						</div>
						<div class="col-md-6">
							<div class="value-item">
								<div class="value-icon"></div>
								<div class="value-content">
									<h2>Wealth</h2>
									<p>A powerful G20 economy with macroeconomic fundamentals that offer long-term
										stability and provide a sustainable platform for investment.</p>
								</div>
							</div>

							<div class="value-item">
								<div class="value-icon"></div>
								<div class="value-content">
									<h2>Transparency</h2>
									<p>Enabling a raft of business reforms aimed at easing investment and bolstering
										investor support in the Kingdom.</p>
								</div>
							</div>
							<div class="value-item">
								<div class="value-icon"></div>
								<div class="value-content">
									<h2>Opportunity</h2>
									<p>Creating unique opportunities in new sectors in the Kingdom that have
										hitherto been restricted to international investment.</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

				<c:forEach items="${productData.paraWithMedia}" var="paraWithMedia">
					<div class="row mt-2">
						<div class="col-md-6">
							<p>${fn:escapeXml(paraWithMedia.value.descriptionText)}</p>
						</div>
						<div class="col-md-6">
							<img class="our-values-img" alt="" src="${paraWithMedia.value.url}" />
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="what-we-do mt-3 mb-3">
				<div class="row">
					<c:forEach items="${productData.subHeadings}" var="articleSubHeading">
						<div class="col-md-12 mb-4">
							<h1 class="article-detail-page-subtitle">${fn:escapeXml(articleSubHeading.key)}</h1>
						</div>
						<div class="col-md-12 mb-4">
							<p>${fn:escapeXml(articleSubHeading.value)}</p>
						</div>
					</c:forEach>


					<div class="col-md-12 boxes-container">
						<c:forEach items="${productData.articleSubDetails4Boxes}" var="articleSubDetails4Boxes">
							<div class="col-md-5 mb-4">
								<div class="what-we-do-box">
									<div class="number-box">1</div>
									<div class="box-content">
										<p class="title">${fn:escapeXml(articleSubDetails4Boxes.key)}</p>
										<p>${fn:escapeXml(articleSubDetails4Boxes.value)}</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<div class="col-md-12 mb-4">
						<p>${fn:escapeXml(productData.articleSubDetails5)}</p>
					</div>

				</div>
			</div>

			<!-- Download, Share Buttons-->
			<div class="download-share-buttons">
				<div class="row">
					<div class="col-md-12">
						<a href="${commonResourcePath}/pdf/resource-Economic-Investment-Monitor-Q2-2022-english.pdf" download class="btn-download" id="pdf-en">
							<spring:theme code="facilityReopen.download.text"/>
							<i class="icon-download"></i>
						</a>
						<a href="${commonResourcePath}/pdf/resource-Economic-Investment-Monitor-Q2-2022-arabic.pdf" download class="btn-download" id="pdf-ar">
							<spring:theme code="facilityReopen.download.text"/>
							<i class="icon-download"></i>
						</a>

						<a class="btn-share">
							<i class="icon-share"></i>
							<spring:theme code="product.share.share"/>
						</a>
					</div>
				</div>
			</div>


			<!-- Recent Projects -->
			<div class="recent-projects mt-4">
				<div class="row">
					<div class="col-md-12 mb-4 article-detail-page-title-container">
						<h1 class="article-detail-page-title">Recent Projects</h1>
						<button class="see-all-btn">
							See All
						</button>
					</div>
					<div class="col-md-3 mb-3">
						<div>
							<img class="recent-projects-card-img" alt=""
								 src="https://investsaudi.sa/medias/Banner-5-.jpg?context=bWFzdGVyfHJvb3R8MjY3ODAzfGltYWdlL2pwZWd8aDRjL2g1Mi84OTUyNzU4MzM3NTY2LmpwZ3xiYzVmM2Y1ZTVmZmNmMDUyNzE5YjE4YmI3YTI3MTkxM2NiNmE0NGEzZWY4YTQxMGFjOGFiNTgzNmU3NjE1YTNj"/>
						</div>
						<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
					</div>
					<div class="col-md-3 mb-3">
						<div><img class="recent-projects-card-img" alt=""
								  src="https://investsaudi.sa/medias/Banner-5-.jpg?context=bWFzdGVyfHJvb3R8MjY3ODAzfGltYWdlL2pwZWd8aDRjL2g1Mi84OTUyNzU4MzM3NTY2LmpwZ3xiYzVmM2Y1ZTVmZmNmMDUyNzE5YjE4YmI3YTI3MTkxM2NiNmE0NGEzZWY4YTQxMGFjOGFiNTgzNmU3NjE1YTNj"/>
						</div>
						<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
					</div>
					<div class="col-md-3 mb-3">
						<div><img class="recent-projects-card-img" alt=""
								  src="https://investsaudi.sa/medias/Banner-5-.jpg?context=bWFzdGVyfHJvb3R8MjY3ODAzfGltYWdlL2pwZWd8aDRjL2g1Mi84OTUyNzU4MzM3NTY2LmpwZ3xiYzVmM2Y1ZTVmZmNmMDUyNzE5YjE4YmI3YTI3MTkxM2NiNmE0NGEzZWY4YTQxMGFjOGFiNTgzNmU3NjE1YTNj"/>
						</div>
						<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
					</div>
					<div class="col-md-3 mb-3">
						<div><img class="recent-projects-card-img" alt=""
								  src="https://investsaudi.sa/medias/Banner-5-.jpg?context=bWFzdGVyfHJvb3R8MjY3ODAzfGltYWdlL2pwZWd8aDRjL2g1Mi84OTUyNzU4MzM3NTY2LmpwZ3xiYzVmM2Y1ZTVmZmNmMDUyNzE5YjE4YmI3YTI3MTkxM2NiNmE0NGEzZWY4YTQxMGFjOGFiNTgzNmU3NjE1YTNj"/>
						</div>
						<div class="recent-projects-card-title">Saudi investment ministry signs four investment agreements</div>
					</div>
				</div>
			</div>

		</div>
	</section>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
