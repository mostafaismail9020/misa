<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="types_investor_license_component">
		
  <c:if test="${not empty icon}">
    <img class="img-fluid w-20" src="${icon.url}" alt='${icon.altText}' title='${icon.altText}' style="">
    <p>${text}</p>
  </c:if>

  <div class="hover_box">
    <div class="hover_box_inner">
      <div class="hover_box_inner_child">
        <c:if test="${not empty text}">
          <p>${text}</p>
          <small>${longDescription}</small>
        </c:if> 
      </div>
    </div>
  </div> 
  
</div> 
	

