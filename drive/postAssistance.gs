function doPost(request) {
    if (typeof request === 'undefined') {
        return sendResponse(400, "No JSON request found")
    }
    
    var contents = JSON.parse(request.postData["contents"]);
    if (contents == null) {
        return sendResponse(400, "No JSON request content found")
    }
    
    var request = Request(contents);
    if (saveData(contents)) {
        return sendResponse(200, "Data saved");
    } else {
        return sendResponse(400, "Data couldn't save in file");
    }
}

function sendResponse(code, msg) {
    var response = {
        result: code,
        message: msg
    };
    
    return ContentService.createTextOutput(JSON.stringify(response))
            .setMimeType(ContentService.MimeType.JSON);
}