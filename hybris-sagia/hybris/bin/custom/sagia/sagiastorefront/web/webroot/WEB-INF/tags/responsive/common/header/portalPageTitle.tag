<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<!--
<section class="page-title">
    <div class="container main-content-area pb-3">
        <h1 class="heading text-left my-4 clearfix">

            <c:if test="${not empty categoryLogo.url}">
                <figure>
                    <img src="${categoryLogo.url}" alt="${categoryLogo.altText}">
                </figure>
            </c:if>
            <em>${cmsPage.title}</em>
        </h1>

        <div class="d-flex flex-wrap justify-content-between">
            <div class="my-page-name"><breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" /></div>
            <div class="fontresize">
                <a onclick="increaseFontSize(); return false;">A+</a>
                <a onclick="resetFontSize(); return false;">A</a>
                <a onclick="decreaseFontSize(); return false;">A-</a>
            </div>
        </div>
    </div>
</section>
-->
