<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
    <div class="col-md-6 col-lg-3">
        <div class="oneCoreEngineItem  wow fadeIn" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeIn;">
           <div class="oneCoreEngineItemImage">
              <img src="${commonResourcePath}/images/service1.jpg" >
           </div>
           <span class="oceNumber">
           <img class="oceImageDark" src="${component.mizaonecorechildimage.url}">
           <img class="oceImagelight" src="${component.mizaonecorechildimage.url}">
           </span>
           <h4>${component.title}</h4>
           <p>${component.description}</p>
        </div>
     </div>
</c:if >
    