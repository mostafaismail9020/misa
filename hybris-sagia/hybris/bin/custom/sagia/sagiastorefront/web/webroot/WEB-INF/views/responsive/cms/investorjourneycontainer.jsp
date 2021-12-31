<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
               
<div class="container-fluid invest_lic_outer investor_journey_outer text-center py-4">
    <div class="investorguide-journey-container">
        <div class="row m-0">
            <div class="col-md-12 title-area">
                <c:if test="${not empty title}">
                    <h1 class="title-heading" data-aos="fade-up">${title}</h1>
                </c:if> 
            </div>
            <div class=""> 
                <c:forEach var="currentComponent" items="${components}" >
                    <cms:component component="${currentComponent}" element="div"/>
                </c:forEach>
            </div> 
         </div>
        <div class="text-center download_btn">
            <button class=""><a href="https://investsaudi.sa/en/login#register-apply" target="_blank"><spring:theme code="portal.invest.guide.start.journey" text="Journey Now"/></a></button>
        </div>
    </div>
</div>

