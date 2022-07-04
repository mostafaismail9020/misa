
SAGIA.licenseApplyReview = {
    _autoload: [
        "bindAll"
    ],
    businessActivitiesSection: $("#businessActivitiesSection"),
    reviewISICSection: $("#reviewISICSection"),
    typeRequirementSection: $("#typeRequirementSection"),
    temporaryLicenseTextBoxSection: $("#temporaryLicenseTextBoxSection"),
    requirementSubmitButton : $("#requirementSubmitButton"),
    termsAndConditions :  $("#termsAndConditions"),
    reviewSubmitButton : $("#reviewSubmitButton"),

    bindAll: function () {
        this.bindButtonClickEvents();
        this.bindLicenseTypeEvent();
        this.bindRequirementSubmitButton();
        this.bindRequirementTextEvent();
        this.bindTermsAndConditions();
		this.bindScrollContentRequirement();
		this.bindSubmitButtonEvent();
		this.rhqReviewSectionCreation()

		$("#printButton").on("click", function() {
			window.print();
		});
    },

    bindButtonClickEvents: function () {
    	var self = this;

        $("#editEntityInformationButton").on("click", function() {
            $("#entityInformationTab").trigger("click");
        });
        $("#editShareholdersButton").on("click", function() {
            $("#shareholdersTab").trigger("click");
        });
        $(".editContactPersonButton").on("click", function() {
            $("#contactPersonTab").trigger("click");
        });
    },

    bindLicenseTypeEvent: function () {
    	var self = this;
        if(sagiaData.licenseType && (sagiaData.licenseType == SAGIA.config.temporaryLicenseConstant)){
        	self.reviewISICSection.hide();
        	self.typeRequirementSection.hide();
        	self.temporaryLicenseTextBoxSection.show();
        }
    },

    bindRequirementSubmitButton: function () {
    	var self = this;
    	self.requirementSubmitButton.on("click", function() {
    		self.termsAndConditions.attr("disabled", false);
        	self.reviewSubmitButton.attr("disabled", true);
        	if(self.termsAndConditions.is(":checked"))
        		self.reviewSubmitButton.attr("disabled", false);

        	self.requirementSubmitButton.attr("disabled", true);
        	self.requirementSubmitButton.text(getI18nText("requirements.status.accepted"));
        	// requirementSubmitButton  Accepted!

        });
    },

    bindRequirementTextEvent: function () {
    	var self = this;
    	self.typeRequirementSection.hide();
    	self.termsAndConditions.attr("disabled", false);
    	self.reviewSubmitButton.attr("disabled", true);
    	if($("#licenseType").val() != SAGIA.config.temporaryLicenseConstant && sagiaData.businessActivities.selectedActivities)
    	{

    		var requirementListIds = [];
    		for (var indexActivity in sagiaData.businessActivities.selectedActivities) {
				if (sagiaData.businessActivities.selectedActivities[indexActivity].splrequirementId != '0000') {
					requirementListIds.push(sagiaData.businessActivities.selectedActivities[indexActivity].splrequirementId);
				}
			}

		    if(requirementListIds.length > 0)
		    {
			    $.ajax(ACC.config.encodedContextPath + controllerUrl + "/sagiaLicenseTypeRequirementList/"+requirementListIds, {
			        type: "GET",
			        responseType: "application/json;charset=utf-8",
			        contentType: "application/json;charset=utf-8",
			        cache: false,
			        success: function (data) {
			            var jsonData = JSON.parse(data);
			            var jsonEmptyData = jsonData != null && jsonData.length === 1 && jsonData[0].id === '0000';

			            if (jsonData != null && !jsonEmptyData) {

			            	var licenseContent = "";
			        	   	$.each(jsonData, function(key,value) {
						  		if(value.content != '' ) {

									var activityList = $('<dl/>', {
										'class':'dlList',
									}).append("<dt>"+getI18nText("license.apply.business.activities")+"</dt>").append("<dd></dd>");

//TODO: here need to use tag file to generate selected activities, may be store in the hidden fileds
//			 						for (var indexActivity in SAGIA.license.apply.data.businessActivities.selectedActivities) {
//
//			 			            	if(SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].splrequirementId != '0000' ) {
//			 			            	   if(value.id == SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].splrequirementId)
//			 			            	   {
//			 			            		  activityList.append("<dd>" +
//			 			                             (SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityIdName ?
//			 			                                 SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityIdName : SAGIA.license.apply.data.businessActivities.selectedActivities[indexActivity].activityId) + "</dd>");
//			 			            	   }
//			 			                 }
//			 			            }

									for (var indexActivity in sagiaData.businessActivities.selectedActivities) {
										if (sagiaData.businessActivities.selectedActivities[indexActivity].splrequirementId != '0000') {
											if (value.id == sagiaData.businessActivities.selectedActivities[indexActivity].splrequirementId) {
												activityList.append("<dd>" +
													(sagiaData.businessActivities.selectedActivities[indexActivity].activityIdName ?
														sagiaData.businessActivities.selectedActivities[indexActivity].activityIdName : sagiaData.businessActivities.selectedActivities[indexActivity].activityId) + "</dd>");
											}
										}
									}

									if(licenseContent != "") {
										licenseContent += "<hr style='border: 1px solid #99a0a9;'>";
									}

			 						licenseContent += activityList.get(0).outerHTML;
			 						licenseContent += value.content;
								}
							});


			            	if(self.requirementSubmitButton.text() != getI18nText("requirements.status.accepted")){
			            		self.termsAndConditions.attr("disabled", true);
			            		self.requirementSubmitButton.attr("disabled", true);

			            	}else {
			            		if(self.termsAndConditions.is(":checked"))
			                		$("#reviewSubmitButton").attr("disabled", false);
			            		self.termsAndConditions.attr("disabled", false);
			            	}

			            	self.typeRequirementSection.show();
			            	//typeRequirementSection.find("#requirementContent").html( jsonData.content );
			            	$("#contentRequirement").html( licenseContent );
			            	if($("#requirementContent").height() > $("#contentRequirement").height() && self.requirementSubmitButton.text() != getI18nText("requirements.status.accepted")) {
								self.requirementSubmitButton.attr("disabled", false);
							}
			            }
			        }
			    });
	    	}
    	}
    },

	bindTermsAndConditions: function () {
		var termsAndConditions = $("#termsAndConditions");
		termsAndConditions.on("click", function() {
			var isChecked = termsAndConditions.is(":checked");
			sagiaData.isTermsAndConditionsChecked = isChecked;
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
	},

	bindScrollContentRequirement: function () {
		$("#requirementContent").scroll(function() {
			if($("#requirementContent").scrollTop() + $("#requirementContent").height()  > $("#contentRequirement").height()) {
				var requirementSubmitButton = $("#requirementSubmitButton");
				if($("#requirementSubmitButton").text() != getI18nText("requirements.status.accepted")){
					requirementSubmitButton.attr("disabled", false);
				}
			}
		});
	},

	bindSubmitButtonEvent: function (){
    	var self = this;
		var termsAndConditions = $("#termsAndConditions");
		$("#reviewSubmitButton").on("click", function(event) {

			var cookieTnC = $.cookie('AcceptTermsAndCondition');

			if (cookieTnC == null) {
				applyNewTnC(event,"Close");
				return;
			}

			// var temporaryLicenseValid = validateTemporaryLicense();
			// if(!temporaryLicenseValid) {
			// 	SAGIA.showError(getI18nText("register.validation.100"));
			// 	SAGIA.license.apply.tabs.showAccessibleTabSelector("#entityInformationTab");
			// 	return false;
			// }

			if(sagiaData.licenseType !== SAGIA.config.temporaryLicenseConstant )
			{
				sagiaData.qeemahChannel = SAGIA.config.regularQeemahChannel;

				var isAllSame = true;
				if(sagiaData.businessActivities.selectedActivities && sagiaData.businessActivities.selectedActivities.length){
					var firstVal = sagiaData.businessActivities.selectedActivities[0].qeemahChannel;
					for (var indexActivity in sagiaData.businessActivities.selectedActivities) {

						if (sagiaData.businessActivities.selectedActivities[indexActivity].qeemahChannel != firstVal) {
							isAllSame = false;
						}
					}

					if(isAllSame)
					{
						sagiaData.qeemahChannel = firstVal;
					}
				}
			}

			if(sagiaData.isTermsAndConditionsChecked) {
				var qeemah = "QEE"+sagiaData.qeemah;
				self.requestPaymentDetails(true, "ZSGS", qeemah,function () {
					$.ajax({
						type: 'POST',
						contentType: 'application/json; charset=utf-8',
						url: ACC.config.encodedContextPath + controllerUrl + "/review",
						data: JSON.stringify(sagiaData),
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
								// SAGIA.license.apply.tabs.showAccessibleTabSelector(jsonResponse.tabName);
								// SAGIA.clearAllTabsErrorsExcept(jsonResponse.section);
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
								// SAGIA.license.apply.tabs.showAccessibleTabSelector(tabName);
								// SAGIA.clearAllTabsErrorsExcept(section);
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
	},
	rhqReviewSectionCreation: function (){
		if(sagiaData.licenseType && (sagiaData.licenseType == '11')){

			$("#reviewMncBranchSection", "#reviewMncCostTable", "#reviewMncBranchSection","#reviewRHQBranchesTable", "#reviewMncBrandTable", "#reviewMncBranchSection", ".rhq-review-items").show();
	   //RHQ review starts - DT

		if (typeof objectBranches == "undefined") {
			objectBranches = [];
		}

		if (typeof objectBrands == "undefined") {
			objectBrands = [];
		}

		if (typeof objectCost == "undefined") {
			objectCost = [];
		}

		if (typeof subsidiaryString == "undefined") {
			subsidiaryString = '';
		}



		$("#reviewRHQBranchesTable tbody").html('');
		try {
			if(typeof objectBranches !== "undefined"){
			if (objectBranches.length > 0) {
				for (var idx = 0; idx < objectBranches.length; idx++) {
					$("#reviewRHQBranchesTable tbody").append('<tr><td>' + objectBranches[idx]['companyName'] + '</td><td>' + objectBranches[idx]['country'] + '</td><td>' + objectBranches[idx]['businessRelationshipType'] + '</td><td>' + objectBranches[idx]['industry'] + '</td><td>' + objectBranches[idx]['operations'] + '</td><td>' + objectBranches[idx]['RhqActivityProvided'] + '</td></tr>');
				}
			}}
		}
		catch (error) {
			console.log(error)
		}



		$("#reviewMncBrandTable tbody").html('');
		try {
			if(typeof objectBrands !== "undefined"){
			if (objectBrands.length > 0) {
				for (var idx = 0; idx < objectBrands.length; idx++) {
					$("#reviewMncBrandTable tbody").append('<tr><td>' + objectBrands[idx]['brandName'] + '</td><td>' + objectBrands[idx]['country'] + '</td><td>' + objectBrands[idx]['industry'] + '</td><td>' + objectBrands[idx]['companyOwningBrandInMENA'] + '</td><td>' + objectBrands[idx]['RhqActivityProvided'] + '</td></tr>');

				}
			}}
		}
		catch (err) {
			console.log(err);
		}


		$("#reviewMncCostTable tbody").html('');
		try {
			if(typeof objectCost !== "undefined"){
			if (objectCost.length > 0) {
				for (var idx = 0; idx < objectCost.length; idx++) {
					$("#reviewMncCostTable tbody").append('<tr><td>' + objectCost[idx]['item'] + '</td><td>' + objectCost[idx]['unitCost'] + '</td><td>' + objectCost[idx]['noOfUnits'] + '</td><td>' + objectCost[idx]['costFrequency'] + '</td><td>' + objectCost[idx]['year2022'] + '</td><td>' + objectCost[idx]['year2023'] + '</td><td>' + objectCost[idx]['year2024'] + '</td></tr>');

				}
				Array.prototype.sum = function (prop) {
					var total = 0
					for (var i = 0, _len = this.length; i < _len; i++) {
						total += parseInt(this[i][prop]);
					}
					return total
				}
				$('#rhqCostTable-totalText').text('Total');
				$('#rhqCostTable-sum1').text(objectCost.sum("year2022"));
				$('#rhqCostTable-sum2').text(objectCost.sum("year2023"));
				$('#rhqCostTable-sum3').text(objectCost.sum("year2024"));
			}
		}
		}
		catch (err) {
			console.log(err);
		}

		$("#rhqSubsidiaryPresenceDiv").html('');
		try {

			$('#rhqSubsidiaryPresenceDiv').html(subsidiaryString);
			switch (subsidiaryString) {
				case "2_to_5_countries":
					$('#rhqSubsidiaryPresenceDiv').html('2 to 5 countries');
					break;
				case "6_to_10_countries":
					$('#rhqSubsidiaryPresenceDiv').html('6 to 10 countries');
					break;
				case "over_10_countries":
					$('#rhqSubsidiaryPresenceDiv').html('over 10 countries');
					break;
				default:
					$("#rhqSubsidiaryPresenceDiv").html('');
			}

		}
		catch (err) {
			console.log(err);
		}

		try{$(".centre-of-admin").each(function() {
			var text = $(this).text();
			text = text.replace("Middle_East_ME", "Middle East (ME)");
			$(this).text(text);
		});}
		catch(e){console.log(e);}

	}
	else{
		try{
			$("#reviewMncBranchSection", "#reviewMncCostTable", "#reviewMncBranchSection","#reviewRHQBranchesTable", "#reviewMncBrandTable", "#reviewMncBranchSection", ".rhq-review-items").hide();

		}catch (err) {
			console.log(err);
		}
		}
    //RHQ Ends -DT
	},
	requestPaymentDetails: function (reviewMode, serviceType, qeemah, callback) {
		if(reviewMode){
			$("#licenseApplicationPayment").find('.apply').show();
			$("#tblGrid").find("tbody").html("");
			if(sagiaData.isEntrepreneur){
				$("#tblGrid").find("tbody").append("<tr>" +
					"<th width='40%'>" + getI18nText("license.apply.payment.service") + "</th>" +
					"<th width='20%'>" + getI18nText("license.apply.payment.duration") + "</th>" +
					"<th width='40%'>" + getI18nText("license.apply.payment.price") + "</th>" +
					"</tr>" +
					"<tr>" +
					"<td width='40%'>" + getI18nText("license.apply.payment.license.fee") + "</td>" +
					"<td width='20%'>" + getI18nText("license.apply.payment.license.fee.entrepreneur.duration."+sagiaData.licenseYear) + "</td>" +
					"<td width='40%'>" + getI18nText("license.apply.payment.license.fee.entrepreneur.price."+sagiaData.licenseYear) + "</td>" +
					"</tr>" +
					"<tr>" +
					"<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee") + "</td>" +
					"<td width='20%'>" + getI18nText("license.apply.payment.subscription.fee.entrepreneur.duration."+sagiaData.licenseYear) + "</td>" +
					"<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee.entrepreneur.price."+sagiaData.licenseYear) + "</td>" +
					"</tr>" +
					"<tr>" +
					"<td width='60%'><span style='color:#00A6BE;font-size:14px'>" + getI18nText("license.apply.payment.entrepreneur.description") + "</span></td>" +
					"<td width='20%'>" + "</td>" +
					"<td width='20%'>" + "</td>" +
					"</tr>" +
					"<tr>" +
					"<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.total") + "</span></td>" +
					"<td width='20%'>" +  "</td>" +
					"<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.entrepreneur.total."+sagiaData.licenseYear) + "</span></td>" +
					"</tr>");

			}
                else if(sagiaData.licenseType && (sagiaData.licenseType == '11')){
                                        $("#tblGrid").find("tbody").append("<tr>" +"<th width='50%'>" + getI18nText("license.apply.payment.service.rhq.message") + "</th>"+"</tr>" );
                  }
			else{
            				$("#tblGrid").find("tbody").append("<tr>" +
            					"<th width='50%'>" + getI18nText("license.apply.payment.service") + "</th>" +
            					"<th width='20%'>" + getI18nText("license.apply.payment.duration") + "</th>" +
            					"<th width='30%'>" + getI18nText("license.apply.payment.price") + "</th>" +
            					"</tr>" +
            					"<tr>" +
            					"<td width='40%' >" + getI18nText("license.apply.payment.license.fee") + "</td>" +
            					"<td width='20%' >" + getI18nText("license.apply.payment.license.fee.duration."+sagiaData.licenseYear) + "</td>" +
            					"<td width='40%' >" + getI18nText("license.apply.payment.license.fee.price."+sagiaData.licenseYear) + "</td>" +
            					"</tr>" +
            					"<tr>" +
            					"<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee") + "</td>" +
            					"<td width='20%'>" + getI18nText("license.apply.payment.subscription.fee.duration."+sagiaData.licenseYear) + "</td>" +
            					"<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee.price."+sagiaData.licenseYear) + "</td>" +
            					"</tr>" +
            					"<tr>" +
            					"<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.total") + "</span></td>" +
            					"<td width='20%'>" +  "</td>" +
            					"<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.total."+sagiaData.licenseYear) + "</span></td>" +
            					"</tr>");
            			}
			$("#licenseApplicationPayment").find('.pay-buttons').hide();
		}
		else{
			$("#licenseApplicationPayment").find('.apply').hide();
			$("#licenseApplicationPayment").find('.pay-buttons').show();
		}

		var requestUrl = ACC.config.encodedContextPath + "/simulator/paymentDetails/"+serviceType;
		if(qeemah){
			requestUrl+= "/"+qeemah;
		}
		$.ajax(requestUrl, {
			type:"GET",
			contentType:"application/json;charset=utf-8",
			cache:false,
			success : function(response) {
				response = JSON.parse(response);
				var items = response.items;
				$("#paymentModal").find("tbody").html("");
				for (var key in items) {
					$("#paymentModal").find("tbody").append('<tr> <td>'+ items[key].serviceName +'</td>'+'<td>'+ Math.round(items[key].netValue * 100) / 100 +'</td></tr>');
				}
				$('#total').text(Math.round(response.total * 100) / 100 +' '+response.currency);
				$('#total').attr("total",Math.round(response.total * 100) / 100);
				$('#total').attr("currency",response.currency);
				$('#licenseApplicationPayment').unbind().modal('toggle');

				if(callback)
					$("#licenseApplicationPayment").find(".btn-apply").unbind().on('click', callback); //use unbind to avoid multiple request on close and reopen same model
			}
		});
	}
};