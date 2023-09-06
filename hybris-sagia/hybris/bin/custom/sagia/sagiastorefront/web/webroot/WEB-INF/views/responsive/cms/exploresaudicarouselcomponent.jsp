<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section class="figures explore-key-section">
	<div class="container">
		<div class="row reasons">
			<div class="col-lg-12 pt-4 pt-lg-0 content mx-auto">
				<h2 class="where_totitle misa-text-title">${fn:escapeXml(component.carouselTitle)}</h2>
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
		                                <span>${loop.index + 1}</span>&nbsp;&nbsp;${fn:escapeXml(currentComponent1.reasonTitle)}
		                              </button>
		                            </h5>
		                          </div>
		
		                          <div id="collapse${loop.index}" class="collapse" aria-labelledby="heading${loop.index}"
		                               data-parent="#accordion-explore">
		                            <div class="card-body">
		                            	${fn:escapeXml(currentComponent1.reasonFullDescription)}
		                            </div>
		                          </div>
		                        </div>
	                        </c:forEach>
	                      </div>
					</div>
					<div class="col-md-6 order-lg-2 order-md-2 order-1">
						<div class="embed-responsive embed-responsive-16by9">
						  <iframe class="embed-responsive-item" src="${fn:escapeXml(component.videoLink.url)}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
						</div>
					</div>
				</div>
				<div class="row explore-keys-btn justify-content-center justify-content-md-between">
					<a href="${portal.cmsLinkUrl(component.carouselExploreAllButton)}" class="btn btn-primary-fill btn-video misa-btn-special">
						${fn:escapeXml(component.carouselExploreAllButton.linkName)}
					</a>
				</div>
			</div>
		</div>
	</div>
</section>
                  