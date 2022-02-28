<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
        <%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
            <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
                <!-- <%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb"%> -->
                    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                        <spring:htmlEscape defaultHtmlEscape="true" />

                        <!-- <c:if test="${fn:length(breadcrumbs) > 0}">
                            <div class="breadcrumb-section breadcrumb-plp">
                                <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                            </div>
                        </c:if> -->
                        <div class="title-heading">Invest Saudi Branding Content</div>
                        <nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}" />
                        <div id="products-wrapper" class="product__listing product__grid">
                            <c:forEach items="${searchPageData.results}" var="product" varStatus="status">
                                <product:productListerGridItem product="${product}" />
                            </c:forEach>
                        </div>

                        <div id="addToCartTitle" class="display-none">
                            <div class="add-to-cart-header">
                                <div class="headline">
                                    <span class="headline-text"><spring:theme code="basket.added.to.basket"/></span>
                                </div>
                            </div>
                        </div>

                        <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}" />