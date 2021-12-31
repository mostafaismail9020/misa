<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:if test="${component.visible}">
    <footer class="footer text-center">

        <div class="text-center my-login-part">
            <c:if test="${not empty component.registerUrl.url}">
                <a href="${component.registerUrl.url}" target="_blank" rel="nofollow noreferrer noopener" class="text-center fixed-registration">
                    ${component.registerUrl.linkName}
                </a>
            </c:if>
            <c:if test="${not empty component.loginUrl.url}">
                <a href="${component.loginUrl.url}" target="_blank" rel="nofollow noreferrer noopener" class="text-center account-login">
                    ${component.loginUrl.linkName}
                </a>
            </c:if>
        </div>

        <div class="container">
            <div class="row no-gutters">
                <div class="col-md-1 col-sm-12 col-xs-12 mb-5 mb-lg-0 float-left text-left d-lg-block d-none">
                    <img src="${component.logo1.url}" class="mb-4" alt="${component.logo1.altText}"/>
                    <img src="${component.logo2.url}" class="sagia" alt="${component.logo2.altText}"/>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12 float-left text-left border-right">
                    <ul class="footer-menu">
                        <c:forEach items="${component.navigationLinks.children}" var="childLevel1" varStatus="childLevel1index">
                            <c:forEach items="${childLevel1.entries}" var="childlink1">
                                <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li"
                                               class="font-bold text-left"/>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-5 col-sm-12 col-xs-12 float-left text-left border-right m-lg-0 mt-3 selctors-industries">
                    <div class="container">
                        <ul class="row pl-0 mb-0">
                            <li class="col-lg-12 col-md-12 col-sm-12"><h6 class="font-bold my-2">${component.navigationSector.name}</h6></li>
                            <c:forEach items="${component.navigationSector.children}" var="childLevel1" varStatus="childLevel1index">
                                <c:forEach items="${childLevel1.entries}" var="childlink1">
                                    <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li"
                                                   class="col-lg-6 col-md-6 col-sm-6"/>
                                </c:forEach>
                            </c:forEach>
                        </ul>
                        <div id="divSubscList">
                            <a class="close-list" onclick="closeMailingList()"><i class="fa fa-times" aria-hidden="true"></i></a>
                            <p class="font-bold f-13">Select your preferences</p>
                            <ul class="list-unstyled row m-0 p-0"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12 pl-3 pos-rel">

                    <div class="follow-us">${component.navigationFollowUs.name}</div>
                    <ul class="social-links clearfix">
                        <c:forEach items="${component.navigationFollowUs.children}" var="childLevel1" varStatus="childLevel1index">
                            <c:forEach items="${childLevel1.entries}" var="childlink1">
                                <li><a href="${childlink1.item.url}" target="${childlink1.item.target}">${childlink1.item.linkName}</a></li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-4 d-lg-none d-block btm-img pl-md-0">
                   <a class="nav-link" href="${portal.cmsLinkUrl(component.logoLink1)}"><img src="${component.logo1.url}" class="mb-4" alt="${component.logo1.altText}" /></a>
                </div>
                <div class="col-4 d-lg-none d-block btm-img pl-md-0">
                     <a class="nav-link" href="${portal.cmsLinkUrl(component.logoLink2)}"><img src="${component.logo2.url}" class="sagia2" style="width:121px" alt="${component.logo2.altText}" /></a>
                </div>
                <div class="col-4 d-lg-none d-block btm-img pl-md-0 pl-4">
                    <a class="nav-link" href="${portal.cmsLinkUrl(component.logoLink3)}"><img src="${component.logo3.url}" class="sagia2" alt="${component.logo3.altText}"/></a>
                </div>
            </div>
        </div>
    </footer>
    <div class="copyright py-4 text-center text-white">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 text-left">
                    ${component.copyrightText}
                </div>
                <div class="col-md-6 col-sm-6">
                    <ul class="nav-bottom-footer clearfix">
                        <c:forEach items="${component.navigationMapPolicy.children}" var="childLevel1" varStatus="childLevel1index">
                            <c:forEach items="${childLevel1.entries}" var="childlink1">
                                <cms:component component="${childlink1.item}" evaluateRestriction="true" element="li"
                                               class="nav-item"/>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</c:if>




