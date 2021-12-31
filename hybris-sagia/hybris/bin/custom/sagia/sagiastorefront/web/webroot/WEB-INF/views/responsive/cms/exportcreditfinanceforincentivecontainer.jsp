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
                  <div class="row justify-content-center align-self-center w-100">
                        <c:forEach var="currentComponent" items="${components}" varStatus="status">
                            <div class="col-md-4 col-xs-12 my-auto">  
                                    <div class="INC-main-item-box text-center p-4">
                                        <img src="${fn:escapeXml(currentComponent.logo.url)}" class="img-fluid">
                                    </div>  
                                    <div class="text-center pt-2 pb-4"> 
                                        <a href="${currentComponent.knowMore.url}" class="alink" target="_blank">${currentComponent.knowMore.linkName}</a> 
                                    </div>
                            </div>
                        </c:forEach>  
                  </div>
              </div> 
            </div>
              <div class="INS_SEMS_BG_center_export">
                  <div class="INS_center_Credit container">
                      <h5 class="INS_SEMS_Credit_header">${component.shortDescription}</h5>
                      <p class="INS_SEMS_Credit_para">${component.longDescription}</p>
                  </div>
              </div>
          </section>