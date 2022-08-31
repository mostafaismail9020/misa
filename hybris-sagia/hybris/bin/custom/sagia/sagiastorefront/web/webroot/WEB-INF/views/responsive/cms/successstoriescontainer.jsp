<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

                    <!-- Breadcurms -->
                    <!--End of  Breadcurms -->
                    <div class="macro_economic_container successstory-container logo-padding-bottom">
                        <div class="row m-0">
                            <div class="col-md-12">
                                <div class="section-title text-center aos-init aos-animate" data-aos="fade-right" data-aos-delay="100">
                                    <c:if test="${not empty title}">
                                        <h2><span class="clr_gld">${title}</span></h2>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <div class="slider-carousel success-logo-container">
                            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel"  data-interval="false">
                                <div class="carousel-logos">
                                    <ol class="carousel-indicators">
                                        <c:if test="${not empty components}">
                                            <c:forEach var="entry" items="${components}">
                                                <c:if test="${not empty entry}">
                                                    <li data-target="#carouselExampleControls" data-slide-to="">
                                                        <img class="img-fluid js-responsive-image achievement_header_icon" id="${entry.uid}" src="${entry.companyLogo.url}" alt='${entry.companyLogo.altText}' title='${entry.companyLogo.altText}' style="" loading="lazy">
                                                    </li>

                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </ol>
                                </div>

                                <div class="carousel-inner">
                                    <c:forEach var="currentComponent" items="${components}">
                                        <div class="carousel-item ${currentComponent.uid}">
                                            <cms:component component="${currentComponent}" element="div" />
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="sucess-slider-contols meet-kingdom-control">
                                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                        <img src="${commonResourcePath}/images/prev.svg" alt="" />
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                        <img src="${commonResourcePath}/images/next.svg" alt="" />
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- <div class="container success-slider">
                            <div class="success-slider-box">
                                <c:if test="${not empty components}">
                                    <c:forEach var="entry" items="${components}">
                                        <c:if test="${not empty entry}">
                                            <img class="js-responsive-image achievement_header_icon" id="${entry.uid}" src="${entry.companyLogo.url}" alt='${entry.companyLogo.altText}' title='${entry.companyLogo.altText}' style="">
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </div>
                        </div> -->
                    </div>