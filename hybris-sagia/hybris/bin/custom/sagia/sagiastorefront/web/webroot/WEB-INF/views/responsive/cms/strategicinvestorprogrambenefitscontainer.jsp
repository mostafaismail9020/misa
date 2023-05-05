<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<c:if test="${component.visible}">
     <section id="programBenefit" class="programPenefit pt-100">
        <div class="benefitBgOverlay"></div>
        <div class="container-fluid">
            <div class="strategicTitle wow zoomIn animated" data-wow-duration="1s" >
            <h1 style="position: relative;"><spring:theme code="portal.sector.program.benefits.label"/></h1>
        </div>
        </div>
        <div class="container-fluid">
            <div class="programBenefitItemWrap">
                <div class="row">
                    <c:forEach var="currentComponent" items="${strategicprogrambenefitslist}" varStatus="status">
                        <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
</c:if >
