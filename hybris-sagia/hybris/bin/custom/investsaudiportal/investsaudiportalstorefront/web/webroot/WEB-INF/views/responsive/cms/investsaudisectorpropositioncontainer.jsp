<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="tabs-reason hajj-omarh mt-5"  style="background:url(${component.backgroundImage.url}) center no-repeat fixed #3D414B;background-size: cover;">
    <div class="container">
        <h1 class="heading text-left my-4">${component.title}</h1>
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-3 tabs_container">
                <c:forEach var="currentComponent" items="${components}" varStatus="status">
                    <cms:component component="${currentComponent}" />
                </c:forEach>
            </div>
        </div>
    </div>
</section>

