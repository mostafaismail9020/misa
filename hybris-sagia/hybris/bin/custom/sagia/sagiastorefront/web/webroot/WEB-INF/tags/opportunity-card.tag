<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--
<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 my-4 opportunity-card text-center">
    <div class="content-box">
        <h2 class="h1 font-bold opp-headtitle text-capitalize">${fn:toLowerCase(result.opportunity.name)}</h2>
        <h3 class="opp-type font-bold text-capitalize">${fn:toLowerCase(result.parentCategory.name)}</h3>
        <div class="d-flex justify-content-between opportunities-cart-button flex-wrap ">
            <a href="${encodedContextPath}${result.opportunity.url}" class="button btn know-more-btn">
                <spring:theme code="portal.opportunity.know.more.button"/>&nbsp;
                <img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
            </a>
            <a href="${encodedContextPath}${result.opportunity.url}/?scrollTo=contact" class="button btn interest-btn">
                <spring:theme code="portal.opportunity.iam.interested.button"/>&nbsp;
                <img class="img-fluid arrow-icon" src="${commonResourcePath}/images/arrow_blue.png" alt=""/>
            </a>
        </div>
    </div>
</div>
-->




<div class="col-md-12 card-wrapper" data-aos="fade-up" data-aos-delay="${loopCount}">
    <div class="flip-card flip-card-custom row">
        <div class="col-md-3">
            <a class="know-more-link" href="${newsUrl}/${currentComponent.uid}">
                <img class="img-fluid" src="https://pixabay.com/get/gc02f7b13514e3f05c2b6f8358024e33ba31efd4d3bd3843d9fbcea38c6bcabfc2f3276190022b9be15391f8a4dde8cc2_1280.jpg" alt="" loading="lazy">
            </a>
        </div>
        <div class="col-md-3">
            <a href="${encodedContextPath}${result.opportunity.url}" class="know-more-link">
                <p>${fn:toLowerCase(result.opportunity.name)}</p>
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

