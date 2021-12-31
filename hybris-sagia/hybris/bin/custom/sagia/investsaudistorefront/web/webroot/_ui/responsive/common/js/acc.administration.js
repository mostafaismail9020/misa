ACC.administration = {

    _autoload: [
        "addNewChildUnitFormSubmit",
        "addNewUserFormSubmit",
        "manageUserActions",
        "disableUnitDropdown"
    ],

    manageUserActions: function () {
        $(document).on('click', '#deleteUser',
            function (event) {
                event.preventDefault();
                ACC.administration.handleManageUserAction('close-childAccount');
            })
        $(document).on('click', '#lockUser',
            function (event) {
                event.preventDefault();
                ACC.administration.handleManageUserAction('lock-account');
            })
        $(document).on('click', '#unLockUser',
            function (event) {
                event.preventDefault();
                ACC.administration.handleManageUserAction('unlock-account');
            })
    },

    handleManageUserAction: function (actionUrl) {
        ACC.administration.clearGlobalAlerts();
        var selected = [];
        $("input[name='b2bUsers'][type='checkbox']:checked").each(function () {
            selected.push($(this).attr('id'));
        });
        if (selected.length == 0) {
            ACC.administration.displayGlobalAlert({type: 'error', message: $('#no-account-selected').innerText});
        } else {
            ACC.administration.executeUserOperation(actionUrl, selected);
        }

        console.log('selected user ids', selected);
    },

    addNewChildUnitFormSubmit: function () {
        $(document).on('click', '#addChildUnit',
            function (event) {
                event.preventDefault();
                ACC.administration.formPostAction('?childAdded=true');
            });
    },

    formPostAction: function (successRedirectUrl) {
        var error = false;
        var form = $('#newChildUnitForm');
        if (form.find('input[name=uid]').val() == "" || form.find('input[name=uid]').val() == null) {
            form.find('#NotEmpty-AddChildUnit-uid')
                .html('Please fill all mandatory fields').show();
            error = true;
        }
        if (form.find('input[name=name]').val() == "" || form.find('input[name=name]').val() == null) {
            form.find('#NotEmpty-AddChildUnit-name')
                .html('Please fill all mandatory fields').show();
            error = true;
        }

        if (error) {
            return false;
        } else {
            form.submit();
        }

    },

    addNewUserFormSubmit: function () {
        $(document).on('click', '#addNewUserSubmit',
            function (event) {
                event.preventDefault();
                ACC.administration.formPostNewUserAction('?addNewUser=true');
            });
    },

     disableUnitDropdown: function () {
            $(document).on('click', '.userRole',
                function (event) {
                    var selectedRadio = $(this).attr('value');
                    if('BDUserGroup' == selectedRadio || 'WOBDUserGroup' == selectedRadio){
                        $('#parentUnit').attr('disabled', false);
                    }else{
                        $('#parentUnit').attr('disabled', true);
                    }

                });
        },

    formPostNewUserAction: function (successRedirectUrl) {
        var error = false;
        var form = $('#newUserForm');

        if (form.find('select[name=title]').val() == "" || form.find('select[name=title]').val() == null) {
            form.find('#NotEmpty-AddNewUserForm-title')
                .html('Please fill all mandatory fields').show();

            error = true;
        }

        if (form.find('input[name=firstName]').val() == "" || form.find('input[name=firstName]').val() == null) {
            form.find('#NotEmpty-AddNewUserForm-firstName')
                .html('Please fill all mandatory fields').show();
            error = true;
        }

        if (form.find('input[name=lastName]').val() == "" || form.find('input[name=lastName]').val() == null) {
            form.find('#NotEmpty-AddNewUserForm-lastName')
                .html('Please fill all mandatory fields').show();
            error = true;
        }

        if (form.find('input[name=email]').val() == "" || form.find('input[name=email]').val() == null) {
            form.find('#NotEmpty-AddNewUserForm-email')
                .html('Please fill all mandatory fields').show();
            error = true;
        } else {
            if (!ACC.administration.validateContactEmail(form.find('input[name=email]').val())) {
                form.find('#NotEmpty-AddNewUserForm-email')
                    .html('Please enter valid format email address').show();
                error = true;
            }
        }

        if (form.find('input[name=mobileNumber]').val() == "" || form.find('input[name=mobileNumber]').val() == null) {
            form.find('#NotEmpty-AddNewUserForm-mobileNumber')
                .html('Please fill all mandatory fields').show();
            error = true;
        }

        if (form.find('select[name=parentUnit]').val() == "" || form.find('select[name=parentUnit]').val() == null) {
            form.find('#NotEmpty-AddNewUserForm-parentUnit')
                .html('Please fill all mandatory fields').show();
            error = true;
        }

        if (!form.find(("input[type='radio'][name='role']:checked")).val()) {
            form.find('#NotEmpty-AddNewUserForm-userGroups')
                .html('Please fill all mandatory fields').show();
            error = true;
        }


        if (error) {
//        ACC.administration.displayGlobalAlert({type: 'error', message: "test"});
//
//        ACC.administration.displayGlobalAlert({type: '', message: "test"});
            return false;
        } else {
            form.submit();

        }

    },
    validateContactEmail: function (emailValue) {
        var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return filter.test(emailValue);
    },
    displayGlobalAlert: function (options) {
        var alertTemplateSelector;

        switch (options.type) {
            case 'error':
                alertTemplateSelector = '#global-alert-danger-template';
                break;
            case 'warning':
                alertTemplateSelector = '#global-alert-warning-template';
                break;
            default:
                alertTemplateSelector = '#global-alert-info-template';
        }

        if (typeof options.message !== 'undefined') {
            $('#global-alerts').append($(alertTemplateSelector).tmpl({message: options.message}));
        }

        if (typeof options.messageId !== 'undefined') {
            $('#global-alerts').append($(alertTemplateSelector).tmpl({message: $('#' + options.messageId).text()}));
        }
        $(".closeAccAlert").on("click", function () {
            $(this).parent('.getAccAlert').remove();
        });
    },
    clearGlobalAlerts: function() {
        $('#global-alerts').empty();
    },
    executeUserOperation : function (actionUrl, userIds) {
        $.ajax({
            url: actionUrl,
            type: 'POST',
            data: JSON.stringify(userIds),
            dataType: "json",
            contentType: "application/json",
            processData: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                $("input[name='b2bUsers'][type='checkbox']:checked").each(function() {
                    $(this).prop('checked', false);
                });
                $( data ).each(function( index ) {
                    if(data[index].status === 'success') {
                        ACC.administration.displayGlobalAlert({ message: data[index].message});
                        if(data[index].userStatus === 'true') {
                            document.getElementById('status-'+data[index].uid).innerText = $('#user-active').text();
                        } else if(data[index].userStatus === 'false') {
                            document.getElementById('status-'+data[index].uid).innerText = $('#user-inactive').text();
                        } else {
                            document.getElementById('status-'+data[index].uid).innerText = $('#user-disabled').text();
                        }

                    } else {
                        ACC.administration.displayGlobalAlert({type: 'error',message: data[index].message});
                    }
                });
            },
            error: function(data) {
                ACC.investsaudicustomerticketing.processErrorResponse(data);
            }
        });
    }
};