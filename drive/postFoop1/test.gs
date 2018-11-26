function testSaveData() {
    try {
        var jsonString = '{"foop1" : {"job" : "Ingeniero III","department" : "Sistemas"}, "client" : {"client" : "David Márquez Hernández","contact" : "Elsa Paula Gutiérrez","assignKey" : "FD1801-ASIGIOAN"},"user" : {"name" : "Cesar Morales Garduño","userId" : "MOGC"}}';
        var json = JSON.parse(jsonString);
        saveData(json);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testCreatePDF() {
    try {
        var userId = "MOGC";
        var date = new Date();
        
        var path = createPdfFile(userId, date);
        Logger.log(path);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testGetShareUrl() {
    var path = "FOOP 001 MOGC 2018-11.pdf";
    Logger.log(path);
    try {
        var url = getFileUrl(path);
        Logger.log(url);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testObtenerNombreArchivo() {
    var userId = "MOGC";
    var dateStr = new Date();
    var fileName = obtenerNombreArchivo(userId, dateStr);
    Logger.log(fileName)
}

function testCleanData() {
    cleanData();
}