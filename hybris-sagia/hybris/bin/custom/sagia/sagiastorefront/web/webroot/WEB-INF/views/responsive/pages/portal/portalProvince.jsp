<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
            <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
                        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
                        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



                            <template:portalpage pageTitle="${pageTitle}">



                                <jsp:body>
								<script>
    document.querySelector("html").style.cssText = 'background:#000!important';
    document.querySelector("body").style.cssText += 'opacity:0!important;display:block!important;';
    window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
    window['_sfw_script'] = 'sf_ab.min.js?v=1.0.4';
    window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
    (function(s,e,ss,i,o,n){
    if(s.console && s.console.log) { s.console.log(i);};
    o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
    n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
    })(window,document,'script','SessionForward Loaded.');
    </script>

								
                                    <header:portalPageTitle/>
                                    <cms:pageSlot position="PortalPageTop" var="slotComponent">
                                        <cms:component component="${slotComponent}" />
                                    </cms:pageSlot>
                                    <main>
                                        <div class="banner-section" style="background-image: url(${provinceDetails.bannerImage.url});">
                                            <div class="banner-container aos-init aos-animate" data-aos="fade-up">
                                                <h1>${provinceDetails.bannerHeader}</h1>
                                                <h2>${provinceDetails.bannerText}</h2>
                                            </div>
                                        </div>
                                        
                                        
                                        		<c:if test="${not empty provinceDetails.videoLink}">
	        <section class="Inc-sector-sucessStories">
	        	<div class="container">
                    <div class="row text-center">
                        <h1 class="w-100 title service-title">
                            <span class="clr_gld">${fn:toUpperCase(provinceDetails.aboutRegionHeader)}</span><br/>
						</h1>
					</div>
				</div>
				<div class="slider-carousel successstory-container">
					<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item">
								<div class="success_story_component container-fluid success-main-content pl-0">
									<div class="row sucess_section">
										<div class="col-md-5 p-md-0 sucess_img_bg aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
											<div class="quote-wrapper">
												<div class="sucess_img ">						                    
													<c:if test="${not empty provinceDetails.videoLink}">
														<div class="video-player-container">
															<div class="embed-responsive embed-responsive-16by9">
																<iframe width="560" height="315" src="${provinceDetails.videoLink.url}" 
																		title="YouTube video player" frameborder="0" 
																		allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
																		allowfullscreen loading="lazy"></iframe>
															</div>
														</div>
													</c:if>
												</div>
											</div>
										</div>
										<div class="col-md-7" data-aos="fade-up" data-aos-delay="600">
											<div class="sucess_content">
												<c:if test="${not empty provinceDetails.aboutRegionText}">
													<p>${provinceDetails.aboutRegionText}</p>
												</c:if>
											</div>
										</div>
									</div>
								</div>		
							</div>	
						</div>
					</div>
				</div> 
			</section>
		</c:if>
                                        
                                        
                                        
                                        

                                        <div class="province-container mt-5 pt-5">
                                            <div class="row h-100 m-0 strategic-sector-sWrapper">
                                                <div class="col-lg-6 pl-0 map-area links map-bg-area">
                                                    <img class="img-fluid mg-background map-bg-area-img" src="${strategicDetails.backgroundImage.url}" alt="" loading="lazy"/>
                                                </div>
                                                <div class="col-lg-6 content-area pl-5">
                                                    <p class="pt-5 mt-5 pr-5">${strategicDetails.longDescription}</p>
                                                    <c:if test="${not empty strategicDetails.localizedStats}">
                                                        <div class="area">
                                                            <c:forEach var="entry" items="${strategicDetails.localizedStats}" varStatus="loop">
                                                            <c:if test="${!loop.last}">
                                                                <div class="sec-area">
                                                                    <%-- <strong>${entry.key}</strong>
									                                <strong>${entry.value}</strong> --%>
                                                                        <strong>${entry.key}</strong>
                                                                        <p>${entry.value}</p>
                                                                </div>
                                                                </c:if>
                                                                <c:if test="${loop.last}">
                                                                <div class="sec-area border-0 pl-5">
                                                                    <%-- <strong>${entry.key}</strong>
									                                <strong>${entry.value}</strong> --%>
                                                                        <strong>${entry.key}</strong>
                                                                        <p>${entry.value}</p>
                                                                </div>
                                                                </c:if>

                                                            </c:forEach>
                                                        </div>
                                                    </c:if>
                                                    <div class="sub-sectors">
                                                        <h6>${strategicDetails.sectorHeader}</h6>
                                                        <ul>
                                                            <c:forEach var="sector" items="${sectors}">

                                                                <li>
                                                                    <img class="img-fluid" src="${sector.imageIcon.url}" alt="" loading="lazy"/>
                                                                    <strong>${sector.headerText}</strong>
                                                                </li>


                                                            </c:forEach>
                                                        </ul>
                                                    </div>

                                                </div>
                                            </div>
                                            

                                            <div class="facts-container">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12 title-area">
                                                            <h1 class="title-heading">${provinceDetails.keyFactsHeader}</h1>
                                                        </div>
                                                    </div>
                                                    <div class="key-fact-section pt-5 mt-5">
                                                        <c:forEach var="keyFact" items="${keyFacts}">
                                                            <div class="macro_economic_component">
                                                                <div class="key-fact-box aos-init" data-aos="fade-up" data-aos-delay="0">
                                                                    <div class="macro_economic_icon box-img">
                                                                        <c:if test="${not empty keyFact.imageIcon}">
                                                                            <img class="js-responsive-image achievement_header_icon" src="${keyFact.imageIcon.url}" alt="${keyFact.imageIcon.altText}" title="${keyFact.imageIcon.altText}" style="" loading="lazy">
                                                                        </c:if>
                                                                    </div>

                                                                    <c:if test="${not empty keyFact.headerText}">
                                                                        <h3 class="count">${keyFact.headerText}</h3>
                                                                    </c:if>

                                                                    <c:if test="${not empty keyFact.text}">
                                                                        <p class="desc">${keyFact.text}</p>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="key-strength-container">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12 title-area  pt-5 mt-5">
                                                            <h1 class="title-heading">${provinceDetails.keyStrengthsHeader}</h1>
                                                        </div>
                                                    </div>
                                                    <div class="key-strength-section pt-5 mt-5">
                                                        <c:forEach var="keyStrength" items="${keyStrengths}">
                                                            <div class="key-strength-box">
                                                                <img class="img-fluid" src="${keyStrength.image.url}" alt="${keyStrength.image.altText}" title="${keyStrength.image.altText}" loading="lazy"/>
                                                                <c:if test="${not empty keyStrength.text}">
                                                                    <div class="overlay-txt">${keyStrength.text}</div>
                                                                </c:if>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="investment-op-container mt-5 pt-5">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12 title-area mt-5 pt-5">
                                                            <h1 class="title-heading mb-5 pb-5">${provinceDetails.opportunitiesHeader}</h1>
                                                            <div class="investment-opportunities">
                                                                <c:forEach var="opportunity" items="${investmentOpportunities}">
                                                                    <div class="macro_economic_component">
                                                                        <div class="panel-box-block d-flex aos-init" data-aos="fade-up" data-aos-delay="0">
                                                                            <div class="macro_economic_icon box-img">
                                                                                <c:if test="${not empty opportunity.iconImage.url}">
                                                                                    <img class="js-responsive-image achievement_header_icon" src="${opportunity.iconImage.url}" alt="${opportunity.iconImage.altText}" title="${opportunity.iconImage.altText}" style="" loading="lazy">
                                                                                </c:if>
                                                                            </div>
                                                                            <div class="box-img-right">
                                                                                <c:if test="${not empty opportunity.headerText}">
                                                                                    <h3 class="count">${opportunity.headerText}</h3>
                                                                                </c:if>

                                                                                <c:if test="${not empty opportunity.text}">
                                                                                    <p class="desc">${opportunity.text}</p>
                                                                                </c:if>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                         </div>   
                                         

<%-- 		<c:if test="${not empty provinceDetails.videoLink}">
	        <section class="Inc-sector-sucessStories">
	        	<div class="container">
                    <div class="row text-center">
                        <h1 class="w-100 title service-title">
                            <span class="clr_gld">${fn:toUpperCase(provinceDetails.aboutRegionHeader)}</span><br/>
						</h1>
					</div>
				</div>
				<div class="slider-carousel successstory-container">
					<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item">
								<div class="success_story_component container-fluid success-main-content pl-0">
									<div class="row sucess_section">
										<div class="col-md-5 p-md-0 sucess_img_bg aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
											<div class="quote-wrapper">
												<div class="sucess_img ">						                    
													<c:if test="${not empty provinceDetails.videoLink}">
														<div class="video-player-container">
															<div class="embed-responsive embed-responsive-16by9">
																<iframe width="560" height="315" src="${provinceDetails.videoLink.url}" 
																		title="YouTube video player" frameborder="0" 
																		allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
																		allowfullscreen loading="lazy"></iframe>
															</div>
														</div>
													</c:if>
												</div>
											</div>
										</div>
										<div class="col-md-7" data-aos="fade-up" data-aos-delay="600">
											<div class="sucess_content">
												<c:if test="${not empty provinceDetails.aboutRegionText}">
													<p>${provinceDetails.aboutRegionText}</p>
												</c:if>
											</div>
										</div>
									</div>
								</div>		
							</div>	
						</div>
					</div>
				</div> 
			</section>
		</c:if> --%>
		
	<c:if test="${not empty regionProductsData.results}">
	<section class="Inc-energyoppertunities">
    <div class="container">
    	<!-- <div class="container"> -->
                  <div class="row text-center">
                      <h1 class="w-100 title service-title">
                          <span class="clr_gld"><spring:theme code="portal.region.media.opportunities" text="OPPORTUNITIES" /></span><br/>
				</h1>
			</div>
		<!-- </div> -->
        <div class="Inc-title-header">
        	
            <%-- <h1 class="Inc-secdetil-enop-header ">
            	<spring:theme code="portal.region.media.opportunities" text="OPPORTUNITIES" />
            	<c:if test="${language eq 'en'}">
            		<span class="clr_gld">${category.name}</span>&nbsp;<spring:theme code="portal.sector.opportunity.label"/>
            	</c:if>
            	<c:if test="${language eq 'ar'}">
            		<span class="clr_gld"><spring:theme code="portal.sector.featured.opportunity.label"/>&nbsp;${category.name}</span>
            	</c:if>
            	
            </h1> --%>
            
            <button class="btn btn-sector-primary responsive-btn-sector">
            	<c:if test="${empty regionProductsData.results}">
            		<a href="/${language}/sectors-opportunities/opportunities">
            		<%-- <a href="/${language}/sectors-opportunities/opportunities">${component.exploreAllURL.linkName}&nbsp; --%>
            		<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
            	</c:if>
            	<c:if test="${not empty regionProductsData.results}">
            		<%-- <a href="/${language}/sectors-opportunities/opportunities" class="btn-primary explore-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
                          	<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                              	<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                            	</svg>
					</a> --%>
					<a href="/${language}/sectors-opportunities/opportunities">Explore All &nbsp;
            		<%-- <a href="${portal.cmsLinkUrl(component.exploreAllURL)}${category.code}">${component.exploreAllURL.linkName}&nbsp; --%>
            		<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
            		
            	</c:if>
            	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/know-more.png" alt="">
            </button>
        </div>
        <c:if test="${empty regionProductsData.results}">
        	<div class="page-main-content mt-0 text-center">
	            <div class="no-opportunities-text"><spring:theme code="portal.sector.opportunity.not.available.label"/></div>
			</div>
        </c:if>
        <c:if test="${not empty regionProductsData.results}">
	        <div class="page-main-content mt-0">
	            <div class="row">                                   
	                 <c:forEach var="featuredOpportunity" items="${regionProductsData.results}" varStatus="status">
	                     <div class="col-lg-4 col-md-6 col-sm-12 my-4">
	                         <a href="${encodedContextPath}${featuredOpportunity.opportunity.url}">
	                             <div class=" Inc-fearured-opp">
	                                 <h2 class="Inc-fearured-opp-headtitle" title="${featuredOpportunity.opportunity.name}">${featuredOpportunity.opportunity.name}</h2>
	                                 <h3 class="Inc-fearured-opp-type">${featuredOpportunity.parentCategory.name}</h3>
	                                 <button class="btn btn-sector-primary mx-auto">
	                                 	<spring:theme code="portal.opportunity.know.more.button"/>
	                                 	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/know-more.png" alt="">
	                                 </button>
	                                 <button class="btn btn-sector-outline mx-auto">
	                                 	<spring:theme code="portal.opportunity.iam.interested.button"/>
	                                 	<img class="img-fluid arrow-icon" src="/sagiastorefront/_ui/responsive/common/images/btn-sector-outline.png" alt="">
	                                 </button>
	                                 <!-- <span class="Inc-sector-icon-mini">
	                                 	<img class="img-fluid" src="${featuredOpportunity.parentCategory.picture.url}" 
	                                 			alt="${featuredOpportunity.parentCategory.picture.altText}" />
	                                 </span> -->
	                             </div>
	                         </a>
	                     </div>
	                 </c:forEach>                                       
	            </div>
	        </div>
        </c:if>
    </div>
</section>
</c:if>
                              
                             <c:if test="${not empty regionNewsData.results}">               
                              <section class="Inc-mediaCenter-sectionwrapper">
				            	<c:url value="/mediaCenter/news" var="newsUrl" />
				                <div class="container">
				                    <div class="row text-center">
				                        <img class="img-fluid title-icon" src="${commonResourcePath}/images/news_icon.png" alt="" loading="lazy"/>
				                        <h1 class="w-100 title service-title">
				                            <spring:theme code="portal.region.media.news" text="NEWS" />
				                            <a href="${newsUrl}" class="btn-primary explore-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
				                            	<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
				                                	<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
				                              	</svg>
											</a>
										</h1>
									</div>
								</div>
				                <div class="container">
				                    <div class="row">                        
				                        <c:forEach items="${regionNewsData.results}" var="newsComponent">
				                            <div class="col-sm-12 col-md-4 col-lg-4 mb-5">
				                                <div class="news-card">
				                                    <div class="news-date text-center">
				                                        <div class="day">
				                                            <fmt:formatDate value="${newsComponent.newsDate}" pattern="d" />
				                                        </div>
				                                        <div class="month">
				                                            <fmt:formatDate value="${newsComponent.newsDate}" pattern="MMMM" />
				                                        </div>
				                                    </div>
				                                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(newsComponent.newsThumbnailImage.url)}" 
				                                    		alt="${newsComponent.newsTitle}" loading="lazy">
				                                    <div class="news-card-inner">
				                                        <h3>${newsComponent.newsTitle}</h3>
				                                        <p>${newsComponent.newsShortInformation}</p>
				                                        <a class="btn btn-primary-fill btn-knowmore" href="${newsUrl}/${newsComponent.uid}"><spring:theme code="portal.media.know.more" text= "Know More"/>&nbsp;
				                                        	<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
				                                        </a>
				                                    </div>
				                                </div>
				                            </div>
				                        </c:forEach>
				                    </div>
				                </div>
				            </section>
				            </c:if>
				            
				<c:if test="${not empty regionEventsData.results}">            
				<section class="Inc-mediaCenter-sectionwrapper">
            	<c:url value="/mediaCenter/events" var="eventsUrl" />
                <div class="container">
                    <div class="row text-center">
                        <img class="img-fluid title-icon" src="${commonResourcePath}/images/events_icon.png" alt="" loading="lazy"/>
                        <h1 class="w-100 title service-title">
                            <spring:theme code="portal.region.media.events" text="EVENTS" />
                            <a href="${eventsUrl}" class="btn-primary explore-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
                            	<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                                	<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                              	</svg>
							</a>
                        </h1>
                    </div>
                </div>
                <div class="container">
                    <div class="row">                        
                        <c:forEach items="${regionEventsData.results}" var="eventComponent">
                            <div class="col-sm-12 col-md-4 mb-5">
                                <div class="news-card">
                                    <div class="news-date text-center">
                                        <div class="day">
                                            <fmt:formatDate value="${eventComponent.eventStartDate}" pattern="d" />
                                        </div>
                                        <div class="month">
                                            <fmt:formatDate value="${eventComponent.eventStartDate}" pattern="MMMM" />
                                        </div>
                                    </div>
                                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(eventComponent.eventThumbnailImage.url)}" 
                                    		alt="${eventComponent.eventName}" loading="lazy">
                                    <div class="news-card-inner">
                                        <h3>${eventComponent.eventName}</h3>
                                        <p>${eventComponent.eventShortInformation}</p>
                                        <a class="btn btn-primary-fill btn-knowmore" href="${eventsUrl}/${eventComponent.uid}"><spring:theme code="portal.media.know.more" text= "Know More"/>&nbsp;
                                        	<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            </c:if>

			<c:if test="${not empty regionResourcesData.results}">
            <section class="Inc-mediaCenter-sectionwrapper pb-0">
            	<c:url value="/mediaCenter/resources" var="resourcesUrl" />
                <div class="container">
                    <div class="row text-center">
                        <img class="img-fluid title-icon" src="${commonResourcePath}/images/resource_icon.png" alt="" loading="lazy"/>
                        <h1 class="w-100 title service-title">
                            <spring:theme code="portal.region.media.resources" text="RESOURCES" />
                            <a href="${resourcesUrl}" class="btn-primary explore-btn"><spring:theme code="portal.media.explore.all" text= "Explore All"/>&nbsp;
                            	<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                                	<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>
                              	</svg>
							</a>
                        </h1>
                    </div>
                </div>
                <div class="container">
                    <div class="row">                       
                        <c:forEach items="${regionResourcesData.results}" var="resourceComponent">
                            <div class="col-sm-12 col-md-4 mb-5">
                                <div class="news-card news-service-card">
                                    <div class="news-date service-date text-center">
                                        <div class="day">
                                            <fmt:formatDate value="${resourceComponent.resourceDate}" pattern="d" />
                                        </div>
                                        <div class="month">
                                            <fmt:formatDate value="${resourceComponent.resourceDate}" pattern="MMMM" />
                                        </div>
                                    </div>
                                    <img class="img-fluid w-100 service-card-img news-card-img" src="${fn:escapeXml(resourceComponent.resourceThumbnailImage.url)}" 
                                    		alt="${resourceComponent.resourceTitle}">
                                    <div class="service-card">
                                        <h3 title="${resourceComponent.resourceTitle}">${resourceComponent.resourceTitle}</h3>
                                        <p>${resourceComponent.resourceShortInformation}</p>
                                        <a class="btn btn-primary-fill btn-knowmore" href="${resourcesUrl}/${resourceComponent.uid}"><spring:theme code="portal.media.know.more" text= "Know More"/>&nbsp;
                                        	<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span> 
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            </c:if>
             <div class="banner-container container aos-init aos-animate" >
                <c:if test="${not empty provinceReport.regionalReport || not empty provinceReport.statisticalReport }">
                    <div class="text-center download_btn">
                        <h1 class="title-heading">${provinceReport.regionReportTitle}</h1>
                         <h2>${provinceReport.regionReportDescription}</h2>
                         <br>
                         <c:if test="${not empty provinceReport.regionalReport}">
                         <c:url value="/mediaCenter/downloadResoruce/${provinceReport.uid}?report=regional" var="resourcedownloadURL"/>
                            <button class=""><a href="${resourcedownloadURL}" target="_blank"><spring:theme code="province.guide.to.uncover.proposition.download.button" text= "Download your copy"/></a></button>
                         </c:if>
                         <c:if test="${not empty provinceReport.statisticalReport }">
                         <c:url value="/mediaCenter/downloadResoruce/${provinceReport.uid}?report=statistical" var="resourcedownloadURL"/>
                            <button class=""><a href="${resourcedownloadURL}" target="_blank"><spring:theme code="province.statistical.report.download.button" text= "Download your copy"/></a></button>
                         </c:if>
                      </div>
                 </c:if>
                <br>
                <br>
             </div>

                                        <%-- <div class="inc-strategic-details row" style="background-image: url(${strategicDetails.backgroundImage.url});">
<p>${strategicDetails.sectorHeader}</p>
<p>${strategicDetails.longDescription}</p>
<c:forEach var="sector" items="${sectors}">
<div class="row"><img class="img-fluid" src="${sector.imageIcon.url}" alt=""/></div>
<h4>${sector.headerText}</h4>
</c:forEach>
</div>

<div class="inc-facts row" >
<p>${provinceDetails.keyFactsHeader}</p>

<c:forEach var="keyFact" items="${keyFacts}">
<div class="row"><img class="img-fluid" src="${keyFact.imageIcon.url}" alt=""/></div>
<h4>${keyFact.headerText}</h4>
<p>${keyFact.text}</p>
</c:forEach>
</div>



<div class="inc-strengths row" >
<p>${provinceDetails.keyStrengthsHeader}</p>
<c:forEach var="keyStrength" items="${keyStrengths}">
<div class="row"><img class="img-fluid" src="${keyStrength.image.url}" alt=""/></div>
<p>${keyStrength.text}</p>
</c:forEach>
</div>

<div class="inc-opportunities row" >
<p>${provinceDetails.opportunitiesHeader}</p>
<c:forEach var="opportunity" items="${investmentOpportunities}">
<div class="row"><img class="img-fluid" src="${opportunity.iconImage.url}" alt=""/></div>
<p>${keyStrength.headerText}</p>
<p>${keyStrength.text}</p>
</c:forEach>
</div> --%>


                                    </main>

                                </jsp:body>

                            </template:portalpage>