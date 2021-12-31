<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="mb-5 general-info">
    <div class="umb-grid">
        <div class="grid-section">
            <div >
                <div class='container'>
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div >
                                <div class="container mb-4">
                                    <div class="row">
                                       <c:forEach var="currentComponent" items="${components}" varStatus="status">
                                            <cms:component component="${currentComponent}" element="div" class="col-md-6"/>
                                       </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>