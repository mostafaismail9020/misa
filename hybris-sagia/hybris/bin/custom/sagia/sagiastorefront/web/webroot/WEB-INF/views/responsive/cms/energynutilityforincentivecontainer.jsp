<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="INS_sections_insdetials INS_energynutil" id="">
            <div class="container pb-4">
              <div class="row">
                  <h3 class="INS_header_golden INS_center_wm0 pt-4">${component.title}</h3>				
                  <p class="INS_header_para INS_center_wm0 pb-4">${component.subTitle}</p>
              </div>
            </div>  
            <div class="INS_EnergynUtil_bg">
              <div class="container"> 
                  <div class="row pt-4 pb-4 INS_rowcenter">
                      <c:forEach var="currentComponent" items="${components}" varStatus="status">  
                      <a href="${currentComponent.logoUrl.url}" target="_blank">
                        <div class="col mb-4">
                            <div class="INS_shadow_pad">
                                <div class="text-center pt-2 pb-4">
                                    <img src="${fn:escapeXml(currentComponent.logo.url)}" class="img-fluid">
                                </div> 
                            </div>
                        </div>
                      </a>
                      </c:forEach>
                  </div>
              </div> 
            </div>
          </section>