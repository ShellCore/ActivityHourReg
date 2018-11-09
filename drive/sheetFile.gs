// Id de la hoja de registros
var SPREADSHEET_ID = "16xHIzeIwORDAP7UmGKBDKS9ZMX-J9GBjAmczjCUjSnY";
var sheet = SpreadsheetApp.openById(SPREADSHEET_ID);
var data = sheet.getDataRange().getValues();

// Fila inicial donde comienzan los registros de datos
var ROW_INITIAL_DAY = 6;
var COLUMN_ID = "B";
var COLUMN_COMMENTS = "H";

function saveData(request) {
    try {
        putUserClientData(request.client);
        for (var i in request.month) {
            var day = request.month[i];
            putDayInFormat(request.user, day);
        }
    } catch (ex) {
        throw (ex)
    }
}

function putUserClientData(clientData) {
    if (typeof clientData === 'undefined') {
        throw ("client not defined in json request");
    }

    if (typeof clientData.client === 'undefined'
            || typeof clientData.company === 'undefined') {
        throw ("client data or company not found in json request");
    }
    
    sheet.getRange("C2:C3")
            .setValues([
                [clientData.client],
                [clientData.company]
            ]);
    sheet.getRange("C41")
            .setValue(clientData.client + " / Gerente de cuenta");
}

function putDayInFormat(user, day) {
    if (typeof user === 'undefined') {
        throw ("user not found in json request");
    }
    if (typeof day === 'undefined') {
        throw ("day not defined in json request");
    }
    if (typeof user.userId === 'undefined'
            || typeof user.name === 'undefined') {
        throw ("user data incomplete");
    }
    if (typeof day.begin === 'undefined'
            || typeof day.food === 'undefined'
            || typeof day.work === 'undefined'
            || typeof day.end === 'undefined'
            || typeof day.comments === 'undefined') {
        throw ("day data incomplete");
    }

    var row = obtainRow(new Date(day.day));
    if (row == 0) {
        throw ("Date not found in file: " + day.day);
    }
    var range = "B" + row + ":H" + row;
    Logger.log(range);
    sheet.getRange(range)
            .setValues([[
                user.userId,
                user.name,
                day.begin,
                day.food,
                day.work,
                day.end,
                day.comments
            ]]);
}

function obtainRow(day) {
    for (var fila = 0; fila < 31; fila++) {
        var row = fila + ROW_INITIAL_DAY;
        var cell = new Date(data[row][0]);
        if (getDay(day) == getDay(cell)) {
            return row + 1;
        }
    }
    return 0;
}