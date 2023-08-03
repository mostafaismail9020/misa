<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="/sectors-opportunities/opportunities" var="searchUrl" />
<spring:url value="/search/autocomplete/{/componentuid}" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid"  value="${component.uid}"/>
</spring:url>
	<div class="container">
		<div class="row justify-content-center pos-rel mt-4">
		<div class="row mt-4">
			<c:forEach var="facet" items="${solrSearchPageData.facets}">
				<div class="sectors-list sectors-list-${facet.name} text-left col-lg-12" style="display: none;">
					<div class="ui-sagia-facet-scroll">
						<%--					<div class="sector-btn" data-sectorid="0" data-sectorName="All" data-selected="no" style="justify-content: center;">--%>
						<%--						<img class="sector-item-icon mr-2" src="" data-norm="" data-alt="" alt=""/>--%>
						<%--						<span class="sector-list-text"><spring:theme code="portal.opportunity.search.sector.all.label"/></span>--%>
						<%--						<img class="img-fluid close-icon" src="${commonResourcePath}/images/close_icon.png" alt=""/>--%>
						<%--					</div>--%>

							<h1 class='section-headline my-5'>
								<spring:theme code="portal.opportunity.search.choose.label"/>&nbsp;
								<span class="clr_gld">${facet.name}</span>
							</h1>

							<c:forEach var="subCategory" items="${facet.values}">
								<a href="${fn:replace(subCategory.query.url,'search','sectors-opportunities/opportunities')}">
									<c:if test="${facet.multiSelect}">

										<div class="sector-btn ${subCategory.selected ? 'selected' : ''}" data-sectorName="${subCategory.code}" data-sectorid="${subCategory.query.url}" data-selected="no">
												<%--							<img class="opp-sector-item-icon deselected-img mr-2" src="${fn:escapeXml(subCategory.logo.url)}"--%>
												<%--								 data-norm="${fn:escapeXml(subCategory.logo.url)}"--%>
												<%--								 data-alt="${fn:escapeXml(subCategory.logo.url)}" alt=""/>--%>

												<%--							<img style="display: none;" class="opp-sector-item-icon selected-img mr-2" src="${fn:escapeXml(subCategory.normal.url)}"--%>
												<%--								 data-norm="${fn:escapeXml(subCategory.normal.url)}"--%>
												<%--								 data-alt="${fn:escapeXml(subCategory.normal.url)}" alt=""/>--%>

											<span class="sector-list-text ml-3">${subCategory.name}</span>
											<img class="img-fluid close-icon" src="${commonResourcePath}/images/close_icon.png" alt=""/>
										</div>
									</c:if>
								</a>
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
			</c:forEach>
		</div>
	</div>

<style>
	.form-control::-webkit-input-placeholder {
		color: #0000002e;
	}
</style>