<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="province-business-culture_component">
	 
      <div class="col">
        <c:if test="${not empty localizedMapLogo}">
          <img class="img-fluid" src="${localizedMapLogo.url}" alt='${localizedMapLogo.altText}' title='${localizedMapLogo.altText}' style="">
        </c:if> 
      </div>
      
      <div class="d-flex rhq-map-info-wrapper">
          <c:if test="${not empty localizedStats}"> 
            <c:forEach var="entry" items="${localizedStats}">
              <div class="col flex-column rhq-map-info">
                <div class="map-info-header"><c:out value="${entry.key}"/></div>
                <div class="map-info-text"><c:out value="${entry.value}"/></div>
              </div>
            </c:forEach>
        </c:if>  
      </div>
    </div>

     <div class="col-md-12 RHQ_riyadh_province_detaisl mb-4"> 
      <div class="pt-4 pb-4"> 
        <c:if test="${not empty headerText}">
          <h5 class="RHQ_riyadh_province_detaisl_title">${headerText}</h5>
          </c:if> 
      </div> 
         
        <div class="d-flex rhq-map-info-wrapper">
                <c:if test="${not empty provinceCultures}">
                  <c:forEach var="culture" items="${provinceCultures}" >
                  <div class="col flex-column rhq-events-block">
                        <c:if test="${not empty culture.image}">
                        <img class="img-fluid " src="${culture.image.url}" alt='${culture.image.altText}' title='${culture.image.altText}' style="">
                      </c:if>
                      <c:if test="${not empty culture.title}">
                        <h4 class="rhq-events">${culture.title}</h4>
                      </c:if>
                 </div> 
                  </c:forEach>  
                </c:if>
         
        </div> 
	      
		</div>
		
	

