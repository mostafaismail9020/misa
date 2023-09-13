<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-sm-12">
	<div class="opportunity announcementsBox box-white-background <c:if test="${loopCount % 2 == 0 }">alternate</c:if>" data-aos="fade-up" data-aos-delay="${loopCount}">
		<div class="image">
            <a href="${encodedContextPath}${result.opportunity.url}" class="link">
	        	<img class="img-fluid service-card-img resources-card-img" src="${commonResourcePath}/images/default-product-image.png" alt="" loading="lazy">
            </a>		
		</div>
		<div class="information">
			<div class="title-and-description">
				<a href="${encodedContextPath}${result.opportunity.url}" class="link">
					<span class="title">${fn:toLowerCase(result.opportunity.name)}</span>
					<p>
						<c:choose>
							<c:when test="${fn:length(result.opportunity.description) gt 50}">
								${fn:substring({result.opportunity.description}, 1, 55)}...
							</c:when>
							<c:otherwise>
								${result.opportunity.description}
							</c:otherwise>
						</c:choose>
					</p>
				</a>
			</div>
			<div class="market-info">Expected IRR: ~16%</div>
			<div class="sector-name" onclick="redirectToLink('${result.parentCategory.url}')">${fn:toLowerCase(result.parentCategory.name)}</div>
		</div>
	</div>
</div>