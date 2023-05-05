<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
      <!--    <section class="aboutSection p100" style="padding-top:50px">
        <div class="container">
           <div class="aboutContentWrap">
              <img class="aboutLogo"src="${component.strategicmainparalogo.url}">
              <h4 class="wow fadeIn  animated" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeIn;">
                ${component.strategicmainparatitle}
              </h4>
              <p class="wow fadeInUp  animated" data-wow-delay="400ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 400ms; animation-name: fadeInUp;">${component.strategicmainparadescription}</p>
           </div>
        </div>
     </section>-->

     <section id="aboutSection" class="pt-100 aboutSection mainStrategicFlex">
      <div class="container-fluid">
      <div class="strategicContent objectiveContent wow fadeInUp animated" >
            <div class="strategicTitle inlineTitile d-block d-lg-none"  >
            <h5 class="strategicSubTitle">  ${component.strategicmainparatitle}
            </h5>
      </div>
         <div class="row align-items-center">
            <div class="col-md-6 abt-odr-1">
                  <div class="strategicTitle inlineTitile d-none d-lg-block"  >
            <h5 class="strategicSubTitle">  ${component.strategicmainparatitle}	</h5>
      </div>
               <p >${component.strategicmainparadescription}</p>
            </div>
            <div class="col-md-6  abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.strategicmainparalogo.url}">
                </div>
            </div>
         </div>

      </div>
      </div>
   </section>
</c:if >
