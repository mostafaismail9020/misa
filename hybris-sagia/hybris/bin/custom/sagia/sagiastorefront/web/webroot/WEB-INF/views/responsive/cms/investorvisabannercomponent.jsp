<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <!-- <div class="banner-container" data-aos="fade-up">
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
          </video> -->







          <section class="investorBanner invVisa-ptb ">
<div class="abtOverImg " >
   <div class="shape-1 wow animated fadeInDown" id="p-shape1">
                <svg xmlns="http://www.w3.org/2000/svg" width="944px" height="894px">
                    <defs>
                        <linearGradient id="PSgrad_0" x1="88.295%" x2="0%" y1="0%" y2="46.947%">
                            <stop offset="0%" stop-color="rgb(0,111,149)" stop-opacity="1"></stop>
                            <stop offset="100%" stop-color="rgb(144,221,134)" stop-opacity="1"></stop>
                        </linearGradient>

                    </defs>
                    <path fill-rule="evenodd" fill="rgb(43, 142, 254)" d="M39.612,410.76 L467.344,29.823 C516.51,-13.476 590.638,-9.94 633.939,39.612 L914.192,317.344 C957.492,366.50 953.109,440.638 904.402,483.939 L476.671,864.191 C427.964,907.492 353.376,903.109 310.76,854.402 L29.823,576.670 C-13.477,527.963 -9.94,453.376 39.612,410.76 Z"></path>
                    <path fill="url(#PSgrad_0)" d="M39.612,410.76 L467.344,29.823 C516.51,-13.476 590.638,-9.94 633.939,39.612 L914.192,317.344 C957.492,366.50 953.109,440.638 904.402,483.939 L476.671,864.191 C427.964,907.492 353.376,903.109 310.76,854.402 L29.823,576.670 C-13.477,527.963 -9.94,453.376 39.612,410.76 Z"></path>
                </svg>
            </div>

            <div class="shape-2 wow animated fadeInDown" id="p-shape2">
                <svg xmlns="http://www.w3.org/2000/svg" width="726.5px" height="726.5px">
                    <path fill-rule="evenodd" stroke="rgb(255, 255, 255)" stroke-width="1px" stroke-linecap="butt" stroke-linejoin="miter" opacity="0.302" fill="none" d="M28.14,285.269 L325.37,21.216 C358.860,-8.852 410.655,-5.808 440.723,28.14 L704.777,325.37 C734.846,358.860 731.802,410.654 697.979,440.723 L400.956,704.777 C367.133,734.845 315.338,731.802 285.269,697.979 L21.216,400.955 C-8.852,367.132 -5.808,315.337 28.14,285.269 Z"></path>
                </svg>
            </div>
            <div class="shape-3 wow animated fadeInUp" id="p-shape3">
                <svg xmlns="http://www.w3.org/2000/svg" width="515px" height="515px">
                    <defs>
                        <linearGradient id="PSgrad_0" x1="0%" x2="89.879%" y1="0%" y2="43.837%">
                            <stop offset="0%" stop-color="rgb(67,186,255)" stop-opacity="1"></stop>
                            <stop offset="100%" stop-color="rgb(113,65,177)" stop-opacity="1"></stop>
                        </linearGradient>

                    </defs>
                    <path fill-rule="evenodd" fill="rgb(43, 142, 254)" d="M19.529,202.281 L230.531,14.699 C254.559,-6.660 291.353,-4.498 312.714,19.529 L500.295,230.531 C521.656,254.559 519.493,291.353 495.466,312.714 L284.463,500.295 C260.436,521.656 223.641,519.493 202.281,495.466 L14.699,284.463 C-6.660,260.435 -4.498,223.641 19.529,202.281 Z"></path>
                    <path fill="url(#PSgrad_0)" d="M19.529,202.281 L230.531,14.699 C254.559,-6.660 291.353,-4.498 312.714,19.529 L500.295,230.531 C521.656,254.559 519.493,291.353 495.466,312.714 L284.463,500.295 C260.436,521.656 223.641,519.493 202.281,495.466 L14.699,284.463 C-6.660,260.435 -4.498,223.641 19.529,202.281 Z"></path>
                </svg>
            </div>
            <div class="shape-4 wow animated fadeInRight" id="p-shape4">
                <svg xmlns="http://www.w3.org/2000/svg" width="972.5px" height="970.5px">
                    <path fill-rule="evenodd" stroke="rgb(255, 255, 255)" stroke-width="1px" stroke-linecap="butt" stroke-linejoin="miter" fill="none" d="M38.245,381.103 L435.258,28.158 C480.467,-12.32 549.697,-7.964 589.888,37.244 L942.832,434.257 C983.23,479.466 978.955,548.697 933.746,588.888 L536.733,941.832 C491.524,982.23 422.293,977.955 382.103,932.746 L29.158,535.733 C-11.32,490.524 -6.963,421.293 38.245,381.103 Z"></path>
                </svg>
            </div>

<video class="bannerVideo" style="object-fit: cover; background-size: cover; width: 100%; height: 100%; "  loop="" muted="" preload="auto" 
poster="${fn:escapeXml(component.image.url)}" playsinline="" autoplay="" >
    <source  src="${fn:escapeXml(component.bannerVideo.url)}" type="${fn:escapeXml(component.bannerVideo.mime)}">
  </video>
  </div>
<div class="container-fluid">
  <div class="row">
    <div class="col-lg-5">
      
    </div>
    <div class="col-lg-12">
    <div class="investorBannerContent ">
        <h2 style="position:relative;" id="invVisaBannerContent" class="wow animated  fadeInUp" data-wow-delay="0.2s" data-wow-duration="1.5s"><span class="bannerLgTitle">${descriptionText}</h2>
        
        <c:if test="${not empty descriptionText2}">
            <div class="descriptionBanner">
            <p class="wow animated  fadeInDown " data-wow-delay="0.2s" data-wow-duration="2s">${descriptionText2}</p>
        </div>
           
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

      <a href="#" class="visaBannerLoadMore wow fadeInUp animated" data-wow-delay="0.5s" data-wow-duration="1.5s"><span class=""><spring:theme code="portal.investor.visa.learn.more.label"/></span></a>
    </div>
    </div>
  </div>
</div>


</section>

























</c:if>




