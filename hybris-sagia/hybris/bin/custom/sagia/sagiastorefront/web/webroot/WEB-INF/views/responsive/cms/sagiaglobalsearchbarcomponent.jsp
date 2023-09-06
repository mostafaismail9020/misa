<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="/resourcesList" var="searchUrl" />
<spring:url value="/search/autocomplete/global/{/componentuid}" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid"  value="${component.uid}"/>
</spring:url>
<section id="home-page-global-search">
    <div class="container">
        <div class="row justify-content-center pos-rel">
            <div class="col-md-5">
                <form name="search_form_${fn:escapeXml(component.uid)}" method="get" action="${searchUrl}">
					<spring:theme code="text.home.globalsearch.placeholder" var="searchPlaceholder"/>

					<ycommerce:testId code="header_search_input">
						<input type="text" id="js-site-search-input"
						       data-test="asdfg"
							   class="js-site-search-input home-page-global-search-input" name="q" value=""
							   maxlength="100" placeholder="${searchPlaceholder}"
							   data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "3","waitTimeBeforeRequest" : "500","displayProductImages" : true}'>
						<a class="a-search">
							<img class="img-fluid search-icon" width="20" src="${commonResourcePath}/images/Icon-awesome-search.png" alt=""/>
						</a>
					</ycommerce:testId>
				</form>
            </div>
        </div>
    </div>
</section>
