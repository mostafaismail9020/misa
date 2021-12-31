<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="container sectors-content">
    <div class="row">
        <h1 class="heading text-left my-4 col-md-12">${component.title}</h1>
        <div class="left col-md-12 text-static font-bold diff-color mt-4 mb-4">${component.subtitle}</div>
        <c:forEach var="currentComponent" items="${components}" varStatus="status">
            <cms:component component="${currentComponent}" element="div" class="col-lg-3 col-md-3 col-6 statistic-section text-left mb-4"/>
        </c:forEach>
    </div>
</section>