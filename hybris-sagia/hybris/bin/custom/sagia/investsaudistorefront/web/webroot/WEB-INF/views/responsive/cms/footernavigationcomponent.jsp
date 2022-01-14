
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
	<%-- <c:forEach items="${component.navigationNode.children}" var="childLevel1">
		<c:forEach items="${childLevel1.children}" step="${component.wrapAfter}" varStatus="i">
				<div class="footer__nav--container col-xs-12">
					<c:choose>
						<c:when test="${component.wrapAfter > i.index}">
							<div class="title">${fn:escapeXml(childLevel1.title)}</div>
						</c:when>
						<c:otherwise>
							<div class="empty-title title" style="opacity: 0;">empty title</div>
						</c:otherwise>
					</c:choose>
					<ul class="footer__nav--links">
						<c:forEach items="${childLevel1.children}" var="childLevel2" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
						<c:forEach items="${childLevel2.entries}" var="childlink" >
							<cms:component component="${childlink.item}" evaluateRestriction="true" element="li" class="footer__link"/>
						</c:forEach>
						</c:forEach>
					</ul>
				</div>
		</c:forEach>
	</c:forEach> --%>

	<div class="footer text-center" id="footer">
		<div class="text-center my-login-part"></div>
		<section class="footer-menu-faq">
			<div class="footerContent">
				<div class="container">
					<div class="row">
						<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll">
							<h2 class="text-uppercase display-7">OPPORTUNITIES</h2>
							<p class="text">Explore all the Opportunities raised by yourself and others.</p>
							<p><a class="link text-uppercase px-0" href="${encodedContextPath}/my-account/support-tickets">OPPORTUNITIES &nbsp; &nbsp;
									<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
							</p>
						</div>
						<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
							<h2 class="text-uppercase display-7">PARTICIPATE</h2>
							<p class="text ">Explore all the events that you can participate with us.</p>
							<p><a class="link text-uppercase px-0" href="${encodedContextPath}/events">EVENTS &nbsp; &nbsp;
									<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
							</p>
						</div>
						<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
							<h2 class="text-uppercase display-7">RESOURCES</h2>
							<p class="text">Explore all the resources you need to work with us.</p>
							<p><a class="link text-uppercase px-0" href="${encodedContextPath}/CONTENT-HUB/c/100000">CONTENT HUB &nbsp; &nbsp;
									<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
							</p>
						</div> 
						<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
							<h2 class="text-uppercase display-7">BUSINESS SUPPORT</h2>
							<p class="text">Link to the enquires raised by yourself.</p>
							<p><a class="link text-uppercase px-0" href="${encodedContextPath}/my-account/support-tickets">SUPPORT TICKET &nbsp; &nbsp;
									<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
							</p>
						</div> 
						<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
							<h2 class="text-uppercase display-7">CONTACT US</h2>
							<p class="text">We love to connect with you.</p>
							<p><a class="link text-uppercase px-0" href="${encodedContextPath}/contact">CONTACT US &nbsp; &nbsp;
									<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</section> 

		<section class="footer-menu-links footer__top mt-5 pb-5">
			<div class="container">
				<div class="row no-gutters footer-wrapper">
					<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 mb-5 mb-lg-0 float-left text-left ft-logo">
						<img src="${commonResourcePath}/images/footer_logo.png" alt="" class="footer_logo">
					</div>
					<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 float-left text-left first-list">
						<div class="footer__left links">
							<div class="footer__nav--container">
								<ul class="footer__nav--links">
									<li class="footer__link">
										<a href="https://investsaudi.sa/en/aboutKingdom" title="" target = "_blank">Meet the Kingdom</a>
									</li>
									<li class="footer__link">
										<a href="https://investsaudi.sa/en/aboutSaudi" title="" target = "_blank">Invest in Saudi Arabia</a>
									</li>
									<li class="footer__link">
										<a href="https://investsaudi.sa/en/investor/guide" title="" target = "_blank">Investment Guide</a>
									</li>
									<li class="footer__link">
										<a href="https://investsaudi.sa/en/mediaCenter" title="" target = "_blank">Media Center</a>
									</li>
									<li class="footer__link">
										<a href="https://investsaudi.sa/en/aboutSaudi" title="" target = "_blank">About</a>
									</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 float-left text-left">
						<div class="footer__left links">
							<div class=" ">
								<%-- <c:forEach items="${component.navigationNode.children}" var="childLevel1">
									<c:forEach items="${childLevel1.children}" step="${component.wrapAfter}" varStatus="i">
									   <div class="footer__nav--container col-xs-12">
										   <c:choose>
											   <c:when test="${component.wrapAfter > i.index}">
												   <div class="title">${fn:escapeXml(childLevel1.title)}</div>
											   </c:when>
											   <c:otherwise>
												   <div class="empty-title title" style="opacity: 0;">empty title</div>
											   </c:otherwise>
										   </c:choose>
										   <ul class="footer__nav--links">
											   <c:forEach items="${childLevel1.children}" var="childLevel2" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
													<c:forEach items="${childLevel2.entries}" var="childlink" >
														<cms:component component="${childlink.item}" evaluateRestriction="true" element="li" class="footer__link"/>
													</c:forEach>
											   </c:forEach>
										   </ul>
									   </div>
									</c:forEach>
								</c:forEach> --%>
								<div class="footer__nav--container col-xs-12">
									<ul class="footer__nav--links">
										<li class="footer__link">Sectors and Opportunities</li>
										<li class="footer__link">
											<a href="https://investsaudi.sa/en/sectors-opportunities/chemicals" title="" target = "_blank"><span class="fw-normal">Chemicals</span></a>
										</li>
										<li class="footer__link">
											<a href="https://investsaudi.sa/en/sectors-opportunities/information-technology" title="" target = "_blank"><span class="fw-normal">Information Technology</span></a>
										</li>
										<li class="footer__link">
											<a href="https://investsaudi.sa/en/sectors-opportunities/energy" title="" target = "_blank"><span class="fw-normal">Energy & Water</span></a>
										</li>
										<li class="footer__link">
											<a href="https://investsaudi.sa/en/sectors-opportunities" title="" target = "_blank"><span class="fw-normal">Explore More</span></a>
										</li>
									</ul>
								</div>
					   		</div>
						</div>
					</div>
				
					<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 float-left text-left">
						<div class="footer__left links">
							<div class=" ">
								<div class="footer__nav--container col-xs-12">
									<ul class="footer__nav--links">
										<li class="footer__link">Program & Incentives</li>
										<li class="footer__link">
											<a href="https://investsaudi.sa/en/sectors-opportunities/regionalHQ" title="" target = "_blank"><span class="fw-normal">Invest Saudi</span></a>
										</li>
										<li class="footer__link">
											<a href="https://investsaudi.sa/en/investor/incentives" title="" target = "_blank"><span class="fw-normal">Vision 2030</span></a>
										</li>
										<li class="footer__link">
											<a href="https://misa.gov.sa/en/" title="" target = "_blank"><span class="fw-normal">Ministry of Investments</span></a>
										</li>
										<li class="footer__link">
											<a href="http://vision2030.gov.sa/ar/" title="" target = "_blank"><span class="fw-normal">Incentive & Support</span></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				
					<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12 ft-social-logo">
						<div class="follow-us">Follow Us</div>
						<ul class="social-links clearfix">
							<li>
								<a href=""><img src="/_ui/responsive/common/images/facebook-square.png" alt="facebook" class=""></a>
							</li>
							<li>
								<a href="https://twitter.com/InvestSaudi"><img src="/_ui/responsive/common/images/twitter-square.png" alt="twitter" class=""></a>
							</li>
						 </ul>
					</div>
				</div>
			</div>
		</section>
	</div>

	<div class="copyright py-4 text-center text-white">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-6 copyrightText">
          			<h6 class="footer_bottom_copy">&copy; 2021 Invest Saudi. All rights reserved.</h6>
        		</div>
				<!-- <div class="col-md-6 col-sm-6">
					<ul class="nav-bottom-footer clearfix">
					<li class="yCmsComponent nav-item"><a href="${termsAndConditions}">Brand Policy</a></li>
            		<li class="yCmsComponent nav-item"><a href="${termsAndConditions}">Privacy Policy</a></li>
            		<li class="yCmsComponent nav-item"><a href="${termsAndConditions}">Sitemap</a></li></ul>
				</div> -->
			</div>
		</div>
	</div>
	
  	<button class="float-button">NEW OPPORTUNITY REQUEST</button>
	<div class="popup">
		<footer id="footer" class="footer-popup">
			<section class="footer-menu-faq">
				<div class="footerContent">
					<div class="container">
						<div class="row">
							<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll">
								<h2 class="text-uppercase display-7">OPPORTUNITIES</h2>
								<p class="text">Explore all the Opportunities raised by yourself and others.</p>
								<p><a class="link text-uppercase px-0" href="${encodedContextPath}/my-account/support-tickets">OPPORTUNITIES &nbsp; &nbsp;
										<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
								</p>
							</div>
							<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
								<h2 class="text-uppercase display-7">PARTICIPATE</h2>
								<p class="text ">Explore all the events that you can participate with us.</p>
								<p><a class="link text-uppercase px-0" href="${encodedContextPath}/events">EVENTS &nbsp; &nbsp;
										<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
								</p>
							</div>
							<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
								<h2 class="text-uppercase display-7">RESOURCES</h2>
								<p class="text">Explore all the resources you need to work with us.</p>
								<p><a class="link text-uppercase px-0" href="${encodedContextPath}/CONTENT-HUB/c/100000">CONTENT HUB &nbsp; &nbsp;
										<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
								</p>
							</div> 
							<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
								<h2 class="text-uppercase display-7">BUSINESS SUPPORT</h2>
								<p class="text">Link to the enquires raised by yourself.</p>
								<p><a class="link text-uppercase px-0" href="${encodedContextPath}/my-account/support-tickets">SUPPORT TICKET &nbsp; &nbsp;
										<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
								</p>
							</div> 
							<div class="col-12 col-md-2 col-lg-2 col-xl-3 mx-auto pt-3 pb-3 f-coll col-half-offset">
								<h2 class="text-uppercase display-7">CONTACT US</h2>
								<p class="text">We love to connect with you.</p>
								<p><a class="link text-uppercase px-0" href="${encodedContextPath}/contact">CONTACT US &nbsp; &nbsp;
										<img src="/_ui/responsive/common/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</section>
		</footer>
	</div>
</c:if>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function () {
		var overflow = "hidden";
		var footerHeight
		$(".float-button").click(function () {
			$(".popup").toggleClass("popup-up");
			$(".float-button").toggleClass("float-button-up");
			$(".full-bg").fadeToggle();
			$(this).html($(this).text() == 'NEW OPPORTUNITY REQUEST' ? "<img class='img-floatclose' src='${commonResourcePath}/images/float-close.png'>": 'NEW OPPORTUNITY REQUEST');
			/*
			$("body").css("overflow", overflow);
			$(this).html($(this).text() == 'Invest Now' ? "<img width='40' src='./images/close.png'>" : 'Invest Now');
			$(this).html($(this).text() == "<img width='40' src='./img/close.png'>" ? 'Invest Now' : 'Invest Now');*/
			overflow = (overflow == "hidden") ? "visible" : "hidden";
		})

		$(window).on('scroll', function () {
			footerHeight = $("#footer").offset().top - 800;
			console.log($(this).scrollTop())
			if ($(this).scrollTop() >= footerHeight) {
				$('.float-button').css("opacity", "0");
			}
			else {
				$('.float-button').css("opacity", "1");
			}
		})
	});
</script>
