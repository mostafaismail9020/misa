<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="upcoming-special-zone-container">
    <div class="row m-0">
        <div class="col-md-12 title-area">
            <c:if test="${not empty title}">
                <h1 class="title-heading">${title}</h1>
            </c:if>
            
        </div>
        <div class="container">
            
            <c:forEach var="currentComponent" items="${components}" >
    <cms:component component="${currentComponent}" element="div"/>
</c:forEach>
        </div>

