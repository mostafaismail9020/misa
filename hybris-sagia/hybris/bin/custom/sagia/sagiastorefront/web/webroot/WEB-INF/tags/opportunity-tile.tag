<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="col-sm-12 show-opportunity opportunity-${loopCount + 1} <c:if test="${loopCount + 1 gt 5}"> opportunity-hidden</c:if>">
	<div class="opportunity search <c:if test="${loopCount % 2 == 0 }">alternate</c:if>">
		<div class="image">
            <a href="${encodedContextPath}${result.url}" class="link">
            	<c:choose>
                   <c:when test="${fn:length(result.imageUrl) gt 0}">
                 	  <img class="img-fluid" src="${result.imageUrl}" alt="" loading="lazy">
                   </c:when>
                   <c:otherwise>
                 	  <img class="img-fluid" src="${commonResourcePath}/images/default-product-image.png" alt="" loading="lazy">
                   </c:otherwise>
                </c:choose>
            </a>		
		</div>
		<div class="information">
			<div class="title-and-description">
				<a href="${encodedContextPath}${result.url}" class="link">
					<span class="title">${result.name}</span>
					<p>
						<c:choose>
							<c:when test="${fn:length(result.description) gt 50}">
							${fn:substring({result.description}, 1, 50)}...</c:when>
							<c:otherwise>
							${result.description}
							</c:otherwise>
						</c:choose>
					</p>
				</a>
			</div>
			<div class="market-info">Expected IRR: ~16%</div>
			<div class="sector-name">${result.parentCategory}</div>
		</div>
	</div>
</div>

<script>
    function redirectToLink(url) {

        var baseUrl = window.location.origin + window.location.pathname.replace(/\/en\//, '/');

        var completeUrl = baseUrl + url;

        window.location.href = completeUrl;
    }
</script>
<script>
    document.getElementById("js-site-search-input").addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            var form = document.querySelector("form[name='search_form_${fn:escapeXml(component.uid)}']");
            form.submit();
        }
    });
</script>