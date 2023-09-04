<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-sm-12">
	<div class="article">
		<div class="image"></div>
		<div class="information">
			<div class="date">12:00pm - 01:00pm</div>
			<a href="${encodedContextPath}${result.url}" class="title">${result.name}</a>
			<p class="description">
				<c:choose>
					<c:when test="${fn:length(result.description) gt 50}">
						${fn:substring(result.description, 0, 50)}...
					</c:when>
					<c:otherwise>
						${result.description}
					</c:otherwise>
				</c:choose>							
			</p>
		</div>
	</div>
	</div>