<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ attribute name="className" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-sm-12">
	<div class="article mb-4">
		<div class="information">
			<a href="${encodedContextPath}${result.url}" title="${result.name}" class="title">${result.name}</a>
			<c:choose>
				<c:when test="${fn:length(result.description) gt 150}">
					<div class="description">${fn:substring(result.description, 0, 150)}...</div>
				</c:when>
				<c:otherwise>
					<div class="description">${result.description}</div>
				</c:otherwise>
			</c:choose>							
		</div>
	</div>
</div>


