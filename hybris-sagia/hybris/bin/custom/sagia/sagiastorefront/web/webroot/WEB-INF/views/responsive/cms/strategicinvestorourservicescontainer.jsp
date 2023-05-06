<%@ page trimDirectiveWhitespaces="true" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
				<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
					<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
						<c:if test="${component.visible}">
							<section id="strategyServices" class="strategyServices pt-100">
								<div class="strategicTitle wow zoomIn animated" data-wow-duration="1s">
									<!-- <h5 class="strategicSubTitle">Strategic</h5> -->
									<h1>Our Services</h1>
								</div>
								<!-- Service Mobile view -->
								<div class="serviceMobileView d-lg-none">
									<div id="serviceAccordion">
										<c:forEach var="currentComponent0" items="${strategicourserviceslist}"
											varStatus="loop">
											<div class="card">
												<div class="card-header" id="mobService-card-${loop.index}">
													<button class="btn btn-link ${loop.first  ? '' : 'collapsed' }"
														data-toggle="collapse" data-target="#mobService-${loop.index}"
														aria-expanded="${loop.first  ? 'true' : 'false' }"
														aria-controls="mobService-1">
														<div class="seviceV3ImageWrap">
															<span class="seviceV3BgIcon"><img
																	src="/_ui/responsive/theme-alpha/img/service-round.png"></span>
															<span class="iconImageService"><img
																	src="${currentComponent0.strategicourserviceicon.url}"></span>
														</div>
														<span
															class="seviceV3Title">${currentComponent0.strategicourservicestitle}</span>
													</button>

												</div>

												<div id="mobService-${loop.index}"
													class="${loop.first  ? 'collapse show' : 'collapse' } "
													aria-labelledby="mobService-card-1" data-parent="#serviceAccordion">
													<div class="card-body">
														<div class="serviceTabPaneContent">
															<img class="animated fadeIn"
																src="${currentComponent0.strategicourserviceimage.url}">

															<div class="serviceSectionV3Description animated zoomIn">
																<h4>${currentComponent0.strategicourservicestitle}</h4>
																<p>${currentComponent0.strategicourservicesdescription}
																</p>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/> -->

										</c:forEach>
									</div>
								</div>
								<!-- Service Mobile view -->
								<div class="serviceSectionV3Wrapper serviceLargeScreen d-none d-lg-flex">
									<div class="serviceSectionV3Tabs serviceSectionV3Col">

										<div class="nav nav-pills servicesNavLinkWrap" id="sercive-pills-tab"
											role="tablist">


											<c:forEach var="currentComponent1" items="${strategicourserviceslist}"
												varStatus="loop">
												<a class="nav-link servicesNavLink ${loop.first  ? 'active' : '' } wow zoomIn animated"
													data-wow-duration="0.2s" id="serviceTab-1" data-toggle="pill"
													href="#service-tab-link-${loop.index}" role="tab"
													aria-controls="service-tab-link-1" aria-selected="true">
													<div class="seviceV3ImageWrap">
														<span class="seviceV3BgIcon"><img
																src="/_ui/responsive/theme-alpha/img/service-round.png"></span>
														<span class="iconImageService"><img
																src="${currentComponent1.strategicourserviceicon.url}"></span>
													</div>
													<span
														class="seviceV3Title">${currentComponent1.strategicourservicestitle}</span>

												</a>
											</c:forEach>
											<span class="servicesNavLink"></span>
										</div>
									</div>

									<div class="serviceSectionV3TabsContents serviceSectionV3Col">
										<div class="tab-content" id="v-pills-tabContent">


											<c:forEach var="currentComponent2" items="${strategicourserviceslist}"
												varStatus="loop">
												<div class="tab-pane ${loop.first  ? 'show active' : '' }"
													id="service-tab-link-${loop.index}" role="tabpanel"
													aria-labelledby="serviceTab">
													<div class="serviceTabPaneContent">

														<img class="animated fadeIn"
															src="${currentComponent2.strategicourserviceimage.url}">

														<div class="serviceSectionV3Description animated zoomIn">
															<h4>${currentComponent2.strategicourservicestitle}</h4>
															<p>${currentComponent2.strategicourservicesdescription}</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</section>
						</c:if>