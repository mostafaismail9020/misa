var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = SAGIA.license.apply || {};
$(function() {
    var isicSections = {};

    var isShowAttachments = false;

    SAGIA.license.businessActivities = {
        businessType: undefined,
        licenseType: undefined,
        selectedSection: null,
        selectedDivisions: [],
        selectedGroups: [],
        selectedClasses: [],
        selectedBranches: [],
        selectedActivities: [],
        existingActivities: [],
        newActivities: [],
        temporaryLicenseTextBox: undefined,
        clearAll: function() {

            $("#businessActivitiesSection #businessActivitiesTable").hide();
            SAGIA.license.businessActivities.selectedSection = null;
            SAGIA.license.businessActivities.selectedDivisions = [];
            SAGIA.license.businessActivities.selectedGroups = [];
            SAGIA.license.businessActivities.selectedClasses = [];
            SAGIA.license.businessActivities.selectedBranches = [];
            SAGIA.license.businessActivities.selectedActivities = [];
            clearData();
        },
        show: function(amendmentWithActivities) {
            var businessActivitiesSection = $("#businessActivitiesSection");
            businessActivitiesSection.find("#businessTypeSection").hide();
            if (!amendmentWithActivities) {
                businessActivitiesSection.find("#noBusinessActivitiesSelected").show();
            }
            if (isShowAttachments) {
                businessActivitiesSection.find("#businessActivitiesTable #supportDocumentsThId").show();
            } else {
                businessActivitiesSection.find("#businessActivitiesTable #supportDocumentsThId").hide();
            }
            //businessActivitiesSection.show();
        },
        setBusinessTypeSectionVisible: function(visible) {},
        setShowAttachments: function(showAttachments) {
            isShowAttachments = showAttachments;
        },
        setLicenseType: function(licenseTypeValue, licenseTypeText, licenseAmendmentCurrentActivities) {
            if (!licenseTypeValue || !licenseTypeValue.length) {
                return;
            }
            SAGIA.license.businessActivities.licenseType = licenseTypeValue;
            $("#businessActivitiesSection #businessActivitiesModal #licenseTypeSpan").html(licenseTypeText ? licenseTypeText : licenseTypeValue);
            $.ajax(ACC.config.encodedContextPath + controllerUrl + "/isic/" + licenseTypeValue, {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function(data) {
                    isicSections = data;

                    if (SAGIA.license.apply.data && (SAGIA.license.apply.data.licenseType == SAGIA.config.temporaryLicenseConstant)) {
                        licenseAmendmentCurrentActivities = null;
                        SAGIA.license.apply.data.qeemahChannel = isicSections.activities[0].qeemahChannel;
                    }

                    if (isicSections.activities) {
                        isicSections.groups.forEach(function(isicGroup) {
                            var foundSection = null;
                            isicSections.divisions.some(function(division) {
                                if (division.divisionId === isicGroup.divisionId) {
                                    foundSection = division.sectionId;
                                    return true;
                                }
                            });
                            isicGroup.sectionId = foundSection;
                        });
                        isicSections.classes.forEach(function(isicSection) {
                            var foundDivisionId = null;
                            isicSections.groups.some(function(group) {
                                if (group.groupId === isicSection.groupId) {
                                    foundDivisionId = group.divisionId;
                                    return true;
                                }
                            });
                            var foundDivision = _findDivision(foundDivisionId);
                            isicSection.sectionId = foundDivision.sectionId;
                            isicSection.divisionId = foundDivision.divisionId;
                        });
                        isicSections.branches.forEach(function(isicBranch) {
                            var foundGroupId = null;
                            isicSections.classes.some(function(classElement) {
                                if (classElement.classId === isicBranch.classId) {
                                    foundGroupId = classElement.groupId;
                                    return true;
                                }
                            });
                            var foundGroup = _findGroup(foundGroupId);
                            isicBranch.sectionId = foundGroup.sectionId;
                            isicBranch.divisionId = foundGroup.divisionId;
                            isicBranch.groupId = foundGroup.groupId;
                        });
                        isicSections.activities.forEach(function(isicActivity) {
                            var foundClassId = null;
                            isicSections.branches.some(function(branch) {
                                if (branch.branchId === isicActivity.branchId) {
                                    foundClassId = branch.classId;
                                    return true;
                                }
                            });
                            var foundClass = _findClass(foundClassId);
                            isicActivity.sectionId = foundClass.sectionId;
                            isicActivity.divisionId = foundClass.divisionId;
                            isicActivity.groupId = foundClass.groupId;
                            isicActivity.classId = foundClass.classId;
                        });

                        var sectionItemsList = $("#businessActivitiesSection #businessActivitiesModal #sectionItems .baList").empty();
                        for (var i = 0; i < isicSections.sections.length; i++) {
                            var section = isicSections.sections[i];
                            if (_hasActivity({ "sectionId": section.sectionId })) {
                                sectionItemsList.append('' +
                                    '<li class="baList-item" data-section-id="' + section.sectionId + '"><a href="#">' + section.sectionId + ' - ' + section.description + '</a></li>'
                                );
                            }
                        }

                        setLicenseAmendmentCurrentActivities(licenseAmendmentCurrentActivities);
                    }
                }
            });
        }
    };

    var setLicenseAmendmentCurrentActivities = function(currentActivities) {
        if (currentActivities && currentActivities.length) {
            // First update the selected activities parent ids
            var currentActivitiesWithParentIds = [];
            isicSections.activities.forEach(function(businessActivity) {
                currentActivities.forEach(function(currentActivity) {
                    if (currentActivity.id == businessActivity.activityId) { //it's string or number
                        //currentActivitiesWithParentIds.push(businessActivity);
                    }
                });
            });

            if (currentActivities.length === currentActivitiesWithParentIds.length) { // Only valid activities: can show table and modal
                var sectionId = currentActivitiesWithParentIds[0].sectionId; // section must be unique
                var divisionsIds = [];
                var groupsIds = [];
                var classesIds = [];
                var branchesIds = [];
                var activitiesIds = [];
                currentActivitiesWithParentIds.forEach(function(selectedActivity) {
                    if (divisionsIds.indexOf(selectedActivity.divisionId) === -1) {
                        divisionsIds.push(selectedActivity.divisionId);
                    }
                    if (groupsIds.indexOf(selectedActivity.groupId) === -1) {
                        groupsIds.push(selectedActivity.groupId);
                    }
                    if (classesIds.indexOf(selectedActivity.classId) === -1) {
                        classesIds.push(selectedActivity.classId);
                    }
                    if (branchesIds.indexOf(selectedActivity.branchId) === -1) {
                        branchesIds.push(selectedActivity.branchId);
                    }
                    if (activitiesIds.indexOf(selectedActivity.activityId) === -1) {
                        activitiesIds.push(selectedActivity.activityId);
                    }
                });

                updateSections($("li[data-section-id=" + sectionId + "]"), false);

                var $divisionItems = $("#divisionItems");
                divisionsIds.forEach(function(divisionId) {
                    $divisionItems.find("input.form-control[data-division-id=" + divisionId + "]").prop("checked", true);
                });
                updateDivisions(false);

                var $groupItems = $("#groupItems");
                groupsIds.forEach(function(groupId) {
                    $groupItems.find("input.form-control[data-group-id=" + groupId + "]").prop("checked", true);
                });
                updateGroups(false);

                var $classItems = $("#classItems");
                classesIds.forEach(function(classId) {
                    $classItems.find("input.form-control[data-class-id=" + classId + "]").prop("checked", true);
                });
                updateClasses(false);

                var $branchItems = $("#branchItems");
                branchesIds.forEach(function(branchId) {
                    $branchItems.find("input.form-control[data-branch-id=" + branchId + "]").prop("checked", true);
                });
                updatedBranches(false);

                var $activityItems = $("#activityItems");
                activitiesIds.forEach(function(activityId) {
                    $activityItems.find("input.form-control[data-activity-id=" + activityId + "]").prop("checked", true);
                });
                updateActivities(true);
                $('#sectionItems').hide();
                SAGIA.license.apply.updateIsicTable();
            } else {
                currentActivities.forEach(function(activity) {
                    SAGIA.license.businessActivities.selectedActivities.push({
                        activityId: activity.id,
                        description: activity.description,
                        splRequirementId: activity.splRequirementId,
                        qeemahChannel: activity.qeemahChannel
                    })
                    SAGIA.license.businessActivities.existingActivities.push({
                        activityId: activity.id,
                        description: activity.description,
                        splRequirementId: activity.splRequirementId,
                        qeemahChannel: activity.qeemahChannel
                    })

                });
                $("#businessActivitiesSection #businessActivitiesModal #sectionItems").show();
                $("#businessActivitiesSection #businessActivitiesModal #sectionList.baBreadcrumb-item").css({ "text-decoration": "underline" });
                $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", true);
                SAGIA.license.apply.updateIsicTable();
                if (currentActivitiesWithParentIds.size > 0) {
                    $("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").removeClass("active");
                    $("#businessActivitiesSection #businessActivitiesModal #activityList").css({ "text-decoration": "underline" });
                    $("#businessActivitiesSection #businessActivitiesModal #activityItems").show();
                } else {
                    $("#businessActivitiesSection #businessActivitiesModal #activityItems").hide();
                    $("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").addClass("active");
                    $("#businessActivitiesSection #businessActivitiesModal #activityList").css({ "text-decoration": "none" });
                }
                $("#businessActivitiesSection #businessActivitiesModal").modal('hide');
            }
            if (SAGIA.license.apply.data) {
                SAGIA.license.apply.data.businessActivities = SAGIA.license.businessActivities;
            }
        } else {
            if (!SAGIA.license.businessActivities.selectedActivities.length > 1 && !$('body').hasClass('page-new-license-apply')) {
                $("#businessActivitiesSection #noBusinessActivitiesSelected").show();
            }
        }
    };

    $("#businessActivitiesSection #businessActivitiesModal #sectionItems").show();
    $("#businessActivitiesSection #businessActivitiesModal #sectionList.baBreadcrumb-item").css({ "text-decoration": "underline" });
    $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", true);

    var _findDivision = function(id) {
        var foundDivision = null;
        isicSections.divisions.some(function(division) {
            if (division.divisionId === id) {
                foundDivision = division;
                return true;
            }
        });
        return foundDivision;
    };
    var _findGroup = function(id) {
        var foundGroup = null;
        isicSections.groups.some(function(group) {
            if (group.groupId === id) {
                foundGroup = group;
                return true;
            }
        });
        return foundGroup;
    };
    var _findClass = function(id) {
        var foundClass = null;
        isicSections.classes.some(function(classElement) {
            if (classElement.classId === id) {
                foundClass = classElement;
                return true;
            }
        });
        return foundClass;
    };
    var _findBranch = function(id) {
        var foundBranch = null;
        isicSections.branches.some(function(branch) {
            if (branch.branchId === id) {
                foundBranch = branch;
                return true;
            }
        });
        return foundBranch;
    };
    var _hasActivity = function(dataIdWithValues) { //{"sectionId" : "5", "divisionId" : "4"}
        var found;
        isicSections.activities.some(function(activity) {
            found = true;
            for (var property in dataIdWithValues) {
                if (dataIdWithValues.hasOwnProperty(property)) {
                    if (activity[property] !== dataIdWithValues[property]) {
                        found = false;
                        break;
                    }
                }
            }
            if (found) {
                return true;
            }
        });
        return found;
    };

    var _updateIsicArray = function(uiArray, dataIds) {

        var isicArray = [];
        uiArray.each(function(index) {

            var isicObject = {};
            var uiElement = $(this);
            dataIds.forEach(function(dataId) {

                isicObject[dataId] = "" + uiElement.data(dataId); //convert this in string
                isicObject[dataId + "Name"] = uiElement.parent().find("label").text().trim();
            });
            isicArray.push(isicObject);
        });
        return isicArray;
    };

    var _findIsicElement = function(type, dataIdWithValues) { //'selectedDivision', {"sectionId" : "5", "divisionId" : "4"}
        var isicElements = SAGIA.license.businessActivities[type];
        var identifiedElement = null;
        isicElements.some(function(isicElement) {
            var found = false;
            for (var property in dataIdWithValues) {
                if (dataIdWithValues.hasOwnProperty(property)) {
                    found = true;
                    if (isicElement[property] !== dataIdWithValues[property]) {
                        found = false;
                        break;
                    }
                }
            }
            if (found) {
                identifiedElement = isicElement;
            }
            return found;
        });
        return identifiedElement;
    };

    var clearData = function() {
        $("#businessActivitiesSection #businessActivitiesModal .baList .baList-item").css({
            "text-decoration": "none",
            "color": "default"
        });
        $("#businessActivitiesSection #businessActivitiesModal a.baBreadcrumb-link").addClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #sectionList a.baBreadcrumb-link").removeClass("active");
        $("#businessActivitiesSection #businessActivitiesModal .baBreadcrumb-subMenu").remove();
        var modalDiv = $("#businessActivitiesSection #businessActivitiesModal");
        modalDiv.find("#sectionList div, #divisionList div, #groupList div, #classList div, #branchList div, #activityList div").empty();
        $("#businessActivitiesSection #businessActivitiesModal #sectionList").trigger("click");
        updateSections(null, true);
        //updateDivisions(false);
    };
    var updateSections = function(uiSelectedSection, displaySections) {
        if (uiSelectedSection) {
            SAGIA.license.businessActivities.selectedSection = "" + uiSelectedSection.data("sectionId");
            SAGIA.license.businessActivities.selectedSectionName = uiSelectedSection.find("a").html();
            var uiSelectedSection_a = uiSelectedSection.find("a");
            $("#businessActivitiesSection #businessActivitiesModal #sectionItems .baList .baList-item").css({
                "text-decoration": "none",
                "color": "default"
            });
            uiSelectedSection.css({
                "background": "#00A6BE"
            });
            uiSelectedSection_a.addClass('amnend-select-b-activities');

            $("#businessActivitiesSection #businessActivitiesModal #sectionList a.baBreadcrumb-link").removeClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #sectionList .baBreadcrumb-subMenu").remove();
            $("#businessActivitiesSection #businessActivitiesModal #sectionList").append("" +
                "<div class='baBreadcrumb-subMenu'>" +
                "<ul class='undottedList'>" +
                "<li class='undottedList-item'>" + uiSelectedSection.find("a").html() + "</li>" +
                "</ul>" +
                "<a href='#' class='subMenuEditButton btn btn_outline btn_round btn_slim'>" +
                "Edit " +
                "<span class='iconElement iconElement_editButton'>" +
                "<svg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'><path fill='#5CC83B' d='M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z'></path></svg>" +
                "</span>" +
                "</a>" +
                "</div");

            $("#businessActivitiesSection #businessActivitiesModal #divisionItems .contentModule-headline").html(uiSelectedSection.find("a").html());
            var divs1 = "";
            var divs2 = "";
            var count = 0;
            var subdivisions = [];
            isicSections.divisions.forEach(function(division) {
                if (division.sectionId === SAGIA.license.businessActivities.selectedSection &&
                    _hasActivity({ "sectionId": division.sectionId })) {
                    count++;
                    subdivisions.push(division);
                }
            });
            subdivisions.forEach(function(division, index) {
                var nodeId = "division" + division.sectionId + division.divisionId;
                if (index < count / 2) {
                    divs1 += '' +
                        '<div class="form-item">' +
                        '<input id="' + nodeId + '" data-section-id="' + division.sectionId + '" data-division-id="' + division.divisionId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                        '<label class="control-label" for="' + nodeId + '">' +
                        '<span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                        division.divisionId + ' - ' + division.description +
                        '</label>' +
                        '</div>';
                } else {
                    divs2 += '' +
                        '<div class="form-item">' +
                        '<input id="' + nodeId + '" data-section-id="' + division.sectionId + '" data-division-id="' + division.divisionId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                        '<label class="control-label" for="' + nodeId + '">' +
                        '<span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                        division.divisionId + ' - ' + division.description +
                        '</label>' +
                        '</div>';
                }
            });
            $("#businessActivitiesSection #businessActivitiesModal #divisionItems .formCheckBox .form-group:first").empty().append(divs1);
            $("#businessActivitiesSection #businessActivitiesModal #divisionItems .formCheckBox .form-group:last").empty().append(divs2);

            updateDivisions(false);
        }
        if (displaySections) {

            $("#businessActivitiesSection #businessActivitiesModal #sectionItems.baModule").show();
            $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", SAGIA.license.businessActivities.selectedSection == null);
        }
    };

    var updateDivisions = function(displayDivisions) {
        $("#businessActivitiesSection #businessActivitiesModal #divisionList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #divisionList a.baBreadcrumb-link").addClass("active");
        var uiSelectedDivisions = $("#businessActivitiesSection #businessActivitiesModal #divisionItems input.form-control:checked");
        if (uiSelectedDivisions.length) {
            $("#businessActivitiesSection #businessActivitiesModal #divisionList a.baBreadcrumb-link").removeClass("active");
            SAGIA.license.businessActivities.selectedDivisions = _updateIsicArray(uiSelectedDivisions, ["sectionId", "divisionId"]);
            var lis = "";
            uiSelectedDivisions.each(function(index) {
                lis += "<li class='undottedList-item'>" + $(this).parent().find("label").text().trim() + "</li>";
            });
            $("#businessActivitiesSection #businessActivitiesModal #divisionList").append("" +
                "<div class='baBreadcrumb-subMenu'>" +
                "   <ul class='undottedList'>" +
                lis +
                "   </ul>" +
                "   <a href='#' class='subMenuEditButton btn btn_outline btn_round btn_slim'>" +
                "       Edit " +
                "       <span class='iconElement iconElement_editButton'>" +
                "           <svg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'><path fill='#5CC83B' d='M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z'></path></svg>" +
                "       </span>" +
                "   </a>" +
                "</div");

            $("#businessActivitiesSection #businessActivitiesModal #groupItems .contentModule-headline").remove();
            $("#businessActivitiesSection #businessActivitiesModal #groupItems .row").remove();
            var contentToAdd = "";
            uiSelectedDivisions.each(function() {
                var uiSelectedDivision = $(this);
                contentToAdd += "" +
                    '<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">' +
                    uiSelectedDivision.parent().find("label").text().trim() +
                    '</div>';
                var divs1 = "";
                var divs2 = "";
                var count = 0;
                var subgroups = [];
                isicSections.groups.forEach(function(group) {
                    if (group.divisionId == uiSelectedDivision.data("divisionId") && //it's string or number
                        _hasActivity({
                            "sectionId": group.sectionId,
                            "divisionId": group.divisionId,
                            "groupId": group.groupId
                        })) {
                        count++;
                        subgroups.push(group);
                    }
                });
                subgroups.forEach(function(group, index) {
                    var nodeId = "group" + group.sectionId + group.divisionId + group.groupId;
                    var foundGroup = _findIsicElement("selectedGroups", {
                        sectionId: group.sectionId,
                        divisionId: group.divisionId,
                        groupId: group.groupId
                    });
                    if (index < count / 2) {
                        divs1 += '' +
                            '<div class="form-item">' +
                            '   <input ' + (foundGroup ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + group.sectionId + '" data-division-id="' + group.divisionId + '" data-group-id="' + group.groupId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                            '   <label class="control-label" for="' + nodeId + '">' +
                            '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                            group.groupId + ' - ' + group.description +
                            '   </label>' +
                            '</div>';
                    } else {
                        divs2 += '' +
                            '<div class="form-item">' +
                            '   <input ' + (foundGroup ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + group.sectionId + '" data-division-id="' + group.divisionId + '" data-group-id="' + group.groupId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                            '   <label class="control-label" for="' + nodeId + '">' +
                            '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                            group.groupId + ' - ' + group.description +
                            '   </label>' +
                            '</div>';
                    }
                });
                contentToAdd += '' +
                    '<div class="row">' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs1 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs2 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '</div>';
            });
            $("#businessActivitiesSection #businessActivitiesModal #groupItems").append(contentToAdd);
        } else {
            SAGIA.license.businessActivities.selectedDivisions = [];
            $("#businessActivitiesSection #businessActivitiesModal #divisionItems input.form-control:checked").prop("checked", false);
            $("#businessActivitiesSection #businessActivitiesModal #divisionList a.baBreadcrumb-link").addClass("active");
            SAGIA.license.businessActivities.selectedGroups = [];
            $("#businessActivitiesSection #businessActivitiesModal #groupItems input.form-control:checked").prop("checked", false);
        }
        if (displayDivisions) {
            $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", SAGIA.license.businessActivities.selectedDivisions.length === 0);
            $("#businessActivitiesSection #businessActivitiesModal #divisionItems.baModule").show();
        }
        updateGroups(false);
    };

    var updateGroups = function(displayGroups) {
        $("#businessActivitiesSection #businessActivitiesModal #groupList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #groupList a.baBreadcrumb-link").addClass("active");
        var uiSelectedGroups = $("#businessActivitiesSection #businessActivitiesModal #groupItems input.form-control:checked");
        if (uiSelectedGroups.length) {
            $("#businessActivitiesSection #businessActivitiesModal #groupList a.baBreadcrumb-link").removeClass("active");
            SAGIA.license.businessActivities.selectedGroups = _updateIsicArray(uiSelectedGroups, ["sectionId", "divisionId", "groupId"]);
            var lis = "";
            uiSelectedGroups.each(function(index) {
                lis += "<li class='undottedList-item'>" + $(this).parent().find("label").text().trim() + "</li>";
            });
            $("#businessActivitiesSection #businessActivitiesModal #groupList").append("" +
                "<div class='baBreadcrumb-subMenu'>" +
                "   <ul class='undottedList'>" +
                lis +
                "   </ul>" +
                "   <a href='#' class='subMenuEditButton btn btn_outline btn_round btn_slim'>" +
                "       Edit " +
                "       <span class='iconElement iconElement_editButton'>" +
                "           <svg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'><path fill='#5CC83B' d='M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z'></path></svg>" +
                "       </span>" +
                "   </a>" +
                "</div");

            $("#businessActivitiesSection #businessActivitiesModal #classItems .contentModule-headline").remove();
            $("#businessActivitiesSection #businessActivitiesModal #classItems .row").remove();
            var contentToAdd = "";
            uiSelectedGroups.each(function() {
                var uiSelectedGroup = $(this);
                contentToAdd += "" +
                    '<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">' +
                    uiSelectedGroup.parent().find("label").text().trim() +
                    '</div>';
                var divs1 = "";
                var divs2 = "";
                var count = 0;
                var subclasses = [];
                isicSections.classes.forEach(function(classElement) {
                    if (classElement.groupId == uiSelectedGroup.data("groupId") && //it's string or number
                        _hasActivity({
                            "sectionId": classElement.sectionId,
                            "divisionId": classElement.divisionId,
                            "groupId": classElement.groupId,
                            "classId": classElement.classId
                        })) {
                        count++;
                        subclasses.push(classElement);
                    }
                });
                subclasses.forEach(function(classElement, index) {
                    var nodeId = "class" + classElement.sectionId + classElement.divisionId + classElement.groupId + classElement.classId;
                    var foundClass = _findIsicElement("selectedClasses", {
                        sectionId: classElement.sectionId,
                        divisionId: classElement.divisionId,
                        groupId: classElement.groupId,
                        classId: classElement.classId
                    });
                    if (index < count / 2) {
                        divs1 += '' +
                            '<div class="form-item">' +
                            '   <input ' + (foundClass ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + classElement.sectionId + '" data-division-id="' + classElement.divisionId + '" data-group-id="' + classElement.groupId + '" data-class-id="' + classElement.classId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                            '   <label class="control-label" for="' + nodeId + '">' +
                            '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                            classElement.classId + ' - ' + classElement.description +
                            '   </label>' +
                            '</div>';
                    } else {
                        divs2 += '' +
                            '<div class="form-item">' +
                            '   <input ' + (foundClass ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + classElement.sectionId + '" data-division-id="' + classElement.divisionId + '" data-group-id="' + classElement.groupId + '" data-class-id="' + classElement.classId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                            '   <label class="control-label" for="' + nodeId + '">' +
                            '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                            classElement.classId + ' - ' + classElement.description +
                            '   </label>' +
                            '</div>';
                    }
                });
                contentToAdd += '' +
                    '<div class="row">' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs1 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs2 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '</div>';
            });
            $("#businessActivitiesSection #businessActivitiesModal #classItems").append(contentToAdd);
        } else {
            SAGIA.license.businessActivities.selectedGroups = [];
            $("#businessActivitiesSection #businessActivitiesModal #groupItems input.form-control:checked").prop("checked", false);
            $("#businessActivitiesSection #businessActivitiesModal #groupList a.baBreadcrumb-link").addClass("active");
            SAGIA.license.businessActivities.selectedClasses = [];
            $("#businessActivitiesSection #businessActivitiesModal #classItems input.form-control:checked").prop("checked", false);
        }
        if (displayGroups) {
            $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", SAGIA.license.businessActivities.selectedGroups.length === 0);
            $("#businessActivitiesSection #businessActivitiesModal #groupItems.baModule").show();
        }
        updateClasses(false);
    };

    var updateClasses = function(displayClasses) {
        $("#businessActivitiesSection #businessActivitiesModal #classList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #classList a.baBreadcrumb-link").addClass("active");
        var uiSelectedClasses = $("#businessActivitiesSection #businessActivitiesModal #classItems input.form-control:checked");
        if (uiSelectedClasses.length) {
            $("#businessActivitiesSection #businessActivitiesModal #classList a.baBreadcrumb-link").removeClass("active");
            SAGIA.license.businessActivities.selectedClasses = _updateIsicArray(uiSelectedClasses, ["sectionId", "divisionId", "groupId", "classId"]);
            var lis = "";
            uiSelectedClasses.each(function(index) {
                lis += "<li class='undottedList-item'>" + $(this).parent().find("label").text().trim() + "</li>";
            });
            $("#businessActivitiesSection #businessActivitiesModal #classList").append("" +
                "<div class='baBreadcrumb-subMenu'>" +
                "   <ul class='undottedList'>" +
                lis +
                "   </ul>" +
                "   <a href='#' class='subMenuEditButton btn btn_outline btn_round btn_slim'>" +
                "       Edit " +
                "       <span class='iconElement iconElement_editButton'>" +
                "           <svg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'><path fill='#5CC83B' d='M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z'></path></svg>" +
                "       </span>" +
                "   </a>" +
                "</div");

            $("#businessActivitiesSection #businessActivitiesModal #branchItems .contentModule-headline").remove();
            $("#businessActivitiesSection #businessActivitiesModal #branchItems .row").remove();
            var contentToAdd = "";
            uiSelectedClasses.each(function() {
                var uiSelectedClass = $(this);
                contentToAdd += "" +
                    '<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">' +
                    uiSelectedClass.parent().find("label").text().trim() +
                    '</div>';
                var divs1 = "";
                var divs2 = "";
                var count = 0;
                var subbranches = [];
                isicSections.branches.forEach(function(branch) {
                    if (branch.classId == uiSelectedClass.data("classId") && // .data returns number or string
                        _hasActivity({
                            "sectionId": branch.sectionId,
                            "divisionId": branch.divisionId,
                            "groupId": branch.groupId,
                            "classId": branch.classId,
                            "branchId": branch.branchId
                        })) {
                        count++;
                        subbranches.push(branch);
                    }
                });
                subbranches.forEach(function(branch, index) {
                    if (branch.classId == uiSelectedClass.data("classId")) { // .data returns number or string
                        var nodeId = "branch" + branch.sectionId + branch.divisionId + branch.groupId + branch.classId + branch.branchId;
                        var foundBranch = _findIsicElement("selectedBranches", {
                            sectionId: branch.sectionId,
                            divisionId: branch.divisionId,
                            groupId: branch.groupId,
                            classId: branch.classId,
                            branchId: branch.branchId
                        });
                        if (index < count / 2) {
                            divs1 += '' +
                                '<div class="form-item">' +
                                '   <input ' + (foundBranch ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + branch.sectionId + '" data-division-id="' + branch.divisionId + '" data-group-id="' + branch.groupId + '" data-class-id="' + branch.classId + '" data-branch-id="' + branch.branchId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                                '   <label class="control-label" for="' + nodeId + '">' +
                                '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                                branch.branchId + ' - ' + branch.description +
                                '   </label>' +
                                '</div>';
                        } else {
                            divs2 += '' +
                                '<div class="form-item">' +
                                '   <input ' + (foundBranch ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + branch.sectionId + '" data-division-id="' + branch.divisionId + '" data-group-id="' + branch.groupId + '" data-class-id="' + branch.classId + '" data-branch-id="' + branch.branchId + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                                '   <label class="control-label" for="' + nodeId + '">' +
                                '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                                branch.branchId + ' - ' + branch.description +
                                '   </label>' +
                                '</div>';
                        }
                    }
                });
                contentToAdd += '' +
                    '<div class="row">' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs1 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs2 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '</div>';
            });
            $("#businessActivitiesSection #businessActivitiesModal #branchItems").append(contentToAdd);
        } else {
            SAGIA.license.businessActivities.selectedClasses = [];
            $("#businessActivitiesSection #businessActivitiesModal #classItems input.form-control:checked").prop("checked", false);
            $("#businessActivitiesSection #businessActivitiesModal #classList a.baBreadcrumb-link").addClass("active");
            SAGIA.license.businessActivities.selectedBranches = [];
            $("#businessActivitiesSection #businessActivitiesModal #branchItems input.form-control:checked").prop("checked", false);
        }
        if (displayClasses) {
            $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", SAGIA.license.businessActivities.selectedClasses.length === 0);
            $("#businessActivitiesSection #businessActivitiesModal #classItems.baModule").show();
        }
        updatedBranches(false);
    };

    var updatedBranches = function(displayBranches) {
        $("#businessActivitiesSection #businessActivitiesModal #branchList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #branchList a.baBreadcrumb-link").addClass("active");
        var uiSelectedBranches = $("#businessActivitiesSection #businessActivitiesModal #branchItems input.form-control:checked");
        if (uiSelectedBranches.length) {
            $("#businessActivitiesSection #businessActivitiesModal #branchList a.baBreadcrumb-link").removeClass("active");
            SAGIA.license.businessActivities.selectedBranches = _updateIsicArray(uiSelectedBranches, ["sectionId", "divisionId", "groupId", "classId", "branchId"]);
            var lis = "";
            uiSelectedBranches.each(function(index) {
                lis += "<li class='undottedList-item'>" + $(this).parent().find("label").text().trim() + "</li>";
            });
            $("#businessActivitiesSection #businessActivitiesModal #branchList").append("" +
                "<div class='baBreadcrumb-subMenu'>" +
                "   <ul class='undottedList'>" +
                lis +
                "   </ul>" +
                "   <a href='#' class='subMenuEditButton btn btn_outline btn_round btn_slim'>" +
                "       Edit " +
                "       <span class='iconElement iconElement_editButton'>" +
                "           <svg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'><path fill='#5CC83B' d='M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z'></path></svg>" +
                "       </span>" +
                "   </a>" +
                "</div");

            $("#businessActivitiesSection #businessActivitiesModal #activityItems .contentModule-headline").remove();
            $("#businessActivitiesSection #businessActivitiesModal #activityItems .row").remove();
            var contentToAdd = "";
            uiSelectedBranches.each(function() {
                var uiSelectedBranch = $(this);
                contentToAdd += "" +
                    '<div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered">' +
                    uiSelectedBranch.parent().find("label").text().trim() +
                    '</div>';
                var divs1 = "";
                var divs2 = "";
                var count = 0;
                var subactivities = [];
                isicSections.activities.forEach(function(activity) {
                    if (activity.branchId == uiSelectedBranch.data("branchId")) { // .data returns number or string
                        count++;
                        subactivities.push(activity);
                    }
                });
                subactivities.forEach(function(activity, index) {
                    if (activity.branchId == uiSelectedBranch.data("branchId")) { // .data returns number or string
                        var nodeId = "activity" + activity.sectionId + activity.divisionId + activity.groupId + activity.classId + activity.branchId + activity.activityId;
                        var foundActivity = _findIsicElement("selectedActivities", {
                            sectionId: activity.sectionId,
                            divisionId: activity.divisionId,
                            groupId: activity.groupId,
                            classId: activity.classId,
                            branchId: activity.branchId,
                            activityId: activity.activityId
                        });

                        if (index < count / 2) {
                            divs1 += '' +
                                '<div class="form-item">' +
                                '   <input ' + (foundActivity ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + activity.sectionId + '" data-division-id="' + activity.divisionId + '" data-group-id="' + activity.groupId + '" data-class-id="' + activity.classId + '" data-branch-id="' + activity.branchId + '" data-activity-id="' + activity.activityId + '" data-splrequirement-id="' + activity.splRequirementId + '" data-qeemah-channel="' + activity.qeemahChannel + '" data-description="' + activity.description + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                                '   <label class="control-label" for="' + nodeId + '">' +
                                '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                                activity.activityId + ' - ' + activity.description +
                                '   </label>' +
                                '</div>';
                        } else {
                            divs2 += '' +
                                '<div class="form-item">' +
                                '   <input ' + (foundActivity ? 'checked="checked"' : '') + ' id="' + nodeId + '" data-section-id="' + activity.sectionId + '" data-division-id="' + activity.divisionId + '" data-group-id="' + activity.groupId + '" data-class-id="' + activity.classId + '" data-branch-id="' + activity.branchId + '" data-activity-id="' + activity.activityId + '" data-splrequirement-id="' + activity.splRequirementId + '" data-qeemah-channel="' + activity.qeemahChannel + '" data-description="' + activity.description + '" class="form-control" placeholder="." type="checkbox" value=""/>' +
                                '   <label class="control-label" for="' + nodeId + '">' +
                                '       <span><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"/></svg></span>' +
                                activity.activityId + ' - ' + activity.description +
                                '   </label>' +
                                '</div>';
                        }

                    }
                });
                contentToAdd += '' +
                    '<div class="row">' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs1 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '   <div class="col-md-6">' +
                    '       <div class="formCheckBox formCheckBox_block formCheckBox_slim">' +
                    '           <div class="form-group">' +
                    divs2 +
                    '           </div>' +
                    '       </div>' +
                    '   </div>' +
                    '</div>';
            });
            $("#businessActivitiesSection #businessActivitiesModal #activityItems").append(contentToAdd);
        } else {
            SAGIA.license.businessActivities.selectedBranches = [];
            $("#businessActivitiesSection #businessActivitiesModal #branchItems input.form-control:checked").prop("checked", false);
            $("#businessActivitiesSection #businessActivitiesModal #branchList a.baBreadcrumb-link").addClass("active");
            SAGIA.license.businessActivities.selectedActivities = [];
            $("#businessActivitiesSection #businessActivitiesModal #activityItems input.form-control:checked").prop("checked", false);
        }
        if (displayBranches) {
            $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", SAGIA.license.businessActivities.selectedBranches.length === 0);
            $("#businessActivitiesSection #businessActivitiesModal #branchItems.baModule").show();
        }
        updateActivities(false);
    };

    var updateActivities = function(displayActivities) {
        $("#businessActivitiesSection #businessActivitiesModal #activityList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").addClass("active");
        var uiSelectedActivities = $("#businessActivitiesSection #businessActivitiesModal #activityItems input.form-control:checked");
        if (uiSelectedActivities.length) {
            $("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").removeClass("active");
            SAGIA.license.businessActivities.selectedActivities = _updateIsicArray(uiSelectedActivities, ["sectionId", "divisionId", "groupId", "classId", "branchId", "activityId", "description", "splrequirementId", "qeemahChannel"]);
            var lis = "";
            uiSelectedActivities.each(function(index) {
                lis += "<li class='undottedList-item'>" + $(this).parent().find("label").text().trim() + "</li>";
            });
            $("#businessActivitiesSection #businessActivitiesModal #activityList").append("" +
                "<div class='baBreadcrumb-subMenu'>" +
                "<ul class='undottedList'>" +
                lis +
                "</ul>" +
                "<a href='#' class='subMenuEditButton btn btn_outline btn_round btn_slim'>" +
                "Edit " +
                "<span class='iconElement iconElement_editButton'>" +
                "<svg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'><path fill='#5CC83B' d='M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z'></path></svg>" +
                "</span>" +
                "</a>" +
                "</div");
        } else {
            SAGIA.license.businessActivities.selectedActivities = [];
            $("#businessActivitiesSection #businessActivitiesModal #activityItems input.form-control:checked").prop("checked", false);
            $("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").addClass("active");
        }
        if (displayActivities) {
            $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", SAGIA.license.businessActivities.selectedActivities.length === 0);
            $("#businessActivitiesSection #businessActivitiesModal #activityItems.baModule").show();
        }
    };

    SAGIA.license.apply.updateIsicTable = function() {

        $("#businessActivitiesSection #businessActivitiesModal #sectionItems").show();
        $("#businessActivitiesSection #businessActivitiesModal #sectionList a.baBreadcrumb-link").removeClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #sectionList .baBreadcrumb-item").css({ "text-decoration": "underline" });
        $("#businessActivitiesSection #businessActivitiesModal #divisionList a.baBreadcrumb-link").addClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #divisionList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #groupList a.baBreadcrumb-link").addClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #groupList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #classList a.baBreadcrumb-link").addClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #classList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #branchList a.baBreadcrumb-link").addClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #branchList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").addClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #activityList .baBreadcrumb-subMenu").remove();
        $("#businessActivitiesSection #businessActivitiesModal #nextButton").prop("disabled", true);
        $("#businessActivitiesSection #businessActivitiesModal").modal('hide');

        /*$("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").removeClass("active");
        $("#businessActivitiesSection #businessActivitiesModal #activityList").css({"text-decoration": "underline"});
        $("#businessActivitiesSection #businessActivitiesModal #activityItems").show();
        $("#businessActivitiesSection #businessActivitiesModal").modal('hide');*/
        var content = '';
        var isicArray = [];

        $.each(SAGIA.license.businessActivities.selectedActivities, function(i, e) {
            var matchingItems = $.grep(SAGIA.license.businessActivities.newActivities, function(item) {
                return item.activityId === e.activityId;
            });
            if (matchingItems.length === 0) {
                SAGIA.license.businessActivities.newActivities.push(e);
            }
        });

        SAGIA.license.businessActivities.selectedActivities = SAGIA.license.businessActivities.newActivities;


        SAGIA.license.businessActivities.selectedActivities.forEach(function(activity) {
            if (isShowAttachments) {
                var combination = activity.sectionId + "/" + activity.divisionId + "/" + activity.groupId + "/" + activity.classId;
                if (isicArray.indexOf(combination) === -1) {
                    isicArray.push(combination);
                }
            }

            content += '' +
                '<tr data-activity-id="' + activity.activityId + '"' +
                'data-section-id="' + activity.sectionId + '"' +
                'data-division-id="' + activity.divisionId + '"' +
                'data-group-id="' + activity.groupId + '"' +
                'data-splrequirement-id="' + activity.splrequirementId + '"' +
                'data-qeemah-channel="' + activity.qeemahChannel + '"' +
                'data-class-id="' + activity.classId + '"' +
                'data-branch-id="' + activity.branchId + '"' + 'style="background:#f5fdf4">' +
                '   <td class="activityId"><span class="activityId text-bold">' + activity.activityId + '</span></td>' +
                '   <td class="activityDescription">' + activity.description + '</td>' +
                (!isShowAttachments ? '' :
                    '   <td class="activityAttachments"></td>') +
                '   <td class="activityActions tableModule-bodyItem-action">' +
                '       <button class="border-0 bg-transparent deleteFromBusinessActivities">' +
                '           <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">' +
                '               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"/>' +
                '           </svg>' +
                '       </button>' +
                '   </td>' +
                '</tr>';
        });
        var activitiesTableBody = $("#businessActivitiesSection #businessActivitiesTable tbody");
        activitiesTableBody.empty().append(content);

        SAGIA.license.businessActivities.existingActivities.forEach(function(existingActivity) {
            $("#businessActivitiesSection #businessActivitiesTable tbody tr").each(function() {
                if ($(this).attr('data-activity-id') === existingActivity.activityId) {
                    $(this).removeAttr("style");
                }
            });
        });

        if (SAGIA.license.apply.data) {
            $("#businessActivitiesSection #businessActivitiesTable tbody tr").removeAttr("style");
        }

        if (isShowAttachments) {
            for (var index in isicArray) {
                if (isicArray.hasOwnProperty(index)) {
                    var splitIsic = isicArray[index].split("/");
                    $.ajax(ACC.config.encodedContextPath + controllerUrl + "/get-isic-details/" + isicArray[index], {
                        type: "GET",
                        responseType: "application/json;charset=utf-8",
                        contentType: "application/json;charset=utf-8",
                        sectionId: splitIsic[0],
                        divisionId: splitIsic[1],
                        groupId: splitIsic[2],
                        classId: splitIsic[3],
                        cache: false,
                        success: function(isicDetails) {
                            for (var index in isicDetails) {
                                if (isicDetails.hasOwnProperty(index) && isicDetails[index].attachments) {
                                    activitiesTableBody.find("tr[data-section-id='" + this.sectionId + "'][data-division-id='" + this.divisionId + "'][data-group-id='" + this.groupId + "'][data-class-id='" + this.classId + "']:first td.activityAttachments").append(
                                        '      <div class="formInputFile">' +
                                        '           <div class="form-group">' +
                                        '               <input class="form-control js-inputFile" type="file" accept="application/pdf" data-file-id="attachment' + index + '" value=""/>' +
                                        '               <input class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>' +
                                        '                   <label class="control-label">' + isicDetails[index].attachments + '</label>' +
                                        '                   <div class="form-icon form-icon_browse">' +
                                        '                       <svg width="18" height="18" viewBox="0 0 18 18" xmlns="http://www.w3.org/2000/svg">' +
                                        '                           <g fill="none" stroke="currentColor">' +
                                        '                               <path d="M16.762 13.39v1.348c0 1.117-.906 2.023-2.023 2.023h-11.466c-1.117 0-2.023-.906-2.023-2.023v-1.348"/>' +
                                        '                              <path d="M9.006 1.25v12.063"/>' +
                                        '                               <path d="M4.07 6.186l4.936-4.936 4.935 4.936"/>' +
                                        '                           </g>' +
                                        '                       </svg>' +
                                        '                   </div>' +
                                        '                   <div class="form-icon form-icon_reset js-inputFile-reset">' +
                                        '                       <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">' +
                                        '                            <path d="M9,17.5c-4.687,0-8.5-3.813-8.5-8.5c0-4.687,3.813-8.5,8.5-8.5c4.687,0,8.5,3.813,8.5,8.5C17.5,13.687,13.687,17.5,9,17.5z M9,1.5C4.864,1.5,1.5,4.864,1.5,9s3.364,7.5,7.5,7.5s7.5-3.364,7.5-7.5S13.136,1.5,9,1.5z M11.813,12.812c-0.268,0-0.519-0.104-0.707-0.292l-1.949-1.949l-1.95,1.949c-0.378,0.377-1.037,0.377-1.414,0c-0.189-0.188-0.293-0.439-0.293-0.707s0.104-0.519,0.293-0.707l1.949-1.949l-1.95-1.95c-0.39-0.39-0.39-1.024,0-1.414c0.377-0.377,1.037-0.377,1.414,0l1.95,1.95l1.949-1.95c0.377-0.377,1.039-0.377,1.414,0c0.389,0.39,0.389,1.023,0,1.413l-1.95,1.95l1.95,1.949c0.39,0.392,0.389,1.025,0,1.414C12.332,12.709,12.081,12.812,11.813,12.812z"/>' +
                                        '                       </svg>' +
                                        '                   </div>' +
                                        '           </div>' +
                                        '           <div class="help-block"></div>' +
                                        '       </div>'
                                    );
                                }
                            }
                        }
                    });
                }
            }
        }

        $("#businessActivitiesSection #noBusinessActivitiesSelected").hide();
        $("#businessActivitiesSection #businessActivitiesTable").show();
    };

    $(document).on("change", "#businessActivitiesSection #businessType", function() {
        SAGIA.license.businessActivities.businessType = $(this).val();
        SAGIA.license.businessActivities.businessTypeName = $(this).find(":selected").text();
        $("#businessActivitiesSection #businessActivitiesModal #businessTypeSpan").parent().show();

        $("#businessActivitiesSection #businessActivitiesModal #businessTypeSpan").html(SAGIA.license.businessActivities.businessTypeName);
        if (SAGIA.license.businessActivities.selectedActivities.length) {
            $("#noBusinessActivitiesSelected").hide();
            $("#businessActivitiesTable").show();
        } else {
            $("#noBusinessActivitiesSelected").show();
            $("#businessActivitiesTable").hide();
        }
    });

    $(document).on("keyup input", "#businessActivitiesSection #businessActivitiesModal .searchInputBox-input", function() {
        var searchQuery = $(this).val().toUpperCase();
        var headline = $(this).parents(".baModule").find(".contentModule-headline");
        var lis = $(this).parents(".baModule").find("ul li");
        var i;
        for (i = 0; i < lis.length; i++) {
            var anchor = lis[i].getElementsByTagName("a")[0];
            if (anchor.innerHTML.toUpperCase().indexOf(searchQuery) > -1) {
                lis[i].style.display = "";
            } else {
                lis[i].style.display = "none";
            }
        }

        var divs = $(this).parents(".baModule").find("div.form-item");
        for (i = 0; i < divs.length; i++) {
            var label = divs[i].getElementsByTagName("label")[0];
            if (label.innerHTML.toUpperCase().indexOf(searchQuery) > -1) {
                divs[i].style.display = "";
            } else {
                divs[i].style.display = "none";
            }
        }
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #sectionItems .baList .baList-item", function() {
        updateSections($(this), true);
    });
    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #sectionList .baBreadcrumb-subMenu .subMenuEditButton", function() {
        $(this).parents("li.baBreadcrumb-link").trigger("click");
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #divisionItems input.form-control", function() {
        updateDivisions(true);
    });
    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #divisionList .baBreadcrumb-subMenu .subMenuEditButton", function() {
        $(this).parents("li.baBreadcrumb-link").trigger("click");
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #groupItems input.form-control", function() {
        updateGroups(true);
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #classItems input.form-control", function() {
        updateClasses(true);
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #branchItems input.form-control", function() {
        updatedBranches(true);
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #activityItems input.form-control", function() {

        if ($(this).is(':checked')) {
            var splRequirementId = $(this).attr("data-splrequirement-id");
            displaySagiaLicenseTypeRequirement(splRequirementId);
        }

        updateActivities(true);
    });

    var displaySagiaLicenseTypeRequirement = function(splRequirementId) {

        if (splRequirementId == '0000') {
            return;
        }
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/sagiaLicenseTypeRequirement/" + splRequirementId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function(data) {
                var jsonData = JSON.parse(data);

                if (jsonData != null) {
                    $('#typeRequirementModal .modal-body').html(jsonData.content);
                    $('#typeRequirementModal').modal('show');

                }

            }
        });
    };


    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal #nextButton", function() {
        $(this).prop("disabled", true);
        $("#businessActivitiesSection #businessActivitiesModal .baBreadcrumb-item").css({ "text-decoration": "none" });
        var visibleSection = $("#businessActivitiesSection #businessActivitiesModal .baModule:visible");
        var visibleSectionId = visibleSection.attr("id");
        visibleSection.hide();
        if (visibleSectionId === "sectionItems") {
            $("#businessActivitiesSection #businessActivitiesModal #sectionList a.baBreadcrumb-link").removeClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #divisionList").css({ "text-decoration": "underline" });
            $("#businessActivitiesSection #businessActivitiesModal #divisionList a.baBreadcrumb-link").addClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #divisionItems").show();
            updateDivisions(true);
        } else if (visibleSectionId === "divisionItems") {
            $("#businessActivitiesSection #businessActivitiesModal #divisionList a.baBreadcrumb-link").removeClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #groupList").css({ "text-decoration": "underline" });
            $("#businessActivitiesSection #businessActivitiesModal #groupList a.baBreadcrumb-link").addClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #groupItems").show();
            updateGroups(true);
        } else if (visibleSectionId === "groupItems") {
            $("#businessActivitiesSection #businessActivitiesModal #groupList a.baBreadcrumb-link").removeClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #classList").css({ "text-decoration": "underline" });
            $("#businessActivitiesSection #businessActivitiesModal #classList a.baBreadcrumb-link").addClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #classItems").show();
            updateClasses(true);
        } else if (visibleSectionId === "classItems") {
            $("#businessActivitiesSection #businessActivitiesModal #classList a.baBreadcrumb-link").removeClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #branchList").css({ "text-decoration": "underline" });
            $("#businessActivitiesSection #businessActivitiesModal #branchList a.baBreadcrumb-link").addClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #branchItems").show();
            updatedBranches(true);
        } else if (visibleSectionId === "branchItems") {
            $("#businessActivitiesSection #businessActivitiesModal #branchList a.baBreadcrumb-link").removeClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #activityList").css({ "text-decoration": "underline" });
            $("#businessActivitiesSection #businessActivitiesModal #activityList a.baBreadcrumb-link").addClass("active");
            $("#businessActivitiesSection #businessActivitiesModal #activityItems").show();
            updateActivities(true);
        } else if (visibleSectionId === "activityItems") {
            SAGIA.license.apply.updateIsicTable();
            $(".modal-backdrop").remove();
            $('#businessActivitiesSection #businessActivitiesModal').modal('toggle');
            $("#businessActivitiesSection").scrollIntoView();
        }
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesTable .deleteFromBusinessActivities", function() {
        var trParent = $(this).parents("tr");
        trParent.remove();
        SAGIA.license.businessActivities.selectedActivities.forEach(function(activity, index, arrayObject) {
            if (activity.activityId == trParent.find("td span.activityId").text()) { //it's string or number
                arrayObject.splice(index, 1);
                $("#businessActivitiesSection #businessActivitiesModal #activityItems input.form-control[id='activity" +
                    activity.sectionId + activity.divisionId + activity.groupId + activity.classId + activity.branchId + activity.activityId +
                    "']:checked").prop("checked", false);
            }
        });

        if (SAGIA.license.apply.data) {
            SAGIA.license.apply.data.businessActivities = SAGIA.license.businessActivities;
        }

        if ($("#businessActivitiesSection #businessActivitiesTable tbody tr").length === 0) {
            $("#businessActivitiesSection #businessActivitiesTable").hide();
            $("#businessActivitiesSection #noBusinessActivitiesSelected").show();
        }
    });

    $(document).on("click", "#businessActivitiesSection #businessActivitiesModal .baBreadcrumb-item", function() {
        if ($(this).find(">a").hasClass("active")) {
            return;
        }
        $("#businessActivitiesSection #businessActivitiesModal .baBreadcrumb-item").css({ "text-decoration": "none" });
        $(this).css({ "text-decoration": "underline" });
        $("#businessActivitiesSection #businessActivitiesModal .baModule").hide();
        var visibleItem = $(this).attr("id");
        if (visibleItem === "sectionList") {
            updateSections(null, true);
        } else if (visibleItem === "divisionList") {
            updateDivisions(true);
        } else if (visibleItem === "groupList") {
            updateGroups(true);
        } else if (visibleItem === "classList") {
            updateClasses(true);
        } else if (visibleItem === "branchList") {
            updatedBranches(true);
        } else if (visibleItem === "activityList") {
            updateActivities(true);
        }
    });
});