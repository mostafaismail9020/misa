<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

                        <!-- ======= Mega And Gigya Container Starts ======= -->
                        <section id="mg" class="mg-container">
                            <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="section-title text-center" data-aos="fade-right" data-aos-delay="100">
                                        <h2>${component.projectsTitle}</h2>

                                    </div>
                                    <a href="${portal.cmsLinkUrl(component.exploreAllUrl)}" class="btn-primary explore-btn explore-gia-btn">
                                          ${component.exploreAllTitle}&nbsp;
                                            <img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
                                    </a>
                                </div>
                            </div>
                            </div>

                            <div class="mg-slider slider-0" id="mg-slider">
                                <div class="tab-content container">
                                    <c:forEach var="currentComponent" items="${components}" varStatus="status">
                                        <cms:component component="${currentComponent}" />
                                    </c:forEach>
                                    <ul class="row d-flex align-items-center clientContainer nav nav-pills" id="pills-tab" role="tablist">
                                        <c:forEach var="currentComponent1" items="${components}" varStatus="status">
                                            <li class="nav-item col-lg-3 col-md-3 col-sm-4 col-6 mb-4">
                                                <a class="nav-link box-wrapper text-center position-relative pb-3 <c:if test="${currentComponent1.defaultProject}">active</c:if>" id="spc_inc-${currentComponent1.projectCSS}-tab" data-toggle="pill" href="#${currentComponent1.projectCSS}" role="tab" aria-controls="${currentComponent1.projectCSS}"
                                                    aria-selected="true">
                                                    <img class="mg-active" style="display: none;" src="${fn:escapeXml(currentComponent1.projectSmartActiveLogo.url)}" class="img-fluid">
                                                    <img class="mg-inActive" style="display: none;" src="${fn:escapeXml(currentComponent1.projectSmartLogo.url)}" class="img-fluid">
                                                    
                                                    <%-- <img src="${fn:escapeXml(currentComponent1.projectSmartActiveLogo.url)}" class="img-fluid"> --%>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            
                        </section>
                        <!-- ======= Mega And Gigya Container Ends ======= -->