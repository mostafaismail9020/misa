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
            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox1.date}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox1.date}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox1.image)}" alt="${lastReportsBox1.title}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox1.title}">${lastReportsBox1.title}</h3>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox1.url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox2.date}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox2.date}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox2.image)}" alt="${lastReportsBox2.title}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox2.title}">${lastReportsBox2.title}</h3>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox2.url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox3.date}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox3.date}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox3.image)}" alt="${lastReportsBox3.title}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox3.title}">${lastReportsBox3.title}</h3>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox3.url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-3 mb-5">
                <div class="news-card">
                    <div class="news-date text-center">
                        <div class="day"><fmt:formatDate value="${lastReportsBox4.date}" pattern="d" /></div>
                        <div class="month"><fmt:formatDate value="${lastReportsBox4.date}" pattern="MMMM" /></div>
                    </div>
                    <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox4.image)}" alt="${lastReportsBox4.title}"/>
                    <div class="news-card-inner">
                        <h3 title="${lastReportsBox4.title}">${lastReportsBox4.title}</h3>
                        <a class="btn btn-primary-fill btn-knowmore" href="${lastReportsBox4.url}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                            <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!--  InvestmentSaudiLastReportCategoryComponent end -->
</c:if>
