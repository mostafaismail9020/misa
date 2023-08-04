<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
   <!--     <div class="d2OurSeriviceItem">
           <div class="d2OurSeriviceItemContent d2OurSeriviceItemInner">
              <div class="d2OurSeriviceItemContentWrap">

                 <h4>${component.strategicprogrambenefitstitle}</h4>
                 ${component.strategicprogrambenefitsdescription}
              </div>
           </div>
           <div class="d2OurSeriviceItemImage d2OurSeriviceItemInner">
              <img src="${component.strategicprogrambenefitsicon.url}">
           </div>
        </div>-->

<div class="col-md-6 col-lg-4 col-xl-3 wow fadeInUp  animated">
   <div class="programBenefitItem">
       <div class="programBenefitItemHover programBenefitItemHover1"></div>
       <div class="programBenefitItemHover programBenefitItemHover2"></div>
       <div class="programBenefitItemHover programBenefitItemHover3"></div>
       <div class="programBenefitItemHover programBenefitItemHover4"></div>
       <div class="programBenefitItemContent">
       <div class="programBenefitItemTop">
           <div class="programBenefitIcon">
               <img src="${component.strategicprogrambenefitsicon.url}">
           </div>
               <h4>${component.strategicprogrambenefitstitle}</h4>
       </div>
       <div class="divLine">
           <div class="programBenefitLine"></div>
       </div>
           <div class="programBenefitItemBtm">
               <p>${component.strategicprogrambenefitsdescription}</p>
           </div>
   </div>
   </div>
</div>
</c:if >
