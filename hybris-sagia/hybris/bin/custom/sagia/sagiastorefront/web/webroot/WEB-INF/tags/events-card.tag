<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/mediaCenter/events" var="eventsUrl" />
<div class="col-12 col-md-3 mb-5 event">
    <div class="row">
        <div class="flip-card">
            <a href="${encodedContextPath}${result.url}" class="know-more-link">
                <div class="flip-card-front">
                    <div class="news-date text-center">
                        <p class="eventDay"><fmt:formatDate value="${result.eventDate}" type="both" dateStyle="long" timeStyle="long" pattern="d" /></p>
                        <p class="eventMonth"><fmt:formatDate value="${result.eventDate}" type="both" dateStyle="long" timeStyle="long" pattern="MMM" /></p>
                    </div>
                    <div class="card-img">
                        <img class="img-fluid events-card-img" src="${result.imageUrl}" alt="${result.name}" loading="lazy">
                    </div>
                    <div class="flip-card-text">
                        <div class="row justify-content-between">
                            <p class="eventShortInformation col-md-6 col-6">${result.eventTiming}</p>
                            <p class="eventShortInformation col-md-6 col-6">${result.eventLocation}</p>
                        </div>
                        <p class="eventsName">${result.name}</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
