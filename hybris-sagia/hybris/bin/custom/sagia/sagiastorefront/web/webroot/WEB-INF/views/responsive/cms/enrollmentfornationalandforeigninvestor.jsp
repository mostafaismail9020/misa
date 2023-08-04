<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
     <section class="enroleRequirement pt-100">
      <div class="enroleRequirementBgImg">
         <img src="${component.enrollmentparaimage.url}">
      </div>
      <div class="container-fluid">
               <div class="enroleRequirementWrap">
                     <div class="strategicTitle wow zoomIn animated" data-wow-duration="1s" >
         <h1>  ${component.enrollmentparatitle}</h1>
      </div>
                  <ul class="wow fadeInUp   animated">
                     <p>${component.enrollmentparadescription}</p>

                  </ul>
               </div>
      </div>
   </section>
</c:if >
