<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-md-12 announcementsBox box-white-background <c:if test="${loopCount % 2 == 0 }">alternate</c:if>" data-aos="fade-up" data-aos-delay="${loopCount}">
    <div class="row">
    	<div class="col-md-3 col-3">
	        <img class="img-fluid service-card-img resources-card-img"
	             src="${commonResourcePath}/images/default-product-image.png"
	             alt="" loading="lazy">
	    </div>
	    <div class="col-md-9 col-9 containerPadding">
	        <div class="row">
	            <div class="col-md-4">
	            	<a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
		                <span class="opportunity-item-title">${fn:toLowerCase(result.opportunity.name)}</span>
		                 <div class="opportunity-item-desc">
			                 <c:choose>
				                 <c:when test="${fn:length(result.opportunity.description) gt 50}">
				                 	${fn:substring({result.opportunity.description}, 1, 55)}...
			                 	 </c:when>
				                 <c:otherwise>
				                 	${result.opportunity.description}
				                 </c:otherwise>
			                 </c:choose>
		                 </div>
		            </a>
	            </div>
	            <div class="col-md-4 mt-3 mt-md-0">
	            	<a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
		                <p class="opportunity-item-irr">Expected IRR: ~16%</p>
		            </a>
	            </div>
	            <div class="col-md-4">
	            	<button type="button" class="btn btn-primary parentCategory-button mt-1 mt-md-0" onclick="redirectToLink('${result.parentCategory.url}')" style="border: none;">
	                    ${fn:toLowerCase(result.parentCategory.name)}
	                </button>
	            </div>
	        </div>
	    </div>
    </div>
</div>
