<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<section class="mb-5 general-info">
    <div class="umb-grid">
        <div class="grid-section">
            <div>
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div>
                                <div class="sitelinks">
                                 
                                    <c:set var="siteMapHierarchy" value="${mapSiteMapItemData}"/>                                                                      
                                   <nav:sagiasitemap siteMap="${siteMapHierarchy}"></nav:sagiasitemap>
                                 
									<%-- <c:forEach var="siteMapItem" items="${siteMapHierarchy}">
   										<a href="${siteMapItem.key.url}">${siteMapItem.key.name}</a></br></br>
									 </c:forEach>   --%>                               
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>