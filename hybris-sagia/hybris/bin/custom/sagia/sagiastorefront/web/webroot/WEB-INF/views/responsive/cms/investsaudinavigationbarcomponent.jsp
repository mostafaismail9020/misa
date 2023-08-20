<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
	@media (min-width: 576px){
		#logoutModal .modal-dialog {
			max-width: 500px;
			margin: 1.75rem auto;
			position: relative;
			width: auto;
			pointer-events: none;
			padding: 0 20px;
		}
		#logoutModal .modal-dialog-centered {
			min-height: calc(100% - (1.75rem * 2));
			display: flex;
			flex-direction: column;
			align-items: center;
			
		}
		
	}
	@media (max-width: 767px){
		.mobile-nav a{
			font-size: 16px;
		}
		.mobile-nav .btn-outline{
			width: 120px;
			height: 50px;
			border-radius: 10px !important;
			padding: 10px ;
			text-align: center;
			border: 1px solid #00a6be;
			margin-right: 5px;
			text-transform: uppercase;
		}
		.mobile-nav .btn-dashboard{
			width: 120px;
			height: 50px;
			border-radius: 10px !important;
			padding: 15px;
			background: #00a6be;
			text-align: center;
			display: flex;
			align-items:center;
			justify-content: center;
		}

		.mobile-nav .sagiaNavigation-user span{
			display: block;
		}
	}

	@media (min-width: 992px){
		#logoutModal .modal-dialog-sm .modal-body, [dir] .modal-dialog-xs .modal-body {
			padding: 0 60px;
		}
		#logoutModal .modal-dialog-sm .modal-footer, [dir] .modal-dialog-xs .modal-footer {
			padding: 0 60px 32px 60px;
		}
	}

	@media screen and (min-width: 768px) and (max-width: 1024px) {
		.sagiaNavigation-user .header-user-name{
			display: block !important;
		}

		.mobile-nav .btn-outline{
			width: 170px;
			height: 60px;
			border-radius: 10px !important;
			padding: 15px ;
			text-align: center;
			border: 1px solid #00a6be;
			margin-right: 10px;
		}
		.mobile-nav .btn-dashboard{
			width: 170px;
			height: 60px;
			border-radius: 10px !important;
			padding: 15px !important;
			background: #00a6be;
			text-align: center;
			display: flex;
			align-items: end;
			justify-content: center;
		}
		.mobile-nav a{
			font-size: 20px;
		}
		.mobile-nav .sagiaNavigation-user span{
			display: block;
		}
	}

	#topbar .social-links a{
		line-height: normal;
	}
	#logoutModal .modal-footer>a, .modal-footer>button,  .modal-footer>div{
		margin-bottom: 0;
	}
	#logoutModal .modal-footer {
		border-top: none;
		margin: 0 -6px;
		flex-wrap: wrap;
		   width: calc(100% + 12px);
	}

	#logoutModal .modal-footer .btn-outline {
		background: #fff!important;
		border: 1px solid #00a6be!important;
		border-radius: 27px;
		margin: 0;
		text-align: center;
		padding: 10px 24px 14px;
		cursor: pointer;
		font-size: var(--content-font);
		font-weight: 600;
	}
	#logoutModal .modal-footer	.btn-ctrl {
		width: 159px!important;
		height: 45px!important;
		line-height: 45px!important;
		border-radius: 27px!important;
		color: #00a6be!important;
	}
	#logoutModal .modal-footer .btn-bg {
		background-color: #00a6be!important;
		color: #fff !important;
	}
	#logoutModal .modal-footer>:not(:last-child) {
		margin-left: 6px;
		margin-right: 6px;
	}
	#logoutModal .modal-footer>:not(:first-child) {
		margin-left: 6px;
		margin-right: 6px;
	}
	#logoutModal .modal-description_largeMargin {
		margin-bottom: 48px;
	}
	#logoutModal .modal-content {
		background: #fff;
		-webkit-box-shadow: 0 18px 33px #00000029;
		box-shadow: 0 18px 33px #00000029;
		border: 1px solid #fff;
		border-radius: 25px;
		outline: 0;
		flex-direction: column;
		width: 100%;
		pointer-events: auto;
		display: flex;
		-webkit-box-orient: vertical;
		-webkit-box-direction: normal;
		position: relative;
	}
	#logoutModal .modal-dialog .modal-header, [dir] .modal-dialog .modal-secondaryContent {
		padding: 40px 60px;
	}
	#logoutModal .modal-dialog-centeredContent {
			text-align: center;
		}
	#logoutModal .modal-header_smallPDB {
		padding-bottom: 16px!important;
	}
	#logoutModal .modal-dialog-centeredContent .modal-header {
		-webkit-box-pack: center;
		-ms-flex-pack: center;
		justify-content: center;
		position: relative;
		border-bottom: none !important;
	}
	#logoutModal .modal-header .modal-title {
		font-size: 45px;
		color: #bf9b2e;
		text-transform: uppercase;
	}
	#logoutModal .js-formInputFileBox .modal-close.bttn_close {
		right: 10px;
		border-radius: 25px!important;
		border: 0 solid #00a6be!important;
		top: 5px;
	}
	
	#logoutModal .modal-close {
		width: 42px;
		height: 42px;
		cursor: pointer;
		background: 0 0;
		position: absolute;
	}
	#logoutModal .modal-close svg {
		margin-top: 6px;
		overflow: hidden;
		fill: #00a6be;
	}
	#logoutModal .modal-close path {
		stroke: #00a6be;
	}
	#logoutModal .modal-footer_spaceBetween {
		justify-content: space-between!important;
	}
	.mobile-nav .btn-dashboard span{
		color: #fff !important;
		font-size: 18px !important;
		font-weight: 500;
		display: flex;
		justify-content: space-evenly;
		align-items: inherit;
	}

	.mobile-nav .btn-outline span{
		font-size: 18px !important;
	}
	.mobile-nav .btn-dashboard svg{
		transform: rotate(90deg);
		margin: 0 5px;
	}
	.mobile-nav .btn-outline span{
		color: #00a6be;
	}
	
	#topbar .social-links .sagiaNavigation-user img{
		margin: 0 10px;
	}
	.mobile-nav .MobileNavUserIcon{
		height: 40px;
		width: 46px;
		background: #ccc;
		margin-right: 20px;
	}
	.MobileNavUser{
		margin: 10px 0 !important;
		border-top: 1px solid #707070;
		border-bottom: 1px solid #707070;
	}
	.header-user-name{
		width: 200px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	@media (min-width: 1200px) and (max-width: 1500px){
		.social-links #logutModal span, .social-links .login-details span {
			font-size: 11px!important;
		}
	}	
	[dir=rtl] #topbar .social-links a{
   		 margin-left: 15px;
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
				 <!-- <a href="#" class="skype d-none d-sm-inline">
					<span class="language" id="font-decrement" onclick="decreaseFontSize()" dir="ltr">A-</span>
					<span class="language" id="font-increment" onclick="increaseFontSize()" dir="ltr">A+</span>
				</a> -->
				 <!-- <c:if test="${userLoggedIn eq 'false'}">
					 <span class="language" id="font-decrement" onclick="decreaseFontSize()" dir="ltr">A-</span>
					 <span class="language" id="font-increment" onclick="increaseFontSize()" dir="ltr">A+</span>
				 </c:if> -->
				<a href="#" class="skype text-decoration-none">
					<span class="btn-group btn-group-toggle btn-trans language-toggle" data-toggle="buttons">
						<!-- <label class="btn lang-btn lang-en active"><input type="radio" name="options" id="option1" autocomplete="off" checked >EN</label> -->
						<label class="lang-btn lang-en active cursor-pointer" onclick="LanguageToggle('en')">EN</label>
						<label class="lang-btn cursor-pointer lang-ar" onclick="LanguageToggle('ar')"><spring:theme code="header.arabic"/></label>
						<!-- <label class="lang-btn cursor-pointer" onclick="LanguageToggle('ar')"><img src="${commonResourcePath}/images/ar_text.png"/></label> -->
					</span>
				</a>
				<c:choose>
					<c:when test="${userLoggedIn}">
						<a href="${encodedContextPath}/my-sagia/sagia-profile" title="${user.name}"class="login-details sagiaNavigation-user d-none d-lg-inline">
					      	<img class="" src="${commonResourcePath}/images/User-icon.svg" /><span class="header-user-name">${user.name}</span>
					    </a>
	                    <a data-toggle="modal" data-target="#logoutModal" title="<spring:theme code='text.logout'/>" class="login-details sagiaNavigation-logout cursor-pointer d-none d-lg-inline">
	                    	<span><spring:theme code="dashboard.logout.label"/></span>
	                    </a>
					    <!-- <a href="https://eservices.sagia.gov.sa:2443/gensurvey" class="login-details linkedin login-text d-none d-lg-inline" target="_blank" rel="nofollow noreferrer noopener ">
					    	<span><spring:theme code="portal.header.feedback.label"/></span>
					    </a> -->
					    <a href="https://dgasurvey.dga.gov.sa/efm/se/5D9518B74A63EA1A" class="login-details linkedin login-text d-none d-lg-inline" target="_blank" rel="nofollow noreferrer noopener ">
					    	<span><spring:theme code="portal.header.feedback.label"/></span>
					    </a>
					    <a href="http://vision2030.gov.sa/ar/" class="Header_vs_logo"><img src="${commonResourcePath}/images/Header_VS_2030.svg" /></a>
					</c:when>
					<c:otherwise>
					    <a href="${encodedContextPath}/login" class="linkedin login-text d-none d-lg-inline" rel="nofollow noreferrer noopener">
					      	<span><spring:theme code="portal.header.login.label"/></span>
						</a>
					    <!-- <a href="https://eservices.sagia.gov.sa:2443/gensurvey" class="linkedin login-text d-none d-lg-inline" target="_blank" rel="nofollow noreferrer noopener">
					    	<span><spring:theme code="portal.header.feedback.label"/></span>
					    </a> -->
					    <a href="https://dgasurvey.dga.gov.sa/efm/se/5D9518B74A63EA1A" class="linkedin login-text d-none d-lg-inline" target="_blank" rel="nofollow noreferrer noopener">
					    	<span><spring:theme code="portal.header.feedback.label"/></span>
					    </a>
					    <a href="http://vision2030.gov.sa/ar/" class="Header_vs_logo"><img src="${commonResourcePath}/images/Header_VS_2030.svg" /></a>
				    </c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<header id="header" class="fixed-top ">
		<div class="container d-flex align-items-center">
			<div class="logo mr-auto">
				<a href="/${language}/">
					<img class="hd-static" src="${fn:escapeXml(component.logoImage.url)}" />
					<img class="hd-scroll" src="${fn:escapeXml(component.visionImage.url)}" />
				</a>
			</div>
			<button type="button" class="mobile-nav-toggle d-lg-none"><i class="icofont-navigation-menu"></i></button>
			<nav class="nav-menu d-none d-lg-block ">
				<div class="d-block d-lg-none">
					<c:choose>
						<c:when test="${userLoggedIn}">
							<div class="MobileNavUser">
								<a href="${encodedContextPath}/my-sagia/sagia-profile"
									title="${user.name}" class="login-details sagiaNavigation-user d-flex">
									<img class="MobileNavUserIcon"
									src="${commonResourcePath}/images/User-icon.svg" />
									<span
										class="header-user-name">${user.name}</span>
								</a>
							</div>
						</c:when>
					</c:choose>
				</div>
				<ul>
				    <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" title=""><spring:theme code="portal.header.dashboard"/></a>
					<ul class="dropdown-menu dropdown-large ">
					     <div class="row g-3">
										<div class="col-5 menu-img-item">
											<img class="img-fluid w-100" src="${commonResourcePath}/images/B2C/background1.jpg" alt="" loading="lazy">
											<p><spring:theme code="portal.header.mydashboardpage"/></p>
										</div>
							            <div class="col-4 sub-items">
							            <li>
							            <c:if test="${hasLicense}">
							            <a class="dropdown-item get_submenus" href="${encodedContextPath}/dashboard"
																	title="My Dashboard"><spring:theme code="portal.header.mydashboard"/></a>
									    </c:if>
									    <c:if test="${!hasLicense}">
									    <a class="dropdown-item get_submenus" href="${encodedContextPath}/dashboard-without-license"
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
											<img class="img-fluid w-100" src="${fn:escapeXml(childLevel1.nodeImage.url)}" alt="" loading="lazy">
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
				<div class="p-3 d-flex  justify-content-center d-lg-none">
					<c:choose>
						<c:when test="${userLoggedIn}">
							<!-- <a href="https://eservices.sagia.gov.sa:2443/gensurvey"
								class="btn-outline login-details linkedin login-text" target="_blank"
								rel="nofollow noreferrer noopener">
								<span>
									<spring:theme code="portal.header.feedback.label" />
								</span>
							</a> -->
							<a href="https://dgasurvey.dga.gov.sa/efm/se/5D9518B74A63EA1A"
								class="btn-outline login-details linkedin login-text" target="_blank"
								rel="nofollow noreferrer noopener">
								<span>
									<spring:theme code="portal.header.feedback.label" />
								</span>
							</a>
							<a data-toggle="modal" data-target="#logoutModal"
								title="<spring:theme code='text.logout'/>"
								class="btn-dashboard text-white login-details sagiaNavigation-logout cursor-pointer mr-3">
								<span>
									<spring:theme code="dashboard.logout.label" />
								</span>
							</a>
						</c:when>
						<c:otherwise>
							<!-- <a href="https://eservices.sagia.gov.sa:2443/gensurvey"
								class="linkedin login-text" target="_blank"
								rel="btn-outline nofollow noreferrer noopener">
								<span>
									<spring:theme code="portal.header.feedback.label" />
								</span>
							</a> -->
							<a href="https://dgasurvey.dga.gov.sa/efm/se/5D9518B74A63EA1A"
								class="linkedin login-text" target="_blank"
								rel="btn-outline nofollow noreferrer noopener">
								<span>
									<spring:theme code="portal.header.feedback.label" />
								</span>
							</a>
							<a href="/en/login" class="btn-dashboard linkedin login-text"
								rel="nofollow noreferrer noopener">
								<span>
									<spring:theme code="portal.header.login.label" />
								</span>
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</nav>
		</div>
		<div id="user-icons" class="user-icons-header p-0 d-none">
			<div class=" user-icon mr-1 mr-md-3">
				<!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/> -->
				<c:if test="${hasLicense or hasAwaitingPayment}">
					<a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
						<img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.svg"/>
					</a>
				</c:if>
			</div>
			<div class=" user-icon mr-1 mr-md-3">
				<!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/> -->
				

				<div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
					<c:if test="${hasLicense or hasAwaitingPayment}">
						<button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
							<span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
							<img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg"/>
						</button>
					</c:if>
					<div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
				</div>
			</div>
			<div class=" user-icon mr-1">
				<a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>" class="sagiaNavigation-btn sagiaNavigation-user"> 
					<img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.svg"/>
				</a>
			</div>
		</div>
	</header>
</c:if>

<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <form action="" class="js-formInputFileBox">
                <div class="modal-header modal-header_smallPDB">
                    <div class="modal-title"><spring:message code="text.logout.title"/></div>
                    <button type="button" class="modal-close bttn_close" data-dismiss="modal" aria-label="Close"> 
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
                    <button type="button" class="btn btn-ctrl btn-bg p-0 m-0 yesButton"><spring:message code="text.logout.yes"/></button>
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