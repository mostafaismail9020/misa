<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
     
        <!-- <div class="d2OurSeriviceItem">
           <div class="d2OurSeriviceItemContent d2OurSeriviceItemInner">
              <div class="d2OurSeriviceItemContentWrap">

                 <h4>${component.strategicinvestoraccordiontitle}</h4>
                 ${component.strategicinvestoraccordiondescription}
              </div>
           </div>
        </div> -->
        <div class="stsaccordion-item ">
         <div class="" id="heading-${loop.index}">
           <h5 class="mb-0">
             <a class="btn-link stsFaqAcc-btn  " data-toggle="collapse" data-target="#collapse1" aria-expanded="false" aria-controls="collapseTwo">
                 <span>${component.strategicinvestoraccordiontitle}</span>
                  <div class="faqIcon-ChangeSec">
                 <i class="fa-solid fa-plus faqPlus-icon"></i>
                 <i class="fa-solid fa-minus faqMinus-icon"></i>
               </div>
             </a>
           </h5>
         </div>
         <div id="collapse1" class="collapse " aria-labelledby="heading-${loop.index}" data-parent="#accordion">
           <div class="sts-accordion-body">
            ${component.strategicinvestoraccordiondescription}</div>
         </div>
       </div>
</c:if >
