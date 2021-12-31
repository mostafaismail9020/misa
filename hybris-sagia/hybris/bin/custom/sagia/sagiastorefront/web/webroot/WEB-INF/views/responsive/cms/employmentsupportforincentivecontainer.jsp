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
            <div class="container">
              <div class="row">
                <div class="col-xs-12 col-md-12 INS_EPM_nav emp-support-program-content">
                      <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                        <c:forEach var="currentComponent" items="${components}" varStatus="status">
                          <li class="nav-item">
                            <a class="nav-link <c:if test="${currentComponent.defaultIncentiveProgram}">active</c:if>" id="pills-${currentComponent.componentTabId}-tab" data-toggle="pill" href="#${currentComponent.componentId}" role="tab" aria-controls="pills-${currentComponent.componentTabId}" aria-selected="true">
                                <div class="INS_EPM_border_set text-center p-3">
                                    <img src="${fn:escapeXml(currentComponent.logo.url)}" class="img-fluid " style="height: 52px;margin: 10px;"> 
                                    <h6 class="INS_EPM_SP_header p-${status.index}">${currentComponent.title}</h6>
                                </div>
                            </a>
                          </li>
                          <li class="mobile_incentives">
                            <div class="tab-content" id="pills-tabContent">
                              <div class="tab-pane fade show <c:if test="${currentComponent.defaultIncentiveProgram}">active </c:if>INS_Tab_pane_Show" id="${currentComponent.componentId}1" role="tabpanel" aria-labelledby="pills-${currentComponent.componentTabId}-tab">
                                <div class="p-4 m-5">
                                    <h5 class="INS_letter_set_header">${currentComponent.title}</h5>
                                    <p class="INS_letter_set_para pb-3 mb-3">${currentComponent.description}</p> 
                                    <a class="INS_aLINK_i" href="${currentComponent.knowMore.url}" target="_blank">${currentComponent.knowMore.linkName}</a>
                                </div>
                              </div>
                            </div>
                          </li>
                        </c:forEach>
                      </ul>
                      <div class="tab-content desktop_incentives incentives-container-tabcontent" id="pills-tabContent">
                        <c:forEach var="currentComponent1" items="${components}" varStatus="status">
                          <div class="tab-pane fade show <c:if test="${currentComponent1.defaultIncentiveProgram}">active </c:if>INS_Tab_pane_Show" id="${currentComponent1.componentId}" role="tabpanel" aria-labelledby="pills-${currentComponent1.componentTabId}-tab">
                            <div class="p-4 m-5">
                                <h5 class="INS_letter_set_header">${currentComponent1.title}</h5>
                                <p class="INS_letter_set_para pb-3 mb-3">${currentComponent1.description}</p> 
                                <a class="INS_aLINK_i" href="${currentComponent1.knowMore.url}" target="_blank">${currentComponent1.knowMore.linkName}</a>
                            </div>
                          </div>
                        </c:forEach>
                      </div>
                  </div>
              </div>
            </div> 
          </section>