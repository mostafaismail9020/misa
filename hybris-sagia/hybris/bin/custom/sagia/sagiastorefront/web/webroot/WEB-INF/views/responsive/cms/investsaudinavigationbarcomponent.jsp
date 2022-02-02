<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
	#topbar span{
		font-size: 12px !important;
	}
	label.lang-btn{
		margin-bottom: 2px;
	}
	.page-dashboard #dashboard-carousel .owl-nav .owl-prev {
		left: 9% !important;
	}
	.page-dashboard #dashboard-carousel .owl-nav .owl-next {
		right: 8% !important;
	}
</style>

<c:url value="false" var="userLoggedIn"/>
<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
    <c:url value="true" var="userLoggedIn"/>
</sec:authorize>

<c:if test="${component.visible}">
	<c:set var="pageLabel" value="${cmsPage.itemtype eq 'ContentPage' ? cmsPage.label : ''}" />
	<!-- ======= Header ======= -->
	<div id="topbar" class="d-flex align-items-center fixed-top ">
		<div class="container d-flex">
			<div class="social-links">
				<!-- <a href="#" class="search_item"><img src="${commonResourcePath}/images/Search_icon.png" /></a>
				<a href="#" class="Font_enlargment"><span class=" btn-trans"><img src="${commonResourcePath}/images/Aa.png"/></span></a>
				 -->
				 <c:if test="${userLoggedIn eq 'false'}">
					 <span class="language" id="font-decrement" onclick="decreaseFontSize()" dir="ltr">A-</span>
					 <span class="language" id="font-increment" onclick="increaseFontSize()" dir="ltr">A+</span>
				 </c:if>
				<a href="#" class="skype">
					<span class="btn-group btn-group-toggle btn-trans language-toggle" data-toggle="buttons">
						<!-- <label class="btn lang-btn lang-en active"><input type="radio" name="options" id="option1" autocomplete="off" checked >EN</label> -->
						<label class="lang-btn lang-en active mr-3" onclick="LanguageToggle('en')">EN</label>
						<label class="lang-btn" onclick="LanguageToggle('ar')"><img src="${commonResourcePath}/images/ar_text.png"/></label>
					</span>
				</a>
				<c:choose>
					<c:when test="${userLoggedIn}">
					      <a href="${encodedContextPath}/my-sagia/sagia-profile" title="${user.name}"class="login-details sagiaNavigation-user"><img class="pr-3" src="${commonResourcePath}/images/User-icon.svg" /><span>${user.name}</span></a>
	                      <a data-toggle="modal" data-target="#logoutModal" title="<spring:theme code='text.logout'/>" class="login-details sagiaNavigation-logout cursor-pointer"><span>Logout</span></a>
					      <a href="https://eservices.sagia.gov.sa:2443/gensurvey" class="login-details linkedin login-text" target="_blank" rel="nofollow noreferrer noopener"><span><spring:theme code="portal.header.feedback.label"/></span></a>
					      <a href="http://vision2030.gov.sa/ar/" class="Header_vs_logo"><img src="${commonResourcePath}/images/Header_VS_2030.svg" /></a>
					</c:when>
					<c:otherwise>
					      <a href="/en/investsaudi-login" class="linkedin login-text" target="_blank" rel="nofollow noreferrer noopener"><span><spring:theme code="portal.header.login.label"/></span></a>
					      <a href="https://eservices.sagia.gov.sa:2443/gensurvey" class="linkedin login-text" target="_blank" rel="nofollow noreferrer noopener"><span><spring:theme code="portal.header.feedback.label"/></span></a>
					      <a href="http://vision2030.gov.sa/ar/" class="Header_vs_logo"><img src="${commonResourcePath}/images/Header_VS_2030.svg" /></a>
				    </c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<header id="header" class="fixed-top ">
		<div class="container d-flex align-items-center">
			<div class="logo mr-auto">
				<a href="/${language}">
					<img class="hd-static" src="${fn:escapeXml(component.logoImage.url)}" />
					<img class="hd-scroll" src="${fn:escapeXml(component.visionImage.url)}" />
				</a>
			</div>
			<nav class="nav-menu d-none d-lg-block ">
				<ul>
				    <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" title=""><spring:theme code="portal.header.dashboard"/></a>
					<ul class="dropdown-menu dropdown-large ">
					     <div class="row g-3">
										<div class="col-5 menu-img-item">
											<img class="img-fluid w-100" src="${commonResourcePath}/images/B2C/background1.jpg" alt="">
											<p>Text  for MY DASHBOARD Page</p>
										</div>
							            <div class="col-4 sub-items">
							            <li>
							            <c:if test="${hasLicense}">
							            <a class="dropdown-item get_submenus" href="/dashboard"
																	title="My Dashboard"><spring:theme code="portal.header.mydashboard"/></a>
									    </c:if>
									    <c:if test="${!hasLicense}">
									    <a class="dropdown-item get_submenus" href="/dashboard-without-license"
																	title="My Dashboard"><spring:theme code="portal.header.mydashboard"/></a>
										</c:if>
										</li>
							            </div>
						</div>
						</ul>
					</li>
					</sec:authorize>
					<c:forEach items="${component.navigationNode.children}" var="childLevel1" varStatus="childLevel1index">
						<!-- <li class="nav-item dropdown"> -->
						<%-- <c:choose>
						<c:when test="${not empty childLevel1.cmsLink}"> --%>
							<c:set var="childlevel1link" value="${childLevel1.cmsLink}" />
							<li class="nav-item dropdown">
								<c:choose>
									<c:when test="${not empty childlevel1link.url}">
										<a class="nav-link dropdown-toggle" href="${childlevel1link.url}"
											data-toggle="dropdown" title="${childLevel1.title}">${childLevel1.title}</a>
									</c:when>
									<c:otherwise>
										<cms:component component="${childlevel1link}" evaluateRestriction="true" element="span"
											class="nav-link dropdown-toggle" />
									</c:otherwise>
								</c:choose>
								<ul class="dropdown-menu dropdown-large ">
									<div class="row g-3">
										<div class="col-5 menu-img-item">
											<img class="img-fluid w-100" src="${fn:escapeXml(childLevel1.nodeImage.url)}" alt="">
											<p>${childLevel1.nodeDescription}</p>
										</div>
										<c:if test="${not empty childLevel1.children}">
											<div class="col-4 sub-items">
												<c:forEach items="${childLevel1.children}" var="childLevel2" varStatus="childLevel2index">
													<c:set var="childlevel2link" value="${childLevel2.cmsLink}" />
													<c:choose>
														<c:when test="${not empty childlevel2link.url}">
															<li><a class="dropdown-item get_submenus" href="${childlevel2link.url}"
																	title="${childLevel2.title}">${childLevel2.title}</a>
																<c:if test="${not empty childLevel2.links}">
																	<ul class="submenu dropdown-menu get_submenus">
																		<c:forEach items="${childLevel2.links}" var="childlevel3link">
																			<c:choose>
																				<c:when test="${not empty childlevel3link.url}">
																					<li><a class="dropdown-item" href="${childlevel3link.url}">${childlevel3link.linkName}</a></li>
																				</c:when>
																				<c:otherwise>
																					<li><a class="dropdown-item" href="${portal.cmsLinkUrl(childlevel3link)}">${childlevel3link.linkName}</a></li>
																				</c:otherwise>
																			</c:choose>
																		</c:forEach>
																	</ul>
																</c:if>
															</li>
														</c:when>
														<c:otherwise>
														<li>
															<cms:component component="${childlevel2link}" evaluateRestriction="true" element="span" class="dropdown-item get_submenus" />
															<c:if test="${not empty childLevel2.links}">
																<ul class="submenu dropdown-menu get_submenus">
																	<c:forEach items="${childLevel2.links}" var="childlevel3link">
																		<c:choose>
																			<c:when test="${not empty childlevel3link.url}">
																				<li><a class="dropdown-item" href="/${language}${childlevel3link.url}">${childlevel3link.linkName}</a></li>
																			</c:when>
																			<c:otherwise>
																				<li><a class="dropdown-item" href="${portal.cmsLinkUrl(childlevel3link)}">${childlevel3link.linkName}</a></li>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</ul>
															</c:if>
														</li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</div>
									</c:if>
								</div>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</nav>
		</div>
	</header>
</c:if>

<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <form action="" class="js-formInputFileBox">
                <div class="modal-header modal-header_smallPDB">
                    <div class="modal-title"><spring:message code="text.logout.title"/></div>
                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close"> 
                        <svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"></path></svg>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="modal-description modal-description_largeMargin modal-description_smallText">
                        <spring:message code="text.logout.description"/>
                    </div>
                </div>
               <div class="modal-footer modal-footer_spaceBetween">
                    <button type="button" class="btn-ctrl btn-warning noButton btn-outline p-0" data-dismiss="modal"><spring:message code="text.logout.no"/></button>
                    <button type="button" class="btn btn-ctrl btn-bg p-0 yesButton"><spring:message code="text.logout.yes"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${commonResourcePath}/js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$(window).scroll(function() {
			if ($(this).scrollTop() > 10) {
				$('#header').addClass('header-scrolled');
				$('#topbar').addClass('topbar-scrolled');
				$('#login-Navigation').addClass('login-scrolled');
			} else {
				$('#header').removeClass('header-scrolled');
				$('#topbar').removeClass('topbar-scrolled');
				$('#login-Navigation').removeClass('login-scrolled');
			}
		});

		if ($(window).scrollTop() > 10) {
			$('#header').addClass('header-scrolled');
			$('#topbar').addClass('topbar-scrolled');
			$('#login-Navigation').addClass('login-scrolled');
		}
	});
</script>