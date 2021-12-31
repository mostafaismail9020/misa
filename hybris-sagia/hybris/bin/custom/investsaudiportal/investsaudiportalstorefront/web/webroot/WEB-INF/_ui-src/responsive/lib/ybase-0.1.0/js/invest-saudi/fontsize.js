var _fontCookieName = 'isfs', containers = document.querySelectorAll('main div,main p,main span');
setFont();
function setFont() {
    var sz = getCookie(_fontCookieName);
    if (sz != null && sz != '') {
        var p = containers;
        for (i = 0; i < p.length; i++) {
            p[i].style.fontSize = sz + "px";
        }
    }
}

var min = 12; var max = 22; var original = 16;
function increaseFontSize() {
    var p = containers;
    for (i = 0; i < p.length; i++) {
        if (p[i].style.fontSize) {
            var s = parseInt(p[i].style.fontSize.replace("px", ""));
        } else {
            var s = original;
        }
        if (s != max) {
            s += 1;
        }
        p[i].style.fontSize = s + "px"
    }
    setCookie(_fontCookieName, s, 30);
}
function decreaseFontSize() {
    var p = containers;
    for (i = 0; i < p.length; i++) {
        if (p[i].style.fontSize) {
            var s = parseInt(p[i].style.fontSize.replace("px", ""));
        } else {
            var s = original;
        }
        if (s != min) {
            s -= 1;
        }
        p[i].style.fontSize = s + "px"
    }
    setCookie(_fontCookieName, s, 30);
}
function resetFontSize() {
    var p = containers;
    for (i = 0; i < p.length; i++) {
        p[i].style.fontSize = original + "px"
    }
    setCookie(_fontCookieName, original, 30);
}