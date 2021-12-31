var SAGIA = SAGIA || {};
SAGIA.i18n = SAGIA.i18n || {};

SAGIA.dataTablesArabic = {
    "sProcessing":   "جارٍ التحميل...",
    "sLengthMenu":   "أظهر _MENU_ مدخلات",
    "sZeroRecords":  "لم يعثر على أية سجلات",
    "sInfo":         "إظهار _START_ إلى _END_ من أصل _TOTAL_ مدخل",
    "sInfoEmpty":    "يعرض 0 إلى 0 من أصل 0 سجل",
    "sInfoFiltered": "(منتقاة من مجموع _MAX_ مُدخل)",
    "sInfoPostFix":  "",
    "sSearch":       "ابحث:",
    "sUrl":          "",
    "oPaginate": {
        "sFirst":    "الأول",
        "sPrevious": "السابق",
        "sNext":     "التالي",
        "sLast":     "الأخير"
    }
};

function utf8To16(stringValue) {
    var _escape = function(s) {
        function q(c) {
            c = c.charCodeAt();
            return '%' + (c<16 ? '0' : '') + c.toString(16).toUpperCase();
        }
        return s.replace(/[\x00-),:-?[-^`{-\xFF]/g, q);
    };
    try {
        return decodeURIComponent(_escape(stringValue));
    } catch (URIError) {
        //include invalid character, cannot convert
        return stringValue;
    }
};

function getI18nText(code) {
    var i18n = SAGIA.i18n;
    if (i18n[code]) {
        //return decodeURI(escape(i18n[code]));
        //return decodeURIComponent(encodeURIComponent(i18n[code]));
        return utf8To16(i18n[code]);
    } else {
        console.warn("Required I18N property " + code + " wasn't found");
        return code;
    }
}
