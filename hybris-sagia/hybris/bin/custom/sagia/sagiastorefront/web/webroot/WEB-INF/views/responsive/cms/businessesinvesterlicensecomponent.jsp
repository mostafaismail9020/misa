<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               

	
	<c:if test="${not empty icon}">
		<img class="img-fluid w-40" src="${icon.url}" alt='${icon.altText}' title='${icon.altText}' style="">
	</c:if>
	
	<c:if test="${not empty text}">
		<p><span>${text}</span></p>
	</c:if>  
	

	

