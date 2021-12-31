ACC.login = {

    _autoload: [
        "disableLoginButtonWhenClicked"
    ],

    disableLoginButtonWhenClicked: function () {
        $('#loginForm').on('submit', function () {
            $(this).find('button[type=submit]').prop("disabled", true);
        })
    }
};
