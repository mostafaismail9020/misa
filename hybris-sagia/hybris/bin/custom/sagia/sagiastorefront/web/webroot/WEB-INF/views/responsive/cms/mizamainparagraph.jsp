<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
          <section class="aboutSection p100" style="padding-top:50px">
        <div class="container">
           <div class="aboutContentWrap">
              <img class="aboutLogo"src="${component.mizamainparalogo.url}">
              <h4 class="wow fadeIn  animated" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeIn;">
                ${component.mizamainparatitle}
              </h4>
              <p class="wow fadeInUp  animated" data-wow-delay="400ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 400ms; animation-name: fadeInUp;">${component.mizamainparadescription}</p>
           </div>
        </div>
     </section>
</c:if >
