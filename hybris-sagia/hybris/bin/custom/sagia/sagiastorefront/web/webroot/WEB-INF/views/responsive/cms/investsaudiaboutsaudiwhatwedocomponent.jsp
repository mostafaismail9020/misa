<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="panel d-flex" data-aos="fade-left">
     <img src="${fn:escapeXml(imageIcon.url)}"
          data-norm="${fn:escapeXml(imageIcon.url)}"
          data-alt="${fn:escapeXml(imageIcon.url)}" alt="" loading="lazy"/>
     <p>${text}</p>
     <img class="img-fluid numbers" src="${fn:escapeXml(numberImage.url)}"
          data-norm="${fn:escapeXml(numberImage.url)}"
          data-alt="${fn:escapeXml(numberImage.url)}" alt="" loading="lazy" />
     <!--  <img src="${commonResourcePath}/images/About-us/number1.png" alt="" class="img-fluid numbers">-->
</div>
