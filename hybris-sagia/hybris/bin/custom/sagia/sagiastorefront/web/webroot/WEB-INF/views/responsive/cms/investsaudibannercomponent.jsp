<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="banner-container" data-aos="fade-up">
	<h1>${descriptionText}</h1>
    <c:if test="${not empty descriptionText2}">
        <h2>${descriptionText2}</h2>
        <br/>
    </c:if>
    <c:if test="${not empty buttonURL}">
        <a href="${fn:escapeXml(buttonURL.url)}" class="enquiry-btn">
        <c:if test="${language eq 'en'}">
        	<span class="general-breadcrumb-left-icon"></span> ${buttonURL.linkName}
        </c:if>
           <c:if test="${language eq 'ar'}">
               ${buttonURL.linkName} <span class="general-breadcrumb-left-icon"></span>
           </c:if>
		</a>
    </c:if>
    <c:if test="${not empty buttonText}">
        <a class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" href="${fn:escapeXml(buttonURL.url)}" target="${fn:escapeXml(link)}">${buttonText}</a>
    </c:if>
</div>
