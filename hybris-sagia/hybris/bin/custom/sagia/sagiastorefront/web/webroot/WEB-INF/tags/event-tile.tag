<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="loopCount" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <div class="col-sm-12 col-md-6 col-lg-3">
                <div class="event">
				<a href="${encodedContextPath}${result.url}" class="know-more-link">
                    <div class="image">
                        <div class="date">
                            <span class="day"><fmt:formatDate value="${result.eventDate}" type="both" dateStyle="long" timeStyle="long" pattern="d" /></span>
                            <span class="month"><fmt:formatDate value="${result.eventDate}" type="both" dateStyle="long" timeStyle="long" pattern="MMM" /></span>
                        </div>
						<c:choose>
                   <c:when test="${fn:length(result.imageUrl) gt 0}">
                 	  <img class="img-fluid" src="${result.imageUrl}" alt="" loading="lazy">
                   </c:when>
                   <c:otherwise>
                 	  <img class="img-fluid" src="${commonResourcePath}/images/default-product-image.png" alt="" loading="lazy">
                   </c:otherwise>
                </c:choose>
                    </div>
					</a>
					<div class="time-and-location">
                        <time class="time">${result.eventTiming}</time>
                        <span class="location">${result.eventLocation}</span>
                    </div>
                    <p class="description">
                        <a href="${encodedContextPath}${result.url}" class="know-more-link">${fn:toLowerCase(result.name)}</a>
                    </p>
                </div>
              </div>

