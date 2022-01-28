<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>

<!--TODO if i have products for business activity selected, need to add products -->
<div class="contentModule-section" id="productsSection" style="display: none;">
    <div id="emptyProductSection">
        <div class="contentModule-headline"><spring:theme code="license.apply.products"/></div>

        <span class="iconElement iconElement_product text-center"><icon:product/></span>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button class="btn addNewProductButton" type="button">+ <spring:theme code="license.apply.new.product"/></button>
        </div>
        <div class="help-block"></div>
    </div>

    <!-- The following fields should be shown when you have clicked on "+ New product"  -->
    <div id="addNewProductSection" style="display: none">
        <form id="productFormId">
        <div class="row">
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select class="js-select2-search form-control" id="productId" name="productId" data-search-placeholder="<spring:theme code='license.apply.product.id.description'/>"></select>
                        <label class="control-label control-label_mandatory" for="productId"><spring:theme code="license.apply.product.id.description"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input class="form-control"  id="productQuantityId" name = "productQuantity" placeholder="." type="text" value=""/>
                                <label class="control-label control-label_mandatory"><spring:theme code="license.apply.quantity"/></label>
                                <div class="help-block"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn form-control" id="productUnitId" name="productUnit"></select>
                                <label class="control-label control-label_mandatory" for="productUnitId"><spring:theme code="license.apply.unit.of.measurement"/></label>
                                <div class="help-block"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="formTextArea">
                    <div class="form-group">
                        <textarea id="productDescriptionId" class="form-control" placeholder="." name="productDescription"></textarea>
                        <label class="control-label control-label_mandatory" for="productDescriptionId"><spring:theme code="license.apply.product.description"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="btn btn-slim btn_outline cancelProductBtn">
                <spring:theme code="general.cancel"/>
            </button>
            <button class="btn saveProductButton" type="button"><spring:theme code="license.apply.save.product"/></button>
        </div>
        </form>
</div>

    <!-- The following table should be shown when you have clicked on "Save product"-->
    <div id="productsTable" style="display: none">
        <div class="tableModule">
            <table class="tableModule-table">
                <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="products.productid"/></th>
                        <th><spring:theme code="products.productdescription"/></th>
                        <th><spring:theme code="products.qty"/></th>
                        <th><spring:theme code="products.unit"/></th>
                        <th id="productsBtnColumnId"></th>
                    </tr>
                </thead>
                <tbody id="productsId" class="tableModule-body">
                    <tr class="productTemplate" style="display: none">
                        <td><strong></strong></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td class="tableModule-bodyItem-action">
                            <button type="button" class="btn btn_link editProductButton" data-toggle="modal" data-target="#productModalId">
                                <icon:edit/>
                            </button>
                            <div class="deleteDropdown js-deleteDropdown">
                                <button type="button" class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
                                    <icon:remove/>
                                </button>
                                <div class="deleteDropdown-drop">
                                    <div class="deleteDropdown-text">
                                        <spring:theme code="products.deleteproduct"/>
                                    </div>
                                    <div class="deleteDropdown-actions">
                                        <button type="button" class="btn btn_outline btn_slim js-deleteDropdown-cancel">
                                            <spring:theme code="general.cancel"/>
                                        </button>
                                        <button type="button" class="btn btn_slim removeProductButton">
                                            <spring:theme code="general.delete"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button class="btn addNewProductButton" type="button">+ <spring:theme code="license.apply.new.product"/></button>
        </div>
    </div>
</div>

<div class="contentModule-actions contentModule-actions_spaceBetween">
    <span>
        <ycommerce:testId code="personalDetails_cancelPersonalDetails_button">
            <button id="entityInformationBackButton" disabled="disabled" type="button" class="btn btn-normal btn-ctrl btn-outline btn_bold">
                <spring:theme code="licenseApplyEntityInformation.button.back" text="Back"/>
            </button>
        </ycommerce:testId>
        <button id="entityInformationCancelButton" type="button" class="btn btn-normal btn-ctrl btn-outline btn_bold bg-transparant">
<spring:theme code="licenseApplyEntityInformation.button.cancel"/>
        </button>
    </span>
    <span>
        <%--<button type="submit" class="btn btn-secondary btn_link btn_bold">Skip</button>--%>
        <ycommerce:testId code="personalDetails_savePersonalDetails_button">
            <button id="entityInformationNextButton" type="button" class="btn hidden">
                <spring:theme code="licenseApplyEntityInformation.button.next" text="Next"/>
            </button>
        </ycommerce:testId>

        <button type="button" class="entity-info-submit btn btn-normal btn-ctrl btn-bg btn_bold">
            <spring:theme code="licenseApplyEntityInformation.button.next" text="Next"/>
        </button>
    </span>
</div>