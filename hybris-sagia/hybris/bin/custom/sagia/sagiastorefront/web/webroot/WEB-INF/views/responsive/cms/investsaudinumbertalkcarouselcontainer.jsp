<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="facts-figures">
    <div class="container pos-rel " id="swiper-container-div">
        <h1 class="heading my-4">${component.title}</h1>
        <div class="row">
            <div class="swiper-container">
                <div class="swiper-wrapper swiper-wrapper-why-page">
                    <c:forEach var="currentComponent" items="${components}" varStatus="status">
                        <div class="my-4 swiper-slide " id="swiper-slide-why-page">
                            <cms:component component="${currentComponent}" element="div" class="facts-corner img-swap text-center"/>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="swiper-button-prev swiper-button-black swiper-button-prev-why-page swiper-button-disabled" ></div>
        <div class="swiper-button-next swiper-button-black swiper-button-next-why-page swiper-button-disabled" ></div>
        
    </div>
</section>