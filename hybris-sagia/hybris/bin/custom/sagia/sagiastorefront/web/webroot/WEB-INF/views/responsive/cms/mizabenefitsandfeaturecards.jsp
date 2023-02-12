<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
    <!-- <div class="container">
    <img class="img-fluid" src="${component.mizabenefitsandfeaturecardsimage.url}" loading="lazy"/>
        <h2 class="heading my-4">${component.mizabenefitscardtitle}</h2>
        <h2 class="heading my-4">${component.mizabenefitscarddescription}</h2>
        <a href="${component.knowMore.url}" class="alink" target="_blank">${component.knowMore.linkName}</a>
    </div> -->

    <div class="col-lg-6">
        <div class="benifitFeatureItem  wow fadeInUp  animated" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeInUp;">
           <div class="benifitFeatureText newDivDesign">
              <div class="benifiIcon">
                 <div class="benifiIconWrap">								
                    <img src="${component.mizabenefitsandfeaturecardsimage.url}" loading="lazy">
                 </div>
              </div>
              <h4>${component.mizabenefitscardtitle}</h4>
              <p>${component.mizabenefitscarddescription}</p>
              <div class="secDivid"></div>
              <a href="${component.knowMore.url}" class="alink" target="_blank">Know More</a>
           </div>
        </div>
     </div>
</c:if >
