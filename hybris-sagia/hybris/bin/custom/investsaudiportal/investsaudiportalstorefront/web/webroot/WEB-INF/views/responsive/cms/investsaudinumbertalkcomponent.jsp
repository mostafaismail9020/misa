<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<img src="${fn:escapeXml(imageNormal.url)}"
     data-norm="${fn:escapeXml(imageNormal.url)}"
     data-alt="${fn:escapeXml(imageHover.url)}" alt="Total Population"/>
<div class="font-bold english-font">${number}</div>
<div class="circle-header">${text}</div>
