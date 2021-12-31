<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<section class="News_press" id="News_press">
   <div class="rect">
               <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" id="carousel" data-interval="false">
                       
                 
                       <div class="carousel-inner">
                           
                           
                        <c:forEach items="${videos}" var="videoComponent">
                                   <div class="carousel-item">
                                    <img class="d-block w-100" src="${fn:escapeXml(videoComponent.videosDetailsImage.url)}" alt="${videoComponent.title}">
                                    
                                       <div class="toplist">
                                           <div class="mask flex-center">
                                              <div class="container-fluid">
                                                 <div class="row  align-items-center">
                                                    <div class="container">
                                                      <div class="col-md-6 col-sm-12 p-0">
                                                         <h2 class="News_Press_Releases"><spring:theme code="portal.media.webinar.videos" text="Videos"/> </h2>
                                                      </div>
                                                    </div>
                                                    
                                                    <div class="col-md-5 col-sm-12 content-slider-part">
                                                       <div class="News_press_bgwhite">
                                                          <div class="top_date position-absolute">
                                                          </div>
                                                          <div class="p-5 paddding_align">
                                                             <div class="mt-0">
                                                               <h3 class="highlight_title">${videoComponent.title}</h3>
                                                               <p class="para_title">${videoComponent.shortDescription}</p>
                                                                <a href="#" class="glightbox play-btn trigger-videoModal"  data-url="${fn:escapeXml(videoComponent.embedURL.url)}"></a>
                                                                <input type="hidden" id="" value="${fn:escapeXml(videoComponent.embedURL.url)}">
                                                             </div>
                                                          </div>
                                                       </div>
                                                    </div>
                                                 </div>
                                              </div>
                                           </div>
                                           </div>
                                   </div>
                               </c:forEach>
                       </div>
                       <div class="arrow_set">
                          <ol class="carousel-indicators">
                             <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                             <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                             <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                             </ol>
                       <a class="carousel-control-prev control_arrow" href="#carouselExampleIndicators" role="button" data-slide="prev">
                           <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                           <span class="sr-only">Previous</span>
                         </a>
                         <a class="carousel-control-next control_arrow" href="#carouselExampleIndicators" role="button" data-slide="next">
                           <span class="carousel-control-next-icon" aria-hidden="true"></span>
                           <span class="sr-only">Next</span>
                         </a>
                       </div>
                         
               </div>
   </div>
</section>


<!-- Modal -->
<!-- Modal -->
<div class="modal fade" id="mediaModal" tabindex="-1" role="dialog" aria-labelledby="mediaModalTitle" aria-hidden="true">
   <button type="button" class="btn-dismiss close" data-dismiss="modal" aria-label="Close">
       <span aria-hidden="true">x</span>
     </button>
  <div class="modal-dialog modal-dialog-centered mediaModal_section" role="document">
     <div class="modal-content">
        <div class="modal-body">
               <div class="video-player-container">
                   <div class="embed-responsive embed-responsive-16by9">
                       <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="315" 
                           src="${fn:escapeXml(videoComponent.embedURL.url)}" 
                           frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                       </iframe>
                   </div>
               </div>
        </div>
     </div>
  </div>
</div>
<!-- Modal End -->


