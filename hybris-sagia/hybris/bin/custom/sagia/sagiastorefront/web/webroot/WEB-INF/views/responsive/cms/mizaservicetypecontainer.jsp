<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

                        <c:if test="${component.visible}">
                            <section class="mizaSection mizaServiceTypes p100" style="padding-bottom:0">
                                <div class="container">
                                    <h2 class="mizaTitle  wow fadeInUp   animated" data-wow-delay="300ms"
                                        data-wow-duration="1s">MIZA<span class="clr_gld"
                                            style="color: var(--miza-primary)"> SERVICE TYPES</span></h2>
                                </div>
                                <div class="servicePortfolio">
                                    <div class="servicePortWrapper">
                                        <c:forEach var="currentComponent" items="${servicetypelist}" varStatus="status">
                                            <c:if test="${status.index % 2 == 0}">
                                                <div class="servicePortfolioItem">
                                                    <cms:component component="${currentComponent}" element="tr" style="text-align: center;" />
                                                    <cms:component component="${servicetypelist[status.index + 1]}" element="tr" style="text-align: center;" />
                                                </div>
                                            </c:if>                                          
                                        </c:forEach>
                                    </div>
                                </div>
                            </section>


                        </c:if>