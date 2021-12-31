<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- <section class='container mb-5 general-info '>
	<div class='umb-grid'>
		<div class='grid-section'>
			<div>
				<div class='container page-contents'>
					<div class='row clearfix'>
						<div class='col-md-12 column'>
							<div>
								<div class="col-md-8 d-flex justify-content-end">
									<a class="about-link-button-padding" el="noopener" href="${fn:escapeXml(aboutMisa.url)}">
										<button class="button btn btn-contact btn-green pl-5 pr-5 m-0 text-uppercase">
											${aboutMisaText}
											<c:choose>
												<c:when test="${currentLanguage.isocode eq 'ar'}">
													<i class="fa fa-long-arrow-left"></i>
												</c:when>
												<c:otherwise>
													<i class="fa fa-long-arrow-right"></i>
												</c:otherwise>
											</c:choose>
										</button>
									</a>
									<a class="about-link-button-padding" rel="noopener" href="${fn:escapeXml(aboutVision.url)}">
										<button class="button btn btn-contact btn-green pl-5 pr-5 m-0 text-uppercase">
											${aboutVisionText}
											<c:choose>
												<c:when test="${currentLanguage.isocode eq 'ar'}">
													<i class="fa fa-long-arrow-left"></i>
												</c:when>
												<c:otherwise>
													<i class="fa fa-long-arrow-right"></i>
												</c:otherwise>
											</c:choose>
										</button>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section> -->