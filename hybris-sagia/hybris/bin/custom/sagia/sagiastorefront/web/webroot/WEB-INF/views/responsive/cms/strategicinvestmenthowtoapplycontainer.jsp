<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

                        <c:if test="${component.visible}">
                            <link rel='stylesheet'
                                href='//cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css?ver=1'
                                type='text/css' media='all' />
                            <section id="howToApply" class="howToApply ">
                                <div class="container-fluid posRel">
                                    <div class="strategicTitle   wow fadeInUp animated" data-wow-duration="0.5s">
                                        <h5 class="strategicSubTitle">
                                            <spring:theme code="portal.sector.how.to.apply.label" />
                                        </h5>
                                        <h1>
                                            <spring:theme code="portal.sector.how.to.apply.main.label" />
                                        </h1>
                                    </div>
                                    <div class="howTOApplyV2">
                                        <div class="howTOApplyV2Wrapper row">
                                            <c:forEach var="currentComponent1" items="${howtoapplycardlist}"
                                                varStatus="loop">
                                                <!-- <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/> -->
                                                <div class="col-md-6 col-xl-3 htaCol">
                                                    <i class="fa-solid fa-arrow-right howTOApplyV2Arrow"></i>
                                                    <div class="howTOApplyV2Item wow fadeInLeft animated"
                                                        data-wow-duration="1s" data-wow-delay="0.8s">
                                                        <!-- <img src="assets/images/logo-icon.png"
                                                            class="cardBackgroundLogo"> -->
                                                        <div class="howToApplyHeader">
                                                            <div class="howToApplyIcon">
                                                                <img src="${currentComponent1.howtoapplyimage.url}">
                                                            </div>
                                                            <div class="howToApplyStep">
                                                                <span>${currentComponent1.howtoapplystep}</span>
                                                            </div>
                                                        </div>
                                                        <div class="howToApplyBody">
                                                            <h5>${currentComponent1.howtoapplytitle} </h5>
                                                            <p> ${currentComponent1.howtoapplydescription}</p>
                                                            <a href="#"
                                                                class="onlineApplyBtn  ${loop.first  ? '' : 'd-none' }">Apply
                                                                Now</a>
                                                            <span
                                                                class="commingSoon  ${loop.first  ? '' : 'd-none' }">Comming
                                                                Soon</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="patternRight"
                                    style="background-image: url(/_ui/responsive/theme-alpha/img/shape-25.png);"></div>
                                <div class="patternLeft"
                                    style="background-image: url(/_ui/responsive/theme-alpha/img/shape-26.png);"></div>
                            </section>
                        </c:if>