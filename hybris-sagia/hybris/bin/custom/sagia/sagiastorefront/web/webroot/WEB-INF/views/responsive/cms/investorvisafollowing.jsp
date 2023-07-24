<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
        <div class="d2OurSeriviceItem">
           <div class="d2OurSeriviceItemContent d2OurSeriviceItemInner">
              <div class="d2OurSeriviceItemContentWrap">
              ${component.number}
                 ${component.description}
              </div>
           </div>
           <img src="${component.investorVisaApplyTermImage.url}">
                                     </div>
</c:if >
