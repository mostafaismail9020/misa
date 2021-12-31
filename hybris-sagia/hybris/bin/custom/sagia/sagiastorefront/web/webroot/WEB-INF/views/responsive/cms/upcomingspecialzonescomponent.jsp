<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="upcoming_special_zone_component">
	
	<c:if test="${not empty backgroundImage}">
		<img class="img-fluid w-20" src="${backgroundImage.url}" alt='${backgroundImage.altText}' title='${backgroundImage.altText}' style="">
	</c:if>
	
	<c:if test="${not empty headerText}">
             <h2>${headerText}</h2>
         </c:if> 
          <c:if test="${not empty text}">
                <h4>${text}</h4>
            </c:if>
            <c:if test="${not empty longDescription}">
                <span>${longDescription}</span>
            </c:if>
            
		</div>
		
	

