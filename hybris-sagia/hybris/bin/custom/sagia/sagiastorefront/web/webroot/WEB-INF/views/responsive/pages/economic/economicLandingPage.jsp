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

<!-- Tableau Report Start -->
<c:if test="${language eq 'en'}">
	<div class='tableauPlaceholder' id='viz1663575604392' style='position: relative'><noscript><a href='#'><img alt='Key indicators EN ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;1-&#47;1-Dashboard-EN&#47;KeyindicatorsEN&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='1-Dashboard-EN&#47;KeyindicatorsEN' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;1-&#47;1-Dashboard-EN&#47;KeyindicatorsEN&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1663575604392');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='1366px';vizElement.style.height='1095px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='1366px';vizElement.style.height='1095px';} else { vizElement.style.width='100%';vizElement.style.height='4227px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
</c:if>
<c:if test="${language eq 'ar'}">
	<div class='tableauPlaceholder' id='viz1663575522592' style='position: relative'><noscript><a href='#'><img alt='Key indicators AR ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;1-&#47;1-Dashboard-AR&#47;KeyindicatorsAR&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='1-Dashboard-AR&#47;KeyindicatorsAR' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;1-&#47;1-Dashboard-AR&#47;KeyindicatorsAR&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /><param name='filter' value='publish=yes' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1663575522592');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='1366px';vizElement.style.height='1095px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='1366px';vizElement.style.height='1095px';} else { vizElement.style.width='100%';vizElement.style.height='4227px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
</c:if>
<!-- Tableau Report End -->

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
