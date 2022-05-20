<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
    	<header:portalPageTitle />

<!-- Banner  -->
  <cms:pageSlot position="PortalPageMain" var="feature">
  <cms:component component="${feature}" element="div" class=""/>
  </cms:pageSlot>
<!-- Banner -->

<div class="economic-dashboard-container economic-overview-container position-relative">
  
  <!-- <section class="eh-page-breadcrums">
    <div class="container">
      <div class="eh-page-grid">
        <div class="thumbnail">
          <a href="${encodedContextPath}/economicHighlights/saInternationalIndices">
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/indices.png" alt=""/>
            <div class="eh-page-grid-link">
              <spring:theme code="economic.highlights.page.heading.dashboard.link.tail.name1"/>
              <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
            </div>
          </a>
        </div>
        <div class="thumbnail">
          <a href="${encodedContextPath}/economicHighlights/infraLogistics">
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/logistics.png" alt=""/>
            <div class="eh-page-grid-link">
              <spring:theme code="economic.highlights.page.heading.dashboard.link.tail.name2"/>
              <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
            </div>
          </a>
        </div>
        <div class="thumbnail">
          <a href="${encodedContextPath}/economicHighlights/saudiEconomicSectors">
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/economic_sectors.png" alt=""/>
            <div class="eh-page-grid-link">
              <spring:theme code="economic.highlights.page.heading.dashboard.link.tail.name3"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
            </div>
          </a>
        </div>
        <div class="thumbnail">
          <a href="${encodedContextPath}/economicHighlights/investmentData">
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/investment_data.png" alt=""/>
            <div class="eh-page-grid-link">
              <spring:theme code="economic.highlights.page.heading.dashboard.link.tail.name4"/>
              <br>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
            </div>
          </a>
        </div>
      </div>
    </div>
  </section> -->
  <div class="container economic-highlights-overview">
      <div class="row">
          <div class="col-md-12 title-area">
              <div class="o-stories__list">
                <c:forEach items="${dashboardCards}" var="card">
                    <c:choose>
                        <c:when test="${card.uid == 'Credit Rating'}">
                            <c:if test="${creditRating.name == 'Credit Rating'}">
                              <div class="eh-card">
                                <div class="macro_economic_component">
                                  <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                                    <div class="credit-rating-stats">
                                      <div>
                                        <h2 class="count"><center>${creditRating.spValue}</center></h2>
                                        <p class="desc">${creditRating.spLabel}</p>
                                      </div>
                                      <div>
                                        <h2 class="count"><center>${creditRating.moodysValue}</center></h2>
                                        <p class="desc">${creditRating.moodysLabel}</p>
                                      </div>
                                      <div>
                                        <h2 class="count"><center>${creditRating.fitchValue}</center></h2>
                                        <p class="desc">${creditRating.fitchLabel}</p>
                                      </div>
                                    </div>

                                    <div>
                                      <h2 class="name"><center>${creditRating.displayName}</center></h2>
                                      <p class="calendar">${creditRating.calenderValue}&nbsp;${creditRating.currenyValue}</p>
                                    </div>

                                  </div>
                                </div>
                              </div>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <div class="eh-card">
                                <div class="macro_economic_component">
                                  <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                                    <h2 class="count"><center>${card.value}</center></h2>
                                    <p class="desc">${card.valueLabel}</p>

                                    <h2 class="count"><center>${card.percentage}</center></h2>
                                    <p class="desc">${card.percentageLabel}</p>

                                    <h2 class="name"><center>${card.displayName}</center></h2>
                                    <p class="calendar">${card.calenderValue}</p>
                                    <p class="calendar">${card.notes}</p>
                                  </div>
                                </div>
                              </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
              </div>
          </div>
      </div>
  </div>


</div>



</jsp:body>
</template:portalpage>
