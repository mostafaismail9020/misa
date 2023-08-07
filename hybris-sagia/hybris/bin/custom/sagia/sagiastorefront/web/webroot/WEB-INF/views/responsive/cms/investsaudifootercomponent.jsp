<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${component.visible}">
	<footer class="footer text-center" id="footer">		
		<c:url value="false" var="userLoggedIn"/>
		<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
		    <c:url value="true" var="userLoggedIn"/>
		</sec:authorize>
        
        <section class="footer-menu-links">
            <div class="container my-3">
                <div class="row no-gutters">
                    <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 footer-column-text first-list text-right-ar">
                        <ul class="footer-menu">
                            <c:forEach items="${component.navigationLinks.children}" var="childLevel1" varStatus="childLevel1index">
                                <c:forEach items="${childLevel1.entries}" var="childlink1">
                                    <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li" />
                                </c:forEach>
                            </c:forEach>
                        </ul>
                    </div>

                    <!-- <c:forEach items="${component.navigationNodesList}" var="NavigationNode">
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
                    </c:forEach> -->
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                        <img src="${commonResourcePath}/images/footer_logo.png" alt="" class="footer_logo" loading="lazy"/>
                    </div>                   
                 </div>
             </div>
         </section>
	</footer>
	
    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-right-ar">
                    <ul class="nav-bottom-footer">
                        <c:forEach items="${component.navigationMapPolicy.children}" var="childLevel1" varStatus="childLevel1index">                       
                            <c:forEach items="${childLevel1.entries}" var="childlink1">
                                <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li" class="nav-item" />
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-12">
                	<p class="footer-second-part">
	                	<spring:theme code="footer.copyright.sign"/>
						<c:set var="now" value="<%=new java.util.Date()%>" />
	                	<fmt:formatDate value="${now}" pattern="yyyy" />&nbsp;
	                	<spring:theme code="footer.copyright.text"/>
                	</p>
                </div>
            </div>
        </div>
    </div>
</c:if>
