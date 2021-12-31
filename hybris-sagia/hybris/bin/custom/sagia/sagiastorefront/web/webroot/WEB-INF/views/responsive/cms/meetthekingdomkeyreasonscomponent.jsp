<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
               
<div class="key_reasons_component">
	
	<c:if test="${not empty backgroundImage}">
		<img class="img-fluid w-100" src="${backgroundImage.url}" alt='${backgroundImage.altText}' title='${backgroundImage.altText}' style="">
	</c:if>
	
	<!-- <c:if test="${not empty videoLink}">
		<div class="video-player-container">
			<div class="embed-responsive embed-responsive-16by9">
				<iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="315" 
					src="${fn:escapeXml(videoLink.url)}" 
					frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
				</iframe>
			</div>
		</div>
	</c:if> -->
	
	<div class="Inc-tab-banner-content ">
		<c:if test="${not empty title}">
			<h1>${title}</h1>
		</c:if>
		<c:if test="${not empty subtitle}">
			<h2>${subtitle}</h2>
		</c:if>
		<c:if test="${not empty text}">
			<h3>${text}</h3>
		</c:if>
		<c:if test="${not empty videoLink}">
			<div class="text-center trigger-videoModal"  data-url="${fn:escapeXml(videoLink.url)}">
				<img class="img-fluid play-button" src="${commonResourcePath}/images/play_video.png" alt="" />
			</div>
			<input type="hidden" id="" value="${fn:escapeXml(videoLink.url)}">
		</c:if>

		<div class="count-outer">
			<c:if test="${not empty localizedStats}">
				<c:forEach var="entry" items="${localizedStats}">
					<div class="count-block">
						<h5><c:out value="${entry.key}" /></h5>
						<p><c:out value="${entry.value}" /></p>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>
