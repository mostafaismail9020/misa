<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section id="search-results" class="sectors-opp sectors-investment pb-5 sectors-opportunity opp-search-container">
    <div class="container">
        <div class="row justify-content-center pos-rel mt-4">
            <div class="col-lg-6 col-md-6">
                <spring:theme code="portal.opportunity.searchby.placeholder" var="searchPlaceholder"/>
                <input type="search" placeholder="${searchPlaceholder}">
                <a class="a-search">
                    <img class="img-fluid search-icon" width="20" src="${commonResourcePath}/images/Icon-awesome-search.png" alt=""/>
                </a>
                <button type="button" class="button btn opp-search-btn search-btn"><spring:theme code="portal.opportunity.search.label"/></button>
                <a class="reset-search small-font"><spring:theme code="portal.opportunity.search.clear.label"/></a>
            </div>
            <div class="col-lg-6 col-md-6 text-right">
                <button id="open-filter" type="button" class="button btn opp-filter-btn filter-btn">
                	<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/filter_icon.png" alt=""/>
                	<spring:theme code="portal.opportunity.search.filter.label"/>
                </button>
                <button id="close-filter" style="display: none;" type="button" class="button btn opp-filter-btn filter-btn">
                	<img class="img-fluid arrow-icon" src="${commonResourcePath}/images/filter_icon.png" alt=""/>
                	<spring:theme code="portal.opportunity.search.close.filter.label"/>
                </button>
            </div>
        </div>
        <div class="row mt-4">
            <div class="sectors-list text-left" style="display: none;">
                <h1 class='section-headline my-5'>
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
                <div class="filter-buttons text-center mt-3">
                    <button type="button" class="button btn opp-reset-search-btn filter-reset-search">
                    	<spring:theme code="portal.opportunity.search.clear.search.label"/>
                    </button>
                    <button type="button" class="button btn opp-filter-search-btn search-btn">
                    	<spring:theme code="portal.opportunity.search.apply.search.label"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>
 
<div>
    <h1 class='section-headline my-5 all-opportunity-description'>
    	<spring:theme code="portal.opportunity.search.all.label"/>&nbsp;
    	<pan class="clr_gld"><spring:theme code="portal.opportunity.search.opportunities.label"/></pan>
    </h1>
</div>
