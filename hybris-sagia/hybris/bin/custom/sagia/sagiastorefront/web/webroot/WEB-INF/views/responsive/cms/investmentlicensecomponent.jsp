<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               

	
  <c:if test="${not empty localizedImage}">
    <img src="${localizedImage.url}" alt='${localizedImage.altText}' title='${localizedImage.altText}' style="">
  </c:if>
    
  <c:if test="${not empty headerText}">
    <p><span >${headerText}</span>
  </c:if> 
  <c:if test="${not empty description}">
    ${description}</p>
  </c:if>
 
	

