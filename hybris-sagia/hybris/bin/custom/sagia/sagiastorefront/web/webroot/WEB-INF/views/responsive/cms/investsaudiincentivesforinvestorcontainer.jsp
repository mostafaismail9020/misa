<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- ======= Incentives for Investor Container Starts ======= -->
<section id="incentiveforinvestors" class="incentiveContainer">
    <!-- Wrap the rest of the page in another container to center all the content. -->
  <div class="container">
      <div class="row">
          <div class="col-md-12 aos-init" data-aos="fade-right" data-aos-delay="100">
              <h2 class="title-heading">${component.investorTitle}</h2>
              <a href="/en/investor/incentives" class="btn-primary explore-btn"><spring:theme code="portal.exploreall.button.text"/>&nbsp;
              		<img src="${fn:escapeXml(component.exploreAllImage.url)}" /></a>
          </div>
      </div>
      <!-- card content start -->
      <div class="row">
          <c:forEach var="currentComponent" items="${components}" varStatus="status">
              <c:set var="loopCount" value="${(status.index) * 150}" />
              <div class="col-12 col-sm-6 col-md-6 col-lg-4 p-3 pb-5 mb-5 features-1" data-aos="fade-up" data-aos-delay="${loopCount}">
                  <cms:component component="${currentComponent}" element="div" />
              </div>
          </c:forEach>
        </div>
    </div>
</section>

<!-- ======= Incentives for Investor Container ends ======= -->