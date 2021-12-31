<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>



<c:forEach var="currentComponent" items="${components}">
            <cms:component component="${currentComponent}" element="div" class="banner-section rhq-banner-content"  style="background-image: url('${fn:escapeXml(currentComponent.image.url)}')"/>
</c:forEach>



