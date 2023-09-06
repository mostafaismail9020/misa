<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ attribute name="className" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="articles-list mb-4 col-md-6">
	<a href="${encodedContextPath}${result.url}" title="${result.name}">
		<div class="articles-list-item ${className}">
			${result.name}
			<p>
				<c:choose>
					<c:when test="${fn:length(result.description) gt 150}">
						${fn:substring(result.description, 0, 150)}...
					</c:when>
					<c:otherwise>
						${result.description}
					</c:otherwise>
				</c:choose>
			</p>
		</div>
	</a>
</div>