function hasTermsAndConditionsError(json){
    return $.each(JSON.parse(json),function (i,item) {
        if(item.field === 'termsAndConditionsChecked')
            return true;
    })
}

function validateTermsAndConditions(selector){
    var $termsCheckbox = $(selector);
    if (!$termsCheckbox.is(":checked")) {
        $termsCheckbox.parents('.form-item').find('.help-block').text(getI18nText("terms.accept.text"));
        return false;
    }else{
        $termsCheckbox.parents('.form-item').find('.help-block').text('');
        return true;
    }
}
