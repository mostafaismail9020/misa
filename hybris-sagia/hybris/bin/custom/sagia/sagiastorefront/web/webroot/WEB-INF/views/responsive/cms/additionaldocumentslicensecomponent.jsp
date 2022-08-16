<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="col additional_lic_inner_box">
  <div class="additional_documents_license_component inner_box" data-aos="fade-up">
    
    <c:if test="${not empty icon}">
      <img class="img-fluid" src="${icon.url}" alt='${icon.altText}' title='${icon.altText}' style="" loading="lazy">
    </c:if>
    
    <c:if test="${not empty headerText}">
        <h6 class="title-heading ">${headerText}</h6>
      </c:if> 
      <c:if test="${not empty longDescription}">
          <h3>${longDescription}</h3>
      </c:if>
  </div> 
</div> 
	

