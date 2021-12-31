<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="brach-location">
    <p class="font-bold">${officeName}</p>
    <ul class="list-unstyled m-0">
        <li class="b-map">${address}</li>
        <li class="b-phone">${telephone}</li>
        <li class="b-building row">${buildingNumber}</li>
        <li class="b-building">${additionalNumber}</li>
        <li class="b-postal">${postalCode}</li>
    </ul>
    <a class="button btn org-button btn-green js-show-map" data-type-lat="${dataTypeLat}" data-type-lng="${dataTypeLng}"><spring:theme code="text.button.show.map"/></a>
</div>

