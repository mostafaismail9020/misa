<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<c:if test="${component.visible}">
    <div class="container">
        <c:forEach var="currentComponent" items="${ourserviceslist}" varStatus="status">
            <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/>
        </c:forEach>
    </div>
</c:if >

	

