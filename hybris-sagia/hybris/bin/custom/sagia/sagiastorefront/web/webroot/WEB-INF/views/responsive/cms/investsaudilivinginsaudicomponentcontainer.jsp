<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<!-- Breadcurms -->
<!--End of  Breadcurms -->
<div class="achievements-panel">
<c:forEach var="currentComponent" items="${components}" >
    <cms:component component="${currentComponent}" element="div"/>
</c:forEach>
</div>