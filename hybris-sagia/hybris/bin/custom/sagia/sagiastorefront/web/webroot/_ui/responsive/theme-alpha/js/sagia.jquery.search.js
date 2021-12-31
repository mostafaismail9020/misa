// best jquery ever
function filterHistory(e) {
    // Declare variables
    var filter, ul, li, a, i;
    filter = e.value.toUpperCase();
    ul = document.getElementById("history-list");
    li = ul.getElementsByTagName('li');

    // Loop through all list items, and hide those who don't match the search query
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

function filterHistoryWithinTable(e,tableId) {
    // Declare variables
    var filter, rows, i;
    filter = e.value.toUpperCase();
    rows = $("#"+tableId).find('tr');

    // Loop through all list items, and hide those who don't match the search query
    for (i = 0; i < rows.length; i++) {
        // filteringEl = rows[i].getElementsByTagName(filteringElement)[0];
        if (rows[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}

function scrollToSelectedElement(container,element){
    container.scrollTop(
        element.offset().top - container.offset().top + container.scrollTop()
    );
}