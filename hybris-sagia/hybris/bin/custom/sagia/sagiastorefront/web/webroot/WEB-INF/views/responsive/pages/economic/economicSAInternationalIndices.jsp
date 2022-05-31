<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<spring:htmlEscape defaultHtmlEscape="true" />

<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
    	<header:portalPageTitle />
<!-- Banner  -->
<cms:pageSlot position="PortalPageMain" var="feature">
  <cms:component component="${feature}" element="div" class=""/>
</cms:pageSlot>
<!-- <section class="eco-banner eco-banner-Inc position-relative">
  <div class="eco-banner-container" data-aos="fade-up">
    <h1><spring:theme code="economic.sainternational.page.heading.name"/></h1>
  </div>
  <section class="eh-page-breadcrum-link">
    <div class="">
      <div class="eh-page-links">
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/dashboard">
            <spring:theme code="economic.sainternational.dashboard.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link active">
          <a href="${encodedContextPath}/economicHighlights/saInternationalIndices">
            <spring:theme code="economic.sainternational.internationalindices.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/infraLogistics">
            <spring:theme code="economic.sainternational.infralogistics.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/saudiEconomicSectors">
            <spring:theme code="economic.sainternational.economicsector.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/investmentData">
            <spring:theme code="economic.sainternational.investmentdata.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
      </div>
    </div>
  </section>
</section> -->
<div id="Json-data" style="display: none;">
  ${gsonJson}
</div>
<!-- Banner -->
<!-- <c:forEach items="${saInternationalIndices}" var="saInternationalIndices">
        <tr>
            <td>Frequency  <c:out value="${saInternationalIndices.frequency} "/></td>
            <td>Year <c:out value="${saInternationalIndices.year} "/></td>
             <td>Rank <c:out value="${saInternationalIndices.rankValue} "/></td>
            <td>Score <c:out value="${saInternationalIndices.scoreValue} "/></td>
        </tr>
    </c:forEach> -->
    <json:array items="${saInternationalIndices}" var="oneRow">
        <div class="economic-dashboard-container economic-overview-container ">
            <div class="economic-overview indices-container">
              <form:form id="rankChart" method="post" action="/saInternationalIndices">
                <div class="filter-header"><spring:theme code="economic.sainternational.filterby.section.heading.name"/></div>
                <div class="row">
                  <div class="col-md-6">
                    <div class="section-header">
                      <spring:theme code="economic.sainternational.selectchart.heading.name"/>
                    </div>
                    <div class="row select-chart select-indicator">
                      <div class="col-md-6">
                        <label class="container"><spring:theme code="economic.sainternational.ranking.radiobutton.text"/>
                          <input type="radio" name="chartType" value="rank" id="rank" checked>
                          <span class="check"></span>
                        </label>
                        <!-- <label class="checkbox-container">Saudi Arabia Ranking in the Global Indicators
                          <input type="checkbox" checked="checked">
                          <span class="checkmark"></span>
                        </label> -->
                      </div>
                      <div class="col-md-6">
                        <label class="container"><spring:theme code="economic.sainternational.scores.radiobutton.text"/>
                          <input type="radio" name="chartType" value="score" id="score">
                          <span class="check"></span>
                        </label>
                        <!-- <label class="checkbox-container">Scores of the Global Indicators
                          <input type="checkbox" checked="checked">
                          <span class="checkmark"></span>
                        </label> -->
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="section-header">
                      <spring:theme code="economic.sainternational.selectperiod.heading.name"/>
                    </div>
                    <div class="row select-date">
                      <div class="col-md-6 mb-2 mb-md-0">
                        
                        <label for="from"><spring:theme code="economic.sainternational.from.text"/> </label>
                        <input type="text" class="startYear form-control" value=""/>
                        <!-- <input id="startYear" type="date" value="2010-10-08"/> -->
                      </div>
                      <div class="col-md-6 mb-2 mb-md-0">
                        <label for="to"><spring:theme code="economic.sainternational.to.text"/> </label>
                        <input type="text" class="endYear form-control" value=""/>
                        <!-- <input id="endYear" type="date" value="2022-10-08"/> -->
                      </div>
                    </div>
                  </div>
                </div>
            
                <div class="row">
                  <div class="col-md-12">
                    <div class="section-header">
                      <spring:theme code="economic.sainternational.selectindicators.heading.name"/>
                    </div>
                    <div class="row select-indicator">
                      <div class="col-md-9">
                        <div class="row">
                          <div class="col-md-4">
                            <label class="container"><spring:theme code="economic.sainternational.protectingminority.radiobutton.text"/>
                              <input type="radio" name="indicator" value="EaseOfProtectingMinorityInvestors" id="EaseOfProtectingMinorityInvestors" checked>
                              <span class="check"></span>
                            </label>
                          </div>
                          <div class="col-md-4">
                            <label class="container"><spring:theme code="economic.sainternational.globalinnovation.radiobutton.text"/>
                              <input type="radio" name="indicator" value="GlobalInnovationIndex" id="GlobalInnovationIndex">
                              <span class="check"></span>
                            </label>
                          </div>
                          <div class="col-md-4">
                            <label class="container"><spring:theme code="economic.sainternational.logisticsperformance.radiobutton.text"/>
                              <input type="radio" name="indicator" value="LogisticsPerformanceIndex" id="LogisticsPerformanceIndex">
                              <span class="check"></span>
                            </label>
                          </div>
                          <div class="col-md-4">
                            <label class="container"><spring:theme code="economic.sainternational.governmentdevelopment.radiobutton.text"/>
                              <input type="radio" name="indicator" value="EGovernmentDevelopmentIndex" id="EGovernmentDevelopmentIndex">
                              <span class="check"></span>
                            </label>
                          </div>
                          <div class="col-md-4">
                            <label class="container"><spring:theme code="economic.sainternational.startbusiness.radiobutton.text"/>
                              <input type="radio" name="indicator" value="EaseOfStartingBusiness" id="EaseOfStartingBusiness">
                              <span class="check"></span>
                            </label>
                          </div>
                          <div class="col-md-4">
                            <label class="container"><spring:theme code="economic.sainternational.economicfreedom.radiobutton.text"/>
                              <input type="radio" name="indicator" value="EconomicFreedomIndex" id="EconomicFreedomIndex">
                              <span class="check"></span>
                            </label>
                          </div>
                          <div class="col-md-4">
                            <label class="container"><spring:theme code="economic.sainternational.competitiveness.radiobutton.text"/>
                              <input type="radio" name="indicator" value="GlobalCompetitivenessReport" id="GlobalCompetitivenessReport">
                              <span class="check"></span>
                            </label>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-3 apply-filter-buttons">
                        <div class="row">
                          <div class="col-md-6 col-sm-12">
                            <button class="secondary" type="button" onClick="refreshPage()">
                              <spring:theme code="economic.sainternational.reset.button.text"/>
                            </button>
                          </div>
                          <div class="col-md-6 col-sm-12">
                            <button type="button" class="primary" id="apply">
                              <spring:theme code="economic.sainternational.apply.button.text"/>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                </div>
              </form:form>
              
            </div>
          </div>
    <!-- </body> 
</html> -->

<div class="chart-container mx-5">
  <div class="chart-header">
    <spring:theme code="economic.sainternational.chart.header.name"/>
  </div>
  <div id="chartdiv" style="height: 500px" dir="ltr"></div>
</div>
</jsp:body>
</template:portalpage>