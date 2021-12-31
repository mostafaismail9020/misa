<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="swiper-container" id="HeaderSwiper">
    <div class="swiper-wrapper">
        <c:forEach var="currentComponent" items="${components}">
            <cms:component component="${currentComponent}" element="div" class="business-header swiper-slide text-center text-white d-flex" style="background-image: url('${fn:escapeXml(currentComponent.image.url)}')"/>
        </c:forEach>
    </div>
    <div class="swiper-pagination swiper-pagination-white"></div>
    <div class="swiper-button-next next-header swiper-button-white swiper-button-disabled"></div>
    <div class="swiper-button-prev prev-header swiper-button-white swiper-button-disabled" ></div>
</div>
