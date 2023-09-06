<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>
<%@ attribute name="loopCount" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <div class="col-sm-12 col-md-12 col-lg-4 mb-5">
                             <div class="events-card">
                                 <a href="${encodedContextPath}${result.opportunity.url}">
                                     <div class="row">
                                         <div class="col-sm-4">
                                             <div class="card-image">
                                                 <img class="img-fluid media-card-img" src="${commonResourcePath}/images/default-product-image.png" alt="" loading="lazy">
                                             </div>
                                         </div>

                                         <div class="col-sm-8">
                                             <div class="card-text">
                                                 <h3 class="reportName">${fn:toLowerCase(result.opportunity.name)}</h3>
                                                 <p class="reportDate d-report-update-date">
                                                     <fmt:formatDate value="${result.opportunity.reportDate}" pattern="d MMM yyyy" />
                                                 </p>
                                             </div>
                                         </div>
                                     </div>
                                 </a>
                             </div>
                         </div>

