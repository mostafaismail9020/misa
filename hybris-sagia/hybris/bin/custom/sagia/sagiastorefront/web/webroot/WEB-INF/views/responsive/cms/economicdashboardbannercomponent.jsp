<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="thumbnail">
	<c:if test="${not empty backgroundImage}">
		<img class="img-fluid close-icon" src="${tailBanner.url}" alt='${backgroundImage.altText}' title='${backgroundImage.altText}' style="">
		<div class="eh-page-grid-link">
			<c:if test="${not empty link.linkName}">
				<a href="${encodedContextPath}/${link.url}">${link.linkName} <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/></a>
			</c:if>
		</div>
	</c:if>
</div>


<!-- <div class="economic_tail_banner_component container" style = "background-image:url(${tailBanner.url})">
	
	
	<c:if test="${not empty tailHeader}">
			
			<a href="${link}"><h1>${tailHeader}</h1></a>
		</c:if>
	
</div> -->
