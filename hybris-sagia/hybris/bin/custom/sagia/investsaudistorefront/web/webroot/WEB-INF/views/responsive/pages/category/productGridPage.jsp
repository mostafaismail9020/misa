<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
        <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
            <%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
                <%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb"%>
                <%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>

                    <template:page pageTitle="${pageTitle}">

                        <cms:pageSlot position="Section1" var="feature" element="div" class="product-grid-section1-slot">
                            <cms:component component="${feature}" element="div" class="yComponentWrapper map product-grid-section1-component" />
                        </cms:pageSlot>
 
                        <div class="breadcrumb-section breadcrumb-plp" style="margin-top: -35px;">
                            <div class="container"><breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" /></div>
                        </div> 
                        <div class=" bg_image_in_show">
                            <div class="container">
                                <div class="row section-bg">
                                    <div class="col-xs-3">
                                        <cms:pageSlot position="ProductLeftRefinements" var="feature" element="div" class="product-grid-left-refinements-slot">
                                            <cms:component component="${feature}" element="div" class="yComponentWrapper product-grid-left-refinements-component" />
                                        </cms:pageSlot>
                                    </div>
                                    <div class="col-sm-12 col-md-9 isb-content-right">
                                        <cms:pageSlot position="ProductGridSlot" var="feature" element="div" class="product-grid-right-result-slot">
                                            <cms:component component="${feature}" element="div" class="product__list--wrapper yComponentWrapper product-grid-right-result-component" />
                                        </cms:pageSlot>
                                    </div>
                                </div>
                        </div>
                        </div>
                        <storepickup:pickupStorePopup />
                    </template:page>