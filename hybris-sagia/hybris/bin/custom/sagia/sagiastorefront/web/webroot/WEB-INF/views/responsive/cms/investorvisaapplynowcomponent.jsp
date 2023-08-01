<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
   <section class="investorVisaApply invVisa-ptb " id="investorVisaApplySec">
      <div class="runningAnimation">
        <img src="${component.planeAnimation.url}">
      </div>

        <div class="container-fluid investorVisaApplyRel">
          <div class="row">
            <div class="col-md-6">
              <div class="investorVisaApplyBtnWrap">
                 <div class="investorVisaApplyBtn animated zoomIn wow" data-wow-delay="1s" data-wow-duration="1s">
                  <span class="buttonbg"></span>
          <a  href="https://visa.mofa.gov.sa/" target="_blank" ><spring:theme code="portal.investor.visa.apply.for.visa.now.label"/></a>
        </div>
              </div>
            </div>
             <div class="col-md-6">
                <div class="investorVisaVideoSec animated slideInRight wow" data-wow-delay="0.5s" data-wow-duration="2s">

            <div class="investorVisaVideoLoad" >
                   <div class="investorVisaVideo" style="<c:if test="${currentLanguage.isocode eq 'en'}">display:none</c:if>" >
                      <video style="object-fit: cover; background-size: cover; width: 100%; height: 100%; "  loop="" muted="" preload="auto" playsinline="" autoplay="" poster="${fn:escapeXml(component.defaultImageAr.url)}">
                          <source src="${fn:escapeXml(component.insideVideoAr.url)}" type="${fn:escapeXml(component.insideVideoAr.mime)}">
                          </video>
                    </div>
                  <div class="investorVisaVideo" style="<c:if test="${currentLanguage.isocode eq 'ar'}">display:none</c:if>">
                  <video style="object-fit: cover; background-size: cover; width: 100%; height: 100%; "  loop="" muted="" preload="auto" playsinline="" autoplay="" poster="${fn:escapeXml(component.defaultImageEn.url)}">
                      <source src="${fn:escapeXml(component.insideVideoEn.url)}" type="${fn:escapeXml(component.insideVideoEn.mime)}">
                      </video>
                </div>
            </div>

        </div>

            </div>
          </div>
        </div>
      </section>
</c:if >
