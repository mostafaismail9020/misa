<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

                    <section id="successstories" class="successstory-container">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="section-title text-center" data-aos="fade-right" data-aos-delay="100">
                                        <h2>${component.carouselTitle} </h2>

                                    </div>
                                    <a href="${component.exploreAllUrl}" class="btn-primary explore-btn explore-gia-btn">
                                          ${component.exploreAllText}&nbsp;
                                            <img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="container-fluid success-main-content pl-0">
                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" data-wrap="false" data-interval="false">
                                <div class="carousel-inner">
                                    <c:forEach var="currentComponent" items="${Videos}" varStatus="status">
                                        <div class="carousel-item item-01 item <c:if test="${currentComponent.defaultVideo}">active</c:if>">
                                            <div class="row sucess_section">
                                                <div class="col-md-5 p-md-0 sucess_img_bg">
                                                    <div class="quote-wrapper">
                                                        <div class="sucess_img">
                                                            <c:if test="${not empty currentComponent.videoLink}">
                                                                <div class="video-player-container">
                                                                    <div class="embed-responsive embed-responsive-16by9">
                                                                        <iframe width="560" height="315" src="${currentComponent.videoLink.url}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-7">
                                                    <div class="sucess_content">
                                                        <div class="sucess_content_itenlogo position-relative"><img src="${fn:escapeXml(currentComponent.companyLogo.url)}" alt="" /></div>
                                                        <p>${currentComponent.description}</p>
														<c:if test="${not empty currentComponent.localizedStats}">
                                   							 <c:forEach var="entry" items="${currentComponent.localizedStats}">
                                       						 <p>
                                           				 <span class="para-title"><c:out value="${entry.key}" /> :</span>
                                            					<c:out value="${entry.value}" />
                                       						 </p>
                                    						</c:forEach>
                                						</c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="sucess-slider-contols">
                                    <a class="prev-01" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                        <!--<img src="${fn:escapeXml(component.carouselArrowRight.url)}" class="img-responsive" id="firstimg">-->
                                        <span id="successstories_left" onclick="mysuccessstoriesleftside()">
                                            <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive leftsideshows opacity_gray_color sssss" id="successstories_firstimg">
                                            <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive leftsideshows" id="successstories_secondimg" style="display: none;">
                                        </span>
                                    </a>
                                    <!--<span class="sucess-slider-count" id="numberss">${component.carouselSliderText}</span>-->
                                    <span class="num-01 ml-3 mr-3 numbers_golden_set"></span>
                                    <a class="next-01" href="#carouselExampleIndicators" role="button" data-slide="next" >
                                        <!--<img src="${fn:escapeXml(component.carouselArrowLeft.url)}" class="img-responsive">
                                        <span class="sr-only">Next</span>-->
                                        <span id="successstories_right"  class="successstories_right" onclick="mysuccessstoriesrightside()">
                                            <img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive" id="ss_right_arrow">
                                        </span>
                                    </a>
                                </div> 
  
                            </div>
                        </div>
                    </section>