
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
	

	<div class="">
	    <div class="footer__top mt-5">
	        <div class="container">
				<div class="row footer-wrapper">
					<div class="footer__left logos pl-0">
						<%-- <img src="${commonResourcePath}/images/logo-en.svg" alt="" width="80px">
						<img src="${commonResourcePath}/images/GIA-en.png" alt="" width="80px"> --%>
						<img src="${commonResourcePath}/images/logo_green.png" alt="" >
					</div>
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
									<li class="footer__link">
										Sectors and Opportunities
									</li>
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
					<div class="footer__left links">
						<div class=" "> 
							<div class="footer__nav--container col-xs-12">
								<ul class="footer__nav--links">
									<li class="footer__link">
										Program & Incentives
									</li>
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
					<%--<div class="footer__left contact">
						<p>How can we help you?</p>
							 <div class="contact-panel-wrapper">
							<a class="contact-panel" href="#">
								<div>
									<div class="contact-panel-title">Call Us</div>
									<div class="contact-panel-content">Let us help you with any question you have.</div>
									<div class="contact-panel-content">Local / International</div>
									<div class="contact-panel-title">+966 11 203 5777</div>
									
								</div>
							</a>
							<a class="contact-panel" href="#">
								<div>
									<div class="contact-panel-title">Make an Enquiry</div>
									<div class="contact-panel-content">Do you have a question? Let us help you.</div>
								</div>
							</a> 
							<a class="contact-panel" href="mailto:bsu@investsaudi.sa">
								<div>
									<div class="contact-panel-title">Email Us</div>
									<div class="contact-panel-content">Email us with comments, questions or feedback</div>
								</div>
							</a>
						</div>   
					</div>--%>
					<div class="footer__left social">
						<div class="follow_us_on">Follow us on</div>
						<div class="text-center">
							<!--<a href="https://www.linkedin.com/company/investsaudi/" target="_blank"><span class="icon-linkedin"></span></a>-->
							<a href="#" target="_blank"><span class="icon-linkedin"></span></a>
							<a href="https://twitter.com/InvestSaudi" target="_blank"><span class="icon-twitter"></span></a>
						</div>
					</div>
					<div class="footer__right hidden">
						<c:if test="${false}">
						   <div class="row">
							   <div class="col-xs-6 col-md-6 footer__dropdown">
								   <footer:languageSelector languages="${languages}" currentLanguage="${currentLanguage}" />
							   </div>
							   <div class="col-xs-6 col-md-6 footer__dropdown">
								   <footer:currencySelector currencies="${currencies}" currentCurrency="${currentCurrency}" />
							   </div>
						   </div>
					   </c:if>
					</div>
				</div>
	        </div>
	    </div>
	</div>
	
	<div class="footer__bottom">
	    <div class="footer__copyright">
			<div class="container footer__copyright"> 
				<div class="row">
					<div class="col-lg-12  p-0"></div>
						<!--<span>${fn:escapeXml(notice)}</span>-->
						<div class="col-lg-6 col-6 pull-left">
							<h6><span class="footer_bottom_copy">&copy; 2021 Invest Saudi. All rights reserved.</span></h6>
						</div>
						<%-- <div class="col-lg-6 col-6  pull-right text-right">
						<c:url var="termsAndConditions" value="/termsAndConditions"/>
							<h6 style="float: right;">
								<span class="pr-2 pl-2 footer_bottom_copy"><a href="${termsAndConditions}">Brand Policy</a></span> 
								<span class="pr-2 pl-2 footer_bottom_copy"><a href="${termsAndConditions}">Privacy Policy</a></span> 
								<span class="pr-2 pl-2 footer_bottom_copy"><a href="${termsAndConditions}">Sitemap</a></span>
							</h6>
						</div> --%> 
					</div>
				</div> 
			</div>
	    </div>
	</div>
</c:if>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> -->