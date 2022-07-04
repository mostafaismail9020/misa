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

                <c:if test="${realGDP.name == 'Real GDP' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${realGDP.value}</center></h2>
                        <p class="desc">${realGDP.valueLabel}</p>

                        <h2 class="count"><center>${realGDP.percentage}</center></h2>
                        <p class="desc">${realGDP.percentageLabel}</p>

                        <h2 class="name"><center>${realGDP.displayName}</center></h2>
                        <p class="calendar">${realGDP.calenderValue}&nbsp;${realGDP.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${moneySupply.name == 'Money Supply M3' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${moneySupply.value}</center></h2>
                        <p class="desc">${moneySupply.valueLabel}</p>

                        <h2 class="count"><center>${moneySupply.percentage}</center></h2>
                        <p class="desc">${moneySupply.percentageLabel}</p>

                        <h2 class="name"><center>${moneySupply.displayName}</center></h2>
                        <p class="calendar">${moneySupply.calenderValue}&nbsp;${moneySupply.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${tradeBalance.name == 'Trade Balance' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${tradeBalance.value}</center></h2>
                        <p class="desc">${tradeBalance.valueLabel}</p>

                        <h2 class="count"><center>${tradeBalance.percentage}</center></h2>
                        <p class="desc">${tradeBalance.percentageLabel}</p>

                        <h2 class="name"><center>${tradeBalance.displayName}</center></h2>
                        <p class="calendar">${tradeBalance.calenderValue}&nbsp;${tradeBalance.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${budgetDeficit.name == 'Budget Deficit' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${budgetDeficit.value}</center></h2>
                        <p class="desc" data-toggle="tooltip" data-placement="top" title="Tooltip on top">${budgetDeficit.valueLabel}</p>

                        <h2 class="count"><center>${budgetDeficit.percentage}</center></h2>
                        <p class="desc">${budgetDeficit.percentageLabel}</p>

                        <h2 class="name"><center>${budgetDeficit.displayName}</center></h2>
                        <p class="calendar">${budgetDeficit.calenderValue}&nbsp;${budgetDeficit.currenyValue}</p>
                        <!-- <p class="info-text">* Expectation Based on The Mid-Year Economic and Fiscal Performance FY 2021</p> -->
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${gDPPerCapita.name == 'GDP Per Capita' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${gDPPerCapita.value}</center></h2>
                        <p class="desc">${gDPPerCapita.valueLabel}</p>

                        <h2 class="count"><center>${gDPPerCapita.percentage}</center></h2>
                        <p class="desc">${gDPPerCapita.percentageLabel}</p>

                        <h2 class="name"><center>${gDPPerCapita.displayName}</center></h2>
                        <p class="calendar">${gDPPerCapita.calenderValue}&nbsp;${gDPPerCapita.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${interestRate.name == 'Interest Rate' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${interestRate.value}</center></h2>
                        <p class="desc">${interestRate.valueLabel}</p>

                        <h2 class="count"><center>${interestRate.percentage}</center></h2>
                        <p class="desc">${interestRate.percentageLabel}</p>

                        <h2 class="name"><center>${interestRate.displayName}</center></h2>
                        <p class="calendar">${interestRate.calenderValue}&nbsp;${interestRate.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${fDIinflow.name == 'FDI Inflow' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${fDIinflow.value}</center></h2>
                        <p class="desc">${fDIinflow.valueLabel}</p>

                        <h2 class="count"><center>${fDIinflow.percentage}</center></h2>
                        <p class="desc">${fDIinflow.percentageLabel}</p>

                        <h2 class="name"><center>${fDIinflow.displayName}</center></h2>
                        <p class="calendar">${fDIinflow.calenderValue}&nbsp;${fDIinflow.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${governmentDebt.name == 'Government Debt' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${governmentDebt.value}</center></h2>
                        <p class="desc">${governmentDebt.valueLabel}</p>

                        <h2 class="count"><center>${governmentDebt.percentage}</center></h2>
                        <p class="desc">${governmentDebt.percentageLabel}</p>

                        <h2 class="name"><center>${governmentDebt.displayName}</center></h2>
                        <p class="calendar">${governmentDebt.calenderValue}&nbsp;${governmentDebt.currenyValue}</p>                        
                      </div>
                    </div>
                  </div>
                </c:if>
                
                 <c:if test="${unemployment.name == 'Unemployment' }">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${unemployment.value}</center></h2>
                        <p class="desc">${unemployment.valueLabel}</p>

                        <h2 class="count"><center>${unemployment.percentage}</center></h2>
                        <p class="desc">${unemployment.percentageLabel}</p>

                        <h2 class="name"><center>${unemployment.displayName}</center></h2>
                        <p class="calendar">${unemployment.calenderValue}&nbsp;${Unemployment.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${inflation.name == 'Inflation'}">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${inflation.value}</center></h2>
                        <p class="desc">${inflation.valueLabel}</p>

                        <h2 class="count"><center>${inflation.percentage}</center></h2>
                        <p class="desc">${inflation.percentageLabel}</p>

                        <h2 class="name"><center>${inflation.displayName}</center></h2>
                        <p class="calendar">${inflation.calenderValue}&nbsp;${inflation.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${saudiStockMarket.name == 'Saudi Stock Market'}">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${saudiStockMarket.value}</center></h2>
                        <p class="desc">${saudiStockMarket.valueLabel}</p>

                        <h2 class="count"><center>${saudiStockMarket.percentage}</center></h2>
                        <p class="desc">${saudiStockMarket.percentageLabel}</p>

                        <h2 class="name"><center>${saudiStockMarket.displayName}</center></h2>
                        <p class="calendar">${saudiStockMarket.calenderValue}&nbsp;${saudiStockMarket.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

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

                <c:if test="${population.name == 'Population'}">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${population.value}</center></h2>
                        <p class="desc">${population.valueLabel}</p>

                        <h2 class="count"><center>${population.percentage}</center></h2>
                        <p class="desc">${population.percentageLabel}</p>

                        <h2 class="name"><center>${population.displayName}</center></h2>
                        <p class="calendar">${population.calenderValue}&nbsp;${population.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${exchangeRate.name == 'Exchange Rate'}">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${exchangeRate.value}</center></h2>
                        <p class="desc">${exchangeRate.valueLabel}</p>

                        <h2 class="count"><center>${exchangeRate.percentage}</center></h2>
                        <p class="desc">${exchangeRate.percentageLabel}</p>

                        <h2 class="name"><center>${exchangeRate.displayName}</center></h2>
                        <p class="calendar">${exchangeRate.calenderValue}&nbsp;${exchangeRate.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${totalReserveAssets.name == 'Total Reserve Assets'}">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${totalReserveAssets.value}</center></h2>
                        <p class="desc">${totalReserveAssets.valueLabel}</p>

                        <h2 class="count"><center>${totalReserveAssets.percentage}</center></h2>
                        <p class="desc">${totalReserveAssets.percentageLabel}</p>

                        <h2 class="name"><center>${totalReserveAssets.displayName}</center></h2>
                        <p class="calendar">${totalReserveAssets.calenderValue}&nbsp;${totalReserveAssets.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

                <c:if test="${governmentReserve.name == 'Government Reserve'}">
                  <div class="eh-card">
                    <div class="macro_economic_component">
                      <div class="eh-panel-box aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                        <h2 class="count"><center>${governmentReserve.value}</center></h2>
                        <p class="desc">${governmentReserve.valueLabel}</p>

                        <h2 class="count"><center>${governmentReserve.percentage}</center></h2>
                        <p class="desc">${governmentReserve.percentageLabel}</p>

                        <h2 class="name"><center>${governmentReserve.displayName}</center></h2>
                        <p class="calendar">${governmentReserve.calenderValue}&nbsp;${governmentReserve.currenyValue}</p>
                      </div>
                    </div>
                  </div>
                </c:if>

              </div>
          </div>
      </div>
  </div>


</div>



</jsp:body>
</template:portalpage>
