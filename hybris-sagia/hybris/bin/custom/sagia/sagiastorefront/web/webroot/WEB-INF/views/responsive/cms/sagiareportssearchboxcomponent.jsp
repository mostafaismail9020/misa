<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="searchUrl" value="${pageContext.request.request.getAttribute('javax.servlet.forward.request_uri')}"/>
<spring:url value="/investment-monitor/resources/autocomplete/{/componentuid}" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid"  value="${component.uid}"/>
</spring:url>
<section id="search-results" class="pb-3 search-reports-container">
	<div class="container">
		<div class="row justify-content-center pos-rel mt-4">
		    <div class="col-lg-3 col-md-3"></div>
			<div class="col-lg-6 col-md-6">
				<form name="search_form_${fn:escapeXml(component.uid)}" method="get" action="${searchUrl}">
					<spring:theme code="economic.investmentreports.searchby.placeholder" var="searchPlaceholder"/>

					<ycommerce:testId code="header_search_input">
                        <input type="text" id="js-site-search-input"
                                data-test="asdfg"
                               class="form-control js-site-search-input" name="q" value=""
                               maxlength="100" placeholder="${searchPlaceholder}"
                               data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'>
                        <a class="a-search">
                            <img class="img-fluid search-icon" width="20" src="${commonResourcePath}/images/Icon-awesome-search.png" alt=""/>
                        </a>
                    </ycommerce:testId>

					<button class="button btn search-btn search-btn js_search_button" type="submit" disabled="true">
						<spring:theme code="economic.investmentreports.search.label"/></button>
					<a class="reset-search small-font"><spring:theme code="economic.investmentreports.search.clear.label"/></a>

					<span class="input-group-btn">
					    <ycommerce:testId code="header_search_button">
                            <button class="btn btn-link js_search_button" type="submit" disabled="true">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </ycommerce:testId>
                    </span>
				</form>
			</div>
			<div class="col-lg-3 col-md-3"></div>
		</div>
	</div>
</section>
<style>
	.form-control::-webkit-input-placeholder {
		color: #0000002e;
	}
</style>