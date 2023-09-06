<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="latest-news">
    <a href="${encodedContextPath}${result.url}" class="link">
        <div class="image-wrapper">
            <img class="image"
                 src="https://investsaudi.sa/medias/all-opportunity.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw4OTU5MzZ8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDljL2hiNC84OTA5NDE5MzQ3OTk4LmpwZ3xiZGM4NzA3ZTZmNDc5ZmJkZDJlZDQ5MjI2ODQ4YzQ1NWY0YTk4N2RlN2QxMTVlNTAyM2Q5NGQ4OWEyZjQ3MGY4"
                 alt="" loading="lazy">
        </div>
        <div class="text">
            <h3 class="title mb-2">${fn:toLowerCase(result.name)}</h3>
            <p class="date">
                <fmt:formatDate value="${result.newsDate}" pattern="d MMM yyyy"/>
            </p>
        </div>
    </a>
</div>


