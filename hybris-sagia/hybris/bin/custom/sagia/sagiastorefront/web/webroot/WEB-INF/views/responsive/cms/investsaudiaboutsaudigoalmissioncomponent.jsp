<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

 
<img class="img-fluid" src="${fn:escapeXml(imageIcon.url)}"
     data-norm="${fn:escapeXml(imageIcon.url)}"
     data-alt="${fn:escapeXml(imageIcon.url)}" alt=""/>
     <h4>${value}</h4>
     <p>${text}</p>
     
