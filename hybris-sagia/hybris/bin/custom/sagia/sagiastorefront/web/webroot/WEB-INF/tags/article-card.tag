<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ attribute name="className" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="articles-list mb-4 col-md-6">
	<a href="${encodedContextPath}${result.opportunity.url}" title="${result.opportunity.name}">
		<div class="articles-list-item ${className}">
			${fn:escapeXml(result.opportunity.name)}
			<p>
				<c:choose>
					<c:when test="${fn:length(result.opportunity.description) gt 150}">
						${fn:escapeXml(fn:substring(result.opportunity.description, 0, 150))}...
					</c:when>
					<c:otherwise>
						${fn:escapeXml(result.opportunity.description)}
					</c:otherwise>
				</c:choose>
			</p>
		</div>
	</a>
</div>