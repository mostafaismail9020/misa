ACC.brandpolicy = {

    _autoload: [
        ["brandpolicy", $(".pageLabel--about-brandpolicy").length > 0]
    ],

    brandpolicy: function () {
        window.toggleCheckbox = function(element) {
            debugger;
            if (element.checked){
                document.getElementById('js-brand-policy-logos').classList.remove('d-none');
                document.getElementById('brand-policy-logos').classList.add('d-none');
            }
            else{
                document.getElementById('js-brand-policy-logos').classList.add('d-none');
                document.getElementById('brand-policy-logos').classList.remove('d-none');
            }
        }
    }

};