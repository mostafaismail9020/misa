<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<a href="${portal.cmsLinkUrl(component.url)}" class="${requestScope['javax.servlet.forward.request_uri'] == portal.cmsLinkUrl(component.url) ? 'active' : ''} "><label>${component.url.linkName}</label></a>

