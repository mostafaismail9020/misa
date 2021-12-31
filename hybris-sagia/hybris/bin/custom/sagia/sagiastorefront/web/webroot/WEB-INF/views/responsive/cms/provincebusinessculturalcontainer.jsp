<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="province-business-culture-container container p-0 region_component meetRegions_some-class" data-id="riyadh_data">
    <div class="row m-0">
        <div class="col-md-12 title-area">
            <div class="col-md-12 RHQ_riyadh_province_detaisl">
            <div class="pt-4 pb-4"> 
                <c:if test="${not empty title}">
                    <h5 class="RHQ_riyadh_province_detaisl_title">${title}</h5>
                </c:if>  
            </div>
            <div class="province-map-details "> 
                <c:forEach var="currentComponent" items="${components}" > 
                    <cms:component component="${currentComponent}" element="div"/>
                </c:forEach> 
            </div>
            </div>
        </div>
    </div>
</div>

</section>
