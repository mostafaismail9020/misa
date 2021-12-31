var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = SAGIA.license.apply || {};

SAGIA.license.apply.onChangeTabToReviewTab = function() {
    $(document).scrollTop(0);

    var reviewHasSagiaLicenseSection = $("#reviewHasSagiaLicenseSection");
    reviewHasSagiaLicenseSection.find(".iconElement").hide();

    var reviewAdvanceLicenseSection = $("#reviewAdvanceLicenseSection");
    var advanceLicenseNumberSection = reviewAdvanceLicenseSection.find("#advanceLicenseNumberSection");
    reviewAdvanceLicenseSection.find(".iconElement").hide();
    reviewAdvanceLicenseSection.hide();

    var reviewHasShareholdersTypePerson = $("#reviewHasShareholdersTypePerson");
    reviewHasShareholdersTypePerson.find(".iconElement").hide();
    reviewHasShareholdersTypePerson.hide();
    
    function showDelegateDetails(newShareholder) {
    	var delegateInformationModal=$("#delegateInformationModal");
    	delegateInformationModal.find("#delegateNameEnglish").text(newShareholder.delegateFullNameEnglish ? newShareholder.delegateFullNameEnglish : "");
    	var arabicName=newShareholder.delegateFirstNameArabic+" "+newShareholder.delegateLastNameArabic;
    	delegateInformationModal.find("#delegateNameArabic").text(arabicName ? arabicName : "");
    	delegateInformationModal.find("#delegateGender").text(newShareholder.delegateGender ? newShareholder.delegateGender : "");
    	delegateInformationModal.find("#delegateCountry").text(newShareholder.delegateCountryText ? newShareholder.delegateCountryText : "");
    	delegateInformationModal.find("#delegateNationality").text(newShareholder.delegateNationalityText ? newShareholder.delegateNationalityText : "");
    	delegateInformationModal.find("#delegateDateOfBirth").text(newShareholder.delegateDateofBirth ? newShareholder.delegateDateofBirth : "");
    	var delegateTelephone=newShareholder.delegateCountryCodeForTelephone+" "+newShareholder.delegateTelephone;
    	delegateInformationModal.find("#delegateTelephone").text(delegateTelephone ? delegateTelephone : "");
    	var delegateMobile=newShareholder.delegateCountryCodeForMobile+" "+newShareholder.delegateMobile;
    	delegateInformationModal.find("#delegateMobileNumber").text(delegateMobile ? delegateMobile : "");
    	delegateInformationModal.find("#delegateEmail").text(newShareholder.delegateEmail ? newShareholder.delegateEmail : "");
    	delegateInformationModal.find("#delegatePobox").text(newShareholder.delegatePOBox ? newShareholder.delegatePOBox : "");
    	delegateInformationModal.find("#delegatePostalcode").text(newShareholder.delegatePostalCode ? newShareholder.delegatePostalCode : "");
    	delegateInformationModal.find("#delegateIdType").text(newShareholder.idType ? newShareholder.idType : "");
    	delegateInformationModal.find("#delegateIdNumber").text(newShareholder.idNumber ? newShareholder.idNumber : "");
    	delegateInformationModal.find("#delegateIdIssueDate").text(newShareholder.delegateIssueDate ? newShareholder.delegateIssueDate : "");
    	delegateInformationModal.find("#delegateIdExpiryDate").text(newShareholder.delegateExpiryDate ? newShareholder.delegateExpiryDate : "");
    	delegateInformationModal.modal('show');
    }

  
    if (SAGIA.license.apply.data.hasSagiaLicense) {
//       reviewHasSagiaLicenseSection.find(".iconElement_ynIcon_yes").show(); 
            reviewHasSagiaLicenseSection.hide(); //ASHAIK 27032019
            
    } else {
     //   reviewHasSagiaLicenseSection.find(".iconElement_ynIcon_no").show();
        reviewHasSagiaLicenseSection.hide(); //ASHAIK 27032019
        reviewAdvanceLicenseSection.hide();
        advanceLicenseNumberSection.hide();
        if(typeof advanceLicenseNumberSection.attr("style") === 'undefined' || advanceLicenseNumberSection.attr("style").indexOf("display: none") === -1) {
            reviewAdvanceLicenseSection.show();
            var advanceLicenseNr = SAGIA.license.apply.data.advanceLicenseNr;
            if (advanceLicenseNr) {
                reviewAdvanceLicenseSection.find(".iconElement_ynIcon_yes").show();
                advanceLicenseNumberSection.show();
                advanceLicenseNumberSection.find("#advanceLicenseNumber").text(advanceLicenseNr);
            } else {
                reviewAdvanceLicenseSection.find(".iconElement_ynIcon_no").show();
            }
        }
        reviewHasShareholdersTypePerson.hide();
        var hasShareholdersTypePerson = $("#hasShareholdersTypePerson");
        if(typeof hasShareholdersTypePerson.attr("style") === 'undefined' || hasShareholdersTypePerson.attr("style").indexOf("display: none") === -1) {
            if(SAGIA.license.apply.data.hasShareholdersTypePerson !== undefined && SAGIA.license.apply.data.hasShareholdersTypePerson !== null) { //has no value whatsoever
                reviewHasShareholdersTypePerson.show();
                if (SAGIA.license.apply.data.hasShareholdersTypePerson) {
                    reviewHasShareholdersTypePerson.find(".iconElement_ynIcon_yes").show();
                } else {
                    reviewHasShareholdersTypePerson.find(".iconElement_ynIcon_no").show();
                }
            }
        }
    }

    

    var licenseTypeSection = $("#licenseTypeSection");
    var reviewLicenseTypeSection = $("#reviewLicenseTypeSection");
    reviewLicenseTypeSection.hide();
    if(typeof licenseTypeSection.attr("style") === 'undefined' || licenseTypeSection.attr("style").indexOf("display: none") === -1) {
        if (SAGIA.license.apply.data.licenseType) {
            reviewLicenseTypeSection.show();
            reviewLicenseTypeSection.find("dd").text(
                SAGIA.license.apply.data.licenseTypeName ? SAGIA.license.apply.data.licenseTypeName : "");
        }
    }
    var reviewLicenseYearSection = $("#reviewLicenseYearSection");
    if(SAGIA.license.apply.data.licenseYear) {
    	reviewLicenseYearSection.show();
    	var licenseYearText = $("#licenseYear option[value='"+SAGIA.license.apply.data.licenseYear+"']").text();
    	reviewLicenseYearSection.find("dd").text(licenseYearText);
    }
  //  var licenseTypeSection = $("#licenseTypeSection");
    var reviewAdvanceLicenseNrSection = $("#reviewAdvanceLicenseNrSection");
   
    reviewAdvanceLicenseNrSection.hide();
        if (SAGIA.license.apply.data.hasAdvanceLicenseNr == true) {
        	reviewAdvanceLicenseNrSection.show();
        	reviewAdvanceLicenseNrSection.find("dd").text(
                SAGIA.license.apply.data.advanceLicenseNr ? SAGIA.license.apply.data.advanceLicenseNr : "");
        }
    
    
    

    var licenseInformationSection = $("#licenseInformationSection");
    var reviewLicenseInformationSection = $("#reviewLicenseInformationSection");
    reviewLicenseInformationSection.hide();
    if(typeof licenseInformationSection.attr("style") === 'undefined' || licenseInformationSection.attr("style").indexOf("display: none") === -1) {
        if(SAGIA.license.apply.data.isEntrepreneur !== undefined && SAGIA.license.apply.data.isEntrepreneur !== null) { //has no value whatsoever
            if (SAGIA.license.apply.data.isEntrepreneur) {
                reviewLicenseInformationSection.find(".iconElement_ynIcon_yes").show();
            } else {
                reviewLicenseInformationSection.find(".iconElement_ynIcon_no").show();
            }
        }
    }

    var stockMarketSection = $("#stockMarketSection");
    var reviewStockMarketSection = $("#reviewStockMarketSection");
    reviewStockMarketSection.hide();
    reviewStockMarketSection.find(".iconElement").hide();
    if(typeof stockMarketSection.attr("style") === 'undefined' || stockMarketSection.attr("style").indexOf("display: none") === -1) {
        if (SAGIA.license.apply.data.hasStockListing !== undefined && SAGIA.license.apply.data.hasStockListing !== null) { //has no value whatsoever
            reviewStockMarketSection.show();
            if (SAGIA.license.apply.data.hasStockListing) {
                reviewStockMarketSection.find(".iconElement_ynIcon_yes").show();
            } else {
                reviewStockMarketSection.find(".iconElement_ynIcon_no").show();
            }
        }
    }

    var onStockMarketSection = $("#onStockMarketSection");
    var reviewOnStockMarketSection = $("#reviewOnStockMarketSection");
    reviewOnStockMarketSection.hide();
    if(typeof onStockMarketSection.attr("style") === 'undefined' || onStockMarketSection.attr("style").indexOf("display: none") === -1) {
        reviewOnStockMarketSection.show();
        reviewOnStockMarketSection.find("#reviewOnStockMarketSectionCountry").text(
            SAGIA.license.apply.data.onStockMarket.countryName ? SAGIA.license.apply.data.onStockMarket.countryName : "");
        reviewOnStockMarketSection.find("#reviewOnStockMarketSectionISINCode").text(
            SAGIA.license.apply.data.onStockMarket.isinCode ? SAGIA.license.apply.data.onStockMarket.isinCode : "");
    }

    var technicalAndFinancialCriteriaSection = $("#technicalAndFinancialCriteriaSection");
    var reviewStockMarketQuestionsSection = $("#reviewStockMarketQuestionsSection");
    var reviewQuestions = reviewStockMarketQuestionsSection.find(".ynList").empty();
    if(typeof technicalAndFinancialCriteriaSection.attr("style") === 'undefined' || technicalAndFinancialCriteriaSection.attr("style").indexOf("display: none") === -1) {
        for (var indexQuestion in SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions) {
            if (SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions.hasOwnProperty(indexQuestion)) {
                var questionObject = SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions[indexQuestion];
                if(questionObject.answer !== "") {
                    reviewQuestions.append("" +
                        "<li class=\"ynList-item\">\n" +
                        (questionObject.answer ?
                                "<span class=\"iconElement iconElement_ynIcon iconElement_ynIcon_yes\">\n" +
                                "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\"\n" +
                                "\t width=\"44px\" height=\"17px\" viewBox=\"-0.5 0 44 17\" enable-background=\"new -0.5 0 44 17\" xml:space=\"preserve\">\n" +
                                "<g>\n" +
                                "\t<path fill=\"none\" stroke=\"#5CC83B\" d=\"M8.5,0.5h26c4.418,0,8,3.582,8,8l0,0c0,4.418-3.582,8-8,8h-26c-4.418,0-8-3.582-8-8l0,0\n" +
                                "\t\tC0.5,4.082,4.082,0.5,8.5,0.5z\"/>\n" +
                                "\t<path fill=\"#5CC83B\" d=\"M16.719,9.295V13h-1.255V9.295l-3.412-5.61h1.105c0.113,0,0.201,0.029,0.267,0.085\n" +
                                "\t\tc0.067,0.06,0.124,0.13,0.168,0.208l2.133,3.62c0.086,0.152,0.159,0.295,0.217,0.43l0.16,0.396l0.155-0.403\n" +
                                "\t\tc0.057-0.134,0.128-0.275,0.215-0.423l2.125-3.62c0.042-0.074,0.097-0.141,0.16-0.198c0.072-0.064,0.167-0.098,0.263-0.094h1.118\n" +
                                "\t\tL16.719,9.295z M22.691,6.311c0.396,0,0.76,0.067,1.094,0.199c0.333,0.132,0.621,0.323,0.863,0.572\n" +
                                "\t\tc0.244,0.249,0.433,0.557,0.569,0.923c0.136,0.366,0.204,0.783,0.204,1.251c0,0.182-0.02,0.303-0.058,0.364\n" +
                                "\t\tc-0.039,0.06-0.113,0.091-0.222,0.091h-4.38c0.009,0.416,0.065,0.778,0.169,1.085c0.104,0.308,0.247,0.565,0.429,0.771\n" +
                                "\t\tc0.182,0.207,0.399,0.359,0.65,0.461c0.251,0.103,0.533,0.154,0.846,0.154c0.289,0,0.539-0.035,0.75-0.101\n" +
                                "\t\tc0.326-0.101,0.639-0.247,0.924-0.437c0.102-0.066,0.189-0.101,0.263-0.101c0.095,0,0.169,0.037,0.222,0.11l0.324,0.423\n" +
                                "\t\tc-0.146,0.177-0.32,0.329-0.514,0.452c-0.199,0.128-0.412,0.232-0.641,0.314c-0.458,0.162-0.94,0.246-1.427,0.247\n" +
                                "\t\tc-0.455,0-0.874-0.077-1.257-0.229c-0.377-0.15-0.717-0.381-0.995-0.678c-0.292-0.317-0.514-0.692-0.653-1.101\n" +
                                "\t\tc-0.156-0.438-0.234-0.94-0.234-1.508c0-0.46,0.07-0.889,0.211-1.287c0.141-0.4,0.344-0.745,0.608-1.037\n" +
                                "\t\tc0.264-0.293,0.587-0.522,0.968-0.69C21.812,6.389,22.25,6.304,22.691,6.311L22.691,6.311z M22.718,7.163\n" +
                                "\t\tc-0.559,0-0.998,0.161-1.319,0.484c-0.32,0.323-0.52,0.77-0.598,1.342h3.582c0-0.268-0.037-0.514-0.111-0.737\n" +
                                "\t\tc-0.067-0.213-0.178-0.41-0.324-0.579c-0.145-0.163-0.322-0.292-0.523-0.377C23.2,7.204,22.96,7.159,22.718,7.163z M30.668,7.501\n" +
                                "\t\tc-0.045,0.091-0.139,0.147-0.24,0.143c-0.078-0.004-0.155-0.029-0.222-0.072c-0.099-0.056-0.2-0.109-0.302-0.159\n" +
                                "\t\tc-0.137-0.065-0.279-0.12-0.426-0.162c-0.189-0.053-0.387-0.079-0.584-0.075c-0.196,0-0.371,0.025-0.527,0.075\n" +
                                "\t\ts-0.29,0.118-0.4,0.204c-0.105,0.081-0.191,0.185-0.253,0.303c-0.059,0.116-0.089,0.244-0.088,0.374\n" +
                                "\t\tc0,0.169,0.049,0.31,0.146,0.422c0.099,0.113,0.228,0.21,0.388,0.293c0.159,0.082,0.342,0.154,0.546,0.217l0.627,0.201\n" +
                                "\t\tc0.215,0.071,0.424,0.15,0.627,0.237c0.204,0.087,0.387,0.195,0.547,0.325s0.289,0.29,0.387,0.479\n" +
                                "\t\tc0.098,0.188,0.146,0.414,0.146,0.678c0,0.304-0.054,0.584-0.162,0.843c-0.107,0.257-0.272,0.485-0.481,0.67\n" +
                                "\t\tc-0.229,0.197-0.493,0.349-0.779,0.444c-0.344,0.113-0.704,0.169-1.066,0.162c-0.46,0-0.875-0.074-1.248-0.224\n" +
                                "\t\tc-0.348-0.134-0.669-0.329-0.949-0.575l0.273-0.441c0.031-0.053,0.072-0.097,0.123-0.131c0.057-0.032,0.123-0.049,0.189-0.045\n" +
                                "\t\tc0.078,0,0.16,0.029,0.246,0.09l0.315,0.201c0.124,0.074,0.272,0.142,0.449,0.203c0.175,0.06,0.395,0.09,0.659,0.09\n" +
                                "\t\tc0.226,0,0.422-0.029,0.592-0.088c0.169-0.058,0.31-0.137,0.422-0.236c0.108-0.095,0.194-0.213,0.25-0.346\n" +
                                "\t\tc0.055-0.131,0.082-0.271,0.082-0.414c0.006-0.164-0.045-0.324-0.146-0.453c-0.107-0.127-0.238-0.23-0.387-0.305\n" +
                                "\t\tc-0.176-0.092-0.359-0.165-0.55-0.221c-0.21-0.064-0.421-0.131-0.63-0.199c-0.214-0.068-0.424-0.148-0.631-0.236\n" +
                                "\t\tc-0.198-0.085-0.383-0.197-0.549-0.336c-0.16-0.133-0.289-0.299-0.387-0.497c-0.104-0.225-0.153-0.471-0.146-0.719\n" +
                                "\t\tc0.002-0.513,0.225-1,0.611-1.336c0.199-0.175,0.444-0.314,0.734-0.418c0.32-0.109,0.656-0.162,0.994-0.157\n" +
                                "\t\tc0.434,0,0.822,0.069,1.167,0.205c0.345,0.137,0.643,0.324,0.894,0.563L30.668,7.501z\"/>\n" +
                                "</g>\n" +
                                "</svg>\n" :
                                "<span class=\"iconElement iconElement_ynIcon iconElement_ynIcon_no\">\n" +
                                "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\"\n" +
                                "\t width=\"44px\" height=\"17px\" viewBox=\"-0.5 0 44 17\" enable-background=\"new -0.5 0 44 17\" xml:space=\"preserve\">\n" +
                                "<g>\n" +
                                "\t<path fill=\"none\" stroke=\"#7B838C\" d=\"M8.5,0.5h26c4.418,0,8,3.582,8,8l0,0c0,4.418-3.582,8-8,8h-26c-4.418,0-8-3.582-8-8l0,0\n" +
                                "\t\tC0.5,4.082,4.082,0.5,8.5,0.5z\"/>\n" +
                                "\t<path fill=\"#32465A\" d=\"M14.781,3.686c0.113,0,0.196,0.014,0.25,0.042c0.072,0.044,0.135,0.103,0.185,0.172l5.396,7.02\n" +
                                "\t\tc-0.013-0.109-0.021-0.219-0.026-0.328c-0.004-0.103-0.007-0.205-0.007-0.309V3.686h1.105V13h-0.637\n" +
                                "\t\tc-0.086,0.003-0.172-0.016-0.25-0.052c-0.078-0.044-0.145-0.104-0.198-0.175L15.21,5.759c0.016,0.201,0.024,0.403,0.026,0.604V13\n" +
                                "\t\th-1.105V3.686H14.781z M26.441,6.31c0.481,0,0.916,0.08,1.304,0.241c0.388,0.16,0.717,0.388,0.987,0.683\n" +
                                "\t\tc0.271,0.294,0.48,0.65,0.625,1.069c0.145,0.418,0.218,0.884,0.218,1.4c0,0.52-0.073,0.988-0.218,1.404\n" +
                                "\t\tc-0.133,0.395-0.346,0.757-0.625,1.065c-0.27,0.295-0.6,0.522-0.987,0.681c-0.415,0.162-0.857,0.243-1.304,0.236\n" +
                                "\t\tc-0.48,0-0.914-0.08-1.303-0.236c-0.376-0.151-0.715-0.383-0.99-0.681c-0.281-0.308-0.496-0.671-0.631-1.065\n" +
                                "\t\tc-0.154-0.452-0.229-0.928-0.222-1.404c0-0.516,0.074-0.982,0.222-1.4c0.146-0.418,0.356-0.775,0.629-1.07\n" +
                                "\t\tc0.277-0.297,0.616-0.53,0.992-0.682C25.527,6.391,25.961,6.311,26.441,6.31L26.441,6.31z M26.441,12.188\n" +
                                "\t\tc0.65,0,1.136-0.219,1.457-0.654c0.319-0.436,0.48-1.043,0.48-1.823c0-0.784-0.16-1.395-0.48-1.833S27.092,7.22,26.441,7.22\n" +
                                "\t\tc-0.33,0-0.614,0.057-0.857,0.17c-0.24,0.11-0.448,0.277-0.607,0.487c-0.174,0.234-0.297,0.5-0.365,0.783\n" +
                                "\t\tc-0.079,0.31-0.119,0.66-0.119,1.05s0.04,0.739,0.119,1.046c0.08,0.309,0.203,0.567,0.365,0.777s0.364,0.371,0.607,0.484\n" +
                                "\t\tC25.827,12.13,26.113,12.188,26.441,12.188z\"/>\n" +
                                "</g>\n" +
                                "</svg>\n"
                        ) +
                        "   </span>\n" +
                        questionObject.originalDescription + "\n" +
                        "</li>\n");
                }
            }
        }
    }

    var basicInformationSection = $("#basicInformationSection");
    var reviewBasicInformationSection = $("#reviewBasicInformationSection");
    reviewBasicInformationSection.hide();
    if(typeof basicInformationSection.attr("style") === 'undefined' || basicInformationSection.attr("style").indexOf("display: none") === -1) {
        if (Object.keys(SAGIA.license.apply.data.basicInformation).length) {
            reviewBasicInformationSection.show();
            reviewBasicInformationSection.find("#reviewBasicInformationEntityName").text(
                SAGIA.license.apply.data.basicInformation.entityName ? SAGIA.license.apply.data.basicInformation.entityName : "");
            reviewBasicInformationSection.find("#reviewBasicInformationEntityNameArabic").text(
                SAGIA.license.apply.data.basicInformation.entityNameArabic ? SAGIA.license.apply.data.basicInformation.entityNameArabic : "");
            reviewBasicInformationSection.find("#reviewBasicInformationStatus").text(
                SAGIA.license.apply.data.basicInformation.legalStatusText ? SAGIA.license.apply.data.basicInformation.legalStatusText : "");
            reviewBasicInformationSection.find("#reviewBasicInformationSize").text(
                SAGIA.license.apply.data.basicInformation.labourSize ? SAGIA.license.apply.data.basicInformation.labourSize : "");
            reviewBasicInformationSection.find("#reviewBasicInformationCapital").text(
                (SAGIA.license.apply.data.basicInformation.capital ? SAGIA.license.apply.data.basicInformation.capital : "0") + " SAR");
            reviewBasicInformationSection.find("#reviewBasicInformationRegion").text(
                SAGIA.license.apply.data.basicInformation.regionText ? SAGIA.license.apply.data.basicInformation.regionText : "");
            reviewBasicInformationSection.find("#reviewBasicInformationCity").text(
                SAGIA.license.apply.data.basicInformation.cityText ? SAGIA.license.apply.data.basicInformation.cityText : "");
        }
    }

    var basicInformationExtendedSection = $("#basicInformationExtendedSection");
    var reviewBasicInformationExtendedSection = $("#reviewBasicInformationExtendedSection");
    reviewBasicInformationExtendedSection.hide();
    if(typeof basicInformationExtendedSection.attr("style") === 'undefined' || basicInformationExtendedSection.attr("style").indexOf("display: none") === -1) {
        if (Object.keys(SAGIA.license.apply.data.basicInformationExtended).length) {
            reviewBasicInformationExtendedSection.show();
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedEntityName").text(
                SAGIA.license.apply.data.basicInformationExtended.entityName ? SAGIA.license.apply.data.basicInformationExtended.entityName : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedEntityNameInArabic").text(
                SAGIA.license.apply.data.basicInformationExtended.entityNameArabic ? SAGIA.license.apply.data.basicInformationExtended.entityNameArabic : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedStatus").text(
                SAGIA.license.apply.data.basicInformationExtended.legalStatusText ? SAGIA.license.apply.data.basicInformationExtended.legalStatusText : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedMultinationalCompany").text(
                SAGIA.license.apply.data.basicInformationExtended.multinationalCompany ? SAGIA.license.apply.data.basicInformationExtended.multinationalCompany : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedCapital").text(
                (SAGIA.license.apply.data.basicInformationExtended.capital ? SAGIA.license.apply.data.basicInformationExtended.capital : "0") + " SAR");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedEmail").text(
                SAGIA.license.apply.data.basicInformationExtended.email ? SAGIA.license.apply.data.basicInformationExtended.email : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedTelephone").text(
                (SAGIA.license.apply.data.basicInformationExtended.countryCodeForTelephone ? (SAGIA.license.apply.data.basicInformationExtended.countryCodeForTelephone + " ") : "") +
                (SAGIA.license.apply.data.basicInformationExtended.telephone ? SAGIA.license.apply.data.basicInformationExtended.telephone : ""));
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedMobilePhone").text(
                (SAGIA.license.apply.data.basicInformationExtended.countryCodeForMobilePhone ? (SAGIA.license.apply.data.basicInformationExtended.countryCodeForMobilePhone + " ") : "") +
                (SAGIA.license.apply.data.basicInformationExtended.mobilePhone ? SAGIA.license.apply.data.basicInformationExtended.mobilePhone : ""));
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedWebsite").text(
                SAGIA.license.apply.data.basicInformationExtended.website ? SAGIA.license.apply.data.basicInformationExtended.website : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedCountry").text(
                SAGIA.license.apply.data.basicInformationExtended.countryName ? SAGIA.license.apply.data.basicInformationExtended.countryName : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedRegion").text(
                SAGIA.license.apply.data.basicInformationExtended.regionText ? SAGIA.license.apply.data.basicInformationExtended.regionText : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedCity").text(
                SAGIA.license.apply.data.basicInformationExtended.cityText ? SAGIA.license.apply.data.basicInformationExtended.cityText : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedAddress").text(
                SAGIA.license.apply.data.basicInformationExtended.address ? SAGIA.license.apply.data.basicInformationExtended.address : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedPostalCode").text(
                SAGIA.license.apply.data.basicInformationExtended.postalCode ? SAGIA.license.apply.data.basicInformationExtended.postalCode : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedPoBox").text(
                SAGIA.license.apply.data.basicInformationExtended.poBox ? SAGIA.license.apply.data.basicInformationExtended.poBox : "");
            reviewBasicInformationExtendedSection.find("#reviewBasicInformationExtendedInvestment").text(
                SAGIA.license.apply.data.basicInformationExtended.investmentText ? SAGIA.license.apply.data.basicInformationExtended.investmentText : "");
        }
    }

    var businessActivitiesSection = $("#businessActivitiesSection");
    var reviewISICSection = $("#reviewISICSection");
    reviewISICSection.hide();
    var typeRequirementSection = $("#typeRequirementSection");
    typeRequirementSection.hide();
	$("#temporaryLicenseTextBoxSection").hide();
    if(SAGIA.license.apply.data.licenseType == SAGIA.config.temporaryLicenseConstant){
    	reviewISICSection.hide();
    	$("#temporaryLicenseTextBoxSection p").text(SAGIA.license.apply.data.businessActivities.temporaryLicenseTextBox);
    	$("#temporaryLicenseTextBoxSection").show();
    }
    else if(typeof businessActivitiesSection.attr("style") === 'undefined' || businessActivitiesSection.attr("style").indexOf("display: none") === -1) {
        if (SAGIA.license.apply.data.businessActivities) {
            reviewISICSection.show();
            reviewISICSection.find("dd").empty();
            var sections = reviewISICSection.find("#reviewSection");
            sections.append("<dd>" +
                (SAGIA.license.apply.data.businessActivities.selectedSectionName ? SAGIA.license.apply.data.businessActivities.selectedSectionName : "") +
                "</dd>");
            var divisions = reviewISICSection.find("#reviewDivision");
            for (var indexDivision in SAGIA.license.apply.data.businessActivities.selectedDivisions) {
                if (SAGIA.license.apply.data.businessActivities.selectedDivisions.hasOwnProperty(indexDivision)) {
                    divisions.append("<dd>" +
                        (SAGIA.license.apply.data.businessActivities.selectedDivisions[indexDivision].divisionIdName ?
                            SAGIA.license.apply.data.businessActivities.selectedDivisions[indexDivision].divisionIdName : "")+
                        "</dd>");
                }
            }
            var groups = reviewISICSection.find("#reviewGroup");
            for (var indexGroup in SAGIA.license.apply.data.businessActivities.selectedGroups) {
                if (SAGIA.license.apply.data.businessActivities.selectedGroups.hasOwnProperty(indexGroup)) {
                    groups.append("<dd>" +
                        (SAGIA.license.apply.data.businessActivities.selectedGroups[indexGroup].groupIdName ?
                            SAGIA.license.apply.data.businessActivities.selectedGroups[indexGroup].groupIdName : "") +
                        "</dd>");
                }
            }
            var classes = reviewISICSection.find("#reviewClass");
            for (var indexClass in SAGIA.license.apply.data.businessActivities.selectedClasses) {
                if (SAGIA.license.apply.data.businessActivities.selectedClasses.hasOwnProperty(indexClass)) {
                    classes.append("<dd>" +
                        (SAGIA.license.apply.data.businessActivities.selectedClasses[indexClass].classIdName ?
                            SAGIA.license.apply.data.businessActivities.selectedClasses[indexClass].classIdName : "") +
                        "</dd>");
                }
            }
            var branches = reviewISICSection.find("#reviewBranch");
            for (var indexBranch in SAGIA.license.apply.data.businessActivities.selectedBranches) {
                if (SAGIA.license.apply.data.businessActivities.selectedBranches.hasOwnProperty(indexBranch)) {
                    branches.append("<dd>" +
                        (SAGIA.license.apply.data.businessActivities.selectedBranches[indexBranch].branchIdName ?
                            SAGIA.license.apply.data.businessActivities.selectedBranches[indexBranch].branchIdName : "") +
                        "</dd>");
                }
            }
            var reviewBusinessType = reviewISICSection.find("#reviewBusinessType");
            reviewBusinessType.hide();
            if (SAGIA.license.apply.data.businessActivities.businessType) {
                reviewBusinessType.show();
                reviewBusinessType.append("<dd>" + SAGIA.license.apply.data.businessActivities.businessTypeName + "</dd>");
            }
            var activities = reviewISICSection.find("#reviewActivities");
            var requirementListIds = [];
            
            
            
            for (var indexActivity in SAGIA.license.apply.data.businessActivities.selectedActivities) {
            	
            	if(SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].splrequirementId != '0000' ) {
            	   requirementListIds.push(SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].splrequirementId);
                 }
                if (SAGIA.license.apply.data.businessActivities.selectedActivities.hasOwnProperty(indexActivity)) {
                	
                    activities.append("<dd>" +
                        (SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityIdName ?
                            SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityIdName : "") + "</dd>");
                }
            }
         
            var requirementSubmitButton = $("#requirementSubmitButton");
            var termsAndConditions = $("#termsAndConditions");
            
            requirementSubmitButton.on("click", function() {
            	termsAndConditions.attr("disabled", false);
            	$("#reviewSubmitButton").attr("disabled", true);
            	if(termsAndConditions.is(":checked"))
            		$("#reviewSubmitButton").attr("disabled", false);
            	requirementSubmitButton.attr("disabled", true);
            	requirementSubmitButton.text(getI18nText("requirements.status.accepted"));
            	// requirementSubmitButton  Accepted!
            	
            });
         
            var typeRequirementSection = $("#typeRequirementSection");
            typeRequirementSection.hide();
            termsAndConditions.attr("disabled", false);
            $("#reviewSubmitButton").attr("disabled", true);
            if(requirementListIds.length > 0) {
            $.ajax(ACC.config.encodedContextPath + controllerUrl + "/sagiaLicenseTypeRequirementList/"+requirementListIds, {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    if (jsonData != null ) {
                       
                    	var licenseContent = "";
                	   $.each(jsonData, function(key,value) {
     					  if(value.content != '' )
     						{
     						  
     						    var activityList = $('<dl/>', {
     							    'class':'dlList',
     							}).append("<dt>"+getI18nText("license.apply.business.activities")+"</dt>").append("<dd></dd>");
     						    
     						   
	     						for (var indexActivity in SAGIA.license.apply.data.businessActivities.selectedActivities) {
	     			            	
	     			            	if(SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].splrequirementId != '0000' ) {
	     			            	   if(value.id == SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].splrequirementId)
	     			            	   {
	     			            		  activityList.append("<dd>" +
	     			                             (SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityIdName ?
	     			                                 SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityIdName : SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityId) + "</dd>");
	     			            	   }
	     			                 }
	     			            }
	     						
	     						if(licenseContent != "")
	     						{
	     							licenseContent += "<hr style='border: 1px solid #99a0a9;'>";
	     						}
	     						licenseContent += activityList.get(0).outerHTML;
	     						licenseContent += value.content;
	     						
     						}
     					});

                    	   
                    	if(requirementSubmitButton.text() != getI18nText("requirements.status.accepted")){
                    		termsAndConditions.attr("disabled", true);
                    		requirementSubmitButton.attr("disabled", true);
                    		 
                    	}else {
                    		if(termsAndConditions.is(":checked"))
                        		$("#reviewSubmitButton").attr("disabled", false);
                    		termsAndConditions.attr("disabled", false);
                    	}
                    	
                    	typeRequirementSection.show();
                    	//typeRequirementSection.find("#requirementContent").html( jsonData.content );
                    	$("#contentRequirement").html( licenseContent );
                    	if($("#requirementContent").height() > $("#contentRequirement").height() && requirementSubmitButton.text() != getI18nText("requirements.status.accepted")){
                    		requirementSubmitButton.attr("disabled", false);
                    	}
                       
                    }
                    
                }
            });
            
            }
            
            
        }
    }   
    
    /*var productsSection = $("#productsSection");
    var reviewProductsSection = $("#reviewProductsSection");
    reviewProductsSection.hide();
    if(typeof productsSection.attr("style") === 'undefined' || productsSection.attr("style").indexOf("display: none") === -1) {
        if (SAGIA.license.apply.data.entityInformation && SAGIA.license.apply.data.entityInformation.products) {
            reviewProductsSection.show();
            var productTable = reviewProductsSection.find(".tableModule-body").empty();
            for (var indexProduct in SAGIA.license.apply.data.entityInformation && SAGIA.license.apply.data.entityInformation.products) {
                if (SAGIA.license.apply.data.entityInformation && SAGIA.license.apply.data.entityInformation.products.hasOwnProperty(indexProduct)) {
                    var product = SAGIA.license.apply.data.entityInformation.products[indexProduct];
                    productTable.append("<tr>" +
                        "<td>" + product.id + "</td>" +
                        "<td>" + product.description + "</td>" +
                        "<td>" + product.quantity + "</td>" +
                        "<td>" + product.unitDescription + "</td>" +
                        "</tr>");
                }
            }
        }
    }*/
    var reviewShareholdersSection = $("#reviewShareholdersSection");
    reviewShareholdersSection.hide();
    var shareholdersTable = reviewShareholdersSection.find(".tableModule-body").empty();
    if(SAGIA.license.apply.data.qeemah === 1) {
        reviewShareholdersSection.show();
        for(var indexExistingShareholder in SAGIA.license.apply.data.qeemah1Data.existingShareholders) {
            if(SAGIA.license.apply.data.qeemah1Data.existingShareholders.hasOwnProperty(indexExistingShareholder)) {
            	SAGIA.license.apply.data.qeemah1Data.existingShareholders[indexExistingShareholder].hasDelegateInfo = "false"; //to avoid null pointer for existing draft data in production
            	var existingShareholder = SAGIA.license.apply.data.qeemah1Data.existingShareholders[indexExistingShareholder];
                var parentCompanyCountryText = $("#existingShareholderParentCompanyCountry option[value='"+existingShareholder.parentCompanyCountry+"']").text();
                parentCompanyCountryText = parentCompanyCountryText?parentCompanyCountryText:existingShareholder.parentCompanyCountryText;
                shareholdersTable.append("<tr>" +
                    "<td>" + existingShareholder.name + "</td>" +
                    "<td>"+getI18nText("shareholder.label.existing")+"</td>" +
                    "<td>" + existingShareholder.sharesPercentage + "</td>" +
                    "<td>"+parentCompanyCountryText+"</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" +
                    "</tr>");
            }
        }
        for(var indexNewShareholder in SAGIA.license.apply.data.qeemah1Data.newShareholders) {
            if(SAGIA.license.apply.data.qeemah1Data.newShareholders.hasOwnProperty(indexNewShareholder)) {
                var newShareholder = SAGIA.license.apply.data.qeemah1Data.newShareholders[indexNewShareholder];
               /* var deletgateTag="<td>Self Delegate</td>";
                if(SAGIA.license.apply.data.qeemah1Data.isDelegate){
                	deletgateTag="<td><a style='cursor: pointer;' name='showDetailsPerson' data-shareholder=" + indexNewShareholder + ">"+newShareholder.delegateFullNameEnglish+"</a></td>"
                }*/
                if(newShareholder.type === "Person") {
                	var currentNationalityText = $("#currentNationality option[value='"+newShareholder.currentNationality.val+"']").text();
                	currentNationalityText = currentNationalityText?currentNationalityText:newShareholder.currentNationality.text;
                	if(newShareholder.hasDelegateInfo === "true" && newShareholder.selfDelegate === "false"){
                    shareholdersTable.append("<tr>" +
                        "<td>" + newShareholder.fullNameEnglish + "</td>" +
                        "<td>"+getI18nText("shareholder.label.person")+"</td>" +
                        "<td>" + newShareholder.sharesPercentage + "</td>" +
                        "<td>" + currentNationalityText + "</td>" +
                        "<td>-</td>" +
                        "<td>" + newShareholder.delegate.fullNameEnglish + "</td>" + 
                        "<td>" + newShareholder.delegate.idNumber + "</td>" + 
                        "</tr>");
                	}
                	else{
                		 shareholdersTable.append("<tr>" +
                                 "<td>" + newShareholder.fullNameEnglish + "</td>" +
                                 "<td>"+getI18nText("shareholder.label.person")+"</td>" +
                                 "<td>" + newShareholder.sharesPercentage + "</td>" +
                                 "<td>" + currentNationalityText + "</td>" +
                                 "<td>-</td>" +
                                 "<td>-</td>" + 
                                 "<td>-</td>" + 
                                 "</tr>");
                	}
                } else if(newShareholder.type === "Organization") {
                	var companyCountryText = $("#companyCountry option[value='"+newShareholder.companyCountry+"']").text();
                	companyCountryText = companyCountryText?companyCountryText:newShareholder.companyCountryText;

                	var organizationLegalStatusText = $("#organizationLegalStatus option[value='"+newShareholder.organizationLegalStatus+"']").text();
                	organizationLegalStatusText = organizationLegalStatusText?organizationLegalStatusText:newShareholder.organizationLegalStatusText;
                	if(newShareholder.hasDelegateInfo === "true"){
                		shareholdersTable.append("<tr>" +
                			"<td>" + newShareholder.organizationNameEnglish + "</td>" +
                			"<td>"+getI18nText("shareholder.label.organization")+"</td>" +
                			"<td>" + newShareholder.companySharesPercentage + "</td>" +
	                        "<td>" + companyCountryText + "</td>" +
	                        "<td>" + organizationLegalStatusText + "</td>" +
	                        "<td>" + newShareholder.delegate.fullNameEnglish + "</td>" + 
	                        "<td>" + newShareholder.delegate.idNumber + "</td>" + 
	                        "</tr>");
                	}
                	else{
                		shareholdersTable.append("<tr>" +
                                "<td>" + newShareholder.organizationNameEnglish + "</td>" +
                                "<td>"+getI18nText("shareholder.label.organization")+"</td>" +
                                "<td>" + newShareholder.companySharesPercentage + "</td>" +
                                "<td>" + companyCountryText + "</td>" +
                                "<td>" + organizationLegalStatusText + "</td>" +
                                "<td>-</td>" + 
                                "<td>-</td>" + 
                                "</tr>");
                	}
                }
            }
        }
    } else if(SAGIA.license.apply.data.qeemah === 2) {
        reviewShareholdersSection.show();
        for(var indexShareholder in SAGIA.license.apply.data.qeemah2Data.shareholders) {
            if(SAGIA.license.apply.data.qeemah2Data.shareholders.hasOwnProperty(indexShareholder)) {
                var shareholder = SAGIA.license.apply.data.qeemah2Data.shareholders[indexShareholder];
                shareholdersTable.append("<tr>" +
                    "<td>" + shareholder.name + "</td>" +
                    "<td>" + shareholder.type + "</td>" +
                    "<td>" + shareholder.sharesPercentage + "</td>" +
                    "<td>" + shareholder.nationalityText + "</td>" +
                    "<td>" + (shareholder.legalStatusText ? shareholder.legalStatusText : "") + "</td>" +
                    "</tr>");
            }
        }
    }

    var reviewContactQeemah1Section = $("#reviewContactQeemah1Section");
    reviewContactQeemah1Section.hide();
    if(SAGIA.license.apply.data.qeemah === 1) {
        var contactQeemah1 = SAGIA.license.apply.data.qeemah1Data.contactPerson;
        reviewContactQeemah1Section.show();
        reviewContactQeemah1Section.find("#contactQeemah1Name").text(
            (contactQeemah1.firstName ? (contactQeemah1.firstName + " ") : "") +
            contactQeemah1.lastName ? contactQeemah1.lastName : "");
        reviewContactQeemah1Section.find("#contactQeemah1Role").text(
            contactQeemah1.roleText ? contactQeemah1.roleText : "");
        reviewContactQeemah1Section.find("#contactQeemah1Education").text(
            contactQeemah1.educationText ? contactQeemah1.educationText : "");
        reviewContactQeemah1Section.find("#contactQeemah1PassportNumber").text(
            contactQeemah1.passportNumber ? contactQeemah1.passportNumber : "");
        reviewContactQeemah1Section.find("#contactQeemah1PassportDate").text(
            contactQeemah1.passportIssueDate ? contactQeemah1.passportIssueDate : "");
        reviewContactQeemah1Section.find("#contactQeemah1PasswordExpiryDate").text(
            contactQeemah1.passportExpiryDate ? contactQeemah1.passportExpiryDate : "");
        reviewContactQeemah1Section.find("#contactQeemah1Country").text(
            contactQeemah1.countryText ? contactQeemah1.countryText : "");
        reviewContactQeemah1Section.find("#contactQeemah1City").text(
            contactQeemah1.city ? contactQeemah1.city : "");
        reviewContactQeemah1Section.find("#contactQeemah1Address").text(
            contactQeemah1.address ? contactQeemah1.address : "");
        reviewContactQeemah1Section.find("#contactQeemah1TelephoneNumber").text(
            (contactQeemah1.countryCodeForTelephone ? (contactQeemah1.countryCodeForTelephone + " ") : "") +
            contactQeemah1.telephone ? contactQeemah1.telephone : "");
        reviewContactQeemah1Section.find("#contactQeemah1MobileNumber").text(
            (contactQeemah1.countryCodeForMobileNumber ? (contactQeemah1.countryCodeForMobileNumber + " ") : "") +
            contactQeemah1.mobileNumber ? contactQeemah1.mobileNumber : "");
        reviewContactQeemah1Section.find("#contactQeemah1Email").text(
            contactQeemah1.email ? contactQeemah1.email : "");
    }

    var reviewContactQeemah2Section = $("#reviewContactQeemah2Section");
    reviewContactQeemah2Section.hide();
    if(SAGIA.license.apply.data.qeemah === 2) {
        var contactQeemah2 = SAGIA.license.apply.data.qeemah2Data.contactPerson;
        reviewContactQeemah2Section.show();
        reviewContactQeemah2Section.find("#contactQeemah2FirstName").text(
            contactQeemah2.firstName ? contactQeemah2.firstName : "");
        reviewContactQeemah2Section.find("#contactQeemah2LastName").text(
            contactQeemah2.lastName ? contactQeemah2.lastName : "");
        reviewContactQeemah2Section.find("#contactQeemah2Nationality").text(
            contactQeemah2.nationalityText ? contactQeemah2.nationalityText : "");
        reviewContactQeemah2Section.find("#contactQeemah2Position").text(
            contactQeemah2.roleText ? contactQeemah2.roleText : "");
        reviewContactQeemah2Section.find("#contactQeemah2Email").text(
            contactQeemah2.email ? contactQeemah2.email : "");
        reviewContactQeemah2Section.find("#contactQeemah2Country").text(
            contactQeemah2.countryText ? contactQeemah2.countryText : "");
        reviewContactQeemah2Section.find("#contactQeemah2MobileNumber").text(
            (contactQeemah2.countryCodeForMobileNumber ? (contactQeemah2.countryCodeForMobileNumber + " ") : "") +
            contactQeemah2.mobileNumber ? contactQeemah2.mobileNumber : "");
        reviewContactQeemah2Section.find("#contactQeemah2TelephoneNumber").text(
            (contactQeemah2.countryCodeForTelephone ? (contactQeemah2.countryCodeForTelephone + " ") : "") +
            contactQeemah2.telephone ? contactQeemah2.telephone : "");
    }
    $("a[name=showDetailsPerson]").on("click", function() {
    	var newShareholder = SAGIA.license.apply.data.qeemah1Data.newShareholders[$(this).data("shareholder")];
    	showDelegateDetails(newShareholder);
    });
    
};

     $("#requirementContent").scroll(function() {
	   if($("#requirementContent").scrollTop() + $("#requirementContent").height()  > $("#contentRequirement").height()) {
		   var requirementSubmitButton = $("#requirementSubmitButton");
		   if($("#requirementSubmitButton").text() != getI18nText("requirements.status.accepted")){
	         requirementSubmitButton.attr("disabled", false);
		   }
	   }
	});



$(function() {
    var termsAndConditions = $("#termsAndConditions");
    termsAndConditions.on("click", function() {
        var isChecked = termsAndConditions.is(":checked");
        SAGIA.license.apply.data.isTermsAndConditionsChecked = isChecked;
        if(!isChecked) {
        	 $("#reviewSubmitButton").attr("disabled", true);
            termsAndConditions.parents(".form-group").addClass("has-error").find(".help-block").text(getI18nText("acceptTerms.text"));
        } else {
        	 var visible = $("#typeRequirementSection").is(":visible");
        	 if(visible){
        		 if($("#requirementSubmitButton").text() == getI18nText("requirements.status.accepted")){
        			 $("#reviewSubmitButton").attr("disabled", false);
        		 }
        	 }
        	 else{
        		 $("#reviewSubmitButton").attr("disabled", false);
        	 }
            termsAndConditions.parents(".form-group").removeClass("has-error").find(".help-block").text("");
        }
    });

    $("#reviewSubmitButton").on("click", function(event) {
    	
    	  var cookieTnC = $.cookie('AcceptTermsAndCondition');

    	    if (cookieTnC == null) {
    	    	applyNewTnC(event,"Close");
    	    	return;
    	    }

        var firstTabLabelsAreValid = validateFirstTabRadioButtons(); //validate first tab radio buttons
//Dont validate the First Tab ASHAIK.RFC#1800000394 		
/*        if(!firstTabLabelsAreValid) {
            SAGIA.showError(getI18nText("register.validation.100"));
            SAGIA.license.apply.tabs.showAccessibleTabSelector("#entityInformationTab");
            return false;
        } */
        
        var temporaryLicenseValid = validateTemporaryLicense();
        if(!temporaryLicenseValid) {
            SAGIA.showError(getI18nText("register.validation.100"));
            SAGIA.license.apply.tabs.showAccessibleTabSelector("#entityInformationTab");
            return false;
        } 
      
        if(SAGIA.license.apply.data.licenseType != SAGIA.config.temporaryLicenseConstant )
        {
        	 SAGIA.license.apply.data.qeemahChannel = SAGIA.config.regularQeemahChannel;
        	 
        	 var isAllSame = true;
        	 if(SAGIA.license.apply.data.businessActivities.selectedActivities && SAGIA.license.apply.data.businessActivities.selectedActivities.length){
        	 var firstVal = SAGIA.license.apply.data.businessActivities.selectedActivities[0].qeemahChannel;
	         for (var indexActivity in SAGIA.license.apply.data.businessActivities.selectedActivities) {
	        	
	            if (SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].qeemahChannel != firstVal) {
	            	isAllSame = false;
	            }
	         }
	         
	         if(isAllSame)
	         {
	        	 SAGIA.license.apply.data.qeemahChannel = firstVal;
	         }
           }
    	}
        
        if(SAGIA.license.apply.data.isTermsAndConditionsChecked) {
            var qeemah = "QEE"+SAGIA.license.apply.data.qeemah;
            SAGIA.payment.requestPaymentDetails(true, "ZSGS", qeemah,function () {
                $.ajax({
                    type: 'POST',
                    contentType: 'application/json; charset=utf-8',
                    url: ACC.config.encodedContextPath + controllerUrl + "/apply",
                    data: JSON.stringify(SAGIA.license.apply.data),
                    displayErrorMessageInModal: false,
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                        xhr.setRequestHeader('Accept', "application/json");
                    },
                    success: function(response) {
                    	$.cookie("AcceptTermsAndCondition", null, { path: '/' });
                        var x = JSON.parse(response);
                        $('#serviceId').val(x.SAGIA_LICENSE_SERVICE_REQUEST_ID);
                        var ratingModal = $('#requestSubmittedComment');
                        ratingModal.find('.modal-description').text(getI18nText("submit.request.message")+" "+getI18nText("submit.request.number") +" "+ x.SAGIA_LICENSE_SERVICE_REQUEST_ID);
                        ratingModal.modal('show');
                        ratingModal.on('hide.bs.modal', function () {
                            window.location.href = ACC.config.encodedContextPath + "/";
                        });
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        var jsonResponse = jqXHR.responseJSON;
                        if (jsonResponse && typeof jsonResponse.sagiaExceptionResponse !== 'undefined' && jsonResponse.sagiaExceptionResponse.message) {
                            SAGIA.showError(jsonResponse.sagiaExceptionResponse.message);
                            return;
                        }
                        if (jsonResponse && jsonResponse.popupError) {
                            var popupError = jsonResponse.popupError;
                            SAGIA.showError(popupError);
                            SAGIA.license.apply.tabs.showAccessibleTabSelector(jsonResponse.tabName);
                            SAGIA.clearAllTabsErrorsExcept(jsonResponse.section);
                            return;
                        }
                        if (jsonResponse && jsonResponse.formErrors) {
                            var formErrors = jsonResponse.formErrors;
                            var section = jsonResponse.section;
                            var tabName = jsonResponse.tabName;
                            SAGIA.showError(getI18nText("register.validation.100"));
                            $(section + ' .help-block').each(function(index, errorMessageContainer) {
                                $(errorMessageContainer).text('');
                                $(errorMessageContainer).closest('.form-group').removeClass('has-error');
                                $(errorMessageContainer).siblings('.form-group').removeClass('has-error');
                            });
                            $.each(formErrors, function(key, errorMessage) {
                                $(section + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                                $(section + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                            });
                            SAGIA.license.apply.tabs.showAccessibleTabSelector(tabName);
                            SAGIA.clearAllTabsErrorsExcept(section);
                            return;
                        }
                        SAGIA.showError(getI18nText("register.validation.100"));
                    }
                });
            });
        } else {
            termsAndConditions.parents(".form-group").addClass("has-error").find(".help-block").text(getI18nText("acceptTerms.text"));
        }
    });
    function clearTabErrors(sectionDiv) {
        $(sectionDiv + ' .has-error').removeClass('has-error');
        $(sectionDiv +' .help-block').text('');
    }
    
    $(function() {
        SAGIA.clearAllTabsErrorsExcept = function(invalidTab) {
            var allTabDivs = ['#basicInformationSection', '#contactPersonTabData', '#shareholdersTab', '#basicInformationExtendedSection'];
            for (var i = 0; i < allTabDivs.length; i++) {
                if (allTabDivs[i] != invalidTab) {
                    clearTabErrors(allTabDivs[i]);
                }
            }
        }
    });

    $("#printButton").on("click", function() {
        window.print();
    });

    $("#editEntityInformationButton").on("click", function() {
        $("#entityInformationTab").trigger("click");
    });
    $("#editShareholdersButton").on("click", function() {
        $("#shareholdersTab").trigger("click");
    });
    $(".editContactPersonButton").on("click", function() {
        $("#contactPersonTab").trigger("click");
    });

    $("#reviewBackButton").on("click", function() {
        SAGIA.license.apply.tabs.showAccessibleTabSelector("#contactPersonTab");
        SAGIA.license.apply.onChangeTabToContactPersonTab();
    });

    $("#reviewTab").on("click", function() {
        SAGIA.license.apply.onChangeTabToReviewTab();
    });

    $.get(ACC.config.encodedContextPath + "/register/unifiedLicenseUrl", function (data) {
        $("#unifiedLicenseUrl").attr("href", data);
    });

    SAGIA.license.apply.onChangeTabToReviewTab();
    var isChecked = termsAndConditions.is(":checked");
    var visible = $("#typeRequirementSection").is(":visible");
    if(isChecked){
	    if(visible){
	    	if($("#requirementSubmitButton").text() == getI18nText("requirements.status.accepted")){
	    		$("#reviewSubmitButton").attr("disabled", false);
	    	}
	    } 
	    else
	    	$("#reviewSubmitButton").attr("disabled", false);
    }
    
});
