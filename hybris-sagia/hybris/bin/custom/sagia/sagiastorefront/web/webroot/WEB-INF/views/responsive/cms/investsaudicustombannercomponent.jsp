<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<a href="${portal.cmsLinkUrl(url)}">
    <img class="corner-borders" src="${fn:escapeXml(backgroundImage.url)}" alt="${fn:escapeXml(backgroundImage.altText)}">
    <h5 class="font-bold heading-img mt-4">${title}</h5>
    <h6 class="diff-color">${additionalText} ></h6>
</a>




