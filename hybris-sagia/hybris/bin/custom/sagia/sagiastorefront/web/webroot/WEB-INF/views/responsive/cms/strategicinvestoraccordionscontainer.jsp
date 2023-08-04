<%@ page trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
        <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
          <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

            <c:if test="${component.visible}">
              <section class="stsFaq-Section">
                <div class="faqBg-sec" style="background-image: url('/_ui/responsive/theme-alpha/img/bg-8.jpg');">
                  <div class="container-fluid">
                    <div class="strategicTitle   wow fadeInUp animated" data-wow-duration="1s">
                      <h1>
                        <spring:theme code="portal.sector.strategic.faq.label" />
                      </h1>
                    </div>
                    <div class="row stsFaqbox-sec">
                      <div class="col-md-6">
                        <div class="stsFaq-AccordinSection">
                          <div id="accordion">
                            <c:forEach var="currentComponent1" items="${accordionList}" begin="0" end="3" varStatus="loop">
                              <!-- <cms:component component="${currentComponent}" element="tr" style="text-align: center;" /> -->
                              <div class="stsaccordion-item ">
                                <div class="" id="faqheading-${loop.index}">
                                  <h5 class="mb-0">
                                    <a class="btn-link stsFaqAcc-btn  collapsed" data-toggle="collapse" data-target="#collapse-${loop.index}" aria-expanded="false" aria-controls="collapseTwo">
                                        <span>${currentComponent1.strategicinvestoraccordiontitle}</span>
                                         <div class="faqIcon-ChangeSec">
                                        <i class="fa-solid fa-plus faqPlus-icon"></i>
                                        <i class="fa-solid fa-minus faqMinus-icon"></i>
                                      </div>
                                    </a>
                                  </h5>
                                </div>
                                <div id="collapse-${loop.index}" class="collapse " aria-labelledby="faqheading-${loop.index}" data-parent="#accordion">
                                  <div class="sts-accordion-body">
                                   ${currentComponent1.strategicinvestoraccordiondescription}</div>
                                </div>
                              </div>
                            </c:forEach>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="stsFaq-AccordinSection">
                          <div id="accordion1">
                            <c:forEach var="currentComponent1" items="${accordionList}"  begin="4" varStatus="loop">
                              <div class="stsaccordion-item ">
                                <div class="" id="faqheading-${loop.index}">
                                  <h5 class="mb-0">
                                    <a class="btn-link stsFaqAcc-btn  collapsed" data-toggle="collapse" data-target="#collapse-${loop.index}" aria-expanded="false" aria-controls="collapseTwo">
                                        <span>${currentComponent1.strategicinvestoraccordiontitle}</span>
                                         <div class="faqIcon-ChangeSec">
                                        <i class="fa-solid fa-plus faqPlus-icon"></i>
                                        <i class="fa-solid fa-minus faqMinus-icon"></i>
                                      </div>
                                    </a>
                                  </h5>
                                </div>
                                <div id="collapse-${loop.index}" class="collapse " aria-labelledby="faqheading-${loop.index}" data-parent="#accordion1">
                                  <div class="sts-accordion-body">
                                   ${currentComponent1.strategicinvestoraccordiondescription}</div>
                                </div>
                              </div>
                            </c:forEach>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </section>
            </c:if>