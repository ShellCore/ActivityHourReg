// Id de la hoja de registros
var SPREADSHEET_ID = "1M4tzNIFiNVS5WJlz4JKhPGtNuAksCPNQWYliCaldJZM";
var sheet = SpreadsheetApp.openById(SPREADSHEET_ID);
var data = sheet.getDataRange().getValues();

// Fila inicial donde comienzan los registros de datos
var CELL_ASSIGN_KEY = "L10";
var CELL_USER = "L15";
var CELL_CLIENT = "D22";
var CELL_APPLICATION = "D24";
var CELL_DESC = "D29";
var CELL_DESC_LARGE = "D31";
var CELL_JUST = "D33";
var CELL_CONTACT_BEGIN = "B66";
var CELL_CONTACT_END = "J66";

function saveData(request) {
    try {
        putData(request.foop2);
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
    sheet.getRange(CELL_APPLICATION).clearContent();
    sheet.getRange(CELL_DESC).clearContent();
    sheet.getRange(CELL_DESC_LARGE).clearContent();
    sheet.getRange(CELL_JUST).clearContent();
    sheet.getRange(CELL_CONTACT_BEGIN).clearContent();
    sheet.getRange(CELL_CONTACT_END).clearContent();
}

function putData(foop2) {
    if (foop2 === 'undefined') {
        throw ("FOOP 002 data not found in request");
    }
    
    sheet.getRange(CELL_APPLICATION).setValue(foop2.application);
    sheet.getRange(CELL_DESC).setValue(foop2.description);
    sheet.getRange(CELL_DESC_LARGE).setValue(foop2.descriptionLarge);
    sheet.getRange(CELL_JUST).setValue(foop2.justification);
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
    sheet.getRange(CELL_CONTACT_BEGIN).setValue(client.contact);
    sheet.getRange(CELL_CONTACT_END).setValue(client.contact);
}