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
				<div id="accordion">
					<c:forEach var="currentComponent1" items="${ExploreSaudi}">
						<div class="card">
							<div class="card-header">
								<a class="card-link" data-toggle="collapse" href="#${currentComponent1.getIndex()}">
									${currentComponent1.reasonTitle}
								</a>
							</div>
							<div id="${currentComponent1.getIndex()}" class="collapse" data-parent="#accordion">
								<div class="card-body">
									${currentComponent1.reasonFullDescription}
								</div>
							</div>
						</div>
					</c:forEach>
					<c:forEach var="currentComponent2" items="${ExploreSaudi}">
						<div class="card">
							<div class="card-header">
								<a class="card-link" data-toggle="collapse" href="#${currentComponent1.getIndex()}">
									${currentComponent2.reasonTitle}
								</a>
							</div>
							<div id="${currentComponent1.getIndex()}" class="collapse" data-parent="#accordion">
								<div class="card-body">
									${currentComponent2.reasonFullDescription}
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>
                  