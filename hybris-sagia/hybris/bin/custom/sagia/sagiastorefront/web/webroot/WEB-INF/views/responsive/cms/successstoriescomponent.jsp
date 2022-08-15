<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

                <div class="success_story_component container-fluid success-main-content pl-0">
                    <div class="row sucess_section">
                        <div class="col-md-5 p-md-0 sucess_img_bg aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
                            <div class="quote-wrapper">
                                <div class="sucess_img ">
                                    <!--<c:if test="${not empty poster}">
			          <img class="js-responsive-image achievement_header_icon" src="${poster.url}" alt='${poster.altText}' title='${poster.altText}' style="">
			      </c:if>-->
                                    <c:if test="${not empty videoLink}">
                                        <div class="video-player-container">
                                            <div class="embed-responsive embed-responsive-16by9">
                                                <iframe width="560" height="315" src="${videoLink.url}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7" data-aos="fade-up" data-aos-delay="600">
                            <div class="sucess_content">
                                <div class="sucess_content_itenlogo position-relative">
                                    <c:if test="${not empty companyLogo}">
                                        <img class="position-absolute js-responsive-image achievement_header_icon pb-0 icon_right" src="${companyLogo.url}" alt='${companyLogo.altText}' title='${companyLogo.altText}' style="">
                                    </c:if>
                                </div>
                                <c:if test="${not empty description}">
                                    <p>${description}</p>
                                </c:if>
                                <c:if test="${not empty localizedStats}">
                                    <c:forEach var="entry" items="${localizedStats}">
                                        <p>
                                            <span class="para-title"><c:out value="${entry.key}" /> :</span>
                                            <c:out value="${entry.value}" />
                                        </p>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>