<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--  InvestmentSaudiLastReportCategoryComponent start -->
<c:if test="${component.visible}">
    <div class="container InvestmentSaudiLastReportCategory-container">
        <div class="row">

            <c:url value="${lastReportsBox1.reportUrl}" var="lastReportsBox1Url"/>
            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox1.reportDate}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox1.reportDate}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox1.reportImage.url)}" alt="${lastReportsBox1.boxTitle}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox1.boxTitle}">${lastReportsBox1.boxTitle}</h3>
                        <p>${lastReportsBox1.reportShortInformation}</p>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox1Url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

            <c:url value="${lastReportsBox2.reportUrl}" var="lastReportsBox2Url"/>
            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox2.reportDate}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox2.reportDate}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox2.reportImage.url)}" alt="${lastReportsBox2.boxTitle}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox2.boxTitle}">${lastReportsBox2.boxTitle}</h3>
                        <p>${lastReportsBox2.reportShortInformation}</p>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox2Url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

            <c:url value="${lastReportsBox3.reportUrl}" var="lastReportsBox3Url"/>
            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox3.reportDate}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox3.reportDate}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox3.reportImage.url)}" alt="${lastReportsBox3.boxTitle}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox3.boxTitle}">${lastReportsBox3.boxTitle}</h3>
                        <p>${lastReportsBox3.reportShortInformation}</p>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox3Url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

            <c:url value="${lastReportsBox4.reportUrl}" var="lastReportsBox4Url"/>
            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox4.reportDate}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox4.reportDate}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox4.reportImage.url)}" alt="${lastReportsBox4.boxTitle}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox4.boxTitle}">${lastReportsBox4.boxTitle}</h3>
                        <p>${lastReportsBox4.reportShortInformation}</p>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox4Url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!--  InvestmentSaudiLastReportCategoryComponent end -->
</c:if>
