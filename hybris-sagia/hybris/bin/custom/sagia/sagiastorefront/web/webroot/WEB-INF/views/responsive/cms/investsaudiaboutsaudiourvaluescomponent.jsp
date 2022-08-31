<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="icon-box">
     <div class="icon d-inline-block">
          <img src="${fn:escapeXml(imageIcon.url)}"
          data-norm="${fn:escapeXml(imageIcon.url)}"
          data-alt="${fn:escapeXml(imageIcon.url)}" alt="" loading="lazy"/>
     </div>
     <div class="icon-desc d-inline-block">
         <h4>${value}</h4>
         <p>${text}</p>
     </div>
 </div>



