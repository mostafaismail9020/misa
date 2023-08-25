<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<spring:url value="/search/autocomplete/sagia-search-box-component" var="autocompleteUrl" htmlEscape="false">
		<spring:param name="componentuid"  value="${component.uid}"/>
	</spring:url>
		<header:portalPageTitle/>

        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
        	<cms:pageSlot position="PortalPageMain" var="slotComponent">
                <cms:component component="${slotComponent}"/>
            </cms:pageSlot>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-6 col-md-8">
							<div class="sector-search">
								<form name="search_form_${fn:escapeXml(component.uid)}" class="position-relative" method="get" action="${searchUrl}">
									<spring:theme code="portal.opportunity.searchby.placeholder" var="searchPlaceholder"/>
									<ycommerce:testId code="header_search_input">
										<input type="text" id="js-site-search-input"
											data-test="asdfg"
											class="js-site-search-input" name="q" value="${fn:containsIgnoreCase(request.getParameter("q"), ':') ? '' : request.getParameter("q")}"
											maxlength="100" placeholder="${searchPlaceholder}"
											data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "3","waitTimeBeforeRequest" : "500","displayProductImages" : true}'>
										<a class="a-search">
											<img class="img-fluid search-icon" width="20" src="${commonResourcePath}/images/Icon-awesome-search.png" alt=""/>
										</a>
									</ycommerce:testId>
									<div class="opportunity-card total-results">
										<spring:message code="portal.opportunity.search.opportunities.totalResults" arguments="${searchPageData.pagination.totalNumberOfResults}"/>
									</div>
								</form>
								<div class="dropdown dropright">
									<button class="sort dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										<svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
											<path d="M1.875 11.1875L2.9375 12.25V1.8125C2.9375 1.27083 3.20833 1 3.75 1C3.97917 1 4.16667 1.07292 4.3125 1.21875C4.47917 1.36458 4.5625 1.5625 4.5625 1.8125V12.25L5.59375 11.1875C5.76042 11.0417 5.95833 10.9688 6.1875 10.9688C6.39583 10.9688 6.58333 11.0417 6.75 11.1875C6.89583 11.3542 6.96875 11.5417 6.96875 11.75C6.96875 11.9792 6.89583 12.1771 6.75 12.3438L4.3125 14.75C4.14583 14.9167 3.95833 15 3.75 15C3.54167 15 3.35417 14.9167 3.1875 14.75L0.75 12.3438C0.583333 12.1354 0.5 11.9375 0.5 11.75C0.5 11.5833 0.583333 11.3958 0.75 11.1875C0.895833 11.0417 1.08333 10.9688 1.3125 10.9688C1.54167 10.9688 1.72917 11.0417 1.875 11.1875ZM10.1562 4.875C10.0104 5.04167 9.82292 5.125 9.59375 5.125C9.36458 5.125 9.17708 5.04167 9.03125 4.875C8.86458 4.70833 8.78125 4.52083 8.78125 4.3125C8.78125 4.10417 8.86458 3.91667 9.03125 3.75L11.4688 1.34375C11.6146 1.17708 11.8021 1.09375 12.0312 1.09375C12.2604 1.09375 12.4479 1.17708 12.5938 1.34375L15 3.75C15.1667 3.89583 15.25 4.08333 15.25 4.3125C15.25 4.54167 15.1667 4.72917 15 4.875C14.8542 5.04167 14.6667 5.125 14.4375 5.125C14.2083 5.125 14.0208 5.04167 13.875 4.875L12.8438 3.84375V14.1875C12.8438 14.4167 12.7604 14.6146 12.5938 14.7812C12.4479 14.9271 12.2604 15 12.0312 15C11.7812 15 11.5833 14.9271 11.4375 14.7812C11.2917 14.6146 11.2188 14.4167 11.2188 14.1875V3.84375L10.1562 4.875Z" fill="white"/>
											</svg>											
									</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
									<button class="dropdown-item" type="button">Action</button>
									<button class="dropdown-item" type="button">Another action</button>
									<button class="dropdown-item" type="button">Something else here</button>
									</div>
								</div>
								</div>
							</div>
						</div>
					</div>
      
        	<%-- <section class="Inc-sector-content">
				<div class="container">
					<div class="row">
						<div class="col-md-11">							
							<h4><spring:theme code="portal.sector.landing.text"/></h4>
						</div>
					</div>
				</div>
			</section> --%>

			<div class="container">
				<div class="sectors">
					<c:forEach var="allCategories" items="${mainCategories}">	
						<div class="sector" id="${allCategories.code}">	
							<a href="${encodedContextPath}/sectors-opportunities/${allCategories.code}">
								<div class="icon">
									<img src="${fn:escapeXml(allCategories.logo.url)}" data-norm="${fn:escapeXml(allCategories.logo.url)}"  data-alt="${fn:escapeXml(allCategories.logo.url)}" alt="" loading="lazy"/>
								</div>
								<h2><c:out value="${allCategories.name}"/></h2>
							</a>
						</div>					
					</c:forEach>
				</div>
			</div>

			<%-- <div class="Inc-sector-panel portal-sector">				
			<c:forEach var="allCategories" items="${mainCategories}"> 
				<div class="test-item" id="${allCategories.code}" id="" data-slide-to="" style="background-image: url(${allCategories.banner.url});"></div>
			</c:forEach>
		</div> --%>
    </main>

    <cms:pageSlot position="PortalPageBottom" var="slotComponent">
        <cms:component component="${slotComponent}"/>
    </cms:pageSlot>

    </jsp:body>
</template:portalpage>
