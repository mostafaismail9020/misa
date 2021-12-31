<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    <!-- Breadcurms -->
                    <!--End of  Breadcurms -->
                    <div class="macro_economic_container economic-overview-container">
                        <div class="container economic-overview">
                            <div class="row">
                                <div class="col-md-12 title-area">

                                    <c:if test="${not empty title}">
                                        <h1 class="title-heading">${title}</h1>
                                    </c:if>

                                    <c:if test="${not empty subtitle}">
                                        <h2 class="title-heading">${subtitle}</h2>
                                    </c:if>
                                    <c:if test="${not empty text}">
                                        <h3>${text}</h3>
                                    </c:if>
                                    <!-- <c:if test="${not empty backgroundImage}">
                                        <img class="js-responsive-image achievement_header_icon position-absolute" src="${backgroundImage.url}" alt='${backgroundImage.altText}' title='${backgroundImage.altText}' style="">
                                    </c:if> -->
                                    
                                    <div class="o-stories__list">
                                        <c:forEach var="currentComponent" items="${components}">
                                            <cms:component component="${currentComponent}" element="div" />
                                        </c:forEach>
                                    </div>
                                    
                                </div>
                            </div>

                        </div>


                    </div>