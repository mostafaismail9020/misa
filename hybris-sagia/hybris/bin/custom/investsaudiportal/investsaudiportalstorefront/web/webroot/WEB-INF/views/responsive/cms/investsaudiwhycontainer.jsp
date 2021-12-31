<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="tabs-reason" id="mywhyKSAanimate" style="background-image: url(${component.backgroundImage.url});">
    <h2 class="heading text-center my-4 mb-5 flex-h-center">${component.title}</h2>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10 col-md-10 col-sm-12">
                <div class="container">
                    <div class="row tabs_container indexed-list whyksa-animate">
                        <c:forEach var="currentComponent" items="${components}" varStatus="status">
                            <cms:component component="${currentComponent}" element="div" class="col-lg-6 col-md-6 col-sm-12 indexed-list-item"/>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
