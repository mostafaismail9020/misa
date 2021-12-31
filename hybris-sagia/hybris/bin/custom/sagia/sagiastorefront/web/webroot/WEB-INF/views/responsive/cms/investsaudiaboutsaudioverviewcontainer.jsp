<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="aboutusContainer aboutInvestinSaudi-toptext" id="aboutusContainer" >    
    <div class="container-fluid aboutus p-0">
        <h2><spring:theme code="portal.about.invest.saudi.section.heading" text=" Saudi Arabia's national investment promotion platform supporting global business expansion into the Kingdom"/></h2>
        <div class="container-fluid">
            <div class="row section-item">
                <div class="col-lg-5 order-1 order-lg-1 section-img" data-aos="fade-right" style="background-image: url(${backgroundImage.url});">
                    <!-- <img src="${commonResourcePath}/images/About-us/section-img.png" alt="" class="img-fluid"> -->
                  </div>
                  <div class="col-lg-7 pt-4 pt-lg-0 order-2 order-lg-2 section-desc" data-aos="fade-left">
                    <c:forEach var="currentComponent" items="${components}" varStatus="status">
                        <cms:component component="${currentComponent}" element="div"/>
                    </c:forEach>
                  </div>
            </div>
        </div>
    </div>
</section>
	