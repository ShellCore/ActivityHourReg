// Id de la hoja de registros
var SPREADSHEET_ID = "17AMg2Tbm0hGJSpMN3cpQkXtPXuJANN5MudoVmA_LLbc";
var sheet = SpreadsheetApp.openById(SPREADSHEET_ID);
var data = sheet.getDataRange().getValues();

// Fila inicial donde comienzan los registros de datos
var CELL_ASSIGN_KEY = "C8";
var CELL_USER = "A12";
var CELL_JOB = "C12";
var CELL_DEPARTMENT = "D12";
var CELL_CLIENT_01 = "F12";
var CELL_CLIENT_02 = "G12";
var CELL_CLIENT_03 = "H12";
var CELL_CLIENT_04 = "I12";
var CELL_USER_DEVELOP = "B24";
var CELL_CONTACT = "B27";

function saveData(request) {
    try {
        putFoop1Data(request.foop1);
        putUserData(request.user);
        putClientData(request.client);
    } catch (ex) {
        throw (ex)
    }
}

function cleanData() {
    sheet.getRange("C8").clearContent();
    sheet.getRange("A12:I12").clearContent();
    sheet.getRange("B24").clearContent();
    sheet.getRange("B27").clearContent();
}

function putFoop1Data(foop1) {
    if (foop1 === 'undefined') {
        throw ("FOOP 001 data not found in request");
    }
    
    sheet.getRange(CELL_JOB).setValue(foop1.job);
    sheet.getRange(CELL_DEPARTMENT).setValue(foop1.department);
}

function putUserData(user) {
    if (user === 'undefined') {
        throw ("User data not found in request");
    }
    
    sheet.getRange(CELL_USER).setValue(user.name);
    sheet.getRange(CELL_USER_DEVELOP).setValue(user.name);
}

function putClientData(client) {
    if (client === 'undefined') {
        throw ("Client data not found in request");
    }
    
    sheet.getRange(CELL_ASSIGN_KEY).setValue(client.assignKey)
    sheet.getRange(CELL_CLIENT_01).setValue(client.client);
    sheet.getRange(CELL_CLIENT_02).setValue(client.client);
    sheet.getRange(CELL_CLIENT_03).setValue(client.client);
    sheet.getRange(CELL_CLIENT_04).setValue(client.client);
    sheet.getRange(CELL_CONTACT).setValue(client.contact);
}