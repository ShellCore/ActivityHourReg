// Constantes
var FOLDER_NAME = "PDF_FILES";

// Id de la hoja de registros
var SPREADSHEET_ID = "16xHIzeIwORDAP7UmGKBDKS9ZMX-J9GBjAmczjCUjSnY";
var sheet = SpreadsheetApp.openById(SPREADSHEET_ID);

function createPdfFile(userId, date) {
    var nombreArchivo = obtenerNombreArchivo(userId, date);
    Logger.log(nombreArchivo);
    var pdf = DriveApp.getFileById(SPREADSHEET_ID)
            .getBlob()
            .setName(nombreArchivo)
            .getAs('application/pdf');
    var folder = null;
    try {
        folder = DriveApp.getFoldersByName(FOLDER_NAME)
                .next();
    } catch (e) {
        folder = DriveApp.createFolder(FOLDER_NAME);
    }
    folder.createFile(pdf);
    return nombreArchivo;
}

function getFileUrl(path) {
    var files = DriveApp.getFilesByName(path);
    if (files.hasNext()) {
        var file = files.next();
        file.setSharing(DriveApp.Access.ANYONE_WITH_LINK, DriveApp.Permission.VIEW);
        var url = "http://drive.google.com/uc?export=view&id=" + file.getId();
        return url;
    } else {
        throw ("File not found: " + path);
    }
}

function obtenerNombreArchivo(userId, date) {
  var cell = sheet.getDataRange().getCell(4, 3);
  var fechaArchivo = cell.getDisplayValue();
  var fechaFormatted = formatYearMonth(date);
  var nombreArchivo = "Registro Asistencia " + userId + " " + fechaFormatted + ".pdf";
  return nombreArchivo;
}
