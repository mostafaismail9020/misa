<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <c:choose>
        <c:when test="${isCategoryPage}">
            <section class="success-story">
                <div class="container">
                    <div class="row">
                        <h1 class="heading my-4 mt-2 col-md-12">${component.title}</h1>
                    </div>
                    <div class="page-main-content mt-0 mb-0">
                        <div class="row">
                            <c:choose>
                                <c:when test="${not empty successStories}">
                                    <c:forEach var="successStoryData" items="${successStories}" varStatus="status">
                                        <div class="col-lg-4 col-md-6 col-sm-12 col-12 my-4">
                                            <a href="${encodedContextPath}${successStoryData.successStory.url}">
                                                <div class="content-box fearured-opp">
                                                    <div>
                                                        <img src="${successStoryData.successStory.logo.url}" class="mx-auto d-block mt-3" alt="">
                                                        <div class="button btn org-button btn-green"><spring:theme code="success.story.button.text"/></div>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </c:forEach>
                                    <div style="direction: ltr;" class="col-md-12 float-rigt text-right explore-more mb-2"><a href="${portal.cmsLinkUrl(component.exploreAllURL)}" class="mb-0">${component.exploreAllURL.linkName}</a></div>
                                </c:when>
                                <c:otherwise>
                                    <h1 class="diff-color comming-soon mx-auto col-md-12"><spring:theme code="text.success.story.contact.message"/></h1>
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>
                </div>
            </section>
        </c:when>
        <c:otherwise>
            <section class="success-story my-5">
                <div class="container">
                    <hr />
                    <h1 class="heading text-center my-4">${component.title}</h1>
                    <div class="row">
                        <c:forEach var="successStoryData" items="${successStories}" varStatus="status">
                            <div class="col-lg-4 col-md-6 col-sm-12 col-12 my-4">
                                <a href="${encodedContextPath}${successStoryData.successStory.url}">
                                    <div class="content-box fearured-opp">
                                        <div>
                                            <img src="${successStoryData.successStory.logo.url}" class="mx-auto d-block mt-3" alt="">
                                            <div class="button btn org-button btn-green"><spring:theme code="success.story.button.text"/></div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row">
                        <div class="col-md-12 float-rigt text-right explore-more"><a href="${portal.cmsLinkUrl(component.exploreAllURL)}">${component.exploreAllURL.linkName}</a></div>
                    </div>
                </div>
            </section>
        </c:otherwise>
    </c:choose>
</c:if>
