<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
     <section class="mizaSection mizacoreEngine p100" style="padding-bottom: 0;">
        <div class="container">
           <div class="oneCoreHeader">
              <h2 class="mizaTitle  wow fadeInUp" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: fadeInUp;">${component.title} <span class="clr_gld"> MIZA</span></h2>
              <p>${component.description}</p>
           </div>
        </div>
        <div class="container-fluid">
           <div class="row " style="justify-content: center;">
            <c:forEach var="currentComponent" items="${childCards}" varStatus="status">
                <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/>
            </c:forEach>
           </div>
        </div>
     </section>
</c:if >
