<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="tab-section Inc-mediaCenter-sectionwrapper">
    <c:url value="/mediaCenter/events" var="eventsUrl" />
    <div class="row">
        <div class="col-sm-12 col-md-3 mb-5">
            <div class="flip-card">
                <a href="${newsUrl}/${currentComponent.uid}">
                    <div class="flip-card-front">
                        <div class="news-date text-center">
                            <div class="day">
                                <fmt:formatDate value="${result.eventDate}" pattern="d" />
                            </div>
                            <div class="month">
                                <fmt:formatDate value="${result.eventDate}" pattern="MMM" />
                            </div>
                        </div>
                        <div class="card-img">
                            <img class="img-fluid events-card-img" src="${result.imageUrl}" alt="${result.name}" loading="lazy">
                        </div>
                        <div class="flip-card-text">
                            <h2 class="eventName">${fn:toLowerCase(result.name)}</h2>
                            <p class="eventShortInformation">${result.eventLocation}</p>
                            <p class="eventShortInformation">${result.description}</p>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</section>
