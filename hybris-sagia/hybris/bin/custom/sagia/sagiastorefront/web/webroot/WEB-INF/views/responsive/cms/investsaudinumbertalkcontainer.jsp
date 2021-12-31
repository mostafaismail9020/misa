<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

                        <section id="when-number-talk" class="when-number-talk-container">
                            <div class="container">

                                <div class="row">
                                    <div class="col-lg-12 pt-4 pt-lg-0 content mx-auto text-center" data-aos="fade-right" data-aos-delay="100">
                                        <h2>${component.title}</h2>
                                    </div>
                                </div>

                                <div class="row pt-5">
                                    <div class="col-lg-12 order-1 order-lg-2">
                                        <div class="row">
                                            <c:forEach var="currentComponent" items="${components}" varStatus="status">
                                                <c:set var="loopCount" value="${(status.index + 1) * 150}" />
                                                <div class="col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="${loopCount}">
                                                    <cms:component component="${currentComponent}" element="div" />
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </section>