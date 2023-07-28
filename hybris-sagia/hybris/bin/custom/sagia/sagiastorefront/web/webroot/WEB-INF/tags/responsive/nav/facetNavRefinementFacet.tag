<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="facetData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<c:if test="${not empty facetData.values}">
    <ycommerce:testId code="facetNav_title_${facetData.name}">
        <div class="facet js-facet">
            <div class="facet__name js-facet-name text-center clr_gld text-uppercase" style="margin-top: 10px;">
                <span class="glyphicon facet__arrow"></span>
                ${facetData.name}
                <i class="fa fa-plus plus-minus-facet open-facet" data-facet="${facetData.code}" style="display: none"></i>
                <i class="fa fa-minus plus-minus-facet close-facet" data-facet="${facetData.code}" style="display: none"></i>
                <hr>
            </div>

            <div class="facet__values js-facet-values js-facet-form" data-facet="${facetData.code}">
                <ul class="facet__list js-facet-list <c:if test="${not empty facetData.topValues}">facet__list--hidden js-facet-list-hidden</c:if>">
                    <c:forEach items="${facetData.values}" var="facetValue">
                        <li>
                            <c:if test="${facetData.multiSelect}">
                                <ycommerce:testId code="facetNav_selectForm">
                                    <form action="#" method="get">
                                        <input type="hidden" name="q" value="${facetValue.query.query.value}" />
                                        <input type="hidden" name="text" value="${solrSearchPageData.freeTextSearch}" />
                                        <label class="text-capitalize ${facetValue.selected ? 'selected' : ''}">
                                            <button type="button" class="btn btn-primary custom-button" onclick="redirectToLink('${fn:replace(facetValue.query.url,'/search','')}')">
                                                ${fn:escapeXml(facetValue.name)}
                                                <span class="badge badge-danger custom-badge">${facetValue.count}</span>
                                            </button>
                                        </label>
                                    </form>
                                </ycommerce:testId>
                            </c:if>
                            <c:if test="${not facetData.multiSelect}">
                                <c:url value="${facetValue.query.url}" var="facetValueQueryUrl" />
                                <button type="button" class="btn btn-primary custom-button" onclick="redirectToLink('${facetValueQueryUrl}')">
                                    ${fn:escapeXml(facetValue.name)}
                                    <span class="badge badge-light custom-badge">${facetValue.count}</span>
                                </button>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </ycommerce:testId>
</c:if>


<script>
    function redirectToLink(url) {

        var baseUrl = window.location.origin + window.location.pathname.replace(/\/en\//, '/');

        var completeUrl = baseUrl + url;

        window.location.href = completeUrl;
    }
</script>


