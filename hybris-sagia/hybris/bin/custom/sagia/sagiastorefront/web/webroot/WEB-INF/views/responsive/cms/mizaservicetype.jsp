<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}" >
    <div class="servicePortfolioItemInner1 servicePortfolioItemInner">
        <img src="${component.servicetypeimage.url}" loading="lazy">
        <div class="servicePortfolioContent">
           <h4>${component.mizaservicetypetitle}</h4>
           <p>${component.mizaservicetypedescription}</p>
        </div>
     </div>
</c:if >
