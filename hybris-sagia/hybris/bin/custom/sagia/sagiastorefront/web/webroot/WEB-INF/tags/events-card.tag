<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-md-12 card-wrapper fixed-height <c:if test="${loopCount % 2 == 0 }">alternate</c:if>" data-aos="fade-up" data-aos-delay="${loopCount}">
    <div class="flip-card flip-card-custom row">
        <div class="col-md-3 col-4 img-opp-container">
            <a class="know-more-link" href="${newsUrl}/${currentComponent.uid}">
            	<c:choose>
                   <c:when test="${fn:length(result.opportunity.imageUrl) gt 0}">
                 	  <img class="img-fluid" src="${result.opportunity.imageUrl}" alt="" loading="lazy">
                   </c:when>
                   <c:otherwise>
                 	  <img class="img-fluid" src="${commonResourcePath}/images/default-product-image.png" alt="" loading="lazy">
                   </c:otherwise>
                </c:choose>
            </a>
        </div>
        <div class="col-md-9 col-8" style="position: relative;">
        	<div class="row" style="margin: 0; position: absolute; top: 50%; -ms-transform: translateY(-50%); transform: translateY(-50%); width:85%">
        		<div class="col-md-6 col-12">
		            <a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
		                <strong>${fn:toLowerCase(result.opportunity.name)}</strong>
		                 <p>
		                 <c:choose>
			                 <c:when test="${fn:length(result.opportunity.description) gt 50}">
			                 	${fn:substring({result.opportunity.description}, 1, 50)}...
			                 </c:when>
			                 <c:otherwise>
			                 	${result.opportunity.description}
			                 </c:otherwise>
		                 </c:choose>
		                 </p>
		                 <p>${result.opportunity.eventTiming}</p>
		            </a>
		        </div>		                            
		        <div class="col-md-3 col-12">
		            <a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
		                <p>${result.opportunity.eventDate}</p>
		            </a>
		        </div>
		        <div class="col-md-3 col-12">
		                <button type="button" class="btn btn-primary parentCategory-button" onclick="redirectToLink('${result.parentCategory.url}')" style="border: none;">
		                    ${result.opportunity.eventLocation}
		                </button>
		            </a>
		        </div>
        	</div>
        </div>
    </div>
</div>