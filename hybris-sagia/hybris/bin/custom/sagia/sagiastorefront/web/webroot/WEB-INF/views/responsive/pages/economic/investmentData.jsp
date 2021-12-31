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
<!-- <section class="eco-banner eco-banner-Inv position-relative">
  <div class="eco-banner-container" data-aos="fade-up">
    <h1><spring:theme code="economic.investmentdata.page.heading.name"/></h1>
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
        <div class="eh-page-link">
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
        <div class="eh-page-link active">
          <a href="${encodedContextPath}/economicHighlights/investmentData">
            <spring:theme code="economic.highlights.investmentdata.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
      </div>
    </div>
  </section>
</section> -->

<!-- Banner  -->

<!--  Investment Data start-->
<section class="" id="">
  <div class="container">
<div class="row INDS_tabs_centers">

<div class="col-md-12">
  <div id="investment-json-data" style="display: none;">
    <c:if test="${not empty annualValueJson}">
      <div id="annualValueJson">
        ${annualValueJson}
      </div>
    </c:if>
    <c:if test="${not empty annualGrowthJson}">
      <div id="annualGrowthJson">
        ${annualGrowthJson}
      </div>
    </c:if>
    <c:if test="${not empty quarterlyValueJson}">
      <div id="quarterlyValueJson">
        ${quarterlyValueJson}
      </div>
    </c:if>
    <c:if test="${not empty quarterlyGrowthJson}">
      <div id="quarterlyGrowthJson">
        ${quarterlyGrowthJson}
      </div>
    </c:if>
    <c:if test="${not empty capitalInformationJson}">
      <div id="capitalInformationJson">
        ${capitalInformationJson}
      </div>
    </c:if>
    <c:if test="${not empty annualFundAssetsJson}">
      <div id="annualFundAssetsJson">
        ${annualFundAssetsJson}
      </div>
    </c:if>
    <c:if test="${not empty quarterlyFundAssetsJson}">
      <div id="quarterlyFundAssetsJson">
        ${quarterlyFundAssetsJson}
      </div>
    </c:if>
    <c:if test="${not empty numberOfCommercialRegisterJson}">
      <div id="numberOfCommercialRegisterJson">
        ${numberOfCommercialRegisterJson}
      </div>
    </c:if>
    <c:if test="${not empty capitalOfCommercialRegisterJson}">
      <div id="capitalOfCommercialRegisterJson">
        ${capitalOfCommercialRegisterJson}
      </div>
    </c:if>
  </div>


      <ul class="nav nav-tabs  mt-3 pr-0">
        <li class="nav-item">
          <a class="nav-link active tabs_bg_gray_color" href="#FDI_Data" data-toggle="tab" role="tab"><spring:theme code="economic.investmentdata.fdidata.tab.text"/></a>
        </li>
         <li class="nav-item">
         <a class="nav-link tabs_bg_gray_color" href="#Domestic_Data" data-toggle="tab" role="tab"><spring:theme code="economic.investmentdata.domesticdata.tab.text"/></a>
         </li>
      </ul>
      <div class="tab-content">
        <!--- FDI Data Start--->
        <div id="FDI_Data" class="tab-pane fade active show" role="tabpanel">

            <div class="col-md-12">
              <div class="w-tab1 h-100 w-100 pt-4 mt-4 pb-4 mb-4">

                <div class="row p-0">
                  <!-- aside hand left-->
                  <aside class="col-lg-3 INDS_gray_color_aside p-0">
                    <h3 class="text-center INDS_Filter_by p-3"><spring:theme code="economic.investmentdata.filterby.section.heading.name"/></h3>
                    <h5 class="text-center INDS_select_sector p-3"><spring:theme code="economic.investmentdata.selectsector.heading.name"/></h5>

                    <!-- <div class="input-group px-4 pb-4">
                      <input class="form-control py-2 INDS_select_sector_input" type="search" value="search" id="example-search-input">
                      <span class="input-group-append">
                        <button class="btn btn-outline-secondary INDS_select_sector_input_btn" type="button">
                          <img src="img\Infrastructures_and_Logistics\data\search.png" class="img-fluid">
                        </button>
                      </span>
                    </div> -->
                    <!-- <div class="row">
                      <div class=" mx-auto">
                        <div class="input-group px-4 pb-4 ">
                          <input class="form-control border-end-0 border rounded-pill" type="search" value="search" id="example-search-input" style="border-radius: 25px 0px 0px 25px;">
                          <span class="input-group-append">
                            <button class="btn btn-outline-secondary bg-white border-bottom-0 rounded-pill ms-n5" type="button" style="border-radius: 0px 25px 25px 0px;    border: 0px;">
                              <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/search.png" class="img-fluid">
                            </button>
                          </span>
                        </div>
                      </div>
                    </div> -->

                    <div class="INDS_HLs_Checkbox">
                      <div class="INDS_HL_Checkbox INDS_padding_15px25 investment-data">
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.manufacturinglicenses.checkbox.text"/>
                          <input type="checkbox" data-id="Manufacturing Licenses" id="manufacturingLicenses" name="manufacturingLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.constructionlicenses.checkbox.text"/>
                          <input type="checkbox" data-id="Construction Licenses" id="constructionLicenses" name="constructionLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.wholesaleretail.checkbox.text"/>
                          <input type="checkbox" data-id="Wholesale and retail trade, repair of mo Licenses" id="wholeSaleRetailTradeMoLicenses" name="wholeSaleRetailTradeMoLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.informationcommunication.checkbox.text"/>
                          <input type="checkbox" data-id="Information and communication Licenses" id="informationAndCommunicationLicenses" name="informationAndCommunicationLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.professionalscientific.checkbox.text"/>
                          <input type="checkbox" data-id="Professional, scientific and technical Licenses" id="professionalScientificAndTechnicalLicenses" name="professionalScientificAndTechnicalLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.accommodationfood.checkbox.text"/>
                          <input type="checkbox" data-id="Accommodation and food service actives Licenses" id="accommodationFoodServiceActivityLicenses" name="accommodationFoodServiceActivityLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.administrationstorage.checkbox.text"/>
                          <input type="checkbox" data-id="Administration and storage Licenses" id="administrativeSupportServiceActivityLicenses" name="administrativeSupportServiceActivityLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.transportstorage.checkbox.text"/>
                          <input type="checkbox" data-id="Transportation and storage Licenses" id="transportationStorageLicenses" name="transportationStorageLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.humanhealth.checkbox.text"/>
                          <input type="checkbox" data-id="Human health and social work activities licenses" id="humanHealthSocialworkActivityLicenses" name="humanHealthSocialworkActivityLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.financialinsurance.checkbox.text"/>
                          <input type="checkbox" data-id="Financial and insurance activities licenses" id="financialInsuranceActivityLicenses" name="financialInsuranceActivityLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.serviceactivities.checkbox.text"/>
                          <input type="checkbox" data-id="Other service activities licenses" id="otherServiceActivityLicenses" name="otherServiceActivityLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.miningquarrying.checkbox.text"/>
                          <input type="checkbox" data-id="Mining and quarrying licenses" id="miningQuarryingLicenses" name="miningQuarryingLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.watersupply.checkbox.text"/>
                          <input type="checkbox" data-id="Water supply; sewerage, waste management" id="waterSupplySewerageWasteManagement" name="waterSupplySewerageWasteManagement">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.education.checkbox.text"/>
                          <input type="checkbox" data-id="Education licenses" id="educationLicenses" name="educationLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.realestate.checkbox.text"/>
                          <input type="checkbox" data-id="Real estate activities licenses" id="realEstateActivityLicenses" name="realEstateActivityLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.artentertainment.checkbox.text"/>
                          <input type="checkbox" data-id="Arts, entertainment and recreation licenses" id="artsEntertainmentRecreationLicenses" name="artsEntertainmentRecreationLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.electricitygas.checkbox.text"/>
                          <input type="checkbox" data-id="Electricity, gas, steam and air conditioner" id="electricityGasSteamAircondition" name="electricityGasSteamAircondition">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.agriculture.checkbox.text"/>
                          <input type="checkbox" data-id="Agriculture, forestry and fishing licenses" id="agricultureForestryFishingLicenses" name="agricultureForestryFishingLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.notassigned.checkbox.text"/>
                          <input type="checkbox" data-id="Not assigned licenses" id="notAssignedLicenses" name="notAssignedLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.publicadmin.checkbox.text"/>
                          <input type="checkbox" data-id="Public administration and defence compu licenses" id="publicAdministrationDefenceCompuLicenses" name="publicAdministrationDefenceCompuLicenses">
                          <span class="checkmark"></span>
                        </label>
                        <label class="checkbox-container" style="margin-bottom: 15px;"><spring:theme code="economic.investmentdata.extraterritorial.checkbox.text"/>
                          <input type="checkbox" data-id="Activities of extraterritorial organizat license" id="activitiesOfExtraterritorialOrganizationLicenses" name="activitiesOfExtraterritorialOrganizationLicenses">
                          <span class="checkmark"></span>
                        </label>
                      </div>
                    </div>

                    <div class="pt-4 mt-3 INDS_select_tab">
                      <h5 class="text-center INDS_select_sector p-3"><spring:theme code="economic.investmentdata.selectperiod.heading.name"/></h5>

                      <ul class="nav nav-tabs m-4 sector-period">
                        <li class="nav-item">
                          <a class="nav-link active" data-id="Annually" data-toggle="tab" role="tab" style="border-radius: 25px 0px 0px 25px;"><spring:theme code="economic.investmentdata.annually.tab.text"/></a>
                        </li>
                         <li class="nav-item">
                         <a class="nav-link " data-id="Quarterly" data-toggle="tab" role="tab"><spring:theme code="economic.investmentdata.quarterly.tab.text"/></a>
                         </li>
                      </ul>
                      <div class="tab-content">
                        <!--- Annualy Start--->
                        <div id="Annualy" class="tab-pane fade active show" role="tabpanel">
                          <h5 class="text-center INDS_select_sector p-2"><spring:theme code="economic.investmentdata.selectyear.heading.name"/></h5>
                          <div class="content-wrapper p-2">
                            <div class="content-header p-2">
                              <div class="input-group d-block w-100">
                                <label class="w-25 mb-3"><spring:theme code="economic.investmentdata.from.text"/></label>
                                <input type="text" class="sectorStartYear form-control" value="" style="margin-left: 20px;" onkeypress="return isNumber(event)" maxlength="4"/>
                                <!-- <input class="form-control" type="text" placeholder="2016"> -->
                                <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="sectorStartYear"/>
                              </div>
                              <div class="input-group d-block w-100">
                                <label class="w-25"><spring:theme code="economic.investmentdata.to.text"/></label>
                                <input type="text" class="sectorEndYear form-control" value="" style="margin-left: 20px;" onkeypress="return isNumber(event)" maxlength="4"/>
                                <!-- <input class="form-control" type="text" placeholder="2020"> -->
                                <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="sectorEndYear"/>
                              </div>
                              <div class="btn-group button-group pt-3 mt-3">
                                <form:form id="sector-filter-form" action="/investmentData">
                                  <a href="#" class="btn btn-primary reset mr-2 filter-reset-search"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20" viewBox="0 0 20 20">
                                    <defs>
                                      <clipPath id="clip-reset_icon">
                                      <rect width="20" height="20"/>
                                      </clipPath>
                                    </defs>
                                    <g id="reset_icon" clip-path="url(#clip-reset_icon)">
                                      <g id="noun_reset_3324723" transform="translate(1 1)">
                                      <g id="Group_1147" data-name="Group 1147" transform="translate(0 0)">
                                        <path id="Path_1979" data-name="Path 1979" d="M25.683,10.8a.724.724,0,0,1-.717.629.739.739,0,0,1-.1-.006l-1.258-.167A8.867,8.867,0,1,1,16.368,7.5a.724.724,0,0,1,0,1.448,7.429,7.429,0,1,0,6.061,3.144l-.069,1.595a.724.724,0,0,1-.723.693h-.032a.724.724,0,0,1-.692-.755l.145-3.372c0-.011,0-.02,0-.031s0-.014,0-.022,0-.007,0-.011h0c0-.014.007-.029.01-.043s.009-.043.015-.064,0-.022.008-.033.012-.026.018-.039.016-.039.026-.057.01-.023.016-.033.018-.025.027-.038.021-.03.033-.045l.01-.014c.005-.006.009-.013.014-.02s.024-.021.035-.033l.034-.031c.012-.01.021-.021.033-.03s.031-.02.046-.03l.032-.019c.014-.008.027-.018.041-.025s.034-.013.05-.02l.037-.013c.015-.005.029-.012.045-.016s.033-.006.049-.009l.049-.008c.014,0,.027-.005.042-.006s.03,0,.045,0,.022,0,.033,0,.021,0,.032,0h.035l3.18.421a.724.724,0,0,1,.623.813Z" transform="translate(-7.497 -7.5)" fill="#00a6be"/>
                                      </g>
                                      </g>
                                    </g>
                                    </svg>
                                    <span>
                                      <spring:theme code="economic.investmentdata.reset.button.text"/>
                                    </span></a>

                                  <a id="sector-filter" class="btn btn-primary apply"><spring:theme code="economic.investmentdata.apply.button.text"/></a>
                                </form:form>

                              </div>
                            </div>
                          </div>
                        </div>
                        <!--- Annualy end--->
                        <!--- Quaterly Start--->
                        <div id="Quaterly" class="tab-pane fade " role="tabpanel">
                          <h5 class="text-center INDS_select_sector p-2"><spring:theme code="economic.investmentdata.selectdaterange.text"/></h5>
                          <div class="content-wrapper p-2">
                            <div class="content-header p-2">
                              <div class="input-group d-block w-100">
                                <label class="w-25 mb-3"><spring:theme code="economic.investmentdata.from.text"/></label>
                                <input class="form-control" type="text" placeholder="2016">
                                <img src="./img/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" />
                              </div>
                              <div class="input-group d-block w-100">
                                <label class="w-25"><spring:theme code="economic.investmentdata.to.text"/></label>
                                <input class="form-control" type="text" placeholder="2020">
                                <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" />
                              </div>
                              <div class="btn-group button-group pt-3 mt-3">
                                <div class="col-6">
                                <a href="#" class="btn btn-primary reset filter-reset-search mr-2"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20" viewBox="0 0 20 20">
                                  <defs>
                                    <clipPath id="clip-reset_icon">
                                    <rect width="20" height="20"/>
                                    </clipPath>
                                  </defs>
                                  <g id="reset_icon" clip-path="url(#clip-reset_icon)">
                                    <g id="noun_reset_3324723" transform="translate(1 1)">
                                    <g id="Group_1147" data-name="Group 1147" transform="translate(0 0)">
                                      <path id="Path_1979" data-name="Path 1979" d="M25.683,10.8a.724.724,0,0,1-.717.629.739.739,0,0,1-.1-.006l-1.258-.167A8.867,8.867,0,1,1,16.368,7.5a.724.724,0,0,1,0,1.448,7.429,7.429,0,1,0,6.061,3.144l-.069,1.595a.724.724,0,0,1-.723.693h-.032a.724.724,0,0,1-.692-.755l.145-3.372c0-.011,0-.02,0-.031s0-.014,0-.022,0-.007,0-.011h0c0-.014.007-.029.01-.043s.009-.043.015-.064,0-.022.008-.033.012-.026.018-.039.016-.039.026-.057.01-.023.016-.033.018-.025.027-.038.021-.03.033-.045l.01-.014c.005-.006.009-.013.014-.02s.024-.021.035-.033l.034-.031c.012-.01.021-.021.033-.03s.031-.02.046-.03l.032-.019c.014-.008.027-.018.041-.025s.034-.013.05-.02l.037-.013c.015-.005.029-.012.045-.016s.033-.006.049-.009l.049-.008c.014,0,.027-.005.042-.006s.03,0,.045,0,.022,0,.033,0,.021,0,.032,0h.035l3.18.421a.724.724,0,0,1,.623.813Z" transform="translate(-7.497 -7.5)" fill="#00a6be"/>
                                    </g>
                                    </g>
                                  </g>
                                  </svg>
                                  <span>
                                    <spring:theme code="economic.investmentdata.reset.button.text"/>
                                  </span></a>
                                </div>
                                <div class="col-6">
                                <a href="#" class="btn btn-primary apply"><spring:theme code="economic.investmentdata.apply.button.text"/></a>
                              </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!--- Quaterly end--->
                      </div>
                    </div>

                  </aside>
                  <!-- aside hand left-->
                  <div class="col-lg-9 p-0">

                    <div>
                      <div id="selectedSectorsList" class="row INID_apadding_050px">
                        <!-- <div class="INID_border_filter_in_line ">Manufacturing Licenses   <span class="INDS_Filter_by_Close">X</span></div>
                        <div class="INID_border_filter_in_line">Construction licenses   <span class="INDS_Filter_by_Close">X</span></div>
                        <div class="INID_border_filter_in_line">Wholesale And Retail Trade; Repair Of Mo Licenses   <span class="INDS_Filter_by_Close">X</span></div>
                        <div class="INID_border_filter_in_line">Information and communication licenses   <span class="INDS_Filter_by_Close">X</span></div>
                        <div class="INID_border_filter_in_line">Professional, Scientific And Technical Licenses   <span class="INDS_Filter_by_Close">X</span></div>
                        <div class="INID_border_filter_in_line">Annualy   <span class="INDS_Filter_by_Close">X</span></div>
                        <div class="INID_border_filter_in_line">From 2016 To 2020   <span class="INDS_Filter_by_Close">X</span></div> -->
                      </div>
                    </div>

                    <section class="" id="">
                      <div class="INID_BG_image">
                        <div class="container">
                        <div class="row">
                          <div class="col-md-12">
                            <div class="INL_bg_gold_header">
                              <h1 class="INID_for_header text-center w-100"><spring:theme code="economic.investmentdata.foreigninvestmentvalue.section.heading.text"/></h1>
                            </div>
                          </div>
                        </div>
                        <div class="row pt-5 pl-3 pr-3 pb-4">

                          <div class="col-4 mx-auto">
                            <div class="INID_SD_Data_IN_sho">
                              <h4 class="INID_SD_inv_data_header value-inflow">${foreignInvestmentValueData[0].value1}</h4>
                              <p class="INID_SUB_data">${foreignInvestmentValueData[0].label1}</p>
                              <br>
                              <h4 class="INID_SD_inv_data_header p-2">${foreignInvestmentValueData[0].value2}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentValueData[0].label2}</p>
                              <br>
                              <h5 class="INID_SD_header_green">${foreignInvestmentValueData[0].value3}</h5>
                              <p class="INID_word_paper2">${foreignInvestmentValueData[0].label3}</p>
                            </div>
                          </div>
                          <div class="col-4 mx-auto">
                            <div class="INID_SD_Data_IN_sho">
                              <h4 class="INID_SD_inv_data_header value-outflow">${foreignInvestmentValueData[1].value1}</h4>
                              <p class="INID_SUB_data">${foreignInvestmentValueData[1].label1}</p>
                              <br>
                              <h4 class="INID_SD_inv_data_header p-2">${foreignInvestmentValueData[1].value2}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentValueData[1].label2}</p>
                              <br>
                              <h5 class="INID_SD_header_green">${foreignInvestmentValueData[1].value3}</h5>
                              <p class="INID_word_paper2">${foreignInvestmentValueData[1].label3}</p>
                            </div>
                          </div>
                          <div class="col-4 mx-auto">
                            <div class="INID_SD_Data_IN_sho">
                              <h4 class="INID_SD_inv_data_header value-total">${foreignInvestmentValueData[2].value1}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentValueData[2].label1}</p>
                              <br>
                              <h4 class="INID_SD_inv_data_header p-2">${foreignInvestmentValueData[2].value2}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentValueData[2].label2}</p>
                              <br>
                              <h5 class="INID_SD_header_green">${foreignInvestmentValueData[2].value3}</h5>
                              <p class="INID_word_paper2" style="height: 24px;">${foreignInvestmentValueData[2].label3}</p>
                            </div>
                          </div>

                        </div>



                        </div>
                      </div>
                      <div class="row p-0 m-4 INID_row_border_image">

                      <div class="col-12">

                        <div class="text-center mt-4 pt-3">
                          <h4 class="INID_Select_over_header"><spring:theme code="economic.investmentdata.graph.heading.text"/></h4>
                        </div>
                        <div class="d-flex">
                          <!-- <div class="pull-left text-left">
                            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/two_chart.png" class="img-fluid">
                          </div> -->
                          <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
                            <span class="INL_Select_download">Download</span>
                            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/PDF_icon1.png" class="img-fluid">
                            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/xls_icon1.png" class="img-fluid">
                          </div> -->
                        </div>

                      </div>
                      <div id="renderFDIValueChartDiv" class="w-100 text-center" dir="ltr"></div> 
                      <div id="ChartDivError" class="w-100 text-center" style="display: none;">
                        No Data Available
                      </div>
                      <!-- <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/Number of Foreign Licenses.png" class="img-fluid m-4"> -->
                      </div>
                    </section>

                    <section class="" id="">
                      <div class="INID_BG_image">
                        <div class="container">
                        <div class="row">
                          <div class="col-md-12">
                            <div class="INL_bg_gold_header">
                              <h1 class="INID_for_header text-center w-100"><spring:theme code="economic.investmentdata.foreigninvestmentgrowth.section.heading.text"/></h1>
                            </div>
                          </div>
                        </div>
                        <div class="row pt-5 pl-3 pr-3 pb-4">
                          <div class="col-4 mx-auto">
                            <div class="INID_SD_Data_IN_sho">
                              <h4 class="INID_SD_inv_data_header value-inflow">${foreignInvestmentGrowthData[0].value1}</h4>
                              <p class="INID_SUB_data">${foreignInvestmentGrowthData[0].label1}</p>
                              <br>
                              <h4 class="INID_SD_inv_data_header p-2">${foreignInvestmentGrowthData[0].value2}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentGrowthData[0].label2}</p>
                              <br>
                              <h5 class="INID_SD_header_green">${foreignInvestmentGrowthData[0].value3}</h5>
                              <!-- <p class="INID_word_paper2">${foreignInvestmentGrowthData[0].label3}</p> -->
                            </div>
                          </div>
                          <div class="col-4 mx-auto">
                            <div class="INID_SD_Data_IN_sho">
                              <h4 class="INID_SD_inv_data_header value-outflow">${foreignInvestmentGrowthData[1].value1}</h4>
                              <p class="INID_SUB_data">${foreignInvestmentGrowthData[1].label1}</p>
                              <br>
                              <h4 class="INID_SD_inv_data_header p-2">${foreignInvestmentGrowthData[1].value2}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentGrowthData[1].label2}</p>
                              <br>
                              <h5 class="INID_SD_header_green">${foreignInvestmentGrowthData[1].value3}</h5>
                              <!-- <p class="INID_word_paper2">${foreignInvestmentGrowthData[1].label3}</p> -->
                            </div>
                          </div>
                          <div class="col-4 mx-auto">
                            <div class="INID_SD_Data_IN_sho">
                              <h4 class="INID_SD_inv_data_header value-total">${foreignInvestmentGrowthData[2].value1}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentGrowthData[2].label1}</p>
                              <br>
                              <h4 class="INID_SD_inv_data_header p-2">${foreignInvestmentGrowthData[2].value2}</h4>
                              <p class="INID_SUB_data ">${foreignInvestmentGrowthData[2].label2}</p>
                              <br>
                              <h5 class="INID_SD_header_green">${foreignInvestmentGrowthData[2].value3}</h5>
                              <!-- <p class="INID_word_paper2" style="height: 24px;">${foreignInvestmentGrowthData[2].label3}</p> -->
                            </div>
                          </div>

                        </div>



                        </div>
                      </div>
                      <div class="row p-0 m-4 INID_row_border_image">

                      <div class="col-12">

                        <div class="text-center mt-4 pt-3">
                          <h4 class="INID_Select_over_header"><spring:theme code="economic.investmentdata.growthrate.graph.heading.text"/></h4>
                        <!-- </div> -->

                      </div>
                      <div id="renderFDIGrowthChartDiv" class="w-100 text-center" dir="ltr"></div>
                      <div id="growthChartDivError" class="w-100 text-center" style="display: none;">
                        No Data Available
                      </div>
                      <!-- <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/Number of Foreign Licenses.png" class="img-fluid m-4"> -->
                      </div>
                    </section>


                  </div>
                </div>

              </div>
            </div>

        </div>
        <!--- FDI Data End--->

        <!--- Domestic Data Start--->
        <div id="Domestic_Data" class="tab-pane fade" role="tabpanel">


          <section class="economic-heightlight-container">
            <!-- Wrap the rest of the page in another container to center all the content. -->
            <div class="row p-1">

              <!-- economic-section content start -->
              <div class="content-wrapper w-100">
                <div class="content-header">
                  <h4><spring:theme code="economic.investmentdata.fixedcapital.heading.name"/></h4>
                  <div class="row">
                    <div class="col-lg-3">
                      <h5><spring:theme code="economic.investmentdata.selectindicator.text"/></h5>
                      <a href="#" class="btn btn-primary"><spring:theme code="economic.investmentdata.selectindicator.text"/></a>
                    </div>
                    <div class="col-lg-6 ">
                      <h5><spring:theme code="economic.investmentdata.selectdaterange.text"/></h5>
                      <div class="d-inline w-100">
                      <div class="input-group w-40">
                        <label><spring:theme code="economic.investmentdata.from.text"/></label>
                        <input class="form-control capitalStartYear" type="text" onkeypress="return isNumber(event)" maxlength="4">
                        <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="capitalStartYear"/>
                      </div>
                      <div class="input-group w-40">
                        <label><spring:theme code="economic.investmentdata.to.text"/></label>
                        <input class="form-control capitalEndYear" type="text" onkeypress="return isNumber(event)" maxlength="4">
                        <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="capitalEndYear"/>
                      </div>
                    </div>
                    </div>
                    <div class="col-lg-3  pt-5">
                      <div class="btn-group button-group w-100 gross-fix-btn-grp">
                        <div class="col-6">
                        <a href="#" class="btn btn-primary reset filter-reset-search mr-2"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20" viewBox="0 0 20 20">
                          <defs>
                            <clipPath id="clip-reset_icon">
                            <rect width="20" height="20"/>
                            </clipPath>
                          </defs>
                          <g id="reset_icon" clip-path="url(#clip-reset_icon)">
                            <g id="noun_reset_3324723" transform="translate(1 1)">
                            <g id="Group_1147" data-name="Group 1147" transform="translate(0 0)">
                              <path id="Path_1979" data-name="Path 1979" d="M25.683,10.8a.724.724,0,0,1-.717.629.739.739,0,0,1-.1-.006l-1.258-.167A8.867,8.867,0,1,1,16.368,7.5a.724.724,0,0,1,0,1.448,7.429,7.429,0,1,0,6.061,3.144l-.069,1.595a.724.724,0,0,1-.723.693h-.032a.724.724,0,0,1-.692-.755l.145-3.372c0-.011,0-.02,0-.031s0-.014,0-.022,0-.007,0-.011h0c0-.014.007-.029.01-.043s.009-.043.015-.064,0-.022.008-.033.012-.026.018-.039.016-.039.026-.057.01-.023.016-.033.018-.025.027-.038.021-.03.033-.045l.01-.014c.005-.006.009-.013.014-.02s.024-.021.035-.033l.034-.031c.012-.01.021-.021.033-.03s.031-.02.046-.03l.032-.019c.014-.008.027-.018.041-.025s.034-.013.05-.02l.037-.013c.015-.005.029-.012.045-.016s.033-.006.049-.009l.049-.008c.014,0,.027-.005.042-.006s.03,0,.045,0,.022,0,.033,0,.021,0,.032,0h.035l3.18.421a.724.724,0,0,1,.623.813Z" transform="translate(-7.497 -7.5)" fill="#00a6be"/>
                            </g>
                            </g>
                          </g>
                          </svg>
                          <span>
                            <spring:theme code="economic.investmentdata.reset.button.text"/>
                          </span>
                          </a>
                        </div>
                        <div class="col-6">
                        <form:form id="capital-data-form" action="/investmentData">
                          <a id="capital-data-submit" class="btn btn-primary apply"><spring:theme code="economic.investmentdata.apply.button.text"/></a>
                        </form:form>
                      </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="content-body">
                  <div id="capitalInformationChartDiv" class="w-100 text-center" dir="ltr"></div>
                  <div id="ChartDivError" class="w-100 text-center" style="display: none;">
                    No Data Available
                  </div>
                  <!-- <img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/Commercial Registers of Companies.png" alt="" /> -->
                  <!-- <div class="reports-download">
                    <a href="#" class="">Download</a>
                    <a href="#" class=""><img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/PDF_icon.png" alt="" /></a>
                    <a href="#" class=""><img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/xls_icon.png" alt="" /></a>
                  </div> -->
                </div>
              </div>
              <div class="content-wrapper w-100">
                <div class="content-header">
                  <h4><spring:theme code="economic.investmentdata.fundassets.heading.text"/></h4>
                  <div class="row">
                    <div class="col-md-12 indicator">
                      <h5><spring:theme code="economic.investmentdata.selectindicator.text"/></h5>
                      <a data-id="totalNoOfInvestmentFund" class="btn btn-primary fund-asset-indicator active"><spring:theme code="economic.investmentdata.totalinvestment.button.text"/></a>
                      <a data-id="totalInvestmentFundAssets" class="btn btn-primary fund-asset-indicator"><spring:theme code="economic.investmentdata.totalassets.button.text"/></a>
                      <a data-id="foreignInvestmentFund" class="btn btn-primary fund-asset-indicator"><spring:theme code="economic.investmentdata.foreigninvestment.button.text"/></a>
                      <a data-id="domesticInvestmentFund" class="btn btn-primary fund-asset-indicator"><spring:theme code="economic.investmentdata.domesticinvestment.button.text"/></a>
                    </div>
                    <div class="col-lg-12 col-12 content-flex ">
                    <div class="col-lg-3">
                      <h5><spring:theme code="economic.investmentdata.selectperiod.heading.name"/></h5>
                      <div class="btn-group btn-group-toggle fund-assets-period" data-toggle="buttons">
                        <label class="btn btn-secondary active" data-id="Annually">
                          <input type="radio" name="options" id="option1" autocomplete="off" checked> <spring:theme code="economic.investmentdata.annually.tab.text"/>
                        </label>
                        <label class="btn btn-secondary" data-id="Quarterly">
                          <input type="radio" name="options" id="option2" autocomplete="off"> <spring:theme code="economic.investmentdata.quarterly.tab.text"/>
                        </label>
                      </div>

                    </div>
                    <div class="col-lg-6 pb-4 fund-asset-range">                     
                      <h5><spring:theme code="economic.investmentdata.selectdaterange.text"/></h5>
                      <div class="d-inline w-100">
                      <div class="input-group w-40">
                        <label><spring:theme code="economic.investmentdata.from.text"/></label>
                        <input class="form-control fundAssetStartYear" type="text" placeholder="2016" onkeypress="return isNumber(event)" maxlength="4">
                        <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="fundAssetStartYear"/>
                      </div>
                      <div class="input-group w-40">
                        <label><spring:theme code="economic.investmentdata.to.text"/></label>
                        <input class="form-control fundAssetEndYear" type="text" placeholder="2020" onkeypress="return isNumber(event)" maxlength="4">
                        <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="fundAssetEndYear"/>
                      </div>
                    </div>
                    </div>
                    <div class="col-lg-3 pt-5 px-0">
                      <div class="btn-group button-group ml-3 fund-asset-btn-grp w-100">
                        <div class="col-6">
                        <a href="#" class="btn btn-primary reset filter-reset-search mr-2"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20" viewBox="0 0 20 20">
                          <defs>
                            <clipPath id="clip-reset_icon">
                            <rect width="20" height="20"/>
                            </clipPath>
                          </defs>
                          <g id="reset_icon" clip-path="url(#clip-reset_icon)">
                            <g id="noun_reset_3324723" transform="translate(1 1)">
                            <g id="Group_1147" data-name="Group 1147" transform="translate(0 0)">
                              <path id="Path_1979" data-name="Path 1979" d="M25.683,10.8a.724.724,0,0,1-.717.629.739.739,0,0,1-.1-.006l-1.258-.167A8.867,8.867,0,1,1,16.368,7.5a.724.724,0,0,1,0,1.448,7.429,7.429,0,1,0,6.061,3.144l-.069,1.595a.724.724,0,0,1-.723.693h-.032a.724.724,0,0,1-.692-.755l.145-3.372c0-.011,0-.02,0-.031s0-.014,0-.022,0-.007,0-.011h0c0-.014.007-.029.01-.043s.009-.043.015-.064,0-.022.008-.033.012-.026.018-.039.016-.039.026-.057.01-.023.016-.033.018-.025.027-.038.021-.03.033-.045l.01-.014c.005-.006.009-.013.014-.02s.024-.021.035-.033l.034-.031c.012-.01.021-.021.033-.03s.031-.02.046-.03l.032-.019c.014-.008.027-.018.041-.025s.034-.013.05-.02l.037-.013c.015-.005.029-.012.045-.016s.033-.006.049-.009l.049-.008c.014,0,.027-.005.042-.006s.03,0,.045,0,.022,0,.033,0,.021,0,.032,0h.035l3.18.421a.724.724,0,0,1,.623.813Z" transform="translate(-7.497 -7.5)" fill="#00a6be"/>
                            </g>
                            </g>
                          </g>
                          </svg>
                          <span>
                            <spring:theme code="economic.investmentdata.reset.button.text"/>
                          </span></a>
                        </div>
                        <div class="col-6">
                        <form:form id="fund-asset-form" action="/investmentData">
                          <a id="fund-asset-submit" class="btn btn-primary apply"><spring:theme code="economic.investmentdata.apply.button.text"/></a>
                        </form:form>
                      </div>
                      </div>
                    </div>
                  </div>
                  </div>
                </div>
                <div class="content-body">
                  <div id="fundAssetChartDiv" class="w-100 text-center" dir="ltr"></div>
                  <div id="ChartDivError" class="w-100 text-center" style="display: none;">
                    No Data Available
                  </div>
                  <!-- <img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/Commercial Registers of Companies.png" alt="" /> -->
                  <!-- <div class="reports-download">
                    <a href="#" class="">Download</a>
                    <a href="#" class=""><img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/PDF_icon.png" alt="" /></a>
                    <a href="#" class=""><img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/xls_icon.png" alt="" /></a>
                  </div> -->
                </div>
              </div>
              <div class="content-wrapper w-100">
                <div class="content-header">
                  <h4><spring:theme code="economic.investmentdata.commercialregisters.section.heading.text"/></h4>
                  <div class="row">

                    <div class="col-lg-6 indicator">
                      <h5><spring:theme code="economic.investmentdata.selectindicator.text"/></h5>
                      <a data-id="noOfCommercialRegisters" class="btn btn-primary commercial-indicator active w-100"><spring:theme code="economic.investmentdata.Numberofcommercialregisters.button.text"/></a>
                      <a data-id="capitalOfCommercialRegisters" class="btn btn-primary commercial-indicator w-100"><spring:theme code="economic.investmentdata.capitalofcommercialregisters.button.text"/></a>
                    </div>
                    <div class="col-md-12 col-lg-3">
                      <h5><spring:theme code="economic.investmentdata.selectdaterange.text"/></h5>
                      <div class="input-group d-block w-100">
                        <label class="w-25 mb-3"><spring:theme code="economic.investmentdata.from.text"/></label>
                        <input class="form-control commercialStartYear" type="text" placeholder="2016" onkeypress="return isNumber(event)" maxlength="4">
                        <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="commercialStartYear"/>
                      </div>
                      <div class="input-group d-block w-100">
                        <label class="w-25"><spring:theme code="economic.investmentdata.to.text"/></label>
                        <input class="form-control commercialEndYear" type="text" placeholder="2020" onkeypress="return isNumber(event)" maxlength="4">
                        <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/calendar_icon.png" alt="" class="commercialEndYear"/>
                      </div>
                    </div>
                    <div class="col-md-12 col-lg-3  pt-3 mt-5 com-reg-btn-grp">
                      <div class="btn-group button-group w-100">
                        <div class="col-6">
                        <a href="#" class="btn btn-primary reset mr-2 com-reg-comp"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20" viewBox="0 0 20 20">
                          <defs>
                            <clipPath id="clip-reset_icon">
                            <rect width="20" height="20"/>
                            </clipPath>
                          </defs>
                          <g id="reset_icon" clip-path="url(#clip-reset_icon)">
                            <g id="noun_reset_3324723" transform="translate(1 1)">
                            <g id="Group_1147" data-name="Group 1147" transform="translate(0 0)">
                              <path id="Path_1979" data-name="Path 1979" d="M25.683,10.8a.724.724,0,0,1-.717.629.739.739,0,0,1-.1-.006l-1.258-.167A8.867,8.867,0,1,1,16.368,7.5a.724.724,0,0,1,0,1.448,7.429,7.429,0,1,0,6.061,3.144l-.069,1.595a.724.724,0,0,1-.723.693h-.032a.724.724,0,0,1-.692-.755l.145-3.372c0-.011,0-.02,0-.031s0-.014,0-.022,0-.007,0-.011h0c0-.014.007-.029.01-.043s.009-.043.015-.064,0-.022.008-.033.012-.026.018-.039.016-.039.026-.057.01-.023.016-.033.018-.025.027-.038.021-.03.033-.045l.01-.014c.005-.006.009-.013.014-.02s.024-.021.035-.033l.034-.031c.012-.01.021-.021.033-.03s.031-.02.046-.03l.032-.019c.014-.008.027-.018.041-.025s.034-.013.05-.02l.037-.013c.015-.005.029-.012.045-.016s.033-.006.049-.009l.049-.008c.014,0,.027-.005.042-.006s.03,0,.045,0,.022,0,.033,0,.021,0,.032,0h.035l3.18.421a.724.724,0,0,1,.623.813Z" transform="translate(-7.497 -7.5)" fill="#00a6be"/>
                            </g>
                            </g>
                          </g>
                          </svg>
                          <span>
                            <spring:theme code="economic.investmentdata.reset.button.text"/>
                          </span></a>
                        </div>
                        <div class="col-6">
                          <form:form id="commercial-form" action="/investmentData">
                            <a id="commercial-submit" class="btn btn-primary apply"><spring:theme code="economic.investmentdata.apply.button.text"/></a>
                          </form:form>
                        </div>
                        <!-- <a href="#" class="btn btn-primary apply">apply</a> -->
                      </div>
                    </div>
                    <!-- <div class="row select-chart select-indicator">
                      <div class="col-md-12"> <h5>Select Chart Type</h5></div>
                      <div class="col-md-6">
                        <label class="container">Number
                          <input type="radio" name="commercialType" value="number" id="number" checked>
                          <span class="check"></span>
                        </label>
                      </div>
                      <div class="col-md-6">
                        <label class="container">Growth Rate
                          <input type="radio" name="commercialType" value="growth" id="growth">
                          <span class="check"></span>
                        </label>
                      </div>
                    </div> -->
                  </div>
                </div>
                <div class="content-body">
                  <div id="CommercialChartDiv" class="w-100 text-center" dir="ltr"></div>
                  <div id="ChartDivError" class="w-100 text-center" style="display: none;">
                    No Data Available
                  </div>
                  <!-- <img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/Employment in the Private Sector by Region.png" alt="" />
                  <div class="reports-download">
                    <a href="#" class="">Download</a>
                    <a href="#" class=""><img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/PDF_icon.png" alt="" /></a>
                    <a href="#" class=""><img class="img-fluid" src="${commonResourcePath}/images/Infrastructures_and_Logistics/data/xls_icon.png" alt="" /></a>
                  </div> -->
                </div>
              </div>
            </div>
          </section>									
           
           
           
        </div> 
        <!--- Domestic Data End--->  
        
      </div>
    
  

</div> 
</div>
  </div>  
</section>
<!--  Investment Data start-->


</jsp:body>
</template:portalpage>