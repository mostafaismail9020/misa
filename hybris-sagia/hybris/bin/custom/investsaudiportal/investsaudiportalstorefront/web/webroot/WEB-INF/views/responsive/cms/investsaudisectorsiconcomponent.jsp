<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<section>
    <div class="umb-grid">
        <div class="grid-section">
            <div class="sectors-opp sectors-investment pb-5">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div>
                                <p class="my-4 dark-gray">${description}</p>
                                <div class="heading text-left my-4">${title}</div>
                                <div class="row">
                                    <c:forEach items="${category.categories}" var="category">
                                        <c:url var="categoryUrl" value="${portal.generateCategoryLink(category)}" />
                                        <div class="col-lg-3 col-md-3 col-sm-6 col-6 my-2">
                                            <a href="${categoryUrl}">
                                                <div class="fearured-opp img-swap text-center">
                                                    <div>
                                                        <img src="${category.picture.url}" data-norm="${category.picture.url}" data-alt="${category.thumbnail.url}" alt="${category.name}">
                                                        <h3 class="font-bold">${category.name}</h3>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
