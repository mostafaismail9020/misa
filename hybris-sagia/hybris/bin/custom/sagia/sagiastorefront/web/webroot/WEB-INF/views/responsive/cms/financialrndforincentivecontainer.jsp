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
                  <div class="row pt-4 pb-4 INS_rowcenter">
                      <c:forEach var="currentComponent" items="${components}" varStatus="status">  
                      <div class="col-md-6 col-xs-12 pt-4">
                          <div class="INS_financial_paddding INS_border_right"> 
                              <h4 class="INS_financial_header">${currentComponent.title}</h4>  
                              <div class="text-center pt-2 pb-4">
                                  <img src="${fn:escapeXml(currentComponent.logo.url)}" class="img-fluid INS_financial_imageheight" loading="lazy">
                              </div> 
                              <p class="INS_financial_para pt-2">${currentComponent.longDescription}</p>
                              <div class="text-center pt-2 pb-4">
                                  <a href="${currentComponent.knowMore.url}" target="_blank" class="alink">${currentComponent.knowMore.linkName}</a> 
                              </div>
                          </div> 
                      </div>
                      </c:forEach>
                  </div>
              </div> 
            </div>
          </section>