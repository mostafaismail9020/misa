var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = SAGIA.license.apply || {};
SAGIA.license.apply.data = SAGIA.license.apply.data || {};
SAGIA.license.apply.data.entityInformation = {};
SAGIA.license.apply.data.businessActivities = SAGIA.license.apply.data.businessActivities || {};
SAGIA.license.apply.data.entityInformation.products = [];

SAGIA.license.apply.displayEntityInformationTab = function () {
    var licenseInformationSection = $("#licenseInformationSection");
    var stockMarketSection = $("#stockMarketSection");
    var hasSagiaLicenseSection = $("#hasSagiaLicenseSection");
    var hasShareholdersTypePerson = $("#hasShareholdersTypePerson");
    var advanceLicenseNumberSection = $("#advanceLicenseNumberSection");
    var licenseTypeSection = $("#licenseTypeSection");
    var onStockMarketSection = $("#onStockMarketSection");
    var notOnStockMarketSection = $("#notOnStockMarketSection");
    var businessActivitiesSection = $("#businessActivitiesSection");
    var basicInformationExtendedSection = $("#basicInformationExtendedSection");
    var basicInformationSection = $("#basicInformationSection");
    var technicalAndFinancialCriteriaSection = $("#technicalAndFinancialCriteriaSection");
    var attachmentSection = $("#attachmentSection");
    var yearSection = $("#licenseYearSection");
    var hasAdvanceLicenseNumberSection = $("#hasAdvanceLicenseNumberSection");
    var productsSection = $("#productsSection");
    var hasProfessionalLicenseSection = $("#hasProfessionalLicenseSection");
    var professionalLicenseCrSection = $("#professionalLicenseCrSection");

    var switchToQeemah1 = function () {
        loadBasicInformationQeemah1Data();
        basicInformationExtendedSection.show();
        licenseInformationSection.show();
        licenseTypeSection.show();
        setMobileCode('#basicInformationExtendedSection #basicInformationExtendedCountry',
            "#basicInformationExtendedCountryCodeForTelephone",
            "#basicInformationExtendedCountryCodeForMobilePhone");
        // changed since now it's also showing license type in qeemah1 too
        // SAGIA.license.apply.data.licenseType = "0"; //other
        // SAGIA.license.businessActivities.setLicenseType(SAGIA.license.apply.data.licenseType);
        // businessActivitiesSection.show();
        loadYearsDropDown();
    };

    $(document).off("click", "#hasGCCNationalityNO");
    hasSagiaLicenseSection.find("#hasGCCNationalityNO").on("click", function() {
        $("#hasGCCNationalityYESLabel").css("border-color", "");
        $("#hasGCCNationalityNOLabel").css("border-color", "");
//start of comment RFC# 1800000394: ASHAIK      
//        $("#hasSagiaLicenseSpan").show(); 
//        $("#hasSagiaLicenseRadioDiv").show();
//end of comment  RFC# 1800000394   
//start of change RFC# 1800000394: ASHAIK     
        SAGIA.license.apply.data.qeemah = 1;
        SAGIA.license.apply.data.hasSagiaLicense = false;
//      basicInformationExtendedSection.hide();
        hasShareholdersTypePerson.show();
        advanceLicenseNumberSection.show();
        advanceLicenseNumberSection.find("input").prop("checked", null);
/*      hasAdvanceLicenseNumberSection.hide();
        licenseTypeSection.hide();
        licenseTypeSection.find("#licenseTypes").val("");
        licenseInformationSection.hide();
        attachmentSection.hide();
        stockMarketSection.hide();
        stockMarketSection.find("input").prop("checked", null);
        onStockMarketSection.hide();
        notOnStockMarketSection.hide();
        basicInformationSection.hide();
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
        businessActivitiesSection.hide();*/
//      $("#hasSagiaLicenseYESLabel").css("border-color", "");
//      $("#hasSagiaLicenseNOLabel").css("border-color", "");  
//      end of change ASHAIK RFC# 1800000394: ASHAIK   
    });
    hasSagiaLicenseSection.find("#hasGCCNationalityYES").on("click", function() {
        window.location.href = "https://mci.gov.sa/";
    });

    $(document).off("click", "#hasSagiaLicenseYES");
    hasSagiaLicenseSection.find("#hasSagiaLicenseYES").on("click", function () {
        SAGIA.license.apply.data.qeemah = 1;
        SAGIA.license.businessActivities.setBusinessTypeSectionVisible(true);
        SAGIA.license.apply.data.hasSagiaLicense = true;
        switchToQeemah1();
        hasShareholdersTypePerson.hide();
        advanceLicenseNumberSection.hide();
        //licenseTypeSection.hide(); //available in qeemah 1 now as well
        //licenseTypeSection.find("#licenseTypes").val("");
        attachmentSection.hide();
        stockMarketSection.hide();
        stockMarketSection.find("input").prop("checked", null);
        onStockMarketSection.hide();
        notOnStockMarketSection.hide();
        basicInformationSection.hide();
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
        $("#hasSagiaLicenseYESLabel").css("border-color", "");
        $("#hasSagiaLicenseNOLabel").css("border-color", "");
        //businessActivitiesSection.hide(); -shown as it's qeemah1
    });
    hasSagiaLicenseSection.find("#hasSagiaLicenseNO").on("click", function () {
        SAGIA.license.apply.data.qeemah = 2;
        SAGIA.license.apply.data.hasSagiaLicense = false;
        basicInformationExtendedSection.hide();
        hasShareholdersTypePerson.show();
        advanceLicenseNumberSection.show();
        advanceLicenseNumberSection.find("input").prop("checked", null);
        hasAdvanceLicenseNumberSection.hide();
        licenseTypeSection.hide();
        licenseTypeSection.find("#licenseTypes").val("");
        licenseInformationSection.hide();
        attachmentSection.hide();
        stockMarketSection.hide();
        stockMarketSection.find("input").prop("checked", null);
        onStockMarketSection.hide();
        notOnStockMarketSection.hide();
        basicInformationSection.hide();
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
        //businessActivitiesSection.hide();
        $("#hasSagiaLicenseYESLabel").css("border-color", "");
        $("#hasSagiaLicenseNOLabel").css("border-color", "");
    });

    hasShareholdersTypePerson.find("#hasShareholdersTypePersonYES").on("click", function () {
        SAGIA.license.apply.data.qeemah = 1;
        SAGIA.license.businessActivities.setBusinessTypeSectionVisible(true);
        SAGIA.license.apply.data.hasShareholdersTypePerson = true;
        switchToQeemah1();
        advanceLicenseNumberSection.hide();
        //licenseTypeSection.hide(); //available in qeemah 1 now as well
        //licenseTypeSection.find("#licenseTypes").val("");
        attachmentSection.hide();
        stockMarketSection.hide();
        stockMarketSection.find("input").prop("checked", null);
        onStockMarketSection.hide();
        notOnStockMarketSection.hide();
        basicInformationSection.hide();
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
        $("#hasShareholdersTypePersonYESLabel").css("border-color", "");
        $("#hasShareholdersTypePersonNOLabel").css("border-color", "");

        //businessActivitiesSection.hide(); -shown as it's qeemah1
    });
    hasShareholdersTypePerson.find("#hasShareholdersTypePersonNO").on("click", function () {
        SAGIA.license.apply.data.qeemah = 1; //RFC# 1800000394: ASHAIK 
        SAGIA.license.apply.data.hasShareholdersTypePerson = false; 
//start of change ASHAIK RFC# 1800000394: ASHAIK 	
        advanceLicenseNumberSection.show();
        switchToQeemah1();
        attachmentSection.hide();
        stockMarketSection.hide();
        stockMarketSection.find("input").prop("checked", null);
        onStockMarketSection.hide();
        licenseTypeSection.hide();
        licenseInformationSection.hide();
        notOnStockMarketSection.hide(); 
        basicInformationSection.hide();
        basicInformationExtendedSection.hide();
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
//end of change  ASHAIK RFC# 1800000394: ASHAIK      
		hasSagiaLicenseSection.find("#hasSagiaLicenseNO").trigger("click"); //start over
        $("#hasShareholdersTypePersonYESLabel").css("border-color", "");
        $("#hasShareholdersTypePersonNOLabel").css("border-color", "");
    });

    $("#advanceLicenseNumberSection #hasAdvanceLicenseNrYES").on("click", function () {
        SAGIA.license.apply.data.hasAdvanceLicenseNr = true;
        hasAdvanceLicenseNumberSection.show();
        hasAdvanceLicenseNumberSection.find("input").focus();
        licenseTypeSection.show(); //irrespective of having or not a license number, this must be shown
        switchToQeemah1();  //ASHAIK 
        licenseTypeSection.find("#licenseTypes").val("");
//      licenseInformationSection.hide();  //24032019
		licenseInformationSection.show();  //24032019											 
        attachmentSection.hide();
        stockMarketSection.hide();
        stockMarketSection.find("input").prop("checked", null);
        onStockMarketSection.hide();
        notOnStockMarketSection.hide();
        basicInformationSection.hide();
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
        //businessActivitiesSection.hide();
        productsSection.hide();
        $("#hasAdvanceLicenseNrYESLabel").css("border-color", "");
        $("#hasAdvanceLicenseNrNOLabel").css("border-color", "");

    });
    $("#advanceLicenseNumberSection #hasAdvanceLicenseNrNO").on("click", function () {
        SAGIA.license.apply.data.hasAdvanceLicenseNr = false;
        SAGIA.license.apply.data.advanceLicenseNr = null;
        hasAdvanceLicenseNumberSection.hide();
        hasAdvanceLicenseNumberSection.find("input").val("");
		switchToQeemah1(); //ashaik
        licenseTypeSection.show(); //irrespective of having or not a license number, this must be shown
//      licenseInformationSection.hide();   //24032019
		licenseInformationSection.show();   //24032019											  
        attachmentSection.hide();
        stockMarketSection.hide();
        stockMarketSection.find("input").prop("checked", null);
        onStockMarketSection.hide();
        notOnStockMarketSection.hide();
        basicInformationSection.hide();
        basicInformationExtendedSection.show(); //RFC# 1800000394: ASHAIK 
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
        //businessActivitiesSection.hide();
        productsSection.hide();
        $("#hasAdvanceLicenseNrYESLabel").css("border-color", "");
        $("#hasAdvanceLicenseNrNOLabel").css("border-color", "");
    });

    hasAdvanceLicenseNumberSection.find("input").on("blur", function () {
        SAGIA.license.apply.data.advanceLicenseNr = $(this).val();
    });

    licenseTypeSection.find("#licenseTypes").on("change", function () {
    	
		$('#licenseTypes').parent().removeClass('has-error');
        SAGIA.license.apply.data.licenseType = $(this).val() + "";
        SAGIA.license.apply.data.licenseTypeName = $(this).find(":selected").text();
        SAGIA.license.businessActivities.clearAll();
        SAGIA.license.apply.data.activityAttachments = [];
        SAGIA.license.businessActivities.setShowAttachments(true);
        var draft = SAGIA.license.apply.draft;
        if (draft && draft.isPresent && SAGIA.license.apply.data.businessActivities) {
            var draftActivities = SAGIA.license.apply.data.businessActivities.selectedActivities;
            if(draftActivities){
            draftActivities.forEach(function (draftActivity) {
                draftActivity.id = draftActivity.activityId;
            });
            }

            SAGIA.license.businessActivities.setLicenseType(SAGIA.license.apply.data.licenseType, SAGIA.license.apply.data.licenseTypeName, draftActivities);

            var draftProducts = SAGIA.license.apply.data.entityInformation.products;
            var licenseType = SAGIA.license.apply.data.licenseType;
            
        } else {
            SAGIA.license.businessActivities.setLicenseType(SAGIA.license.apply.data.licenseType, SAGIA.license.apply.data.licenseTypeName);
        }
        attachmentSection.hide();
        stockMarketSection.hide();
        onStockMarketSection.hide();
        notOnStockMarketSection.hide();
 //       basicInformationSection.hide();            RFC# 1800000394: ASHAIK 
 //       basicInformationExtendedSection.show();  //RFC# 1800000394: ASHAIK  
        technicalAndFinancialCriteriaSection.hide();
        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
        //businessActivitiesSection.hide();

        if (SAGIA.license.apply.data.licenseType === "0") { //Other
            SAGIA.license.apply.data.qeemah = 1;
            SAGIA.license.businessActivities.setBusinessTypeSectionVisible(true);
            switchToQeemah1();
        } else if (SAGIA.license.apply.data.licenseType && SAGIA.license.apply.data.qeemah === 2) {
            SAGIA.license.businessActivities.setBusinessTypeSectionVisible(false);
             loadBasicInformationQeemah2Data(); 
      //      basicInformationSection.show(); RFC# 1800000394: ASHAIK(-)
      //      stockMarketSection.show();      RFC# 1800000394: ASHAIK(-) 
            basicInformationExtendedSection.show();  //RFC# 1800000394: ASHAIK (+) 
               stockMarketSection.hide();
        }

           productsSection.hide();

        //if (SAGIA.license.apply.data.qeemah === 1) { //irrespective of qeemah
            businessActivitiesSection.show();
            SAGIA.license.businessActivities.show();
            SAGIA.license.businessActivities.setBusinessTypeSectionVisible(true); //ASHAIK 29032019
         
        //}
            
          if(SAGIA.license.apply.data.licenseType == SAGIA.config.temporaryLicenseConstant ){
        	  $("#noBusinessActivitiesSelected").hide();
        	  businessActivitiesSection.hide(); 
        	  $("#temporaryLicenseTextBox").show();
          }
          else {
        	  $("#temporaryLicenseTextBox").hide();

          }

    });
	
	$("#temporaryLicenseTextBox textarea").on("blur", function () {
	        SAGIA.license.apply.data.businessActivities.temporaryLicenseTextBox = $(this).val();
	});

    var productNo = SAGIA.license.apply.data.entityInformation.products.length;
    var newItemId = (productNo === 0 ? 1000 : productNo + 1000);
    var editProduct = function () {
        //clearProductValidation();
        SAGIA.license.apply.handleProductsSearch();
        productsSection.find("#addNewProductSection").show();
        $(".addNewProductButton").attr("disabled", "disabled");
        var selectedProductId = $(this).closest('tr').attr('id');
        var selectedProduct = SAGIA.license.apply.data.entityInformation.products.find(function (product) {
            return product.srId === selectedProductId || product.newItemId === parseInt(selectedProductId);
        });

        var $productId = $("#productId");
        $productId.select2();
        $productId.append(new Option(selectedProduct.description  + ' ' + selectedProduct.id, selectedProduct.id, false, false));
        $productId.val(selectedProduct.id).attr('disabled', true);
        $productId.next().addClass("select2Container_selected");
        $productId.trigger('change.select2');


        productsSection.find("#productDescriptionId").val(selectedProduct.description);
        productsSection.find("#productQuantityId").val(selectedProduct.quantity);

        var $productUnit = productsSection.find("#productUnitId");
        $productUnit.next().addClass("select2Container_selected");
        $productUnit.val(selectedProduct.unit).trigger('change');

        productsSection.find(".saveProductButton").attr('id', selectedProduct.srId || selectedProduct.newItemId);
        SAGIA.formElements.placeholderPolyfill();

    };
    var saveProduct = function () {
        var validator = productValidator();
        if(!validator.form()) {
            return;
        }

        productsSection.find("#addNewProductSection").hide();

        var saveProductButton = productsSection.find(".saveProductButton");
        var productSrId = saveProductButton.attr("id");
        saveProductButton.removeAttr("id");

        var id = productsSection.find("#productId").val();
        var description = productsSection.find("#productDescriptionId").val();
        var quantity = productsSection.find("#productQuantityId").val();

        var $unit = productsSection.find("#productUnitId option:selected");
        var unit = $unit.val();
        var unitDescription = $unit.text();

        var productRow;
        if (productSrId) { // edit product
            productRow = $('#' + productSrId);
            productRow.children().first().html(id).next().text(description).next().text(quantity).next().text(unit);

            var productIndex = SAGIA.license.apply.data.entityInformation.products.findIndex(function (product) {
                return product.srId === productSrId || product.newItemId === parseInt(productSrId);
            });

            if (SAGIA.license.apply.data.entityInformation.products[productIndex].srId) {
                SAGIA.license.apply.data.entityInformation.products[productIndex].action = '02';
            }
            SAGIA.license.apply.data.entityInformation.products[productIndex].id = id;
            SAGIA.license.apply.data.entityInformation.products[productIndex].description = description;
            SAGIA.license.apply.data.entityInformation.products[productIndex].quantity = quantity;
            SAGIA.license.apply.data.entityInformation.products[productIndex].unit = unit;
            SAGIA.license.apply.data.entityInformation.products[productIndex].unitDescription = unitDescription;
        } else { // new product
            productRow = productsSection.find('.productTemplate').first().clone();
            productRow.show();
            productRow.attr("id", newItemId).children().first().html(id).next().text(description).next().text(quantity).next().text(unit);
            productRow.find(".removeProductButton").on('click', removeProduct);
            productRow.find(".editProductButton").on('click', editProduct);
            productsSection.find('#productsId').append(productRow);

            SAGIA.license.apply.data.entityInformation.products.push({
                id: id,
                description: description,
                quantity: quantity,
                unit: unit,
                unitDescription: unitDescription,
                newItemId: newItemId++,
                action: '01'
            });

            $("#productsTable").show();
            $("#productsSection #emptyProductSection .addNewProductButton").hide();
        }
        clearProductForm();
        $(".addNewProductButton").removeAttr("disabled");

    };

    var removeProduct = function () {
        var $productRow = $(this).closest('tr');
        var productId = $productRow.attr('id');
        $productRow.remove();

        var products = SAGIA.license.apply.data.entityInformation.products;
        for (var i = 0; i < products.length; i++) {
            //please leave it this way, it's a string.
            if (productId == products[i].srId) {
                products[i].action = '03';
                break;
            }
            //please leave it this way, it's a string.
            else if (productId == products[i].newItemId) {
                products.splice(i, 1);
                break;
            }
        }
    };

    $(".editProductButton").on('click', editProduct);

    $(".removeProductButton").on("click", removeProduct);

    function productValidator() {
        $.validator.addMethod('minStrict', function (value, el, param) {
            return value > param;
        });
        return $("#productFormId").validate({

            highlight: function(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function(element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            focusInvalid: false,
            ignore: "",
            errorElement: 'span',
            errorPlacement: function(error, element) {
                if (element.closest('.form-group').find('.help-block').length>0) {
                    error.appendTo(element.closest('.form-group').find('.help-block'));
                } else if (element.hasClass('js-select2-oneColumn')  || element.hasClass('js-select2-search')){
                    error.appendTo(element.closest('.formSelectBox').find('.help-block'));
                } else {
                    error.appendTo(element.closest('.formInputBox').find('.help-block'));
                }
            },
            rules: {
                productId: "required",
                productDescription: "required",
                productQuantity: {
                        required: true,
                        number: true,
                        minStrict: 0
                    },
                productUnit: "required"
            },
            messages: {
                productId: {
                    required: getI18nText("validation.product")
                },
                productDescription: {
                    required: getI18nText("validation.productDesc")
                },
                productQuantity: {
                    required: getI18nText("validation.quantity"),
                    minStrict: getI18nText("validation.quantity"),
                    number:getI18nText("validation.quantity")
                },
                productUnit: {
                    required: getI18nText("validation.productUnit")
                }
            }
        });
    }


    var clearProductForm = function () {
        var $productId = $('#productId');
        $productId.val('').attr('disabled', false);
        $productId.trigger('change.select2');
        $('#productDescriptionId').val('');
        $('#productQuantityId').val('');

        var $productUnit = productsSection.find("#productUnitId");
        $productUnit.next().removeClass("select2Container_selected");
        $productUnit.val('').trigger('change');

        $('.saveProductButton').removeAttr('id');
        removeErrorIfExists();
    };
    var removeErrorIfExists = function () {
        $('.form-group').each(function () {
            if ($(this).hasClass('has-error')) {
                $(this).removeClass('has-error');
            }

            if ($(this).find('.help-block:first').length) {
                $(this).find('.help-block:first').text('');
            }

            if ($(this).closest('.formSelectBox').length) {
                $(this).closest('.formSelectBox').find('.help-block').text('');
            }

            if ($(this).closest('.formInputBox').length) {
                $(this).closest('.formInputBox').find('.help-block').text('');
            }
        });
    };


    $('#productId').on("change", function () {
        removeErrorIfExists();

    });

    productsSection.find(".addNewProductButton").on("click", function () {
        clearProductForm();
        productsSection.find("#addNewProductSection").show();
        $(".addNewProductButton").attr("disabled", "disabled");
        SAGIA.license.apply.handleProductsSearch();
        });
    productsSection.find(".saveProductButton").on("click", function () {
        saveProduct();
    });
  
    productsSection.find(".cancelProductBtn").on("click", function () {
        productsSection.find("#addNewProductSection").hide();
        clearProductForm();
        $(".addNewProductButton").removeAttr("disabled");
    });


    licenseInformationSection.find("#isEntrepreneurYES").on("click", function () {
        SAGIA.license.apply.data.isEntrepreneur = true;
        attachmentSection.show();
        loadYearsDropDownForEntrepreneur();
        $("#licenseYearSection").find("#licenseYear").val("1").trigger("blur").trigger('change');
        
    });
    licenseInformationSection.find("#isEntrepreneurNO").on("click", function () {
        SAGIA.license.apply.data.isEntrepreneur = false;
        attachmentSection.hide();
        loadYearsDropDown();
        $("#licenseYearSection").find("#licenseYear").prop("disabled", false);
        
    });
    
    licenseInformationSection.find("#hasProfessionalLicenseCrYES").on("click", function () {
        SAGIA.license.apply.data.hasProfessionalLicenseCr = true;
        professionalLicenseCrSection.show();
    });
    
    licenseInformationSection.find("#hasProfessionalLicenseCrNO").on("click", function () {
        SAGIA.license.apply.data.hasProfessionalLicenseCr = false;
        professionalLicenseCrSection.hide();
    });

    SAGIA.license.apply.data.attachments = {};
    attachmentSection.find("#boardResolutionFile").on("change", function () {
        SAGIA.license.apply.data.attachments.boardResolutionFile = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.boardResolutionFileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.boardResolutionFileName = SAGIA.license.apply.data.attachments.boardResolutionFile;
            SAGIA.license.apply.data.attachments.boardResolutionFile = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
    attachmentSection.find("#letterOfSupportFile").on("change", function () {
        SAGIA.license.apply.data.attachments.letterOfSupportFile = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.letterOfSupportFileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.letterOfSupportFileName = SAGIA.license.apply.data.attachments.letterOfSupportFile;
            SAGIA.license.apply.data.attachments.letterOfSupportFile = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });

 attachmentSection.find("#entityListedInStockMarketFile").on("change", function () {
        SAGIA.license.apply.data.attachments.entityListedInStockMarketFile = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.entityListedInStockMarketFileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.entityListedInStockMarketFileName = SAGIA.license.apply.data.attachments.entityListedInStockMarketFile;
            SAGIA.license.apply.data.attachments.entityListedInStockMarketFile = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#entityAssetFile").on("change", function () {
        SAGIA.license.apply.data.attachments.entityAssetFile = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.entityAssetFileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.entityAssetFileName = SAGIA.license.apply.data.attachments.entityAssetFile;
            SAGIA.license.apply.data.attachments.entityAssetFile = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#entityRevenueFile").on("change", function () {
        SAGIA.license.apply.data.attachments.entityRevenueFile = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.entityRevenueFileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.entityRevenueFileName = SAGIA.license.apply.data.attachments.entityRevenueFile;
            SAGIA.license.apply.data.attachments.entityRevenueFile = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#branchCR1File").on("change", function () {
        SAGIA.license.apply.data.attachments.branchCR1File = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.branchCR1FileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.branchCR1FileName = SAGIA.license.apply.data.attachments.branchCR1File;
            SAGIA.license.apply.data.attachments.branchCR1File = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#branchCR2File").on("change", function () {
        SAGIA.license.apply.data.attachments.branchCR2File = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.branchCR2FileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.branchCR2FileName = SAGIA.license.apply.data.attachments.branchCR2File;
            SAGIA.license.apply.data.attachments.branchCR2File = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#branchCR3File").on("change", function () {
        SAGIA.license.apply.data.attachments.branchCR3File = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.branchCR3FileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.branchCR3FileName = SAGIA.license.apply.data.attachments.branchCR3File;
            SAGIA.license.apply.data.attachments.branchCR3File = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#branchCR4File").on("change", function () {
        SAGIA.license.apply.data.attachments.branchCR4File = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.branchCR4FileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.branchCR4FileName = SAGIA.license.apply.data.attachments.branchCR4File;
            SAGIA.license.apply.data.attachments.branchCR4File = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#mainBranchCRFile").on("change", function () {
        SAGIA.license.apply.data.attachments.mainBranchCRFile = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.mainBranchCRFileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.mainBranchCRFileName = SAGIA.license.apply.data.attachments.mainBranchCRFile;
            SAGIA.license.apply.data.attachments.mainBranchCRFile = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#otherBranchCR1File").on("change", function () {
        SAGIA.license.apply.data.attachments.otherBranchCR1File = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.otherBranchCR1FileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.otherBranchCR1FileName = SAGIA.license.apply.data.attachments.otherBranchCR1File;
            SAGIA.license.apply.data.attachments.otherBranchCR1File = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });
	
 attachmentSection.find("#otherBranchCR2File").on("change", function () {
        SAGIA.license.apply.data.attachments.otherBranchCR2File = $(this).val();
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.attachments.otherBranchCR2FileMimeType = event.target._TYPE;
            SAGIA.license.apply.data.attachments.otherBranchCR2FileName = SAGIA.license.apply.data.attachments.otherBranchCR2File;
            SAGIA.license.apply.data.attachments.otherBranchCR2File = removeBase64prefix(event.target._TYPE, event.target.result);
        };
        fileReader._TYPE = $(this)[0].files[0].type;
        fileReader.readAsDataURL($(this)[0].files[0]);
    });


    var loadBasicInformationQeemah1Data = function () {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var basicInformationExtendedSection = $("#basicInformationExtendedSection");

                var countries = basicInformationExtendedSection.find("#basicInformationExtendedCountry");
                countries.find("option").remove();
                countries.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                //countries.val(null);
                countries.attr("disabled", true);
                countries.val("SA").trigger("blur").trigger("change"); //hardcoded

                var legalStatus = basicInformationExtendedSection.find("#basicInformationExtendedLegalStatus");
                legalStatus.find("option").remove();
                legalStatus.append(new Option("", "", false, false));
                jsonData.legalStatus.forEach(function (currentValue) {
                    legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
                });
                legalStatus.val(null);

                var multinationalCompany = basicInformationExtendedSection.find("#basicInformationExtendedMultinationalCompany");
                multinationalCompany.find("option").remove();
                multinationalCompany.append(new Option("", "", false, false));
                jsonData.multinationalCompany.forEach(function (currentValue) {
                    multinationalCompany.append(new Option(currentValue.multinationalCompanyText, currentValue.multinationalCompany, false, false));
                });
                multinationalCompany.val(null);

                var regions = basicInformationExtendedSection.find("#basicInformationExtendedRegion");
                regions.find("option").remove();
                regions.append(new Option("", "", false, false));
                jsonData.regions.forEach(function (currentValue) {
                    regions.append(new Option(currentValue.regionText, currentValue.region, false, false));
                });
                regions.val(null);

                var investments = basicInformationExtendedSection.find("#basicInformationExtendedInvestment");
                investments.find("option").remove();
                investments.append(new Option("", "", false, false));
                jsonData.expectedInvestments.forEach(function (currentValue) {
                    investments.append(new Option(currentValue.value, currentValue.key, false, false));
                });
                investments.val(null);

                var draft = SAGIA.license.apply.draft;
                if (draft && SAGIA.license.apply.draft.isPresent) {
                    if(SAGIA.license.apply.data.basicInformationExtended.legalStatus) {
                        updateDropDown("#basicInformationExtendedSection #basicInformationExtendedLegalStatus", SAGIA.license.apply.data.basicInformationExtended.legalStatus);
                    }

                    if( SAGIA.license.apply.data.basicInformationExtended.multinationalCompany){
                        updateDropDown("#basicInformationExtendedSection #basicInformationExtendedMultinationalCompany", SAGIA.license.apply.data.basicInformationExtended.multinationalCompany);
                    }

                    if(SAGIA.license.apply.data.basicInformationExtended.investment) {
                        updateDropDown("#basicInformationExtendedSection #basicInformationExtendedInvestment", SAGIA.license.apply.data.basicInformationExtended.investment);
                    }

                    if(SAGIA.license.apply.data.basicInformationExtended.country){
                        updateDropDown("#basicInformationExtendedSection #basicInformationExtendedCountry", SAGIA.license.apply.data.basicInformationExtended.country);
                    }

                    if(SAGIA.license.apply.data.basicInformationExtended.region) {
                        updateDropDown("#basicInformationExtendedSection #basicInformationExtendedRegion", SAGIA.license.apply.data.basicInformationExtended.region);
                    }
                }
            }
        });
    };
       
    var  loadYearsDropDown = function() {
    	var entityLicenseYear = $("#licenseYearSection").find("#licenseYear");
        var previousentityLicenseYear = entityLicenseYear.val();
        entityLicenseYear.find("option").remove();
        entityLicenseYear.append(new Option("", "", false, false));
        
        
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.1"), "1", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.2"), "2", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.3"), "3", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.4"), "4", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.5"), "5", false, false));
		
        if(previousentityLicenseYear) {
        	entityLicenseYear.val(previousentityLicenseYear).trigger("blur").trigger('change');
        } else {
        	entityLicenseYear.val(null);
        }
        
    };
    
    var loadYearsDropDownForEntrepreneur = function(){
    	var entityLicenseYear = $("#licenseYearSection").find("#licenseYear");
        entityLicenseYear.find("option").remove();
        entityLicenseYear.append(new Option("", "", false, false));
        
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.entrepreneur.1"), "1", false, false));

    }

    function updateDropDown(selectId, value) {
        var $select = $(selectId);
        $select.next().addClass("select2Container_selected");
        $select.val(value).trigger("change");
    }

    var loadBasicInformationQeemah1Cities = function (region) {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/cities/" + region, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var basicInformationExtendedSection = $("#basicInformationExtendedSection");
                var basicInformationExtendedCity = basicInformationExtendedSection.find("#basicInformationExtendedCity");
                basicInformationExtendedCity.find("option").remove();
                basicInformationExtendedCity.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                jsonData.forEach(function (currentValue) {
                    basicInformationExtendedCity.append(new Option(currentValue.cityText, currentValue.city, false, false));
                });

                var draft = SAGIA.license.apply.draft;
                if (draft && SAGIA.license.apply.draft.isPresent) {

                    if(SAGIA.license.apply.data.basicInformationExtended.city){
                        updateDropDown("#basicInformationExtendedSection #basicInformationExtendedCity", SAGIA.license.apply.data.basicInformationExtended.city);
                    }
                }
            }
        });
    };

    var loadBasicInformationQeemah2Data = function () {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah2", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var basicInformationSection = $("#basicInformationSection");
                var basicInformationLegalStatus = basicInformationSection.find("#basicInformationLegalStatus");
                basicInformationLegalStatus.find("option").remove();
                basicInformationLegalStatus.append(new Option("", "", false, false));
                jsonData.legalStatusOrganization.forEach(function (currentValue) {
                    basicInformationLegalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
                });
                if(SAGIA.license.apply.data.basicInformation.legalStatus) {
                    basicInformationLegalStatus.val(SAGIA.license.apply.data.basicInformation.legalStatus).trigger("blur").trigger("change");
                } else {
                    basicInformationLegalStatus.val(null);
                }

                var basicInformationRegion = basicInformationSection.find("#basicInformationRegion");
                basicInformationRegion.find("option").remove();
                basicInformationRegion.append(new Option("", "", false, false));
                jsonData.regions.forEach(function (currentValue) {
                    basicInformationRegion.append(new Option(currentValue.regionText, currentValue.region, false, false));
                });
                if(SAGIA.license.apply.data.basicInformation.region) {
                    basicInformationRegion.val(SAGIA.license.apply.data.basicInformation.region).trigger("blur").trigger("change");
                } else {
                    basicInformationRegion.val(null);
                }

                var investments = basicInformationSection.find("#basicInformationInvestment");
                investments.find("option").remove();
                investments.append(new Option("", "", false, false));
                jsonData.expectedInvestments.forEach(function (currentValue) {
                    investments.append(new Option(currentValue.value, currentValue.key, false, false));
                });
                investments.val(null);

                var draft = SAGIA.license.apply.draft;
                if (draft && SAGIA.license.apply.draft.isPresent) {
                    if (SAGIA.license.apply.data.basicInformation.legalStatus) {
                        updateDropDown("#basicInformationSection #basicInformationLegalStatus", SAGIA.license.apply.data.basicInformation.legalStatus);
                    }

                    if (SAGIA.license.apply.data.basicInformation.country) {
                        updateDropDown("#basicInformationSection #basicInformationCountry", SAGIA.license.apply.data.basicInformation.country);
                    }

                    if (SAGIA.license.apply.data.basicInformation.region) {
                        updateDropDown("#basicInformationSection #basicInformationRegion", SAGIA.license.apply.data.basicInformation.region);
                    }

                    if (SAGIA.license.apply.data.basicInformation.investment) {
                        updateDropDown("#basicInformationSection #basicInformationInvestment", SAGIA.license.apply.data.basicInformation.investment);
                    }
                }
            }
        });
    };

    var loadBasicInformationQeemah2Cities = function (region) {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah2/cities/" + region, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var basicInformationSection = $("#basicInformationSection");
                var basicInformationCity = basicInformationSection.find("#basicInformationCity");
                basicInformationCity.find("option").remove();
                basicInformationCity.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                jsonData.forEach(function (currentValue) {
                    basicInformationCity.append(new Option(currentValue.cityText, currentValue.city, false, false));
                });
                if(SAGIA.license.apply.data.basicInformation.city) {
                    basicInformationCity.val(SAGIA.license.apply.data.basicInformation.city).trigger("blur").trigger("change");
                } else {
                    basicInformationCity.val(null);
                }

                var draft = SAGIA.license.apply.draft;
                if (draft && SAGIA.license.apply.draft.isPresent) {

                    if (SAGIA.license.apply.data.basicInformation.city) {
                        updateDropDown("#basicInformationSection #basicInformationCity", SAGIA.license.apply.data.basicInformation.city);
                    }
                }
            }
        });
    };

    var loadStockMarketCountries = function () {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQDF", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var onStockMarketSection = $("#onStockMarketSection");
                var countries = onStockMarketSection.find("#countries");
                countries.find("option").remove();
                countries.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                jsonData.stockMarketCountries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.value, currentValue.key, false, false));
                });
                if(SAGIA.license.apply.data.basicInformation.country) {
                    countries.val(SAGIA.license.apply.data.basicInformation.country).trigger("blur").trigger("change");
                } else {
                    countries.val(null);
                }

                var licenseData = SAGIA.license.apply.data;
                var draft = SAGIA.license.apply.draft;
                if (draft && draft.isPresent) {
                    if(SAGIA.license.apply.data.onStockMarket.country) {
                        $("#onStockMarketSection #countries").val(licenseData.onStockMarket.country).trigger("change")
                            .next().addClass("select2Container_selected");
                    }

                    if(SAGIA.license.apply.data.onStockMarket.isinCode){
                        onStockMarketSection.find("#isinCode").val(licenseData.onStockMarket.isinCode);
                    }
                }
            }
        });
    };

    var loadIsicSections = function (isicSectionsElement) {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/isicSectionsQDF/" + SAGIA.license.apply.data.licenseType, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                isicSectionsElement.find("option").remove();
                isicSectionsElement.append(new Option("", "", false, false));
                jsonData.forEach(function (currentValue) {
                    isicSectionsElement.append(new Option(currentValue.sectionDescription, currentValue.sectionNumber, false, false));
                });
                if(SAGIA.license.apply.data.notOnStockMarket.isicSection) {
                    isicSectionsElement,val(SAGIA.license.apply.data.notOnStockMarket.isicSection).trigger("blur").trigger("change");
                } else {
                    isicSectionsElement.val(null);
                }
            }
        });
    };
    var loadIsicDivisions = function (isicDivisionsElement, sectionId) {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/isicDivisionsQDF/" + SAGIA.license.apply.data.licenseType + "/" + sectionId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                isicDivisionsElement.find("option").remove();
                isicDivisionsElement.append(new Option("", "", false, false));
                jsonData.forEach(function (currentValue) {
                    isicDivisionsElement.append(new Option(currentValue.divisionDescription, currentValue.divisionNumber, false, false));
                });
                if(SAGIA.license.apply.data.notOnStockMarket.isicDivison) {
                    isicDivisionsElement.val(SAGIA.license.apply.data.notOnStockMarket.isicDivison).trigger("blur").trigger("change");
                } else {
                    isicDivisionsElement.val(null);
                }
            }
        });
    };

//    stockMarketSection.find("#hasStockListingYES").on("click", function () {
//       $("#hasStockListingYESLabel").css("border-color", "");
//       $("#hasStockListingNOLabel").css("border-color", "");
//        SAGIA.license.apply.data.hasStockListing = true;
//        technicalAndFinancialCriteriaSection.hide();
//        technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
//        notOnStockMarketSection.hide();
//        //this section with small isic section has been decided never to be shown, instead it's the business activities section
//        // loadIsicSections(onStockMarketSection.find("#isicSection"));
//        loadStockMarketCountries();
//        onStockMarketSection.show();
//        //businessActivitiesSection.hide();
//        businessActivitiesSection.show();
//        SAGIA.license.businessActivities.show();
//    });
//    stockMarketSection.find("#hasStockListingNO").on("click", function () {
//        $("#hasStockListingYESLabel").css("border-color", "");
//        $("#hasStockListingNOLabel").css("border-color", "");
//        SAGIA.license.apply.data.hasStockListing = false;
//        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/stockMarketQuestions", {
//            type: "GET",
//            responseType: "application/json;charset=utf-8",
//            contentType: "application/json;charset=utf-8",
//            cache: false,
//            success: function (data) {
//                technicalAndFinancialCriteriaSection.empty();
//                var jsonData = data;
//                SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions = {};
//                for (var index in jsonData) {
//                    if (jsonData.hasOwnProperty(index)) {
//                        var dropdownValue = jsonData[index];
//                        var question = $("#technicalAndFinancialCriteriaQuestionTemplate").clone().removeAttr("id");
//                        question.attr("id", dropdownValue.questionId);
//                        var questionKey = "license.apply.technicalAndFinancialQuestions." + dropdownValue.questionId;
//                        question.find(".questionDescription").text(getI18nText(questionKey) !== questionKey ? getI18nText(questionKey) : dropdownValue.questionText);
//
//                        question.find(".questionDescriptionRadioYes").attr("name", dropdownValue.questionId);
//                        question.find(".questionDescriptionRadioYes").attr("id", dropdownValue.questionId + "YES");
//                        var yesKey = dropdownValue.financialQuestions.find(function (x) {
//                            //return x.value === 'Yes';
//                            return x.key === "01"; //because the texts in arabic have different values
//                        });
//                        question.find(".questionDescriptionRadioYes").attr("data-answer-key", yesKey.key);
//                        question.find(".questionDescriptionRadioYes").attr("data-answer-value", yesKey.value);
//
//                        question.find(".questionDescriptionRadioNo").attr("name", dropdownValue.questionId);
//                        question.find(".questionDescriptionRadioNo").attr("id", dropdownValue.questionId + "NO");
//                        var noKey = dropdownValue.financialQuestions.find(function (x) {
//                            //return x.value === 'No';
//                            return x.key === "02"; //because the texts in arabic have different values
//                        });
//                        question.find(".questionDescriptionRadioNo").attr("data-answer-key", noKey.key);
//                        question.find(".questionDescriptionRadioNo").attr("data-answer-value", noKey.value);
//
//                        question.find("label").each(function () {
//                            $(this).attr("for", $(this).parent().find("input").attr("id"));
//                            $(this).text($(this).parent().find("input").val() === "yes" ? yesKey.value : noKey.value);
//                        });
//                        technicalAndFinancialCriteriaSection.append(question.show());
//                        SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions[dropdownValue.questionId] = {
//                            answer: "",
//                            description: encodeURI(dropdownValue.questionText),
//                            answerKey: "",
//                            answerValue: ""
//                        };
//                    }
//                }
//                loadBasicInformationQeemah1Data();
//                loadBasicInformationQeemah2Data();
////              technicalAndFinancialCriteriaSection.show();
//                technicalAndFinancialCriteriaSection.find("input").prop("checked", null);
//
//                var draft = SAGIA.license.apply.draft;
//                if (draft && draft.isPresent && !draft.handledForFinancialQuestions) {
//                    var $technicalAndFinancialCriteriaSectionDiv = $("#technicalAndFinancialCriteriaSection");
//                    var technicalAndFinancialCriteriaSectionDraft = draft.technicalAndFinancialCriteriaQuestions;
//
//                    for (var indexQuestion in technicalAndFinancialCriteriaSectionDraft) {
//                        if (technicalAndFinancialCriteriaSectionDraft.hasOwnProperty(indexQuestion)) {
//                            var answerValue = technicalAndFinancialCriteriaSectionDraft[indexQuestion].answer ? 'yes' : 'no';
//                            var $question = $technicalAndFinancialCriteriaSectionDiv.find("input[value='" + answerValue + "'][name ='"+ indexQuestion+"']");
//                            $question[0].checked = true;
//                            $question.trigger('change');
//                        }
//                    }
//                    draft.handledForFinancialQuestions = true;
//                }
//            }
//        });
//        onStockMarketSection.hide();
//        //this section with small isic section has been decided never to be shown, instead it's the business activities section
//        //loadIsicSections(notOnStockMarketSection.find("#isicSection"));
//        //notOnStockMarketSection.show();
//        //businessActivitiesSection.hide();
//		if(!SAGIA.license.apply.data.licenseType == SAGIA.config.temporaryLicenseConstant){
//        businessActivitiesSection.show();
//        SAGIA.license.businessActivities.show();
//		}
//
//    });

    $(document).on("change", "#technicalAndFinancialCriteriaSection input", function () {
        var questionObject = {};
        questionObject.answer = $(this).val() === "yes";
        questionObject.description = encodeURI($(this).parents(".row").find(".questionDescription").html());
        questionObject.originalDescription = $(this).parents(".row").find(".questionDescription").html();
        questionObject.answerKey = $(this).attr('data-answer-key');
        questionObject.answerValue = $(this).attr('data-answer-value');
        SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions[$(this).attr("name")] = questionObject;
        if ($("#technicalAndFinancialCriteriaSection input[value='yes']").is(":checked")) {
            SAGIA.license.apply.data.qeemah = 2;
            SAGIA.license.businessActivities.setBusinessTypeSectionVisible(false);
            basicInformationExtendedSection.hide();
            basicInformationSection.show();
        } else {
            SAGIA.license.apply.data.qeemah = 1;
            SAGIA.license.businessActivities.setBusinessTypeSectionVisible(true);
            basicInformationExtendedSection.show();
            basicInformationSection.hide();
        }
    });

    SAGIA.license.apply.data.basicInformation = {};
    basicInformationSection.find("#basicInformationEntityName").on("blur", function () {
        SAGIA.license.apply.data.basicInformation.entityName = $(this).val();
    });
    basicInformationSection.find("#basicInformationEntityNameArabic").on("blur", function () {
        SAGIA.license.apply.data.basicInformation.entityNameArabic = $(this).val();
    });
    basicInformationSection.find("#basicInformationLegalStatus").on("change", function () {
        SAGIA.license.apply.data.basicInformation.legalStatus = $(this).val();
        SAGIA.license.apply.data.basicInformation.legalStatusText = $(this).find(":selected").text();
    });
    basicInformationSection.find("#basicInformationLabourSize").on("blur", function () {
        SAGIA.license.apply.data.basicInformation.labourSize = $(this).val();
    });
    basicInformationSection.find("#basicInformationCapital").on("blur", function () {
        SAGIA.license.apply.data.basicInformation.capital = $(this).val();
    });
    basicInformationSection.find("#basicInformationCountry").on("change", function () {
        SAGIA.license.apply.data.basicInformation.country = $(this).val();
        SAGIA.license.apply.data.basicInformation.countryName = $(this).find(":selected").text();
    });
    basicInformationSection.find("#basicInformationRegion").on("change", function () {
        SAGIA.license.apply.data.basicInformation.region = $(this).val();
        SAGIA.license.apply.data.basicInformation.regionText = $(this).find(":selected").text();
        loadBasicInformationQeemah2Cities($(this).val());
    });
    basicInformationSection.find("#basicInformationInvestment").on("change", function () {
        SAGIA.license.apply.data.basicInformation.investment = $(this).val();
        SAGIA.license.apply.data.basicInformation.investmentText = $(this).find(":selected").text();
    });
    basicInformationSection.find("#basicInformationCity").on("change", function () {
        SAGIA.license.apply.data.basicInformation.city = $(this).val();
        SAGIA.license.apply.data.basicInformation.cityText = $(this).find(":selected").text();
    });

    SAGIA.license.apply.data.basicInformationExtended = {};
    basicInformationExtendedSection.find("#basicInformationExtendedEntityName").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.entityName = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedEntityNameArabic").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.entityNameArabic = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedLegalStatus").on("change", function () {
        SAGIA.license.apply.data.basicInformationExtended.legalStatus = $(this).val();
        SAGIA.license.apply.data.basicInformationExtended.legalStatusText = $(this).find(":selected").text();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedMultinationalCompany").on("change", function () {
        SAGIA.license.apply.data.basicInformationExtended.multinationalCompany = $(this).val();
        SAGIA.license.apply.data.basicInformationExtended.multinationalCompanyText = $(this).find(":selected").text();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedInvestment").on("change", function () {
        SAGIA.license.apply.data.basicInformationExtended.investment = $(this).val();
        SAGIA.license.apply.data.basicInformationExtended.investmentText = $(this).find(":selected").text();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedCapital").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.capital = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedEmail").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.email = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedCountryCodeForTelephone").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.countryCodeForTelephone = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedTelephone").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.telephone = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedCountryCodeForMobilePhone").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.countryCodeForMobilePhone = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedMobilePhone").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.mobilePhone = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedWebsite").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.website = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedCountry").on("change", function () {
        SAGIA.license.apply.data.basicInformationExtended.country = $(this).val();
        SAGIA.license.apply.data.basicInformationExtended.countryName = $(this).find(":selected").text();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedRegion").on("change", function () {
        SAGIA.license.apply.data.basicInformationExtended.region = $(this).val();
        SAGIA.license.apply.data.basicInformationExtended.regionText = $(this).find(":selected").text();
        loadBasicInformationQeemah1Cities($(this).val());
    });
    basicInformationExtendedSection.find("#basicInformationExtendedCity").on("change", function () {
        SAGIA.license.apply.data.basicInformationExtended.city = $(this).val();
        SAGIA.license.apply.data.basicInformationExtended.cityText = $(this).find(":selected").text();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedAddress").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.address = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedPostalCode").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.postalCode = $(this).val();
    });
    basicInformationExtendedSection.find("#basicInformationExtendedPOBox").on("blur", function () {
        SAGIA.license.apply.data.basicInformationExtended.poBox = $(this).val();
    });

    SAGIA.license.apply.data.notOnStockMarket = {};
    notOnStockMarketSection.find("#isicSection").on("change", function () {
        SAGIA.license.apply.data.notOnStockMarket.isicSection = $(this).val();
        SAGIA.license.apply.data.notOnStockMarket.isicSectionName = $(this).find(":selected").text();
        loadIsicDivisions(notOnStockMarketSection.find("#isicDivision"), $(this).val());
    });
    notOnStockMarketSection.find("#isicDivision").on("change", function () {
        SAGIA.license.apply.data.notOnStockMarket.isicDivison = $(this).val();
        SAGIA.license.apply.data.notOnStockMarket.isicDivisonName = $(this).find(":selected").text();
    });
    notOnStockMarketSection.find("#licenseCondition").on("change", function () {
        SAGIA.license.apply.data.notOnStockMarket.licenseCondition = $(this).val();
    });

    SAGIA.license.apply.data.onStockMarket = {};
    onStockMarketSection.find("#countries").on("change", function () {
        SAGIA.license.apply.data.onStockMarket.country = $(this).val();
        SAGIA.license.apply.data.onStockMarket.countryName = $(this).find(":selected").text();
        if ($(this).val() === "Other") {
            SAGIA.license.apply.data.qeemah = 1;
            SAGIA.license.businessActivities.setBusinessTypeSectionVisible(true);
        } else if ($(this).val()) {
            SAGIA.license.apply.data.qeemah = 2;
            SAGIA.license.businessActivities.setBusinessTypeSectionVisible(false);
        }
    });
    onStockMarketSection.find("#isinCode").on("blur", function () {
        SAGIA.license.apply.data.onStockMarket.isinCode = $(this).val();
    });
    onStockMarketSection.find("#isicSection").on("change", function () {
        SAGIA.license.apply.data.onStockMarket.isicSection = $(this).val();
        SAGIA.license.apply.data.onStockMarket.isicSectionName = $(this).find(":selected").text();
        loadIsicDivisions(onStockMarketSection.find("#isicDivision"), $(this).val());
    });
    onStockMarketSection.find("#isicDivision").on("change", function () {
        SAGIA.license.apply.data.onStockMarket.isicDivision = $(this).val();
        SAGIA.license.apply.data.onStockMarket.isicDivisionName = $(this).find(":selected").text();
    });
    onStockMarketSection.find("#licenseCondition").on("blur", function () {
        SAGIA.license.apply.data.onStockMarket.licenseCondition = $(this).val();
    });

    //product are shown based on license type, not activities.
    // businessActivitiesSection.find("#businessActivitiesModal").on('hidden.bs.modal', function() {
    //     $("#productsSection").show();
    // });

    $(document).on("change", ".activityAttachments input[type='file']", function() {
        SAGIA.license.apply.data.activityAttachments = SAGIA.license.apply.data.activityAttachments || [];
        var currentElement = $(this);
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            SAGIA.license.apply.data.activityAttachments.push({
                activityId : currentElement.parents("tr").attr("data-activity-Id"),
                sectionId : currentElement.parents("tr").attr("data-section-id"),
                divisionId : currentElement.parents("tr").attr("data-division-id"),
                groupId : currentElement.parents("tr").attr("data-group-id"),
                classId : currentElement.parents("tr").attr("data-class-id"),
                branchId : currentElement.parents("tr").attr("data-branch-id"),
                fileId : currentElement.attr("data-file-id"),
                fileName : currentElement.val(),
                fileMimeType : event.target._TYPE,
                content : removeBase64prefix(event.target._TYPE, event.target.result)
            });
        };
        fileReader._TYPE = currentElement[0].files[0].type;
        fileReader.readAsDataURL(currentElement[0].files[0]);
    });
    var removeAttachmentsBy = function(sectionId, divisionId, groupId, classId, fileId) { //fileId is optional
        var attachments = SAGIA.license.apply.data.activityAttachments;
        var index = attachments.length;
        while (index--) {
            if(attachments.hasOwnProperty(index)) {
                var attachment = attachments[index];
                if(attachment.sectionId === sectionId &&
                        attachment.divisionId === divisionId &&
                        attachment.groupId === groupId &&
                        attachment.classId === classId &&
                        (!fileId || attachment.fileId === fileId)) {
                    attachments.splice(index, 1);
                }
            }
        }
    };
    $(document).on('click', '.activityAttachments .js-inputFile-reset', function() {
        var parentTr = $(this).parents("tr");
        removeAttachmentsBy(parentTr.attr("data-section-id"), parentTr.attr("data-division-id"), parentTr.attr("data-group-id"), parentTr.attr("data-class-id"),
            $(this).parents(".formInputFile").find(".js-inputFile").attr("data-file-id"));
    });

    $(document).on("hidden.bs.modal", "#businessActivitiesSection #businessActivitiesModal", function() {
        SAGIA.license.apply.data.businessActivities = SAGIA.license.businessActivities;
    });

    $(".js-inputFile").on('change', function(event) {
        if(!event.target.files[0].type.match('application/pdf')) {
            SAGIA.showError(getI18nText("validation.upload.pdf"));
            $(this).parent().find('.js-inputFile-reset').click();
        }
    });
};

SAGIA.license.apply.handleProductsSearch = function () {
    var selectElement = $("#productId");

    // verifying Select2 has been initialized
    if (selectElement.hasClass("select2-hidden-accessible")) {
        selectElement.empty();
    }

    selectElement.select2({
        dropdownCssClass: 'select2-dropdown_oneColumn select2-dropdown_oneColumn_hasFilter',
        ajax: {
            type: "POST",
            contentType: "application/json",
            url: ACC.config.encodedContextPath + controllerUrl + "/products",
            displayErrorMessageInModal: false,
            data: function (params) {
                var filterForProducts = {};
                filterForProducts['batchNo'] = params.page;
                filterForProducts['userInput'] = params.term;
                return JSON.stringify(filterForProducts);
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                var parsedData = [];
                if (data.length) {
                    parsedData = $.map((JSON.parse(data)), function (obj) {
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
        templateResult: function (repo) {
            if (repo.loading) {
                return repo.text;
            }
            return repo.ProductID + ' ' + repo.ProdDesc;
        },
        templateSelection: function (repo) {
            return repo.ProdDesc + ' ' + repo.ProductID;
        }
    });

    selectElement.on('select2:select', function (e) {
        var data = e.params.data;
        var productsTable = $('#productsId');
        var productAlreadyAdded = false;
        $('.productTemplate', productsTable).each(function (index, elem) {
            var rowElem = $(elem);
            if (rowElem.find('td:first-child').text() === data.ProductID) productAlreadyAdded = true;
        });
        if (productAlreadyAdded) {
            SAGIA.showError(getI18nText("license.apply.product.already.added"));
            clearProductForm();
            SAGIA.formElements.placeholderPolyfill();
            return;
        }

        $("#productDescriptionId").val(data.ProdDesc);
        $("#productQuantityId").val(data.Quantity);
        var productUnitId = $('#productUnitId');
        productUnitId.empty().append(new Option('', '', false, false));
        productUnitId.append(new Option(data.Unit, data.Unit, false, false));
        productUnitId.val(data.Unit);
        productUnitId.trigger('change.select2');
        SAGIA.formElements.placeholderPolyfill();
    });

    selectElement.on('select2:closing', function (e) {
        if (!$('.spinnerContainer').hasClass('hidden')) {
            e.preventDefault();
            e.stopPropagation();
        }
    });


    selectElement.on('select2:open', function (e) {
        var $search = selectElement.data('select2').dropdown.$search || selectElement.data('select2').selection.$search;
        $search.val(' ');
        $search.trigger('input');
    });

    var clearProductForm = function () {
        var $productId = $('#productId');
        $productId.val('').attr('disabled', false);
        $productId.trigger('change.select2');
        $('#productDescriptionId').val('');
        $('#productQuantityId').val('');

        var $productUnit = $("#productUnitId");
        $productUnit.next().removeClass("select2Container_selected");
        $productUnit.val('').trigger('change');

        $('.saveProductButton').removeAttr('id');
        removeErrorIfExists();
    };
    var removeErrorIfExists = function () {
        $('.form-group').each(function () {
            if ($(this).hasClass('has-error')) {
                $(this).removeClass('has-error');
            }

            if ($(this).find('.help-block:first').length) {
                $(this).find('.help-block:first').text('');
            }

            if ($(this).closest('.formSelectBox').length) {
                $(this).closest('.formSelectBox').find('.help-block').text('');
            }

            if ($(this).closest('.formInputBox').length) {
                $(this).closest('.formInputBox').find('.help-block').text('');
            }
        });
    };


}

SAGIA.license.apply.onChangeTabToEntityInformation = function () {
    $(document).scrollTop(0);
};

function makeRedBorderFor(controlId) {
	$('#' + controlId).css({"border-color": "#FF0000", 
       "border-width":"1px", 
       "border-style":"solid"
    });
}

function areAllRadioButtonsChecked() {
	var hasSagiaLicenseNO = $('#hasSagiaLicenseNO');
	var hasSagiaLicenseYES = $('#hasSagiaLicenseYES');
	var hasGCCNationalityYES = $('#hasGCCNationalityYES');
	var hasGCCNationalityNO = $('#hasGCCNationalityNO');
	var hasShareholdersTypePersonNO = $('#hasShareholdersTypePersonNO');
	var hasShareholdersTypePersonYES = $('#hasShareholdersTypePersonYES');
	var hasAdvanceLicenseNrNO = $('#hasAdvanceLicenseNrNO');
	var hasAdvanceLicenseNrYES = $('#hasAdvanceLicenseNrYES');
	var hasStockListingYES = $('#hasStockListingYES');
	var hasStockListingNO = $('#hasStockListingNO');

    if(hasGCCNationalityNO.is(':checked') === false && hasGCCNationalityYES.is(':checked') === false) {
        makeRedBorderFor("hasGCCNationalityNOLabel");
        makeRedBorderFor("hasGCCNationalityYESLabel");
        return false;
    }

	if(hasSagiaLicenseNO.is(':checked') === false && hasSagiaLicenseYES.is(':checked') === false) {
		makeRedBorderFor("hasSagiaLicenseNOLabel");
		makeRedBorderFor("hasSagiaLicenseYESLabel");
		return false;
	}
	if(hasSagiaLicenseYES.is(':checked') === true) {
		return true;
	}
	if(hasSagiaLicenseNO.is(':checked') === true 
			&& hasShareholdersTypePersonNO.is(':checked') === false
			&& hasShareholdersTypePersonYES.is(':checked') === false) {
		makeRedBorderFor("hasShareholdersTypePersonNOLabel");
		makeRedBorderFor("hasShareholdersTypePersonYESLabel");
		return false;
	}
	if(hasSagiaLicenseNO.is(':checked') === true
        && hasAdvanceLicenseNrNO.is(":visible") === true
        && hasAdvanceLicenseNrNO.is(':checked') === false
        && hasAdvanceLicenseNrYES.is(':checked') === false) {
		makeRedBorderFor("hasAdvanceLicenseNrNOLabel");
		makeRedBorderFor("hasAdvanceLicenseNrYESLabel");
		return false;
	}

	if(hasSagiaLicenseNO.is(':checked') === true
        && hasShareholdersTypePersonNO.is(':checked') === true
        && hasAdvanceLicenseNrNO.is(':checked') === true
        && hasStockListingYES.is(':checked')=== false
        && hasStockListingNO.is(':checked') === false) {
        makeRedBorderFor("hasStockListingYESLabel");
        makeRedBorderFor("hasStockListingNOLabel");
        return false;
    }
	return true;
}

function validateFirstTabRadioButtons() {
	
	if(areAllRadioButtonsChecked() === false) {
		return false;
	 }
	if($('#licenseTypes').val() === "") {
		$('#licenseTypes').parent().addClass('has-error');
		return false;
	}
	
	
	return true;
} 

function validateTemporaryLicense() {
    
	var temporaryLicenseTextBox  = $("#temporaryLicenseTextBox");
	
    if(typeof temporaryLicenseTextBox.attr("style") === 'undefined' || temporaryLicenseTextBox.attr("style").indexOf("display: none") === -1) {
    	
    	var temporaryLicenseTextBoxContent = $("#temporaryLicenseTextBoxContent");
    	if(!temporaryLicenseTextBoxContent.val()) {
    		temporaryLicenseTextBoxContent.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.purpose.temporary.certificate"));           
    		return false;
        } 
    	
    } 
	
	return true;
}

$(document).on("click", "#entityForm .entity-info-submit", function () {
    var isValid = true;
    var promises = [];
//    isValid = validateFirstTabRadioButtons(); RFC# 1800000394: ASHAIK

    if($('#licenseTypes').val() === "") {
        isValid = false;
        $('#licenseTypes').parent().addClass('has-error');
    }
    if($('#licenseYear').val() === "") {
        isValid = false;
        $('#licenseYear').parent().addClass('has-error').siblings().find(".help-block").text(getI18nText("validation.entity.license.year"));
    }


    var temporaryLicenseTextBox  = $("#temporaryLicenseTextBox");
    if(typeof temporaryLicenseTextBox.attr("style") === 'undefined' || temporaryLicenseTextBox.attr("style").indexOf("display: none") === -1) {

        var temporaryLicenseTextBoxContent = $("#temporaryLicenseTextBoxContent");
        if(!temporaryLicenseTextBoxContent.val()) {
            temporaryLicenseTextBoxContent.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.purpose.temporary.certificate"));
            isValid = false;
        } else {
            temporaryLicenseTextBoxContent.closest('.form-group').removeClass('has-error').parent().find(".help-block").text("");
        }

    }


    var basicInformationExtendedSection = $("#basicInformationExtendedSection");
    if(typeof basicInformationExtendedSection.attr("style") === 'undefined' || basicInformationExtendedSection.attr("style").indexOf("display: none") === -1) {
        promises.push(validateLicenseData('#basicInformationExtendedSection', 'organization-information', SAGIA.license.apply.data.basicInformationExtended, function() {
        }, undefined, function() {
            var multinationalCompany = basicInformationExtendedSection.find("#basicInformationExtendedMultinationalCompany");
            if(!multinationalCompany.val()) {
                multinationalCompany.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.basicinformation.multinationalCompany"));
                isValid = false;
            } else {
                multinationalCompany.closest('.form-group').removeClass('has-error').parent().find(".help-block").text("");
            }
            var country = basicInformationExtendedSection.find("#basicInformationExtendedCountry");
            if(!country.val()) {
                country.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.basicinformation.country"));
                isValid = false;
            } else {
                country.closest('.form-group').removeClass('has-error').parent().find(".help-block").text("");
            }
        }));
    }

    if(typeof $("#attachmentSection").attr("style") === 'undefined' || $("#attachmentSection").attr("style").indexOf("display: none") === -1) {
        var boardResolutionFile = $("#boardResolutionFile");
        boardResolutionFile.closest('.form-group').removeClass("has-error").parent().find(".help-block").text('');
        if(!boardResolutionFile.val()) {
            boardResolutionFile.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.attachment"));
            isValid = false;
        }

        var letterOfSupportFile = $("#letterOfSupportFile");
        letterOfSupportFile.closest('.form-group').removeClass("has-error").parent().find(".help-block").text('');
        if(!letterOfSupportFile.val()) {
            letterOfSupportFile.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.attachment"));
            isValid = false;
        }
    }

    if(typeof $("#onStockMarketSection").attr("style") === 'undefined' ||  $("#onStockMarketSection").attr("style").indexOf("display: none") === -1){
        promises.push(validateLicenseData('#onStockMarketSection', 'has-stock-listed-info', SAGIA.license.apply.data.onStockMarket, function() {
        }))
    }

    if(typeof $("#basicInformationSection").attr("style") === 'undefined' || $("#basicInformationSection").attr("style").indexOf("display: none") === -1) {
        promises.push(validateLicenseData('#basicInformationSection', 'basic-organization-information', SAGIA.license.apply.data.basicInformation, function() {
        }));
    }
    if(typeof $("#technicalAndFinancialCriteriaSection").attr("style") === 'undefined' || $("#technicalAndFinancialCriteriaSection").attr("style").indexOf("display: none") === -1) {
        $('#technicalAndFinancialCriteriaSection .has-error').text('');
        promises.push(validateLicenseData(
            '#technicalAndFinancialCriteriaSection',
            'financial-questions',
            SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions,
            function(){
            },
            function(xhr, status, error){
                var formErrors = xhr.responseJSON.formErrors;
                // $('#technicalAndFinancialCriteriaSection .form-group').removeClass('has-error');
                $.each(formErrors, function(key, errorMessage) {
                    // $('#technicalAndFinancialCriteriaSection #' + key + "YES").closest('.form-group').addClass('has-error');
                    $('#technicalAndFinancialCriteriaSection #' + key + "YES").closest('.form-group').siblings('.help-block').text(errorMessage);
                });
            })
        );
    }

    $.when.apply($, promises).done(function(results) {
        var scrollTo = null;

        if (typeof $("#businessActivitiesSection").attr("style") === 'undefined' ||$("#businessActivitiesSection").attr("style").indexOf("display: none") === -1) {
            if(SAGIA.license.businessActivities) {
                if(SAGIA.license.businessActivities.selectedActivities.length <= 0) {
                    isValid = false;
                    $('#noBusinessActivitiesSelected .help-block').text(getI18nText("business.activities.mandatory")).css({"color": "red"});
                    scrollTo = $("#businessActivitiesSection").offset().top + 130;
                } else {
                    $("#businessActivitiesSection #businessActivitiesTable .js-inputFile").each(function() {
                        if(!$(this).val()) {
                            $(this).closest(".form-group").addClass("has-error").parent().find(".help-block").text(getI18nText("validation.attachment"));
                            isValid = false;
                        } else {
                            $(this).closest(".form-group").removeClass("has-error").parent().find(".help-block").text('');
                        }
                    });
                }
            }
        } else {
            $('')
        }

        if(typeof $("#productsSection").attr("style") === 'undefined' || $("#productsSection").attr("style").indexOf("display: none") === -1) {
            if(SAGIA.license.apply.data.entityInformation.products && SAGIA.license.apply.data.entityInformation.products.length <= 0) {
                if(isValid) {
                    scrollTo = $("#productsSection").offset().top + 130;
                }
                $('#emptyProductSection .help-block').text(getI18nText("products.mandatory")).css({"color": "red"});
                isValid = false;
            }
        }

        if (isValid) {
            if (SAGIA.license.businessActivities.newActivities.length > 0) {
                $.each(SAGIA.license.businessActivities.newActivities, function (index, value) {
                    var element = $('<input/>').prop('type', 'hidden').prop('name', 'business-activities[]').val(JSON.stringify(value));
                    // $('#businessActivitiesJsonInputs').append(element);
                });
            }

            $('#entityForm').submit();

        }
        if(scrollTo != null) {
            $([document.documentElement, document.body]).animate({
                scrollTop: scrollTo
            }, 500);
        }
    });
});

$(document).on("click", "#entityInformationNextButton", function () {
    var isValid = true;
    var promises = [];
//    isValid = validateFirstTabRadioButtons(); RFC# 1800000394: ASHAIK 

    if($('#licenseTypes').val() === "") {
    	isValid = false;
		$('#licenseTypes').parent().addClass('has-error');
	}
    if($('#licenseYear').val() === "") {
    	isValid = false;
		$('#licenseYear').parent().addClass('has-error').siblings().find(".help-block").text(getI18nText("validation.entity.license.year"));
	}

    
    var temporaryLicenseTextBox  = $("#temporaryLicenseTextBox");
    if(typeof temporaryLicenseTextBox.attr("style") === 'undefined' || temporaryLicenseTextBox.attr("style").indexOf("display: none") === -1) {
    	
    	var temporaryLicenseTextBoxContent = $("#temporaryLicenseTextBoxContent");
    	if(!temporaryLicenseTextBoxContent.val()) {
    		temporaryLicenseTextBoxContent.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.purpose.temporary.certificate"));
            isValid = false;
        } else {
        	temporaryLicenseTextBoxContent.closest('.form-group').removeClass('has-error').parent().find(".help-block").text("");
        }
    	
    }
    
    
    var basicInformationExtendedSection = $("#basicInformationExtendedSection");
    if(typeof basicInformationExtendedSection.attr("style") === 'undefined' || basicInformationExtendedSection.attr("style").indexOf("display: none") === -1) {
        promises.push(validateLicenseData('#basicInformationExtendedSection', 'organization-information', SAGIA.license.apply.data.basicInformationExtended, function() {
        }, undefined, function() {
            var multinationalCompany = basicInformationExtendedSection.find("#basicInformationExtendedMultinationalCompany");
            if(!multinationalCompany.val()) {
                multinationalCompany.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.basicinformation.multinationalCompany"));
                isValid = false;
            } else {
                multinationalCompany.closest('.form-group').removeClass('has-error').parent().find(".help-block").text("");
            }
            var country = basicInformationExtendedSection.find("#basicInformationExtendedCountry");
            if(!country.val()) {
                country.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.basicinformation.country"));
                isValid = false;
            } else {
                country.closest('.form-group').removeClass('has-error').parent().find(".help-block").text("");
            }
        }));
    }

    if(typeof $("#attachmentSection").attr("style") === 'undefined' || $("#attachmentSection").attr("style").indexOf("display: none") === -1) {
        var boardResolutionFile = $("#boardResolutionFile");
        boardResolutionFile.closest('.form-group').removeClass("has-error").parent().find(".help-block").text('');
        if(!boardResolutionFile.val()) {
            boardResolutionFile.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.attachment"));
            isValid = false;
        }

        var letterOfSupportFile = $("#letterOfSupportFile");
        letterOfSupportFile.closest('.form-group').removeClass("has-error").parent().find(".help-block").text('');
        if(!letterOfSupportFile.val()) {
            letterOfSupportFile.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.attachment"));
            isValid = false;
        }
    }

    if(typeof $("#onStockMarketSection").attr("style") === 'undefined' ||  $("#onStockMarketSection").attr("style").indexOf("display: none") === -1){
        promises.push(validateLicenseData('#onStockMarketSection', 'has-stock-listed-info', SAGIA.license.apply.data.onStockMarket, function() {
        }))
    }

    if(typeof $("#basicInformationSection").attr("style") === 'undefined' || $("#basicInformationSection").attr("style").indexOf("display: none") === -1) {
        promises.push(validateLicenseData('#basicInformationSection', 'basic-organization-information', SAGIA.license.apply.data.basicInformation, function() {
        }));
    }
    if(typeof $("#technicalAndFinancialCriteriaSection").attr("style") === 'undefined' || $("#technicalAndFinancialCriteriaSection").attr("style").indexOf("display: none") === -1) {
        $('#technicalAndFinancialCriteriaSection .has-error').text('');
        promises.push(validateLicenseData(
            '#technicalAndFinancialCriteriaSection',
            'financial-questions',
            SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions,
            function(){
            },
            function(xhr, status, error){
                var formErrors = xhr.responseJSON.formErrors;
                // $('#technicalAndFinancialCriteriaSection .form-group').removeClass('has-error');
                $.each(formErrors, function(key, errorMessage) {
                    // $('#technicalAndFinancialCriteriaSection #' + key + "YES").closest('.form-group').addClass('has-error');
                    $('#technicalAndFinancialCriteriaSection #' + key + "YES").closest('.form-group').siblings('.help-block').text(errorMessage);
                });
            })
        );
    }

    $.when.apply($, promises).done(function(results) {
        var scrollTo = null;

        if (typeof $("#businessActivitiesSection").attr("style") === 'undefined' ||$("#businessActivitiesSection").attr("style").indexOf("display: none") === -1) {
            if(SAGIA.license.businessActivities) {
                if(SAGIA.license.businessActivities.selectedActivities.length <= 0) {
                    isValid = false;
                    $('#noBusinessActivitiesSelected .help-block').text(getI18nText("business.activities.mandatory")).css({"color": "red"});
                    scrollTo = $("#businessActivitiesSection").offset().top + 130;
                } else {
                    $("#businessActivitiesSection #businessActivitiesTable .js-inputFile").each(function() {
                        if(!$(this).val()) {
                            $(this).closest(".form-group").addClass("has-error").parent().find(".help-block").text(getI18nText("validation.attachment"));
                            isValid = false;
                        } else {
                            $(this).closest(".form-group").removeClass("has-error").parent().find(".help-block").text('');
                        }
                    });
                }
            }
        }

        if(typeof $("#productsSection").attr("style") === 'undefined' || $("#productsSection").attr("style").indexOf("display: none") === -1) {
            if(SAGIA.license.apply.data.entityInformation.products && SAGIA.license.apply.data.entityInformation.products.length <= 0) {
                if(isValid) {
                    scrollTo = $("#productsSection").offset().top + 130;
                }
                $('#emptyProductSection .help-block').text(getI18nText("products.mandatory")).css({"color": "red"});
                isValid = false;
            }
        }

        if (isValid) {
            $('#noBusinessActivitiesSelected .help-block').text("");
            $('#emptyProductSection .help-block').text("");
            SAGIA.license.apply.tabs.showAccessibleTabSelector("#shareholdersTab");
            SAGIA.license.apply.onChangeTabToShareholdersTab();
        }
        if(scrollTo != null) {
            $([document.documentElement, document.body]).animate({
                scrollTop: scrollTo
            }, 500);
        }
    });
    
});

$("#licenseYearSection").find("#licenseYear").on("change", function () {
    SAGIA.license.apply.data.licenseYear = $(this).val() + "";
    if(SAGIA.license.apply.data.isEntrepreneur){
    	$("#licenseYearSection").find("#licenseYear").prop("disabled", true);
    }

});

$("#basicInformationExtendedCountryCodeForMobilePhone").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#basicInformationExtendedMobilePhone").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#basicInformationExtendedCountryCodeForTelephone").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#basicInformationExtendedTelephone").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#basicInformationExtendedPOBox").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#basicInformationExtendedPostalCode").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});

$('#basicInformationExtendedEmail').focusout(function() {
   	var emailValue = $('#basicInformationExtendedEmail').val();

   	if (!validateEmail(emailValue)) {
   	 	$("#emailValidation").text(getI18nText("validation.basicinformation.email"));
   	 	$("#entityInformationNextButton").attr("disabled", true);
   	}
   	else {
   		$("#entityInformationNextButton").attr("disabled", false);	
   	}
   });

   $('#basicInformationExtendedEmail').focusin(function() {
   	$("#emailValidation").text("");
   });
   
   function validateEmail(emailValue) {
	 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	 return filter.test(emailValue);
	}
