<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>


<section id="hero" class="d-flex align-items-center">
    
    <img class="home-banner" src="${component.backgroundBannerImage.url}" alt="homepage-banner" loading="lazy">
    <div class="container position-relative" >
      <h2  data-aos="zoom-in" data-aos-easing="ease" data-aos-delay="150">${component.title}</h2>
      <h1  data-aos="fade-up" data-aos-easing="ease" data-aos-delay="300">${component.description}</h1>
      <div class="col-md-5 p-0 d-flex video-popup-btn" data-aos="fade-up" data-aos-easing="ease" data-aos-delay="450">
            <a href="${portal.cmsLinkUrl(component.exploreNowUrl)}" class="btn-primary-fill btn-video">${component.exploreNowUrl.linkName} <span class="btn-icon"><img src="${commonResourcePath}/images/arow_Path.png" /></span></a>
            <a href="#" class="glightbox play-btn mb-4" data-toggle="modal" data-target="#videoModal"></a>
      </div>
      <!-- <a href="#about" class="btn-get-started scrollto">Explore Oppurtunities</a> -->
    </div>

    <a href="#when-number-talk" class="m-scrollButton scrollto">
      <svg width="25px" height="100%" viewBox="0 0 247 390" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="fill-rule:evenodd;clip-rule:evenodd;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:1.5;">
        <path id="wheel" d="M123.359,79.775l0,72.843" style="fill:none;stroke:#fff;stroke-width:10px;"/>
        <path id="mouse" d="M236.717,123.359c0,-62.565 -50.794,-113.359 -113.358,-113.359c-62.565,0 -113.359,50.794 -113.359,113.359l0,143.237c0,62.565 50.794,113.359 113.359,113.359c62.564,0 113.358,-50.794 113.358,-113.359l0,-143.237Z" style="fill:none;stroke:#fff;stroke-width:10px;"/>
      </svg>
    </a>

  </section>

  <div id="videoModal" class="modal fade">
    <button type="button" class="btn-dismiss close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true">x</span>
    </button>
    <c:if test="${not empty videoLink}">
    <div class="modal-dialog modal-dialog-centered video_section">
        <div class="modal-content">
            <div class="modal-body">
              <div class="embed-responsive embed-responsive-16by9">
                <iframe id="cartoonVideo" width="560" height="315" src="${videoLink.url}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                
              </div>
            </div>
        </div>
    </div>
    </c:if>
</div>  


