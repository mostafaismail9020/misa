<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="banner-section">
    <div class="container position-relative">
        <div class="">
            <h1>
            	<c:if test="${language eq 'en'}">
            		<span class="clr_gld">${category.name}</span><spring:theme code="portal.sector.opportunity.label"/>
            	</c:if>
            	<c:if test="${language eq 'ar'}">
            		<span class="clr_gld"><spring:theme code="portal.sector.featured.opportunity.label"/>{category.name}</span>
            	</c:if>
            </h1>
        </div>
    </div>
</section>
        
