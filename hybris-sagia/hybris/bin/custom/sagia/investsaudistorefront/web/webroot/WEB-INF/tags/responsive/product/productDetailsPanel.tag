<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<div class="product-details page-title">
	<ycommerce:testId
		code="productDetails_productNamePrice_label_${product.code}">
		<div class="name">
			${fn:escapeXml(product.name)}
			<%--<span class="sku">ID</span><span class="code">${fn:escapeXml(product.code)}</span>--%>
		</div>
	</ycommerce:testId>
	<%--<product:productReviewSummary product="${product}" showLinks="true"/>--%>
</div>
<div class="row">
	<div class="col-xs-10 col-xs-push-1 col-sm-6 col-sm-push-0 col-lg-4">
		<product:productImagePanel galleryImages="${galleryImages}" />
	</div>
	<div class="clearfix hidden-sm hidden-md hidden-lg"></div>
	<div class="col-sm-6 col-lg-8">
		<div class="product-main-info">
			<div class="row">
				<div class="col-lg-8">
					<div class="product-details">
						<div class="add-to-cart">
							<c:url value="/cart/add" var="addToCartUrl" />
							<%--<form:form method="post" id="addToCartForm" class="add_to_cart_form" action="${addToCartUrl}">--%>
							<%--<input type="hidden" maxlength="3" size="1" id="qty" name="qty" class="qty js-qty-selector-input" value="1">--%>
							<%--<input type="hidden" name="productCodePost" value="${fn:escapeXml(product.code)}"/>--%>
							<%--<button type="submit" class="btn btn-sagia btn-sagia-green"><spring:theme code="text.addToCart.custom"/></button>--%>
							<%--</form:form>--%>
							<%--  <a type="submit" onclick="$('#linksModal').modal('show');" class="btn btn-sagia btn-sagia-green"><spring:theme
									code="text.addToCart.custom" /> </a> 
						
                             --%>
							<c:if test="${not empty documents}">
								<div style="margin-top: 5px">
									<a class="btn btn-sagia btn-sagia-green btn-modal-downloads" href="#"> Downloads </a>
							  	</div>
							</c:if>
						</div>
						<%--<product:productPromotionSection product="${product}"/>--%>
						<%--<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">--%>
						<%--<product:productPricePanel product="${product}" />--%>
						<%--</ycommerce:testId>--%>
						<div class="description">${ycommerce:sanitizeHTML(product.description)}</div>
					</div>
				</div>

				<div class="col-sm-12 col-md-9 col-lg-6">
					<%--<cms:pageSlot position="VariantSelector" var="component" element="div" class="page-details-variants-select">--%>
					<%--<cms:component component="${component}" element="div" class="yComponentWrapper page-details-variants-select-component"/>--%>
					<%--</cms:pageSlot>--%>
					<%--<cms:pageSlot position="AddToCart" var="component" element="div" class="page-details-variants-select">--%>
					<%--<cms:component component="${component}" element="div" class="yComponentWrapper page-details-add-to-cart-component"/>--%>
					<%--</cms:pageSlot>--%>
				</div>
			</div>
		</div>
	</div>
</div>

<div style="display: none;" >
	<div id="product-download-modal" >
			<span></span>
			<div style="margin-top: 5px">
			<table class="downldTable">
				<c:forEach items="${documents}" var="document">
						<tr>
							<td class="downloadContent"><a class="specificationDoc"  onclick="window.dataLayer.push({'data_name':'${document.fileName}','product_code':'${product.code}'});" data-name="${document.fileName}" href="${document.url}" download><img class = "downloadImg" src="${commonResourcePath}/images/download_Icon.jpg"></i></a></td>
							<td class="downloadContent"><b>${document.fileName}</b></td>
						</tr>
				</c:forEach>
			</table>
			</div>
	</div>
</div>