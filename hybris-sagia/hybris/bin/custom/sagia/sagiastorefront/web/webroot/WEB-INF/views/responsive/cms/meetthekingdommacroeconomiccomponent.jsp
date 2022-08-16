<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


                <div class="macro_economic_component">
                    <div class="panel-box aos-init" data-aos="fade-up" data-aos-delay="0">
                        <div class="macro_economic_icon box-img">
                            <c:if test="${not empty imageIcon}">
                                <img class="js-responsive-image achievement_header_icon" src="${imageIcon.url}" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="" loading="lazy">
                            </c:if>
                        </div>

                        <c:if test="${not empty headerText}">
                            <h3 class="count">${headerText}</h3>
                        </c:if>

                        <c:if test="${not empty description}">
                            <p class="desc">${description}</p>
                        </c:if>
                    </div>



                </div>