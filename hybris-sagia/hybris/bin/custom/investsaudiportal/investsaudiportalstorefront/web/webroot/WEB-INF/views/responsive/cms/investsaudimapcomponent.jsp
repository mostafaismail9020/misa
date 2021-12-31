<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:if test="${component.visible}">
    <input type="hidden" id="hfLat" value="24.737773" />
    <input type="hidden" id="hfLng" value="46.636507" />
    <div id="contactLoc" class="gmap"></div>
    
    
    <c:set var="includeMapJavascripts" scope="session" value="true" />
    <c:set var="googleMapsAPIKey" scope="session" value="${component.googleMapsAPIKey}" />
</c:if>

