$(function () {
    var productTempId;
    var productInitialDescription;
    var editProduct = function () {
        clearProductValidation();
        var selectedProductId = $(this).closest('tr').attr('id');
        var selectedProduct = licenseAmendment.products.find(function (product) {
            return product.srId == selectedProductId || product.newItemId == parseInt(selectedProductId);
        });

        productTempId = selectedProduct.id;
        $('#productId').val(selectedProduct.id).attr('disabled', true);

        productInitialDescription = selectedProduct.description;
        $('#productDescriptionId').val(selectedProduct.description);
        $('#productQuantityId').val(selectedProduct.quantity);

        var $productUnit = $('#productUnitId');
        $productUnit.next().addClass("select2Container_selected");
        $productUnit.val(selectedProduct.unit).trigger('change');

        $('.saveProductBtn').attr('id', selectedProduct.srId || selectedProduct.newItemId);
    };

    var saveProduct = function () {
        var validator = productValidator();
        if(!validator.form()) {
            return;
        }

        SAGIA.licenseAmendment.dirtyAmendment = true;
        var saveProductBtn = $('.saveProductBtn');
        var productSrId = saveProductBtn.attr('id');
        saveProductBtn.removeAttr('id');

        var id = $('#productId').val() || productTempId;
        var description = $('#productDescriptionId').val();
        var descriptionChanged = productInitialDescription !== description;
        var quantity = $('#productQuantityId').val();

        var $unit = $('#productUnitId option:selected');
        var unit = $unit.val();
        var unitDescription = $unit.text();

        var productRow;
        if (productSrId) { // edit product
            productRow = $('#' + productSrId);
            productRow.children().first().html(id).next().text(description).next().text(quantity).next().text(unit);

            var productIndex = licenseAmendment.products.findIndex(function (product) {
                return product.srId == productSrId || product.newItemId == parseInt(productSrId);
            });

            if (licenseAmendment.products[productIndex].srId) {
                licenseAmendment.products[productIndex].action = '02';
                setColorForModifiedRow(productRow);
            }
            licenseAmendment.products[productIndex].id = id;
            licenseAmendment.products[productIndex].description = description;
            licenseAmendment.products[productIndex].descriptionChanged = descriptionChanged;
            licenseAmendment.products[productIndex].quantity = quantity;
            licenseAmendment.products[productIndex].unit = unit;
            licenseAmendment.products[productIndex].unitDescription = unitDescription;
        } else { // new product
            productRow = $('.productTemplate').first().clone();
            productRow.show();
            productRow.attr("id", newItemId).children().first().html(id).next().text(description).next().text(quantity).next().text(unit);
            setColorForNewRow(productRow);
            productRow.find('.removeProductBtn').on('click', removeProduct);
            productRow.find('.editProductBtn').on('click', editProduct);
            $('#productsId').append(productRow);

            licenseAmendment.products.push({
                id: id,
                description: description,
                descriptionChanged : descriptionChanged,
                quantity: quantity,
                unit: unit,
                unitDescription: unitDescription,
                newItemId: newItemId++,
                action: '01'
            });
        }
        $("#productModalId").modal('hide');
        clearProductForm();
    };

    var removeProduct = function () {
        SAGIA.licenseAmendment.dirtyAmendment = true;
        var $productRow = $(this).closest('tr');
        var productId = $productRow.attr('id');
        $productRow.remove();

        var products = licenseAmendment.products;
        for (var i = 0; i < products.length; i++) {
            if (productId == products[i].srId) {
                products[i].action = '03';
                break;
            } else if (productId == products[i].newItemId) {
                products.splice(i, 1);
                break;
            }
        }
    };

    var clearProductForm = function () {
        var $productId = $('#productId');
        $productId.val('').attr('disabled', false);
        $productId.trigger('change.select2');
        $('#productDescriptionId').val('');
        $('#productQuantityId').val('');

        var $productUnit = $('#productUnitId');
        $productUnit.val(null).trigger('change');
        $productUnit.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

        $('.saveProductBtn').removeAttr('id');
        removeErrorIfExists();
    };

    var removeErrorIfExists = function(){
        $('.form-group').each(function(i, obj) {
            if($(this).hasClass('has-error')){
                $(this).removeClass('has-error');
            }

            if($(this).find('.help-block:first').length) {
                $(this).find('.help-block:first').text('');
            }

            if ($(this).closest('.formSelectBox').length){
                $(this).closest('.formSelectBox').find('.help-block').text('');
            }

            if($(this).closest('.formInputBox').length){
                $(this).closest('.formInputBox').find('.help-block').text('');
            }
        });
    };

    if ($('body').hasClass('page-license-amend')) {
        $(".editProductBtn").on('click', editProduct);
        $('.saveProductBtn').on('click', saveProduct);
        $('.cancelProductBtn').on('click', clearProductForm);
        $('.removeProductBtn').on('click', removeProduct);

        $('#productSearchBtnId').on('click', function () {
            $('#productSearchModalId').modal({
                backdrop: "static",
                keyboard: false
            });
        });

        var $selectElement = $('.js-product-list');
        $selectElement.select2({
            dropdownCssClass: 'select2-dropdown_oneColumn select2-dropdown_oneColumn_hasFilter',
            ajax: {
                type: "POST",
                contentType:"application/json",
                url: ACC.config.encodedContextPath + "/my-sagia/license/amend/products",
                displayErrorMessageInModal: false,
                data: function (params) {
                    var filterForProducts = {};
                    filterForProducts['batchNo'] = params.page;
                    filterForProducts['userInput']= params.term;
                    return JSON.stringify(filterForProducts);
                },
                processResults: function (data,params) {
                    params.page = params.page || 1;
                    if(data.length) {
                        var parsedData = $.map((JSON.parse(data)), function (obj) {
                            obj.id = obj.id || obj.ProductID; // replace pk with your identifier
                            return obj;

                        });
                    }
                    return {
                        results: (data.length ? parsedData : ""),
                        pagination: {
                            more: (params.page * 100) <= (data.length ? parsedData.length : 0)
                        }
                    }
                }
            },

            escapeMarkup: function (markup) {
                return markup; // let our custom formatter work
            },
            minimumInputLength: -1,
            templateResult: function(repo) {
                if (repo.loading) {
                    return repo.text;
                }
                return repo.ProductID + ' ' + repo.ProdDesc;
            },
            templateSelection: function(repo) {
                return repo.ProdDesc + ' ' + repo.ProductID;
            }
        });

        $selectElement.on('select2:select', function (e) {
            var data = e.params.data;
            document.getElementById('productDescriptionId').value = productInitialDescription = data.ProdDesc;
            document.getElementById('productQuantityId').value = data.Quantity;
            var $productUnitId = $('#productUnitId');
            $productUnitId.val(data.Unit);
            $productUnitId.trigger('change.select2');
            SAGIA.formElements.placeholderPolyfill();
        });
        
        $selectElement.on('select2:closing', function(e){
            if(!$('.spinnerContainer').hasClass('hidden')) {
                e.preventDefault();
                e.stopPropagation();
            }
        })
    }

    $('#productId').on("change", function () {
        if($(this).closest('.form-group').hasClass('has-error')) {
            $(this).closest('.form-group').removeClass('has-error');
        }

        if ($(this).closest('.formSelectBox').length>0){
            $(this).closest('.formSelectBox').find('.help-block').text('');
        }


        var $productDescriptionId = $("#productDescriptionId").closest('.form-group');
        if($productDescriptionId.hasClass('has-error')) {
            $productDescriptionId.removeClass('has-error');
        }

        var $productDescriptionId = $("#productDescriptionId");
        if ($productDescriptionId.nextAll('.help-block:first').length) {
            $productDescriptionId.nextAll('.help-block').text('');

        }

        var $productQuantityId = $("#productQuantityId").closest('.form-group');
        if($productQuantityId.hasClass('has-error')) {
            $productQuantityId.removeClass('has-error');
        }

        var $productQuantityId = $('#productQuantityId');
        if ($productQuantityId.nextAll('.help-block:first').length) {
            $productQuantityId.nextAll('.help-block').text('');
        }

        var $productUnitId = $('#productUnitId').closest('.form-group');
        if($productUnitId.hasClass('has-error')) {
            $productUnitId.removeClass('has-error');
        }

        var $productUnitId = $('#productUnitId');
        if ($productUnitId.closest('.formSelectBox').length>0){
            $productUnitId.closest('.formSelectBox').find('.help-block').text('');
        }

    });
});







