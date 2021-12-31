<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<section class="sectors-investment">
    <div class="container">
        <h2 class="heading my-4">${title}</h2>
        <div class="row">
            <c:forEach items="${category.categories}" var="category" varStatus="item">
                <c:if test="${item.index <= 5}">
                    <c:url var="categoryUrl" value="${portal.generateCategoryLink(category)}" />
                    <div class="col-lg-2 col-md-6 col-sm-6 col-6 my-4 mywow sector fadeOuts">
                        <a href="${categoryUrl}">
                            <div class="fearured-opp img-swap text-center">
                                <div>
                                    <img src="${category.picture.url}" data-norm="${category.picture.url}" data-alt="${category.thumbnail.url}" alt="${category.name}"/>
                                    <h3 class="font-bold">${category.name}</h3>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:if>
            </c:forEach>
            <div class="col-md-12 text-right explore-more"><a href="${portal.cmsLinkUrl(exploreAllURL)}">${exploreAllText}</a></div>
        </div>
    </div>
</section>
