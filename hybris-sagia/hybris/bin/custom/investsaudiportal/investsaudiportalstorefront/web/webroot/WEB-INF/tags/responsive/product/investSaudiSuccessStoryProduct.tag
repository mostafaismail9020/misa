<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<main>
    <c:if test="${not empty productData.banner.url}">
        <section class="company-bg-profile">
            <div class="container">
                <img class="img-fluid" src="${productData.banner.url}" height="166" alt="${productData.banner.altText}">
            </div>
        </section>
    </c:if>
    <div class="container sectors-content">
        <div class="row">
            <div class="col-lg-9 col-md-9 col-sm-12mt-3">
                ${productData.description}
                <c:if test="${not empty productData.featureAdditionalInfoMap}">
                    <div class="container mt-4 mb-4">
                        <div class="row clearfix">
                            <c:forEach var="featureMapItem" items="${productData.featureAdditionalInfoMap}">

                                <div class="col-md-3 d-flex alignment-stretch">
                                    <div class="facts-block d-flex">
                                        <div class="facts-corner text-center mb-2">
                                            <div class="div-text">${featureMapItem.key}</div>
                                            <div class="div-text">${featureMapItem.value}</div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </c:if>

            </div>

            <c:if test="${not empty productData.featureInfoMap}">
                <div class="col-lg-3 col-md-3 col-sm-12 py-3">
                    <div class="company-detail-block">
                        <c:if test="${not empty productData.logo.url}">
                            <img src="${productData.logo.url}"
                                 class="mx-auto d-block my-3" alt="${productData.logo.altText}">
                        </c:if>
                        <ul class="text-left company-detail-list">
                            <c:forEach var="featureMapItem" items="${productData.featureInfoMap}">
                                <li>${featureMapItem.value}<br /><span class="dark-gray">${featureMapItem.key}</span></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:if>

        </div>
    </div>

    <section class="company-mgr-quote">

        <div class="umb-grid">
            <div class="grid-section">
                <div>
                    <div class='container'>
                        <div class="row clearfix">
                            <div class="col-md-12 column">
                                <div>
                                    ${productData.quote}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>