// Id de la hoja de registros
var SPREADSHEET_ID = "1yjMoG5Pu4sSbdgmc2nVgyT0x5oBQxlS-g8TcIOvH6TI";
var sheet = SpreadsheetApp.openById(SPREADSHEET_ID);
var data = sheet.getDataRange().getValues();

// Fila inicial donde comienzan los registros de datos
var CELL_ASSIGN_KEY = "C6";
var CELL_USER = "C24";
var CELL_CLIENT = "C25";
var CELL_CONTACT = "C26";

var BEGIN_DATA_ROW = 9;


function saveData(request) {
    try {
        putData(request.foop3, request.user, request.client);
        putUserData(request.user);
        putClientData(request.client);
    } catch (ex) {
        throw (ex)
    }
}

function cleanData() {
    sheet.getRange(CELL_ASSIGN_KEY).clearContent();
    sheet.getRange(CELL_USER).clearContent();
    sheet.getRange(CELL_CLIENT).clearContent();
    sheet.getRange(CELL_CONTACT).clearContent();
    sheet.getRange("A9:L22").clearContent();
}

function putData(foop3, user, client) {
    if (foop3 === 'undefined') {
        throw ("FOOP 003 data not found in request");
    }

    try {
        for (var i in foop3) {
            var data = foop3[i];
            putDataLine(data, user, client, Number(i));
        }
    } catch (ex) {
        throw (ex);
    }
}

function putDataLine(data, user, client, row) {
    if (data.num === 'undefined') {
        throw ("Number data not found in request");
    }
    if (data.description === 'undefined') {
        throw ("Description data not found in request");
    }
    if (data.app === 'undefined') {
        throw ("App data not found in request");
    }
    if (data.begin === 'undefined') {
        throw ("Begin data not found in request");
    }
    if (data.end === 'undefined') {
        throw ("End data not found in request");
    }
    if (data.scheduledHour === 'undefined') {
        throw ("Scheduled Hour data not found in request");
    }
    if (data.appliedHour === 'undefined') {
        throw ("Applied Hour data not found in request");
    }
    
    var rangeRow = row + BEGIN_DATA_ROW;
    var range = "A" + rangeRow + ":L" + rangeRow;
    sheet.getRange(range)
            .setValues([[
                data.num,
                data.description,
                "Construcción",
                "Urgente",
                client.client,
                "Atendido",
                data.app,
                user.name,
                data.begin,
                data.end,
                data.scheduledHour,
                data.appliedHour
            ]]);
}

function putUserData(user) {
    if (user === 'undefined') {
        throw ("User data not found in request");
    }
    
    sheet.getRange(CELL_USER).setValue(user.name);
}

function putClientData(client) {
    if (client === 'undefined') {
        throw ("Client data not found in request");
    }
    
    sheet.getRange(CELL_ASSIGN_KEY).setValue(client.assignKey);
    sheet.getRange(CELL_CLIENT).setValue(client.client);
    sheet.getRange(CELL_CONTACT).setValue(client.contact);
}