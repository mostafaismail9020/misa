function setColorForDraftRow($row, action) {
    if (action == '01') {
        setColorForNewRow($row);
    } else if (action == '02') {
        setColorForModifiedRow($row);
    }
}

function setColorForNewRow($row) {
    $row.css('background-color', '#daecde');
}

function setColorForModifiedRow($row) {
    $row.css('background-color', '#f5fdf4');
}

function removeBase64prefix(mimeType,content) {
    var prefixStr = 'data:'+mimeType+';base64,';
    var index = content.indexOf(prefixStr);
    if(index!=-1)
    {
        content = content.substring(index+prefixStr.length);
    }
    return content;
};