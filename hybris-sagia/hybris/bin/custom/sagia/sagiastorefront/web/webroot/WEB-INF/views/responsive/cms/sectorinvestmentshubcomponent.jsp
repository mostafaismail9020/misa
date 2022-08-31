<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="panel panle-box" data-aos="fade-left">
    <div class="panel-img">
		<img class="img-fluid" src="${fn:escapeXml(imageIcon.url)}" data-norm="${fn:escapeXml(imageIcon.url)}" 
			data-alt="${fn:escapeXml(imageIcon.url)}" alt="" loading="lazy"/>
	</div>
	<p><a href="${investmentLink.url}">${investmentLink.linkName}</a></p>
</div>