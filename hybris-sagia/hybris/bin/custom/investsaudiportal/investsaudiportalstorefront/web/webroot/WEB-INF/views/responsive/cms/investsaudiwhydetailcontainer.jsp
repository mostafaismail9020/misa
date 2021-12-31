<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="tabs-reason" id="why-page" style="background-image: url(${component.backgroundImage.url});">
    <div class="container">
        <h2 class="heading text-left my-4">${component.title}</h2>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-3 tabs_container yksaac">
                <c:forEach var="currentComponent" items="${components}" varStatus="status">
                    <cms:component component="${currentComponent}"/>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

