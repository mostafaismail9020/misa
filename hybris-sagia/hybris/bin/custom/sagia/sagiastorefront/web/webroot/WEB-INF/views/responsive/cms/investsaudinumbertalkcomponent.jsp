<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<!-- ======= When Number Talk Component Starts ======= -->
<div class="talk_item">
<img src="${fn:escapeXml(imageNormal.url)}" data-norm="${fn:escapeXml(imageNormal.url)}" data-alt="${fn:escapeXml(imageHover.url)}" alt="Total Population"   loading="lazy"/>
<div class="talk_item-number">${number}</div>
<div class="talk_item-text">${text}</div>
</div>
<!-- ======= When Number Talk Component Ends ======= -->
