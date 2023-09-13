<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="page-link-and-title">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:if test="${not empty buttonURL}">
                    <a href="${fn:escapeXml(buttonURL.url)}" class="link">
                        <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M10.5625 11.1875C10.75 11.375 10.8438 11.6042 10.8438 11.875C10.8438 12.1042 10.75 12.3333 10.5625 12.5625C10.375 12.75 10.1458 12.8438 9.875 12.8438C9.64583 12.8438 9.41667 12.75 9.1875 12.5625L5.3125 8.6875C5.125 8.45833 5.03125 8.22917 5.03125 8C5.03125 7.72917 5.125 7.5 5.3125 7.3125L9.1875 3.4375C9.41667 3.25 9.64583 3.15625 9.875 3.15625C10.1458 3.15625 10.375 3.25 10.5625 3.4375C10.75 3.625 10.8438 3.85417 10.8438 4.125C10.8438 4.35417 10.75 4.58333 10.5625 4.8125L7.34375 8L10.5625 11.1875Z" fill="#00714D"/>
                        </svg>
                        ${buttonURL.linkName}                      
                    </a>
                </c:if>
                <h1 class="title">
                    <c:if test="${language eq 'en'}">
                        <span class="clr_gld">${category.name}</span><spring:theme code="portal.sector.list.label"/>
                    </c:if>
                    <c:if test="${language eq 'ar'}">
                        <span class="clr_gld"><spring:theme code="portal.sector.list.arabic.label"/>{category.name}</span>
                    </c:if>                    
                </h1>
            </div>
        </div>
    </div>
</section>
        
