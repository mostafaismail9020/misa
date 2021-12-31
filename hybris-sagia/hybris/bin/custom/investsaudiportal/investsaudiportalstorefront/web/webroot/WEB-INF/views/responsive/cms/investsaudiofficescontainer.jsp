<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="container">
    <div class="row">
        <h2 class="col-lg-12 col-md-12 col-sm-12 mb-4">Saudi Offices</h2>
        <c:forEach var="currentComponent" items="${components}">
            <cms:component component="${currentComponent}" element="div" class="col-lg-6 col-md-6 col-sm-12 d-flex alignment-stretch"/>
        </c:forEach>
    </div>
</div>
