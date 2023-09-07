<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <div class="col-sm-12 col-md-12 col-lg-4 mb-5 report">
                             <div class="events-card">
                                 <a href="${encodedContextPath}${result.opportunity.url}">
                                     <div class="row">
                                         <div class="col-md-12">
                                             <div class="card-image">
                                                 <img class="img-fluid media-card-img" src="https://investsaudi.sa/medias/all-opportunity.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw4OTU5MzZ8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDljL2hiNC84OTA5NDE5MzQ3OTk4LmpwZ3xiZGM4NzA3ZTZmNDc5ZmJkZDJlZDQ5MjI2ODQ4YzQ1NWY0YTk4N2RlN2QxMTVlNTAyM2Q5NGQ4OWEyZjQ3MGY4" alt="" loading="lazy">
                                             </div>
                                         </div>

                                         <div class="col-md-12">
                                             <div class="card-text">
                                             <p class="reportDate d-report-update-date">
                                                        <fmt:formatDate value="${result.opportunity.reportDate}" pattern="d MMM yyyy" />
                                                    </p>
                                                 <h3 class="reportName">${result.opportunity.name}</h3>
                                                 <h3 class="reportType">${fn:substring(result.opportunity.description, 0, 45)}</h3>
                                             </div>
                                         </div>
                                     </div>
                                 </a>
                             </div>
                         </div>

