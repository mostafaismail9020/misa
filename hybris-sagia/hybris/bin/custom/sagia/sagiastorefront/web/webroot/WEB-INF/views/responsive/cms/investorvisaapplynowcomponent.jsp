<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
     <section id="aboutSection" class="pt-100 aboutSection mainStrategicFlex">
            <div class="col-md-6 abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.borderImage.url}">
                </div>
            </div>
            <div class="col-md-6 abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.backgroundImage.url}">
                </div>
            </div>
             <div class="col-md-6 abt-odr-2">
                <div  class=" aboutImageGrid">
                   <img class="aboutLogo"src="${component.planeAnimation.url}">
                 </div>
             </div>
            <video class="int-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
                  <source src="${fn:escapeXml(component.insideVideo.url)}" type="${fn:escapeXml(component.insideVideo.mime)}">
                </video>
   </section>
</c:if >
