<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="facetData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetData" %>
<%@ attribute name="sortCodeSelected" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<c:if test="${not empty facetData.values}">
    <ycommerce:testId code="facetNav_title_${facetData.name}">
        <div class="facet js-facet">
            <div class="facet__values js-facet-values js-facet-form" data-facet="${facetData.code}">
                <label for="facet-${facetData.code}" class="full">${facetData.name}:&nbsp;</label>
                <select id="facet-${facetData.code}" name="q" class="form-control--plp-sorting browser-default custom-select form-control" style=";padding: 6px 20px;">
                    <option value="">
                        <spring:theme code="economic.investmentreports.search.select"/>
                    </option>
                    <c:forEach items="${facetData.values}" var="facetValue">
                        <option value=":${sortCodeSelected}:${facetData.code}:${facetValue.code}" ${facetValue.selected? 'selected="selected"' : ''}>
                            ${fn:escapeXml(facetValue.name)}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </ycommerce:testId>
</c:if>