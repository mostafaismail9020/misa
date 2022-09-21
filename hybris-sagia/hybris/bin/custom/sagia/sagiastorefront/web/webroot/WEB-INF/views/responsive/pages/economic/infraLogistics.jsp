<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<!-- <section class="eco-banner eco-banner-Log position-relative">
  <div class="eco-banner-container" data-aos="fade-up">
    <h1><spring:theme code="economic.infralogistics.page.heading.name"/></h1>
  </div>
  <section class="eh-page-breadcrum-link">
    <div class="">
      <div class="eh-page-links">
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/dashboard">
            <spring:theme code="economic.highlights.dashboard.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/saInternationalIndices">
            <spring:theme code="economic.highlights.internationalindices.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link active">
          <a href="${encodedContextPath}/economicHighlights/infraLogistics">
            <spring:theme code="economic.highlights.infralogistics.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/saudiEconomicSectors">
            <spring:theme code="economic.highlights.economicsector.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/investmentData">
            <spring:theme code="economic.highlights.investmentdata.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
      </div>
    </div>
  </section>
</section> -->

<!-- Banner -->

<!-- Tableau Report Start -->
<c:if test="${language eq 'en'}">
	<div class='tableauPlaceholder' id='viz1661433152520' style='position: relative'><noscript><a href='#'><img alt='InfraLogistics Dashboard  ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-InfraLogisticsDashboard-EN&#47;InfraLogisticsDashboard&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='3-Infrastructureslogistics-InfraLogisticsDashboard-EN&#47;InfraLogisticsDashboard' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-InfraLogisticsDashboard-EN&#47;InfraLogisticsDashboard&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433152520');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*1.77)+'px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433176074' style='position: relative'><noscript><a href='#'><img alt='Industrial cities  ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-Industrialcities-EN&#47;Industrialcities&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='3-Infrastructureslogistics-Industrialcities-EN&#47;Industrialcities' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-Industrialcities-EN&#47;Industrialcities&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433176074');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1877px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433201870' style='position: relative'><noscript><a href='#'><img alt='Private Industrial Cities  ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-PrivateIndustrialCities-EN&#47;PrivateIndustrialCities&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='3-Infrastructureslogistics-PrivateIndustrialCities-EN&#47;PrivateIndustrialCities' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-PrivateIndustrialCities-EN&#47;PrivateIndustrialCities&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433201870');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='2777px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
</c:if>
<c:if test="${language eq 'ar'}">
	<div class='tableauPlaceholder' id='viz1661433026151' style='position: relative'><noscript><a href='#'><img alt='InfraLogistics Dashboard ar ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-INfraLogisticsDashboard-AR&#47;InfraLogisticsDashboardar&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='3-Infrastructureslogistics-INfraLogisticsDashboard-AR&#47;InfraLogisticsDashboardar' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-INfraLogisticsDashboard-AR&#47;InfraLogisticsDashboardar&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433026151');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*1.77)+'px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433059049' style='position: relative'><noscript><a href='#'><img alt='Industrial cities ar ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-IndustrialCitiesDashboard-AR&#47;Industrialcitiesar&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='3-Infrastructureslogistics-IndustrialCitiesDashboard-AR&#47;Industrialcitiesar' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-IndustrialCitiesDashboard-AR&#47;Industrialcitiesar&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433059049');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1877px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433103860' style='position: relative'><noscript><a href='#'><img alt='Private Industrial Cities ar ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-PrivateIndustrialCities-AR&#47;PrivateIndustrialCitiesar&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='3-Infrastructureslogistics-PrivateIndustrialCities-AR&#47;PrivateIndustrialCitiesar' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;3-&#47;3-Infrastructureslogistics-PrivateIndustrialCities-AR&#47;PrivateIndustrialCitiesar&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433103860');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='2827px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
</c:if>
<!-- Tableau Report End -->


<section class="" id="">
          <div class="container">
			<div class="row INL_row_shadow">
				<div class="col-md-12">
					<div class="INL_bg_gold_header">
						<h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.infralogistics.heading.name"/></h1>
					</div>
				</div>
				<div class="col-lg-6 pull-left INL_icons">
					<div class="row pt-2">
						<div class="col-xs-4 col-md-4">
							<div class="INL_padding_Center text-center">
								<img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/International_Airports_icon.png" loading="lazy">
								<h6 class="INL_logistics_icons_headeron">${infraLogisticsLanding.internationalAirportsValue}</h6>
								<p class="INL_logistics_icons_para">${infraLogisticsLanding.displayAirportslabel}</p>
							</div>
						</div>
						<div class="col-xs-4 col-md-4">
							<div class="INL_padding_Center text-center">
								<img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Domestic _icon.png" loading="lazy">
								<h6 class="INL_logistics_icons_headeron">${infraLogisticsLanding.domesticAirportsValue}</h6>
								<p class="INL_logistics_icons_para">${infraLogisticsLanding.domesticAirportsLabel}</p>
							</div>
						</div>
						<div class="col-xs-4 col-md-4">
							<div class="INL_padding_Center text-center">
								<img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Containers_icon.png" loading="lazy">
								<h6 class="INL_logistics_icons_headeron">${infraLogisticsLanding.containersValue}</h6>
								<p class="INL_logistics_icons_para">${infraLogisticsLanding.containersLabel} ${infraLogisticsLanding.containersAmount}</p>
							</div>
						</div>
					</div>
					<div class="row pd-2">
						<div class="col-xs-4 col-md-4">
							<div class="INL_padding_Center text-center">
								<img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Ports_Capacity_icon.png" loading="lazy">
								<h6 class="INL_logistics_icons_headeron">${infraLogisticsLanding.portsCapacityValue}</h6>
								<p class="INL_logistics_icons_para">${infraLogisticsLanding.portsCapacityLabel} ${infraLogisticsLanding.portsCapacityAmount}</p>
							</div>
						</div>
						<div class="col-xs-4 col-md-4">
							<div class="INL_padding_Center text-center">
								<img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Airports_icon.png" loading="lazy">
								<h6 class="INL_logistics_icons_headeron">${infraLogisticsLanding.airportsValue}</h6>
								<p class="INL_logistics_icons_para">${infraLogisticsLanding.airportsLabel}</p>
							</div>
						</div>
						<div class="col-xs-4 col-md-4">
							<div class="INL_padding_Center text-center">
								<img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Ports_icon.png" loading="lazy">
								<h6 class="INL_logistics_icons_headeron">${infraLogisticsLanding.portsValue}</h6>
								<p class="INL_logistics_icons_para">${infraLogisticsLanding.portsLabel}</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6 pull-right">
					<div id="logistics-json-data" style="display: none;">
						${lengthOfNetworkjson}
					</div>
					<div class="">
						<!-- <div class="logistics-chart-header">
							Length Of Network (Km)
						</div> -->
						<div id="chartdiv" style="height: 500px; padding-top: 20px;" dir="ltr"></div>
					  </div>
				</div>
			</div>
          </div>
        </section>
        <!-- -----------Logistics Dashboard--------------- -->

        <!-- ----------Industrial Dashboard---------------- -->
        <section class="" id="">
          <div class="container">
			<div class="row INL_row_gray_shadow INDS_BG_none">
				<div class="col-md-12">
					<div class="INL_bg_gold_header">
						<h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.infralogistics.section.heading.name"/></h1>
					</div>
				</div>
				<div class="col-md-12 text-center">
					<h4 class="INL_industrial_city_header"><spring:theme code="economic.infralogistics.industial.section.heading.name"/></h4>
				</div>
				<div class="col-md-12 text-center">

					<div class="container-fluid w-highlight-container h-100 d-flex flex-column">
						<main role="main" class="pb-3">
							<ul class="nav nav-tabs  mt-3 p-0">
								<li class="nav-item">
								  <a class="nav-link active tabs_bg_gray_color w-100" href="#Eatern" data-toggle="tab" role="tab"><spring:theme code="economic.infralogistics.industial.eastern.sector.tab.text"/></a>
								</li>
							   <li class="nav-item">
								 <a class="nav-link tabs_bg_gray_color w-100" href="#Middle" data-toggle="tab" role="tab"><spring:theme code="economic.infralogistics.industial.middle.sector.tab.text"/></a>
							   </li>
							   <li class="nav-item ">
								 <a class="nav-link tabs_bg_gray_color w-100" href="#Western" data-toggle="tab" role="tab"><spring:theme code="economic.infralogistics.industial.western.sector.tab.text"/></a>
							   </li>
							</ul>
							<div class="tab-content INDS_Border_blue">
								<div id="Eatern" class="tab-pane fade active show INDS_margin_leftn40" role="tabpanel">
									<div class="row">
										<div class="col-md-12">
											<div class="w-tab1 h-100 w-100">

												<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="980.047" height="902.046" viewBox="0 0 1116.047 902.046">
													<defs>
													   <filter id="a" x="316.397" y="230.063" width="433.521" height="545.471" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="b"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="b"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="c" x="176.301" y="396.77" width="377.384" height="429.818" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="d"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="d"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="e" x="96.379" y="236.531" width="390.752" height="393.375" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="f"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="f"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="g" x="441.747" y="155.087" width="619.399" height="716.347" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="h"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="h"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="i" x="292.344" y="239.747" width="310.948" height="292.889" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="j"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="j"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="k" x="190.533" y="165.696" width="411.007" height="334.331" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="l"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="l"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="m" x="0" y="160.756" width="405.59" height="375.815" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="n"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="n"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="o" x="142.154" y="0" width="518.387" height="399.363" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="p"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="p"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="q" x="299.232" y="651.184" width="240.463" height="250.863" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="r"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="r"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="s" x="386.638" y="597.6" width="339.362" height="279.331" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="t"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="t"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="u" x="266.523" y="542.39" width="218.143" height="228.124" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="v"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="v"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="w" x="14.258" y="26.296" width="464.837" height="340.682" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="x"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="x"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													   <filter id="y" x="291.405" y="532.86" width="298.063" height="324.219" filterUnits="userSpaceOnUse">
														  <feOffset dy="18" input="SourceAlpha"/>
														  <feGaussianBlur stdDeviation="27.5" result="z"/>
														  <feFlood flood-opacity="0.161"/>
														  <feComposite operator="in" in2="z"/>
														  <feComposite in="SourceGraphic"/>
													   </filter>
													</defs>
													<g transform="translate(-129.225 -1677.866)">
													   <g transform="translate(0 10)">
														  <g transform="translate(170.002 1674.398)">
															 <g transform="translate(-0.001 0)">
																<path d="M419.9,883.276l-.5-1.016.882-2.605-1.78-1.711-1.238-3.253.825-5.247-.489-.425-.692-.886v-1.367l.017-.842-3.7-4.58-2.076-1.015-.942.368-.576-1.99-.752-.279-1.015-.457,1.573-1.412-.781-2.051.458-2.723-2.02-1.651-3.2-.987-1.959-2.767.339-2.7-.679-.263-1.647-.987-.384.441,1.663,2.872-.972.971.073,2.917-.442,1.4-.263-5.494-1.444-4.7-.78-6.125-.826-.457.971-3.107-.429-2.4-2.12-.558-2.311-3.641-2.906-2.221-1.443.073-.292-2.2-1.165-1.384-10.785-7.675-.959-1.517L373,809.138l-1.83-1.505-1.546-.041.279-1.181-5.567-5.126,1.015-.012-1.484-1.343.381-1.574-.87,1.161-.721-.485-.178-.809.869-.267-.323-1.489-2.048-.793-1.121-2.638,2.9-4.641,5.232-3.018.329-.71-.329.634-5.232,3.022-2.9,4.64-.586-2.755-.4-.485-1.4-1.812.044-.72-.47-.842-.547-.546-.752,1.121-.529-.514.529-1.7-.676-.356-.708-.955.223-1.282-1.076-1.283-.8-3.006.073-1.177-.247-2.076-1.21-1.177.223-1.784-2.08-1.562-.926-1.914-3.33-.134-1.563-1.222-.279-2.387-2.565-3.2,1.83-7.205-2.3-.591-1.7-1.351-1.768-4.021,1.9-4.422-.295-2.488-4.5-1.914-1.636-3.876.562-2.225-.663-2.12-1.21-.854-.825-2.007-2.326-1.193.44-.631-.708-2.99.74-4.082-1.8-1.93-3.343-.825-4.111-3.107-.412-1.5,1.534,1.368.5-3.091-1.664-4.628-2.1-2.415-3.035.841-1.489-.473.264,1.149,1.634.769-.44.409-1.987-1.234-.636-2.209-2.091-1.048-.442-2.945-1.917-.854.618-1.857-.6-1.651-8.386-5.761-4.39-1.4-1.826-2.99-.958-.073.206-1.841-3.375.1-2.137-2.237-.753-2.181-2.428-.87-2.785.384.089.752-1.107.19-3.092-2.3-1.68.3,2.255,1.133.412,1.092-3.937-1.873-7.588-5.729-2.593-2.873.857-.8-1.578-1.1.268-1.578-1.417.457-1.032-1-1.489-1.946.34-.546,1.474,1-.587-1.724-1.461.133-2.178-2.917-2.42-4.45.77-1.133-.251-1.092-2.137-1.323-.223.943.886,1.428-.857-.324-1.178-3.2,1.311.631.5-.3-.779-.631L252,648l-.987-.178-.813.279-3.019-2.533.368-1.93-1.238-1.133-1.9-.105-1.824-2.856.235-1.768-1.947-3.463-2.444-1.473-1.312-6.247-1.222-1.032-1.873-3.681.161-1.149,1.283-.574,1.9-1.327,1.033-2.751-1.8-1.505.19-1.206-.384-1.092,1.326-.146-1.355-1.974.368-.574h-.87l-.353.425-1.148-.983-1.372-8.945-2.107-4.6-4.771-6.23-.562-2.739.578-.692-.752-2.124.866-1.145,2.019-.417-1.5,2.771.766.943.68-1.295,1.165.251.292-4.054,1.8.15-.353-1.266.826-3.257-.5-1.016-.826,4.2-.854-.38,1.178-2.872-.47-2.169,3.314-6.17-.029-2.5.648-1.06,1.239-.267.468-2.031-1.72-.016-.223,1.355-.485-.279-1.063-.842.708-3.152-.146-5.3-2.771-4.641-.576-2.593-1.723-2.1,1.314-1.327-1.371-.1-.636.453-6.289-10.5,1.915.724,2.019,3.3-.279.7,1.753.307.748-1.06-2.327-3.313-.072-2.2h-3.375l.473-.781-1.313.146-.382.518-2.12-3.224,1.089-2.432-1.076-5.6-.368-1.121-1,1-2.477-3.609-.134-1.974-.485-.575-.34-.223-1.445-3.285-.1-3.827,7.542-.591,4.537-1.355,1.209.5,3.14,5.037,3.565,2.727,5.039,6.008,2.477,1.918,2.281.336,7.192-1.635,2.326.546.635,5.684,1.032.858,15.381-4.79,7.014-.457,1.578,2.051.412,4.786-.266.7.295-.768-.413-4.79-1.578-2.047-7.014.457-15.382,4.786-1.031-.854-.632-5.684-2.33-.546-7.191,1.635-2.282-.34-2.459-1.914-5.039-6.008-3.565-2.711-3.127-5.041-1.207-.5-4.536,1.355-7.547.587-.308.044-.178.028-.033-.168-.039.006.039-.006-.91-4.6-1.942-1.946.308-1.545-1.428.587-.96-.514-.19,1.1,1.239.591-.547.587-1.267-.736.146-2.225-.839-1.987.19-.765-1.148-.591-1.032-1.21-1.4-4.106-.013-2.4-1.506-1.651-1.355-.133-1.282-2.3-3.55-2.427-.854-1.517-.65-.413-1.323-.9-.485.9-1.032-.429-.489-.647-1.618-1.473.558-1.327-1.473.6L185,472.638l-2.678-1.752.234-.457-.915-.5-1.663.68-5.365-4.183.913-.752-.869-.429.854-2.472-2.049,1.117.664.563-.647,1.869L171,466.04l-4.051-3.034L165.2,459.9l-1.165.235-.558-1.048-1.017-.206-.133-1.222-2.286.886-1.529-.53-.118,1.194.794.235-.547,1.339-2.222.073-.975-1.44-1.725.129-2.68-3.989-2.2-1.578.457-2.682.987.679.381-1.651-1.736-2.164-.619-2.136-1.479-1.252-.01.059-2.387-4.272.21-1.015-1.238.485-.825-2.415-4.1-2.225.489-.664-1.137-2.444,1.238.1L139,430.555l-1.355-.518.62.6-1.15.858-1.959-2.622,1.089-.886,1.991.441-1.312.647,1.769.546.515-1.736,1.7-1.388-1.02-2.193,1.962-4.584-2.136-4.167,1.161-2.152-.866-3.86-6.883-7.687-2.017-5.142.13-2.314-3.977-3.224.06-2.622-1.371-.368-.324-3.051-1.578-2.31-3.107-1.343-3.286-4.685-.575.016.781,2.225-.547.263-.9-.089-.782-1.311-1.326-.089-1.618-1.266.263-1.562-.721.574-.664-.765-.854-2.828-1.411-.441,2.384-3.771.942-3.4-.352-2.861-2.64-1.651-4.078-.234-.413-.251-3.463-2.885-1.607-5.438-4.523-7.351-3.2-8.4L88.464,328.7l-2.979-3.2-1.5-2.917-2.577-1.428-2.639-6.2-.234-2.549L74.1,307.52,71.253,305.9l-.425-1.193-4.541-4.54-.631-3.091,1.489-1.562-.458-1.7-3.39-5.567-3.772-1.93-3.815-4.361-.267-2-2.636-3.536-.591-4.568-2.18-2.783-.56-2.933L47.972,263.4l-1.25-1.266L44,261.31l-.279-3.7-3.671-2.945-.955-2.387L34.2,249.747l-.279-.854,3.209-.959-.1-.87-2.946-.53-1-.87-1.915.089-2.5-2.444-.826.324-1.327-.886v.384l.753,1.194-1.622-.959L22.1,245.313l-3.315-.251-.784.6-.336-1.412,1.5-.825-2.444.162-.857,1.986-.4-.619.942-1.647-.825.117-.793,1.076-1.506-.239.647,1.873-1.412.028.647-2.476-1.986-.279.47-.486-.648-1.222-.368.72-1.06-.441-1.667,2.3L8.78,245.9l-1,.206L6.733,248.23l-1.15-2.136.3-1.885-.781-.324-1.105.737L4,243.456l2.89-3.726,1.428.53L7.7,238.726l.886-.631,1.118-3.742-.5-.117L14.086,222.2l1.7-2.549-.4-5.171-1.134-2.844,2.829-8.484.044-4.345.576-4.361.942-1.869-.6-2.448,1.205-1.987.15-2.533.972-4.155,1.088-1.121-.263-1.209,1.529-.9-.7-2.828,1.192-.676-.355-1.266.9-2.007,56.3,10.005,21.586-17.781L114.674,141.9l37.957-7.6,8.519-19.416,16.676-9.989L127.349,46.388,176.6,31.917l.054-.263,51.616-15.188,15.5-13.509L243.106,0l1.712,2.266,3.432,2.079L275.6,12.327l8.943,4.183,5.037,3.4,130.68,92.3,97.321,67.248,10.311,2.387,66.484,10.43-18.36,6.465-12.156,12.845L545.2,246.474l-13.479,17.9-1.9,6.882.574,2.237,2.165,2.695-1.915,3.285L528,280.737l-1.94-.667.047.052,2.222.765,2.655-1.266,1.888-3.24-2.094-2.594-.56-2.253,1.886-6.878,13.483-17.9,18.652-34.87,12.172-12.845,18.343-6.465,7.516.19,3.888-1.869,6.026-4.657,46.192,6.057,1.282.987,4.95,7.926.9,3.755.326,6.793,5.2,6.922-.251,1.032,36.8-.635,1.739,1.679,1.886.692-.06,3.317,1.237.971-.263.5,1.25.219-1.485,4.434,1.43,2.917,2.165,1.517,2.5,3.285-1.015.781-.269,3.932.575,1.473,3.02,3.508,5.734,1.545-1.138,1.355-1.235-.117.118,1.549,2.344,2.666,1.473.146.072,1.99,2.99,1.853-.529,3.095.68.647-.118.692-1.326.886V263.3l-.813-.061.428-2.945-1.06,1.5-.425-2.075-1.918,6.481.942.117.664-1.841.514,3.508.943-1.4.618,1-.5,1.74,1.573-.457-.468.854-.77.162-.073,1.21-.469,1.015,1.646.8,1.105-1.946.93.279-.841,1.533.161,1.9,1.194-.607-.146-1.529,2.3-1.165,1.384.546,1.591,2.31,3.819,1.711,5.391-.089,2.489,1.222.9,1.958,1.65.93.3,2.678-.826-.219.369-1.015-.53-.8-1.044,1.7-.8-.9-1.047,1.044-1.1-1.06-1.093.692-.458-.663-1.015,1.355-1.21-.5-.486,1.076.6-.307,2.271,1.384.824,1.048,1.489-.356-.044-1.546,2,1.133-.9,1.121-1.077.1.474,1.355-.987,1.813,2.533-1.606,2.388.8-.017,5.466.988,1.663.8.033.618-.089.782-.34,1.68.842-.71,2.783-.958-.34-.647.6.752.016-.295.825,1.121-.457.781,1.517.987-2.549.735.514-.223,1.562,1.77-.971,2.152.752-1.314-2.476,1.873-1.93-2.549-2.326-1,.117.956-1.533-.31-.781,1.089-.692.681,2.9,1.635.089.339,2.213-1.091.453,1.428,1.416,1.326-1.032.871-3.961,1.429-1.93-1.457,4.345-.652,2.3L765.4,298.7l.81,2.888,1.724,1.707,3.536-2.1,1.578.6-.044.134-.721-.016-.134.061-.4.692-.4-.692L768,303.677l1.412,1.813,2.2-.53-.238,1.076-.821.028.027,1.752,1.049.045.352,1.254.19.291,3.392,2.666.542.162,1.784,1.076,1.667.546,1.161.9,4.392.441,3.051,3.257,6.617,7.792.72.663.606.591-.873.129-1.915-2.549-2.477-.631-1.44,1-1.812-2.876-.09-1.295-.69,3.3.218,1.4,1.193.292-.513,1.606.456,3.92,1.368,3.431,2.08,2.711.146-1.7.458-.453v0l.558,1.46.68-2.4.66.162-.086,3.524,1.162.647,1.757-2.593-.534-.486.562-2.666.368.574.179,1.562.559-.9.072,1.736-1.44.858.117,1.545-.931.162,2.432,2.237.765,2.387-.792,6.04.663,1-.534.044-.146,2.177-1.473,1.667.279-1.149-1.283-1.8-.235,3.3.619.769-1.221,3.123.278,1.914-1.1.619-3.861-8.779-1.121-.8-.765.473-1.694,4.111,1.311,1.9-.765.987.06,4.45.9.235,1.181-1.355,1.194,1.088,2.02.15.736,1.1-.222,1.165.93.837-1.02.235-.546,1.533,1.89,4.288,2.667,3.945,1.605.975,1.032,2.974,1.1.591-.336,1.295-.606.267-.587-2.165-2.093-.49-1.473-2.165-.812.251-.693-.854,1,2.561,1.606,1.121.073.825L797.6,381l.324.413.352,2.65,1.886,1.711.986,2.148,1.858.647.1,1.622,3.006,3.742.591.057-.072-1.618-1-1.339.975-.781.911,2.415,4.76,3.666,1.962,5.951-.5,3.552,1.842,2.99-.607,2.621,1.227,3.771-.4,1.3,1.311.664-.736-.959.473-1.21,3.715,5.2,1.547,4.859,1.4.061.792,1.015,2.2,9.621.944-2.711,2.326-.445-.369.708,1.591,3.139,5.438,6.449,6.746,1.181,3.614-1.416,2.4-2.65,1.709.575,1.477-.486-.15.749.883.651,1.121-.518-.972,1.885.19,3.564,2.137-1.279.547-2.047,2.358-1.917-.134-1.4,1.312.781.781-.631.6,2.415,1.519-.854,1.121,1.117-.915.943-1.78.105-.8,1.4-.646-.235-.619,2.177-4.524,3.6-.883,4.552-.824.19.188,2.5,3.3.619,1.324-1.736,2.99-.267,2.464.971,2.223,3.123,2.4-.1,1.105,1.736-.752,6.234,50.538,61.321,35.052,7.015,87.982,20.269,10.384-10.988L1075,584.329l-33.847,107.887L888.8,745.838l-58.879,11.7-85.267,9.3-47.281,23.1-30.812,37.71-7.175,17.987-2.254,1.367,3.816-36.015,11.771-93.525,28.731-225.133.032-11.417-2-10.163-.023-.047,2,10.1-.029,11.417-28.73,225.149-14.925,3.872,14.925-3.872L660.92,810.89,657.1,846.9l-11.023,6.955-10.359-.53-12.051-16.264-15.765,2.253L588.87,838.7l-25.39-3.475-9.209-.619-9.207-5.935H515.993l-2.047,1.444-2.874.4-3.893-2.047-3.978.174-12.949,1.651-1.21,2.241-1.591-1.974-4.347.781-2.019,2.828-3.845-1.06-2.358.631-2.9-.425-1-1.311-2.974.53-3.809-3.97-.068,1.109-3.491-3.386-7.795-4.964-3.625-.5-2.806,1.368.021.032,1.43,2.047-2.829,4.3.134,3.492,2.282,1.327v.959l1.2.514.381,1.016-6.616,2.079.619,2.755-2.237,2.015.072,2.14-.915.469-.089,1.295.133.518,1.445-.591,2.4.708,1.21,2.415-.81,1.339-3.347-.38.386,4.139-2.21,1.032-.061.882,2.137,2.783,2.282.5.489,1.222,1.484.708-.322,1.857.708,1.266-2.108.162-.47,1.193.721,3.831-1.235.218-3.051-1.635-.975.267.65,1.323-1.521,4.038.076,1.562-6.515,1.711-2.461,1.752.619,3.993L427,884Zm44.173-69.765,1.914,8.735Zm-25.919,2.549,1.017.842,4.334.88-.017-.026-4.347-.886-1-.837-.573-1Zm-27.348.089,2.239.927,2.08-.206.824-.971,2.461-9.944,1.78-3.091,4.232-3.184,3.252-1.294-.014-.017-3.254,1.295-4.229,3.184-1.785,3.095-2.444,9.94-.841.975-2.063.206-2.239-.93-4.787-4.653Zm18.154-9.6,4.793,2.423-4.793-2.425Zm-39.37-2.974,12.2.587-12.2-.591-1.076-.943Zm39.548-3.55v0l-.84-.939ZM464.159,790l2.031,9.014Zm-86.008,6.7h0l-1.093-.691Zm1.088-.166,3.253-.5h-.007Zm-2.588-2.735.411,2.209-.411-2.211Zm1.205-10.713-1.172,10.416,1.188-10.416L376,781.574Zm86.421,4.462L464.159,790l.118-2.444,1.294-2.492Zm-94.759-5.293,6.484-.687-6.483.682Zm-19.742-32.077.9,4.373,1.5,2.181,2.125.708,9.887.312,3.107,1.78,4.341,13.31.006-.024-4.375-13.464-3.111-1.764-9.887-.312-2.1-.72-1.518-2.169-.87-4.215Zm153.029.1-1.43,3.273,1.43-3.269.25-3.123Zm-2.343-15.746,2.593,12.627Zm20.937-7.97,7.693,3.524,4.774,1.06,13.039,1.032-13.039-1.032-4.774-1.06-7.693-3.524-12.537-8.233ZM328.472,714.828l1.913,2.27,5.439,3.14,3.034,4.507,2.343,1.545h3.816l3.254-1.59,3.8-13.229,3.538-6.57,2.237-.87,6.795-.057,1.161-.736,2.226-1.092,3.375.194,2.7,2.736-.014-.1-2.727-2.787-3.375-.19-2.21,1.088,6.275-12.8-6.275,12.8-1.166.752-6.807.061-2.237.854-3.537,6.57-3.8,13.242-3.254,1.59h-3.8l-2.343-1.546-3.035-4.507-5.422-3.14-1.893-2.239Zm44.039,2.711,3.171,6.432.01-.036-3.173-6.436ZM320.176,689.89l.987,1.032,5.39,1.606,1.4,2.2,0-.076-1.429-2.237-5.378-1.622-.987-1.031Zm158.6-5.45.324,2.354,4.051,4.361-4.051-4.361Zm-105.823-2.019-1.238,2.638ZM383.06,674.439Zm-3.184-5.247,3.022,2.9-3.019-2.9-.341-1.161Zm-.337-1.161,3.151-9.4Zm-37.807-11.239,1.617,6.615v-.032l-1.611-6.606Zm12.123,2.828,0,0-.968-2.849Zm120.38-12.627,4.525,7.262,5.3,5.3-5.3-5.3-4.525-7.262-8.48-7.375Zm-20.965-52.016.133,1.682-.132-1.685Zm1.8-27.794L457.7,586.47l0-.005-2.638-19.29Zm.96-11.4.453,2.31-.453-2.31L446.52,553.7l-8.323,2.164-5.451-1.885,5.451,1.885,8.323-2.164ZM273.291,538.353l.089,1.266.866,1.092,5.867,1.736,3.079,2.816.664,3.4-1.226,5.7.959,2.326,7.012.825,3.437-1.651,6.818-6.036,10.008,3.253,2.387-.028,6.584-9.811L333.6,532.758l7.544-7.841,3.609-2.605,8.355-3.4.826-1.121-.016-.059-.781,1.062-8.356,3.4-3.61,2.605-7.543,7.853-13.763,10.491-6.587,9.807-2.385.032-10-3.257-6.823,6.04-3.435,1.651-7.014-.825-.955-2.326,1.223-5.688-.663-3.4-3.08-2.812-5.863-1.74-.871-1.088-.088-1.258Zm157.6,12.226h0l.026-.515Zm-38.383-36.1,7.588,16.587,4.435,2.4,20.776.931,4.553,1.25,2.711,2.771-2.711-2.771-4.553-1.25-20.776-.931-4.435-2.4Zm-24.268-21.7-1.562,2.254-17.312,8.6-5.292,4.228.011.089,5.253-4.2,17.312-8.589,1.563-2.253,1.015-3.253v-.037Zm11.509-10.2.53,4.612,6.115,4.859,1.663,7.161,2.488,5.561-2.474-5.561-1.663-7.161-6.13-4.859Zm-11.509-4.948-.854,3.415,0,.008.823-3.276,4.771-1.517,3.839.5L373,476.118ZM380,435.709l5.952,4.933.013-.1-6.007-4.974Zm37.617.882,8.353-1.311-8.355,1.311Zm18.754-11.518,2.815,5.644ZM362.9,413.126l2.565,2.978,7.807,2.047,2.08,1.663L378,428.88l-2.692-9.229-2.063-1.663-7.822-2.047-2.517-2.9Zm296.115-53.986.559,33.333-.926,22.2.513,10.919.016.083-.5-10.882.931-22.2-.562-33.325-.631-7.307-.026-.062ZM276.739,396.052l.926,2.371,1.915,2.2,2.3,1.044,2.286.044,13.808-4.77,11.873-.574,3.553-1.74,5.7-6.833,4.816-1.845,9.845-.218,17.385,3.4,10.2,2.694,1.979,1.7-.021-.143-1.985-1.707-10.2-2.7-17.385-3.4-9.856.222-4.821,1.841-5.7,6.837-3.553,1.736-11.873.574-13.808,4.774-2.281-.044-2.3-1.032-1.919-2.193-.881-2.258ZM156.965,366.207l.987,2.416,3.2,2.727,5.479,4.907,2.65,5.713,2.638,9.313,1.093.453,4.671.194,1.613,2.011,0-.053-1.647-2.063-4.674-.19-1.088-.457-2.638-9.309-2.652-5.717-5.482-4.9-3.2-2.727-.968-2.367Zm359.146,3.2,3.714,7.161.457,4.657-.457-4.657Zm-140.594.87,3.536,7.044ZM388,356.178,389.2,365.5Zm113.614-14.791.725,2.241,5.3,5.255,9.854,14.969.205,1.266-.205-1.266-9.854-14.969-5.3-5.255ZM151.806,360.244l3.287,1.707,1.071.911-.024-.1-1.076-.9-3.286-1.7-5.908-3.71-2.258.251-2.856,2.606.016.088,2.873-2.622,2.254-.251Zm-15.13-12.4,2.47,4.634-.01-.038-2.479-4.644Zm140.192-4.523.5,1.091,0-.059-.542-1.165-1.078-.352-4.819,1.222-1.116-.352-1.712-2.173v.094l1.739,2.213,1.134.352,4.819-1.238ZM502.215,325.39l.636,7.541Zm-374.94-3.6,1.474,2.331,2.358,1.93,7.042,1.986,2.258,1.392-.02-.065-2.271-1.4-7.041-1.987-2.36-1.93-1.45-2.294Zm142.966,4.333.961,2.2,0-.019-.978-2.24-.009.01Zm-31.091-.385,9.683-.765L264.227,322l2.258-.1,1.094.61,0-.007-1.134-.631-2.254.117-15.4,2.99-9.651.762Zm-45.469-8.2,0,.019.632-3.51-.007-.031Zm21.6-7.278-.618,1.076.457,2.225.758,2.192.035-.028-.765-2.209-.458-2.209.6-1.049Zm-26.922-2.148.006.03.293-2.328-.008-.01Zm329.593-12.756,5.3,4.4,1.975,3.686.22,3.682-.22-3.682-1.975-3.686-5.3-4.4-3.464-1.372ZM226.7,238.33l2.063,1.8,1.663,1.946,3.477.764,6.014,1.06,2.267.356,7.3,1.93,4.33,3.342,1.917,2.359,3.4.987,1.1.457,1.959,3.564,1.15.676,3.479,1.46,2.076,2.415,1.534,3.7,3.3,13.8.575,2.4.206,1.355,2.063,5.761,5.5,3.168,1.194.53,4.362,2.71.515,1.117-.418,3.483,0,0,.428-3.552-.529-1.121-4.35-2.711-1.207-.53-5.5-3.18-2.063-5.761-.206-1.355-.575-2.4-3.3-13.8-1.55-3.7-2.061-2.415-3.491-1.461-1.138-.676-1.959-3.581-1.1-.457-3.4-.987-1.917-2.342-4.33-3.342-7.3-1.914-2.267-.356-6.014-1.06L230.428,242l-1.663-1.962-2.08-1.78-2.4-.724-5,1.473-5.243,2.771-2.55.117-1.913-1.59-1.15-3.637-1.108-.312-2.412.473-1.138-.7L201.5,234.28l-1.178.057-3.538,2.577-14.924-1.736-4.893-1.622-3.565-1.78-5.114-5.231-1.105-2.4-.837-3.9-.457-6.469.663-10.592-.681-2.415-2.31-1.384-1.137.6-4.229,4.244-2.328,1.06-1.857,2.165-3.8,8.929-2.315.837-12.6-1.044-6.806-2.654-7.6-1.06-6.588,1.238-6.467,1.517-4.832,2.019-2.667.486-2.536-.943L88.625,204.8,86.9,202.388l-2.93-3.492-3.7-2.844-7.486-2.27,7.486,2.286,3.7,2.84L86.9,202.4l1.724,2.415L97.8,216.794l2.536.943,2.667-.473,4.832-2.031,6.467-1.517,6.575-1.222,7.6,1.06,6.808,2.65,12.6,1.06,2.311-.837,3.8-8.913,1.854-2.181,2.33-1.06,4.229-4.244,1.133-.6,2.315,1.4.676,2.416-.66,10.592.453,6.465.843,3.892,1.1,2.416,5.127,5.227,3.565,1.784,4.876,1.618,14.94,1.74,3.538-2.577,1.165-.061,2.267,1.857,1.152.692,2.415-.457,1.105.312,1.165,3.637,1.9,1.59,2.55-.129,5.243-2.771,5-1.456ZM123.883,296.548l2.095,2.017-.014-.059-2.108-2.035-2.271-.263-4.6,1.016-1.167-.692.006.044,1.19.708,4.6-1Zm389.605-5.336h0l.09-2.339Zm29.484-.676.03.037-2.05-7.433-.011-.011Zm-52.129-29.578,14.7,1.9,4.594,1.765-.09-.053-4.832-1.857-14.605-1.888Zm-35.834-29.02,3.711-1.238,7.442-4.98,2.193-.292-.211-.165-2.315.312-7.441,4.976-3.684,1.229Zm-19.819-4.6,1.415.457,9.521-.455-.29-.151-9.562.457-1.146-.37Zm-21.012-7.836,5.88-.312,6.807-3.653,3.77-.66-.151-.181-3.949.7-6.808,3.653-5.812.3Zm-13.61-12.736-.115-.1L379.135,201.5l-6.559.53-7.014,1.99-17.931-5.2L316.748,206l-3.492-.546-6.118-2.655,0,.005,6.455,2.8,3.491.546,30.886-7.173,17.93,5.2,7.017-1.986,6.556-.53ZM35.7,200.308l2.83-.174-2.834.173Zm6.221-3.5-3.391,3.33,3.406-3.318,4.8-1.234Zm314.822-92.258,0-.005-5.3-2.342-8.029.061-4.564-.92.138.111,4.758.959,8.028-.061ZM281.614,89.37l5.452,1.651,26.447.915,10.316-1.3-.315-.147-10.331,1.294-26.445-.914L281.533,89.3Zm-14.939-6.319,6.454.117,2.3,1-.015-.012L272.8,83.022l-6.454-.117L238.336,77.82l-11.865.206-10.679,2.007L195.78,82.225l-5.038-.057-3.166-.851.095.082,3.4.914,5.042.061,20.011-2.2,10.68-2,11.861-.207ZM382.9,875.763l.574-1.06.62.279.263-.129.5.38-.959,1.651Zm4.565-3.945-2.667-.546,1.828-.886-.134-1.853-2.649-.651-4.48,1.371-2.9-2.415-1.68-.473-1.757-1.517-1.412-2.8-1.962-.045.68-1.845-1.474-1.189-.06-1.562,4.673,2.711,2.165,4.58,3.46.987,2.343-.53,1.461,1.974,2.355-.692-.413-1.327-.647-.105.413.914-1.238.061-.631-.9.765-.858.148-1.885.866.15.268-.931,1.267.15.312,2.1,1.116,1.505.62-.117.869,1.473,1.341-.312.044.752.691-.089-.117,1.388-.91.955.647,4.377-1.372.162Zm-4.9-.283-.725,1.137-.822-1.723.514-.825,1.238-.162.53.781-.619,2.209Zm-8.431-1.016-.913-1.283,2.095,1.562Zm-6.247-3.3-.956-1.121,1.166-.5,2.841.736,1.667,2.4Zm12.289-3.977-4.447-1.477-2.7-3.843.018-1.59,2.415-.3,1.383-1.93,1.68,1.149-.821,1.384.854,1.355,2.031.619-.188,1.283-1.919-.469-1.089.866,3.226,2.448,1.092,1.529-.411.663Zm-20.229-2.46.307-.49-1.1-1.015.116-2.108,3.023,4.495Zm22.382-.708.721.5-2.388.206Zm-18.963-4.216,1.647-2.075,2.225,1.618-1.015,1.711-1.475.457Zm12.168-3.977,2.416.987.619,2.079Zm-4.86,2.342-.323-1.707,1.663.955-.691.842ZM130.59,420.889l.428-1.206,1.769,1.234v.975Zm-8.916-22.126.118-1.724-3-1.635.527-1.165,1.355.886.619-.312,1.417,3.949Zm6-4.066-1.841.061.6-1.606-.987-.635.591-.19.958.235.559,1.873.81-.267-.384,1.222Zm-3.464-3.4.636-1.133,1.328,1.282Zm679.961-2.65-1.489-.886.057-1,1.8.781.515,3.047Zm-681.829-.073.133-2.2,1.384,3.447Zm-3.89-.4,1.06-1.206.826,1.368-1.255.825Zm3.917-2.65.708-.4.6.651-.381.91Zm-21.436-2.593,1.162-1.032,2.047,2.371-2.281.15Zm2.959-5.082-.029-2.46.692,2.638,1.547-.93-.146,1.869.987.785-1.089.1ZM789.9,330.957l-1.489-.178.312-.955,1.708-.53,1.281,1.015-.072,2.359Zm-30.031-39.345-.322-1.149.809-2.286,1.032-1.278,1.975-.53,2.577,1.545L772,289.5l-2.755.073-.765-.38-3.493,1.1-.663-.886.663-.368-.161-.72-1.724-.809-3.142,3.447.5.837ZM9.634,252.9l-.663.044-.085,1.21L8,253.991l.352-1.327,1.68-.68,1.3.279.882,2.257-1.5.631Zm-9.149-.016L0,249.7l2.152-.854.705,1.044-1.812.724-.661-.473.029.563,1.619,1.234,2.845-.5-.146,1.193,1.725,1.574-3.509.518Z" transform="translate(-0.001 -0.398)" fill="#fff" stroke="#fff" stroke-width="0.5" opacity="0.6"/>
															 </g>
														  </g>
														  <g transform="translate(212.002 1732.516)">
															 <g transform="translate(0)">
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#a)">
																   <path d="M502.065,555.151l-12.435,3.228-20.119,2.934L397.27,567.5l-10.864-.859-3.977-.884-6.408-2.934-10.446-6.862h0L348.955,541.12l-4.8-7.807-3.376-3.634-.27-1.964,1.51-7.5,4.1-7.476.1-3.633-1.3-2.124-4.419-4.419-3.768-6.052-7.071-6.15h0L324.33,481.7l-5.855-7.807-.712-1.8,2.161-10.41-.663-8.531,2.86-4.2.835-2.872-2.2-16.081,1.178-7.562-.381-1.927-7.918-1.731-6.935,1.8-4.542-1.571-1.547-2.823.344-6.555,1.056-3.6-2.259-2.308-3.793-1.043-17.308-.773-3.7-2-6.322-13.822-1.117-6.923-2.578-5.794-1.387-5.966-5.107-4.051-.442-3.842.54-4.468h0l-.049-11.085,1.731-5.99-1.154-4.419,3.621-5.217.491-3.854h0l3.732-6.236,2.664-1.94,1.841.147,6.346,3.13,4.751.994,7.034.614,6.96-1.093,6.985,1.068,2.983-2.2,1.043-2.676-2.345-4.7.184-2.823,6.653-8.212,4.014-3.093,15.811-3.867,3.486-3.449L339.122,288l2.8-5.487,2.48-2.418,3.842-1.007,10.189,1.3,8.838-.835,6.788.27,1.031-4.787-.38-3.879-3.093-5.966,1.326-3.572-.172-1.056L364.557,248.1l-4.419-4.382-.6-1.866,1.031-7.046-.528-6.285,4.922-1.35,5.524-7.206,3.02-2.59,5.131-2.259.761-1.817-.184-3.069-1.645-3.069-4.419-3.67-2.885-1.142h0l-.835-2.308.074-1.952,1.215-3.805,2.971-4.076,3.867-2.013h0l2.381,2.6,1.854.638,2.21-1.056,1.6-2.737h0l4.076,2.848,2.6,2.713,1.719,6.273,1.522,1.878,12.779,7.463,7.058,1.3,4.247,5.18,4.984,4.149,2.872.773,6.923.135,3.7.994,12.1,8.519,3.056,1.461,2.9.061,5.413-1.24,1.9.27,7.918,7.463,1.8,3.093,10.9,3.879,4.738,2.553,1.338,3.142.528,6.089.466,27.779-.773,18.5.43,9.1.835,4.542,1.375,2.2,2.8,2.21,11.588,7.733,11.281,11.244,2.062,2.529,3.621,7.1,1.669,8.458L526,367.535Z" transform="translate(141.14 107.28)" fill="#025635" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#c)">
																   <path d="M143.707,348.451h0l.147-.025Zm140.847-24.293-.54,4.468.442,3.842,5.094,4.051,1.387,5.966,2.59,5.794,1.117,6.923,6.322,13.822,3.7,2,17.308.773,3.793,1.043,2.259,2.308-1.043,3.6-.356,6.543,1.547,2.835,4.542,1.571,6.936-1.8,7.918,1.731.38,1.927-1.178,7.574,2.2,16.081-.835,2.872-2.86,4.21.675,8.519-2.16,10.41.712,1.8,5.855,7.807,5.327,8.666h0l-4.935,1.8-7.009,4.628-6.408-1.6-2.774.307-3.879,1.866-5.585.344-5.229,4.677-7.868,3.756-12.41,11.343-2.836,1.4-2.811-.295-1.719-1.252-5.733-11.134h0l-2.43-2.369-1.817-.27-6.653,3.388-5.107-.246-5.659,2.173-2.345-1.792L260.9,471.6l-1.5-1.78-1.755-.749-2.774.393-1.669,1.731-.859,2.6,1.363,5.573-.147,2.9-3.3,5.684-1.682,4.37-9.489,2.283-4.91,5.033.209,1.743.822.859,4.493,1.338,1.191,1.878-.381,6.482,1.117,4.775-.331,5.45,1.6,1.89,4.53,2.615,2.529,3.756,1.952,1.289h3.179l2.713-1.326,3.167-11.023,2.946-5.475,1.866-.724,5.659-.049.97-.614h0l1.854-.908,2.811.16,2.271,2.308.258,1.866-1.608,8.494,2.688,5.45-1.559,5.647-.933,1.743-1.866.921-5.819-.528-2.75.454-4.763,3.462-.921.073-2.418-2.3-.6,12.275.749,3.646,1.252,1.817,1.768.589,8.237.258,2.59,1.485,3.658,11.22-1.841,7.868h0l-1.24,2.381L269.86,583.4l-2.418,3.867h0l-.491-2.3-.331-.4-1.166-1.51.037-.6-.393-.7-.454-.454-.626.933-.442-.43.442-1.412-.565-.295-.589-.8.184-1.068-.9-1.068-.663-2.5.061-.982-.209-1.731-1.007-.982.184-1.485-1.731-1.3-.773-1.6-2.774-.11-1.3-1.019-.233-1.989L251.57,560.8l1.522-6-1.915-.491-1.412-1.129-1.473-3.351,1.583-3.683-.245-2.074-3.744-1.6-1.363-3.229.466-1.854-.552-1.768-1.007-.712-.687-1.669-1.939-.995.368-.528-.589-2.492.614-3.4-1.5-1.608-2.787-.687-3.425-2.59-.344-1.252,1.277,1.142.417-2.578-1.387-3.854-1.755-2.013-2.529.7-1.24-.393.221.958,1.363.638-.368.344-1.657-1.031-.528-1.841-1.743-.872-.368-2.455-1.6-.712.516-1.547-.5-1.375-6.985-4.8-3.658-1.166-1.522-2.492-.8-.062.172-1.534-2.811.086-1.78-1.866-.626-1.817-2.025-.724-2.32.319.074.626-.921.16-2.578-1.915-1.4.246,1.878.945.344.908-3.278-1.559-6.322-4.775-2.161-2.394.712-.663-1.314-.921.221-1.313-1.178.381-.859-.835-1.24-1.62.282-.454,1.228.835-.491-1.436-1.215.11-1.817-2.431-2.013-3.707.638-.945-.209-.908-1.78-1.1-.184.786.737,1.191-.712-.27-.982-2.664,1.092.528.417-.246-.651-.528.356-.307-.822-.147-.675.233-2.516-2.111.307-1.608-1.031-.945-1.584-.086-1.522-2.381.2-1.473-1.62-2.885-2.038-1.227-1.092-5.2-1.019-.859-1.559-3.069.135-.957,1.068-.479,1.583-1.1.859-2.3-1.5-1.252.16-1.007-.319-.908,1.1-.123-1.129-1.645.307-.479h-.724l-.295.356-.957-.822-1.142-7.451-1.755-3.83-3.977-5.192-.466-2.283.479-.577-.626-1.768.724-.957,1.682-.344-1.252,2.308.638.786.565-1.08.97.209.246-3.376,1.5.123-.295-1.056.687-2.713-.417-.847-.687,3.5-.712-.319.982-2.394-.393-1.8,2.762-5.143-.025-2.087.54-.884,1.031-.221.393-1.694-1.436-.012-.184,1.129-.405-.233-.884-.7.589-2.627-.123-4.419L159.6,384.16l-.479-2.16-1.436-1.755,1.092-1.1-1.142-.086-.528.38-5.242-8.752,1.6.6,1.682,2.75-.233.577,1.461.258.626-.884-1.939-2.762L155,369.393h-2.811l.393-.651-1.093.123-.319.43-1.768-2.688.908-2.025-.9-4.665-.307-.933-.835.835-2.062-3.007-.11-1.645-.405-.478-.282-.184-1.2-2.737-.086-3.192h0l6.285-.491,3.781-1.129,1.007.417,2.615,4.2,2.971,2.271,4.2,5.008,2.062,1.6,1.9.282,5.99-1.363,1.939.454.528,4.738.859.712,12.815-3.99,5.843-.381,1.313,1.706.344,3.99-3.253,8.507.074,1.056.724.908,4.886,1.448,2.566,2.345.552,2.836-1.019,4.751.8,1.939,5.843.688,2.86-1.375,5.683-5.033,8.335,2.713,1.989-.025,5.487-8.175,11.465-8.74,6.285-6.531,3.007-2.173,6.96-2.835.687-.933-.491-1.927-6.567-3.609-.933-.81-.245-1.829,4.407-3.523,14.423-7.157,1.3-1.878.847-2.713-.2-3.953-1.362-3.093.712-2.848,3.977-1.264Z" transform="translate(115.13 138.16)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#e)">
																   <path d="M142.695,354.643l-1.1-.749-.405.749-.859-.356-.405-.54-1.35-1.228.466-1.1-1.228.5-1.264-1.878-2.234-1.461.2-.381-.761-.417-1.387.565-4.468-3.486.761-.626-.724-.356.712-2.062-1.706.933.552.467-.54,1.559-2.062-.233-3.376-2.529-1.461-2.59-.97.2-.466-.872-.847-.172-.11-1.019-1.9.737-1.277-.442-.1.994.663.2-.454,1.117-1.854.061-.81-1.2-1.436.11L108.25,335.8l-1.829-1.313.381-2.234.822.565.319-1.375-1.448-1.8-.516-1.78-1.277-1.08h0l.675-3.977,1.166-1.8,6.936-.589h0l2.9-1.117h0l.97-.872h0l1.436-2.222h0l.675-.872h0l1.9-.5,2.836.552h0l3.167-.037,1.976-1.019,1.129-1.89.27-1.915h0l-.135-3.9h0L130.333,294h0l1.031-5.855h0l.123-.908h0l.307-2.823h0l-1.375-1.719h0l-3.891-.159h0l-.908-.381h0l-2.2-7.758h0l-2.21-4.763-4.566-4.088h0l-2.664-2.271-.822-2.013h0l-.663-2.786-.9-.749h0l-2.737-1.412h0l-4.922-3.093-1.878.209h0l-2.394,2.185-.344-1.915h0l-.994-3.83-2.075-3.891h0l-.822-2.062h0l.2-2.86h0l3.867-6.8.319-2.16h0l-.442-1.448h0l-1.89-1.166h0l-5.868-1.657h0l-1.964-1.608h0l-1.228-1.939-.847-3.228.994-10.962h0l-1.215-5.156h0L85.6,203.24h0l-1.89-.221h0l-3.83.847h0l-.994-.589-.11-.921,1.227-1.8h0l3.83-.847h0l1.989-.528h0l2.05-.994h0l1.952-.7h0l3.953-1.841h0l2.013-1.3h0l.933-.417h0l1.9-.479h0l4.861,2.038h0l2.9,2.136h0l2.971.381,2.124-.516,7.181-4.088h0l1.9-.565h0l.957.381h0l1.363,1.866h0l.982,3.879h0l.687,2.062h0l3.008,5.242h0l1.338,1.9h0l.994.687h0l1.866.319h0l6.6.295,1.227,1.522h0l-.245,1.952h0l.184.97,1.8,1.277h0l2.787,1.743h0l.184.921h0l-.528,2.946.233.921.908.417h0l.921-.012,1.927-1.461h0l1.89-1.682h0l2.075-1.584,2.025-.4,7.562,3.081.982-.786-.638-1.841h0l-.381-1.841.516-.9.921-.049h0l1.571,1.448,1.178,3.781h0l2.9,1.019h0l2.9.712,2.786,1.755h0l1.866,1.51h0l2.971,2.247,2.774.5h0l8.065-.638h0l12.828-2.48h0l1.878-.086h0l.945.528h0l1.5,1.755h0l.687,1.24h0l.835,1.915.295,3.265-.921,2.971-1.989,2.762v2l1.449,1.841.945.295,4.014-1.031.9.307.442.97-.135,5.107-2.332,13.392,2.553,12.03-.638,12.447.773,1.976,1.6,1.829,1.915.872,1.9.037,11.5-3.977,9.894-.479,2.958-1.448,4.751-5.7,4.014-1.534,8.2-.184,14.485,2.836h0l8.495,2.246,1.657,1.424.344,2.431-.7,13.9,2.136,2.479,6.506,1.706,1.731,1.387L299,319.266,304,323.415h0l-.491,3.855-3.621,5.217,1.154,4.419-1.731,5.99.049,11.085h0l-6.2-1.043L289.2,354.2l-.712,2.848,1.363,3.094.2,3.965-.847,2.713-1.3,1.878-14.423,7.169-4.407,3.523.246,1.829.933.81,6.567,3.609.491,1.927-.687.933-6.96,2.836-3.007,2.173-6.285,6.543L248.9,408.79l-5.487,8.175-1.989.025-8.335-2.713-5.683,5.033-2.86,1.375L218.7,420l-.8-1.94,1.019-4.738-.552-2.836-2.566-2.345-4.886-1.448-.724-.908-.074-1.056,3.253-8.495-.344-3.989-1.313-1.706-5.843.381-12.815,3.989-.859-.712-.528-4.738-1.94-.454-5.99,1.362-1.9-.282-2.05-1.6-4.2-5.008-2.971-2.259-2.6-4.2-1.007-.417-3.781,1.129-6.285.491h0l-.258.037h0l-.147.025h0l-.786-3.977-1.62-1.62.258-1.289-1.191.491-.8-.43-.16.921,1.031.491-.454.491-1.056-.614.123-1.854-.7-1.657.16-.638-.958-.491-.859-1.007-1.166-3.425-.012-2L149.1,360.3l-1.129-.111-1.068-1.915-2.958-2.025-.712-1.264-.54-.344Z" transform="translate(100.36 108.46)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#g)">
																   <path d="M587.171,293.572l1.5.651.429,2.541-.737-1.62-1.24-.737.049-.835Zm-10.25-47.886,1.068.847-.062,1.964-1.448-1.424-1.24-.147.258-.8Zm-22.55-35.77,2.148,1.289,5.045,1.326-2.3.061-.638-.319-2.909.921-.552-.737.552-.307-.135-.6-1.436-.675-2.615,2.872.417.7-.491-.159-.27-.958.675-1.9.859-1.068ZM578.394,251.1l.467,1.215.564-2,.553.135-.073,2.934.97.54,1.461-2.161-.442-.405.467-2.222.307.479.147,1.3.466-.749.061,1.448-1.2.712.1,1.289-.773.135,2.025,1.866.638,1.989-.663,5.033.553.835-.442.037-.123,1.817-1.227,1.387.233-.957-1.068-1.5-.2,2.75.516.638-1.019,2.6.233,1.6-.921.516-3.216-7.316-.933-.663-.638.393L573.8,268.2l1.092,1.584-.638.822.049,3.707.749.2.982-1.129.994.908,1.682.123.614.921-.184.97.773.7-.847.2-.454,1.277,1.571,3.572,2.222,3.29,1.338.81.859,2.48.921.491-.282,1.08-.5.221-.491-1.8-1.743-.405-1.227-1.8-.675.209-.577-.712.835,2.136,1.338.933.062.687.626-.884.27.344.295,2.21,1.571,1.424.823,1.792,1.547.54.086,1.35,2.5,3.118.491.049-.062-1.35-.835-1.117.81-.651.761,2.013,3.965,3.057,1.633,4.959-.418,2.958,1.535,2.492-.5,2.185,1.019,3.142-.332,1.08,1.093.552-.614-.8.393-1.007,3.094,4.333L603.3,325.5l1.166.049.663.847,1.829,8.016.786-2.259,1.94-.368-.307.589,1.326,2.615,4.53,5.377,5.622.982,3.008-1.178,2-2.209,1.424.479,1.228-.405-.123.626.736.54.933-.43-.81,1.571.159,2.971,1.78-1.068.454-1.706,1.964-1.6-.11-1.166,1.092.651.651-.528.5,2.013,1.264-.712.933.933-.761.786L635.7,341l-.663,1.166-.54-.2-.515,1.817-3.769,3-.736,3.793-.688.16.16,2.087,2.75.516,1.1-1.448,2.492-.221,2.05.81,1.854,2.6,2-.086.921,1.448-.626,5.192,42.1,51.1,29.2,5.843,73.3,16.891,8.654-9.157L814,458.209l-28.2,89.9L658.872,592.8l-49.052,9.747-71.037,7.746-39.392,19.248L473.723,660.96l-5.978,14.988-1.878,1.141h0l3.179-30.013,9.808-77.936h0l23.937-187.6.024-9.513-1.669-8.47-3.633-7.1-2.05-2.529L484.182,342.7l-11.588-7.734-2.811-2.209-1.375-2.2-.835-4.542-.417-9.084.773-18.5-.466-27.767-.528-6.089-1.338-3.142-4.738-2.553-10.9-3.891-1.792-3.093-7.918-7.463-1.9-.27-5.413,1.24-2.884-.049-3.069-1.461-12.1-8.519-3.695-.994-6.923-.135-2.885-.773-4.984-4.149-4.247-5.18-7.046-1.3-12.779-7.463-1.51-1.878-1.731-6.273-2.6-2.713-4.076-2.848h0l-1.8-2.234-.467-1.878,1.571-5.733,11.232-14.914,15.54-29.056,10.14-10.7,15.283-5.389h0l6.26.16,3.241-1.559,5.02-3.879,38.483,5.045,1.068.822,4.124,6.6.749,3.13.27,5.659,4.333,5.769-.209.859,30.664-.528,1.448,1.4,1.571.577-.049,2.762,1.031.81-.221.417,1.043.184-1.24,3.695,1.191,2.431,1.8,1.264,2.087,2.737-.847.651-.221,3.277.479,1.228,2.516,2.921,4.775,1.289-.945,1.129-1.031-.1.1,1.289,1.952,2.222,1.228.123.061,1.657,2.492,1.547-.442,2.578.565.54-.1.577-1.1.737v-1.093l-.675-.049.356-2.455-.884,1.252-.356-1.731-1.6,5.4.785.1.552-1.534.43,2.922.786-1.166.516.835-.417,1.449,1.313-.381-.393.712-.638.135-.061,1.007-.393.847,1.375.663.921-1.62.773.233-.7,1.277.135,1.583.994-.5-.123-1.277,1.915-.97,1.154.454,1.326,1.927,3.179,1.424,4.493-.074,2.074,1.019.749,1.633,1.375.773.245,2.234-.687-.184.307-.847-.442-.663-.871,1.412-.663-.749-.872.872-.921-.884-.908.577-.381-.552-.847,1.129-1.006-.417-.405.9.5-.258,1.891,1.154.687.872,1.24-.295-.037-1.289,1.67.945-.749.933-.9.086.393,1.129-.822,1.51,2.111-1.338,1.988.663-.012,4.554.823,1.387.663.025.516-.074.651-.282,1.4.7-.589,2.32-.8-.282-.54.5.626.012-.246.687.933-.381.65,1.264.823-2.124.614.43-.184,1.3,1.473-.81,1.792.626-1.093-2.062,1.559-1.608-2.123-1.94-.835.1.8-1.277-.258-.651.908-.577.565,2.418,1.363.074.282,1.841-.908.381,1.191,1.178,1.1-.859.724-3.3,1.191-1.608L557.38,217.1l-.54,1.915-.773,1.178.675,2.406,1.436,1.424,2.946-1.755,1.314.5-.037.111-.6-.012-.111.049-.331.577-.332-.577-2.8,1.424,1.178,1.51,1.829-.442-.2.9-.687.025.024,1.461.872.037.295,1.043.16.245,2.823,2.222.454.135,1.485.9,1.387.454.97.749,3.658.368,2.541,2.713,5.512,6.494.6.552.5.491-.724.11-1.6-2.124-2.062-.528-1.2.835-1.51-2.394-.074-1.08-.577,2.75.184,1.166.994.246-.43,1.338.38,3.265,1.142,2.86,1.731,2.259.123-1.412.381-.381Z" transform="translate(164.38 93.37)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#i)">
																   <path d="M238.577,279.732l1.473-1.682,6.076-.908,7.365-4.382,7.3-.945.994-.8.061-1.007-2.946-5.868.393-3,1.547-1.854,1.056-.233,4.849,1.743,2.983.307.577-.945L269.3,252.4l2.32-5.892.945-.5,1.964.638,4.076-.958,1.068-5.278,5.831-5.929,14.6-11.342,13.883-6.42,3.388-7.107,3.339-3.388,17.8-9.121,5.094-1.645,2.909,1.056,5.45,5.512,7.353,5.057,9.17.663,6.2-7.169h0l2.885,1.142,4.419,3.67,1.645,3.069.184,3.069-.761,1.817-5.131,2.259-3.02,2.59-5.524,7.206-4.922,1.35.528,6.285-1.031,7.046.6,1.866,4.419,4.382,8.212,12.472.172,1.056-1.326,3.572,3.093,5.966.38,3.879-1.031,4.788-6.788-.27-8.838.835-10.188-1.3-3.842,1.007-2.48,2.418-2.8,5.487-3.793,2.136-3.486,3.449-15.811,3.867-4.014,3.093-6.653,8.212-.184,2.823,2.345,4.7-1.043,2.676-2.983,2.2-6.985-1.068-6.96,1.093-7.034-.614-4.75-.994-6.346-3.13L274,314.521l-2.664,1.939-3.732,6.236h0l-5.008-4.149-3.867-13.257-1.719-1.387L250.5,302.2l-2.136-2.467.712-13.9-.344-2.431-1.657-1.424Z" transform="translate(136.72 109.06)" fill="#025635" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#k)">
																   <path d="M190.055,240.982l2.443-2.934,2.872-9.526,3.842-4.591h0l5.45-5.07.356-2.958h0l-.442-.933h0l-3.621-2.259h0l-1.007-.442h0l-4.579-2.651-1.719-4.8h0l-.172-1.129h0l-.479-2h0l-2.75-11.5-1.289-3.081h0l-1.719-2.013-2.909-1.215h0l-.945-.565-1.633-2.983-.921-.38h0L178,179.127h0l-1.6-1.952h0l-3.609-2.786-6.076-1.6h0l-1.89-.295h0l-5.008-.884h0l-2.9-.638-1.387-1.633h0l4.53-5.156,7.7-4.345,12.189-5.377,5.266-.847,16.044-9.329L217.1,137.5l3.732.81h0l5.377,2.332,2.909.454,25.729-5.978,14.939,4.333,5.843-1.657,5.463-.442,17.762,4.309,2.909,2.443,5.156,6.334,3.093,1.792,4.9-.258,5.671-3.044,3.29-.577.823.982,1.08,7.635,1.743,1.792,1.178.381,7.967-.381,7.365,3.83,3.093-1.031,6.2-4.149,1.927-.258,1.94,1.51,1.326,1.989,6.481,11.858,3.413,9.452,2.48,3.044,3,1.768,12.251,1.583,4.026,1.547,1.988,1.166,7.463,6.776,1.264,2.3h0L397,202.155l-2.971,4.076-1.215,3.805-.074,1.952.835,2.308h0l-6.2,7.169-9.17-.663-7.353-5.057-5.45-5.512-2.909-1.056-5.094,1.645-17.8,9.121-3.339,3.388-3.388,7.107L319,236.858,304.4,248.2l-5.831,5.929-1.068,5.278-4.075.958-1.964-.638-.945.5-2.32,5.892,1.007,7.77-.577.945-2.983-.307-4.849-1.743-1.056.233-1.547,1.854-.393,3,2.946,5.868-.061,1.007-.994.8-7.3.945-7.365,4.382-6.076.908-1.473,1.682h0l-14.485-2.836-8.212.184-4.014,1.535-4.751,5.7-2.958,1.449-9.894.479-11.5,3.977-1.9-.037-1.915-.859-1.6-1.829-.773-1.976.626-12.447-2.541-12.042,2.332-13.38.147-5.107-.454-.97-.9-.295-4.014,1.019-.933-.295-1.449-1.841V251.9l1.989-2.774.908-2.958-.295-3.265Z" transform="translate(117.83 95.33)" fill="#025635" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#m)">
																   <path d="M109.429,319.958l1.473,1.031v.81l-1.829-.835Zm-9.747-21.2,1.129.736.516-.258,1.178,3.29h-.859l.1-1.436-2.5-1.363Zm5.6-1.6.8.2.466,1.559.675-.221-.319,1.019-.258-.577-1.534.049.5-1.338-.822-.528.491-.16Zm-.994-1.8,1.1,1.068-1.633-.123Zm-4.444-2.664.687,1.142-1.043.687-.528-.822Zm2.467-.491,1.154,2.872-1.264-1.043Zm.5-1.043.5.54-.319.761-.773-.97.589-.331Zm-17.48-2.688,1.706,1.976-1.9.123-.773-1.24Zm3.216-2.443.822.651-.908.086L86.83,285.1l-.025-2.05.577,2.2,1.289-.773-.123,1.559ZM8.637,180.216l1.08.233.737,1.878-1.252.528-.9-1.878-.552.037-.074,1.007-.737-.135.295-1.1,1.4-.565ZM2.069,177.6l.589.872-1.51.6L.6,178.681l.025.467,1.35,1.031,2.369-.417-.123.994,1.436,1.313-2.922.43-2.05-1.534-.405-2.651Zm58.848-45.885,6.236,1.9h0l3.081,2.369,2.443,2.909h0l1.436,2.013h0l7.648,9.98h0l2.111.786,2.222-.393h0l4.026-1.694,5.389-1.264h0l5.475-1.019h0l6.334.884,5.671,2.21,10.5.884h0l1.927-.7h0l3.167-7.427h0l1.547-1.817h0l1.939-.884h0l3.523-3.535h0l.945-.5,1.927,1.166h0l.565,2.013h0l-.552,8.826.381,5.389h0l.7,3.241h0l.921,2.013h0l4.272,4.358,2.971,1.485h0l4.063,1.35h0l12.447,1.448h0l2.946-2.148.97-.049h0l1.89,1.547h0l.957.577h0l2.013-.381h0l.921.258.97,3.032,1.584,1.326h0l2.124-.11,4.37-2.308h0l4.161-1.215,2.013.6,1.719,1.5h0l1.387,1.62,2.9.638h0l5.008.884h0l1.89.295h0l6.076,1.608,3.609,2.786h0l1.6,1.964h0l2.836.822h0l.921.381,1.633,2.971.957.565h0l2.9,1.215L224.3,188.1h0l1.277,3.081,2.75,11.5h0l.479,2h0l.172,1.129h0l1.719,4.8,4.579,2.639h0l.994.442h0l3.634,2.259h0l.43.933h0l-.356,2.958-5.45,5.07h0l-3.842,4.591-2.86,9.538-2.443,2.934h0l-.687-1.24h0l-1.5-1.755h0l-.945-.528h0l-1.878.1h0l-12.828,2.492h0l-8.065.638h0l-2.774-.491-2.971-2.246h0l-1.866-1.51h0l-2.787-1.755-2.9-.712h0l-2.885-1.019h0l-1.178-3.781-1.571-1.436h0l-.921.049-.516.9.381,1.854h0l.638,1.841-.982.786-7.549-3.081-2.025.4-2.062,1.584h0l-1.89,1.682h0L163.7,236.2l-.921.012h0l-.908-.417-.233-.921.528-2.946h0l-.184-.921h0l-2.786-1.743h0l-1.8-1.277-.184-.97h0l.245-1.952h0l-1.228-1.522-6.6-.282h0l-1.866-.319h0l-.994-.687h0l-1.338-1.9h0l-3.007-5.242h0l-.687-2.062h0l-.982-3.879h0l-1.363-1.866h0l-.945-.381h0l-1.9.565h0l-7.181,4.088-2.124.516-2.971-.381h0l-2.885-2.136h0l-4.861-2.038h0l-1.9.479h0l-.933.417h0l-2.013,1.3h0l-3.953,1.841h0l-1.952.7h0l-2.05.994h0l-1.989.528h0l-3.83.847h0l-1.228,1.8.11.921.994.589h0l3.83-.835h0l1.89.221h0l1.755,1.694h0l1.215,5.156h0l-.994,10.962.847,3.228,1.228,1.94h0l1.964,1.608h0l5.868,1.657h0l1.89,1.166h0l.442,1.448h0l-.319,2.16-3.867,6.8h0l-.2,2.86h0l.822,2.062h0l2.075,3.891.994,3.83h0l.344,1.915,2.394-2.185h0l1.878-.209,4.922,3.093h0l2.737,1.424h0l.9.761.663,2.786h0l.822,2.013,2.664,2.271h0l4.566,4.088,2.21,4.763h0l2.2,7.758h0l.908.38h0l3.891.16h0l1.375,1.719h0l-.307,2.823h0l-.123.908h0l-1.031,5.855h0l.27,12.607h0l.147,3.9h0l-.27,1.915-1.129,1.89-1.976,1.019-3.167.037h0l-2.836-.552-1.9.5h0l-.675.871h0l-1.436,2.222h0l-.97.872h0l-2.9,1.117h0l-6.936.589-1.166,1.8-.675,3.977h0l-1.989-3.56.172-.847-1.031.4-.687-2.013-3.413-1.854.405-.552-.945-2.038,1.031.086-.061-1.522-1.129-.43.516.5-.957.712-1.633-2.185.908-.736,1.657.368-1.092.54,1.473.454.43-1.449,1.412-1.154-.847-1.829,1.633-3.818-1.78-3.474.97-1.792-.724-3.216-5.733-6.408-1.682-4.284.11-1.927-3.314-2.688.049-2.185-1.142-.307-.27-2.541-1.313-1.927-2.59-1.117-2.737-3.9-.479.012.651,1.854-.454.221-.749-.074-.651-1.092-1.1-.074-1.35-1.056.221-1.3-.6.479-.552-.638-.712-2.357-1.178-.368,1.989-3.142.786-2.836-.295-2.381-2.2-1.375-3.4-.2-.344-.209-2.885-2.406-1.338-4.53-3.769-6.125-2.664-7-3.241-5.487-2.48-2.664-1.252-2.431L68.1,237.861l-2.2-5.168-.2-2.124-3.695-4.075-2.369-1.35-.356-.994L55.5,220.368l-.528-2.578,1.24-1.3-.381-1.412-2.823-4.64-3.142-1.608L46.69,205.2l-.221-1.669-2.2-2.946-.491-3.805-1.817-2.32-.466-2.443-1.252-2.283L39.2,188.673l-2.271-.687L36.7,184.9l-3.057-2.455-.8-1.989-4.075-2.111-.233-.712,2.676-.8-.086-.724-2.455-.442-.835-.724-1.6.074-2.087-2.038-.687.27-1.1-.737v.319l.626.994-1.35-.8-2.946,1.62-2.762-.209-.651.5L15,173.771l1.252-.687-2.038.135-.712,1.657-.331-.516.786-1.375-.687.1-.663.9-1.252-.2.54,1.559-1.178.025.54-2.062-1.657-.233.393-.405-.54-1.019-.307.6-.884-.368L6.869,173.8l.724,1.35-.835.172-.872,1.768-.957-1.78.246-1.571-.651-.27-.921.614.012-.97L6.022,170l1.191.442L6.7,169.168l.737-.528.933-3.118-.417-.1L12.012,155.4l1.412-2.124-.331-4.309-.945-2.369,2.357-7.071.037-3.621h0l3.965.2,2.013-.749,3.057.958h0l6.445.847h0l2.357-.147,2.836-2.762h0l4-1.031h0l7.4-1.338h0l2.885.27h0l4.063-.847h0l4.149-.209h0l3.2.626Z" transform="translate(82.5 94.42)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#o)">
																   <path d="M469.337,160.38l-15.295,5.389-10.127,10.7-15.54,29.068-11.232,14.915-1.584,5.733.479,1.866,1.8,2.246h0l-1.6,2.737-2.21,1.056-1.854-.638-2.381-2.6h0l-1.264-2.3-7.463-6.776-1.989-1.166-4.026-1.547-12.251-1.583-3-1.768-2.48-3.044-3.412-9.452-6.481-11.858-1.326-1.989-1.94-1.51-1.927.258-6.2,4.149-3.093,1.031-7.365-3.83-7.967.381-1.178-.381L334.7,187.68l-1.08-7.635-.822-.982-3.29.577-5.671,3.044-4.9.258-3.093-1.792-5.156-6.334-2.909-2.443-17.762-4.309-5.462.442-5.843,1.657-14.939-4.333-25.729,5.978-2.909-.454-5.377-2.332h0l6.9-6.985,1.092-7.439.479-11.49,1.719-3.032,7.562-5.794L280.985,113.6l6.457-5.806-.123-1.927-9.55-7.684-3.842-7.844-2.566-3.069-4.419-1.952-6.69.049-3.965-.8L246.995,77.1l-3.314-1.547-8.6,1.08-22.034-.761L208.5,74.5l-4.885-4.223-2.185-.945-5.377-.1L172.717,65l-9.882.172-8.9,1.669-16.67,1.829-4.2-.049-2.836-.761-2.688-2.332-4.775-8.544L117,52.455l-.712-8.249,3.486-10.238,1.559-7.439h0l43-12.656L177.246,2.617,176.694.15l1.424,1.89,2.86,1.731,22.783,6.653,7.451,3.486,4.2,2.836L324.28,93.663,405.359,149.7l8.593,1.989Z" transform="translate(108.62 64.5)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#q)">
																   <path d="M264.294,608.224l.516.233.221-.11.417.319-.8,1.375-.835-.933Zm-.589-3.953.442.651-.516,1.842-.1-1.179-.6.945-.687-1.436.43-.687Zm-7.954-.6,1.743,1.3-.982-.234Zm-4.272-3.032,2.369.614,1.387,2-3.928-1.265-.8-.932.97-.418Zm11.858-4.6.6.417-1.988.172Zm-11.269-2.222,3.891,2.259,1.8,3.818,2.885.822,1.952-.442,1.215,1.645,1.964-.577-.344-1.1-.54-.086.344.761-1.031.049-.528-.749.638-.712.123-1.571.724.122.221-.773,1.056.123.258,1.755.933,1.252.516-.1.724,1.228,1.117-.258.037.626.577-.074-.1,1.154-.761.8.54,3.645-1.141.135-1.522-1.743-2.222-.454,1.522-.736-.111-1.547-2.21-.54-3.732,1.142-2.418-2.013-1.4-.393-1.461-1.264-1.178-2.332-1.633-.037.565-1.535-1.227-.994-.049-1.3Zm-7.942-.2,2.516,3.744-1.952-.736.258-.405-.921-.847Zm14.644-2.553,1.4.957-.687,1.154.712,1.129,1.694.515-.16,1.068-1.6-.393-.908.724,2.688,2.037.908,1.277-.344.552-.933-1.411-3.707-1.228-2.246-3.2.012-1.326,2.013-.245,1.154-1.608Zm-9.857-.27,1.853,1.35-.847,1.424-1.228.38-1.154-1.423,1.375-1.731Zm4.444-1.056,1.387.8-.577.7-.54-.074Zm6.334.295.516,1.731-2.529-2.553,2.013.822ZM252.6,531.2l5.475-.577,1.547,1.264-1.006,8.924.344,1.841.908.577,3.621-.552,2.639.283,1.326,1.657,1.043,3.547.9.786,10.164.491,1.571,1.522,1.952,4.578,3.99,3.891,1.866.773,1.731-.172.687-.81,2.05-8.286,1.485-2.578,3.523-2.652,2.713-1.08L302.347,546l-.16,5.425,4.419,2.234,3.241,5.7.847.7,3.621.737,2.43,3.695h0l1.191,1.706-2.357,3.585.111,2.909,1.9,1.1v.8l.994.43.319.847-5.512,1.731.516,2.3-1.866,1.682.061,1.78-.761.393-.074,1.081.111.429,1.2-.491,2,.589,1.007,2.013-.675,1.117-2.786-.319.319,3.449-1.841.859-.049.736,1.78,2.32,1.9.417.4,1.019,1.24.589-.27,1.547.589,1.056-1.755.135-.393.994.6,3.192-1.031.184-2.541-1.363-.81.221.54,1.1-1.264,3.364.061,1.3-5.426,1.424-2.05,1.461.516,3.327-2.1.466-5.917-.6-.417-.847.737-2.173-1.485-1.424-1.031-2.713.687-4.37-.4-.356-.577-.737v-1.142l.012-.7-3.081-3.818-1.731-.847-.786.307-.479-1.657-.626-.233-.847-.38,1.314-1.179-.651-1.706.381-2.271-1.682-1.375-2.664-.823-1.633-2.308.282-2.247-.565-.221-1.375-.822-.319.368,1.387,2.394-.81.81.061,2.43-.368,1.166-.221-4.578-1.2-3.916-.651-5.106-.688-.381.81-2.59-.356-2-1.768-.466-1.927-3.032-2.418-1.854-1.2.062-.245-1.829-.97-1.154-8.985-6.4-.8-1.265-1.78-.4-1.522-1.252-1.289-.037.233-.982-4.64-4.272.847-.012-1.24-1.117.319-1.314-.724.97-.6-.4-.147-.675.724-.221-.27-1.24-1.706-.663-.933-2.2h0l2.418-3.867,4.358-2.517Z" transform="translate(137.96 185.32)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#s)">
																   <path d="M419.209,588.142l-21.15-2.9-7.672-.516-7.672-4.947H358.5l-1.706,1.2-2.394.331-3.241-1.706-3.314.147-10.79,1.375L336.044,583l-1.326-1.645L331.1,582l-1.682,2.357-3.2-.884-1.964.528-2.418-.356-.835-1.092-2.479.442-3.179-3.314.086-1.276h0l1.412-3.977-1.6-7.279.822-1.8L317,555.072l-1.694-7.512.1-2.038,1.08-2.075,3.781-4.21,10.078-9.28,1.326-1.817,1.3-3.707,1.166-1.289,7.758-2.48,2.921-1.743,1.5-1.731,1.191-2.725.209-2.6-2.161-10.52.847-1.952,4.726-2.382L352.5,495.3l.049-7.463h0L363,494.7l6.408,2.934,3.977.884,10.864.859,72.24-6.187,20.12-2.934,12.435-3.228h0l-9.808,77.936-3.179,30.013h0l-9.182,5.794-8.629-.442L448.2,586.779l-13.135,1.878Z" transform="translate(154.16 175.4)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#u)">
																   <path d="M269.717,448.287l-2.639,7.881.282.97,2.516,2.418.135,1.952-1.277,1.78-7.144,4.873-1.031,2.2.282,4.358-5.229,10.667h0l-.97.626-5.671.049-1.866.712-2.946,5.475-3.167,11.035-2.713,1.326h-3.167l-1.952-1.289-2.529-3.756-4.517-2.615-1.6-1.89.319-5.438-1.117-4.775.38-6.493-1.191-1.866-4.481-1.35-.822-.859-.221-1.731,4.922-5.033,9.489-2.283,1.682-4.37,3.3-5.683.147-2.885-1.362-5.585.859-2.59,1.669-1.743,2.774-.38,1.755.749,1.485,1.78,1.571,4.64,2.357,1.792,5.659-2.173,5.119.246,6.641-3.376,1.829.27Z" transform="translate(131.9 165.16)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#w)">
																   <path d="M13.788,149.807l.81-3.462.908-.933-.221-1.007,1.277-.749-.589-2.357.994-.565-.295-1.056.749-1.669,46.9,8.335,17.983-14.816,10.864-18.143,31.621-6.334,7.1-16.179,13.9-8.323L103.729,33.793,145.084,21.64h0l-1.559,7.439-3.486,10.238.712,8.249,5.769,4.53,4.775,8.544,2.688,2.332,2.836.761,4.2.049,16.67-1.829,8.9-1.669,9.882-.172L219.8,64.346l5.377.1,2.185.945,4.886,4.223,4.542,1.375,22.034.761,8.6-1.08,3.314,1.547,9.292,7.463,3.965.8,6.69-.049,4.419,1.952,2.566,3.069,3.842,7.844,9.55,7.684.123,1.927-6.457,5.806-33.475,20.684-7.562,5.794-1.719,3.032-.479,11.49-1.093,7.439-6.9,6.985h0l-3.732-.81-15.835,6.788L217.9,179.439l-5.266.847-12.189,5.377-7.7,4.345-4.53,5.156h0l-1.731-1.485-2-.6-4.161,1.227h0l-4.37,2.308-2.124.1h0l-1.6-1.326-.958-3.032-.921-.258h0l-2.013.393h0l-.945-.577h0l-1.89-1.547h0l-.982.049-2.946,2.148h0l-12.435-1.448h0l-4.075-1.35h0l-2.971-1.485-4.26-4.358h0l-.921-2h0l-.7-3.253h0l-.381-5.389.552-8.826h0l-.565-2.013h0l-1.927-1.154-.945.5h0l-3.523,3.535h0l-1.939.884h0l-1.547,1.8h0l-3.167,7.439h0l-1.927.7h0l-10.5-.872-5.671-2.21-6.334-.884h0l-5.487,1.031h0l-5.389,1.264-4.026,1.682h0l-2.222.405-2.111-.786h0l-7.648-9.98h0l-1.436-2.013h0l-2.443-2.909-3.081-2.369h0l-6.236-1.89h0l-3.2-.626h0l-4.149.209h0l-4.063.847h0l-2.885-.27h0l-7.4,1.338h0l-4.014,1.019h0l-2.823,2.774-2.369.147h0l-6.432-.847h0l-3.057-.957L15.862,161l-3.965-.2h0l.479-3.634.786-1.559-.5-2.038,1.007-1.657Z" transform="translate(85.14 69.51)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5"/>
																</g>
																<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#y)">
																   <path d="M318.173,586.39l-2.43-3.695-3.621-.737-.835-.7-3.253-5.684-4.419-2.234.172-5.426-1.228-1.375-2.713,1.08-3.523,2.652-1.485,2.578-2.038,8.286-.7.81-1.719.172-1.866-.774-3.989-3.879-1.952-4.578L281,571.365l-10.164-.491-.9-.785-1.043-3.548-1.326-1.657-2.652-.283-3.609.552-.908-.577-.344-1.841,1.019-8.924-1.559-1.264-5.475.577h0l1.841-7.868-3.646-11.22-2.59-1.473-8.237-.258-1.755-.6-1.264-1.8-.749-3.646.6-12.275,2.418,2.307.921-.074,4.751-3.462,2.75-.466,5.819.54,1.866-.921.933-1.743,1.559-5.647-2.688-5.45,1.608-8.495-.258-1.854-2.271-2.32-2.811-.159-1.841.908h0l5.229-10.667-.282-4.358,1.031-2.2,7.144-4.873,1.277-1.78-.135-1.952L262.75,458.9l-.282-.97,2.639-7.881h0l5.733,11.146,1.718,1.252,2.8.295,2.836-1.387L290.6,450l7.868-3.756,5.229-4.665,5.585-.344,3.879-1.866,2.774-.294,6.4,1.6,7.021-4.628,4.935-1.792h0l7.07,6.15,3.769,6.052,4.419,4.419,1.3,2.123-.1,3.634-4.1,7.476-1.51,7.5.27,1.964,3.376,3.634,4.8,7.807,16.621,14.841h0l-.049,7.463-1.375,1.706-4.726,2.381-.847,1.952,2.16,10.52-.209,2.6-1.191,2.725-1.5,1.731-2.922,1.743-7.758,2.48-1.166,1.289-1.3,3.707L348,541.966l-10.078,9.28-3.781,4.21-1.08,2.074-.1,2.038,1.694,7.513-.945,10.274-.822,1.8,1.6,7.279-1.412,3.977h0l-.135,2.209-2.909-2.823-6.494-4.137-3.02-.417Z" transform="translate(136.51 163.4)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5"/>
																</g>
															 </g>
														  </g>
													   </g>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.dammamfirst.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		 <table class='table borderless m-0'>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>1973</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>2,440</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>2,440</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>169</span></td>
																			 </tr>
																		 </table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=cd2b5a09-6400-4635-a10a-ede581361bed' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">
															 <g transform="translate(895.825 2080.585)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
															 </g>
															 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(1020 2075)' : 'translate(932 2075)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																 <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.dammamfirst.city.name.text"/></tspan>
																 <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
															 </text>
														 </a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.dammamsecond.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		 <table class='table borderless m-0'>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>1978</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>25,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>25,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>1,049</span></td>
																			 </tr>
																		 </table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=39f03900-6255-435f-b994-9c728d4005f3' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">
														   <g transform="translate(900.825 2135.448)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
														   </g>
														   <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(1029 2133)' : 'translate(939 2133)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.dammamsecond.city.name.text"/> </tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														   </text>
													   </a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.oasis.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.alahsa.city.name.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		<table class='table borderless m-0'>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>2013</span></td>
																			</tr>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>2,000</span></td>
																			</tr>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>543</span></td>
																			</tr>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>14</span></td>
																			</tr>
																		</table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/Oasis/Pages/Oasis.aspx?OasisId=6a2a3514-1320-443c-8611-3337250b829a' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/> </a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														   <g transform="translate(1020 2193.149)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
														   </g>
														   <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(1137 2191)' : 'translate(1057 2191)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.oasis.city.name.text"/> </tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.alahsa.city.name.text"/></tspan>
														   </text>
														</a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.hafralbatin.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		 <table class='table borderless m-0'>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>2011</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>100,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>1,500</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/> </span></td>
																				 <td class='text-left'><span class='INL_table_number'>11</span></td>
																			 </tr>
																		 </table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=b71f9764-eb8c-453d-bc8e-0757ed0857bc' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

													   <g transform="translate(811 2005.149)">
														  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
													   </g>
													   <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(940 2003.149)' : 'translate(848 2003)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
														  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.hafralbatin.city.name.text"/> </tspan>
														  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
													   </text>
													   </a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.waadalshamal.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		 <table class='table borderless m-0'>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>2012</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>290,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>200</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>5</span></td>
																			 </tr>
																		 </table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=fa82b44b-f37b-4fd8-bd7e-75964732920e' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														   <g transform="translate(488 1778.149)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
														   </g>
														   <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(615 1778.149)' : 'translate(525 1776)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.waadalshamal.city.name.text"/></tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
														   </text>
													   </a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.oasis.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		<table class='table borderless m-0'>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>2003</span></td>
																			</tr>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>3,000</span></td>
																			</tr>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>979</span></td>
																			</tr>
																			<tr>
																				<td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				<td class='text-left'><span class='INL_table_number'>-</span></td>
																			</tr>
																		</table>

																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/Oasis/Pages/Oasis.aspx?OasisId=b02c2fda-e934-4502-bb0f-6816e78b11ee' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														   <g transform="translate(380 1841.149)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
														   </g>
														   <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(474 1845.149)' : 'translate(417 1839)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.oasis.city.name.text"/> </tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.aljouf.city.name.text"/> </tspan>
														   </text>
													   </a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.arr.industrial.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		 <table class='table borderless m-0'>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/> </span></td>
																				 <td class='text-left'><span class='INL_table_number'>2009</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>2,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>1,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>9</span></td>
																			 </tr>
																		 </table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=1ef6545a-57c6-44eb-905b-5767f0dc4d96' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														   <g transform="translate(583 1843.149)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
														   </g>
														   <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(745 1848.149)' : 'translate(619 1848)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.arrindustrial.city.name.text"/></tspan>
														   </text>
													   </a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.dammamthird.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		 <table class='table borderless m-0'>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>2012</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>48,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>10,000</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				 <td class='text-center'><span class='INL_table_number'>176</span></td>
																			 </tr>
																		 </table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=f00da909-20dc-44ed-94f9-d7f645c7a6c0' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														   <g transform="translate(2.901 7.597)">
															  <g transform="translate(939 2183.403)">
																 <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
															  </g>
															  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(925.174 2181.403)' : 'translate(825.174 2181.403)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																 <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.dammamthird.city.name.text"/> </tspan>
																 <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
															  </text>
														   </g>
													   </a>
													   <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														 <div class='container py-3 px-0'>
															 <div class='w-100'>
																 <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.alahsa.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																 <div class='p-2'>
																	 <div class='INL_table'>
																		 <table class='table borderless m-0'>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/> </span></td>
																				 <td class='text-left'><span class='INL_table_number'>1981</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/> </span></td>
																				 <td class='text-left'><span class='INL_table_number'>1,500</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>1,500</span></td>
																			 </tr>
																			 <tr>
																				 <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				 <td class='text-left'><span class='INL_table_number'>144</span></td>
																			 </tr>
																		 </table>
																	 </div>
																 </div>
																 <div class='text-center w-100 pb-2'>
																	 <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=4fc60b7a-1fd9-41cb-b3b5-9e8dd4a8f678' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																 </div>
															 </div>
														 </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														   <g transform="translate(-0.173 10.162)">
															  <g transform="translate(927.5 2231.838)">
																 <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2116.013 478.319)" fill="#00a6be"/>
															  </g>
															  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(911.174 2229.838)' : 'translate(811.174 2229.838)'}" fill="#707070" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																 <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.alahsa.city.name.text"/> </tspan>
																 <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
															  </text>
														   </g>
													   </a>
													</g>
												 </svg>
											</div>
										</div>
									</div>
								</div>
								<div id="Middle" class="tab-pane fade INDS_margin_leftn40" role="tabpanel">
									 <div class="row">
										<div class="col-md-12">
											<div class="w-tab2 h-100 w-100">
												<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="980.047" height="902.046" viewBox="0 0 1116.047 902.046">
													<defs>
														  <filter id="a" x="316.397" y="230.063" width="433.521" height="545.471" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="b" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="b" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="c" x="176.301" y="396.77" width="377.384" height="429.818" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="d" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="d" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="e" x="96.379" y="236.531" width="390.752" height="393.375" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="f" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="f" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="g" x="441.747" y="155.087" width="619.399" height="716.347" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="h" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="h" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="i" x="292.344" y="239.747" width="310.948" height="292.889" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="j" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="j" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="k" x="190.533" y="165.696" width="411.007" height="334.331" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="l" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="l" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="m" x="0" y="160.756" width="405.59" height="375.815" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="n" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="n" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="o" x="142.154" y="0" width="518.387" height="399.363" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="p" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="p" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="q" x="299.232" y="651.184" width="240.463" height="250.863" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="r" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="r" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="s" x="386.638" y="597.6" width="339.362" height="279.331" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="t" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="t" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="u" x="266.523" y="542.39" width="218.143" height="228.124" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="v" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="v" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="w" x="14.258" y="26.296" width="464.837" height="340.682" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="x" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="x" />
																<feComposite in="SourceGraphic" />
														  </filter>
														  <filter id="y" x="291.405" y="532.86" width="298.063" height="324.219" filterUnits="userSpaceOnUse">
																<feOffset dy="18" input="SourceAlpha" />
																<feGaussianBlur stdDeviation="27.5" result="z" />
																<feFlood flood-opacity="0.161" />
																<feComposite operator="in" in2="z" />
																<feComposite in="SourceGraphic" />
														  </filter>
													</defs>
													<g transform="translate(-129.225 -1677.866)">
														  <g transform="translate(0 10)">
																<g transform="translate(170.002 1674.398)">
																	  <g transform="translate(-0.001 0)">
																			<path d="M419.9,883.276l-.5-1.016.882-2.605-1.78-1.711-1.238-3.253.825-5.247-.489-.425-.692-.886v-1.367l.017-.842-3.7-4.58-2.076-1.015-.942.368-.576-1.99-.752-.279-1.015-.457,1.573-1.412-.781-2.051.458-2.723-2.02-1.651-3.2-.987-1.959-2.767.339-2.7-.679-.263-1.647-.987-.384.441,1.663,2.872-.972.971.073,2.917-.442,1.4-.263-5.494-1.444-4.7-.78-6.125-.826-.457.971-3.107-.429-2.4-2.12-.558-2.311-3.641-2.906-2.221-1.443.073-.292-2.2-1.165-1.384-10.785-7.675-.959-1.517L373,809.138l-1.83-1.505-1.546-.041.279-1.181-5.567-5.126,1.015-.012-1.484-1.343.381-1.574-.87,1.161-.721-.485-.178-.809.869-.267-.323-1.489-2.048-.793-1.121-2.638,2.9-4.641,5.232-3.018.329-.71-.329.634-5.232,3.022-2.9,4.64-.586-2.755-.4-.485-1.4-1.812.044-.72-.47-.842-.547-.546-.752,1.121-.529-.514.529-1.7-.676-.356-.708-.955.223-1.282-1.076-1.283-.8-3.006.073-1.177-.247-2.076-1.21-1.177.223-1.784-2.08-1.562-.926-1.914-3.33-.134-1.563-1.222-.279-2.387-2.565-3.2,1.83-7.205-2.3-.591-1.7-1.351-1.768-4.021,1.9-4.422-.295-2.488-4.5-1.914-1.636-3.876.562-2.225-.663-2.12-1.21-.854-.825-2.007-2.326-1.193.44-.631-.708-2.99.74-4.082-1.8-1.93-3.343-.825-4.111-3.107-.412-1.5,1.534,1.368.5-3.091-1.664-4.628-2.1-2.415-3.035.841-1.489-.473.264,1.149,1.634.769-.44.409-1.987-1.234-.636-2.209-2.091-1.048-.442-2.945-1.917-.854.618-1.857-.6-1.651-8.386-5.761-4.39-1.4-1.826-2.99-.958-.073.206-1.841-3.375.1-2.137-2.237-.753-2.181-2.428-.87-2.785.384.089.752-1.107.19-3.092-2.3-1.68.3,2.255,1.133.412,1.092-3.937-1.873-7.588-5.729-2.593-2.873.857-.8-1.578-1.1.268-1.578-1.417.457-1.032-1-1.489-1.946.34-.546,1.474,1-.587-1.724-1.461.133-2.178-2.917-2.42-4.45.77-1.133-.251-1.092-2.137-1.323-.223.943.886,1.428-.857-.324-1.178-3.2,1.311.631.5-.3-.779-.631L252,648l-.987-.178-.813.279-3.019-2.533.368-1.93-1.238-1.133-1.9-.105-1.824-2.856.235-1.768-1.947-3.463-2.444-1.473-1.312-6.247-1.222-1.032-1.873-3.681.161-1.149,1.283-.574,1.9-1.327,1.033-2.751-1.8-1.505.19-1.206-.384-1.092,1.326-.146-1.355-1.974.368-.574h-.87l-.353.425-1.148-.983-1.372-8.945-2.107-4.6-4.771-6.23-.562-2.739.578-.692-.752-2.124.866-1.145,2.019-.417-1.5,2.771.766.943.68-1.295,1.165.251.292-4.054,1.8.15-.353-1.266.826-3.257-.5-1.016-.826,4.2-.854-.38,1.178-2.872-.47-2.169,3.314-6.17-.029-2.5.648-1.06,1.239-.267.468-2.031-1.72-.016-.223,1.355-.485-.279-1.063-.842.708-3.152-.146-5.3-2.771-4.641-.576-2.593-1.723-2.1,1.314-1.327-1.371-.1-.636.453-6.289-10.5,1.915.724,2.019,3.3-.279.7,1.753.307.748-1.06-2.327-3.313-.072-2.2h-3.375l.473-.781-1.313.146-.382.518-2.12-3.224,1.089-2.432-1.076-5.6-.368-1.121-1,1-2.477-3.609-.134-1.974-.485-.575-.34-.223-1.445-3.285-.1-3.827,7.542-.591,4.537-1.355,1.209.5,3.14,5.037,3.565,2.727,5.039,6.008,2.477,1.918,2.281.336,7.192-1.635,2.326.546.635,5.684,1.032.858,15.381-4.79,7.014-.457,1.578,2.051.412,4.786-.266.7.295-.768-.413-4.79-1.578-2.047-7.014.457-15.382,4.786-1.031-.854-.632-5.684-2.33-.546-7.191,1.635-2.282-.34-2.459-1.914-5.039-6.008-3.565-2.711-3.127-5.041-1.207-.5-4.536,1.355-7.547.587-.308.044-.178.028-.033-.168-.039.006.039-.006-.91-4.6-1.942-1.946.308-1.545-1.428.587-.96-.514-.19,1.1,1.239.591-.547.587-1.267-.736.146-2.225-.839-1.987.19-.765-1.148-.591-1.032-1.21-1.4-4.106-.013-2.4-1.506-1.651-1.355-.133-1.282-2.3-3.55-2.427-.854-1.517-.65-.413-1.323-.9-.485.9-1.032-.429-.489-.647-1.618-1.473.558-1.327-1.473.6L185,472.638l-2.678-1.752.234-.457-.915-.5-1.663.68-5.365-4.183.913-.752-.869-.429.854-2.472-2.049,1.117.664.563-.647,1.869L171,466.04l-4.051-3.034L165.2,459.9l-1.165.235-.558-1.048-1.017-.206-.133-1.222-2.286.886-1.529-.53-.118,1.194.794.235-.547,1.339-2.222.073-.975-1.44-1.725.129-2.68-3.989-2.2-1.578.457-2.682.987.679.381-1.651-1.736-2.164-.619-2.136-1.479-1.252-.01.059-2.387-4.272.21-1.015-1.238.485-.825-2.415-4.1-2.225.489-.664-1.137-2.444,1.238.1L139,430.555l-1.355-.518.62.6-1.15.858-1.959-2.622,1.089-.886,1.991.441-1.312.647,1.769.546.515-1.736,1.7-1.388-1.02-2.193,1.962-4.584-2.136-4.167,1.161-2.152-.866-3.86-6.883-7.687-2.017-5.142.13-2.314-3.977-3.224.06-2.622-1.371-.368-.324-3.051-1.578-2.31-3.107-1.343-3.286-4.685-.575.016.781,2.225-.547.263-.9-.089-.782-1.311-1.326-.089-1.618-1.266.263-1.562-.721.574-.664-.765-.854-2.828-1.411-.441,2.384-3.771.942-3.4-.352-2.861-2.64-1.651-4.078-.234-.413-.251-3.463-2.885-1.607-5.438-4.523-7.351-3.2-8.4L88.464,328.7l-2.979-3.2-1.5-2.917-2.577-1.428-2.639-6.2-.234-2.549L74.1,307.52,71.253,305.9l-.425-1.193-4.541-4.54-.631-3.091,1.489-1.562-.458-1.7-3.39-5.567-3.772-1.93-3.815-4.361-.267-2-2.636-3.536-.591-4.568-2.18-2.783-.56-2.933L47.972,263.4l-1.25-1.266L44,261.31l-.279-3.7-3.671-2.945-.955-2.387L34.2,249.747l-.279-.854,3.209-.959-.1-.87-2.946-.53-1-.87-1.915.089-2.5-2.444-.826.324-1.327-.886v.384l.753,1.194-1.622-.959L22.1,245.313l-3.315-.251-.784.6-.336-1.412,1.5-.825-2.444.162-.857,1.986-.4-.619.942-1.647-.825.117-.793,1.076-1.506-.239.647,1.873-1.412.028.647-2.476-1.986-.279.47-.486-.648-1.222-.368.72-1.06-.441-1.667,2.3L8.78,245.9l-1,.206L6.733,248.23l-1.15-2.136.3-1.885-.781-.324-1.105.737L4,243.456l2.89-3.726,1.428.53L7.7,238.726l.886-.631,1.118-3.742-.5-.117L14.086,222.2l1.7-2.549-.4-5.171-1.134-2.844,2.829-8.484.044-4.345.576-4.361.942-1.869-.6-2.448,1.205-1.987.15-2.533.972-4.155,1.088-1.121-.263-1.209,1.529-.9-.7-2.828,1.192-.676-.355-1.266.9-2.007,56.3,10.005,21.586-17.781L114.674,141.9l37.957-7.6,8.519-19.416,16.676-9.989L127.349,46.388,176.6,31.917l.054-.263,51.616-15.188,15.5-13.509L243.106,0l1.712,2.266,3.432,2.079L275.6,12.327l8.943,4.183,5.037,3.4,130.68,92.3,97.321,67.248,10.311,2.387,66.484,10.43-18.36,6.465-12.156,12.845L545.2,246.474l-13.479,17.9-1.9,6.882.574,2.237,2.165,2.695-1.915,3.285L528,280.737l-1.94-.667.047.052,2.222.765,2.655-1.266,1.888-3.24-2.094-2.594-.56-2.253,1.886-6.878,13.483-17.9,18.652-34.87,12.172-12.845,18.343-6.465,7.516.19,3.888-1.869,6.026-4.657,46.192,6.057,1.282.987,4.95,7.926.9,3.755.326,6.793,5.2,6.922-.251,1.032,36.8-.635,1.739,1.679,1.886.692-.06,3.317,1.237.971-.263.5,1.25.219-1.485,4.434,1.43,2.917,2.165,1.517,2.5,3.285-1.015.781-.269,3.932.575,1.473,3.02,3.508,5.734,1.545-1.138,1.355-1.235-.117.118,1.549,2.344,2.666,1.473.146.072,1.99,2.99,1.853-.529,3.095.68.647-.118.692-1.326.886V263.3l-.813-.061.428-2.945-1.06,1.5-.425-2.075-1.918,6.481.942.117.664-1.841.514,3.508.943-1.4.618,1-.5,1.74,1.573-.457-.468.854-.77.162-.073,1.21-.469,1.015,1.646.8,1.105-1.946.93.279-.841,1.533.161,1.9,1.194-.607-.146-1.529,2.3-1.165,1.384.546,1.591,2.31,3.819,1.711,5.391-.089,2.489,1.222.9,1.958,1.65.93.3,2.678-.826-.219.369-1.015-.53-.8-1.044,1.7-.8-.9-1.047,1.044-1.1-1.06-1.093.692-.458-.663-1.015,1.355-1.21-.5-.486,1.076.6-.307,2.271,1.384.824,1.048,1.489-.356-.044-1.546,2,1.133-.9,1.121-1.077.1.474,1.355-.987,1.813,2.533-1.606,2.388.8-.017,5.466.988,1.663.8.033.618-.089.782-.34,1.68.842-.71,2.783-.958-.34-.647.6.752.016-.295.825,1.121-.457.781,1.517.987-2.549.735.514-.223,1.562,1.77-.971,2.152.752-1.314-2.476,1.873-1.93-2.549-2.326-1,.117.956-1.533-.31-.781,1.089-.692.681,2.9,1.635.089.339,2.213-1.091.453,1.428,1.416,1.326-1.032.871-3.961,1.429-1.93-1.457,4.345-.652,2.3L765.4,298.7l.81,2.888,1.724,1.707,3.536-2.1,1.578.6-.044.134-.721-.016-.134.061-.4.692-.4-.692L768,303.677l1.412,1.813,2.2-.53-.238,1.076-.821.028.027,1.752,1.049.045.352,1.254.19.291,3.392,2.666.542.162,1.784,1.076,1.667.546,1.161.9,4.392.441,3.051,3.257,6.617,7.792.72.663.606.591-.873.129-1.915-2.549-2.477-.631-1.44,1-1.812-2.876-.09-1.295-.69,3.3.218,1.4,1.193.292-.513,1.606.456,3.92,1.368,3.431,2.08,2.711.146-1.7.458-.453v0l.558,1.46.68-2.4.66.162-.086,3.524,1.162.647,1.757-2.593-.534-.486.562-2.666.368.574.179,1.562.559-.9.072,1.736-1.44.858.117,1.545-.931.162,2.432,2.237.765,2.387-.792,6.04.663,1-.534.044-.146,2.177-1.473,1.667.279-1.149-1.283-1.8-.235,3.3.619.769-1.221,3.123.278,1.914-1.1.619-3.861-8.779-1.121-.8-.765.473-1.694,4.111,1.311,1.9-.765.987.06,4.45.9.235,1.181-1.355,1.194,1.088,2.02.15.736,1.1-.222,1.165.93.837-1.02.235-.546,1.533,1.89,4.288,2.667,3.945,1.605.975,1.032,2.974,1.1.591-.336,1.295-.606.267-.587-2.165-2.093-.49-1.473-2.165-.812.251-.693-.854,1,2.561,1.606,1.121.073.825L797.6,381l.324.413.352,2.65,1.886,1.711.986,2.148,1.858.647.1,1.622,3.006,3.742.591.057-.072-1.618-1-1.339.975-.781.911,2.415,4.76,3.666,1.962,5.951-.5,3.552,1.842,2.99-.607,2.621,1.227,3.771-.4,1.3,1.311.664-.736-.959.473-1.21,3.715,5.2,1.547,4.859,1.4.061.792,1.015,2.2,9.621.944-2.711,2.326-.445-.369.708,1.591,3.139,5.438,6.449,6.746,1.181,3.614-1.416,2.4-2.65,1.709.575,1.477-.486-.15.749.883.651,1.121-.518-.972,1.885.19,3.564,2.137-1.279.547-2.047,2.358-1.917-.134-1.4,1.312.781.781-.631.6,2.415,1.519-.854,1.121,1.117-.915.943-1.78.105-.8,1.4-.646-.235-.619,2.177-4.524,3.6-.883,4.552-.824.19.188,2.5,3.3.619,1.324-1.736,2.99-.267,2.464.971,2.223,3.123,2.4-.1,1.105,1.736-.752,6.234,50.538,61.321,35.052,7.015,87.982,20.269,10.384-10.988L1075,584.329l-33.847,107.887L888.8,745.838l-58.879,11.7-85.267,9.3-47.281,23.1-30.812,37.71-7.175,17.987-2.254,1.367,3.816-36.015,11.771-93.525,28.731-225.133.032-11.417-2-10.163-.023-.047,2,10.1-.029,11.417-28.73,225.149-14.925,3.872,14.925-3.872L660.92,810.89,657.1,846.9l-11.023,6.955-10.359-.53-12.051-16.264-15.765,2.253L588.87,838.7l-25.39-3.475-9.209-.619-9.207-5.935H515.993l-2.047,1.444-2.874.4-3.893-2.047-3.978.174-12.949,1.651-1.21,2.241-1.591-1.974-4.347.781-2.019,2.828-3.845-1.06-2.358.631-2.9-.425-1-1.311-2.974.53-3.809-3.97-.068,1.109-3.491-3.386-7.795-4.964-3.625-.5-2.806,1.368.021.032,1.43,2.047-2.829,4.3.134,3.492,2.282,1.327v.959l1.2.514.381,1.016-6.616,2.079.619,2.755-2.237,2.015.072,2.14-.915.469-.089,1.295.133.518,1.445-.591,2.4.708,1.21,2.415-.81,1.339-3.347-.38.386,4.139-2.21,1.032-.061.882,2.137,2.783,2.282.5.489,1.222,1.484.708-.322,1.857.708,1.266-2.108.162-.47,1.193.721,3.831-1.235.218-3.051-1.635-.975.267.65,1.323-1.521,4.038.076,1.562-6.515,1.711-2.461,1.752.619,3.993L427,884Zm44.173-69.765,1.914,8.735Zm-25.919,2.549,1.017.842,4.334.88-.017-.026-4.347-.886-1-.837-.573-1Zm-27.348.089,2.239.927,2.08-.206.824-.971,2.461-9.944,1.78-3.091,4.232-3.184,3.252-1.294-.014-.017-3.254,1.295-4.229,3.184-1.785,3.095-2.444,9.94-.841.975-2.063.206-2.239-.93-4.787-4.653Zm18.154-9.6,4.793,2.423-4.793-2.425Zm-39.37-2.974,12.2.587-12.2-.591-1.076-.943Zm39.548-3.55v0l-.84-.939ZM464.159,790l2.031,9.014Zm-86.008,6.7h0l-1.093-.691Zm1.088-.166,3.253-.5h-.007Zm-2.588-2.735.411,2.209-.411-2.211Zm1.205-10.713-1.172,10.416,1.188-10.416L376,781.574Zm86.421,4.462L464.159,790l.118-2.444,1.294-2.492Zm-94.759-5.293,6.484-.687-6.483.682Zm-19.742-32.077.9,4.373,1.5,2.181,2.125.708,9.887.312,3.107,1.78,4.341,13.31.006-.024-4.375-13.464-3.111-1.764-9.887-.312-2.1-.72-1.518-2.169-.87-4.215Zm153.029.1-1.43,3.273,1.43-3.269.25-3.123Zm-2.343-15.746,2.593,12.627Zm20.937-7.97,7.693,3.524,4.774,1.06,13.039,1.032-13.039-1.032-4.774-1.06-7.693-3.524-12.537-8.233ZM328.472,714.828l1.913,2.27,5.439,3.14,3.034,4.507,2.343,1.545h3.816l3.254-1.59,3.8-13.229,3.538-6.57,2.237-.87,6.795-.057,1.161-.736,2.226-1.092,3.375.194,2.7,2.736-.014-.1-2.727-2.787-3.375-.19-2.21,1.088,6.275-12.8-6.275,12.8-1.166.752-6.807.061-2.237.854-3.537,6.57-3.8,13.242-3.254,1.59h-3.8l-2.343-1.546-3.035-4.507-5.422-3.14-1.893-2.239Zm44.039,2.711,3.171,6.432.01-.036-3.173-6.436ZM320.176,689.89l.987,1.032,5.39,1.606,1.4,2.2,0-.076-1.429-2.237-5.378-1.622-.987-1.031Zm158.6-5.45.324,2.354,4.051,4.361-4.051-4.361Zm-105.823-2.019-1.238,2.638ZM383.06,674.439Zm-3.184-5.247,3.022,2.9-3.019-2.9-.341-1.161Zm-.337-1.161,3.151-9.4Zm-37.807-11.239,1.617,6.615v-.032l-1.611-6.606Zm12.123,2.828,0,0-.968-2.849Zm120.38-12.627,4.525,7.262,5.3,5.3-5.3-5.3-4.525-7.262-8.48-7.375Zm-20.965-52.016.133,1.682-.132-1.685Zm1.8-27.794L457.7,586.47l0-.005-2.638-19.29Zm.96-11.4.453,2.31-.453-2.31L446.52,553.7l-8.323,2.164-5.451-1.885,5.451,1.885,8.323-2.164ZM273.291,538.353l.089,1.266.866,1.092,5.867,1.736,3.079,2.816.664,3.4-1.226,5.7.959,2.326,7.012.825,3.437-1.651,6.818-6.036,10.008,3.253,2.387-.028,6.584-9.811L333.6,532.758l7.544-7.841,3.609-2.605,8.355-3.4.826-1.121-.016-.059-.781,1.062-8.356,3.4-3.61,2.605-7.543,7.853-13.763,10.491-6.587,9.807-2.385.032-10-3.257-6.823,6.04-3.435,1.651-7.014-.825-.955-2.326,1.223-5.688-.663-3.4-3.08-2.812-5.863-1.74-.871-1.088-.088-1.258Zm157.6,12.226h0l.026-.515Zm-38.383-36.1,7.588,16.587,4.435,2.4,20.776.931,4.553,1.25,2.711,2.771-2.711-2.771-4.553-1.25-20.776-.931-4.435-2.4Zm-24.268-21.7-1.562,2.254-17.312,8.6-5.292,4.228.011.089,5.253-4.2,17.312-8.589,1.563-2.253,1.015-3.253v-.037Zm11.509-10.2.53,4.612,6.115,4.859,1.663,7.161,2.488,5.561-2.474-5.561-1.663-7.161-6.13-4.859Zm-11.509-4.948-.854,3.415,0,.008.823-3.276,4.771-1.517,3.839.5L373,476.118ZM380,435.709l5.952,4.933.013-.1-6.007-4.974Zm37.617.882,8.353-1.311-8.355,1.311Zm18.754-11.518,2.815,5.644ZM362.9,413.126l2.565,2.978,7.807,2.047,2.08,1.663L378,428.88l-2.692-9.229-2.063-1.663-7.822-2.047-2.517-2.9Zm296.115-53.986.559,33.333-.926,22.2.513,10.919.016.083-.5-10.882.931-22.2-.562-33.325-.631-7.307-.026-.062ZM276.739,396.052l.926,2.371,1.915,2.2,2.3,1.044,2.286.044,13.808-4.77,11.873-.574,3.553-1.74,5.7-6.833,4.816-1.845,9.845-.218,17.385,3.4,10.2,2.694,1.979,1.7-.021-.143-1.985-1.707-10.2-2.7-17.385-3.4-9.856.222-4.821,1.841-5.7,6.837-3.553,1.736-11.873.574-13.808,4.774-2.281-.044-2.3-1.032-1.919-2.193-.881-2.258ZM156.965,366.207l.987,2.416,3.2,2.727,5.479,4.907,2.65,5.713,2.638,9.313,1.093.453,4.671.194,1.613,2.011,0-.053-1.647-2.063-4.674-.19-1.088-.457-2.638-9.309-2.652-5.717-5.482-4.9-3.2-2.727-.968-2.367Zm359.146,3.2,3.714,7.161.457,4.657-.457-4.657Zm-140.594.87,3.536,7.044ZM388,356.178,389.2,365.5Zm113.614-14.791.725,2.241,5.3,5.255,9.854,14.969.205,1.266-.205-1.266-9.854-14.969-5.3-5.255ZM151.806,360.244l3.287,1.707,1.071.911-.024-.1-1.076-.9-3.286-1.7-5.908-3.71-2.258.251-2.856,2.606.016.088,2.873-2.622,2.254-.251Zm-15.13-12.4,2.47,4.634-.01-.038-2.479-4.644Zm140.192-4.523.5,1.091,0-.059-.542-1.165-1.078-.352-4.819,1.222-1.116-.352-1.712-2.173v.094l1.739,2.213,1.134.352,4.819-1.238ZM502.215,325.39l.636,7.541Zm-374.94-3.6,1.474,2.331,2.358,1.93,7.042,1.986,2.258,1.392-.02-.065-2.271-1.4-7.041-1.987-2.36-1.93-1.45-2.294Zm142.966,4.333.961,2.2,0-.019-.978-2.24-.009.01Zm-31.091-.385,9.683-.765L264.227,322l2.258-.1,1.094.61,0-.007-1.134-.631-2.254.117-15.4,2.99-9.651.762Zm-45.469-8.2,0,.019.632-3.51-.007-.031Zm21.6-7.278-.618,1.076.457,2.225.758,2.192.035-.028-.765-2.209-.458-2.209.6-1.049Zm-26.922-2.148.006.03.293-2.328-.008-.01Zm329.593-12.756,5.3,4.4,1.975,3.686.22,3.682-.22-3.682-1.975-3.686-5.3-4.4-3.464-1.372ZM226.7,238.33l2.063,1.8,1.663,1.946,3.477.764,6.014,1.06,2.267.356,7.3,1.93,4.33,3.342,1.917,2.359,3.4.987,1.1.457,1.959,3.564,1.15.676,3.479,1.46,2.076,2.415,1.534,3.7,3.3,13.8.575,2.4.206,1.355,2.063,5.761,5.5,3.168,1.194.53,4.362,2.71.515,1.117-.418,3.483,0,0,.428-3.552-.529-1.121-4.35-2.711-1.207-.53-5.5-3.18-2.063-5.761-.206-1.355-.575-2.4-3.3-13.8-1.55-3.7-2.061-2.415-3.491-1.461-1.138-.676-1.959-3.581-1.1-.457-3.4-.987-1.917-2.342-4.33-3.342-7.3-1.914-2.267-.356-6.014-1.06L230.428,242l-1.663-1.962-2.08-1.78-2.4-.724-5,1.473-5.243,2.771-2.55.117-1.913-1.59-1.15-3.637-1.108-.312-2.412.473-1.138-.7L201.5,234.28l-1.178.057-3.538,2.577-14.924-1.736-4.893-1.622-3.565-1.78-5.114-5.231-1.105-2.4-.837-3.9-.457-6.469.663-10.592-.681-2.415-2.31-1.384-1.137.6-4.229,4.244-2.328,1.06-1.857,2.165-3.8,8.929-2.315.837-12.6-1.044-6.806-2.654-7.6-1.06-6.588,1.238-6.467,1.517-4.832,2.019-2.667.486-2.536-.943L88.625,204.8,86.9,202.388l-2.93-3.492-3.7-2.844-7.486-2.27,7.486,2.286,3.7,2.84L86.9,202.4l1.724,2.415L97.8,216.794l2.536.943,2.667-.473,4.832-2.031,6.467-1.517,6.575-1.222,7.6,1.06,6.808,2.65,12.6,1.06,2.311-.837,3.8-8.913,1.854-2.181,2.33-1.06,4.229-4.244,1.133-.6,2.315,1.4.676,2.416-.66,10.592.453,6.465.843,3.892,1.1,2.416,5.127,5.227,3.565,1.784,4.876,1.618,14.94,1.74,3.538-2.577,1.165-.061,2.267,1.857,1.152.692,2.415-.457,1.105.312,1.165,3.637,1.9,1.59,2.55-.129,5.243-2.771,5-1.456ZM123.883,296.548l2.095,2.017-.014-.059-2.108-2.035-2.271-.263-4.6,1.016-1.167-.692.006.044,1.19.708,4.6-1Zm389.605-5.336h0l.09-2.339Zm29.484-.676.03.037-2.05-7.433-.011-.011Zm-52.129-29.578,14.7,1.9,4.594,1.765-.09-.053-4.832-1.857-14.605-1.888Zm-35.834-29.02,3.711-1.238,7.442-4.98,2.193-.292-.211-.165-2.315.312-7.441,4.976-3.684,1.229Zm-19.819-4.6,1.415.457,9.521-.455-.29-.151-9.562.457-1.146-.37Zm-21.012-7.836,5.88-.312,6.807-3.653,3.77-.66-.151-.181-3.949.7-6.808,3.653-5.812.3Zm-13.61-12.736-.115-.1L379.135,201.5l-6.559.53-7.014,1.99-17.931-5.2L316.748,206l-3.492-.546-6.118-2.655,0,.005,6.455,2.8,3.491.546,30.886-7.173,17.93,5.2,7.017-1.986,6.556-.53ZM35.7,200.308l2.83-.174-2.834.173Zm6.221-3.5-3.391,3.33,3.406-3.318,4.8-1.234Zm314.822-92.258,0-.005-5.3-2.342-8.029.061-4.564-.92.138.111,4.758.959,8.028-.061ZM281.614,89.37l5.452,1.651,26.447.915,10.316-1.3-.315-.147-10.331,1.294-26.445-.914L281.533,89.3Zm-14.939-6.319,6.454.117,2.3,1-.015-.012L272.8,83.022l-6.454-.117L238.336,77.82l-11.865.206-10.679,2.007L195.78,82.225l-5.038-.057-3.166-.851.095.082,3.4.914,5.042.061,20.011-2.2,10.68-2,11.861-.207ZM382.9,875.763l.574-1.06.62.279.263-.129.5.38-.959,1.651Zm4.565-3.945-2.667-.546,1.828-.886-.134-1.853-2.649-.651-4.48,1.371-2.9-2.415-1.68-.473-1.757-1.517-1.412-2.8-1.962-.045.68-1.845-1.474-1.189-.06-1.562,4.673,2.711,2.165,4.58,3.46.987,2.343-.53,1.461,1.974,2.355-.692-.413-1.327-.647-.105.413.914-1.238.061-.631-.9.765-.858.148-1.885.866.15.268-.931,1.267.15.312,2.1,1.116,1.505.62-.117.869,1.473,1.341-.312.044.752.691-.089-.117,1.388-.91.955.647,4.377-1.372.162Zm-4.9-.283-.725,1.137-.822-1.723.514-.825,1.238-.162.53.781-.619,2.209Zm-8.431-1.016-.913-1.283,2.095,1.562Zm-6.247-3.3-.956-1.121,1.166-.5,2.841.736,1.667,2.4Zm12.289-3.977-4.447-1.477-2.7-3.843.018-1.59,2.415-.3,1.383-1.93,1.68,1.149-.821,1.384.854,1.355,2.031.619-.188,1.283-1.919-.469-1.089.866,3.226,2.448,1.092,1.529-.411.663Zm-20.229-2.46.307-.49-1.1-1.015.116-2.108,3.023,4.495Zm22.382-.708.721.5-2.388.206Zm-18.963-4.216,1.647-2.075,2.225,1.618-1.015,1.711-1.475.457Zm12.168-3.977,2.416.987.619,2.079Zm-4.86,2.342-.323-1.707,1.663.955-.691.842ZM130.59,420.889l.428-1.206,1.769,1.234v.975Zm-8.916-22.126.118-1.724-3-1.635.527-1.165,1.355.886.619-.312,1.417,3.949Zm6-4.066-1.841.061.6-1.606-.987-.635.591-.19.958.235.559,1.873.81-.267-.384,1.222Zm-3.464-3.4.636-1.133,1.328,1.282Zm679.961-2.65-1.489-.886.057-1,1.8.781.515,3.047Zm-681.829-.073.133-2.2,1.384,3.447Zm-3.89-.4,1.06-1.206.826,1.368-1.255.825Zm3.917-2.65.708-.4.6.651-.381.91Zm-21.436-2.593,1.162-1.032,2.047,2.371-2.281.15Zm2.959-5.082-.029-2.46.692,2.638,1.547-.93-.146,1.869.987.785-1.089.1ZM789.9,330.957l-1.489-.178.312-.955,1.708-.53,1.281,1.015-.072,2.359Zm-30.031-39.345-.322-1.149.809-2.286,1.032-1.278,1.975-.53,2.577,1.545L772,289.5l-2.755.073-.765-.38-3.493,1.1-.663-.886.663-.368-.161-.72-1.724-.809-3.142,3.447.5.837ZM9.634,252.9l-.663.044-.085,1.21L8,253.991l.352-1.327,1.68-.68,1.3.279.882,2.257-1.5.631Zm-9.149-.016L0,249.7l2.152-.854.705,1.044-1.812.724-.661-.473.029.563,1.619,1.234,2.845-.5-.146,1.193,1.725,1.574-3.509.518Z" transform="translate(-0.001 -0.398)" fill="#fff" stroke="#fff" stroke-width="0.5" opacity="0.6" />
																	  </g>
																</g>
																<g transform="translate(212.002 1732.516)">
																	  <g transform="translate(0)">
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#a)">
																				  <path d="M502.065,555.151l-12.435,3.228-20.119,2.934L397.27,567.5l-10.864-.859-3.977-.884-6.408-2.934-10.446-6.862h0L348.955,541.12l-4.8-7.807-3.376-3.634-.27-1.964,1.51-7.5,4.1-7.476.1-3.633-1.3-2.124-4.419-4.419-3.768-6.052-7.071-6.15h0L324.33,481.7l-5.855-7.807-.712-1.8,2.161-10.41-.663-8.531,2.86-4.2.835-2.872-2.2-16.081,1.178-7.562-.381-1.927-7.918-1.731-6.935,1.8-4.542-1.571-1.547-2.823.344-6.555,1.056-3.6-2.259-2.308-3.793-1.043-17.308-.773-3.7-2-6.322-13.822-1.117-6.923-2.578-5.794-1.387-5.966-5.107-4.051-.442-3.842.54-4.468h0l-.049-11.085,1.731-5.99-1.154-4.419,3.621-5.217.491-3.854h0l3.732-6.236,2.664-1.94,1.841.147,6.346,3.13,4.751.994,7.034.614,6.96-1.093,6.985,1.068,2.983-2.2,1.043-2.676-2.345-4.7.184-2.823,6.653-8.212,4.014-3.093,15.811-3.867,3.486-3.449L339.122,288l2.8-5.487,2.48-2.418,3.842-1.007,10.189,1.3,8.838-.835,6.788.27,1.031-4.787-.38-3.879-3.093-5.966,1.326-3.572-.172-1.056L364.557,248.1l-4.419-4.382-.6-1.866,1.031-7.046-.528-6.285,4.922-1.35,5.524-7.206,3.02-2.59,5.131-2.259.761-1.817-.184-3.069-1.645-3.069-4.419-3.67-2.885-1.142h0l-.835-2.308.074-1.952,1.215-3.805,2.971-4.076,3.867-2.013h0l2.381,2.6,1.854.638,2.21-1.056,1.6-2.737h0l4.076,2.848,2.6,2.713,1.719,6.273,1.522,1.878,12.779,7.463,7.058,1.3,4.247,5.18,4.984,4.149,2.872.773,6.923.135,3.7.994,12.1,8.519,3.056,1.461,2.9.061,5.413-1.24,1.9.27,7.918,7.463,1.8,3.093,10.9,3.879,4.738,2.553,1.338,3.142.528,6.089.466,27.779-.773,18.5.43,9.1.835,4.542,1.375,2.2,2.8,2.21,11.588,7.733,11.281,11.244,2.062,2.529,3.621,7.1,1.669,8.458L526,367.535Z" transform="translate(141.14 107.28)" fill="#025635" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#c)">
																				  <path d="M143.707,348.451h0l.147-.025Zm140.847-24.293-.54,4.468.442,3.842,5.094,4.051,1.387,5.966,2.59,5.794,1.117,6.923,6.322,13.822,3.7,2,17.308.773,3.793,1.043,2.259,2.308-1.043,3.6-.356,6.543,1.547,2.835,4.542,1.571,6.936-1.8,7.918,1.731.38,1.927-1.178,7.574,2.2,16.081-.835,2.872-2.86,4.21.675,8.519-2.16,10.41.712,1.8,5.855,7.807,5.327,8.666h0l-4.935,1.8-7.009,4.628-6.408-1.6-2.774.307-3.879,1.866-5.585.344-5.229,4.677-7.868,3.756-12.41,11.343-2.836,1.4-2.811-.295-1.719-1.252-5.733-11.134h0l-2.43-2.369-1.817-.27-6.653,3.388-5.107-.246-5.659,2.173-2.345-1.792L260.9,471.6l-1.5-1.78-1.755-.749-2.774.393-1.669,1.731-.859,2.6,1.363,5.573-.147,2.9-3.3,5.684-1.682,4.37-9.489,2.283-4.91,5.033.209,1.743.822.859,4.493,1.338,1.191,1.878-.381,6.482,1.117,4.775-.331,5.45,1.6,1.89,4.53,2.615,2.529,3.756,1.952,1.289h3.179l2.713-1.326,3.167-11.023,2.946-5.475,1.866-.724,5.659-.049.97-.614h0l1.854-.908,2.811.16,2.271,2.308.258,1.866-1.608,8.494,2.688,5.45-1.559,5.647-.933,1.743-1.866.921-5.819-.528-2.75.454-4.763,3.462-.921.073-2.418-2.3-.6,12.275.749,3.646,1.252,1.817,1.768.589,8.237.258,2.59,1.485,3.658,11.22-1.841,7.868h0l-1.24,2.381L269.86,583.4l-2.418,3.867h0l-.491-2.3-.331-.4-1.166-1.51.037-.6-.393-.7-.454-.454-.626.933-.442-.43.442-1.412-.565-.295-.589-.8.184-1.068-.9-1.068-.663-2.5.061-.982-.209-1.731-1.007-.982.184-1.485-1.731-1.3-.773-1.6-2.774-.11-1.3-1.019-.233-1.989L251.57,560.8l1.522-6-1.915-.491-1.412-1.129-1.473-3.351,1.583-3.683-.245-2.074-3.744-1.6-1.363-3.229.466-1.854-.552-1.768-1.007-.712-.687-1.669-1.939-.995.368-.528-.589-2.492.614-3.4-1.5-1.608-2.787-.687-3.425-2.59-.344-1.252,1.277,1.142.417-2.578-1.387-3.854-1.755-2.013-2.529.7-1.24-.393.221.958,1.363.638-.368.344-1.657-1.031-.528-1.841-1.743-.872-.368-2.455-1.6-.712.516-1.547-.5-1.375-6.985-4.8-3.658-1.166-1.522-2.492-.8-.062.172-1.534-2.811.086-1.78-1.866-.626-1.817-2.025-.724-2.32.319.074.626-.921.16-2.578-1.915-1.4.246,1.878.945.344.908-3.278-1.559-6.322-4.775-2.161-2.394.712-.663-1.314-.921.221-1.313-1.178.381-.859-.835-1.24-1.62.282-.454,1.228.835-.491-1.436-1.215.11-1.817-2.431-2.013-3.707.638-.945-.209-.908-1.78-1.1-.184.786.737,1.191-.712-.27-.982-2.664,1.092.528.417-.246-.651-.528.356-.307-.822-.147-.675.233-2.516-2.111.307-1.608-1.031-.945-1.584-.086-1.522-2.381.2-1.473-1.62-2.885-2.038-1.227-1.092-5.2-1.019-.859-1.559-3.069.135-.957,1.068-.479,1.583-1.1.859-2.3-1.5-1.252.16-1.007-.319-.908,1.1-.123-1.129-1.645.307-.479h-.724l-.295.356-.957-.822-1.142-7.451-1.755-3.83-3.977-5.192-.466-2.283.479-.577-.626-1.768.724-.957,1.682-.344-1.252,2.308.638.786.565-1.08.97.209.246-3.376,1.5.123-.295-1.056.687-2.713-.417-.847-.687,3.5-.712-.319.982-2.394-.393-1.8,2.762-5.143-.025-2.087.54-.884,1.031-.221.393-1.694-1.436-.012-.184,1.129-.405-.233-.884-.7.589-2.627-.123-4.419L159.6,384.16l-.479-2.16-1.436-1.755,1.092-1.1-1.142-.086-.528.38-5.242-8.752,1.6.6,1.682,2.75-.233.577,1.461.258.626-.884-1.939-2.762L155,369.393h-2.811l.393-.651-1.093.123-.319.43-1.768-2.688.908-2.025-.9-4.665-.307-.933-.835.835-2.062-3.007-.11-1.645-.405-.478-.282-.184-1.2-2.737-.086-3.192h0l6.285-.491,3.781-1.129,1.007.417,2.615,4.2,2.971,2.271,4.2,5.008,2.062,1.6,1.9.282,5.99-1.363,1.939.454.528,4.738.859.712,12.815-3.99,5.843-.381,1.313,1.706.344,3.99-3.253,8.507.074,1.056.724.908,4.886,1.448,2.566,2.345.552,2.836-1.019,4.751.8,1.939,5.843.688,2.86-1.375,5.683-5.033,8.335,2.713,1.989-.025,5.487-8.175,11.465-8.74,6.285-6.531,3.007-2.173,6.96-2.835.687-.933-.491-1.927-6.567-3.609-.933-.81-.245-1.829,4.407-3.523,14.423-7.157,1.3-1.878.847-2.713-.2-3.953-1.362-3.093.712-2.848,3.977-1.264Z" transform="translate(115.13 138.16)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#e)">
																				  <path d="M142.695,354.643l-1.1-.749-.405.749-.859-.356-.405-.54-1.35-1.228.466-1.1-1.228.5-1.264-1.878-2.234-1.461.2-.381-.761-.417-1.387.565-4.468-3.486.761-.626-.724-.356.712-2.062-1.706.933.552.467-.54,1.559-2.062-.233-3.376-2.529-1.461-2.59-.97.2-.466-.872-.847-.172-.11-1.019-1.9.737-1.277-.442-.1.994.663.2-.454,1.117-1.854.061-.81-1.2-1.436.11L108.25,335.8l-1.829-1.313.381-2.234.822.565.319-1.375-1.448-1.8-.516-1.78-1.277-1.08h0l.675-3.977,1.166-1.8,6.936-.589h0l2.9-1.117h0l.97-.872h0l1.436-2.222h0l.675-.872h0l1.9-.5,2.836.552h0l3.167-.037,1.976-1.019,1.129-1.89.27-1.915h0l-.135-3.9h0L130.333,294h0l1.031-5.855h0l.123-.908h0l.307-2.823h0l-1.375-1.719h0l-3.891-.159h0l-.908-.381h0l-2.2-7.758h0l-2.21-4.763-4.566-4.088h0l-2.664-2.271-.822-2.013h0l-.663-2.786-.9-.749h0l-2.737-1.412h0l-4.922-3.093-1.878.209h0l-2.394,2.185-.344-1.915h0l-.994-3.83-2.075-3.891h0l-.822-2.062h0l.2-2.86h0l3.867-6.8.319-2.16h0l-.442-1.448h0l-1.89-1.166h0l-5.868-1.657h0l-1.964-1.608h0l-1.228-1.939-.847-3.228.994-10.962h0l-1.215-5.156h0L85.6,203.24h0l-1.89-.221h0l-3.83.847h0l-.994-.589-.11-.921,1.227-1.8h0l3.83-.847h0l1.989-.528h0l2.05-.994h0l1.952-.7h0l3.953-1.841h0l2.013-1.3h0l.933-.417h0l1.9-.479h0l4.861,2.038h0l2.9,2.136h0l2.971.381,2.124-.516,7.181-4.088h0l1.9-.565h0l.957.381h0l1.363,1.866h0l.982,3.879h0l.687,2.062h0l3.008,5.242h0l1.338,1.9h0l.994.687h0l1.866.319h0l6.6.295,1.227,1.522h0l-.245,1.952h0l.184.97,1.8,1.277h0l2.787,1.743h0l.184.921h0l-.528,2.946.233.921.908.417h0l.921-.012,1.927-1.461h0l1.89-1.682h0l2.075-1.584,2.025-.4,7.562,3.081.982-.786-.638-1.841h0l-.381-1.841.516-.9.921-.049h0l1.571,1.448,1.178,3.781h0l2.9,1.019h0l2.9.712,2.786,1.755h0l1.866,1.51h0l2.971,2.247,2.774.5h0l8.065-.638h0l12.828-2.48h0l1.878-.086h0l.945.528h0l1.5,1.755h0l.687,1.24h0l.835,1.915.295,3.265-.921,2.971-1.989,2.762v2l1.449,1.841.945.295,4.014-1.031.9.307.442.97-.135,5.107-2.332,13.392,2.553,12.03-.638,12.447.773,1.976,1.6,1.829,1.915.872,1.9.037,11.5-3.977,9.894-.479,2.958-1.448,4.751-5.7,4.014-1.534,8.2-.184,14.485,2.836h0l8.495,2.246,1.657,1.424.344,2.431-.7,13.9,2.136,2.479,6.506,1.706,1.731,1.387L299,319.266,304,323.415h0l-.491,3.855-3.621,5.217,1.154,4.419-1.731,5.99.049,11.085h0l-6.2-1.043L289.2,354.2l-.712,2.848,1.363,3.094.2,3.965-.847,2.713-1.3,1.878-14.423,7.169-4.407,3.523.246,1.829.933.81,6.567,3.609.491,1.927-.687.933-6.96,2.836-3.007,2.173-6.285,6.543L248.9,408.79l-5.487,8.175-1.989.025-8.335-2.713-5.683,5.033-2.86,1.375L218.7,420l-.8-1.94,1.019-4.738-.552-2.836-2.566-2.345-4.886-1.448-.724-.908-.074-1.056,3.253-8.495-.344-3.989-1.313-1.706-5.843.381-12.815,3.989-.859-.712-.528-4.738-1.94-.454-5.99,1.362-1.9-.282-2.05-1.6-4.2-5.008-2.971-2.259-2.6-4.2-1.007-.417-3.781,1.129-6.285.491h0l-.258.037h0l-.147.025h0l-.786-3.977-1.62-1.62.258-1.289-1.191.491-.8-.43-.16.921,1.031.491-.454.491-1.056-.614.123-1.854-.7-1.657.16-.638-.958-.491-.859-1.007-1.166-3.425-.012-2L149.1,360.3l-1.129-.111-1.068-1.915-2.958-2.025-.712-1.264-.54-.344Z" transform="translate(100.36 108.46)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#g)">
																				  <path d="M587.171,293.572l1.5.651.429,2.541-.737-1.62-1.24-.737.049-.835Zm-10.25-47.886,1.068.847-.062,1.964-1.448-1.424-1.24-.147.258-.8Zm-22.55-35.77,2.148,1.289,5.045,1.326-2.3.061-.638-.319-2.909.921-.552-.737.552-.307-.135-.6-1.436-.675-2.615,2.872.417.7-.491-.159-.27-.958.675-1.9.859-1.068ZM578.394,251.1l.467,1.215.564-2,.553.135-.073,2.934.97.54,1.461-2.161-.442-.405.467-2.222.307.479.147,1.3.466-.749.061,1.448-1.2.712.1,1.289-.773.135,2.025,1.866.638,1.989-.663,5.033.553.835-.442.037-.123,1.817-1.227,1.387.233-.957-1.068-1.5-.2,2.75.516.638-1.019,2.6.233,1.6-.921.516-3.216-7.316-.933-.663-.638.393L573.8,268.2l1.092,1.584-.638.822.049,3.707.749.2.982-1.129.994.908,1.682.123.614.921-.184.97.773.7-.847.2-.454,1.277,1.571,3.572,2.222,3.29,1.338.81.859,2.48.921.491-.282,1.08-.5.221-.491-1.8-1.743-.405-1.227-1.8-.675.209-.577-.712.835,2.136,1.338.933.062.687.626-.884.27.344.295,2.21,1.571,1.424.823,1.792,1.547.54.086,1.35,2.5,3.118.491.049-.062-1.35-.835-1.117.81-.651.761,2.013,3.965,3.057,1.633,4.959-.418,2.958,1.535,2.492-.5,2.185,1.019,3.142-.332,1.08,1.093.552-.614-.8.393-1.007,3.094,4.333L603.3,325.5l1.166.049.663.847,1.829,8.016.786-2.259,1.94-.368-.307.589,1.326,2.615,4.53,5.377,5.622.982,3.008-1.178,2-2.209,1.424.479,1.228-.405-.123.626.736.54.933-.43-.81,1.571.159,2.971,1.78-1.068.454-1.706,1.964-1.6-.11-1.166,1.092.651.651-.528.5,2.013,1.264-.712.933.933-.761.786L635.7,341l-.663,1.166-.54-.2-.515,1.817-3.769,3-.736,3.793-.688.16.16,2.087,2.75.516,1.1-1.448,2.492-.221,2.05.81,1.854,2.6,2-.086.921,1.448-.626,5.192,42.1,51.1,29.2,5.843,73.3,16.891,8.654-9.157L814,458.209l-28.2,89.9L658.872,592.8l-49.052,9.747-71.037,7.746-39.392,19.248L473.723,660.96l-5.978,14.988-1.878,1.141h0l3.179-30.013,9.808-77.936h0l23.937-187.6.024-9.513-1.669-8.47-3.633-7.1-2.05-2.529L484.182,342.7l-11.588-7.734-2.811-2.209-1.375-2.2-.835-4.542-.417-9.084.773-18.5-.466-27.767-.528-6.089-1.338-3.142-4.738-2.553-10.9-3.891-1.792-3.093-7.918-7.463-1.9-.27-5.413,1.24-2.884-.049-3.069-1.461-12.1-8.519-3.695-.994-6.923-.135-2.885-.773-4.984-4.149-4.247-5.18-7.046-1.3-12.779-7.463-1.51-1.878-1.731-6.273-2.6-2.713-4.076-2.848h0l-1.8-2.234-.467-1.878,1.571-5.733,11.232-14.914,15.54-29.056,10.14-10.7,15.283-5.389h0l6.26.16,3.241-1.559,5.02-3.879,38.483,5.045,1.068.822,4.124,6.6.749,3.13.27,5.659,4.333,5.769-.209.859,30.664-.528,1.448,1.4,1.571.577-.049,2.762,1.031.81-.221.417,1.043.184-1.24,3.695,1.191,2.431,1.8,1.264,2.087,2.737-.847.651-.221,3.277.479,1.228,2.516,2.921,4.775,1.289-.945,1.129-1.031-.1.1,1.289,1.952,2.222,1.228.123.061,1.657,2.492,1.547-.442,2.578.565.54-.1.577-1.1.737v-1.093l-.675-.049.356-2.455-.884,1.252-.356-1.731-1.6,5.4.785.1.552-1.534.43,2.922.786-1.166.516.835-.417,1.449,1.313-.381-.393.712-.638.135-.061,1.007-.393.847,1.375.663.921-1.62.773.233-.7,1.277.135,1.583.994-.5-.123-1.277,1.915-.97,1.154.454,1.326,1.927,3.179,1.424,4.493-.074,2.074,1.019.749,1.633,1.375.773.245,2.234-.687-.184.307-.847-.442-.663-.871,1.412-.663-.749-.872.872-.921-.884-.908.577-.381-.552-.847,1.129-1.006-.417-.405.9.5-.258,1.891,1.154.687.872,1.24-.295-.037-1.289,1.67.945-.749.933-.9.086.393,1.129-.822,1.51,2.111-1.338,1.988.663-.012,4.554.823,1.387.663.025.516-.074.651-.282,1.4.7-.589,2.32-.8-.282-.54.5.626.012-.246.687.933-.381.65,1.264.823-2.124.614.43-.184,1.3,1.473-.81,1.792.626-1.093-2.062,1.559-1.608-2.123-1.94-.835.1.8-1.277-.258-.651.908-.577.565,2.418,1.363.074.282,1.841-.908.381,1.191,1.178,1.1-.859.724-3.3,1.191-1.608L557.38,217.1l-.54,1.915-.773,1.178.675,2.406,1.436,1.424,2.946-1.755,1.314.5-.037.111-.6-.012-.111.049-.331.577-.332-.577-2.8,1.424,1.178,1.51,1.829-.442-.2.9-.687.025.024,1.461.872.037.295,1.043.16.245,2.823,2.222.454.135,1.485.9,1.387.454.97.749,3.658.368,2.541,2.713,5.512,6.494.6.552.5.491-.724.11-1.6-2.124-2.062-.528-1.2.835-1.51-2.394-.074-1.08-.577,2.75.184,1.166.994.246-.43,1.338.38,3.265,1.142,2.86,1.731,2.259.123-1.412.381-.381Z" transform="translate(164.38 93.37)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#i)">
																				  <path d="M238.577,279.732l1.473-1.682,6.076-.908,7.365-4.382,7.3-.945.994-.8.061-1.007-2.946-5.868.393-3,1.547-1.854,1.056-.233,4.849,1.743,2.983.307.577-.945L269.3,252.4l2.32-5.892.945-.5,1.964.638,4.076-.958,1.068-5.278,5.831-5.929,14.6-11.342,13.883-6.42,3.388-7.107,3.339-3.388,17.8-9.121,5.094-1.645,2.909,1.056,5.45,5.512,7.353,5.057,9.17.663,6.2-7.169h0l2.885,1.142,4.419,3.67,1.645,3.069.184,3.069-.761,1.817-5.131,2.259-3.02,2.59-5.524,7.206-4.922,1.35.528,6.285-1.031,7.046.6,1.866,4.419,4.382,8.212,12.472.172,1.056-1.326,3.572,3.093,5.966.38,3.879-1.031,4.788-6.788-.27-8.838.835-10.188-1.3-3.842,1.007-2.48,2.418-2.8,5.487-3.793,2.136-3.486,3.449-15.811,3.867-4.014,3.093-6.653,8.212-.184,2.823,2.345,4.7-1.043,2.676-2.983,2.2-6.985-1.068-6.96,1.093-7.034-.614-4.75-.994-6.346-3.13L274,314.521l-2.664,1.939-3.732,6.236h0l-5.008-4.149-3.867-13.257-1.719-1.387L250.5,302.2l-2.136-2.467.712-13.9-.344-2.431-1.657-1.424Z" transform="translate(136.72 109.06)" fill="#025635" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#k)">
																				  <path d="M190.055,240.982l2.443-2.934,2.872-9.526,3.842-4.591h0l5.45-5.07.356-2.958h0l-.442-.933h0l-3.621-2.259h0l-1.007-.442h0l-4.579-2.651-1.719-4.8h0l-.172-1.129h0l-.479-2h0l-2.75-11.5-1.289-3.081h0l-1.719-2.013-2.909-1.215h0l-.945-.565-1.633-2.983-.921-.38h0L178,179.127h0l-1.6-1.952h0l-3.609-2.786-6.076-1.6h0l-1.89-.295h0l-5.008-.884h0l-2.9-.638-1.387-1.633h0l4.53-5.156,7.7-4.345,12.189-5.377,5.266-.847,16.044-9.329L217.1,137.5l3.732.81h0l5.377,2.332,2.909.454,25.729-5.978,14.939,4.333,5.843-1.657,5.463-.442,17.762,4.309,2.909,2.443,5.156,6.334,3.093,1.792,4.9-.258,5.671-3.044,3.29-.577.823.982,1.08,7.635,1.743,1.792,1.178.381,7.967-.381,7.365,3.83,3.093-1.031,6.2-4.149,1.927-.258,1.94,1.51,1.326,1.989,6.481,11.858,3.413,9.452,2.48,3.044,3,1.768,12.251,1.583,4.026,1.547,1.988,1.166,7.463,6.776,1.264,2.3h0L397,202.155l-2.971,4.076-1.215,3.805-.074,1.952.835,2.308h0l-6.2,7.169-9.17-.663-7.353-5.057-5.45-5.512-2.909-1.056-5.094,1.645-17.8,9.121-3.339,3.388-3.388,7.107L319,236.858,304.4,248.2l-5.831,5.929-1.068,5.278-4.075.958-1.964-.638-.945.5-2.32,5.892,1.007,7.77-.577.945-2.983-.307-4.849-1.743-1.056.233-1.547,1.854-.393,3,2.946,5.868-.061,1.007-.994.8-7.3.945-7.365,4.382-6.076.908-1.473,1.682h0l-14.485-2.836-8.212.184-4.014,1.535-4.751,5.7-2.958,1.449-9.894.479-11.5,3.977-1.9-.037-1.915-.859-1.6-1.829-.773-1.976.626-12.447-2.541-12.042,2.332-13.38.147-5.107-.454-.97-.9-.295-4.014,1.019-.933-.295-1.449-1.841V251.9l1.989-2.774.908-2.958-.295-3.265Z" transform="translate(117.83 95.33)" fill="#025635" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#m)">
																				  <path d="M109.429,319.958l1.473,1.031v.81l-1.829-.835Zm-9.747-21.2,1.129.736.516-.258,1.178,3.29h-.859l.1-1.436-2.5-1.363Zm5.6-1.6.8.2.466,1.559.675-.221-.319,1.019-.258-.577-1.534.049.5-1.338-.822-.528.491-.16Zm-.994-1.8,1.1,1.068-1.633-.123Zm-4.444-2.664.687,1.142-1.043.687-.528-.822Zm2.467-.491,1.154,2.872-1.264-1.043Zm.5-1.043.5.54-.319.761-.773-.97.589-.331Zm-17.48-2.688,1.706,1.976-1.9.123-.773-1.24Zm3.216-2.443.822.651-.908.086L86.83,285.1l-.025-2.05.577,2.2,1.289-.773-.123,1.559ZM8.637,180.216l1.08.233.737,1.878-1.252.528-.9-1.878-.552.037-.074,1.007-.737-.135.295-1.1,1.4-.565ZM2.069,177.6l.589.872-1.51.6L.6,178.681l.025.467,1.35,1.031,2.369-.417-.123.994,1.436,1.313-2.922.43-2.05-1.534-.405-2.651Zm58.848-45.885,6.236,1.9h0l3.081,2.369,2.443,2.909h0l1.436,2.013h0l7.648,9.98h0l2.111.786,2.222-.393h0l4.026-1.694,5.389-1.264h0l5.475-1.019h0l6.334.884,5.671,2.21,10.5.884h0l1.927-.7h0l3.167-7.427h0l1.547-1.817h0l1.939-.884h0l3.523-3.535h0l.945-.5,1.927,1.166h0l.565,2.013h0l-.552,8.826.381,5.389h0l.7,3.241h0l.921,2.013h0l4.272,4.358,2.971,1.485h0l4.063,1.35h0l12.447,1.448h0l2.946-2.148.97-.049h0l1.89,1.547h0l.957.577h0l2.013-.381h0l.921.258.97,3.032,1.584,1.326h0l2.124-.11,4.37-2.308h0l4.161-1.215,2.013.6,1.719,1.5h0l1.387,1.62,2.9.638h0l5.008.884h0l1.89.295h0l6.076,1.608,3.609,2.786h0l1.6,1.964h0l2.836.822h0l.921.381,1.633,2.971.957.565h0l2.9,1.215L224.3,188.1h0l1.277,3.081,2.75,11.5h0l.479,2h0l.172,1.129h0l1.719,4.8,4.579,2.639h0l.994.442h0l3.634,2.259h0l.43.933h0l-.356,2.958-5.45,5.07h0l-3.842,4.591-2.86,9.538-2.443,2.934h0l-.687-1.24h0l-1.5-1.755h0l-.945-.528h0l-1.878.1h0l-12.828,2.492h0l-8.065.638h0l-2.774-.491-2.971-2.246h0l-1.866-1.51h0l-2.787-1.755-2.9-.712h0l-2.885-1.019h0l-1.178-3.781-1.571-1.436h0l-.921.049-.516.9.381,1.854h0l.638,1.841-.982.786-7.549-3.081-2.025.4-2.062,1.584h0l-1.89,1.682h0L163.7,236.2l-.921.012h0l-.908-.417-.233-.921.528-2.946h0l-.184-.921h0l-2.786-1.743h0l-1.8-1.277-.184-.97h0l.245-1.952h0l-1.228-1.522-6.6-.282h0l-1.866-.319h0l-.994-.687h0l-1.338-1.9h0l-3.007-5.242h0l-.687-2.062h0l-.982-3.879h0l-1.363-1.866h0l-.945-.381h0l-1.9.565h0l-7.181,4.088-2.124.516-2.971-.381h0l-2.885-2.136h0l-4.861-2.038h0l-1.9.479h0l-.933.417h0l-2.013,1.3h0l-3.953,1.841h0l-1.952.7h0l-2.05.994h0l-1.989.528h0l-3.83.847h0l-1.228,1.8.11.921.994.589h0l3.83-.835h0l1.89.221h0l1.755,1.694h0l1.215,5.156h0l-.994,10.962.847,3.228,1.228,1.94h0l1.964,1.608h0l5.868,1.657h0l1.89,1.166h0l.442,1.448h0l-.319,2.16-3.867,6.8h0l-.2,2.86h0l.822,2.062h0l2.075,3.891.994,3.83h0l.344,1.915,2.394-2.185h0l1.878-.209,4.922,3.093h0l2.737,1.424h0l.9.761.663,2.786h0l.822,2.013,2.664,2.271h0l4.566,4.088,2.21,4.763h0l2.2,7.758h0l.908.38h0l3.891.16h0l1.375,1.719h0l-.307,2.823h0l-.123.908h0l-1.031,5.855h0l.27,12.607h0l.147,3.9h0l-.27,1.915-1.129,1.89-1.976,1.019-3.167.037h0l-2.836-.552-1.9.5h0l-.675.871h0l-1.436,2.222h0l-.97.872h0l-2.9,1.117h0l-6.936.589-1.166,1.8-.675,3.977h0l-1.989-3.56.172-.847-1.031.4-.687-2.013-3.413-1.854.405-.552-.945-2.038,1.031.086-.061-1.522-1.129-.43.516.5-.957.712-1.633-2.185.908-.736,1.657.368-1.092.54,1.473.454.43-1.449,1.412-1.154-.847-1.829,1.633-3.818-1.78-3.474.97-1.792-.724-3.216-5.733-6.408-1.682-4.284.11-1.927-3.314-2.688.049-2.185-1.142-.307-.27-2.541-1.313-1.927-2.59-1.117-2.737-3.9-.479.012.651,1.854-.454.221-.749-.074-.651-1.092-1.1-.074-1.35-1.056.221-1.3-.6.479-.552-.638-.712-2.357-1.178-.368,1.989-3.142.786-2.836-.295-2.381-2.2-1.375-3.4-.2-.344-.209-2.885-2.406-1.338-4.53-3.769-6.125-2.664-7-3.241-5.487-2.48-2.664-1.252-2.431L68.1,237.861l-2.2-5.168-.2-2.124-3.695-4.075-2.369-1.35-.356-.994L55.5,220.368l-.528-2.578,1.24-1.3-.381-1.412-2.823-4.64-3.142-1.608L46.69,205.2l-.221-1.669-2.2-2.946-.491-3.805-1.817-2.32-.466-2.443-1.252-2.283L39.2,188.673l-2.271-.687L36.7,184.9l-3.057-2.455-.8-1.989-4.075-2.111-.233-.712,2.676-.8-.086-.724-2.455-.442-.835-.724-1.6.074-2.087-2.038-.687.27-1.1-.737v.319l.626.994-1.35-.8-2.946,1.62-2.762-.209-.651.5L15,173.771l1.252-.687-2.038.135-.712,1.657-.331-.516.786-1.375-.687.1-.663.9-1.252-.2.54,1.559-1.178.025.54-2.062-1.657-.233.393-.405-.54-1.019-.307.6-.884-.368L6.869,173.8l.724,1.35-.835.172-.872,1.768-.957-1.78.246-1.571-.651-.27-.921.614.012-.97L6.022,170l1.191.442L6.7,169.168l.737-.528.933-3.118-.417-.1L12.012,155.4l1.412-2.124-.331-4.309-.945-2.369,2.357-7.071.037-3.621h0l3.965.2,2.013-.749,3.057.958h0l6.445.847h0l2.357-.147,2.836-2.762h0l4-1.031h0l7.4-1.338h0l2.885.27h0l4.063-.847h0l4.149-.209h0l3.2.626Z" transform="translate(82.5 94.42)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#o)">
																				  <path d="M469.337,160.38l-15.295,5.389-10.127,10.7-15.54,29.068-11.232,14.915-1.584,5.733.479,1.866,1.8,2.246h0l-1.6,2.737-2.21,1.056-1.854-.638-2.381-2.6h0l-1.264-2.3-7.463-6.776-1.989-1.166-4.026-1.547-12.251-1.583-3-1.768-2.48-3.044-3.412-9.452-6.481-11.858-1.326-1.989-1.94-1.51-1.927.258-6.2,4.149-3.093,1.031-7.365-3.83-7.967.381-1.178-.381L334.7,187.68l-1.08-7.635-.822-.982-3.29.577-5.671,3.044-4.9.258-3.093-1.792-5.156-6.334-2.909-2.443-17.762-4.309-5.462.442-5.843,1.657-14.939-4.333-25.729,5.978-2.909-.454-5.377-2.332h0l6.9-6.985,1.092-7.439.479-11.49,1.719-3.032,7.562-5.794L280.985,113.6l6.457-5.806-.123-1.927-9.55-7.684-3.842-7.844-2.566-3.069-4.419-1.952-6.69.049-3.965-.8L246.995,77.1l-3.314-1.547-8.6,1.08-22.034-.761L208.5,74.5l-4.885-4.223-2.185-.945-5.377-.1L172.717,65l-9.882.172-8.9,1.669-16.67,1.829-4.2-.049-2.836-.761-2.688-2.332-4.775-8.544L117,52.455l-.712-8.249,3.486-10.238,1.559-7.439h0l43-12.656L177.246,2.617,176.694.15l1.424,1.89,2.86,1.731,22.783,6.653,7.451,3.486,4.2,2.836L324.28,93.663,405.359,149.7l8.593,1.989Z" transform="translate(108.62 64.5)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#q)">
																				  <path d="M264.294,608.224l.516.233.221-.11.417.319-.8,1.375-.835-.933Zm-.589-3.953.442.651-.516,1.842-.1-1.179-.6.945-.687-1.436.43-.687Zm-7.954-.6,1.743,1.3-.982-.234Zm-4.272-3.032,2.369.614,1.387,2-3.928-1.265-.8-.932.97-.418Zm11.858-4.6.6.417-1.988.172Zm-11.269-2.222,3.891,2.259,1.8,3.818,2.885.822,1.952-.442,1.215,1.645,1.964-.577-.344-1.1-.54-.086.344.761-1.031.049-.528-.749.638-.712.123-1.571.724.122.221-.773,1.056.123.258,1.755.933,1.252.516-.1.724,1.228,1.117-.258.037.626.577-.074-.1,1.154-.761.8.54,3.645-1.141.135-1.522-1.743-2.222-.454,1.522-.736-.111-1.547-2.21-.54-3.732,1.142-2.418-2.013-1.4-.393-1.461-1.264-1.178-2.332-1.633-.037.565-1.535-1.227-.994-.049-1.3Zm-7.942-.2,2.516,3.744-1.952-.736.258-.405-.921-.847Zm14.644-2.553,1.4.957-.687,1.154.712,1.129,1.694.515-.16,1.068-1.6-.393-.908.724,2.688,2.037.908,1.277-.344.552-.933-1.411-3.707-1.228-2.246-3.2.012-1.326,2.013-.245,1.154-1.608Zm-9.857-.27,1.853,1.35-.847,1.424-1.228.38-1.154-1.423,1.375-1.731Zm4.444-1.056,1.387.8-.577.7-.54-.074Zm6.334.295.516,1.731-2.529-2.553,2.013.822ZM252.6,531.2l5.475-.577,1.547,1.264-1.006,8.924.344,1.841.908.577,3.621-.552,2.639.283,1.326,1.657,1.043,3.547.9.786,10.164.491,1.571,1.522,1.952,4.578,3.99,3.891,1.866.773,1.731-.172.687-.81,2.05-8.286,1.485-2.578,3.523-2.652,2.713-1.08L302.347,546l-.16,5.425,4.419,2.234,3.241,5.7.847.7,3.621.737,2.43,3.695h0l1.191,1.706-2.357,3.585.111,2.909,1.9,1.1v.8l.994.43.319.847-5.512,1.731.516,2.3-1.866,1.682.061,1.78-.761.393-.074,1.081.111.429,1.2-.491,2,.589,1.007,2.013-.675,1.117-2.786-.319.319,3.449-1.841.859-.049.736,1.78,2.32,1.9.417.4,1.019,1.24.589-.27,1.547.589,1.056-1.755.135-.393.994.6,3.192-1.031.184-2.541-1.363-.81.221.54,1.1-1.264,3.364.061,1.3-5.426,1.424-2.05,1.461.516,3.327-2.1.466-5.917-.6-.417-.847.737-2.173-1.485-1.424-1.031-2.713.687-4.37-.4-.356-.577-.737v-1.142l.012-.7-3.081-3.818-1.731-.847-.786.307-.479-1.657-.626-.233-.847-.38,1.314-1.179-.651-1.706.381-2.271-1.682-1.375-2.664-.823-1.633-2.308.282-2.247-.565-.221-1.375-.822-.319.368,1.387,2.394-.81.81.061,2.43-.368,1.166-.221-4.578-1.2-3.916-.651-5.106-.688-.381.81-2.59-.356-2-1.768-.466-1.927-3.032-2.418-1.854-1.2.062-.245-1.829-.97-1.154-8.985-6.4-.8-1.265-1.78-.4-1.522-1.252-1.289-.037.233-.982-4.64-4.272.847-.012-1.24-1.117.319-1.314-.724.97-.6-.4-.147-.675.724-.221-.27-1.24-1.706-.663-.933-2.2h0l2.418-3.867,4.358-2.517Z" transform="translate(137.96 185.32)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#s)">
																				  <path d="M419.209,588.142l-21.15-2.9-7.672-.516-7.672-4.947H358.5l-1.706,1.2-2.394.331-3.241-1.706-3.314.147-10.79,1.375L336.044,583l-1.326-1.645L331.1,582l-1.682,2.357-3.2-.884-1.964.528-2.418-.356-.835-1.092-2.479.442-3.179-3.314.086-1.276h0l1.412-3.977-1.6-7.279.822-1.8L317,555.072l-1.694-7.512.1-2.038,1.08-2.075,3.781-4.21,10.078-9.28,1.326-1.817,1.3-3.707,1.166-1.289,7.758-2.48,2.921-1.743,1.5-1.731,1.191-2.725.209-2.6-2.161-10.52.847-1.952,4.726-2.382L352.5,495.3l.049-7.463h0L363,494.7l6.408,2.934,3.977.884,10.864.859,72.24-6.187,20.12-2.934,12.435-3.228h0l-9.808,77.936-3.179,30.013h0l-9.182,5.794-8.629-.442L448.2,586.779l-13.135,1.878Z" transform="translate(154.16 175.4)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#u)">
																				  <path d="M269.717,448.287l-2.639,7.881.282.97,2.516,2.418.135,1.952-1.277,1.78-7.144,4.873-1.031,2.2.282,4.358-5.229,10.667h0l-.97.626-5.671.049-1.866.712-2.946,5.475-3.167,11.035-2.713,1.326h-3.167l-1.952-1.289-2.529-3.756-4.517-2.615-1.6-1.89.319-5.438-1.117-4.775.38-6.493-1.191-1.866-4.481-1.35-.822-.859-.221-1.731,4.922-5.033,9.489-2.283,1.682-4.37,3.3-5.683.147-2.885-1.362-5.585.859-2.59,1.669-1.743,2.774-.38,1.755.749,1.485,1.78,1.571,4.64,2.357,1.792,5.659-2.173,5.119.246,6.641-3.376,1.829.27Z" transform="translate(131.9 165.16)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#w)">
																				  <path d="M13.788,149.807l.81-3.462.908-.933-.221-1.007,1.277-.749-.589-2.357.994-.565-.295-1.056.749-1.669,46.9,8.335,17.983-14.816,10.864-18.143,31.621-6.334,7.1-16.179,13.9-8.323L103.729,33.793,145.084,21.64h0l-1.559,7.439-3.486,10.238.712,8.249,5.769,4.53,4.775,8.544,2.688,2.332,2.836.761,4.2.049,16.67-1.829,8.9-1.669,9.882-.172L219.8,64.346l5.377.1,2.185.945,4.886,4.223,4.542,1.375,22.034.761,8.6-1.08,3.314,1.547,9.292,7.463,3.965.8,6.69-.049,4.419,1.952,2.566,3.069,3.842,7.844,9.55,7.684.123,1.927-6.457,5.806-33.475,20.684-7.562,5.794-1.719,3.032-.479,11.49-1.093,7.439-6.9,6.985h0l-3.732-.81-15.835,6.788L217.9,179.439l-5.266.847-12.189,5.377-7.7,4.345-4.53,5.156h0l-1.731-1.485-2-.6-4.161,1.227h0l-4.37,2.308-2.124.1h0l-1.6-1.326-.958-3.032-.921-.258h0l-2.013.393h0l-.945-.577h0l-1.89-1.547h0l-.982.049-2.946,2.148h0l-12.435-1.448h0l-4.075-1.35h0l-2.971-1.485-4.26-4.358h0l-.921-2h0l-.7-3.253h0l-.381-5.389.552-8.826h0l-.565-2.013h0l-1.927-1.154-.945.5h0l-3.523,3.535h0l-1.939.884h0l-1.547,1.8h0l-3.167,7.439h0l-1.927.7h0l-10.5-.872-5.671-2.21-6.334-.884h0l-5.487,1.031h0l-5.389,1.264-4.026,1.682h0l-2.222.405-2.111-.786h0l-7.648-9.98h0l-1.436-2.013h0l-2.443-2.909-3.081-2.369h0l-6.236-1.89h0l-3.2-.626h0l-4.149.209h0l-4.063.847h0l-2.885-.27h0l-7.4,1.338h0l-4.014,1.019h0l-2.823,2.774-2.369.147h0l-6.432-.847h0l-3.057-.957L15.862,161l-3.965-.2h0l.479-3.634.786-1.559-.5-2.038,1.007-1.657Z" transform="translate(85.14 69.51)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5" />
																			</g>
																			<g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#y)">
																				  <path d="M318.173,586.39l-2.43-3.695-3.621-.737-.835-.7-3.253-5.684-4.419-2.234.172-5.426-1.228-1.375-2.713,1.08-3.523,2.652-1.485,2.578-2.038,8.286-.7.81-1.719.172-1.866-.774-3.989-3.879-1.952-4.578L281,571.365l-10.164-.491-.9-.785-1.043-3.548-1.326-1.657-2.652-.283-3.609.552-.908-.577-.344-1.841,1.019-8.924-1.559-1.264-5.475.577h0l1.841-7.868-3.646-11.22-2.59-1.473-8.237-.258-1.755-.6-1.264-1.8-.749-3.646.6-12.275,2.418,2.307.921-.074,4.751-3.462,2.75-.466,5.819.54,1.866-.921.933-1.743,1.559-5.647-2.688-5.45,1.608-8.495-.258-1.854-2.271-2.32-2.811-.159-1.841.908h0l5.229-10.667-.282-4.358,1.031-2.2,7.144-4.873,1.277-1.78-.135-1.952L262.75,458.9l-.282-.97,2.639-7.881h0l5.733,11.146,1.718,1.252,2.8.295,2.836-1.387L290.6,450l7.868-3.756,5.229-4.665,5.585-.344,3.879-1.866,2.774-.294,6.4,1.6,7.021-4.628,4.935-1.792h0l7.07,6.15,3.769,6.052,4.419,4.419,1.3,2.123-.1,3.634-4.1,7.476-1.51,7.5.27,1.964,3.376,3.634,4.8,7.807,16.621,14.841h0l-.049,7.463-1.375,1.706-4.726,2.381-.847,1.952,2.16,10.52-.209,2.6-1.191,2.725-1.5,1.731-2.922,1.743-7.758,2.48-1.166,1.289-1.3,3.707L348,541.966l-10.078,9.28-3.781,4.21-1.08,2.074-.1,2.038,1.694,7.513-.945,10.274-.822,1.8,1.6,7.279-1.412,3.977h0l-.135,2.209-2.909-2.823-6.494-4.137-3.02-.417Z" transform="translate(136.51 163.4)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																			</g>
																	  </g>
																</g>
														  </g>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.riyadhfirst.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1973</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>500</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>500</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>63</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=813b5e10-5431-47de-87ca-501ac0a524c8' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">
															  <g transform="translate(698.001 2179.066)">
																	<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
															  </g>
															  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(820 2150)' : 'translate(730 2143)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																	<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.riyadhfirst.city.name.text"/></tspan>
																	<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
															  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.shaqraa.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2011</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>10,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>30</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=54afd4a5-8075-4a84-94a2-bbab806393a3' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/> </a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(692.676 2122.772)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(814.675 2096.706)' : 'translate(724.675 2086.706)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.shaqraa.city.name.text"/></tspan>
																<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.durma.industrial.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2003</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>11,500</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>3,800</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>16</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=51cce217-412b-4470-a8d5-3628f700c6e8' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(706.001 2247.066)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(830 2214)' : 'translate(736 2214)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.durma.industrial.city.name.text"/></tspan>
																<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.riyadhsecond.industrial.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1976</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>19,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>19,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1,115</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=20b51c2e-200a-46dc-ab02-a18bfe5d4c15' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(661.001 2229.066)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(646 2197)' : 'translate(546 2197)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.riyadhsecond.industrial.city.name.text"/></tspan>
																<tspan x="0" y="38" xml:space="preserve"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.riyadhthird.industrial.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2010</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>26</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=106fd109-aabe-45c2-ab22-d3d5c49df371' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(634.501 2159.259)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(609 2130.025)' : 'translate(529 2130.025)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.riyadhthird.industrial.city.name.text"/> </tspan>
																<tspan x="0" y="38" xml:space="preserve"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.alkharj.industrial.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2009</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>99,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>24,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>379</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=a5df6728-f143-4717-affb-6891e525d895' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(622.001 2070.259)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(734 2046.193)' : 'translate(659 2036.193)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.alkharj.industrial.city.name.text"/></tspan>
																<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.alzulfi.industrial.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2010</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>18,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>11</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=19d6ec80-c35b-48b3-b942-024b908c0e12' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(744.001 2045.326)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(860 2013.193)' : 'translate(775 2013.193)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.alzulfi.industrial.city.name.text"/></tspan>
																<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.hail.industrial.city.name.text"/>  <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2003</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>3,880</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>3,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>107</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=c362f09e-a54b-48c1-81dd-54c9e805dc27' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(589.001 2082.092)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(634 2080.025)' : 'translate(580.5 2087.025)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.hail.industrial.city.name.text"/></tspan>
																<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.city.name.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.qassimfirst.industrial.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1980</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>1,500</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/> </span></td>
																				  <td class='text-left'><span class='INL_table_number'>1,500</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>156</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=1fd97192-4294-4b4b-9b48-b2cc640fe50d' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(613.001 2016)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(733 1975)' : 'translate(643 1983)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.qassimfirst.industrial.city.name.text"/>  </tspan>
																<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.oasis.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.qassim.industrial.city.name.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/> </span></td>
																				  <td class='text-left'><span class='INL_table_number'>2012</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>740</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>740</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>31</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/Oasis/Pages/Oasis.aspx?OasisId=cd0aa297-1ab7-479b-8d74-ce409af8f673' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(575.001 2034)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(615 1959)' : 'translate(515 1959)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="-45.496" y="18"><spring:theme code="economic.infralogistics.maps.oasis.city.name.text"/></tspan>
																<tspan x="-34.192" y="38"><spring:theme code="economic.infralogistics.maps.qassim.industrial.city.name.text"/></tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.qassimsecond.industrial.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2012</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>11,800</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2,000</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/> </span></td>
																				  <td class='text-left'><span class='INL_table_number'>38</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=21e3bfa6-737f-4176-ae78-fd2b1969e12c' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														  <g transform="translate(555.001 2087.579)">
																<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														  </g>
														  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(539 2056.386)' : 'translate(435 2056.386)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																<tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.qassimsecond.industrial.city.name.text"/> </tspan>
																<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
														  </text>
														  </a>
														  <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														  <div class='container py-3 px-0'>
															  <div class='w-100'>
																  <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.sudair.industrial.city.name.text"/></h6>
																  <div class='p-2'>
																	  <div class='INL_table'>
																		  <table class='table borderless m-0'>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>2009</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>63,936</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>16,900</span></td>
																			  </tr>
																			  <tr>
																				  <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				  <td class='text-left'><span class='INL_table_number'>329</span></td>
																			  </tr>
																		  </table>
																	  </div>
																  </div>
																  <div class='text-center w-100 pb-2'>
																	  <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=cbedec34-2138-4b40-9bf3-12ea1ce95a38' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																  </div>
															  </div>
														  </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">
															  <g transform="translate(539.164 2037.772)">
																	<path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
															  </g>
															  <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(523.162 2005)' : 'translate(363.162 2005)'}" fill="#f0f0ef" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																	<tspan x="0" y="18" xml:space="preserve"><spring:theme code="economic.infralogistics.maps.sudair.city.name.text"/></tspan>
																	<tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.sudair.city.business.name.text"/></tspan>
															  </text>
														  </a>
													</g>
											  </svg>
											</div>
										</div>
									</div>
								</div>
								<div id="Western" class="tab-pane fade INDS_margin_leftn40" role="tabpanel">
									 <div class="row">
										<div class="col-md-12">
											<div class="w-tab2 h-100 w-100">
												<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="980.047" height="902.046" viewBox="0 0 1117.047 902.046">
													<defs>
														 <filter id="a" x="316.397" y="230.063" width="433.521" height="545.471" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="b" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="b" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="c" x="176.301" y="396.77" width="377.384" height="429.818" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="d" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="d" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="e" x="96.379" y="236.531" width="390.752" height="393.375" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="f" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="f" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="g" x="441.747" y="155.087" width="619.399" height="716.347" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="h" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="h" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="i" x="292.344" y="239.747" width="310.948" height="292.889" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="j" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="j" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="k" x="190.533" y="165.696" width="411.007" height="334.331" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="l" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="l" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="m" x="0" y="160.756" width="405.59" height="375.815" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="n" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="n" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="o" x="142.154" y="0" width="518.387" height="399.363" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="p" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="p" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="q" x="299.232" y="651.184" width="240.463" height="250.863" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="r" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="r" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="s" x="386.638" y="597.6" width="339.362" height="279.331" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="t" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="t" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="u" x="266.523" y="542.39" width="218.143" height="228.124" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="v" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="v" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="w" x="14.258" y="26.296" width="464.837" height="340.682" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="x" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="x" />
															  <feComposite in="SourceGraphic" />
														 </filter>
														 <filter id="y" x="291.405" y="532.86" width="298.063" height="324.219" filterUnits="userSpaceOnUse">
															  <feOffset dy="18" input="SourceAlpha" />
															  <feGaussianBlur stdDeviation="27.5" result="z" />
															  <feFlood flood-opacity="0.161" />
															  <feComposite operator="in" in2="z" />
															  <feComposite in="SourceGraphic" />
														 </filter>
													</defs>
													<g transform="translate(-129.225 -1667.866)">
														 <g transform="translate(171.002 1676.398)">
															  <g transform="translate(-0.001 0)">
																   <path d="M419.9,883.276l-.5-1.016.882-2.605-1.78-1.711-1.238-3.253.825-5.247-.489-.425-.692-.886v-1.367l.017-.842-3.7-4.58-2.076-1.015-.942.368-.576-1.99-.752-.279-1.015-.457,1.573-1.412-.781-2.051.458-2.723-2.02-1.651-3.2-.987-1.959-2.767.339-2.7-.679-.263-1.647-.987-.384.441,1.663,2.872-.972.971.073,2.917-.442,1.4-.263-5.494-1.444-4.7-.78-6.125-.826-.457.971-3.107-.429-2.4-2.12-.558-2.311-3.641-2.906-2.221-1.443.073-.292-2.2-1.165-1.384-10.785-7.675-.959-1.517L373,809.138l-1.83-1.505-1.546-.041.279-1.181-5.567-5.126,1.015-.012-1.484-1.343.381-1.574-.87,1.161-.721-.485-.178-.809.869-.267-.323-1.489-2.048-.793-1.121-2.638,2.9-4.641,5.232-3.018.329-.71-.329.634-5.232,3.022-2.9,4.64-.586-2.755-.4-.485-1.4-1.812.044-.72-.47-.842-.547-.546-.752,1.121-.529-.514.529-1.7-.676-.356-.708-.955.223-1.282-1.076-1.283-.8-3.006.073-1.177-.247-2.076-1.21-1.177.223-1.784-2.08-1.562-.926-1.914-3.33-.134-1.563-1.222-.279-2.387-2.565-3.2,1.83-7.205-2.3-.591-1.7-1.351-1.768-4.021,1.9-4.422-.295-2.488-4.5-1.914-1.636-3.876.562-2.225-.663-2.12-1.21-.854-.825-2.007-2.326-1.193.44-.631-.708-2.99.74-4.082-1.8-1.93-3.343-.825-4.111-3.107-.412-1.5,1.534,1.368.5-3.091-1.664-4.628-2.1-2.415-3.035.841-1.489-.473.264,1.149,1.634.769-.44.409-1.987-1.234-.636-2.209-2.091-1.048-.442-2.945-1.917-.854.618-1.857-.6-1.651-8.386-5.761-4.39-1.4-1.826-2.99-.958-.073.206-1.841-3.375.1-2.137-2.237-.753-2.181-2.428-.87-2.785.384.089.752-1.107.19-3.092-2.3-1.68.3,2.255,1.133.412,1.092-3.937-1.873-7.588-5.729-2.593-2.873.857-.8-1.578-1.1.268-1.578-1.417.457-1.032-1-1.489-1.946.34-.546,1.474,1-.587-1.724-1.461.133-2.178-2.917-2.42-4.45.77-1.133-.251-1.092-2.137-1.323-.223.943.886,1.428-.857-.324-1.178-3.2,1.311.631.5-.3-.779-.631L252,648l-.987-.178-.813.279-3.019-2.533.368-1.93-1.238-1.133-1.9-.105-1.824-2.856.235-1.768-1.947-3.463-2.444-1.473-1.312-6.247-1.222-1.032-1.873-3.681.161-1.149,1.283-.574,1.9-1.327,1.033-2.751-1.8-1.505.19-1.206-.384-1.092,1.326-.146-1.355-1.974.368-.574h-.87l-.353.425-1.148-.983-1.372-8.945-2.107-4.6-4.771-6.23-.562-2.739.578-.692-.752-2.124.866-1.145,2.019-.417-1.5,2.771.766.943.68-1.295,1.165.251.292-4.054,1.8.15-.353-1.266.826-3.257-.5-1.016-.826,4.2-.854-.38,1.178-2.872-.47-2.169,3.314-6.17-.029-2.5.648-1.06,1.239-.267.468-2.031-1.72-.016-.223,1.355-.485-.279-1.063-.842.708-3.152-.146-5.3-2.771-4.641-.576-2.593-1.723-2.1,1.314-1.327-1.371-.1-.636.453-6.289-10.5,1.915.724,2.019,3.3-.279.7,1.753.307.748-1.06-2.327-3.313-.072-2.2h-3.375l.473-.781-1.313.146-.382.518-2.12-3.224,1.089-2.432-1.076-5.6-.368-1.121-1,1-2.477-3.609-.134-1.974-.485-.575-.34-.223-1.445-3.285-.1-3.827,7.542-.591,4.537-1.355,1.209.5,3.14,5.037,3.565,2.727,5.039,6.008,2.477,1.918,2.281.336,7.192-1.635,2.326.546.635,5.684,1.032.858,15.381-4.79,7.014-.457,1.578,2.051.412,4.786-.266.7.295-.768-.413-4.79-1.578-2.047-7.014.457-15.382,4.786-1.031-.854-.632-5.684-2.33-.546-7.191,1.635-2.282-.34-2.459-1.914-5.039-6.008-3.565-2.711-3.127-5.041-1.207-.5-4.536,1.355-7.547.587-.308.044-.178.028-.033-.168-.039.006.039-.006-.91-4.6-1.942-1.946.308-1.545-1.428.587-.96-.514-.19,1.1,1.239.591-.547.587-1.267-.736.146-2.225-.839-1.987.19-.765-1.148-.591-1.032-1.21-1.4-4.106-.013-2.4-1.506-1.651-1.355-.133-1.282-2.3-3.55-2.427-.854-1.517-.65-.413-1.323-.9-.485.9-1.032-.429-.489-.647-1.618-1.473.558-1.327-1.473.6L185,472.638l-2.678-1.752.234-.457-.915-.5-1.663.68-5.365-4.183.913-.752-.869-.429.854-2.472-2.049,1.117.664.563-.647,1.869L171,466.04l-4.051-3.034L165.2,459.9l-1.165.235-.558-1.048-1.017-.206-.133-1.222-2.286.886-1.529-.53-.118,1.194.794.235-.547,1.339-2.222.073-.975-1.44-1.725.129-2.68-3.989-2.2-1.578.457-2.682.987.679.381-1.651-1.736-2.164-.619-2.136-1.479-1.252-.01.059-2.387-4.272.21-1.015-1.238.485-.825-2.415-4.1-2.225.489-.664-1.137-2.444,1.238.1L139,430.555l-1.355-.518.62.6-1.15.858-1.959-2.622,1.089-.886,1.991.441-1.312.647,1.769.546.515-1.736,1.7-1.388-1.02-2.193,1.962-4.584-2.136-4.167,1.161-2.152-.866-3.86-6.883-7.687-2.017-5.142.13-2.314-3.977-3.224.06-2.622-1.371-.368-.324-3.051-1.578-2.31-3.107-1.343-3.286-4.685-.575.016.781,2.225-.547.263-.9-.089-.782-1.311-1.326-.089-1.618-1.266.263-1.562-.721.574-.664-.765-.854-2.828-1.411-.441,2.384-3.771.942-3.4-.352-2.861-2.64-1.651-4.078-.234-.413-.251-3.463-2.885-1.607-5.438-4.523-7.351-3.2-8.4L88.464,328.7l-2.979-3.2-1.5-2.917-2.577-1.428-2.639-6.2-.234-2.549L74.1,307.52,71.253,305.9l-.425-1.193-4.541-4.54-.631-3.091,1.489-1.562-.458-1.7-3.39-5.567-3.772-1.93-3.815-4.361-.267-2-2.636-3.536-.591-4.568-2.18-2.783-.56-2.933L47.972,263.4l-1.25-1.266L44,261.31l-.279-3.7-3.671-2.945-.955-2.387L34.2,249.747l-.279-.854,3.209-.959-.1-.87-2.946-.53-1-.87-1.915.089-2.5-2.444-.826.324-1.327-.886v.384l.753,1.194-1.622-.959L22.1,245.313l-3.315-.251-.784.6-.336-1.412,1.5-.825-2.444.162-.857,1.986-.4-.619.942-1.647-.825.117-.793,1.076-1.506-.239.647,1.873-1.412.028.647-2.476-1.986-.279.47-.486-.648-1.222-.368.72-1.06-.441-1.667,2.3L8.78,245.9l-1,.206L6.733,248.23l-1.15-2.136.3-1.885-.781-.324-1.105.737L4,243.456l2.89-3.726,1.428.53L7.7,238.726l.886-.631,1.118-3.742-.5-.117L14.086,222.2l1.7-2.549-.4-5.171-1.134-2.844,2.829-8.484.044-4.345.576-4.361.942-1.869-.6-2.448,1.205-1.987.15-2.533.972-4.155,1.088-1.121-.263-1.209,1.529-.9-.7-2.828,1.192-.676-.355-1.266.9-2.007,56.3,10.005,21.586-17.781L114.674,141.9l37.957-7.6,8.519-19.416,16.676-9.989L127.349,46.388,176.6,31.917l.054-.263,51.616-15.188,15.5-13.509L243.106,0l1.712,2.266,3.432,2.079L275.6,12.327l8.943,4.183,5.037,3.4,130.68,92.3,97.321,67.248,10.311,2.387,66.484,10.43-18.36,6.465-12.156,12.845L545.2,246.474l-13.479,17.9-1.9,6.882.574,2.237,2.165,2.695-1.915,3.285L528,280.737l-1.94-.667.047.052,2.222.765,2.655-1.266,1.888-3.24-2.094-2.594-.56-2.253,1.886-6.878,13.483-17.9,18.652-34.87,12.172-12.845,18.343-6.465,7.516.19,3.888-1.869,6.026-4.657,46.192,6.057,1.282.987,4.95,7.926.9,3.755.326,6.793,5.2,6.922-.251,1.032,36.8-.635,1.739,1.679,1.886.692-.06,3.317,1.237.971-.263.5,1.25.219-1.485,4.434,1.43,2.917,2.165,1.517,2.5,3.285-1.015.781-.269,3.932.575,1.473,3.02,3.508,5.734,1.545-1.138,1.355-1.235-.117.118,1.549,2.344,2.666,1.473.146.072,1.99,2.99,1.853-.529,3.095.68.647-.118.692-1.326.886V263.3l-.813-.061.428-2.945-1.06,1.5-.425-2.075-1.918,6.481.942.117.664-1.841.514,3.508.943-1.4.618,1-.5,1.74,1.573-.457-.468.854-.77.162-.073,1.21-.469,1.015,1.646.8,1.105-1.946.93.279-.841,1.533.161,1.9,1.194-.607-.146-1.529,2.3-1.165,1.384.546,1.591,2.31,3.819,1.711,5.391-.089,2.489,1.222.9,1.958,1.65.93.3,2.678-.826-.219.369-1.015-.53-.8-1.044,1.7-.8-.9-1.047,1.044-1.1-1.06-1.093.692-.458-.663-1.015,1.355-1.21-.5-.486,1.076.6-.307,2.271,1.384.824,1.048,1.489-.356-.044-1.546,2,1.133-.9,1.121-1.077.1.474,1.355-.987,1.813,2.533-1.606,2.388.8-.017,5.466.988,1.663.8.033.618-.089.782-.34,1.68.842-.71,2.783-.958-.34-.647.6.752.016-.295.825,1.121-.457.781,1.517.987-2.549.735.514-.223,1.562,1.77-.971,2.152.752-1.314-2.476,1.873-1.93-2.549-2.326-1,.117.956-1.533-.31-.781,1.089-.692.681,2.9,1.635.089.339,2.213-1.091.453,1.428,1.416,1.326-1.032.871-3.961,1.429-1.93-1.457,4.345-.652,2.3L765.4,298.7l.81,2.888,1.724,1.707,3.536-2.1,1.578.6-.044.134-.721-.016-.134.061-.4.692-.4-.692L768,303.677l1.412,1.813,2.2-.53-.238,1.076-.821.028.027,1.752,1.049.045.352,1.254.19.291,3.392,2.666.542.162,1.784,1.076,1.667.546,1.161.9,4.392.441,3.051,3.257,6.617,7.792.72.663.606.591-.873.129-1.915-2.549-2.477-.631-1.44,1-1.812-2.876-.09-1.295-.69,3.3.218,1.4,1.193.292-.513,1.606.456,3.92,1.368,3.431,2.08,2.711.146-1.7.458-.453v0l.558,1.46.68-2.4.66.162-.086,3.524,1.162.647,1.757-2.593-.534-.486.562-2.666.368.574.179,1.562.559-.9.072,1.736-1.44.858.117,1.545-.931.162,2.432,2.237.765,2.387-.792,6.04.663,1-.534.044-.146,2.177-1.473,1.667.279-1.149-1.283-1.8-.235,3.3.619.769-1.221,3.123.278,1.914-1.1.619-3.861-8.779-1.121-.8-.765.473-1.694,4.111,1.311,1.9-.765.987.06,4.45.9.235,1.181-1.355,1.194,1.088,2.02.15.736,1.1-.222,1.165.93.837-1.02.235-.546,1.533,1.89,4.288,2.667,3.945,1.605.975,1.032,2.974,1.1.591-.336,1.295-.606.267-.587-2.165-2.093-.49-1.473-2.165-.812.251-.693-.854,1,2.561,1.606,1.121.073.825L797.6,381l.324.413.352,2.65,1.886,1.711.986,2.148,1.858.647.1,1.622,3.006,3.742.591.057-.072-1.618-1-1.339.975-.781.911,2.415,4.76,3.666,1.962,5.951-.5,3.552,1.842,2.99-.607,2.621,1.227,3.771-.4,1.3,1.311.664-.736-.959.473-1.21,3.715,5.2,1.547,4.859,1.4.061.792,1.015,2.2,9.621.944-2.711,2.326-.445-.369.708,1.591,3.139,5.438,6.449,6.746,1.181,3.614-1.416,2.4-2.65,1.709.575,1.477-.486-.15.749.883.651,1.121-.518-.972,1.885.19,3.564,2.137-1.279.547-2.047,2.358-1.917-.134-1.4,1.312.781.781-.631.6,2.415,1.519-.854,1.121,1.117-.915.943-1.78.105-.8,1.4-.646-.235-.619,2.177-4.524,3.6-.883,4.552-.824.19.188,2.5,3.3.619,1.324-1.736,2.99-.267,2.464.971,2.223,3.123,2.4-.1,1.105,1.736-.752,6.234,50.538,61.321,35.052,7.015,87.982,20.269,10.384-10.988L1075,584.329l-33.847,107.887L888.8,745.838l-58.879,11.7-85.267,9.3-47.281,23.1-30.812,37.71-7.175,17.987-2.254,1.367,3.816-36.015,11.771-93.525,28.731-225.133.032-11.417-2-10.163-.023-.047,2,10.1-.029,11.417-28.73,225.149-14.925,3.872,14.925-3.872L660.92,810.89,657.1,846.9l-11.023,6.955-10.359-.53-12.051-16.264-15.765,2.253L588.87,838.7l-25.39-3.475-9.209-.619-9.207-5.935H515.993l-2.047,1.444-2.874.4-3.893-2.047-3.978.174-12.949,1.651-1.21,2.241-1.591-1.974-4.347.781-2.019,2.828-3.845-1.06-2.358.631-2.9-.425-1-1.311-2.974.53-3.809-3.97-.068,1.109-3.491-3.386-7.795-4.964-3.625-.5-2.806,1.368.021.032,1.43,2.047-2.829,4.3.134,3.492,2.282,1.327v.959l1.2.514.381,1.016-6.616,2.079.619,2.755-2.237,2.015.072,2.14-.915.469-.089,1.295.133.518,1.445-.591,2.4.708,1.21,2.415-.81,1.339-3.347-.38.386,4.139-2.21,1.032-.061.882,2.137,2.783,2.282.5.489,1.222,1.484.708-.322,1.857.708,1.266-2.108.162-.47,1.193.721,3.831-1.235.218-3.051-1.635-.975.267.65,1.323-1.521,4.038.076,1.562-6.515,1.711-2.461,1.752.619,3.993L427,884Zm44.173-69.765,1.914,8.735Zm-25.919,2.549,1.017.842,4.334.88-.017-.026-4.347-.886-1-.837-.573-1Zm-27.348.089,2.239.927,2.08-.206.824-.971,2.461-9.944,1.78-3.091,4.232-3.184,3.252-1.294-.014-.017-3.254,1.295-4.229,3.184-1.785,3.095-2.444,9.94-.841.975-2.063.206-2.239-.93-4.787-4.653Zm18.154-9.6,4.793,2.423-4.793-2.425Zm-39.37-2.974,12.2.587-12.2-.591-1.076-.943Zm39.548-3.55v0l-.84-.939ZM464.159,790l2.031,9.014Zm-86.008,6.7h0l-1.093-.691Zm1.088-.166,3.253-.5h-.007Zm-2.588-2.735.411,2.209-.411-2.211Zm1.205-10.713-1.172,10.416,1.188-10.416L376,781.574Zm86.421,4.462L464.159,790l.118-2.444,1.294-2.492Zm-94.759-5.293,6.484-.687-6.483.682Zm-19.742-32.077.9,4.373,1.5,2.181,2.125.708,9.887.312,3.107,1.78,4.341,13.31.006-.024-4.375-13.464-3.111-1.764-9.887-.312-2.1-.72-1.518-2.169-.87-4.215Zm153.029.1-1.43,3.273,1.43-3.269.25-3.123Zm-2.343-15.746,2.593,12.627Zm20.937-7.97,7.693,3.524,4.774,1.06,13.039,1.032-13.039-1.032-4.774-1.06-7.693-3.524-12.537-8.233ZM328.472,714.828l1.913,2.27,5.439,3.14,3.034,4.507,2.343,1.545h3.816l3.254-1.59,3.8-13.229,3.538-6.57,2.237-.87,6.795-.057,1.161-.736,2.226-1.092,3.375.194,2.7,2.736-.014-.1-2.727-2.787-3.375-.19-2.21,1.088,6.275-12.8-6.275,12.8-1.166.752-6.807.061-2.237.854-3.537,6.57-3.8,13.242-3.254,1.59h-3.8l-2.343-1.546-3.035-4.507-5.422-3.14-1.893-2.239Zm44.039,2.711,3.171,6.432.01-.036-3.173-6.436ZM320.176,689.89l.987,1.032,5.39,1.606,1.4,2.2,0-.076-1.429-2.237-5.378-1.622-.987-1.031Zm158.6-5.45.324,2.354,4.051,4.361-4.051-4.361Zm-105.823-2.019-1.238,2.638ZM383.06,674.439Zm-3.184-5.247,3.022,2.9-3.019-2.9-.341-1.161Zm-.337-1.161,3.151-9.4Zm-37.807-11.239,1.617,6.615v-.032l-1.611-6.606Zm12.123,2.828,0,0-.968-2.849Zm120.38-12.627,4.525,7.262,5.3,5.3-5.3-5.3-4.525-7.262-8.48-7.375Zm-20.965-52.016.133,1.682-.132-1.685Zm1.8-27.794L457.7,586.47l0-.005-2.638-19.29Zm.96-11.4.453,2.31-.453-2.31L446.52,553.7l-8.323,2.164-5.451-1.885,5.451,1.885,8.323-2.164ZM273.291,538.353l.089,1.266.866,1.092,5.867,1.736,3.079,2.816.664,3.4-1.226,5.7.959,2.326,7.012.825,3.437-1.651,6.818-6.036,10.008,3.253,2.387-.028,6.584-9.811L333.6,532.758l7.544-7.841,3.609-2.605,8.355-3.4.826-1.121-.016-.059-.781,1.062-8.356,3.4-3.61,2.605-7.543,7.853-13.763,10.491-6.587,9.807-2.385.032-10-3.257-6.823,6.04-3.435,1.651-7.014-.825-.955-2.326,1.223-5.688-.663-3.4-3.08-2.812-5.863-1.74-.871-1.088-.088-1.258Zm157.6,12.226h0l.026-.515Zm-38.383-36.1,7.588,16.587,4.435,2.4,20.776.931,4.553,1.25,2.711,2.771-2.711-2.771-4.553-1.25-20.776-.931-4.435-2.4Zm-24.268-21.7-1.562,2.254-17.312,8.6-5.292,4.228.011.089,5.253-4.2,17.312-8.589,1.563-2.253,1.015-3.253v-.037Zm11.509-10.2.53,4.612,6.115,4.859,1.663,7.161,2.488,5.561-2.474-5.561-1.663-7.161-6.13-4.859Zm-11.509-4.948-.854,3.415,0,.008.823-3.276,4.771-1.517,3.839.5L373,476.118ZM380,435.709l5.952,4.933.013-.1-6.007-4.974Zm37.617.882,8.353-1.311-8.355,1.311Zm18.754-11.518,2.815,5.644ZM362.9,413.126l2.565,2.978,7.807,2.047,2.08,1.663L378,428.88l-2.692-9.229-2.063-1.663-7.822-2.047-2.517-2.9Zm296.115-53.986.559,33.333-.926,22.2.513,10.919.016.083-.5-10.882.931-22.2-.562-33.325-.631-7.307-.026-.062ZM276.739,396.052l.926,2.371,1.915,2.2,2.3,1.044,2.286.044,13.808-4.77,11.873-.574,3.553-1.74,5.7-6.833,4.816-1.845,9.845-.218,17.385,3.4,10.2,2.694,1.979,1.7-.021-.143-1.985-1.707-10.2-2.7-17.385-3.4-9.856.222-4.821,1.841-5.7,6.837-3.553,1.736-11.873.574-13.808,4.774-2.281-.044-2.3-1.032-1.919-2.193-.881-2.258ZM156.965,366.207l.987,2.416,3.2,2.727,5.479,4.907,2.65,5.713,2.638,9.313,1.093.453,4.671.194,1.613,2.011,0-.053-1.647-2.063-4.674-.19-1.088-.457-2.638-9.309-2.652-5.717-5.482-4.9-3.2-2.727-.968-2.367Zm359.146,3.2,3.714,7.161.457,4.657-.457-4.657Zm-140.594.87,3.536,7.044ZM388,356.178,389.2,365.5Zm113.614-14.791.725,2.241,5.3,5.255,9.854,14.969.205,1.266-.205-1.266-9.854-14.969-5.3-5.255ZM151.806,360.244l3.287,1.707,1.071.911-.024-.1-1.076-.9-3.286-1.7-5.908-3.71-2.258.251-2.856,2.606.016.088,2.873-2.622,2.254-.251Zm-15.13-12.4,2.47,4.634-.01-.038-2.479-4.644Zm140.192-4.523.5,1.091,0-.059-.542-1.165-1.078-.352-4.819,1.222-1.116-.352-1.712-2.173v.094l1.739,2.213,1.134.352,4.819-1.238ZM502.215,325.39l.636,7.541Zm-374.94-3.6,1.474,2.331,2.358,1.93,7.042,1.986,2.258,1.392-.02-.065-2.271-1.4-7.041-1.987-2.36-1.93-1.45-2.294Zm142.966,4.333.961,2.2,0-.019-.978-2.24-.009.01Zm-31.091-.385,9.683-.765L264.227,322l2.258-.1,1.094.61,0-.007-1.134-.631-2.254.117-15.4,2.99-9.651.762Zm-45.469-8.2,0,.019.632-3.51-.007-.031Zm21.6-7.278-.618,1.076.457,2.225.758,2.192.035-.028-.765-2.209-.458-2.209.6-1.049Zm-26.922-2.148.006.03.293-2.328-.008-.01Zm329.593-12.756,5.3,4.4,1.975,3.686.22,3.682-.22-3.682-1.975-3.686-5.3-4.4-3.464-1.372ZM226.7,238.33l2.063,1.8,1.663,1.946,3.477.764,6.014,1.06,2.267.356,7.3,1.93,4.33,3.342,1.917,2.359,3.4.987,1.1.457,1.959,3.564,1.15.676,3.479,1.46,2.076,2.415,1.534,3.7,3.3,13.8.575,2.4.206,1.355,2.063,5.761,5.5,3.168,1.194.53,4.362,2.71.515,1.117-.418,3.483,0,0,.428-3.552-.529-1.121-4.35-2.711-1.207-.53-5.5-3.18-2.063-5.761-.206-1.355-.575-2.4-3.3-13.8-1.55-3.7-2.061-2.415-3.491-1.461-1.138-.676-1.959-3.581-1.1-.457-3.4-.987-1.917-2.342-4.33-3.342-7.3-1.914-2.267-.356-6.014-1.06L230.428,242l-1.663-1.962-2.08-1.78-2.4-.724-5,1.473-5.243,2.771-2.55.117-1.913-1.59-1.15-3.637-1.108-.312-2.412.473-1.138-.7L201.5,234.28l-1.178.057-3.538,2.577-14.924-1.736-4.893-1.622-3.565-1.78-5.114-5.231-1.105-2.4-.837-3.9-.457-6.469.663-10.592-.681-2.415-2.31-1.384-1.137.6-4.229,4.244-2.328,1.06-1.857,2.165-3.8,8.929-2.315.837-12.6-1.044-6.806-2.654-7.6-1.06-6.588,1.238-6.467,1.517-4.832,2.019-2.667.486-2.536-.943L88.625,204.8,86.9,202.388l-2.93-3.492-3.7-2.844-7.486-2.27,7.486,2.286,3.7,2.84L86.9,202.4l1.724,2.415L97.8,216.794l2.536.943,2.667-.473,4.832-2.031,6.467-1.517,6.575-1.222,7.6,1.06,6.808,2.65,12.6,1.06,2.311-.837,3.8-8.913,1.854-2.181,2.33-1.06,4.229-4.244,1.133-.6,2.315,1.4.676,2.416-.66,10.592.453,6.465.843,3.892,1.1,2.416,5.127,5.227,3.565,1.784,4.876,1.618,14.94,1.74,3.538-2.577,1.165-.061,2.267,1.857,1.152.692,2.415-.457,1.105.312,1.165,3.637,1.9,1.59,2.55-.129,5.243-2.771,5-1.456ZM123.883,296.548l2.095,2.017-.014-.059-2.108-2.035-2.271-.263-4.6,1.016-1.167-.692.006.044,1.19.708,4.6-1Zm389.605-5.336h0l.09-2.339Zm29.484-.676.03.037-2.05-7.433-.011-.011Zm-52.129-29.578,14.7,1.9,4.594,1.765-.09-.053-4.832-1.857-14.605-1.888Zm-35.834-29.02,3.711-1.238,7.442-4.98,2.193-.292-.211-.165-2.315.312-7.441,4.976-3.684,1.229Zm-19.819-4.6,1.415.457,9.521-.455-.29-.151-9.562.457-1.146-.37Zm-21.012-7.836,5.88-.312,6.807-3.653,3.77-.66-.151-.181-3.949.7-6.808,3.653-5.812.3Zm-13.61-12.736-.115-.1L379.135,201.5l-6.559.53-7.014,1.99-17.931-5.2L316.748,206l-3.492-.546-6.118-2.655,0,.005,6.455,2.8,3.491.546,30.886-7.173,17.93,5.2,7.017-1.986,6.556-.53ZM35.7,200.308l2.83-.174-2.834.173Zm6.221-3.5-3.391,3.33,3.406-3.318,4.8-1.234Zm314.822-92.258,0-.005-5.3-2.342-8.029.061-4.564-.92.138.111,4.758.959,8.028-.061ZM281.614,89.37l5.452,1.651,26.447.915,10.316-1.3-.315-.147-10.331,1.294-26.445-.914L281.533,89.3Zm-14.939-6.319,6.454.117,2.3,1-.015-.012L272.8,83.022l-6.454-.117L238.336,77.82l-11.865.206-10.679,2.007L195.78,82.225l-5.038-.057-3.166-.851.095.082,3.4.914,5.042.061,20.011-2.2,10.68-2,11.861-.207ZM382.9,875.763l.574-1.06.62.279.263-.129.5.38-.959,1.651Zm4.565-3.945-2.667-.546,1.828-.886-.134-1.853-2.649-.651-4.48,1.371-2.9-2.415-1.68-.473-1.757-1.517-1.412-2.8-1.962-.045.68-1.845-1.474-1.189-.06-1.562,4.673,2.711,2.165,4.58,3.46.987,2.343-.53,1.461,1.974,2.355-.692-.413-1.327-.647-.105.413.914-1.238.061-.631-.9.765-.858.148-1.885.866.15.268-.931,1.267.15.312,2.1,1.116,1.505.62-.117.869,1.473,1.341-.312.044.752.691-.089-.117,1.388-.91.955.647,4.377-1.372.162Zm-4.9-.283-.725,1.137-.822-1.723.514-.825,1.238-.162.53.781-.619,2.209Zm-8.431-1.016-.913-1.283,2.095,1.562Zm-6.247-3.3-.956-1.121,1.166-.5,2.841.736,1.667,2.4Zm12.289-3.977-4.447-1.477-2.7-3.843.018-1.59,2.415-.3,1.383-1.93,1.68,1.149-.821,1.384.854,1.355,2.031.619-.188,1.283-1.919-.469-1.089.866,3.226,2.448,1.092,1.529-.411.663Zm-20.229-2.46.307-.49-1.1-1.015.116-2.108,3.023,4.495Zm22.382-.708.721.5-2.388.206Zm-18.963-4.216,1.647-2.075,2.225,1.618-1.015,1.711-1.475.457Zm12.168-3.977,2.416.987.619,2.079Zm-4.86,2.342-.323-1.707,1.663.955-.691.842ZM130.59,420.889l.428-1.206,1.769,1.234v.975Zm-8.916-22.126.118-1.724-3-1.635.527-1.165,1.355.886.619-.312,1.417,3.949Zm6-4.066-1.841.061.6-1.606-.987-.635.591-.19.958.235.559,1.873.81-.267-.384,1.222Zm-3.464-3.4.636-1.133,1.328,1.282Zm679.961-2.65-1.489-.886.057-1,1.8.781.515,3.047Zm-681.829-.073.133-2.2,1.384,3.447Zm-3.89-.4,1.06-1.206.826,1.368-1.255.825Zm3.917-2.65.708-.4.6.651-.381.91Zm-21.436-2.593,1.162-1.032,2.047,2.371-2.281.15Zm2.959-5.082-.029-2.46.692,2.638,1.547-.93-.146,1.869.987.785-1.089.1ZM789.9,330.957l-1.489-.178.312-.955,1.708-.53,1.281,1.015-.072,2.359Zm-30.031-39.345-.322-1.149.809-2.286,1.032-1.278,1.975-.53,2.577,1.545L772,289.5l-2.755.073-.765-.38-3.493,1.1-.663-.886.663-.368-.161-.72-1.724-.809-3.142,3.447.5.837ZM9.634,252.9l-.663.044-.085,1.21L8,253.991l.352-1.327,1.68-.68,1.3.279.882,2.257-1.5.631Zm-9.149-.016L0,249.7l2.152-.854.705,1.044-1.812.724-.661-.473.029.563,1.619,1.234,2.845-.5-.146,1.193,1.725,1.574-3.509.518Z" transform="translate(-0.001 -0.398)" fill="#fff" stroke="#fff" stroke-width="0.5" opacity="0.6" />
															  </g>
														 </g>
														 <g transform="translate(212.002 1732.516)">
															  <g transform="translate(0)">
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#a)">
																		<path d="M502.065,555.151l-12.435,3.228-20.119,2.934L397.27,567.5l-10.864-.859-3.977-.884-6.408-2.934-10.446-6.862h0L348.955,541.12l-4.8-7.807-3.376-3.634-.27-1.964,1.51-7.5,4.1-7.476.1-3.633-1.3-2.124-4.419-4.419-3.768-6.052-7.071-6.15h0L324.33,481.7l-5.855-7.807-.712-1.8,2.161-10.41-.663-8.531,2.86-4.2.835-2.872-2.2-16.081,1.178-7.562-.381-1.927-7.918-1.731-6.935,1.8-4.542-1.571-1.547-2.823.344-6.555,1.056-3.6-2.259-2.308-3.793-1.043-17.308-.773-3.7-2-6.322-13.822-1.117-6.923-2.578-5.794-1.387-5.966-5.107-4.051-.442-3.842.54-4.468h0l-.049-11.085,1.731-5.99-1.154-4.419,3.621-5.217.491-3.854h0l3.732-6.236,2.664-1.94,1.841.147,6.346,3.13,4.751.994,7.034.614,6.96-1.093,6.985,1.068,2.983-2.2,1.043-2.676-2.345-4.7.184-2.823,6.653-8.212,4.014-3.093,15.811-3.867,3.486-3.449L339.122,288l2.8-5.487,2.48-2.418,3.842-1.007,10.189,1.3,8.838-.835,6.788.27,1.031-4.787-.38-3.879-3.093-5.966,1.326-3.572-.172-1.056L364.557,248.1l-4.419-4.382-.6-1.866,1.031-7.046-.528-6.285,4.922-1.35,5.524-7.206,3.02-2.59,5.131-2.259.761-1.817-.184-3.069-1.645-3.069-4.419-3.67-2.885-1.142h0l-.835-2.308.074-1.952,1.215-3.805,2.971-4.076,3.867-2.013h0l2.381,2.6,1.854.638,2.21-1.056,1.6-2.737h0l4.076,2.848,2.6,2.713,1.719,6.273,1.522,1.878,12.779,7.463,7.058,1.3,4.247,5.18,4.984,4.149,2.872.773,6.923.135,3.7.994,12.1,8.519,3.056,1.461,2.9.061,5.413-1.24,1.9.27,7.918,7.463,1.8,3.093,10.9,3.879,4.738,2.553,1.338,3.142.528,6.089.466,27.779-.773,18.5.43,9.1.835,4.542,1.375,2.2,2.8,2.21,11.588,7.733,11.281,11.244,2.062,2.529,3.621,7.1,1.669,8.458L526,367.535Z" transform="translate(141.14 107.28)" fill="#025635" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#c)">
																		<path d="M143.707,348.451h0l.147-.025Zm140.847-24.293-.54,4.468.442,3.842,5.094,4.051,1.387,5.966,2.59,5.794,1.117,6.923,6.322,13.822,3.7,2,17.308.773,3.793,1.043,2.259,2.308-1.043,3.6-.356,6.543,1.547,2.835,4.542,1.571,6.936-1.8,7.918,1.731.38,1.927-1.178,7.574,2.2,16.081-.835,2.872-2.86,4.21.675,8.519-2.16,10.41.712,1.8,5.855,7.807,5.327,8.666h0l-4.935,1.8-7.009,4.628-6.408-1.6-2.774.307-3.879,1.866-5.585.344-5.229,4.677-7.868,3.756-12.41,11.343-2.836,1.4-2.811-.295-1.719-1.252-5.733-11.134h0l-2.43-2.369-1.817-.27-6.653,3.388-5.107-.246-5.659,2.173-2.345-1.792L260.9,471.6l-1.5-1.78-1.755-.749-2.774.393-1.669,1.731-.859,2.6,1.363,5.573-.147,2.9-3.3,5.684-1.682,4.37-9.489,2.283-4.91,5.033.209,1.743.822.859,4.493,1.338,1.191,1.878-.381,6.482,1.117,4.775-.331,5.45,1.6,1.89,4.53,2.615,2.529,3.756,1.952,1.289h3.179l2.713-1.326,3.167-11.023,2.946-5.475,1.866-.724,5.659-.049.97-.614h0l1.854-.908,2.811.16,2.271,2.308.258,1.866-1.608,8.494,2.688,5.45-1.559,5.647-.933,1.743-1.866.921-5.819-.528-2.75.454-4.763,3.462-.921.073-2.418-2.3-.6,12.275.749,3.646,1.252,1.817,1.768.589,8.237.258,2.59,1.485,3.658,11.22-1.841,7.868h0l-1.24,2.381L269.86,583.4l-2.418,3.867h0l-.491-2.3-.331-.4-1.166-1.51.037-.6-.393-.7-.454-.454-.626.933-.442-.43.442-1.412-.565-.295-.589-.8.184-1.068-.9-1.068-.663-2.5.061-.982-.209-1.731-1.007-.982.184-1.485-1.731-1.3-.773-1.6-2.774-.11-1.3-1.019-.233-1.989L251.57,560.8l1.522-6-1.915-.491-1.412-1.129-1.473-3.351,1.583-3.683-.245-2.074-3.744-1.6-1.363-3.229.466-1.854-.552-1.768-1.007-.712-.687-1.669-1.939-.995.368-.528-.589-2.492.614-3.4-1.5-1.608-2.787-.687-3.425-2.59-.344-1.252,1.277,1.142.417-2.578-1.387-3.854-1.755-2.013-2.529.7-1.24-.393.221.958,1.363.638-.368.344-1.657-1.031-.528-1.841-1.743-.872-.368-2.455-1.6-.712.516-1.547-.5-1.375-6.985-4.8-3.658-1.166-1.522-2.492-.8-.062.172-1.534-2.811.086-1.78-1.866-.626-1.817-2.025-.724-2.32.319.074.626-.921.16-2.578-1.915-1.4.246,1.878.945.344.908-3.278-1.559-6.322-4.775-2.161-2.394.712-.663-1.314-.921.221-1.313-1.178.381-.859-.835-1.24-1.62.282-.454,1.228.835-.491-1.436-1.215.11-1.817-2.431-2.013-3.707.638-.945-.209-.908-1.78-1.1-.184.786.737,1.191-.712-.27-.982-2.664,1.092.528.417-.246-.651-.528.356-.307-.822-.147-.675.233-2.516-2.111.307-1.608-1.031-.945-1.584-.086-1.522-2.381.2-1.473-1.62-2.885-2.038-1.227-1.092-5.2-1.019-.859-1.559-3.069.135-.957,1.068-.479,1.583-1.1.859-2.3-1.5-1.252.16-1.007-.319-.908,1.1-.123-1.129-1.645.307-.479h-.724l-.295.356-.957-.822-1.142-7.451-1.755-3.83-3.977-5.192-.466-2.283.479-.577-.626-1.768.724-.957,1.682-.344-1.252,2.308.638.786.565-1.08.97.209.246-3.376,1.5.123-.295-1.056.687-2.713-.417-.847-.687,3.5-.712-.319.982-2.394-.393-1.8,2.762-5.143-.025-2.087.54-.884,1.031-.221.393-1.694-1.436-.012-.184,1.129-.405-.233-.884-.7.589-2.627-.123-4.419L159.6,384.16l-.479-2.16-1.436-1.755,1.092-1.1-1.142-.086-.528.38-5.242-8.752,1.6.6,1.682,2.75-.233.577,1.461.258.626-.884-1.939-2.762L155,369.393h-2.811l.393-.651-1.093.123-.319.43-1.768-2.688.908-2.025-.9-4.665-.307-.933-.835.835-2.062-3.007-.11-1.645-.405-.478-.282-.184-1.2-2.737-.086-3.192h0l6.285-.491,3.781-1.129,1.007.417,2.615,4.2,2.971,2.271,4.2,5.008,2.062,1.6,1.9.282,5.99-1.363,1.939.454.528,4.738.859.712,12.815-3.99,5.843-.381,1.313,1.706.344,3.99-3.253,8.507.074,1.056.724.908,4.886,1.448,2.566,2.345.552,2.836-1.019,4.751.8,1.939,5.843.688,2.86-1.375,5.683-5.033,8.335,2.713,1.989-.025,5.487-8.175,11.465-8.74,6.285-6.531,3.007-2.173,6.96-2.835.687-.933-.491-1.927-6.567-3.609-.933-.81-.245-1.829,4.407-3.523,14.423-7.157,1.3-1.878.847-2.713-.2-3.953-1.362-3.093.712-2.848,3.977-1.264Z" transform="translate(115.13 138.16)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#e)">
																		<path d="M142.695,354.643l-1.1-.749-.405.749-.859-.356-.405-.54-1.35-1.228.466-1.1-1.228.5-1.264-1.878-2.234-1.461.2-.381-.761-.417-1.387.565-4.468-3.486.761-.626-.724-.356.712-2.062-1.706.933.552.467-.54,1.559-2.062-.233-3.376-2.529-1.461-2.59-.97.2-.466-.872-.847-.172-.11-1.019-1.9.737-1.277-.442-.1.994.663.2-.454,1.117-1.854.061-.81-1.2-1.436.11L108.25,335.8l-1.829-1.313.381-2.234.822.565.319-1.375-1.448-1.8-.516-1.78-1.277-1.08h0l.675-3.977,1.166-1.8,6.936-.589h0l2.9-1.117h0l.97-.872h0l1.436-2.222h0l.675-.872h0l1.9-.5,2.836.552h0l3.167-.037,1.976-1.019,1.129-1.89.27-1.915h0l-.135-3.9h0L130.333,294h0l1.031-5.855h0l.123-.908h0l.307-2.823h0l-1.375-1.719h0l-3.891-.159h0l-.908-.381h0l-2.2-7.758h0l-2.21-4.763-4.566-4.088h0l-2.664-2.271-.822-2.013h0l-.663-2.786-.9-.749h0l-2.737-1.412h0l-4.922-3.093-1.878.209h0l-2.394,2.185-.344-1.915h0l-.994-3.83-2.075-3.891h0l-.822-2.062h0l.2-2.86h0l3.867-6.8.319-2.16h0l-.442-1.448h0l-1.89-1.166h0l-5.868-1.657h0l-1.964-1.608h0l-1.228-1.939-.847-3.228.994-10.962h0l-1.215-5.156h0L85.6,203.24h0l-1.89-.221h0l-3.83.847h0l-.994-.589-.11-.921,1.227-1.8h0l3.83-.847h0l1.989-.528h0l2.05-.994h0l1.952-.7h0l3.953-1.841h0l2.013-1.3h0l.933-.417h0l1.9-.479h0l4.861,2.038h0l2.9,2.136h0l2.971.381,2.124-.516,7.181-4.088h0l1.9-.565h0l.957.381h0l1.363,1.866h0l.982,3.879h0l.687,2.062h0l3.008,5.242h0l1.338,1.9h0l.994.687h0l1.866.319h0l6.6.295,1.227,1.522h0l-.245,1.952h0l.184.97,1.8,1.277h0l2.787,1.743h0l.184.921h0l-.528,2.946.233.921.908.417h0l.921-.012,1.927-1.461h0l1.89-1.682h0l2.075-1.584,2.025-.4,7.562,3.081.982-.786-.638-1.841h0l-.381-1.841.516-.9.921-.049h0l1.571,1.448,1.178,3.781h0l2.9,1.019h0l2.9.712,2.786,1.755h0l1.866,1.51h0l2.971,2.247,2.774.5h0l8.065-.638h0l12.828-2.48h0l1.878-.086h0l.945.528h0l1.5,1.755h0l.687,1.24h0l.835,1.915.295,3.265-.921,2.971-1.989,2.762v2l1.449,1.841.945.295,4.014-1.031.9.307.442.97-.135,5.107-2.332,13.392,2.553,12.03-.638,12.447.773,1.976,1.6,1.829,1.915.872,1.9.037,11.5-3.977,9.894-.479,2.958-1.448,4.751-5.7,4.014-1.534,8.2-.184,14.485,2.836h0l8.495,2.246,1.657,1.424.344,2.431-.7,13.9,2.136,2.479,6.506,1.706,1.731,1.387L299,319.266,304,323.415h0l-.491,3.855-3.621,5.217,1.154,4.419-1.731,5.99.049,11.085h0l-6.2-1.043L289.2,354.2l-.712,2.848,1.363,3.094.2,3.965-.847,2.713-1.3,1.878-14.423,7.169-4.407,3.523.246,1.829.933.81,6.567,3.609.491,1.927-.687.933-6.96,2.836-3.007,2.173-6.285,6.543L248.9,408.79l-5.487,8.175-1.989.025-8.335-2.713-5.683,5.033-2.86,1.375L218.7,420l-.8-1.94,1.019-4.738-.552-2.836-2.566-2.345-4.886-1.448-.724-.908-.074-1.056,3.253-8.495-.344-3.989-1.313-1.706-5.843.381-12.815,3.989-.859-.712-.528-4.738-1.94-.454-5.99,1.362-1.9-.282-2.05-1.6-4.2-5.008-2.971-2.259-2.6-4.2-1.007-.417-3.781,1.129-6.285.491h0l-.258.037h0l-.147.025h0l-.786-3.977-1.62-1.62.258-1.289-1.191.491-.8-.43-.16.921,1.031.491-.454.491-1.056-.614.123-1.854-.7-1.657.16-.638-.958-.491-.859-1.007-1.166-3.425-.012-2L149.1,360.3l-1.129-.111-1.068-1.915-2.958-2.025-.712-1.264-.54-.344Z" transform="translate(100.36 108.46)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#g)">
																		<path d="M587.171,293.572l1.5.651.429,2.541-.737-1.62-1.24-.737.049-.835Zm-10.25-47.886,1.068.847-.062,1.964-1.448-1.424-1.24-.147.258-.8Zm-22.55-35.77,2.148,1.289,5.045,1.326-2.3.061-.638-.319-2.909.921-.552-.737.552-.307-.135-.6-1.436-.675-2.615,2.872.417.7-.491-.159-.27-.958.675-1.9.859-1.068ZM578.394,251.1l.467,1.215.564-2,.553.135-.073,2.934.97.54,1.461-2.161-.442-.405.467-2.222.307.479.147,1.3.466-.749.061,1.448-1.2.712.1,1.289-.773.135,2.025,1.866.638,1.989-.663,5.033.553.835-.442.037-.123,1.817-1.227,1.387.233-.957-1.068-1.5-.2,2.75.516.638-1.019,2.6.233,1.6-.921.516-3.216-7.316-.933-.663-.638.393L573.8,268.2l1.092,1.584-.638.822.049,3.707.749.2.982-1.129.994.908,1.682.123.614.921-.184.97.773.7-.847.2-.454,1.277,1.571,3.572,2.222,3.29,1.338.81.859,2.48.921.491-.282,1.08-.5.221-.491-1.8-1.743-.405-1.227-1.8-.675.209-.577-.712.835,2.136,1.338.933.062.687.626-.884.27.344.295,2.21,1.571,1.424.823,1.792,1.547.54.086,1.35,2.5,3.118.491.049-.062-1.35-.835-1.117.81-.651.761,2.013,3.965,3.057,1.633,4.959-.418,2.958,1.535,2.492-.5,2.185,1.019,3.142-.332,1.08,1.093.552-.614-.8.393-1.007,3.094,4.333L603.3,325.5l1.166.049.663.847,1.829,8.016.786-2.259,1.94-.368-.307.589,1.326,2.615,4.53,5.377,5.622.982,3.008-1.178,2-2.209,1.424.479,1.228-.405-.123.626.736.54.933-.43-.81,1.571.159,2.971,1.78-1.068.454-1.706,1.964-1.6-.11-1.166,1.092.651.651-.528.5,2.013,1.264-.712.933.933-.761.786L635.7,341l-.663,1.166-.54-.2-.515,1.817-3.769,3-.736,3.793-.688.16.16,2.087,2.75.516,1.1-1.448,2.492-.221,2.05.81,1.854,2.6,2-.086.921,1.448-.626,5.192,42.1,51.1,29.2,5.843,73.3,16.891,8.654-9.157L814,458.209l-28.2,89.9L658.872,592.8l-49.052,9.747-71.037,7.746-39.392,19.248L473.723,660.96l-5.978,14.988-1.878,1.141h0l3.179-30.013,9.808-77.936h0l23.937-187.6.024-9.513-1.669-8.47-3.633-7.1-2.05-2.529L484.182,342.7l-11.588-7.734-2.811-2.209-1.375-2.2-.835-4.542-.417-9.084.773-18.5-.466-27.767-.528-6.089-1.338-3.142-4.738-2.553-10.9-3.891-1.792-3.093-7.918-7.463-1.9-.27-5.413,1.24-2.884-.049-3.069-1.461-12.1-8.519-3.695-.994-6.923-.135-2.885-.773-4.984-4.149-4.247-5.18-7.046-1.3-12.779-7.463-1.51-1.878-1.731-6.273-2.6-2.713-4.076-2.848h0l-1.8-2.234-.467-1.878,1.571-5.733,11.232-14.914,15.54-29.056,10.14-10.7,15.283-5.389h0l6.26.16,3.241-1.559,5.02-3.879,38.483,5.045,1.068.822,4.124,6.6.749,3.13.27,5.659,4.333,5.769-.209.859,30.664-.528,1.448,1.4,1.571.577-.049,2.762,1.031.81-.221.417,1.043.184-1.24,3.695,1.191,2.431,1.8,1.264,2.087,2.737-.847.651-.221,3.277.479,1.228,2.516,2.921,4.775,1.289-.945,1.129-1.031-.1.1,1.289,1.952,2.222,1.228.123.061,1.657,2.492,1.547-.442,2.578.565.54-.1.577-1.1.737v-1.093l-.675-.049.356-2.455-.884,1.252-.356-1.731-1.6,5.4.785.1.552-1.534.43,2.922.786-1.166.516.835-.417,1.449,1.313-.381-.393.712-.638.135-.061,1.007-.393.847,1.375.663.921-1.62.773.233-.7,1.277.135,1.583.994-.5-.123-1.277,1.915-.97,1.154.454,1.326,1.927,3.179,1.424,4.493-.074,2.074,1.019.749,1.633,1.375.773.245,2.234-.687-.184.307-.847-.442-.663-.871,1.412-.663-.749-.872.872-.921-.884-.908.577-.381-.552-.847,1.129-1.006-.417-.405.9.5-.258,1.891,1.154.687.872,1.24-.295-.037-1.289,1.67.945-.749.933-.9.086.393,1.129-.822,1.51,2.111-1.338,1.988.663-.012,4.554.823,1.387.663.025.516-.074.651-.282,1.4.7-.589,2.32-.8-.282-.54.5.626.012-.246.687.933-.381.65,1.264.823-2.124.614.43-.184,1.3,1.473-.81,1.792.626-1.093-2.062,1.559-1.608-2.123-1.94-.835.1.8-1.277-.258-.651.908-.577.565,2.418,1.363.074.282,1.841-.908.381,1.191,1.178,1.1-.859.724-3.3,1.191-1.608L557.38,217.1l-.54,1.915-.773,1.178.675,2.406,1.436,1.424,2.946-1.755,1.314.5-.037.111-.6-.012-.111.049-.331.577-.332-.577-2.8,1.424,1.178,1.51,1.829-.442-.2.9-.687.025.024,1.461.872.037.295,1.043.16.245,2.823,2.222.454.135,1.485.9,1.387.454.97.749,3.658.368,2.541,2.713,5.512,6.494.6.552.5.491-.724.11-1.6-2.124-2.062-.528-1.2.835-1.51-2.394-.074-1.08-.577,2.75.184,1.166.994.246-.43,1.338.38,3.265,1.142,2.86,1.731,2.259.123-1.412.381-.381Z" transform="translate(164.38 93.37)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#i)">
																		<path d="M238.577,279.732l1.473-1.682,6.076-.908,7.365-4.382,7.3-.945.994-.8.061-1.007-2.946-5.868.393-3,1.547-1.854,1.056-.233,4.849,1.743,2.983.307.577-.945L269.3,252.4l2.32-5.892.945-.5,1.964.638,4.076-.958,1.068-5.278,5.831-5.929,14.6-11.342,13.883-6.42,3.388-7.107,3.339-3.388,17.8-9.121,5.094-1.645,2.909,1.056,5.45,5.512,7.353,5.057,9.17.663,6.2-7.169h0l2.885,1.142,4.419,3.67,1.645,3.069.184,3.069-.761,1.817-5.131,2.259-3.02,2.59-5.524,7.206-4.922,1.35.528,6.285-1.031,7.046.6,1.866,4.419,4.382,8.212,12.472.172,1.056-1.326,3.572,3.093,5.966.38,3.879-1.031,4.788-6.788-.27-8.838.835-10.188-1.3-3.842,1.007-2.48,2.418-2.8,5.487-3.793,2.136-3.486,3.449-15.811,3.867-4.014,3.093-6.653,8.212-.184,2.823,2.345,4.7-1.043,2.676-2.983,2.2-6.985-1.068-6.96,1.093-7.034-.614-4.75-.994-6.346-3.13L274,314.521l-2.664,1.939-3.732,6.236h0l-5.008-4.149-3.867-13.257-1.719-1.387L250.5,302.2l-2.136-2.467.712-13.9-.344-2.431-1.657-1.424Z" transform="translate(136.72 109.06)" fill="#025635" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#k)">
																		<path d="M190.055,240.982l2.443-2.934,2.872-9.526,3.842-4.591h0l5.45-5.07.356-2.958h0l-.442-.933h0l-3.621-2.259h0l-1.007-.442h0l-4.579-2.651-1.719-4.8h0l-.172-1.129h0l-.479-2h0l-2.75-11.5-1.289-3.081h0l-1.719-2.013-2.909-1.215h0l-.945-.565-1.633-2.983-.921-.38h0L178,179.127h0l-1.6-1.952h0l-3.609-2.786-6.076-1.6h0l-1.89-.295h0l-5.008-.884h0l-2.9-.638-1.387-1.633h0l4.53-5.156,7.7-4.345,12.189-5.377,5.266-.847,16.044-9.329L217.1,137.5l3.732.81h0l5.377,2.332,2.909.454,25.729-5.978,14.939,4.333,5.843-1.657,5.463-.442,17.762,4.309,2.909,2.443,5.156,6.334,3.093,1.792,4.9-.258,5.671-3.044,3.29-.577.823.982,1.08,7.635,1.743,1.792,1.178.381,7.967-.381,7.365,3.83,3.093-1.031,6.2-4.149,1.927-.258,1.94,1.51,1.326,1.989,6.481,11.858,3.413,9.452,2.48,3.044,3,1.768,12.251,1.583,4.026,1.547,1.988,1.166,7.463,6.776,1.264,2.3h0L397,202.155l-2.971,4.076-1.215,3.805-.074,1.952.835,2.308h0l-6.2,7.169-9.17-.663-7.353-5.057-5.45-5.512-2.909-1.056-5.094,1.645-17.8,9.121-3.339,3.388-3.388,7.107L319,236.858,304.4,248.2l-5.831,5.929-1.068,5.278-4.075.958-1.964-.638-.945.5-2.32,5.892,1.007,7.77-.577.945-2.983-.307-4.849-1.743-1.056.233-1.547,1.854-.393,3,2.946,5.868-.061,1.007-.994.8-7.3.945-7.365,4.382-6.076.908-1.473,1.682h0l-14.485-2.836-8.212.184-4.014,1.535-4.751,5.7-2.958,1.449-9.894.479-11.5,3.977-1.9-.037-1.915-.859-1.6-1.829-.773-1.976.626-12.447-2.541-12.042,2.332-13.38.147-5.107-.454-.97-.9-.295-4.014,1.019-.933-.295-1.449-1.841V251.9l1.989-2.774.908-2.958-.295-3.265Z" transform="translate(117.83 95.33)" fill="#025635" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#m)">
																		<path d="M109.429,319.958l1.473,1.031v.81l-1.829-.835Zm-9.747-21.2,1.129.736.516-.258,1.178,3.29h-.859l.1-1.436-2.5-1.363Zm5.6-1.6.8.2.466,1.559.675-.221-.319,1.019-.258-.577-1.534.049.5-1.338-.822-.528.491-.16Zm-.994-1.8,1.1,1.068-1.633-.123Zm-4.444-2.664.687,1.142-1.043.687-.528-.822Zm2.467-.491,1.154,2.872-1.264-1.043Zm.5-1.043.5.54-.319.761-.773-.97.589-.331Zm-17.48-2.688,1.706,1.976-1.9.123-.773-1.24Zm3.216-2.443.822.651-.908.086L86.83,285.1l-.025-2.05.577,2.2,1.289-.773-.123,1.559ZM8.637,180.216l1.08.233.737,1.878-1.252.528-.9-1.878-.552.037-.074,1.007-.737-.135.295-1.1,1.4-.565ZM2.069,177.6l.589.872-1.51.6L.6,178.681l.025.467,1.35,1.031,2.369-.417-.123.994,1.436,1.313-2.922.43-2.05-1.534-.405-2.651Zm58.848-45.885,6.236,1.9h0l3.081,2.369,2.443,2.909h0l1.436,2.013h0l7.648,9.98h0l2.111.786,2.222-.393h0l4.026-1.694,5.389-1.264h0l5.475-1.019h0l6.334.884,5.671,2.21,10.5.884h0l1.927-.7h0l3.167-7.427h0l1.547-1.817h0l1.939-.884h0l3.523-3.535h0l.945-.5,1.927,1.166h0l.565,2.013h0l-.552,8.826.381,5.389h0l.7,3.241h0l.921,2.013h0l4.272,4.358,2.971,1.485h0l4.063,1.35h0l12.447,1.448h0l2.946-2.148.97-.049h0l1.89,1.547h0l.957.577h0l2.013-.381h0l.921.258.97,3.032,1.584,1.326h0l2.124-.11,4.37-2.308h0l4.161-1.215,2.013.6,1.719,1.5h0l1.387,1.62,2.9.638h0l5.008.884h0l1.89.295h0l6.076,1.608,3.609,2.786h0l1.6,1.964h0l2.836.822h0l.921.381,1.633,2.971.957.565h0l2.9,1.215L224.3,188.1h0l1.277,3.081,2.75,11.5h0l.479,2h0l.172,1.129h0l1.719,4.8,4.579,2.639h0l.994.442h0l3.634,2.259h0l.43.933h0l-.356,2.958-5.45,5.07h0l-3.842,4.591-2.86,9.538-2.443,2.934h0l-.687-1.24h0l-1.5-1.755h0l-.945-.528h0l-1.878.1h0l-12.828,2.492h0l-8.065.638h0l-2.774-.491-2.971-2.246h0l-1.866-1.51h0l-2.787-1.755-2.9-.712h0l-2.885-1.019h0l-1.178-3.781-1.571-1.436h0l-.921.049-.516.9.381,1.854h0l.638,1.841-.982.786-7.549-3.081-2.025.4-2.062,1.584h0l-1.89,1.682h0L163.7,236.2l-.921.012h0l-.908-.417-.233-.921.528-2.946h0l-.184-.921h0l-2.786-1.743h0l-1.8-1.277-.184-.97h0l.245-1.952h0l-1.228-1.522-6.6-.282h0l-1.866-.319h0l-.994-.687h0l-1.338-1.9h0l-3.007-5.242h0l-.687-2.062h0l-.982-3.879h0l-1.363-1.866h0l-.945-.381h0l-1.9.565h0l-7.181,4.088-2.124.516-2.971-.381h0l-2.885-2.136h0l-4.861-2.038h0l-1.9.479h0l-.933.417h0l-2.013,1.3h0l-3.953,1.841h0l-1.952.7h0l-2.05.994h0l-1.989.528h0l-3.83.847h0l-1.228,1.8.11.921.994.589h0l3.83-.835h0l1.89.221h0l1.755,1.694h0l1.215,5.156h0l-.994,10.962.847,3.228,1.228,1.94h0l1.964,1.608h0l5.868,1.657h0l1.89,1.166h0l.442,1.448h0l-.319,2.16-3.867,6.8h0l-.2,2.86h0l.822,2.062h0l2.075,3.891.994,3.83h0l.344,1.915,2.394-2.185h0l1.878-.209,4.922,3.093h0l2.737,1.424h0l.9.761.663,2.786h0l.822,2.013,2.664,2.271h0l4.566,4.088,2.21,4.763h0l2.2,7.758h0l.908.38h0l3.891.16h0l1.375,1.719h0l-.307,2.823h0l-.123.908h0l-1.031,5.855h0l.27,12.607h0l.147,3.9h0l-.27,1.915-1.129,1.89-1.976,1.019-3.167.037h0l-2.836-.552-1.9.5h0l-.675.871h0l-1.436,2.222h0l-.97.872h0l-2.9,1.117h0l-6.936.589-1.166,1.8-.675,3.977h0l-1.989-3.56.172-.847-1.031.4-.687-2.013-3.413-1.854.405-.552-.945-2.038,1.031.086-.061-1.522-1.129-.43.516.5-.957.712-1.633-2.185.908-.736,1.657.368-1.092.54,1.473.454.43-1.449,1.412-1.154-.847-1.829,1.633-3.818-1.78-3.474.97-1.792-.724-3.216-5.733-6.408-1.682-4.284.11-1.927-3.314-2.688.049-2.185-1.142-.307-.27-2.541-1.313-1.927-2.59-1.117-2.737-3.9-.479.012.651,1.854-.454.221-.749-.074-.651-1.092-1.1-.074-1.35-1.056.221-1.3-.6.479-.552-.638-.712-2.357-1.178-.368,1.989-3.142.786-2.836-.295-2.381-2.2-1.375-3.4-.2-.344-.209-2.885-2.406-1.338-4.53-3.769-6.125-2.664-7-3.241-5.487-2.48-2.664-1.252-2.431L68.1,237.861l-2.2-5.168-.2-2.124-3.695-4.075-2.369-1.35-.356-.994L55.5,220.368l-.528-2.578,1.24-1.3-.381-1.412-2.823-4.64-3.142-1.608L46.69,205.2l-.221-1.669-2.2-2.946-.491-3.805-1.817-2.32-.466-2.443-1.252-2.283L39.2,188.673l-2.271-.687L36.7,184.9l-3.057-2.455-.8-1.989-4.075-2.111-.233-.712,2.676-.8-.086-.724-2.455-.442-.835-.724-1.6.074-2.087-2.038-.687.27-1.1-.737v.319l.626.994-1.35-.8-2.946,1.62-2.762-.209-.651.5L15,173.771l1.252-.687-2.038.135-.712,1.657-.331-.516.786-1.375-.687.1-.663.9-1.252-.2.54,1.559-1.178.025.54-2.062-1.657-.233.393-.405-.54-1.019-.307.6-.884-.368L6.869,173.8l.724,1.35-.835.172-.872,1.768-.957-1.78.246-1.571-.651-.27-.921.614.012-.97L6.022,170l1.191.442L6.7,169.168l.737-.528.933-3.118-.417-.1L12.012,155.4l1.412-2.124-.331-4.309-.945-2.369,2.357-7.071.037-3.621h0l3.965.2,2.013-.749,3.057.958h0l6.445.847h0l2.357-.147,2.836-2.762h0l4-1.031h0l7.4-1.338h0l2.885.27h0l4.063-.847h0l4.149-.209h0l3.2.626Z" transform="translate(82.5 94.42)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#o)">
																		<path d="M469.337,160.38l-15.295,5.389-10.127,10.7-15.54,29.068-11.232,14.915-1.584,5.733.479,1.866,1.8,2.246h0l-1.6,2.737-2.21,1.056-1.854-.638-2.381-2.6h0l-1.264-2.3-7.463-6.776-1.989-1.166-4.026-1.547-12.251-1.583-3-1.768-2.48-3.044-3.412-9.452-6.481-11.858-1.326-1.989-1.94-1.51-1.927.258-6.2,4.149-3.093,1.031-7.365-3.83-7.967.381-1.178-.381L334.7,187.68l-1.08-7.635-.822-.982-3.29.577-5.671,3.044-4.9.258-3.093-1.792-5.156-6.334-2.909-2.443-17.762-4.309-5.462.442-5.843,1.657-14.939-4.333-25.729,5.978-2.909-.454-5.377-2.332h0l6.9-6.985,1.092-7.439.479-11.49,1.719-3.032,7.562-5.794L280.985,113.6l6.457-5.806-.123-1.927-9.55-7.684-3.842-7.844-2.566-3.069-4.419-1.952-6.69.049-3.965-.8L246.995,77.1l-3.314-1.547-8.6,1.08-22.034-.761L208.5,74.5l-4.885-4.223-2.185-.945-5.377-.1L172.717,65l-9.882.172-8.9,1.669-16.67,1.829-4.2-.049-2.836-.761-2.688-2.332-4.775-8.544L117,52.455l-.712-8.249,3.486-10.238,1.559-7.439h0l43-12.656L177.246,2.617,176.694.15l1.424,1.89,2.86,1.731,22.783,6.653,7.451,3.486,4.2,2.836L324.28,93.663,405.359,149.7l8.593,1.989Z" transform="translate(108.62 64.5)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#q)">
																		<path d="M264.294,608.224l.516.233.221-.11.417.319-.8,1.375-.835-.933Zm-.589-3.953.442.651-.516,1.842-.1-1.179-.6.945-.687-1.436.43-.687Zm-7.954-.6,1.743,1.3-.982-.234Zm-4.272-3.032,2.369.614,1.387,2-3.928-1.265-.8-.932.97-.418Zm11.858-4.6.6.417-1.988.172Zm-11.269-2.222,3.891,2.259,1.8,3.818,2.885.822,1.952-.442,1.215,1.645,1.964-.577-.344-1.1-.54-.086.344.761-1.031.049-.528-.749.638-.712.123-1.571.724.122.221-.773,1.056.123.258,1.755.933,1.252.516-.1.724,1.228,1.117-.258.037.626.577-.074-.1,1.154-.761.8.54,3.645-1.141.135-1.522-1.743-2.222-.454,1.522-.736-.111-1.547-2.21-.54-3.732,1.142-2.418-2.013-1.4-.393-1.461-1.264-1.178-2.332-1.633-.037.565-1.535-1.227-.994-.049-1.3Zm-7.942-.2,2.516,3.744-1.952-.736.258-.405-.921-.847Zm14.644-2.553,1.4.957-.687,1.154.712,1.129,1.694.515-.16,1.068-1.6-.393-.908.724,2.688,2.037.908,1.277-.344.552-.933-1.411-3.707-1.228-2.246-3.2.012-1.326,2.013-.245,1.154-1.608Zm-9.857-.27,1.853,1.35-.847,1.424-1.228.38-1.154-1.423,1.375-1.731Zm4.444-1.056,1.387.8-.577.7-.54-.074Zm6.334.295.516,1.731-2.529-2.553,2.013.822ZM252.6,531.2l5.475-.577,1.547,1.264-1.006,8.924.344,1.841.908.577,3.621-.552,2.639.283,1.326,1.657,1.043,3.547.9.786,10.164.491,1.571,1.522,1.952,4.578,3.99,3.891,1.866.773,1.731-.172.687-.81,2.05-8.286,1.485-2.578,3.523-2.652,2.713-1.08L302.347,546l-.16,5.425,4.419,2.234,3.241,5.7.847.7,3.621.737,2.43,3.695h0l1.191,1.706-2.357,3.585.111,2.909,1.9,1.1v.8l.994.43.319.847-5.512,1.731.516,2.3-1.866,1.682.061,1.78-.761.393-.074,1.081.111.429,1.2-.491,2,.589,1.007,2.013-.675,1.117-2.786-.319.319,3.449-1.841.859-.049.736,1.78,2.32,1.9.417.4,1.019,1.24.589-.27,1.547.589,1.056-1.755.135-.393.994.6,3.192-1.031.184-2.541-1.363-.81.221.54,1.1-1.264,3.364.061,1.3-5.426,1.424-2.05,1.461.516,3.327-2.1.466-5.917-.6-.417-.847.737-2.173-1.485-1.424-1.031-2.713.687-4.37-.4-.356-.577-.737v-1.142l.012-.7-3.081-3.818-1.731-.847-.786.307-.479-1.657-.626-.233-.847-.38,1.314-1.179-.651-1.706.381-2.271-1.682-1.375-2.664-.823-1.633-2.308.282-2.247-.565-.221-1.375-.822-.319.368,1.387,2.394-.81.81.061,2.43-.368,1.166-.221-4.578-1.2-3.916-.651-5.106-.688-.381.81-2.59-.356-2-1.768-.466-1.927-3.032-2.418-1.854-1.2.062-.245-1.829-.97-1.154-8.985-6.4-.8-1.265-1.78-.4-1.522-1.252-1.289-.037.233-.982-4.64-4.272.847-.012-1.24-1.117.319-1.314-.724.97-.6-.4-.147-.675.724-.221-.27-1.24-1.706-.663-.933-2.2h0l2.418-3.867,4.358-2.517Z" transform="translate(137.96 185.32)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#s)">
																		<path d="M419.209,588.142l-21.15-2.9-7.672-.516-7.672-4.947H358.5l-1.706,1.2-2.394.331-3.241-1.706-3.314.147-10.79,1.375L336.044,583l-1.326-1.645L331.1,582l-1.682,2.357-3.2-.884-1.964.528-2.418-.356-.835-1.092-2.479.442-3.179-3.314.086-1.276h0l1.412-3.977-1.6-7.279.822-1.8L317,555.072l-1.694-7.512.1-2.038,1.08-2.075,3.781-4.21,10.078-9.28,1.326-1.817,1.3-3.707,1.166-1.289,7.758-2.48,2.921-1.743,1.5-1.731,1.191-2.725.209-2.6-2.161-10.52.847-1.952,4.726-2.382L352.5,495.3l.049-7.463h0L363,494.7l6.408,2.934,3.977.884,10.864.859,72.24-6.187,20.12-2.934,12.435-3.228h0l-9.808,77.936-3.179,30.013h0l-9.182,5.794-8.629-.442L448.2,586.779l-13.135,1.878Z" transform="translate(154.16 175.4)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#u)">
																		<path d="M269.717,448.287l-2.639,7.881.282.97,2.516,2.418.135,1.952-1.277,1.78-7.144,4.873-1.031,2.2.282,4.358-5.229,10.667h0l-.97.626-5.671.049-1.866.712-2.946,5.475-3.167,11.035-2.713,1.326h-3.167l-1.952-1.289-2.529-3.756-4.517-2.615-1.6-1.89.319-5.438-1.117-4.775.38-6.493-1.191-1.866-4.481-1.35-.822-.859-.221-1.731,4.922-5.033,9.489-2.283,1.682-4.37,3.3-5.683.147-2.885-1.362-5.585.859-2.59,1.669-1.743,2.774-.38,1.755.749,1.485,1.78,1.571,4.64,2.357,1.792,5.659-2.173,5.119.246,6.641-3.376,1.829.27Z" transform="translate(131.9 165.16)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#w)">
																		<path d="M13.788,149.807l.81-3.462.908-.933-.221-1.007,1.277-.749-.589-2.357.994-.565-.295-1.056.749-1.669,46.9,8.335,17.983-14.816,10.864-18.143,31.621-6.334,7.1-16.179,13.9-8.323L103.729,33.793,145.084,21.64h0l-1.559,7.439-3.486,10.238.712,8.249,5.769,4.53,4.775,8.544,2.688,2.332,2.836.761,4.2.049,16.67-1.829,8.9-1.669,9.882-.172L219.8,64.346l5.377.1,2.185.945,4.886,4.223,4.542,1.375,22.034.761,8.6-1.08,3.314,1.547,9.292,7.463,3.965.8,6.69-.049,4.419,1.952,2.566,3.069,3.842,7.844,9.55,7.684.123,1.927-6.457,5.806-33.475,20.684-7.562,5.794-1.719,3.032-.479,11.49-1.093,7.439-6.9,6.985h0l-3.732-.81-15.835,6.788L217.9,179.439l-5.266.847-12.189,5.377-7.7,4.345-4.53,5.156h0l-1.731-1.485-2-.6-4.161,1.227h0l-4.37,2.308-2.124.1h0l-1.6-1.326-.958-3.032-.921-.258h0l-2.013.393h0l-.945-.577h0l-1.89-1.547h0l-.982.049-2.946,2.148h0l-12.435-1.448h0l-4.075-1.35h0l-2.971-1.485-4.26-4.358h0l-.921-2h0l-.7-3.253h0l-.381-5.389.552-8.826h0l-.565-2.013h0l-1.927-1.154-.945.5h0l-3.523,3.535h0l-1.939.884h0l-1.547,1.8h0l-3.167,7.439h0l-1.927.7h0l-10.5-.872-5.671-2.21-6.334-.884h0l-5.487,1.031h0l-5.389,1.264-4.026,1.682h0l-2.222.405-2.111-.786h0l-7.648-9.98h0l-1.436-2.013h0l-2.443-2.909-3.081-2.369h0l-6.236-1.89h0l-3.2-.626h0l-4.149.209h0l-4.063.847h0l-2.885-.27h0l-7.4,1.338h0l-4.014,1.019h0l-2.823,2.774-2.369.147h0l-6.432-.847h0l-3.057-.957L15.862,161l-3.965-.2h0l.479-3.634.786-1.559-.5-2.038,1.007-1.657Z" transform="translate(85.14 69.51)" fill="#b8b8b8" stroke="#fff" stroke-width="0.5" />
																   </g>
																   <g transform="matrix(1, 0, 0, 1, -82.78, -64.65)" filter="url(#y)">
																		<path d="M318.173,586.39l-2.43-3.695-3.621-.737-.835-.7-3.253-5.684-4.419-2.234.172-5.426-1.228-1.375-2.713,1.08-3.523,2.652-1.485,2.578-2.038,8.286-.7.81-1.719.172-1.866-.774-3.989-3.879-1.952-4.578L281,571.365l-10.164-.491-.9-.785-1.043-3.548-1.326-1.657-2.652-.283-3.609.552-.908-.577-.344-1.841,1.019-8.924-1.559-1.264-5.475.577h0l1.841-7.868-3.646-11.22-2.59-1.473-8.237-.258-1.755-.6-1.264-1.8-.749-3.646.6-12.275,2.418,2.307.921-.074,4.751-3.462,2.75-.466,5.819.54,1.866-.921.933-1.743,1.559-5.647-2.688-5.45,1.608-8.495-.258-1.854-2.271-2.32-2.811-.159-1.841.908h0l5.229-10.667-.282-4.358,1.031-2.2,7.144-4.873,1.277-1.78-.135-1.952L262.75,458.9l-.282-.97,2.639-7.881h0l5.733,11.146,1.718,1.252,2.8.295,2.836-1.387L290.6,450l7.868-3.756,5.229-4.665,5.585-.344,3.879-1.866,2.774-.294,6.4,1.6,7.021-4.628,4.935-1.792h0l7.07,6.15,3.769,6.052,4.419,4.419,1.3,2.123-.1,3.634-4.1,7.476-1.51,7.5.27,1.964,3.376,3.634,4.8,7.807,16.621,14.841h0l-.049,7.463-1.375,1.706-4.726,2.381-.847,1.952,2.16,10.52-.209,2.6-1.191,2.725-1.5,1.731-2.922,1.743-7.758,2.48-1.166,1.289-1.3,3.707L348,541.966l-10.078,9.28-3.781,4.21-1.08,2.074-.1,2.038,1.694,7.513-.945,10.274-.822,1.8,1.6,7.279-1.412,3.977h0l-.135,2.209-2.909-2.823-6.494-4.137-3.02-.417Z" transform="translate(136.51 163.4)" fill="#bf9b2e" stroke="#fff" stroke-width="0.5" />
																   </g>
															  </g>
														 </g>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.makkahal.mukarrama.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>1985</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>730</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>730</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>74</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=30c5c366-2950-4e30-a08f-b43d8b680fa5' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

															 <g transform="translate(450.001 2175.066)">
																  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
															 </g>
															 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(435 2141)' : 'translate(273 2141)'}" fill="#025635" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.makkahal.mukarrama.city.name.text"/> </tspan>
																  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
															 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.jeddahfirst.city.name.text"/>  <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>1971</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>12,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>12,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>1,059</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=ec1f5ee4-2eaa-4356-b5a7-f0f1bd6b2a2a' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(347 2103)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(337.999 2069)' : 'translate(240.999 2069)'}" fill="#025635" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"> <spring:theme code="economic.infralogistics.maps.jeddahfirst.city.name.text"/></tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.jeddahsecond.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/> </span></td>
																				   <td class='text-left'><span class='INL_table_number'>2009</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>8,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>8,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>275</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=d969ca50-5f40-44df-a44e-06348c04e720' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(394.852 2116.066)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(510.523 2080.32)' : 'translate(427.523 2080.32)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.jeddahsecond.city.name.text"/></tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.madina.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2003</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>17,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>10,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>243</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=5ab5a824-f7fc-4658-8d09-b8291cf70412' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(527.162 2180.494)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(649 2142.747)' : 'translate(559.836 2144.747)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.madina.city.name.text"/> </tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.altaif.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2019</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>11,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>1,200</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>-</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=a7ffdb54-b475-46a0-8aa6-ee05c67f437a' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(499.35 2155.621)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(658.836 2112.494)' : 'translate(528.836 2122.494)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.altaif.industrial.city.name.text"/> </tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.oasis.yanbu.city.name.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2014</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>500</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>500</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>-</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/Oasis/Pages/Oasis.aspx?OasisId=dbbd0530-c4dd-4c82-bfba-2e2c0cd2ad79' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(550.836 2226.428)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(673.512 2200.488)' : 'translate(583.512 2208.488)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.oasis.yanbu.city.name.text"/> </tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.jeddahthird.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2012</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>80,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>24,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>539</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=5c5260fb-b217-45b5-ab6c-0e4062825ad6' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(386.676 2070.066)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(499.348 2034.319)' : 'translate(419.348 2034.319)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.jeddahthird.city.name.text"/></tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.oasis.jeddah.city.name.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2015</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>5,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2,500</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>6</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/Oasis/Pages/Oasis.aspx?OasisId=7d7b13d4-095a-4b77-ade6-e56d7274b520' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(303.676 2024.133)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(385.35 1988.385)' : 'translate(336.35 1988.385)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.oasis.city.name.text"/></tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.jeddah.name.text"/> </tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.tabuk.city.name.text"/><br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2003</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>4,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2,900</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>78</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=4d14ebfb-dc8a-44cc-9fd7-6fa682df365e' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(339 1964.133)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(490 1940.259)' : 'translate(370 1940.259)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.tabuk.industrial.city.name.text"/></tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.asir.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>1990</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/> </span></td>
																				   <td class='text-left'><span class='INL_table_number'>2,700</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2,700</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>157</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=ae93ba2c-f395-4ed0-90b6-12a7e2e16de1' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(561.336 2312.133)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(712.336 2288.259)' : 'translate(592.336 2288.259)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.asir.industrial.city.name.text"/></tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.najran.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2003</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/> </span></td>
																				   <td class='text-left'><span class='INL_table_number'>6,500</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>3,600</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>30</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=b7e7a0ea-871e-4326-9b96-7fde985d9456' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

															 <g transform="translate(565.676 2267.807)">
																  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
															 </g>
															 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(720.676 2243.934)' : 'translate(596.676 2243.934)'}" fill="#fff" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
																  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.najran.industrial.city.name.text"/></tspan>
															 </text>
														 </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.rabigh.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2015</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>75,500</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>-</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=2a5dda14-bc4a-4110-be51-46b6d2137815' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(408.162 2222.681)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(398.161 2188.807)' : 'translate(298.161 2188.807)'}" fill="#025635" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.rabigh.city.name.text"/></tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.Jazan.city.name.text"/> <br><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2009</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>39,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>29,750</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>49</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=206efd56-77fc-4188-9d62-d3a45736b48f' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <g transform="translate(454.352 2281)">
															  <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-2123.014 446.319)" fill="#00a6be" />
														 </g>
														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(445.662 2247)' : 'translate(349.662 2247)'}" fill="#025635" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.Jazan.city.name.text"/> </tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/> </tspan>
														 </text>
														   </a>

														 <a class="logis_show" data-container="body" data-toggle="popover" data-placement="right" data-content="
														   <div class='container py-3 px-0'>
															   <div class='w-100'>
																   <h6 class='INLS_popover_word'><spring:theme code="economic.infralogistics.maps.albaha.city.name.text"/>  <br>
<spring:theme code="economic.infralogistics.maps.industrial.city.text"/></h6>
																   <div class='p-2'>
																	   <div class='INL_table'>
																		   <table class='table borderless m-0'>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>2012</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>3,000</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>1,300</span></td>
																			   </tr>
																			   <tr>
																				   <td><span class='INL_table_para'><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
																				   <td class='text-left'><span class='INL_table_number'>22</span></td>
																			   </tr>
																		   </table>
																	   </div>
																   </div>
																   <div class='text-center w-100 pb-2'>
																	   <a href='https://modon.gov.sa/en/Cities/IndustrialCities/Pages/IndustrialCity.aspx?CityId=b755a8e8-fd01-44d8-876d-3c444dd8d555' class='INLS_popover_alink text-center'  target='_blank'><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
																   </div>
															   </div>
														   </div>" onclick="logis_fun()" data-original-title="" title="" aria-describedby="popover67314">

														 <text transform="${currentLanguage.isocode eq 'ar' ? 'translate(533.012 2290.934)' : 'translate(443.012 2200.934)'}" fill="#025635" font-size="16" font-family="TheSans-BoldPlain, TheSans" font-weight="700">
															  <tspan x="0" y="18"><spring:theme code="economic.infralogistics.maps.albaha.city.name.text"/></tspan>
															  <tspan x="0" y="38"><spring:theme code="economic.infralogistics.maps.industrial.city.text"/></tspan>
														 </text>
														 <path d="M2132.93-478.319c.838.138,1.685.235,2.511.421a15.98,15.98,0,0,1,12.021,12.78,19.471,19.471,0,0,1-1.377,10.486,43.039,43.039,0,0,1-6.581,10.941,63.394,63.394,0,0,1-6.516,7.051,1.4,1.4,0,0,1-2.257,0,58.242,58.242,0,0,1-11.275-14.119,27.126,27.126,0,0,1-3.24-8.979,15.975,15.975,0,0,1,12.946-18.337c.539-.093,1.082-.161,1.624-.241Zm7.726,15.586a8.824,8.824,0,0,0-9.032-8.546,8.827,8.827,0,0,0-8.566,9.054,8.828,8.828,0,0,0,9.033,8.546A8.826,8.826,0,0,0,2140.656-462.733Z" transform="translate(-1601.676 2725.252)" fill="#00a6be" />
														</a>

													</g>
											   </svg>
											</div>
										</div>
									</div>
								</div>
							</div>
						</main>
					</div>

				</div>
			</div>
          </div>
        </section>
        <!-- -----------Industrial Dashboard--------------- -->

        <!-- ----------total Dashboard---------------- -->
        <section class="" id="">
          <div class="container infra-item-cards">
			<div class="row">
			<c:forEach items="${infrastructureLogisticsData}" var="card">
			<c:choose>
			    <c:when test = "${empty card.value4}">
                    <div class="col-12 col-lg-4 pb-4">
                        <div class="INL_total_bg_box">
                            <div class="text-center pt-2 pb-2">
                                <c:set var = "imgString" value="${card.displayName}"/>
                                <c:if test = "${fn:contains(imgString, 'Infrastructure') || fn:contains(imgString, 'إجمالي المساحة')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Infrastructure_icon.png" class="img-fluid" loading="lazy">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Employment') || fn:contains(imgString, 'التوظيف')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Employment_icon.png" class="img-fluid" loading="lazy">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Area') || fn:contains(imgString, 'البنية التحتية')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Total_Area_icon.png" class="img-fluid" loading="lazy">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Housing') || fn:contains(imgString, 'المرافق والإسكان')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Housing_&_Facilities_icon.png" class="img-fluid" loading="lazy">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Industrial') || fn:contains(imgString, 'المدن الصناعية')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Industrial_Cities_icon.png" class="img-fluid" loading="lazy">
                                </c:if>                            </div>
                            <h5 class="INL_total_gold_header">${card.displayName}</h5>
                            <div class="row pt-4 INL_height_150px">
                                <div class="col-md-6">
                                    <h5 class="INL_total_number">${card.value1}</h5>
                                    <p class="INL_total_number_contant">
                                        ${card.label1}
                                    </p>
                                </div>
                                <div class="col-md-6">
                                    <h5 class="INL_total_number">${card.value2}</h5>
                                    <p class="INL_total_number_contant">
                                        ${card.label2}
                                    </p>
                                </div>
                            </div>
                            <div class="row pt-4 INL_height_150px">
                                <div class="col-md-12">
                                    <h5 class="INL_total_number">${card.value3}</h5>
                                    <p class="INL_total_number_contant">
                                        ${card.label3}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
			    </c:when>
			    <c:otherwise>
			        <div class="col-12 col-lg-6 ">
                        <div class="INL_total_bg_box">
                            <div class="text-center pt-2 pb-2">
                                <c:set var = "imgString" value="${card.displayName}"/>
                                <c:if test = "${fn:contains(imgString, 'Infrastructure')  || fn:contains(imgString, 'إجمالي المساحة')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Infrastructure_icon.png" class="img-fluid">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Employment') || fn:contains(imgString, 'التوظيف')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Employment_icon.png" class="img-fluid">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Area') || fn:contains(imgString, 'البنية التحتية')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Total_Area_icon.png" class="img-fluid">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Housing')  || fn:contains(imgString, 'المرافق والإسكان')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Housing_&_Facilities_icon.png" class="img-fluid">
                                </c:if>
                                <c:if test = "${fn:contains(imgString, 'Industrial')  || fn:contains(imgString, 'المدن الصناعية')}">
                                    <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Industrial_Cities_icon.png" class="img-fluid">
                                </c:if>
                            </div>
                            <h5 class="INL_total_gold_header">${card.displayName}</h5>
                            <c:if test = "${empty card.value5}">
                                <div class="row pt-4 INL_height_190px">
                                    <div class="col-md-6">
                                        <h5 class="INL_total_number">${card.value1}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label1}
                                        </p>
                                    </div>
                                    <div class="col-md-6">
                                        <h5 class="INL_total_number">${card.value2}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label2}
                                        </p>
                                    </div>
                                </div>
                                <div class="row pt-4 INL_height_150px">
                                    <div class="col-md-6">
                                        <h5 class="INL_total_number">${card.value3}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label3}
                                        </p>
                                    </div>
                                    <div class="col-md-6">
                                        <h5 class="INL_total_number">${card.value4}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label4}
                                        </p>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${not empty card.value5}">
                                <div class="row pt-4 INL_height_190px">
                                    <div class="col-md-4">
                                        <h5 class="INL_total_number">${card.value1}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label1}
                                        </p>
                                    </div>
                                    <div class="col-md-4">
                                        <h5 class="INL_total_number">${card.value2}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label2}
                                        </p>
                                    </div>
                                    <div class="col-md-4">
                                        <h5 class="INL_total_number">${card.value3}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label3}
                                        </p>
                                    </div>
                                </div>
                                <div class="row pt-4 INL_height_150px">
                                    <div class="col-md-6">
                                        <h5 class="INL_total_number">${card.value4}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label4}
                                        </p>
                                    </div>
                                    <div class="col-md-6">
                                        <h5 class="INL_total_number">${card.value5}</h5>
                                        <p class="INL_total_number_contant">
                                            ${card.label5}
                                        </p>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
			    </c:otherwise>
			</c:choose>
            </c:forEach>
				<%--<div class="col-12 col-lg-4 ">
					<div class="INL_total_bg_box">
						<div class="text-center pt-2 pb-2">
							<img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Infrastructure_icon.png" class="img-fluid">
						</div>
						<h5 class="INL_total_gold_header">${infrastructure.displayName}</h5>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-6">
								<h5 class="INL_total_number">${infrastructure.value1}</h5>
								<p class="INL_total_number_contant">
									${infrastructure.label1}
								</p>
							</div>
							<div class="col-md-6">
								<h5 class="INL_total_number">${infrastructure.value2}</h5>
								<p class="INL_total_number_contant">
									${infrastructure.label2}
								</p>
							</div>
						</div>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-12">
								<h5 class="INL_total_number">${infrastructure.value3}</h5>
								<p class="INL_total_number_contant">
									${infrastructure.label3}
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-4 ">
					<div class="INL_total_bg_box">
						<div class="text-center pt-2 pb-2">
							<img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Employment_icon.png" class="img-fluid">
						</div>
						<h5 class="INL_total_gold_header">${employment.displayName}</h5>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-6">
								<h5 class="INL_total_number">${employment.value1}</h5>
								<p class="INL_total_number_contant">
									${employment.label1}
								</p>
							</div>
							<div class="col-md-6">
								<h5 class="INL_total_number">${employment.value2}</h5>
								<p class="INL_total_number_contant">
									${employment.label2}
								</p>
							</div>
						</div>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-12">
								<h5 class="INL_total_number">${employment.value3}</h5>
								<p class="INL_total_number_contant">
									${employment.label3}
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-4 ">
					<div class="INL_total_bg_box">
						<div class="text-center pt-2 pb-2">
							<img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Total_Area_icon.png" class="img-fluid">
						</div>
						<h5 class="INL_total_gold_header">${totalArea.displayName}</h5>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-6">
								<h5 class="INL_total_number">${totalArea.value1}</h5>
								<p class="INL_total_number_contant">
									${totalArea.label1}
								</p>
							</div>
							<div class="col-md-6">
								<h5 class="INL_total_number">${totalArea.value2}</h5>
								<p class="INL_total_number_contant">
									${totalArea.label2}
								</p>
							</div>
						</div>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-12">
								<h5 class="INL_total_number">${totalArea.value3}</h5>
								<p class="INL_total_number_contant">
									${totalArea.label3}
								</p>
							</div>
						</div>
					</div>
				</div>--%>
			</div>
			<div class="row justify-content-md-center pt-4">
			<%--<div class="col-12 col-lg-6 ">
					<div class="INL_total_bg_box">
						<div class="text-center pt-2 pb-2">
							<img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Housing_&_Facilities_icon.png" class="img-fluid">
						</div>
						<h5 class="INL_total_gold_header">${housingFacilities.displayName}</h5>
						<div class="row pt-4 INL_height_190px">
							<div class="col-md-4">
								<h5 class="INL_total_number">${housingFacilities.value1}</h5>
								<p class="INL_total_number_contant">
									${housingFacilities.label1}
								</p>
							</div>
							<div class="col-md-4">
								<h5 class="INL_total_number">${housingFacilities.value2}</h5>
								<p class="INL_total_number_contant">
									${housingFacilities.label2}
								</p>
							</div>
							<div class="col-md-4">
								<h5 class="INL_total_number">${housingFacilities.value3}</h5>
								<p class="INL_total_number_contant">
									${housingFacilities.label3}
								</p>
							</div>
						</div>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-6">
								<h5 class="INL_total_number">${housingFacilities.value4}</h5>
								<p class="INL_total_number_contant">
									${housingFacilities.label4}
								</p>
							</div>
							<div class="col-md-6">
								<h5 class="INL_total_number">${housingFacilities.value5}</h5>
								<p class="INL_total_number_contant">
									${housingFacilities.label5}
								</p>
							</div>
						</div>
					</div>
				</div> --%>
			<%--<div class="col-12 col-lg-6 ">
					<div class="INL_total_bg_box">
						<div class="text-center pt-2 pb-2">
							<img src="${commonResourcePath}/images/Infrastructures_and_Logistics/icon/Industrial_Cities_icon.png" class="img-fluid">
						</div>
						<h5 class="INL_total_gold_header">${industrialCities.displayName}</h5>
						<div class="row pt-4 INL_height_190px">
							<div class="col-md-6 pull-left">
								<h5 class="INL_total_number">${industrialCities.value1}</h5>
								<p class="INL_total_number_contant">
									${industrialCities.label1}
								</p>
							</div>
							<div class="col-md-6 pull-left">
								<h5 class="INL_total_number">${industrialCities.value2}</h5>
								<p class="INL_total_number_contant">
									${industrialCities.label2}
								</p>
							</div>
						</div>
						<div class="row pt-4 INL_height_150px">
							<div class="col-md-6 pull-left">
								<h5 class="INL_total_number">${industrialCities.value3}</h5>
								<p class="INL_total_number_contant">
									${industrialCities.label3}
								</p>
							</div>
							<div class="col-md-6 pull-left">
								<h5 class="INL_total_number">${industrialCities.value4}</h5>
								<p class="INL_total_number_contant">
									${industrialCities.label4}
								</p>
							</div>
						</div>
					</div>
				</div>--%>
			</div>
          </div>
        </section>
        <!-- -----------total Dashboard--------------- -->
        <!-- ----------Industrial_Cities Dashboard---------------- -->
        <section class="" id="">
          <div style="background-image:url(img/Infrastructures_and_Logistics/Private_Industrial_Cities_and_Complexes_bg.jpg);    background-repeat: no-repeat;
    background-size: 100% 100%;">
			  <div class="container">
				<div class="row" >
					<div class="col-md-12 pb-5">
						<div class="INL_bg_gold_header">
							<h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.infralogistics.private.industrialsector.section.heading.name"/></h1>
						</div>
					</div>

						 <c:forEach items="${privateIndustrialCitiesData}" var="privateIndustrialCities">
                            <div class="col-md-6 col-lg-3  mx-auto">
                            <div class="INL_SD_Industrial_Cities">
								<h4 class="INL_SD_Industrial_Cities_header">${privateIndustrialCities.displayName}</h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">${privateIndustrialCities.establishmentYear}</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">${privateIndustrialCities.totalArea}</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">${privateIndustrialCities.developedArea}</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">${privateIndustrialCities.noOfContracts}</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a target="_blank" href="${privateIndustrialCities.cityUrlLink}" class="alink"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
							</div>
                           </div>
						</c:forEach>

					<!--<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
						${privateIndustrialCities}
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.zamil.industial.section.heading.name"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2013</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">1,340</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">1,273</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a target="_blank" href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=8d344bd7-2ca7-406e-b9b5-7776a5540ce6" class="alink"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.special.industial.development.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2007</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">1,311</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="" class="alink" disabled target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.obeikan.industial.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2007</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">984</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=7ac3646e-02fe-42cd-921a-b9ee0d3462d4" class="alink" target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.petrorabigh.industial.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2010</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2,400</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2,400</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=c421244f-6e4f-4bf4-9608-0a87986197e5" class="alink" target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.alagimi.industial.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2008</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">1,340</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">1,051</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=31ba79ab-2604-43e1-b919-f5e4d03d0dd2" class="alink" target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.alfanar.industial.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2007</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">681</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">681</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=07968910-816a-4d39-8db5-6c7e32cce6ab" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.industial.complex.logistic.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">418</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">405</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=c9d63b6f-acc5-451e-beac-94ba1172f732" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.gate.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">6,522</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">5,608</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=6a521302-8ef5-4870-8dd4-f65b16f84319" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.binladen.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2020</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">5,068</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=bdaaf87a-ccfc-4635-b481-8ef3fb6360f9" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.riyadhcable.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">313</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">313</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=32818ed7-4607-4c72-98eb-ab31089a4f32" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.industrial.services.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">418</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">405</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=c9d63b6f-acc5-451e-beac-94ba1172f732" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.constructionmaterial.complex.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">189</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">189</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=0e94cf40-87e4-4711-9754-40a86c0416fd" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.alnahdi.medical.company.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">249</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=633cb1c3-e3cb-44f6-83d2-0ea4c33fce6e" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.eastern.textile.company.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">1994</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">265</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">265</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=d48a1ccb-7639-45d8-a86e-50bf9441dca3" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.saudiceramic.company.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.online.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">423</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">423</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=654651eb-8d5f-4a8e-8923-e3be7ca378a0" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.altatweer.private.indusrial.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2020</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">1,300</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=3f99c687-dca2-40ed-8ad2-33acd1714f58" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>-->
				</div>
				<!--<div class="row w-60 infraLogisticsLastDiv mx-auto pb-5">
					<div class="col-md-6  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.kingsalman.energypark.text"/> </h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2021</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">50,000</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=d0c8d6f2-ec35-4b02-9ad0-84b139637569" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-6  mx-auto">
						<div class="INL_SD_Industrial_Cities">
							<h4 class="INL_SD_Industrial_Cities_header"><spring:theme code="economic.infralogistics.fawzan.industrial.city.text"/></h4>
							<div class="p-2">
								<div class="INL_table">
									<table class="table borderless">
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.zamil.industial.establishment.year.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">2013</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.totalarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">3,324</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.developedarea.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
										<tr>
											<td><span class="INL_table_para"><spring:theme code="economic.infralogistics.private.industrial.numberofcontracts.text"/></span></td>
											<td class="text-center"><span class="INL_table_number">-</span></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="text-center pb-2">
								<a href="https://modon.gov.sa/en/Cities/PrivateCities/Pages/PrivateCity.aspx?CityId=3fcdd8cb-2138-4e79-bf11-5509ed5f8234" class="alink"  target="_blank"><spring:theme code="economic.infralogistics.private.industrial.viewcity.website.link.text"/></a>
							</div>
						</div>
					</div>
				</div>-->
			  </div>
          </div>
        </section>
        <!-- -----------Industrial_Cities Dashboard--------------- -->

        <!-- ----------Technology Zones ---------------- -->
        <section class="infraLogisticsTechZone" id="">
          <div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="INL_bg_gold_header">
						<h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.infralogistics.technology.zones.heading.text"/></h1>
					</div>
				</div>
			</div>
			<div class="row pt-5 pb-5">
				<div class=" col-md-4 mx-auto">
                <c:choose>
                    <c:when test="${currentLanguage.isocode eq 'en'}">
                        <a href="https://modon.gov.sa/en/Cities/TechnologyZones/Pages/TechZone.aspx?ZoneId=cce3ebfd-4cec-431d-a2ca-b029f7958a0f" target="_blank">
                    </c:when>
                    <c:otherwise>
                        <a href="https://modon.gov.sa/ar/Cities/TechnologyZones/Pages/TechZone.aspx?ZoneId=cce3ebfd-4cec-431d-a2ca-b029f7958a0f" target="_blank">
                    </c:otherwise>
                </c:choose>
						<div class="INL_technology_Text_Block">
						  <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/tech/Riyadh_Technology_Valley.jpg"  style="width:100%;" loading="lazy">
						  <div class="INL_technology_text text-center mx-auto w-100">
							<h4 class="INL_technology_text_content"><spring:theme code="economic.infralogistics.technology.zones.riyadhvalley.text"/></h4>
						  </div>
						</div>
					</a>
				</div>
				<div class=" col-md-4 mx-auto">
                   <c:choose>
                        <c:when test="${currentLanguage.isocode eq 'en'}">
					        <a href="https://modon.gov.sa/en/Cities/TechnologyZones/Pages/TechZone.aspx?ZoneId=6802e405-13ba-4e0b-9a31-ba2018734541" target="_blank">
                        </c:when>
                        <c:otherwise>
					            <a href="https://modon.gov.sa/ar/Cities/TechnologyZones/Pages/TechZone.aspx?ZoneId=6802e405-13ba-4e0b-9a31-ba2018734541" target="_blank">
                        </c:otherwise>
                    </c:choose>
						<div class="INL_technology_Text_Block">
						  <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/tech/Al_Raidah_Digital_City.jpg"  style="width:100%;" loading="lazy">
						  <div class="INL_technology_text text-center mx-auto w-100">
							<h4 class="INL_technology_text_content"><spring:theme code="economic.infralogistics.technology.zones.alraidah.digital.city.text"/></h4>
						  </div>
						</div>
					</a>
				</div>
				<div class=" col-md-4 mx-auto">
                   <c:choose>
                        <c:when test="${currentLanguage.isocode eq 'en'}">
                         <a href="https://modon.gov.sa/en/Cities/TechnologyZones/Pages/TechZone.aspx?ZoneId=8a703703-ea82-493f-9702-1330832d9668" target="_blank">
                        </c:when>
                        <c:otherwise>
                            <a href="https://modon.gov.sa/ar/Cities/TechnologyZones/Pages/TechZone.aspx?ZoneId=8a703703-ea82-493f-9702-1330832d9668" target="_blank">
                        </c:otherwise>
                    </c:choose>
						<div class="INL_technology_Text_Block">
						  <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/tech/Wadi_Makkah_Technological_Complex.jpg"  style="width:100%;" loading="lazy">
						  <div class="INL_technology_text text-center mx-auto w-100">
							<h4 class="INL_technology_text_content"><spring:theme code="economic.infralogistics.technology.zones.wadimakkah.complex.text"/></h4>
						  </div>
						</div>
					</a>
				</div>
			</div>
          </div>
        </section>
        <!-- -----------Technology Zones--------------- -->

        <!-- ----------Technology Zones ---------------- -->
        <section class="infraLogisticsTechZone" id="">
          <div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="INL_bg_gold_header">
						<h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.infralogistics.economic.cities.heading.text"/></h1>
					</div>
				</div>
			</div>
			<div class="row pt-5 pb-5 w-80  mx-auto">
				<div class="col-md-6 mx-auto">
					<a href="http://ecza.gov.sa/webcenter/faces/oracle/webcenter/page/scopedMD/s8bba98ff_4cbb_40b8_beee_296c916a23ed/businessRolePages/Page139.jspx?_adf.ctrl-state=15t3l15ynz_4&wc.contextURL=%2Fspaces&wc.contentSource=%2Fportal%2Fhome?wc.contentSource=%2Fportal%2Fhome&_afrLoop=49251248903339502" target="_blank">
						<div class="INL_technology_Text_Block">
						  <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/tech/Knowledge_Economic_City.jpg"  style="width:100%;" loading="lazy">
						  <div class="INL_technology_text text-center mx-auto w-100">
							<h4 class="INL_technology_text_content"><spring:theme code="economic.infralogistics.economic.cities.knowledge.economic.city.text"/></h4>
						  </div>
						</div>
					</a>
				</div>
				<div class="col-md-6 mx-auto">
					<a href="https://eur03.safelinks.protection.outlook.com/?url=http%3A%2F%2Fecza.gov.sa%2Fwebcenter%2Ffaces%2FmainNavInclude%2FecoCityCont%2FKAEC%3F_adf.ctrl-state%3D1dqzdhrr50_4%26wc.contentSource%3D%252Fportal%252Fhome%3Fwc.contentSource%3D%252Fportal%252Fhome%26wcnav.model%3D%252Foracle%252Fwebcenter%252Fsiteresources%252FscopedMD%252Fs8bba98ff_4cbb_40b8_beee_296c916a23ed%252Fnavigation%252Fgsr6e4c8b1a_55bc_4496_93ac_21c1bfe2c23e%252FNavigation%26_afrLoop%3D3601923884509849&data=04%7C01%7CSusanna.Joseph%40in.bosch.com%7C4f3066a5f14746909ad808d99267b7c7%7C0ae51e1907c84e4bbb6d648ee58410f4%7C0%7C0%7C637701796446809110%7CUnknown%7CTWFpbGZsb3d8eyJWIjoiMC4wLjAwMDAiLCJQIjoiV2luMzIiLCJBTiI6Ik1haWwiLCJXVCI6Mn0%3D%7C1000&sdata=sZJl%2Ff%2Ft51sME2BPlBOH50KUKhBaIw5XA62mEKtVrGk%3D&reserved=0" target="_blank">
						<div class="INL_technology_Text_Block">
						  <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/tech/King_Abdullah Economic_City.jpg"  style="width:100%;" loading="lazy">
						  <div class="INL_technology_text text-center mx-auto w-100">
							<h4 class="INL_technology_text_content"><spring:theme code="economic.infralogistics.economic.cities.kingabdullah.economic.city.text"/></h4>
						  </div>
						</div>
					</a>
				</div>
			</div>
          </div>
        </section>
        <!-- -----------Technology Zones--------------- -->

</jsp:body>
</template:portalpage>
