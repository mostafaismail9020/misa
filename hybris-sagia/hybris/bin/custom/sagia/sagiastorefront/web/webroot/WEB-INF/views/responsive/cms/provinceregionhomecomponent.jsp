<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
               
<div class="region_component meetRegions_some-class" id="${componentId}" data-id="${componentId}">
	
	<c:if test="${not empty backgroundImage}">
		<img class="region_component_image"  src="${backgroundImage.url}" alt='${backgroundImage.altText}' title='${backgroundImage.altText}' style="" loading="lazy">
		<input type="hidden" id="" value="${backgroundImage.url}">
	</c:if>
	
	<div class="row region-wrapper m-0" >
		
		<div class="col-md-6 region-wrapper-right">
			<c:if test="${not empty title}">
				<h1 >${title}</h1>
			</c:if>
			<c:if test="${not empty longDescription}">
				<h3>${longDescription}</h3>
			</c:if>
			<c:if test="${not empty localizedStats}">
					<div class="box-inner">
						<c:forEach var="entry" items="${localizedStats}">
								<div class="box-num">
									<h4 class="count">${entry.key}</h4>
									<strong> ${entry.value}</strong>
								</div>
						</c:forEach>
					</div>
			</c:if>
						
			<c:set var="province" value="${fn:split(componentId, '_')}" />
			<c:set var="provinceId" value="${province[0]}" />
			<c:url value="/meetTheKingdom/province/${provinceId}" var="provinceUrl"/>
				
			<a class="button btn know-more-btn" href="${provinceUrl}"><spring:theme code="portal.explore.more" text="Explore More"/>&nbsp;
				<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt="">
			</a>
			<!-- <button formaction="${buttonLink}">${buttonText}</button> -->
		</div>
	</div>
</div>
