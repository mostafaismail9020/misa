<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="col-sm-12 col-md-4">
	<div class="news">
             <a href="${encodedContextPath}${result.url}" class="know-more-link">
            	<c:choose>
                   <c:when test="${fn:length(result.imageUrl) gt 0}">
                 	  <img class="img-fluid image" src="${result.imageUrl}" alt="" loading="lazy">
                   </c:when>
                   <c:otherwise>
                 	  <img class="img-fluid image" src="${commonResourcePath}/images/default-product-image.png" alt="" loading="lazy">
                   </c:otherwise>
                </c:choose>
            </a>
		<div class="headline-and-date">
			<a href="${encodedContextPath}${result.url}" class="headline">${fn:toLowerCase(result.name)}</a>
			<div class="date">${result.newsDate}</div>
		</div>
	</div>
</div>