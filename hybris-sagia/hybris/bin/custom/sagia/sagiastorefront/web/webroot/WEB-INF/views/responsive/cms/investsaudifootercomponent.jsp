<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:if test="${component.visible}">
	<footer class="footer text-center" id="footer">
    	<div class="text-center my-login-part">
        	<%--  <c:if test="${not empty component.registerUrl.url}">
            	    <a href="${component.registerUrl.url}" target="_blank" rel="nofollow noreferrer noopener" class="text-center fixed-registration">
                	    ${component.registerUrl.linkName}
                	</a>
            </c:if> 
            <c:if test="${not empty component.loginUrl.url}">
               	<!-- <a href="${component.loginUrl.url}" target="_blank" rel="nofollow noreferrer noopener" class="text-center account-login">
                    ${component.loginUrl.linkName}
                </a>  -->
            	<!-- <a href="/sagiastorefront/login?site=sagia" target="_blank" rel="nofollow noreferrer noopener" class="text-center account-login">
                    ${component.loginUrl.linkName}
                </a> -->
             </c:if> --%>                               
		</div>
		
		<c:url value="false" var="userLoggedIn"/>
		<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
		    <c:url value="true" var="userLoggedIn"/>
		</sec:authorize>
		
        <section class="footer-menu-faq">
            <div class="footerContent">
                <div class="container">
                    <div class="row">
                        <div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-1 f-coll">
                            <h2 class="text-uppercase display-7"><spring:theme code="portal.footer.invest.saudi.label"/></h2>
                            <p class="text"><spring:theme code="portal.footer.invest.saudi.text"/></p>
                            <p><a class="link text-uppercase px-0" href="/${language}/sectors-opportunities/opportunities"><spring:theme code="portal.footer.explore.opportunities.label"/> &nbsp; &nbsp;
						    		<img class="transform-180-degree p-0" src="${commonResourcePath}/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
                            </p>
                        </div>
                        <div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-1 f-coll">
                            <h2 class="text-uppercase display-7"><spring:theme code="portal.footer.regional.hq.label"/></h2>
                            <p class="text "><spring:theme code="portal.footer.regional.hq.text"/></p>
                            <p><a class="link text-uppercase px-0" href="/${language}/sectors-opportunities/regionalHQ"><spring:theme code="portal.footer.start.now.label"/> &nbsp; &nbsp;
                            		<img class="transform-180-degree p-0" src="${commonResourcePath}/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
                            </p>
                        </div>
                        <div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-1 f-coll">
                            <h2 class="text-uppercase display-7"><spring:theme code="portal.footer.business.support.label"/></h2>
                            <p class="text"><spring:theme code="portal.footer.business.support.text"/></p>
                            <p><a class="link text-uppercase px-0" href="/${language}/investor/guide"><spring:theme code="portal.footer.investment.guide.label"/> &nbsp; &nbsp;
                            		<img class="transform-180-degree p-0" src="${commonResourcePath}/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
                            </p>
                        </div>
                        <div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-1 f-coll">
                            <h2 class="text-uppercase display-7"><spring:theme code="portal.footer.apply.license.label"/></h2>
                            <p class="text"><spring:theme code="portal.footer.journey.start.text"/></p>
                            <ul class="list-unstyled list-inline footer-column-text">
                            	<c:if test="${userLoggedIn eq 'false'}">
                                	<li class="list-inline-item"><a class="text-uppercase px-0" href="/en/investsaudi-login" role="button"><spring:theme code="portal.footer.login.label"/></a></li>
                                	<li class="list-inline-item"><a class="text-uppercase" href="/en/login#register-quick" role="button"><spring:theme code="portal.footer.register.label"/></a></li>
                                </c:if>
                                <li class="list-inline-item border-0"><a class="text-uppercase" href="/${language}/contactUs" role="button"><spring:theme code="portal.footer.contact.us.label"/></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <section class="footer-menu-links">
            <div class="container my-3">
                <div class="row no-gutters">
                    <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 mb-5 mb-lg-0 footer-column-text ft-logo">
                        <img src="${commonResourcePath}/images/footer_logo.png" alt="" class="footer_logo" loading="lazy"/>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 footer-column-text first-list text-right-ar">
                        <ul class="footer-menu">
                            <c:forEach items="${component.navigationLinks.children}" var="childLevel1" varStatus="childLevel1index">
                                <c:forEach items="${childLevel1.entries}" var="childlink1">
                                    <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li" />
                                </c:forEach>
                            </c:forEach>
                        </ul>
                    </div>

                    <c:forEach items="${component.navigationNodesList}" var="NavigationNode">
                        <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 footer-column-text text-right-ar">
                            <ul class="footer-menu">
                                <li><p>${NavigationNode.title}</p></li>
                                <c:forEach items="${NavigationNode.children}" var="childLevel1" varStatus="childLevel1index">
                                    <c:forEach items="${childLevel1.entries}" var="childlink1">
                                        <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li" />
                                    </c:forEach>
                                </c:forEach>
                            </ul>
                            <div id="divSubscList">
                                <a class="close-list" onclick="closeMailingList()"><i class="fa fa-times" aria-hidden="true"></i></a>
                                <p class="font-bold f-13">Select your preferences</p>
                                <ul class="list-unstyled row m-0 p-0"></ul>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="col-lg-1 col-md-1 col-sm-12 col-xs-12 ft-social-logo">
                        <div class="follow-us">${component.navigationFollowUs.title}</div>
                        <ul class="social-links clearfix">
                            <%-- <li>
                                <a href=""><img src="${commonResourcePath}/images/facebook-square.png" alt="facebook" class="" /></a>
                            </li> --%>
                            <li>
                                <a href="https://twitter.com/InvestSaudi"><img src="${commonResourcePath}/images/twitter-square.png" alt="twitter" class="" loading="lazy"/></a>
                            </li>                                                
                         </ul>
                     </div>                     
                 </div>
             </div>
         </section>
	</footer>
	
    <div class="copyright py-2 text-center text-white">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 copyrightText  d-flex d-sm-block">${component.copyrightText}</div>
                <div class="col-md-6 col-sm-6  d-flex d-sm-block">
                    <ul class="nav-bottom-footer clearfix">
                        <c:forEach items="${component.navigationMapPolicy.children}" var="childLevel1" varStatus="childLevel1index">                       
                            <c:forEach items="${childLevel1.entries}" var="childlink1">
                                <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li" class="nav-item" />
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    
	<!-- Floating button -->
   	<button class="float-button"><spring:theme code="portal.footer.contact.us.label"/></button>
   	<div class="popup">
		<footer id="footer" class="footer-popup">
			<section class="footer-menu-faq">
               	<div class="footerContent">
                   	<div class="container">
                       	<div class="row">
                           	<div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-3 f-coll">
                            	<h2 class="text-uppercase display-7"><spring:theme code="portal.footer.invest.saudi.label"/></h2>
                               	<p class="text"><spring:theme code="portal.footer.invest.saudi.text"/></p>
                               	<p><a class="link text-uppercase px-0" href="/${language}/sectors-opportunities/opportunities"><spring:theme code="portal.footer.explore.opportunities.label"/> &nbsp; &nbsp;
               							<img class="transform-180-degree" src="${commonResourcePath}/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
                               	</p>
                           </div>
                           <div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-3 f-coll">
                               	<h2 class="text-uppercase display-7"><spring:theme code="portal.footer.regional.hq.label"/></h2>
                               	<p class="text "><spring:theme code="portal.footer.regional.hq.text"/></p>
                               	<p><a class="link text-uppercase px-0" href="/${language}/sectors-opportunities/regionalHQ"><spring:theme code="portal.footer.start.now.label"/> &nbsp; &nbsp;
                               			<img class="transform-180-degree" src="${commonResourcePath}/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
                               	</p>
                           </div>
                           <div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-3 f-coll">
                               	<h2 class="text-uppercase display-7"><spring:theme code="portal.footer.business.support.label"/></h2>
                               	<p class="text"><spring:theme code="portal.footer.business.support.text"/></p>
                               	<p><a class="link text-uppercase px-0" href="/${language}/investor/guide"><spring:theme code="portal.footer.investment.guide.label"/> &nbsp; &nbsp;
                               			<img class="transform-180-degree" src="${commonResourcePath}/images/arrow-round-forward.png" width="16" height="11" alt=""></a>
                               	</p>
                           </div>
                           <div class="col-12 col-md-3 col-lg-3 col-xl-3 mx-auto pt-3 pb-3 f-coll">
                               	<h2 class="text-uppercase display-7"><spring:theme code="portal.footer.apply.license.label"/></h2>
                               	<p class="text"><spring:theme code="portal.footer.journey.start.text"/></p>
                               	<ul class="list-unstyled list-inline footer-column-text">
                                   	<c:if test="${userLoggedIn eq 'false'}">
                                   		<li class="list-inline-item"><a class="text-uppercase px-0" href="/en/investsaudi-login" role="button"><spring:theme code="portal.footer.login.label"/></a></li>
                                   		<li class="list-inline-item"><a class="text-uppercase" href="/en/login#register-quick" role="button"><spring:theme code="portal.footer.register.label"/></a></li>
                                   	</c:if>
                                   	<li class="list-inline-item border-0"><a class="text-uppercase" href="/${language}/contactUs" role="button"><spring:theme code="portal.footer.contact.us.label"/></a></li>
                               	</ul>
                           	</div>
                       	</div>
                   	</div>
               	</div>
           	</section>
       	</footer>
	</div>
</c:if>
