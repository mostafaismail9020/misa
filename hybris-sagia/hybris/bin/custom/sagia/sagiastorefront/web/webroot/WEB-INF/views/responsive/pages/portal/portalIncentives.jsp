<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>

<template:portalpage pageTitle="${pageTitle}">
    <jsp:body>
		<header:portalPageTitle />
        
         
        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
            <cms:pageSlot position="PortalPageMain" var="slotComponent">
                <cms:component component="${slotComponent}"/>
            </cms:pageSlot>
        </main>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>
         
               
        <!-- Banner  -->
        <!-- <section class="eco-banner INC-banner-portal">
            <div class="eco-banner-container" data-aos="fade-up">
            <h1>INCENTIVES</h1>
            </div>
        </section> -->
        <!-- 
        <section id="page-breadcrums" class="page-breadcrums">
            <div class="container">
            <div class="row">
                <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Incentives for investors</li>
                </ol>
                </nav>
            </div>
            </div>
        </section>
         -->
        <!-- Banner  -->

         <!-- -------------------------- -->
         <!-- <section class="INS_sections_insdetials" id="">
            <div class="container">
              <div class="row">
                  <p class="INS_para">To facilitate investment in Saudi Arabia, the Kingdom offers appealing incentives designed to encourage investment with the potential to diversify and improve the Kingdom's competitiveness. </p>
                  <p class="INS_para">Special incentives and assistance are offered to foreign-affiliated enterprises and international companies wishing to create offices in the Kingdom, in order to promote clustering activities, ensure knowledge transfer, and accelerate the development of skills and competencies within the Kingdom.</p>
              </div>
            </div>  
          </section> -->
          
          <!-- -------------loan programs Start------------- --> 
          <%-- <section class="INS_sections_insdetials" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">loan programs</h3>				
              </div>
            </div>  
            <div class="INS_loan_bg">
              <div class="container">
                  <div class="row pt-4 pb-4 INS_rowcenter">  
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 INS_img_width">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/arab-fund.jpg" class="img-fluid">
                              </div>
                              <h3 class="INS_loanpbm_header pt-3">Loan program for public/private investment projects</h3>
                              <p class="INS_loanpbm_para">Financing for public/private investment projects offering:</p>
                              <ul class="INS_list_font">
                                  <li>Reduced interest rate</li>
                                  <li>4-6 grace period</li>
                                  <li>Long term extended to 22-25 years</li>
                              </ul> 
                              <div class="text-center pt-2 pb-4">
                                  <a href="https://www.arabfund.org/" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div>
                      </div>	
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 INS_img_width">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Mask Group 114.jpg" class="img-fluid">
                              </div>
                              <h3 class="INS_loanpbm_header pt-3">Loan program for economic development and reform in Arab countries</h3>
                              <p class="INS_loanpbm_para">This program offers loans and facilities to help address the overall balance of payment deficit of member countries, including 4 types of loans that vary in size, terms, and maturity.</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="https://www.amf.org.ae/en" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                              </div> 
                          </div> 
                      </div>	
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 INS_img_width">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/atfp.jpg" class="img-fluid">
                              </div> 
                              <h3 class="INS_loanpbm_header pt-3">Financing facilities for trade transactions of goods of Arab origin</h3>
                              <p class="INS_loanpbm_para">This loan program offers refinancing of Arab exports to Arab & non-Arab countries through lines of credit, e.g., pre-export credits, post-shipment credits, buyers’ credits, and import credits.</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="https://atfp.org.ae/" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div> 
                          
                      </div>			
                  </div>
                  <div class="row pt-4 pb-4 INS_rowcenter">  
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 ">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/20190630_1561924345-744688.jpg" class="img-fluid">
                              </div>
                              <h3 class="INS_loanpbm_header pt-3">Political and commercial risk insurance for investments and export credit guarantees</h3>
                              <p class="INS_loanpbm_para">Insurance schemes and guarantees to encourage investments in Arab countries, and enhance inter-Arab trade and Arab exports worldwide.</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="https://www.dhaman.net/en/" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                              </div> 
                          </div> 
                      </div>	
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Mask Group 115.jpg" class="img-fluid">
                              </div> 
                              <h3 class="INS_loanpbm_header pt-3">Equity and debt financing for enterprises and productive projects in member countries</h3>
                              <p class="INS_loanpbm_para">Offers different financing schemes to support investment capital of projects, grants loans to enterprises and productive projects, and accepts deposits to mobilize financial resources in accordance with the provisions of Islamic law.</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="https://www.isdb.org/" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div> 
                      </div>	
                  </div>
              </div> 
            </div>
          </section> --%>
          <!-- -------------loan programs End------------- --> 
          
          <!-- ------------EnergynUtil Start-------------- -->
          <%-- <section class="INS_sections_insdetials INS_energynutil" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4">ENERGY AND UTILITY ENABLEMENT</h3>				
                  <p class="INS_header_para INS_center_wm0 pb-4">Enabling energy and utilities in industrial cities</p>
              </div>
            </div>  
            <div class="INS_EnergynUtil_bg">
              <div class="container"> 
                  <div class="row pt-4 pb-4 INS_rowcenter">  
                      <a href="https://www.mim.gov.sa/mim/index-ltr.html" target="_blank">
                        <div class="col mb-4">
                            <div class="INS_shadow_pad">
                                <div class="text-center pt-2 pb-4">
                                    <img src="${commonResourcePath}/images/Incentives-for-Investor/logo_ar.jpg" class="img-fluid">
                                </div> 
                            </div>
                        </div>
                      </a>
                      <a href="https://www.aramco.com/" target="_blank">
                      <div class="col mb-4">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4">
                                  <!-- <img src="${commonResourcePath}/images/Incentives-for-Investor/أرامكو السعودية.jpg" class="img-fluid"> -->
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/saudi-aramco.jpg" class="img-fluid">
                              </div> 
                          </div>
                      </div>
                    </a>
                    <a href="https://modon.gov.sa/en/Pages/default.aspx" target="_blank">
                      <div class="col mb-4">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Mask Group 109.jpg" class="img-fluid">
                              </div> 
                          </div>
                      </div>
                    </a>
                    <a href="https://www.se.com.sa/en-us/Pages/home.aspx" target="_blank">
                      <div class="col mb-4">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/1280px-Saudi_Electricity_Company_Logo.svg.jpg" class="img-fluid">
                              </div> 
                          </div>
                      </div> 
                    </a>
                    <a href="https://www.nwc.com.sa/English/pages/default.aspx" target="_blank">
                      <div class="col mb-4">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4">
                                  <!-- <img src="${commonResourcePath}/images/Incentives-for-Investor/شركة-المياه.jpg" class="img-fluid"> -->
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/national-water.jpg" class="img-fluid">
                              </div> 
                          </div>
                      </div>
                    </a>
                  </div>
              </div> 
            </div>
          </section> --%>
          <!-- -------------EnergynUtil End------------- -->
          
          <!-- ------------EXPORT Start-------------- -->
          <%-- <section class="INS_sections_insdetials INS_Financial pb-0" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">EMPLOYMENT SUPPORT PROGRAM</h3>				
              </div>
            </div>   
            <div class="container">
              <div class="row">
                  
                  <div class="col-xs-12 INS_EPM_nav">
                      
                      <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                        <li class="nav-item">
                          <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#Tamheer" role="tab" aria-controls="pills-home" aria-selected="true">
                              <div class="INS_EPM_border_set p-3">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Group 686.jpg" class="img-fluid "> 
                              </div>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#Program" role="tab" aria-controls="pills-profile" aria-selected="false">
                              <div class="INS_EPM_border_set text-center p-3">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Mask Group 116.jpg" class="img-fluid " style="height: 52px;margin: 10px;"> 
                                  <h6 class="INS_EPM_SP_header p-1">Program to support the growth of Saudization of enterprises</h6>
                              </div>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#Professional" role="tab" aria-controls="pills-contact" aria-selected="false">
                              <div class="INS_EPM_border_set text-center p-3">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Mask Group 117.jpg" class="img-fluid" style="height: 52px;margin: 10px;"> 
                                  <h6 class="INS_EPM_SP_header p-2">Professional certification support program</h6>
                              </div>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#Doroob" role="tab" aria-controls="pills-contact" aria-selected="false">
                              <div class="INS_EPM_border_set text-center p-3">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Image 77.jpg" class="img-fluid" style="height: 52px;margin: 10px;"> 
                                  <h6 class="INS_EPM_SP_header p-3">Doroob Program</h6>
                              </div>
                          </a>
                        </li>
                      </ul>
                      <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active INS_Tab_pane_Show" id="Tamheer" role="tabpanel" aria-labelledby="pills-home-tab">
                          <div class="p-4 m-5">
                              <h5 class="INS_letter_set_header pb-2 mb-3">Tamheer program</h5>
                              <p class="INS_letter_set_para mb-3">The program offers on-the-job training programs for Saudi graduates including:
                                  <ul class="INS_li_ul_under  pb-3 mb-3" >
                                      <li>Financial support of SAR 3,000 ($800) per month during the 3 to 6 months of training with occupational hazard insurance.</li>
                                  </ul>
                              </p> 
                              <a class="INS_aLINK_i" href="https://www.hrdf.org.sa/program/%D8%A8%D8%B1%D9%86%D8%A7%D9%85%D8%AC-%D8%AF%D8%B9%D9%85-%D8%A7%D9%84%D8%AA%D9%88%D8%B8%D9%8A%D9%81-%D8%A3%D9%81%D8%B1%D8%A7%D8%AF" target="_blank">KNOW MORE - VISIT WEBSITE</a>
                          </div>
                        </div>
                        <div class="tab-pane fade INS_Tab_pane_Show" id="Program" role="tabpanel" aria-labelledby="pills-profile-tab">
                          <div class="p-4 m-5">
                              <h5 class="INS_letter_set_header pb-3 mb-3">Program to support the growth of Saudization of enterprises</h5> 
                              <a class="INS_aLINK_i" href="https://www.hrdf.org.sa/program/%D8%A8%D8%B1%D9%86%D8%A7%D9%85%D8%AC-%D8%AF%D8%B9%D9%85-%D8%A7%D9%84%D8%AA%D9%88%D8%B8%D9%8A%D9%81-%D8%A3%D9%81%D8%B1%D8%A7%D8%AF" target="_blank">KNOW MORE - VISIT WEBSITE</a>
                          </div> 
                        </div>
                        <div class="tab-pane fade INS_Tab_pane_Show" id="Professional" role="tabpanel" aria-labelledby="pills-contact-tab">
                          <div class="p-4 m-5">
                              <h5 class="INS_letter_set_header">Professional certification support program</h5> 
                              <p class="INS_letter_set_para pb-3 mb-3">The program aims to enable the Saudi workforce to obtain professional certificates accredited in several professional fields required by the labor market (CMMP, CIT, PMP, etc.). Additionally, HRDF provides reimbursement for training costs and test fees.</p>
                              <a class="INS_aLINK_i" href="https://www.hrdf.org.sa/program/%D8%AF%D8%B9%D9%85-%D8%A7%D9%84%D8%B4%D9%87%D8%A7%D8%AF%D8%A7%D8%AA-%D8%A7%D9%84%D9%85%D9%87%D9%86%D9%8A%D8%A9-%D8%A7%D9%84%D8%A7%D8%AD%D8%AA%D8%B1%D8%A7%D9%81%D9%8A%D8%A9" target="_blank">KNOW MORE - VISIT WEBSITE</a>
                          </div> 
                        </div>
                        <div class="tab-pane fade INS_Tab_pane_Show" id="Doroob" role="tabpanel" aria-labelledby="pills-contact-tab">
                          <div class="p-4 m-5">
                              <h5 class="INS_letter_set_header">Doroob Program</h5> 
                              <p class="INS_letter_set_para pb-3 mb-3">Doroob is a comprehensive electronic learning platform enabling Saudi nationals to obtain certifications approved and recognized by the main companies in the Kingdom.</p>
                              <a class="INS_aLINK_i" href="https://doroob.sa/" target="_blank">KNOW MORE - VISIT WEBSITE</a>
                          </div>
                        </div>
                      </div>
                      
                      
                  </div>
                  
                  
              </div>
            </div> 
          </section> --%>
          <!-- ------------EXPORT End-------------- -->
          
          <!-- ------------EXPORT Start-------------- -->
          <%-- <section class="INS_sections_insdetials INS_Financial pb-0" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">EXPORT CREDIT FINANCING, GUARANTEE, AND INSURANCE</h3>				
              </div>
            </div>  
              <!-- <div class="text-center"> 
                  <img src="img/SMEs ECOSYSTEM STIMULUS/SMES ECOSYSTEM STIMULUS.jpg" class="img-fluid INS_financial_imageheight">
              </div> -->
            <div class="container" style="margin-bottom: -160px;position: relative;">
              <div class="INS_smes_imageshadow" >
                  <div class="row justify-content-center align-self-center w-100">  
                      <div class="col-4  my-auto">  
                            <div class="INC-main-item-box text-center p-4">
                                <img src="${commonResourcePath}/images/Incentives-for-Investor/SIDF_logo.jpg" class="img-fluid">
                            </div>  
                            <div class="text-center pt-2 pb-4"> 
                                <a href="https://www.sidf.gov.sa/en/Pages/Home.aspx" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                            </div>
                      </div>
                      <div class="col-4 my-auto">  
                            <div class="INC-main-item-box text-center p-4">
                                <img src="${commonResourcePath}/images/Incentives-for-Investor/itfc_eng_rgb_vertical.jpg" class="img-fluid">
                            </div> 
                            <div class="text-center pt-2 pb-4"> 
                                <a href="https://www.itfc-idb.org/en" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                            </div>  
                      </div> 
                      <div class="col-4  my-auto"> 
                          <div class="INC-main-item-box text-center p-4">
                              <img src="${commonResourcePath}/images/Incentives-for-Investor/saudi exim.jpg" class="img-fluid">
                          </div> 
                          <div class="text-center pt-2 pb-4"> 
                            <a href="https://saudiexim.gov.sa/en/Pages/default.aspx" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                        </div>
                      </div>  
                  </div>
              </div> 
            </div>
              <div class="INS_SEMS_BG_center_export">
                  <div class="INS_center_Credit">
                      <h5 class="INS_SEMS_Credit_header">Credit financing, guarantee, and insurance</h5>
                      <p class="INS_SEMS_Credit_para">Saudi Arabia offers incentives for pre-export financing to Saudi exporters’ buyers and post-shipment financing to international buyers of Saudi goods.</p>
                  </div>
              </div>
          </section> --%>
          <!-- ------------EXPORT End-------------- -->
          
          <!-- ------------ATTRACTIVE Start-------------- -->
          <%-- <section class="INS_sections_insdetials INS_attractive" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">ATTRACTIVE LAND SOLUTIONS </h3>				
              </div>
            </div>  
            <div class="INS_attractive_bg">
              <div class="container">
                  <div class="row pt-4 pb-4 INS_rowcenter">  
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 INS_img_width">
                                  <!-- <img src="${commonResourcePath}/images/Incentives-for-Investor/مدن.jpg" class="img-fluid"> -->
								  <img src="${commonResourcePath}/images/Incentives-for-Investor/modon.jpg" class="img-fluid">
                              </div> 
                              <p class="INS_loanpbm_para pt-4 text-center">Saudi Authority for Industrial Cities and Technology Zones (Modon)*:</p> 
                              <div class="text-center pt-2 pb-4"> 
                                  <a href="https://modon.gov.sa/ar/Pages/default.aspx" class="alink" target="_blank">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div>
                      </div>	
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 INS_img_width">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/fl-02.jpg" class="img-fluid">
                              </div>
                              <p class="INS_loanpbm_para pt-4 text-center">Royal Commission for Jubail and Yanbu*:</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="https://www.rcjy.gov.sa/en-us/jubail/pages/default.aspx" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                              </div> 
                          </div> 
                      </div>	
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 INS_img_width">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Image 85.jpg" class="img-fluid">
                              </div>  
                              <p class="INS_loanpbm_para pt-4 text-center">Economic Cities and Special Zones Authority (ECZA):</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="http://www.ecza.gov.sa/" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div> 
                          
                      </div>			
                  </div>
                  <div class="row pt-4 pb-4 INS_rowcenter">  
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 ">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Image 90.jpg" class="img-fluid">
                              </div> 
                              <p class="INS_loanpbm_para pt-3">King Abdullah Economic City (KAEC):</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="https://www.kaec.net/" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                              </div> 
                          </div> 
                      </div>	
                      <div class="INS_col_loanpbm">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/industrial-clusters-seeklogo.com-01.jpg" class="img-fluid">
                              </div> 
                              <p class="INS_loanpbm_para">National Industrial Development Centre</p>
                               <div class="text-center pt-2 pb-4">
                                  <a href="https://www.ic.gov.sa/en/" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div> 
                          
                      </div>	
                  </div>
              </div> 
            </div>
          </section> --%>
          <!-- ------------ATTRACTIVE End-------------- -->
          
          <!-- ------------FINANCIAL Start-------------- -->
          <%-- <section class="INS_sections_insdetials INS_Financial" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">FINANCIAL INCENTIVES FOR R&amp;D</h3>				
              </div>
            </div>  
            <div class="">
              <div class="container">
                  <div class="row pt-4 pb-4 INS_rowcenter">  
                      <div class="col-md-6 col-xs-12 pt-4">
                          <div class="INS_financial_paddding INS_border_right"> 
                              <h4 class="INS_financial_header">FINANCIAL INCENTIVES FOR R&amp;D</h4>  
                              <div class="text-center pt-2 pb-4">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/kacst.jpg" class="img-fluid INS_financial_imageheight">
                              </div> 
                              <p class="INS_financial_para pt-2">Investment in selected local and international R&amp;D projects with potential to boost the country’s economic growth and self-reliance.</p>
                              <div class="text-center pt-2 pb-4">
                                  <a href="https://www.kacst.edu.sa/" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div> 
                      </div>
                      <div class="col-md-6 col-xs-12 pt-4">
                          <div class="INS_financial_paddding"> 
                              <h4 class="INS_financial_header">MULTI-PURPOSE LOAN</h4>  
                              <div class="text-center pt-2 pb-4">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/SIDF_logo.jpg" class="img-fluid INS_financial_imageheight">
                              </div> 
                              <p class="INS_financial_para pt-2">Finance capital expenditure items not resulting in rated production capacity increases, but potentially improving the borrower’s business or operations.</p>
                              <div class="text-center pt-2 pb-4">
                                  <a href="https://www.sidf.gov.sa/en/Pages/Home.aspx" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                              </div>
                          </div> 
                      </div>
                  </div>
              </div> 
            </div>
          </section> --%>
          <!-- ------------FINANCIAL End-------------- -->
          
          <!-- ------------TAX Start-------------- -->
          <%-- <section class="INS_sections_insdetials INS_Financial" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">TAX CREDIT AND EXEMPTION</h3>				
              </div>
            </div>  
            <div class="">
              <div class="container">
                  <div class="row mt-4 pt-4 pb-4 INS_rowcenter INS_tax_bg_linear">  
                      <div class=" col-md-4 pull-left INS_tax_pos_image">
                          <div class="INS_tax_imageshadow">
                              <div class="text-center pt-2 pb-4 INS_margin_hed">
                                  <img src="${commonResourcePath}/images/Incentives-for-Investor/Tax_credit.png" class="img-fluid INS_financial_imageheight">
                              </div>
                          </div>
                      </div>
                      <div class="col-md-8 pull-right INS_padding_tax_word">
                          <h6 class="INS_tax_headeron text-left">Tax Credit on Saudi National Payroll and Training Cost</h6>
                          <h6 class="INS_tax_headeron_para text-left pt-2">NIDC offers up to a 50% tax credit on Saudi national worker’s payroll and training costs for 10 years.</h6>
                          <div class="text-left pt-4 pb-2">
                              <a href="https://www.ic.gov.sa/en/" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                          </div>
                      </div>
                  </div>
              </div> 
            </div>
          </section> --%>
          <!-- ------------TAX End-------------- -->
          
          <!-- ------------SMEs Start-------------- -->
          <%-- <section class="INS_sections_insdetials INS_Financial pb-0" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">SMEs ECOSYSTEM STIMULUS</h3>				
              </div>
            </div>  
              <!-- <div class="text-center"> 
                  <img src="img/SMEs ECOSYSTEM STIMULUS/SMES ECOSYSTEM STIMULUS.jpg" class="img-fluid INS_financial_imageheight">
              </div> -->
            <div class="container" style="margin-bottom: -160px;position: relative;">
              <div class="INS_smes_imageshadow" >
                  <div class="row">  
                      <div class="col-md-4 pull-left INS_padd_Smes_20"> 
                          <div class="text-center p-4">
                              <img src="${commonResourcePath}/images/Incentives-for-Investor/monshaat_logo.png" class="img-fluid">
                          </div> 
                      </div>
                      <div class="col-md-8 pull-right INS_padding_Smes_word">
                          <h6 class="INS_smes_headeron text-left">Indirect lending initiative</h6>
                          <h6 class="INS_smes_headeron_para text-left pt-2">This program is designed to enhance SME lending by offering competitive loans to licensed financing companies that are used in turn to provide financing solutions and loans with competitive advantages for SMEs.</h6>
                          <div class="text-left pt-4 pb-2">
                              <a href="https://monshaat.gov.sa/" target="_blank" class="alink">KNOW MORE - VISIT WEBSITE</a> 
                          </div>
                      </div>
                  </div>
              </div> 
            </div>
              <div class="INS_SEMS_BG_center">
                  
              </div>
          </section> --%>
          <!-- ------------SMEs End-------------- -->

    </jsp:body>
</template:portalpage>