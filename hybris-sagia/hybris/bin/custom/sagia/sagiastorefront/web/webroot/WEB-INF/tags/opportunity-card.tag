<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="col-md-12 card-wrapper" data-aos="fade-up" data-aos-delay="${loopCount}">
    <div class="flip-card flip-card-custom row">
        <div class="col-md-3">
            <a class="know-more-link" href="${newsUrl}/${currentComponent.uid}">
                <img class="img-fluid" src="https://pixabay.com/get/g48741768e85aa9caa5e102358bf75c4e994c4a6328b84bc951459741fd9a2db5558456d89155d0c17fa8694265c32c94_1280.jpg" alt="" loading="lazy">
            </a>
        </div>
        <div class="col-md-3">
            <a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
                <strong>${fn:toLowerCase(result.opportunity.name)}</strong>
                 <p>This opportunity entails building and operating zoos of various sizes across the Kingdom</p>
            </a>
        </div>
        <div class="col-md-3">
            <a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
                <p>Expected IRR: ~16%</p>
            </a>
        </div>
        <div class="col-md-3">
            <a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
                <button type="button" class="btn btn-primary parentCategory-button" onclick="redirectToLink('${result.parentCategory.url}')">
                    ${fn:toLowerCase(result.parentCategory.name)}
                </button>
            </a>
        </div>
    </div>
</div>
<hr style="width: 90%">

