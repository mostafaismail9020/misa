<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<c:set var="selectedTab" value="1" />
<c:choose>
    <c:when test="${component.index eq selectedTab}">
        <input id="tab-${component.index}" type="radio" name="tabs" checked>
    </c:when>
    <c:otherwise>
        <input id="tab-${component.index}" type="radio" name="tabs">
    </c:otherwise>
</c:choose>
<label for="tab-${component.index}"><span>0${component.index}</span><em>${component.url.linkName}</em></label>
<div id="content-${component.index}" class="tab-content">
    <div class="scroller sub-sector-content">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="umb-grid">
                    <div class="grid-section">
                        <div>
                            <div class='container'>
                                <div class="row clearfix">
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

