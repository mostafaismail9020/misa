<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section class="figures explore-key-section">
	<div class="container">
		<div class="row reasons">
			<div class="col-lg-12 pt-4 pt-lg-0 content mx-auto aos-init" data-aos="fade-right" data-aos-delay="100">
				<h2 class="where_totitle misa-text-title">${component.carouselTitle}</h2>
			</div>
			<div class="col-lg-12">
				<div class="row">
					<div class="col-md-6 order-lg-1 order-md-1 order-2">
						<div id="accordion-explore" class="contact-accordion">
							<c:forEach var="currentComponent1" items="${ExploreSaudi}" varStatus="loop">
		                        <div class="card">
		                          <div class="card-header" id="heading${loop.index}">
		                            <h5 class="mb-0">
		                              <button class="btn btn-link collapsed" data-toggle="collapse"
		                                data-target="#collapse${loop.index}" aria-expanded="true" aria-controls="collapse${loop.index}">
		                                <span>${loop.index + 1}</span>&nbsp;&nbsp;${currentComponent1.reasonTitle}
		                              </button>
		                            </h5>
		                          </div>
		
		                          <div id="collapse${loop.index}" class="collapse" aria-labelledby="heading${loop.index}"
		                            data-parent="#accordion-explore">
		                            <div class="card-body">
		                              ${currentComponent1.reasonFullDescription}
		                            </div>
		                          </div>
		                        </div>
	                        </c:forEach>
	                      </div>
						<!-- <div id="accordion-explore">
							<c:forEach var="currentComponent1" items="${ExploreSaudi}" varStatus="loop">
								<div class="card">
									<div class="card-header">
										<a class="card-link" data-toggle="collapse" href="#${loop.index}">
											<i class="more-less glyphicon-plus"></i>
											${loop.index + 1}&nbsp;&nbsp;${currentComponent1.reasonTitle}
										</a>
									</div>
									<div id="${loop.index}" class="collapse" data-parent="#accordion">
										<div class="card-body">
											${currentComponent1.reasonFullDescription}
										</div>
									</div>
								</div>
							</c:forEach>
						</div> -->
					</div>
					<div class="col-md-6 order-lg-2 order-md-2 order-1">
						<div class="embed-responsive embed-responsive-16by9">
						  <iframe class="embed-responsive-item" src="${component.videoLink.url}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
						</div>
						<!-- <div id="videoModal" class="modal fade">
						    <button type="button" class="btn-dismiss close" data-dismiss="modal" aria-label="Close">
						      <span aria-hidden="true">x</span>
						    </button>
						    <c:if test="${not empty videoLink}">
						    <div class="modal-dialog modal-dialog-centered video_section">
						        <div class="modal-content">
						            <div class="modal-body">
						              <div class="embed-responsive embed-responsive-16by9">
						                <iframe id="cartoonVideo" width="560" height="315" src="${videoLink.url}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
						                
						              </div>
						            </div>
						        </div>
						    </div>
						    </c:if>
						</div> -->
					</div>
				</div>
				<div class="row explore-keys-btn justify-content-center justify-content-md-between">
					<a href="${portal.cmsLinkUrl(component.carouselExploreAllButton)}" class="btn btn-primary-fill btn-video misa-btn-special">
						${component.carouselExploreAllButton.linkName}
					</a>
				</div>
			</div>
		</div>
	</div>
</section>
                  