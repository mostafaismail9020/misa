<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">

    <section class="benifitFeature bgOverlay posRel p100">
        <div class="container ">
           <div class="row">
              <div class="col-lg-6 colDived">
                 <div class="secTitle">
                    <span class="smallTag  wow fadeIn  animated" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeIn;">MIZA</span>
                    <h1><span class="wow fadeInLeft  animated" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeInLeft;">${component.mizabenefitsparenttitle}</span></h1>
                 </div>
              </div>
              <div class="col-lg-6">
                 <div class="secDesc  wow fadeInUp  animated" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeInUp;">
                    <p>${component.mizabenefitsparentdescription}</p>
                 </div>
              </div>
           </div>
           <div class="benifitFeatureItemWrap">
              <div class="row">
                <c:forEach var="currentComponent" items="${benefitsandfeatures}" varStatus="status">
                    <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/>
                </c:forEach>
              </div>
           </div>
        </div>
     </section>


</c:if >
