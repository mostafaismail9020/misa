<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="/globalSearch" var="searchUrl" />
<spring:url value="/search/autocomplete/global/{/componentuid}" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid"  value="${component.uid}"/>
</spring:url>
<section id="home-page-global-search">
    <div class="container">
        <div class="row justify-content-center pos-rel">
            <div class="col-lg-6 col-md-6">
                <form name="search_form_${fn:escapeXml(component.uid)}" method="get" action="${searchUrl}">
					<spring:theme code="text.home.globalsearch.placeholder" var="searchPlaceholder"/>

					<ycommerce:testId code="header_search_input">
						<input type="text" id="js-site-search-input"
						       data-test="asdfg"
							   class="js-site-search-input home-page-global-search-input" name="q" value=""
							   maxlength="100" placeholder="${searchPlaceholder}"
							   data-options='{"autocompleteUrl" : "${autocompleteUrl}"}'>
					</ycommerce:testId>
				</form>
            </div>
        </div>
        <div class="row">
            <div class="sectors-list text-left" style="display: none;">
                <h1 class="section-headline my-5">
                	<spring:theme code="portal.opportunity.search.choose.label"/>&nbsp;
                	<span class="clr_gld"><spring:theme code="portal.opportunity.search.sector.label"/></span>
                </h1>
                <div>
                    <div class="sector-btn" data-sectorid="0" data-sectorName="All" data-selected="no" style="justify-content: center;">
                        <img class="sector-item-icon mr-2" src="" data-norm="" data-alt="" alt=""/>
                        <span class="sector-list-text"><spring:theme code="portal.opportunity.search.sector.all.label"/></span>
                        <img class="img-fluid close-icon" src="${commonResourcePath}/images/close_icon.png" alt=""/>
                    </div>
                    <c:forEach var="subCategory" items="${categoryList}">
                        <div class="sector-btn" data-sectorName="${subCategory.name}" data-sectorid="${subCategory.code}" data-selected="no">
                        	 <img class="opp-sector-item-icon deselected-img mr-2" src="${fn:escapeXml(subCategory.logo.url)}" 
                            							data-norm="${fn:escapeXml(subCategory.logo.url)}" 
                                                        data-alt="${fn:escapeXml(subCategory.logo.url)}" alt=""/>
                                                        
                            <img style="display: none;" class="opp-sector-item-icon selected-img mr-2" src="${fn:escapeXml(subCategory.normal.url)}" 
                            							data-norm="${fn:escapeXml(subCategory.normal.url)}" 
                                                        data-alt="${fn:escapeXml(subCategory.normal.url)}" alt=""/>
						                                                        
                            <span class="sector-list-text ml-3">${subCategory.name}</span>
                            <img class="img-fluid close-icon" src="${commonResourcePath}/images/close_icon.png" alt=""/>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
