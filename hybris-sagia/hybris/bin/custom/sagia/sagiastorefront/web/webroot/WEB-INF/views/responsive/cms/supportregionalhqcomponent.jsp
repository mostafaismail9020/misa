<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="support_regional_hq_component panel-box aos-init " data-aos="fade-up " data-aos-delay="0 ">    
    <c:if test="${not empty image}">
        <div class="box-img "><img class="img-fluid w-40" src="${image.url}" alt='${image.altText}' title='${image.altText}' style=""></div>
    </c:if>
   
    <ul class="list ">
        <c:if test="${not empty icon}">            
            <img class="img-fluid w-40 service-icon" src="${icon.url}" alt='${icon.altText}' title='${icon.altText}' style="">
        </c:if>
   
        <c:if test="${not empty headerText}">
            <h1 class="regional-hq-service-heading">${headerText}</h1>
        </c:if>
        <c:if test="${not empty description}">
            <li class="regional-hq-services">${description}</li>
            <!-- <h4 class="regional-hq-services">${description}</h4> -->              
        </c:if>
    </ul>
</div>
<!-- <div class="support_regional_hq_component">
	
	<c:if test="${not empty image}">
		<img class="img-fluid w-40" src="${image.url}" alt='${image.altText}' title='${image.altText}' style="">
	</c:if>
	
	<c:if test="${not empty icon}">
		<img class="img-fluid w-40" src="${icon.url}" alt='${icon.altText}' title='${icon.altText}' style="">
	</c:if>
	
	<c:if test="${not empty headerText}">
             <h1>${headerText}</h1>
         </c:if> 
          <c:if test="${not empty description}">
                <h4>${description}</h4>
            </c:if>
  	</div> -->
		
	

