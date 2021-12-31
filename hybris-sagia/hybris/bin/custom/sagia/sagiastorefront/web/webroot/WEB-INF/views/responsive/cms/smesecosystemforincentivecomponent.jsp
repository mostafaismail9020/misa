<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="INS_sections_insdetials INS_Financial pb-0" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">${component.title}</h3>				
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
                              <img src="${fn:escapeXml(component.logo.url)}" class="img-fluid">
                          </div> 
                      </div>
                      <div class="col-md-8 pull-right INS_padding_Smes_word">
                          <h6 class="INS_smes_headeron text-left">${component.shortDescription}</h6>
                          <h6 class="INS_smes_headeron_para text-left pt-2">${component.longDescription}</h6>
                          <div class="text-left pt-4 pb-2">
                              <a href="${component.knowMore.url}" target="_blank" class="alink">${component.knowMore.linkName}</a> 
                          </div>
                      </div>
                  </div>
              </div> 
            </div>
              <div class="INS_SEMS_BG_center">
                  
              </div>
          </section>