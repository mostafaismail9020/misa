<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />


<div class="container">
    <div class="row p-2">
        <div class="col-md-12 col-sm-12 page-main-content">
            <c:choose>
                <c:when test="${not empty searchPageData.results}">
                    <h2 class="newsTitle">Latest News</h2>
                    <div class="col-md-12">
                    <div class="row">
                        <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
                            <tags:report-card result="${result}" loopCount="${status.index}"/>
                        </c:forEach>
                    </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12 col-md-12 mt-4 text-center">
                        <spring:theme code="text.label.notFound"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <section class="Inc-mediaCenter-sectionwrapper pb-0">
        <div class="container">
            <div class="row text-center">
                <h2 class="newsTitle">Latest announcements from the ministry</h2>
            </div>
        </div>
        <div class="container">
            <div class="row announcementsBox">
                <div class="col-md-3">
                    <img class="img-fluid service-card-img resources-card-img"
                         src="${commonResourcePath}/images/default-product-image.png"
                         alt="" loading="lazy">
                </div>
                <div class="col-md-9 containerPadding">
                    <div class="row">
                        <div class="col-md-6 resourceDate">
                            16 Mar 2023
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="resourceTitle">Quarterly report template update</h3>
                            <p class="resourceInfo">Cursus purus, diam, aliquet scelerisque dignissim tellus aenean viverra. In risus elit vel id tincidunt vel. Nunc ac ipsum.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container containerPadding ">
            <div class="row announcementsBox">
                <div class="col-md-3">
                    <img class="img-fluid service-card-img resources-card-img"
                         src="${commonResourcePath}/images/default-product-image.png"
                         alt="" loading="lazy">
                </div>
                <div class="col-md-9 containerPadding">
                    <div class="row">
                        <div class="col-md-6 resourceDate">
                            16 Mar 2023
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="resourceTitle">Quarterly report template update</h3>
                            <p class="resourceInfo">Cursus purus, diam, aliquet scelerisque dignissim tellus aenean viverra. In risus elit vel id tincidunt vel. Nunc ac ipsum.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container  ">
            <div class="row announcementsBox">
                <div class="col-md-3">
                    <img class="img-fluid service-card-img resources-card-img"
                         src="${commonResourcePath}/images/default-product-image.png"
                         alt="" loading="lazy">
                </div>
                <div class="col-md-9 containerPadding">
                    <div class="row">
                        <div class="col-md-6 resourceDate">
                            16 Mar 2023
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="resourceTitle">Quarterly report template update</h3>
                            <p class="resourceInfo">Cursus purus, diam, aliquet scelerisque dignissim tellus aenean viverra. In risus elit vel id tincidunt vel. Nunc ac ipsum.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
        <section class="newsletterSection">
                <div class="container text-center get-updates-section">
                    <div class="row">
                        <div class="col-md-12">
                            <h2 class="get-updates-title">Get the Latest Updates</h2>
                            <button class="btn btn-primary join-newsletter-btn">Join Our Newsletter</button>
                        </div>
                    </div>
                </div>
            </section>
       <section class="Inc-mediaCenter-sectionwrapper pb-3">
           <div class="container">
               <div class="row text-center col-md-12">
                   <h2 class="newsTitle">Media Library </h2>
               </div>
           </div>
           <div class="container">
               <div class="row">
                   <div class="col-md-6">
                       <div class="video-player-container video-inner-div border-0 p-0">
                           <div class="embed-responsive embed-responsive-16by9 video-events" >
                               <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="80%" height="100%"
                                       src="https://www.youtube.com/embed/u3sQ7TDFUWs" frameborder="0"
                                       allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                               </iframe>
                           </div>
                          <h4 class= "videoTitle">Saudi investment ministry signs four investment agreements</h4>
                          <span class="videoDate">25 Oct 2022</span>
                       </div>
                   </div>
                   <div class="col-md-6">
                       <div class="row">
                           <div class="col-md-10 news vedio_outer">
                               <div class="video-player-container video-inner-div border-0 p-0">
                                   <div class="embed-responsive embed-responsive-16by9 video-events">
                                       <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="80%" height="100%"
                                               src="https://www.youtube.com/embed/u3sQ7TDFUWs" frameborder="0"
                                               allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                       </iframe>
                                   </div>
                               </div>
                           </div>
                           <div class="col-md-2 news vedio_outer">
                            <h4 class= "videoTitle">Saudi investment ministry signs four investment agreements</h4>
                            <span class="videoDate">25 Oct 2022</span>
                             </div>
                             <div class="col-md-10 news vedio_outer">
                               <div class="video-player-container video-inner-div border-0 p-0">
                                   <div class="embed-responsive embed-responsive-16by9 video-events">
                                       <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="80%" height="100%"
                                               src="https://www.youtube.com/embed/u3sQ7TDFUWs" frameborder="0"
                                               allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                       </iframe>
                                   </div>
                               </div>
                           </div>
                           <div class="col-md-2 news vedio_outer">
                                                   <h4 class= "videoTitle" >Saudi investment ministry signs four investment agreements</h4>
                                                   <span class="videoDate">25 Oct 2022</span>
                                                    </div>
                       </div>
                   </div>
               </div>
           </div>
       </section>
</div>
