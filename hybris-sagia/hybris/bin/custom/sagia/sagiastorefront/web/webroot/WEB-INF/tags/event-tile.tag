<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/mediaCenter/events" var="eventsUrl" />
<div class="col-md-3 mb-5 event show-event event-${loopCount + 1} <c:if test="${loopCount + 1 gt 8}"> event-hidden</c:if>">
        <div class="flip-card">
            <a href="${encodedContextPath}${result.url}" class="know-more-link">
                <div class="flip-card-front">
                    <div class="news-date text-center">
                        <span class="eventDay"><fmt:formatDate value="${result.eventDate}" type="both" dateStyle="long" timeStyle="long" pattern="d" /></span>
                        <span class="eventMonth"><fmt:formatDate value="${result.eventDate}" type="both" dateStyle="long" timeStyle="long" pattern="MMM" /></span>
                    </div>
                    <div class="card-img">
                        <img class="img-fluid events-card-img" src="${result.imageUrl}" alt="${result.name}" loading="lazy">
                    </div>
                    <div class="flip-card-text">

                        <span class="eventShortInformation mt-2 mb-2">${result.eventTiming}</span>
                        <span class="eventShortInformation mt-2 mb-2">${result.eventLocation}</span>
                    </div>

                   <c:choose>
                    <c:when test="${fn:length(result.name) gt 20}">
                         <span class="eventsName mt-2 mb-2">${fn:substring(result.name, 0, 20)}...</span>
                    </c:when>
                    <c:otherwise>
                          <span class="eventsName mt-2 mb-2">${result.name}</span>
                    </c:otherwise>
                 </c:choose>

                </div>
            </a>
        </div>
</div>