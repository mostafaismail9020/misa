<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="INS_sections_insdetials INS_Financial" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">${component.title}</h3>				
              </div>
            </div>  
            <div class="">
              <div class="container">
                  <div class="row mt-4 pt-4 pb-4 INS_rowcenter INS_tax_bg_linear">  
                      <div class=" col-md-4 pull-left INS_tax_pos_image">
                          <div class="INS_tax_imageshadow">
                              <div class="text-center pt-2 pb-4 INS_margin_hed">
                                  <img src="${fn:escapeXml(component.logo.url)}" class="img-fluid INS_financial_imageheight">
                              </div>
                          </div>
                      </div>
                      <div class="col-md-8 pull-right INS_padding_tax_word">
                          <h6 class="INS_tax_headeron text-left">${component.shortDescription}</h6>
                          <h6 class="INS_tax_headeron_para text-left pt-2">${component.longDescription}</h6>
                          <div class="text-left pt-4 pb-2">
                              <a href="${component.knowMore.url}" target="_blank" class="alink">${component.knowMore.linkName}</a> 
                          </div>
                      </div>
                  </div>
              </div> 
            </div>
          </section>