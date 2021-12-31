<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<span class="f-35">${fn:escapeXml(component.figures)}</span>
<span class="gray-color mx-0">${fn:escapeXml(component.unit)}</span>
<div class="text-static font-bold">${fn:escapeXml(component.facts)}</div>

