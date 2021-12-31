<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="investor_journey_component d-flex document_lic_inner">
    <div class="col  investor_journey_inner" data-aos="fade-up">
        <c:if test="${not empty localizedImage}">
            <img class="img-fluid w-40" src="${localizedImage.url}" alt='${localizedImage.altText}' title='${localizedImage.altText}' style="">
        </c:if>
        
        <c:if test="${not empty mainHeader}">
            <p>
                <strong>${mainHeader}</strong><br>
        </c:if> 
        <c:if test="${not empty mainDescription}">
            <span>${mainDescription}</span>
        </c:if>
        </p>
    </div>
    
    <div class="col document_lic_inner_right investor_journey_inner_right" data-aos="fade-up">
        <div class="document_lic_box">
            <c:if test="${not empty requirementHeader}">
                <strong>${requirementHeader}</strong>
            </c:if>
            <c:if test="${not empty requirementDescription}">
                <p>${requirementDescription}</p>
            </c:if> 
		</div>

        <div class="document_lic_box"> 
            <c:if test="${not empty responsibleHeader}">
                <strong>${responsibleHeader}</strong>
            </c:if> 
            <c:if test="${not empty responsibleIcon}">
                <c:forEach var="icon" items="${responsibleIcon}" >
                    <img src="${icon.url}" alt='${icon.altText}' title='${icon.altText}' style="">
                </c:forEach> 
            </c:if>
        </div>

    </div>
</div>  

