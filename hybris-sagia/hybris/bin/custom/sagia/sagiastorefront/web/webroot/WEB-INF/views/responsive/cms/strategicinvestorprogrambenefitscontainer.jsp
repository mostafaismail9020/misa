<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<c:if test="${component.visible}">
    <section class="ourServices p100" style="padding-bottom:0">
        <div class="container">
            <h2 class="mizaTitle  wow fadeInUp   animated" data-wow-delay="300ms" data-wow-duration="1s" ><span class="clr_gld"><spring:theme code="portal.sector.program.benefits.label"/></span></h2>
         </div>
        <div class="d2OurSeriviceWrap">
        <c:forEach var="currentComponent" items="${strategicprogrambenefitslist}" varStatus="status">
            <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/>
        </c:forEach>
        </div>
     </section>
</c:if >

	

