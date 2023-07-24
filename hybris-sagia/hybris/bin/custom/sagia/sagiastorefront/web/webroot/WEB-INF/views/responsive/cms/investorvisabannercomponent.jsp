<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:if test="${component.visible}">
    <div class="banner-container" data-aos="fade-up">
        <h1>${descriptionText}</h1>
        <c:if test="${not empty descriptionText2}">
            <h2>${descriptionText2}</h2>
            <br/>
        </c:if>
        <c:if test="${not empty buttonURL}">
            <a href="${fn:escapeXml(buttonURL.url)}" class="enquiry-btn">${buttonURL.linkName} <svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
             <path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                                          </svg></a>
        </c:if>
        <c:if test="${not empty buttonText}">
            <a class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" href="${fn:escapeXml(buttonURL.url)}" target="${fn:escapeXml(link)}">${buttonText}</a>
        </c:if>
      </div>
      <video class="int-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
            <source src="${fn:escapeXml(component.bannerVideo.url)}" type="${fn:escapeXml(component.bannerVideo.mime)}">
          </video>
</c:if>




