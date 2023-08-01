<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
     <!-- <section id="aboutSection" class="pt-100 aboutSection mainStrategicFlex">
      <div class="container-fluid">
      <div class="strategicContent objectiveContent wow fadeInUp animated" >
            <div class="strategicTitle inlineTitile d-block d-lg-none"  >
            <h5 class="strategicSubTitle">  ${component.title}
            </h5>
      </div>
         <div class="row align-items-center">
            <div class="col-md-6 abt-odr-1">
                  <div class="strategicTitle inlineTitile d-none d-lg-block"  >
            <h5 class="strategicSubTitle">  ${component.title}	</h5>
      </div>
               <p >${component.description}</p>
            </div>
            <div class="col-md-6 abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.primaryImage.url}">
                </div>
            </div>
            <div class="col-md-6 abt-odr-2">
               <div  class=" aboutImageGrid">
                  <img class="aboutLogo"src="${component.innerImage.url}">
                </div>
            </div>
         </div>

      </div>
      </div>
   </section> -->





   <section class="investorVisaAbout  reveal">
  
      <div class="containerFluid">
      <div class="invVisaAboutWrap">
        <div class="invVisaAboutLeft">
            <div class="investorVisaTitle">
          <h2 id="sectionTitle1"  class=" sectionTitle-animation">  ${component.title}</h2>
        </div>
       <div class="investorVisaAbtContent">
        <p class="animated fadeInUp wow" data-wow-delay="0.2s" data-wow-duration="2s">${component.description} </p>
      </div>
        </div>
      <div class="invVisaAboutRight">
      <div class="invVisaAboutRightImg">
      <div class="invVisaAboutLgImg">
        <img class="" src="${component.primaryImage.url}"/>
      </div>
        <div class="imageInnerBox">
          <img src="${component.innerImage.url}">
        </div>
      </div>
      </div>
      </div>
    
      </div>
      
      </section>









</c:if >
