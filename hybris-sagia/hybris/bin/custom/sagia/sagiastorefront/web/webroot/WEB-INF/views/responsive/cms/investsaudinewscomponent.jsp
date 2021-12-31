<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <div class="container sectors-content mb-5">

        <div class="row">

            <div class="col-md-4 news-image">
                <img src="${fn:escapeXml(component.newsDetailsImage.url)}"
                     alt="${fn:escapeXml(component.newsDetailsImage.altText)}"/>
            </div>
            <div class="col-lg-8 col-md-8 col-sm-8 pb-5 news-contents">
                <c:if test="${not empty component.newsFullReport}">
                    <a rel="noopener" href="${fn:escapeXml(component.newsFullReport.url)}" target="_blank"
                       class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" download=""><img style="display: none;"
                                                                                                  src="https://px.ads.linkedin.com/collect/?pid=656043&amp;conversionId=1411604&amp;fmt=gif"
                                                                                                  alt="" width="1"
                                                                                                  height="1"><spring:theme code="text.news.download.full.report"/></a>
                </c:if>
                <p>&nbsp;</p>

                    ${component.newsFullDescription}
                <p>

                    <c:if test="${not empty component.newsFullReport}">
                <p>&nbsp;</p>
                <a rel="noopener" href="${fn:escapeXml(component.newsFullReport.url)}" target="_blank"
                   class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" download=""><img style="display: none;"
                                                                                              src="https://px.ads.linkedin.com/collect/?pid=656043&amp;conversionId=1411604&amp;fmt=gif"
                                                                                              alt="" width="1"
                                                                                              height="1"><spring:theme code="text.news.download.full.report"/></a>
                </p>
                <p>
                <p>&nbsp;</p>
                <a rel="noopener" href="/en" target="_blank" class="button btn btn-green pl-5 pr-5 m-0 text-uppercase">
                    <spring:theme code="text.learn.more.about.investment.opportunities"/>
                </a>
                </p>
                </c:if>
            </div>

        </div>

        <div class="row">
            <c:forEach items="${component.galleryImages.medias}" var="media" varStatus="varStatus">
                <div class="col-md-3 my-3 d-flex align-items-center justify-content-center">
                    <a class="" data-fancybox="gallery" href="#"
                       data-caption="${newsTitle}">
                        <img class="img-fluid rounded" src="${media.url}"
                             alt="${newsTitle}">
                    </a>
                </div>
            </c:forEach>
        </div>


    </div>
</c:if>