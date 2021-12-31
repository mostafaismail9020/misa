<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<section class="regional-hqs-container mt-5 pt-5">
    <div class="macro_economic_container successstory-container">
        <div class="row m-0 ">
            <div class="col-md-12 ">
                <div class="section-title text-center d-block w-50 mr-auto ml-auto">
                    <c:if test="${not empty title}">
                        <h2>${title}</h2>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="slider-carousel success-logo-container ">
            <div id="carouselExampleControls" class="carousel slide " data-ride="carousel ">
                <div class="carousel-inner ">
                    <c:forEach var="currentComponent" items="${components}" >
                        <div class="carousel-item">
                            <cms:component component="${currentComponent}" element="div"/>
                        </div>
                    </c:forEach>
                </div>
                <div class="sucess-slider-contols meet-kingdom-control rhq_arrow_control">
                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                        <img src="/_ui/responsive/common/images/rhq/previous.png" alt="">
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                        <img src="/_ui/responsive/common/images/rhq/next.png" alt="">
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>

    </div>
</section>               


