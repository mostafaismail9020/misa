<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="d-flex document_lic_inner">
    <div class="col invest_lic_inner text-center">
        <c:if test="${not empty localizedImage}">
            <img  src="${localizedImage.url}" alt='${localizedImage.altText}' title='${localizedImage.altText}' style="">
        </c:if>
        <p><span><c:if test="${not empty description}">${description}</c:if></span></p>
        
    </div>

    <div class="col document_lic_inner_right">
        <c:if test="${not empty subText}"><span class="document_lic_inner_right-Span">${subText}</span></c:if>
        <c:if test="${not empty documentsRequired}">
		<c:forEach var="point" items="${documentsRequired}" >
        <div class="document_lic_box d-flex">
        <c:if test="${not empty point.icon}">
                <span><img src="${point.icon.url}" alt='${point.icon.altText}' title='${point.icon.altText}' style=""></span>
        </c:if>
        <c:if test="${not empty point.longDescription}">
                <p>${point.longDescription}</p>
            </c:if>
        </div>
		
    </c:forEach>  
    </c:if>
    <c:if test="${not empty text}"><p class="INC_Box_set">${text}</p></c:if>   
    </div>
</div>
<div class="text-center download_btn"><a>
</a><button class=""><a></a><a href="https://investsaudi.sa/en/login#register-apply" target="_blank"><spring:theme code="portal.investment.guide.investor.licence.visit.the.licence.portal.url" text= "Visit the licensing portal to register as an investor today"/></a></button>
</div>
</div>

               

	

