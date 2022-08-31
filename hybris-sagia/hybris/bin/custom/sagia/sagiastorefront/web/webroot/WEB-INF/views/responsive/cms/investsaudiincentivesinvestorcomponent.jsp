<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<!-- ======= Incentives for Investors Component Starts ======= -->
<div class="card  text-center border-0 inc-card">
	<span class="d-block pb-4 img-box"> 
	<img src="${fn:escapeXml(imageNormal.url)}" loading="lazy">
	</span>
	<h4 class="card-title">${title}</h4>
	<p>${description}</p>
</div>
<!-- ======= Incentives for Investors Component Ends ======= -->
