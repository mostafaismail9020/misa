<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
    <div class="container">
    <img class="img-fluid" src="${component.ourserviceimage.url}" loading="lazy"/>
        <h2 class="heading my-4">${component.title}</h2>
        <h2 class="heading my-4">${component.description}</h2>
    </div>
</c:if >
