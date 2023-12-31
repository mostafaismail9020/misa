<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
	<!--
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
	-->

	<div class=background>    
		<div class="no-touch_screen">
			<div class="row container-fluid login-container">
				<div class="row col-lg-5 col-xl-5 col-12 login-account-screen ">
					<div class="login-right-wrapper">
						<div class="login-register-text"><spring:theme code="investor.registration.login.button"/></div>	
						<div class="register-role-selection">
							<img src="" alt="Investor" class="img-fluid login-role" />
						</div>											
						<div class="login-register-role"><spring:theme code="choose.your.role.label"/></div>
						<div class="login-role-selection">
							<div class="login-role-selection-box role-investor">
									<img src="${commonResourcePath}/images/B2C/Investor-icon.png" alt="Investor" class="img-fluid img-ivestor-inactive" />
									<img src="${commonResourcePath}/images/B2C/Investor-icons.png" alt="Investor" class="img-fluid img-ivestor-active d-none" />
									<p class="role-text"><spring:theme code="investor.select.role.label"/></p>
							</div>
							<div class="login-role-selection-box role-partner">
								<img src="${commonResourcePath}/images/B2C/Partner.png" alt="Partner" class="img-fluid img-partner-inactive" />
								<img src="${commonResourcePath}/images/B2C/Partner-icon.png" alt="Investor" class="img-fluid img-partner-active d-none" />
								<p class="role-text"><spring:theme code="partner.select.role.label"/></p>
							</div>
						</div>
						<div class="login-buttons">
							<div class="col-md-6 col-12">
								<button class="login-btn login-cancel"><spring:theme code="investor.registration.cancel.button"/></button>
							</div>
							<div class="col-md-6 col-12">													
								<button class="login-btn login-btn-next" ><spring:theme code="investor.registration.next.button"/>&nbsp;
									<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561" class="next-hide">
										<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward"
											d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
											transform="translate(-7.875 -11.252)" fill="#fff">
										</path>
									</svg>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<script src="./Absher-أبشر_files/jquery.min.js.download"></script>
			<script src="./Absher-أبشر_files/bootstrap.min.js.download"></script>
			<script src="./Absher-أبشر_files/wow.min.js.download"></script>
			<script>
				$(document)
						.ready(
								function() {
									//Check Touch Devices
									if (is_touch_screen = 'ontouchstart' in document.documentElement) {
										$('body').addClass('touch_screen');
									} else {
										$('body').addClass(
												'no-touch_screen');
									}
									//Initialize WOW.js
									new WOW().init();
								});
			</script>
		</div>
	</div>
    </jsp:body>
</template:portalpage>
