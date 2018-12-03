function doPost(request) {
    if (typeof request === 'undefined') {
        return sendResponse(400, "No JSON request found")
    }
    
    var contents = JSON.parse(request.postData["contents"]);
    if (contents == null) {
        return sendResponse(400, "No JSON request content found")
    }
    
    try {
        saveData(contents);
        var userId = getUserId(contents);
        var date = new Date();
        var url = createPdfPath(userId, date);
        cleanData();
        return sendResponse(200, url);
    } catch (ex) {
        return sendResponse(400, ex);
    }
}

function sendResponse(res, msg) {
    var response = {
        result: res,
        message: msg
    };
    
    return ContentService.createTextOutput(JSON.stringify(response))
            .setMimeType(ContentService.MimeType.JSON);
}