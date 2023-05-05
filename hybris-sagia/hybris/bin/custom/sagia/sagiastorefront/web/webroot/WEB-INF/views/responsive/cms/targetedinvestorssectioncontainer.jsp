<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">


     <section id="targerSectionV3" class="targerSectionV3 pt-100">

        <div class="container-fluid">
            <div class="strategicTitle wow zoomIn animated" data-wow-duration="1s" >
                    <!-- <h5 class="strategicSubTitle">Target segments</h5> -->
            <h1><spring:theme code="portal.sector.taget.investors.label"/></h1>
        </div>
        </div>


        <div class="container">
            <div class="targerSectionV3Wrapper">
      <link rel="stylesheet" href="/_ui/responsive/theme-alpha/css/swiper-bundle.min.css" />
      <link rel="stylesheet" href="/_ui/responsive/theme-alpha/css/animate.min.css" />



      <!-- Swiper -->
      <div class="swiper targetedMySwiper">
        <div class="swiper-wrapper">

            <c:forEach var="currentComponent" items="${targetedinvestorlist}" varStatus="status">
                <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/>
            </c:forEach>

        </div>
        <div class="swiperArrow">
        <div class="swiper-pagination"></div>
      </div>
      </div>


            </div>
        </div>
    </section>
</c:if >


<script  src="/_ui/responsive/theme-alpha/js/swiper-bundle.min.js"></script>
