<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:set var="selectedTab" value="${empty param.tab ? 1 : param.tab}" />
<c:choose>
    <c:when test="${component.index eq selectedTab}">
        <input id="tab-${component.index}" type="radio" name="tabs" checked>
    </c:when>
    <c:otherwise>
        <input id="tab-${component.index}" type="radio" name="tabs">
    </c:otherwise>
</c:choose>
<label for="tab-${component.index}"><span>0${component.index}</span>${component.url.linkName}</label>
<div id="content-${component.index}" class="tab-content">
    <div class="scroller">
        <div class="row">
            <div class="col-md-12">
                <div class="umb-grid">
                    <div class="grid-section">
                        <div>
                            <div class="container">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <div>
                                            ${component.description}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>