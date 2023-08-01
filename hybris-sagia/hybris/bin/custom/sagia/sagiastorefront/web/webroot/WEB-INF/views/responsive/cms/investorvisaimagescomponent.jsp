<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
     <!-- <section id="aboutSection" class="pt-100 aboutSection mainStrategicFlex">
            <div class="col-md-6 abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.firstImage.url}">
                </div>
            </div>
            <div class="col-md-6 abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.secondImage.url}">
                </div>
            </div>
            <div class="col-md-6 abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.thirdImage.url}">
                </div>
            </div>
   </section> -->


   <div class="invVisaAboutBtmSection">
      <ul>
        <li class="animated wow zoomIn"  data-wow-duration="0.5s"><img src="${component.firstImage.url}" /></li>
        <li class="animated wow zoomIn"  data-wow-duration="1s"><img src="${component.secondImage.url}" /></li>
        <li class="animated wow zoomIn"  data-wow-duration="1.5s"><img src="${component.thirdImage.url}" /></li>
      </ul>
    </div>




</c:if >
