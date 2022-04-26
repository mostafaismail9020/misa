<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="INS_sections_insdetials INS_attractive" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4 pb-4">${component.title} </h3>				
              </div>
            </div>  
            <div class="INS_attractive_bg">
              <div class="container">
                  <div class="row pt-4 pb-4 INS_rowcenter">
                  <c:forEach var="currentComponent" items="${components}" varStatus="status">  
                      <div class="INS_col_loanpbm mb-5">
                          <div class="INS_shadow_pad">
                              <div class="text-center pt-2 pb-4 INS_img_width">
                                  <!-- <img src="${commonResourcePath}/images/Incentives-for-Investor/مدن.jpg" class="img-fluid"> -->
								  <img src="${fn:escapeXml(currentComponent.logo.url)}" class="img-fluid" alt="">
                              </div> 
                              <div class="INS_height_150px">
                                <div class="INS_height_texts">
                                    <p class="INS_loanpbm_para pt-4 text-center">${currentComponent.title}</p> 
                                </div>
                            </div>
                              <div class="text-center pt-2 pb-4"> 
                                  <a href="${currentComponent.knowMore.url}" class="alink" target="_blank">${currentComponent.knowMore.linkName}</a> 
                              </div>
                          </div>
                      </div>
                   </c:forEach>	
                  </div>
              </div> 
            </div>
          </section>