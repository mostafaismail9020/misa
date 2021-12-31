<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 

                    <section class="figures explore-key-section">
                        <div class="container">
                            <div class="row reasons">
                                <div class="col-lg-12 p-0">
                                    <div class="titleArea">
                                        <h2 class="where_totitle aos-init" data-aos="fade-right" data-aos-delay="100">${component.carouselTitle}</h2>
                                        <div id="headingOne">
                                            <a href="${portal.cmsLinkUrl(component.carouselExploreAllButton)}" class="get-explore-btn rounded-pill">
							                 ${component.carouselExploreAllButton.linkName}
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right pt-1" viewBox="0 0 16 16">
                                                   <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
                                            </svg>
							                </a>
                                        </div> 
                                    </div>
                                    <div class="tab-content pt-4 dsp-mob-none" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="resons-home1" role="tabpanel" aria-labelledby="pills-home-tab1">
                                            <div class="flex-container">
                                                <c:forEach var="currentComponent1" items="${ExploreSaudi}" varStatus="status">
                                                    <c:set var="loopCount" value="${status}" />
                                                    <c:if test="${currentComponent1.getIndex() <= component.getCapacity()}">
                                                        <div class="flex-slide" style="background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0)), url(${currentComponent1.reasonbackgroundImage.url});background-size: cover;background-position: center center;">
                                                            <img class="img-fluid" src="${commonResourcePath}/images/Explore/exp_1.png" alt="" />

                                                            <div class="flex-title">
                                                                <img src="${fn:escapeXml(currentComponent1.reasonImage.url)}" class="img-fluid"><br>
                                                                <h4>${currentComponent1.reasonTitle} </h4><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="66.437" height="54.958" viewBox="0 0 66.437 54.958">
                                                                    <defs>
                                                                      <filter id="Path" x="0" y="0" width="66.437" height="54.958" filterUnits="userSpaceOnUse">
                                                                        <feOffset dy="3" input="SourceAlpha"/>
                                                                        <feGaussianBlur stdDeviation="3" result="blur"/>
                                                                        <feFlood flood-color="#001f24"/>
                                                                        <feComposite operator="in" in2="blur"/>
                                                                        <feComposite in="SourceGraphic"/>
                                                                      </filter>
                                                                    </defs>
                                                                    <!-- <g transform="matrix(1, 0, 0, 1, 0, 0)" filter="url(#Path)">
                                                                      <path id="Path-2" data-name="Path" d="M36.958,18.527l-2.667,2.667L20.384,7.335v41.1h-3.81V7.335L2.667,21.194,0,18.527,18.479,0Z" transform="translate(57.44 6) rotate(90)" fill="#22d5ef"/>
                                                                    </g> -->
                                                                  </svg>

                                                            </div>
                                                            <div class="flex-about">
                                                                <p>${currentComponent1.reasonFullDescription}</p>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="resons-profile1" role="tabpanel" aria-labelledby="pills-profile-tab1">
                                            <div class="flex-container">
                                                <c:forEach var="currentComponent2" items="${ExploreSaudi}" varStatus="status">
                                                    <c:if test="${currentComponent2.getIndex() > component.getCapacity()}">
                                                        <div class="flex-slide" style="background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0)), url(${currentComponent2.reasonbackgroundImage.url});background-size: cover;background-position: center center;">
                                                            <div class="flex-title">
                                                                <img src="${fn:escapeXml(currentComponent2.reasonImage.url)}" class="img-fluid"><br> ${currentComponent2.reasonTitle}
                                                            </div>
                                                            <div class="flex-about">
                                                                <p>${currentComponent2.reasonFullDescription}</p>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-content pt-4 dsp-none" id="pills-tabContent-mob">
                                        <div class="swiper swiper-container-invest" id="resons-home1">
                                            <div class="swiper-wrapper">
                                                <c:forEach var="currentComponent1" items="${ExploreSaudi}" varStatus="status">
                                                    <c:set var="loopCount" value="${status}" />
                                                        <div class="swiper-slide" style="background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0)), url(${currentComponent1.reasonbackgroundImage.url});background-size: cover;background-position: center center;">
                                                             <div class="swiper-about">
                                                                <p>${currentComponent1.reasonFullDescription}</p>
                                                            </div>
                                                            <div class="swiper-title">
                                                                <img src="${fn:escapeXml(currentComponent1.reasonImage.url)}" class="img-fluid"><br>
                                                                <h4>${currentComponent1.reasonTitle} </h4>
                                                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="66.437" height="54.958" viewBox="0 0 66.437 54.958">
                                                                    <defs>
                                                                        <filter id="Path" x="0" y="0" width="66.437" height="54.958" filterUnits="userSpaceOnUse">
                                                                        <feOffset dy="3" input="SourceAlpha"/>
                                                                        <feGaussianBlur stdDeviation="3" result="blur"/>
                                                                        <feFlood flood-color="#001f24"/>
                                                                        <feComposite operator="in" in2="blur"/>
                                                                        <feComposite in="SourceGraphic"/>
                                                                        </filter>
                                                                    </defs>
                                                                </svg>
                                                            </div>
                                                        </div>    
                                                </c:forEach>
                                            </div>
                                            <div class="swiper-pagination"></div>
                                        </div>
                                </div>
                                <div class="arrow-top pt-3">
                                    <div class="reasons">
                                        <div class="mb-2 pb-2">
                                            <ul class="nav nav-pills mb-3 nav-tabs pagination pg-blue justify-content-end border-0 mn_10" id="pills-tab" role="tablist">
                                                <li class="nav-item  nav_margin page-item">
                                                    <a class="active page-link border-0" id="pills-profile-tab1" data-toggle="pill" href="#resons-home1" role="tab" aria-controls="resons-profile1" aria-selected="false">
                                                        <span id="left" onclick="myFunctionleftside()">
                                                            <img src="${commonResourcePath}/images/arrow-right.png" class="img-responsive" id="firstimg"> 
                                                            <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive leftsideshows showss" id="secondimg">
                                                        </span>
                                                    </a>
                                                </li>
                                                <li class="page-item"><a class="page-link border-0" id="numberss">01-02</a></li>
                                                <li class="nav-item nav_margin page-item">
                                                    <a class="page-link border-0" id="pills-home-tab1" data-toggle="pill" href="#resons-profile1" role="tab" aria-controls="resons-home1" aria-selected="true">
                                                        <span id="right" onclick="myFunctionrightside()"><img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive"></span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>


                  